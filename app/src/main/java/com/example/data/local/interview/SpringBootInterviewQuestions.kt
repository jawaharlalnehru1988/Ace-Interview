package com.example.data.local.interview

import com.example.domain.model.InterviewQuestion

object SpringBootInterviewQuestions {

    private fun part1(): List<InterviewQuestion> = listOf(
        InterviewQuestion(
            id = "iq_spring_001",
            trackId = "spring_boot_interview",
            conceptId = "spring_ioc_lifecycle",
            conceptName = "IoC Container & Bean Lifecycle",
            title = "Spring Bean Lifecycle Phases and Extension Points",
            question = "Describe the complete lifecycle of a Spring Bean from instantiation to destruction. What role do BeanPostProcessors play?",
            shortAnswer = "A Spring bean undergoes: 1) Instantiation, 2) Property population/Dependency injection, 3) Aware callbacks (BeanNameAware, ApplicationContextAware), 4) BeanPostProcessor.postProcessBeforeInitialization(), 5) Initialization (@PostConstruct, InitializingBean, initMethod), 6) BeanPostProcessor.postProcessAfterInitialization() (proxy wrapping for AOP/Transactions), 7) Ready for use, and 8) Destruction (@PreDestroy, DisposableBean, destroyMethod).",
            keyPoints = listOf(
                "Instantiation -> DI -> Aware callbacks",
                "BeanPostProcessor before and after initialization hooks",
                "Initialization callbacks (@PostConstruct / InitializingBean)",
                "AOP proxies generated in postProcessAfterInitialization",
                "Destruction callbacks (@PreDestroy / DisposableBean)",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_002",
            trackId = "spring_boot_interview",
            conceptId = "spring_ioc_lifecycle",
            conceptName = "IoC Container & Bean Lifecycle",
            title = "Circular Dependency Handling and Three-Level Cache",
            question = "How does Spring solve circular dependencies between singleton beans using the Three-Level Cache, and why does constructor injection fail?",
            shortAnswer = "Spring resolves singleton circular dependencies in DefaultSingletonBeanRegistry using 3 caches: 1) singletonObjects (fully initialized beans), 2) earlySingletonObjects (early uninitialized/proxy references), and 3) singletonFactories (ObjectFactory providing early references). When bean A instantiates, it puts an ObjectFactory in cache 3. When injecting B, B requests A and gets early reference from cache 3 (promoted to cache 2). Constructor injection fails because the bean cannot even be instantiated to publish its factory.",
            keyPoints = listOf(
                "Three caches: singletonObjects, earlySingletonObjects, singletonFactories",
                "singletonFactories stores ObjectFactory for early references",
                "Breaks circular loops during setter/field injection",
                "Constructor injection fails with BeanCurrentlyInCreationException",
                "Resolution via @Lazy, Provider, or architectural decoupling",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_003",
            trackId = "spring_boot_interview",
            conceptId = "spring_ioc_lifecycle",
            conceptName = "IoC Container & Bean Lifecycle",
            title = "Spring Bean Scopes and Injection Mismatch",
            question = "Explain Spring Bean scopes. What happens when a Prototype scoped bean is injected into a Singleton scoped bean, and how do you resolve it?",
            shortAnswer = "Scopes include singleton (default, one per context), prototype (new instance per request/injection), request, session, and application. When a prototype is injected into a singleton via regular @Autowired, it is injected once at startup, acting as a singleton. Solutions: 1) @Lookup method injection, 2) ObjectProvider<T> / Provider<T>, or 3) ScopedProxyMode.TARGET_CLASS.",
            keyPoints = listOf(
                "Singleton is default and container-wide",
                "Prototype creates fresh instance on each request",
                "Singleton injection freezes prototype instance at context startup",
                "Resolve via @Lookup, ObjectProvider<T>, or scoped proxy",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_004",
            trackId = "spring_boot_interview",
            conceptId = "spring_ioc_lifecycle",
            conceptName = "IoC Container & Bean Lifecycle",
            title = "BeanFactory vs ApplicationContext",
            question = "What is the key architectural difference between BeanFactory and ApplicationContext?",
            shortAnswer = "BeanFactory is the basic IoC container providing lazy bean loading (instantiates on getBean()). ApplicationContext extends BeanFactory, providing eager instantiation of singletons at startup, full AOP integration, internationalization (MessageSource), event publishing (ApplicationEventPublisher), and environment profiles.",
            keyPoints = listOf(
                "BeanFactory is basic with lazy initialization",
                "ApplicationContext is advanced enterprise container with eager singleton loading",
                "ApplicationContext provides I18N, Event publishing, and Env profile abstractions",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_005",
            trackId = "spring_boot_interview",
            conceptId = "spring_ioc_lifecycle",
            conceptName = "IoC Container & Bean Lifecycle",
            title = "Constructor Injection vs Setter vs Field Injection",
            question = "Why is Constructor Injection strongly favored over Field Injection (@Autowired on fields)?",
            shortAnswer = "Constructor injection ensures immutability (fields can be final), guarantees that dependencies cannot be null (prevents NullPointerExceptions at runtime), facilitates clean unit testing without needing reflection or Spring runners, and fails fast at startup if dependencies are missing or circular. Field injection hides dependencies and makes testing harder.",
            keyPoints = listOf(
                "Enables immutable final fields",
                "Guarantees full instantiation preventing null dependencies",
                "Simplifies unit testing without Spring context or reflection",
                "Field injection hides dependencies and tightly couples to Spring",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_006",
            trackId = "spring_boot_interview",
            conceptId = "spring_ioc_lifecycle",
            conceptName = "IoC Container & Bean Lifecycle",
            title = "BeanPostProcessor vs BeanFactoryPostProcessor",
            question = "Differentiate between BeanPostProcessor and BeanFactoryPostProcessor in Spring.",
            shortAnswer = "BeanFactoryPostProcessor operates on bean configuration metadata (BeanDefinitions) before any bean instances are created; examples include PropertySourcesPlaceholderConfigurer which resolves \${...} placeholders. BeanPostProcessor operates on actual instantiated bean instances, intercepting initialization methods to configure properties or wrap beans with AOP proxies.",
            keyPoints = listOf(
                "BeanFactoryPostProcessor modifies BeanDefinitions before instantiation",
                "BeanPostProcessor intercepts instantiated bean objects around initialization",
                "PropertySourcesPlaceholderConfigurer is a BeanFactoryPostProcessor",
                "AOP proxy creators are BeanPostProcessors",
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_spring_007",
            trackId = "spring_boot_interview",
            conceptId = "spring_ioc_lifecycle",
            conceptName = "IoC Container & Bean Lifecycle",
            title = "@Component vs @Bean Annotation Differences",
            question = "When should you use @Component versus @Bean in Spring applications?",
            shortAnswer = "Use @Component (and its stereotypes @Service, @Repository, @Controller) on your own class definitions to let Spring auto-detect and instantiate them via component scanning. Use @Bean inside a @Configuration class to explicitly configure and instantiate third-party classes, customize constructor parameters, or conditionally instantiate beans based on logic.",
            keyPoints = listOf(
                "@Component is class-level for auto-detection via component scanning",
                "@Bean is method-level inside @Configuration classes",
                "@Bean is ideal for third-party libraries and explicit customized creation",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_008",
            trackId = "spring_boot_interview",
            conceptId = "spring_ioc_lifecycle",
            conceptName = "IoC Container & Bean Lifecycle",
            title = "@Primary vs @Qualifier for Disambiguation",
            question = "How do @Primary and @Qualifier resolve NoUniqueBeanDefinitionException?",
            shortAnswer = "When multiple beans of the same type exist, Spring throws NoUniqueBeanDefinitionException. @Primary marks a default bean candidate when no specific qualifier is requested. @Qualifier specifies the exact bean name at the injection site, taking precedence over @Primary to achieve fine-grained disambiguation.",
            keyPoints = listOf(
                "NoUniqueBeanDefinitionException when multiple candidates exist",
                "@Primary designates default fallback candidate",
                "@Qualifier explicitly targets specific bean name and overrides @Primary",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_009",
            trackId = "spring_boot_interview",
            conceptId = "spring_ioc_lifecycle",
            conceptName = "IoC Container & Bean Lifecycle",
            title = "@Configuration(proxyBeanMethods = true vs false)",
            question = "What does proxyBeanMethods = false (Lite Mode) do in @Configuration classes?",
            shortAnswer = "When proxyBeanMethods = true (Full mode, default), Spring uses CGLIB to subclass the configuration class, intercepting internal @Bean method invocations to return existing singleton instances from the context. Setting proxyBeanMethods = false (Lite mode) disables CGLIB proxying, treating @Bean methods as plain Java methods, saving startup time and memory when beans do not reference each other.",
            keyPoints = listOf(
                "Full mode uses CGLIB to enforce singleton semantics for inter-bean calls",
                "Lite mode (false) executes direct Java method calls without proxy",
                "Lite mode reduces startup memory and eliminates CGLIB overhead",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_010",
            trackId = "spring_boot_interview",
            conceptId = "spring_ioc_lifecycle",
            conceptName = "IoC Container & Bean Lifecycle",
            title = "Spring Event Publishing Mechanism",
            question = "How does Spring's ApplicationEventPublisher work, and how does @EventListener handle asynchronous events?",
            shortAnswer = "Components inject ApplicationEventPublisher and call publishEvent(event). Listeners handle events synchronously by default using @EventListener on a method. To handle events asynchronously on a background thread pool, combine @EventListener with @Async, ensuring the listener does not block the publisher thread.",
            keyPoints = listOf(
                "ApplicationEventPublisher dispatches event objects",
                "@EventListener registers decoupled subscriber methods",
                "Synchronous by default; combine with @Async for non-blocking execution",
                "@TransactionalEventListener controls event execution relative to transaction phases",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_011",
            trackId = "spring_boot_interview",
            conceptId = "spring_ioc_lifecycle",
            conceptName = "IoC Container & Bean Lifecycle",
            title = "@TransactionalEventListener Phase Boundaries",
            question = "How does @TransactionalEventListener differ from standard @EventListener?",
            shortAnswer = "@TransactionalEventListener binds event handling to specific phases of the active transaction: AFTER_COMMIT (default, runs only if transaction commits successfully), AFTER_ROLLBACK, AFTER_COMPLETION, or BEFORE_COMMIT. If no transaction is active, the event is ignored unless fallbackExecution = true is set.",
            keyPoints = listOf(
                "Tied to transaction phases (AFTER_COMMIT, AFTER_ROLLBACK, etc.)",
                "Default phase is AFTER_COMMIT",
                "Prevents sending notifications (emails/messages) if transaction subsequently rolls back",
                "fallbackExecution attribute handles non-transactional contexts",
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_spring_012",
            trackId = "spring_boot_interview",
            conceptId = "spring_ioc_lifecycle",
            conceptName = "IoC Container & Bean Lifecycle",
            title = "Spring Profiles and Dynamic Environment Activation",
            question = "How does @Profile work, and how can active profiles be set in Spring Boot?",
            shortAnswer = "@Profile restricts beans or @Configuration classes to specific active environments (e.g. @Profile('prod')). Active profiles can be configured via spring.profiles.active in application.properties, environment variable SPRING_PROFILES_ACTIVE, JVM argument -Dspring.profiles.active, or programmatically via ConfigurableEnvironment.",
            keyPoints = listOf(
                "Conditionally registers beans based on active environment tags",
                "Supports logical expressions like @Profile('!dev & prod')",
                "Configured via properties, environment variables, or CLI arguments",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_013",
            trackId = "spring_boot_interview",
            conceptId = "spring_ioc_lifecycle",
            conceptName = "IoC Container & Bean Lifecycle",
            title = "Spring Aware Interfaces Purpose and Examples",
            question = "What is the purpose of Spring *Aware interfaces, and what are common examples?",
            shortAnswer = "Aware interfaces provide marker callbacks allowing a bean to access infrastructure objects of the Spring container itself, breaking pure inversion of control when framework introspection is necessary. Examples include BeanNameAware (learns its bean ID), ApplicationContextAware (gets context reference), and EnvironmentAware.",
            keyPoints = listOf(
                "Provides beans with access to container infrastructure",
                "BeanNameAware provides assigned bean identifier",
                "ApplicationContextAware provides container context handle",
                "Introduces framework coupling; should be used judiciously",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_014",
            trackId = "spring_boot_interview",
            conceptId = "spring_ioc_lifecycle",
            conceptName = "IoC Container & Bean Lifecycle",
            title = "Spring @DependsOn Annotation",
            question = "When is the @DependsOn annotation used, and how does it affect bean initialization order?",
            shortAnswer = "@DependsOn explicitly forces one or more beans to be initialized before the annotated bean is initialized, even when there is no direct dependency between them (e.g., initializing an in-memory database or registering a cache manager before a service starts). It also ensures that the dependent bean is destroyed after the target bean.",
            keyPoints = listOf(
                "Controls bean initialization sequence without direct injection",
                "Ensures prerequisite infrastructure is ready",
                "Inverts destruction order during container shutdown",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_015",
            trackId = "spring_boot_interview",
            conceptId = "spring_ioc_lifecycle",
            conceptName = "IoC Container & Bean Lifecycle",
            title = "@Lazy Initialization in Spring",
            question = "What does @Lazy do on a Spring Bean, and what are the trade-offs of enabling spring.main.lazy-initialization=true?",
            shortAnswer = "By default, Spring eagerly instantiates singletons at startup. @Lazy delays instantiation until the bean is first requested. Global lazy initialization speeds up development startup time and reduces memory usage, but postpones bean creation errors to runtime and increases latency for the first incoming HTTP request.",
            keyPoints = listOf(
                "Delays singleton creation until first access",
                "Speeds up application startup time and reduces memory footprint",
                "Trade-off: hides initialization errors until runtime and adds first-request latency",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_016",
            trackId = "spring_boot_interview",
            conceptId = "spring_ioc_lifecycle",
            conceptName = "IoC Container & Bean Lifecycle",
            title = "Resolving Multiple Autowired Candidates via @Order",
            question = "How does Spring handle injecting a List<MyInterface> when multiple implementations exist, and what does @Order do?",
            shortAnswer = "When a collection like List<MyInterface> is autowired, Spring collects and injects all registered beans implementing MyInterface. The @Order annotation (or PriorityOrdered) defines the sorting order of the elements inside the injected list, essential for interceptor chains, validation filters, or strategy handlers.",
            keyPoints = listOf(
                "Spring autowires all matching bean implementations into a Collection",
                "@Order defines the sequence order in the injected collection",
                "Lower order values have higher priority",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_017",
            trackId = "spring_boot_interview",
            conceptId = "spring_ioc_lifecycle",
            conceptName = "IoC Container & Bean Lifecycle",
            title = "FactoryBean vs Normal Bean",
            question = "What is a Spring FactoryBean, and how do you retrieve the FactoryBean instance itself?",
            shortAnswer = "FactoryBean<T> is an interface for creating complex objects that cannot be easily created using simple declarative XML or annotations (e.g. ProxyFactoryBean, JndiObjectFactoryBean). Calling getBean('myBean') returns the object created by getObject(). Prefixing the name with an ampersand, getBean('&myBean'), returns the FactoryBean instance itself.",
            keyPoints = listOf(
                "Encapsulates complex object creation logic",
                "getBean('name') returns the product of getObject()",
                "getBean('&name') returns the FactoryBean itself",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_018",
            trackId = "spring_boot_interview",
            conceptId = "spring_ioc_lifecycle",
            conceptName = "IoC Container & Bean Lifecycle",
            title = "@Value Annotation and SpEL (Spring Expression Language)",
            question = "Differentiate between property placeholder syntax and SpEL in @Value.",
            shortAnswer = "Property placeholder syntax @Value('\${property.name:default}') resolves values from PropertySources (application.properties, system env). SpEL syntax @Value('#{systemProperties[\"user.home\"]}') evaluates dynamic runtime expressions, method invocations, and bean interactions using the SpEL engine at injection time.",
            keyPoints = listOf(
                "\${...} resolves static property placeholders with optional defaults",
                "#{...} evaluates dynamic SpEL expressions against beans and runtime context",
                "Can be nested: @Value('#{\${max.size} * 2}')",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_019",
            trackId = "spring_boot_interview",
            conceptId = "spring_ioc_lifecycle",
            conceptName = "IoC Container & Bean Lifecycle",
            title = "Spring @Async Architecture and ThreadPool Configuration",
            question = "How does @Async work internally, and why must you configure a custom TaskExecutor?",
            shortAnswer = "@Async wraps the method invocation in an AOP proxy that submits the task to a TaskExecutor. If no custom executor is configured, Spring Boot defaults to SimpleAsyncTaskExecutor (which creates a new thread per request without pooling) or an unconstrained thread pool, risking thread starvation. Production code should declare a ThreadPoolTaskExecutor with explicit core, max, and queue capacity.",
            keyPoints = listOf(
                "Intercepted by AsyncAnnotationBeanPostProcessor via AOP proxy",
                "Returns void or CompletableFuture<T>",
                "Default executor creates new threads without pooling; custom ThreadPoolTaskExecutor is mandatory",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_020",
            trackId = "spring_boot_interview",
            conceptId = "spring_ioc_lifecycle",
            conceptName = "IoC Container & Bean Lifecycle",
            title = "@Scheduled Annotation and Cron Scheduling",
            question = "How does @Scheduled work, and how do fixedRate, fixedDelay, and cron differ?",
            shortAnswer = "@Scheduled runs background tasks periodically. fixedRate executes tasks at fixed intervals measured from the start time of the previous execution. fixedDelay executes tasks measured from the completion time of the previous execution. cron uses standard 6-field cron expressions for calendar-based scheduling. Requires @EnableScheduling.",
            keyPoints = listOf(
                "fixedRate schedules relative to previous task start",
                "fixedDelay schedules relative to previous task completion",
                "cron expression handles time/calendar schedules",
                "Requires custom TaskScheduler to prevent sequential single-thread blocking",
            ),
            difficulty = "Mid-Level"
        ),
    )

    private fun part2(): List<InterviewQuestion> = listOf(
        InterviewQuestion(
            id = "iq_spring_021",
            trackId = "spring_boot_interview",
            conceptId = "spring_ioc_lifecycle",
            conceptName = "IoC Container & Bean Lifecycle",
            title = "Spring AOP Architecture: JDK Dynamic Proxy vs CGLIB",
            question = "How does Spring AOP implement proxies, and when does it choose JDK Dynamic Proxies versus CGLIB?",
            shortAnswer = "Spring AOP creates runtime proxies to intercept method calls. JDK Dynamic Proxies require the target class to implement interfaces, generating a proxy implementing those interfaces via InvocationHandler. CGLIB generates a dynamic subclass of the target class at bytecode level. Since Spring Boot 2.x, CGLIB is the default (spring.aop.proxy-target-class=true) for consistent class-based proxying.",
            keyPoints = listOf(
                "JDK dynamic proxy requires interface implementation",
                "CGLIB subclasses target class using bytecode enhancement",
                "Spring Boot 2+ defaults to CGLIB (proxy-target-class=true)",
                "CGLIB cannot proxy final classes or final methods",
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_spring_022",
            trackId = "spring_boot_interview",
            conceptId = "spring_ioc_lifecycle",
            conceptName = "IoC Container & Bean Lifecycle",
            title = "AOP Pointcut, JoinPoint, and Advice Types",
            question = "Define Pointcut, JoinPoint, and the five Advice types in Spring AOP.",
            shortAnswer = "A JoinPoint is any point of execution (in Spring AOP, method execution). A Pointcut is an expression that matches specific JoinPoints. Advice is the action taken at a JoinPoint: 1) @Before, 2) @AfterReturning, 3) @AfterThrowing, 4) @After (finally), and 5) @Around (wraps method, controls ProceedingJoinPoint execution).",
            keyPoints = listOf(
                "JoinPoint is method execution point",
                "Pointcut expression defines where advice applies",
                "Five advice types: Before, AfterReturning, AfterThrowing, After, Around",
                "Around advice uses ProceedingJoinPoint.proceed()",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_023",
            trackId = "spring_boot_interview",
            conceptId = "spring_ioc_lifecycle",
            conceptName = "IoC Container & Bean Lifecycle",
            title = "Spring AOP Self-Invocation Problem",
            question = "Why does calling an AOP-annotated method (@Transactional, @Async, @Cacheable) from another method in the same class fail to execute advice?",
            shortAnswer = "Spring AOP relies on runtime proxies. When an external client calls a bean, it invokes the proxy, which executes advice before delegating to the target. In internal self-invocation (this.methodB()), the call bypasses the proxy and executes directly on the target instance ('this'), skipping all interceptor advice.",
            keyPoints = listOf(
                "Advised behavior is added by the proxy, not bytecode modification of the target",
                "Internal calls use 'this' reference bypassing the proxy",
                "Resolutions: refactor to separate bean, self-inject bean via @Lazy, or AopContext.currentProxy()",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_024",
            trackId = "spring_boot_interview",
            conceptId = "spring_ioc_lifecycle",
            conceptName = "IoC Container & Bean Lifecycle",
            title = "Property Resolution Hierarchy in Spring Boot",
            question = "What is the precedence order of configuration properties in Spring Boot?",
            shortAnswer = "Spring Boot evaluates property sources in a strict descending hierarchy: 1) CLI arguments (--server.port=8081), 2) SPRING_APPLICATION_JSON, 3) OS Environment variables, 4) Java System properties (-D...), 5) Config file outside packaged jar (application-{profile}.properties), 6) Config file inside packaged jar, 7) @PropertySource, 8) Default properties (SpringApplication.setDefaultProperties).",
            keyPoints = listOf(
                "CLI arguments take highest precedence",
                "OS Environment variables override application.properties",
                "Profile-specific properties override generic application.properties",
                "Packaged application.properties is low precedence default",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_025",
            trackId = "spring_boot_interview",
            conceptId = "spring_ioc_lifecycle",
            conceptName = "IoC Container & Bean Lifecycle",
            title = "@Import and Custom ImportSelector",
            question = "What does @Import do, and how does ImportSelector enable dynamic configuration?",
            shortAnswer = "@Import explicitly registers additional @Configuration classes or component classes without component scanning. Implementing ImportSelector allows dynamic, conditional selection of configuration classes at runtime based on AnnotationMetadata, which is the foundational mechanism behind Spring Boot's @Enable* annotations.",
            keyPoints = listOf(
                "Imports configuration classes directly without scanning",
                "ImportSelector dynamically determines configuration classes to load",
                "Underpins @EnableAsync, @EnableCaching, and auto-configuration candidates",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_026",
            trackId = "spring_boot_interview",
            conceptId = "spring_boot_internals",
            conceptName = "Spring Boot Internals & Autoconfiguration",
            title = "How Spring Boot Auto-Configuration Works Internally",
            question = "How does @SpringBootApplication trigger auto-configuration, and where are auto-configuration classes declared in Spring Boot 2.7+ and 3.x?",
            shortAnswer = "@SpringBootApplication combines @Configuration, @ComponentScan, and @EnableAutoConfiguration. @EnableAutoConfiguration imports AutoConfigurationImportSelector, which reads auto-configuration candidate classes listed in META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports (in Spring Boot 3.x/2.7+, replacing the older spring.factories). Each class evaluates conditional annotations to decide if its beans should be registered.",
            keyPoints = listOf(
                "@EnableAutoConfiguration imports AutoConfigurationImportSelector",
                "Reads META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports",
                "Older versions used META-INF/spring.factories",
                "Candidate classes are gated by @Conditional annotations",
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_spring_027",
            trackId = "spring_boot_interview",
            conceptId = "spring_boot_internals",
            conceptName = "Spring Boot Internals & Autoconfiguration",
            title = "Key @Conditional Annotations in Auto-Configuration",
            question = "Explain the role of @ConditionalOnClass, @ConditionalOnMissingBean, and @ConditionalOnProperty.",
            shortAnswer = "@ConditionalOnClass checks if specific classes are present on the classpath before configuring beans. @ConditionalOnMissingBean activates only if the developer has not declared a bean of that type or name, allowing user beans to seamlessly override framework defaults. @ConditionalOnProperty checks if a configuration property has a specific value or is present in application.properties.",
            keyPoints = listOf(
                "@ConditionalOnClass tests classpath presence safely without ClassNotFoundException",
                "@ConditionalOnMissingBean provides override mechanism for user-defined beans",
                "@ConditionalOnProperty toggles feature modules based on environment configuration",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_028",
            trackId = "spring_boot_interview",
            conceptId = "spring_boot_internals",
            conceptName = "Spring Boot Internals & Autoconfiguration",
            title = "Creating a Custom Spring Boot Starter",
            question = "What are the essential components and steps required to build a custom Spring Boot Starter?",
            shortAnswer = "A custom starter typically consists of two modules: 1) An autoconfigure module containing the configuration classes, @ConfigurationProperties bean, and conditional bean declarations. 2) A starter module (empty pom/gradle) that pulls in the autoconfigure module and third-party dependencies. You register the configuration class in META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports and annotate it with @AutoConfiguration.",
            keyPoints = listOf(
                "Separation of autoconfigure logic from dependency starter pom",
                "@ConfigurationProperties for binding external properties",
                "Registering configuration class in AutoConfiguration.imports",
                "Using @ConditionalOnMissingBean so consumers can override defaults",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_029",
            trackId = "spring_boot_interview",
            conceptId = "spring_boot_internals",
            conceptName = "Spring Boot Internals & Autoconfiguration",
            title = "Spring Boot 3 Baseline: Jakarta EE and Java 17+",
            question = "What are the primary breaking changes and upgrades introduced in Spring Boot 3.0?",
            shortAnswer = "1) Java baseline increased to Java 17 minimum (supports Java 21). 2) Package migration from javax.* to jakarta.* (Jakarta EE 9/10) impacting Servlet, JPA, and Validation APIs. 3) First-class support for GraalVM Native Image compilation via Ahead-Of-Time (AOT) engine. 4) Observability overhaul replacing Spring Cloud Sleuth with Micrometer Tracing. 5) Removal of legacy spring.factories auto-configuration registration.",
            keyPoints = listOf(
                "Java 17 minimum requirement",
                "javax.* to jakarta.* namespace migration",
                "GraalVM Native Image AOT support",
                "Micrometer Tracing replaces Spring Cloud Sleuth",
                "Deprecated APIs and spring.factories removed",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_030",
            trackId = "spring_boot_interview",
            conceptId = "spring_boot_internals",
            conceptName = "Spring Boot Internals & Autoconfiguration",
            title = "GraalVM Native Image Ahead-Of-Time (AOT) Compilation",
            question = "What is Ahead-Of-Time (AOT) compilation in Spring Boot 3, and what are its benefits and constraints?",
            shortAnswer = "Spring AOT evaluates the application at build-time, generating bytecode and reflection hints to compile a standalone OS binary with GraalVM native-image. Benefits: near-instant startup (<50ms) and minimal memory footprint (<50MB RAM). Constraints: dynamic reflection, CGLIB proxies, dynamic class loading, and unsafe operations require explicit reachability metadata hints.",
            keyPoints = listOf(
                "Build-time analysis and bytecode optimization",
                "Sub-second startup time and reduced memory usage for serverless/containers",
                "Requires explicit reflection and proxy metadata hints (RuntimeHintsRegistrar)",
                "Closed-world assumption limits dynamic runtime behavior",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_031",
            trackId = "spring_boot_interview",
            conceptId = "spring_boot_internals",
            conceptName = "Spring Boot Internals & Autoconfiguration",
            title = "Embedded Servlet Containers: Tomcat, Jetty, Undertow",
            question = "How does Spring Boot package and run embedded web servers, and how do you switch from Tomcat to Undertow?",
            shortAnswer = "Spring Boot packages the servlet container as a library inside the executable fat JAR. During startup, ServletWebServerFactory (e.g. TomcatServletWebServerFactory) creates and starts the server on the configured port. To switch to Undertow, exclude spring-boot-starter-tomcat from spring-boot-starter-web and include spring-boot-starter-undertow.",
            keyPoints = listOf(
                "Fat JAR contains embedded container libraries",
                "ServletWebServerApplicationContext creates web server during onRefresh()",
                "Exclude Tomcat starter and add Undertow starter in build file",
                "Undertow uses non-blocking XNIO architecture for high throughput",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_032",
            trackId = "spring_boot_interview",
            conceptId = "spring_boot_internals",
            conceptName = "Spring Boot Internals & Autoconfiguration",
            title = "Spring Boot Fat JAR Structure and JarLauncher",
            question = "How does a Spring Boot executable fat JAR execute without unpacking its nested JARs?",
            shortAnswer = "Standard Java JVM ClassLoaders cannot load nested JARs inside JARs. Spring Boot solves this with a custom loader (JarLauncher). The root of the fat JAR contains BOOT-INF/classes (compiled app code), BOOT-INF/lib (nested dependency JARs), and org/springframework/boot/loader/JarLauncher. The MANIFEST.MF Main-Class is set to JarLauncher, which sets up an LaunchedURLClassLoader to load nested JARs directly before delegating to your app's main class.",
            keyPoints = listOf(
                "BOOT-INF/classes and BOOT-INF/lib directory layout",
                "JarLauncher is the true Main-Class in MANIFEST.MF",
                "Custom LaunchedURLClassLoader loads nested JAR bytecodes directly",
                "Start-Class attribute points to user Application main() method",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_033",
            trackId = "spring_boot_interview",
            conceptId = "spring_boot_internals",
            conceptName = "Spring Boot Internals & Autoconfiguration",
            title = "CommandLineRunner vs ApplicationRunner",
            question = "Compare CommandLineRunner and ApplicationRunner in Spring Boot.",
            shortAnswer = "Both interfaces provide a run() callback executed immediately after the ApplicationContext is fully refreshed and before the SpringApplication.run() method finishes. CommandLineRunner receives raw string varargs (String... args). ApplicationRunner receives ApplicationArguments, providing convenient parsed access to option arguments (--name=value) and non-option arguments.",
            keyPoints = listOf(
                "Execute startup warmup/seeding logic after context initialization",
                "CommandLineRunner accepts raw String... array",
                "ApplicationRunner accepts structured ApplicationArguments object",
                "Both support @Order for execution sequencing",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_034",
            trackId = "spring_boot_interview",
            conceptId = "spring_boot_internals",
            conceptName = "Spring Boot Internals & Autoconfiguration",
            title = "@ConfigurationProperties vs @Value",
            question = "Why is @ConfigurationProperties preferred over @Value for complex application configuration?",
            shortAnswer = "@ConfigurationProperties provides type-safe hierarchical binding with relaxed binding rules (kebab-case, camelCase, snake_case all map to the same field). It supports JSR-380 validation (@NotNull, @Min) with @Validated, IDE autocomplete support via metadata generation, and cleanly groups related configuration into a single immutable record or class.",
            keyPoints = listOf(
                "Type safety and hierarchical prefix binding",
                "Relaxed binding handles various naming conventions",
                "Integrated JSR-380 Bean Validation support",
                "IDE autocomplete via spring-boot-configuration-processor",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_035",
            trackId = "spring_boot_interview",
            conceptId = "spring_boot_internals",
            conceptName = "Spring Boot Internals & Autoconfiguration",
            title = "Relaxed Binding in Spring Boot Properties",
            question = "What is Relaxed Binding in Spring Boot, and how does it map environment variables to properties?",
            shortAnswer = "Relaxed binding allows property names in configuration sources to use different formats while mapping to the exact same Java property. For instance, acme.my-project.person.first-name in properties, acme.myProject.person.firstName in YAML, and ACME_MYPROJECT_PERSON_FIRSTNAME in environment variables all bind seamlessly to the firstName field of person in an AcmeProperties class.",
            keyPoints = listOf(
                "Permits kebab-case, camelCase, and uppercase underscore formats",
                "Enables seamless environment variable overrides in Kubernetes/Docker",
                "Standardizes configuration access in Java code",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_036",
            trackId = "spring_boot_interview",
            conceptId = "spring_boot_internals",
            conceptName = "Spring Boot Internals & Autoconfiguration",
            title = "Graceful Shutdown in Spring Boot",
            question = "How does Spring Boot handle Graceful Shutdown, and what configuration is needed?",
            shortAnswer = "Configuring server.shutdown=graceful instructs the embedded web server (Tomcat, Netty, Undertow) to stop accepting new requests upon receiving SIGTERM and allow active in-flight requests a grace period to complete (configured via spring.lifecycle.timeout-per-shutdown-phase, default 30s). Once requests finish, the ApplicationContext shuts down beans cleanly.",
            keyPoints = listOf(
                "server.shutdown=graceful stops accepting new connections on SIGTERM",
                "Allows active HTTP requests to complete within grace period",
                "spring.lifecycle.timeout-per-shutdown-phase controls timeout",
                "Critical for zero-downtime rolling deployments in Kubernetes",
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_spring_037",
            trackId = "spring_boot_interview",
            conceptId = "spring_boot_internals",
            conceptName = "Spring Boot Internals & Autoconfiguration",
            title = "Disabling Specific Auto-Configurations",
            question = "How do you disable a specific Spring Boot auto-configuration class that you do not want?",
            shortAnswer = "You can exclude auto-configuration classes in three ways: 1) Using the exclude attribute on @SpringBootApplication: @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}). 2) In application.properties: spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration. 3) In @EnableAutoConfiguration(exclude = ...).",
            keyPoints = listOf(
                "@SpringBootApplication(exclude = {Class.class})",
                "spring.autoconfigure.exclude property in application.properties",
                "Useful when replacing default database or security configurations with custom setups",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_038",
            trackId = "spring_boot_interview",
            conceptId = "spring_boot_internals",
            conceptName = "Spring Boot Internals & Autoconfiguration",
            title = "Spring Boot Logging Hierarchy and Logback Integration",
            question = "How does Spring Boot configure logging by default, and how do you customize it via logback-spring.xml?",
            shortAnswer = "Spring Boot uses Commons Logging internally and routes all logs (SLF4J, Log4j2, JUL) to Logback by default. It provides sensible console formatting with color coding. Placing logback-spring.xml in the classpath allows advanced multi-profile configuration using <springProfile name='prod'>, JSON structured logging for log aggregators (ELK/Datadog), and custom rolling file policies.",
            keyPoints = listOf(
                "SLF4J facade with Logback implementation by default",
                "Configurable via logging.level.root and logging.level.package in properties",
                "logback-spring.xml allows <springProfile> conditional blocks",
                "Supports structured JSON logging for log aggregation engines",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_039",
            trackId = "spring_boot_interview",
            conceptId = "spring_boot_internals",
            conceptName = "Spring Boot Internals & Autoconfiguration",
            title = "Multi-Document application.yml Configuration",
            question = "How does multi-document YAML or Spring Boot 2.4+ config tree file loading work?",
            shortAnswer = "In YAML, documents are separated by '---'. In Spring Boot 2.4+, activation is controlled via 'spring.config.activate.on-profile: prod'. You can also import external configuration via 'spring.config.import=optional:configserver:', file paths, or Kubernetes config maps, providing structured multi-environment setup in a single file.",
            keyPoints = listOf(
                "--- separates distinct configuration documents in a single YAML file",
                "spring.config.activate.on-profile selectively applies document blocks",
                "spring.config.import enables loading external config maps or vault secrets",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_040",
            trackId = "spring_boot_interview",
            conceptId = "spring_boot_internals",
            conceptName = "Spring Boot Internals & Autoconfiguration",
            title = "Spring Boot DevTools Features and ClassLoader Architecture",
            question = "What does spring-boot-devtools provide, and how does its two-classloader restart mechanism work?",
            shortAnswer = "DevTools provides automatic application restart on classpath file changes, LiveReload for browser refresh, and disables template caching. It uses two ClassLoaders: a Base ClassLoader for third-party libraries (which rarely change) and a Restart ClassLoader for your project code. When code changes, only the Restart ClassLoader is discarded and recreated, making restarts significantly faster than a full cold boot.",
            keyPoints = listOf(
                "Automatic rapid restart on code recompilation",
                "Dual ClassLoader design: Base (third-party) and Restart (project classes)",
                "Disables view/template caching during development",
                "Automatically disabled in production packaged fat JARs",
            ),
            difficulty = "Senior"
        ),
    )

    private fun part3(): List<InterviewQuestion> = listOf(
        InterviewQuestion(
            id = "iq_spring_041",
            trackId = "spring_boot_interview",
            conceptId = "spring_boot_internals",
            conceptName = "Spring Boot Internals & Autoconfiguration",
            title = "SpringApplication Lifecycle Events",
            question = "What are the primary SpringApplication events published during startup?",
            shortAnswer = "In order: 1) ApplicationStartingEvent (before any processing begins), 2) ApplicationEnvironmentPreparedEvent (Environment is ready but context not yet created), 3) ApplicationContextInitializedEvent, 4) ApplicationPreparedEvent (BeanDefinitions loaded, before bean creation), 5) ApplicationStartedEvent (context refreshed), 6) AvailabilityChangeEvent (LivenessState.CORRECT), 7) ApplicationReadyEvent (runners completed, ready to serve), or ApplicationFailedEvent.",
            keyPoints = listOf(
                "ApplicationStartingEvent -> EnvironmentPreparedEvent",
                "ApplicationPreparedEvent before bean instantiation",
                "ApplicationStartedEvent -> ApplicationReadyEvent after runners finish",
                "AvailabilityChangeEvent for Kubernetes liveness/readiness probes",
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_spring_042",
            trackId = "spring_boot_interview",
            conceptId = "spring_boot_internals",
            conceptName = "Spring Boot Internals & Autoconfiguration",
            title = "Virtual Threads Configuration in Spring Boot 3.2+",
            question = "How do you enable and configure Java 21 Virtual Threads in Spring Boot 3.2+?",
            shortAnswer = "In Spring Boot 3.2+ running on Java 21, simply set spring.threads.virtual.enabled=true in application.properties. Spring Boot automatically configures the embedded Tomcat server to use an Executor with virtual threads per request, configures Spring MVC async task executors, and enables virtual threads for @Async, providing high throughput for blocking I/O calls without thread starvation.",
            keyPoints = listOf(
                "spring.threads.virtual.enabled=true property",
                "Automatically configures Tomcat/Jetty to use virtual thread per task executor",
                "Replaces standard platform thread pools for MVC and @Async",
                "Eliminates need for reactive WebFlux complexity for typical I/O workloads",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_043",
            trackId = "spring_boot_interview",
            conceptId = "spring_boot_internals",
            conceptName = "Spring Boot Internals & Autoconfiguration",
            title = "Spring Boot Banner Customization",
            question = "How can you customize or disable the startup ASCII banner in Spring Boot?",
            shortAnswer = "Place a banner.txt file in src/main/resources to provide a custom ASCII banner with color formatting and variables like \${spring-boot.version}. You can disable the banner by setting spring.main.banner-mode=off in application.properties or programmatically using app.setBannerMode(Banner.Mode.OFF).",
            keyPoints = listOf(
                "banner.txt in resources directory",
                "Supports ANSI color codes and metadata placeholders",
                "Disabled via spring.main.banner-mode=off",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_044",
            trackId = "spring_boot_interview",
            conceptId = "spring_boot_internals",
            conceptName = "Spring Boot Internals & Autoconfiguration",
            title = "ConditionalOnExpression and SpEL in Configuration",
            question = "When is @ConditionalOnExpression used in auto-configuration?",
            shortAnswer = "@ConditionalOnExpression evaluates a SpEL (Spring Expression Language) expression against the Environment before loading a bean or configuration class (e.g. @ConditionalOnExpression(\"\${feature.enabled:false} and \${feature.mode} == 'PROD'\")). It should be used when complex compound logical conditions cannot be expressed with standard @ConditionalOnProperty.",
            keyPoints = listOf(
                "Evaluates SpEL expressions against environment properties",
                "Enables complex boolean logic across multiple properties",
                "Fallback default values supported inside expression",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_045",
            trackId = "spring_boot_interview",
            conceptId = "spring_boot_internals",
            conceptName = "Spring Boot Internals & Autoconfiguration",
            title = "Spring Boot Starter Parent vs Importing Dependencies POM",
            question = "What is the difference between inheriting spring-boot-starter-parent and using dependencyManagement with spring-boot-dependencies?",
            shortAnswer = "Inheriting spring-boot-starter-parent provides dependency management, default plugin configurations (compiler, surefire, spring-boot-maven-plugin), and resource filtering. If your project already has a company-wide corporate parent POM, you cannot inherit multiple parents; instead, you import spring-boot-dependencies in <dependencyManagement> with scope=import, though you must configure plugins manually.",
            keyPoints = listOf(
                "Parent POM provides dependencies, plugin defaults, and resource filtering",
                "BOM import in dependencyManagement enables multi-inheritance corporate setups",
                "BOM import requires manual configuration of spring-boot-maven-plugin",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_046",
            trackId = "spring_boot_interview",
            conceptId = "spring_boot_internals",
            conceptName = "Spring Boot Internals & Autoconfiguration",
            title = "Custom Auto-Configuration Ordering: @AutoConfigureAfter and @AutoConfigureBefore",
            question = "How do you control the execution order of auto-configuration classes?",
            shortAnswer = "Use @AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE) or specific sequencing annotations: @AutoConfigureAfter(DataSourceAutoConfiguration.class) and @AutoConfigureBefore(WebMvcAutoConfiguration.class). This ensures prerequisite auto-configured beans exist before your auto-configuration class evaluates its @ConditionalOnBean checks.",
            keyPoints = listOf(
                "@AutoConfigureAfter ensures prerequisite auto-configurations execute first",
                "@AutoConfigureBefore executes prior to target auto-configuration",
                "@AutoConfigureOrder sets explicit numerical precedence",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_047",
            trackId = "spring_boot_interview",
            conceptId = "spring_boot_internals",
            conceptName = "Spring Boot Internals & Autoconfiguration",
            title = "Configuring SSL / TLS in Spring Boot",
            question = "How do you configure HTTPS/TLS for the embedded web server in Spring Boot?",
            shortAnswer = "Configure server.ssl properties in application.properties: server.port=8443, server.ssl.key-store=classpath:keystore.p12, server.ssl.key-store-password=secret, server.ssl.key-store-type=PKCS12, and server.ssl.key-alias=tomcat. Spring Boot automatically configures SSL/TLS termination on the embedded connector.",
            keyPoints = listOf(
                "server.ssl.key-store specifies certificate bundle path",
                "PKCS12 keystore format recommended",
                "Enables secure TLS termination directly on embedded server",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_048",
            trackId = "spring_boot_interview",
            conceptId = "spring_boot_internals",
            conceptName = "Spring Boot Internals & Autoconfiguration",
            title = "Spring Boot Layered JARs for Docker Optimization",
            question = "How do Layered JARs in Spring Boot optimize Docker container image builds?",
            shortAnswer = "Spring Boot divides the JAR into layers: dependencies, spring-boot-loader, snapshot-dependencies, and application. In Dockerfiles, you extract these layers and copy them separately. Because application code changes frequently while third-party dependencies rarely change, Docker reuses cached dependency layers, reducing image build times and container push/pull bandwidth from 100MB+ to a few megabytes.",
            keyPoints = listOf(
                "Separates JAR into dependencies, loader, snapshots, and application layers",
                "Docker caches static dependency layers",
                "Reduces image build times and network transfer sizes significantly",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_049",
            trackId = "spring_boot_interview",
            conceptId = "spring_boot_internals",
            conceptName = "Spring Boot Internals & Autoconfiguration",
            title = "Spring Cloud Config Client Integration",
            question = "How does Spring Boot integrate with Spring Cloud Config Server for centralized externalized configuration?",
            shortAnswer = "In Spring Boot 2.4+, you add spring-cloud-starter-config and configure spring.config.import=configserver:http://localhost:8888. During startup, the application queries the Config Server for environment-specific properties (application-{profile}.yml) backed by a Git or Vault repo. With @RefreshScope and Spring Boot Actuator's /actuator/refresh, properties can be reloaded dynamically without restarting the application.",
            keyPoints = listOf(
                "spring.config.import=configserver: URL syntax",
                "Centralized configuration pulled from Git/Vault via Config Server",
                "@RefreshScope re-instantiates beans when configuration updates",
                "/actuator/refresh endpoint triggers hot reloading",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_050",
            trackId = "spring_boot_interview",
            conceptId = "spring_boot_internals",
            conceptName = "Spring Boot Internals & Autoconfiguration",
            title = "Application Startup Failure Analysis with FailureAnalyzers",
            question = "How does Spring Boot format startup failure messages, and how do you write a custom FailureAnalyzer?",
            shortAnswer = "When startup fails, Spring Boot intercepts exceptions and runs registered FailureAnalyzer implementations to output a clear, actionable diagnostic message with 'APPLICATION FAILED TO START', Description, and Action. To write one, extend AbstractFailureAnalyzer<E>, implement analyze(Throwable rootFailure, E cause), and register it in META-INF/spring/org.springframework.boot.diagnostics.FailureAnalyzer.imports.",
            keyPoints = listOf(
                "Produces human-readable Description and Action startup reports",
                "Extends AbstractFailureAnalyzer<T>",
                "Registered via failure analyzer imports in META-INF",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_051",
            trackId = "spring_boot_interview",
            conceptId = "spring_mvc_rest",
            conceptName = "Spring MVC & RESTful Architecture",
            title = "DispatcherServlet Request Processing Workflow",
            question = "Explain the step-by-step request processing flow through DispatcherServlet in Spring MVC.",
            shortAnswer = "1) HTTP request hits DispatcherServlet. 2) DispatcherServlet queries HandlerMapping to find the appropriate HandlerExecutionChain (Controller method + interceptors). 3) It invokes HandlerAdapter to execute the handler method. 4) PreHandle interceptors run. 5) Controller executes business logic. 6) PostHandle interceptors run. 7) In REST, HttpMessageConverter writes JSON/XML directly to response body; in traditional MVC, ViewResolver renders the View. 8) AfterCompletion interceptor runs.",
            keyPoints = listOf(
                "Front controller pattern coordinating request dispatch",
                "HandlerMapping identifies controller handler execution chain",
                "HandlerAdapter adapts and executes target controller method",
                "Interceptors execute preHandle, postHandle, afterCompletion",
                "HttpMessageConverter serializes response object directly for REST",
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_spring_052",
            trackId = "spring_boot_interview",
            conceptId = "spring_mvc_rest",
            conceptName = "Spring MVC & RESTful Architecture",
            title = "@Controller vs @RestController",
            question = "What is the difference between @Controller and @RestController in Spring MVC?",
            shortAnswer = "@Controller is a traditional stereotype used for web applications where methods return a view name (String) resolved by a ViewResolver to render HTML templates (JSP, Thymeleaf). @RestController is a composite annotation combining @Controller and @ResponseBody; every method automatically serializes the returned domain object directly into the HTTP response body (JSON/XML) via HttpMessageConverter.",
            keyPoints = listOf(
                "@Controller returns view names for server-side HTML rendering",
                "@RestController combines @Controller and @ResponseBody",
                "Automatically converts return objects to JSON/XML via HttpMessageConverters",
                "Eliminates redundant @ResponseBody on every method",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_053",
            trackId = "spring_boot_interview",
            conceptId = "spring_mvc_rest",
            conceptName = "Spring MVC & RESTful Architecture",
            title = "@PathVariable vs @RequestParam vs @RequestBody",
            question = "Compare @PathVariable, @RequestParam, and @RequestBody with appropriate HTTP method use cases.",
            shortAnswer = "@PathVariable extracts values embedded in the URI path template (/users/{id}) for identifying resources. @RequestParam extracts query parameters (/users?page=2) or form data, typically optional or used for filtering and pagination. @RequestBody binds the HTTP request body payload (JSON/XML) to a Java domain object via HttpMessageConverter, primarily used in POST, PUT, and PATCH requests.",
            keyPoints = listOf(
                "@PathVariable binds URI path segments for resource identification",
                "@RequestParam binds query parameters for filtering, sorting, and pagination",
                "@RequestBody deserializes JSON/XML payload from request body in POST/PUT/PATCH",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_054",
            trackId = "spring_boot_interview",
            conceptId = "spring_mvc_rest",
            conceptName = "Spring MVC & RESTful Architecture",
            title = "Global Exception Handling with @RestControllerAdvice",
            question = "How do you implement centralized, type-safe exception handling using @RestControllerAdvice and @ExceptionHandler?",
            shortAnswer = "Annotate a class with @RestControllerAdvice. Inside, define methods annotated with @ExceptionHandler(SpecificException.class) that return ResponseEntity<ErrorResponse>. The handler intercepts exceptions thrown by any controller, sets the appropriate HTTP status code (e.g. 404 NOT_FOUND, 400 BAD_REQUEST), and returns a consistent JSON error schema to the client.",
            keyPoints = listOf(
                "@RestControllerAdvice provides global cross-cutting exception interception",
                "@ExceptionHandler catches specified exception classes",
                "Returns standardized error JSON with custom status codes and timestamps",
                "Separates error-handling logic completely from business controllers",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_055",
            trackId = "spring_boot_interview",
            conceptId = "spring_mvc_rest",
            conceptName = "Spring MVC & RESTful Architecture",
            title = "RFC 7807 Problem Details in Spring Boot 3",
            question = "How does Spring Boot 3 implement RFC 7807 Problem Details for REST API error responses?",
            shortAnswer = "Spring Boot 3 introduced native support for RFC 7807 Problem Details using the ProblemDetail class. Setting spring.mvc.problemdetails.enabled=true automatically formats framework exceptions into standard RFC 7807 JSON containing 'type', 'title', 'status', 'detail', and 'instance'. Controllers or @RestControllerAdvice can also return ProblemDetail directly or extend ResponseEntityExceptionHandler.",
            keyPoints = listOf(
                "Standardized RFC 7807 error schema across microservices",
                "ProblemDetail fields: type, title, status, detail, instance",
                "Enabled globally via spring.mvc.problemdetails.enabled=true",
                "Extend ResponseEntityExceptionHandler to customize standard Spring MVC exceptions",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_056",
            trackId = "spring_boot_interview",
            conceptId = "spring_mvc_rest",
            conceptName = "Spring MVC & RESTful Architecture",
            title = "Bean Validation: @Valid vs @Validated",
            question = "What is the difference between @Valid and @Validated in Spring Boot?",
            shortAnswer = "@Valid is standard JSR-380/Jakarta Bean Validation, used on method arguments and fields for cascading validation on nested objects. @Validated is a Spring-specific variant that supports Validation Groups (e.g. validating different rules for Create vs Update operations) and can be placed on @Service or @Repository classes to enable method-level parameter validation via AOP.",
            keyPoints = listOf(
                "@Valid is standard Jakarta annotation with nested cascade support",
                "@Validated is Spring annotation supporting validation groups",
                "@Validated at class level enables service-layer method parameter validation",
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_spring_057",
            trackId = "spring_boot_interview",
            conceptId = "spring_mvc_rest",
            conceptName = "Spring MVC & RESTful Architecture",
            title = "Handling Validation Errors with MethodArgumentNotValidException",
            question = "How do you capture and format field-level validation errors in a REST API?",
            shortAnswer = "When @Valid fails on a @RequestBody object, Spring throws MethodArgumentNotValidException. In a @RestControllerAdvice class, create an @ExceptionHandler(MethodArgumentNotValidException.class) method. Extract BindingResult from the exception, loop through ex.getBindingResult().getFieldErrors(), and map each field name to its default validation message in an error DTO.",
            keyPoints = listOf(
                "MethodArgumentNotValidException thrown on @Valid failure",
                "BindingResult.getFieldErrors() provides field names and rejected values",
                "Format into map of field -> message with HTTP 400 Bad Request",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_058",
            trackId = "spring_boot_interview",
            conceptId = "spring_mvc_rest",
            conceptName = "Spring MVC & RESTful Architecture",
            title = "Custom Validation Annotations and ConstraintValidators",
            question = "How do you create a custom validation annotation with a ConstraintValidator in Spring?",
            shortAnswer = "1) Define the annotation @interface (e.g. @ValidPostalCode) annotated with @Constraint(validatedBy = PostalCodeValidator.class), @Target, and @Retention. Include mandatory attributes: message(), groups(), and payload(). 2) Implement ConstraintValidator<ValidPostalCode, String>, overriding isValid(String value, ConstraintValidatorContext context). Spring automatically injects dependencies into the validator bean.",
            keyPoints = listOf(
                "@Constraint(validatedBy = Validator.class)",
                "Mandatory message(), groups(), and payload() attributes",
                "Implement ConstraintValidator interface with isValid() logic",
                "Spring autowires dependencies directly into ConstraintValidator beans",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_059",
            trackId = "spring_boot_interview",
            conceptId = "spring_mvc_rest",
            conceptName = "Spring MVC & RESTful Architecture",
            title = "HandlerInterceptor vs Servlet Filter",
            question = "What is the difference between a Servlet Filter and a Spring HandlerInterceptor?",
            shortAnswer = "A Servlet Filter is part of the Servlet container specification, executing before the request reaches DispatcherServlet; it can wrap or modify HttpServletRequest/Response and operates on raw HTTP streams. A HandlerInterceptor is part of Spring MVC, executing inside DispatcherServlet after HandlerMapping resolves the target controller, giving it direct access to the handler method metadata (HandlerMethod) and ModelAndView.",
            keyPoints = listOf(
                "Filter is servlet container level, executing before DispatcherServlet",
                "HandlerInterceptor is Spring MVC level, executing around Controller methods",
                "Filter can wrap request/response objects (e.g. gzip compression, security)",
                "Interceptor has access to Spring HandlerMethod and application context",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_060",
            trackId = "spring_boot_interview",
            conceptId = "spring_mvc_rest",
            conceptName = "Spring MVC & RESTful Architecture",
            title = "CORS Configuration in Spring Boot",
            question = "How does CORS work, and what are the three ways to configure CORS in Spring Boot?",
            shortAnswer = "CORS (Cross-Origin Resource Sharing) prevents unauthorized cross-origin requests via browser preflight OPTIONS requests. In Spring Boot: 1) @CrossOrigin on controllers or specific handler methods for fine-grained control. 2) Global configuration by implementing WebMvcConfigurer and overriding addCorsMappings(CorsRegistry registry). 3) A CorsFilter bean registered in the security filter chain, essential for Spring Security integration.",
            keyPoints = listOf(
                "Browser preflight OPTIONS request validates allowed origins and methods",
                "@CrossOrigin at controller/method level",
                "WebMvcConfigurer.addCorsMappings() for global MVC CORS",
                "CorsFilter bean for Spring Security pre-auth interception",
            ),
            difficulty = "Mid-Level"
        ),
    )

    private fun part4(): List<InterviewQuestion> = listOf(
        InterviewQuestion(
            id = "iq_spring_061",
            trackId = "spring_boot_interview",
            conceptId = "spring_mvc_rest",
            conceptName = "Spring MVC & RESTful Architecture",
            title = "Content Negotiation and HttpMessageConverter",
            question = "How does Spring MVC decide whether to return JSON or XML, and what role do HttpMessageConverters play?",
            shortAnswer = "Spring MVC checks the client's Accept HTTP header (or format URL parameter) and queries registered HttpMessageConverters in order. If Accept: application/json, MappingJackson2HttpMessageConverter serializes the object. If Accept: application/xml and Jackson XML is present, MappingJackson2XmlHttpMessageConverter handles it. Custom converters can be registered via WebMvcConfigurer.configureMessageConverters().",
            keyPoints = listOf(
                "Accept header inspected during content negotiation",
                "Registered HttpMessageConverters match supported media types",
                "Jackson handles application/json and application/xml",
                "Custom converters registered via WebMvcConfigurer",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_062",
            trackId = "spring_boot_interview",
            conceptId = "spring_mvc_rest",
            conceptName = "Spring MVC & RESTful Architecture",
            title = "Idempotency in REST API Design",
            question = "Which HTTP methods are idempotent, and how do you implement idempotency for POST endpoints in Spring Boot?",
            shortAnswer = "GET, PUT, DELETE, HEAD, and OPTIONS are idempotent (repeating them produces the same server state). POST is not idempotent. To make POST idempotent (e.g. payment processing), require an Idempotency-Key header from the client. Use a Redis cache or database table to store the key with request hash and response; if the key is seen again, return the cached response without re-executing business logic.",
            keyPoints = listOf(
                "GET, PUT, DELETE are inherently idempotent; POST is non-idempotent",
                "Idempotency-Key HTTP header pattern",
                "Check-and-store in Redis/DB with TTL",
                "Return identical cached response on duplicate submissions",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_063",
            trackId = "spring_boot_interview",
            conceptId = "spring_mvc_rest",
            conceptName = "Spring MVC & RESTful Architecture",
            title = "PUT vs PATCH HTTP Methods",
            question = "What is the semantic difference between PUT and PATCH, and how is partial update implemented in Spring Boot?",
            shortAnswer = "PUT replaces the entire resource representation (missing fields are overwritten with null or defaults). PATCH applies partial modifications to specific fields. In Spring Boot, partial updates can be handled using JSON Patch (RFC 6902) / JSON Merge Patch (RFC 7396), Map<String, Object> reflection updates, or dedicated DTOs with MapStruct partial mapping.",
            keyPoints = listOf(
                "PUT replaces the complete resource entity",
                "PATCH updates only specified fields without affecting others",
                "JSON Merge Patch (RFC 7396) handles partial nulls vs omitted fields",
                "MapStruct @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_064",
            trackId = "spring_boot_interview",
            conceptId = "spring_mvc_rest",
            conceptName = "Spring MVC & RESTful Architecture",
            title = "Async Requests with DeferredResult and Callable",
            question = "How do DeferredResult and Callable prevent servlet thread blocking in Spring MVC?",
            shortAnswer = "When a controller returns Callable<T>, Spring runs the callable on an asynchronous TaskExecutor, releasing the container Servlet thread back to the pool. When returning DeferredResult<T>, the controller immediately releases the Servlet thread, and an external event (JMS message, background worker, or timer) completes the result asynchronously via deferredResult.setResult(data).",
            keyPoints = listOf(
                "Frees container Servlet thread to handle other incoming connections",
                "Callable executes on a Spring-managed background thread pool",
                "DeferredResult enables event-driven asynchronous completion from external threads",
                "Supported natively without migrating to reactive WebFlux",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_065",
            trackId = "spring_boot_interview",
            conceptId = "spring_mvc_rest",
            conceptName = "Spring MVC & RESTful Architecture",
            title = "Server-Sent Events (SSE) using SseEmitter",
            question = "How do you implement real-time server-to-client notifications using SseEmitter in Spring Boot?",
            shortAnswer = "The controller returns an SseEmitter instance with media type text/event-stream. The client maintains an open HTTP connection using the EventSource browser API. The server stores active SseEmitter references and pushes real-time messages via emitter.send(data), completing with emitter.complete() or handling disconnects via onCompletion() and onTimeout() callbacks.",
            keyPoints = listOf(
                "text/event-stream media type over standard HTTP",
                "Browser uses EventSource API for automatic reconnects",
                "SseEmitter.send() pushes data events asynchronously",
                "onCompletion and onTimeout callbacks handle client lifecycle",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_066",
            trackId = "spring_boot_interview",
            conceptId = "spring_mvc_rest",
            conceptName = "Spring MVC & RESTful Architecture",
            title = "WebSocket and STOMP Messaging in Spring Boot",
            question = "How does Spring Boot configure full-duplex WebSocket messaging with STOMP?",
            shortAnswer = "Add spring-boot-starter-websocket and annotate a configuration class with @EnableWebSocketMessageBroker. Configure MessageBrokerRegistry to enable a simple in-memory broker (/topic, /queue) and set application destination prefixes (/app). Controllers use @MessageMapping to handle incoming client messages and SimpMessagingTemplate to broadcast events to subscribed clients.",
            keyPoints = listOf(
                "@EnableWebSocketMessageBroker enables STOMP over WebSockets",
                "Simple broker or external broker (RabbitMQ/ActiveMQ) for /topic and /queue",
                "@MessageMapping handles client-to-server messaging",
                "SimpMessagingTemplate sends asynchronous broadcasts to destinations",
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_spring_067",
            trackId = "spring_boot_interview",
            conceptId = "spring_mvc_rest",
            conceptName = "Spring MVC & RESTful Architecture",
            title = "Custom HandlerMethodArgumentResolver",
            question = "When and how do you implement a HandlerMethodArgumentResolver?",
            shortAnswer = "Implement HandlerMethodArgumentResolver to automatically resolve custom method parameters in controllers (e.g. injecting an @CurrentUser UserPrincipal or a tenant header). Implement supportsParameter() to match the target type/annotation, and resolveArgument() to extract data from NativeWebRequest and return the resolved object. Register it in WebMvcConfigurer.addArgumentResolvers().",
            keyPoints = listOf(
                "Decouples parameter extraction from controller business logic",
                "supportsParameter() checks parameter type or custom annotations",
                "resolveArgument() extracts data from request and returns domain object",
                "Registered in WebMvcConfigurer",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_068",
            trackId = "spring_boot_interview",
            conceptId = "spring_mvc_rest",
            conceptName = "Spring MVC & RESTful Architecture",
            title = "Custom HttpMessageConverter Implementation",
            question = "How do you write and register a custom HttpMessageConverter in Spring Boot?",
            shortAnswer = "Extend AbstractHttpMessageConverter<T> and implement supports(), readInternal() (deserializing HTTP request input stream to Java object), and writeInternal() (serializing Java object to output stream with MediaType). Register the converter by overriding extendMessageConverters() in a WebMvcConfigurer bean to append it to the existing converters.",
            keyPoints = listOf(
                "Extends AbstractHttpMessageConverter<T>",
                "Implements readInternal() and writeInternal() with custom MediaType",
                "Registered via WebMvcConfigurer.extendMessageConverters()",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_069",
            trackId = "spring_boot_interview",
            conceptId = "spring_mvc_rest",
            conceptName = "Spring MVC & RESTful Architecture",
            title = "Jackson Custom Serializer and Deserializer",
            question = "How do you customize JSON serialization for specific fields or classes in Spring Boot?",
            shortAnswer = "Extend JsonSerializer<T> overriding serialize(), or JsonDeserializer<T> overriding deserialize(). Register them using @JsonSerialize(using = CustomSerializer.class) directly on entity fields, or annotate the serializer class with @JsonComponent so Spring Boot automatically registers it with the application ObjectMapper.",
            keyPoints = listOf(
                "Extends JsonSerializer<T> or JsonDeserializer<T>",
                "@JsonComponent enables auto-detection and registration by Spring Boot",
                "@JsonSerialize and @JsonDeserialize for field-level binding",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_070",
            trackId = "spring_boot_interview",
            conceptId = "spring_mvc_rest",
            conceptName = "Spring MVC & RESTful Architecture",
            title = "API Versioning Strategies in Spring Boot",
            question = "What are the common API versioning strategies in Spring Boot, and what are their trade-offs?",
            shortAnswer = "1) URI Path Versioning (/api/v1/users): Highly visible, easy to route, browser-friendly (most common). 2) Request Parameter Versioning (/api/users?version=1): Simple fallback. 3) Custom Header Versioning (X-API-VERSION: 1): Keeps URIs clean, but requires API clients to set headers. 4) Media Type / Accept Header (application/vnd.company.v1+json): Pure RESTful HATEOAS, but harder to test in browsers.",
            keyPoints = listOf(
                "URI path versioning is industry standard for clarity and routing",
                "Custom headers keep URIs clean but complicate caching",
                "Accept header / vendor media type aligns with REST purity",
                "Trade-offs: client complexity vs caching friendliness",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_071",
            trackId = "spring_boot_interview",
            conceptId = "spring_mvc_rest",
            conceptName = "Spring MVC & RESTful Architecture",
            title = "HATEOAS in Spring Boot (spring-boot-starter-hateoas)",
            question = "What is HATEOAS, and how does Spring HATEOAS generate hypermedia links?",
            shortAnswer = "HATEOAS (Hypermedia As The Engine Of Application State) allows REST responses to include dynamic hypermedia links informing clients of possible next actions. Spring HATEOAS provides RepresentationModel<T> and WebMvcLinkBuilder. Using linkTo(methodOn(UserController.class).getUser(id)).withSelfRel(), you dynamically generate self, update, or payment links without hardcoding URLs.",
            keyPoints = listOf(
                "Enriches REST responses with actionable hypermedia links",
                "RepresentationModel<T> base class for resource DTOs",
                "WebMvcLinkBuilder.linkTo(methodOn(...)) generates type-safe refactor-safe links",
                "Promotes client decoupling from rigid server endpoints",
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_spring_072",
            trackId = "spring_boot_interview",
            conceptId = "spring_mvc_rest",
            conceptName = "Spring MVC & RESTful Architecture",
            title = "Rate Limiting REST Endpoints in Spring Boot",
            question = "How do you implement API Rate Limiting in Spring Boot using Bucket4j and Redis?",
            shortAnswer = "Add bucket4j-core and bucket4j-redis. In an interceptor or OncePerRequestFilter, extract client identity (API key or IP). Retrieve or create a token bucket backed by Redis, and invoke bucket.tryConsume(1). If true, proceed with the request; if false, halt the request and return HTTP 429 Too Many Requests with headers: X-Rate-Limit-Remaining and Retry-After.",
            keyPoints = listOf(
                "Token Bucket algorithm via Bucket4j",
                "Distributed token state in Redis prevents cluster bypass",
                "HTTP 429 Too Many Requests response with Retry-After header",
                "Applied via Filter or Interceptor based on client IP / API Key",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_073",
            trackId = "spring_boot_interview",
            conceptId = "spring_mvc_rest",
            conceptName = "Spring MVC & RESTful Architecture",
            title = "File Upload and Multipart Configuration",
            question = "How does Spring Boot handle multipart file uploads, and how do you configure upload limits?",
            shortAnswer = "Spring Boot auto-configures StandardServletMultipartResolver. In controllers, accept MultipartFile or MultipartFile[] with @RequestParam('file'). Configure limits in application.properties: spring.servlet.multipart.max-file-size (e.g. 10MB) and spring.servlet.multipart.max-request-size (e.g. 20MB). If limits are exceeded, MaxUploadSizeExceededException is thrown.",
            keyPoints = listOf(
                "StandardServletMultipartResolver handles multipart payloads",
                "Controller accepts MultipartFile or MultipartFile[]",
                "spring.servlet.multipart.max-file-size sets individual file size cap",
                "MaxUploadSizeExceededException caught via @ControllerAdvice",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_074",
            trackId = "spring_boot_interview",
            conceptId = "spring_mvc_rest",
            conceptName = "Spring MVC & RESTful Architecture",
            title = "RestTemplate vs WebClient vs RestClient (Spring 6.1+)",
            question = "Compare RestTemplate, WebClient, and the new RestClient in Spring 6.1 / Spring Boot 3.2.",
            shortAnswer = "RestTemplate is the legacy synchronous HTTP client in maintenance mode. WebClient (from WebFlux) is reactive and non-blocking, supporting both synchronous and asynchronous operations but requires reactive dependencies. RestClient (introduced in Spring 6.1) provides a modern, fluent, synchronous HTTP client interface identical to WebClient's design without pulling in Project Reactor or WebFlux.",
            keyPoints = listOf(
                "RestTemplate is legacy synchronous client in maintenance mode",
                "WebClient is reactive non-blocking client requiring Project Reactor",
                "RestClient is modern fluent synchronous client introduced in Spring 6.1",
                "RestClient is recommended for imperative Spring Boot 3.2+ applications",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_075",
            trackId = "spring_boot_interview",
            conceptId = "spring_mvc_rest",
            conceptName = "Spring MVC & RESTful Architecture",
            title = "HTTP Interfaces / Declarative HTTP Clients (Spring 6+)",
            question = "How do Declarative HTTP Interfaces work in Spring Boot 3 without Feign?",
            shortAnswer = "In Spring 6+, you define a Java interface with HTTP exchange annotations: @GetExchange('/users/{id}'), @PostExchange, etc. You then create a proxy adapter using HttpServiceProxyFactory backed by RestClient or WebClient: factory.createClient(UserClient.class). Spring dynamically generates the HTTP client implementation, providing Feign-like declarative REST calls with native Spring 3 support.",
            keyPoints = listOf(
                "Declarative HTTP interface with @GetExchange, @PostExchange annotations",
                "HttpServiceProxyFactory generates client proxy dynamically",
                "Replaces third-party OpenFeign with first-party Spring framework solution",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_076",
            trackId = "spring_boot_interview",
            conceptId = "spring_jpa_persistence",
            conceptName = "Spring Data JPA & Persistence",
            title = "JPA N+1 Problem and Three Primary Solutions",
            question = "What causes the N+1 query problem in JPA/Hibernate, and what are the three main solutions?",
            shortAnswer = "The N+1 problem occurs when fetching 1 parent entity query triggers N additional individual SQL queries to fetch lazy or eager associations for each parent row. Solutions: 1) JOIN FETCH in JPQL to load parents and associations in a single SQL JOIN. 2) @EntityGraph on repository methods to declaratively specify the fetch plan. 3) @BatchSize(size=25) to batch secondary loads into bulk IN(...) clauses.",
            keyPoints = listOf(
                "1 query for parents + N separate queries for children",
                "JOIN FETCH executes single SQL join query",
                "@EntityGraph specifies declarative dynamic fetch plans",
                "@BatchSize groups individual lookups into batched SQL IN clauses",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_077",
            trackId = "spring_boot_interview",
            conceptId = "spring_jpa_persistence",
            conceptName = "Spring Data JPA & Persistence",
            title = "LazyInitializationException Root Cause and Fixes",
            question = "What causes LazyInitializationException in Hibernate, and how should it be avoided in modern clean architecture?",
            shortAnswer = "LazyInitializationException occurs when accessing a lazy-loaded association or collection on a detached entity after the Hibernate Session/Persistence Context has closed (e.g. inside a Controller or DTO serializer). Avoid using Open Session in View (OSIV) as a band-aid. Proper fixes: fetch required associations in the service layer using JOIN FETCH, @EntityGraph, or map directly to projection DTOs inside the transaction boundary.",
            keyPoints = listOf(
                "Accessing uninitialized proxy after Persistence Context is closed",
                "Do not rely on Open Session in View (OSIV) due to connection holding risks",
                "Fetch required data inside @Transactional service boundary via JOIN FETCH",
                "Map to DTOs before exiting the transaction",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_078",
            trackId = "spring_boot_interview",
            conceptId = "spring_jpa_persistence",
            conceptName = "Spring Data JPA & Persistence",
            title = "Open Session in View (OSIV) Pattern Trade-Offs",
            question = "Why does Spring Boot enable spring.jpa.open-in-view=true by default, and why should it be disabled in high-scale production?",
            shortAnswer = "OSIV keeps the Hibernate Session and database connection open throughout the entire web request, allowing view templates and JSON serializers to lazily fetch associations without throwing LazyInitializationException. In high-traffic microservices, this severely exhausts database connection pools because slow HTTP transfers hold DB connections idle. Best practice: set spring.jpa.open-in-view=false.",
            keyPoints = listOf(
                "OSIV keeps DB session and connection open through view rendering",
                "Prevents LazyInitializationException for naive templates",
                "Causes DB connection pool exhaustion under load",
                "Production best practice is disabling OSIV (spring.jpa.open-in-view=false)",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_079",
            trackId = "spring_boot_interview",
            conceptId = "spring_jpa_persistence",
            conceptName = "Spring Data JPA & Persistence",
            title = "Hibernate Entity Lifecycle States",
            question = "Describe the four states of an entity in Hibernate and how transitions occur.",
            shortAnswer = "1) Transient: Newly instantiated with 'new', not in DB, not associated with a Session. 2) Persistent/Managed: Associated with an open Session, tracked by first-level cache, automatic dirty checking flushes updates. 3) Detached: Previously managed, but the Session was closed or clear()/detach() was called. 4) Removed: Scheduled for deletion on flush via session.remove().",
            keyPoints = listOf(
                "Transient (new, unmanaged) -> persist() -> Managed",
                "Managed (tracked in Session with dirty checking) -> close()/detach() -> Detached",
                "Detached -> merge() -> Managed",
                "Managed -> remove() -> Removed",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_080",
            trackId = "spring_boot_interview",
            conceptId = "spring_jpa_persistence",
            conceptName = "Spring Data JPA & Persistence",
            title = "First-Level Cache vs Second-Level Cache in Hibernate",
            question = "Differentiate between First-Level and Second-Level caching in Hibernate.",
            shortAnswer = "First-level cache is the Hibernate Session (Persistence Context) cache. It is enabled by default, non-configurable, and scoped to a single transaction/session. Second-level cache is optional, shared across all Sessions in the SessionFactory, and configurable with distributed cache providers (Redis, Hazelcast, Ehcache) via @Cacheable and @Cache(usage = ...).",
            keyPoints = listOf(
                "First-level cache is mandatory, per-Session, transaction-scoped",
                "Second-level cache is optional, cross-Session, process or cluster-wide",
                "Second-level caching requires cache providers (Ehcache, Hazelcast, Redis)",
                "Cache concurrency strategies: READ_ONLY, NONSTRICT_READ_WRITE, READ_WRITE",
            ),
            difficulty = "Mid-Level"
        ),
    )

    private fun part5(): List<InterviewQuestion> = listOf(
        InterviewQuestion(
            id = "iq_spring_081",
            trackId = "spring_boot_interview",
            conceptId = "spring_jpa_persistence",
            conceptName = "Spring Data JPA & Persistence",
            title = "Hibernate Dirty Checking Mechanism",
            question = "How does Hibernate Dirty Checking work, and why do you not need to call save() for updates inside @Transactional?",
            shortAnswer = "When an entity is loaded into the Persistence Context, Hibernate creates a snapshot copy in the first-level cache. During transaction commit or before query flush, Hibernate compares the entity's current state with the snapshot. If any field differs, it automatically generates and executes an SQL UPDATE statement without requiring an explicit repository.save() call.",
            keyPoints = listOf(
                "Compares current entity state against initial snapshot in first-level cache",
                "Executes during flush phase before transaction commit",
                "Calling repository.save() on managed entities is redundant",
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_spring_082",
            trackId = "spring_boot_interview",
            conceptId = "spring_jpa_persistence",
            conceptName = "Spring Data JPA & Persistence",
            title = "Optimistic Locking with @Version",
            question = "How does Optimistic Locking work in JPA, and what happens when concurrent updates collide?",
            shortAnswer = "Optimistic locking adds a @Version column (integer or timestamp) to the entity. When updating, Hibernate executes: UPDATE entity SET ..., version = version + 1 WHERE id = ? AND version = current_version. If another transaction updated the row first, the version check fails (0 rows updated), and Hibernate throws OptimisticLockException. The application can retry or notify the user.",
            keyPoints = listOf(
                "@Version annotation tracks entity modification revisions",
                "SQL update checks matching version in WHERE clause",
                "Throws OptimisticLockException on concurrent conflict",
                "High scalability because it avoids database-level row locks",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_083",
            trackId = "spring_boot_interview",
            conceptId = "spring_jpa_persistence",
            conceptName = "Spring Data JPA & Persistence",
            title = "Pessimistic Locking in Spring Data JPA",
            question = "When should you use Pessimistic Locking, and how do PESSIMISTIC_READ and PESSIMISTIC_WRITE differ?",
            shortAnswer = "Pessimistic locking uses database-level row locks for high-contention scenarios (e.g. banking balances, inventory reservation) where conflicts are frequent and rollbacks costly. PESSIMISTIC_READ applies a shared lock (SELECT ... FOR SHARE), allowing other readers but blocking writers. PESSIMISTIC_WRITE applies an exclusive lock (SELECT ... FOR UPDATE), blocking both other readers and writers.",
            keyPoints = listOf(
                "Applies database-level row locks via SELECT ... FOR UPDATE",
                "@Lock(LockModeType.PESSIMISTIC_WRITE) on repository methods",
                "PESSIMISTIC_READ is shared lock; PESSIMISTIC_WRITE is exclusive lock",
                "Prevents conflicts upfront at the cost of concurrency throughput",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_084",
            trackId = "spring_boot_interview",
            conceptId = "spring_jpa_persistence",
            conceptName = "Spring Data JPA & Persistence",
            title = "Spring Data Interface-Based vs Class-Based Projections",
            question = "Why should you use Spring Data Projections instead of fetching complete entities?",
            shortAnswer = "Fetching full entities loads all columns and manages them in the Persistence Context, consuming excessive memory. Projections fetch only necessary columns. Interface-based projections define getter methods (e.g. interface UserSummary { String getName(); String getEmail(); }), causing Spring Data to generate a dynamic proxy reading only those columns from SQL. Class-based (DTO) projections use constructor expressions (SELECT new com.example.UserDto(...)).",
            keyPoints = listOf(
                "Projections avoid loading full entity graphs and L1 cache overhead",
                "Interface projections generate dynamic proxies for selected columns",
                "DTO class projections use constructor expressions in JPQL",
                "Massively improves read query performance and memory footprint",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_085",
            trackId = "spring_boot_interview",
            conceptId = "spring_jpa_persistence",
            conceptName = "Spring Data JPA & Persistence",
            title = "Dynamic Queries with JPA Specifications and Criteria API",
            question = "How do JPA Specifications enable dynamic search and filtering in Spring Data JPA?",
            shortAnswer = "Repositories extend JpaSpecificationExecutor<T>. A Specification<T> wraps a functional method toPredicate(Root, CriteriaQuery, CriteriaBuilder). Specifications can be combined dynamically using logical operators (Specification.where(hasName).and(hasAge).or(hasCity)), producing type-safe dynamic SQL queries based on user search filters without string concatenation.",
            keyPoints = listOf(
                "Extend JpaSpecificationExecutor<T> in repository",
                "Specification functional interface constructs CriteriaBuilder predicates",
                "Compose filters dynamically using .where(), .and(), .or()",
                "Type-safe and prevents SQL injection",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_086",
            trackId = "spring_boot_interview",
            conceptId = "spring_jpa_persistence",
            conceptName = "Spring Data JPA & Persistence",
            title = "CascadeType and OrphanRemoval Differences",
            question = "What is the difference between CascadeType.REMOVE and orphanRemoval = true in JPA relationships?",
            shortAnswer = "CascadeType.REMOVE cascades the delete operation: when the parent entity is deleted, all associated child entities are also deleted. orphanRemoval = true does that AND additionally deletes a child from the database if it is simply removed from the parent's collection (e.g. parent.getChildren().remove(child)), treating disconnected children as orphans.",
            keyPoints = listOf(
                "CascadeType.REMOVE deletes children only when parent is deleted",
                "orphanRemoval = true also deletes child if removed from parent collection",
                "orphanRemoval models true composition lifecycle",
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_spring_087",
            trackId = "spring_boot_interview",
            conceptId = "spring_jpa_persistence",
            conceptName = "Spring Data JPA & Persistence",
            title = "Primary Key Generation Strategies: IDENTITY vs SEQUENCE",
            question = "Why does GenerationType.IDENTITY disable Hibernate batch inserts, and why is SEQUENCE preferred?",
            shortAnswer = "GenerationType.IDENTITY relies on the DB auto-increment column. To obtain the generated ID, Hibernate must execute the SQL INSERT immediately when persist() is called, completely breaking Hibernate's write-behind optimization and JDBC batching. GenerationType.SEQUENCE pre-allocates IDs from a database sequence in blocks (allocationSize=50), allowing Hibernate to batch hundreds of INSERT statements in a single round-trip.",
            keyPoints = listOf(
                "IDENTITY forces immediate SQL INSERT to retrieve generated ID",
                "Disables JDBC batching and write-behind cache",
                "SEQUENCE pre-allocates ID ranges via allocationSize parameter",
                "SEQUENCE enables high-performance bulk batch inserts",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_088",
            trackId = "spring_boot_interview",
            conceptId = "spring_jpa_persistence",
            conceptName = "Spring Data JPA & Persistence",
            title = "HikariCP Connection Pool Configuration and Tuning",
            question = "How do you configure and tune HikariCP connection pool settings in Spring Boot?",
            shortAnswer = "Spring Boot uses HikariCP by default. Key properties: spring.datasource.hikari.maximum-pool-size (calculated via formula: connections = ((core_count * 2) + effective_spindle_count)), minimum-idle (recommend same as max-pool-size for fixed pool), connection-timeout (max wait for connection, e.g. 20000ms), and max-lifetime (must be 30-60s shorter than DB server connection timeout to prevent broken pipes).",
            keyPoints = listOf(
                "maximum-pool-size sized according to CPU cores and I/O disk concurrency",
                "minimum-idle equal to maximum-pool-size avoids connection spike latency",
                "max-lifetime set below database server timeout to prevent stale TCP resets",
                "connection-timeout defines fail-fast latency cap",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_089",
            trackId = "spring_boot_interview",
            conceptId = "spring_jpa_persistence",
            conceptName = "Spring Data JPA & Persistence",
            title = "Database Schema Migrations with Flyway",
            question = "How does Flyway manage database schema versioning in Spring Boot applications?",
            shortAnswer = "Include flyway-core. Flyway scans classpath:db/migration for SQL scripts named with version patterns: V1__init.sql, V2__add_index.sql. On startup, Flyway creates a flyway_schema_history table. It calculates checksums for migration scripts, executes unapplied scripts in strict numerical order within a transaction, and fails fast if previously applied scripts were modified, guaranteeing reproducible schemas across environments.",
            keyPoints = listOf(
                "V{version}__{description}.sql naming convention",
                "flyway_schema_history table tracks executed versions and checksums",
                "Executes migrations automatically before JPA EntityManagerFactory initializes",
                "Fails fast on checksum mismatch to prevent schema drift",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_090",
            trackId = "spring_boot_interview",
            conceptId = "spring_jpa_persistence",
            conceptName = "Spring Data JPA & Persistence",
            title = "Auditing with @EnableJpaAuditing",
            question = "How do you automatically track createdDate, lastModifiedDate, and createdBy in JPA entities?",
            shortAnswer = "Annotate a configuration class with @EnableJpaAuditing. Annotate the entity base class (@MappedSuperclass) with @EntityListeners(AuditingEntityListener.class) and fields with @CreatedDate, @LastModifiedDate, @CreatedBy, and @LastModifiedBy. Implement AuditorAware<String> bean returning the current authenticated username from SecurityContextHolder.",
            keyPoints = listOf(
                "@EnableJpaAuditing and @EntityListeners(AuditingEntityListener.class)",
                "@CreatedDate and @LastModifiedDate manage automatic timestamps",
                "AuditorAware<T> supplies authenticated principal for @CreatedBy",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_091",
            trackId = "spring_boot_interview",
            conceptId = "spring_jpa_persistence",
            conceptName = "Spring Data JPA & Persistence",
            title = "Pagination and Slice in Spring Data JPA",
            question = "What is the difference between returning Page<T> and Slice<T> in repository queries?",
            shortAnswer = "Page<T> executes two queries: the content query and an additional COUNT(*) query to calculate total elements and total pages, which is extremely expensive on large tables. Slice<T> fetches pageSize + 1 records to determine if a next page exists (slice.hasNext()) without executing a COUNT(*) query, making it vastly faster for mobile infinite scrolling or large data streams.",
            keyPoints = listOf(
                "Page<T> triggers an expensive extra SELECT COUNT(*) query",
                "Slice<T> fetches (pageSize + 1) without COUNT query",
                "Slice is optimal for infinite scroll and high-performance pagination",
                "Sort parameter handles dynamic column ordering",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_092",
            trackId = "spring_boot_interview",
            conceptId = "spring_jpa_persistence",
            conceptName = "Spring Data JPA & Persistence",
            title = "Modifying Queries with @Modifying in Spring Data",
            question = "Why is @Modifying required on UPDATE/DELETE @Query methods, and what does clearAutomatically do?",
            shortAnswer = "@Modifying tells Spring Data that the query is an executeUpdate() DML operation rather than a SELECT query. clearAutomatically = true clears the Hibernate first-level cache (Persistence Context) immediately after execution. This is critical because direct bulk updates execute in the database bypassing the Persistence Context; clearing ensures subsequent entity lookups don't read stale cached data.",
            keyPoints = listOf(
                "Required for INSERT, UPDATE, DELETE JPQL/Native queries",
                "Executes via executeUpdate() instead of getResultList()",
                "clearAutomatically = true clears Persistence Context to prevent stale entity reads",
                "flushAutomatically = true flushes pending changes before execution",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_093",
            trackId = "spring_boot_interview",
            conceptId = "spring_jpa_persistence",
            conceptName = "Spring Data JPA & Persistence",
            title = "Soft Delete Pattern in Hibernate with @SQLDelete and @Where / @SQLRestriction",
            question = "How do you implement transparent Soft Delete in Spring Boot entities?",
            shortAnswer = "Add a boolean deleted column. Annotate the entity with @SQLDelete(sql = 'UPDATE my_table SET deleted = true WHERE id = ?') so repository.delete() executes an UPDATE. In Hibernate 6 / Spring Boot 3, annotate the entity with @SQLRestriction('deleted = false') (or @Where in older versions) so all SELECT queries automatically append the filter clause without manual modification.",
            keyPoints = listOf(
                "@SQLDelete overrides standard SQL DELETE with an UPDATE statement",
                "@SQLRestriction('deleted = false') automatically filters out soft-deleted records in SELECT queries",
                "Preserves audit trail and relational integrity",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_094",
            trackId = "spring_boot_interview",
            conceptId = "spring_jpa_persistence",
            conceptName = "Spring Data JPA & Persistence",
            title = "Spring Cache Abstraction: @Cacheable, @CachePut, @CacheEvict",
            question = "Explain the roles of @Cacheable, @CachePut, and @CacheEvict in Spring's caching abstraction.",
            shortAnswer = "@Cacheable checks the cache first; if key exists, it returns cached data and skips method execution; if missing, it runs the method and caches the return value. @CachePut always executes the method and updates the cache with the result (ideal for updates). @CacheEvict removes one or all entries (allEntries = true) from the cache (ideal for deletes).",
            keyPoints = listOf(
                "@Cacheable skips method execution on cache hit",
                "@CachePut always executes method and refreshes cache entry",
                "@CacheEvict purges stale entries on deletion or modification",
                "CacheManager pluggable with Redis, Caffeine, or Hazelcast",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_095",
            trackId = "spring_boot_interview",
            conceptId = "spring_jpa_persistence",
            conceptName = "Spring Data JPA & Persistence",
            title = "Redis Cache Manager Integration with Spring Boot",
            question = "How do you configure Redis as the central cache manager in Spring Boot with TTL per cache?",
            shortAnswer = "Add spring-boot-starter-data-redis and spring-boot-starter-cache with @EnableCaching. Declare a RedisCacheManager bean using RedisCacheConfiguration. Customize default TTL, disable caching null values, and define a map of custom RedisCacheConfigurations with distinct TTLs (e.g. 5 minutes for user-tokens, 24 hours for lookup-tables) and Jackson JSON serialization.",
            keyPoints = listOf(
                "spring-boot-starter-data-redis with @EnableCaching",
                "RedisCacheConfiguration sets default and per-cache TTL durations",
                "Configuring GenericJackson2JsonRedisSerializer for human-readable JSON in Redis",
                "disableCachingNullValues() prevents cache penetration",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_096",
            trackId = "spring_boot_interview",
            conceptId = "spring_jpa_persistence",
            conceptName = "Spring Data JPA & Persistence",
            title = "Composite Primary Keys: @IdClass vs @EmbeddedId",
            question = "Compare @IdClass and @EmbeddedId for defining composite keys in JPA.",
            shortAnswer = "Both represent composite keys that implement Serializable, equals(), and hashCode(). @IdClass repeats the key fields in the entity as separate @Id fields, using a separate class as the ID identifier. @EmbeddedId encapsulates all key fields inside an @Embeddable class, which is placed as a single field in the entity (@EmbeddedId private OrderId id;), resulting in cleaner object-oriented encapsulation.",
            keyPoints = listOf(
                "Must implement Serializable, equals(), and hashCode()",
                "@IdClass flattens key fields in entity using shadow ID class",
                "@EmbeddedId encapsulates key fields into a single @Embeddable object field",
                "@EmbeddedId provides superior object-oriented encapsulation",
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_spring_097",
            trackId = "spring_boot_interview",
            conceptId = "spring_jpa_persistence",
            conceptName = "Spring Data JPA & Persistence",
            title = "JPA Inheritance Strategies",
            question = "Compare the three JPA inheritance mapping strategies: SINGLE_TABLE, JOINED, and TABLE_PER_CLASS.",
            shortAnswer = "1) SINGLE_TABLE: All classes in hierarchy mapped to one table with a @DiscriminatorColumn; fastest (no joins), but nullable columns required. 2) JOINED: Base table has shared fields, subclasses have separate tables joined on primary key; normalized without nulls, but query joins add overhead. 3) TABLE_PER_CLASS: Concrete class has standalone complete table; polymorphic queries require expensive UNIONs.",
            keyPoints = listOf(
                "SINGLE_TABLE has best performance with discriminator column, but causes sparse nullable columns",
                "JOINED is normalized with foreign key joins, slight performance penalty",
                "TABLE_PER_CLASS creates independent tables requiring SQL UNION for polymorphic queries",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_098",
            trackId = "spring_boot_interview",
            conceptId = "spring_jpa_persistence",
            conceptName = "Spring Data JPA & Persistence",
            title = "Entity Callback Annotations vs EntityListeners",
            question = "How do JPA Entity Callbacks (@PrePersist, @PostPersist, @PreUpdate) work?",
            shortAnswer = "JPA provides lifecycle event annotations placed on entity methods or external @EntityListeners classes: @PrePersist (runs before INSERT), @PostPersist, @PreUpdate (runs before UPDATE), @PostUpdate, @PreRemove, @PostRemove, and @PostLoad. They allow automated auditing, data formatting (e.g. lowercasing emails), or publishing domain events before DB mutations.",
            keyPoints = listOf(
                "Execute directly during entity lifecycle state transitions",
                "@PrePersist and @PreUpdate allow sanitizing or calculating fields",
                "External @EntityListeners decouple lifecycle logic from domain entities",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_099",
            trackId = "spring_boot_interview",
            conceptId = "spring_jpa_persistence",
            conceptName = "Spring Data JPA & Persistence",
            title = "ReadOnly Transactions and Performance Optimization",
            question = "What performance optimizations occur when you specify @Transactional(readOnly = true)?",
            shortAnswer = "1) Hibernate sets the FlushMode to MANUAL, completely disabling dirty checking and eliminating memory snapshots in the first-level cache. 2) The JDBC connection is marked read-only, allowing databases (like PostgreSQL, MySQL) to route queries to read replicas and skip transaction log writes. 3) Memory footprint is drastically reduced for large result sets.",
            keyPoints = listOf(
                "Disables Hibernate dirty-checking snapshot creation and comparison",
                "Sets FlushMode to MANUAL",
                "Enables database driver routing to read replicas",
                "Reduces CPU and heap allocation for query-heavy operations",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_100",
            trackId = "spring_boot_interview",
            conceptId = "spring_jpa_persistence",
            conceptName = "Spring Data JPA & Persistence",
            title = "Database Read/Write Replica Routing via AbstractRoutingDataSource",
            question = "How do you implement dynamic database read/write replica routing in Spring Boot?",
            shortAnswer = "Configure multiple DataSource beans: WriteDataSource (master) and ReadDataSource (replica). Implement an AbstractRoutingDataSource extending determineCurrentLookupKey(), which inspects TransactionSynchronizationManager.isCurrentTransactionReadOnly(). If true, it routes the connection to the read replica; otherwise, to the write master. Wrap in a LazyConnectionDataSourceProxy so connection acquisition is deferred until actual SQL execution.",
            keyPoints = listOf(
                "AbstractRoutingDataSource inspects transaction context lookup key",
                "TransactionSynchronizationManager.isCurrentTransactionReadOnly() determines target",
                "LazyConnectionDataSourceProxy defers connection checkout until first SQL query",
                "Enables transparent scaling of database read traffic",
            ),
            difficulty = "Senior"
        ),
    )

    private fun part6(): List<InterviewQuestion> = listOf(
        InterviewQuestion(
            id = "iq_spring_101",
            trackId = "spring_boot_interview",
            conceptId = "spring_transactions",
            conceptName = "Transaction Management & Isolation",
            title = "@Transactional Propagation: REQUIRED vs REQUIRES_NEW",
            question = "Compare Propagation.REQUIRED and Propagation.REQUIRES_NEW. What happens if the inner REQUIRES_NEW transaction fails?",
            shortAnswer = "REQUIRED (default) joins the existing transaction if one exists, or starts a new one if none exists. REQUIRES_NEW always suspends the outer transaction and starts a completely independent new physical transaction. If the inner transaction throws an unhandled exception, it rolls back; if the outer transaction catches the exception, the outer transaction can still commit successfully. If uncaught, both roll back.",
            keyPoints = listOf(
                "REQUIRED joins existing transaction; shares commit/rollback outcome",
                "REQUIRES_NEW suspends outer transaction and creates independent physical transaction",
                "Inner rollback does not force outer rollback if outer method catches the exception",
                "Requires two physical database connections while suspended",
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_spring_102",
            trackId = "spring_boot_interview",
            conceptId = "spring_transactions",
            conceptName = "Transaction Management & Isolation",
            title = "Propagation.NESTED vs REQUIRES_NEW",
            question = "How does Propagation.NESTED differ from Propagation.REQUIRES_NEW?",
            shortAnswer = "NESTED uses a single physical database connection and creates a database Savepoint within the existing outer transaction. If the nested transaction fails, only work done since the savepoint is rolled back; the outer transaction continues without rolling back. REQUIRES_NEW uses two separate physical database connections and transactions. NESTED requires JDBC 3.0 Savepoint support (not supported by JTA).",
            keyPoints = listOf(
                "NESTED uses database Savepoints within the same physical connection",
                "REQUIRES_NEW creates an independent transaction on a second connection",
                "Failing NESTED rolls back to savepoint without aborting outer transaction",
                "Requires underlying JDBC driver savepoint capability",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_103",
            trackId = "spring_boot_interview",
            conceptId = "spring_transactions",
            conceptName = "Transaction Management & Isolation",
            title = "Overview of All 7 Spring Propagation Behaviors",
            question = "Briefly explain all 7 transaction propagation behaviors in Spring.",
            shortAnswer = "1) REQUIRED: Join existing, or start new. 2) REQUIRES_NEW: Suspend existing, start new. 3) SUPPORTS: Join if exists, run non-transactionally if none. 4) NOT_SUPPORTED: Suspend existing, run non-transactionally. 5) MANDATORY: Join existing; throw exception if none exists. 6) NEVER: Throw exception if transaction exists; run non-transactionally. 7) NESTED: Execute within a savepoint if transaction exists, or start new.",
            keyPoints = listOf(
                "REQUIRED, REQUIRES_NEW, NESTED",
                "SUPPORTS, NOT_SUPPORTED",
                "MANDATORY, NEVER",
                "Know which ones require, forbid, or suspend transactions",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_104",
            trackId = "spring_boot_interview",
            conceptId = "spring_transactions",
            conceptName = "Transaction Management & Isolation",
            title = "Database Isolation Levels and Concurrency Phenomena",
            question = "Explain the four SQL isolation levels and the anomalies they prevent (Dirty Read, Non-Repeatable Read, Phantom Read).",
            shortAnswer = "1) READ_UNCOMMITTED: Allows dirty reads (reading uncommitted changes). 2) READ_COMMITTED: Prevents dirty reads, but allows non-repeatable reads (re-reading a row yields different data due to concurrent commit). 3) REPEATABLE_READ: Prevents dirty and non-repeatable reads using MVCC/shared locks, but allows phantom reads (re-querying yields new matching rows). 4) SERIALIZABLE: Prevents all anomalies via strict range locks or serial execution.",
            keyPoints = listOf(
                "Dirty Read: reading uncommitted data that may roll back",
                "Non-Repeatable Read: re-reading single row returns modified values",
                "Phantom Read: re-running range query returns newly inserted rows",
                "Default in PostgreSQL/Oracle is READ_COMMITTED; MySQL InnoDB is REPEATABLE_READ",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_105",
            trackId = "spring_boot_interview",
            conceptId = "spring_transactions",
            conceptName = "Transaction Management & Isolation",
            title = "Default Rollback Rules and rollbackFor Attribute",
            question = "What exceptions cause @Transactional to roll back by default, and why should you configure rollbackFor = Exception.class?",
            shortAnswer = "By default, Spring rolls back transactions only on unchecked exceptions (RuntimeException and Error). Checked exceptions (subclasses of Exception that aren't RuntimeException) will NOT trigger a rollback; the transaction will commit. Specifying @Transactional(rollbackFor = Exception.class) ensures any checked exception (e.g. IOException, custom business exceptions) also triggers a transaction rollback.",
            keyPoints = listOf(
                "Default rollback triggers on RuntimeException and Error only",
                "Checked exceptions do NOT cause rollback by default",
                "Always specify rollbackFor = Exception.class in enterprise applications",
                "noRollbackFor allows whitelisting specific exceptions",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_106",
            trackId = "spring_boot_interview",
            conceptId = "spring_transactions",
            conceptName = "Transaction Management & Isolation",
            title = "Self-Invocation Bypass of @Transactional",
            question = "Why does calling a @Transactional method from another method within the same bean class fail to manage the transaction?",
            shortAnswer = "Spring @Transactional is implemented via AOP runtime proxies (TransactionInterceptor). When an external bean calls the method, the call goes through the proxy, which begins and commits the transaction. Inside the same class, invoking this.otherMethod() bypasses the proxy and executes directly on the target object, so no transaction interceptor is ever invoked.",
            keyPoints = listOf(
                "Proxies intercept calls from external callers only",
                "Internal 'this' calls bypass the Spring proxy",
                "Resolutions: move method to a separate service bean, self-inject via @Lazy, or use TransactionTemplate",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_107",
            trackId = "spring_boot_interview",
            conceptId = "spring_transactions",
            conceptName = "Transaction Management & Isolation",
            title = "Programmatic Transactions with TransactionTemplate",
            question = "When and how do you use TransactionTemplate instead of declarative @Transactional?",
            shortAnswer = "Use TransactionTemplate when you need fine-grained control over transaction boundaries (e.g. executing slow HTTP/file operations outside the transaction, or starting a transaction inside a loop without self-invocation proxy issues). Inject TransactionTemplate and call execute(status -> { ... return result; }). To trigger rollback programmatically, call status.setRollbackOnly().",
            keyPoints = listOf(
                "Avoids long-running transactions holding DB connections during HTTP calls",
                "Eliminates self-invocation proxy issues",
                "TransactionTemplate.execute() callback pattern",
                "status.setRollbackOnly() triggers explicit rollback",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_108",
            trackId = "spring_boot_interview",
            conceptId = "spring_transactions",
            conceptName = "Transaction Management & Isolation",
            title = "PlatformTransactionManager Architecture",
            question = "What is the role of PlatformTransactionManager in Spring's transaction architecture?",
            shortAnswer = "PlatformTransactionManager is the core SPI abstraction for transaction management, decoupling code from specific persistence technologies. Key implementations include JpaTransactionManager (for JPA/Hibernate), DataSourceTransactionManager (for plain JDBC/MyBatis), and JtaTransactionManager (for distributed XA transactions). It defines getTransaction(), commit(), and rollback() methods.",
            keyPoints = listOf(
                "Core SPI with getTransaction, commit, rollback methods",
                "JpaTransactionManager binds EntityManager and JDBC connection to thread",
                "DataSourceTransactionManager manages raw JDBC connections",
                "Decouples transaction semantics from underlying technology",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_109",
            trackId = "spring_boot_interview",
            conceptId = "spring_transactions",
            conceptName = "Transaction Management & Isolation",
            title = "Transaction Synchronization and ThreadLocal Binding",
            question = "How does Spring bind active transactions and database connections to the executing thread?",
            shortAnswer = "Spring uses TransactionSynchronizationManager, which maintains ThreadLocal variables holding the current transaction state, active database Connection/EntityManagerHolder, and registered TransactionSynchronization callbacks (beforeCommit, afterCommit, afterCompletion). This ensures all repositories participating in the transaction reuse the exact same connection.",
            keyPoints = listOf(
                "TransactionSynchronizationManager uses ThreadLocal storage",
                "Binds active Connection/EntityManager to the thread",
                "Enables thread-safe sharing of connection across multiple DAOs",
                "TransactionSynchronization hooks provide callbacks for commit/rollback events",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_110",
            trackId = "spring_boot_interview",
            conceptId = "spring_transactions",
            conceptName = "Transaction Management & Isolation",
            title = "Saga Pattern vs 2PC (Two-Phase Commit) in Microservices",
            question = "Why is Two-Phase Commit (2PC / XA) avoided in modern distributed microservices, and how does the Saga Pattern replace it?",
            shortAnswer = "2PC is a blocking protocol requiring distributed locks across services; if the coordinator or a participant fails, resources remain locked indefinitely, causing poor scalability and latency bottlenecks. The Saga Pattern decomposes the distributed transaction into a sequence of local transactions. If a step fails, the Saga executes Compensating Transactions backward to undo previous changes, using either Choreography (events) or Orchestration.",
            keyPoints = listOf(
                "2PC causes distributed blocking locks and single point of failure coordinator",
                "Sagas execute individual local transactions per service",
                "Compensating transactions reverse partial changes on failure",
                "Choreography (event-driven) vs Orchestration (central workflow state machine)",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_111",
            trackId = "spring_boot_interview",
            conceptId = "spring_transactions",
            conceptName = "Transaction Management & Isolation",
            title = "Transaction Timeout and Deadlock Detection",
            question = "How does @Transactional(timeout = 10) work, and how are deadlocks handled in Spring Boot?",
            shortAnswer = "The timeout attribute specifies the maximum duration (in seconds) allowed for the transaction before Spring marks it as rollback-only. When passed to the underlying database via Statement.setQueryTimeout(), the database aborts queries exceeding the limit. If a deadlock occurs, the database aborts one transaction with a deadlock error, which Spring translates into CannotAcquireLockException or DeadlockLoserDataAccessException for retry logic.",
            keyPoints = listOf(
                "timeout sets maximum transaction execution duration",
                "Propagated to JDBC Statement.setQueryTimeout()",
                "Spring translates DB deadlock codes to CannotAcquireLockException",
                "Retry patterns (@Retryable) handle transient deadlock errors",
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_spring_112",
            trackId = "spring_boot_interview",
            conceptId = "spring_transactions",
            conceptName = "Transaction Management & Isolation",
            title = "Pitfalls of Long-Running Transactions",
            question = "What severe architectural hazards are caused by long-running @Transactional methods in high-traffic APIs?",
            shortAnswer = "1) Connection pool exhaustion: keeping DB connections checked out while waiting for third-party REST APIs or file processing blocks other threads. 2) Database lock contention: prolonged row or table locks cause thread queuing and deadlocks. 3) Undo log / MVCC bloat: long transactions prevent vacuuming/purge of old row versions. Rule: Keep @Transactional strictly around DB operations; execute remote calls outside.",
            keyPoints = listOf(
                "Connection pool exhaustion starving other requests",
                "Excessive database row/table lock holding time",
                "Database undo log / temp space bloat in MVCC engines",
                "Remote HTTP/RPC calls must never be executed inside a @Transactional block",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_113",
            trackId = "spring_boot_interview",
            conceptId = "spring_transactions",
            conceptName = "Transaction Management & Isolation",
            title = "Multiple Transaction Managers and @Transactional('tmName')",
            question = "How do you configure and use multiple transaction managers for separate databases in Spring Boot?",
            shortAnswer = "Declare two DataSource, EntityManagerFactory, and PlatformTransactionManager beans (e.g. primaryTransactionManager and secondaryTransactionManager). Mark one with @Primary as the default. To target the secondary database, specify the qualifier name in the annotation: @Transactional('secondaryTransactionManager').",
            keyPoints = listOf(
                "Declare separate DataSource and PlatformTransactionManager beans",
                "@Primary designates default transaction manager",
                "@Transactional('customTxManager') routes to specific transaction manager",
                "Avoids distributed XA overhead when operations are segregated by database",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_114",
            trackId = "spring_boot_interview",
            conceptId = "spring_transactions",
            conceptName = "Transaction Management & Isolation",
            title = "ChainedTransactionManager Deprecation and Alternatives",
            question = "Why was ChainedTransactionManager deprecated in Spring, and what should be used instead?",
            shortAnswer = "ChainedTransactionManager attempted pseudo-distributed transactions across multiple data sources in a 1-2 commit order. If the second commit failed, the first could not be rolled back, leaving data permanently inconsistent (silent partial commit). It was deprecated because it falsely implied transactional safety. Proper alternatives: Saga pattern with compensating transactions, Outbox pattern, or explicit JTA.",
            keyPoints = listOf(
                "Cannot guarantee atomicity across multiple resources without 2PC",
                "Partial commit failure causes silent data corruption",
                "Replaced by Saga Pattern or Transactional Outbox pattern",
                "JTA/XA used only when true 2PC is strictly required",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_115",
            trackId = "spring_boot_interview",
            conceptId = "spring_transactions",
            conceptName = "Transaction Management & Isolation",
            title = "Transactional Outbox Pattern for Reliable Event Publishing",
            question = "How does the Transactional Outbox Pattern solve dual-write inconsistency between the database and Kafka?",
            shortAnswer = "Writing to a database and publishing to Kafka in the same method suffers from dual-write failure: if Kafka fails after DB commit, events are lost; if DB fails after Kafka send, ghost events are published. The Outbox pattern writes domain entity changes AND an OutboxEvent record to the same database within a single local @Transactional boundary. A separate asynchronous process (Debezium CDC or polling publisher) reads the Outbox table and publishes to Kafka with at-least-once delivery guarantees.",
            keyPoints = listOf(
                "Solves dual-write distributed failure without 2PC",
                "Writes business entity and outbox message in same local database transaction",
                "Asynchronous CDC (Debezium) or poller streams outbox rows to Kafka/RabbitMQ",
                "Guarantees at-least-once message delivery",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_116",
            trackId = "spring_boot_interview",
            conceptId = "spring_security",
            conceptName = "Spring Security & Filter Chains",
            title = "DelegatingFilterProxy and FilterChainProxy Architecture",
            question = "How does Spring Security hook into a standard Servlet container? Explain DelegatingFilterProxy and FilterChainProxy.",
            shortAnswer = "The Servlet container only knows standard servlet filters. Spring registers DelegatingFilterProxy in the servlet container, which delegates HTTP request filtering to a Spring bean called FilterChainProxy. FilterChainProxy manages one or more SecurityFilterChains, which contain ordered security filters (UsernamePasswordAuthenticationFilter, BearerTokenFilter, AuthorizationFilter) matching specific URL patterns.",
            keyPoints = listOf(
                "DelegatingFilterProxy bridges Servlet container to Spring ApplicationContext",
                "FilterChainProxy is the Spring-managed bean orchestrating security",
                "Contains ordered SecurityFilterChains mapped by RequestMatcher",
                "Invokes security filters in strict sequence",
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_spring_117",
            trackId = "spring_boot_interview",
            conceptId = "spring_security",
            conceptName = "Spring Security & Filter Chains",
            title = "Modern SecurityFilterChain Configuration with Lambda DSL",
            question = "How is Spring Security configured in Spring Boot 3 using SecurityFilterChain and Lambda DSL?",
            shortAnswer = "In Spring Boot 3 / Security 6, WebSecurityConfigurerAdapter is removed. You declare a @Bean of type SecurityFilterChain accepting HttpSecurity: http.csrf(csrf -> csrf.disable()).sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authorizeHttpRequests(auth -> auth.requestMatchers('/public/**').permitAll().anyRequest().authenticated()).build().",
            keyPoints = listOf(
                "WebSecurityConfigurerAdapter is removed",
                "SecurityFilterChain bean with HttpSecurity injection",
                "Lambda DSL configuration syntax",
                "authorizeHttpRequests with requestMatchers() replaces antMatchers()",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_118",
            trackId = "spring_boot_interview",
            conceptId = "spring_security",
            conceptName = "Spring Security & Filter Chains",
            title = "SecurityContextHolder, SecurityContext, and ThreadLocal Storage",
            question = "How does SecurityContextHolder manage authentication state, and what is its default storage strategy?",
            shortAnswer = "SecurityContextHolder stores the SecurityContext, which contains the current Authentication principal and authorities. By default, it uses MODE_THREADLOCAL, storing security state in a ThreadLocal variable unique to the processing thread. For async/reactive tasks, MODE_INHERITABLETHREADLOCAL or ReactiveSecurityContextHolder (Project Reactor Context) must be used.",
            keyPoints = listOf(
                "Stores SecurityContext containing Authentication object",
                "MODE_THREADLOCAL is the default storage strategy",
                "Authentication provides getPrincipal(), getAuthorities(), isAuthenticated()",
                "Must be cleared at the end of the request to prevent thread pool leaks",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_119",
            trackId = "spring_boot_interview",
            conceptId = "spring_security",
            conceptName = "Spring Security & Filter Chains",
            title = "Stateless JWT Authentication Filter Flow",
            question = "Describe the implementation of a custom JWT Authentication filter in Spring Boot.",
            shortAnswer = "Create a class extending OncePerRequestFilter. In doFilterInternal(): 1) Extract the Authorization header and verify the 'Bearer ' prefix. 2) Parse and validate the JWT signature, claims, and expiration. 3) Load user details or extract username and roles directly from JWT claims. 4) Build a UsernamePasswordAuthenticationToken and place it into SecurityContextHolder.getContext().setAuthentication(auth). 5) Call filterChain.doFilter().",
            keyPoints = listOf(
                "Extends OncePerRequestFilter for guaranteed single execution",
                "Extracts Bearer token from HTTP Authorization header",
                "Validates cryptographic signature and expiration",
                "Creates UsernamePasswordAuthenticationToken and populates SecurityContextHolder",
                "Configured with SessionCreationPolicy.STATELESS",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_120",
            trackId = "spring_boot_interview",
            conceptId = "spring_security",
            conceptName = "Spring Security & Filter Chains",
            title = "AuthenticationEntryPoint vs AccessDeniedHandler",
            question = "What is the difference between AuthenticationEntryPoint and AccessDeniedHandler?",
            shortAnswer = "AuthenticationEntryPoint handles authentication failures (unauthenticated/anonymous user attempting to access a secured resource), returning HTTP 401 Unauthorized. AccessDeniedHandler handles authorization failures (authenticated user lacking necessary roles or permissions), returning HTTP 403 Forbidden. Both are registered in ExceptionTranslationFilter.",
            keyPoints = listOf(
                "AuthenticationEntryPoint handles unauthenticated requests (HTTP 401 Unauthorized)",
                "AccessDeniedHandler handles unauthorized authenticated users (HTTP 403 Forbidden)",
                "Both managed by ExceptionTranslationFilter in the security filter chain",
            ),
            difficulty = "Mid-Level"
        ),
    )

    private fun part7(): List<InterviewQuestion> = listOf(
        InterviewQuestion(
            id = "iq_spring_121",
            trackId = "spring_boot_interview",
            conceptId = "spring_security",
            conceptName = "Spring Security & Filter Chains",
            title = "Method-Level Security: @PreAuthorize vs @Secured vs @RolesAllowed",
            question = "Compare @PreAuthorize, @Secured, and @RolesAllowed, and explain why @PreAuthorize is superior.",
            shortAnswer = "Enable with @EnableMethodSecurity. @Secured is a legacy Spring annotation supporting simple role strings ('ROLE_ADMIN'). @RolesAllowed is the JSR-250 equivalent. @PreAuthorize is the modern standard, supporting full SpEL expressions (e.g. @PreAuthorize('hasRole(\"ADMIN\") or #userId == authentication.principal.id')), enabling fine-grained attribute-based authorization checks before method execution.",
            keyPoints = listOf(
                "@EnableMethodSecurity enables method-level security in Spring Boot 3",
                "@Secured and @RolesAllowed only support static role names",
                "@PreAuthorize supports dynamic SpEL expressions against method arguments",
                "@PostAuthorize allows inspecting return objects before sending to caller",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_122",
            trackId = "spring_boot_interview",
            conceptId = "spring_security",
            conceptName = "Spring Security & Filter Chains",
            title = "CSRF Protection: When to Enable and When to Disable",
            question = "Why should CSRF protection be disabled in stateless REST APIs using JWT, but kept enabled for browser sessions?",
            shortAnswer = "CSRF (Cross-Site Request Forgery) exploits automatic browser credential attachment (cookies and HTTP basic auth). When using session cookies, malicious third-party websites can forge unauthorized state-changing requests. In stateless REST APIs where authentication is sent via explicit custom headers (Authorization: Bearer <token>) stored in JS memory, browsers never send the token automatically, making CSRF impossible and enabling csrf.disable().",
            keyPoints = listOf(
                "CSRF relies on automatic browser cookie transmission",
                "Session-based apps require CSRF tokens (CookieCsrfTokenRepository)",
                "Stateless REST APIs using Authorization: Bearer headers cannot be CSRF-attacked",
                "Safe to disable for pure stateless REST APIs (http.csrf(csrf -> csrf.disable()))",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_123",
            trackId = "spring_boot_interview",
            conceptId = "spring_security",
            conceptName = "Spring Security & Filter Chains",
            title = "Spring Security OAuth 2.0 Resource Server Architecture",
            question = "How do you configure a Spring Boot application as an OAuth 2.0 Resource Server validating JWTs?",
            shortAnswer = "Add spring-boot-starter-oauth2-resource-server. Configure spring.security.oauth2.resourceserver.jwt.issuer-uri in application.properties (e.g. Keycloak, Auth0, Okta). In SecurityFilterChain, configure http.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults())). Spring automatically queries the JWKS endpoint (JSON Web Key Set), caches public keys, and validates token signature and claims.",
            keyPoints = listOf(
                "spring-boot-starter-oauth2-resource-server dependency",
                "Configuring issuer-uri automatically discovers JWKS public keys",
                "Validates JWT cryptographic signatures and issuer/expiration claims",
                "Custom JwtAuthenticationConverter maps custom claims to GrantedAuthorities",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_124",
            trackId = "spring_boot_interview",
            conceptId = "spring_security",
            conceptName = "Spring Security & Filter Chains",
            title = "OAuth 2.0 Authorization Code Flow with PKCE",
            question = "Why is PKCE (Proof Key for Code Exchange) mandatory for modern single-page apps (SPAs) and mobile clients?",
            shortAnswer = "Public clients (SPAs, mobile apps) cannot securely store a client_secret. In standard Authorization Code flow, if an attacker intercepts the authorization code, they can exchange it for an access token. PKCE generates a dynamic cryptographic code_verifier and code_challenge (SHA256 hash). The client sends the challenge during login and the verifier during token exchange, proving that the client exchanging the code is the same client that initiated the request.",
            keyPoints = listOf(
                "Public clients cannot protect client secrets",
                "PKCE replaces static client_secret with dynamic cryptographic handshake",
                "code_challenge sent on initial authorization redirect",
                "code_verifier sent on token exchange prevents code interception attacks",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_125",
            trackId = "spring_boot_interview",
            conceptId = "spring_security",
            conceptName = "Spring Security & Filter Chains",
            title = "Password Hashing with BCryptPasswordEncoder and Argon2",
            question = "How does BCryptPasswordEncoder protect passwords, and what is the role of salt and cost factor?",
            shortAnswer = "BCrypt uses a slow cryptographic hashing algorithm based on the Blowfish cipher. It automatically generates a cryptographically secure 16-byte random salt and prepends it to the hash, defeating rainbow table attacks. The cost factor (work factor, default 10 = 2^10 iterations) controls the time taken to hash a password, intentionally slowing down brute-force and GPU dictionary attacks.",
            keyPoints = listOf(
                "BCrypt is slow by design to thwart GPU brute-force attacks",
                "Random salt generated per password defeats precomputed rainbow tables",
                "Cost factor exponent controls hashing iteration iterations",
                "Argon2 is memory-hard alternative resistant to ASIC hardware cracking",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_126",
            trackId = "spring_boot_interview",
            conceptId = "spring_security",
            conceptName = "Spring Security & Filter Chains",
            title = "Refresh Token Rotation and Revocation Strategy",
            question = "How do you implement secure Refresh Token Rotation in Spring Boot?",
            shortAnswer = "Issue short-lived Access Tokens (15 mins) and long-lived Refresh Tokens (7 days). Store refresh tokens in a database or Redis with a family ID. When the client exchanges a refresh token for a new access token, invalidate the used refresh token and issue a brand-new refresh token (rotation). If an invalidated refresh token is ever presented again, detect reuse: immediately revoke all active tokens in that user family (compromise detected).",
            keyPoints = listOf(
                "Short-lived access tokens + long-lived refresh tokens",
                "Refresh Token Rotation invalidates used token upon exchange",
                "Detecting token reuse triggers immediate family revocation",
                "Mitigates impact of stolen refresh tokens",
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_spring_127",
            trackId = "spring_boot_interview",
            conceptId = "spring_security",
            conceptName = "Spring Security & Filter Chains",
            title = "Role-Based Access Control (RBAC) vs Permissions in Spring Security",
            question = "How does Spring Security differentiate between 'ROLE_' prefixed roles and granular permissions/authorities?",
            shortAnswer = "In Spring Security, everything is represented as a GrantedAuthority. By convention, roles carry the 'ROLE_' prefix (e.g. 'ROLE_ADMIN'). Methods like hasRole('ADMIN') automatically prepend 'ROLE_' when evaluating authorities. Granular permissions (e.g. 'read:orders', 'user:delete') do not carry the prefix and are evaluated via hasAuthority('read:orders'), providing fine-grained permission-based access control.",
            keyPoints = listOf(
                "hasRole('ADMIN') checks for authority 'ROLE_ADMIN'",
                "hasAuthority('perm') checks exact authority string without prefix",
                "Granular authorities allow permission-based authorization models",
                "Custom UserDetailsService maps DB permissions to GrantedAuthority list",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_128",
            trackId = "spring_boot_interview",
            conceptId = "spring_security",
            conceptName = "Spring Security & Filter Chains",
            title = "Custom UserDetailsService and UserDetails",
            question = "What is the role of UserDetailsService, and how is it customized with Spring Data JPA?",
            shortAnswer = "UserDetailsService is the core SPI for loading user authentication data by username. Implement loadUserByUsername(String username): query the User JPA repository, throw UsernameNotFoundException if absent, and return a UserDetails implementation (mapping user credentials, account non-expired/non-locked flags, and authorities to Spring Security User).",
            keyPoints = listOf(
                "Core SPI with loadUserByUsername(String username)",
                "Throws UsernameNotFoundException if user doesn't exist",
                "Returns UserDetails containing credentials and GrantedAuthorities",
                "DaoAuthenticationProvider delegates credential verification to PasswordEncoder",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_129",
            trackId = "spring_boot_interview",
            conceptId = "spring_security",
            conceptName = "Spring Security & Filter Chains",
            title = "AuthenticationManager, ProviderManager, and AuthenticationProvider",
            question = "Explain the relationship between AuthenticationManager, ProviderManager, and AuthenticationProviders.",
            shortAnswer = "AuthenticationManager is the main interface with authenticate(Authentication). ProviderManager is its default implementation, containing a list of AuthenticationProviders. When authenticate() is called, ProviderManager iterates through its providers; each provider calls supports() to see if it can handle the token type (e.g. DaoAuthenticationProvider for passwords, JwtAuthenticationProvider for tokens). The first capable provider authenticates the request.",
            keyPoints = listOf(
                "AuthenticationManager is high-level authentication entry point",
                "ProviderManager is default implementation holding a list of providers",
                "AuthenticationProvider executes specific authentication logic",
                "supports() method inspects Authentication token type",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_130",
            trackId = "spring_boot_interview",
            conceptId = "spring_security",
            conceptName = "Spring Security & Filter Chains",
            title = "CORS Integration inside Spring Security Filter Chain",
            question = "Why must CORS be configured inside the Spring Security filter chain rather than just Spring MVC?",
            shortAnswer = "Spring Security filters execute before Spring MVC DispatcherServlet. If CORS is configured only in Spring MVC (WebMvcConfigurer), the browser's preflight OPTIONS request is intercepted by Spring Security first, which rejects it with HTTP 401/403 before it ever reaches MVC. Configuring http.cors(Customizer.withDefaults()) inside SecurityFilterChain ensures the CorsFilter runs at the very beginning of the security chain.",
            keyPoints = listOf(
                "Security filters execute before Spring MVC DispatcherServlet",
                "Unauthenticated OPTIONS preflight requests get rejected by security filters",
                "http.cors() places CorsFilter before authentication filters",
                "CorsConfigurationSource defines allowed origins, methods, and headers",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_131",
            trackId = "spring_boot_interview",
            conceptId = "spring_security",
            conceptName = "Spring Security & Filter Chains",
            title = "Preventing Session Fixation Attacks",
            question = "How does Spring Security protect against Session Fixation attacks?",
            shortAnswer = "In session fixation, an attacker creates a session on a site and tricks a victim into authenticating with that pre-existing session ID. Spring Security provides sessionManagement().sessionFixation().changeSessionId() (default in modern servlet containers), which generates a brand new HTTP Session ID upon successful authentication while retaining all existing session attributes.",
            keyPoints = listOf(
                "Attack forces victim to use attacker-controlled session ID",
                "changeSessionId() generates fresh session ID upon login while preserving attributes",
                "migrateSession() creates new session copying all attributes",
                "newSession() creates fresh session without copying attributes",
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_spring_132",
            trackId = "spring_boot_interview",
            conceptId = "spring_security",
            conceptName = "Spring Security & Filter Chains",
            title = "Security Headers Configuration (HSTS, CSP, X-Frame-Options)",
            question = "What security headers are enabled by default in Spring Security, and why?",
            shortAnswer = "Spring Security enables essential secure headers: 1) X-Content-Type-Options: nosniff (prevents MIME-type sniffing). 2) X-Frame-Options: DENY (prevents clickjacking attacks in iframes). 3) Strict-Transport-Security (HSTS, forces HTTPS). 4) X-XSS-Protection (blocks cross-site scripting). Content-Security-Policy (CSP) can be customized via http.headers(headers -> headers.contentSecurityPolicy(...)).",
            keyPoints = listOf(
                "nosniff prevents MIME type confusion attacks",
                "X-Frame-Options prevents clickjacking in frames",
                "HSTS forces secure HTTPS communication",
                "CSP defines trusted sources for script and style loading",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_133",
            trackId = "spring_boot_interview",
            conceptId = "spring_security",
            conceptName = "Spring Security & Filter Chains",
            title = "Blacklisting / Revoking JWT Tokens in Stateless Architectures",
            question = "How do you implement instant JWT revocation in a stateless Spring Boot architecture?",
            shortAnswer = "Because JWTs are self-contained and stateless, the server cannot naturally revoke them before expiration. Solution: Maintain a JWT Blacklist in Redis. Store the token's unique jti (JWT ID) in Redis with a TTL equal to the remaining expiration time of the token. In the JWT filter, check if the token's jti is in the Redis blacklist; if found, reject the request with HTTP 401.",
            keyPoints = listOf(
                "Stateless JWTs cannot be natively revoked before expiration",
                "Redis blacklist stores token 'jti' with TTL matching remaining token lifespan",
                "Automatic eviction when TTL expires prevents unbounded Redis growth",
                "Filter checks Redis blacklist on every incoming request",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_134",
            trackId = "spring_boot_interview",
            conceptId = "spring_security",
            conceptName = "Spring Security & Filter Chains",
            title = "Spring Security Context Propagation in Asynchronous Methods",
            question = "Why is SecurityContext null inside @Async methods, and how do you propagate it?",
            shortAnswer = "SecurityContextHolder defaults to ThreadLocal, which does not propagate to new threads spawned by @Async or custom TaskExecutors. Solutions: 1) Set SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL) to propagate to child threads. 2) Wrap the TaskExecutor in a DelegatingSecurityContextAsyncTaskExecutor to safely copy the SecurityContext across pooled threads.",
            keyPoints = listOf(
                "ThreadLocal is isolated to the calling thread",
                "@Async runs on a separate worker thread where SecurityContext is null",
                "DelegatingSecurityContextAsyncTaskExecutor copies context across pool threads",
                "MODE_INHERITABLETHREADLOCAL propagates to newly created child threads",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_135",
            trackId = "spring_boot_interview",
            conceptId = "spring_security",
            conceptName = "Spring Security & Filter Chains",
            title = "Spring Authorization Server (SAS) Architecture",
            question = "What is Spring Authorization Server, and what role does it play in enterprise OAuth2 ecosystems?",
            shortAnswer = "Spring Authorization Server is the official framework for implementing an OAuth 2.1 and OpenID Connect 1.0 Authorization Server. It issues access tokens, refresh tokens, and ID tokens, handles user authentication and consent screens, supports PKCE, client registration (RegisteredClientRepository), and exposes standard endpoints: /oauth2/authorize, /oauth2/token, /oauth2/jwks, and /.well-known/openid-configuration.",
            keyPoints = listOf(
                "Implements OAuth 2.1 and OIDC 1.0 specifications",
                "Replaces deprecated Spring Security OAuth project",
                "RegisteredClientRepository manages client credentials and grant types",
                "Provides JWKS endpoint for token verification by Resource Servers",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_136",
            trackId = "spring_boot_interview",
            conceptId = "spring_ops_observability",
            conceptName = "Actuator, Observability & Cloud Readiness",
            title = "Actuator Endpoints Exposure and Security Hardening",
            question = "How do you expose specific Actuator endpoints and secure sensitive diagnostic data in production?",
            shortAnswer = "By default, only /health is exposed over HTTP. To expose endpoints, configure management.endpoints.web.exposure.include=health,info,metrics,prometheus in application.properties. Sensitive endpoints (heapdump, env, beans) should never be exposed with wildcard '*'. In Spring Security, isolate the actuator port (management.server.port=8081) and restrict /actuator/** to an internal ADMIN role via requestMatchers('/actuator/**').hasRole('ADMIN').",
            keyPoints = listOf(
                "management.endpoints.web.exposure.include explicitly lists safe endpoints",
                "Avoid wildcard '*' in production to prevent leaking env vars or heap dumps",
                "Isolate management port via management.server.port",
                "Secure /actuator/** with Spring Security role checks",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_137",
            trackId = "spring_boot_interview",
            conceptId = "spring_ops_observability",
            conceptName = "Actuator, Observability & Cloud Readiness",
            title = "Custom HealthIndicator Implementation",
            question = "How do you write a custom HealthIndicator in Spring Boot?",
            shortAnswer = "Implement the HealthIndicator interface or extend AbstractHealthIndicator. Override the health() method: perform a check against the dependent subsystem (e.g. custom TCP service, external API, disk volume). Return Health.up().withDetail('latencyMs', 12).build() on success, or Health.down(exception).withDetail('error', 'Connection timed out').build() on failure.",
            keyPoints = listOf(
                "Implement HealthIndicator interface",
                "Return Health.up() or Health.down(exception)",
                "Enrich response with withDetail(key, value) diagnostics",
                "Automatically aggregated into /actuator/health status",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_138",
            trackId = "spring_boot_interview",
            conceptId = "spring_ops_observability",
            conceptName = "Actuator, Observability & Cloud Readiness",
            title = "Kubernetes Liveness and Readiness Probes Configuration",
            question = "How does Spring Boot configure Kubernetes liveness and readiness probe endpoints?",
            shortAnswer = "Spring Boot detects Kubernetes environment or property management.endpoint.health.probes.enabled=true, exposing /actuator/health/liveness and /actuator/health/readiness. Liveness checks internal app state (LivenessState.CORRECT); failure triggers a pod restart by K8s. Readiness checks external connectivity (ReadinessState.ACCEPTING_TRAFFIC); failure removes the pod from the K8s Service endpoint load balancer without restarting.",
            keyPoints = listOf(
                "/actuator/health/liveness restarts container on failure",
                "/actuator/health/readiness diverts ingress traffic on failure",
                "AvailabilityChangeEvent allows publishing state transitions programmatically",
                "Decouples application crash detection from temporary resource warming",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_139",
            trackId = "spring_boot_interview",
            conceptId = "spring_ops_observability",
            conceptName = "Actuator, Observability & Cloud Readiness",
            title = "Micrometer Metrics and Prometheus Integration",
            question = "How does Spring Boot Actuator integrate with Micrometer and Prometheus?",
            shortAnswer = "Add micrometer-registry-prometheus. Spring Boot auto-configures a MeterRegistry and exposes /actuator/prometheus. Micrometer acts as the facade (like SLF4J for metrics), recording JVM memory, garbage collection, CPU usage, and HTTP request timings. Prometheus periodically scrapes /actuator/prometheus in OpenMetrics text format, which is visualized in Grafana dashboards.",
            keyPoints = listOf(
                "Micrometer provides dimensional metrics facade",
                "micrometer-registry-prometheus exposes /actuator/prometheus",
                "Tracks JVM memory, GC pauses, thread pools, and HTTP latency",
                "Prometheus pull model scrapes metrics for Grafana visualization",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_140",
            trackId = "spring_boot_interview",
            conceptId = "spring_ops_observability",
            conceptName = "Actuator, Observability & Cloud Readiness",
            title = "Custom Metrics: Counter, Timer, and Gauge",
            question = "Explain the differences between Counter, Timer, and Gauge in Micrometer with code examples.",
            shortAnswer = "1) Counter: Monotonically increasing metric tracking event occurrences (e.g. ordersPlacedCounter.increment()). 2) Timer: Measures both duration and event frequency (e.g. timer.record(() -> executeOrder())). 3) Gauge: Instantaneous snapshot of a fluctuating value (e.g. Gauge.builder('queue.size', queue, Queue::size).register(registry)).",
            keyPoints = listOf(
                "Counter tracks cumulative counts and rates of occurrence",
                "Timer tracks latency distribution, percentiles, and request throughput",
                "Gauge observes instantaneous fluctuating values (memory, active connections)",
                "Injected via MeterRegistry with tags for multi-dimensional querying",
            ),
            difficulty = "Mid-Level"
        ),
    )

    private fun part8(): List<InterviewQuestion> = listOf(
        InterviewQuestion(
            id = "iq_spring_141",
            trackId = "spring_boot_interview",
            conceptId = "spring_ops_observability",
            conceptName = "Actuator, Observability & Cloud Readiness",
            title = "Distributed Tracing in Spring Boot 3 with Micrometer Tracing",
            question = "How does Distributed Tracing work in Spring Boot 3, and what replaced Spring Cloud Sleuth?",
            shortAnswer = "In Spring Boot 3, Spring Cloud Sleuth was replaced by Micrometer Tracing. Add micrometer-tracing-bridge-brave or micrometer-tracing-bridge-otel and a reporter (e.g. zipkin-reporter). Micrometer Tracing injects Trace ID and Span ID into logs (MDC) and propagates W3C traceparent headers across HTTP calls (via RestClient/WebClient), allowing tools like Jaeger or Zipkin to correlate end-to-end request journeys across microservices.",
            keyPoints = listOf(
                "Micrometer Tracing replaces Spring Cloud Sleuth in Spring Boot 3",
                "Injects traceId and spanId into SLF4J MDC for unified logging",
                "Propagates W3C Trace Context headers (traceparent) across network calls",
                "Visualized in distributed tracing tools like Zipkin, Jaeger, or Tempo",
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_spring_142",
            trackId = "spring_boot_interview",
            conceptId = "spring_ops_observability",
            conceptName = "Actuator, Observability & Cloud Readiness",
            title = "Dynamic Log Level Changes via Actuator",
            question = "How do you change the log level of a specific package in production without restarting the service?",
            shortAnswer = "Expose the loggers endpoint: management.endpoints.web.exposure.include=loggers. Send an HTTP POST request to /actuator/loggers/com.example.service with JSON body: {\"configuredLevel\": \"DEBUG\"}. Spring Boot immediately changes the logger level in memory, allowing live debugging of production incidents without restarting containers.",
            keyPoints = listOf(
                "Expose /actuator/loggers endpoint",
                "HTTP POST with configuredLevel payload updates log level immediately",
                "Zero-downtime diagnostic troubleshooting",
                "Supports resetting back to default levels",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_143",
            trackId = "spring_boot_interview",
            conceptId = "spring_ops_observability",
            conceptName = "Actuator, Observability & Cloud Readiness",
            title = "Info Endpoint Customization and Git Commit Metadata",
            question = "How do you expose build and Git commit metadata via /actuator/info?",
            shortAnswer = "Add git-commit-id-maven-plugin or git-commit-id-plugin in Gradle to generate git.properties during the build. In application.properties, enable management.info.git.mode=full and management.info.env.enabled=true. /actuator/info will return commit hash, branch, committer, and build timestamp, ensuring exact traceability of deployed artifacts.",
            keyPoints = listOf(
                "git-commit-id plugin generates git.properties during CI/CD build",
                "management.info.git.mode=full exposes detailed commit and branch info",
                "Ensures exact Git SHA traceability in production clusters",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_144",
            trackId = "spring_boot_interview",
            conceptId = "spring_ops_observability",
            conceptName = "Actuator, Observability & Cloud Readiness",
            title = "Spring Boot Admin Features and Architecture",
            question = "What is Spring Boot Admin, and how does it monitor a fleet of microservices?",
            shortAnswer = "Spring Boot Admin provides a community web UI for managing and monitoring Spring Boot applications. Client applications include spring-boot-admin-starter-client and register with the Admin server via Eureka or direct HTTP registration. The dashboard aggregates health, JVM metrics, thread dumps, environment properties, and allows runtime log level changes across all instances.",
            keyPoints = listOf(
                "Centralized monitoring dashboard for Spring Boot microservices",
                "Client-server architecture registering via Service Discovery or HTTP",
                "Visualizes health, metrics, thread states, and environment properties",
                "Enables administrative actions like dynamic log level mutations",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_145",
            trackId = "spring_boot_interview",
            conceptId = "spring_ops_observability",
            conceptName = "Actuator, Observability & Cloud Readiness",
            title = "Thread Dumps and Heap Dumps via Actuator",
            question = "How do you capture thread dumps and heap dumps using Actuator, and what are the operational cautions?",
            shortAnswer = "Access /actuator/threaddump for a JSON or text snapshot of all running JVM threads and lock states (useful for analyzing deadlocks or CPU spikes). Access /actuator/heapdump to trigger a live HPROF memory dump download. Caution: /actuator/heapdump pauses the JVM during dump creation, consumes massive disk/memory, and exposes sensitive passwords or keys residing in memory.",
            keyPoints = listOf(
                "/actuator/threaddump captures thread stacks and lock contention states",
                "/actuator/heapdump generates .hprof memory file for Eclipse MAT analysis",
                "Heap dumps pause JVM execution and can exhaust container disk",
                "Heap dumps expose sensitive in-memory credentials and must be strictly secured",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_146",
            trackId = "spring_boot_interview",
            conceptId = "spring_ops_observability",
            conceptName = "Actuator, Observability & Cloud Readiness",
            title = "OpenTelemetry and OTLP Protocol in Spring Boot 3",
            question = "How does Spring Boot 3 support OpenTelemetry (OTel) for vendor-neutral telemetry exports?",
            shortAnswer = "Spring Boot 3 natively supports OpenTelemetry via micrometer-tracing-bridge-otel and opentelemetry-exporter-otlp. Telemetry (traces, metrics) is pushed directly using the open-standard OTLP protocol (gRPC or HTTP/protobuf) to an OpenTelemetry Collector, which routes data to vendors like Dynatrace, New Relic, Datadog, or Grafana Tempo without proprietary vendor agents.",
            keyPoints = listOf(
                "Vendor-neutral telemetry standard (OpenTelemetry)",
                "OTLP protocol transmits traces and metrics via gRPC or HTTP",
                "Routes through an OpenTelemetry Collector daemon",
                "Eliminates vendor lock-in for APM monitoring",
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_spring_147",
            trackId = "spring_boot_interview",
            conceptId = "spring_ops_observability",
            conceptName = "Actuator, Observability & Cloud Readiness",
            title = "HTTP Request Tracing with HttpExchangeRepository",
            question = "How does Spring Boot track recent HTTP request/response exchanges?",
            shortAnswer = "In Spring Boot 3, HttpTraceRepository was replaced by HttpExchangeRepository. Expose /actuator/httpexchanges and register an InMemoryHttpExchangeRepository bean. Actuator records the last 100 HTTP requests, including method, URI, status code, request/response headers, and response duration in milliseconds.",
            keyPoints = listOf(
                "HttpExchangeRepository replaces deprecated HttpTraceRepository",
                "Exposes /actuator/httpexchanges endpoint",
                "Tracks timestamps, method, URI, response code, and latency",
                "InMemory repository is bounded to prevent heap leaks",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_148",
            trackId = "spring_boot_interview",
            conceptId = "spring_ops_observability",
            conceptName = "Actuator, Observability & Cloud Readiness",
            title = "Startup Tracking with ApplicationStartup and Flight Recorder",
            question = "How do you measure and optimize startup bottlenecks in Spring Boot using ApplicationStartup?",
            shortAnswer = "SpringApplication supports custom ApplicationStartup implementations. By configuring app.setApplicationStartup(new BufferingApplicationStartup(2048)), Spring records startup steps and durations, accessible via the /actuator/startup endpoint. Alternatively, FlightRecorderApplicationStartup records startup events into JDK Flight Recorder (JFR) for analysis in JDK Mission Control.",
            keyPoints = listOf(
                "BufferingApplicationStartup records startup steps and durations",
                "/actuator/startup endpoint exposes chronological startup timeline",
                "Pinpoints slow bean initializations and configuration bottlenecks",
                "FlightRecorderApplicationStartup integrates with JDK Flight Recorder (JFR)",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_149",
            trackId = "spring_boot_interview",
            conceptId = "spring_ops_observability",
            conceptName = "Actuator, Observability & Cloud Readiness",
            title = "Container Health Checks vs Spring Boot Actuator Probes",
            question = "Why should Docker / Kubernetes health checks use /actuator/health/liveness rather than the root /actuator/health?",
            shortAnswer = "The root /actuator/health aggregates ALL registered health indicators, including database, Kafka, and Redis. If a secondary database or cache has a temporary network blip, root /actuator/health becomes DOWN. If a container liveness probe points to root /health, Kubernetes will kill and restart the container in an infinite CrashLoopBackOff. Liveness probe should only check internal process viability (/actuator/health/liveness).",
            keyPoints = listOf(
                "Root /actuator/health aggregates all external infrastructure dependencies",
                "External network blips cause root health to become DOWN",
                "Liveness failure causes Kubernetes container restart and CrashLoopBackOff",
                "Liveness must check internal process viability; Readiness checks external dependencies",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_150",
            trackId = "spring_boot_interview",
            conceptId = "spring_ops_observability",
            conceptName = "Actuator, Observability & Cloud Readiness",
            title = "AuditEvents in Spring Boot Actuator",
            question = "How does Spring Boot record and expose security audit events?",
            shortAnswer = "Add an AuditEventRepository bean (e.g. InMemoryAuditEventRepository). Spring Security automatically publishes AuditEvents on authentication successes, failures, and access denials. These events are exposed via the /actuator/auditevents endpoint, providing security teams with a verifiable audit log of user logins and security exceptions.",
            keyPoints = listOf(
                "AuditEventRepository stores security audit records",
                "Automatically captures authentication success, failure, and authorization denial",
                "Exposed via /actuator/auditevents endpoint",
                "Can be extended to publish to external SIEM or Kafka audit topics",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_151",
            trackId = "spring_boot_interview",
            conceptId = "spring_testing_slices",
            conceptName = "Testing & Integration Architecture",
            title = "@SpringBootTest WebEnvironment Options",
            question = "Explain the four WebEnvironment modes of @SpringBootTest and when to use each.",
            shortAnswer = "1) MOCK (default): Creates a mock servlet environment; no real HTTP server is started; tests use MockMvc. 2) RANDOM_PORT: Starts an actual embedded server (Tomcat) on an ephemeral free port; tests use TestRestTemplate or WebTestClient for full HTTP integration tests. 3) DEFINED_PORT: Starts real server on configured port (e.g. 8080); risks port conflicts. 4) NONE: Loads ApplicationContext without any web environment; ideal for backend batch/messaging services.",
            keyPoints = listOf(
                "MOCK: mock servlet environment with MockMvc",
                "RANDOM_PORT: real embedded web server on dynamic port with TestRestTemplate",
                "DEFINED_PORT: real server on static port with port conflict risks",
                "NONE: non-web application context for testing batch or asynchronous services",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_152",
            trackId = "spring_boot_interview",
            conceptId = "spring_testing_slices",
            conceptName = "Testing & Integration Architecture",
            title = "Spring Boot Test Slices: @WebMvcTest vs @DataJpaTest",
            question = "What are Test Slices in Spring Boot, and how do @WebMvcTest and @DataJpaTest improve test execution speed?",
            shortAnswer = "Test Slices load only the subset of the ApplicationContext relevant to a specific architectural layer, running in milliseconds instead of spinning up the entire application graph. @WebMvcTest loads only web layer components (@Controller, @ControllerAdvice, Filters, Security) and requires @MockBean for services. @DataJpaTest configures an in-memory DB or test schema, loads only @Entity and Spring Data repositories, and rolls back transactions by default.",
            keyPoints = listOf(
                "Test Slices load focused contextual subsets for fast unit/integration testing",
                "@WebMvcTest loads only web layer components and mocks services",
                "@DataJpaTest configures JPA entities and repositories with auto-rollback",
                "Eliminates full ApplicationContext startup overhead",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_153",
            trackId = "spring_boot_interview",
            conceptId = "spring_testing_slices",
            conceptName = "Testing & Integration Architecture",
            title = "@MockBean vs @SpyBean vs Mockito @Mock",
            question = "Differentiate between Mockito's @Mock and Spring Boot's @MockBean.",
            shortAnswer = "@Mock is a pure Mockito annotation used in unit tests with MockitoExtension; it creates a mock instance without touching the Spring context. @MockBean is a Spring Boot Test annotation that creates a Mockito mock AND registers/replaces the matching bean inside the Spring ApplicationContext. If an existing bean exists, it is replaced; if none exists, the mock is added. @SpyBean wraps an existing real bean with a Mockito spy.",
            keyPoints = listOf(
                "Mockito @Mock creates isolated mock without Spring container involvement",
                "@MockBean injects or replaces a bean directly inside the Spring ApplicationContext",
                "@SpyBean wraps a real Spring bean to monitor interactions or stub specific methods",
                "Overuse of @MockBean causes ApplicationContext cache pollution and slower test suites",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_154",
            trackId = "spring_boot_interview",
            conceptId = "spring_testing_slices",
            conceptName = "Testing & Integration Architecture",
            title = "ApplicationContext Caching in Spring Test Framework",
            question = "How does Spring's TestContext Framework cache contexts across tests, and what breaks the cache?",
            shortAnswer = "The TestContext Framework caches ApplicationContext instances across test classes based on their configuration signature (profiles, property overrides, test slices). Caching allows subsequent test classes to reuse the context in milliseconds. The cache is invalidated (context closed and rebuilt) when a test uses @DirtiesContext, changes dynamic properties inconsistently, or uses unique sets of @MockBean definitions.",
            keyPoints = listOf(
                "Tests sharing identical configuration reuse cached ApplicationContext",
                "Significantly speeds up multi-test test suite execution",
                "@DirtiesContext marks context as dirty and forces slow context rebuild",
                "Varying @MockBean combinations between classes creates distinct cache keys",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_155",
            trackId = "spring_boot_interview",
            conceptId = "spring_testing_slices",
            conceptName = "Testing & Integration Architecture",
            title = "Integration Testing with Testcontainers",
            question = "Why are Testcontainers preferred over H2 in-memory databases for integration testing?",
            shortAnswer = "In-memory databases like H2 have dialect, function, and locking differences compared to production databases (e.g. PostgreSQL JSONB columns, stored procedures, MySQL collation). Testcontainers uses Docker to spin up lightweight, ephemeral, real database instances (e.g. PostgreSQLContainer) during tests, ensuring integration tests execute against the identical database engine used in production.",
            keyPoints = listOf(
                "Eliminates H2 vs production DB dialect and feature discrepancies",
                "Runs real PostgreSQL, Redis, or Kafka containers inside Docker",
                "Guarantees production fidelity and catches subtle SQL syntax issues",
                "Automatically terminates and cleans up containers after tests",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_156",
            trackId = "spring_boot_interview",
            conceptId = "spring_testing_slices",
            conceptName = "Testing & Integration Architecture",
            title = "@DynamicPropertySource in Testcontainers Integration",
            question = "How do you inject dynamic Testcontainer ports into Spring Boot properties using @DynamicPropertySource?",
            shortAnswer = "Define a static container (e.g. static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(...)). Annotate a static method with @DynamicPropertySource taking DynamicPropertyRegistry. Inside, register properties with dynamic lambdas: registry.add('spring.datasource.url', postgres::getJdbcUrl), registry.add('spring.datasource.username', postgres::getUsername). Spring Boot dynamically overrides properties before context initialization.",
            keyPoints = listOf(
                "Static container instance lifecycle",
                "@DynamicPropertySource static callback method",
                "DynamicPropertyRegistry binds ephemeral container port and URL to Spring properties",
                "Standardized pattern replacing legacy ApplicationContextInitializer",
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_spring_157",
            trackId = "spring_boot_interview",
            conceptId = "spring_testing_slices",
            conceptName = "Testing & Integration Architecture",
            title = "Testing Spring Security with @WithMockUser and @WithUserDetails",
            question = "How do you test secured endpoints in Spring MVC tests without performing real login flows?",
            shortAnswer = "Annotate test methods with @WithMockUser(username = 'admin', roles = {'ADMIN'}) to populate the SecurityContext with an authenticated UsernamePasswordAuthenticationToken before the test runs. If your application requires a custom UserDetails principal class, use @WithUserDetails(value = 'john_doe', userDetailsServiceBeanName = 'customUserDetailsService') to load the principal through the application's real UserDetailsService.",
            keyPoints = listOf(
                "@WithMockUser synthesizes authenticated principal with roles/authorities",
                "Avoids performing explicit HTTP login or token generation in unit tests",
                "@WithUserDetails loads realistic custom domain principal via UserDetailsService",
                "Supports testing @PreAuthorize annotations in isolation",
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_spring_158",
            trackId = "spring_boot_interview",
            conceptId = "spring_testing_slices",
            conceptName = "Testing & Integration Architecture",
            title = "Transactional Rollback in Integration Tests",
            question = "Why are @Transactional tests rolled back by default in Spring Test, and how do you disable rollback?",
            shortAnswer = "In test classes, @Transactional wraps each test method in a transaction and automatically rolls it back upon method completion. This ensures tests are isolated and leaves the database in a clean state for subsequent tests without manual cleanup. To commit changes (e.g. when verifying asynchronous worker processing), annotate the test method with @Commit or @Rollback(false).",
            keyPoints = listOf(
                "Default rollback keeps database clean and tests idempotent",
                "Prevents test pollution across integration test suites",
                "@Commit or @Rollback(false) disables rollback when persistence must be committed",
                "Warning: WebEnvironment.RANDOM_PORT runs server on separate thread where rollback does not apply",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_159",
            trackId = "spring_boot_interview",
            conceptId = "spring_testing_slices",
            conceptName = "Testing & Integration Architecture",
            title = "MockMvc vs WebTestClient vs TestRestTemplate",
            question = "Compare MockMvc, WebTestClient, and TestRestTemplate for testing REST APIs in Spring Boot.",
            shortAnswer = "MockMvc tests Spring MVC controllers in a simulated servlet environment without network overhead (fast, no real HTTP server). TestRestTemplate makes real HTTP calls over a network socket to an embedded server running on RANDOM_PORT. WebTestClient was originally for WebFlux but now supports binding to MockMvc, WebFlux, or a real HTTP server with a modern fluent API.",
            keyPoints = listOf(
                "MockMvc: simulated servlet environment, fast, tests controller layer in-memory",
                "TestRestTemplate: real HTTP client over real network port",
                "WebTestClient: fluent assertion API capable of testing both MockMvc and real HTTP servers",
                "Choose based on testing unit controller logic vs full end-to-end integration",
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_spring_160",
            trackId = "spring_boot_interview",
            conceptId = "spring_testing_slices",
            conceptName = "Testing & Integration Architecture",
            title = "Database Initialization in Tests with @Sql",
            question = "How does the @Sql annotation manage test database state before and after test execution?",
            shortAnswer = "@Sql executes SQL scripts against the configured DataSource. You can execute setup scripts before the test (@Sql('/data/insert_users.sql', executionPhase = BEFORE_TEST_METHOD)) and cleanup scripts after (@Sql('/data/clean_users.sql', executionPhase = AFTER_TEST_METHOD)). @SqlMergeMode(MERGE) allows class-level and method-level scripts to combine seamlessly.",
            keyPoints = listOf(
                "Executes declarative SQL scripts for test fixture setup",
                "executionPhase controls execution BEFORE_TEST_METHOD or AFTER_TEST_METHOD",
                "@SqlMergeMode combines class-level schema setup with method-level test data",
                "Provides deterministic test preconditions",
            ),
            difficulty = "Senior"
        ),
    )

    private fun part9(): List<InterviewQuestion> = listOf(
        InterviewQuestion(
            id = "iq_spring_161",
            trackId = "spring_boot_interview",
            conceptId = "spring_testing_slices",
            conceptName = "Testing & Integration Architecture",
            title = "Testing Asynchronous and Scheduled Code with Awaitility",
            question = "How do you test @Async methods and @Scheduled tasks reliably without Thread.sleep()?",
            shortAnswer = "Never use Thread.sleep() because it causes flaky tests and inflates test execution times. Use the Awaitility library: await().atMost(5, SECONDS).untilAsserted(() -> assertThat(repository.count()).isEqualTo(1)). Awaitility polls the condition with a configurable interval and proceeds as soon as the assertion succeeds or fails fast if the timeout expires.",
            keyPoints = listOf(
                "Thread.sleep() introduces flakiness and slow build times",
                "Awaitility polls until condition succeeds or timeout is reached",
                "await().atMost(...).untilAsserted(...) provides clean fluent assertions",
                "Essential for testing asynchronous message consumers and scheduled tasks",
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_spring_162",
            trackId = "spring_boot_interview",
            conceptId = "spring_testing_slices",
            conceptName = "Testing & Integration Architecture",
            title = "Testing REST Clients with @RestClientTest",
            question = "How does @RestClientTest test outbound HTTP client calls without making real external network requests?",
            shortAnswer = "@RestClientTest tests outbound client beans (RestTemplate or RestClient). It configures MockRestServiceServer, which intercepts outgoing HTTP requests and verifies request URIs, HTTP methods, and headers. You mock expected responses using server.expect(requestTo('/api/orders')).andRespond(withSuccess(jsonBody, MediaType.APPLICATION_JSON)), verifying integration without hitting third-party servers.",
            keyPoints = listOf(
                "Test slice dedicated to outbound REST client testing",
                "MockRestServiceServer intercepts client HTTP requests in memory",
                "Verifies outbound request parameters, headers, and body",
                "Simulates external server responses without live network calls",
            ),
            difficulty = "Mid-Level"
        ),
    )

    fun getQuestions(): List<InterviewQuestion> =
        part1() + part2() + part3() + part4() + part5() + part6() + part7() + part8() + part9()
}
