package com.example.data.local.interview

import com.example.domain.model.VideoMockInterview
import com.example.domain.model.VideoMockTopic

object VideoMockCatalog {

    private val topicsList: List<VideoMockTopic> = listOf(
        VideoMockTopic(
            id = "java",
            name = "Java",
            icon = "☕",
            videoCount = 10,
            relatedTrackId = "java_interview"
        ),
        VideoMockTopic(
            id = "spring_boot",
            name = "Spring Boot",
            icon = "🍃",
            videoCount = 10,
            relatedTrackId = "spring_boot_interview"
        ),
        VideoMockTopic(
            id = "microservices",
            name = "Microservices",
            icon = "🌐",
            videoCount = 10,
            relatedTrackId = "microservices_interview"
        ),
        VideoMockTopic(
            id = "full_stack",
            name = "Full Stack",
            icon = "⚡",
            videoCount = 10,
            relatedTrackId = "full_stack_interview"
        ),
        VideoMockTopic(
            id = "hld",
            name = "HLD",
            icon = "🏛️",
            videoCount = 10,
            relatedTrackId = "hld_interview"
        ),
        VideoMockTopic(
            id = "lld",
            name = "LLD",
            icon = "🧩",
            videoCount = 10,
            relatedTrackId = "lld_interview"
        ),
        VideoMockTopic(
            id = "system_design",
            name = "System Design",
            icon = "📐",
            videoCount = 10,
            relatedTrackId = "system_design_interview"
        ),
        VideoMockTopic(
            id = "devops",
            name = "DevOps",
            icon = "♾️",
            videoCount = 10,
            relatedTrackId = "devops_interview"
        ),
        VideoMockTopic(
            id = "sql",
            name = "SQL & Database",
            icon = "🗄️",
            videoCount = 10,
            relatedTrackId = "sql_interview"
        ),
        VideoMockTopic(
            id = "angular",
            name = "Angular",
            icon = "🅰️",
            videoCount = 10,
            relatedTrackId = "angular_interview"
        ),
        VideoMockTopic(
            id = "security",
            name = "Security",
            icon = "🛡️",
            videoCount = 10,
            relatedTrackId = "security_interview"
        )
    )

    // --- 1. Java Mock Interviews (10 videos) ---
    private val javaVideos = listOf(
        VideoMockInterview(
            id = "vm_java_01",
            topicId = "java",
            title = "Senior Java Developer Mock Interview: JVM Internals & Concurrency",
            channelName = "Exponent",
            youtubeVideoId = "30eW33XW_oI",
            duration = "46 min",
            difficulty = "Senior",
            description = "A comprehensive FAANG-level mock interview exploring JVM memory model, young vs old generation garbage collectors (G1, ZGC), volatile semantics, and race conditions.",
            keyTakeaways = listOf(
                "Explaining JVM heap partitions (Eden, Survivor, Tenured, Metaspace) concisely",
                "How happens-before relationship guarantees cache coherence across CPU cores",
                "Comparing ConcurrentHashMap lock-striping vs synchronized maps"
            ),
            relatedTrackId = "java_interview"
        ),
        VideoMockInterview(
            id = "vm_java_02",
            topicId = "java",
            title = "Java 8-21 Functional Streams & Virtual Threads Mock Interview",
            channelName = "Concept & Coding",
            youtubeVideoId = "3PZ65mu_f4E",
            duration = "52 min",
            difficulty = "Mid-Level",
            description = "Evaluates intermediate operations vs terminal operations, parallelStream thread pool pitfalls, and how Project Loom Virtual Threads revolutionize I/O concurrency.",
            keyTakeaways = listOf(
                "Why ForkJoinPool.commonPool() can be starved by blocking stream operations",
                "Memory overhead comparison: 1MB platform thread stack vs ~1KB virtual thread carrier",
                "Safe use of Collectors.groupingBy and custom collectors"
            ),
            relatedTrackId = "java_interview"
        ),
        VideoMockInterview(
            id = "vm_java_03",
            topicId = "java",
            title = "Java Multithreading & Lock-Free Data Structures Interview",
            channelName = "Defog Tech",
            youtubeVideoId = "d9S_jO160q0",
            duration = "38 min",
            difficulty = "Senior",
            description = "Candidate is questioned on CAS (Compare-And-Swap), AtomicInteger, ReentrantLock condition variables, and deadlock diagnosis using jstack thread dumps.",
            keyTakeaways = listOf(
                "How CAS avoids kernel context-switching overhead compared to synchronized",
                "Preventing deadlocks with strict resource acquisition ordering",
                "ReentrantLock fair vs unfair scheduling trade-offs"
            ),
            relatedTrackId = "java_interview"
        ),
        VideoMockInterview(
            id = "vm_java_04",
            topicId = "java",
            title = "Core Java Collections Framework In-Depth Mock Interview",
            channelName = "Amigoscode",
            youtubeVideoId = "xk4_1vDrzzo",
            duration = "42 min",
            difficulty = "Mid-Level",
            description = "Deep dive into HashMap collision resolution (Linked list to Red-Black tree at threshold 8), equals() and hashCode() contract, and ArrayList resizing mechanics.",
            keyTakeaways = listOf(
                "Why load factor 0.75 balances space and lookup time complexity",
                "Treeifying bins with TreeNode when bucket size exceeds TREEIFY_THRESHOLD (8)",
                "How mutating an object after inserting into a HashSet leads to memory leaks"
            ),
            relatedTrackId = "java_interview"
        ),
        VideoMockInterview(
            id = "vm_java_05",
            topicId = "java",
            title = "Java Garbage Collection Tuning & Memory Leak Diagnosis",
            channelName = "Tech Primers",
            youtubeVideoId = "GuF759yqW00",
            duration = "35 min",
            difficulty = "Staff",
            description = "Scenario-based interview addressing OutOfMemoryError (Heap space vs Metaspace), heap dump analysis with Eclipse MAT, and ZGC sub-millisecond pauses.",
            keyTakeaways = listOf(
                "Identifying GC roots (local stack frames, static fields, JNI references)",
                "Diagnosing unclosed ThreadLocal variables leading to thread-pool memory leaks",
                "Color pointers and load barriers enabling concurrent ZGC phases"
            ),
            relatedTrackId = "java_interview"
        ),
        VideoMockInterview(
            id = "vm_java_06",
            topicId = "java",
            title = "Java Design Patterns & Clean Code Mock Interview",
            channelName = "Dan Vega",
            youtubeVideoId = "YZZ_nL1GfCY",
            duration = "48 min",
            difficulty = "Mid-Level",
            description = "Candidate demonstrates practical implementations of Singleton (double-checked locking with volatile), Builder, Factory, and Strategy patterns in idiomatic Java.",
            keyTakeaways = listOf(
                "Why volatile is strictly required in lazy double-checked locking singletons",
                "Using functional interfaces to replace heavyweight Strategy pattern classes",
                "Immutable domain models using Java 16+ Records"
            ),
            relatedTrackId = "java_interview"
        ),
        VideoMockInterview(
            id = "vm_java_07",
            topicId = "java",
            title = "Java Generics, Type Erasure & Wildcards Technical Interview",
            channelName = "FreeCodeCamp",
            youtubeVideoId = "K1iu1kXkVoA",
            duration = "40 min",
            difficulty = "Mid-to-Senior",
            description = "Probing candidate understanding of PECS (Producer Extends, Consumer Super), invariance of arrays vs generics, and bridge methods generated by javac.",
            keyTakeaways = listOf(
                "Applying PECS principle when designing generic API collections",
                "Understanding why List<Object> is not a supertype of List<String>",
                "Runtime type erasure implications when using reflections"
            ),
            relatedTrackId = "java_interview"
        ),
        VideoMockInterview(
            id = "vm_java_08",
            topicId = "java",
            title = "Modern Java (17 to 21) Language Features Live Coding Mock",
            channelName = "Marco Codes",
            youtubeVideoId = "36jZ3n_w-aQ",
            duration = "33 min",
            difficulty = "Mid-Level",
            description = "Focuses on Pattern Matching for switch, Sealed Classes & Interfaces, Text Blocks, Records, and Sequenced Collections introduced in LTS releases.",
            keyTakeaways = listOf(
                "Exhaustiveness checking with sealed hierarchies and pattern matching",
                "Using records for data transfer objects (DTOs) with automatic equals and hashCode",
                "Virtual threads structured concurrency with StructuredTaskScope"
            ),
            relatedTrackId = "java_interview"
        ),
        VideoMockInterview(
            id = "vm_java_09",
            topicId = "java",
            title = "Java Exception Handling Best Practices & Performance Impact",
            channelName = "Continuous Delivery",
            youtubeVideoId = "Wl4i8f9XJ1Q",
            duration = "39 min",
            difficulty = "Senior",
            description = "Evaluates checked vs unchecked exceptions, cost of stack trace capture (Throwable.fillInStackTrace), and try-with-resources AutoCloseable suppressed exceptions.",
            keyTakeaways = listOf(
                "Why exception instantiation is CPU expensive due to native stack trace walking",
                "Handling suppressed exceptions in nested try-with-resources blocks",
                "Designing domain-specific exception hierarchies with informative diagnostic metadata"
            ),
            relatedTrackId = "java_interview"
        ),
        VideoMockInterview(
            id = "vm_java_10",
            topicId = "java",
            title = "Java High Throughput Microbenchmarking with JMH",
            channelName = "InfoQ",
            youtubeVideoId = "va4_C73V2vM",
            duration = "45 min",
            difficulty = "Staff",
            description = "Deep architectural exploration into JIT compiler optimizations (inlining, escape analysis, loop unrolling) and avoiding dead code elimination in benchmarks.",
            keyTakeaways = listOf(
                "Writing reliable microbenchmarks using Blackhole to prevent dead code removal",
                "How escape analysis converts heap allocations into scalar stack variables",
                "Deoptimization traps and tier-4 C2 JIT compilation thresholds"
            ),
            relatedTrackId = "java_interview"
        )
    )

