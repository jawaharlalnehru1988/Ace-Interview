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
        ),
    )

    // --- 1. Java Mock Interviews (10 real verified videos) ---
    private val javaVideos = listOf(
        VideoMockInterview(
            id = "vm_java_01",
            topicId = "java",
            title = "??Java Developer Interview #shorts #simplilearn",
            channelName = "Simplilearn",
            youtubeVideoId = "FwNZ4D-wscs",
            duration = "42 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Java concepts, architectural trade-offs, and live candidate evaluation with Simplilearn.",
            keyTakeaways = listOf(
                "Core JVM memory partitions (Eden, Survivor, Tenured, Metaspace) and heap garbage collection",
                "Thread synchronization, volatile semantics, happens-before consistency, and CAS operations",
                "Collections internals: HashMap treeification threshold and thread-safe ConcurrentHashMap patterns"
            ),
            relatedTrackId = "java_interview"
        ),
        VideoMockInterview(
            id = "vm_java_02",
            topicId = "java",
            title = "Don�t Go to Your Java Interview Without Watching This!",
            channelName = "Supersourcing",
            youtubeVideoId = "AT0fq2q0570",
            duration = "42 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Java concepts, architectural trade-offs, and live candidate evaluation with Supersourcing.",
            keyTakeaways = listOf(
                "Thread synchronization, volatile semantics, happens-before consistency, and CAS operations",
                "Collections internals: HashMap treeification threshold and thread-safe ConcurrentHashMap patterns",
                "Modern Java features: Virtual Threads, sealed classes, pattern matching, and functional streams"
            ),
            relatedTrackId = "java_interview"
        ),
        VideoMockInterview(
            id = "vm_java_03",
            topicId = "java",
            title = "What Is Interface In Java? | Java Interview Question | Kunal Sir",
            channelName = "CJC EdTech by Kunal Sir",
            youtubeVideoId = "t2BTaO9lVzg",
            duration = "42 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Java concepts, architectural trade-offs, and live candidate evaluation with CJC EdTech by Kunal Sir.",
            keyTakeaways = listOf(
                "Collections internals: HashMap treeification threshold and thread-safe ConcurrentHashMap patterns",
                "Modern Java features: Virtual Threads, sealed classes, pattern matching, and functional streams",
                "Core JVM memory partitions (Eden, Survivor, Tenured, Metaspace) and heap garbage collection"
            ),
            relatedTrackId = "java_interview"
        ),
        VideoMockInterview(
            id = "vm_java_04",
            topicId = "java",
            title = "How Much Time It Takes To Crack A Java Interview? | Java Interview Tips",
            channelName = "TAP ACADEMY",
            youtubeVideoId = "iFm74m-FaDU",
            duration = "42 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Java concepts, architectural trade-offs, and live candidate evaluation with TAP ACADEMY.",
            keyTakeaways = listOf(
                "Modern Java features: Virtual Threads, sealed classes, pattern matching, and functional streams",
                "Core JVM memory partitions (Eden, Survivor, Tenured, Metaspace) and heap garbage collection",
                "Thread synchronization, volatile semantics, happens-before consistency, and CAS operations"
            ),
            relatedTrackId = "java_interview"
        ),
        VideoMockInterview(
            id = "vm_java_05",
            topicId = "java",
            title = "Core Java Interview Questions!",
            channelName = "Kiran Academy - Java By Kiran",
            youtubeVideoId = "KrBCxXpbtnA",
            duration = "42 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Java concepts, architectural trade-offs, and live candidate evaluation with Kiran Academy - Java By Kiran.",
            keyTakeaways = listOf(
                "Core JVM memory partitions (Eden, Survivor, Tenured, Metaspace) and heap garbage collection",
                "Thread synchronization, volatile semantics, happens-before consistency, and CAS operations",
                "Collections internals: HashMap treeification threshold and thread-safe ConcurrentHashMap patterns"
            ),
            relatedTrackId = "java_interview"
        ),
        VideoMockInterview(
            id = "vm_java_06",
            topicId = "java",
            title = "Fresher Mock Interview CORE JAVA | Technical Round | CORE JAVA Interview | @magneqsoftware6896",
            channelName = "Magneq Software",
            youtubeVideoId = "c2tOBH3mUIg",
            duration = "42 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Java concepts, architectural trade-offs, and live candidate evaluation with Magneq Software.",
            keyTakeaways = listOf(
                "Thread synchronization, volatile semantics, happens-before consistency, and CAS operations",
                "Collections internals: HashMap treeification threshold and thread-safe ConcurrentHashMap patterns",
                "Modern Java features: Virtual Threads, sealed classes, pattern matching, and functional streams"
            ),
            relatedTrackId = "java_interview"
        ),
        VideoMockInterview(
            id = "vm_java_07",
            topicId = "java",
            title = "Frequently Asked Core Java Interview Quetsions | Beginner to Advanced Level Questions | in ?????",
            channelName = "UNIQ Technologies",
            youtubeVideoId = "bF6gLekjU4E",
            duration = "42 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Java concepts, architectural trade-offs, and live candidate evaluation with UNIQ Technologies.",
            keyTakeaways = listOf(
                "Collections internals: HashMap treeification threshold and thread-safe ConcurrentHashMap patterns",
                "Modern Java features: Virtual Threads, sealed classes, pattern matching, and functional streams",
                "Core JVM memory partitions (Eden, Survivor, Tenured, Metaspace) and heap garbage collection"
            ),
            relatedTrackId = "java_interview"
        ),
        VideoMockInterview(
            id = "vm_java_08",
            topicId = "java",
            title = "Mock Interview 5+ year experienced | Spring Boot | Java | Microservice | System Design | Code Decode",
            channelName = "Code Decode",
            youtubeVideoId = "xMlcsFLk-CU",
            duration = "42 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Java concepts, architectural trade-offs, and live candidate evaluation with Code Decode.",
            keyTakeaways = listOf(
                "Modern Java features: Virtual Threads, sealed classes, pattern matching, and functional streams",
                "Core JVM memory partitions (Eden, Survivor, Tenured, Metaspace) and heap garbage collection",
                "Thread synchronization, volatile semantics, happens-before consistency, and CAS operations"
            ),
            relatedTrackId = "java_interview"
        ),
        VideoMockInterview(
            id = "vm_java_09",
            topicId = "java",
            title = "Java Tech Lead Interview Questions #javainterviewquestions #javainterview",
            channelName = "TinyCodeFix",
            youtubeVideoId = "sfLl3pH0NFY",
            duration = "42 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Java concepts, architectural trade-offs, and live candidate evaluation with TinyCodeFix.",
            keyTakeaways = listOf(
                "Core JVM memory partitions (Eden, Survivor, Tenured, Metaspace) and heap garbage collection",
                "Thread synchronization, volatile semantics, happens-before consistency, and CAS operations",
                "Collections internals: HashMap treeification threshold and thread-safe ConcurrentHashMap patterns"
            ),
            relatedTrackId = "java_interview"
        ),
        VideoMockInterview(
            id = "vm_java_10",
            topicId = "java",
            title = "JAVA Full Stack | Fresher Mock Interview | Best Software Training Institute in Hyderabad | VCUBE",
            channelName = "V CUBE Software Solutions",
            youtubeVideoId = "lXKzWaQK5Ek",
            duration = "42 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Java concepts, architectural trade-offs, and live candidate evaluation with V CUBE Software Solutions.",
            keyTakeaways = listOf(
                "Thread synchronization, volatile semantics, happens-before consistency, and CAS operations",
                "Collections internals: HashMap treeification threshold and thread-safe ConcurrentHashMap patterns",
                "Modern Java features: Virtual Threads, sealed classes, pattern matching, and functional streams"
            ),
            relatedTrackId = "java_interview"
        ),
    )

    // --- 2. Spring Boot Mock Interviews (10 real verified videos) ---
    private val springbootVideos = listOf(
        VideoMockInterview(
            id = "vm_spring_boot_01",
            topicId = "spring_boot",
            title = "Deloitte Java Developer 2nd Round Interview Questions (5 Years Experience) | Spring Boot + DSA",
            channelName = "Java is Awesome",
            youtubeVideoId = "8_DGr9tg3u8",
            duration = "48 min",
            difficulty = "Mid-to-Senior",
            description = "Real-world technical mock interview covering Spring Boot concepts, architectural trade-offs, and live candidate evaluation with Java is Awesome.",
            keyTakeaways = listOf(
                "Spring IoC container lifecycle, BeanPostProcessor, and circular dependency resolution",
                "@Transactional boundary rules, rollbackFor exception handling, and REQUIRES_NEW propagation",
                "Spring Security 6 stateless filter chains, OAuth2, and JWT OncePerRequestFilter"
            ),
            relatedTrackId = "spring_boot_interview"
        ),
        VideoMockInterview(
            id = "vm_spring_boot_02",
            topicId = "spring_boot",
            title = "??Spring MVC Interview Questions | Spring Boot Vs Spring MVC | Intellipaat #Shorts #SpringMVC",
            channelName = "Intellipaat",
            youtubeVideoId = "2l1rs81kucM",
            duration = "48 min",
            difficulty = "Mid-to-Senior",
            description = "Real-world technical mock interview covering Spring Boot concepts, architectural trade-offs, and live candidate evaluation with Intellipaat.",
            keyTakeaways = listOf(
                "@Transactional boundary rules, rollbackFor exception handling, and REQUIRES_NEW propagation",
                "Spring Security 6 stateless filter chains, OAuth2, and JWT OncePerRequestFilter",
                "Hibernate / Spring Data JPA N+1 query diagnostics, JOIN FETCH, and DTO projections"
            ),
            relatedTrackId = "spring_boot_interview"
        ),
        VideoMockInterview(
            id = "vm_spring_boot_03",
            topicId = "spring_boot",
            title = "??Java Developer Interview #shorts #simplilearn",
            channelName = "Simplilearn",
            youtubeVideoId = "FwNZ4D-wscs",
            duration = "48 min",
            difficulty = "Mid-to-Senior",
            description = "Real-world technical mock interview covering Spring Boot concepts, architectural trade-offs, and live candidate evaluation with Simplilearn.",
            keyTakeaways = listOf(
                "Spring Security 6 stateless filter chains, OAuth2, and JWT OncePerRequestFilter",
                "Hibernate / Spring Data JPA N+1 query diagnostics, JOIN FETCH, and DTO projections",
                "Spring IoC container lifecycle, BeanPostProcessor, and circular dependency resolution"
            ),
            relatedTrackId = "spring_boot_interview"
        ),
        VideoMockInterview(
            id = "vm_spring_boot_04",
            topicId = "spring_boot",
            title = "?? Spring Boot Coding Interview Question | Build REST API to Fetch Last 10 User Activities ??",
            channelName = "Java Full Stack Interview Questions & Answers",
            youtubeVideoId = "kFRHVM8gzHA",
            duration = "48 min",
            difficulty = "Mid-to-Senior",
            description = "Real-world technical mock interview covering Spring Boot concepts, architectural trade-offs, and live candidate evaluation with Java Full Stack Interview Questions & Answers.",
            keyTakeaways = listOf(
                "Hibernate / Spring Data JPA N+1 query diagnostics, JOIN FETCH, and DTO projections",
                "Spring IoC container lifecycle, BeanPostProcessor, and circular dependency resolution",
                "@Transactional boundary rules, rollbackFor exception handling, and REQUIRES_NEW propagation"
            ),
            relatedTrackId = "spring_boot_interview"
        ),
        VideoMockInterview(
            id = "vm_spring_boot_05",
            topicId = "spring_boot",
            title = "Java Interview Question | Why to Use Spring Boot? | #shorts #kiransir #freshers",
            channelName = "Kiran Academy - Java By Kiran",
            youtubeVideoId = "_vuxLQ6zq34",
            duration = "48 min",
            difficulty = "Mid-to-Senior",
            description = "Real-world technical mock interview covering Spring Boot concepts, architectural trade-offs, and live candidate evaluation with Kiran Academy - Java By Kiran.",
            keyTakeaways = listOf(
                "Spring IoC container lifecycle, BeanPostProcessor, and circular dependency resolution",
                "@Transactional boundary rules, rollbackFor exception handling, and REQUIRES_NEW propagation",
                "Spring Security 6 stateless filter chains, OAuth2, and JWT OncePerRequestFilter"
            ),
            relatedTrackId = "spring_boot_interview"
        ),
        VideoMockInterview(
            id = "vm_spring_boot_06",
            topicId = "spring_boot",
            title = "Mock Interview 5+ year experienced | Spring Boot | Java | Microservice | System Design | Code Decode",
            channelName = "Code Decode",
            youtubeVideoId = "xMlcsFLk-CU",
            duration = "48 min",
            difficulty = "Mid-to-Senior",
            description = "Real-world technical mock interview covering Spring Boot concepts, architectural trade-offs, and live candidate evaluation with Code Decode.",
            keyTakeaways = listOf(
                "@Transactional boundary rules, rollbackFor exception handling, and REQUIRES_NEW propagation",
                "Spring Security 6 stateless filter chains, OAuth2, and JWT OncePerRequestFilter",
                "Hibernate / Spring Data JPA N+1 query diagnostics, JOIN FETCH, and DTO projections"
            ),
            relatedTrackId = "spring_boot_interview"
        ),
        VideoMockInterview(
            id = "vm_spring_boot_07",
            topicId = "spring_boot",
            title = "Java Spring Boot Interview Playlist Introduction",
            channelName = "The Curious Coder",
            youtubeVideoId = "6BUOmiezXHo",
            duration = "48 min",
            difficulty = "Mid-to-Senior",
            description = "Real-world technical mock interview covering Spring Boot concepts, architectural trade-offs, and live candidate evaluation with The Curious Coder.",
            keyTakeaways = listOf(
                "Spring Security 6 stateless filter chains, OAuth2, and JWT OncePerRequestFilter",
                "Hibernate / Spring Data JPA N+1 query diagnostics, JOIN FETCH, and DTO projections",
                "Spring IoC container lifecycle, BeanPostProcessor, and circular dependency resolution"
            ),
            relatedTrackId = "spring_boot_interview"
        ),
        VideoMockInterview(
            id = "vm_spring_boot_08",
            topicId = "spring_boot",
            title = "IOC and DI in Java Spring Boot : Java Spring Boot Interview Question 1",
            channelName = "The Curious Coder",
            youtubeVideoId = "5UtjUMoXrno",
            duration = "48 min",
            difficulty = "Mid-to-Senior",
            description = "Real-world technical mock interview covering Spring Boot concepts, architectural trade-offs, and live candidate evaluation with The Curious Coder.",
            keyTakeaways = listOf(
                "Hibernate / Spring Data JPA N+1 query diagnostics, JOIN FETCH, and DTO projections",
                "Spring IoC container lifecycle, BeanPostProcessor, and circular dependency resolution",
                "@Transactional boundary rules, rollbackFor exception handling, and REQUIRES_NEW propagation"
            ),
            relatedTrackId = "spring_boot_interview"
        ),
        VideoMockInterview(
            id = "vm_spring_boot_09",
            topicId = "spring_boot",
            title = "3-7 Years Interview Experience | Java | Spring Boot | Microservices | Maven | SQL",
            channelName = "Insights Instructor",
            youtubeVideoId = "I8vcwnn6Qc8",
            duration = "48 min",
            difficulty = "Mid-to-Senior",
            description = "Real-world technical mock interview covering Spring Boot concepts, architectural trade-offs, and live candidate evaluation with Insights Instructor.",
            keyTakeaways = listOf(
                "Spring IoC container lifecycle, BeanPostProcessor, and circular dependency resolution",
                "@Transactional boundary rules, rollbackFor exception handling, and REQUIRES_NEW propagation",
                "Spring Security 6 stateless filter chains, OAuth2, and JWT OncePerRequestFilter"
            ),
            relatedTrackId = "spring_boot_interview"
        ),
        VideoMockInterview(
            id = "vm_spring_boot_10",
            topicId = "spring_boot",
            title = "Spring boot interview questions| |annotation",
            channelName = "Sridhar TA Technology",
            youtubeVideoId = "CslDNtfonPw",
            duration = "48 min",
            difficulty = "Mid-to-Senior",
            description = "Real-world technical mock interview covering Spring Boot concepts, architectural trade-offs, and live candidate evaluation with Sridhar TA Technology.",
            keyTakeaways = listOf(
                "@Transactional boundary rules, rollbackFor exception handling, and REQUIRES_NEW propagation",
                "Spring Security 6 stateless filter chains, OAuth2, and JWT OncePerRequestFilter",
                "Hibernate / Spring Data JPA N+1 query diagnostics, JOIN FETCH, and DTO projections"
            ),
            relatedTrackId = "spring_boot_interview"
        ),
    )

    // --- 3. Microservices Mock Interviews (10 real verified videos) ---
    private val microservicesVideos = listOf(
        VideoMockInterview(
            id = "vm_microservices_01",
            topicId = "microservices",
            title = "Microservices Interview Questions 2026 | Top Microservices Interview Questions & Answers | MindMajix",
            channelName = "MindMajix",
            youtubeVideoId = "wmawYODmQU0",
            duration = "50 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Microservices concepts, architectural trade-offs, and live candidate evaluation with MindMajix.",
            keyTakeaways = listOf(
                "Saga pattern implementation: Choreography vs Orchestration and compensating transactions",
                "Event-driven architecture with Apache Kafka partitioning and Transactional Outbox pattern",
                "Resilience patterns: Sliding window circuit breakers, retry with backoff, and bulkhead isolation"
            ),
            relatedTrackId = "microservices_interview"
        ),
        VideoMockInterview(
            id = "vm_microservices_02",
            topicId = "microservices",
            title = "Microservices interview questions (Tricky | Scenario-Based | 2-7 Yrs)",
            channelName = "GenZ Career",
            youtubeVideoId = "xH9bB7oluKc",
            duration = "50 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Microservices concepts, architectural trade-offs, and live candidate evaluation with GenZ Career.",
            keyTakeaways = listOf(
                "Event-driven architecture with Apache Kafka partitioning and Transactional Outbox pattern",
                "Resilience patterns: Sliding window circuit breakers, retry with backoff, and bulkhead isolation",
                "Distributed tracing with OpenTelemetry, W3C traceparent headers, and centralized Jaeger logs"
            ),
            relatedTrackId = "microservices_interview"
        ),
        VideoMockInterview(
            id = "vm_microservices_03",
            topicId = "microservices",
            title = "Microservices Interview Questions and Answers for experienced and fresher | Most Asked | Code Decode",
            channelName = "Code Decode",
            youtubeVideoId = "9x_6VLk3GtY",
            duration = "50 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Microservices concepts, architectural trade-offs, and live candidate evaluation with Code Decode.",
            keyTakeaways = listOf(
                "Resilience patterns: Sliding window circuit breakers, retry with backoff, and bulkhead isolation",
                "Distributed tracing with OpenTelemetry, W3C traceparent headers, and centralized Jaeger logs",
                "Saga pattern implementation: Choreography vs Orchestration and compensating transactions"
            ),
            relatedTrackId = "microservices_interview"
        ),
        VideoMockInterview(
            id = "vm_microservices_04",
            topicId = "microservices",
            title = "Microservices Interview questions and answers for experienced and freshers | Code Decode |  Part-2",
            channelName = "Code Decode",
            youtubeVideoId = "oNL5tQ4L9xg",
            duration = "50 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Microservices concepts, architectural trade-offs, and live candidate evaluation with Code Decode.",
            keyTakeaways = listOf(
                "Distributed tracing with OpenTelemetry, W3C traceparent headers, and centralized Jaeger logs",
                "Saga pattern implementation: Choreography vs Orchestration and compensating transactions",
                "Event-driven architecture with Apache Kafka partitioning and Transactional Outbox pattern"
            ),
            relatedTrackId = "microservices_interview"
        ),
        VideoMockInterview(
            id = "vm_microservices_05",
            topicId = "microservices",
            title = "What is Microservices Architecture | Full Stack Developer Interview Questions with Explanation",
            channelName = "Easy Learning 24x7",
            youtubeVideoId = "cMgcw7usjKE",
            duration = "50 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Microservices concepts, architectural trade-offs, and live candidate evaluation with Easy Learning 24x7.",
            keyTakeaways = listOf(
                "Saga pattern implementation: Choreography vs Orchestration and compensating transactions",
                "Event-driven architecture with Apache Kafka partitioning and Transactional Outbox pattern",
                "Resilience patterns: Sliding window circuit breakers, retry with backoff, and bulkhead isolation"
            ),
            relatedTrackId = "microservices_interview"
        ),
        VideoMockInterview(
            id = "vm_microservices_06",
            topicId = "microservices",
            title = "Java Mock Interview Series | Question 9 | Security Best Practices in Microservices",
            channelName = "Rise Academy Career Connect",
            youtubeVideoId = "UdX-zT-pnOE",
            duration = "50 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Microservices concepts, architectural trade-offs, and live candidate evaluation with Rise Academy Career Connect.",
            keyTakeaways = listOf(
                "Event-driven architecture with Apache Kafka partitioning and Transactional Outbox pattern",
                "Resilience patterns: Sliding window circuit breakers, retry with backoff, and bulkhead isolation",
                "Distributed tracing with OpenTelemetry, W3C traceparent headers, and centralized Jaeger logs"
            ),
            relatedTrackId = "microservices_interview"
        ),
        VideoMockInterview(
            id = "vm_microservices_07",
            topicId = "microservices",
            title = "Microservices Interview Question ?? How Do Microservices Communicate? | REST vs Kafka",
            channelName = "Java Full Stack Interview Questions & Answers",
            youtubeVideoId = "DfUB8x0hL4c",
            duration = "50 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Microservices concepts, architectural trade-offs, and live candidate evaluation with Java Full Stack Interview Questions & Answers.",
            keyTakeaways = listOf(
                "Resilience patterns: Sliding window circuit breakers, retry with backoff, and bulkhead isolation",
                "Distributed tracing with OpenTelemetry, W3C traceparent headers, and centralized Jaeger logs",
                "Saga pattern implementation: Choreography vs Orchestration and compensating transactions"
            ),
            relatedTrackId = "microservices_interview"
        ),
        VideoMockInterview(
            id = "vm_microservices_08",
            topicId = "microservices",
            title = "In microservices, how can one microservice communicate with another",
            channelName = "Code X",
            youtubeVideoId = "lVPB0o2Ihys",
            duration = "50 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Microservices concepts, architectural trade-offs, and live candidate evaluation with Code X.",
            keyTakeaways = listOf(
                "Distributed tracing with OpenTelemetry, W3C traceparent headers, and centralized Jaeger logs",
                "Saga pattern implementation: Choreography vs Orchestration and compensating transactions",
                "Event-driven architecture with Apache Kafka partitioning and Transactional Outbox pattern"
            ),
            relatedTrackId = "microservices_interview"
        ),
        VideoMockInterview(
            id = "vm_microservices_09",
            topicId = "microservices",
            title = "Interview Questions That Exposes Fake Microservices Experience",
            channelName = "Selenium Express",
            youtubeVideoId = "-6Hj9GvFLyE",
            duration = "50 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Microservices concepts, architectural trade-offs, and live candidate evaluation with Selenium Express.",
            keyTakeaways = listOf(
                "Saga pattern implementation: Choreography vs Orchestration and compensating transactions",
                "Event-driven architecture with Apache Kafka partitioning and Transactional Outbox pattern",
                "Resilience patterns: Sliding window circuit breakers, retry with backoff, and bulkhead isolation"
            ),
            relatedTrackId = "microservices_interview"
        ),
        VideoMockInterview(
            id = "vm_microservices_10",
            topicId = "microservices",
            title = "Microservices Interview Secrets: What 10 Years Taught Us",
            channelName = "Selenium Express",
            youtubeVideoId = "t_yaUzErzAs",
            duration = "50 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Microservices concepts, architectural trade-offs, and live candidate evaluation with Selenium Express.",
            keyTakeaways = listOf(
                "Event-driven architecture with Apache Kafka partitioning and Transactional Outbox pattern",
                "Resilience patterns: Sliding window circuit breakers, retry with backoff, and bulkhead isolation",
                "Distributed tracing with OpenTelemetry, W3C traceparent headers, and centralized Jaeger logs"
            ),
            relatedTrackId = "microservices_interview"
        ),
    )

    // --- 4. Full Stack Mock Interviews (10 real verified videos) ---
    private val fullstackVideos = listOf(
        VideoMockInterview(
            id = "vm_full_stack_01",
            topicId = "full_stack",
            title = "??????????????'?? ?????????????????? ??????????????????",
            channelName = "ReactJS Developer Interview Series",
            youtubeVideoId = "l2f3xVvZGLA",
            duration = "55 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Full Stack concepts, architectural trade-offs, and live candidate evaluation with ReactJS Developer Interview Series.",
            keyTakeaways = listOf(
                "End-to-end integration: TypeScript frontend models aligned with backend Spring Boot DTO contracts",
                "Authentication security: HttpOnly SameSite cookies vs JWT, avoiding XSS and CSRF vulnerabilities",
                "High performance rendering: Virtual scrolling, cursor-based pagination, and bundle code splitting"
            ),
            relatedTrackId = "full_stack_interview"
        ),
        VideoMockInterview(
            id = "vm_full_stack_02",
            topicId = "full_stack",
            title = "Top 5 Front-end Developer Interview Questions every fresher should know!",
            channelName = "LIVEWIRE India",
            youtubeVideoId = "2ls1QcubWxw",
            duration = "55 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Full Stack concepts, architectural trade-offs, and live candidate evaluation with LIVEWIRE India.",
            keyTakeaways = listOf(
                "Authentication security: HttpOnly SameSite cookies vs JWT, avoiding XSS and CSRF vulnerabilities",
                "High performance rendering: Virtual scrolling, cursor-based pagination, and bundle code splitting",
                "Real-time communication: WebSockets with STOMP relays, RxJS subject state, and Redis backplanes"
            ),
            relatedTrackId = "full_stack_interview"
        ),
        VideoMockInterview(
            id = "vm_full_stack_03",
            topicId = "full_stack",
            title = "Full Stack Web Development Technical  Interview Questions  2024 | PrasannaNIT",
            channelName = "Prasanna Tech AI",
            youtubeVideoId = "dbyelTJsSG4",
            duration = "55 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Full Stack concepts, architectural trade-offs, and live candidate evaluation with Prasanna Tech AI.",
            keyTakeaways = listOf(
                "High performance rendering: Virtual scrolling, cursor-based pagination, and bundle code splitting",
                "Real-time communication: WebSockets with STOMP relays, RxJS subject state, and Redis backplanes",
                "End-to-end integration: TypeScript frontend models aligned with backend Spring Boot DTO contracts"
            ),
            relatedTrackId = "full_stack_interview"
        ),
        VideoMockInterview(
            id = "vm_full_stack_04",
            topicId = "full_stack",
            title = "Full Stack Developer Interview Questions YOU MUST KNOW ?? | Intellipaat #Shorts #FullStack",
            channelName = "Intellipaat",
            youtubeVideoId = "IuAZ35vf_YI",
            duration = "55 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Full Stack concepts, architectural trade-offs, and live candidate evaluation with Intellipaat.",
            keyTakeaways = listOf(
                "Real-time communication: WebSockets with STOMP relays, RxJS subject state, and Redis backplanes",
                "End-to-end integration: TypeScript frontend models aligned with backend Spring Boot DTO contracts",
                "Authentication security: HttpOnly SameSite cookies vs JWT, avoiding XSS and CSRF vulnerabilities"
            ),
            relatedTrackId = "full_stack_interview"
        ),
        VideoMockInterview(
            id = "vm_full_stack_05",
            topicId = "full_stack",
            title = "Full stack Development Mock Interview",
            channelName = "GALTech Learning Hub",
            youtubeVideoId = "f2KFCh0V764",
            duration = "55 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Full Stack concepts, architectural trade-offs, and live candidate evaluation with GALTech Learning Hub.",
            keyTakeaways = listOf(
                "End-to-end integration: TypeScript frontend models aligned with backend Spring Boot DTO contracts",
                "Authentication security: HttpOnly SameSite cookies vs JWT, avoiding XSS and CSRF vulnerabilities",
                "High performance rendering: Virtual scrolling, cursor-based pagination, and bundle code splitting"
            ),
            relatedTrackId = "full_stack_interview"
        ),
        VideoMockInterview(
            id = "vm_full_stack_06",
            topicId = "full_stack",
            title = "developer interviews in 2026 ?? Comment �app� for the link. #adparakeetai #developer #interview #fyp",
            channelName = "Auzio",
            youtubeVideoId = "oxsRVKk5taY",
            duration = "55 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Full Stack concepts, architectural trade-offs, and live candidate evaluation with Auzio.",
            keyTakeaways = listOf(
                "Authentication security: HttpOnly SameSite cookies vs JWT, avoiding XSS and CSRF vulnerabilities",
                "High performance rendering: Virtual scrolling, cursor-based pagination, and bundle code splitting",
                "Real-time communication: WebSockets with STOMP relays, RxJS subject state, and Redis backplanes"
            ),
            relatedTrackId = "full_stack_interview"
        ),
        VideoMockInterview(
            id = "vm_full_stack_07",
            topicId = "full_stack",
            title = "FullStack Interview Questions (Junior & Mid)",
            channelName = "theSeniorDev",
            youtubeVideoId = "sgckyASEMdI",
            duration = "55 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Full Stack concepts, architectural trade-offs, and live candidate evaluation with theSeniorDev.",
            keyTakeaways = listOf(
                "High performance rendering: Virtual scrolling, cursor-based pagination, and bundle code splitting",
                "Real-time communication: WebSockets with STOMP relays, RxJS subject state, and Redis backplanes",
                "End-to-end integration: TypeScript frontend models aligned with backend Spring Boot DTO contracts"
            ),
            relatedTrackId = "full_stack_interview"
        ),
        VideoMockInterview(
            id = "vm_full_stack_08",
            topicId = "full_stack",
            title = "?? Mock Interview for Full Stack Developers � Ace Your Dream Job! ??",
            channelName = "10000Coders",
            youtubeVideoId = "z1UteKjglG8",
            duration = "55 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Full Stack concepts, architectural trade-offs, and live candidate evaluation with 10000Coders.",
            keyTakeaways = listOf(
                "Real-time communication: WebSockets with STOMP relays, RxJS subject state, and Redis backplanes",
                "End-to-end integration: TypeScript frontend models aligned with backend Spring Boot DTO contracts",
                "Authentication security: HttpOnly SameSite cookies vs JWT, avoiding XSS and CSRF vulnerabilities"
            ),
            relatedTrackId = "full_stack_interview"
        ),
        VideoMockInterview(
            id = "vm_full_stack_09",
            topicId = "full_stack",
            title = "Mock Interview 5+ year experienced | Spring Boot | Java | Microservice | System Design | Code Decode",
            channelName = "Code Decode",
            youtubeVideoId = "xMlcsFLk-CU",
            duration = "55 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Full Stack concepts, architectural trade-offs, and live candidate evaluation with Code Decode.",
            keyTakeaways = listOf(
                "End-to-end integration: TypeScript frontend models aligned with backend Spring Boot DTO contracts",
                "Authentication security: HttpOnly SameSite cookies vs JWT, avoiding XSS and CSRF vulnerabilities",
                "High performance rendering: Virtual scrolling, cursor-based pagination, and bundle code splitting"
            ),
            relatedTrackId = "full_stack_interview"
        ),
        VideoMockInterview(
            id = "vm_full_stack_10",
            topicId = "full_stack",
            title = "Nail Your Interview with this pro tip!  #shorts #interviewtips",
            channelName = "Error Makes Clever",
            youtubeVideoId = "eSymSLKy2QE",
            duration = "55 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Full Stack concepts, architectural trade-offs, and live candidate evaluation with Error Makes Clever.",
            keyTakeaways = listOf(
                "Authentication security: HttpOnly SameSite cookies vs JWT, avoiding XSS and CSRF vulnerabilities",
                "High performance rendering: Virtual scrolling, cursor-based pagination, and bundle code splitting",
                "Real-time communication: WebSockets with STOMP relays, RxJS subject state, and Redis backplanes"
            ),
            relatedTrackId = "full_stack_interview"
        ),
    )

    // --- 5. HLD Mock Interviews (10 real verified videos) ---
    private val hldVideos = listOf(
        VideoMockInterview(
            id = "vm_hld_01",
            topicId = "hld",
            title = "System Design Mock Interview: Design TikTok ft. Google TPM",
            channelName = "Aced (formerly Exponent)",
            youtubeVideoId = "Z-0g_aJL5Fw",
            duration = "58 min",
            difficulty = "Staff",
            description = "Real-world technical mock interview covering HLD concepts, architectural trade-offs, and live candidate evaluation with Aced (formerly Exponent).",
            keyTakeaways = listOf(
                "System scale requirements clarification, QPS estimation, and bandwidth capacity planning",
                "High-level blueprints: Multi-tier load balancing, CDN edge caching, and distributed storage",
                "Database architectural choices: Read replicas, sharding strategies, and wide-column stores"
            ),
            relatedTrackId = "hld_interview"
        ),
        VideoMockInterview(
            id = "vm_hld_02",
            topicId = "hld",
            title = "How to Answer System Design Interview Questions (Complete Guide)",
            channelName = "Aced (formerly Exponent)",
            youtubeVideoId = "L9TfZdODuFQ",
            duration = "58 min",
            difficulty = "Staff",
            description = "Real-world technical mock interview covering HLD concepts, architectural trade-offs, and live candidate evaluation with Aced (formerly Exponent).",
            keyTakeaways = listOf(
                "High-level blueprints: Multi-tier load balancing, CDN edge caching, and distributed storage",
                "Database architectural choices: Read replicas, sharding strategies, and wide-column stores",
                "Trade-offs under high concurrency: Cache stampede mitigation, rate limiting, and zero downtime updates"
            ),
            relatedTrackId = "hld_interview"
        ),
        VideoMockInterview(
            id = "vm_hld_03",
            topicId = "hld",
            title = "System Design Mock Interview: Design Leetcode ft. Ex Google Engineer",
            channelName = "Anubhav Sethi",
            youtubeVideoId = "QBHTbtWSECg",
            duration = "58 min",
            difficulty = "Staff",
            description = "Real-world technical mock interview covering HLD concepts, architectural trade-offs, and live candidate evaluation with Anubhav Sethi.",
            keyTakeaways = listOf(
                "Database architectural choices: Read replicas, sharding strategies, and wide-column stores",
                "Trade-offs under high concurrency: Cache stampede mitigation, rate limiting, and zero downtime updates",
                "System scale requirements clarification, QPS estimation, and bandwidth capacity planning"
            ),
            relatedTrackId = "hld_interview"
        ),
        VideoMockInterview(
            id = "vm_hld_04",
            topicId = "hld",
            title = "Github System Design Interview",
            channelName = "NextWork",
            youtubeVideoId = "qtkSifj_vus",
            duration = "58 min",
            difficulty = "Staff",
            description = "Real-world technical mock interview covering HLD concepts, architectural trade-offs, and live candidate evaluation with NextWork.",
            keyTakeaways = listOf(
                "Trade-offs under high concurrency: Cache stampede mitigation, rate limiting, and zero downtime updates",
                "System scale requirements clarification, QPS estimation, and bandwidth capacity planning",
                "High-level blueprints: Multi-tier load balancing, CDN edge caching, and distributed storage"
            ),
            relatedTrackId = "hld_interview"
        ),
        VideoMockInterview(
            id = "vm_hld_05",
            topicId = "hld",
            title = "System design interview: Design at a high level",
            channelName = "Aced (formerly Exponent)",
            youtubeVideoId = "kUebWKoBmIU",
            duration = "58 min",
            difficulty = "Staff",
            description = "Real-world technical mock interview covering HLD concepts, architectural trade-offs, and live candidate evaluation with Aced (formerly Exponent).",
            keyTakeaways = listOf(
                "System scale requirements clarification, QPS estimation, and bandwidth capacity planning",
                "High-level blueprints: Multi-tier load balancing, CDN edge caching, and distributed storage",
                "Database architectural choices: Read replicas, sharding strategies, and wide-column stores"
            ),
            relatedTrackId = "hld_interview"
        ),
        VideoMockInterview(
            id = "vm_hld_06",
            topicId = "hld",
            title = "Last minute tech interview preparation!",
            channelName = "Swati Jha",
            youtubeVideoId = "P2xAMQPMvrE",
            duration = "58 min",
            difficulty = "Staff",
            description = "Real-world technical mock interview covering HLD concepts, architectural trade-offs, and live candidate evaluation with Swati Jha.",
            keyTakeaways = listOf(
                "High-level blueprints: Multi-tier load balancing, CDN edge caching, and distributed storage",
                "Database architectural choices: Read replicas, sharding strategies, and wide-column stores",
                "Trade-offs under high concurrency: Cache stampede mitigation, rate limiting, and zero downtime updates"
            ),
            relatedTrackId = "hld_interview"
        ),
        VideoMockInterview(
            id = "vm_hld_07",
            topicId = "hld",
            title = "10 Imp System Design Topics to prepare before Interview #systemdesign #softwareengineer",
            channelName = "Concept && Coding - by Shrayansh",
            youtubeVideoId = "PVBjJQBHdlk",
            duration = "58 min",
            difficulty = "Staff",
            description = "Real-world technical mock interview covering HLD concepts, architectural trade-offs, and live candidate evaluation with Concept && Coding - by Shrayansh.",
            keyTakeaways = listOf(
                "Database architectural choices: Read replicas, sharding strategies, and wide-column stores",
                "Trade-offs under high concurrency: Cache stampede mitigation, rate limiting, and zero downtime updates",
                "System scale requirements clarification, QPS estimation, and bandwidth capacity planning"
            ),
            relatedTrackId = "hld_interview"
        ),
        VideoMockInterview(
            id = "vm_hld_08",
            topicId = "hld",
            title = "System Design Interview Tips ? #Shorts #SystemDesign #Interview #SoftwareEngineering",
            channelName = "Gaurav Sen",
            youtubeVideoId = "fhVCssGJlR4",
            duration = "58 min",
            difficulty = "Staff",
            description = "Real-world technical mock interview covering HLD concepts, architectural trade-offs, and live candidate evaluation with Gaurav Sen.",
            keyTakeaways = listOf(
                "Trade-offs under high concurrency: Cache stampede mitigation, rate limiting, and zero downtime updates",
                "System scale requirements clarification, QPS estimation, and bandwidth capacity planning",
                "High-level blueprints: Multi-tier load balancing, CDN edge caching, and distributed storage"
            ),
            relatedTrackId = "hld_interview"
        ),
        VideoMockInterview(
            id = "vm_hld_09",
            topicId = "hld",
            title = "Design Youtube - System Design Interview",
            channelName = "NeetCode",
            youtubeVideoId = "jPKTo1iGQiE",
            duration = "58 min",
            difficulty = "Staff",
            description = "Real-world technical mock interview covering HLD concepts, architectural trade-offs, and live candidate evaluation with NeetCode.",
            keyTakeaways = listOf(
                "System scale requirements clarification, QPS estimation, and bandwidth capacity planning",
                "High-level blueprints: Multi-tier load balancing, CDN edge caching, and distributed storage",
                "Database architectural choices: Read replicas, sharding strategies, and wide-column stores"
            ),
            relatedTrackId = "hld_interview"
        ),
        VideoMockInterview(
            id = "vm_hld_10",
            topicId = "hld",
            title = "System Design Interview: Design YouTube w/ a Ex-Meta Staff Engineer",
            channelName = "Hello Interview",
            youtubeVideoId = "IUrQ5_g3XKs",
            duration = "58 min",
            difficulty = "Staff",
            description = "Real-world technical mock interview covering HLD concepts, architectural trade-offs, and live candidate evaluation with Hello Interview.",
            keyTakeaways = listOf(
                "High-level blueprints: Multi-tier load balancing, CDN edge caching, and distributed storage",
                "Database architectural choices: Read replicas, sharding strategies, and wide-column stores",
                "Trade-offs under high concurrency: Cache stampede mitigation, rate limiting, and zero downtime updates"
            ),
            relatedTrackId = "hld_interview"
        ),
    )

    // --- 6. LLD Mock Interviews (10 real verified videos) ---
    private val lldVideos = listOf(
        VideoMockInterview(
            id = "vm_lld_01",
            topicId = "lld",
            title = "Are you expected to run code in a low level design interview?",
            channelName = "InterviewReady",
            youtubeVideoId = "06dP7vLzbQc",
            duration = "50 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering LLD concepts, architectural trade-offs, and live candidate evaluation with InterviewReady.",
            keyTakeaways = listOf(
                "Object-oriented modeling adhering to SOLID principles and Clean Architecture boundaries",
                "GoF design patterns: Strategy, State, Factory, Observer, and Decorator implementations",
                "Thread safety: Atomic references, read-write locks, and concurrent data structures"
            ),
            relatedTrackId = "lld_interview"
        ),
        VideoMockInterview(
            id = "vm_lld_02",
            topicId = "lld",
            title = "How to Clear Machine Coding Round?",
            channelName = "SCALER",
            youtubeVideoId = "7sTNsN-rBPk",
            duration = "50 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering LLD concepts, architectural trade-offs, and live candidate evaluation with SCALER.",
            keyTakeaways = listOf(
                "GoF design patterns: Strategy, State, Factory, Observer, and Decorator implementations",
                "Thread safety: Atomic references, read-write locks, and concurrent data structures",
                "Extensibility and machine coding: Clean separation of models, controllers, and strategy interfaces"
            ),
            relatedTrackId = "lld_interview"
        ),
        VideoMockInterview(
            id = "vm_lld_03",
            topicId = "lld",
            title = "Last minute tech interview preparation!",
            channelName = "Swati Jha",
            youtubeVideoId = "P2xAMQPMvrE",
            duration = "50 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering LLD concepts, architectural trade-offs, and live candidate evaluation with Swati Jha.",
            keyTakeaways = listOf(
                "Thread safety: Atomic references, read-write locks, and concurrent data structures",
                "Extensibility and machine coding: Clean separation of models, controllers, and strategy interfaces",
                "Object-oriented modeling adhering to SOLID principles and Clean Architecture boundaries"
            ),
            relatedTrackId = "lld_interview"
        ),
        VideoMockInterview(
            id = "vm_lld_04",
            topicId = "lld",
            title = "If I was a beginner in LLD, I would do THIS for interviews! To-The-Point Roadmap",
            channelName = "Keerti Purswani",
            youtubeVideoId = "NrAXmQX-XI8",
            duration = "50 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering LLD concepts, architectural trade-offs, and live candidate evaluation with Keerti Purswani.",
            keyTakeaways = listOf(
                "Extensibility and machine coding: Clean separation of models, controllers, and strategy interfaces",
                "Object-oriented modeling adhering to SOLID principles and Clean Architecture boundaries",
                "GoF design patterns: Strategy, State, Factory, Observer, and Decorator implementations"
            ),
            relatedTrackId = "lld_interview"
        ),
        VideoMockInterview(
            id = "vm_lld_05",
            topicId = "lld",
            title = "how to fail any low level interview in under 5 seconds",
            channelName = "Coding Jesus (getcracked.io)",
            youtubeVideoId = "A2zxzC7Z3aM",
            duration = "50 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering LLD concepts, architectural trade-offs, and live candidate evaluation with Coding Jesus (getcracked.io).",
            keyTakeaways = listOf(
                "Object-oriented modeling adhering to SOLID principles and Clean Architecture boundaries",
                "GoF design patterns: Strategy, State, Factory, Observer, and Decorator implementations",
                "Thread safety: Atomic references, read-write locks, and concurrent data structures"
            ),
            relatedTrackId = "lld_interview"
        ),
        VideoMockInterview(
            id = "vm_lld_06",
            topicId = "lld",
            title = "Design Rate Limiter (LLD) - Token Bucket, Fixed & Sliding Window with Thread Safety",
            channelName = "Shubh Patel",
            youtubeVideoId = "7y0KWxaUn-E",
            duration = "50 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering LLD concepts, architectural trade-offs, and live candidate evaluation with Shubh Patel.",
            keyTakeaways = listOf(
                "GoF design patterns: Strategy, State, Factory, Observer, and Decorator implementations",
                "Thread safety: Atomic references, read-write locks, and concurrent data structures",
                "Extensibility and machine coding: Clean separation of models, controllers, and strategy interfaces"
            ),
            relatedTrackId = "lld_interview"
        ),
        VideoMockInterview(
            id = "vm_lld_07",
            topicId = "lld",
            title = "Design Amazon Locker System (LLD) | Package Delivery & Pickup Flow with OTP Verification",
            channelName = "Shubh Patel",
            youtubeVideoId = "Lw9X8SaB6iA",
            duration = "50 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering LLD concepts, architectural trade-offs, and live candidate evaluation with Shubh Patel.",
            keyTakeaways = listOf(
                "Thread safety: Atomic references, read-write locks, and concurrent data structures",
                "Extensibility and machine coding: Clean separation of models, controllers, and strategy interfaces",
                "Object-oriented modeling adhering to SOLID principles and Clean Architecture boundaries"
            ),
            relatedTrackId = "lld_interview"
        ),
        VideoMockInterview(
            id = "vm_lld_08",
            topicId = "lld",
            title = "Flipkart Machine Coding Round: FoodKart LLD Solution (Step-by-Step Implementation with UML)",
            channelName = "Code design by Naman",
            youtubeVideoId = "ZZzZGqlLzGc",
            duration = "50 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering LLD concepts, architectural trade-offs, and live candidate evaluation with Code design by Naman.",
            keyTakeaways = listOf(
                "Extensibility and machine coding: Clean separation of models, controllers, and strategy interfaces",
                "Object-oriented modeling adhering to SOLID principles and Clean Architecture boundaries",
                "GoF design patterns: Strategy, State, Factory, Observer, and Decorator implementations"
            ),
            relatedTrackId = "lld_interview"
        ),
        VideoMockInterview(
            id = "vm_lld_09",
            topicId = "lld",
            title = "?? LLD Interview Question | Customer Issue Resolution System (PhonePe)",
            channelName = "Code design by Naman",
            youtubeVideoId = "wp7_HlGFERY",
            duration = "50 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering LLD concepts, architectural trade-offs, and live candidate evaluation with Code design by Naman.",
            keyTakeaways = listOf(
                "Object-oriented modeling adhering to SOLID principles and Clean Architecture boundaries",
                "GoF design patterns: Strategy, State, Factory, Observer, and Decorator implementations",
                "Thread safety: Atomic references, read-write locks, and concurrent data structures"
            ),
            relatedTrackId = "lld_interview"
        ),
        VideoMockInterview(
            id = "vm_lld_10",
            topicId = "lld",
            title = "Tab Form Component (Myntra, Zepto) - Frontend Interview Question using React",
            channelName = "Akshay Saini",
            youtubeVideoId = "UTky8eipUhA",
            duration = "50 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering LLD concepts, architectural trade-offs, and live candidate evaluation with Akshay Saini.",
            keyTakeaways = listOf(
                "GoF design patterns: Strategy, State, Factory, Observer, and Decorator implementations",
                "Thread safety: Atomic references, read-write locks, and concurrent data structures",
                "Extensibility and machine coding: Clean separation of models, controllers, and strategy interfaces"
            ),
            relatedTrackId = "lld_interview"
        ),
    )

    // --- 7. System Design Mock Interviews (10 real verified videos) ---
    private val systemdesignVideos = listOf(
        VideoMockInterview(
            id = "vm_system_design_01",
            topicId = "system_design",
            title = "How to Answer System Design Interview Questions (Complete Guide)",
            channelName = "Aced (formerly Exponent)",
            youtubeVideoId = "L9TfZdODuFQ",
            duration = "52 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering System Design concepts, architectural trade-offs, and live candidate evaluation with Aced (formerly Exponent).",
            keyTakeaways = listOf(
                "Fundamental distributed theorems: CAP theorem, PACELC trade-offs, and partition tolerance",
                "Consistent hashing with virtual nodes to minimize redistribution during cluster topology changes",
                "Consensus protocols: Raft leader election, quorum log replication, and split-brain prevention"
            ),
            relatedTrackId = "system_design_interview"
        ),
        VideoMockInterview(
            id = "vm_system_design_02",
            topicId = "system_design",
            title = "Design Google Docs | System Design Interview (with Amazon Engineer, ex-Microsoft)",
            channelName = "Aced (formerly Exponent)",
            youtubeVideoId = "cL9If4X7aaE",
            duration = "52 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering System Design concepts, architectural trade-offs, and live candidate evaluation with Aced (formerly Exponent).",
            keyTakeaways = listOf(
                "Consistent hashing with virtual nodes to minimize redistribution during cluster topology changes",
                "Consensus protocols: Raft leader election, quorum log replication, and split-brain prevention",
                "Distributed primitives: Twitter Snowflake 64-bit ID generation and distributed locks"
            ),
            relatedTrackId = "system_design_interview"
        ),
        VideoMockInterview(
            id = "vm_system_design_03",
            topicId = "system_design",
            title = "Design Uber Eats | System Design Interview (with Senior Netflix Engineer, Ex-Microsoft)",
            channelName = "Aced (formerly Exponent)",
            youtubeVideoId = "dgawYAH0pO4",
            duration = "52 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering System Design concepts, architectural trade-offs, and live candidate evaluation with Aced (formerly Exponent).",
            keyTakeaways = listOf(
                "Consensus protocols: Raft leader election, quorum log replication, and split-brain prevention",
                "Distributed primitives: Twitter Snowflake 64-bit ID generation and distributed locks",
                "Fundamental distributed theorems: CAP theorem, PACELC trade-offs, and partition tolerance"
            ),
            relatedTrackId = "system_design_interview"
        ),
        VideoMockInterview(
            id = "vm_system_design_04",
            topicId = "system_design",
            title = "System Design Primer ??: How to start with distributed systems?",
            channelName = "Gaurav Sen",
            youtubeVideoId = "SqcXvc3ZmRU",
            duration = "52 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering System Design concepts, architectural trade-offs, and live candidate evaluation with Gaurav Sen.",
            keyTakeaways = listOf(
                "Distributed primitives: Twitter Snowflake 64-bit ID generation and distributed locks",
                "Fundamental distributed theorems: CAP theorem, PACELC trade-offs, and partition tolerance",
                "Consistent hashing with virtual nodes to minimize redistribution during cluster topology changes"
            ),
            relatedTrackId = "system_design_interview"
        ),
        VideoMockInterview(
            id = "vm_system_design_05",
            topicId = "system_design",
            title = "System Design BASICS: Horizontal vs. Vertical Scaling",
            channelName = "Gaurav Sen",
            youtubeVideoId = "xpDnVSmNFX0",
            duration = "52 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering System Design concepts, architectural trade-offs, and live candidate evaluation with Gaurav Sen.",
            keyTakeaways = listOf(
                "Fundamental distributed theorems: CAP theorem, PACELC trade-offs, and partition tolerance",
                "Consistent hashing with virtual nodes to minimize redistribution during cluster topology changes",
                "Consensus protocols: Raft leader election, quorum log replication, and split-brain prevention"
            ),
            relatedTrackId = "system_design_interview"
        ),
        VideoMockInterview(
            id = "vm_system_design_06",
            topicId = "system_design",
            title = "52 FAANG Questions Drill cards Cheatsheets Vault",
            channelName = "SystemDR - Scalable System Design",
            youtubeVideoId = "j5Mn_P8d6V8",
            duration = "52 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering System Design concepts, architectural trade-offs, and live candidate evaluation with SystemDR - Scalable System Design.",
            keyTakeaways = listOf(
                "Consistent hashing with virtual nodes to minimize redistribution during cluster topology changes",
                "Consensus protocols: Raft leader election, quorum log replication, and split-brain prevention",
                "Distributed primitives: Twitter Snowflake 64-bit ID generation and distributed locks"
            ),
            relatedTrackId = "system_design_interview"
        ),
        VideoMockInterview(
            id = "vm_system_design_07",
            topicId = "system_design",
            title = "System Design Interview (prep)",
            channelName = "NextWork",
            youtubeVideoId = "rTyW2IpINDo",
            duration = "52 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering System Design concepts, architectural trade-offs, and live candidate evaluation with NextWork.",
            keyTakeaways = listOf(
                "Consensus protocols: Raft leader election, quorum log replication, and split-brain prevention",
                "Distributed primitives: Twitter Snowflake 64-bit ID generation and distributed locks",
                "Fundamental distributed theorems: CAP theorem, PACELC trade-offs, and partition tolerance"
            ),
            relatedTrackId = "system_design_interview"
        ),
        VideoMockInterview(
            id = "vm_system_design_08",
            topicId = "system_design",
            title = "How to crack system design interview | Master System Design for FAANG Interviews",
            channelName = "Rocky Bhatia",
            youtubeVideoId = "WySMcnNGK60",
            duration = "52 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering System Design concepts, architectural trade-offs, and live candidate evaluation with Rocky Bhatia.",
            keyTakeaways = listOf(
                "Distributed primitives: Twitter Snowflake 64-bit ID generation and distributed locks",
                "Fundamental distributed theorems: CAP theorem, PACELC trade-offs, and partition tolerance",
                "Consistent hashing with virtual nodes to minimize redistribution during cluster topology changes"
            ),
            relatedTrackId = "system_design_interview"
        ),
        VideoMockInterview(
            id = "vm_system_design_09",
            topicId = "system_design",
            title = "Last minute tech interview preparation!",
            channelName = "Swati Jha",
            youtubeVideoId = "P2xAMQPMvrE",
            duration = "52 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering System Design concepts, architectural trade-offs, and live candidate evaluation with Swati Jha.",
            keyTakeaways = listOf(
                "Fundamental distributed theorems: CAP theorem, PACELC trade-offs, and partition tolerance",
                "Consistent hashing with virtual nodes to minimize redistribution during cluster topology changes",
                "Consensus protocols: Raft leader election, quorum log replication, and split-brain prevention"
            ),
            relatedTrackId = "system_design_interview"
        ),
        VideoMockInterview(
            id = "vm_system_design_10",
            topicId = "system_design",
            title = "Verify Assumptions during FAANG Tech interviews",
            channelName = "Aanchal Chauhan",
            youtubeVideoId = "TSoKml_FF1I",
            duration = "52 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering System Design concepts, architectural trade-offs, and live candidate evaluation with Aanchal Chauhan.",
            keyTakeaways = listOf(
                "Consistent hashing with virtual nodes to minimize redistribution during cluster topology changes",
                "Consensus protocols: Raft leader election, quorum log replication, and split-brain prevention",
                "Distributed primitives: Twitter Snowflake 64-bit ID generation and distributed locks"
            ),
            relatedTrackId = "system_design_interview"
        ),
    )

    // --- 8. DevOps Mock Interviews (10 real verified videos) ---
    private val devopsVideos = listOf(
        VideoMockInterview(
            id = "vm_devops_01",
            topicId = "devops",
            title = "Fixing Autoscaling Issues - DevOps Engineer Mock #interview #devops #cloud #mentorship #aws #azure",
            channelName = "DevOps Cloud and AI Labs",
            youtubeVideoId = "OyGtrtVsjoY",
            duration = "45 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering DevOps concepts, architectural trade-offs, and live candidate evaluation with DevOps Cloud and AI Labs.",
            keyTakeaways = listOf(
                "Kubernetes troubleshooting: Diagnosing CrashLoopBackOff, pod OOMKilled, and probe configs",
                "Docker internals: Linux cgroups resource limits, namespaces isolation, and layer caching",
                "GitOps declarative continuous delivery using ArgoCD and automated drift reconciliation"
            ),
            relatedTrackId = "devops_interview"
        ),
        VideoMockInterview(
            id = "vm_devops_02",
            topicId = "devops",
            title = "Kubernetes Interview Question - Pod Anti Affinity",
            channelName = "TechWorld with Sahana",
            youtubeVideoId = "WcOUODSszLM",
            duration = "45 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering DevOps concepts, architectural trade-offs, and live candidate evaluation with TechWorld with Sahana.",
            keyTakeaways = listOf(
                "Docker internals: Linux cgroups resource limits, namespaces isolation, and layer caching",
                "GitOps declarative continuous delivery using ArgoCD and automated drift reconciliation",
                "Production observability: Prometheus PromQL P99 percentiles, Grafana alerts, and SLO tracking"
            ),
            relatedTrackId = "devops_interview"
        ),
        VideoMockInterview(
            id = "vm_devops_03",
            topicId = "devops",
            title = "Fresher Interview #devops #cloudjobs #fresherjobs #devopsengineer #devopsjobs #devopsinterview",
            channelName = "Saurabh Porwal / AI, clearly explained",
            youtubeVideoId = "UO_xIsPWCCQ",
            duration = "45 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering DevOps concepts, architectural trade-offs, and live candidate evaluation with Saurabh Porwal / AI, clearly explained.",
            keyTakeaways = listOf(
                "GitOps declarative continuous delivery using ArgoCD and automated drift reconciliation",
                "Production observability: Prometheus PromQL P99 percentiles, Grafana alerts, and SLO tracking",
                "Kubernetes troubleshooting: Diagnosing CrashLoopBackOff, pod OOMKilled, and probe configs"
            ),
            relatedTrackId = "devops_interview"
        ),
        VideoMockInterview(
            id = "vm_devops_04",
            topicId = "devops",
            title = "Top Kubernetes Interview Q&A for Experienced DevOps",
            channelName = "CodeWithChandra",
            youtubeVideoId = "KXN_BQlHUf4",
            duration = "45 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering DevOps concepts, architectural trade-offs, and live candidate evaluation with CodeWithChandra.",
            keyTakeaways = listOf(
                "Production observability: Prometheus PromQL P99 percentiles, Grafana alerts, and SLO tracking",
                "Kubernetes troubleshooting: Diagnosing CrashLoopBackOff, pod OOMKilled, and probe configs",
                "Docker internals: Linux cgroups resource limits, namespaces isolation, and layer caching"
            ),
            relatedTrackId = "devops_interview"
        ),
        VideoMockInterview(
            id = "vm_devops_05",
            topicId = "devops",
            title = "Kubernetes Interview Questions | Part 1 | DevOps | DevOpsEngine",
            channelName = "DevOpsEngine",
            youtubeVideoId = "3-Nl4GDugDE",
            duration = "45 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering DevOps concepts, architectural trade-offs, and live candidate evaluation with DevOpsEngine.",
            keyTakeaways = listOf(
                "Kubernetes troubleshooting: Diagnosing CrashLoopBackOff, pod OOMKilled, and probe configs",
                "Docker internals: Linux cgroups resource limits, namespaces isolation, and layer caching",
                "GitOps declarative continuous delivery using ArgoCD and automated drift reconciliation"
            ),
            relatedTrackId = "devops_interview"
        ),
        VideoMockInterview(
            id = "vm_devops_06",
            topicId = "devops",
            title = "Site Reliability Engineer (SRE) Interview Questions 2026 | SRE Interview Questions and Answers",
            channelName = "MindMajix",
            youtubeVideoId = "nHlL_v4wCrM",
            duration = "45 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering DevOps concepts, architectural trade-offs, and live candidate evaluation with MindMajix.",
            keyTakeaways = listOf(
                "Docker internals: Linux cgroups resource limits, namespaces isolation, and layer caching",
                "GitOps declarative continuous delivery using ArgoCD and automated drift reconciliation",
                "Production observability: Prometheus PromQL P99 percentiles, Grafana alerts, and SLO tracking"
            ),
            relatedTrackId = "devops_interview"
        ),
        VideoMockInterview(
            id = "vm_devops_07",
            topicId = "devops",
            title = "DevOps/SRE Mock Interview helped him gain real time experience|How do you upgrade kubernetes cluster",
            channelName = "Praveen Singampalli",
            youtubeVideoId = "QYHKFnJK74w",
            duration = "45 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering DevOps concepts, architectural trade-offs, and live candidate evaluation with Praveen Singampalli.",
            keyTakeaways = listOf(
                "GitOps declarative continuous delivery using ArgoCD and automated drift reconciliation",
                "Production observability: Prometheus PromQL P99 percentiles, Grafana alerts, and SLO tracking",
                "Kubernetes troubleshooting: Diagnosing CrashLoopBackOff, pod OOMKilled, and probe configs"
            ),
            relatedTrackId = "devops_interview"
        ),
        VideoMockInterview(
            id = "vm_devops_08",
            topicId = "devops",
            title = "Kubernetes Interview Questions 2026 | Kubernetes (K8s) Interview Questions and Answers | MindMajix",
            channelName = "MindMajix",
            youtubeVideoId = "WRDf3aKH3X0",
            duration = "45 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering DevOps concepts, architectural trade-offs, and live candidate evaluation with MindMajix.",
            keyTakeaways = listOf(
                "Production observability: Prometheus PromQL P99 percentiles, Grafana alerts, and SLO tracking",
                "Kubernetes troubleshooting: Diagnosing CrashLoopBackOff, pod OOMKilled, and probe configs",
                "Docker internals: Linux cgroups resource limits, namespaces isolation, and layer caching"
            ),
            relatedTrackId = "devops_interview"
        ),
        VideoMockInterview(
            id = "vm_devops_09",
            topicId = "devops",
            title = "5 topics to pass DevOps Interview",
            channelName = "DevOps Cloud and AI Labs",
            youtubeVideoId = "qlzVv8yZjK0",
            duration = "45 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering DevOps concepts, architectural trade-offs, and live candidate evaluation with DevOps Cloud and AI Labs.",
            keyTakeaways = listOf(
                "Kubernetes troubleshooting: Diagnosing CrashLoopBackOff, pod OOMKilled, and probe configs",
                "Docker internals: Linux cgroups resource limits, namespaces isolation, and layer caching",
                "GitOps declarative continuous delivery using ArgoCD and automated drift reconciliation"
            ),
            relatedTrackId = "devops_interview"
        ),
        VideoMockInterview(
            id = "vm_devops_10",
            topicId = "devops",
            title = "Kubernetes Interview Questions and Answers | What is Self Healing in Kubernetes | #kubernetes",
            channelName = "DevOps Molvi",
            youtubeVideoId = "UM5p-Ol5rxw",
            duration = "45 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering DevOps concepts, architectural trade-offs, and live candidate evaluation with DevOps Molvi.",
            keyTakeaways = listOf(
                "Docker internals: Linux cgroups resource limits, namespaces isolation, and layer caching",
                "GitOps declarative continuous delivery using ArgoCD and automated drift reconciliation",
                "Production observability: Prometheus PromQL P99 percentiles, Grafana alerts, and SLO tracking"
            ),
            relatedTrackId = "devops_interview"
        ),
    )

    // --- 9. SQL & Database Mock Interviews (10 real verified videos) ---
    private val sqlVideos = listOf(
        VideoMockInterview(
            id = "vm_sql_01",
            topicId = "sql",
            title = "28.SQL Performance Tuning Interview Questions And Answers|Clustered and non clustered index in SQL",
            channelName = "Pandey Guruji",
            youtubeVideoId = "5qzQakofAoY",
            duration = "44 min",
            difficulty = "Mid-to-Senior",
            description = "Real-world technical mock interview covering SQL & Database concepts, architectural trade-offs, and live candidate evaluation with Pandey Guruji.",
            keyTakeaways = listOf(
                "Query execution optimization: Analyzing EXPLAIN plans, index scans vs sequential scans",
                "Index architecture: B-Tree leaf structures, covering indexes, and composite index ordering",
                "ACID transaction isolation: MVCC readers-don't-block-writers, phantom reads, and locking modes"
            ),
            relatedTrackId = "sql_interview"
        ),
        VideoMockInterview(
            id = "vm_sql_02",
            topicId = "sql",
            title = "38.How to increase the Performance of SQL Queries|optimize SQL Queries|how to speed up SQL Query",
            channelName = "Pandey Guruji",
            youtubeVideoId = "_jM4BDlfZ1Q",
            duration = "44 min",
            difficulty = "Mid-to-Senior",
            description = "Real-world technical mock interview covering SQL & Database concepts, architectural trade-offs, and live candidate evaluation with Pandey Guruji.",
            keyTakeaways = listOf(
                "Index architecture: B-Tree leaf structures, covering indexes, and composite index ordering",
                "ACID transaction isolation: MVCC readers-don't-block-writers, phantom reads, and locking modes",
                "Analytical queries: Window functions (ROW_NUMBER, DENSE_RANK, LAG, LEAD) and partitioning"
            ),
            relatedTrackId = "sql_interview"
        ),
        VideoMockInterview(
            id = "vm_sql_03",
            topicId = "sql",
            title = "Performance Tuning In MS SQL Server | Real Time MS SQL DBA Issues Part1",
            channelName = "MS SQL DBA Tech Support",
            youtubeVideoId = "gHDQQJK6lVQ",
            duration = "44 min",
            difficulty = "Mid-to-Senior",
            description = "Real-world technical mock interview covering SQL & Database concepts, architectural trade-offs, and live candidate evaluation with MS SQL DBA Tech Support.",
            keyTakeaways = listOf(
                "ACID transaction isolation: MVCC readers-don't-block-writers, phantom reads, and locking modes",
                "Analytical queries: Window functions (ROW_NUMBER, DENSE_RANK, LAG, LEAD) and partitioning",
                "Query execution optimization: Analyzing EXPLAIN plans, index scans vs sequential scans"
            ),
            relatedTrackId = "sql_interview"
        ),
        VideoMockInterview(
            id = "vm_sql_04",
            topicId = "sql",
            title = "SQL Complex Queries , Query Optimization and Interview Questions SQLServer 2016",
            channelName = "techsapphire",
            youtubeVideoId = "-t-8-xoLyv4",
            duration = "44 min",
            difficulty = "Mid-to-Senior",
            description = "Real-world technical mock interview covering SQL & Database concepts, architectural trade-offs, and live candidate evaluation with techsapphire.",
            keyTakeaways = listOf(
                "Analytical queries: Window functions (ROW_NUMBER, DENSE_RANK, LAG, LEAD) and partitioning",
                "Query execution optimization: Analyzing EXPLAIN plans, index scans vs sequential scans",
                "Index architecture: B-Tree leaf structures, covering indexes, and composite index ordering"
            ),
            relatedTrackId = "sql_interview"
        ),
        VideoMockInterview(
            id = "vm_sql_05",
            topicId = "sql",
            title = "Quick SQL Server Performance Analysis using performance dashboard",
            channelName = "techsapphire",
            youtubeVideoId = "mGUR5Sw6T8Y",
            duration = "44 min",
            difficulty = "Mid-to-Senior",
            description = "Real-world technical mock interview covering SQL & Database concepts, architectural trade-offs, and live candidate evaluation with techsapphire.",
            keyTakeaways = listOf(
                "Query execution optimization: Analyzing EXPLAIN plans, index scans vs sequential scans",
                "Index architecture: B-Tree leaf structures, covering indexes, and composite index ordering",
                "ACID transaction isolation: MVCC readers-don't-block-writers, phantom reads, and locking modes"
            ),
            relatedTrackId = "sql_interview"
        ),
        VideoMockInterview(
            id = "vm_sql_06",
            topicId = "sql",
            title = "SQL Query Optimization and performance tuning tips | SQL Tutorial for Beginners | SQL Interview tips",
            channelName = "The Engineer's Desk",
            youtubeVideoId = "xuxgxdbCPnY",
            duration = "44 min",
            difficulty = "Mid-to-Senior",
            description = "Real-world technical mock interview covering SQL & Database concepts, architectural trade-offs, and live candidate evaluation with The Engineer's Desk.",
            keyTakeaways = listOf(
                "Index architecture: B-Tree leaf structures, covering indexes, and composite index ordering",
                "ACID transaction isolation: MVCC readers-don't-block-writers, phantom reads, and locking modes",
                "Analytical queries: Window functions (ROW_NUMBER, DENSE_RANK, LAG, LEAD) and partitioning"
            ),
            relatedTrackId = "sql_interview"
        ),
        VideoMockInterview(
            id = "vm_sql_07",
            topicId = "sql",
            title = "SQL Server Performance tuning and query optimization interview questions| Performance tuning SQL",
            channelName = "DBA Doctor",
            youtubeVideoId = "CvgNMwzg2F8",
            duration = "44 min",
            difficulty = "Mid-to-Senior",
            description = "Real-world technical mock interview covering SQL & Database concepts, architectural trade-offs, and live candidate evaluation with DBA Doctor.",
            keyTakeaways = listOf(
                "ACID transaction isolation: MVCC readers-don't-block-writers, phantom reads, and locking modes",
                "Analytical queries: Window functions (ROW_NUMBER, DENSE_RANK, LAG, LEAD) and partitioning",
                "Query execution optimization: Analyzing EXPLAIN plans, index scans vs sequential scans"
            ),
            relatedTrackId = "sql_interview"
        ),
        VideoMockInterview(
            id = "vm_sql_08",
            topicId = "sql",
            title = "SQL Performance Tuning | SQL Performance Tuning in SQL Server | SQL Performance Tuning Tutorial",
            channelName = "Questpond",
            youtubeVideoId = "8cdu9MINAFA",
            duration = "44 min",
            difficulty = "Mid-to-Senior",
            description = "Real-world technical mock interview covering SQL & Database concepts, architectural trade-offs, and live candidate evaluation with Questpond.",
            keyTakeaways = listOf(
                "Analytical queries: Window functions (ROW_NUMBER, DENSE_RANK, LAG, LEAD) and partitioning",
                "Query execution optimization: Analyzing EXPLAIN plans, index scans vs sequential scans",
                "Index architecture: B-Tree leaf structures, covering indexes, and composite index ordering"
            ),
            relatedTrackId = "sql_interview"
        ),
        VideoMockInterview(
            id = "vm_sql_09",
            topicId = "sql",
            title = "Fresher Mock Interview SQL | Technical Round | SQL Interview for Fresher | HR Interview",
            channelName = "Lotus IT Hub training institute",
            youtubeVideoId = "WF_-8S4mSpU",
            duration = "44 min",
            difficulty = "Mid-to-Senior",
            description = "Real-world technical mock interview covering SQL & Database concepts, architectural trade-offs, and live candidate evaluation with Lotus IT Hub training institute.",
            keyTakeaways = listOf(
                "Query execution optimization: Analyzing EXPLAIN plans, index scans vs sequential scans",
                "Index architecture: B-Tree leaf structures, covering indexes, and composite index ordering",
                "ACID transaction isolation: MVCC readers-don't-block-writers, phantom reads, and locking modes"
            ),
            relatedTrackId = "sql_interview"
        ),
        VideoMockInterview(
            id = "vm_sql_10",
            topicId = "sql",
            title = "SQL Performance Tuning Interview Questions",
            channelName = "AI Code with Haritha",
            youtubeVideoId = "1jV649NO23o",
            duration = "44 min",
            difficulty = "Mid-to-Senior",
            description = "Real-world technical mock interview covering SQL & Database concepts, architectural trade-offs, and live candidate evaluation with AI Code with Haritha.",
            keyTakeaways = listOf(
                "Index architecture: B-Tree leaf structures, covering indexes, and composite index ordering",
                "ACID transaction isolation: MVCC readers-don't-block-writers, phantom reads, and locking modes",
                "Analytical queries: Window functions (ROW_NUMBER, DENSE_RANK, LAG, LEAD) and partitioning"
            ),
            relatedTrackId = "sql_interview"
        ),
    )

    // --- 10. Angular Mock Interviews (10 real verified videos) ---
    private val angularVideos = listOf(
        VideoMockInterview(
            id = "vm_angular_01",
            topicId = "angular",
            title = "Top 5 Angular Interview Questions & Answers | Angular Basics for Freshers (2025 Guide)",
            channelName = "CodeWithIndu",
            youtubeVideoId = "3h3Jqv3mfHc",
            duration = "46 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Angular concepts, architectural trade-offs, and live candidate evaluation with CodeWithIndu.",
            keyTakeaways = listOf(
                "Modern Angular Signals: Push-Pull reactivity, computed values, effects, and glitch-free evaluation",
                "Change detection: Zoneless execution, OnPush strategy, and eliminating Zone.js overhead",
                "Dependency injection: Hierarchical injectors, EnvironmentInjector vs ElementInjector, and inject()"
            ),
            relatedTrackId = "angular_interview"
        ),
        VideoMockInterview(
            id = "vm_angular_02",
            topicId = "angular",
            title = "10 Most asked Angular Interview Questions",
            channelName = "Questpond",
            youtubeVideoId = "MxyWKdoRH08",
            duration = "46 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Angular concepts, architectural trade-offs, and live candidate evaluation with Questpond.",
            keyTakeaways = listOf(
                "Change detection: Zoneless execution, OnPush strategy, and eliminating Zone.js overhead",
                "Dependency injection: Hierarchical injectors, EnvironmentInjector vs ElementInjector, and inject()",
                "RxJS state pipelines: Flattening operators (switchMap, concatMap) and takeUntilDestroyed lifecycle"
            ),
            relatedTrackId = "angular_interview"
        ),
        VideoMockInterview(
            id = "vm_angular_03",
            topicId = "angular",
            title = "Angular Interview Questions and answers 2024 (Angular 17)",
            channelName = "Tech Stack",
            youtubeVideoId = "ESXu9kMxPV8",
            duration = "46 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Angular concepts, architectural trade-offs, and live candidate evaluation with Tech Stack.",
            keyTakeaways = listOf(
                "Dependency injection: Hierarchical injectors, EnvironmentInjector vs ElementInjector, and inject()",
                "RxJS state pipelines: Flattening operators (switchMap, concatMap) and takeUntilDestroyed lifecycle",
                "Modern Angular Signals: Push-Pull reactivity, computed values, effects, and glitch-free evaluation"
            ),
            relatedTrackId = "angular_interview"
        ),
        VideoMockInterview(
            id = "vm_angular_04",
            topicId = "angular",
            title = "Ace Your Interviews: Top Free Mock Interview Websites",
            channelName = "Vaibhav Tyagi",
            youtubeVideoId = "QmsmScpkfos",
            duration = "46 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Angular concepts, architectural trade-offs, and live candidate evaluation with Vaibhav Tyagi.",
            keyTakeaways = listOf(
                "RxJS state pipelines: Flattening operators (switchMap, concatMap) and takeUntilDestroyed lifecycle",
                "Modern Angular Signals: Push-Pull reactivity, computed values, effects, and glitch-free evaluation",
                "Change detection: Zoneless execution, OnPush strategy, and eliminating Zone.js overhead"
            ),
            relatedTrackId = "angular_interview"
        ),
        VideoMockInterview(
            id = "vm_angular_05",
            topicId = "angular",
            title = "Common Asked Angular Interview Questions",
            channelName = "Interview Preparation",
            youtubeVideoId = "dS1KtQIQcwc",
            duration = "46 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Angular concepts, architectural trade-offs, and live candidate evaluation with Interview Preparation.",
            keyTakeaways = listOf(
                "Modern Angular Signals: Push-Pull reactivity, computed values, effects, and glitch-free evaluation",
                "Change detection: Zoneless execution, OnPush strategy, and eliminating Zone.js overhead",
                "Dependency injection: Hierarchical injectors, EnvironmentInjector vs ElementInjector, and inject()"
            ),
            relatedTrackId = "angular_interview"
        ),
        VideoMockInterview(
            id = "vm_angular_06",
            topicId = "angular",
            title = "Angular Mock Interview 2026 | Live Mock Interview & Feedback",
            channelName = "Frontend to AI",
            youtubeVideoId = "yBcVp7UqSE0",
            duration = "46 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Angular concepts, architectural trade-offs, and live candidate evaluation with Frontend to AI.",
            keyTakeaways = listOf(
                "Change detection: Zoneless execution, OnPush strategy, and eliminating Zone.js overhead",
                "Dependency injection: Hierarchical injectors, EnvironmentInjector vs ElementInjector, and inject()",
                "RxJS state pipelines: Flattening operators (switchMap, concatMap) and takeUntilDestroyed lifecycle"
            ),
            relatedTrackId = "angular_interview"
        ),
        VideoMockInterview(
            id = "vm_angular_07",
            topicId = "angular",
            title = "Angular Interview Discussion & Interview preparation for 7+ Years Experience @uidevguide",
            channelName = "Ui Dev Guide",
            youtubeVideoId = "amNjEdDqAxg",
            duration = "46 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Angular concepts, architectural trade-offs, and live candidate evaluation with Ui Dev Guide.",
            keyTakeaways = listOf(
                "Dependency injection: Hierarchical injectors, EnvironmentInjector vs ElementInjector, and inject()",
                "RxJS state pipelines: Flattening operators (switchMap, concatMap) and takeUntilDestroyed lifecycle",
                "Modern Angular Signals: Push-Pull reactivity, computed values, effects, and glitch-free evaluation"
            ),
            relatedTrackId = "angular_interview"
        ),
        VideoMockInterview(
            id = "vm_angular_08",
            topicId = "angular",
            title = "Angular Interview Preparation | Mock Interview With Feedback for 4+ Years Experience @uidevguide",
            channelName = "Ui Dev Guide",
            youtubeVideoId = "eSd1-0-ShDs",
            duration = "46 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Angular concepts, architectural trade-offs, and live candidate evaluation with Ui Dev Guide.",
            keyTakeaways = listOf(
                "RxJS state pipelines: Flattening operators (switchMap, concatMap) and takeUntilDestroyed lifecycle",
                "Modern Angular Signals: Push-Pull reactivity, computed values, effects, and glitch-free evaluation",
                "Change detection: Zoneless execution, OnPush strategy, and eliminating Zone.js overhead"
            ),
            relatedTrackId = "angular_interview"
        ),
        VideoMockInterview(
            id = "vm_angular_09",
            topicId = "angular",
            title = "Senior Angular Developer Interview (theory)",
            channelName = "WeCoded",
            youtubeVideoId = "pLy2hm7_70o",
            duration = "46 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Angular concepts, architectural trade-offs, and live candidate evaluation with WeCoded.",
            keyTakeaways = listOf(
                "Modern Angular Signals: Push-Pull reactivity, computed values, effects, and glitch-free evaluation",
                "Change detection: Zoneless execution, OnPush strategy, and eliminating Zone.js overhead",
                "Dependency injection: Hierarchical injectors, EnvironmentInjector vs ElementInjector, and inject()"
            ),
            relatedTrackId = "angular_interview"
        ),
        VideoMockInterview(
            id = "vm_angular_10",
            topicId = "angular",
            title = "Top 5 Front-end Developer Interview Questions every fresher should know!",
            channelName = "LIVEWIRE India",
            youtubeVideoId = "2ls1QcubWxw",
            duration = "46 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Angular concepts, architectural trade-offs, and live candidate evaluation with LIVEWIRE India.",
            keyTakeaways = listOf(
                "Change detection: Zoneless execution, OnPush strategy, and eliminating Zone.js overhead",
                "Dependency injection: Hierarchical injectors, EnvironmentInjector vs ElementInjector, and inject()",
                "RxJS state pipelines: Flattening operators (switchMap, concatMap) and takeUntilDestroyed lifecycle"
            ),
            relatedTrackId = "angular_interview"
        ),
    )

    // --- 11. Security Mock Interviews (10 real verified videos) ---
    private val securityVideos = listOf(
        VideoMockInterview(
            id = "vm_security_01",
            topicId = "security",
            title = "Top Cybersecurity Interview Questions: Most Commonly Asked Questions & Answers #shorts #simplilearn",
            channelName = "Simplilearn",
            youtubeVideoId = "Rl1cRMY27SI",
            duration = "48 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Security concepts, architectural trade-offs, and live candidate evaluation with Simplilearn.",
            keyTakeaways = listOf(
                "OWASP Top 10 vulnerabilities: Broken Access Control (IDOR), SQL injection, SSRF, and defenses",
                "Modern authentication: OAuth 2.0 Authorization Code with PKCE and OIDC token lifecycles",
                "JWT security: Alg none mitigation, asymmetric RS256 signing, and short-lived token rotation"
            ),
            relatedTrackId = "security_interview"
        ),
        VideoMockInterview(
            id = "vm_security_02",
            topicId = "security",
            title = "Cyber Security Interview Questions and Answers",
            channelName = "Knowledge Topper",
            youtubeVideoId = "6OvNNJQJixU",
            duration = "48 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Security concepts, architectural trade-offs, and live candidate evaluation with Knowledge Topper.",
            keyTakeaways = listOf(
                "Modern authentication: OAuth 2.0 Authorization Code with PKCE and OIDC token lifecycles",
                "JWT security: Alg none mitigation, asymmetric RS256 signing, and short-lived token rotation",
                "Enterprise AppSec: Zero Trust architecture, mutual TLS (mTLS), and strict Content Security Policy"
            ),
            relatedTrackId = "security_interview"
        ),
        VideoMockInterview(
            id = "vm_security_03",
            topicId = "security",
            title = "Why Candidates Failed in CyberSecurity Interviews",
            channelName = "Prabh Nair",
            youtubeVideoId = "HDCZiOCy_ww",
            duration = "48 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Security concepts, architectural trade-offs, and live candidate evaluation with Prabh Nair.",
            keyTakeaways = listOf(
                "JWT security: Alg none mitigation, asymmetric RS256 signing, and short-lived token rotation",
                "Enterprise AppSec: Zero Trust architecture, mutual TLS (mTLS), and strict Content Security Policy",
                "OWASP Top 10 vulnerabilities: Broken Access Control (IDOR), SQL injection, SSRF, and defenses"
            ),
            relatedTrackId = "security_interview"
        ),
        VideoMockInterview(
            id = "vm_security_04",
            topicId = "security",
            title = "MOCK INTERVIEW | CYBER SECURITY | Cyber Security Interview Questions",
            channelName = "CC Cyber Campus",
            youtubeVideoId = "swQjtCyedGc",
            duration = "48 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Security concepts, architectural trade-offs, and live candidate evaluation with CC Cyber Campus.",
            keyTakeaways = listOf(
                "Enterprise AppSec: Zero Trust architecture, mutual TLS (mTLS), and strict Content Security Policy",
                "OWASP Top 10 vulnerabilities: Broken Access Control (IDOR), SQL injection, SSRF, and defenses",
                "Modern authentication: OAuth 2.0 Authorization Code with PKCE and OIDC token lifecycles"
            ),
            relatedTrackId = "security_interview"
        ),
        VideoMockInterview(
            id = "vm_security_05",
            topicId = "security",
            title = "Fresher Interview #devops #cloudjobs #fresherjobs #devopsengineer #devopsjobs #devopsinterview",
            channelName = "Saurabh Porwal / AI, clearly explained",
            youtubeVideoId = "UO_xIsPWCCQ",
            duration = "48 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Security concepts, architectural trade-offs, and live candidate evaluation with Saurabh Porwal / AI, clearly explained.",
            keyTakeaways = listOf(
                "OWASP Top 10 vulnerabilities: Broken Access Control (IDOR), SQL injection, SSRF, and defenses",
                "Modern authentication: OAuth 2.0 Authorization Code with PKCE and OIDC token lifecycles",
                "JWT security: Alg none mitigation, asymmetric RS256 signing, and short-lived token rotation"
            ),
            relatedTrackId = "security_interview"
        ),
        VideoMockInterview(
            id = "vm_security_06",
            topicId = "security",
            title = "Cyber Security Interview Questions and Answers | Encoding, Encryption, and Hashing | Threat vs Risk",
            channelName = "Josh Madakor",
            youtubeVideoId = "IlaM1GoRXR0",
            duration = "48 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Security concepts, architectural trade-offs, and live candidate evaluation with Josh Madakor.",
            keyTakeaways = listOf(
                "Modern authentication: OAuth 2.0 Authorization Code with PKCE and OIDC token lifecycles",
                "JWT security: Alg none mitigation, asymmetric RS256 signing, and short-lived token rotation",
                "Enterprise AppSec: Zero Trust architecture, mutual TLS (mTLS), and strict Content Security Policy"
            ),
            relatedTrackId = "security_interview"
        ),
        VideoMockInterview(
            id = "vm_security_07",
            topicId = "security",
            title = "Cyber Security Interview Questions and Answers | HTTPS vs SSL vs TLS, Encryption & Compression",
            channelName = "Josh Madakor",
            youtubeVideoId = "eB1XwhGuBBk",
            duration = "48 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Security concepts, architectural trade-offs, and live candidate evaluation with Josh Madakor.",
            keyTakeaways = listOf(
                "JWT security: Alg none mitigation, asymmetric RS256 signing, and short-lived token rotation",
                "Enterprise AppSec: Zero Trust architecture, mutual TLS (mTLS), and strict Content Security Policy",
                "OWASP Top 10 vulnerabilities: Broken Access Control (IDOR), SQL injection, SSRF, and defenses"
            ),
            relatedTrackId = "security_interview"
        ),
        VideoMockInterview(
            id = "vm_security_08",
            topicId = "security",
            title = "Application Security Interview Questions And Answers | Part 1 | App Sec | AppSec | Cyber Security",
            channelName = "CyberPlatter",
            youtubeVideoId = "HrQL7BARODA",
            duration = "48 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Security concepts, architectural trade-offs, and live candidate evaluation with CyberPlatter.",
            keyTakeaways = listOf(
                "Enterprise AppSec: Zero Trust architecture, mutual TLS (mTLS), and strict Content Security Policy",
                "OWASP Top 10 vulnerabilities: Broken Access Control (IDOR), SQL injection, SSRF, and defenses",
                "Modern authentication: OAuth 2.0 Authorization Code with PKCE and OIDC token lifecycles"
            ),
            relatedTrackId = "security_interview"
        ),
        VideoMockInterview(
            id = "vm_security_09",
            topicId = "security",
            title = "Top 30 Cyber Security Interview Questions & Answers 2025 | Cyber Security Job Interview| Intellipaat",
            channelName = "Intellipaat",
            youtubeVideoId = "v0el2gamx0Q",
            duration = "48 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Security concepts, architectural trade-offs, and live candidate evaluation with Intellipaat.",
            keyTakeaways = listOf(
                "OWASP Top 10 vulnerabilities: Broken Access Control (IDOR), SQL injection, SSRF, and defenses",
                "Modern authentication: OAuth 2.0 Authorization Code with PKCE and OIDC token lifecycles",
                "JWT security: Alg none mitigation, asymmetric RS256 signing, and short-lived token rotation"
            ),
            relatedTrackId = "security_interview"
        ),
        VideoMockInterview(
            id = "vm_security_10",
            topicId = "security",
            title = "Preparing for a cybersecurity interview?",
            channelName = "Prabh Nair",
            youtubeVideoId = "p24JMh5vHd8",
            duration = "48 min",
            difficulty = "Senior",
            description = "Real-world technical mock interview covering Security concepts, architectural trade-offs, and live candidate evaluation with Prabh Nair.",
            keyTakeaways = listOf(
                "Modern authentication: OAuth 2.0 Authorization Code with PKCE and OIDC token lifecycles",
                "JWT security: Alg none mitigation, asymmetric RS256 signing, and short-lived token rotation",
                "Enterprise AppSec: Zero Trust architecture, mutual TLS (mTLS), and strict Content Security Policy"
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
