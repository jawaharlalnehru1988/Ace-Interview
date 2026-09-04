package com.example.util.audio

import android.content.Context
import android.media.MediaRecorder
import android.os.Build
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.io.File

data class AudioRecordingResult(
    val filePath: String,
    val durationMs: Long
)

class AudioRecordingManager(private val context: Context) {

    private val tag = "AudioRecordingManager"
    private var mediaRecorder: MediaRecorder? = null
    private var currentOutputFile: File? = null
    private var recordingStartTimeMs: Long = 0L

    private var tickerJob: Job? = null
    private val scope = CoroutineScope(Dispatchers.Default)

    private val _isRecording = MutableStateFlow(false)
    val isRecording: StateFlow<Boolean> = _isRecording.asStateFlow()

    private val _elapsedSeconds = MutableStateFlow(0)
    val elapsedSeconds: StateFlow<Int> = _elapsedSeconds.asStateFlow()

    private val _amplitude = MutableStateFlow(0)
    val amplitude: StateFlow<Int> = _amplitude.asStateFlow()

    private val audioDir: File
        get() {
            val dir = File(context.filesDir, "interview_audios")
            if (!dir.exists()) {
                dir.mkdirs()
            }
            return dir
        }

    fun startRecording(questionId: String): Result<File> {
        return try {
            cancelRecording() // Clean up any lingering recording

            val sanitizedId = questionId.replace("[^a-zA-Z0-9_]".toRegex(), "_")
            val file = File(audioDir, "audio_${sanitizedId}_${System.currentTimeMillis()}.m4a")
            currentOutputFile = file

            val recorder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                MediaRecorder(context)
            } else {
                @Suppress("DEPRECATION")
                MediaRecorder()
            }

            recorder.apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
                setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
                setAudioEncodingBitRate(128000)
                setAudioSamplingRate(44100)
                setOutputFile(file.absolutePath)
                prepare()
                start()
            }

            mediaRecorder = recorder
            recordingStartTimeMs = System.currentTimeMillis()
            _isRecording.value = true
            _elapsedSeconds.value = 0
            _amplitude.value = 0

            startTicker()
            Result.success(file)
        } catch (e: Exception) {
            Log.e(tag, "Failed to start recording", e)
            cancelRecording()
            Result.failure(e)
        }
    }

    private fun startTicker() {
        tickerJob?.cancel()
        tickerJob = scope.launch {
            while (isActive && _isRecording.value) {
                delay(100)
                val elapsed = ((System.currentTimeMillis() - recordingStartTimeMs) / 1000).toInt()
                _elapsedSeconds.value = elapsed
                try {
                    val maxAmp = mediaRecorder?.maxAmplitude ?: 0
                    _amplitude.value = maxAmp
                } catch (_: Exception) {}
            }
        }
    }

    fun stopRecording(): AudioRecordingResult? {
        val file = currentOutputFile ?: return null
        return try {
            tickerJob?.cancel()
            tickerJob = null

            mediaRecorder?.apply {
                try {
                    stop()
                } catch (_: Exception) {}
                release()
            }
            mediaRecorder = null
            _isRecording.value = false

            val durationMs = System.currentTimeMillis() - recordingStartTimeMs
            currentOutputFile = null

            AudioRecordingResult(
                filePath = file.absolutePath,
                durationMs = durationMs.coerceAtLeast(1000L)
            )
        } catch (e: Exception) {
            Log.e(tag, "Error stopping recorder", e)
            cancelRecording()
            null
        }
    }

    fun cancelRecording() {
        tickerJob?.cancel()
        tickerJob = null
        try {
            mediaRecorder?.apply {
                try {
                    stop()
                } catch (_: Exception) {}
                release()
            }
        } catch (_: Exception) {}
        mediaRecorder = null
        _isRecording.value = false
        _elapsedSeconds.value = 0
        _amplitude.value = 0

        currentOutputFile?.let {
            if (it.exists()) {
                it.delete()
            }
        }
        currentOutputFile = null
    }

    fun release() {
        cancelRecording()
    }
}
