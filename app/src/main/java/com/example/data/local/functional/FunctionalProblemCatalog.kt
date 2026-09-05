package com.example.data.local.functional

import com.example.domain.model.FunctionalProblem
import com.example.domain.model.FunctionalTrack

object FunctionalProblemCatalog {

    fun getAllProblems(): List<FunctionalProblem> {
        return JavaStreamProblems.getAll() +
                JsFunctionalProblems.getAll() +
                RxjsProblems.getAll()
    }

    fun getProblemsByTrack(trackId: String): List<FunctionalProblem> {
        return when (trackId) {
            "java_stream" -> JavaStreamProblems.getAll()
            "js_functional" -> JsFunctionalProblems.getAll()
            "rxjs" -> RxjsProblems.getAll()
            else -> JavaStreamProblems.getAll()
        }
    }

    fun getTracks(): List<FunctionalTrack> {
        val javaCount = JavaStreamProblems.getAll().size
        val jsCount = JsFunctionalProblems.getAll().size
        val rxjsCount = RxjsProblems.getAll().size

        return listOf(
            FunctionalTrack(
                id = "java_stream",
                title = "Java Stream API",
                subtitle = "100+ Stream Pipelines & Collectors",
                description = "Master Java 8+ functional stream pipelines: groupingBy, partitioningBy, flatMap, custom collectors, and imperative comparisons.",
                totalCount = javaCount,
                solvedCount = 0,
                tags = listOf("Streams", "Collectors", "groupingBy", "flatMap", "Parallel")
            ),
            FunctionalTrack(
                id = "js_functional",
                title = "JS Built-in Methods",
                subtitle = "100+ Functional Array & Object Utilities",
                description = "Deep dive into pure functions: map, filter, reduce, Object.groupBy, immutability, currying, and loop comparisons.",
                totalCount = jsCount,
                solvedCount = 0,
                tags = listOf("reduce", "Object.groupBy", "Immutability", "Currying", "Pipe")
            ),
            FunctionalTrack(
                id = "rxjs",
                title = "RxJS Reactive Streams",
                subtitle = "100+ Asynchronous & Observable Pipelines",
                description = "Reactive programming with switchMap, mergeMap, concatMap, exhaustMap, combineLatest, debounceTime, and error recovery.",
                totalCount = rxjsCount,
                solvedCount = 0,
                tags = listOf("Observables", "switchMap", "combineLatest", "debounce", "State")
            )
        )
    }
}
