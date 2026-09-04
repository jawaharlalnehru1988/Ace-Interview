package com.example.util.audio

import android.content.Context
import android.media.MediaPlayer
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

class AudioPlaybackManager(private val context: Context) {

    private val tag = "AudioPlaybackManager"
    private var mediaPlayer: MediaPlayer? = null
    private var progressJob: Job? = null
    private val scope = CoroutineScope(Dispatchers.Default)

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    private val _currentlyPlayingPath = MutableStateFlow<String?>(null)
    val currentlyPlayingPath: StateFlow<String?> = _currentlyPlayingPath.asStateFlow()

    private val _currentPositionMs = MutableStateFlow(0)
    val currentPositionMs: StateFlow<Int> = _currentPositionMs.asStateFlow()

    private val _durationMs = MutableStateFlow(0)
    val durationMs: StateFlow<Int> = _durationMs.asStateFlow()

    fun play(filePath: String) {
        val file = File(filePath)
        if (!file.exists()) {
            Log.e(tag, "Audio file does not exist: $filePath")
            return
        }

        // If currently playing the same file, toggle pause
        if (_currentlyPlayingPath.value == filePath && mediaPlayer != null) {
            if (_isPlaying.value) {
                pause()
            } else {
                resume()
            }
            return
        }

        stop()

        try {
            val player = MediaPlayer().apply {
                setDataSource(filePath)
                prepare()
                setOnCompletionListener {
                    _isPlaying.value = false
                    _currentPositionMs.value = duration
                    stopProgressJob()
                }
            }

            mediaPlayer = player
            _currentlyPlayingPath.value = filePath
            _durationMs.value = player.duration
            _currentPositionMs.value = 0

            player.start()
            _isPlaying.value = true
            startProgressJob()
        } catch (e: Exception) {
            Log.e(tag, "Failed to start audio playback", e)
            stop()
        }
    }

    fun pause() {
        try {
            mediaPlayer?.let {
                if (it.isPlaying) {
                    it.pause()
                    _isPlaying.value = false
                    stopProgressJob()
                }
            }
        } catch (e: Exception) {
            Log.e(tag, "Error pausing audio", e)
        }
    }

    fun resume() {
        try {
            mediaPlayer?.let {
                it.start()
                _isPlaying.value = true
                startProgressJob()
            }
        } catch (e: Exception) {
            Log.e(tag, "Error resuming audio", e)
        }
    }

    fun stop() {
        stopProgressJob()
        try {
            mediaPlayer?.apply {
                if (isPlaying) {
                    stop()
                }
                release()
            }
        } catch (_: Exception) {}
        mediaPlayer = null
        _isPlaying.value = false
        _currentlyPlayingPath.value = null
        _currentPositionMs.value = 0
        _durationMs.value = 0
    }

    fun seekTo(positionMs: Int) {
        try {
            mediaPlayer?.seekTo(positionMs)
            _currentPositionMs.value = positionMs
        } catch (e: Exception) {
            Log.e(tag, "Error seeking audio", e)
        }
    }

    private fun startProgressJob() {
        stopProgressJob()
        progressJob = scope.launch {
            while (isActive && _isPlaying.value) {
                delay(200)
                try {
                    mediaPlayer?.let {
                        if (it.isPlaying) {
                            _currentPositionMs.value = it.currentPosition
                        }
                    }
                } catch (_: Exception) {}
            }
        }
    }

    private fun stopProgressJob() {
        progressJob?.cancel()
        progressJob = null
    }

    fun release() {
        stop()
    }
}
