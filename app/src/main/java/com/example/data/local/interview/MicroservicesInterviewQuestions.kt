package com.example.data.local.interview

import com.example.domain.model.InterviewQuestion

object MicroservicesInterviewQuestions {

    fun getQuestions(): List<InterviewQuestion> = listOf(
        // --- Concept 1: Distributed Transactions & Sagas ---
        InterviewQuestion(
            id = "iq_ms_001",
            trackId = "microservices_interview",
            conceptId = "ms_distributed_tx",
            conceptName = "Distributed Transactions & Sagas",
            title = "Saga Pattern: Choreography vs Orchestration",
            question = "Explain how the Saga pattern coordinates distributed transactions across microservices. Compare choreography versus orchestration.",
            shortAnswer = "A Saga breaks a distributed transaction into a sequence of local transactions, where each step updates its local database and publishes an event or message. If a step fails, compensating transactions are executed in reverse to undo partial work. In Choreography, services react to domain events published by peers without a central coordinator (decentralized, but hard to track complex flows). In Orchestration, a central Saga Orchestrator coordinates the workflow, commanding each participant service what action or compensating action to execute.",
            keyPoints = listOf(
                "Breaks global transactions into atomic local transactions with compensating rollbacks",
                "Compensating transactions semantically undo changes (cannot physically rollback committed state)",
                "Choreography: decentralized pub/sub, best for simple 2-4 step workflows",
                "Orchestration: centralized state machine, clear visibility and error handling for complex sagas",
                "Replaces blocking Two-Phase Commit (2PC) to preserve microservice autonomy and scalability"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ms_002",
            trackId = "microservices_interview",
            conceptId = "ms_distributed_tx",
            conceptName = "Distributed Transactions & Sagas",
            title = "Transactional Outbox Pattern & CDC",
            question = "How does the Transactional Outbox pattern solve the dual-write problem between an atomic database write and a message broker publish?",
            shortAnswer = "Writing to a relational database and publishing an event to a message broker in the same operation risks data inconsistency if the broker crashes after the DB commits. The Transactional Outbox pattern solves this by writing the domain entity and an outbox event record into an 'outbox' table within the exact same ACID database transaction. A separate relay process (or Change Data Capture tool like Debezium tailing the DB transaction log) reads outbox entries and publishes them reliably to the message broker with at-least-once delivery guarantees.",
            keyPoints = listOf(
                "Dual-write problem: cannot atomically commit DB write and Kafka message in 2PC",
                "Entity and Outbox Event are persisted in the same local database transaction",
                "CDC (Debezium / Kafka Connect) tails database write-ahead log (WAL) without polling",
                "Guarantees at-least-once message delivery to message broker",
                "Consumers must implement idempotency using unique event IDs"
            ),
            difficulty = "Staff"
        ),

        // --- Concept 2: Resilience & Fault Tolerance ---
        InterviewQuestion(
            id = "iq_ms_003",
            trackId = "microservices_interview",
            conceptId = "ms_resilience",
            conceptName = "Resilience & Fault Tolerance",
            title = "Circuit Breaker Pattern & States",
            question = "What are the states of a Circuit Breaker (Resilience4j), and how does it prevent cascading system failures across dependent microservices?",
            shortAnswer = "A Circuit Breaker has three states: 1) CLOSED: normal operation; requests flow downstream while failure rate is monitored in a sliding window. 2) OPEN: when failures exceed the configured threshold (e.g. 50%), the breaker trips OPEN, immediately rejecting incoming calls with a CallNotPermittedException or returning fallbacks to protect the downstream service. 3) HALF-OPEN: after a wait duration, a trial number of requests are permitted through. If successful, it resets to CLOSED; if failures persist, it returns to OPEN.",
            keyPoints = listOf(
                "CLOSED state monitors failure rates in count-based or time-based sliding windows",
                "OPEN state fails fast without consuming thread resources or overwhelming degraded service",
                "HALF-OPEN state tests if the downstream service has recovered",
                "Fallback mechanisms return cached data or graceful degradation responses",
                "Prevents cascading resource exhaustion across synchronous service chains"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_ms_004",
            trackId = "microservices_interview",
            conceptId = "ms_resilience",
            conceptName = "Resilience & Fault Tolerance",
            title = "Bulkhead Pattern & Thread Isolation",
            question = "How does the Bulkhead pattern isolate failures in microservice architectures?",
            shortAnswer = "Inspired by ship watertight compartments, the Bulkhead pattern partitions system resources (such as thread pools or connection pools) across different downstream dependencies. If Service C becomes slow or unresponsive, only the dedicated thread pool allocated for calls to Service C becomes exhausted. Calls to Service A and Service B utilize independent thread pools and continue functioning normally, preventing a single slow dependency from starving the entire application's worker threads.",
            keyPoints = listOf(
                "Resource partitioning: separate thread pools or semaphores per dependency",
                "Failure containment: keeps downstream outages from consuming all container threads",
                "ThreadPoolBulkhead uses dedicated thread queues per target service",
                "SemaphoreBulkhead limits concurrent in-flight requests without context-switch overhead",
                "Crucial for protecting edge gateways and composite microservices"
            ),
            difficulty = "Senior"
        ),

        // --- Concept 3: API Gateway & Service Mesh ---
        InterviewQuestion(
            id = "iq_ms_005",
            trackId = "microservices_interview",
            conceptId = "ms_gateway_mesh",
            conceptName = "API Gateway & Service Mesh",
            title = "API Gateway vs Service Mesh Responsibilities",
            question = "Differentiate the roles of an API Gateway (North-South traffic) and a Service Mesh like Istio (East-West traffic).",
            shortAnswer = "An API Gateway manages North-South traffic (client-to-service communication from outside the cluster): it handles SSL termination, authentication/token validation, external rate limiting, request routing, and API protocol translation (REST/JSON to gRPC). A Service Mesh manages East-West traffic (internal service-to-service communication within the cluster): using sidecar proxies (Envoy), it provides transparent mutual TLS (mTLS) encryption, fine-grained traffic shifting (canary deployments), circuit breaking, and distributed telemetry without changing application code.",
            keyPoints = listOf(
                "North-South traffic: incoming client requests into cluster handled by API Gateway",
                "East-West traffic: inter-service communication inside cluster handled by Service Mesh",
                "Gateway handles client-facing concerns: auth token translation, public rate limits, CORS",
                "Service Mesh handles internal infra: mTLS, service-to-service retries, traffic splitting",
                "They complement each other rather than being mutually exclusive"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ms_006",
            trackId = "microservices_interview",
            conceptId = "ms_gateway_mesh",
            conceptName = "API Gateway & Service Mesh",
            title = "Distributed Tracing with W3C TraceContext",
            question = "How does distributed tracing track a request across a chain of 10 microservices? Explain TraceId, SpanId, and Context Propagation.",
            shortAnswer = "Distributed tracing generates a globally unique TraceId when a request enters the system (e.g. at the API Gateway). Each sub-unit of work or hop between services creates a new Span with its own SpanId and a parentSpanId. These identifiers are propagated across HTTP headers using W3C TraceContext standards ('traceparent: 00-traceId-spanId-traceFlags'). Downstream services extract this header and continue the trace, sending span data to collectors (OpenTelemetry / Jaeger) where full end-to-end request timelines and latency bottlenecks are reconstructed.",
            keyPoints = listOf(
                "TraceId uniquely identifies the entire end-to-end request lifecycle across all systems",
                "SpanId identifies an individual unit of work (HTTP call, DB query, Kafka publish)",
                "W3C traceparent header propagates context across network boundaries",
                "Context propagation in Java uses ThreadLocal or OpenTelemetry Context API",
                "Identifies latency bottlenecks and exact point of failures in distributed call chains"
            ),
            difficulty = "Senior"
        )
    )
}
