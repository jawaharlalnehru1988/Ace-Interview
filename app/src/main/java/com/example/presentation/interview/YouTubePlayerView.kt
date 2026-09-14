package com.example.presentation.interview

import android.util.Log
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

private const val TAG = "YouTubePlayerView"

/**
 * Native in-app YouTube Player using com.pierfrancescosoffritti.androidyoutubeplayer.
 * Handles lifecycle, cueing/loading, auto-advance, and clean error reporting.
 */
@Composable
fun YouTubePlayerView(
    videoId: String,
    onVideoEnded: () -> Unit,
    modifier: Modifier = Modifier,
    autoPlay: Boolean = false
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val cleanVideoId = remember(videoId) { videoId.trim() }
    val currentVideoId = rememberUpdatedState(cleanVideoId)
    val currentOnVideoEnded = rememberUpdatedState(onVideoEnded)

    var youTubePlayerRef by remember { mutableStateOf<YouTubePlayer?>(null) }
    var loadedVideoId by remember { mutableStateOf<String?>(cleanVideoId) }
    var playerViewRef by remember { mutableStateOf<YouTubePlayerView?>(null) }

    // When videoId changes, cue/load the new video on the active player instance
    LaunchedEffect(cleanVideoId, youTubePlayerRef) {
        val player = youTubePlayerRef
        if (player != null && cleanVideoId.isNotBlank() && loadedVideoId != cleanVideoId) {
            Log.d(TAG, "Switching active player to video: $cleanVideoId (autoPlay=$autoPlay)")
            loadedVideoId = cleanVideoId
            if (autoPlay) {
                player.loadVideo(cleanVideoId, 0f)
            } else {
                player.cueVideo(cleanVideoId, 0f)
            }
        }
    }

    DisposableEffect(lifecycleOwner) {
        onDispose {
            playerViewRef?.let { view ->
                try {
                    lifecycleOwner.lifecycle.removeObserver(view)
                    view.release()
                    Log.d(TAG, "Player released cleanly")
                } catch (e: Exception) {
                    Log.w(TAG, "Error releasing player", e)
                }
            }
        }
    }

    AndroidView(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .testTag("youtube_player_box"),
        factory = { context ->
            YouTubePlayerView(context).apply {
                playerViewRef = this
                enableAutomaticInitialization = false
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                lifecycleOwner.lifecycle.addObserver(this)

                val options = IFramePlayerOptions.Builder()
                    .controls(1)
                    .fullscreen(1)
                    .rel(0)
                    .origin("https://ace-interview.app")
                    .build()

                initialize(
                    object : AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            Log.i(TAG, "YouTubePlayer onReady for video: ${currentVideoId.value}")
                            youTubePlayerRef = youTubePlayer
                            if (autoPlay) {
                                youTubePlayer.play()
                            }
                        }

                        override fun onStateChange(
                            youTubePlayer: YouTubePlayer,
                            state: PlayerConstants.PlayerState
                        ) {
                            Log.d(TAG, "Player state changed: $state")
                            if (state == PlayerConstants.PlayerState.ENDED) {
                                currentOnVideoEnded.value()
                            }
                        }

                        override fun onError(
                            youTubePlayer: YouTubePlayer,
                            error: PlayerConstants.PlayerError
                        ) {
                            Log.e(TAG, "YouTubePlayer onError: $error for video ${currentVideoId.value}")
                        }
                    },
                    handleNetworkEvents = true,
                    playerOptions = options,
                    videoId = cleanVideoId
                )
            }
        },
        update = {
            // Handled via LaunchedEffect
        }
    )
}

