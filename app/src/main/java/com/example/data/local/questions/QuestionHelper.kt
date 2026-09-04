package com.example.data.local.questions

import com.example.data.local.entity.QuestionEntity
import org.json.JSONArray
import java.util.Random

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
        val originalOptions = listOf(opt0, opt1, opt2, opt3)
        val correctOption = originalOptions.getOrElse(correctIndex) { opt0 }

        // Uniform 32-bit hash mixing for deterministic, balanced option distribution (~25% per position)
        var h = id.hashCode()
        h = ((h xor (h ushr 16)) * 0x45d9f3b)
        h = ((h xor (h ushr 16)) * 0x45d9f3b)
        h = (h xor (h ushr 16)) and 0x7fffffff

        val rnd = Random(h.toLong())
        val indices = intArrayOf(0, 1, 2, 3)
        for (i in 3 downTo 1) {
            val j = rnd.nextInt(i + 1)
            val tmp = indices[i]
            indices[i] = indices[j]
            indices[j] = tmp
        }

        val shuffledOptions = indices.map { originalOptions[it] }
        val newCorrectIndex = shuffledOptions.indexOf(correctOption).coerceAtLeast(0)

        val optionsArray = JSONArray().apply {
            shuffledOptions.forEach { put(it) }
        }
        return QuestionEntity(
            id = id,
            categoryId = categoryId,
            title = title,
            prompt = prompt,
            optionsJson = optionsArray.toString(),
            correctAnswerIndex = newCorrectIndex,
            explanation = explanation,
            difficulty = difficulty,
            tags = tags
        )
    }
}