    // --- 2. Spring Boot Mock Interviews (10 videos) ---
    private val springBootVideos = listOf(
        VideoMockInterview(
            id = "vm_spring_01",
            topicId = "spring_boot",
            title = "Spring Boot Senior Developer Mock Interview: IoC & Bean Lifecycle",
            channelName = "Dan Vega",
            youtubeVideoId = "9SGDpanrc8U",
            duration = "50 min",
            difficulty = "Senior",
            description = "Investigates ApplicationContext startup, BeanPostProcessor vs BeanFactoryPostProcessor, circular dependency resolution, and @Lazy proxies.",
            keyTakeaways = listOf(
                "Step-by-step Bean creation lifecycle from instantiation to PostConstruct to ready",
                "How Spring's 3-level singleton cache resolves circular dependencies with early proxies",
                "Differences between @Component, @Service, @Repository and custom stereotypes"
            ),
            relatedTrackId = "spring_boot_interview"
        ),
        VideoMockInterview(
            id = "vm_spring_02",
            topicId = "spring_boot",
            title = "Spring Boot Transaction Management (@Transactional) & Propagation",
            channelName = "Concept & Coding",
            youtubeVideoId = "z2kLzT7o97k",
            duration = "44 min",
            difficulty = "Mid-to-Senior",
            description = "Explains proxy mechanics, self-invocation bypass problem, rollbackFor rules (RuntimeException vs Checked), and REQUIRED vs REQUIRES_NEW propagation.",
            keyTakeaways = listOf(
                "Why calling a @Transactional method from inside the same class bypasses the proxy",
                "Isolation levels and how phantom reads/dirty reads are managed with underlying RDBMS",
                "When to use Propagation.REQUIRES_NEW for isolated audit logs"
            ),
            relatedTrackId = "spring_boot_interview"
        ),
        VideoMockInterview(
            id = "vm_spring_03",
            topicId = "spring_boot",
            title = "Spring Security 6 Architecture & JWT Filter Chain Mock Interview",
            channelName = "Amigoscode",
            youtubeVideoId = "b9O9830BBC8",
            duration = "55 min",
            difficulty = "Senior",
            description = "Comprehensive walkthrough of SecurityFilterChain, DelegatingFilterProxy, SecurityContextHolder thread-local propagation, and stateless JWT authentication.",
            keyTakeaways = listOf(
                "Building stateless authentication chains without deprecated WebSecurityConfigurerAdapter",
                "Properly handling expired JWT tokens in custom OncePerRequestFilter",
                "Role-based access control with @PreAuthorize and MethodSecurity"
            ),
            relatedTrackId = "spring_boot_interview"
        ),
        VideoMockInterview(
            id = "vm_spring_04",
            topicId = "spring_boot",
            title = "Spring Data JPA & Hibernate N+1 Query Problem Live Debugging",
            channelName = "Java Brains",
            youtubeVideoId = "cRff_M_vW5E",
            duration = "42 min",
            difficulty = "Mid-Level",
            description = "Candidate diagnoses silent N+1 queries using Hibernate SQL logging and fixes them using JOIN FETCH, @EntityGraph, and DTO Projections.",
            keyTakeaways = listOf(
                "Why FetchType.LAZY triggers individual queries inside a collection loop",
                "Comparing JOIN FETCH with @EntityGraph attribute paths",
                "Achieving maximum read performance with Spring Data interface/record projections"
            ),
            relatedTrackId = "spring_boot_interview"
        ),
        VideoMockInterview(
            id = "vm_spring_05",
            topicId = "spring_boot",
            title = "Spring Boot Auto-Configuration Internals & Custom Starters",
            channelName = "Tech Primers",
            youtubeVideoId = "GB8wZz6L_z0",
            duration = "36 min",
            difficulty = "Staff",
            description = "Explains spring.factories / AutoConfiguration.imports, @ConditionalOnClass, @ConditionalOnMissingBean, and constructing reusable enterprise starters.",
            keyTakeaways = listOf(
                "How Spring Boot resolves conditions during ApplicationContext initialization",
                "Building custom @ConfigurationProperties with validation (@Validated)",
                "Designing library starters that gracefully yield to user-defined beans"
            ),
            relatedTrackId = "spring_boot_interview"
        ),
        VideoMockInterview(
            id = "vm_spring_06",
            topicId = "spring_boot",
            title = "Spring Boot REST API Design, Validation & Global Exception Handling",
            channelName = "Daily Code Buffer",
            youtubeVideoId = "31KTdfRH6nY",
            duration = "40 min",
            difficulty = "Mid-Level",
            description = "Mock interview on RFC 7807 ProblemDetails, Jakarta Bean Validation (@NotNull, @Pattern), and centralized handling with @RestControllerAdvice.",
            keyTakeaways = listOf(
                "Structuring standard RFC 7807 error responses for enterprise clients",
                "BindingResult inspection and customizing MethodArgumentNotValidException",
                "Idempotency in REST APIs using client-supplied request tokens"
            ),
            relatedTrackId = "spring_boot_interview"
        ),
        VideoMockInterview(
            id = "vm_spring_07",
            topicId = "spring_boot",
            title = "Spring Boot Caching with Redis & Cache Eviction Strategies",
            channelName = "Concept & Coding",
            youtubeVideoId = "d0s3W9pXf88",
            duration = "38 min",
            difficulty = "Senior",
            description = "Evaluates @Cacheable, @CachePut, @CacheEvict, distributed Redis serialization with Jackson, and preventing cache stampede / dogpiling.",
            keyTakeaways = listOf(
                "Configuring RedisCacheManager with customized TTLs per cache name",
                "Cache key generation with SpEL (Spring Expression Language)",
                "Using mutex locks or probabilistic early expiration to avoid cache stampede"
            ),
            relatedTrackId = "spring_boot_interview"
        ),
        VideoMockInterview(
            id = "vm_spring_08",
            topicId = "spring_boot",
            title = "Spring Boot Reactive Programming with Spring WebFlux",
            channelName = "Defog Tech",
            youtubeVideoId = "03wH2tZp7z0",
            duration = "45 min",
            difficulty = "Staff",
            description = "Deep dive into Project Reactor Mono vs Flux, Netty event-loop non-blocking I/O, backpressure handling, and reactive R2DBC database access.",
            keyTakeaways = listOf(
                "When to use WebFlux vs Spring MVC with Project Loom Virtual Threads",
                "Handling backpressure strategies (Buffer, Drop, Latest) in reactive streams",
                "Diagnosing blocking calls in reactive threads using BlockHound"
            ),
            relatedTrackId = "spring_boot_interview"
        ),
        VideoMockInterview(
            id = "vm_spring_09",
            topicId = "spring_boot",
            title = "Spring Boot Actuator, Micrometer & Prometheus Observability",
            channelName = "TechWorld with Nana",
            youtubeVideoId = "hA4h_H_7lG4",
            duration = "34 min",
            difficulty = "Mid-to-Senior",
            description = "Candidate discusses health indicators, liveness/readiness probes in Kubernetes, custom Micrometer counters/timers, and Prometheus scraping endpoints.",
            keyTakeaways = listOf(
                "Configuring management.endpoints.web.exposure.include safely",
                "Implementing custom HealthIndicator for critical third-party dependencies",
                "Creating high-cardinality-safe Micrometer timers for SLA monitoring"
            ),
            relatedTrackId = "spring_boot_interview"
        ),
        VideoMockInterview(
            id = "vm_spring_10",
            topicId = "spring_boot",
            title = "Spring Boot Testing Strategies: @SpringBootTest vs Slice Tests",
            channelName = "Amigoscode",
            youtubeVideoId = "Geq68OVyQRk",
            duration = "47 min",
            difficulty = "Senior",
            description = "Evaluates test isolation, @WebMvcTest, @DataJpaTest, Testcontainers for real PostgreSQL and Kafka testing, and preventing dirty context reloads.",
            keyTakeaways = listOf(
                "Why @MockBean causes Spring Test Context cache invalidation and slow builds",
                "Using Testcontainers to eliminate H2 vs PostgreSQL dialect discrepancy bugs",
                "Slice testing controller contracts with MockMvc and JsonPath"
            ),
            relatedTrackId = "spring_boot_interview"
        )
    )

    // --- 3. Microservices Mock Interviews (10 videos) ---
    private val microservicesVideos = listOf(
        VideoMockInterview(
            id = "vm_micro_01",
            topicId = "microservices",
            title = "Senior Microservices Architecture Mock Interview: Distributed Patterns",
            channelName = "Exponent",
            youtubeVideoId = "rv4LlELmVVE",
            duration = "56 min",
            difficulty = "Senior",
            description = "Deep exploration into decomposing monoliths, bounded contexts, API Gateway patterns, database per service, and distributed transaction strategies.",
            keyTakeaways = listOf(
                "Defining bounded contexts using Domain-Driven Design (DDD) aggregates",
                "Managing cross-service transactions without two-phase commit (2PC)",
                "API Gateway routing, rate limiting, and SSL termination"
            ),
            relatedTrackId = "microservices_interview"
        ),
        VideoMockInterview(
            id = "vm_micro_02",
            topicId = "microservices",
            title = "The Saga Pattern: Choreography vs Orchestration Deep Dive",
            channelName = "ByteByteGo",
            youtubeVideoId = "0UTOLR-kCtc",
            duration = "41 min",
            difficulty = "Senior",
            description = "Compares event-choreographed Sagas vs centralized orchestrator engines (Temporal / Camunda), compensating transactions, and pivot steps.",
            keyTakeaways = listOf(
                "Designing reliable compensating transactions for reversible actions",
                "Why orchestration prevents cyclic dependency spaghetti in complex order workflows",
                "Handling duplicate events and network retries with idempotency keys"
            ),
            relatedTrackId = "microservices_interview"
        ),
        VideoMockInterview(
            id = "vm_micro_03",
            topicId = "microservices",
            title = "Event-Driven Microservices with Apache Kafka Mock Interview",
            channelName = "Concept & Coding",
            youtubeVideoId = "R873BlNVUB4",
            duration = "49 min",
            difficulty = "Senior",
            description = "Evaluates Kafka topic partitioning, consumer group rebalancing, offset commit semantics (at-least-once vs exactly-once), and transactional outbox.",
            keyTakeaways = listOf(
                "How partition keys guarantee in-order message processing per customer",
                "Solving dual-write inconsistency with the Transactional Outbox pattern & Debezium",
                "Dead letter topics (DLT) for unprocessable poison-pill payloads"
            ),
            relatedTrackId = "microservices_interview"
        ),
        VideoMockInterview(
            id = "vm_micro_04",
            topicId = "microservices",
            title = "Fault Tolerance: Circuit Breaker & Rate Limiting with Resilience4j",
            channelName = "Defog Tech",
            youtubeVideoId = "aE5d487wEcA",
            duration = "37 min",
            difficulty = "Mid-to-Senior",
            description = "Interviewer challenges candidate on sliding window error rate thresholds, half-open state recovery, fallback mechanisms, and bulkheading.",
            keyTakeaways = listOf(
                "Configuring count-based vs time-based sliding windows in Resilience4j",
                "Isolating thread pools with Bulkhead to prevent cascading downstream outages",
                "Safe fallback degradations during payment gateway downtime"
            ),
            relatedTrackId = "microservices_interview"
        ),
        VideoMockInterview(
            id = "vm_micro_05",
            topicId = "microservices",
            title = "Distributed Tracing with OpenTelemetry, Jaeger & W3C Trace Context",
            channelName = "TechWorld with Nana",
            youtubeVideoId = "c3p57KfZg3E",
            duration = "43 min",
            difficulty = "Senior",
            description = "Candidate traces an end-to-end request across 5 microservices using traceId, spanId, baggage propagation, and sampling strategies.",
            keyTakeaways = listOf(
                "Propagating W3C traceparent headers across HTTP and Kafka message headers",
                "Head-based vs tail-based trace sampling in high-scale production clusters",
                "Correlating structured logs with trace IDs for instant root-cause analysis"
            ),
            relatedTrackId = "microservices_interview"
        ),
        VideoMockInterview(
            id = "vm_micro_06",
            topicId = "microservices",
            title = "Service Discovery & Dynamic Routing: Eureka vs Kubernetes DNS",
            channelName = "Java Brains",
            youtubeVideoId = "3c_S_Vj9w78",
            duration = "35 min",
            difficulty = "Mid-Level",
            description = "Compares client-side load balancing (Spring Cloud LoadBalancer) with server-side cloud native routing (Kubernetes CoreDNS & Envoy ingress).",
            keyTakeaways = listOf(
                "Heartbeats, self-preservation mode, and lease renewals in Eureka",
                "Why container orchestration shifted service discovery into the network layer",
                "Client-side round-robin and weighted response time algorithms"
            ),
            relatedTrackId = "microservices_interview"
        ),
        VideoMockInterview(
            id = "vm_micro_07",
            topicId = "microservices",
            title = "CQRS & Event Sourcing in Distributed Systems Mock Interview",
            channelName = "Derek Comartin",
            youtubeVideoId = "cq8_m8_7hP0",
            duration = "48 min",
            difficulty = "Staff",
            description = "Deep dive into separating read and write models, event streams as single source of truth, projections, and eventual consistency lag.",
            keyTakeaways = listOf(
                "When CQRS is justified versus when it adds unnecessary accidental complexity",
                "Rebuilding read store projections from historic immutable event logs",
                "Managing schema evolution and upcasting in long-lived event stores"
            ),
            relatedTrackId = "microservices_interview"
        ),
        VideoMockInterview(
            id = "vm_micro_08",
            topicId = "microservices",
            title = "gRPC vs REST in Microservices Communication Mock Interview",
            channelName = "Hussein Nasser",
            youtubeVideoId = "gnchfOoj9Ec",
            duration = "40 min",
            difficulty = "Mid-to-Senior",
            description = "Explores HTTP/2 multiplexing, Protocol Buffers binary serialization efficiency, streaming RPCs, and browser compatibility limits.",
            keyTakeaways = listOf(
                "Binary proto framing vs text-based JSON serialization CPU benchmarks",
                "Bidirectional streaming for real-time backchannel telemetry",
                "Using gRPC-Gateway for exposing public HTTP/JSON endpoints"
            ),
            relatedTrackId = "microservices_interview"
        ),
        VideoMockInterview(
            id = "vm_micro_09",
            topicId = "microservices",
            title = "Microservices Data Consistency & Distributed Locks (Redlock)",
            channelName = "ByteByteGo",
            youtubeVideoId = "dxbQ2d7fAfg",
            duration = "36 min",
            difficulty = "Staff",
            description = "Analyzes distributed locking algorithms using Redis Redlock, clock drift risks (Martin Kleppmann critique), and fencing tokens.",
            keyTakeaways = listOf(
                "Why distributed locks require monotonic fencing tokens on storage backends",
                "Failure modes when GC pause exceeds the lock lease duration",
                "Alternative optimistic concurrency control with database version columns"
            ),
            relatedTrackId = "microservices_interview"
        ),
        VideoMockInterview(
            id = "vm_micro_10",
            topicId = "microservices",
            title = "Zero-Downtime Microservices Deployment: Blue-Green & Canary",
            channelName = "Continuous Delivery",
            youtubeVideoId = "gq41bZ-q78U",
            duration = "44 min",
            difficulty = "Senior",
            description = "Step-by-step evaluation of backward-compatible schema changes (Expand-Contract pattern), traffic shifting with Argo Rollouts, and automated rollbacks.",
            keyTakeaways = listOf(
                "Applying the Expand-Contract database pattern across rolling service versions",
                "Gradual traffic shifting based on error rate metrics and latency P99 thresholds",
                "Managing stateless pod drain intervals during Kubernetes deployment updates"
            ),
            relatedTrackId = "microservices_interview"
        )
    )

