package com.example.data.local.interview

import com.example.domain.model.InterviewQuestion

object FullStackInterviewQuestions {

    fun getQuestions(): List<InterviewQuestion> = listOf(
        // --- Concept 1: Modern Angular & Reactive State ---
        InterviewQuestion(
            id = "iq_fs_001",
            trackId = "full_stack_interview",
            conceptId = "fs_angular_signals",
            conceptName = "Angular Signals & Change Detection",
            title = "Angular Signals vs RxJS Observables & Zoneless",
            question = "How do Angular Signals change reactivity in Angular 16+? How do they enable fine-grained, Zoneless change detection compared to Zone.js?",
            shortAnswer = "Traditional Angular relied on Zone.js monkey-patching all browser async APIs (setTimeout, XHR, click), triggering a top-down dirty check across the entire component tree whenever an event fired. Angular Signals introduce fine-grained reactivity using a reactive graph: a signal tracks its dependencies and notifies only the specific template nodes or computed expressions that depend on it. This enables Zoneless Angular applications, eliminating Zone.js bundle overhead and top-down tree re-renders in favor of direct, local DOM updates.",
            keyPoints = listOf(
                "Signals provide synchronous, glitch-free reactive state with getter syntax count()",
                "Zone.js monkey-patched async events, forcing broad top-down change detection passes",
                "Signals establish a dependency graph, re-rendering only affected template nodes",
                "computed() derives cached values; effect() executes side-effects on signal change",
                "Enables Zoneless Angular apps with reduced bundle sizes and maximum rendering speed"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_fs_002",
            trackId = "full_stack_interview",
            conceptId = "fs_angular_signals",
            conceptName = "Angular Signals & Change Detection",
            title = "ChangeDetectionStrategy.OnPush Mechanics",
            question = "How does ChangeDetectionStrategy.OnPush optimize Angular component rendering, and what triggers an OnPush component to check its view?",
            shortAnswer = "By default (Default strategy), Angular checks every component in the tree during every change detection cycle. With OnPush, Angular skips checking the component and its sub-tree unless: 1) One of its @Input() properties changes by reference (new object reference), 2) An event originated from within the component template (e.g. (click)), 3) An Observable bound via async pipe emits a new value, 4) A Signal read in the template emits, or 5) ChangeDetectorRef.markForCheck() is explicitly called.",
            keyPoints = listOf(
                "Skips component tree evaluation unless specific change triggers occur",
                "Input references are compared by reference identity (===), requiring immutable data",
                "Template event handlers automatically mark component dirty for check",
                "Async pipe automatically calls markForCheck() on every emission",
                "Drastically cuts CPU rendering time in large enterprise dashboards"
            ),
            difficulty = "Mid-Level"
        ),

        // --- Concept 2: Full Stack Integration & API Design ---
        InterviewQuestion(
            id = "iq_fs_003",
            trackId = "full_stack_interview",
            conceptId = "fs_api_integration",
            conceptName = "Full Stack Integration & Networking",
            title = "CORS Preflight (OPTIONS) & Backend Configuration",
            question = "What is a CORS preflight request (HTTP OPTIONS), what triggers it, and how must the backend respond?",
            shortAnswer = "A CORS preflight request is an automatic HTTP OPTIONS request sent by the browser before the actual request to determine if the cross-origin server permits the call. It is triggered whenever a request is 'not simple': using HTTP methods other than GET/HEAD/POST, using custom headers (e.g. Authorization, X-Requested-With), or using Content-Type other than text/plain, multipart/form-data, or application/x-www-form-urlencoded (such as application/json). The backend must respond with HTTP 200/204 and headers Access-Control-Allow-Origin, Access-Control-Allow-Methods, and Access-Control-Allow-Headers.",
            keyPoints = listOf(
                "Browser security mechanism enforcing the Same-Origin Policy (SOP)",
                "Preflight OPTIONS precedes non-simple requests (JSON body, custom headers, PUT/DELETE)",
                "Backend returns Access-Control-Allow-Origin, Methods, and Headers",
                "Access-Control-Max-Age allows caching the preflight response to avoid redundant roundtrips",
                "Backend proxies (API Gateway / Reverse Proxy) can handle CORS centrally"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_fs_004",
            trackId = "full_stack_interview",
            conceptId = "fs_api_integration",
            conceptName = "Full Stack Integration & Networking",
            title = "Backend-for-Frontend (BFF) Pattern",
            question = "What problem does the Backend-for-Frontend (BFF) pattern solve for web and mobile clients in a microservice ecosystem?",
            shortAnswer = "In microservice architectures, different client platforms (Desktop Web vs Mobile App) have contrasting display requirements, network bandwidth constraints, and payload sizes. A generic one-size-fits-all API forces clients to make multiple roundtrips to orchestrate data or receives bloated payloads with unneeded fields. The BFF pattern creates dedicated lightweight backend translation layers for each client type (e.g. Web-BFF, Mobile-BFF). The BFF aggregates downstream microservices, trims payloads, formats dates, and optimizes caching tailored specifically to that client's user experience.",
            keyPoints = listOf(
                "Tailors API endpoints and payloads to specific client device needs (Web vs Mobile)",
                "Aggregates multiple downstream microservice calls into a single client request",
                "Shields frontend from backend domain schema changes and refactorings",
                "Reduces mobile battery and mobile data consumption through payload trimming",
                "Can manage client-specific authentication session tokens and cookies"
            ),
            difficulty = "Senior"
        )
    )
}
