package com.example.presentation.interview

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Engineering
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.SmartDisplay
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.model.InterviewScreenMode
import com.example.domain.model.InterviewTrack
import com.example.domain.model.VideoMockInterview
import com.example.domain.model.VideoMockTopic
import com.example.presentation.common.LoadingState
import com.example.presentation.common.ScreenHeader
import com.example.presentation.common.StatusBadge
import com.example.presentation.viewmodel.InterviewUiState
import com.example.presentation.viewmodel.InterviewViewModel

@Composable
fun InterviewScreen(
    viewModel: InterviewViewModel,
    onStartSession: (trackId: String, trackTitle: String) -> Unit = { _, _ -> },
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    Box(modifier = modifier.fillMaxSize()) {
        when (val current = state) {
            is InterviewUiState.Loading -> {
                LoadingState(modifier = Modifier.fillMaxSize())
            }
            is InterviewUiState.Success -> {
                if (current.screenMode == InterviewScreenMode.VIDEO_MOCK) {
                    VideoMockContent(
                        screenMode = current.screenMode,
                        onSelectMode = { viewModel.setScreenMode(it) },
                        topics = current.videoTopics,
                        selectedTopicId = current.selectedVideoTopicId,
                        activeClassroomTopicId = current.activeClassroomTopicId,
                        videoMocks = current.videoMocks,
                        selectedVideo = current.selectedVideo,
                        currentIndex = current.currentVideoIndex,
                        onOpenTopicClassroom = { viewModel.openTopicClassroom(it) },
                        onCloseTopicClassroom = { viewModel.closeTopicClassroom() },
                        onSelectTopic = { viewModel.selectVideoTopic(it) },
                        onSelectVideo = { viewModel.selectVideo(it) },
                        onPlayNext = { viewModel.playNextVideo() },
                        onPlayPrevious = { viewModel.playPreviousVideo() },
                        onStartSession = onStartSession
                    )
                } else {
                    InterviewContent(
                        screenMode = current.screenMode,
                        onSelectMode = { viewModel.setScreenMode(it) },
                        tracks = current.tracks,
                        activeFilter = current.filterRole,
                        onFilterSelected = { viewModel.setFilter(it) },
                        onTrackClick = { track ->
                            viewModel.selectTrack(track.id)
                            onStartSession(track.id, track.title)
                        }
                    )
                }
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        )
    }
}

/**
 * Interactive Mock Sessions View.
 */
@Composable
fun InterviewContent(
    screenMode: InterviewScreenMode,
    onSelectMode: (InterviewScreenMode) -> Unit,
    tracks: List<InterviewTrack>,
    activeFilter: String,
    onFilterSelected: (String) -> Unit,
    onTrackClick: (InterviewTrack) -> Unit,
    modifier: Modifier = Modifier
) {
    val filterOptions = listOf("All", "Mid", "Senior", "Staff")

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("interview_screen_content")
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
            InterviewModeSwitcher(
                selectedMode = screenMode,
                onSelectMode = onSelectMode
            )
        }

        item {
            ScreenHeader(
                title = "Mock Interviews",
                subtitle = "${tracks.size} Specialized tracks replicating real-world technical loops"
            )
        }

        // Info Banner
        item {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Engineering,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(14.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Engineered for Software Engineers",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = "Standard 45-60 minute scenarios modeled after FAANG & Unicorn hiring bars.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f)
                        )
                    }
                }
            }
        }

        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(filterOptions) { filter ->
                    FilterChip(
                        selected = activeFilter == filter,
                        onClick = { onFilterSelected(filter) },
                        label = { Text(filter) },
                        shape = RoundedCornerShape(10.dp),
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    )
                }
            }
        }

        items(tracks, key = { it.id }) { track ->
            InterviewTrackCard(
                track = track,
                onStart = { onTrackClick(track) }
            )
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

/**
 * Curated In-App Video Mock Interviews with Topic Hub and Dedicated Topic Classrooms (Option B).
 */
@Composable
fun VideoMockContent(
    screenMode: InterviewScreenMode,
    onSelectMode: (InterviewScreenMode) -> Unit,
    topics: List<VideoMockTopic>,
    selectedTopicId: String,
    activeClassroomTopicId: String?,
    videoMocks: List<VideoMockInterview>,
    selectedVideo: VideoMockInterview?,
    currentIndex: Int,
    onOpenTopicClassroom: (String) -> Unit,
    onCloseTopicClassroom: () -> Unit,
    onSelectTopic: (String) -> Unit,
    onSelectVideo: (VideoMockInterview) -> Unit,
    onPlayNext: () -> Unit,
    onPlayPrevious: () -> Unit,
    onStartSession: (trackId: String, trackTitle: String) -> Unit,
    modifier: Modifier = Modifier
) {
    // Intercept back button to smoothly return to Topic Hub when inside a dedicated classroom
    BackHandler(enabled = activeClassroomTopicId != null) {
        onCloseTopicClassroom()
    }

    val activeTopic = topics.firstOrNull { it.id.equals(selectedTopicId, ignoreCase = true) }
        ?: topics.firstOrNull()

    if (activeClassroomTopicId == null || activeTopic == null) {
        // --- Option B: 11-Topic Hub ---
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .testTag("video_mock_hub_content")
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
                InterviewModeSwitcher(
                    selectedMode = screenMode,
                    onSelectMode = onSelectMode
                )
            }

            item {
                ScreenHeader(
                    title = "Video Mock Technical Curriculum",
                    subtitle = "Curated real-world technical loops with in-app native player and auto-progression"
                )
            }

            // Educational Hero Banner
            item {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.45f),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f))
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.SmartDisplay,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.size(32.dp)
                        )
                        Spacer(modifier = Modifier.width(14.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Watch & Learn, Then Attempt",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                            Text(
                                text = "Listen to clear English mock interviews by industry leaders to master articulation and system architecture. Select any topic below to open its dedicated video classroom.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.85f)
                            )
                        }
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Technical Domains",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "${topics.size} Topics • 110 Videos",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            // 11 Dedicated Topic Cards
            items(topics, key = { it.id }) { topic ->
                VideoMockTopicCard(
                    topic = topic,
                    onSelectTopic = { onOpenTopicClassroom(topic.id) }
                )
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    } else {
        // --- Option B: Dedicated Topic Classroom ---
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .testTag("topic_classroom_content_${activeTopic.id}")
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
                TopicClassroomTopBar(
                    topic = activeTopic,
                    onBack = onCloseTopicClassroom
                )
            }

            // Main Video Player Section (Native Android-YouTube-Player)
            if (selectedVideo != null) {
                item {
                    VideoMockPlayerSection(
                        video = selectedVideo,
                        currentIndex = currentIndex,
                        totalCountInTopic = videoMocks.size,
                        onVideoEnded = onPlayNext,
                        onPlayNext = onPlayNext,
                        onPlayPrevious = onPlayPrevious,
                        onAttemptMockSession = { trackId, title ->
                            onStartSession(trackId, title)
                        }
                    )
                }
            }

            // Series Playlist Header
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${activeTopic.name} Curriculum Playlist",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "${videoMocks.size} Videos",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            // 10 Topic Videos in the Playlist
            items(videoMocks.size, key = { index -> videoMocks[index].id }) { index ->
                val video = videoMocks[index]
                val isPlaying = video.id == selectedVideo?.id
                VideoMockPlaylistItem(
                    video = video,
                    index = index,
                    isPlaying = isPlaying,
                    onSelect = { onSelectVideo(video) }
                )
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun InterviewTrackCard(
    track: InterviewTrack,
    onStart: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onStart() }
            .testTag("interview_track_${track.id}"),
        shape = RoundedCornerShape(18.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)),
        tonalElevation = 1.dp
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = track.title,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
                )
                StatusBadge(
                    text = track.roleLevel,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = track.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Schedule,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${track.durationMinutes} min",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "• ${track.questionCount} Questions",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant
                ) {
                    Text(
                        text = track.format,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Button(
                onClick = onStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Start Mock Session",
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold)
                )
            }
        }
    }
}
