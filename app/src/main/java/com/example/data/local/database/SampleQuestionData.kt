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
import com.example.data.local.questions.SecurityAdvancedQuestions
import com.example.data.local.questions.SecurityBeginnerQuestions
import com.example.data.local.questions.SecurityIntermediateQuestions
import com.example.data.local.questions.SpringBootAdvancedQuestions
import com.example.data.local.questions.SpringBootBeginnerQuestions
import com.example.data.local.questions.SpringBootIntermediateQuestions
import com.example.data.local.questions.SqlAdvancedQuestions
import com.example.data.local.questions.SqlBeginnerQuestions
import com.example.data.local.questions.SqlIntermediateQuestions
import com.example.data.local.questions.DevopsQuestions
import com.example.data.local.questions.SystemDesignQuestions
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
     * - Security & AppSec Beginner, Intermediate, Advanced (300 MCQs)
     * - System Design (80 MCQs)
     * - DevOps (80 MCQs)
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
        SecurityBeginnerQuestions.getAll() +
        SecurityIntermediateQuestions.getAll() +
        SecurityAdvancedQuestions.getAll() +
        SystemDesignQuestions.getAll() +
        DevopsQuestions.getAll() +
        otherDomainQuestions
    }

    private val otherDomainQuestions: List<QuestionEntity> = listOf(
        // 3. Spring Boot - Transaction Management
        com.example.data.local.questions.QuestionHelper.q(
            id = "q_spring_transactional",
            categoryId = "spring_boot",
            title = "Spring @Transactional Rollback Rules",
            prompt = "By default, which types of exceptions cause Spring's @Transactional to roll back a transaction?",
            opt0 = "All exceptions including checked and unchecked exceptions and Errors",
            opt1 = "Only unchecked exceptions (subclasses of RuntimeException and Error)",
            opt2 = "Only checked exceptions declared in the throws signature of the method",
            opt3 = "Only custom application exceptions annotated with @ResponseStatus rules",
            correctIndex = 1,
            explanation = "By default, Spring's declarative transaction management rolls back transactions only on unchecked exceptions (RuntimeException and Error). To roll back on checked exceptions, you must explicitly specify rollbackFor = Exception.class.",
            difficulty = "Intermediate",
            tags = "Spring Boot,Transactions,JPA"
        ),

        // 4. Spring Boot - Concurrency & Bean Scopes
        com.example.data.local.questions.QuestionHelper.q(
            id = "q_spring_scopes",
            categoryId = "spring_boot",
            title = "Spring Bean Scopes & Thread Safety",
            prompt = "What is the default scope of a bean in the Spring ApplicationContext, and what are its concurrency implications?",
            opt0 = "Prototype scope; a new instance is created per injection point with isolated state",
            opt1 = "Singleton scope; a single shared instance per container requiring thread-safety",
            opt2 = "Request scope; bound strictly to the HTTP request lifecycle inside web containers",
            opt3 = "Thread scope; each active worker thread receives its own dedicated bean instance",
            correctIndex = 1,
            explanation = "Spring beans are singletons by default. Since multiple threads (e.g. incoming HTTP servlet threads) can invoke methods on the same bean concurrently, singleton beans must either be stateless or have synchronized/thread-safe shared state.",
            difficulty = "Beginner",
            tags = "Spring Boot,Dependency Injection,Core"
        ),

        // 5. Microservices - Distributed Transactions
        com.example.data.local.questions.QuestionHelper.q(
            id = "q_micro_saga",
            categoryId = "microservices",
            title = "Saga Pattern: Orchestration vs Choreography",
            prompt = "What is the primary difference between Choreography and Orchestration in the Saga pattern?",
            opt0 = "Choreography uses a central coordinator; Orchestration relies on peer pub/sub",
            opt1 = "Choreography uses decentralized event pub/sub; Orchestration uses a central coordinator",
            opt2 = "Choreography uses two-phase commit locks; Orchestration uses three-phase commit",
            opt3 = "Choreography requires relational databases; Orchestration only works with NoSQL stores",
            correctIndex = 1,
            explanation = "In choreography, services publish and listen to domain events and execute local transactions independently. In orchestration, a dedicated saga orchestrator directs the participants by sending command messages and managing compensating actions on failures.",
            difficulty = "Advanced",
            tags = "Microservices,Saga Pattern,Distributed Systems"
        ),

        // 6. Microservices - Fault Tolerance
        com.example.data.local.questions.QuestionHelper.q(
            id = "q_micro_circuit_breaker",
            categoryId = "microservices",
            title = "Circuit Breaker States",
            prompt = "What are the three canonical states of the Circuit Breaker pattern in microservices?",
            opt0 = "Active, Inactive, and Blocked states for load management",
            opt1 = "Closed (normal), Open (fast-fail), and Half-Open (trial calls)",
            opt2 = "Synchronous, Asynchronous, and Fallback operational modes",
            opt3 = "Read-Only, Write-Only, and Locked transaction levels",
            correctIndex = 1,
            explanation = "Closed passes all requests through. When failures exceed a threshold, it trips to Open, instantly failing calls to prevent cascading resource exhaustion. After a sleep window, it enters Half-Open, allowing trial requests to verify if the downstream service has recovered.",
            difficulty = "Intermediate",
            tags = "Microservices,Resilience,Fault Tolerance"
        ),

        // 7. System Design / HLD - Scalability
        com.example.data.local.questions.QuestionHelper.q(
            id = "q_hld_consistent_hashing",
            categoryId = "hld",
            title = "Consistent Hashing & Virtual Nodes",
            prompt = "Why are 'virtual nodes' (vnodes) utilized in Consistent Hashing rings used by distributed systems (e.g. Cassandra, DynamoDB)?",
            opt0 = "To encrypt cache keys before storing them in persistent flash memory",
            opt1 = "To ensure uniform key distribution across physical servers and avoid hot spots",
            opt2 = "To replicate all partition data to secondary geographic cloud regions",
            opt3 = "To replace standard TCP socket communication with virtual IPC channels",
            correctIndex = 1,
            explanation = "Virtual nodes map each physical server to multiple points on the hash ring. This evens out data distribution, avoids hot spots caused by non-uniform hash spread, and ensures smooth proportional redistribution when nodes join or leave.",
            difficulty = "Advanced",
            tags = "HLD,System Design,Distributed Systems"
        ),

        // 9. SQL - Transactions & Isolation
        com.example.data.local.questions.QuestionHelper.q(
            id = "q_sql_isolation_levels",
            categoryId = "sql",
            title = "SQL Transaction Isolation Levels",
            prompt = "Which standard ANSI SQL isolation level prevents dirty reads, non-repeatable reads, and phantom reads?",
            opt0 = "Read Uncommitted isolation level",
            opt1 = "Read Committed isolation level",
            opt2 = "Repeatable Read isolation level",
            opt3 = "Serializable transaction isolation level",
            correctIndex = 3,
            explanation = "Serializable is the highest isolation level. It executes transactions concurrently with results equivalent to some serial execution, completely eliminating dirty reads, non-repeatable reads, and phantom reads.",
            difficulty = "Intermediate",
            tags = "SQL,Databases,ACID,Transactions"
        ),

        // 10. Security - Authentication & Tokens
        com.example.data.local.questions.QuestionHelper.q(
            id = "q_sec_jwt_signature",
            categoryId = "security",
            title = "JWT Structure & Signature Verification",
            prompt = "Which component of a signed JSON Web Token (JWT: Header.Payload.Signature) prevents the client from tampering with user claims such as roles or user IDs?",
            opt0 = "The base64url-encoded Payload claims dictionary",
            opt1 = "The cryptographic Signature generated with a secret key",
            opt2 = "The CORS header returned by authorization server",
            opt3 = "The sub (subject) identifier claim in the token body",
            correctIndex = 1,
            explanation = "The cryptographic Signature is generated by taking the encoded Header, encoded Payload, a secret/private key, and signing with the specified algorithm (e.g. HMAC-SHA256 or RSA). Modifying the payload invalidates the signature, preventing tampering.",
            difficulty = "Intermediate",
            tags = "Security,OAuth 2.0,JWT,AppSec"
        )
    )
}
