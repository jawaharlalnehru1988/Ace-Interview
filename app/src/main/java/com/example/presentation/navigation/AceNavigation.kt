package com.example.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountTree
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.outlined.AccountTree
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.RecordVoiceOver
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.outlined.Psychology
import androidx.compose.material.icons.filled.AltRoute
import androidx.compose.material.icons.outlined.AltRoute
import androidx.compose.ui.graphics.vector.ImageVector

enum class ScreenDestination(
    val route: String,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val testTag: String
) {
    HOME(
        route = "home",
        label = "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        testTag = "nav_tab_home"
    ),
    PRACTICE(
        route = "practice",
        label = "Practice",
        selectedIcon = Icons.Filled.MenuBook,
        unselectedIcon = Icons.Outlined.MenuBook,
        testTag = "nav_tab_practice"
    ),
    DSA(
        route = "dsa",
        label = "DSA",
        selectedIcon = Icons.Filled.AccountTree,
        unselectedIcon = Icons.Outlined.AccountTree,
        testTag = "nav_tab_dsa"
    ),
    TRICKY(
        route = "tricky",
        label = "Tricky",
        selectedIcon = Icons.Filled.Psychology,
        unselectedIcon = Icons.Outlined.Psychology,
        testTag = "nav_tab_tricky"
    ),
    FUNCTIONAL(
        route = "functional",
        label = "Functional",
        selectedIcon = Icons.Filled.AltRoute,
        unselectedIcon = Icons.Outlined.AltRoute,
        testTag = "nav_tab_functional"
    ),
    INTERVIEW(
        route = "interview",
        label = "Interview",
        selectedIcon = Icons.Filled.RecordVoiceOver,
        unselectedIcon = Icons.Outlined.RecordVoiceOver,
        testTag = "nav_tab_interview"
    ),
    PROFILE(
        route = "profile",
        label = "Profile",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
        testTag = "nav_tab_profile"
    )
}
