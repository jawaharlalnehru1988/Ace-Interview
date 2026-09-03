package com.example.data.repository

import com.example.data.local.database.AceInterviewDatabase
import com.example.data.local.database.SampleQuestionData
import com.example.data.local.entity.QuestionAttemptEntity
import com.example.data.local.entity.QuizSessionEntity
import com.example.data.local.entity.toDomain
import com.example.domain.model.DsaTopic
import com.example.domain.model.InterviewTrack
import com.example.domain.model.Question
import com.example.domain.model.TechnicalCategory
import com.example.domain.model.TodayTraining
import com.example.domain.model.UserDashboard
import com.example.domain.model.UserProfile
import com.example.domain.model.WeakArea
import com.example.domain.repository.InterviewRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
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

    override fun getUserDashboard(): Flow<UserDashboard> = flow {
        // Structured dashboard data ready to bind to database in subsequent iterations
        emit(
            UserDashboard(
                readinessScore = 68,
                readinessLevel = "Mid-to-Senior Ready",
                questionsCompleted = 142,
                targetQuestions = 500,
                currentStreakDays = 7,
                accuracyPercentage = 84,
                weakAreas = listOf(
                    WeakArea(
                        id = "wa-1",
                        topic = "System Design",
                        subtopic = "Database Sharding & Replication",
                        accuracy = 42,
                        recommendation = "Review Master-Slave vs Multi-Leader replication trade-offs and consistent hashing"
                    ),
                    WeakArea(
                        id = "wa-2",
                        topic = "Java",
                        subtopic = "Concurrency & Volatile Memory Model",
                        accuracy = 55,
                        recommendation = "Practice synchronized blocks, CAS operations, and CountDownLatch patterns"
                    ),
                    WeakArea(
                        id = "wa-3",
                        topic = "Spring Boot",
                        subtopic = "Distributed Transaction Management",
                        accuracy = 60,
                        recommendation = "Focus on Saga orchestrator pattern vs 2PC protocol in microservices"
                    )
                ),
                todayTrainings = listOf(
                    TodayTraining(
                        id = "tt-1",
                        title = "Microservices Resiliency Drills",
                        category = "Microservices",
                        questionsCount = 10,
                        estimatedMinutes = 12,
                        isCompleted = false
                    ),
                    TodayTraining(
                        id = "tt-2",
                        title = "System Design Scenario: Rate Limiter",
                        category = "System Design",
                        questionsCount = 5,
                        estimatedMinutes = 15,
                        isCompleted = false
                    ),
                    TodayTraining(
                        id = "tt-3",
                        title = "Java Virtual Threads & Garbage Collection",
                        category = "Java",
                        questionsCount = 8,
                        estimatedMinutes = 10,
                        isCompleted = true
                    )
                )
            )
        )
    }

    override fun getTechnicalCategories(): Flow<List<TechnicalCategory>> = flow {
        emit(
            listOf(
                TechnicalCategory(
                    id = "java",
                    name = "Java",
                    description = "Core language, JVM internals, multithreading, collections, and modern Java 17-21 features.",
                    questionCount = 300,
                    difficulty = "Beginner to Advanced (100+100+100)",
                    badgeText = "Core Language"
                ),
                TechnicalCategory(
                    id = "spring_boot",
                    name = "Spring Boot",
                    description = "IoC container, dependency injection, autoconfiguration, JPA/Hibernate, and actuators.",
                    questionCount = 140,
                    difficulty = "Intermediate",
                    badgeText = "Framework"
                ),
                TechnicalCategory(
                    id = "microservices",
                    name = "Microservices",
                    description = "Service discovery, API gateways, circuit breakers, event-driven design, and Saga patterns.",
                    questionCount = 125,
                    difficulty = "Advanced",
                    badgeText = "Architecture"
                ),
                TechnicalCategory(
                    id = "hld",
                    name = "HLD",
                    description = "High Level Design, scalability, load balancing, caching, CDN, and high availability systems.",
                    questionCount = 95,
                    difficulty = "Senior",
                    badgeText = "System Architecture"
                ),
                TechnicalCategory(
                    id = "lld",
                    name = "LLD",
                    description = "Low Level Design, SOLID principles, design patterns (Creational, Structural, Behavioral), and UML diagrams.",
                    questionCount = 110,
                    difficulty = "Intermediate",
                    badgeText = "Software Design"
                ),
                TechnicalCategory(
                    id = "system_design",
                    name = "System Design",
                    description = "End-to-end distributed system blueprints: chat systems, URL shorteners, search auto-complete, and payment gateways.",
                    questionCount = 90,
                    difficulty = "Senior+",
                    badgeText = "Distributed Systems"
                ),
                TechnicalCategory(
                    id = "security",
                    name = "Security",
                    description = "OAuth 2.0, OpenID Connect, JWT, SQL Injection, CSRF, TLS/mTLS, and API gateway authorization.",
                    questionCount = 75,
                    difficulty = "Intermediate",
                    badgeText = "AppSec"
                ),
                TechnicalCategory(
                    id = "sql",
                    name = "SQL",
                    description = "Query optimization, indexing (B-Tree/Hash), ACID transactions, isolation levels, and complex window functions.",
                    questionCount = 105,
                    difficulty = "Intermediate",
                    badgeText = "Database"
                ),
                TechnicalCategory(
                    id = "angular",
                    name = "Angular",
                    description = "Components, RxJS reactive patterns, dependency injection, change detection strategies, and routing.",
                    questionCount = 85,
                    difficulty = "Intermediate",
                    badgeText = "Frontend"
                ),
                TechnicalCategory(
                    id = "devops",
                    name = "DevOps",
                    description = "Docker containerization, Kubernetes orchestration, CI/CD pipelines, Terraform, and Prometheus monitoring.",
                    questionCount = 90,
                    difficulty = "Intermediate to Advanced",
                    badgeText = "Infrastructure"
                )
            )
        )
    }

    override fun getDsaTopics(): Flow<List<DsaTopic>> = flow {
        emit(
            listOf(
                DsaTopic(
                    id = "arrays",
                    name = "Arrays",
                    description = "Two Pointers, Sliding Window, Prefix Sums, and Matrix traversals.",
                    problemsCount = 45,
                    solvedCount = 6,
                    easyCount = 15,
                    mediumCount = 20,
                    hardCount = 10
                ),
                DsaTopic(
                    id = "strings",
                    name = "Strings",
                    description = "Pattern matching, anagrams, palindrome manipulation, and string hashing.",
                    problemsCount = 35,
                    solvedCount = 4,
                    easyCount = 12,
                    mediumCount = 16,
                    hardCount = 7
                ),
                DsaTopic(
                    id = "linked_list",
                    name = "Linked List",
                    description = "Cycle detection (Floyd's algorithm), reversal, fast-slow pointers, and merging.",
                    problemsCount = 25,
                    solvedCount = 3,
                    easyCount = 8,
                    mediumCount = 12,
                    hardCount = 5
                ),
                DsaTopic(
                    id = "stack",
                    name = "Stack",
                    description = "Monotonic stack, parentheses validation, and expression evaluation.",
                    problemsCount = 28,
                    solvedCount = 2,
                    easyCount = 9,
                    mediumCount = 14,
                    hardCount = 5
                ),
                DsaTopic(
                    id = "queue",
                    name = "Queue",
                    description = "Double-ended queues (Deque), BFS orderings, and circular buffers.",
                    problemsCount = 22,
                    solvedCount = 1,
                    easyCount = 7,
                    mediumCount = 11,
                    hardCount = 4
                ),
                DsaTopic(
                    id = "trees",
                    name = "Trees",
                    description = "Binary trees, BST properties, Lowest Common Ancestor, and Trie structures.",
                    problemsCount = 40,
                    solvedCount = 2,
                    easyCount = 10,
                    mediumCount = 22,
                    hardCount = 8
                ),
                DsaTopic(
                    id = "graphs",
                    name = "Graphs",
                    description = "DFS, BFS, Dijkstra shortest path, topological sorting, and Union-Find.",
                    problemsCount = 38,
                    solvedCount = 0,
                    easyCount = 6,
                    mediumCount = 20,
                    hardCount = 12
                ),
                DsaTopic(
                    id = "recursion",
                    name = "Recursion",
                    description = "Backtracking, permutation generation, subsets, and divide & conquer paradigms.",
                    problemsCount = 26,
                    solvedCount = 0,
                    easyCount = 5,
                    mediumCount = 15,
                    hardCount = 6
                ),
                DsaTopic(
                    id = "dp",
                    name = "Dynamic Programming",
                    description = "1D & 2D memoization, knapsack variants, longest common subsequence, and interval DP.",
                    problemsCount = 48,
                    solvedCount = 0,
                    easyCount = 8,
                    mediumCount = 25,
                    hardCount = 15
                )
            )
        )
    }

    override fun getInterviewTracks(): Flow<List<InterviewTrack>> = flow {
        emit(
            listOf(
                InterviewTrack(
                    id = "java_interview",
                    title = "Java Interview",
                    roleLevel = "Mid-Level",
                    durationMinutes = 45,
                    questionCount = 12,
                    format = "Language Internals & Concurrency",
                    description = "Simulates a round covering JVM memory, garbage collection algorithms, functional streams, and thread synchronization."
                ),
                InterviewTrack(
                    id = "spring_boot_interview",
                    title = "Spring Boot Interview",
                    roleLevel = "Mid-to-Senior",
                    durationMinutes = 45,
                    questionCount = 10,
                    format = "Enterprise Architecture & Best Practices",
                    description = "Evaluates dependency injection lifecycle, Spring Security filter chains, REST API design, and transaction boundaries."
                ),
                InterviewTrack(
                    id = "microservices_interview",
                    title = "Microservices Interview",
                    roleLevel = "Senior Engineer",
                    durationMinutes = 50,
                    questionCount = 8,
                    format = "Distributed Architecture & Scenarios",
                    description = "Focuses on event-driven streaming with Kafka, circuit breaking, distributed tracing, and database per service patterns."
                ),
                InterviewTrack(
                    id = "full_stack_interview",
                    title = "Full Stack Interview",
                    roleLevel = "Senior Full Stack",
                    durationMinutes = 60,
                    questionCount = 14,
                    format = "End-to-End System Integration",
                    description = "Cross-cutting evaluation from Angular front-end state management to Java/Spring Boot backend REST APIs and SQL schema design."
                ),
                InterviewTrack(
                    id = "hld_interview",
                    title = "HLD Interview",
                    roleLevel = "Staff / Principal",
                    durationMinutes = 45,
                    questionCount = 5,
                    format = "High-Level Architectural Design",
                    description = "Architect large-scale distributed platforms: capacity estimations, geo-DNS routing, caching layers, and database partitioning."
                ),
                InterviewTrack(
                    id = "lld_interview",
                    title = "LLD Interview",
                    roleLevel = "Senior Engineer",
                    durationMinutes = 50,
                    questionCount = 6,
                    format = "Object-Oriented Design & Clean Code",
                    description = "Live object modeling: design a Parking Lot, Movie Ticket Booking System, or Elevator system adhering to SOLID principles."
                ),
                InterviewTrack(
                    id = "system_design_interview",
                    title = "System Design Interview",
                    roleLevel = "Senior / Lead",
                    durationMinutes = 60,
                    questionCount = 6,
                    format = "Deep-Dive Trade-offs & Availability",
                    description = "Design mission-critical systems like distributed message queues, video streaming pipelines, and rate limiting engines."
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

    override fun getUserProfile(): Flow<UserProfile> = flow {
        emit(
            UserProfile(
                name = "Alex Morgan",
                targetRole = "Senior Software Engineer",
                targetTimeline = "Target Date: Q4 2026",
                overallLevel = "Senior Candidate (L5)",
                questionsAttempted = 142,
                accuracyPercentage = 84,
                dsaProblemsSolved = 18,
                interviewSessions = 3,
                streakDays = 7
            )
        )
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
    }
}
