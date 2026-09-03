package com.example.data.local.database

import com.example.data.local.entity.QuestionEntity
import com.example.data.local.questions.AngularAdvancedQuestions
import com.example.data.local.questions.AngularBeginnerQuestions
import com.example.data.local.questions.AngularIntermediateQuestions
import com.example.data.local.questions.HldAdvancedQuestions
import com.example.data.local.questions.HldBeginnerQuestions
import com.example.data.local.questions.HldIntermediateQuestions
import com.example.data.local.questions.JavaAdvancedQuestions
import com.example.data.local.questions.JavaBeginnerQuestions
import com.example.data.local.questions.JavaIntermediateQuestions
import com.example.data.local.questions.LldAdvancedQuestions
import com.example.data.local.questions.LldBeginnerQuestions
import com.example.data.local.questions.LldIntermediateQuestions
import com.example.data.local.questions.MicroservicesAdvancedQuestions
import com.example.data.local.questions.MicroservicesBeginnerQuestions
import com.example.data.local.questions.MicroservicesIntermediateQuestions
import com.example.data.local.questions.SpringBootAdvancedQuestions
import com.example.data.local.questions.SpringBootBeginnerQuestions
import com.example.data.local.questions.SpringBootIntermediateQuestions
import com.example.data.local.questions.SqlAdvancedQuestions
import com.example.data.local.questions.SqlBeginnerQuestions
import com.example.data.local.questions.SqlIntermediateQuestions
import org.json.JSONArray

object SampleQuestionData {

    private fun buildJson(options: List<String>): String {
        val array = JSONArray()
        options.forEach { array.put(it) }
        return array.toString()
    }

    /**
     * Complete Question Bank for Software Engineer Technical Interview Preparation.
     * Contains:
     * - Java Beginner, Intermediate, Advanced (300 MCQs)
     * - Spring Boot Beginner, Intermediate, Advanced (300 MCQs)
     * - Microservices Beginner, Intermediate, Advanced (300 MCQs)
     * - HLD Beginner, Intermediate, Advanced (300 MCQs)
     * - LLD Beginner, Intermediate, Advanced (300 MCQs)
     * - SQL & Database Design Beginner, Intermediate, Advanced (300 MCQs)
     * - Angular & Frontend Beginner, Intermediate, Advanced (300 MCQs)
     * - Core questions for Security and DevOps.
     */
    val sampleQuestions: List<QuestionEntity> by lazy {
        JavaBeginnerQuestions.getAll() +
        JavaIntermediateQuestions.getAll() +
        JavaAdvancedQuestions.getAll() +
        SpringBootBeginnerQuestions.getAll() +
        SpringBootIntermediateQuestions.getAll() +
        SpringBootAdvancedQuestions.getAll() +
        MicroservicesBeginnerQuestions.getAll() +
        MicroservicesIntermediateQuestions.getAll() +
        MicroservicesAdvancedQuestions.getAll() +
        HldBeginnerQuestions.getAll() +
        HldIntermediateQuestions.getAll() +
        HldAdvancedQuestions.getAll() +
        LldBeginnerQuestions.getAll() +
        LldIntermediateQuestions.getAll() +
        LldAdvancedQuestions.getAll() +
        SqlBeginnerQuestions.getAll() +
        SqlIntermediateQuestions.getAll() +
        SqlAdvancedQuestions.getAll() +
        AngularBeginnerQuestions.getAll() +
        AngularIntermediateQuestions.getAll() +
        AngularAdvancedQuestions.getAll() +
        otherDomainQuestions
    }