    // --- 4. Full Stack Mock Interviews (10 videos) ---
    private val fullStackVideos = listOf(
        VideoMockInterview(
            id = "vm_fs_01",
            topicId = "full_stack",
            title = "Full Stack Engineer Mock Interview: Angular Frontend to Spring Boot Backend",
            channelName = "Exponent",
            youtubeVideoId = "e5vC-c1bX_g",
            duration = "58 min",
            difficulty = "Senior",
            description = "End-to-end technical loop covering Angular state management, REST API contract design, Spring Boot security filter chain, and PostgreSQL indexing.",
            keyTakeaways = listOf(
                "Designing cohesive TypeScript interfaces that match backend DTO contracts",
                "Handling CORS preflight OPTIONS requests securely in Spring Security",
                "Optimizing page load with route-level code splitting and backend pagination"
            ),
            relatedTrackId = "full_stack_interview"
        ),
        VideoMockInterview(
            id = "vm_fs_02",
            topicId = "full_stack",
            title = "Full Stack System Architecture: Real-Time Collaborative Workspace",
            channelName = "Concept & Coding",
            youtubeVideoId = "Z8_4X8t20w8",
            duration = "52 min",
            difficulty = "Senior",
            description = "Candidate designs a multi-user collaborative board: WebSockets with STOMP in Spring Boot, RxJS streams in frontend, and Redis Pub/Sub for scale.",
            keyTakeaways = listOf(
                "Heartbeat and reconnection resilience in STOMP over WebSocket connections",
                "Debouncing user keystrokes in frontend before broadcasting changes",
                "Scaling WebSocket servers across multiple pods using Redis broker relays"
            ),
            relatedTrackId = "full_stack_interview"
        ),
        VideoMockInterview(
            id = "vm_fs_03",
            topicId = "full_stack",
            title = "Full Stack Authentication: HttpOnly Cookies, CSRF & OAuth2",
            channelName = "Amigoscode",
            youtubeVideoId = "x_W0a3_k8XQ",
            duration = "46 min",
            difficulty = "Mid-to-Senior",
            description = "Probing candidate on why storing JWT in localStorage is vulnerable to XSS, implementing HttpOnly SameSite cookies, and Double Submit CSRF tokens.",
            keyTakeaways = listOf(
                "Protecting tokens from malicious scripts with HttpOnly and Secure flags",
                "Configuring Spring Security CsrfTokenRepository for single-page apps",
                "Implementing silent token refresh using HTTP interceptors"
            ),
            relatedTrackId = "full_stack_interview"
        ),
        VideoMockInterview(
            id = "vm_fs_04",
            topicId = "full_stack",
            title = "Full Stack Performance: Web Vitals & Backend Query Tuning",
            channelName = "FreeCodeCamp",
            youtubeVideoId = "0aY_q478yq0",
            duration = "49 min",
            difficulty = "Senior",
            description = "Focuses on Largest Contentful Paint (LCP), Cumulative Layout Shift (CLS), server-side rendering (SSR), and optimizing database execution plans.",
            keyTakeaways = listOf(
                "Identifying rendering bottlenecks and deferring non-critical scripts",
                "Using composite B-Tree indexes on backend SQL tables to satisfy filtering queries",
                "Applying gzip/Brotli compression and CDN caching headers for static assets"
            ),
            relatedTrackId = "full_stack_interview"
        ),
        VideoMockInterview(
            id = "vm_fs_05",
            topicId = "full_stack",
            title = "Full Stack Live Coding: Infinite Scroll & Virtualized List",
            channelName = "Dan Vega",
            youtubeVideoId = "7m-5P0X8A3U",
            duration = "43 min",
            difficulty = "Mid-Level",
            description = "Candidate builds an infinite scrolling feed with cursor-based backend pagination (WHERE id < last_seen_id) instead of slow OFFSET queries.",
            keyTakeaways = listOf(
                "Why OFFSET pagination degrades to O(N) performance on large database tables",
                "Implementing CDK Virtual Scroll to keep DOM node count minimal in frontend",
                "Handling race conditions when user scrolls rapidly"
            ),
            relatedTrackId = "full_stack_interview"
        ),
        VideoMockInterview(
            id = "vm_fs_06",
            topicId = "full_stack",
            title = "File Upload Pipeline: S3 Presigned URLs & Progress Tracking",
            channelName = "Hussein Nasser",
            youtubeVideoId = "5p_3M1t9x8Q",
            duration = "39 min",
            difficulty = "Senior",
            description = "Candidate designs direct browser-to-S3 multi-part file uploads using short-lived backend presigned URLs to relieve backend server memory.",
            keyTakeaways = listOf(
                "Bypassing application server I/O bottleneck with S3 direct presigned uploads",
                "Tracking upload progress via XMLHttpRequest/Fetch progress events",
                "Validating file MIME types and scanning for malware using asynchronous Lambdas"
            ),
            relatedTrackId = "full_stack_interview"
        ),
        VideoMockInterview(
            id = "vm_fs_07",
            topicId = "full_stack",
            title = "Full Stack Error Handling & Resilient Network Interceptors",
            channelName = "Daily Code Buffer",
            youtubeVideoId = "m_C6zX7d-0Q",
            duration = "37 min",
            difficulty = "Mid-Level",
            description = "Examines building HTTP interceptors for automatic exponential backoff retry on 503/504 errors, unified snackbar error alerts, and 401 redirection.",
            keyTakeaways = listOf(
                "Implementing retryWithBackoff RxJS operators in frontend HTTP clients",
                "Mapping backend ProblemDetails JSON into localized UI error banners",
                "Clearing stale state and redirecting to login on authentication expiration"
            ),
            relatedTrackId = "full_stack_interview"
        ),
        VideoMockInterview(
            id = "vm_fs_08",
            topicId = "full_stack",
            title = "Micro-Frontend vs Monolithic SPA Architecture Interview",
            channelName = "ByteByteGo",
            youtubeVideoId = "6Y_d20y00-4",
            duration = "45 min",
            difficulty = "Staff",
            description = "Evaluates Module Federation, iframe embedding, shared state coordination across teams, CSS isolation, and independent deployability.",
            keyTakeaways = listOf(
                "Webpack Module Federation configuration for runtime remote container loading",
                "Shared dependency version alignment (preventing double loading Angular/React)",
                "Cross-microfrontend communication using CustomEvents or reactive pub/sub bus"
            ),
            relatedTrackId = "full_stack_interview"
        ),
        VideoMockInterview(
            id = "vm_fs_09",
            topicId = "full_stack",
            title = "Full Stack Testing: End-to-End Cypress/Playwright + Testcontainers",
            channelName = "Continuous Delivery",
            youtubeVideoId = "x3g45yZ67w8",
            duration = "41 min",
            difficulty = "Mid-to-Senior",
            description = "Candidate designs a test automation pyramid: unit tests with Vitest/JUnit 5, integration tests with Testcontainers, and smoke tests with Playwright.",
            keyTakeaways = listOf(
                "Writing deterministic E2E tests with Playwright web-first assertions",
                "Spinning up ephemeral PostgreSQL instances in CI/CD pipeline",
                "Mocking third-party payment gateways with WireMock"
            ),
            relatedTrackId = "full_stack_interview"
        ),
        VideoMockInterview(
            id = "vm_fs_10",
            topicId = "full_stack",
            title = "Full Stack Internationalization (i18n), Accessibility & Dark Mode",
            channelName = "Kevin Powell",
            youtubeVideoId = "bV0P1v_LqQ0",
            duration = "36 min",
            difficulty = "Mid-Level",
            description = "Evaluates WCAG 2.1 AA compliance (ARIA landmarks, focus management, color contrast), RTL language layout flipping, and system dark mode sync.",
            keyTakeaways = listOf(
                "Using CSS variables and prefers-color-scheme for flicker-free theme switching",
                "ARIA attributes (aria-live, aria-expanded) for assistive screen readers",
                "Managing localized bundle extraction without duplicating application logic"
            ),
            relatedTrackId = "full_stack_interview"
        )
    )

