package com.example.presentation.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountTree
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.model.TodayTraining
import com.example.domain.model.TrainingType
import com.example.domain.model.UserDashboard
import com.example.domain.model.WeakArea
import com.example.presentation.common.LoadingState
import com.example.presentation.common.MetricStatCard
import com.example.presentation.common.ScreenHeader
import com.example.presentation.common.SectionTitle
import com.example.presentation.common.StatusBadge
import com.example.presentation.viewmodel.HomeUiState
import com.example.presentation.viewmodel.HomeViewModel
import com.example.ui.theme.AmberTertiaryLight
import com.example.ui.theme.CyanSecondaryDark
import com.example.ui.theme.DangerRed
import com.example.ui.theme.IndigoPrimaryDark
import com.example.ui.theme.SuccessGreen
import com.example.ui.theme.WarningOrange

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
    onNavigateToPractice: () -> Unit = {},
    onNavigateToQuiz: (categoryId: String, categoryName: String) -> Unit = { _, _ -> },
    onNavigateToInterview: (trackId: String, trackTitle: String, conceptId: String?) -> Unit = { _, _, _ -> },
    onNavigateToDsa: () -> Unit = {},
    onNavigateToTricky: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    when (val current = state) {
        is HomeUiState.Loading -> {
            LoadingState(modifier = modifier.fillMaxSize())
        }
        is HomeUiState.Success -> {
            HomeContent(
                dashboard = current.dashboard,
                onToggleTraining = { viewModel.toggleTrainingItem(it) },
                onNavigateToPractice = onNavigateToPractice,
                onNavigateToQuiz = onNavigateToQuiz,
                onNavigateToInterview = onNavigateToInterview,
                onNavigateToDsa = onNavigateToDsa,
                onNavigateToTricky = onNavigateToTricky,
                modifier = modifier
            )
        }
    }
}

