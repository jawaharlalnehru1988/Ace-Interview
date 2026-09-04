package com.example.presentation.practice

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.CloudQueue
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.DataObject
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Schema
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material.icons.filled.Web
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.model.TechnicalCategory
import com.example.presentation.common.LoadingState
import com.example.presentation.common.ScreenHeader
import com.example.presentation.common.StatusBadge
import com.example.presentation.viewmodel.PracticeUiState
import com.example.presentation.viewmodel.PracticeViewModel
import kotlinx.coroutines.launch

@Composable
fun PracticeScreen(
    viewModel: PracticeViewModel,
    onNavigateToQuiz: (categoryId: String, categoryName: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Box(modifier = modifier.fillMaxSize()) {
        when (val current = state) {
            is PracticeUiState.Loading -> {
                LoadingState(modifier = Modifier.fillMaxSize())
            }
            is PracticeUiState.Success -> {
                PracticeContent(
                    categories = current.categories,
                    activeFilter = current.activeFilter,
                    searchQuery = current.searchQuery,
                    onFilterSelected = { viewModel.setFilter(it) },
                    onSearchChanged = { viewModel.onSearchQueryChanged(it) },
                    onCategoryClick = { category ->
                        onNavigateToQuiz(category.id, category.name)
                    }
                )
            }
        }
    }
}

@Composable
fun PracticeContent(
    categories: List<TechnicalCategory>,
    activeFilter: String,
    searchQuery: String,
    onFilterSelected: (String) -> Unit,
    onSearchChanged: (String) -> Unit,
    onCategoryClick: (TechnicalCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    val filterOptions = listOf("All", "Backend", "Architecture", "Infra & Sec", "Frontend")

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("practice_screen_content")
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
            ScreenHeader(
                title = "Technical Practice",
                subtitle = "10 Core domains for software engineering interview rounds"
            )
        }

        item {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("practice_search_field"),
                placeholder = { Text("Search topics, keywords (e.g. Spring, SQL)...") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
                )
            )
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

        items(categories, key = { it.id }) { category ->
            CategoryCardItem(
                category = category,
                onClick = { onCategoryClick(category) },
                onSubLevelClick = { subId, subName ->
                    onCategoryClick(category.copy(id = subId, name = subName))
                }
            )
        }
    }
}

