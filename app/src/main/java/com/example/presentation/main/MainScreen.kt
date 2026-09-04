package com.example.presentation.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ScreenDestination.HOME.route

    val viewModelFactory = ViewModelFactory(container.interviewRepository, container.application)

    val showBottomBar = currentRoute.startsWith("mcq_quiz/").not() && currentRoute.startsWith("mock_interview/").not()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.safeDrawing,
        bottomBar = {
            if (showBottomBar) {
                Column {
                    androidx.compose.material3.HorizontalDivider(
                        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.35f),
                        thickness = 1.dp
                    )
                    NavigationBar(
                        windowInsets = WindowInsets.navigationBars,
                        containerColor = MaterialTheme.colorScheme.surface,
                        tonalElevation = 0.dp,
                        modifier = Modifier.testTag("bottom_navigation_bar")
                    ) {
                        ScreenDestination.entries.forEach { destination ->
                            val isSelected = currentRoute == destination.route
                            NavigationBarItem(
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
                                icon = {
                                    Icon(
                                        imageVector = if (isSelected) destination.selectedIcon else destination.unselectedIcon,
                                        contentDescription = destination.label
                                    )
                                },
                                label = {
                                    Text(
                                        text = destination.label,
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            fontSize = 11.sp,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                                        )
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = MaterialTheme.colorScheme.primary,
                                    selectedTextColor = MaterialTheme.colorScheme.primary,
                                    indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                                ),
                                modifier = Modifier.testTag(destination.testTag)
                            )
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
                val dsaViewModel: DsaViewModel = viewModel(factory = viewModelFactory)
                DsaScreen(viewModel = dsaViewModel)
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
                route = "mock_interview/{trackId}/{trackTitle}",
                arguments = listOf(
                    navArgument("trackId") { type = NavType.StringType },
                    navArgument("trackTitle") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val trackId = backStackEntry.arguments?.getString("trackId") ?: "java_interview"
                val rawTrackTitle = backStackEntry.arguments?.getString("trackTitle") ?: "Mock Interview"
                val trackTitle = try {
                    java.net.URLDecoder.decode(rawTrackTitle, "UTF-8")
                } catch (_: Exception) {
                    rawTrackTitle
                }

                val mockViewModel: com.example.presentation.interview.MockInterviewViewModel = viewModel(factory = viewModelFactory)
                LaunchedEffect(trackId) {
                    mockViewModel.loadSession(trackId, trackTitle)
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