@Composable
fun HomeContent(
    dashboard: UserDashboard,
    onToggleTraining: (String) -> Unit,
    onNavigateToPractice: () -> Unit,
    onNavigateToQuiz: (categoryId: String, categoryName: String) -> Unit = { _, _ -> },
    onNavigateToInterview: (trackId: String, trackTitle: String, conceptId: String?) -> Unit = { _, _, _ -> },
    onNavigateToDsa: () -> Unit = {},
    onNavigateToTricky: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("home_screen_content")
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
            ScreenHeader(
                title = "Ace Interview",
                subtitle = "Real-Time Interview Readiness"
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = MaterialTheme.colorScheme.surface,
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.4f))
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.LocalFireDepartment,
                                contentDescription = "Streak",
                                tint = WarningOrange,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "${dashboard.currentStreakDays}d",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp
                                ),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    // User Profile Chip
                    Surface(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(34.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = "AI",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp
                                ),
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }
            }
        }

        // --- Interview Readiness Hero Card (Clean Utility Minimal) ---
        item {
            InterviewReadinessCard(
                readinessScore = dashboard.readinessScore,
                readinessLevel = dashboard.readinessLevel,
                questionsCompleted = dashboard.questionsCompleted,
                targetQuestions = dashboard.targetQuestions
            )
        }

        // --- 4 Quick Metrics Grid (2 rows x 2 columns) ---
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    MetricStatCard(
                        title = "Streak",
                        value = "${dashboard.currentStreakDays}",
                        subtitle = "Days",
                        icon = Icons.Filled.LocalFireDepartment,
                        iconTint = WarningOrange,
                        modifier = Modifier
                            .weight(1f)
                            .testTag("metric_streak")
                    )
                    MetricStatCard(
                        title = "Completed",
                        value = "${dashboard.questionsCompleted}",
                        subtitle = "Tasks",
                        icon = Icons.Filled.School,
                        iconTint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .weight(1f)
                            .testTag("metric_completed")
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    MetricStatCard(
                        title = "Accuracy",
                        value = "${dashboard.accuracyPercentage}",
                        subtitle = "%",
                        icon = Icons.Filled.Speed,
                        iconTint = SuccessGreen,
                        modifier = Modifier
                            .weight(1f)
                            .testTag("metric_accuracy")
                    )
                    MetricStatCard(
                        title = "DSA Solved",
                        value = "${dashboard.dsaSolvedCount}",
                        subtitle = "${dashboard.dsaSolvedCount} of ${dashboard.totalDsaProblems}",
                        icon = Icons.Filled.AccountTree,
                        iconTint = CyanSecondaryDark,
                        modifier = Modifier
                            .weight(1f)
                            .clickable { onNavigateToDsa() }
                            .testTag("metric_dsa_solved")
                    )
                }
            }
        }

        // --- Dedicated DSA Progress Roadmap Card ---
        item {
            val dsaPercent = if (dashboard.totalDsaProblems > 0) {
                (dashboard.dsaSolvedCount.toFloat() / dashboard.totalDsaProblems.toFloat() * 100).toInt()
            } else 0

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onNavigateToDsa() }
                    .testTag("card_home_dsa_progress"),
                shape = RoundedCornerShape(18.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.65f),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.AccountTree,
                                contentDescription = "DSA Progress",
                                tint = CyanSecondaryDark,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = "DSA ROADMAP PROGRESS",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.1.sp,
                                    fontSize = 11.sp
                                ),
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        StatusBadge(
                            text = "${dashboard.dsaSolvedCount} / ${dashboard.totalDsaProblems} Solved",
                            color = if (dashboard.dsaSolvedCount > 0) SuccessGreen else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Algorithmic Foundation Coverage",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "$dsaPercent%",
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                            color = if (dsaPercent > 0) SuccessGreen else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    LinearProgressIndicator(
                        progress = { if (dashboard.totalDsaProblems > 0) dashboard.dsaSolvedCount.toFloat() / dashboard.totalDsaProblems.toFloat() else 0f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = MaterialTheme.colorScheme.primary,
                        trackColor = MaterialTheme.colorScheme.surface
                    )
                }
            }
        }

        // --- Dedicated Tricky Code Mastery Card ---
        item {
            val trickyPercent = if (dashboard.trickyTotalCount > 0) {
                (dashboard.trickySolvedCount.toFloat() / dashboard.trickyTotalCount.toFloat() * 100).toInt()
            } else 0

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onNavigateToTricky() }
                    .testTag("card_home_tricky_progress"),
                shape = RoundedCornerShape(18.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.65f),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Psychology,
                                contentDescription = "Tricky Progress",
                                tint = WarningOrange,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = "TRICKY CODE MASTERY",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.1.sp,
                                    fontSize = 11.sp
                                ),
                                color = WarningOrange
                            )
                        }

                        StatusBadge(
                            text = if (dashboard.trickySolvedCount > 0) "${dashboard.trickySolvedCount} Solved (${dashboard.trickyAccuracy}%)" else "Start Drills",
                            color = if (dashboard.trickySolvedCount > 0) SuccessGreen else MaterialTheme.colorScheme.primary
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Java & JS Counter-Intuitive Edge Cases",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "$trickyPercent%",
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                            color = if (trickyPercent > 0) SuccessGreen else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    LinearProgressIndicator(
                        progress = { if (dashboard.trickyTotalCount > 0) dashboard.trickySolvedCount.toFloat() / dashboard.trickyTotalCount.toFloat() else 0f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = WarningOrange,
                        trackColor = MaterialTheme.colorScheme.surface
                    )
                }
            }
        }

        // --- Featured Today's Training Banner ---
        item {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onNavigateToPractice() }
                    .testTag("banner_start_session"),
                shape = RoundedCornerShape(18.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp, vertical = 14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "TODAY'S PRIORITY SESSION",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp,
                                fontSize = 10.sp
                            ),
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f)
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = if (dashboard.questionsCompleted == 0) "Java & Spring Boot Foundations" else "Continuous Technical Drills",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.SemiBold
                            ),
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colorScheme.primary
                    ) {
                        Text(
                            text = "Start",
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }

        // --- Today's Training Checklist Section ---
        item {
            SectionTitle(
                title = "Today's Training",
                badgeText = "${dashboard.todayTrainings.count { it.isCompleted }}/${dashboard.todayTrainings.size} Done"
            )
        }

        items(dashboard.todayTrainings, key = { it.id }) { training ->
            TodayTrainingItem(
                training = training,
                onClick = {
                    if (training.type == TrainingType.MCQ) {
                        onNavigateToQuiz(training.targetId, training.title)
                    } else {
                        onNavigateToInterview(training.targetId, training.title, training.targetConceptId)
                    }
                }
            )
        }

        // --- Weak Areas Section ---
        item {
            SectionTitle(
                title = "Weak Areas for Review",
                badgeText = "Prioritize"
            )
        }

        items(dashboard.weakAreas, key = { it.id }) { weakArea ->
            WeakAreaItem(
                weakArea = weakArea,
                onReviewClick = onNavigateToPractice
            )
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun InterviewReadinessCard(
    readinessScore: Int,
    readinessLevel: String,
    questionsCompleted: Int,
    targetQuestions: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .testTag("card_interview_readiness"),
        shape = RoundedCornerShape(24.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "INTERVIEW READINESS",
                    style = MaterialTheme.typography.labelSmall.copy(
                        letterSpacing = 1.2.sp,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    ),
                    color = MaterialTheme.colorScheme.primary
                )
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.4f))
                ) {
                    Text(
                        text = if (readinessScore >= 80) "L5 READY" else if (readinessScore >= 40) "IN PROGRESS" else "BASELINE",
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 10.sp,
                            letterSpacing = 0.6.sp
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = "$readinessScore%",
                            style = MaterialTheme.typography.displaySmall.copy(
                                fontWeight = FontWeight.Light,
                                letterSpacing = (-1).sp
                            ),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "readiness score",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Medium,
                                fontSize = 11.sp
                            ),
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = if (questionsCompleted == 0) "Complete MCQs to calibrate your benchmark score" else "Target: L5 Software Engineer ($readinessLevel)",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(68.dp)
                ) {
                    CircularProgressIndicator(
                        progress = { readinessScore / 100f },
                        modifier = Modifier.size(68.dp),
                        strokeWidth = 6.dp,
                        color = MaterialTheme.colorScheme.primary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                    Text(
                        text = "$readinessScore%",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Progress bar
            val progress = (questionsCompleted.toFloat() / targetQuestions.toFloat()).coerceIn(0f, 1f)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Foundation Goal Progress",
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "$questionsCompleted / $targetQuestions Qs",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 11.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(CircleShape),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )
        }
    }
}

