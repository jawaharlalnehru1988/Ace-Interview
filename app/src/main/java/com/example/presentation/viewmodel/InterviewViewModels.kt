package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.domain.model.DsaProblem
import com.example.domain.model.DsaTopic
import com.example.domain.model.InterviewTrack
import com.example.domain.model.TechnicalCategory
import com.example.domain.model.UserDashboard
import com.example.domain.model.UserProfile
import com.example.domain.repository.InterviewRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// --- Home ViewModel ---
sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Success(val dashboard: UserDashboard) : HomeUiState
}

class HomeViewModel(
    private val repository: InterviewRepository
) : ViewModel() {
    private val _completedTrainings = MutableStateFlow<Set<String>>(emptySet())

    val uiState: StateFlow<HomeUiState> = repository.getUserDashboard()
        .combine(_completedTrainings) { dashboard, completedSet ->
            val updatedTrainings = dashboard.todayTrainings.map { training ->
                training.copy(isCompleted = training.isCompleted || completedSet.contains(training.id))
            }
            HomeUiState.Success(dashboard.copy(todayTrainings = updatedTrainings))
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiState.Loading
        )

    fun toggleTrainingItem(id: String) {
        _completedTrainings.update { current ->
            if (current.contains(id)) current - id else current + id
        }
    }
}

// --- Practice ViewModel ---
sealed interface PracticeUiState {
    data object Loading : PracticeUiState
    data class Success(
        val categories: List<TechnicalCategory>,
        val activeFilter: String = "All",
        val searchQuery: String = ""
    ) : PracticeUiState
}

class PracticeViewModel(
    private val repository: InterviewRepository
) : ViewModel() {
    private val _activeFilter = MutableStateFlow("All")
    private val _searchQuery = MutableStateFlow("")

    val uiState: StateFlow<PracticeUiState> = combine(
        repository.getTechnicalCategories(),
        _activeFilter,
        _searchQuery
    ) { categories, filter, query ->
        val filtered = categories.filter { cat ->
            val matchesFilter = when (filter) {
                "Architecture" -> cat.id in listOf("hld", "lld", "system_design", "microservices")
                "Backend" -> cat.id in listOf("java", "spring_boot", "sql")
                "Infra & Sec" -> cat.id in listOf("devops", "security")
                "Frontend" -> cat.id == "angular"
                else -> true
            }
            val matchesQuery = query.isBlank() ||
                cat.name.contains(query, ignoreCase = true) ||
                cat.description.contains(query, ignoreCase = true)

            matchesFilter && matchesQuery
        }
        PracticeUiState.Success(
            categories = filtered,
            activeFilter = filter,
            searchQuery = query
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = PracticeUiState.Loading
    )

    fun setFilter(filter: String) {
        _activeFilter.value = filter
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }
}

// --- DSA ViewModel ---
sealed interface DsaUiState {
    data object Loading : DsaUiState
    data class Success(
        val topics: List<DsaTopic>,
        val selectedTopicId: String? = null,
        val selectedTopicProblems: List<DsaProblem> = emptyList()
    ) : DsaUiState
}

class DsaViewModel(
    private val repository: InterviewRepository
) : ViewModel() {
    private val _selectedTopicId = MutableStateFlow<String?>(null)

    val uiState: StateFlow<DsaUiState> = combine(
        repository.getDsaTopics(),
        _selectedTopicId.flatMapLatest { topicId ->
            if (topicId != null) repository.getDsaProblems(topicId)
            else flowOf(emptyList())
        },
        _selectedTopicId
    ) { topics, problems, selectedId ->
        DsaUiState.Success(
            topics = topics,
            selectedTopicId = selectedId,
            selectedTopicProblems = problems
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DsaUiState.Loading
    )

    fun selectTopic(topicId: String?) {
        _selectedTopicId.value = topicId
    }

    fun toggleProblemSolved(problemId: String) {
        viewModelScope.launch {
            repository.toggleDsaProblemSolved(problemId)
        }
    }
}

// --- Interview ViewModel ---
sealed interface InterviewUiState {
    data object Loading : InterviewUiState
    data class Success(
        val tracks: List<InterviewTrack>,
        val selectedTrackId: String? = null,
        val filterRole: String = "All"
    ) : InterviewUiState
}

class InterviewViewModel(
    private val repository: InterviewRepository
) : ViewModel() {
    private val _filterRole = MutableStateFlow("All")
    private val _selectedTrackId = MutableStateFlow<String?>(null)

    val uiState: StateFlow<InterviewUiState> = combine(
        repository.getInterviewTracks(),
        _filterRole,
        _selectedTrackId
    ) { tracks, filter, selectedId ->
        val filtered = if (filter == "All") {
            tracks
        } else {
            tracks.filter { it.roleLevel.contains(filter, ignoreCase = true) }
        }
        InterviewUiState.Success(
            tracks = filtered,
            selectedTrackId = selectedId,
            filterRole = filter
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = InterviewUiState.Loading
    )

    fun setFilter(role: String) {
        _filterRole.value = role
    }

    fun selectTrack(trackId: String?) {
        _selectedTrackId.update { current ->
            if (current == trackId) null else trackId
        }
    }
}

// --- Profile ViewModel ---
sealed interface ProfileUiState {
    data object Loading : ProfileUiState
    data class Success(
        val profile: UserProfile,
        val dailyReminderEnabled: Boolean = true,
        val offlineSyncEnabled: Boolean = true
    ) : ProfileUiState
}

class ProfileViewModel(
    private val repository: InterviewRepository
) : ViewModel() {
    private val _dailyReminderEnabled = MutableStateFlow(true)
    private val _offlineSyncEnabled = MutableStateFlow(true)

    val uiState: StateFlow<ProfileUiState> = combine(
        repository.getUserProfile(),
        _dailyReminderEnabled,
        _offlineSyncEnabled
    ) { profile, reminder, sync ->
        ProfileUiState.Success(
            profile = profile,
            dailyReminderEnabled = reminder,
            offlineSyncEnabled = sync
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ProfileUiState.Loading
    )

    fun toggleDailyReminder() {
        _dailyReminderEnabled.update { !it }
    }

    fun toggleOfflineSync() {
        _offlineSyncEnabled.update { !it }
    }
}

// Factory Helper
class ViewModelFactory(
    private val repository: InterviewRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) ->
                HomeViewModel(repository) as T
            modelClass.isAssignableFrom(PracticeViewModel::class.java) ->
                PracticeViewModel(repository) as T
            modelClass.isAssignableFrom(com.example.presentation.practice.McqPracticeViewModel::class.java) ->
                com.example.presentation.practice.McqPracticeViewModel(repository) as T
            modelClass.isAssignableFrom(DsaViewModel::class.java) ->
                DsaViewModel(repository) as T
            modelClass.isAssignableFrom(InterviewViewModel::class.java) ->
                InterviewViewModel(repository) as T
            modelClass.isAssignableFrom(ProfileViewModel::class.java) ->
                ProfileViewModel(repository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