    // --- 5. HLD (High-Level Design) Mock Interviews (10 videos) ---
    private val hldVideos = listOf(
        VideoMockInterview(
            id = "vm_hld_01",
            topicId = "hld",
            title = "Design YouTube: High Level System Design Mock Interview",
            channelName = "ByteByteGo",
            youtubeVideoId = "jKCy4X96VwU",
            duration = "52 min",
            difficulty = "Staff",
            description = "Comprehensive architectural loop on video ingestion pipeline, DAG transcoding into multiple bitrates (DASH/HLS), CDN caching, and metadata storage.",
            keyTakeaways = listOf(
                "Transcoding pipeline architecture using task queues and blob storage",
                "Global CDN edge caching strategies for hot vs long-tail video catalogs",
                "Handling video upload resume with chunked multipart uploads"
            ),
            relatedTrackId = "hld_interview"
        ),
        VideoMockInterview(
            id = "vm_hld_02",
            topicId = "hld",
            title = "Design Uber / Ride Sharing: Staff Engineer Mock Interview",
            channelName = "Exponent",
            youtubeVideoId = "lsK7z6T9b2k",
            duration = "59 min",
            difficulty = "Staff",
            description = "Candidate architects real-time geospatial location tracking: Google S2 / Uber H3 hexagonal spatial indexing, driver matching, and surge pricing engines.",
            keyTakeaways = listOf(
                "Why traditional R-Trees struggle with 1M+ driver updates per second",
                "Using Uber H3 hierarchical hexagonal spatial index with Redis memory cache",
                "Consistent hashing and partition keys for driver location streaming"
            ),
            relatedTrackId = "hld_interview"
        ),
        VideoMockInterview(
            id = "vm_hld_03",
            topicId = "hld",
            title = "Design WhatsApp / Messenger: Real-Time Chat at Scale",
            channelName = "Gaurav Sen",
            youtubeVideoId = "vvhC64hQZMk",
            duration = "48 min",
            difficulty = "Senior",
            description = "Deep dive into persistent WebSocket gateway servers, session managers, message persistence with Cassandra, end-to-end encryption, and offline queues.",
            keyTakeaways = listOf(
                "Managing 50M concurrent TCP connections with distributed gateway clusters",
                "Wide-column NoSQL schema design (Cassandra) partitioned by conversationId",
                "Double Ratchet protocol intuition for end-to-end encryption"
            ),
            relatedTrackId = "hld_interview"
        ),
        VideoMockInterview(
            id = "vm_hld_04",
            topicId = "hld",
            title = "Design Twitter / X News Feed: Fan-Out on Write vs Read",
            channelName = "ByteByteGo",
            youtubeVideoId = "KmAyPUv97nM",
            duration = "44 min",
            difficulty = "Senior",
            description = "Analyzes fan-out on write (push to follower timelines) vs fan-out on read (pull at fetch time for celebrity accounts like Elon Musk).",
            keyTakeaways = listOf(
                "Hybrid fan-out strategy: push for normal users, pull for high-follower celebrities",
                "In-memory Redis timeline caches holding user's top 800 tweet IDs",
                "Ranking algorithms combining recency, engagement, and user graph affinity"
            ),
            relatedTrackId = "hld_interview"
        ),
        VideoMockInterview(
            id = "vm_hld_05",
            topicId = "hld",
            title = "Design Stripe / Payment Processing System: Double-Entry Ledger",
            channelName = "Jordan has no life",
            youtubeVideoId = "M8_4V6X7b-0",
            duration = "55 min",
            difficulty = "Staff",
            description = "Architects a financial transaction system requiring zero balance discrepancy: double-entry bookkeeping, idempotency keys, and reconciliation jobs.",
            keyTakeaways = listOf(
                "Double-entry bookkeeping principle: every credit must match an equal debit",
                "Guaranteeing idempotency at API gateway with distributed locking and UUID keys",
                "Automated end-of-day batch reconciliation to detect external bank discrepancies"
            ),
            relatedTrackId = "hld_interview"
        ),
        VideoMockInterview(
            id = "vm_hld_06",
            topicId = "hld",
            title = "Design Google Drive / Dropbox: Chunking & Deduplication",
            channelName = "Tech Dummies",
            youtubeVideoId = "U0xTu6hlW0Y",
            duration = "46 min",
            difficulty = "Senior",
            description = "Probing candidate on content-defined chunking (Rabin fingerprints), deduplicating identical file blocks across users, and delta syncing file revisions.",
            keyTakeaways = listOf(
                "Content-addressed storage where chunk SHA-256 hash serves as storage key",
                "Deduplicating duplicate uploads to achieve 60%+ cloud storage savings",
                "Metadata sync engine using notification services and long polling"
            ),
            relatedTrackId = "hld_interview"
        ),
        VideoMockInterview(
            id = "vm_hld_07",
            topicId = "hld",
            title = "Design Netflix / Global Video Streaming Infrastructure",
            channelName = "Gaurav Sen",
            youtubeVideoId = "psQzyFfsUGU",
            duration = "47 min",
            difficulty = "Staff",
            description = "Explores Open Connect custom CDN appliances, adaptive bitrate streaming, recommendation engine pipelines, and multi-region failover.",
            keyTakeaways = listOf(
                "Deploying Open Connect Appliances directly inside ISP data centers",
                "Adaptive bitrate streaming (ABR) switching quality dynamically based on network bandwidth",
                "Active-active multi-region replication with Cassandra and Eureka"
            ),
            relatedTrackId = "hld_interview"
        ),
        VideoMockInterview(
            id = "vm_hld_08",
            topicId = "hld",
            title = "Design a Web Crawler: Scalability, Politeness & Deduplication",
            channelName = "ByteByteGo",
            youtubeVideoId = "BKZxZwUgL3Y",
            duration = "42 min",
            difficulty = "Senior",
            description = "Candidate designs a distributed crawler: URL frontier with priority and politeness queues, DNS caching, Bloom filters for visited URLs, and HTML parsing.",
            keyTakeaways = listOf(
                "Politeness queue implementation respecting robots.txt crawl delays per host",
                "Using Bloom filters to check billions of visited URLs with minimal memory",
                "Consistent hashing to distribute URL frontier work across worker nodes"
            ),
            relatedTrackId = "hld_interview"
        ),
        VideoMockInterview(
            id = "vm_hld_09",
            topicId = "hld",
            title = "Design Google Search Typeahead / Autocomplete System",
            channelName = "Concept & Coding",
            youtubeVideoId = "us0qyKmxQc8",
            duration = "39 min",
            difficulty = "Senior",
            description = "Deep dive into Trie data structure optimization, caching top 10 search terms at each Trie node, offline map-reduce frequency calculation, and browser caching.",
            keyTakeaways = listOf(
                "Precomputing and storing top K results in Trie nodes to achieve O(1) query latency",
                "Updating search suggestion frequencies asynchronously using MapReduce / Spark",
                "Using browser localStorage caching to instantly show recent search queries"
            ),
            relatedTrackId = "hld_interview"
        ),
        VideoMockInterview(
            id = "vm_hld_10",
            topicId = "hld",
            title = "Design an LLM Inference Platform (RAG & Multi-Tenant Serving)",
            channelName = "Jordan has no life",
            youtubeVideoId = "C0sW3q7y_g4",
            duration = "53 min",
            difficulty = "Staff",
            description = "Cutting-edge system design interview covering vector database indexing (HNSW), continuous batching in vLLM, KV cache management (PagedAttention), and GPU routing.",
            keyTakeaways = listOf(
                "How PagedAttention solves KV cache memory fragmentation on GPU VRAM",
                "Vector database indexing trade-offs: HNSW recall speed vs memory footprint",
                "Routing user prompts based on context length and model weight affinity"
            ),
            relatedTrackId = "hld_interview"
        )
    )

    // --- 6. LLD (Low-Level Design) Mock Interviews (10 videos) ---
    private val lldVideos = listOf(
        VideoMockInterview(
            id = "vm_lld_01",
            topicId = "lld",
            title = "Design a Parking Lot System: Machine Coding & Object Modeling",
            channelName = "Concept & Coding",
            youtubeVideoId = "tVRyb4HaHgw",
            duration = "54 min",
            difficulty = "Mid-to-Senior",
            description = "Candidate models vehicle types (Car, Bike, Truck), spot allocation strategies (Nearest First, Floor Wise), ticketing, payment calculation, and thread safety.",
            keyTakeaways = listOf(
                "Using Strategy pattern for interchangeable parking spot assignment algorithms",
                "Thread-safe spot reservation with ReentrantReadWriteLock",
                "Applying Open/Closed principle when introducing Electric Vehicle (EV) charging spots"
            ),
            relatedTrackId = "lld_interview"
        ),
        VideoMockInterview(
            id = "vm_lld_02",
            topicId = "lld",
            title = "Design an Elevator System: State & Strategy Design Patterns",
            channelName = "Sahn Lam",
            youtubeVideoId = "siqiJXX5w-4",
            duration = "48 min",
            difficulty = "Senior",
            description = "Models ElevatorCar state transitions (Idle, Moving Up, Moving Down), request dispatching algorithms (SCAN / LOOK elevator algorithm), and emergency overrides.",
            keyTakeaways = listOf(
                "State pattern modeling for Elevator states and valid transition triggers",
                "LOOK disk scheduling algorithm applied to elevator hall call dispatching",
                "Handling concurrency when multiple passengers press buttons simultaneously"
            ),
            relatedTrackId = "lld_interview"
        ),
        VideoMockInterview(
            id = "vm_lld_03",
            topicId = "lld",
            title = "Design an In-Memory Rate Limiter: Token Bucket & Leaky Bucket",
            channelName = "Concept & Coding",
            youtubeVideoId = "m_T90p_wP3E",
            duration = "45 min",
            difficulty = "Senior",
            description = "Live coding demonstration implementing Token Bucket and Sliding Window Log rate limiters in Java with thread safety, atomic references, and unit tests.",
            keyTakeaways = listOf(
                "Lazy refill logic in Token Bucket avoiding background timer thread overhead",
                "Thread safety using AtomicInteger or synchronized refill blocks",
                "Trade-offs between Sliding Window Counter vs Token Bucket memory usage"
            ),
            relatedTrackId = "lld_interview"
        ),
        VideoMockInterview(
            id = "vm_lld_04",
            topicId = "lld",
            title = "Design Splitwise (Expense Sharing Application) Machine Coding",
            channelName = "Concept & Coding",
            youtubeVideoId = "3yW4V_k_Z8s",
            duration = "56 min",
            difficulty = "Senior",
            description = "Candidate designs expense splitting algorithms (Equal, Exact, Percentage), debt simplification graph algorithm (min-cash flow), and transaction history.",
            keyTakeaways = listOf(
                "Modeling Expense classes with Factory and Strategy patterns for split calculation",
                "Simplifying debts using directed graphs and greedy balance settlement",
                "Validating percentage splits to ensure sum exactly equals 100%"
            ),
            relatedTrackId = "lld_interview"
        ),
        VideoMockInterview(
            id = "vm_lld_05",
            topicId = "lld",
            title = "Design a Snake and Ladder Board Game: Clean OOP Principles",
            channelName = "Sahn Lam",
            youtubeVideoId = "b90_t7Xv6YQ",
            duration = "42 min",
            difficulty = "Mid-Level",
            description = "Object-oriented modeling of Board, Cells, Jump objects (Snake/Ladder inheritance), Dice with configurable sides, and multi-player game loop.",
            keyTakeaways = listOf(
                "Abstracting Jump base class with snake (tail < head) and ladder (tail > head) subclasses",
                "Decoupling Dice rolling logic for easy mock testing in unit test suites",
                "Circular queue representing player turn order"
            ),
            relatedTrackId = "lld_interview"
        ),
        VideoMockInterview(
            id = "vm_lld_06",
            topicId = "lld",
            title = "Design Tic-Tac-Toe Game with O(1) Move Validation",
            channelName = "Concept & Coding",
            youtubeVideoId = "p_5_kM8e300",
            duration = "38 min",
            difficulty = "Mid-Level",
            description = "Walkthrough of board modeling, piece types (X, O), and an optimized O(1) algorithm tracking row, column, and diagonal sums instead of O(N) scanning.",
            keyTakeaways = listOf(
                "Checking win condition in O(1) time using row/col counters (+1 for X, -1 for O)",
                "Separating Game Controller from Board domain state",
                "Extensibility for N x N board sizes and M consecutive pieces to win"
            ),
            relatedTrackId = "lld_interview"
        ),
        VideoMockInterview(
            id = "vm_lld_07",
            topicId = "lld",
            title = "Design a Movie Ticket Booking System (BookMyShow / Fandango)",
            channelName = "Concept & Coding",
            youtubeVideoId = "k_3Qp0X7u80",
            duration = "51 min",
            difficulty = "Senior",
            description = "Models Cinema, Screens, Shows, Seat categories, and locking seats for 10 minutes during payment checkout with timeout expiry.",
            keyTakeaways = listOf(
                "Handling concurrent seat booking race conditions with optimistic locking",
                "Implementing a temporary lock manager with scheduled eviction",
                "Designing clean relational and class schemas for Theater -> Screen -> Show -> Seat"
            ),
            relatedTrackId = "lld_interview"
        ),
        VideoMockInterview(
            id = "vm_lld_08",
            topicId = "lld",
            title = "Design a Notification System: Observer & Decorator Patterns",
            channelName = "Sahn Lam",
            youtubeVideoId = "qV_eZ3t0_kU",
            duration = "40 min",
            difficulty = "Mid-to-Senior",
            description = "Implements multi-channel notifications (Email, SMS, Push), priority queues, template rendering, and rate limiting per user.",
            keyTakeaways = listOf(
                "Observer pattern for subscribing services to notification event publishers",
                "Decorator pattern for adding encryption or logging wrappers around sender channels",
                "Decoupling third-party provider integrations (Twilio, SendGrid, FCM) with Adapter pattern"
            ),
            relatedTrackId = "lld_interview"
        ),
        VideoMockInterview(
            id = "vm_lld_09",
            topicId = "lld",
            title = "Design an In-Memory File System (mkdir, ls, cat, touch)",
            channelName = "Tech Dummies",
            youtubeVideoId = "n9_e4P0cW1A",
            duration = "44 min",
            difficulty = "Senior",
            description = "Models hierarchical directories and files using Composite pattern, trie-like path parsing, and permission validation.",
            keyTakeaways = listOf(
                "Composite pattern allowing Directory and File to share common Node interface",
                "Parsing absolute and relative file paths using split delimiter tokens",
                "Implementing read and write locks on directory nodes for thread safety"
            ),
            relatedTrackId = "lld_interview"
        ),
        VideoMockInterview(
            id = "vm_lld_10",
            topicId = "lld",
            title = "Design a Cricbuzz / Live Cricket Score Dashboard",
            channelName = "Concept & Coding",
            youtubeVideoId = "o7w8_A4V0kY",
            duration = "47 min",
            difficulty = "Senior",
            description = "Models live match state, over-by-over ball events, commentary feeds, player statistics calculation, and push notifications to spectators.",
            keyTakeaways = listOf(
                "Observer pattern updating multiple dashboards on every ball bowled event",
                "State pattern handling MatchStatus (Upcoming, InProgress, RainDelay, Completed)",
                "Immutable event records representing ball outcomes (Runs, Wicket, Extra)"
            ),
            relatedTrackId = "lld_interview"
        )
    )

