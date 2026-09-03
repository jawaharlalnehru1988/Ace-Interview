package com.example

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.local.database.AceInterviewDatabase
import com.example.data.local.database.SampleQuestionData
import com.example.data.local.entity.toDomain
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class RoomQuestionDatabaseTest {

    private lateinit var database: AceInterviewDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AceInterviewDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun verifySeedingTenSampleQuestions() = runBlocking {
        val questionDao = database.questionDao()
        assertEquals(0, questionDao.getQuestionCount())

        questionDao.insertQuestions(SampleQuestionData.sampleQuestions)
        val count = questionDao.getQuestionCount()
        assertEquals(10, count)

        val allQuestions = questionDao.getAllQuestions().first()
        assertEquals(10, allQuestions.size)

        // Verify category filtering and domain parsing
        val javaQuestions = questionDao.getQuestionsByCategory("java").first()
        assertEquals(2, javaQuestions.size)

        val firstJavaQ = javaQuestions.first().toDomain()
        assertTrue(firstJavaQ.options.size >= 4)
        assertNotNull(firstJavaQ.title)
        assertTrue(firstJavaQ.explanation.isNotEmpty())
        assertTrue(firstJavaQ.tags.isNotEmpty())

        val sysDesignQ = questionDao.getQuestionsByCategory("system_design").first()
        assertEquals(1, sysDesignQ.size)
        assertEquals("q_sys_cap_theorem", sysDesignQ.first().id)

        val sqlQ = questionDao.getQuestionsByCategory("sql").first()
        assertEquals(1, sqlQ.size)
        assertEquals("q_sql_isolation_levels", sqlQ.first().id)
    }
}
