package com.example.data.repository

import com.example.data.local.database.AceInterviewDatabase
import com.example.data.local.database.SampleQuestionData
import com.example.data.local.dsa.DsaProblemData
import com.example.data.local.entity.DsaAttemptEntity
import com.example.data.local.entity.QuestionAttemptEntity
import com.example.data.local.entity.QuizSessionEntity
import com.example.data.local.entity.toDomain
import com.example.domain.model.DsaProblem
import com.example.domain.model.DsaTopic
import com.example.domain.model.InterviewTrack
import com.example.domain.model.Question
import com.example.domain.model.TechnicalCategory
import com.example.domain.model.TodayTraining
import com.example.domain.model.UserDashboard
import com.example.domain.model.UserProfile
import com.example.domain.model.WeakArea
import com.example.domain.repository.InterviewRepository
import com.example.util.training.DailyTrainingScheduler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class InterviewRepositoryImpl(
    private val database: AceInterviewDatabase
) : InterviewRepository {

    init {
        CoroutineScope(Dispatchers.IO).launch {
            ensureSampleQuestionsSeeded()
        }
    }

    private suspend fun ensureSampleQuestionsSeeded() {
        try {
            if (database.questionDao().getQuestionCount() < SampleQuestionData.sampleQuestions.size) {
                database.questionDao().insertQuestions(SampleQuestionData.sampleQuestions)
            }
        } catch (_: Exception) {
            // Safe fallback if database is undergoing initial creation
        }
    }

    private fun calculateStreakDays(attempts: List<QuestionAttemptEntity>): Int {
        if (attempts.isEmpty()) return 0
        val oneDayMs = 24 * 60 * 60 * 1000L
        val now = System.currentTimeMillis()
        val attemptDays = attempts.map { it.timestamp / oneDayMs }.toSet().sortedDescending()
        val todayDay = now / oneDayMs
        if (attemptDays.isEmpty() || (todayDay - attemptDays.first()) > 1) {
            return if (attemptDays.contains(todayDay)) 1 else 0
        }
        var streak = 0
        var expectedDay = if (attemptDays.contains(todayDay)) todayDay else todayDay - 1
        for (day in attemptDays) {
            if (day == expectedDay) {
                streak++
                expectedDay--
            } else if (day < expectedDay) {
                break
            }
        }
        return streak.coerceAtLeast(1)
    }

    override fun getUserDashboard(): Flow<UserDashboard> {
        return combine(
            database.quizDao().getAllAttempts().onStart { emit(emptyList()) },
            database.questionDao().getAllQuestions().onStart { ensureSampleQuestionsSeeded(); emit(emptyList()) },
            database.quizDao().getAllSessions().onStart { emit(emptyList()) },
            database.interviewDao().getAllRecordedResponses().onStart { emit(emptyList()) },
            database.dsaDao().getAllAttempts().onStart { emit(emptyList()) }
        ) { attempts, allQuestions, quizSessions, recordedAudios, dsaAttempts ->
            val questionMap = allQuestions.associateBy { it.id }
            val distinctAnsweredIds = attempts.map { it.questionId }.toSet()
            val questionsCompleted = distinctAnsweredIds.size
            val totalAttempts = attempts.size
            val correctAttempts = attempts.count { it.isCorrect }

            val allDsaIds = DsaProblemData.getAll().map { it.id }.toSet()
            val dsaSolvedIds = dsaAttempts.map { it.problemId }.filter { allDsaIds.contains(it) }.toSet()
            val dsaSolvedCount = dsaSolvedIds.size
            val totalDsaProblems = allDsaIds.size

            val accuracyPercentage = if (totalAttempts > 0) {
                ((correctAttempts.toDouble() / totalAttempts) * 100).toInt()
            } else {
                0
            }

            val currentStreakDays = calculateStreakDays(attempts)
            val targetQuestions = 300 // Foundation milestone

            // Dynamic readiness score (0 to 100) combining MCQ + DSA Solved + Accuracy + Streak
            val progressScore = (questionsCompleted.toDouble() / targetQuestions).coerceAtMost(1.0) * 35.0
            val dsaScore = if (totalDsaProblems > 0) {
                (dsaSolvedCount.toDouble() / totalDsaProblems.toDouble()).coerceAtMost(1.0) * 20.0
            } else 0.0
            val accuracyScore = (accuracyPercentage.toDouble() / 100.0) * 35.0
            val streakScore = (currentStreakDays.coerceAtMost(7).toDouble() / 7.0) * 10.0

            val readinessScore = if (questionsCompleted > 0 || dsaSolvedCount > 0) {
                (progressScore + dsaScore + accuracyScore + streakScore).toInt().coerceIn(1, 100)
            } else {
                0
            }

            val readinessLevel = when {
                readinessScore >= 85 -> "Senior Ready"
                readinessScore >= 65 -> "Mid-to-Senior Ready"
                readinessScore >= 40 -> "Intermediate Ready"
                readinessScore > 0 -> "In Progress"
                else -> "Get Started"
            }

            // Real Weak Areas: dynamically evaluated from user's mistakes
            val categoryAttempts = attempts.groupBy { questionMap[it.questionId]?.categoryId ?: "general" }
            val computedWeakAreas = categoryAttempts.mapNotNull { (catId, catAttempts) ->
                val catTotal = catAttempts.size
                val catCorrect = catAttempts.count { it.isCorrect }
                val catAccuracy = (catCorrect * 100) / catTotal
                if (catAccuracy < 75 && catTotal >= 1) {
                    val catName = when (catId) {
                        "java" -> "Java Core"
                        "spring_boot" -> "Spring Boot"
                        "microservices" -> "Microservices"
                        "hld" -> "High Level Design"
                        "lld" -> "Low Level Design"
                        "sql" -> "SQL & Databases"
                        "angular" -> "Angular & Frontend"
                        "security" -> "Security & AppSec"
                        else -> catId.replaceFirstChar { it.uppercase() }
                    }
                    WeakArea(
                        id = "wa-$catId",
                        topic = catName,
                        subtopic = "Diagnostic: $catAccuracy% accuracy ($catCorrect/$catTotal correct)",
                        accuracy = catAccuracy,
                        recommendation = "Review missed questions in $catName practice sessions."
                    )
                } else null
            }.sortedBy { it.accuracy }

            val weakAreas = if (computedWeakAreas.isNotEmpty()) {
                computedWeakAreas.take(3)
            } else if (questionsCompleted > 0) {
                listOf(
                    WeakArea(
                        id = "wa-strong",
                        topic = "Consistent Performance",
                        subtopic = "High accuracy across attempted categories",
                        accuracy = accuracyPercentage,
                        recommendation = "Keep taking advanced drills to discover subtle edge cases."
                    )
                )
            } else {
                listOf(
                    WeakArea(
                        id = "wa-diag-1",
                        topic = "Java & Spring Boot",
                        subtopic = "Core Backend Architecture",
                        accuracy = 0,
                        recommendation = "Practice your first set of MCQs to diagnose personal weak areas."
                    ),
                    WeakArea(
                        id = "wa-diag-2",
                        topic = "System Design (HLD/LLD)",
                        subtopic = "Architecture & Patterns",
                        accuracy = 0,
                        recommendation = "Take a quick design drill to establish your baseline score."
                    ),
                    WeakArea(
                        id = "wa-diag-3",
                        topic = "SQL & Security",
                        subtopic = "Data & AppSec",
                        accuracy = 0,
                        recommendation = "Test your knowledge on indexing, transactions, and OAuth 2.0."
                    )
                )
            }

            // Dynamic Daily Training: Rotating Daily MCQ concept + Rotating Daily Interview concept (5 Audio recordings target)
            val todayTrainings = listOf(
                DailyTrainingScheduler.getTodayMcqTraining(
                    sessions = quizSessions,
                    attempts = attempts
                ),
                DailyTrainingScheduler.getTodayInterviewTraining(
                    audioResponses = recordedAudios
                )
            )

            // Calculate Tricky Questions metrics
            val trickyAttempts = attempts.filter {
                val cat = questionMap[it.questionId]?.categoryId
                cat == "java_tricky" || cat == "js_tricky"
            }
            val trickySolvedIds = trickyAttempts.filter { it.isCorrect }.map { it.questionId }.toSet()
            val trickySolvedCount = trickySolvedIds.size
            val trickyTotalCount = com.example.data.local.questions.JavaTrickyQuestions.getAll().size + com.example.data.local.questions.JsTrickyQuestions.getAll().size
            val trickyAccuracy = if (trickyAttempts.isNotEmpty()) {
                ((trickyAttempts.count { it.isCorrect }.toDouble() / trickyAttempts.size) * 100).toInt()
            } else 0

            UserDashboard(
                readinessScore = readinessScore,
                readinessLevel = readinessLevel,
                questionsCompleted = questionsCompleted,
                targetQuestions = targetQuestions,
                currentStreakDays = currentStreakDays,
                accuracyPercentage = accuracyPercentage,
                weakAreas = weakAreas,
                todayTrainings = todayTrainings,
                dsaSolvedCount = dsaSolvedCount,
                totalDsaProblems = totalDsaProblems,
                trickySolvedCount = trickySolvedCount,
                trickyTotalCount = trickyTotalCount,
                trickyAccuracy = trickyAccuracy
            )
        }
    }

    private val _lastAttemptedConceptId = kotlinx.coroutines.flow.MutableStateFlow<String?>(null)

    override fun getLastAttemptedConceptId(): Flow<String?> = _lastAttemptedConceptId.asStateFlow()

    override fun setLastAttemptedConcept(conceptId: String) {
        _lastAttemptedConceptId.value = conceptId
    }

    override fun getTechnicalCategories(): Flow<List<TechnicalCategory>> {
        val baseCategories = listOf(
            TechnicalCategory(
                id = "java",
                name = "Java",
                description = "Core language, JVM internals, multithreading, collections, and modern Java 17-21 features.",
                questionCount = com.example.domain.model.TechnicalConceptCatalog.javaConcepts.sumOf { it.questionCount },
                difficulty = "${com.example.domain.model.TechnicalConceptCatalog.javaConcepts.size} Concepts",
                badgeText = "Core Language",
                concepts = com.example.domain.model.TechnicalConceptCatalog.javaConcepts
            ),
            TechnicalCategory(
                id = "spring_boot",
                name = "Spring Boot",
                description = "IoC container, dependency injection, autoconfiguration, JPA/Hibernate, and actuators.",
                questionCount = com.example.domain.model.TechnicalConceptCatalog.springBootConcepts.sumOf { it.questionCount },
                difficulty = "${com.example.domain.model.TechnicalConceptCatalog.springBootConcepts.size} Concepts",
                badgeText = "Framework",
                concepts = com.example.domain.model.TechnicalConceptCatalog.springBootConcepts
            ),
            TechnicalCategory(
                id = "microservices",
                name = "Microservices",
                description = "Service discovery, API gateways, circuit breakers, event-driven design, and Saga patterns.",
                questionCount = com.example.domain.model.TechnicalConceptCatalog.microservicesConcepts.sumOf { it.questionCount },
                difficulty = "${com.example.domain.model.TechnicalConceptCatalog.microservicesConcepts.size} Concepts",
                badgeText = "Architecture",
                concepts = com.example.domain.model.TechnicalConceptCatalog.microservicesConcepts
            ),
            TechnicalCategory(
                id = "hld",
                name = "HLD",
                description = "High Level Design, scalability, load balancing, caching, CDN, and high availability systems.",
                questionCount = com.example.domain.model.TechnicalConceptCatalog.hldConcepts.sumOf { it.questionCount },
                difficulty = "${com.example.domain.model.TechnicalConceptCatalog.hldConcepts.size} Concepts",
                badgeText = "System Architecture",
                concepts = com.example.domain.model.TechnicalConceptCatalog.hldConcepts
            ),
            TechnicalCategory(
                id = "lld",
                name = "LLD",
                description = "Low Level Design, SOLID principles, design patterns (Creational, Structural, Behavioral), and UML diagrams.",
                questionCount = com.example.domain.model.TechnicalConceptCatalog.lldConcepts.sumOf { it.questionCount },
                difficulty = "${com.example.domain.model.TechnicalConceptCatalog.lldConcepts.size} Concepts",
                badgeText = "Software Design",
                concepts = com.example.domain.model.TechnicalConceptCatalog.lldConcepts
            ),
            TechnicalCategory(
                id = "system_design",
                name = "System Design",
                description = "End-to-end distributed system blueprints: chat systems, URL shorteners, search auto-complete, and payment gateways.",
                questionCount = com.example.domain.model.TechnicalConceptCatalog.systemDesignConcepts.sumOf { it.questionCount },
                difficulty = "${com.example.domain.model.TechnicalConceptCatalog.systemDesignConcepts.size} Concepts",
                badgeText = "Distributed Systems",
                concepts = com.example.domain.model.TechnicalConceptCatalog.systemDesignConcepts
            ),
            TechnicalCategory(
                id = "security",
                name = "Security & AppSec",
                description = "OAuth 2.0, OpenID Connect, JWT, SQLi, CSRF, TLS 1.3/mTLS, Zero Trust, Post-Quantum Crypto, and container security.",
                questionCount = com.example.domain.model.TechnicalConceptCatalog.securityConcepts.sumOf { it.questionCount },
                difficulty = "${com.example.domain.model.TechnicalConceptCatalog.securityConcepts.size} Concepts",
                badgeText = "AppSec",
                concepts = com.example.domain.model.TechnicalConceptCatalog.securityConcepts
            ),
            TechnicalCategory(
                id = "sql",
                name = "SQL & Database Design",
                description = "Relational modeling, indexing (B-Tree/GIN/Hash), ACID transactions, isolation levels, query optimization, and window functions.",
                questionCount = com.example.domain.model.TechnicalConceptCatalog.sqlConcepts.sumOf { it.questionCount },
                difficulty = "${com.example.domain.model.TechnicalConceptCatalog.sqlConcepts.size} Concepts",
                badgeText = "Database",
                concepts = com.example.domain.model.TechnicalConceptCatalog.sqlConcepts
            ),
            TechnicalCategory(
                id = "angular",
                name = "Angular & Frontend",
                description = "Components, Signals, RxJS reactive patterns, dependency injection, Zoneless change detection, and routing.",
                questionCount = com.example.domain.model.TechnicalConceptCatalog.angularConcepts.sumOf { it.questionCount },
                difficulty = "${com.example.domain.model.TechnicalConceptCatalog.angularConcepts.size} Concepts",
                badgeText = "Frontend",
                concepts = com.example.domain.model.TechnicalConceptCatalog.angularConcepts
            ),
            TechnicalCategory(
                id = "devops",
                name = "DevOps",
                description = "Docker containerization, Kubernetes orchestration, CI/CD pipelines, Terraform, and Prometheus monitoring.",
                questionCount = com.example.domain.model.TechnicalConceptCatalog.devopsConcepts.sumOf { it.questionCount },
                difficulty = "${com.example.domain.model.TechnicalConceptCatalog.devopsConcepts.size} Concepts",
                badgeText = "Infrastructure",
                concepts = com.example.domain.model.TechnicalConceptCatalog.devopsConcepts
            )
        )

        return combine(
            database.quizDao().getAllSessions().onStart { emit(emptyList()) },
            _lastAttemptedConceptId
        ) { sessions, lastAttemptedId ->
            val latestSessionByConcept = sessions
                .groupBy { it.categoryId }
                .mapValues { entry -> entry.value.maxByOrNull { it.completedAt } }

            val effectiveLastAttempted = lastAttemptedId ?: sessions.firstOrNull()?.categoryId

            baseCategories.map { category ->
                val updatedConcepts = category.concepts.map { concept ->
                    val session = latestSessionByConcept[concept.id]
                    concept.copy(
                        userScore = session?.correctCount,
                        totalQuestionsAttempted = session?.totalQuestions,
                        isLastAttempted = (concept.id == effectiveLastAttempted)
                    )
                }

                val scoredConcepts = updatedConcepts.filter { it.hasScore }
                val totalScore = scoredConcepts.sumOf { it.userScore ?: 0 }
                val totalAttempted = scoredConcepts.sumOf { it.totalQuestionsAttempted ?: 0 }
                val hasAnyScore = totalAttempted > 0

                val difficultyText = if (hasAnyScore) {
                    "Score: $totalScore/$totalAttempted • ${updatedConcepts.size} Concepts"
                } else if (updatedConcepts.isNotEmpty()) {
                    "${updatedConcepts.size} Concepts"
                } else {
                    category.difficulty
                }

                category.copy(
                    concepts = updatedConcepts,
                    questionCount = updatedConcepts.sumOf { it.questionCount }.takeIf { it > 0 } ?: category.questionCount,
                    difficulty = difficultyText
                )
            }
        }
    }

    override fun getDsaTopics(): Flow<List<DsaTopic>> {
        return database.dsaDao().getAllAttempts().onStart { emit(emptyList()) }.map { attempts ->
            val solvedProblemIds = attempts.map { it.problemId }.toSet()
            val allProblems = DsaProblemData.getAll()
            val solvedByTopic = allProblems.filter { solvedProblemIds.contains(it.id) }.groupBy { it.topic }

            val topicConfigs = listOf(
                Triple("arrays", "Arrays", "Two Pointers, Sliding Window, Prefix Sums, and Matrix traversals."),
                Triple("strings", "Strings", "Pattern matching, anagrams, palindrome manipulation, and string hashing."),
                Triple("linked_list", "Linked List", "Cycle detection (Floyd's algorithm), reversal, fast-slow pointers, and merging."),
                Triple("stack", "Stack", "Monotonic stack, parentheses validation, and expression evaluation."),
                Triple("queue", "Queue", "Double-ended queues (Deque), BFS orderings, and circular buffers."),
                Triple("trees", "Trees", "Binary trees, BST properties, Lowest Common Ancestor, and Trie structures."),
                Triple("graphs", "Graphs", "DFS, BFS, Dijkstra shortest path, topological sorting, and Union-Find."),
                Triple("recursion", "Recursion", "Backtracking, permutation generation, subsets, and divide & conquer paradigms."),
                Triple("dp", "Dynamic Programming", "1D & 2D memoization, knapsack variants, longest common subsequence, and interval DP.")
            )

            topicConfigs.map { (id, name, desc) ->
                val topicProblems = DsaProblemData.getByTopic(id)
                val solvedList = solvedByTopic[id] ?: emptyList()
                DsaTopic(
                    id = id,
                    name = name,
                    description = desc,
                    problemsCount = topicProblems.size,
                    solvedCount = solvedList.size,
                    easyCount = topicProblems.count { it.difficulty.equals("Easy", ignoreCase = true) },
                    mediumCount = topicProblems.count { it.difficulty.equals("Medium", ignoreCase = true) },
                    hardCount = topicProblems.count { it.difficulty.equals("Hard", ignoreCase = true) }
                )
            }
        }
    }

    override fun getDsaProblems(topicId: String): Flow<List<DsaProblem>> {
        return database.dsaDao().getAllAttempts().onStart { emit(emptyList()) }.map { attempts ->
            val solvedIds = attempts.map { it.problemId }.toSet()
            DsaProblemData.getByTopic(topicId).map { problem ->
                problem.copy(isSolved = solvedIds.contains(problem.id))
            }
        }
    }

    override suspend fun toggleDsaProblemSolved(problemId: String) {
        val attempts = database.dsaDao().getAllAttempts().firstOrNull() ?: emptyList()
        val isAlreadySolved = attempts.any { it.problemId == problemId }
        if (isAlreadySolved) {
            database.dsaDao().deleteAttemptsByProblemId(problemId)
        } else {
            database.dsaDao().insertAttempt(
                DsaAttemptEntity(
                    problemId = problemId,
                    status = "solved",
                    language = "Java",
                    notes = "",
                    attemptedAt = System.currentTimeMillis()
                )
            )
        }
    }

    override fun getInterviewTracks(): Flow<List<InterviewTrack>> = flow {
        emit(
            listOf(
                InterviewTrack(
                    id = "java_interview",
                    title = "Java Interview",
                    roleLevel = "Mid-Level",
                    durationMinutes = 45,
                    questionCount = com.example.data.local.interview.InterviewQuestionCatalog.getQuestionsForTrack("java_interview").size,
                    format = "Language Internals & Concurrency",
                    description = "Simulates a round covering JVM memory, garbage collection algorithms, functional streams, and thread synchronization."
                ),
                InterviewTrack(
                    id = "spring_boot_interview",
                    title = "Spring Boot Interview",
                    roleLevel = "Mid-to-Senior",
                    durationMinutes = 45,
                    questionCount = com.example.data.local.interview.InterviewQuestionCatalog.getQuestionsForTrack("spring_boot_interview").size,
                    format = "Enterprise Architecture & Best Practices",
                    description = "Evaluates dependency injection lifecycle, Spring Security filter chains, REST API design, and transaction boundaries."
                ),
                InterviewTrack(
                    id = "microservices_interview",
                    title = "Microservices Interview",
                    roleLevel = "Senior Engineer",
                    durationMinutes = 50,
                    questionCount = com.example.data.local.interview.InterviewQuestionCatalog.getQuestionsForTrack("microservices_interview").size,
                    format = "Distributed Architecture & Scenarios",
                    description = "Focuses on event-driven streaming with Kafka, circuit breaking, distributed tracing, and database per service patterns."
                ),
                InterviewTrack(
                    id = "full_stack_interview",
                    title = "Full Stack Interview",
                    roleLevel = "Senior Full Stack",
                    durationMinutes = 60,
                    questionCount = com.example.data.local.interview.InterviewQuestionCatalog.getQuestionsForTrack("full_stack_interview").size,
                    format = "End-to-End System Integration",
                    description = "Cross-cutting evaluation from Angular front-end state management to Java/Spring Boot backend REST APIs and SQL schema design."
                ),
                InterviewTrack(
                    id = "hld_interview",
                    title = "HLD (High-Level Design) Interview",
                    roleLevel = "Staff / Principal",
                    durationMinutes = 60,
                    questionCount = com.example.data.local.interview.InterviewQuestionCatalog.getQuestionsForTrack("hld_interview").size,
                    format = "Real-World Distributed Blueprints & Scale",
                    description = "End-to-end distributed system blueprints: Twitter/X, YouTube, Uber, WhatsApp, Stripe, Netflix, Google Search, Kafka, LLM inference serving (vLLM/RAG), and zero-downtime migrations."
                ),
                InterviewTrack(
                    id = "lld_interview",
                    title = "LLD Interview",
                    roleLevel = "Senior Engineer",
                    durationMinutes = 50,
                    questionCount = com.example.data.local.interview.InterviewQuestionCatalog.getQuestionsForTrack("lld_interview").size,
                    format = "Object-Oriented Design, SOLID & Machine Coding",
                    description = "Deep object-oriented modeling, GoF creational/structural/behavioral patterns, thread safety, real-world machine coding (Parking Lot, Splitwise, Elevator, Rate Limiter), and DDD Clean Architecture."
                ),
                InterviewTrack(
                    id = "system_design_interview",
                    title = "System Design Interview",
                    roleLevel = "Senior / Lead",
                    durationMinutes = 60,
                    questionCount = com.example.data.local.interview.InterviewQuestionCatalog.getQuestionsForTrack("system_design_interview").size,
                    format = "Deep-Dive Trade-offs & Availability",
                    description = "Design mission-critical systems like distributed message queues, video streaming pipelines, and rate limiting engines."
                ),
                InterviewTrack(
                    id = "devops_interview",
                    title = "DevOps Interview",
                    roleLevel = "Senior / SRE",
                    durationMinutes = 50,
                    questionCount = com.example.data.local.interview.InterviewQuestionCatalog.getQuestionsForTrack("devops_interview").size,
                    format = "Containerization, K8s & GitOps",
                    description = "Evaluates Linux cgroups/namespaces, Docker layer caching, Kubernetes pod lifecycle, CrashLoopBackOff troubleshooting, and CI/CD pipelines."
                ),
                InterviewTrack(
                    id = "sql_interview",
                    title = "SQL & Database Interview",
                    roleLevel = "Mid-to-Senior",
                    durationMinutes = 45,
                    questionCount = com.example.data.local.interview.InterviewQuestionCatalog.getQuestionsForTrack("sql_interview").size,
                    format = "Indexing, ACID & Query Optimization",
                    description = "Deep dive into B-Tree vs Hash indexing, Covering indexes, Clustered index lookups, MVCC, and SQL transaction isolation levels."
                ),
                InterviewTrack(
                    id = "angular_interview",
                    title = "Angular & Frontend Interview",
                    roleLevel = "Senior / Lead Frontend",
                    durationMinutes = 55,
                    questionCount = com.example.data.local.interview.InterviewQuestionCatalog.getQuestionsForTrack("angular_interview").size,
                    format = "Signals, Zoneless, RxJS & Architecture",
                    description = "Comprehensive modern Angular 16-19+ mastery: Signals, computed & effects, Push-Pull reactivity, Zoneless change detection, RxJS streams, hierarchical DI, NgRx & SignalStore, standalone routing, micro-frontends, and SSR hydration."
                ),
                InterviewTrack(
                    id = "security_interview",
                    title = "Security & AppSec Interview",
                    roleLevel = "Senior AppSec / InfoSec",
                    durationMinutes = 55,
                    questionCount = com.example.data.local.interview.InterviewQuestionCatalog.getQuestionsForTrack("security_interview").size,
                    format = "OAuth 2.0, Zero Trust, Cryptography & AppSec",
                    description = "End-to-end security architecture: OAuth 2.0 PKCE, OIDC, JWT revocation, OWASP Top 10 defenses (SQLi, XSS, SSRF, IDOR), RBAC/ABAC, TLS 1.3 & mTLS, KMS envelope encryption, container security, DevSecOps, and Zero Trust architecture."
                ),
                InterviewTrack(
                    id = "senior_engineer_interview",
                    title = "Senior Engineer Interview",
                    roleLevel = "Lead / Staff",
                    durationMinutes = 60,
                    questionCount = 10,
                    format = "Technical Leadership & System Design",
                    description = "Comprehensive blend of architecture design, troubleshooting production outages, code review standards, and engineering trade-offs."
                )
            )
        )
    }

    override fun getUserProfile(): Flow<UserProfile> {
        return combine(
            database.quizDao().getAllAttempts().onStart { emit(emptyList()) },
            database.questionDao().getAllQuestions().onStart { ensureSampleQuestionsSeeded(); emit(emptyList()) },
            database.dsaDao().getAllAttempts().onStart { emit(emptyList()) },
            database.interviewDao().getAllRecordedResponses().onStart { emit(emptyList()) },
            database.interviewDao().getAllSessions().onStart { emit(emptyList()) }
        ) { attempts, allQuestions, dsaAttempts, recordedAudios, interviewSessions ->
            val distinctAnsweredIds = attempts.map { it.questionId }.toSet()
            val totalAttempts = attempts.size
            val correctAttempts = attempts.count { it.isCorrect }
            val accuracy = if (totalAttempts > 0) ((correctAttempts * 100) / totalAttempts) else 0
            val streak = calculateStreakDays(attempts)

            val dsaSolvedIds = dsaAttempts.map { it.problemId }.toSet()
            val dsaProblemsSolved = dsaSolvedIds.size
            val totalDsa = DsaProblemData.getAll().size
            val dsaProgress = if (totalDsa > 0) (dsaProblemsSolved.toFloat() / totalDsa.toFloat()).coerceIn(0f, 1f) else 0f

            // Dynamic progress for Backend & Frameworks (Java, Spring Boot, Microservices, SQL)
            val backendQuestions = allQuestions.filter { it.categoryId in setOf("java", "spring_boot", "microservices", "sql") }
            val backendSolvedCount = distinctAnsweredIds.count { qId -> backendQuestions.any { it.id == qId } }
            val backendTarget = backendQuestions.size.coerceAtMost(50).coerceAtLeast(15)
            val backendProgress = (backendSolvedCount.toFloat() / backendTarget.toFloat()).coerceIn(0f, 1f)

            // Dynamic progress for System Architecture & HLD (HLD, LLD, Security)
            val archQuestions = allQuestions.filter { it.categoryId in setOf("hld", "lld", "security") }
            val archSolvedCount = distinctAnsweredIds.count { qId -> archQuestions.any { it.id == qId } }
            val archTarget = archQuestions.size.coerceAtMost(40).coerceAtLeast(15)
            val systemDesignProgress = (archSolvedCount.toFloat() / archTarget.toFloat()).coerceIn(0f, 1f)

            // Real completed interview sessions count
            val completedInterviews = interviewSessions.size + (recordedAudios.size / 5).coerceAtLeast(if (recordedAudios.isNotEmpty()) 1 else 0)

            UserProfile(
                name = "Interview Candidate",
                targetRole = "Senior Software Engineer",
                targetTimeline = "Target: L5 / Staff Track",
                overallLevel = when {
                    (dsaProblemsSolved >= 50 && distinctAnsweredIds.size >= 80) -> "Senior Candidate (L5)"
                    (dsaProblemsSolved >= 15 || distinctAnsweredIds.size >= 30) -> "Intermediate Ready"
                    (dsaProblemsSolved > 0 || distinctAnsweredIds.isNotEmpty() || recordedAudios.isNotEmpty()) -> "In Progress"
                    else -> "New Candidate"
                },
                questionsAttempted = distinctAnsweredIds.size,
                accuracyPercentage = accuracy,
                dsaProblemsSolved = dsaProblemsSolved,
                totalDsaProblems = totalDsa,
                interviewSessions = completedInterviews,
                streakDays = streak,
                backendProgress = backendProgress,
                systemDesignProgress = systemDesignProgress,
                dsaProgress = dsaProgress
            )
        }
    }

    override fun getAllQuestions(): Flow<List<Question>> {
        return database.questionDao().getAllQuestions()
            .onStart { ensureSampleQuestionsSeeded() }
            .map { entities -> entities.map { it.toDomain() } }
    }

    override fun getQuestionsByCategory(categoryId: String): Flow<List<Question>> {
        return database.questionDao().getQuestionsByCategory(categoryId)
            .onStart { ensureSampleQuestionsSeeded() }
            .map { entities -> entities.map { it.toDomain() } }
    }

    override suspend fun getQuestionById(id: String): Question? {
        ensureSampleQuestionsSeeded()
        return database.questionDao().getQuestionById(id)?.toDomain()
    }

    override suspend fun seedSampleQuestions() {
        database.questionDao().insertQuestions(SampleQuestionData.sampleQuestions)
    }

    override suspend fun recordQuestionAttempt(
        questionId: String,
        selectedIndex: Int,
        isCorrect: Boolean,
        timeSpentSeconds: Int
    ) {
        database.quizDao().insertAttempt(
            QuestionAttemptEntity(
                questionId = questionId,
                selectedOptionIndex = selectedIndex,
                isCorrect = isCorrect,
                timeSpentSeconds = timeSpentSeconds,
                timestamp = System.currentTimeMillis()
            )
        )
    }

    override suspend fun recordQuizSession(
        categoryId: String,
        totalQuestions: Int,
        correctCount: Int,
        scorePercentage: Int
    ) {
        val now = System.currentTimeMillis()
        database.quizDao().insertSession(
            QuizSessionEntity(
                categoryId = categoryId,
                totalQuestions = totalQuestions,
                correctCount = correctCount,
                scorePercentage = scorePercentage,
                startedAt = now - 60000,
                completedAt = now
            )
        )
        _lastAttemptedConceptId.value = categoryId
    }

    override fun getInterviewQuestionsForTrack(trackId: String): Flow<List<com.example.domain.model.InterviewQuestion>> = flow {
        emit(com.example.data.local.interview.InterviewQuestionCatalog.getQuestionsForTrack(trackId))
    }

    override fun getConceptGroupsForTrack(trackId: String): Flow<List<com.example.domain.model.ConceptInterviewGroup>> = flow {
        emit(com.example.data.local.interview.InterviewQuestionCatalog.getConceptGroupsForTrack(trackId))
    }

    override fun getAudioAnswersForTrack(trackId: String): Flow<Map<String, com.example.domain.model.QuestionAudioAnswer>> {
        return database.interviewDao().getResponsesForTrack(trackId).map { list ->
            list.filter { it.questionId.isNotBlank() && !it.audioFilePath.isNullOrBlank() }
                .associate { entity ->
                    entity.questionId to com.example.domain.model.QuestionAudioAnswer(
                        questionId = entity.questionId,
                        audioFilePath = entity.audioFilePath,
                        audioDurationMs = entity.audioDurationMs,
                        recordedAt = entity.recordedAt
                    )
                }
        }
    }

    override suspend fun saveAudioAnswer(
        questionId: String,
        trackId: String,
        conceptName: String,
        questionText: String,
        shortAnswer: String,
        audioFilePath: String,
        durationMs: Long
    ) {
        database.interviewDao().deleteResponsesForQuestion(questionId)
        database.interviewDao().insertResponse(
            com.example.data.local.entity.InterviewResponseEntity(
                sessionId = 0L,
                trackId = trackId,
                questionId = questionId,
                questionNumber = 0,
                questionText = questionText,
                responseText = "",
                aiFeedback = "",
                score = 0,
                audioFilePath = audioFilePath,
                audioDurationMs = durationMs,
                conceptName = conceptName,
                shortAnswer = shortAnswer,
                recordedAt = System.currentTimeMillis()
            )
        )
    }

    override suspend fun deleteAudioAnswer(questionId: String) {
        database.interviewDao().deleteResponsesForQuestion(questionId)
    }

    override fun getFunctionalProblems(trackId: String): Flow<List<com.example.domain.model.FunctionalProblem>> {
        return database.dsaDao().getAllAttempts().onStart { emit(emptyList()) }.map { attempts ->
            val solvedIds = attempts.map { it.problemId }.toSet()
            val problems = com.example.data.local.functional.FunctionalProblemCatalog.getProblemsByTrack(trackId)
            problems.map { problem ->
                problem.copy(isSolved = solvedIds.contains(problem.id))
            }
        }
    }

    override fun getFunctionalTracks(): Flow<List<com.example.domain.model.FunctionalTrack>> {
        return database.dsaDao().getAllAttempts().onStart { emit(emptyList()) }.map { attempts ->
            val solvedIds = attempts.map { it.problemId }.toSet()
            val baseTracks = com.example.data.local.functional.FunctionalProblemCatalog.getTracks()
            baseTracks.map { track ->
                val trackProblems = com.example.data.local.functional.FunctionalProblemCatalog.getProblemsByTrack(track.id)
                val solvedInTrack = trackProblems.count { solvedIds.contains(it.id) }
                track.copy(solvedCount = solvedInTrack)
            }
        }
    }

    override suspend fun toggleFunctionalProblemSolved(problemId: String) {
        val attempts = database.dsaDao().getAllAttempts().firstOrNull() ?: emptyList()
        val isAlreadySolved = attempts.any { it.problemId == problemId }
        if (isAlreadySolved) {
            database.dsaDao().deleteAttemptsByProblemId(problemId)
        } else {
            database.dsaDao().insertAttempt(
                com.example.data.local.entity.DsaAttemptEntity(
                    problemId = problemId,
                    status = "solved",
                    language = "Functional",
                    notes = "",
                    attemptedAt = System.currentTimeMillis()
                )
            )
        }
    }
}