    // --- 7. System Design Core Mock Interviews (10 videos) ---
    private val systemDesignVideos = listOf(
        VideoMockInterview(
            id = "vm_sd_01",
            topicId = "system_design",
            title = "Distributed Systems Fundamentals: CAP Theorem & PACELC",
            channelName = "ByteByteGo",
            youtubeVideoId = "k-Yaq8AHlFA",
            duration = "36 min",
            difficulty = "Senior",
            description = "Interviewer tests candidate on consistency vs availability under network partitions, PACELC latency vs consistency trade-offs, and real-world database classifications.",
            keyTakeaways = listOf(
                "Why network partitions (P) are unavoidable in distributed physical networks",
                "PACELC formulation: If partition (P), trade Consistency (C) vs Availability (A); Else (E), trade Latency (L) vs Consistency (C)",
                "How Spanner achieves external consistency using GPS atomic clocks (TrueTime)"
            ),
            relatedTrackId = "system_design_interview"
        ),
        VideoMockInterview(
            id = "vm_sd_02",
            topicId = "system_design",
            title = "Consistent Hashing & Virtual Nodes Deep Dive Interview",
            channelName = "ByteByteGo",
            youtubeVideoId = "zaRkONvyGr8",
            duration = "41 min",
            difficulty = "Senior",
            description = "Detailed walkthrough of hashing ring topology, minimizing key redistribution during node join/leave, and solving hot-spotting with virtual nodes.",
            keyTakeaways = listOf(
                "Why naive hash(key) % N redistributes almost all keys when N changes",
                "Using MD5/SHA-256 on a 2^32 hash ring to only relocate K/N keys",
                "Assigning 100-200 virtual nodes per physical server for uniform load distribution"
            ),
            relatedTrackId = "system_design_interview"
        ),
        VideoMockInterview(
            id = "vm_sd_03",
            topicId = "system_design",
            title = "Database Sharding & Partitioning Strategies Mock Interview",
            channelName = "Hussein Nasser",
            youtubeVideoId = "5faMjKuB9bc",
            duration = "48 min",
            difficulty = "Staff",
            description = "Explores range-based vs hash-based sharding, directory-based routing, handling cross-shard joins, and online re-sharding without downtime.",
            keyTakeaways = listOf(
                "Selecting the optimal shard key to avoid single-shard hotspotting",
                "Mitigating cross-shard joins using denormalization or application-level merges",
                "Dual-writing and backfilling data during online database migrations"
            ),
            relatedTrackId = "system_design_interview"
        ),
        VideoMockInterview(
            id = "vm_sd_04",
            topicId = "system_design",
            title = "Distributed Caching: Eviction Policies & Write Strategies",
            channelName = "Concept & Coding",
            youtubeVideoId = "6Y_d20y00-4",
            duration = "42 min",
            difficulty = "Mid-to-Senior",
            description = "Candidate is evaluated on Cache-Aside, Read-Through, Write-Through, Write-Behind (Write-Back), and LRU / LFU cache eviction algorithms.",
            keyTakeaways = listOf(
                "Cache-Aside pattern resilience against direct cache server failures",
                "Write-Behind buffering benefits and data loss risks on node crash",
                "Implementing O(1) LRU Cache using Doubly Linked List + HashMap"
            ),
            relatedTrackId = "system_design_interview"
        ),
        VideoMockInterview(
            id = "vm_sd_05",
            topicId = "system_design",
            title = "Distributed Consensus: Raft vs Paxos Explained in an Interview",
            channelName = "Jordan has no life",
            youtubeVideoId = "30eW33XW_oI",
            duration = "51 min",
            difficulty = "Staff",
            description = "Deep architectural discussion on leader election, log replication, split brain prevention using quorums (N/2 + 1), and cluster membership changes in Raft.",
            keyTakeaways = listOf(
                "Randomized election timeouts preventing split-vote deadlocks in Raft",
                "Why majority quorum prevents dual leaders during network partitions",
                "Raft state machine safety invariant: committed logs are never overwritten"
            ),
            relatedTrackId = "system_design_interview"
        ),
        VideoMockInterview(
            id = "vm_sd_06",
            topicId = "system_design",
            title = "Distributed Unique ID Generator (Twitter Snowflake)",
            channelName = "ByteByteGo",
            youtubeVideoId = "gocwRvLhDf8",
            duration = "37 min",
            difficulty = "Mid-to-Senior",
            description = "Candidate designs a 64-bit unique ID generator: 1 bit sign, 41 bits timestamp (69 years), 10 bits machine/datacenter ID, and 12 bits sequence number.",
            keyTakeaways = listOf(
                "Generating time-sortable 64-bit IDs without database sequence bottleneck",
                "Handling system clock rollbacks (NTP synchronization backwards step)",
                "Generating up to 4,096 IDs per millisecond per machine"
            ),
            relatedTrackId = "system_design_interview"
        ),
        VideoMockInterview(
            id = "vm_sd_07",
            topicId = "system_design",
            title = "API Rate Limiting Architecture at Global Edge",
            channelName = "ByteByteGo",
            youtubeVideoId = "CRGPbCBpeqI",
            duration = "43 min",
            difficulty = "Senior",
            description = "Evaluates deploying rate limiters at API gateway vs edge CDN (Cloudflare Workers), Redis sliding window rate limiting scripts with Lua, and headers.",
            keyTakeaways = listOf(
                "Using atomic Redis Lua scripts to avoid race conditions during counter updates",
                "Returning standard HTTP 429 Too Many Requests with Retry-After header",
                "Differentiating rate limits by IP, user ID, or API token tier"
            ),
            relatedTrackId = "system_design_interview"
        ),
        VideoMockInterview(
            id = "vm_sd_08",
            topicId = "system_design",
            title = "Distributed Lock Manager: Etcd vs Redis vs ZooKeeper",
            channelName = "Hussein Nasser",
            youtubeVideoId = "m_T90p_wP3E",
            duration = "40 min",
            difficulty = "Staff",
            description = "Investigates lease renewals, ephemeral nodes in ZooKeeper, watch mechanisms, and why Raft-backed Etcd is preferred for Kubernetes state.",
            keyTakeaways = listOf(
                "How ZooKeeper sequential ephemeral nodes solve thundering herd problem",
                "Etcd key-value watch API for instant notification of lock releases",
                "Fencing tokens preventing split-brain writes during garbage collection pauses"
            ),
            relatedTrackId = "system_design_interview"
        ),
        VideoMockInterview(
            id = "vm_sd_09",
            topicId = "system_design",
            title = "Designing High-Throughput Distributed Message Queues",
            channelName = "Gaurav Sen",
            youtubeVideoId = "vvhC64hQZMk",
            duration = "46 min",
            difficulty = "Senior",
            description = "Candidate designs a persistent message broker: sequential disk I/O, zero-copy OS transfers (sendfile), consumer groups, and message acknowledgement.",
            keyTakeaways = listOf(
                "Why sequential disk I/O matches or exceeds random memory access speeds",
                "Linux OS page cache and zero-copy transfers bypassing user space buffers",
                "Managing consumer group offsets to guarantee message delivery guarantees"
            ),
            relatedTrackId = "system_design_interview"
        ),
        VideoMockInterview(
            id = "vm_sd_10",
            topicId = "system_design",
            title = "Multi-Region Active-Active Replication & Conflict Resolution",
            channelName = "Jordan has no life",
            youtubeVideoId = "KmAyPUv97nM",
            duration = "54 min",
            difficulty = "Staff",
            description = "Examines bi-directional database replication across US and Europe regions, Last-Write-Wins (LWW) clock skew vulnerabilities, and CRDTs.",
            keyTakeaways = listOf(
                "Why Last-Write-Wins can silently drop valid data updates due to NTP clock drift",
                "Conflict-Free Replicated Data Types (CRDTs) enabling mathematically provable convergence",
                "Geo-routing DNS with latency-based routing policies"
            ),
            relatedTrackId = "system_design_interview"
        )
    )

