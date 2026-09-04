package com.example.domain.model

data class TechnicalConceptModule(
    val id: String,
    val name: String,
    val questionCount: Int,
    val keywords: List<String> = emptyList(),
    val userScore: Int? = null,
    val totalQuestionsAttempted: Int? = null,
    val isLastAttempted: Boolean = false
) {
    val hasScore: Boolean
        get() = userScore != null

    val scoreDisplay: String
        get() = if (userScore != null && totalQuestionsAttempted != null) {
            "$userScore/$totalQuestionsAttempted"
        } else {
            "$questionCount"
        }
}

object TechnicalConceptCatalog {

    val javaConcepts = listOf(
        TechnicalConceptModule(
            id = "java_concurrency",
            name = "Multithreading & Concurrency",
            questionCount = 71,
            keywords = listOf("concurrency", "thread", "lock", "executor", "atomic", "volatile", "future", "synchronized", "semaphore", "barrier", "latch", "mutex", "deadlock", "forkjoin", "loom", "virtual thread")
        ),
        TechnicalConceptModule(
            id = "java_collections",
            name = "Collections & Generics",
            questionCount = 62,
            keywords = listOf("collection", "hashmap", "list", "set", "map", "generic", "iterator", "queue", "treemap", "linkedlist", "arraylist", "concurrentmodification", "pecs", "comparator", "comparable")
        ),
        TechnicalConceptModule(
            id = "java_oop",
            name = "OOP Concepts & Fundamentals",
            questionCount = 54,
            keywords = listOf("oop", "polymorphism", "inheritance", "encapsulation", "interface", "abstract", "overload", "override", "final", "access modifier", "casting", "class", "record", "sealed")
        ),
        TechnicalConceptModule(
            id = "java_memory",
            name = "Memory Management & JVM",
            questionCount = 53,
            keywords = listOf("jvm", "memory", "gc", "garbage", "heap", "stack", "metaspace", "oom", "leak", "jfr", "safepoint", "escape", "jit", "classloader", "bytecode")
        ),
        TechnicalConceptModule(
            id = "java_string",
            name = "String & Primitive Types",
            questionCount = 24,
            keywords = listOf("string", "stringbuilder", "stringbuffer", "pool", "immutable", "primitive", "autoboxing", "wrapper", "cache", "integer", "boolean", "pass by value")
        ),
        TechnicalConceptModule(
            id = "java_exceptions",
            name = "Exceptions & Try-With-Resources",
            questionCount = 20,
            keywords = listOf("exception", "throwable", "error", "try", "catch", "finally", "suppressed", "trywithresources", "io", "nio", "file", "serializ")
        ),
        TechnicalConceptModule(
            id = "java_streams",
            name = "Java 8+ Features & Streams",
            questionCount = 16,
            keywords = listOf("stream", "lambda", "optional", "functional", "method reference", "default method", "pattern matching", "switch", "java 8", "java 17", "java 21")
        )
    )

    val springBootConcepts = listOf(
        TechnicalConceptModule(
            id = "spring_data",
            name = "Spring Data & JPA/Hibernate",
            questionCount = 130,
            keywords = listOf("jpa", "hibernate", "data", "entity", "repository", "transaction", "transactional", "query", "orm", "n+1", "audit", "datasource", "flyway", "liquibase")
        ),
        TechnicalConceptModule(
            id = "spring_core",
            name = "Core IoC & Dependency Injection",
            questionCount = 109,
            keywords = listOf("ioc", "di", "bean", "context", "autowire", "scope", "lifecycle", "postprocessor", "factory", "inject", "configuration", "component", "condition")
        ),
        TechnicalConceptModule(
            id = "spring_mvc",
            name = "Spring MVC & REST APIs",
            questionCount = 32,
            keywords = listOf("mvc", "rest", "controller", "endpoint", "requestmapping", "responsebody", "validation", "exceptionhandler", "hateoas", "content negotiation", "cors", "filter", "interceptor")
        ),
        TechnicalConceptModule(
            id = "spring_boot3",
            name = "Spring Boot 3 & Native AOT",
            questionCount = 12,
            keywords = listOf("boot 3", "aot", "graalvm", "native", "virtual thread", "loom", "cloud native", "starter", "autoconfigure", "banner")
        ),
        TechnicalConceptModule(
            id = "spring_actuator",
            name = "Actuator & Observability",
            questionCount = 8,
            keywords = listOf("actuator", "metric", "micrometer", "health", "prometheus", "info", "logging", "monitoring", "tracing", "dump")
        ),
        TechnicalConceptModule(
            id = "spring_security",
            name = "Spring Security & OAuth2",
            questionCount = 7,
            keywords = listOf("security", "auth", "oauth", "jwt", "filterchain", "userdetails", "authentication", "authorization", "csrf", "roles", "methodsecurity")
        ),
        TechnicalConceptModule(
            id = "spring_cloud",
            name = "Spring Cloud & Microservices",
            questionCount = 2,
            keywords = listOf("cloud", "eureka", "gateway", "feign", "resilience4j", "circuit breaker", "config server", "sleuth", "zipkin", "openfeign")
        )
    )