@Composable
fun TodayTrainingItem(
    training: TodayTraining,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(
            1.dp,
            if (training.isCompleted) SuccessGreen.copy(alpha = 0.5f)
            else MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)
        )
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (training.isCompleted) Icons.Filled.CheckCircle else Icons.Filled.RadioButtonUnchecked,
                contentDescription = if (training.isCompleted) "Completed" else "Incomplete",
                tint = if (training.isCompleted) SuccessGreen else MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    StatusBadge(
                        text = if (training.type == TrainingType.MCQ) "MCQ Drill" else "Interview Practice",
                        color = if (training.type == TrainingType.MCQ) MaterialTheme.colorScheme.primary else IndigoPrimaryDark
                    )
                    StatusBadge(
                        text = training.category,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = training.title,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )

                if (training.subtitle.isNotBlank()) {
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = training.subtitle,
                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    val detailText = if (training.type == TrainingType.INTERVIEW) {
                        "${training.completedCount}/${training.targetGoalCount} Audio Recorded • ${training.questionsCount} Qs in Pool"
                    } else {
                        "${training.questionsCount} Qs • ~${training.estimatedMinutes} min"
                    }
                    Text(
                        text = detailText,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontSize = 11.sp,
                            fontWeight = if (training.isCompleted) FontWeight.Bold else FontWeight.Normal
                        ),
                        color = if (training.isCompleted) SuccessGreen else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                imageVector = Icons.Filled.ChevronRight,
                contentDescription = "Start training",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun WeakAreaItem(
    weakArea: WeakArea,
    onReviewClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onReviewClick() },
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.35f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = weakArea.topic.uppercase(),
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.8.sp,
                            fontSize = 10.sp
                        ),
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = weakArea.subtopic,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = when {
                        weakArea.accuracy < 50 -> DangerRed.copy(alpha = 0.12f)
                        weakArea.accuracy < 60 -> WarningOrange.copy(alpha = 0.12f)
                        else -> MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)
                    },
                    border = BorderStroke(
                        1.dp,
                        when {
                            weakArea.accuracy < 50 -> DangerRed.copy(alpha = 0.3f)
                            weakArea.accuracy < 60 -> WarningOrange.copy(alpha = 0.3f)
                            else -> MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
                        }
                    )
                ) {
                    Text(
                        text = "${weakArea.accuracy}% Acc",
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp
                        ),
                        color = when {
                            weakArea.accuracy < 50 -> DangerRed
                            weakArea.accuracy < 60 -> WarningOrange
                            else -> MaterialTheme.colorScheme.primary
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            LinearProgressIndicator(
                progress = { weakArea.accuracy / 100f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .clip(CircleShape),
                color = when {
                    weakArea.accuracy < 50 -> DangerRed
                    weakArea.accuracy < 60 -> WarningOrange
                    else -> MaterialTheme.colorScheme.primary
                },
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = weakArea.recommendation,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
