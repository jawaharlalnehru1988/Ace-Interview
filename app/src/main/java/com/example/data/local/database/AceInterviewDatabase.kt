package com.example.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.data.local.dao.CategoryDao
import com.example.data.local.dao.DsaDao
import com.example.data.local.dao.InterviewDao
import com.example.data.local.dao.QuestionDao
import com.example.data.local.dao.QuizDao
import com.example.data.local.dao.UserProgressDao
import com.example.data.local.entity.CategoryEntity
import com.example.data.local.entity.DsaAttemptEntity
import com.example.data.local.entity.DsaProblemEntity
import com.example.data.local.entity.InterviewResponseEntity
import com.example.data.local.entity.InterviewSessionEntity
import com.example.data.local.entity.QuestionAttemptEntity
import com.example.data.local.entity.QuestionEntity
import com.example.data.local.entity.QuizSessionEntity
import com.example.data.local.entity.UserProgressEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        QuestionEntity::class,
        CategoryEntity::class,
        QuestionAttemptEntity::class,
        QuizSessionEntity::class,
        UserProgressEntity::class,
        DsaProblemEntity::class,
        DsaAttemptEntity::class,
        InterviewSessionEntity::class,
        InterviewResponseEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AceInterviewDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun questionDao(): QuestionDao
    abstract fun quizDao(): QuizDao
    abstract fun userProgressDao(): UserProgressDao
    abstract fun dsaDao(): DsaDao
    abstract fun interviewDao(): InterviewDao

    companion object {
        @Volatile
        private var INSTANCE: AceInterviewDatabase? = null

        fun getInstance(context: Context): AceInterviewDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AceInterviewDatabase::class.java,
                    "ace_interview_db"
                )
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        CoroutineScope(Dispatchers.IO).launch {
                            INSTANCE?.questionDao()?.insertQuestions(SampleQuestionData.sampleQuestions)
                        }
                    }
                })
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
