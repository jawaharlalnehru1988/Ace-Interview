package com.example.data.local.interview

import com.example.domain.model.ConceptInterviewGroup
import com.example.domain.model.InterviewQuestion

object InterviewQuestionCatalog {

    fun getAllQuestions(): List<InterviewQuestion> {
        return JavaInterviewQuestions.getQuestions() +
            SpringBootInterviewQuestions.getQuestions() +
            MicroservicesInterviewQuestions.getQuestions() +
            SystemDesignInterviewQuestions.getQuestions() +
            DevopsInterviewQuestions.getQuestions() +
            LldInterviewQuestions.getQuestions() +
            SqlInterviewQuestions.getQuestions() +
            SecurityInterviewQuestions.getQuestions() +
            FullStackInterviewQuestions.getQuestions()
    }

    fun getQuestionsForTrack(trackId: String): List<InterviewQuestion> {
        return when (trackId.lowercase()) {
            "java_interview", "java" -> JavaInterviewQuestions.getQuestions()
            "spring_boot_interview", "spring_boot", "spring" -> SpringBootInterviewQuestions.getQuestions()
            "microservices_interview", "microservices" -> MicroservicesInterviewQuestions.getQuestions()
            "system_design_interview", "system_design" -> SystemDesignInterviewQuestions.getQuestions()
            "hld_interview", "hld" -> SystemDesignInterviewQuestions.getQuestions()
            "devops_interview", "devops" -> DevopsInterviewQuestions.getQuestions()
            "lld_interview", "lld" -> LldInterviewQuestions.getQuestions()
            "sql_interview", "sql" -> SqlInterviewQuestions.getQuestions()
            "security_interview", "security" -> SecurityInterviewQuestions.getQuestions()
            "full_stack_interview", "full_stack", "angular" -> FullStackInterviewQuestions.getQuestions()
            "senior_engineer_interview", "senior_engineer" -> {
                // Blend of architecture, microservices, and system design
                SystemDesignInterviewQuestions.getQuestions().take(3) +
                MicroservicesInterviewQuestions.getQuestions().take(3) +
                DevopsInterviewQuestions.getQuestions().take(2) +
                JavaInterviewQuestions.getQuestions().take(2)
            }
            else -> JavaInterviewQuestions.getQuestions()
        }
    }

    fun getConceptGroupsForTrack(trackId: String): List<ConceptInterviewGroup> {
        val questions = getQuestionsForTrack(trackId)
        return questions
            .groupBy { it.conceptId }
            .map { (conceptId, groupQuestions) ->
                ConceptInterviewGroup(
                    conceptId = conceptId,
                    conceptName = groupQuestions.firstOrNull()?.conceptName ?: conceptId,
                    questions = groupQuestions
                )
            }
    }

    fun findQuestionById(questionId: String): InterviewQuestion? {
        return getAllQuestions().find { it.id == questionId }
    }
}
