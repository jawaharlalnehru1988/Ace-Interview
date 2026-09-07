package com.example.presentation.main

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.AceInterviewAppContainer
import com.example.presentation.dsa.DsaScreen
import com.example.presentation.home.HomeScreen
import com.example.presentation.interview.InterviewScreen
import com.example.presentation.navigation.ScreenDestination
import com.example.presentation.practice.McqPracticeScreen
import com.example.presentation.practice.McqPracticeViewModel
import com.example.presentation.practice.PracticeScreen
import com.example.presentation.profile.ProfileScreen
import com.example.presentation.viewmodel.DsaViewModel
import com.example.presentation.viewmodel.HomeViewModel
import com.example.presentation.viewmodel.InterviewViewModel
import com.example.presentation.viewmodel.PracticeViewModel
import com.example.presentation.viewmodel.ProfileViewModel
import com.example.presentation.viewmodel.ViewModelFactory

@Composable
fun MainScreen(
    container: AceInterviewAppContainer,
    navController: NavHostController = rememberNavController(),
    pendingNavigationIntent: Intent? = null,
    onIntentConsumed: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ScreenDestination.HOME.route

    val viewModelFactory = ViewModelFactory(container.interviewRepository, container.application)
    val dsaViewModel: DsaViewModel = viewModel(factory = viewModelFactory)

    // Handle deep-link notification intents (e.g., daily practice reminder)
    LaunchedEffect(pendingNavigationIntent) {
        val intent = pendingNavigationIntent ?: return@LaunchedEffect
        val navigateTo = intent.getStringExtra("navigate_to")
        if (navigateTo == "practice_quiz") {
            val categoryId = intent.getStringExtra("category_id") ?: "all"
            val categoryName = intent.getStringExtra("category_name") ?: "Practice Drill"
            val encodedName = try {
                java.net.URLEncoder.encode(categoryName, "UTF-8")
            } catch (_: Exception) {
                categoryName
            }
            navController.navigate("mcq_quiz/$categoryId/$encodedName")
            onIntentConsumed()
        } else if (navigateTo == "practice") {
            navController.navigate(ScreenDestination.PRACTICE.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
            onIntentConsumed()
        }
    }

    val showBottomBar = currentRoute.startsWith("mcq_quiz/").not() && currentRoute.startsWith("mock_interview/").not()
    val navScrollState = rememberScrollState()
    var tabPositions by remember { mutableStateOf(mapOf<Int, Int>()) }
    val selectedIndex = ScreenDestination.entries.indexOfFirst { it.route == currentRoute }

    LaunchedEffect(selectedIndex, tabPositions, navScrollState.viewportSize) {
        if (selectedIndex >= 0 && navScrollState.viewportSize > 0) {
            tabPositions[selectedIndex]?.let { centerX ->
                val halfViewport = navScrollState.viewportSize / 2
                val targetScroll = (centerX - halfViewport).coerceAtLeast(0)
                navScrollState.animateScrollTo(targetScroll)
            }
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.safeDrawing,
        bottomBar = {
            if (showBottomBar) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("bottom_navigation_bar"),
                    color = MaterialTheme.colorScheme.surface,
                    tonalElevation = 2.dp
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .navigationBarsPadding()
                            .padding(bottom = 10.dp)
                    ) {
                        HorizontalDivider(
                            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.35f),
                            thickness = 1.dp
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(navScrollState)
                                .padding(horizontal = 8.dp, vertical = 6.dp),
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ScreenDestination.entries.forEachIndexed { index, destination ->
                                val isSelected = currentRoute == destination.route
                                ScrollableNavTabItem(
                                    selected = isSelected,
                                    onClick = {
                                        if (currentRoute != destination.route) {
                                            navController.navigate(destination.route) {
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }
                                    },
                                    icon = if (isSelected) destination.selectedIcon else destination.unselectedIcon,
                                    label = destination.label,
                                    testTag = destination.testTag,
                                    modifier = Modifier.onGloballyPositioned { coords ->
                                        val parentCoords = coords.parentCoordinates
                                        if (parentCoords != null) {
                                            val x = coords.positionInParent().x.toInt()
                                            val width = coords.size.width
                                            val centerX = x + width / 2
                                            if (tabPositions[index] != centerX) {
                                                tabPositions = tabPositions + (index to centerX)
                                            }
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ScreenDestination.HOME.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(ScreenDestination.HOME.route) {
                val homeViewModel: HomeViewModel = viewModel(factory = viewModelFactory)
                HomeScreen(
                    viewModel = homeViewModel,
                    onNavigateToPractice = {
                        navController.navigate(ScreenDestination.PRACTICE.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    onNavigateToQuiz = { categoryId, categoryName ->
                        val encodedName = java.net.URLEncoder.encode(categoryName, "UTF-8")
                        navController.navigate("mcq_quiz/$categoryId/$encodedName")
                    },
                    onNavigateToInterview = { trackId, trackTitle, conceptId ->
                        val encodedTitle = try {
                            java.net.URLEncoder.encode(trackTitle, "UTF-8")
                        } catch (_: Exception) {
                            trackTitle
                        }
                        val route = if (!conceptId.isNullOrBlank()) {
                            "mock_interview/$trackId/$encodedTitle?conceptId=$conceptId"
                        } else {
                            "mock_interview/$trackId/$encodedTitle"
                        }
                        navController.navigate(route)
                    },
                    onNavigateToDsa = {
                        dsaViewModel.selectTopic(null)
                        navController.navigate(ScreenDestination.DSA.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    onNavigateToDsaTopic = { topicId ->
                        dsaViewModel.selectTopic(topicId)
                        navController.navigate(ScreenDestination.DSA.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                        }
                    },
                    onNavigateToTricky = {
                        navController.navigate(ScreenDestination.TRICKY.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }

            composable(ScreenDestination.PRACTICE.route) {
                val practiceViewModel: PracticeViewModel = viewModel(factory = viewModelFactory)
                PracticeScreen(
                    viewModel = practiceViewModel,
                    onNavigateToQuiz = { categoryId, categoryName ->
                        val encodedName = java.net.URLEncoder.encode(categoryName, "UTF-8")
                        navController.navigate("mcq_quiz/$categoryId/$encodedName")
                    }
                )
            }

            composable(
                route = "mcq_quiz/{categoryId}/{categoryName}",
                arguments = listOf(
                    navArgument("categoryId") { type = NavType.StringType },
                    navArgument("categoryName") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val categoryId = backStackEntry.arguments?.getString("categoryId") ?: "all"
                val rawCategoryName = backStackEntry.arguments?.getString("categoryName") ?: "Practice"
                val categoryName = try {
                    java.net.URLDecoder.decode(rawCategoryName, "UTF-8")
                } catch (_: Exception) {
                    rawCategoryName
                }

                val mcqViewModel: McqPracticeViewModel = viewModel(factory = viewModelFactory)
                LaunchedEffect(categoryId) {
                    mcqViewModel.startQuiz(categoryId, categoryName)
                }

                McqPracticeScreen(
                    viewModel = mcqViewModel,
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }

            composable(ScreenDestination.DSA.route) {
                DsaScreen(viewModel = dsaViewModel)
            }

            composable(ScreenDestination.TRICKY.route) {
                val trickyViewModel: com.example.presentation.tricky.TrickyViewModel = viewModel(factory = viewModelFactory)
                com.example.presentation.tricky.TrickyScreen(
                    viewModel = trickyViewModel,
                    onStartTrickyQuiz = { trackId, trackTitle ->
                        val encodedTitle = try {
                            java.net.URLEncoder.encode(trackTitle, "UTF-8")
                        } catch (_: Exception) {
                            trackTitle
                        }
                        navController.navigate("mcq_quiz/$trackId/$encodedTitle")
                    }
                )
            }

            composable(ScreenDestination.FUNCTIONAL.route) {
                val functionalViewModel: com.example.presentation.functional.FunctionalViewModel = viewModel(factory = viewModelFactory)
                com.example.presentation.functional.FunctionalScreen(viewModel = functionalViewModel)
            }

            composable(ScreenDestination.INTERVIEW.route) {
                val interviewViewModel: InterviewViewModel = viewModel(factory = viewModelFactory)
                InterviewScreen(
                    viewModel = interviewViewModel,
                    onStartSession = { trackId, trackTitle ->
                        val encodedTitle = try {
                            java.net.URLEncoder.encode(trackTitle, "UTF-8")
                        } catch (_: Exception) {
                            trackTitle
                        }
                        navController.navigate("mock_interview/$trackId/$encodedTitle")
                    }
                )
            }

            composable(
                route = "mock_interview/{trackId}/{trackTitle}?conceptId={conceptId}",
                arguments = listOf(
                    navArgument("trackId") { type = NavType.StringType },
                    navArgument("trackTitle") { type = NavType.StringType },
                    navArgument("conceptId") {
                        type = NavType.StringType
                        nullable = true
                        defaultValue = null
                    }
                )
            ) { backStackEntry ->
                val trackId = backStackEntry.arguments?.getString("trackId") ?: "java_interview"
                val rawTrackTitle = backStackEntry.arguments?.getString("trackTitle") ?: "Mock Interview"
                val trackTitle = try {
                    java.net.URLDecoder.decode(rawTrackTitle, "UTF-8")
                } catch (_: Exception) {
                    rawTrackTitle
                }
                val conceptId = backStackEntry.arguments?.getString("conceptId")

                val mockViewModel: com.example.presentation.interview.MockInterviewViewModel = viewModel(factory = viewModelFactory)
                LaunchedEffect(trackId, conceptId) {
                    mockViewModel.loadSession(trackId, trackTitle, conceptId)
                }

                com.example.presentation.interview.MockInterviewSessionScreen(
                    viewModel = mockViewModel,
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }

            composable(ScreenDestination.PROFILE.route) {
                val profileViewModel: ProfileViewModel = viewModel(factory = viewModelFactory)
                ProfileScreen(viewModel = profileViewModel)
            }
        }
    }
}

@Composable
private fun ScrollableNavTabItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector,
    label: String,
    testTag: String,
    modifier: Modifier = Modifier
) {
    val contentColor = if (selected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }
    val indicatorColor = if (selected) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        Color.Transparent
    }

    Column(
        modifier = modifier
            .widthIn(min = 76.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .testTag(testTag),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(indicatorColor)
                .padding(horizontal = 16.dp, vertical = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = contentColor,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            maxLines = 1,
            softWrap = false,
            style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 11.sp,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium
            ),
            color = contentColor
        )
    }
}

