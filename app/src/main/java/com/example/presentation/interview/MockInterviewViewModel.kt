package com.example.presentation.interview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.ConceptInterviewGroup
import com.example.domain.model.InterviewQuestion
import com.example.domain.model.QuestionAudioAnswer
import com.example.domain.repository.InterviewRepository
import com.example.util.audio.AudioPlaybackManager
import com.example.util.audio.AudioRecordingManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface MockInterviewSessionUiState {
    data object Loading : MockInterviewSessionUiState
    data class Success(
        val trackId: String,
        val trackTitle: String,
        val questions: List<InterviewQuestion>,
        val conceptGroups: List<ConceptInterviewGroup>,
        val selectedConceptId: String? = null,
        val recordedAnswers: Map<String, QuestionAudioAnswer> = emptyMap(),
        val recordingQuestion: InterviewQuestion? = null,
        val expandedAnswerQuestionIds: Set<String> = emptySet()
    ) : MockInterviewSessionUiState {
        val displayedQuestions: List<InterviewQuestion>
            get() = if (selectedConceptId == null) {
                questions
            } else {
                questions.filter { it.conceptId == selectedConceptId }
            }

        val totalQuestionsCount: Int
            get() = questions.size

        val recordedCount: Int
            get() = questions.count { recordedAnswers.containsKey(it.id) }
    }
}

class MockInterviewViewModel(
    application: Application,
    private val repository: InterviewRepository
) : AndroidViewModel(application) {

    val audioRecorder = AudioRecordingManager(application.applicationContext)
    val audioPlayer = AudioPlaybackManager(application.applicationContext)

    private val _trackId = MutableStateFlow("")
    private val _trackTitle = MutableStateFlow("")
    private val _selectedConceptId = MutableStateFlow<String?>(null)
    private val _recordingQuestion = MutableStateFlow<InterviewQuestion?>(null)
    private val _expandedAnswerQuestionIds = MutableStateFlow<Set<String>>(emptySet())

    private val _questions = MutableStateFlow<List<InterviewQuestion>>(emptyList())
    private val _conceptGroups = MutableStateFlow<List<ConceptInterviewGroup>>(emptyList())
    private val _recordedAnswers = MutableStateFlow<Map<String, QuestionAudioAnswer>>(emptyMap())

    private data class TrackData(
        val trackId: String,
        val trackTitle: String,
        val questions: List<InterviewQuestion>,
        val conceptGroups: List<ConceptInterviewGroup>
    )

    private data class InteractionState(
        val selectedConceptId: String?,
        val recordedAnswers: Map<String, QuestionAudioAnswer>,
        val recordingQuestion: InterviewQuestion?,
        val expandedAnswerQuestionIds: Set<String>
    )

    private val trackDataFlow = combine(
        _trackId,
        _trackTitle,
        _questions,
        _conceptGroups
    ) { trackId, trackTitle, questions, groups ->
        TrackData(trackId, trackTitle, questions, groups)
    }

    private val interactionFlow = combine(
        _selectedConceptId,
        _recordedAnswers,
        _recordingQuestion,
        _expandedAnswerQuestionIds
    ) { selectedConcept, answers, recordingQ, expandedIds ->
        InteractionState(selectedConcept, answers, recordingQ, expandedIds)
    }

    val uiState: StateFlow<MockInterviewSessionUiState> = combine(
        trackDataFlow,
        interactionFlow
    ) { trackData, interaction ->
        if (trackData.trackId.isBlank() || trackData.questions.isEmpty()) {
            MockInterviewSessionUiState.Loading
        } else {
            MockInterviewSessionUiState.Success(
                trackId = trackData.trackId,
                trackTitle = trackData.trackTitle,
                questions = trackData.questions,
                conceptGroups = trackData.conceptGroups,
                selectedConceptId = interaction.selectedConceptId,
                recordedAnswers = interaction.recordedAnswers,
                recordingQuestion = interaction.recordingQuestion,
                expandedAnswerQuestionIds = interaction.expandedAnswerQuestionIds
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = MockInterviewSessionUiState.Loading
    )

    fun loadSession(trackId: String, trackTitle: String, initialConceptId: String? = null) {
        _trackId.value = trackId
        _trackTitle.value = trackTitle
        _selectedConceptId.value = initialConceptId
        _recordingQuestion.value = null

        viewModelScope.launch {
            repository.getInterviewQuestionsForTrack(trackId).collect { qList ->
                _questions.value = qList
            }
        }

        viewModelScope.launch {
            repository.getConceptGroupsForTrack(trackId).collect { gList ->
                _conceptGroups.value = gList
            }
        }

        viewModelScope.launch {
            repository.getAudioAnswersForTrack(trackId).collect { answersMap ->
                _recordedAnswers.value = answersMap
            }
        }
    }

    fun selectConcept(conceptId: String?) {
        _selectedConceptId.value = conceptId
    }

    fun toggleShortAnswer(questionId: String) {
        _expandedAnswerQuestionIds.update { set ->
            if (set.contains(questionId)) set - questionId else set + questionId
        }
    }

    fun startRecording(question: InterviewQuestion) {
        audioPlayer.stop()
        val result = audioRecorder.startRecording(question.id)
        if (result.isSuccess) {
            _recordingQuestion.value = question
        }
    }

    fun stopAndSaveRecording(question: InterviewQuestion) {
        val result = audioRecorder.stopRecording()
        _recordingQuestion.value = null

        if (result != null) {
            viewModelScope.launch {
                repository.saveAudioAnswer(
                    questionId = question.id,
                    trackId = question.trackId,
                    conceptName = question.conceptName,
                    questionText = question.question,
                    shortAnswer = question.shortAnswer,
                    audioFilePath = result.filePath,
                    durationMs = result.durationMs
                )
            }
        }
    }

    fun cancelRecording() {
        audioRecorder.cancelRecording()
        _recordingQuestion.value = null
    }

    fun playAudio(filePath: String) {
        audioPlayer.play(filePath)
    }

    fun pauseAudio() {
        audioPlayer.pause()
    }

    fun stopAudio() {
        audioPlayer.stop()
    }

    fun deleteAnswer(questionId: String) {
        viewModelScope.launch {
            repository.deleteAudioAnswer(questionId)
        }
    }

    override fun onCleared() {
        super.onCleared()
        audioRecorder.release()
        audioPlayer.release()
    }
}
