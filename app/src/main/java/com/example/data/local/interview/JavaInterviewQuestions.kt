package com.example.data.local.interview

import com.example.domain.model.InterviewQuestion

object JavaInterviewQuestions {

    fun getQuestions(): List<InterviewQuestion> = listOf(
        // --- Concept 1: JVM Architecture & Memory Management ---
        InterviewQuestion(
            id = "iq_java_001",
            trackId = "java_interview",
            conceptId = "java_jvm_memory",
            conceptName = "JVM Architecture & Memory Management",
            title = "JVM Runtime Data Areas: Stack vs Heap",
            question = "Explain the difference between the JVM Stack and Heap memory. What causes StackOverflowError versus OutOfMemoryError?",
            shortAnswer = "The JVM Stack stores primitive variables and references to objects inside local method frames; each thread has its own private stack. The Heap is shared across all threads and stores actual object instances. StackOverflowError occurs when method call depth exceeds stack allocation (e.g. infinite recursion). OutOfMemoryError (OOM) occurs when the heap runs out of space to allocate new objects and Garbage Collection cannot reclaim sufficient memory.",
            keyPoints = listOf(
                "Stack is per-thread; Heap is shared among all threads",
                "Stack allocates stack frames for method calls and primitive/reference locals",
                "Heap stores all object instances and arrays",
                "StackOverflowError is caused by excessive call depth / infinite recursion",
                "OutOfMemoryError: Java heap space is caused by memory leaks or undersized heap (-Xmx)"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_java_002",
            trackId = "java_interview",
            conceptId = "java_jvm_memory",
            conceptName = "JVM Architecture & Memory Management",
            title = "Generational Garbage Collection & G1 GC",
            question = "How does Generational Garbage Collection work in modern JVMs, and why is G1 GC favored for low-latency enterprise applications?",
            shortAnswer = "Generational GC exploits the weak generational hypothesis: most objects die young. Memory is divided into Young Generation (Eden and Survivor spaces S0/S1) and Old (Tenured) Generation. Surviving objects are promoted after multiple minor GC cycles. G1 GC (Garbage-First) divides heap into equal-sized regions and targets regions with the highest proportion of garbage first within a user-defined pause-time goal (-XX:MaxGCPauseMillis), minimizing latency spikes.",
            keyPoints = listOf(
                "Weak generational hypothesis: young objects have short lifespans",
                "Minor GC collects Eden and Survivor spaces; Major/Full GC sweeps Old Gen",
                "Object age threshold (tenuring threshold) promotes long-lived objects to Old Gen",
                "G1 GC partitions heap into equal regions rather than contiguous generations",
                "Predictable pause time targeting with mixed collections"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_java_003",
            trackId = "java_interview",
            conceptId = "java_jvm_memory",
            conceptName = "JVM Architecture & Memory Management",
            title = "Metaspace vs PermGen Internals",
            question = "What replaced PermGen in Java 8, and how does Metaspace prevent OutOfMemoryError: PermGen space?",
            shortAnswer = "Java 8 replaced PermGen with Metaspace. PermGen resided in the contiguous JVM heap with a fixed default maximum size. In contrast, Metaspace stores class metadata in native OS memory and dynamically expands by default up to available physical memory, unless capped via -XX:MaxMetaspaceSize. This drastically reduces classloading OOM issues.",
            keyPoints = listOf(
                "PermGen was in JVM heap with rigid size limits",
                "Metaspace allocates class metadata in native off-heap memory",
                "Grows dynamically according to OS memory availability",
                "Can be constrained using -XX:MaxMetaspaceSize",
                "Classloaders and interned strings moved to heap / native memory"
            ),
            difficulty = "Senior"
        ),

        // --- Concept 2: Collections & Hashing Internals ---
        InterviewQuestion(
            id = "iq_java_004",
            trackId = "java_interview",
            conceptId = "java_collections_hash",
            conceptName = "Collections & Hashing Internals",
            title = "HashMap Internal Mechanics in Java 8+",
            question = "How does HashMap work internally in Java 8+? What happens during a hash collision and treeification?",
            shortAnswer = "HashMap uses an array of Node buckets indexed via (n - 1) & hash(key). In Java 8, collisions initially append to a singly-linked list. If a bucket's chain exceeds 8 elements (TREEIFY_THRESHOLD) and the table capacity is at least 64, the chain converts to a Red-Black Tree (TreeNode), reducing lookup time from O(n) to O(log n). If the tree shrinks to 6 elements (UNTREEIFY_THRESHOLD), it converts back to a linked list.",
            keyPoints = listOf(
                "Bucket index computed via bitwise AND with capacity power-of-two",
                "Collisions initially handled via separate chaining (linked list)",
                "Treeification threshold of 8 converts bucket into Red-Black Tree (O(log n))",
                "Table capacity must be at least 64 for treeification, else resize occurs",
                "Load factor (default 0.75) triggers array resizing (doubling capacity)"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_java_005",
            trackId = "java_interview",
            conceptId = "java_collections_hash",
            conceptName = "Collections & Hashing Internals",
            title = "ConcurrentHashMap vs SynchronizedMap",
            question = "What makes ConcurrentHashMap more performant than Collections.synchronizedMap() under heavy concurrent traffic?",
            shortAnswer = "Collections.synchronizedMap() synchronizes every operation on a single mutex lock, serializing all read and write threads. ConcurrentHashMap achieves high concurrency by avoiding table-wide locks: reads are non-blocking via volatile node pointers, while writes lock only the specific bucket head node using synchronized and CAS (Compare-And-Swap) operations. This allows simultaneous access to distinct buckets without contention.",
            keyPoints = listOf(
                "synchronizedMap locks the entire map instance on every access",
                "ConcurrentHashMap uses CAS for empty bucket insertions and synchronized on bucket head",
                "Lock granularity is per-bucket rather than map-wide",
                "Volatile reads allow non-blocking concurrent get() operations",
                "No ConcurrentModificationException during iteration"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_java_006",
            trackId = "java_interview",
            conceptId = "java_collections_hash",
            conceptName = "Collections & Hashing Internals",
            title = "equals() and hashCode() Contract",
            question = "What is the equals() and hashCode() contract in Java, and what catastrophic bug occurs if hashCode() is not overridden alongside equals()?",
            shortAnswer = "The contract states: if two objects are equal according to equals(), they must produce the same hashCode(). If hashCode() is not overridden, two objects that represent identical domain entities will produce different hash codes based on Object's default memory address. Consequently, when used in hash-based collections (HashMap, HashSet), the object will look in the wrong bucket, failing to find stored entries or permitting duplicate keys.",
            keyPoints = listOf(
                "Equal objects (equals == true) must have identical hash codes",
                "Unequal objects can share the same hash code (hash collision)",
                "Inconsistent implementation causes broken lookups in HashMap and HashSet",
                "Objects may appear 'missing' even when present in the collection",
                "Always use the same fields in both equals and hashCode calculations"
            ),
            difficulty = "Mid-Level"
        ),

        // --- Concept 3: Concurrency & Multithreading ---
        InterviewQuestion(
            id = "iq_java_007",
            trackId = "java_interview",
            conceptId = "java_concurrency",
            conceptName = "Concurrency & Multithreading",
            title = "volatile vs synchronized vs Atomic Variables",
            question = "Differentiate between volatile, synchronized, and AtomicInteger in Java. When would you choose each?",
            shortAnswer = "volatile guarantees visibility and prevents instruction reordering via memory barriers, but does not provide mutual exclusion or compound atomicity (e.g. count++ is not thread-safe). synchronized provides mutual exclusion and visibility by acquiring an intrinsic monitor lock, but introduces thread blocking overhead. AtomicInteger uses non-blocking hardware-level CAS (Compare-And-Swap) to execute lock-free atomic compound operations efficiently.",
            keyPoints = listOf(
                "volatile ensures reads and writes hit main memory directly (visibility only)",
                "volatile does not make compound operations (check-then-act, increment) atomic",
                "synchronized locks the object monitor, guaranteeing atomicity and visibility with blocking",
                "AtomicInteger utilizes low-level CPU CAS primitives for lock-free atomicity",
                "Use volatile for status flags, Atomic for counters, synchronized for complex invariant updates"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_java_008",
            trackId = "java_interview",
            conceptId = "java_concurrency",
            conceptName = "Concurrency & Multithreading",
            title = "ThreadPoolExecutor Sizing & Rejection Policies",
            question = "How does ThreadPoolExecutor process submitted tasks, and what happens when both the core pool and the work queue become full?",
            shortAnswer = "ThreadPoolExecutor first spawns worker threads up to corePoolSize. When all core threads are busy, incoming tasks are queued in the BlockingQueue. Only when the queue fills does it create extra threads up to maximumPoolSize. If the queue and maximumPoolSize are both saturated, the RejectedExecutionHandler is triggered: AbortPolicy (throws exception), CallerRunsPolicy (executes in caller thread to throttle input), DiscardPolicy, or DiscardOldestPolicy.",
            keyPoints = listOf(
                "Tasks go to core threads first, then queue, then max threads",
                "Unbounded queues (e.g. LinkedBlockingQueue without size) mean maxPoolSize is never reached",
                "CallerRunsPolicy acts as a natural backpressure valve by slowing down the caller",
                "AbortPolicy throws RejectedExecutionException (default)",
                "KeepAliveTime determines when idle non-core threads terminate"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_java_009",
            trackId = "java_interview",
            conceptId = "java_concurrency",
            conceptName = "Concurrency & Multithreading",
            title = "ThreadLocal Usage & Memory Leaks in App Servers",
            question = "What is the purpose of ThreadLocal, and how can it cause severe memory leaks in thread-pooled web servers (like Tomcat)?",
            shortAnswer = "ThreadLocal provides thread-isolated variable copies so each thread accesses its own independent state. In servlet containers, worker threads are pooled and reused across many HTTP requests. Because a thread's ThreadLocalMap uses WeakReferences for keys but strong references for values, if ThreadLocal.remove() is not invoked in a finally block, the value remains referenced by the alive thread indefinitely, causing ClassLoader and memory leaks.",
            keyPoints = listOf(
                "ThreadLocal binds isolated state to Thread.currentThread()",
                "Worker threads in thread pools are reused rather than destroyed",
                "ThreadLocalMap keys are weak references; entry values are strong references",
                "Failing to call remove() leaks values and prevents garbage collection",
                "Always wrap ThreadLocal usage in try-finally with remove() in finally block"
            ),
            difficulty = "Senior"
        ),

        // --- Concept 4: Modern Java 8+ to 21 ---
        InterviewQuestion(
            id = "iq_java_010",
            trackId = "java_interview",
            conceptId = "java_modern_features",
            conceptName = "Modern Java (8 to 21 Features)",
            title = "Virtual Threads (Project Loom) in Java 21",
            question = "How do Java 21 Virtual Threads differ from traditional Platform (OS) Threads, and how do they achieve massive throughput for I/O-bound services?",
            shortAnswer = "Platform threads are 1:1 wrappers around OS threads with fixed 1MB stack memory, limiting concurrency to thousands before exhausting OS resources. Virtual threads are lightweight user-mode threads managed by the JVM (M:N mapping). When a virtual thread blocks on socket or file I/O, the JVM unmounts it from the carrier OS thread, parking its continuation on the heap so the OS thread can execute other virtual threads. This enables millions of concurrent connections with synchronous code style.",
            keyPoints = listOf(
                "Platform threads have 1:1 mapping to kernel threads with high memory overhead",
                "Virtual threads are JVM-scheduled lightweight threads with tiny heap footprints",
                "Blocking I/O unmounts virtual thread continuation without blocking carrier thread",
                "Eliminates complex reactive callback pyramids in favor of simple synchronous code",
                "Not intended for CPU-heavy tasks, ideal for network and database I/O"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_java_011",
            trackId = "java_interview",
            conceptId = "java_modern_features",
            conceptName = "Modern Java (8 to 21 Features)",
            title = "Stream API Lazy Evaluation & Short-Circuiting",
            question = "What does it mean that Java Streams are lazy, and how do intermediate operations differ from terminal operations?",
            shortAnswer = "Intermediate operations (filter, map, sorted) return a new Stream and do not execute any computation immediately; they construct an execution pipeline. Computation begins only when a terminal operation (collect, forEach, findFirst, reduce) is invoked. This allows stream optimizations like loop fusion and short-circuiting (e.g. limit(1) or findAny() terminates as soon as the first matching element is processed without evaluating the full dataset).",
            keyPoints = listOf(
                "Intermediate operations are lazy and declarative (pipeline construction)",
                "Terminal operations trigger the actual traversal and produce a non-stream result",
                "Short-circuiting operations (anyMatch, findFirst, limit) stop processing early",
                "Stream pipelines fuse operations into a single traversal pass over the source",
                "Streams cannot be reused once a terminal operation has been executed"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_java_012",
            trackId = "java_interview",
            conceptId = "java_modern_features",
            conceptName = "Modern Java (8 to 21 Features)",
            title = "CompletableFuture Async Orchestration",
            question = "How does CompletableFuture enable non-blocking asynchronous programming in Java? How do thenApply, thenCompose, and thenCombine differ?",
            shortAnswer = "CompletableFuture represents a future result produced asynchronously via ForkJoinPool or custom Executors. thenApply transforms the completed value synchronously (like map). thenCompose flattens another asynchronous stage returning a CompletableFuture (like flatMap, chaining dependent calls). thenCombine waits for two independent CompletableFutures to finish and combines their results with a BiFunction.",
            keyPoints = listOf(
                "Non-blocking callback execution without calling blocking get()",
                "thenApply transforms T to U synchronously",
                "thenCompose chains dependent futures avoiding CompletableFuture<CompletableFuture<T>>",
                "thenCombine joins two concurrent asynchronous computations",
                "exceptionally and handle provide robust asynchronous error recovery"
            ),
            difficulty = "Senior"
        ),

        // --- Concept 5: OOP & Class Loading ---
        InterviewQuestion(
            id = "iq_java_013",
            trackId = "java_interview",
            conceptId = "java_oop_internals",
            conceptName = "OOP & Class Loading Internals",
            title = "Classloader Hierarchy & Delegation Model",
            question = "Explain the Java ClassLoader delegation model. Why does the JVM delegate classloading upwards to parent classloaders first?",
            shortAnswer = "The JVM classloader hierarchy consists of Bootstrap ClassLoader, Platform/Extension ClassLoader, and Application (System) ClassLoader. When asked to load a class, a classloader delegates the request to its parent first before searching its own classpath. This ensures security and consistency: core JDK classes (like java.lang.Object or java.lang.String) are always loaded by the trusted Bootstrap ClassLoader and cannot be hijacked or overridden by malicious application jars.",
            keyPoints = listOf(
                "Three-tier hierarchy: Bootstrap -> Platform -> Application ClassLoader",
                "Parent delegation principle: delegate up before loading locally",
                "Guarantees core JDK classes cannot be replaced by custom code",
                "Two classes are only identical if loaded by the exact same ClassLoader instance",
                "Frameworks (OSGi, Tomcat) create custom classloaders to achieve hot-reloading"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_java_014",
            trackId = "java_interview",
            conceptId = "java_oop_internals",
            conceptName = "OOP & Class Loading Internals",
            title = "Polymorphism & JVM Invocation Bytecode",
            question = "How does the JVM implement runtime polymorphism? Differentiate between invokevirtual and invokestatic.",
            shortAnswer = "Runtime polymorphism is implemented via virtual method tables (vtable) associated with each class in the Method Area. When invokevirtual is executed, the JVM inspects the target object's actual runtime class vtable to resolve the concrete method pointer dynamically (dynamic dispatch). In contrast, invokestatic and invokespecial resolve the target method statically at compile time without vtable lookup, resulting in faster execution.",
            keyPoints = listOf(
                "vtable contains pointers to method implementations for virtual dispatch",
                "invokevirtual checks runtime instance type to execute overridden method",
                "invokestatic calls static methods resolved at compile time",
                "invokespecial calls private methods, constructors (<init>), and super methods",
                "JIT compiler uses monomorphic inlining to optimize virtual calls at runtime"
            ),
            difficulty = "Staff"
        )
    )
}
