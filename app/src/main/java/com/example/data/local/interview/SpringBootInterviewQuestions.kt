package com.example.data.local.interview

import com.example.domain.model.InterviewQuestion

object SpringBootInterviewQuestions {

    fun getQuestions(): List<InterviewQuestion> = listOf(
        // --- Concept 1: IoC Container & Bean Lifecycle ---
        InterviewQuestion(
            id = "iq_spring_001",
            trackId = "spring_boot_interview",
            conceptId = "spring_ioc_lifecycle",
            conceptName = "IoC Container & Bean Lifecycle",
            title = "Spring Bean Lifecycle Stages",
            question = "Describe the lifecycle of a Spring Bean from instantiation to destruction. What role do BeanPostProcessors play?",
            shortAnswer = "A Spring Bean goes through: 1) Instantiation (constructor), 2) Populating properties/dependencies, 3) Aware interfaces callback (BeanNameAware, ApplicationContextAware), 4) BeanPostProcessor.postProcessBeforeInitialization(), 5) Initialization (@PostConstruct, InitializingBean, custom initMethod), 6) BeanPostProcessor.postProcessAfterInitialization() (where AOP proxies are created), 7) Ready for use, and 8) Destruction (@PreDestroy, DisposableBean, destroyMethod). BeanPostProcessors allow intercepting and wrapping beans with proxies before and after initialization.",
            keyPoints = listOf(
                "Instantiation -> Dependency Injection -> Aware interfaces invocation",
                "BeanPostProcessor beforeInitialization allows pre-setup inspection",
                "Initialization via @PostConstruct / InitializingBean.afterPropertiesSet()",
                "BeanPostProcessor afterInitialization wraps beans in dynamic/CGLIB proxies (AOP)",
                "Destruction hooks via @PreDestroy and DisposableBean.destroy()"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_002",
            trackId = "spring_boot_interview",
            conceptId = "spring_ioc_lifecycle",
            conceptName = "IoC Container & Bean Lifecycle",
            title = "Circular Dependency Resolution & Three-Level Cache",
            question = "How does Spring solve circular dependencies between singleton beans, and why does constructor injection fail with circular references?",
            shortAnswer = "Spring solves singleton circular dependencies via its three-level cache in DefaultSingletonBeanRegistry: singletonObjects (ready beans), earlySingletonObjects (early exposed beans), and singletonFactories (ObjectFactories that can expose early proxy references). When Bean A is instantiated, an ObjectFactory is placed in the third cache before dependencies are injected. When A injects B and B injects A, B retrieves A's early reference from the factory. Constructor injection fails because the bean instance cannot even be created to expose an early reference.",
            keyPoints = listOf(
                "Three caches: singletonObjects, earlySingletonObjects, singletonFactories",
                "Third cache stores ObjectFactory to generate early bean/proxy reference",
                "Breaks cycles during setter / field injection without waiting for full initialization",
                "Constructor injection cannot instantiate either bean, resulting in BeanCurrentlyInCreationException",
                "Fixes: @Lazy injection, redesigning architecture, or event decoupling"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_spring_003",
            trackId = "spring_boot_interview",
            conceptId = "spring_ioc_lifecycle",
            conceptName = "IoC Container & Bean Lifecycle",
            title = "Spring Bean Scopes & Concurrency",
            question = "What are the common Spring Bean scopes, and what concurrency hazard arises when injecting a Prototype bean into a Singleton bean?",
            shortAnswer = "Common scopes are singleton (one per ApplicationContext, default), prototype (new instance per request), request, session, and application. When a Prototype bean is injected into a Singleton bean via regular @Autowired, it is injected only once at container startup, effectively behaving as a singleton. To obtain a fresh prototype instance on every invocation, you must use @Lookup method injection, ObjectProvider<T>, or Provider/ProxyMode.",
            keyPoints = listOf(
                "Singleton is container-wide default; must be thread-safe/stateless",
                "Prototype creates a fresh instance per injection point or getBean() call",
                "Injecting prototype into singleton freezes the prototype instance at startup",
                "Use @Lookup or ObjectFactory / ObjectProvider to fetch new prototypes at runtime",
                "Web scopes (request, session) require ScopedProxyMode.TARGET_CLASS in singletons"
            ),
            difficulty = "Mid-Level"
        ),

        // --- Concept 2: Spring Security & Filter Chains ---
        InterviewQuestion(
            id = "iq_spring_004",
            trackId = "spring_boot_interview",
            conceptId = "spring_security",
            conceptName = "Spring Security & Filter Chains",
            title = "SecurityFilterChain & DelegatingFilterProxy",
            question = "How does Spring Security intercept HTTP requests within a standard servlet container? Explain DelegatingFilterProxy and FilterChainProxy.",
            shortAnswer = "The servlet container manages standard servlet filters. Spring uses DelegatingFilterProxy registered in web.xml / ServletContext, which bridges the servlet container and Spring ApplicationContext by delegating request filtering to a Spring bean called FilterChainProxy. FilterChainProxy maintains a list of SecurityFilterChains (containing UsernamePasswordAuthenticationFilter, BearerTokenAuthenticationFilter, ExceptionTranslationFilter, AuthorizationFilter) that match request URIs and execute security rules.",
            keyPoints = listOf(
                "DelegatingFilterProxy acts as the bridge from servlet container to Spring context",
                "FilterChainProxy contains all configured SecurityFilterChains",
                "Filters execute sequentially: authentication, exception translation, authorization",
                "SecurityContextHolder stores SecurityContext with Authentication principal in ThreadLocal",
                "SecurityFilterChain is configured using modern lambda DSL with HttpSecurity bean"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_005",
            trackId = "spring_boot_interview",
            conceptId = "spring_security",
            conceptName = "Spring Security & Filter Chains",
            title = "JWT Authentication & Stateless Session Policy",
            question = "How do you implement stateless REST API security in Spring Boot with JWT and disable HTTP session creation?",
            shortAnswer = "Configure HttpSecurity with SessionCreationPolicy.STATELESS to ensure Spring Security never creates or uses an HttpSession. Add a custom OncePerRequestFilter (e.g. JwtAuthenticationFilter) before UsernamePasswordAuthenticationFilter. The filter extracts the Bearer token from the Authorization header, validates the cryptographic signature and expiration, builds an UsernamePasswordAuthenticationToken with user authorities, and sets it in SecurityContextHolder.getContext().setAuthentication().",
            keyPoints = listOf(
                "SessionCreationPolicy.STATELESS stops servlet session storage",
                "OncePerRequestFilter guarantees single execution per request dispatch",
                "Extracts token from Authorization: Bearer <token>",
                "Validates cryptographic signature and claims (expiration, subject, roles)",
                "Populates SecurityContextHolder with authenticated principal and GrantedAuthorities"
            ),
            difficulty = "Mid-Level"
        ),

        // --- Concept 3: Transactions & JPA ---
        InterviewQuestion(
            id = "iq_spring_006",
            trackId = "spring_boot_interview",
            conceptId = "spring_transactions_jpa",
            conceptName = "Transactions & JPA Integration",
            title = "@Transactional Rollback Rules & Self-Invocation Trap",
            question = "By default, when does Spring's @Transactional roll back? Why does calling a @Transactional method from within the same class bypass the transaction?",
            shortAnswer = "By default, @Transactional rolls back only on unchecked exceptions (RuntimeException and Error), not checked exceptions (must use rollbackFor = Exception.class). Self-invocation bypasses transactions because Spring @Transactional works via AOP dynamic proxies. When an internal method (this.methodB()) is called directly from methodA() inside the same class, the call does not pass through the Spring proxy, bypassing transaction interceptors completely.",
            keyPoints = listOf(
                "Default rollback: RuntimeException and Error only",
                "Checked exceptions require explicit rollbackFor = Exception.class attribute",
                "AOP proxy intercepts external calls to manage begin, commit, and rollback",
                "Internal calls (this.doSomething()) bypass proxy and transaction interceptor",
                "Fixes: self-injection, ApplicationContext.getBean(), or moving to a separate service bean"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_007",
            trackId = "spring_boot_interview",
            conceptId = "spring_transactions_jpa",
            conceptName = "Transactions & JPA Integration",
            title = "JPA N+1 Problem & Mitigation Strategies",
            question = "What causes the JPA/Hibernate N+1 select problem, and how do you resolve it in high-scale production systems?",
            shortAnswer = "The N+1 problem occurs when fetching 1 parent entity with a one-to-many or many-to-one relationship (1 query) causes Hibernate to trigger N separate SQL queries to load the associated child entities for each parent record. Solutions: 1) Use JOIN FETCH in JPQL/HQL to load parent and children in a single SQL join query, 2) Use @EntityGraph to declaratively specify fetch attributes, or 3) Configure @BatchSize on child entities to load associations in bulk IN-clauses.",
            keyPoints = listOf(
                "1 query loads N parents, then N additional queries load each parent's children",
                "Occurs with both lazy loading during iteration and eager loading",
                "JOIN FETCH loads parent and children in a single SQL query join",
                "@EntityGraph provides reusable and dynamic fetch plan specifications",
                "@BatchSize(size = 25) groups secondary lookups into batched IN (...) queries"
            ),
            difficulty = "Senior"
        ),

        // --- Concept 4: Actuator & Autoconfiguration ---
        InterviewQuestion(
            id = "iq_spring_008",
            trackId = "spring_boot_interview",
            conceptId = "spring_boot_internals",
            conceptName = "Spring Boot Internals & Production Readiness",
            title = "How Spring Boot Auto-Configuration Works",
            question = "How does @SpringBootApplication trigger auto-configuration? How do conditional annotations like @ConditionalOnClass function?",
            shortAnswer = "@SpringBootApplication includes @EnableAutoConfiguration, which uses SpringFactoriesLoader / AutoConfiguration.imports to load auto-configuration candidate classes listed under META-INF/spring/. Each configuration class uses conditional annotations like @ConditionalOnClass (activates only if specific libraries are on classpath), @ConditionalOnMissingBean (activates only if user hasn't declared their own bean), and @ConditionalOnProperty. This provides sensible defaults without overriding custom developer configurations.",
            keyPoints = listOf(
                "@EnableAutoConfiguration scans META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports",
                "@ConditionalOnClass checks classpath presence without ClassNotFoundException",
                "@ConditionalOnMissingBean allows custom developer beans to override framework defaults",
                "@ConditionalOnProperty enables/disables features based on application.properties/yml",
                "Auto-configuration classes are evaluated after user-defined configuration beans"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_009",
            trackId = "spring_boot_interview",
            conceptId = "spring_boot_internals",
            conceptName = "Spring Boot Internals & Production Readiness",
            title = "Actuator Health Checks & Kubernetes Probes",
            question = "How does Spring Boot Actuator support Kubernetes Liveness and Readiness probes?",
            shortAnswer = "Spring Boot Actuator exposes /actuator/health/liveness and /actuator/health/readiness endpoints. Liveness probes indicate whether the internal application state is healthy (if broken, Kubernetes restarts the pod). Readiness probes indicate whether the application is ready to accept user traffic (e.g. database connections established, caches warmed). If readiness fails, Kubernetes temporarily removes the pod from the Service load balancer without restarting it.",
            keyPoints = listOf(
                "/actuator/health/liveness checks if app process is alive (restarts container on failure)",
                "/actuator/health/readiness checks if app can serve traffic (routes traffic away on failure)",
                "AvailabilityChangeEvent allows programmatic publishing of LivenessState / ReadinessState",
                "Graceful shutdown drains active HTTP connections during pod termination",
                "Custom HealthIndicator beans can be added to the health aggregation composite"
            ),
            difficulty = "Mid-Level"
        )
    )
}
