package com.example.data.local.interview

import com.example.domain.model.InterviewQuestion

object SqlInterviewQuestions {

    fun getQuestions(): List<InterviewQuestion> = listOf(
        // --- Concept 1: Indexing & Query Optimization ---
        InterviewQuestion(
            id = "iq_sql_001",
            trackId = "sql_interview",
            conceptId = "sql_indexing",
            conceptName = "Indexing & Query Optimization",
            title = "B-Tree vs Hash Indexes & Covering Indexes",
            question = "How does a B-Tree index work in relational databases (PostgreSQL, MySQL InnoDB)? What is a Covering Index and why is it faster?",
            shortAnswer = "A B-Tree index maintains a balanced, multi-level tree of sorted keys where leaf nodes are linked sequentially. This supports fast point lookups (O(log n)), range queries (BETWEEN, >, <), and ORDER BY operations. A Covering Index (Index-Only Scan) includes all columns requested in the SELECT clause (via composite keys or INCLUDE clause). Because the database satisfies the entire query directly from the index leaves in memory without probing the underlying table data pages (eliminating table heap lookups), it executes significantly faster.",
            keyPoints = listOf(
                "B-Tree keeps keys sorted; enables both exact equality and range/prefix searches",
                "Hash indexes only support equality lookups (=, IN), not range scans (<, >)",
                "Covering Index satisfies SELECT, WHERE, and ORDER BY columns from index alone",
                "Eliminates expensive random disk reads to table data pages (table lookups)",
                "Leftmost prefix rule applies: composite index (A, B, C) works for queries on A or (A, B)"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_sql_002",
            trackId = "sql_interview",
            conceptId = "sql_indexing",
            conceptName = "Indexing & Query Optimization",
            title = "Clustered vs Non-Clustered Indexes",
            question = "Explain the difference between a Clustered and a Non-Clustered index in MySQL InnoDB. What is the bookmark lookup overhead?",
            shortAnswer = "In InnoDB, a Clustered Index (always the PRIMARY KEY) dictates the physical ordering of table rows on disk; its leaf nodes contain the actual full row data. A Non-Clustered (Secondary) index maintains sorted index columns, but its leaf nodes store only the primary key value (not a disk pointer). When a secondary index query needs columns not in the index, it performs a 'bookmark lookup': looking up the primary key in the clustered index to fetch the full row, adding secondary index navigation overhead.",
            keyPoints = listOf(
                "Clustered index leaf nodes contain the actual complete row data (1 per table)",
                "Non-clustered (secondary) index leaf nodes store index keys + Primary Key",
                "Bookmark lookup: secondary index -> primary key -> clustered index traverse -> row data",
                "Keep primary keys small and sequential (e.g. BIGINT auto_increment) to keep secondary indexes compact",
                "Avoid UUID primary keys in high-write tables to prevent heavy clustered index page splits"
            ),
            difficulty = "Senior"
        ),

        // --- Concept 2: ACID Transactions & Concurrency ---
        InterviewQuestion(
            id = "iq_sql_003",
            trackId = "sql_interview",
            conceptId = "sql_transactions",
            conceptName = "ACID Transactions & Concurrency",
            title = "SQL Isolation Levels & Concurrency Anomalies",
            question = "Compare the 4 standard SQL isolation levels: Read Uncommitted, Read Committed, Repeatable Read, and Serializable. What anomalies does each prevent?",
            shortAnswer = "1) Read Uncommitted: permits Dirty Reads (reading uncommitted changes from other transactions). 2) Read Committed: prevents Dirty Reads, but permits Non-Repeatable Reads (re-reading a row returns updated values). 3) Repeatable Read (MySQL default): prevents Dirty and Non-Repeatable Reads via MVCC read views, but can permit Phantom Reads (new matching rows inserted by other transactions). 4) Serializable: prevents all anomalies (Dirty, Non-Repeatable, and Phantom Reads) by emulating serial execution using range/predicate locks or two-phase locking.",
            keyPoints = listOf(
                "Dirty Read: reading uncommitted data that may later be rolled back",
                "Non-Repeatable Read: reading the same row twice returns differing column values",
                "Phantom Read: re-running a range query returns newly inserted/deleted rows",
                "Repeatable Read in MySQL uses MVCC snapshot reads and Next-Key Locking",
                "Higher isolation levels reduce concurrency and increase lock contention/deadlocks"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_004",
            trackId = "sql_interview",
            conceptId = "sql_transactions",
            conceptName = "ACID Transactions & Concurrency",
            title = "Multi-Version Concurrency Control (MVCC)",
            question = "How does MVCC allow concurrent reads and writes without blocking in PostgreSQL and MySQL InnoDB?",
            shortAnswer = "MVCC eliminates read-write lock contention by ensuring 'readers never block writers, and writers never block readers.' Instead of locking rows on update, the database maintains multiple historical versions of each row. When a transaction performs a SELECT, it sees a consistent snapshot of the data matching its transaction start time (based on transaction IDs and undo logs). Updates create a new row version with the current transaction ID; older versions are reclaimed later by PostgreSQL VACUUM or MySQL Undo log purge.",
            keyPoints = listOf(
                "Readers never block writers; writers never block readers",
                "Each row has system hidden columns (created_tx_id, deleted_tx_id / roll_ptr)",
                "Read view determines which row versions are visible to a transaction snapshot",
                "Undo log in InnoDB allows reconstructing historical row states on demand",
                "Background vacuum/purge processes reclaim space from obsolete dead tuples"
            ),
            difficulty = "Staff"
        )
    )
}
