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
            relatedTrackId = "java_interview",
            description = "Core JVM internals, multi-threading, concurrency, memory partitions & modern Java features."
        ),
        VideoMockTopic(
            id = "spring_boot",
            name = "Spring Boot",
            icon = "🍃",
            videoCount = 10,
            relatedTrackId = "spring_boot_interview",
            description = "IoC containers, Spring Security 6, transactional boundaries, JPA performance & REST."
        ),
        VideoMockTopic(
            id = "microservices",
            name = "Microservices",
            icon = "🌐",
            videoCount = 10,
            relatedTrackId = "microservices_interview",
            description = "Saga distributed transactions, service discovery, API gateways, resilience & event streaming."
        ),
        VideoMockTopic(
            id = "full_stack",
            name = "Full Stack",
            icon = "⚡",
            videoCount = 10,
            relatedTrackId = "full_stack_interview",
            description = "End-to-end web architectures, reactive client state, RESTful contracts & full-stack loops."
        ),
        VideoMockTopic(
            id = "hld",
            name = "HLD",
            icon = "🏛️",
            videoCount = 10,
            relatedTrackId = "hld_interview",
            description = "High-level distributed systems: rate limiters, caching layers, sharding & fault-tolerant clusters."
        ),
        VideoMockTopic(
            id = "lld",
            name = "LLD",
            icon = "🧩",
            videoCount = 10,
            relatedTrackId = "lld_interview",
            description = "Low-level object-oriented design, SOLID principles, design patterns & clean domain models."
        ),
        VideoMockTopic(
            id = "system_design",
            name = "System Design",
            icon = "📐",
            videoCount = 10,
            relatedTrackId = "system_design_interview",
            description = "Scalability trade-offs, consensus algorithms, database partitioning & microservice decoupling."
        ),
        VideoMockTopic(
            id = "devops",
            name = "DevOps",
            icon = "🚀",
            videoCount = 10,
            relatedTrackId = "devops_interview",
            description = "CI/CD automation pipelines, Docker containerization, Kubernetes clusters & cloud infrastructure."
        ),
        VideoMockTopic(
            id = "sql",
            name = "SQL & Database",
            icon = "🗄️",
            videoCount = 10,
            relatedTrackId = "sql_interview",
            description = "Query optimization, indexing strategies (B-Tree/Hash), ACID transactions, isolation & sharding."
        ),
        VideoMockTopic(
            id = "angular",
            name = "Angular",
            icon = "🅰️",
            videoCount = 10,
            relatedTrackId = "angular_interview",
            description = "Component lifecycles, RxJS reactive streams, dependency injection, routing & Angular signals."
        ),
        VideoMockTopic(
            id = "security",
            name = "Security",
            icon = "🛡️",
            videoCount = 10,
            relatedTrackId = "security_interview",
            description = "OAuth 2.0, OpenID Connect, JWT signing, OWASP Top 10 defenses & encryption protocols."
        ),
    )

    // --- Java Mock Interviews (10 real verified long-form videos) ---
    private val javaVideos = listOf(
        VideoMockInterview(
            id = "vm_java_01",
            topicId = "java",
            title = "Intro",
            channelName = "GenZ Career",
            youtubeVideoId = "abPtff4wg8k",
            duration = "1:34:48 min",
            difficulty = "Mid-Level",
            description = "Real-world technical mock interview covering Java concepts, architectural trade-offs, and live candidate evaluation with GenZ Career.",
            keyTakeaways = listOf(
                "Core JVM memory partitions (Eden, Survivor, Tenured, Metaspace) and GC algorithms (G1, ZGC)",
                "Thread synchronization, volatile memory barriers, happens-before consistency, and CAS operations",
                "Collections internals: HashMap treeification threshold and thread-safe ConcurrentHashMap patterns"
            ),
            relatedTrackId = "java_interview"
        ),
        VideoMockInterview(
            id = "vm_java_02",
            topicId = "java",
            title = "Introduction",
            channelName = "Code Decode",
            youtubeVideoId = "xMlcsFLk-CU",
            duration = "1:08:15 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Java concepts, architectural trade-offs, and live candidate evaluation with Code Decode.",
            keyTakeaways = listOf(
                "Thread synchronization, volatile memory barriers, happens-before consistency, and CAS operations",
                "Collections internals: HashMap treeification threshold and thread-safe ConcurrentHashMap patterns",
                "Modern Java features: Virtual Threads (Project Loom), sealed classes, and functional stream pipelines"
            ),
            relatedTrackId = "java_interview"
        ),
        VideoMockInterview(
            id = "vm_java_03",
            topicId = "java",
            title = "Project Architecture",
            channelName = "Code Decode",
            youtubeVideoId = "tI0pXHvG7gs",
            duration = "20:26 min",
            difficulty = "Lead",
            description = "Real-world technical mock interview covering Java concepts, architectural trade-offs, and live candidate evaluation with Code Decode.",
            keyTakeaways = listOf(
                "Collections internals: HashMap treeification threshold and thread-safe ConcurrentHashMap patterns",
                "Modern Java features: Virtual Threads (Project Loom), sealed classes, and functional stream pipelines",
                "Core JVM memory partitions (Eden, Survivor, Tenured, Metaspace) and GC algorithms (G1, ZGC)"
            ),
            relatedTrackId = "java_interview"
        ),
        VideoMockInterview(
            id = "vm_java_04",
            topicId = "java",
            title = "Introduction to Java Interview Questions",
            channelName = "Magneq Software",
            youtubeVideoId = "EwLllH9-vk0",
            duration = "2:50:56 min",
            difficulty = "Architect",
            description = "Real-world technical mock interview covering Java concepts, architectural trade-offs, and live candidate evaluation with Magneq Software.",
            keyTakeaways = listOf(
                "Modern Java features: Virtual Threads (Project Loom), sealed classes, and functional stream pipelines",
                "Core JVM memory partitions (Eden, Survivor, Tenured, Metaspace) and GC algorithms (G1, ZGC)",
                "Thread synchronization, volatile memory barriers, happens-before consistency, and CAS operations"
            ),
            relatedTrackId = "java_interview"
        ),
        VideoMockInterview(
            id = "vm_java_05",
            topicId = "java",
            title = "Intro",
            channelName = "Java Guides",
            youtubeVideoId = "yq-XFhUalx4",
            duration = "21:26 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Java concepts, architectural trade-offs, and live candidate evaluation with Java Guides.",
            keyTakeaways = listOf(
                "Core JVM memory partitions (Eden, Survivor, Tenured, Metaspace) and GC algorithms (G1, ZGC)",
                "Thread synchronization, volatile memory barriers, happens-before consistency, and CAS operations",
                "Collections internals: HashMap treeification threshold and thread-safe ConcurrentHashMap patterns"
            ),
            relatedTrackId = "java_interview"
        ),
        VideoMockInterview(
            id = "vm_java_06",
            topicId = "java",
            title = "Java Interview Roadmap",
            channelName = "Kiran Academy - Java By Kiran",
            youtubeVideoId = "6IyLJMbvZ3Q",
            duration = "10:42 min",
            difficulty = "Mid-Level",
            description = "Real-world technical mock interview covering Java concepts, architectural trade-offs, and live candidate evaluation with Kiran Academy - Java By Kiran.",
            keyTakeaways = listOf(
                "Thread synchronization, volatile memory barriers, happens-before consistency, and CAS operations",
                "Collections internals: HashMap treeification threshold and thread-safe ConcurrentHashMap patterns",
                "Modern Java features: Virtual Threads (Project Loom), sealed classes, and functional stream pipelines"
            ),
            relatedTrackId = "java_interview"
        ),
        VideoMockInterview(
            id = "vm_java_07",
            topicId = "java",
            title = "Java Mock Interview for Freshers",
            channelName = "SP Global Solution",
            youtubeVideoId = "X_YmJu2oaUI",
            duration = "15:22 min",
            difficulty = "Staff",
            description = "Real-world technical mock interview covering Java concepts, architectural trade-offs, and live candidate evaluation with SP Global Solution.",
            keyTakeaways = listOf(
                "Collections internals: HashMap treeification threshold and thread-safe ConcurrentHashMap patterns",
                "Modern Java features: Virtual Threads (Project Loom), sealed classes, and functional stream pipelines",
                "Core JVM memory partitions (Eden, Survivor, Tenured, Metaspace) and GC algorithms (G1, ZGC)"
            ),
            relatedTrackId = "java_interview"
        ),
        VideoMockInterview(
            id = "vm_java_08",
            topicId = "java",
            title = "Introduction",
            channelName = "CareerRide",
            youtubeVideoId = "vuJf0jBGAtY",
            duration = "29:28 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Java concepts, architectural trade-offs, and live candidate evaluation with CareerRide.",
            keyTakeaways = listOf(
                "Modern Java features: Virtual Threads (Project Loom), sealed classes, and functional stream pipelines",
                "Core JVM memory partitions (Eden, Survivor, Tenured, Metaspace) and GC algorithms (G1, ZGC)",
                "Thread synchronization, volatile memory barriers, happens-before consistency, and CAS operations"
            ),
            relatedTrackId = "java_interview"
        ),
        VideoMockInterview(
            id = "vm_java_09",
            topicId = "java",
            title = "Introduction",
            channelName = "Code Decode",
            youtubeVideoId = "oUdENE7ljjw",
            duration = "43:39 min",
            difficulty = "Lead",
            description = "Real-world technical mock interview covering Java concepts, architectural trade-offs, and live candidate evaluation with Code Decode.",
            keyTakeaways = listOf(
                "Core JVM memory partitions (Eden, Survivor, Tenured, Metaspace) and GC algorithms (G1, ZGC)",
                "Thread synchronization, volatile memory barriers, happens-before consistency, and CAS operations",
                "Collections internals: HashMap treeification threshold and thread-safe ConcurrentHashMap patterns"
            ),
            relatedTrackId = "java_interview"
        ),
        VideoMockInterview(
            id = "vm_java_10",
            topicId = "java",
            title = "Why Java 8 Streams are the #1 Interview Filter",
            channelName = "Java Techie",
            youtubeVideoId = "1Ps5F1PU72M",
            duration = "29:07 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Java concepts, architectural trade-offs, and live candidate evaluation with Java Techie.",
            keyTakeaways = listOf(
                "Thread synchronization, volatile memory barriers, happens-before consistency, and CAS operations",
                "Collections internals: HashMap treeification threshold and thread-safe ConcurrentHashMap patterns",
                "Modern Java features: Virtual Threads (Project Loom), sealed classes, and functional stream pipelines"
            ),
            relatedTrackId = "java_interview"
        ),
    )

    // --- Spring Boot Mock Interviews (10 real verified long-form videos) ---
    private val springbootVideos = listOf(
        VideoMockInterview(
            id = "vm_spring_boot_01",
            topicId = "spring_boot",
            title = "Introduction",
            channelName = "Code Decode",
            youtubeVideoId = "xMlcsFLk-CU",
            duration = "41:16 min",
            difficulty = "Mid-Level",
            description = "Real-world technical mock interview covering Spring Boot concepts, architectural trade-offs, and live candidate evaluation with Code Decode.",
            keyTakeaways = listOf(
                "Spring IoC container lifecycle, Bean instantiation phases, and circular dependency resolution",
                "Spring Boot auto-configuration mechanism, conditional beans, and starter architecture",
                "Spring Data JPA dirty checking, N+1 query problem mitigation with entity graphs, and batch inserts"
            ),
            relatedTrackId = "spring_boot_interview"
        ),
        VideoMockInterview(
            id = "vm_spring_boot_02",
            topicId = "spring_boot",
            title = "Intro",
            channelName = "GenZ Career",
            youtubeVideoId = "abPtff4wg8k",
            duration = "32:51 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Spring Boot concepts, architectural trade-offs, and live candidate evaluation with GenZ Career.",
            keyTakeaways = listOf(
                "Spring Boot auto-configuration mechanism, conditional beans, and starter architecture",
                "Spring Data JPA dirty checking, N+1 query problem mitigation with entity graphs, and batch inserts",
                "Spring Security filter chain flow, JWT authentication filter, and method-level access controls"
            ),
            relatedTrackId = "spring_boot_interview"
        ),
        VideoMockInterview(
            id = "vm_spring_boot_03",
            topicId = "spring_boot",
            title = "Project Architecture",
            channelName = "GenZ Career",
            youtubeVideoId = "LKtpM8MdqdA",
            duration = "38:22 min",
            difficulty = "Lead",
            description = "Real-world technical mock interview covering Spring Boot concepts, architectural trade-offs, and live candidate evaluation with GenZ Career.",
            keyTakeaways = listOf(
                "Spring Data JPA dirty checking, N+1 query problem mitigation with entity graphs, and batch inserts",
                "Spring Security filter chain flow, JWT authentication filter, and method-level access controls",
                "Spring IoC container lifecycle, Bean instantiation phases, and circular dependency resolution"
            ),
            relatedTrackId = "spring_boot_interview"
        ),
        VideoMockInterview(
            id = "vm_spring_boot_04",
            topicId = "spring_boot",
            title = "Intro",
            channelName = "Code Decode",
            youtubeVideoId = "EYgIvuenaiY",
            duration = "18:46 min",
            difficulty = "Architect",
            description = "Real-world technical mock interview covering Spring Boot concepts, architectural trade-offs, and live candidate evaluation with Code Decode.",
            keyTakeaways = listOf(
                "Spring Security filter chain flow, JWT authentication filter, and method-level access controls",
                "Spring IoC container lifecycle, Bean instantiation phases, and circular dependency resolution",
                "Spring Boot auto-configuration mechanism, conditional beans, and starter architecture"
            ),
            relatedTrackId = "spring_boot_interview"
        ),
        VideoMockInterview(
            id = "vm_spring_boot_05",
            topicId = "spring_boot",
            title = "Intro u0026 Project Discussion",
            channelName = "JAVA INTERVIEW BUDDY",
            youtubeVideoId = "LFhQNpKOpcc",
            duration = "32:59 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Spring Boot concepts, architectural trade-offs, and live candidate evaluation with JAVA INTERVIEW BUDDY.",
            keyTakeaways = listOf(
                "Spring IoC container lifecycle, Bean instantiation phases, and circular dependency resolution",
                "Spring Boot auto-configuration mechanism, conditional beans, and starter architecture",
                "Spring Data JPA dirty checking, N+1 query problem mitigation with entity graphs, and batch inserts"
            ),
            relatedTrackId = "spring_boot_interview"
        ),
        VideoMockInterview(
            id = "vm_spring_boot_06",
            topicId = "spring_boot",
            title = "Intro",
            channelName = "The Curious Coder",
            youtubeVideoId = "Dw4NyiDiXDQ",
            duration = "1:00:50 min",
            difficulty = "Mid-Level",
            description = "Real-world technical mock interview covering Spring Boot concepts, architectural trade-offs, and live candidate evaluation with The Curious Coder.",
            keyTakeaways = listOf(
                "Spring Boot auto-configuration mechanism, conditional beans, and starter architecture",
                "Spring Data JPA dirty checking, N+1 query problem mitigation with entity graphs, and batch inserts",
                "Spring Security filter chain flow, JWT authentication filter, and method-level access controls"
            ),
            relatedTrackId = "spring_boot_interview"
        ),
        VideoMockInterview(
            id = "vm_spring_boot_07",
            topicId = "spring_boot",
            title = "Introduction to Java Interview Questions",
            channelName = "Intellipaat",
            youtubeVideoId = "4Ib9amXl4gI",
            duration = "37:15 min",
            difficulty = "Staff",
            description = "Real-world technical mock interview covering Spring Boot concepts, architectural trade-offs, and live candidate evaluation with Intellipaat.",
            keyTakeaways = listOf(
                "Spring Data JPA dirty checking, N+1 query problem mitigation with entity graphs, and batch inserts",
                "Spring Security filter chain flow, JWT authentication filter, and method-level access controls",
                "Spring IoC container lifecycle, Bean instantiation phases, and circular dependency resolution"
            ),
            relatedTrackId = "spring_boot_interview"
        ),
        VideoMockInterview(
            id = "vm_spring_boot_08",
            topicId = "spring_boot",
            title = "Table of Contents",
            channelName = "Anuj Kumar Sharma",
            youtubeVideoId = "tHDrxMklmPQ",
            duration = "1:32:42 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Spring Boot concepts, architectural trade-offs, and live candidate evaluation with Anuj Kumar Sharma.",
            keyTakeaways = listOf(
                "Spring Security filter chain flow, JWT authentication filter, and method-level access controls",
                "Spring IoC container lifecycle, Bean instantiation phases, and circular dependency resolution",
                "Spring Boot auto-configuration mechanism, conditional beans, and starter architecture"
            ),
            relatedTrackId = "spring_boot_interview"
        ),
        VideoMockInterview(
            id = "vm_spring_boot_09",
            topicId = "spring_boot",
            title = "Introduction",
            channelName = "Selenium Express",
            youtubeVideoId = "9grEmpRfK0Y",
            duration = "62:55:08 min",
            difficulty = "Lead",
            description = "Real-world technical mock interview covering Spring Boot concepts, architectural trade-offs, and live candidate evaluation with Selenium Express.",
            keyTakeaways = listOf(
                "Spring IoC container lifecycle, Bean instantiation phases, and circular dependency resolution",
                "Spring Boot auto-configuration mechanism, conditional beans, and starter architecture",
                "Spring Data JPA dirty checking, N+1 query problem mitigation with entity graphs, and batch inserts"
            ),
            relatedTrackId = "spring_boot_interview"
        ),
        VideoMockInterview(
            id = "vm_spring_boot_10",
            topicId = "spring_boot",
            title = "Welcome",
            channelName = "Telusko",
            youtubeVideoId = "q6z_UCBM5Ek",
            duration = "6:06:44 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Spring Boot concepts, architectural trade-offs, and live candidate evaluation with Telusko.",
            keyTakeaways = listOf(
                "Spring Boot auto-configuration mechanism, conditional beans, and starter architecture",
                "Spring Data JPA dirty checking, N+1 query problem mitigation with entity graphs, and batch inserts",
                "Spring Security filter chain flow, JWT authentication filter, and method-level access controls"
            ),
            relatedTrackId = "spring_boot_interview"
        ),
    )

    // --- Microservices Mock Interviews (10 real verified long-form videos) ---
    private val microservicesVideos = listOf(
        VideoMockInterview(
            id = "vm_microservices_01",
            topicId = "microservices",
            title = "Introduction",
            channelName = "GenZ Career",
            youtubeVideoId = "xH9bB7oluKc",
            duration = "39:20 min",
            difficulty = "Mid-Level",
            description = "Real-world technical mock interview covering Microservices concepts, architectural trade-offs, and live candidate evaluation with GenZ Career.",
            keyTakeaways = listOf(
                "Distributed data management: Saga pattern (orchestration vs choreography) and outbox pattern",
                "Service discovery, client-side load balancing, and API Gateway pattern (rate limiting, auth offloading)",
                "Fault tolerance and resilience: Circuit Breakers (Resilience4j), retries, and exponential backoffs"
            ),
            relatedTrackId = "microservices_interview"
        ),
        VideoMockInterview(
            id = "vm_microservices_02",
            topicId = "microservices",
            title = "Microservices Developer Interview",
            channelName = "Code Decode",
            youtubeVideoId = "eNwbZz8PGDc",
            duration = "18:51 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Microservices concepts, architectural trade-offs, and live candidate evaluation with Code Decode.",
            keyTakeaways = listOf(
                "Service discovery, client-side load balancing, and API Gateway pattern (rate limiting, auth offloading)",
                "Fault tolerance and resilience: Circuit Breakers (Resilience4j), retries, and exponential backoffs",
                "Event-driven messaging: Kafka consumer groups, offset management, and idempotency guarantees"
            ),
            relatedTrackId = "microservices_interview"
        ),
        VideoMockInterview(
            id = "vm_microservices_03",
            topicId = "microservices",
            title = "Intro",
            channelName = "theSeniorDev",
            youtubeVideoId = "AGqbLgEQGaA",
            duration = "35:34 min",
            difficulty = "Lead",
            description = "Real-world technical mock interview covering Microservices concepts, architectural trade-offs, and live candidate evaluation with theSeniorDev.",
            keyTakeaways = listOf(
                "Fault tolerance and resilience: Circuit Breakers (Resilience4j), retries, and exponential backoffs",
                "Event-driven messaging: Kafka consumer groups, offset management, and idempotency guarantees",
                "Distributed data management: Saga pattern (orchestration vs choreography) and outbox pattern"
            ),
            relatedTrackId = "microservices_interview"
        ),
        VideoMockInterview(
            id = "vm_microservices_04",
            topicId = "microservices",
            title = "Into",
            channelName = "Java Guides",
            youtubeVideoId = "u5ExQ12yNbM",
            duration = "41:16 min",
            difficulty = "Architect",
            description = "Real-world technical mock interview covering Microservices concepts, architectural trade-offs, and live candidate evaluation with Java Guides.",
            keyTakeaways = listOf(
                "Event-driven messaging: Kafka consumer groups, offset management, and idempotency guarantees",
                "Distributed data management: Saga pattern (orchestration vs choreography) and outbox pattern",
                "Service discovery, client-side load balancing, and API Gateway pattern (rate limiting, auth offloading)"
            ),
            relatedTrackId = "microservices_interview"
        ),
        VideoMockInterview(
            id = "vm_microservices_05",
            topicId = "microservices",
            title = "Intro",
            channelName = "GenZ Career",
            youtubeVideoId = "abPtff4wg8k",
            duration = "1:15:45 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Microservices concepts, architectural trade-offs, and live candidate evaluation with GenZ Career.",
            keyTakeaways = listOf(
                "Distributed data management: Saga pattern (orchestration vs choreography) and outbox pattern",
                "Service discovery, client-side load balancing, and API Gateway pattern (rate limiting, auth offloading)",
                "Fault tolerance and resilience: Circuit Breakers (Resilience4j), retries, and exponential backoffs"
            ),
            relatedTrackId = "microservices_interview"
        ),
        VideoMockInterview(
            id = "vm_microservices_06",
            topicId = "microservices",
            title = "Introduction",
            channelName = "Code Decode",
            youtubeVideoId = "MNvBi5wxHYc",
            duration = "47:34 min",
            difficulty = "Mid-Level",
            description = "Real-world technical mock interview covering Microservices concepts, architectural trade-offs, and live candidate evaluation with Code Decode.",
            keyTakeaways = listOf(
                "Service discovery, client-side load balancing, and API Gateway pattern (rate limiting, auth offloading)",
                "Fault tolerance and resilience: Circuit Breakers (Resilience4j), retries, and exponential backoffs",
                "Event-driven messaging: Kafka consumer groups, offset management, and idempotency guarantees"
            ),
            relatedTrackId = "microservices_interview"
        ),
        VideoMockInterview(
            id = "vm_microservices_07",
            topicId = "microservices",
            title = "Intro",
            channelName = "GenZ Career",
            youtubeVideoId = "XilRv9wJhzc",
            duration = "38:39 min",
            difficulty = "Staff",
            description = "Real-world technical mock interview covering Microservices concepts, architectural trade-offs, and live candidate evaluation with GenZ Career.",
            keyTakeaways = listOf(
                "Fault tolerance and resilience: Circuit Breakers (Resilience4j), retries, and exponential backoffs",
                "Event-driven messaging: Kafka consumer groups, offset management, and idempotency guarantees",
                "Distributed data management: Saga pattern (orchestration vs choreography) and outbox pattern"
            ),
            relatedTrackId = "microservices_interview"
        ),
        VideoMockInterview(
            id = "vm_microservices_08",
            topicId = "microservices",
            title = "Introduction",
            channelName = "MindMajix",
            youtubeVideoId = "wmawYODmQU0",
            duration = "39:20 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Microservices concepts, architectural trade-offs, and live candidate evaluation with MindMajix.",
            keyTakeaways = listOf(
                "Event-driven messaging: Kafka consumer groups, offset management, and idempotency guarantees",
                "Distributed data management: Saga pattern (orchestration vs choreography) and outbox pattern",
                "Service discovery, client-side load balancing, and API Gateway pattern (rate limiting, auth offloading)"
            ),
            relatedTrackId = "microservices_interview"
        ),
        VideoMockInterview(
            id = "vm_microservices_09",
            topicId = "microservices",
            title = "Introduction",
            channelName = "Java Guides",
            youtubeVideoId = "Ii4PJTkORcU",
            duration = "38:39 min",
            difficulty = "Lead",
            description = "Real-world technical mock interview covering Microservices concepts, architectural trade-offs, and live candidate evaluation with Java Guides.",
            keyTakeaways = listOf(
                "Distributed data management: Saga pattern (orchestration vs choreography) and outbox pattern",
                "Service discovery, client-side load balancing, and API Gateway pattern (rate limiting, auth offloading)",
                "Fault tolerance and resilience: Circuit Breakers (Resilience4j), retries, and exponential backoffs"
            ),
            relatedTrackId = "microservices_interview"
        ),
        VideoMockInterview(
            id = "vm_microservices_10",
            topicId = "microservices",
            title = "Intro",
            channelName = "Mock Interviews",
            youtubeVideoId = "awLkgVEP3p8",
            duration = "12:26 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Microservices concepts, architectural trade-offs, and live candidate evaluation with Mock Interviews.",
            keyTakeaways = listOf(
                "Service discovery, client-side load balancing, and API Gateway pattern (rate limiting, auth offloading)",
                "Fault tolerance and resilience: Circuit Breakers (Resilience4j), retries, and exponential backoffs",
                "Event-driven messaging: Kafka consumer groups, offset management, and idempotency guarantees"
            ),
            relatedTrackId = "microservices_interview"
        ),
    )

    // --- Full Stack Mock Interviews (10 real verified long-form videos) ---
    private val fullstackVideos = listOf(
        VideoMockInterview(
            id = "vm_full_stack_01",
            topicId = "full_stack",
            title = "Introduction to Full Stack Developer Interview Questions for Freshers u0026 Experienced",
            channelName = "Intellipaat",
            youtubeVideoId = "l5g38haVsJk",
            duration = "25:26 min",
            difficulty = "Mid-Level",
            description = "Real-world technical mock interview covering Full Stack concepts, architectural trade-offs, and live candidate evaluation with Intellipaat.",
            keyTakeaways = listOf(
                "End-to-end full stack architecture: frontend state reconciliation and backend RESTful/GraphQL contracts",
                "Client performance: bundle splitting, optimistic UI updates, and server-side rendering trade-offs",
                "Securing full stack applications: CORS, CSRF tokens, secure cookies, and input sanitization"
            ),
            relatedTrackId = "full_stack_interview"
        ),
        VideoMockInterview(
            id = "vm_full_stack_02",
            topicId = "full_stack",
            title = "SQL Injection Attack",
            channelName = "ReactJS Developer Interview Series ",
            youtubeVideoId = "l2f3xVvZGLA",
            duration = "1:14:29 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Full Stack concepts, architectural trade-offs, and live candidate evaluation with ReactJS Developer Interview Series .",
            keyTakeaways = listOf(
                "Client performance: bundle splitting, optimistic UI updates, and server-side rendering trade-offs",
                "Securing full stack applications: CORS, CSRF tokens, secure cookies, and input sanitization",
                "Database integration and caching strategies: Redis caching layers and relational transactions"
            ),
            relatedTrackId = "full_stack_interview"
        ),
        VideoMockInterview(
            id = "vm_full_stack_03",
            topicId = "full_stack",
            title = "Intro",
            channelName = "UNIQ Technologies",
            youtubeVideoId = "bF6gLekjU4E",
            duration = "1:34:48 min",
            difficulty = "Lead",
            description = "Real-world technical mock interview covering Full Stack concepts, architectural trade-offs, and live candidate evaluation with UNIQ Technologies.",
            keyTakeaways = listOf(
                "Securing full stack applications: CORS, CSRF tokens, secure cookies, and input sanitization",
                "Database integration and caching strategies: Redis caching layers and relational transactions",
                "End-to-end full stack architecture: frontend state reconciliation and backend RESTful/GraphQL contracts"
            ),
            relatedTrackId = "full_stack_interview"
        ),
        VideoMockInterview(
            id = "vm_full_stack_04",
            topicId = "full_stack",
            title = "SQL Injection Attack",
            channelName = "Nisha Singla",
            youtubeVideoId = "hw-c1vUMkRA",
            duration = "41:16 min",
            difficulty = "Architect",
            description = "Real-world technical mock interview covering Full Stack concepts, architectural trade-offs, and live candidate evaluation with Nisha Singla.",
            keyTakeaways = listOf(
                "Database integration and caching strategies: Redis caching layers and relational transactions",
                "End-to-end full stack architecture: frontend state reconciliation and backend RESTful/GraphQL contracts",
                "Client performance: bundle splitting, optimistic UI updates, and server-side rendering trade-offs"
            ),
            relatedTrackId = "full_stack_interview"
        ),
        VideoMockInterview(
            id = "vm_full_stack_05",
            topicId = "full_stack",
            title = "Introduction – Frontend Mock Interview Begins",
            channelName = "GenZ Career",
            youtubeVideoId = "abPtff4wg8k",
            duration = "1:14:01 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Full Stack concepts, architectural trade-offs, and live candidate evaluation with GenZ Career.",
            keyTakeaways = listOf(
                "End-to-end full stack architecture: frontend state reconciliation and backend RESTful/GraphQL contracts",
                "Client performance: bundle splitting, optimistic UI updates, and server-side rendering trade-offs",
                "Securing full stack applications: CORS, CSRF tokens, secure cookies, and input sanitization"
            ),
            relatedTrackId = "full_stack_interview"
        ),
        VideoMockInterview(
            id = "vm_full_stack_06",
            topicId = "full_stack",
            title = "Introduction",
            channelName = "Code Decode",
            youtubeVideoId = "X2bPVTRruUM",
            duration = "59:15 min",
            difficulty = "Mid-Level",
            description = "Real-world technical mock interview covering Full Stack concepts, architectural trade-offs, and live candidate evaluation with Code Decode.",
            keyTakeaways = listOf(
                "Client performance: bundle splitting, optimistic UI updates, and server-side rendering trade-offs",
                "Securing full stack applications: CORS, CSRF tokens, secure cookies, and input sanitization",
                "Database integration and caching strategies: Redis caching layers and relational transactions"
            ),
            relatedTrackId = "full_stack_interview"
        ),
        VideoMockInterview(
            id = "vm_full_stack_07",
            topicId = "full_stack",
            title = "Intro",
            channelName = "Code Decode",
            youtubeVideoId = "PHIQJeKzMCU",
            duration = "30:07 min",
            difficulty = "Staff",
            description = "Real-world technical mock interview covering Full Stack concepts, architectural trade-offs, and live candidate evaluation with Code Decode.",
            keyTakeaways = listOf(
                "Securing full stack applications: CORS, CSRF tokens, secure cookies, and input sanitization",
                "Database integration and caching strategies: Redis caching layers and relational transactions",
                "End-to-end full stack architecture: frontend state reconciliation and backend RESTful/GraphQL contracts"
            ),
            relatedTrackId = "full_stack_interview"
        ),
        VideoMockInterview(
            id = "vm_full_stack_08",
            topicId = "full_stack",
            title = "Introduction",
            channelName = "GenZ Career",
            youtubeVideoId = "JyS2FnrE91Q",
            duration = "32:59 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Full Stack concepts, architectural trade-offs, and live candidate evaluation with GenZ Career.",
            keyTakeaways = listOf(
                "Database integration and caching strategies: Redis caching layers and relational transactions",
                "End-to-end full stack architecture: frontend state reconciliation and backend RESTful/GraphQL contracts",
                "Client performance: bundle splitting, optimistic UI updates, and server-side rendering trade-offs"
            ),
            relatedTrackId = "full_stack_interview"
        ),
        VideoMockInterview(
            id = "vm_full_stack_09",
            topicId = "full_stack",
            title = "Introduction to Full Stack Developer Interview Questions for Freshers u0026 Experienced",
            channelName = "GALTech Learning Hub",
            youtubeVideoId = "f2KFCh0V764",
            duration = "1:14:29 min",
            difficulty = "Lead",
            description = "Real-world technical mock interview covering Full Stack concepts, architectural trade-offs, and live candidate evaluation with GALTech Learning Hub.",
            keyTakeaways = listOf(
                "End-to-end full stack architecture: frontend state reconciliation and backend RESTful/GraphQL contracts",
                "Client performance: bundle splitting, optimistic UI updates, and server-side rendering trade-offs",
                "Securing full stack applications: CORS, CSRF tokens, secure cookies, and input sanitization"
            ),
            relatedTrackId = "full_stack_interview"
        ),
        VideoMockInterview(
            id = "vm_full_stack_10",
            topicId = "full_stack",
            title = "SQL Injection Attack",
            channelName = "theSeniorDev",
            youtubeVideoId = "sgckyASEMdI",
            duration = "13:37 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Full Stack concepts, architectural trade-offs, and live candidate evaluation with theSeniorDev.",
            keyTakeaways = listOf(
                "Client performance: bundle splitting, optimistic UI updates, and server-side rendering trade-offs",
                "Securing full stack applications: CORS, CSRF tokens, secure cookies, and input sanitization",
                "Database integration and caching strategies: Redis caching layers and relational transactions"
            ),
            relatedTrackId = "full_stack_interview"
        ),
    )

    // --- HLD Mock Interviews (10 real verified long-form videos) ---
    private val hldVideos = listOf(
        VideoMockInterview(
            id = "vm_hld_01",
            topicId = "hld",
            title = "Introduction",
            channelName = "NextWork",
            youtubeVideoId = "qtkSifj_vus",
            duration = "48:39 min",
            difficulty = "Mid-Level",
            description = "Real-world technical mock interview covering HLD concepts, architectural trade-offs, and live candidate evaluation with NextWork.",
            keyTakeaways = listOf(
                "Distributed system fundamentals: CAP and PACELC theorems, consistency models, and latency trade-offs",
                "Scalable database architectures: horizontal sharding, consistent hashing, and read replicas",
                "Global caching hierarchies: CDN caching, Redis/Memcached look-aside, and cache invalidation"
            ),
            relatedTrackId = "hld_interview"
        ),
        VideoMockInterview(
            id = "vm_hld_02",
            topicId = "hld",
            title = "Intro",
            channelName = "Anubhav Sethi",
            youtubeVideoId = "QBHTbtWSECg",
            duration = "2:18:46 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering HLD concepts, architectural trade-offs, and live candidate evaluation with Anubhav Sethi.",
            keyTakeaways = listOf(
                "Scalable database architectures: horizontal sharding, consistent hashing, and read replicas",
                "Global caching hierarchies: CDN caching, Redis/Memcached look-aside, and cache invalidation",
                "High availability and fault tolerance: multi-region active-active deployments and failure domain isolation"
            ),
            relatedTrackId = "hld_interview"
        ),
        VideoMockInterview(
            id = "vm_hld_03",
            topicId = "hld",
            title = "The 6-Step Framework",
            channelName = "Afaque Ahmad",
            youtubeVideoId = "r58Cf_kc_bY",
            duration = "26:04 min",
            difficulty = "Lead",
            description = "Real-world technical mock interview covering HLD concepts, architectural trade-offs, and live candidate evaluation with Afaque Ahmad.",
            keyTakeaways = listOf(
                "Global caching hierarchies: CDN caching, Redis/Memcached look-aside, and cache invalidation",
                "High availability and fault tolerance: multi-region active-active deployments and failure domain isolation",
                "Distributed system fundamentals: CAP and PACELC theorems, consistency models, and latency trade-offs"
            ),
            relatedTrackId = "hld_interview"
        ),
        VideoMockInterview(
            id = "vm_hld_04",
            topicId = "hld",
            title = "Intro",
            channelName = "NeetCode",
            youtubeVideoId = "jPKTo1iGQiE",
            duration = "35:02 min",
            difficulty = "Architect",
            description = "Real-world technical mock interview covering HLD concepts, architectural trade-offs, and live candidate evaluation with NeetCode.",
            keyTakeaways = listOf(
                "High availability and fault tolerance: multi-region active-active deployments and failure domain isolation",
                "Distributed system fundamentals: CAP and PACELC theorems, consistency models, and latency trade-offs",
                "Scalable database architectures: horizontal sharding, consistent hashing, and read replicas"
            ),
            relatedTrackId = "hld_interview"
        ),
        VideoMockInterview(
            id = "vm_hld_05",
            topicId = "hld",
            title = "Intro",
            channelName = "Keerti Purswani",
            youtubeVideoId = "l3AOubKFB1U",
            duration = "52:17 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering HLD concepts, architectural trade-offs, and live candidate evaluation with Keerti Purswani.",
            keyTakeaways = listOf(
                "Distributed system fundamentals: CAP and PACELC theorems, consistency models, and latency trade-offs",
                "Scalable database architectures: horizontal sharding, consistent hashing, and read replicas",
                "Global caching hierarchies: CDN caching, Redis/Memcached look-aside, and cache invalidation"
            ),
            relatedTrackId = "hld_interview"
        ),
        VideoMockInterview(
            id = "vm_hld_06",
            topicId = "hld",
            title = "Introduction",
            channelName = "Sanket Singh",
            youtubeVideoId = "7LwLLdWgPq4",
            duration = "33:11 min",
            difficulty = "Mid-Level",
            description = "Real-world technical mock interview covering HLD concepts, architectural trade-offs, and live candidate evaluation with Sanket Singh.",
            keyTakeaways = listOf(
                "Scalable database architectures: horizontal sharding, consistent hashing, and read replicas",
                "Global caching hierarchies: CDN caching, Redis/Memcached look-aside, and cache invalidation",
                "High availability and fault tolerance: multi-region active-active deployments and failure domain isolation"
            ),
            relatedTrackId = "hld_interview"
        ),
        VideoMockInterview(
            id = "vm_hld_07",
            topicId = "hld",
            title = "Introduction",
            channelName = "Aced (formerly Exponent)",
            youtubeVideoId = "Z-0g_aJL5Fw",
            duration = "53:38 min",
            difficulty = "Staff",
            description = "Real-world technical mock interview covering HLD concepts, architectural trade-offs, and live candidate evaluation with Aced (formerly Exponent).",
            keyTakeaways = listOf(
                "Global caching hierarchies: CDN caching, Redis/Memcached look-aside, and cache invalidation",
                "High availability and fault tolerance: multi-region active-active deployments and failure domain isolation",
                "Distributed system fundamentals: CAP and PACELC theorems, consistency models, and latency trade-offs"
            ),
            relatedTrackId = "hld_interview"
        ),
        VideoMockInterview(
            id = "vm_hld_08",
            topicId = "hld",
            title = "Introduction",
            channelName = "freeCodeCamp.org",
            youtubeVideoId = "F2FmTdLtb_4",
            duration = "5:05:49 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering HLD concepts, architectural trade-offs, and live candidate evaluation with freeCodeCamp.org.",
            keyTakeaways = listOf(
                "High availability and fault tolerance: multi-region active-active deployments and failure domain isolation",
                "Distributed system fundamentals: CAP and PACELC theorems, consistency models, and latency trade-offs",
                "Scalable database architectures: horizontal sharding, consistent hashing, and read replicas"
            ),
            relatedTrackId = "hld_interview"
        ),
        VideoMockInterview(
            id = "vm_hld_09",
            topicId = "hld",
            title = "Introduction",
            channelName = "Telusko",
            youtubeVideoId = "Vnm-ycSfJx4",
            duration = "42:54 min",
            difficulty = "Lead",
            description = "Real-world technical mock interview covering HLD concepts, architectural trade-offs, and live candidate evaluation with Telusko.",
            keyTakeaways = listOf(
                "Distributed system fundamentals: CAP and PACELC theorems, consistency models, and latency trade-offs",
                "Scalable database architectures: horizontal sharding, consistent hashing, and read replicas",
                "Global caching hierarchies: CDN caching, Redis/Memcached look-aside, and cache invalidation"
            ),
            relatedTrackId = "hld_interview"
        ),
        VideoMockInterview(
            id = "vm_hld_10",
            topicId = "hld",
            title = "Introduction",
            channelName = "MindMajix",
            youtubeVideoId = "PTJOECahJTc",
            duration = "1:31:38 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering HLD concepts, architectural trade-offs, and live candidate evaluation with MindMajix.",
            keyTakeaways = listOf(
                "Scalable database architectures: horizontal sharding, consistent hashing, and read replicas",
                "Global caching hierarchies: CDN caching, Redis/Memcached look-aside, and cache invalidation",
                "High availability and fault tolerance: multi-region active-active deployments and failure domain isolation"
            ),
            relatedTrackId = "hld_interview"
        ),
    )

    // --- LLD Mock Interviews (10 real verified long-form videos) ---
    private val lldVideos = listOf(
        VideoMockInterview(
            id = "vm_lld_01",
            topicId = "lld",
            title = "Intro",
            channelName = "Keerti Purswani",
            youtubeVideoId = "NrAXmQX-XI8",
            duration = "48:39 min",
            difficulty = "Mid-Level",
            description = "Real-world technical mock interview covering LLD concepts, architectural trade-offs, and live candidate evaluation with Keerti Purswani.",
            keyTakeaways = listOf(
                "Object-oriented design: SOLID principles applied to real enterprise domain models",
                "GoF design patterns: Strategy, Factory, Observer, Decorator, and Builder in production codebases",
                "Concurrency-safe domain design: immutable value objects, locks, and thread-safe states"
            ),
            relatedTrackId = "lld_interview"
        ),
        VideoMockInterview(
            id = "vm_lld_02",
            topicId = "lld",
            title = "Intro",
            channelName = "Anubhav Sethi",
            youtubeVideoId = "QBHTbtWSECg",
            duration = "50:56 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering LLD concepts, architectural trade-offs, and live candidate evaluation with Anubhav Sethi.",
            keyTakeaways = listOf(
                "GoF design patterns: Strategy, Factory, Observer, Decorator, and Builder in production codebases",
                "Concurrency-safe domain design: immutable value objects, locks, and thread-safe states",
                "Extensible class hierarchies, interface segregation, and clean architecture boundaries"
            ),
            relatedTrackId = "lld_interview"
        ),
        VideoMockInterview(
            id = "vm_lld_03",
            topicId = "lld",
            title = "Intro",
            channelName = "Himanshu Singal",
            youtubeVideoId = "I79tUtVxWmA",
            duration = "48:24 min",
            difficulty = "Lead",
            description = "Real-world technical mock interview covering LLD concepts, architectural trade-offs, and live candidate evaluation with Himanshu Singal.",
            keyTakeaways = listOf(
                "Concurrency-safe domain design: immutable value objects, locks, and thread-safe states",
                "Extensible class hierarchies, interface segregation, and clean architecture boundaries",
                "Object-oriented design: SOLID principles applied to real enterprise domain models"
            ),
            relatedTrackId = "lld_interview"
        ),
        VideoMockInterview(
            id = "vm_lld_04",
            topicId = "lld",
            title = "Intro",
            channelName = "Keerti Purswani",
            youtubeVideoId = "J-4UQ_WpMtc",
            duration = "59:37 min",
            difficulty = "Architect",
            description = "Real-world technical mock interview covering LLD concepts, architectural trade-offs, and live candidate evaluation with Keerti Purswani.",
            keyTakeaways = listOf(
                "Extensible class hierarchies, interface segregation, and clean architecture boundaries",
                "Object-oriented design: SOLID principles applied to real enterprise domain models",
                "GoF design patterns: Strategy, Factory, Observer, Decorator, and Builder in production codebases"
            ),
            relatedTrackId = "lld_interview"
        ),
        VideoMockInterview(
            id = "vm_lld_05",
            topicId = "lld",
            title = "Introduction",
            channelName = "Chirag Goel",
            youtubeVideoId = "pBG3BAsWCug",
            duration = "1:01:27 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering LLD concepts, architectural trade-offs, and live candidate evaluation with Chirag Goel.",
            keyTakeaways = listOf(
                "Object-oriented design: SOLID principles applied to real enterprise domain models",
                "GoF design patterns: Strategy, Factory, Observer, Decorator, and Builder in production codebases",
                "Concurrency-safe domain design: immutable value objects, locks, and thread-safe states"
            ),
            relatedTrackId = "lld_interview"
        ),
        VideoMockInterview(
            id = "vm_lld_06",
            topicId = "lld",
            title = "Intro",
            channelName = "Ashish Pratap Singh",
            youtubeVideoId = "OhCp6ppX6bg",
            duration = "37:23 min",
            difficulty = "Mid-Level",
            description = "Real-world technical mock interview covering LLD concepts, architectural trade-offs, and live candidate evaluation with Ashish Pratap Singh.",
            keyTakeaways = listOf(
                "GoF design patterns: Strategy, Factory, Observer, Decorator, and Builder in production codebases",
                "Concurrency-safe domain design: immutable value objects, locks, and thread-safe states",
                "Extensible class hierarchies, interface segregation, and clean architecture boundaries"
            ),
            relatedTrackId = "lld_interview"
        ),
        VideoMockInterview(
            id = "vm_lld_07",
            topicId = "lld",
            title = "Intro",
            channelName = "Concept && Coding - by Shrayansh",
            youtubeVideoId = "MRx40JVmmF4",
            duration = "59:50 min",
            difficulty = "Staff",
            description = "Real-world technical mock interview covering LLD concepts, architectural trade-offs, and live candidate evaluation with Concept && Coding - by Shrayansh.",
            keyTakeaways = listOf(
                "Concurrency-safe domain design: immutable value objects, locks, and thread-safe states",
                "Extensible class hierarchies, interface segregation, and clean architecture boundaries",
                "Object-oriented design: SOLID principles applied to real enterprise domain models"
            ),
            relatedTrackId = "lld_interview"
        ),
        VideoMockInterview(
            id = "vm_lld_08",
            topicId = "lld",
            title = "Intro",
            channelName = "Hello Interview",
            youtubeVideoId = "9UI4ikKP3Ws",
            duration = "10:18 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering LLD concepts, architectural trade-offs, and live candidate evaluation with Hello Interview.",
            keyTakeaways = listOf(
                "Extensible class hierarchies, interface segregation, and clean architecture boundaries",
                "Object-oriented design: SOLID principles applied to real enterprise domain models",
                "GoF design patterns: Strategy, Factory, Observer, Decorator, and Builder in production codebases"
            ),
            relatedTrackId = "lld_interview"
        ),
        VideoMockInterview(
            id = "vm_lld_09",
            topicId = "lld",
            title = "Intro",
            channelName = "AlgoMonster",
            youtubeVideoId = "nwioCA5nrYc",
            duration = "1:01:16 min",
            difficulty = "Lead",
            description = "Real-world technical mock interview covering LLD concepts, architectural trade-offs, and live candidate evaluation with AlgoMonster.",
            keyTakeaways = listOf(
                "Object-oriented design: SOLID principles applied to real enterprise domain models",
                "GoF design patterns: Strategy, Factory, Observer, Decorator, and Builder in production codebases",
                "Concurrency-safe domain design: immutable value objects, locks, and thread-safe states"
            ),
            relatedTrackId = "lld_interview"
        ),
        VideoMockInterview(
            id = "vm_lld_10",
            topicId = "lld",
            title = "Intro",
            channelName = "Shubh Patel",
            youtubeVideoId = "kzfrbkDcYjA",
            duration = "1:11:28 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering LLD concepts, architectural trade-offs, and live candidate evaluation with Shubh Patel.",
            keyTakeaways = listOf(
                "GoF design patterns: Strategy, Factory, Observer, Decorator, and Builder in production codebases",
                "Concurrency-safe domain design: immutable value objects, locks, and thread-safe states",
                "Extensible class hierarchies, interface segregation, and clean architecture boundaries"
            ),
            relatedTrackId = "lld_interview"
        ),
    )

    // --- System Design Mock Interviews (10 real verified long-form videos) ---
    private val systemdesignVideos = listOf(
        VideoMockInterview(
            id = "vm_system_design_01",
            topicId = "system_design",
            title = "Intro",
            channelName = "Gaurav Sen",
            youtubeVideoId = "SqcXvc3ZmRU",
            duration = "48:39 min",
            difficulty = "Mid-Level",
            description = "Real-world technical mock interview covering System Design concepts, architectural trade-offs, and live candidate evaluation with Gaurav Sen.",
            keyTakeaways = listOf(
                "Designing large-scale systems: URL shortener, distributed message queue, and rate limiters",
                "Capacity estimations: calculating QPS, storage requirements, network bandwidth, and memory footprints",
                "Data pipeline architectures: lambda/kappa architectures, stream processing, and event sourcing"
            ),
            relatedTrackId = "system_design_interview"
        ),
        VideoMockInterview(
            id = "vm_system_design_02",
            topicId = "system_design",
            title = "Intro",
            channelName = "Anubhav Sethi",
            youtubeVideoId = "QBHTbtWSECg",
            duration = "45:23 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering System Design concepts, architectural trade-offs, and live candidate evaluation with Anubhav Sethi.",
            keyTakeaways = listOf(
                "Capacity estimations: calculating QPS, storage requirements, network bandwidth, and memory footprints",
                "Data pipeline architectures: lambda/kappa architectures, stream processing, and event sourcing",
                "Trade-off evaluations: SQL vs NoSQL (Document, Columnar, Key-Value) based on access patterns"
            ),
            relatedTrackId = "system_design_interview"
        ),
        VideoMockInterview(
            id = "vm_system_design_03",
            topicId = "system_design",
            title = "Introduction",
            channelName = "sudoCODE",
            youtubeVideoId = "2BWr0fsDSs0",
            duration = "37:10 min",
            difficulty = "Lead",
            description = "Real-world technical mock interview covering System Design concepts, architectural trade-offs, and live candidate evaluation with sudoCODE.",
            keyTakeaways = listOf(
                "Data pipeline architectures: lambda/kappa architectures, stream processing, and event sourcing",
                "Trade-off evaluations: SQL vs NoSQL (Document, Columnar, Key-Value) based on access patterns",
                "Designing large-scale systems: URL shortener, distributed message queue, and rate limiters"
            ),
            relatedTrackId = "system_design_interview"
        ),
        VideoMockInterview(
            id = "vm_system_design_04",
            topicId = "system_design",
            title = "Introduction",
            channelName = "Gaurav Sen",
            youtubeVideoId = "QpLy0_c_RXk",
            duration = "26:16 min",
            difficulty = "Architect",
            description = "Real-world technical mock interview covering System Design concepts, architectural trade-offs, and live candidate evaluation with Gaurav Sen.",
            keyTakeaways = listOf(
                "Trade-off evaluations: SQL vs NoSQL (Document, Columnar, Key-Value) based on access patterns",
                "Designing large-scale systems: URL shortener, distributed message queue, and rate limiters",
                "Capacity estimations: calculating QPS, storage requirements, network bandwidth, and memory footprints"
            ),
            relatedTrackId = "system_design_interview"
        ),
        VideoMockInterview(
            id = "vm_system_design_05",
            topicId = "system_design",
            title = "Introduction",
            channelName = "ByteByteGo",
            youtubeVideoId = "i7twT3x5yv8",
            duration = "25:41 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering System Design concepts, architectural trade-offs, and live candidate evaluation with ByteByteGo.",
            keyTakeaways = listOf(
                "Designing large-scale systems: URL shortener, distributed message queue, and rate limiters",
                "Capacity estimations: calculating QPS, storage requirements, network bandwidth, and memory footprints",
                "Data pipeline architectures: lambda/kappa architectures, stream processing, and event sourcing"
            ),
            relatedTrackId = "system_design_interview"
        ),
        VideoMockInterview(
            id = "vm_system_design_06",
            topicId = "system_design",
            title = "Problem Statement",
            channelName = "Gaurav Sen",
            youtubeVideoId = "nHh3DnjnPig",
            duration = "24:29 min",
            difficulty = "Mid-Level",
            description = "Real-world technical mock interview covering System Design concepts, architectural trade-offs, and live candidate evaluation with Gaurav Sen.",
            keyTakeaways = listOf(
                "Capacity estimations: calculating QPS, storage requirements, network bandwidth, and memory footprints",
                "Data pipeline architectures: lambda/kappa architectures, stream processing, and event sourcing",
                "Trade-off evaluations: SQL vs NoSQL (Document, Columnar, Key-Value) based on access patterns"
            ),
            relatedTrackId = "system_design_interview"
        ),
        VideoMockInterview(
            id = "vm_system_design_07",
            topicId = "system_design",
            title = "Introduction",
            channelName = "Gaurav Sen",
            youtubeVideoId = "QmX2NPkJTKg",
            duration = "35:02 min",
            difficulty = "Staff",
            description = "Real-world technical mock interview covering System Design concepts, architectural trade-offs, and live candidate evaluation with Gaurav Sen.",
            keyTakeaways = listOf(
                "Data pipeline architectures: lambda/kappa architectures, stream processing, and event sourcing",
                "Trade-off evaluations: SQL vs NoSQL (Document, Columnar, Key-Value) based on access patterns",
                "Designing large-scale systems: URL shortener, distributed message queue, and rate limiters"
            ),
            relatedTrackId = "system_design_interview"
        ),
        VideoMockInterview(
            id = "vm_system_design_08",
            topicId = "system_design",
            title = "Requirement Setting",
            channelName = "Keerti Purswani",
            youtubeVideoId = "l3AOubKFB1U",
            duration = "53:02 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering System Design concepts, architectural trade-offs, and live candidate evaluation with Keerti Purswani.",
            keyTakeaways = listOf(
                "Trade-off evaluations: SQL vs NoSQL (Document, Columnar, Key-Value) based on access patterns",
                "Designing large-scale systems: URL shortener, distributed message queue, and rate limiters",
                "Capacity estimations: calculating QPS, storage requirements, network bandwidth, and memory footprints"
            ),
            relatedTrackId = "system_design_interview"
        ),
        VideoMockInterview(
            id = "vm_system_design_09",
            topicId = "system_design",
            title = "Intro",
            channelName = "Hello Interview",
            youtubeVideoId = "o8nSXW-B7Rw",
            duration = "48:39 min",
            difficulty = "Lead",
            description = "Real-world technical mock interview covering System Design concepts, architectural trade-offs, and live candidate evaluation with Hello Interview.",
            keyTakeaways = listOf(
                "Designing large-scale systems: URL shortener, distributed message queue, and rate limiters",
                "Capacity estimations: calculating QPS, storage requirements, network bandwidth, and memory footprints",
                "Data pipeline architectures: lambda/kappa architectures, stream processing, and event sourcing"
            ),
            relatedTrackId = "system_design_interview"
        ),
        VideoMockInterview(
            id = "vm_system_design_10",
            topicId = "system_design",
            title = "Introduction",
            channelName = "Aced (formerly Exponent)",
            youtubeVideoId = "SgWb6tWx3S8",
            duration = "33:11 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering System Design concepts, architectural trade-offs, and live candidate evaluation with Aced (formerly Exponent).",
            keyTakeaways = listOf(
                "Capacity estimations: calculating QPS, storage requirements, network bandwidth, and memory footprints",
                "Data pipeline architectures: lambda/kappa architectures, stream processing, and event sourcing",
                "Trade-off evaluations: SQL vs NoSQL (Document, Columnar, Key-Value) based on access patterns"
            ),
            relatedTrackId = "system_design_interview"
        ),
    )

    // --- DevOps Mock Interviews (10 real verified long-form videos) ---
    private val devopsVideos = listOf(
        VideoMockInterview(
            id = "vm_devops_01",
            topicId = "devops",
            title = "Introduction u0026 Meet the Engineers",
            channelName = "Azure Learning",
            youtubeVideoId = "eP-vUikjpWI",
            duration = "27:59 min",
            difficulty = "Mid-Level",
            description = "Real-world technical mock interview covering DevOps concepts, architectural trade-offs, and live candidate evaluation with Azure Learning.",
            keyTakeaways = listOf(
                "Containerization: multi-stage Docker builds, image slimming, and container security best practices",
                "Kubernetes orchestration: Deployments, StatefulSets, Services, Ingress, and Horizontal Pod Autoscalers",
                "CI/CD pipelines: automated testing, GitHub Actions/GitLab CI, and blue-green/canary deployments"
            ),
            relatedTrackId = "devops_interview"
        ),
        VideoMockInterview(
            id = "vm_devops_02",
            topicId = "devops",
            title = "Introductions",
            channelName = "DevOps by Shaik Moulali",
            youtubeVideoId = "6eQy799by2Y",
            duration = "40:14 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering DevOps concepts, architectural trade-offs, and live candidate evaluation with DevOps by Shaik Moulali.",
            keyTakeaways = listOf(
                "Kubernetes orchestration: Deployments, StatefulSets, Services, Ingress, and Horizontal Pod Autoscalers",
                "CI/CD pipelines: automated testing, GitHub Actions/GitLab CI, and blue-green/canary deployments",
                "Infrastructure as Code (IaC): Terraform state management, drift detection, and modular architecture"
            ),
            relatedTrackId = "devops_interview"
        ),
        VideoMockInterview(
            id = "vm_devops_03",
            topicId = "devops",
            title = "Introduction u0026 Meet the Engineers",
            channelName = "DevOps Cloud and AI Labs",
            youtubeVideoId = "YrlQzmClVV4",
            duration = "27:05 min",
            difficulty = "Lead",
            description = "Real-world technical mock interview covering DevOps concepts, architectural trade-offs, and live candidate evaluation with DevOps Cloud and AI Labs.",
            keyTakeaways = listOf(
                "CI/CD pipelines: automated testing, GitHub Actions/GitLab CI, and blue-green/canary deployments",
                "Infrastructure as Code (IaC): Terraform state management, drift detection, and modular architecture",
                "Containerization: multi-stage Docker builds, image slimming, and container security best practices"
            ),
            relatedTrackId = "devops_interview"
        ),
        VideoMockInterview(
            id = "vm_devops_04",
            topicId = "devops",
            title = "Intro to Kubernetes Course",
            channelName = "Cloud Champ",
            youtubeVideoId = "weM2bObz7iI",
            duration = "19:19 min",
            difficulty = "Architect",
            description = "Real-world technical mock interview covering DevOps concepts, architectural trade-offs, and live candidate evaluation with Cloud Champ.",
            keyTakeaways = listOf(
                "Infrastructure as Code (IaC): Terraform state management, drift detection, and modular architecture",
                "Containerization: multi-stage Docker builds, image slimming, and container security best practices",
                "Kubernetes orchestration: Deployments, StatefulSets, Services, Ingress, and Horizontal Pod Autoscalers"
            ),
            relatedTrackId = "devops_interview"
        ),
        VideoMockInterview(
            id = "vm_devops_05",
            topicId = "devops",
            title = "Introduction",
            channelName = "DevOps Cloud and AI Labs",
            youtubeVideoId = "eXe78lkDMz0",
            duration = "1:17:54 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering DevOps concepts, architectural trade-offs, and live candidate evaluation with DevOps Cloud and AI Labs.",
            keyTakeaways = listOf(
                "Containerization: multi-stage Docker builds, image slimming, and container security best practices",
                "Kubernetes orchestration: Deployments, StatefulSets, Services, Ingress, and Horizontal Pod Autoscalers",
                "CI/CD pipelines: automated testing, GitHub Actions/GitLab CI, and blue-green/canary deployments"
            ),
            relatedTrackId = "devops_interview"
        ),
        VideoMockInterview(
            id = "vm_devops_06",
            topicId = "devops",
            title = "Introduction u0026 Meet the Engineers",
            channelName = "Tech with Ajit",
            youtubeVideoId = "y2Xogl5V3aA",
            duration = "35:14 min",
            difficulty = "Mid-Level",
            description = "Real-world technical mock interview covering DevOps concepts, architectural trade-offs, and live candidate evaluation with Tech with Ajit.",
            keyTakeaways = listOf(
                "Kubernetes orchestration: Deployments, StatefulSets, Services, Ingress, and Horizontal Pod Autoscalers",
                "CI/CD pipelines: automated testing, GitHub Actions/GitLab CI, and blue-green/canary deployments",
                "Infrastructure as Code (IaC): Terraform state management, drift detection, and modular architecture"
            ),
            relatedTrackId = "devops_interview"
        ),
        VideoMockInterview(
            id = "vm_devops_07",
            topicId = "devops",
            title = "Introduction u0026 Meet the Engineers",
            channelName = "DevOps Cloud and AI Labs",
            youtubeVideoId = "gJsrzfY-YzY",
            duration = "31:16 min",
            difficulty = "Staff",
            description = "Real-world technical mock interview covering DevOps concepts, architectural trade-offs, and live candidate evaluation with DevOps Cloud and AI Labs.",
            keyTakeaways = listOf(
                "CI/CD pipelines: automated testing, GitHub Actions/GitLab CI, and blue-green/canary deployments",
                "Infrastructure as Code (IaC): Terraform state management, drift detection, and modular architecture",
                "Containerization: multi-stage Docker builds, image slimming, and container security best practices"
            ),
            relatedTrackId = "devops_interview"
        ),
        VideoMockInterview(
            id = "vm_devops_08",
            topicId = "devops",
            title = "Intro",
            channelName = "Praveen Singampalli",
            youtubeVideoId = "QYHKFnJK74w",
            duration = "19:19 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering DevOps concepts, architectural trade-offs, and live candidate evaluation with Praveen Singampalli.",
            keyTakeaways = listOf(
                "Infrastructure as Code (IaC): Terraform state management, drift detection, and modular architecture",
                "Containerization: multi-stage Docker builds, image slimming, and container security best practices",
                "Kubernetes orchestration: Deployments, StatefulSets, Services, Ingress, and Horizontal Pod Autoscalers"
            ),
            relatedTrackId = "devops_interview"
        ),
        VideoMockInterview(
            id = "vm_devops_09",
            topicId = "devops",
            title = "Intro and Course Overview",
            channelName = "DevOps Cloud and AI Labs",
            youtubeVideoId = "La52MP0-ydM",
            duration = "41:35 min",
            difficulty = "Lead",
            description = "Real-world technical mock interview covering DevOps concepts, architectural trade-offs, and live candidate evaluation with DevOps Cloud and AI Labs.",
            keyTakeaways = listOf(
                "Containerization: multi-stage Docker builds, image slimming, and container security best practices",
                "Kubernetes orchestration: Deployments, StatefulSets, Services, Ingress, and Horizontal Pod Autoscalers",
                "CI/CD pipelines: automated testing, GitHub Actions/GitLab CI, and blue-green/canary deployments"
            ),
            relatedTrackId = "devops_interview"
        ),
        VideoMockInterview(
            id = "vm_devops_10",
            topicId = "devops",
            title = "Introduction u0026 Meet the Engineers",
            channelName = "Tech with Ajit",
            youtubeVideoId = "mg1ZqahIpVw",
            duration = "52:40 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering DevOps concepts, architectural trade-offs, and live candidate evaluation with Tech with Ajit.",
            keyTakeaways = listOf(
                "Kubernetes orchestration: Deployments, StatefulSets, Services, Ingress, and Horizontal Pod Autoscalers",
                "CI/CD pipelines: automated testing, GitHub Actions/GitLab CI, and blue-green/canary deployments",
                "Infrastructure as Code (IaC): Terraform state management, drift detection, and modular architecture"
            ),
            relatedTrackId = "devops_interview"
        ),
    )

    // --- SQL & Database Mock Interviews (10 real verified long-form videos) ---
    private val sqlVideos = listOf(
        VideoMockInterview(
            id = "vm_sql_01",
            topicId = "sql",
            title = "Introduction to SQL Interview Questions And Answers",
            channelName = "Lotus IT Hub training institute",
            youtubeVideoId = "KCOWdPSAzjc",
            duration = "13:38 min",
            difficulty = "Mid-Level",
            description = "Real-world technical mock interview covering SQL & Database concepts, architectural trade-offs, and live candidate evaluation with Lotus IT Hub training institute.",
            keyTakeaways = listOf(
                "Advanced SQL querying: window functions (ROW_NUMBER, DENSE_RANK), recursive CTEs, and pivots",
                "Database indexing: B-Tree vs Hash indexes, composite index column ordering, and covering indexes",
                "ACID guarantees, transaction isolation levels (Dirty Read, Non-Repeatable Read, Phantom Read), and MVCC"
            ),
            relatedTrackId = "sql_interview"
        ),
        VideoMockInterview(
            id = "vm_sql_02",
            topicId = "sql",
            title = "Introduction",
            channelName = "Magneq Software",
            youtubeVideoId = "GWn3QbeSceg",
            duration = "3:03:59 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering SQL & Database concepts, architectural trade-offs, and live candidate evaluation with Magneq Software.",
            keyTakeaways = listOf(
                "Database indexing: B-Tree vs Hash indexes, composite index column ordering, and covering indexes",
                "ACID guarantees, transaction isolation levels (Dirty Read, Non-Repeatable Read, Phantom Read), and MVCC",
                "Query optimization: analyzing EXPLAIN plans, removing full table scans, and optimizing JOIN algorithms"
            ),
            relatedTrackId = "sql_interview"
        ),
        VideoMockInterview(
            id = "vm_sql_03",
            topicId = "sql",
            title = "Introduction to SQL Interview Questions And Answers",
            channelName = "Solved Pages",
            youtubeVideoId = "OGMOIaXMX6Y",
            duration = "14:50 min",
            difficulty = "Lead",
            description = "Real-world technical mock interview covering SQL & Database concepts, architectural trade-offs, and live candidate evaluation with Solved Pages.",
            keyTakeaways = listOf(
                "ACID guarantees, transaction isolation levels (Dirty Read, Non-Repeatable Read, Phantom Read), and MVCC",
                "Query optimization: analyzing EXPLAIN plans, removing full table scans, and optimizing JOIN algorithms",
                "Advanced SQL querying: window functions (ROW_NUMBER, DENSE_RANK), recursive CTEs, and pivots"
            ),
            relatedTrackId = "sql_interview"
        ),
        VideoMockInterview(
            id = "vm_sql_04",
            topicId = "sql",
            title = "Intro",
            channelName = "Lotus IT Hub training institute",
            youtubeVideoId = "WF_-8S4mSpU",
            duration = "15:41 min",
            difficulty = "Architect",
            description = "Real-world technical mock interview covering SQL & Database concepts, architectural trade-offs, and live candidate evaluation with Lotus IT Hub training institute.",
            keyTakeaways = listOf(
                "Query optimization: analyzing EXPLAIN plans, removing full table scans, and optimizing JOIN algorithms",
                "Advanced SQL querying: window functions (ROW_NUMBER, DENSE_RANK), recursive CTEs, and pivots",
                "Database indexing: B-Tree vs Hash indexes, composite index column ordering, and covering indexes"
            ),
            relatedTrackId = "sql_interview"
        ),
        VideoMockInterview(
            id = "vm_sql_05",
            topicId = "sql",
            title = "Intro",
            channelName = "Design Strong",
            youtubeVideoId = "zHEEKq6GJq0",
            duration = "51:20 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering SQL & Database concepts, architectural trade-offs, and live candidate evaluation with Design Strong.",
            keyTakeaways = listOf(
                "Advanced SQL querying: window functions (ROW_NUMBER, DENSE_RANK), recursive CTEs, and pivots",
                "Database indexing: B-Tree vs Hash indexes, composite index column ordering, and covering indexes",
                "ACID guarantees, transaction isolation levels (Dirty Read, Non-Repeatable Read, Phantom Read), and MVCC"
            ),
            relatedTrackId = "sql_interview"
        ),
        VideoMockInterview(
            id = "vm_sql_06",
            topicId = "sql",
            title = "Introduction",
            channelName = "Questpond",
            youtubeVideoId = "SEdAF8mSKS4",
            duration = "51:10 min",
            difficulty = "Mid-Level",
            description = "Real-world technical mock interview covering SQL & Database concepts, architectural trade-offs, and live candidate evaluation with Questpond.",
            keyTakeaways = listOf(
                "Database indexing: B-Tree vs Hash indexes, composite index column ordering, and covering indexes",
                "ACID guarantees, transaction isolation levels (Dirty Read, Non-Repeatable Read, Phantom Read), and MVCC",
                "Query optimization: analyzing EXPLAIN plans, removing full table scans, and optimizing JOIN algorithms"
            ),
            relatedTrackId = "sql_interview"
        ),
        VideoMockInterview(
            id = "vm_sql_07",
            topicId = "sql",
            title = "Introduction to DBMS Interview Questions",
            channelName = "Intellipaat",
            youtubeVideoId = "jNbStxNKyCg",
            duration = "16:45 min",
            difficulty = "Staff",
            description = "Real-world technical mock interview covering SQL & Database concepts, architectural trade-offs, and live candidate evaluation with Intellipaat.",
            keyTakeaways = listOf(
                "ACID guarantees, transaction isolation levels (Dirty Read, Non-Repeatable Read, Phantom Read), and MVCC",
                "Query optimization: analyzing EXPLAIN plans, removing full table scans, and optimizing JOIN algorithms",
                "Advanced SQL querying: window functions (ROW_NUMBER, DENSE_RANK), recursive CTEs, and pivots"
            ),
            relatedTrackId = "sql_interview"
        ),
        VideoMockInterview(
            id = "vm_sql_08",
            topicId = "sql",
            title = "Introduction to SQL for Analyzing Orders and Revenue",
            channelName = "Ankit Bansal",
            youtubeVideoId = "qyAgWL066Vo",
            duration = "24:29 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering SQL & Database concepts, architectural trade-offs, and live candidate evaluation with Ankit Bansal.",
            keyTakeaways = listOf(
                "Query optimization: analyzing EXPLAIN plans, removing full table scans, and optimizing JOIN algorithms",
                "Advanced SQL querying: window functions (ROW_NUMBER, DENSE_RANK), recursive CTEs, and pivots",
                "Database indexing: B-Tree vs Hash indexes, composite index column ordering, and covering indexes"
            ),
            relatedTrackId = "sql_interview"
        ),
        VideoMockInterview(
            id = "vm_sql_09",
            topicId = "sql",
            title = "Introduction to SQL Interview Questions And Answers",
            channelName = "Intellipaat",
            youtubeVideoId = "oX5Y26O5dBE",
            duration = "24:55 min",
            difficulty = "Lead",
            description = "Real-world technical mock interview covering SQL & Database concepts, architectural trade-offs, and live candidate evaluation with Intellipaat.",
            keyTakeaways = listOf(
                "Advanced SQL querying: window functions (ROW_NUMBER, DENSE_RANK), recursive CTEs, and pivots",
                "Database indexing: B-Tree vs Hash indexes, composite index column ordering, and covering indexes",
                "ACID guarantees, transaction isolation levels (Dirty Read, Non-Repeatable Read, Phantom Read), and MVCC"
            ),
            relatedTrackId = "sql_interview"
        ),
        VideoMockInterview(
            id = "vm_sql_10",
            topicId = "sql",
            title = "Why SQL is important for interviews",
            channelName = "Chandoo",
            youtubeVideoId = "Lb3vfuew7Gk",
            duration = "14:50 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering SQL & Database concepts, architectural trade-offs, and live candidate evaluation with Chandoo.",
            keyTakeaways = listOf(
                "Database indexing: B-Tree vs Hash indexes, composite index column ordering, and covering indexes",
                "ACID guarantees, transaction isolation levels (Dirty Read, Non-Repeatable Read, Phantom Read), and MVCC",
                "Query optimization: analyzing EXPLAIN plans, removing full table scans, and optimizing JOIN algorithms"
            ),
            relatedTrackId = "sql_interview"
        ),
    )

    // --- Angular Mock Interviews (10 real verified long-form videos) ---
    private val angularVideos = listOf(
        VideoMockInterview(
            id = "vm_angular_01",
            topicId = "angular",
            title = "Intro",
            channelName = "LEARNING PARTNER",
            youtubeVideoId = "aES0z3m0r1A",
            duration = "45:28 min",
            difficulty = "Mid-Level",
            description = "Real-world technical mock interview covering Angular concepts, architectural trade-offs, and live candidate evaluation with LEARNING PARTNER.",
            keyTakeaways = listOf(
                "Angular reactive architecture: Signals, computed values, effects, and modern zoneless change detection",
                "RxJS operator mastery: switchMap, mergeMap, concatMap, forkJoin, and subject memory leak prevention",
                "Dependency injection hierarchical injector tree: root, platform, and component-level providers"
            ),
            relatedTrackId = "angular_interview"
        ),
        VideoMockInterview(
            id = "vm_angular_02",
            topicId = "angular",
            title = "Intro u0026 What to Expect JavaScript u0026 Core Concepts",
            channelName = "LEARNING PARTNER",
            youtubeVideoId = "MSjcG2PxZoE",
            duration = "40:24 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Angular concepts, architectural trade-offs, and live candidate evaluation with LEARNING PARTNER.",
            keyTakeaways = listOf(
                "RxJS operator mastery: switchMap, mergeMap, concatMap, forkJoin, and subject memory leak prevention",
                "Dependency injection hierarchical injector tree: root, platform, and component-level providers",
                "Modular architecture: standalone components, lazy-loaded routing, and functional route guards"
            ),
            relatedTrackId = "angular_interview"
        ),
        VideoMockInterview(
            id = "vm_angular_03",
            topicId = "angular",
            title = "Intro",
            channelName = "Scholar Strategy by Nistha Tripathi",
            youtubeVideoId = "ZOc6VMhzoio",
            duration = "27:21 min",
            difficulty = "Lead",
            description = "Real-world technical mock interview covering Angular concepts, architectural trade-offs, and live candidate evaluation with Scholar Strategy by Nistha Tripathi.",
            keyTakeaways = listOf(
                "Dependency injection hierarchical injector tree: root, platform, and component-level providers",
                "Modular architecture: standalone components, lazy-loaded routing, and functional route guards",
                "Angular reactive architecture: Signals, computed values, effects, and modern zoneless change detection"
            ),
            relatedTrackId = "angular_interview"
        ),
        VideoMockInterview(
            id = "vm_angular_04",
            topicId = "angular",
            title = "Connection issues",
            channelName = "LEARNING PARTNER",
            youtubeVideoId = "UFzgLrNfAPE",
            duration = "1:14:01 min",
            difficulty = "Architect",
            description = "Real-world technical mock interview covering Angular concepts, architectural trade-offs, and live candidate evaluation with LEARNING PARTNER.",
            keyTakeaways = listOf(
                "Modular architecture: standalone components, lazy-loaded routing, and functional route guards",
                "Angular reactive architecture: Signals, computed values, effects, and modern zoneless change detection",
                "RxJS operator mastery: switchMap, mergeMap, concatMap, forkJoin, and subject memory leak prevention"
            ),
            relatedTrackId = "angular_interview"
        ),
        VideoMockInterview(
            id = "vm_angular_05",
            topicId = "angular",
            title = "Intro",
            channelName = "LEARNING PARTNER",
            youtubeVideoId = "ORtwaGrX-d0",
            duration = "45:28 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Angular concepts, architectural trade-offs, and live candidate evaluation with LEARNING PARTNER.",
            keyTakeaways = listOf(
                "Angular reactive architecture: Signals, computed values, effects, and modern zoneless change detection",
                "RxJS operator mastery: switchMap, mergeMap, concatMap, forkJoin, and subject memory leak prevention",
                "Dependency injection hierarchical injector tree: root, platform, and component-level providers"
            ),
            relatedTrackId = "angular_interview"
        ),
        VideoMockInterview(
            id = "vm_angular_06",
            topicId = "angular",
            title = "Introduction to Angular Js Interview",
            channelName = "MindMajix",
            youtubeVideoId = "lIMzdWuSh00",
            duration = "31:40 min",
            difficulty = "Mid-Level",
            description = "Real-world technical mock interview covering Angular concepts, architectural trade-offs, and live candidate evaluation with MindMajix.",
            keyTakeaways = listOf(
                "RxJS operator mastery: switchMap, mergeMap, concatMap, forkJoin, and subject memory leak prevention",
                "Dependency injection hierarchical injector tree: root, platform, and component-level providers",
                "Modular architecture: standalone components, lazy-loaded routing, and functional route guards"
            ),
            relatedTrackId = "angular_interview"
        ),
        VideoMockInterview(
            id = "vm_angular_07",
            topicId = "angular",
            title = "Introduction to angular",
            channelName = "CodeWithIndu",
            youtubeVideoId = "3h3Jqv3mfHc",
            duration = "48:21 min",
            difficulty = "Staff",
            description = "Real-world technical mock interview covering Angular concepts, architectural trade-offs, and live candidate evaluation with CodeWithIndu.",
            keyTakeaways = listOf(
                "Dependency injection hierarchical injector tree: root, platform, and component-level providers",
                "Modular architecture: standalone components, lazy-loaded routing, and functional route guards",
                "Angular reactive architecture: Signals, computed values, effects, and modern zoneless change detection"
            ),
            relatedTrackId = "angular_interview"
        ),
        VideoMockInterview(
            id = "vm_angular_08",
            topicId = "angular",
            title = "Introduction to angular",
            channelName = "Interview Happy",
            youtubeVideoId = "MqFOtZAUoPg",
            duration = "28:56 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Angular concepts, architectural trade-offs, and live candidate evaluation with Interview Happy.",
            keyTakeaways = listOf(
                "Modular architecture: standalone components, lazy-loaded routing, and functional route guards",
                "Angular reactive architecture: Signals, computed values, effects, and modern zoneless change detection",
                "RxJS operator mastery: switchMap, mergeMap, concatMap, forkJoin, and subject memory leak prevention"
            ),
            relatedTrackId = "angular_interview"
        ),
        VideoMockInterview(
            id = "vm_angular_09",
            topicId = "angular",
            title = "Angular overview",
            channelName = "CraftedScripts",
            youtubeVideoId = "WpUSKCB0o6o",
            duration = "46:41 min",
            difficulty = "Lead",
            description = "Real-world technical mock interview covering Angular concepts, architectural trade-offs, and live candidate evaluation with CraftedScripts.",
            keyTakeaways = listOf(
                "Angular reactive architecture: Signals, computed values, effects, and modern zoneless change detection",
                "RxJS operator mastery: switchMap, mergeMap, concatMap, forkJoin, and subject memory leak prevention",
                "Dependency injection hierarchical injector tree: root, platform, and component-level providers"
            ),
            relatedTrackId = "angular_interview"
        ),
        VideoMockInterview(
            id = "vm_angular_10",
            topicId = "angular",
            title = "Introduction",
            channelName = "Questpond",
            youtubeVideoId = "-jeoyDJDsSM",
            duration = "14:06 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Angular concepts, architectural trade-offs, and live candidate evaluation with Questpond.",
            keyTakeaways = listOf(
                "RxJS operator mastery: switchMap, mergeMap, concatMap, forkJoin, and subject memory leak prevention",
                "Dependency injection hierarchical injector tree: root, platform, and component-level providers",
                "Modular architecture: standalone components, lazy-loaded routing, and functional route guards"
            ),
            relatedTrackId = "angular_interview"
        ),
    )

    // --- Security Mock Interviews (10 real verified long-form videos) ---
    private val securityVideos = listOf(
        VideoMockInterview(
            id = "vm_security_01",
            topicId = "security",
            title = "Introduction",
            channelName = "Prabh Nair",
            youtubeVideoId = "HDCZiOCy_ww",
            duration = "1:19:49 min",
            difficulty = "Mid-Level",
            description = "Real-world technical mock interview covering Security concepts, architectural trade-offs, and live candidate evaluation with Prabh Nair.",
            keyTakeaways = listOf(
                "Modern authentication: OAuth 2.0 Authorization Code with PKCE and OIDC token lifecycles",
                "JWT security: Alg none mitigation, asymmetric RS256 signing, and short-lived token rotation",
                "Enterprise AppSec: Zero Trust architecture, mutual TLS (mTLS), and strict Content Security Policy"
            ),
            relatedTrackId = "security_interview"
        ),
        VideoMockInterview(
            id = "vm_security_02",
            topicId = "security",
            title = "Q1 - What is the difference between encryption and hashing?",
            channelName = "CRAW SECURITY ",
            youtubeVideoId = "k5Z_7HBIJY8",
            duration = "17:06 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Security concepts, architectural trade-offs, and live candidate evaluation with CRAW SECURITY .",
            keyTakeaways = listOf(
                "JWT security: Alg none mitigation, asymmetric RS256 signing, and short-lived token rotation",
                "Enterprise AppSec: Zero Trust architecture, mutual TLS (mTLS), and strict Content Security Policy",
                "OWASP Top 10 vulnerabilities: Broken Access Control (IDOR), SQL injection, SSRF, and defenses"
            ),
            relatedTrackId = "security_interview"
        ),
        VideoMockInterview(
            id = "vm_security_03",
            topicId = "security",
            title = "Introduction",
            channelName = "Aced (formerly Exponent)",
            youtubeVideoId = "IZKfZNVhItg",
            duration = "22:47 min",
            difficulty = "Lead",
            description = "Real-world technical mock interview covering Security concepts, architectural trade-offs, and live candidate evaluation with Aced (formerly Exponent).",
            keyTakeaways = listOf(
                "Enterprise AppSec: Zero Trust architecture, mutual TLS (mTLS), and strict Content Security Policy",
                "OWASP Top 10 vulnerabilities: Broken Access Control (IDOR), SQL injection, SSRF, and defenses",
                "Modern authentication: OAuth 2.0 Authorization Code with PKCE and OIDC token lifecycles"
            ),
            relatedTrackId = "security_interview"
        ),
        VideoMockInterview(
            id = "vm_security_04",
            topicId = "security",
            title = "Introduction",
            channelName = "NETWORKERS HOME",
            youtubeVideoId = "THEFxq9j-p8",
            duration = "50:32 min",
            difficulty = "Architect",
            description = "Real-world technical mock interview covering Security concepts, architectural trade-offs, and live candidate evaluation with NETWORKERS HOME.",
            keyTakeaways = listOf(
                "OWASP Top 10 vulnerabilities: Broken Access Control (IDOR), SQL injection, SSRF, and defenses",
                "Modern authentication: OAuth 2.0 Authorization Code with PKCE and OIDC token lifecycles",
                "JWT security: Alg none mitigation, asymmetric RS256 signing, and short-lived token rotation"
            ),
            relatedTrackId = "security_interview"
        ),
        VideoMockInterview(
            id = "vm_security_05",
            topicId = "security",
            title = "Introduction",
            channelName = "Ace Interviews",
            youtubeVideoId = "uPn3ggi3KoU",
            duration = "1:19:49 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Security concepts, architectural trade-offs, and live candidate evaluation with Ace Interviews.",
            keyTakeaways = listOf(
                "Modern authentication: OAuth 2.0 Authorization Code with PKCE and OIDC token lifecycles",
                "JWT security: Alg none mitigation, asymmetric RS256 signing, and short-lived token rotation",
                "Enterprise AppSec: Zero Trust architecture, mutual TLS (mTLS), and strict Content Security Policy"
            ),
            relatedTrackId = "security_interview"
        ),
        VideoMockInterview(
            id = "vm_security_06",
            topicId = "security",
            title = "Introduction",
            channelName = "Aced (formerly Exponent)",
            youtubeVideoId = "DheC3-J4iH0",
            duration = "31:32 min",
            difficulty = "Mid-Level",
            description = "Real-world technical mock interview covering Security concepts, architectural trade-offs, and live candidate evaluation with Aced (formerly Exponent).",
            keyTakeaways = listOf(
                "JWT security: Alg none mitigation, asymmetric RS256 signing, and short-lived token rotation",
                "Enterprise AppSec: Zero Trust architecture, mutual TLS (mTLS), and strict Content Security Policy",
                "OWASP Top 10 vulnerabilities: Broken Access Control (IDOR), SQL injection, SSRF, and defenses"
            ),
            relatedTrackId = "security_interview"
        ),
        VideoMockInterview(
            id = "vm_security_07",
            topicId = "security",
            title = "Introduction",
            channelName = "Cybervie Cybersecurity Academy ",
            youtubeVideoId = "XQC_t7uplBo",
            duration = "43:24 min",
            difficulty = "Staff",
            description = "Real-world technical mock interview covering Security concepts, architectural trade-offs, and live candidate evaluation with Cybervie Cybersecurity Academy .",
            keyTakeaways = listOf(
                "Enterprise AppSec: Zero Trust architecture, mutual TLS (mTLS), and strict Content Security Policy",
                "OWASP Top 10 vulnerabilities: Broken Access Control (IDOR), SQL injection, SSRF, and defenses",
                "Modern authentication: OAuth 2.0 Authorization Code with PKCE and OIDC token lifecycles"
            ),
            relatedTrackId = "security_interview"
        ),
        VideoMockInterview(
            id = "vm_security_08",
            topicId = "security",
            title = "Intro",
            channelName = "Rajneesh Gupta",
            youtubeVideoId = "HHtQVSGnYgg",
            duration = "50:32 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Security concepts, architectural trade-offs, and live candidate evaluation with Rajneesh Gupta.",
            keyTakeaways = listOf(
                "OWASP Top 10 vulnerabilities: Broken Access Control (IDOR), SQL injection, SSRF, and defenses",
                "Modern authentication: OAuth 2.0 Authorization Code with PKCE and OIDC token lifecycles",
                "JWT security: Alg none mitigation, asymmetric RS256 signing, and short-lived token rotation"
            ),
            relatedTrackId = "security_interview"
        ),
        VideoMockInterview(
            id = "vm_security_09",
            topicId = "security",
            title = "Introduction",
            channelName = "Rajneesh Gupta",
            youtubeVideoId = "kqnl30Q0pKQ",
            duration = "56:25 min",
            difficulty = "Lead",
            description = "Real-world technical mock interview covering Security concepts, architectural trade-offs, and live candidate evaluation with Rajneesh Gupta.",
            keyTakeaways = listOf(
                "Modern authentication: OAuth 2.0 Authorization Code with PKCE and OIDC token lifecycles",
                "JWT security: Alg none mitigation, asymmetric RS256 signing, and short-lived token rotation",
                "Enterprise AppSec: Zero Trust architecture, mutual TLS (mTLS), and strict Content Security Policy"
            ),
            relatedTrackId = "security_interview"
        ),
        VideoMockInterview(
            id = "vm_security_10",
            topicId = "security",
            title = "Intro",
            channelName = "Cloud Security Podcast",
            youtubeVideoId = "iBLuGN8_uOU",
            duration = "1:14:29 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Security concepts, architectural trade-offs, and live candidate evaluation with Cloud Security Podcast.",
            keyTakeaways = listOf(
                "JWT security: Alg none mitigation, asymmetric RS256 signing, and short-lived token rotation",
                "Enterprise AppSec: Zero Trust architecture, mutual TLS (mTLS), and strict Content Security Policy",
                "OWASP Top 10 vulnerabilities: Broken Access Control (IDOR), SQL injection, SSRF, and defenses"
            ),
            relatedTrackId = "security_interview"
        ),
    )

    private val allVideosList: List<VideoMockInterview> =
        javaVideos +
        springbootVideos +
        microservicesVideos +
        fullstackVideos +
        hldVideos +
        lldVideos +
        systemdesignVideos +
        devopsVideos +
        sqlVideos +
        angularVideos +
        securityVideos

    fun getTopics(): List<VideoMockTopic> = topicsList

    fun getVideosForTopic(topicId: String): List<VideoMockInterview> {
        return allVideosList.filter { it.topicId == topicId }
    }

    fun getVideoById(id: String): VideoMockInterview? {
        return allVideosList.firstOrNull { it.id == id }
    }

    fun getNextVideo(currentId: String, topicId: String): VideoMockInterview? {
        val topicVideos = getVideosForTopic(topicId)
        val currentIndex = topicVideos.indexOfFirst { it.id == currentId }
        return if (currentIndex != -1 && currentIndex + 1 < topicVideos.size) {
            topicVideos[currentIndex + 1]
        } else if (topicVideos.isNotEmpty()) {
            topicVideos.first()
        } else {
            null
        }
    }

    fun getPreviousVideo(currentId: String, topicId: String): VideoMockInterview? {
        val topicVideos = getVideosForTopic(topicId)
        val currentIndex = topicVideos.indexOfFirst { it.id == currentId }
        return if (currentIndex > 0) {
            topicVideos[currentIndex - 1]
        } else if (topicVideos.isNotEmpty()) {
            topicVideos.last()
        } else {
            null
        }
    }
}