    val microservicesConcepts = listOf(
        TechnicalConceptModule(
            id = "ms_k8s",
            name = "Kubernetes & Containers",
            questionCount = 156,
            keywords = listOf("kubernetes", "k8s", "docker", "pod", "deployment", "service", "ingress", "container", "helm", "sidecar", "mesh", "istio")
        ),
        TechnicalConceptModule(
            id = "ms_events",
            name = "Event-Driven & Kafka Messaging",
            questionCount = 53,
            keywords = listOf("kafka", "event", "messaging", "pubsub", "consumer", "producer", "broker", "queue", "rabbitmq", "partition", "offset", "topic")
        ),
        TechnicalConceptModule(
            id = "ms_resilience",
            name = "Resilience & Fault Tolerance",
            questionCount = 42,
            keywords = listOf("resilience", "circuit breaker", "bulkhead", "retry", "fallback", "timeout", "ratelimit", "fault")
        ),
        TechnicalConceptModule(
            id = "ms_discovery",
            name = "Service Discovery & API Gateway",
            questionCount = 33,
            keywords = listOf("gateway", "discovery", "eureka", "consul", "load balance", "routing", "ingress", "envoy", "reverse proxy")
        ),
        TechnicalConceptModule(
            id = "ms_patterns",
            name = "Saga & Distributed Patterns",
            questionCount = 16,
            keywords = listOf("saga", "cqrs", "outbox", "strangler", "event sourcing", "choreography", "orchestration", "distributed transaction", "2pc")
        )
    )

    val hldConcepts = listOf(
        TechnicalConceptModule(
            id = "hld_scaling",
            name = "Scalability & Load Balancing",
            questionCount = 112,
            keywords = listOf("scaling", "scale", "horizontal", "vertical", "load balancer", "l4", "l7", "round robin", "least connections", "reverse proxy", "throughput", "latency")
        ),
        TechnicalConceptModule(
            id = "hld_databases",
            name = "Database Scaling & Sharding",
            questionCount = 77,
            keywords = listOf("database", "sharding", "replication", "master-slave", "partitioning", "consistency", "acid", "cap", "nosql", "dynamodb", "cassandra", "read replica")
        ),
        TechnicalConceptModule(
            id = "hld_caching",
            name = "Caching & CDN Networks",
            questionCount = 34,
            keywords = listOf("cache", "caching", "redis", "memcached", "cdn", "write-through", "write-back", "cache-aside", "eviction", "lru", "lfu", "bloom filter")
        ),
        TechnicalConceptModule(
            id = "hld_blueprints",
            name = "System Architecture Blueprints",
            questionCount = 34,
            keywords = listOf("design a", "url shortener", "chat", "rate limiter", "notification", "uber", "netflix", "youtube", "search", "e-commerce")
        ),
        TechnicalConceptModule(
            id = "hld_messaging",
            name = "Message Queues & Streaming",
            questionCount = 28,
            keywords = listOf("kafka", "queue", "streaming", "event-driven", "pubsub", "async", "rabbitmq", "message broker", "backpressure")
        ),
        TechnicalConceptModule(
            id = "hld_reliability",
            name = "Reliability, SLA & Recovery",
            questionCount = 8,
            keywords = listOf("sla", "availability", "spof", "disaster", "failover", "multi-region", "rpo", "rto", "health check")
        ),
        TechnicalConceptModule(
            id = "hld_distributed",
            name = "Distributed Consensus & Raft",
            questionCount = 7,
            keywords = listOf("consensus", "raft", "paxos", "zookeeper", "quorum", "vector clock", "eventual consistency", "pacelc", "linearizability", "byzantine")
        )
    )

