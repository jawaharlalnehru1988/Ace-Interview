package com.example.presentation.functional

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.FunctionalProblem
import com.example.domain.model.FunctionalTrack
import com.example.domain.repository.InterviewRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed interface FunctionalUiState {
    data object Loading : FunctionalUiState
    data class Success(
        val tracks: List<FunctionalTrack>,
        val selectedTrackId: String? = null,
        val selectedTrack: FunctionalTrack? = null,
        val problems: List<FunctionalProblem> = emptyList(),
        val filteredProblems: List<FunctionalProblem> = emptyList(),
        val selectedCategory: String = "All",
        val selectedDifficulty: String = "All",
        val searchQuery: String = "",
        val availableCategories: List<String> = emptyList(),
        val totalSolved: Int = 0,
        val totalCount: Int = 0
    ) : FunctionalUiState
}

@OptIn(ExperimentalCoroutinesApi::class)
class FunctionalViewModel(
    private val repository: InterviewRepository
) : ViewModel() {

    private val _selectedTrackId = MutableStateFlow<String?>(null)
    private val _selectedCategory = MutableStateFlow("All")
    private val _selectedDifficulty = MutableStateFlow("All")
    private val _searchQuery = MutableStateFlow("")

    val uiState: StateFlow<FunctionalUiState> = combine(
        repository.getFunctionalTracks(),
        _selectedTrackId,
        _selectedCategory,
        _selectedDifficulty,
        _searchQuery
    ) { tracks, selectedTrackId, category, difficulty, query ->
        StateParams(tracks, selectedTrackId, category, difficulty, query)
    }.flatMapLatest { params ->
        val selectedTrack = params.tracks.find { it.id == params.selectedTrackId }
        val problemsFlow = if (params.selectedTrackId != null) {
            repository.getFunctionalProblems(params.selectedTrackId)
        } else {
            flowOf(emptyList())
        }

        problemsFlow.combine(repository.getFunctionalTracks()) { problems, updatedTracks ->
            val totalCount = updatedTracks.sumOf { it.totalCount }
            val totalSolved = updatedTracks.sumOf { it.solvedCount }

            val categories = listOf("All") + problems.map { it.category }.distinct().sorted()

            val filtered = problems.filter { p ->
                val matchesCategory = params.category == "All" || p.category.equals(params.category, ignoreCase = true)
                val matchesDifficulty = params.difficulty == "All" || p.difficulty.equals(params.difficulty, ignoreCase = true)
                val matchesQuery = params.query.isBlank() ||
                        p.title.contains(params.query, ignoreCase = true) ||
                        p.pattern.contains(params.query, ignoreCase = true) ||
                        p.description.contains(params.query, ignoreCase = true)
                matchesCategory && matchesDifficulty && matchesQuery
            }

            FunctionalUiState.Success(
                tracks = updatedTracks,
                selectedTrackId = params.selectedTrackId,
                selectedTrack = selectedTrack,
                problems = problems,
                filteredProblems = filtered,
                selectedCategory = params.category,
                selectedDifficulty = params.difficulty,
                searchQuery = params.query,
                availableCategories = categories,
                totalSolved = totalSolved,
                totalCount = totalCount
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = FunctionalUiState.Loading
    )

    fun selectTrack(trackId: String?) {
        _selectedTrackId.value = trackId
        _selectedCategory.value = "All"
        _selectedDifficulty.value = "All"
        _searchQuery.value = ""
    }

    fun setCategory(category: String) {
        _selectedCategory.value = category
    }

    fun setDifficulty(difficulty: String) {
        _selectedDifficulty.value = difficulty
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun toggleProblemSolved(problemId: String) {
        viewModelScope.launch {
            repository.toggleFunctionalProblemSolved(problemId)
        }
    }

    private data class StateParams(
        val tracks: List<FunctionalTrack>,
        val selectedTrackId: String?,
        val category: String,
        val difficulty: String,
        val query: String
    )
}