@Composable
fun CategoryCardItem(
    category: TechnicalCategory,
    onClick: () -> Unit,
    onSubLevelClick: ((subId: String, subName: String) -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val icon = getCategoryIcon(category.id)

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .testTag("category_card_${category.id}"),
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)),
        tonalElevation = 1.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f),
                            shape = RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = category.name,
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        StatusBadge(
                            text = category.badgeText,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = category.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 2
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val totalCategoryScore = category.concepts.mapNotNull { it.userScore }.sum()
                        val totalCategoryAttempted = category.concepts.mapNotNull { it.totalQuestionsAttempted }.sum()
                        val hasCategoryScore = totalCategoryAttempted > 0

                        Text(
                            text = if (category.concepts.isNotEmpty()) "${category.concepts.size} Concepts • ${category.questionCount} Qs" else "${category.questionCount} Questions",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "•",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = if (hasCategoryScore) "Score: $totalCategoryScore/$totalCategoryAttempted" else category.difficulty,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = if (hasCategoryScore) FontWeight.Bold else FontWeight.Normal
                            ),
                            color = if (hasCategoryScore) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    imageVector = Icons.Filled.ChevronRight,
                    contentDescription = "Open category",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                )
            }

            // Concept-based practice modules (e.g. OOP, Collections, Concurrency, etc.)
            if (category.concepts.isNotEmpty() && onSubLevelClick != null) {
                Spacer(modifier = Modifier.height(10.dp))
                val listState = rememberLazyListState()
                val highlightedIndex = category.concepts.indexOfFirst { it.isLastAttempted }
                LaunchedEffect(highlightedIndex) {
                    if (highlightedIndex >= 0) {
                        listState.animateScrollToItem(highlightedIndex)
                    }
                }
                LazyRow(
                    state = listState,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(category.concepts, key = { it.id }) { concept ->
                        val chipLabel = if (concept.hasScore) {
                            "${concept.name}: ${concept.scoreDisplay}"
                        } else {
                            "${concept.name} (${concept.questionCount})"
                        }
                        LevelPracticeChip(
                            label = chipLabel,
                            isHighlighted = concept.isLastAttempted,
                            hasScore = concept.hasScore,
                            onClick = { onSubLevelClick(concept.id, "${category.name}: ${concept.name}") }
                        )
                    }
                }
            } else if ((category.id == "java" || category.id == "spring_boot" || category.id == "microservices" || category.id == "hld" || category.id == "lld" || category.id == "sql" || category.id == "angular" || category.id == "security") && onSubLevelClick != null) {
                val prefix = when (category.id) {
                    "java" -> "java"
                    "spring_boot" -> "spring"
                    "microservices" -> "ms"
                    "hld" -> "hld"
                    "lld" -> "lld"
                    "sql" -> "sql"
                    "angular" -> "ng"
                    else -> "sec"
                }
                val titlePrefix = when (category.id) {
                    "java" -> "Java"
                    "spring_boot" -> "Spring Boot"
                    "microservices" -> "Microservices"
                    "hld" -> "HLD"
                    "lld" -> "LLD"
                    "sql" -> "SQL"
                    "angular" -> "Angular"
                    else -> "Security"
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    LevelPracticeChip(
                        label = "Beginner",
                        onClick = { onSubLevelClick("${prefix}_beginner", "$titlePrefix Beginner") },
                        modifier = Modifier.weight(1f)
                    )
                    LevelPracticeChip(
                        label = "Intermediate",
                        onClick = { onSubLevelClick("${prefix}_intermediate", "$titlePrefix Intermediate") },
                        modifier = Modifier.weight(1f)
                    )
                    LevelPracticeChip(
                        label = "Advanced",
                        onClick = { onSubLevelClick("${prefix}_advanced", "$titlePrefix Advanced") },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun LevelPracticeChip(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isHighlighted: Boolean = false,
    hasScore: Boolean = false
) {
    val containerColor = when {
        isHighlighted -> MaterialTheme.colorScheme.primaryContainer
        hasScore -> MaterialTheme.colorScheme.surfaceVariant
        else -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
    }
    val borderColor = when {
        isHighlighted -> MaterialTheme.colorScheme.primary
        hasScore -> MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
        else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)
    }
    val textColor = when {
        isHighlighted -> MaterialTheme.colorScheme.onPrimaryContainer
        hasScore -> MaterialTheme.colorScheme.primary
        else -> MaterialTheme.colorScheme.primary.copy(alpha = 0.85f)
    }

    Surface(
        modifier = modifier
            .clickable { onClick() }
            .testTag("level_chip_${label.lowercase().replace(" ", "_").replace("(", "").replace(")", "").replace(":", "")}"),
        shape = RoundedCornerShape(8.dp),
        color = containerColor,
        border = BorderStroke(if (isHighlighted) 2.dp else 1.dp, borderColor)
    ) {
        Row(
            modifier = Modifier.padding(vertical = 6.dp, horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            if (isHighlighted) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Attempted",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(13.dp)
                )
            } else if (hasScore) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Scored",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(13.dp)
                )
            }
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = if (isHighlighted) FontWeight.Bold else FontWeight.SemiBold,
                    fontSize = 12.sp
                ),
                color = textColor,
                maxLines = 1
            )
        }
    }
}

fun getCategoryIcon(id: String): ImageVector {
    return when (id) {
        "java" -> Icons.Filled.DataObject
        "spring_boot" -> Icons.Filled.Terminal
        "microservices" -> Icons.Filled.Hub
        "hld" -> Icons.Filled.CloudQueue
        "lld" -> Icons.Filled.Layers
        "system_design" -> Icons.Filled.Schema
        "security" -> Icons.Filled.Security
        "sql" -> Icons.Filled.Storage
        "angular" -> Icons.Filled.Web
        "devops" -> Icons.Filled.Code
        else -> Icons.Filled.DataObject
    }
}
