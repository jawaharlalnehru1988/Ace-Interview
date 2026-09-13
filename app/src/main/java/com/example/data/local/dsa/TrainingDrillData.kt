package com.example.data.local.dsa

import com.example.domain.model.TrainingDrill
import com.example.domain.model.TrainingTopic

object TrainingDrillData {

    val arrayDrills: List<TrainingDrill> = arrayTrainingDrills
    val stringDrills: List<TrainingDrill> = stringTrainingDrills
    val recursionDrills: List<TrainingDrill> = recursionTrainingDrills
    val linkedListDrills: List<TrainingDrill> = linkedListTrainingDrills
    val dpDrills: List<TrainingDrill> = dpTrainingDrills

    fun getTopics(): List<TrainingTopic> {
        return listOf(
            TrainingTopic(
                id = "arrays",
                name = "Arrays Progression",
                description = "Master pointers, windows, prefix memory, binary search, and matrix mechanics step-by-step with 1 concept per drill.",
                drills = arrayDrills,
                totalCount = arrayDrills.size
            ),
            TrainingTopic(
                id = "strings",
                name = "Strings Progression",
                description = "Learn character frequencies, two-pointer parsing, sliding windows, stacks, Rabin-Karp, and Tries.",
                drills = stringDrills,
                totalCount = stringDrills.size
            ),
            TrainingTopic(
                id = "recursion",
                name = "Recursion & Backtracking",
                description = "Demystify call stacks, tree branching, state undoing, permutations, puzzles, and divide & conquer.",
                drills = recursionDrills,
                totalCount = recursionDrills.size
            ),
            TrainingTopic(
                id = "linked_list",
                name = "Linked List Mechanics",
                description = "Build muscle memory for pointer rewiring, runner velocities, dummy heads, cycle entry, and LRU/LFU caches.",
                drills = linkedListDrills,
                totalCount = linkedListDrills.size
            ),
            TrainingTopic(
                id = "dp",
                name = "Dynamic Programming",
                description = "Bridge recursion to memoization, tabulation, state space optimization, knapsacks, grids, and interval DP.",
                drills = dpDrills,
                totalCount = dpDrills.size
            )
        )
    }

    fun getDrillsByTopic(topicId: String): List<TrainingDrill> {
        return when (topicId) {
            "arrays" -> arrayDrills
            "strings" -> stringDrills
            "recursion" -> recursionDrills
            "linked_list" -> linkedListDrills
            "dp" -> dpDrills
            else -> arrayDrills
        }
    }

    fun getAllDrills(): List<TrainingDrill> {
        return arrayDrills + stringDrills + recursionDrills + linkedListDrills + dpDrills
    }
}
