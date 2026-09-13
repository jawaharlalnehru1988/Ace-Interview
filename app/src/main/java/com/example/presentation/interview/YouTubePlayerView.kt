package com.example.presentation.interview

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

class YouTubeBridge(
    private val onVideoEndedCallback: () -> Unit
) {
    private val mainHandler = Handler(Looper.getMainLooper())

    @JavascriptInterface
    fun onVideoEnded() {
        mainHandler.post {
            onVideoEndedCallback()
        }
    }
}

/**
 * Robust in-app YouTube Player using Android WebView with hardware acceleration,
 * official IFrame API, and automated series progression.
 */
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun YouTubePlayerView(
    videoId: String,
    onVideoEnded: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val bridge = remember(onVideoEnded) { YouTubeBridge(onVideoEnded) }

    val webView = remember {
        WebView(context).apply {
            // Hardware acceleration is strictly required for WebView video decoding/rendering on Android
            setLayerType(View.LAYER_TYPE_HARDWARE, null)

            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                mediaPlaybackRequiresUserGesture = false
                allowFileAccess = false
                allowContentAccess = false
                cacheMode = WebSettings.LOAD_DEFAULT
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            }
            webChromeClient = WebChromeClient()
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                }
            }
            setBackgroundColor(android.graphics.Color.BLACK)
            addJavascriptInterface(bridge, "AndroidBridge")
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            try {
                webView.loadUrl("about:blank")
                webView.onPause()
                webView.destroy()
            } catch (_: Exception) {}
        }
    }

    // Load or switch video when videoId changes
    LaunchedEffect(videoId) {
        val htmlContent = buildYouTubeHtml(videoId)
        webView.loadDataWithBaseURL(
            "https://www.youtube.com",
            htmlContent,
            "text/html",
            "UTF-8",
            null
        )
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Black)
            .testTag("youtube_player_box")
    ) {
        AndroidView(
            factory = { webView },
            modifier = Modifier.fillMaxSize()
        )
    }
}

private fun buildYouTubeHtml(videoId: String): String {
    return """
        <!DOCTYPE html>
        <html>
        <head>
          <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
          <style>
            * { margin: 0; padding: 0; box-sizing: border-box; }
            html, body {
              width: 100%;
              height: 100%;
              background-color: #000000;
              overflow: hidden;
            }
            #player {
              position: absolute;
              top: 0;
              left: 0;
              width: 100%;
              height: 100%;
            }
          </style>
          <script src="https://www.youtube.com/iframe_api"></script>
        </head>
        <body>
          <div id="player"></div>
          <script>
            var player;
            function onYouTubeIframeAPIReady() {
              player = new YT.Player('player', {
                height: '100%',
                width: '100%',
                videoId: '$videoId',
                playerVars: {
                  'autoplay': 1,
                  'playsinline': 1,
                  'enablejsapi': 1,
                  'fs': 1,
                  'rel': 0,
                  'modestbranding': 1,
                  'origin': 'https://www.youtube.com'
                },
                events: {
                  'onReady': onPlayerReady,
                  'onStateChange': onPlayerStateChange
                }
              });
            }

            function onPlayerReady(event) {
              event.target.playVideo();
            }

            function onPlayerStateChange(event) {
              // YT.PlayerState.ENDED is 0
              if (event.data === 0) {
                if (window.AndroidBridge && window.AndroidBridge.onVideoEnded) {
                  window.AndroidBridge.onVideoEnded();
                }
              }
            }
          </script>
        </body>
        </html>
    """.trimIndent()
}
