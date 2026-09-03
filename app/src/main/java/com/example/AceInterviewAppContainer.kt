package com.example

import android.content.Context
import com.example.data.local.database.AceInterviewDatabase
import com.example.data.repository.InterviewRepositoryImpl
import com.example.domain.repository.InterviewRepository

class AceInterviewAppContainer(context: Context) {
    val database: AceInterviewDatabase by lazy {
        AceInterviewDatabase.getInstance(context)
    }

    val interviewRepository: InterviewRepository by lazy {
        InterviewRepositoryImpl(database)
    }
}
