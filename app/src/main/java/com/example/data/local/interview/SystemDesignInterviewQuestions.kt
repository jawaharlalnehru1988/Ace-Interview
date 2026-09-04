package com.example.data.local.interview

import com.example.domain.model.InterviewQuestion

object SystemDesignInterviewQuestions {

    fun getQuestions(): List<InterviewQuestion> = listOf(
        // --- Concept 1: Storage, Sharding & Partitioning ---
        InterviewQuestion(
            id = "iq_sys_001",
            trackId = "system_design_interview",
            conceptId = "sys_storage_sharding",
            conceptName = "Storage, Sharding & Partitioning",
            title = "Consistent Hashing & Virtual Nodes",
            question = "How does Consistent Hashing work in distributed databases (e.g. Cassandra, DynamoDB), and how do virtual nodes resolve hot spots?",
            shortAnswer = "Consistent hashing maps both servers and data keys onto a circular hash ring (e.g. 0 to 2^32 - 1). A key is stored on the first server clockwise from its hash position. When a node is added or removed, only k/n keys need migration on average, rather than rehashing the entire keyspace. Virtual nodes (vnodes) assign multiple random positions on the ring to each physical machine, ensuring uniform key distribution across servers with varying hardware capacities and eliminating hot spots during scale events.",
            keyPoints = listOf(
                "Ring topology: keys map to the nearest clockwise server on the hash ring",
                "Rebalancing: only a fraction (1/N) of keys move when nodes join or leave",
                "Virtual nodes (vnodes) map physical servers to hundreds of token positions",
                "Vnodes distribute load proportionally according to server hardware specs",
                "Eliminates cascading thundering herd when a storage node fails"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sys_002",
            trackId = "system_design_interview",
            conceptId = "sys_storage_sharding",
            conceptName = "Storage, Sharding & Partitioning",
            title = "LSM-Trees vs B-Trees for High-Write Workloads",
            question = "Why do write-heavy databases (Cassandra, RocksDB) choose Log-Structured Merge-Trees (LSM) over traditional B-Trees?",
            shortAnswer = "B-Trees perform random in-place updates on disk pages, causing high disk seek latency and write amplification under heavy concurrent writes. LSM-Trees convert all writes into sequential I/O: incoming writes append to an in-memory MemTable and write-ahead log (WAL). When the MemTable is full, it is flushed sequentially to disk as an immutable SSTable. Periodic background compaction merges SSTables to reclaim space. Sequential disk writes allow LSM-Trees to achieve orders-of-magnitude higher write throughput than B-Trees.",
            keyPoints = listOf(
                "B-Trees require random disk page writes and random in-place updates",
                "LSM-Trees append all writes sequentially to memory (MemTable) and disk (WAL)",
                "Immutable SSTables eliminate write locks on existing disk structures",
                "Compaction (Leveled or Size-Tiered) merges SSTables and cleans deleted tombstones",
                "Reads in LSM utilize Bloom filters to avoid probing every SSTable on disk"
            ),
            difficulty = "Staff"
        ),

        // --- Concept 2: Caching & High Throughput ---
        InterviewQuestion(
            id = "iq_sys_003",
            trackId = "system_design_interview",
            conceptId = "sys_caching_throughput",
            conceptName = "Caching & High Throughput",
            title = "Cache-Aside vs Write-Through vs Write-Behind",
            question = "Compare Cache-Aside, Write-Through, and Write-Behind (Write-Back) caching patterns. How do they handle consistency and failure?",
            shortAnswer = "In Cache-Aside (Lazy Loading), the application reads cache first; on miss, it loads from DB and updates cache. Writes update DB then invalidate/evict cache. In Write-Through, the application writes directly to the cache, which synchronously updates the database before returning (high consistency, higher write latency). In Write-Behind (Write-Back), the application writes to the cache, which acknowledges immediately and asynchronously flushes writes in batches to the DB (maximum write throughput, but risks data loss if cache crashes before flush).",
            keyPoints = listOf(
                "Cache-Aside: app queries cache, fetches DB on miss; writes evict cache entry",
                "Write-Through: synchronous write to cache and DB simultaneously",
                "Write-Behind: asynchronous batched write to DB; highest write speed, potential data loss risk",
                "Cache invalidation (delete) is strongly preferred over cache update to avoid race conditions",
                "TTL expiration prevents permanently stale data across cache layers"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_sys_004",
            trackId = "system_design_interview",
            conceptId = "sys_caching_throughput",
            conceptName = "Caching & High Throughput",
            title = "Cache Stampede & Cache Penetration Mitigation",
            question = "Explain Cache Stampede (Thundering Herd) and Cache Penetration. How do you protect a distributed system from them?",
            shortAnswer = "Cache Stampede occurs when a high-traffic cache key expires, causing thousands of concurrent requests to experience a cache miss and simultaneously slam the underlying database. Fixes: 1) Distributed mutex lock (only 1 thread recomputes, others wait or read stale value), 2) Probabilistic early expiration (XFetch). Cache Penetration occurs when attackers query non-existent keys (e.g. id = -1) that are never cached, repeatedly hitting the database. Fixes: 1) Cache null values with short TTL, 2) Place a Bloom Filter in front of the cache to immediately reject non-existent IDs.",
            keyPoints = listOf(
                "Cache Stampede: popular key expires; concurrent threads overload DB",
                "Stampede fixes: distributed lock (Redlock/SETNX) or stale-while-revalidate background refresh",
                "Cache Penetration: malicious queries for non-existent IDs bypassing cache to DB",
                "Penetration fixes: Bloom filters to filter out invalid IDs before hitting cache",
                "Caching null objects with short TTLs prevents repeated empty DB lookups"
            ),
            difficulty = "Senior"
        ),

        // --- Concept 3: Real-World Architecture Blueprints ---
        InterviewQuestion(
            id = "iq_sys_005",
            trackId = "system_design_interview",
            conceptId = "sys_blueprints",
            conceptName = "Real-World Architecture Blueprints",
            title = "Design a URL Shortener (TinyURL)",
            question = "How would you design a highly scalable URL Shortening service (like TinyURL or Bitly)? Explain Base62 encoding and Key Generation Service.",
            shortAnswer = "A URL Shortener maps a long URL to a 7-character short token. 7 Base62 characters ([a-zA-Z0-9]) provide 62^7 ≈ 3.5 trillion unique URLs. Instead of hashing long URLs with MD5/SHA256 (which causes collisions and requires truncation), use an auto-incrementing distributed 64-bit ID generator (or a dedicated Key Generation Service with pre-generated random tokens stored in memory). Convert the numeric ID to Base62. Store mappings in NoSQL (DynamoDB/Cassandra) with high read caching in Redis. Return HTTP 302 (Found) for analytics tracking or 301 (Moved Permanently) for maximum browser caching.",
            keyPoints = listOf(
                "Base62 encoding (a-z, A-Z, 0-9) yields 62^7 = 3.5 trillion unique short links",
                "Distributed ID generator or pre-generated KGS tokens prevent hash collision issues",
                "HTTP 301 redirects cache in browser (low server load); 302 tracks click analytics",
                "Read-heavy system (100:1 read-to-write ratio); use Redis caching cluster for top 20% URLs",
                "Partition database by short URL hash or range-based key generation buckets"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sys_006",
            trackId = "system_design_interview",
            conceptId = "sys_blueprints",
            conceptName = "Real-World Architecture Blueprints",
            title = "Design a Real-Time Chat System (WhatsApp / Slack)",
            question = "How do you design a real-time messaging platform supporting 1-on-1 and group chats? How do clients stay connected and receive messages?",
            shortAnswer = "Clients maintain persistent bi-directional WebSocket connections to a distributed pool of Chat Gateway servers. When User A sends a message to User B: 1) Gateway writes message to message store (Cassandra or HBase for high write throughput and time-ordered pagination), 2) Gateway queries a Redis User Presence / Session Service to find which Gateway server holds User B's active WebSocket connection, 3) The message is routed via Redis Pub/Sub or Kafka topic to User B's Gateway server, which pushes it over the WebSocket. If User B is offline, an asynchronous Notification Service sends push notifications via APNS/FCM.",
            keyPoints = listOf(
                "Persistent WebSocket connections for bi-directional low-latency messaging",
                "User Session / Presence Service in Redis maps userId to gatewayServerId",
                "Inter-gateway message dispatch via Redis Pub/Sub channels or Kafka topics",
                "LSM-based storage (Cassandra/ScyllaDB) for high-volume time-series message history",
                "Offline push notification pipelines (APNS/FCM) triggered on recipient absence"
            ),
            difficulty = "Staff"
        )
    )
}
