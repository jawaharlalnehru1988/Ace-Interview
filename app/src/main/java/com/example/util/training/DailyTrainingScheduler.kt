package com.example.util.training

import com.example.data.local.entity.InterviewResponseEntity
import com.example.data.local.entity.QuestionAttemptEntity
import com.example.data.local.entity.QuizSessionEntity
import com.example.data.local.interview.InterviewQuestionCatalog
import com.example.domain.model.TechnicalConceptCatalog
import com.example.domain.model.TodayTraining
import com.example.domain.model.TrainingType

object DailyTrainingScheduler {

    data class McqStack(
        val domainId: String,
        val displayName: String
    )

    data class InterviewTrackInfo(
        val trackId: String,
        val trackTitle: String
    )

    val mcqStacks = listOf(
        McqStack("java", "Java"),
        McqStack("spring_boot", "Spring Boot"),
        McqStack("microservices", "Microservices"),
        McqStack("hld", "HLD"),
        McqStack("lld", "LLD"),
        McqStack("system_design", "System Design"),
        McqStack("security", "Security & AppSec"),
        McqStack("sql", "SQL & Database"),
        McqStack("angular", "Angular"),
        McqStack("devops", "DevOps")
    )

    val interviewTracks = listOf(
        InterviewTrackInfo("java_interview", "Java Interview"),
        InterviewTrackInfo("spring_boot_interview", "Spring Boot Interview"),
        InterviewTrackInfo("microservices_interview", "Microservices Interview"),
        InterviewTrackInfo("system_design_interview", "System Design Interview"),
        InterviewTrackInfo("hld_interview", "HLD Interview"),
        InterviewTrackInfo("lld_interview", "LLD Interview"),
        InterviewTrackInfo("devops_interview", "DevOps Interview"),
        InterviewTrackInfo("sql_interview", "SQL & Database Interview"),
        InterviewTrackInfo("angular_interview", "Angular & Frontend Interview"),
        InterviewTrackInfo("security_interview", "Security & AppSec Interview"),
        InterviewTrackInfo("full_stack_interview", "Full Stack Interview")
    )

    fun getTodayEpochDay(): Long {
        val oneDayMs = 24 * 60 * 60 * 1000L
        return System.currentTimeMillis() / oneDayMs
    }

    fun getTodayMcqTraining(
        epochDay: Long = getTodayEpochDay(),
        sessions: List<QuizSessionEntity> = emptyList(),
        attempts: List<QuestionAttemptEntity> = emptyList()
    ): TodayTraining {
        val safeEpoch = epochDay.coerceAtLeast(0)
        val stackIndex = (safeEpoch % mcqStacks.size).toInt()
        val currentStack = mcqStacks[stackIndex]
        val concepts = TechnicalConceptCatalog.getConceptsForDomain(currentStack.domainId)
        val cycle = (safeEpoch / mcqStacks.size).toInt()
        val concept = if (concepts.isNotEmpty()) {
            concepts[cycle % concepts.size]
        } else {
            TechnicalConceptCatalog.javaConcepts.first()
        }

        val oneDayMs = 24 * 60 * 60 * 1000L
        val todayStartMs = safeEpoch * oneDayMs

        // Check if attempted today via sessions or attempts
        val hasSessionToday = sessions.any { 
            it.categoryId == concept.id && it.completedAt >= todayStartMs 
        }
        val hasAnySessionForConcept = sessions.any { it.categoryId == concept.id }
        val hasAttemptsToday = attempts.any { it.timestamp >= todayStartMs }

        val isAttempted = hasSessionToday || (hasAnySessionForConcept && hasAttemptsToday)

        val totalQuestions = concept.questionCount
        val estimatedMins = (totalQuestions / 4).coerceIn(5, 20)

        return TodayTraining(
            id = "today_mcq_${concept.id}",
            type = TrainingType.MCQ,
            title = "${currentStack.displayName}: ${concept.name}",
            subtitle = "Daily Concept MCQ Drill",
            category = currentStack.displayName,
            targetId = concept.id,
            targetConceptId = concept.id,
            targetConceptName = concept.name,
            questionsCount = totalQuestions,
            completedCount = if (isAttempted) totalQuestions else 0,
            targetGoalCount = totalQuestions,
            estimatedMinutes = estimatedMins,
            isCompleted = isAttempted
        )
    }

    fun getTodayInterviewTraining(
        epochDay: Long = getTodayEpochDay(),
        audioResponses: List<InterviewResponseEntity> = emptyList()
    ): TodayTraining {
        val safeEpoch = epochDay.coerceAtLeast(0)
        val trackIndex = (safeEpoch % interviewTracks.size).toInt()
        val currentTrack = interviewTracks[trackIndex]
        val groups = InterviewQuestionCatalog.getConceptGroupsForTrack(currentTrack.trackId)
        val cycle = (safeEpoch / interviewTracks.size).toInt()
        val conceptGroup = if (groups.isNotEmpty()) {
            groups[cycle % groups.size]
        } else {
            InterviewQuestionCatalog.getConceptGroupsForTrack("java_interview").first()
        }

        val targetGoal = 5
        // Audio recordings count for this concept
        val matchingAudioCount = audioResponses.count { response ->
            response.trackId == currentTrack.trackId &&
            (response.conceptName.equals(conceptGroup.conceptName, ignoreCase = true) ||
             response.conceptName.isBlank()) &&
            !response.audioFilePath.isNullOrBlank()
        }

        val isCompleted = matchingAudioCount >= targetGoal

        return TodayTraining(
            id = "today_interview_${currentTrack.trackId}_${conceptGroup.conceptId}",
            type = TrainingType.INTERVIEW,
            title = "${currentTrack.trackTitle}: ${conceptGroup.conceptName}",
            subtitle = "Record 5 Audio Answers to Complete",
            category = currentTrack.trackTitle.replace(" Interview", ""),
            targetId = currentTrack.trackId,
            targetConceptId = conceptGroup.conceptId,
            targetConceptName = conceptGroup.conceptName,
            questionsCount = conceptGroup.questions.size,
            completedCount = matchingAudioCount.coerceAtMost(targetGoal),
            targetGoalCount = targetGoal,
            estimatedMinutes = 15,
            isCompleted = isCompleted
        )
    }
}
