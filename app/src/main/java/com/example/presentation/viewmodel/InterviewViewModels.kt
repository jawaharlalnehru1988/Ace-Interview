package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.domain.model.DsaProblem
import com.example.domain.model.DsaScreenMode
import com.example.domain.model.DsaTopic
import com.example.domain.model.InterviewTrack
import com.example.domain.model.TechnicalCategory
import com.example.domain.model.TrainingDrill
import com.example.domain.model.TrainingTopic
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

    val uiState: StateFlow<HomeUiState> = combine(
        repository.getUserDashboard(),
        repository.getDsaTopics(),
        _completedTrainings
    ) { dashboard, dsaTopics, completedSet ->
        val updatedTrainings = dashboard.todayTrainings.map { training ->
            training.copy(isCompleted = training.isCompleted || completedSet.contains(training.id))
        }
        HomeUiState.Success(
            dashboard.copy(
                todayTrainings = updatedTrainings,
                dsaTopics = dsaTopics
            )
        )
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
                "Database" -> cat.id == "sql"
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
        val selectedTopicProblems: List<DsaProblem> = emptyList(),
        val screenMode: DsaScreenMode = DsaScreenMode.ROADMAP,
        val trainingTopics: List<TrainingTopic> = emptyList(),
        val selectedTrainingTopicId: String? = "arrays",
        val selectedTrainingDrills: List<TrainingDrill> = emptyList(),
        val selectedDrillId: String? = null
    ) : DsaUiState
}

private data class DsaRoadmapData(
    val topics: List<DsaTopic>,
    val problems: List<DsaProblem>,
    val selectedId: String?,
    val mode: DsaScreenMode
)

private data class DsaTrainingData(
    val trainTopics: List<TrainingTopic>,
    val drills: List<TrainingDrill>,
    val trainTopicId: String?,
    val drillId: String?
)

class DsaViewModel(
    private val repository: InterviewRepository
) : ViewModel() {
    private val _selectedTopicId = MutableStateFlow<String?>(null)
    private val _screenMode = MutableStateFlow(DsaScreenMode.ROADMAP)
    private val _selectedTrainingTopicId = MutableStateFlow<String?>("arrays")
    private val _selectedDrillId = MutableStateFlow<String?>(null)

    private val dsaProblemsFlow = _selectedTopicId.flatMapLatest { topicId ->
        if (topicId != null) repository.getDsaProblems(topicId)
        else flowOf(emptyList())
    }

    private val trainingDrillsFlow = _selectedTrainingTopicId.flatMapLatest { topicId ->
        if (topicId != null) repository.getDrillsForTopic(topicId)
        else flowOf(emptyList())
    }

    val uiState: StateFlow<DsaUiState> = combine(
        combine(
            repository.getDsaTopics(),
            dsaProblemsFlow,
            _selectedTopicId,
            _screenMode
        ) { topics, problems, topicId, mode ->
            DsaRoadmapData(topics, problems, topicId, mode)
        },
        combine(
            repository.getTrainingTopics(),
            trainingDrillsFlow,
            _selectedTrainingTopicId,
            _selectedDrillId
        ) { trainTopics, drills, trainTopicId, drillId ->
            DsaTrainingData(trainTopics, drills, trainTopicId, drillId)
        }
    ) { roadmap, training ->
        DsaUiState.Success(
            topics = roadmap.topics,
            selectedTopicId = roadmap.selectedId,
            selectedTopicProblems = roadmap.problems,
            screenMode = roadmap.mode,
            trainingTopics = training.trainTopics,
            selectedTrainingTopicId = training.trainTopicId,
            selectedTrainingDrills = training.drills,
            selectedDrillId = training.drillId
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

    fun setScreenMode(mode: DsaScreenMode) {
        _screenMode.value = mode
    }

    fun selectTrainingTopic(topicId: String?) {
        _selectedTrainingTopicId.value = topicId
        _selectedDrillId.value = null
    }

    fun selectDrill(drillId: String?) {
        _selectedDrillId.value = drillId
    }

    fun toggleDrillCompleted(drillId: String) {
        viewModelScope.launch {
            repository.toggleDrillCompleted(drillId)
        }
    }

    fun navigateToTraining(topicId: String? = null) {
        _screenMode.value = DsaScreenMode.TRAINING
        if (topicId != null) {
            _selectedTrainingTopicId.value = topicId
        }
        _selectedDrillId.value = null
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
    private val repository: InterviewRepository,
    private val application: android.app.Application? = null
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
        val newState = !_dailyReminderEnabled.value
        _dailyReminderEnabled.value = newState
        application?.let { ctx ->
            if (newState) {
                com.example.util.notification.ReminderScheduler.scheduleDailyReminders(ctx)
            } else {
                com.example.util.notification.ReminderScheduler.cancelAllReminders(ctx)
            }
        }
    }

    fun toggleOfflineSync() {
        _offlineSyncEnabled.update { !it }
    }
}

// Factory Helper
class ViewModelFactory(
    private val repository: InterviewRepository,
    private val application: android.app.Application? = null
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
            modelClass.isAssignableFrom(com.example.presentation.interview.MockInterviewViewModel::class.java) ->
                com.example.presentation.interview.MockInterviewViewModel(
                    application ?: throw IllegalStateException("Application required for MockInterviewViewModel"),
                    repository
                ) as T
            modelClass.isAssignableFrom(com.example.presentation.tricky.TrickyViewModel::class.java) ->
                com.example.presentation.tricky.TrickyViewModel(repository) as T
            modelClass.isAssignableFrom(com.example.presentation.functional.FunctionalViewModel::class.java) ->
                com.example.presentation.functional.FunctionalViewModel(repository) as T
            modelClass.isAssignableFrom(ProfileViewModel::class.java) ->
                ProfileViewModel(repository, application) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