    val lldConcepts = listOf(
        TechnicalConceptModule(
            id = "lld_behavioral",
            name = "Behavioral Design Patterns",
            questionCount = 161,
            keywords = listOf("observer", "strategy", "command", "chain of responsibility", "state pattern", "iterator pattern", "mediator", "memento", "template method", "visitor")
        ),
        TechnicalConceptModule(
            id = "lld_concurrency",
            name = "Concurrency Design & Safety",
            questionCount = 66,
            keywords = listOf("concurrency", "thread safety", "lock", "producer consumer", "read write lock", "deadlock", "blocking queue")
        ),
        TechnicalConceptModule(
            id = "lld_case_studies",
            name = "Real-World LLD Case Studies",
            questionCount = 25,
            keywords = listOf("case study", "parking lot", "elevator", "snake and ladder", "tic tac toe", "atm", "splitwise", "bookmyshow", "vending machine", "chess")
        ),
        TechnicalConceptModule(
            id = "lld_structural",
            name = "Structural Design Patterns",
            questionCount = 19,
            keywords = listOf("adapter", "decorator", "facade", "composite", "proxy", "bridge", "flyweight")
        ),
        TechnicalConceptModule(
            id = "lld_creational",
            name = "Creational Design Patterns",
            questionCount = 11,
            keywords = listOf("factory", "builder", "singleton", "prototype", "abstract factory", "object pool")
        ),
        TechnicalConceptModule(
            id = "lld_solid",
            name = "SOLID Principles & Clean Code",
            questionCount = 10,
            keywords = listOf("solid", "single responsibility", "open closed", "liskov", "interface segregation", "dependency inversion", "dry", "kiss", "yagni", "clean code")
        ),
        TechnicalConceptModule(
            id = "lld_uml",
            name = "UML & Class Modeling",
            questionCount = 8,
            keywords = listOf("uml", "class diagram", "sequence diagram", "relationship", "aggregation", "composition", "association")
        )
    )

    val sqlConcepts = listOf(
        TechnicalConceptModule(
            id = "sql_indexing",
            name = "Indexing & Query Optimization",
            questionCount = 176,
            keywords = listOf("index", "b-tree", "hash index", "explain", "query plan", "optimization", "performance", "gin", "gist", "covering index", "composite index")
        ),
        TechnicalConceptModule(
            id = "sql_transactions",
            name = "Transactions & ACID Isolation",
            questionCount = 43,
            keywords = listOf("transaction", "acid", "isolation", "dirty read", "phantom", "serializable", "read committed", "mvcc", "wal", "locking", "deadlock", "2pc")
        ),
        TechnicalConceptModule(
            id = "sql_joins",
            name = "Joins, Aggregation & Subqueries",
            questionCount = 23,
            keywords = listOf("join", "inner join", "outer join", "left join", "group by", "having", "aggregate", "subquery", "union")
        ),
        TechnicalConceptModule(
            id = "sql_advanced",
            name = "Window Functions & CTEs",
            questionCount = 21,
            keywords = listOf("window function", "rank", "dense_rank", "row_number", "over", "partition by", "cte", "recursive", "with clause", "analytic")
        ),
        TechnicalConceptModule(
            id = "sql_schema",
            name = "Schema Design & Normalization",
            questionCount = 17,
            keywords = listOf("normalization", "1nf", "2nf", "3nf", "bcnf", "foreign key", "primary key", "constraint", "schema", "table design", "surrogate key")
        ),
        TechnicalConceptModule(
            id = "sql_postgres",
            name = "PostgreSQL Advanced Features",
            questionCount = 14,
            keywords = listOf("postgresql", "jsonb", "vacuum", "analyze", "tuple", "autovacuum", "connection pool", "partitioning")
        ),
        TechnicalConceptModule(
            id = "sql_distributed",
            name = "Distributed SQL & Storage Engines",
            questionCount = 6,
            keywords = listOf("distributed sql", "cockroachdb", "yugabyte", "innodb", "myisam", "storage engine", "sharding", "lsm", "wal", "checkpoint")
        )
    )

