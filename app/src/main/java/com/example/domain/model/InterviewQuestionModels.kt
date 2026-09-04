package com.example.domain.model

data class InterviewQuestion(
    val id: String,
    val trackId: String,
    val conceptId: String,
    val conceptName: String,
    val title: String,
    val question: String,
    val shortAnswer: String,
    val keyPoints: List<String> = emptyList(),
    val difficulty: String = "Mid-Level"
)

data class ConceptInterviewGroup(
    val conceptId: String,
    val conceptName: String,
    val questions: List<InterviewQuestion>
)

data class QuestionAudioAnswer(
    val questionId: String,
    val audioFilePath: String?,
    val audioDurationMs: Long = 0L,
    val recordedAt: Long = System.currentTimeMillis()
)
