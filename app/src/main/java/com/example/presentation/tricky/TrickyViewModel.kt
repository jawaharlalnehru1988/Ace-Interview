package com.example.presentation.tricky

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.InterviewRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

data class TrickyTrackInfo(
    val id: String,
    val title: String,
    val subtitle: String,
    val description: String,
    val totalQuestions: Int,
    val solvedCount: Int,
    val accuracy: Int,
    val tags: List<String>,
    val categories: List<com.example.domain.model.TrickyCategory> = emptyList(),
    val badge: String
)

sealed interface TrickyUiState {
    data object Loading : TrickyUiState
    data class Success(
        val javaTrack: TrickyTrackInfo,
        val jsTrack: TrickyTrackInfo,
        val totalQuestions: Int,
        val totalSolved: Int,
        val overallAccuracy: Int
    ) : TrickyUiState
}

class TrickyViewModel(
    private val repository: InterviewRepository
) : ViewModel() {

    val uiState: StateFlow<TrickyUiState> = combine(
        repository.getAllQuestions(),
        repository.getUserDashboard()
    ) { allQuestions, dashboard ->
        val javaQuestions = com.example.data.local.questions.JavaTrickyQuestions.getAll()
        val jsQuestions = com.example.data.local.questions.JsTrickyQuestions.getAll()

        val javaCategoriesWithCounts = com.example.domain.model.TrickyCategoryCatalog.javaCategories.map { cat ->
            val count = if (cat.id.endsWith("_all")) {
                javaQuestions.size
            } else {
                javaQuestions.count { q ->
                    com.example.domain.model.TrickyCategoryCatalog.matchesCategory(
                        cat, q.title, q.prompt, q.explanation, q.tags?.split(",") ?: emptyList()
                    )
                }
            }
            cat.copy(questionCount = count)
        }

        val jsCategoriesWithCounts = com.example.domain.model.TrickyCategoryCatalog.jsCategories.map { cat ->
            val count = if (cat.id.endsWith("_all")) {
                jsQuestions.size
            } else {
                jsQuestions.count { q ->
                    com.example.domain.model.TrickyCategoryCatalog.matchesCategory(
                        cat, q.title, q.prompt, q.explanation, q.tags?.split(",") ?: emptyList()
                    )
                }
            }
            cat.copy(questionCount = count)
        }

        val javaTrack = TrickyTrackInfo(
            id = "java_tricky",
            title = "Java Tricky Questions",
            subtitle = "150+ Counter-Intuitive JVM & Core Drills",
            description = "Master subtle JVM behaviors: Integer cache, literal promotions, constructor chaining, finally overrides, generics erasure, and method hiding.",
            totalQuestions = javaQuestions.size,
            solvedCount = 0,
            accuracy = 0,
            tags = listOf("Syntax Nuances", "Control Flow", "Collections & Generics", "OOP & Inheritance"),
            categories = javaCategoriesWithCounts,
            badge = "150+ MCQs"
        )

        val jsTrack = TrickyTrackInfo(
            id = "js_tricky",
            title = "JavaScript Tricky Questions",
            subtitle = "150+ Edge Cases & Runtime Quirks",
            description = "Master ECMAScript traps: Type coercion, Temporal Dead Zone, closure loops, lexical this, event loop microtasks, and object prototypes.",
            totalQuestions = jsQuestions.size,
            solvedCount = 0,
            accuracy = 0,
            tags = listOf("Type Coercion", "Scoping & TDZ", "This & Prototypes", "Event Loop"),
            categories = jsCategoriesWithCounts,
            badge = "150+ MCQs"
        )

        TrickyUiState.Success(
            javaTrack = javaTrack,
            jsTrack = jsTrack,
            totalQuestions = javaQuestions.size + jsQuestions.size,
            totalSolved = dashboard.trickySolvedCount,
            overallAccuracy = dashboard.trickyAccuracy
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = TrickyUiState.Loading
    )
}