    val angularConcepts = listOf(
        TechnicalConceptModule(
            id = "ng_rxjs",
            name = "RxJS & Reactive Programming",
            questionCount = 94,
            keywords = listOf("rxjs", "observable", "subject", "pipe", "switchmap", "mergemap", "concatmap", "behaviorsubject", "subscription", "takeuntil", "operator")
        ),
        TechnicalConceptModule(
            id = "ng_signals",
            name = "Signals & Modern Angular 16-18",
            questionCount = 64,
            keywords = listOf("signal", "computed", "effect", "standalone", "control flow", "defer", "inject", "modernangular", "zoneless")
        ),
        TechnicalConceptModule(
            id = "ng_components",
            name = "Components & Directives",
            questionCount = 57,
            keywords = listOf("component", "directive", "viewchild", "contentchild", "input", "output", "lifecycle", "ngoninit", "ngonchanges", "encapsulation")
        ),
        TechnicalConceptModule(
            id = "ng_change_detection",
            name = "Change Detection & Performance",
            questionCount = 34,
            keywords = listOf("change detection", "onpush", "zone", "zoneless", "cdk", "virtual scroll", "performance", "trackby", "pipe", "pure pipe")
        ),
        TechnicalConceptModule(
            id = "ng_routing",
            name = "Routing, Guards & Lazy Loading",
            questionCount = 21,
            keywords = listOf("routing", "router", "guard", "canactivate", "lazy loading", "resolver", "route", "navigation", "intercept")
        ),
        TechnicalConceptModule(
            id = "ng_forms",
            name = "Reactive & Template Forms",
            questionCount = 17,
            keywords = listOf("form", "reactiveforms", "formgroup", "formcontrol", "validator", "validation", "template-driven")
        ),
        TechnicalConceptModule(
            id = "ng_services",
            name = "Services, DI & State Management",
            questionCount = 13,
            keywords = listOf("service", "dependency injection", "providedin", "injectiontoken", "httpclient", "state", "store", "ngrx", "interceptor")
        )
    )

    val securityConcepts = listOf(
        TechnicalConceptModule(
            id = "sec_auth",
            name = "Authentication, OAuth 2.0 & JWT",
            questionCount = 128,
            keywords = listOf("oauth", "jwt", "oidc", "token", "sso", "saml", "pkce", "session", "mfa", "password", "hash", "bcrypt")
        ),
        TechnicalConceptModule(
            id = "sec_web",
            name = "Web Vulnerabilities & OWASP Top 10",
            questionCount = 55,
            keywords = listOf("owasp", "sqli", "xss", "csrf", "ssrf", "injection", "cors", "clickjacking", "xxe", "deserialization", "cwe")
        ),
        TechnicalConceptModule(
            id = "sec_crypto",
            name = "Cryptography & Key Management",
            questionCount = 38,
            keywords = listOf("cryptography", "encryption", "aes", "rsa", "hash", "sha", "hashing", "key management", "kms", "symmetric", "asymmetric", "hsm")
        ),
        TechnicalConceptModule(
            id = "sec_cloud",
            name = "Cloud, Container & DevSecOps",
            questionCount = 38,
            keywords = listOf("cloud", "container", "docker", "kubernetes", "devsecops", "ci/cd", "sast", "dast", "supply chain", "sbom", "registry")
        ),
        TechnicalConceptModule(
            id = "sec_network",
            name = "Network Security, TLS & mTLS",
            questionCount = 16,
            keywords = listOf("tls", "mtls", "ssl", "certificate", "https", "handshake", "hsts", "vpn", "firewall", "ddos", "waf")
        ),
        TechnicalConceptModule(
            id = "sec_architecture",
            name = "Security Architecture & Zero Trust",
            questionCount = 14,
            keywords = listOf("zero trust", "defense in depth", "audit", "compliance", "threat model", "stride", "incident", "siem")
        ),
        TechnicalConceptModule(
            id = "sec_api",
            name = "API Security & Access Control",
            questionCount = 11,
            keywords = listOf("api security", "rbac", "abac", "authorization", "rate limit", "gateway", "access control", "privilege")
        )
    )

    val systemDesignConcepts = listOf(
        TechnicalConceptModule(
            id = "sys_theory",
            name = "Distributed Theory & Consistency",
            questionCount = 12,
            keywords = listOf("cap", "pacelc", "consistency", "linearizability", "eventual", "2pc", "two phase", "vector clock", "idempotency", "consensus", "paxos", "raft")
        ),
        TechnicalConceptModule(
            id = "sys_storage",
            name = "Storage, Sharding & Replication",
            questionCount = 14,
            keywords = listOf("shard", "partition", "consistent hash", "virtual node", "replication", "quorum", "dynamo", "lsm", "b-tree", "wal", "storage")
        ),
        TechnicalConceptModule(
            id = "sys_caching",
            name = "Caching, CDN & Data Flow",
            questionCount = 12,
            keywords = listOf("cache", "cache-aside", "write-through", "write-back", "stampede", "thundering herd", "redis", "memcached", "cdn", "invalidation", "ttl")
        ),
        TechnicalConceptModule(
            id = "sys_traffic",
            name = "Traffic, Resiliency & Rate Limiting",
            questionCount = 12,
            keywords = listOf("rate limit", "token bucket", "leaky bucket", "sliding window", "load balancer", "l4", "l7", "circuit breaker", "bulkhead", "proxy", "gateway")
        ),
        TechnicalConceptModule(
            id = "sys_messaging",
            name = "Messaging & Event-Driven Systems",
            questionCount = 12,
            keywords = listOf("kafka", "rabbitmq", "message queue", "event", "consumer group", "partition", "at-least-once", "exactly-once", "dead letter", "backpressure", "pub/sub")
        ),
        TechnicalConceptModule(
            id = "sys_blueprints",
            name = "Real-World System Blueprints",
            questionCount = 18,
            keywords = listOf("blueprint", "url shortener", "tinyurl", "snowflake", "chat", "news feed", "fan-out", "autocomplete", "trie", "video streaming", "hls", "payment", "saga", "uber", "notification")
        )
    )