    // --- 8. DevOps Mock Interviews (10 videos) ---
    private val devopsVideos = listOf(
        VideoMockInterview(
            id = "vm_devops_01",
            topicId = "devops",
            title = "Senior DevOps & SRE Mock Interview: Kubernetes Pod Lifecycle & Debugging",
            channelName = "TechWorld with Nana",
            youtubeVideoId = "X48VuDVv0do",
            duration = "52 min",
            difficulty = "Senior",
            description = "Interviewer tests candidate on debugging CrashLoopBackOff, OOMKilled pods, InitContainers, readiness vs liveness probes, and node eviction.",
            keyTakeaways = listOf(
                "Diagnosing CrashLoopBackOff using kubectl logs --previous and kubectl describe",
                "Understanding Linux cgroups memory.max causing container OOMKilled exit code 137",
                "Proper configuration of readiness probes to prevent traffic hitting booting pods"
            ),
            relatedTrackId = "devops_interview"
        ),
        VideoMockInterview(
            id = "vm_devops_02",
            topicId = "devops",
            title = "Docker Internals: Linux Namespaces, Cgroups & Multi-Stage Builds",
            channelName = "Hussein Nasser",
            youtubeVideoId = "eGz9DS-aIeY",
            duration = "44 min",
            difficulty = "Mid-to-Senior",
            description = "Deep dive into container virtualization primitives (PID, NET, MNT namespaces), layer caching optimization, and minimal distroless base images.",
            keyTakeaways = listOf(
                "How Linux kernel namespaces isolate process visibility and networking",
                "Optimizing Dockerfile instruction order to maximize build layer caching",
                "Using Google Distroless or Alpine images to minimize container attack surface"
            ),
            relatedTrackId = "devops_interview"
        ),
        VideoMockInterview(
            id = "vm_devops_03",
            topicId = "devops",
            title = "CI/CD Pipeline Security & GitOps with ArgoCD Technical Interview",
            channelName = "Continuous Delivery",
            youtubeVideoId = "mehyxQ_0xN0",
            duration = "47 min",
            difficulty = "Senior",
            description = "Candidate explains pull-based GitOps deployment loops, declarative state reconciliation, secret management with SealedSecrets / HashiCorp Vault.",
            keyTakeaways = listOf(
                "Comparing push-based CI deployment vs pull-based GitOps continuous delivery",
                "Injecting secrets dynamically into pods using Vault Agent sidecars",
                "Automated drift detection and self-healing in ArgoCD"
            ),
            relatedTrackId = "devops_interview"
        ),
        VideoMockInterview(
            id = "vm_devops_04",
            topicId = "devops",
            title = "Infrastructure as Code (IaC): Terraform State & Module Design",
            channelName = "TechWorld with Nana",
            youtubeVideoId = "7xngnjfIlK4",
            duration = "40 min",
            difficulty = "Mid-Level",
            description = "Probing candidate on remote state locking with S3 + DynamoDB, terraform plan inspection, importing existing infrastructure, and zero-downtime resource recreation.",
            keyTakeaways = listOf(
                "Preventing concurrent state corruption using DynamoDB distributed state locking",
                "Structuring reusable Terraform modules with strict input type constraints",
                "Managing lifecycle create_before_destroy for seamless cloud updates"
            ),
            relatedTrackId = "devops_interview"
        ),
        VideoMockInterview(
            id = "vm_devops_05",
            topicId = "devops",
            title = "Kubernetes Networking Deep Dive: CNI, ClusterIP, NodePort & Ingress",
            channelName = "TechWorld with Nana",
            youtubeVideoId = "0Omvgd7CavE",
            duration = "49 min",
            difficulty = "Staff",
            description = "Explains iptables vs IPVS packet routing in kube-proxy, overlay networks (Calico, Flannel), Cilium eBPF packet processing, and Ingress controllers.",
            keyTakeaways = listOf(
                "How kube-proxy programs iptables rules to load balance ClusterIP traffic",
                "Replacing iptables with eBPF in Cilium for massive throughput gains at scale",
                "Ingress controller architecture converting HTTP host rules into pod endpoints"
            ),
            relatedTrackId = "devops_interview"
        ),
        VideoMockInterview(
            id = "vm_devops_06",
            topicId = "devops",
            title = "Production Incident Management & Post-Mortem SRE Interview",
            channelName = "Exponent",
            youtubeVideoId = "aE5d487wEcA",
            duration = "46 min",
            difficulty = "Staff",
            description = "Candidate handles a simulated major P0 production outage: incident triage, establishing incident commander, mitigation, and blameless post-mortem writing.",
            keyTakeaways = listOf(
                "Separating triage mitigation from deep root-cause debugging during live incidents",
                "Establishing clear incident communication cadence to stakeholders",
                "Writing blameless post-mortems focusing on systemic guardrails rather than human error"
            ),
            relatedTrackId = "devops_interview"
        ),
        VideoMockInterview(
            id = "vm_devops_07",
            topicId = "devops",
            title = "Prometheus, Grafana & Alertmanager Monitoring Setup Interview",
            channelName = "TechWorld with Nana",
            youtubeVideoId = "hA4h_H_7lG4",
            duration = "38 min",
            difficulty = "Mid-to-Senior",
            description = "Evaluates pull-based metrics scraping, PromQL queries (rate, irate, histogram_quantile P99), alert silencing, and defining actionable SLO alerts.",
            keyTakeaways = listOf(
                "Calculating P99 latency percentiles using histogram_quantile PromQL functions",
                "Configuring alert routing to avoid alert fatigue in engineering on-call rotations",
                "Differentiating service level indicators (SLI) from internal vanity metrics"
            ),
            relatedTrackId = "devops_interview"
        ),
        VideoMockInterview(
            id = "vm_devops_08",
            topicId = "devops",
            title = "Linux Performance Troubleshooting: CPU, Memory, Disk & Network",
            channelName = "Brendan Gregg Talks",
            youtubeVideoId = "va4_C73V2vM",
            duration = "55 min",
            difficulty = "Staff",
            description = "Systematic 60-second Linux triage using uptime, dmesg, vmstat, mpstat, pidstat, iostat, free, and perf to uncover kernel bottlenecks.",
            keyTakeaways = listOf(
                "Interpreting CPU load averages against physical core count",
                "Identifying disk saturation using iostat %util and await metrics",
                "Analyzing page faults (major vs minor) and swap activity in vmstat"
            ),
            relatedTrackId = "devops_interview"
        ),
        VideoMockInterview(
            id = "vm_devops_09",
            topicId = "devops",
            title = "Zero Trust Cloud Networking: Service Mesh (Istio / Envoy) Interview",
            channelName = "Hussein Nasser",
            youtubeVideoId = "c3p57KfZg3E",
            duration = "43 min",
            difficulty = "Senior",
            description = "Explores Envoy sidecar proxy injection, automatic mutual TLS (mTLS) between microservices, traffic splitting, and fault injection testing.",
            keyTakeaways = listOf(
                "Transparent iptables redirection into Envoy sidecar proxy",
                "Enforcing cryptographic pod-to-pod identity with SPIFFE/SPIRE certificates",
                "Chaos testing using Istio HTTP delay and abort fault injection"
            ),
            relatedTrackId = "devops_interview"
        ),
        VideoMockInterview(
            id = "vm_devops_10",
            topicId = "devops",
            title = "Container Security & Vulnerability Scanning (Trivy, Falco)",
            channelName = "Continuous Delivery",
            youtubeVideoId = "gq41bZ-q78U",
            duration = "41 min",
            difficulty = "Mid-to-Senior",
            description = "Evaluates static image scanning in CI pipelines, runtime kernel syscall monitoring with Falco, and Kubernetes Pod Security Standards (PSS).",
            keyTakeaways = listOf(
                "Blocking high/critical CVEs in CI pipelines before registry push with Trivy",
                "Detecting unexpected shell executions inside containers via Falco eBPF rules",
                "Enforcing runAsNonRoot and readOnlyRootFilesystem security contexts"
            ),
            relatedTrackId = "devops_interview"
        )
    )