    private val otherDomainQuestions: List<QuestionEntity> = listOf(
        // 3. Spring Boot - Transaction Management
        QuestionEntity(
            id = "q_spring_transactional",
            categoryId = "spring_boot",
            title = "Spring @Transactional Rollback Rules",
            prompt = "By default, which types of exceptions cause Spring's @Transactional to roll back a transaction?",
            optionsJson = buildJson(
                listOf(
                    "All exceptions including checked and unchecked (RuntimeException and Error)",
                    "Only unchecked exceptions (subclasses of RuntimeException and Error)",
                    "Only checked exceptions that are declared in the method throws signature",
                    "Only custom exceptions annotated with @ResponseStatus"
                )
            ),
            correctAnswerIndex = 1,
            explanation = "By default, Spring's declarative transaction management rolls back transactions only on unchecked exceptions (RuntimeException and Error). To roll back on checked exceptions, you must explicitly specify rollbackFor = Exception.class.",
            difficulty = "Intermediate",
            tags = "Spring Boot,Transactions,JPA"
        ),

        // 4. Spring Boot - Concurrency & Bean Scopes
        QuestionEntity(
            id = "q_spring_scopes",
            categoryId = "spring_boot",
            title = "Spring Bean Scopes & Thread Safety",
            prompt = "What is the default scope of a bean in the Spring ApplicationContext, and what are its concurrency implications?",
            optionsJson = buildJson(
                listOf(
                    "Prototype scope; a new instance is created per injection point so state is isolated",
                    "Singleton scope; a single shared instance per container, so it should generally be stateless or thread-safe",
                    "Request scope; bound strictly to the HTTP request lifecycle in web applications",
                    "Thread scope; each worker thread receives its own dedicated bean instance"
                )
            ),
            correctAnswerIndex = 1,
            explanation = "Spring beans are singletons by default. Since multiple threads (e.g. incoming HTTP servlet threads) can invoke methods on the same bean concurrently, singleton beans must either be stateless or have synchronized/thread-safe shared state.",
            difficulty = "Easy",
            tags = "Spring Boot,Dependency Injection,Core"
        ),

        // 5. Microservices - Distributed Transactions
        QuestionEntity(
            id = "q_micro_saga",
            categoryId = "microservices",
            title = "Saga Pattern: Orchestration vs Choreography",
            prompt = "What is the primary difference between Choreography and Orchestration in the Saga pattern?",
            optionsJson = buildJson(
                listOf(
                    "Choreography uses a central coordinator service, whereas Orchestration relies on peer-to-peer pub/sub",
                    "Choreography relies on event-driven collaboration without a central coordinator, whereas Orchestration uses a central orchestrator telling participants what local transactions to execute",
                    "Choreography uses Two-Phase Commit (2PC) locks, whereas Orchestration uses Three-Phase Commit (3PC)",
                    "Choreography can only be used with relational databases, whereas Orchestration only works with NoSQL"
                )
            ),
            correctAnswerIndex = 1,
            explanation = "In choreography, services publish and listen to domain events and execute local transactions independently. In orchestration, a dedicated saga orchestrator directs the participants by sending command messages and managing compensating actions on failures.",
            difficulty = "Advanced",
            tags = "Microservices,Saga Pattern,Distributed Systems"
        ),

        // 6. Microservices - Fault Tolerance
        QuestionEntity(
            id = "q_micro_circuit_breaker",
            categoryId = "microservices",
            title = "Circuit Breaker States",
            prompt = "What are the three canonical states of the Circuit Breaker pattern in microservices?",
            optionsJson = buildJson(
                listOf(
                    "Active, Inactive, Blocked",
                    "Closed (normal traffic), Open (fast-fail without calling remote service), and Half-Open (trial calls to test recovery)",
                    "Synchronous, Asynchronous, Fallback",
                    "Read-Only, Write-Only, Locked"
                )
            ),
            correctAnswerIndex = 1,
            explanation = "Closed passes all requests through. When failures exceed a threshold, it trips to Open, instantly failing calls to prevent cascading resource exhaustion. After a sleep window, it enters Half-Open, allowing trial requests to verify if the downstream service has recovered.",
            difficulty = "Intermediate",
            tags = "Microservices,Resilience,Fault Tolerance"
        ),

        // 7. System Design / HLD - Scalability
        QuestionEntity(
            id = "q_hld_consistent_hashing",
            categoryId = "hld",
            title = "Consistent Hashing & Virtual Nodes",
            prompt = "Why are 'virtual nodes' (vnodes) utilized in Consistent Hashing rings used by distributed systems (e.g. Cassandra, DynamoDB)?",
            optionsJson = buildJson(
                listOf(
                    "To encrypt keys before storing them in cache memory",
                    "To ensure uniform key distribution across physical servers and avoid hot-spotting/skew when cluster topology changes",
                    "To replicate data to secondary geographic regions asynchronously",
                    "To replace standard TCP sockets with virtual IPC channels"
                )
            ),
            correctAnswerIndex = 1,
            explanation = "Virtual nodes map each physical server to multiple points on the hash ring. This evens out data distribution, avoids hot spots caused by non-uniform hash spread, and ensures smooth proportional redistribution when nodes join or leave.",
            difficulty = "Advanced",
            tags = "HLD,System Design,Distributed Systems"
        ),

        // 8. System Design - Distributed Systems Theory
        QuestionEntity(
            id = "q_sys_cap_theorem",
            categoryId = "system_design",
            title = "CAP Theorem Under Network Partitions",
            prompt = "According to the CAP Theorem, when a network partition (P) occurs in a distributed system, what trade-off must be made?",
            optionsJson = buildJson(
                listOf(
                    "Choose between Consistency (returning the most recent write or an error) and Availability (every non-failing node returns a non-error response)",
                    "Choose between Concurrency and Persistence",
                    "Choose between Latency and Throughput",
                    "Choose between Atomicity and Durability"
                )
            ),
            correctAnswerIndex = 0,
            explanation = "In distributed systems, network partitions (P) are inevitable. When a partition occurs, the system must choose between CP (Consistency: refuse writes or block reads to maintain linearizability) or AP (Availability: accept operations even if replicas might diverge).",
            difficulty = "Intermediate",
            tags = "System Design,Distributed Systems,CAP Theorem"
        ),

        // 9. SQL - Transactions & Isolation
        QuestionEntity(
            id = "q_sql_isolation_levels",
            categoryId = "sql",
            title = "SQL Transaction Isolation Levels",
            prompt = "Which standard ANSI SQL isolation level prevents dirty reads, non-repeatable reads, and phantom reads?",
            optionsJson = buildJson(
                listOf(
                    "Read Uncommitted",
                    "Read Committed",
                    "Repeatable Read",
                    "Serializable"
                )
            ),
            correctAnswerIndex = 3,
            explanation = "Serializable is the highest isolation level. It executes transactions concurrently with results equivalent to some serial execution, completely eliminating dirty reads, non-repeatable reads, and phantom reads.",
            difficulty = "Intermediate",
            tags = "SQL,Databases,ACID,Transactions"
        ),

        // 10. Security - Authentication & Tokens
        QuestionEntity(
            id = "q_sec_jwt_signature",
            categoryId = "security",
            title = "JWT Structure & Signature Verification",
            prompt = "Which component of a signed JSON Web Token (JWT: Header.Payload.Signature) prevents the client from tampering with user claims such as roles or user IDs?",
            optionsJson = buildJson(
                listOf(
                    "The base64url-encoded Payload claims dictionary",
                    "The cryptographic Signature generated using the secret/private key over the Header and Payload",
                    "The CORS header returned by the authorization server",
                    "The sub (subject) identifier claim in the body"
                )
            ),
            correctAnswerIndex = 1,
            explanation = "The cryptographic Signature is generated by taking the encoded Header, encoded Payload, a secret/private key, and signing with the specified algorithm (e.g. HMAC-SHA256 or RSA). Modifying the payload invalidates the signature, preventing tampering.",
            difficulty = "Intermediate",
            tags = "Security,OAuth 2.0,JWT,AppSec"
        )
    )
}