    val devopsConcepts = listOf(
        TechnicalConceptModule(
            id = "devops_linux",
            name = "Linux, Networking & OS Internals",
            questionCount = 14,
            keywords = listOf("linux", "signal", "sigterm", "sigkill", "cgroup", "namespace", "ss", "iptables", "dns", "tcpdump", "load average", "oom", "file descriptor", "socket")
        ),
        TechnicalConceptModule(
            id = "devops_docker",
            name = "Docker & Containerization",
            questionCount = 14,
            keywords = listOf("docker", "container", "dockerfile", "multi-stage", "layer", "bridge", "volume", "bind mount", "distroless", "rootless", "image", "cfs")
        ),
        TechnicalConceptModule(
            id = "devops_k8s",
            name = "Kubernetes Orchestration",
            questionCount = 16,
            keywords = listOf("kubernetes", "k8s", "pod", "deployment", "statefulset", "daemonset", "kube-apiserver", "etcd", "kubelet", "ingress", "hpa", "rbac", "serviceaccount", "crd")
        ),
        TechnicalConceptModule(
            id = "devops_cicd",
            name = "CI/CD Pipelines & GitOps",
            questionCount = 12,
            keywords = listOf("cicd", "ci/cd", "pipeline", "gitops", "argocd", "flux", "blue-green", "canary", "rolling update", "vault", "artifact", "trunk-based")
        ),
        TechnicalConceptModule(
            id = "devops_iac",
            name = "Infrastructure as Code (IaC)",
            questionCount = 12,
            keywords = listOf("terraform", "iac", "state", "remote backend", "tfstate", "ansible", "vpc", "subnet", "nat gateway", "security group", "nacl", "immutable")
        ),
        TechnicalConceptModule(
            id = "devops_sre",
            name = "Observability & SRE Principles",
            questionCount = 12,
            keywords = listOf("sre", "observability", "metrics", "logs", "traces", "opentelemetry", "prometheus", "promql", "grafana", "alertmanager", "slo", "sli", "sla", "error budget", "chaos")
        )
    )

    private val allConceptsList = javaConcepts +
        springBootConcepts +
        microservicesConcepts +
        hldConcepts +
        lldConcepts +
        sqlConcepts +
        angularConcepts +
        securityConcepts +
        systemDesignConcepts +
        devopsConcepts

    private val conceptMap = allConceptsList.associateBy { it.id }

    fun findConcept(conceptId: String): TechnicalConceptModule? = conceptMap[conceptId]

    fun getDomainForConcept(conceptId: String): String {
        return when {
            conceptId.startsWith("java_") -> "java"
            conceptId.startsWith("spring_") -> "spring_boot"
            conceptId.startsWith("ms_") -> "microservices"
            conceptId.startsWith("hld_") -> "hld"
            conceptId.startsWith("lld_") -> "lld"
            conceptId.startsWith("sql_") -> "sql"
            conceptId.startsWith("ng_") -> "angular"
            conceptId.startsWith("sec_") -> "security"
            conceptId.startsWith("sys_") -> "system_design"
            conceptId.startsWith("devops_") -> "devops"
            else -> "java"
        }
    }

    fun getConceptsForDomain(domainId: String): List<TechnicalConceptModule> {
        return when (domainId) {
            "java" -> javaConcepts
            "spring_boot" -> springBootConcepts
            "microservices" -> microservicesConcepts
            "hld" -> hldConcepts
            "lld" -> lldConcepts
            "sql" -> sqlConcepts
            "angular" -> angularConcepts
            "security" -> securityConcepts
            "system_design" -> systemDesignConcepts
            "devops" -> devopsConcepts
            else -> emptyList()
        }
    }

    fun matchesConcept(concept: TechnicalConceptModule, title: String, prompt: String, tags: List<String>): Boolean {
        val searchBlob = (title + " " + prompt + " " + tags.joinToString(" ")).lowercase()
        return concept.keywords.any { kw -> searchBlob.contains(kw) }
    }
}