    // --- 9. SQL & Database Mock Interviews (10 videos) ---
    private val sqlVideos = listOf(
        VideoMockInterview(
            id = "vm_sql_01",
            topicId = "sql",
            title = "SQL Performance Tuning & Indexing: Senior Database Mock Interview",
            channelName = "Hussein Nasser",
            youtubeVideoId = "HubezKbFL7E",
            duration = "53 min",
            difficulty = "Senior",
            description = "Candidate dissects EXPLAIN ANALYZE output: sequential scan vs index scan, index only scan, B-Tree leaf node lookups, and composite index column order.",
            keyTakeaways = listOf(
                "How leading column rule dictates whether a composite index can be leveraged",
                "Eliminating table heap lookups using covering indexes with INCLUDE columns",
                "Understanding when database query planners intentionally choose sequential scans"
            ),
            relatedTrackId = "sql_interview"
        ),
        VideoMockInterview(
            id = "vm_sql_02",
            topicId = "sql",
            title = "ACID Transactions & Transaction Isolation Levels Deep Dive",
            channelName = "Concept & Coding",
            youtubeVideoId = "z2kLzT7o97k",
            duration = "46 min",
            difficulty = "Senior",
            description = "Examines Read Uncommitted, Read Committed, Repeatable Read, and Serializable levels; dirty reads, non-repeatable reads, phantom reads, and MVCC.",
            keyTakeaways = listOf(
                "How Multi-Version Concurrency Control (MVCC) allows readers to never block writers",
                "Preventing phantom reads with range locks or next-key locks in InnoDB",
                "Write skew anomalies and when Serializable snapshot isolation is mandatory"
            ),
            relatedTrackId = "sql_interview"
        ),
        VideoMockInterview(
            id = "vm_sql_03",
            topicId = "sql",
            title = "Complex SQL Queries & Window Functions Live Coding Interview",
            channelName = "Alex The Analyst",
            youtubeVideoId = "Ww71knvhQ-s",
            duration = "48 min",
            difficulty = "Mid-Level",
            description = "Candidate solves real-world analytics questions using ROW_NUMBER(), RANK(), DENSE_RANK(), LEAD(), LAG(), and cumulative running sums with PARTITION BY.",
            keyTakeaways = listOf(
                "Differences between RANK (skips ranks on ties) and DENSE_RANK (no gaps)",
                "Calculating period-over-period growth rates using LAG() window offset",
                "Filtering window function calculations safely using Common Table Expressions (CTEs)"
            ),
            relatedTrackId = "sql_interview"
        ),
        VideoMockInterview(
            id = "vm_sql_04",
            topicId = "sql",
            title = "Database Normalization (1NF to BCNF) & Intentional Denormalization",
            channelName = "FreeCodeCamp",
            youtubeVideoId = "GFQaEYEc8_8",
            duration = "42 min",
            difficulty = "Mid-Level",
            description = "Candidate walks through functional dependencies, removing partial and transitive dependencies, and when to denormalize for read performance.",
            keyTakeaways = listOf(
                "Eliminating repeating groups in 1NF and partial key dependencies in 2NF",
                "Removing transitive non-key dependencies to satisfy 3NF rules",
                "Intentional denormalization trade-offs: faster reads vs write update anomaly risks"
            ),
            relatedTrackId = "sql_interview"
        ),
        VideoMockInterview(
            id = "vm_sql_05",
            topicId = "sql",
            title = "PostgreSQL Internals: WAL, Vacuuming & Buffer Cache Mock Interview",
            channelName = "Hussein Nasser",
            youtubeVideoId = "5faMjKuB9bc",
            duration = "50 min",
            difficulty = "Staff",
            description = "Deep dive into Write-Ahead Logging (WAL) crash recovery, dead row bloat, autovacuum tuning, and shared_buffers memory management.",
            keyTakeaways = listOf(
                "How WAL guarantees durability without immediate flush of dirty table pages",
                "Dead tuple accumulation in PostgreSQL due to MVCC row updates and deletes",
                "Tuning autovacuum_vacuum_scale_factor to prevent catastrophic table bloat"
            ),
            relatedTrackId = "sql_interview"
        ),
        VideoMockInterview(
            id = "vm_sql_06",
            topicId = "sql",
            title = "Database Locking Mechanisms: Optimistic vs Pessimistic Locking",
            channelName = "ByteByteGo",
            youtubeVideoId = "m_T90p_wP3E",
            duration = "39 min",
            difficulty = "Senior",
            description = "Candidate is questioned on SELECT ... FOR UPDATE pessimistic row locks, deadlock detection graphs, and version column optimistic locking.",
            keyTakeaways = listOf(
                "When optimistic locking with version column outperforms pessimistic row locks",
                "Preventing deadlocks by sorting resource IDs before acquiring locks",
                "Using SELECT FOR UPDATE SKIP LOCKED for high-throughput message queue workers"
            ),
            relatedTrackId = "sql_interview"
        ),
        VideoMockInterview(
            id = "vm_sql_07",
            topicId = "sql",
            title = "SQL Joins Under the Hood: Nested Loop vs Hash Join vs Merge Join",
            channelName = "Hussein Nasser",
            youtubeVideoId = "HubezKbFL7E",
            duration = "44 min",
            difficulty = "Senior",
            description = "Explains how the SQL query engine picks join algorithms based on table statistics, indexes, and available work_mem memory.",
            keyTakeaways = listOf(
                "Hash Join building in-memory hash table on smaller relation before probing",
                "Merge Join efficiency when both inputs are pre-sorted by index",
                "Why missing indexes force expensive O(M * N) Nested Loop scans"
            ),
            relatedTrackId = "sql_interview"
        ),
        VideoMockInterview(
            id = "vm_sql_08",
            topicId = "sql",
            title = "Large Scale Table Partitioning (Range, List, Hash) in PostgreSQL",
            channelName = "Daily Code Buffer",
            youtubeVideoId = "cRff_M_vW5E",
            duration = "37 min",
            difficulty = "Senior",
            description = "Candidate models time-series data using declarative range partitioning by month, partition pruning by the query planner, and automated partition creation.",
            keyTakeaways = listOf(
                "How constraint exclusion and partition pruning skip 95%+ of irrelevant partition scans",
                "Dropping historic data instantly with DROP TABLE on a partition without VACUUM overhead",
                "Global index vs local index considerations across partitioned tables"
            ),
            relatedTrackId = "sql_interview"
        ),
        VideoMockInterview(
            id = "vm_sql_09",
            topicId = "sql",
            title = "NoSQL vs Relational Databases: When to Choose What Mock Interview",
            channelName = "ByteByteGo",
            youtubeVideoId = "0UTOLR-kCtc",
            duration = "41 min",
            difficulty = "Mid-to-Senior",
            description = "Compares PostgreSQL with MongoDB document store, DynamoDB key-value store, and Cassandra wide-column store across access patterns.",
            keyTakeaways = listOf(
                "Query-driven schema modeling in DynamoDB vs relational schema normalization",
                "When JSONB columns in PostgreSQL provide document database agility with ACID safety",
                "Horizontal scaling limitations of traditional relational primary-replica setups"
            ),
            relatedTrackId = "sql_interview"
        ),
        VideoMockInterview(
            id = "vm_sql_10",
            topicId = "sql",
            title = "Database Connection Pooling Internals (HikariCP) & Starvation",
            channelName = "Tech Primers",
            youtubeVideoId = "GuF759yqW00",
            duration = "35 min",
            difficulty = "Staff",
            description = "Investigates pool sizing formulas, connection leak detection, thread starvation, and TCP connection establishment overhead.",
            keyTakeaways = listOf(
                "The classic PostgreSQL pool sizing formula: connections = (core_count * 2) + effective_spindle_count",
                "Diagnosing unreturned connections with HikariCP leakDetectionThreshold",
                "Why oversized connection pools degrade database performance due to CPU context switching"
            ),
            relatedTrackId = "sql_interview"
        )
    )

    // --- 10. Angular Mock Interviews (10 videos) ---
    private val angularVideos = listOf(
        VideoMockInterview(
            id = "vm_ang_01",
            topicId = "angular",
            title = "Modern Angular 17/18 Senior Mock Interview: Signals vs RxJS",
            channelName = "Decoded Frontend",
            youtubeVideoId = "30eW33XW_oI",
            duration = "54 min",
            difficulty = "Senior",
            description = "Deep architectural dive into Angular Signals, signal(), computed(), effect(), Push-Pull reactivity model, and interoperability with RxJS using toSignal() and toObservable().",
            keyTakeaways = listOf(
                "Push-pull reactivity: graph notification marks dirty, value read is lazily evaluated",
                "Glitch-free evaluation preventing intermediate stale calculations in diamond graphs",
                "When to choose Signals (synchronous UI state) vs RxJS (asynchronous event streams)"
            ),
            relatedTrackId = "angular_interview"
        ),
        VideoMockInterview(
            id = "vm_ang_02",
            topicId = "angular",
            title = "Zoneless Angular & Change Detection Internals Mock Interview",
            channelName = "Monsterlessons Academy",
            youtubeVideoId = "xk4_1vDrzzo",
            duration = "47 min",
            difficulty = "Staff",
            description = "Explains Zone.js monkey-patching overhead, OnPush change detection strategy, and transitioning to Zoneless Angular with provideExperimentalZonelessChangeDetection().",
            keyTakeaways = listOf(
                "How Zone.js wraps browser async APIs (setTimeout, Promise, addEventListener) to trigger full tree checks",
                "Why Zoneless change detection provides massive bundle size reduction and execution speedup",
                "Explicit notification mechanisms (Signals, markForCheck, AsyncPipe) in Zoneless apps"
            ),
            relatedTrackId = "angular_interview"
        ),
        VideoMockInterview(
            id = "vm_ang_03",
            topicId = "angular",
            title = "Angular Dependency Injection & Hierarchical Injectors Interview",
            channelName = "Decoded Frontend",
            youtubeVideoId = "3PZ65mu_f4E",
            duration = "45 min",
            difficulty = "Senior",
            description = "Candidate is questioned on EnvironmentInjector vs ElementInjector, providedIn: 'root', multi-providers, InjectionToken, and resolution modifiers (@Self, @SkipSelf, @Optional).",
            keyTakeaways = listOf(
                "How Angular injector resolution bubbles up from Component to Parent to Root injector",
                "Using InjectionToken with factory functions for tree-shakable configurable services",
                "Component-level providers creating isolated service instances scoped to component lifecycle"
            ),
            relatedTrackId = "angular_interview"
        ),
        VideoMockInterview(
            id = "vm_ang_04",
            topicId = "angular",
            title = "Advanced RxJS in Angular: Flattening Operators & Memory Leaks",
            channelName = "Joshua Morony",
            youtubeVideoId = "d9S_jO160q0",
            duration = "43 min",
            difficulty = "Mid-to-Senior",
            description = "Comprehensive comparison of switchMap (cancels previous), mergeMap (concurrent), concatMap (sequential in-order), exhaustMap (ignores while active), and takeUntilDestroyed.",
            keyTakeaways = listOf(
                "Using switchMap for typeahead search queries to cancel in-flight stale network responses",
                "Preventing memory leaks cleanly using Angular 16+ takeUntilDestroyed() operator",
                "Why manual subscribe() in component classes is an anti-pattern compared to AsyncPipe"
            ),
            relatedTrackId = "angular_interview"
        ),
        VideoMockInterview(
            id = "vm_ang_05",
            topicId = "angular",
            title = "Angular State Management: NgRx SignalStore vs ComponentStore",
            channelName = "Decoded Frontend",
            youtubeVideoId = "YZZ_nL1GfCY",
            duration = "50 min",
            difficulty = "Senior",
            description = "Candidate designs state architecture using the modern @ngrx/signals SignalStore: withState, withComputed, withMethods, and custom extensible store features.",
            keyTakeaways = listOf(
                "Declarative state definition using SignalStore functional extensions",
                "Eliminating boilerplate actions and reducers compared to classic NgRx Store",
                "Integrating RxJS reactive effects seamlessly with rxMethod in SignalStore"
            ),
            relatedTrackId = "angular_interview"
        ),
        VideoMockInterview(
            id = "vm_ang_06",
            topicId = "angular",
            title = "Modern Angular Routing: Standalone, Resolvers & Functional Guards",
            channelName = "Monsterlessons Academy",
            youtubeVideoId = "K1iu1kXkVoA",
            duration = "39 min",
            difficulty = "Mid-Level",
            description = "Focuses on standalone routing without NgModule, functional CanActivateFn guards, inject() in guards, and deferred component loading with loadComponent.",
            keyTakeaways = listOf(
                "Replacing class-based guards with concise functional CanActivateFn guards",
                "Lazy-loading standalone components and routes to achieve sub-second initial bundle downloads",
                "Preloading strategies (PreloadAllModules vs custom network-aware preloader)"
            ),
            relatedTrackId = "angular_interview"
        ),
        VideoMockInterview(
            id = "vm_ang_07",
            topicId = "angular",
            title = "Angular Control Flow (@if, @for, @switch) & Deferrable Views (@defer)",
            channelName = "Joshua Morony",
            youtubeVideoId = "36jZ3n_w-aQ",
            duration = "38 min",
            difficulty = "Mid-Level",
            description = "Deep exploration into built-in template control flow syntax replacing *ngIf/*ngFor, mandatory track expression performance gains, and @defer trigger conditions.",
            keyTakeaways = listOf(
                "Why mandatory tracking in @for eliminates unnecessary DOM node destruction and recreation",
                "Using @defer (on viewport) to lazily download and render below-the-fold heavy components",
                "Configuring @placeholder, @loading, and @error blocks for smooth user experience"
            ),
            relatedTrackId = "angular_interview"
        ),
        VideoMockInterview(
            id = "vm_ang_08",
            topicId = "angular",
            title = "Angular Server-Side Rendering (SSR) & Hydration Mock Interview",
            channelName = "Decoded Frontend",
            youtubeVideoId = "b9O9830BBC8",
            duration = "46 min",
            difficulty = "Staff",
            description = "Explores Angular Universal SSR, non-destructive hydration (no DOM flickering), TransferState API for avoiding double HTTP calls, and event replay.",
            keyTakeaways = listOf(
                "How non-destructive hydration preserves server-rendered DOM nodes while attaching listeners",
                "Using TransferState to cache API responses fetched on the server for instant client reuse",
                "Guarding browser-only code (window, document) with isPlatformBrowser(platformId)"
            ),
            relatedTrackId = "angular_interview"
        ),
        VideoMockInterview(
            id = "vm_ang_09",
            topicId = "angular",
            title = "Angular Performance Optimization: Web Workers & Memory Leaks",
            channelName = "Monsterlessons Academy",
            youtubeVideoId = "Wl4i8f9XJ1Q",
            duration = "42 min",
            difficulty = "Senior",
            description = "Candidate diagnoses memory leaks with Chrome DevTools Heap Snapshots, optimizes bundle size using source-map-explorer, and offloads heavy computation to Web Workers.",
            keyTakeaways = listOf(
                "Identifying detached DOM tree memory leaks caused by uncleaned event listeners",
                "Offloading CPU-intensive parsing or cryptographic hashing to Angular Web Workers",
                "Optimizing asset loading and image rendering using NgOptimizedImage directive"
            ),
            relatedTrackId = "angular_interview"
        ),
        VideoMockInterview(
            id = "vm_ang_10",
            topicId = "angular",
            title = "Angular Testing: Component Harnesses, TestBed & Vitest",
            channelName = "Joshua Morony",
            youtubeVideoId = "Geq68OVyQRk",
            duration = "44 min",
            difficulty = "Mid-to-Senior",
            description = "Evaluates testing modern Angular: Angular CDK Component Harnesses, mocking HTTP with provideHttpClientTesting(), and lightning-fast unit tests with Vitest.",
            keyTakeaways = listOf(
                "Using Component Harnesses to insulate tests from internal CSS selector changes",
                "Mocking HTTP requests with HttpTestingController verify() checks",
                "Testing Signals and computed properties without requiring async fakeAsync tick"
            ),
            relatedTrackId = "angular_interview"
        )
    )

