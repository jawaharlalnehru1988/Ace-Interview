package com.example.data.local.questions

import com.example.data.local.entity.QuestionEntity
import org.json.JSONArray

object QuestionHelper {
    fun q(
        id: String,
        categoryId: String,
        title: String,
        prompt: String,
        opt0: String,
        opt1: String,
        opt2: String,
        opt3: String,
        correctIndex: Int,
        explanation: String,
        difficulty: String,
        tags: String
    ): QuestionEntity {
        val optionsArray = JSONArray().apply {
            put(opt0)
            put(opt1)
            put(opt2)
            put(opt3)
        }
        return QuestionEntity(
            id = id,
            categoryId = categoryId,
            title = title,
            prompt = prompt,
            optionsJson = optionsArray.toString(),
            correctAnswerIndex = correctIndex,
            explanation = explanation,
            difficulty = difficulty,
            tags = tags
        )
    }
}