    // --- 11. Security & AppSec Mock Interviews (10 videos) ---
    private val securityVideos = listOf(
        VideoMockInterview(
            id = "vm_sec_01",
            topicId = "security",
            title = "Senior Application Security (AppSec) Mock Interview: OWASP Top 10",
            channelName = "Exponent",
            youtubeVideoId = "W0w9G_y3u3E",
            duration = "55 min",
            difficulty = "Senior",
            description = "Comprehensive walkthrough covering Broken Access Control (IDOR), Cryptographic Failures, Injection attacks (SQLi, Command Injection), and SSRF mitigation.",
            keyTakeaways = listOf(
                "Preventing Insecure Direct Object References (IDOR) with strict tenant-context authorization checks",
                "Why Server-Side Request Forgery (SSRF) requires metadata IP blocking (169.254.169.254) and allowlisting",
                "Parameterized prepared statements as the definitive defense against SQL injection"
            ),
            relatedTrackId = "security_interview"
        ),
        VideoMockInterview(
            id = "vm_sec_02",
            topicId = "security",
            title = "OAuth 2.0 & OpenID Connect (OIDC) Architecture Mock Interview",
            channelName = "Hussein Nasser",
            youtubeVideoId = "x_W0a3_k8XQ",
            duration = "48 min",
            difficulty = "Senior",
            description = "Deep dive into Authorization Code Flow with PKCE (Proof Key for Code Exchange), ID tokens vs Access tokens vs Refresh tokens, and token revocation.",
            keyTakeaways = listOf(
                "Why PKCE is mandatory for single-page and mobile apps without client secrets",
                "Differences between OIDC ID Token (who you are) and OAuth Access Token (what you can access)",
                "Protecting refresh tokens with token rotation and automatic reuse detection"
            ),
            relatedTrackId = "security_interview"
        ),
        VideoMockInterview(
            id = "vm_sec_03",
            topicId = "security",
            title = "JWT Security Flaws & Best Practices Technical Interview",
            channelName = "Amigoscode",
            youtubeVideoId = "b9O9830BBC8",
            duration = "43 min",
            difficulty = "Mid-to-Senior",
            description = "Evaluates common JWT vulnerabilities: alg: 'none' attack, HMAC vs RSA key confusion, missing expiration validation, and stateless revocation strategies.",
            keyTakeaways = listOf(
                "Enforcing strict algorithm allowlists on the backend to prevent alg: none exploits",
                "Managing stateless JWT invalidation using short TTLs and Redis blacklist/bloom filter",
                "Asymmetric key signing (RS256/ES256) allowing public verification without exposing private key"
            ),
            relatedTrackId = "security_interview"
        ),
        VideoMockInterview(
            id = "vm_sec_04",
            topicId = "security",
            title = "Zero Trust Architecture & Micro-segmentation Interview",
            channelName = "TechWorld with Nana",
            youtubeVideoId = "c3p57KfZg3E",
            duration = "46 min",
            difficulty = "Staff",
            description = "Candidate explains Never Trust, Always Verify principle, identity-aware proxies, mutual TLS (mTLS) with short-lived certificates, and least-privilege RBAC.",
            keyTakeaways = listOf(
                "Deconstructing the perimeter security model in modern distributed cloud environments",
                "Enforcing cryptographic workload identity via SPIFFE/SPIRE",
                "Micro-segmenting Kubernetes network policies to isolate compromised pods"
            ),
            relatedTrackId = "security_interview"
        ),
        VideoMockInterview(
            id = "vm_sec_05",
            topicId = "security",
            title = "Web Security Headers: CSP, HSTS, CORS & SameSite Cookies",
            channelName = "Hussein Nasser",
            youtubeVideoId = "e5vC-c1bX_g",
            duration = "42 min",
            difficulty = "Mid-Level",
            description = "Probing candidate on Content Security Policy (CSP) nonce directives, strict HSTS preloading, SameSite=Lax/Strict cookie flags, and CORS configuration pitfalls.",
            keyTakeaways = listOf(
                "Defeating XSS attacks using strict Content Security Policy with cryptographic nonces",
                "Why CORS is a browser security relaxation mechanism, not an authorization boundary",
                "SameSite=Strict cookie policy completely neutralizing Cross-Site Request Forgery (CSRF)"
            ),
            relatedTrackId = "security_interview"
        ),
        VideoMockInterview(
            id = "vm_sec_06",
            topicId = "security",
            title = "TLS 1.3 Handshake & Cryptographic Foundations Interview",
            channelName = "Hussein Nasser",
            youtubeVideoId = "5faMjKuB9bc",
            duration = "39 min",
            difficulty = "Senior",
            description = "Walks through 1-RTT TLS 1.3 handshake, Diffie-Hellman ephemeral key exchange (Forward Secrecy), removing insecure ciphers, and 0-RTT replay attack risks.",
            keyTakeaways = listOf(
                "How TLS 1.3 achieves 1-RTT connection setup while preserving forward secrecy",
                "Why Ephemeral Diffie-Hellman ensures historic traffic cannot be decrypted if private key leaks",
                "0-RTT early data replay vulnerability and safe methods (GET idempotency requirement)"
            ),
            relatedTrackId = "security_interview"
        ),
        VideoMockInterview(
            id = "vm_sec_07",
            topicId = "security",
            title = "Cross-Site Scripting (XSS) & Defenses in Modern Frameworks",
            channelName = "FreeCodeCamp",
            youtubeVideoId = "0aY_q478yq0",
            duration = "40 min",
            difficulty = "Mid-Level",
            description = "Compares Stored XSS, Reflected XSS, and DOM-based XSS; automatic HTML sanitization in Angular/React, and bypass risks when using innerHTML / bypassSecurityTrust.",
            keyTakeaways = listOf(
                "How modern frontend frameworks contextually escape user strings by default",
                "Risks of DomSanitizer.bypassSecurityTrustHtml in enterprise single-page applications",
                "Preventing session hijacking by enforcing HttpOnly flags on session tokens"
            ),
            relatedTrackId = "security_interview"
        ),
        VideoMockInterview(
            id = "vm_sec_08",
            topicId = "security",
            title = "Cloud Security Posture Management (CSPM) & IAM Least Privilege",
            channelName = "TechWorld with Nana",
            youtubeVideoId = "7xngnjfIlK4",
            duration = "45 min",
            difficulty = "Senior",
            description = "Evaluates cloud credential rotation, AWS IAM permission boundary enforcement, avoiding wildcard actions (Action: *), and S3 bucket public access blocks.",
            keyTakeaways = listOf(
                "Enforcing least privilege using IAM condition keys and service control policies (SCPs)",
                "Eliminating static long-lived credentials with IAM Roles for Service Accounts (IRSA)",
                "Automated detection of publicly exposed storage buckets and open security groups"
            ),
            relatedTrackId = "security_interview"
        ),
        VideoMockInterview(
            id = "vm_sec_09",
            topicId = "security",
            title = "Secure Software Supply Chain: Software Bill of Materials (SBOM)",
            channelName = "Continuous Delivery",
            youtubeVideoId = "gq41bZ-q78U",
            duration = "38 min",
            difficulty = "Senior",
            description = "Candidate designs supply chain security: generating SPDX/CycloneDX SBOMs, signing container images with Cosign/Sigstore, and dependency vulnerability scanning.",
            keyTakeaways = listOf(
                "Preventing dependency confusion and typosquatting attacks using private proxy registries",
                "Cryptographically verifying container image signatures in Kubernetes admission webhooks",
                "Maintaining an accurate SBOM for rapid remediation during zero-day disclosure (e.g. Log4j)"
            ),
            relatedTrackId = "security_interview"
        ),
        VideoMockInterview(
            id = "vm_sec_10",
            topicId = "security",
            title = "Post-Quantum Cryptography (PQC) & Future-Proof AppSec",
            channelName = "Jordan has no life",
            youtubeVideoId = "C0sW3q7y_g4",
            duration = "47 min",
            difficulty = "Staff",
            description = "Explores how Shor's algorithm threatens RSA and Elliptic Curve cryptography, NIST standardized post-quantum algorithms (ML-KEM, ML-DSA), and crypto-agility.",
            keyTakeaways = listOf(
                "Why 'Harvest Now, Decrypt Later' makes current encrypted data vulnerable to future quantum computers",
                "Hybrid key exchange combining classical X25519 with lattice-based ML-KEM (Kyber)",
                "Designing crypto-agile software architectures that support cipher swapping without major rewrites"
            ),
            relatedTrackId = "security_interview"
        )
    )

    private val allVideosList: List<VideoMockInterview> =
        javaVideos +
        springBootVideos +
        microservicesVideos +
        fullStackVideos +
        hldVideos +
        lldVideos +
        systemDesignVideos +
        devopsVideos +
        sqlVideos +
        angularVideos +
        securityVideos

    fun getTopics(): List<VideoMockTopic> = topicsList

    fun getVideosForTopic(topicId: String): List<VideoMockInterview> {
        return allVideosList.filter { it.topicId.equals(topicId, ignoreCase = true) }
    }

    fun getAllVideos(): List<VideoMockInterview> = allVideosList

    fun getVideoById(id: String): VideoMockInterview? {
        return allVideosList.firstOrNull { it.id == id }
    }

    fun getNextVideo(currentId: String, topicId: String): VideoMockInterview? {
        val topicVideos = getVideosForTopic(topicId)
        if (topicVideos.isEmpty()) return null
        val currentIndex = topicVideos.indexOfFirst { it.id == currentId }
        return if (currentIndex != -1 && currentIndex < topicVideos.lastIndex) {
            topicVideos[currentIndex + 1]
        } else if (topicVideos.isNotEmpty()) {
            topicVideos[0]
        } else null
    }

    fun getPreviousVideo(currentId: String, topicId: String): VideoMockInterview? {
        val topicVideos = getVideosForTopic(topicId)
        if (topicVideos.isEmpty()) return null
        val currentIndex = topicVideos.indexOfFirst { it.id == currentId }
        return if (currentIndex > 0) {
            topicVideos[currentIndex - 1]
        } else if (topicVideos.isNotEmpty()) {
            topicVideos.last()
        } else null
    }
}
