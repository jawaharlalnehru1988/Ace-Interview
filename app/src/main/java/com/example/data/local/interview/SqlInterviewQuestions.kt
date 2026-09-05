package com.example.data.local.interview

import com.example.domain.model.InterviewQuestion

object SqlInterviewQuestions {

    fun getQuestions(): List<InterviewQuestion> =
        part1() + part2() + part3() + part4() + part5() + part6() + part7() + part8() + part9()

    private fun part1(): List<InterviewQuestion> = listOf(
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
        InterviewQuestion(
            id = "iq_sql_003",
            trackId = "sql_interview",
            conceptId = "sql_indexing",
            conceptName = "Indexing & Query Optimization",
            title = "B-Tree vs B+ Tree Structural Architecture",
            question = "Why do relational database engines almost universally implement B+ Trees rather than standard B-Trees for table indexes?",
            shortAnswer = "In a standard B-Tree, every node (root, internal, and leaf) stores both keys and record pointers/payloads. In a B+ Tree, internal nodes ONLY store keys and child page pointers, while ALL actual data/row pointers reside strictly in the leaf nodes, which are connected via a doubly-linked list. Benefits: 1) Higher Fanout: Internal pages hold far more keys because they have no data payloads, resulting in a much flatter tree (typically depth 3-4 for millions of rows) requiring fewer disk I/Os. 2) Sequential Range Scans: Once the start key is located, range scans simply traverse the leaf linked list sequentially without backtracking up and down tree levels. 3) Predictable O(log N) lookup latency.",
            keyPoints = listOf(
                "B+ Tree internal nodes hold only keys and child page pointers, maximizing fanout",
                "Flatter tree depth (3-4 levels) drastically minimizes disk page reads per lookup",
                "Leaf nodes are linked in a bidirectional chain, making range scans and ORDER BY efficient",
                "Standard B-Tree internal nodes hold data payloads, reducing the branching factor",
                "Predictable constant search depth since all record pointers reside at leaf level"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_004",
            trackId = "sql_interview",
            conceptId = "sql_indexing",
            conceptName = "Indexing & Query Optimization",
            title = "Composite Index Leftmost Prefix Rule and Column Ordering",
            question = "Given a composite index on (tenant_id, status, created_at), explain how the query planner uses it. What happens when a range filter is applied to the middle column?",
            shortAnswer = "A composite B-Tree sorts records by the first column, then by the second within equal first values, and so forth. The query planner can use the index for queries filtering on: 1) tenant_id, 2) (tenant_id, status), or 3) (tenant_id, status, created_at). It CANNOT use the index efficiently for status alone or created_at alone because they are not sorted globally. If a range condition (<, >, BETWEEN) is applied to status (e.g. WHERE tenant_id = 5 AND status > 2 AND created_at > '2024-01-01'), the B-Tree can seek to the range start for status, but cannot use the index ordering for created_at because across differing status values, created_at is not sorted. Rule: Place high-cardinality equality columns first, followed by range/sorting columns.",
            keyPoints = listOf(
                "Composite index sorting is strictly hierarchical based on column declaration order",
                "Queries must match the leftmost prefix of the composite index to leverage index seeks",
                "A range condition on an intermediate column stops further B-Tree index seek matching",
                "Columns after a range predicate can only be evaluated via Index Condition Pushdown (ICP) filtering",
                "Design composite indexes with equality filters first, range/ordering filters last"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_sql_005",
            trackId = "sql_interview",
            conceptId = "sql_indexing",
            conceptName = "Indexing & Query Optimization",
            title = "Covering Indexes and the INCLUDE Clause in PostgreSQL",
            question = "What is the difference between adding a column to a composite index key vs using the PostgreSQL INCLUDE clause? When should each be used?",
            shortAnswer = "In a standard composite index `CREATE INDEX idx ON orders (user_id, status)`, both columns are stored in the internal B-Tree navigation nodes and participate in tree sorting and uniqueness constraints. With `CREATE INDEX idx ON orders (user_id) INCLUDE (status)`, `user_id` is the search key in all tree levels, while `status` is stored ONLY as non-key payload in the leaf pages. Benefits of INCLUDE: 1) Keeps internal nodes compact, maintaining maximum fanout and shallow tree depth. 2) Allows covering index benefits without imposing uniqueness constraints on the included column in unique indexes. 3) Saves index maintenance overhead when the included column is never used in WHERE or ORDER BY clauses.",
            keyPoints = listOf(
                "Keys in composite index participate in sorting and B-Tree internal navigation",
                "INCLUDE columns exist only in leaf pages as non-key payload data",
                "INCLUDE maintains higher internal page fanout and flatter B-Tree depth",
                "Allows creating unique indexes on primary attributes while covering extra query columns",
                "Included columns cannot be used for WHERE range filtering or ORDER BY sorting"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_006",
            trackId = "sql_interview",
            conceptId = "sql_indexing",
            conceptName = "Indexing & Query Optimization",
            title = "Index Condition Pushdown (ICP) in MySQL",
            question = "What is Index Condition Pushdown (ICP) in MySQL and MariaDB, and how does it reduce disk I/O and storage engine roundtrips?",
            shortAnswer = "Without ICP, the storage engine reads the index record, uses the primary key to fetch the full row from the clustered index table page (table lookup), and passes the full row up to the MySQL server layer to evaluate the remaining WHERE conditions. With ICP enabled (default since MySQL 5.6), the server pushes the evaluation of WHERE conditions that involve index columns down into the storage engine itself. The storage engine checks if the index tuple satisfies the condition BEFORE performing the expensive table lookup. If the condition fails, the row is discarded immediately, eliminating unnecessary table page disk I/O.",
            keyPoints = listOf(
                "Without ICP, every secondary index match triggers a full clustered index row lookup",
                "ICP pushes applicable WHERE clause predicates directly into the storage engine layer",
                "Storage engine evaluates index columns before reading the full table row from disk",
                "Drastically reduces table page disk I/O and buffer pool cache thrashing",
                "Visible in EXPLAIN output as 'Using index condition'"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_007",
            trackId = "sql_interview",
            conceptId = "sql_indexing",
            conceptName = "Indexing & Query Optimization",
            title = "Partial (Filtered) Indexes vs Full Table Indexes",
            question = "What is a Partial Index in PostgreSQL and how does it improve query performance and write throughput compared to standard indexes?",
            shortAnswer = "A Partial Index (`CREATE INDEX idx ON orders (created_at) WHERE status = 'PENDING'`) indexes only rows matching the specified WHERE predicate. Benefits: 1) Massive Space Savings: In skewed tables where 98% of rows are 'COMPLETED' and 2% are 'PENDING', the partial index is 98% smaller than a full index, fitting entirely in RAM buffer cache. 2) Zero Write Overhead on Most Updates: When a row is inserted or updated with status != 'PENDING', the engine completely skips modifying the partial index. 3) Faster Scans: The query planner matches the partial index for queries containing the same or a subset WHERE filter, scanning a fraction of pages.",
            keyPoints = listOf(
                "Partial index stores index entries only for rows satisfying a static WHERE predicate",
                "Drastically smaller index size that easily fits in RAM buffer pool",
                "Eliminates index maintenance overhead for rows not matching the condition",
                "Ideal for low-cardinality flags, soft-delete active records, or unprocessed task queues",
                "Query planner uses partial index only when query WHERE clause guarantees predicate match"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_sql_008",
            trackId = "sql_interview",
            conceptId = "sql_indexing",
            conceptName = "Indexing & Query Optimization",
            title = "Functional (Expression-Based) Indexes",
            question = "Why doesn't a standard index on `email` get used by `WHERE LOWER(email) = 'test@example.com'`, and how do Expression Indexes solve this?",
            shortAnswer = "A standard B-Tree index on `email` stores and sorts raw string values. Wrapping the column in a function like `LOWER(email)` creates a non-sargable (Search Argument Able) expression: the database cannot know which raw string equals the lowercase target without executing `LOWER()` on every row, forcing a full table scan. An Expression Index (`CREATE INDEX idx_lower_email ON users (LOWER(email))`) evaluates the function during INSERT/UPDATE and stores the computed result directly in the B-Tree index. The optimizer recognizes the matching expression and performs an index seek.",
            keyPoints = listOf(
                "Function calls on indexed columns prevent B-Tree seek operations (non-sargable query)",
                "Database cannot deduce pre-function values without evaluating every table row",
                "Expression index precomputes and stores the function result inside the B-Tree leaves",
                "Query optimizer automatically substitutes the expression index when matching AST nodes",
                "Incurs CPU and write overhead on INSERT/UPDATE to evaluate the expression"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_sql_009",
            trackId = "sql_interview",
            conceptId = "sql_indexing",
            conceptName = "Indexing & Query Optimization",
            title = "Index Selectivity, Cardinality and Optimizer Choices",
            question = "What is index selectivity, and why does a query optimizer often choose a Full Table Scan over an index on a boolean column like `is_active`?",
            shortAnswer = "Index Selectivity is the ratio of distinct values to total rows (`cardinality / total_rows`), ranging from 0 (all identical) to 1 (unique). High selectivity (near 1, like user_id) returns very few rows per lookup; low selectivity (like boolean `is_active`) returns a large percentage of the table. If a query matches 30% of table rows, an index scan would perform thousands of random I/O bookmark lookups. A Sequential Table Scan reads contiguous disk blocks sequentially (leveraging OS read-ahead and high throughput) in a single pass. When the cost of random I/O exceeds sequential I/O, the optimizer deliberately abandons the index.",
            keyPoints = listOf(
                "Selectivity = distinct values / total rows; determines filtering efficiency",
                "Low selectivity indexes (status, boolean flags) return massive percentages of table rows",
                "Index scan on low selectivity columns results in thousands of random I/O table lookups",
                "Full Table Scan uses efficient sequential disk read-ahead, outperforming random lookups",
                "The tipping point where optimizer switches from index to table scan is typically 5-20% of rows"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_sql_010",
            trackId = "sql_interview",
            conceptId = "sql_indexing",
            conceptName = "Indexing & Query Optimization",
            title = "GIN (Generalized Inverted Index) vs GiST in PostgreSQL",
            question = "Compare GIN and GiST indexes in PostgreSQL. What workloads and data types is each optimized for?",
            shortAnswer = "1) GIN (Generalized Inverted Index): Maps individual internal elements (words, array elements, JSON keys/values) to lists of row IDs (posting lists/trees) where they appear. Optimized for read-heavy multi-value lookups: full-text search (`tsvector`), JSONB containment (`@>`), and array overlap (`&&`). GIN writes are slower because one row update may touch many inverted keys, mitigated by `fastupdate` pending buffers. 2) GiST (Generalized Search Tree): A balanced tree framework that represents hierarchical bounding boxes (R-Trees). Optimized for geometric/spatial data (PostGIS polygons, points, distance `<->`), range types (`tsrange` overlaps `&&`), and nearest-neighbor KNN searches.",
            keyPoints = listOf(
                "GIN is an inverted index mapping elements/words to posting lists of matching row IDs",
                "GIN excels in JSONB containment, full-text search, and array containment queries",
                "GIN has higher write amplification; uses fastupdate buffer to batch leaf updates",
                "GiST is a lossy/balanced tree framework ideal for geometric bounding boxes and ranges",
                "GiST supports nearest-neighbor (KNN) distance operators and PostGIS spatial queries"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_011",
            trackId = "sql_interview",
            conceptId = "sql_indexing",
            conceptName = "Indexing & Query Optimization",
            title = "BRIN (Block Range Index) for Massive Datasets",
            question = "What is a BRIN index in PostgreSQL, and why is it hundreds of times smaller than a B-Tree for append-only time-series tables?",
            shortAnswer = "A BRIN (Block Range Index) divides the table into contiguous physical disk page ranges (default 128 pages = 1MB). For each block range, it stores only the MIN and MAX value of the indexed column. When a query runs (e.g. `WHERE timestamp BETWEEN A AND B`), PostgreSQL checks the BRIN index to skip entire block ranges whose [min, max] does not overlap the query range, reading only relevant physical blocks. Because it stores only 2 values per 128 pages instead of an entry for every single row, a BRIN index on a 100GB table can be just a few megabytes. Prerequisite: Data must be physically clustered or correlated with insert order (like autoincrement IDs or timestamps).",
            keyPoints = listOf(
                "BRIN stores only min and max values for physical ranges of table pages (default 128 pages)",
                "Extremely compact storage footprint (often 100x to 1000x smaller than B-Tree)",
                "Allows optimizer to skip reading non-overlapping physical disk block ranges entirely",
                "Requires physical table data to be naturally correlated with column value (e.g. append-only timestamps)",
                "Poor performance on randomly updated or unordered tables where min/max ranges overlap"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_012",
            trackId = "sql_interview",
            conceptId = "sql_indexing",
            conceptName = "Indexing & Query Optimization",
            title = "B-Tree Page Splits and Index Fragmentation",
            question = "What causes B-Tree page splits in relational databases? How do random UUID primary keys exacerbate index fragmentation?",
            shortAnswer = "A B-Tree page has fixed size (16KB in InnoDB, 8KB in Postgres). When a new key must be inserted into a page that is 100% full, the engine cannot simply shift bytes. It allocates a new page, moves roughly 50% of the records to the new page (a 'page split'), and updates parent navigation pointers. When using sequential keys (BIGINT auto-increment), new keys always append to the rightmost leaf page, filling pages to 100% without splitting existing pages. With random UUIDs (UUIDv4), insertions land randomly across the entire tree. This triggers frequent page splits throughout all pages, leaving them half-empty (50% fill rate), doubling disk consumption and destroying cache locality.",
            keyPoints = listOf(
                "Page split occurs when inserting a key into an already full B-Tree leaf page",
                "Database allocates new page and moves half the records, generating heavy disk I/O and locks",
                "Sequential keys append to rightmost page cleanly, avoiding mid-tree splits",
                "Random UUIDv4 inserts scatter across random pages, causing massive page splits",
                "Results in internal index fragmentation, 50% wasted space, and bloated buffer pool memory"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_013",
            trackId = "sql_interview",
            conceptId = "sql_indexing",
            conceptName = "Indexing & Query Optimization",
            title = "Sargable vs Non-Sargable Query Predicates",
            question = "What makes a SQL query predicate 'sargable' (Search Argument Able)? Provide three common non-sargable query anti-patterns and their optimized fixes.",
            shortAnswer = "A predicate is Sargable if the database engine can evaluate it using a B-Tree index seek rather than scanning every row. Common Non-Sargable Anti-Patterns and Fixes: 1) Function wrapping: `WHERE YEAR(created_at) = 2024` -> Fix: `WHERE created_at >= '2024-01-01' AND created_at < '2025-01-01'`. 2) Leading wildcards: `WHERE username LIKE '%john'` -> Fix: Cannot use B-Tree; use full-text search, trigram `pg_trgm` GIN index, or reverse index if trailing. 3) Arithmetic on column: `WHERE price * 1.2 > 100` -> Fix: Isolate column on one side: `WHERE price > 100 / 1.2`. 4) Implicit type coercion: `WHERE string_code = 123` forces database to cast every row.",
            keyPoints = listOf(
                "Sargable predicates enable direct B-Tree index range and point seek operations",
                "Function wrappers on columns force full table scans; fix by shifting logic to the literal",
                "Leading wildcards (LIKE '%abc') cannot leverage B-Tree prefix sorting",
                "Arithmetic expressions must isolate the column: `price > 100 / 1.2`",
                "Implicit type casting between strings and integers invalidates index scans"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_sql_014",
            trackId = "sql_interview",
            conceptId = "sql_indexing",
            conceptName = "Indexing & Query Optimization",
            title = "Interpreting EXPLAIN and EXPLAIN ANALYZE Output",
            question = "Walk through the key metrics in `EXPLAIN ANALYZE` (PostgreSQL/MySQL): Cost, Actual Time, Rows, Loops, and Buffers. How do you spot bad query plans?",
            shortAnswer = "`EXPLAIN` shows the planner's estimates; `EXPLAIN ANALYZE` actually executes the query and reports runtime statistics: 1) `Cost (e.g. 0.00..450.25)`: Planner's estimated start cost and total cost in arbitrary I/O units. 2) `Actual Time (0.045..12.350 ms)`: Time to fetch first row and all rows. 3) `Rows estimated vs Rows actual`: Compare `rows=10` with `actual rows=50000`. A massive disparity indicates stale table statistics (`ANALYZE` needed), leading to suboptimal join algorithm selection. 4) `Loops=N`: The operation executed N times; multiply actual time and actual rows by N to find true totals. 5) `Buffers: shared hit=400 read=1200`: `read` = disk I/O, `hit` = RAM buffer pool hit. Red flags: high disk reads, nested loop joins on wrong cardinality estimates.",
            keyPoints = listOf(
                "EXPLAIN shows planner estimates; EXPLAIN ANALYZE executes query and measures actual runtime",
                "Large discrepancy between estimated rows and actual rows indicates stale statistics",
                "Loops multiplier: actual rows and actual time per node must be multiplied by loops count",
                "Buffers (shared read vs shared hit) identifies whether query hit disk or RAM cache",
                "Red flags: high sequential scans on large tables, disk-spilling hash joins, inaccurate cardinalities"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_015",
            trackId = "sql_interview",
            conceptId = "sql_indexing",
            conceptName = "Indexing & Query Optimization",
            title = "Bitmap Index Scan vs Index Scan vs Sequential Scan in PostgreSQL",
            question = "How does PostgreSQL choose between an Index Scan, a Bitmap Index Scan, and a Sequential Scan?",
            shortAnswer = "1) Index Scan: Traverses the B-Tree to find a tuple pointer and immediately visits the table heap page to retrieve the row (interleaved random I/O). Ideal when fetching very few rows (< 1-2% of table). 2) Bitmap Index Scan: Used when matching a moderate number of rows (e.g. 5-15%) or combining multiple indexes (OR/AND). Phase 1 (BitmapIndexScan) scans the index and builds an in-memory bitmap of physical table pages containing matching tuples. If multiple indexes are used, it performs bitwise AND/OR on the bitmaps. Phase 2 (BitmapHeapScan) reads the table heap pages in strict physical disk order, converting random I/O into sequential I/O and reading each page only once. 3) Sequential Scan: Reads all heap pages sequentially; fastest when matching > 20% of rows.",
            keyPoints = listOf(
                "Index Scan fetches rows immediately from heap per index hit; excels for tiny row counts",
                "Bitmap Index Scan constructs an in-memory page bitmap before touching the table heap",
                "BitmapHeapScan reads pages in physical disk order, eliminating redundant page visits and random I/O",
                "Enables combining multiple separate single-column indexes using bitwise AND/OR operations",
                "Sequential Scan reads contiguous physical storage; chosen for high row percentage matches"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_016",
            trackId = "sql_interview",
            conceptId = "sql_indexing",
            conceptName = "Indexing & Query Optimization",
            title = "Why Foreign Keys Require Indexes on Child Tables",
            question = "Why is it a critical best practice to index foreign key columns in child tables? What happens during parent row DELETES if the child FK is unindexed?",
            shortAnswer = "When creating a foreign key `order_items(order_id) REFERENCES orders(id)`, the database does NOT automatically create an index on `order_items.order_id` (Postgres and Oracle do not; MySQL InnoDB does). If unindexed: 1) Child Deletion/Update Cascades: Deleting an `orders` row requires verifying if any child `order_items` exist. Without an index, the engine must perform a FULL TABLE SCAN on `order_items`. 2) Massive Lock Escalation: In PostgreSQL, an unindexed FK check during parent delete can place share locks on large portions of the child table, serializing concurrent inserts and causing deadlocks. 3) Joins: Joining parent and child tables on the FK will suffer full table scans on the child.",
            keyPoints = listOf(
                "Most database engines (Postgres, Oracle) do not automatically index foreign key columns",
                "Parent row DELETE/UPDATE must verify referential integrity on the child table",
                "Unindexed FK forces a full table scan on the child table for every parent row deleted",
                "Acquires broad table-level share locks, stalling concurrent child inserts and causing deadlocks",
                "Joining parent and child tables on FK performs full table scans without an index"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_sql_017",
            trackId = "sql_interview",
            conceptId = "sql_indexing",
            conceptName = "Indexing & Query Optimization",
            title = "Detecting and Pruning Redundant or Unused Indexes",
            question = "How do redundant and unused indexes degrade database performance, and how do you detect them in PostgreSQL and MySQL?",
            shortAnswer = "Every index on a table incurs write amplification: every INSERT requires writing to table heap + all N indexes; every UPDATE modifying indexed columns rewrites index pages, increasing WAL generation and dirty page flushing. Redundant indexes occur when index A is `(user_id, created_at)` and index B is `(user_id)`—index B is 100% redundant because index A satisfies all queries on `user_id`. Detection: In PostgreSQL, query `pg_stat_user_indexes` (check `idx_scan = 0` for unused indexes). In MySQL, query `sys.schema_unused_indexes` and `sys.schema_redundant_indexes`. Drop unused indexes to reclaim buffer pool RAM and accelerate write throughput.",
            keyPoints = listOf(
                "Every secondary index adds write overhead to INSERT, UPDATE, and DELETE operations",
                "Increases WAL volume, checkpoint write stalls, and buffer pool memory consumption",
                "An index on (A, B) makes an index on (A) completely redundant via leftmost prefix",
                "PostgreSQL detection: inspect `pg_stat_user_indexes` for zero `idx_scan` counts",
                "MySQL detection: inspect `sys.schema_unused_indexes` and `sys.schema_redundant_indexes`"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_018",
            trackId = "sql_interview",
            conceptId = "sql_indexing",
            conceptName = "Indexing & Query Optimization",
            title = "Index Fill Factor and PCTFREE Tuning",
            question = "What is the purpose of configuring `FILLFACTOR` (PostgreSQL) or `PCTFREE` (Oracle) on indexes and tables with high update concurrency?",
            shortAnswer = "By default, B-Tree leaf pages are packed to 100% (or 90%). When an existing row is updated in PostgreSQL, MVCC must create a new row version (tuple). If `FILLFACTOR` on the table is set to 80 (leaving 20% free space per page), PostgreSQL can store the new tuple inside the SAME physical data page as the old tuple. This enables HOT (Heap-Only Tuples) optimization: the table pointers do not change, meaning ZERO secondary indexes need to be updated. On indexes, leaving free space (e.g. fillfactor 85) allows non-sequential inserts to fit into leaf pages without triggering expensive B-Tree page splits.",
            keyPoints = listOf(
                "FILLFACTOR sets percentage of page space filled during initial population, reserving headroom",
                "Table fillfactor headroom enables HOT (Heap-Only Tuples) optimization in PostgreSQL",
                "HOT allows row updates to stay on the same page, avoiding updates to all secondary indexes",
                "Index fillfactor reserves space in B-Tree leaves to absorb random inserts without page splits",
                "Trade-off: slightly larger physical table size and more disk pages to scan"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_019",
            trackId = "sql_interview",
            conceptId = "sql_indexing",
            conceptName = "Indexing & Query Optimization",
            title = "Trigram Indexes (pg_trgm) for Fuzzy and Wildcard Searches",
            question = "How does a Trigram index (`pg_trgm` extension in PostgreSQL) enable B-Tree-defying wildcard searches like `LIKE '%pattern%'`?",
            shortAnswer = "A trigram is a group of three consecutive characters taken from a string (e.g., 'word' produces `  w`, ` wo`, `wor`, `ord`, `rd `). The `pg_trgm` extension extracts all trigrams from text and indexes them using a GIN or GiST index. When a wildcard query executes (`WHERE name LIKE '%apple%'`), Postgres breaks 'apple' into its constituent trigrams (`  a`, ` ap`, `app`, `ppl`, `ple`, `le `) and queries the GIN inverted index to find rows containing those exact trigrams. It then evaluates the remaining candidates. This converts an impossible full-table scan into an efficient index lookup, also enabling fuzzy distance searches (`%` similarity operator).",
            keyPoints = listOf(
                "Trigram decomposes strings into contiguous 3-character substrings",
                "Uses GIN or GiST inverted indexes to map each trigram to matching row IDs",
                "Enables index seeks for arbitrary substring patterns (`LIKE '%xyz%'` and regex)",
                "Supports fuzzy string matching, similarity scoring (`similarity(a, b)`), and typo tolerance",
                "Higher index creation time and storage footprint compared to standard B-Trees"
            ),
            difficulty = "Senior"
        )
    )

    private fun part2(): List<InterviewQuestion> = listOf(
        InterviewQuestion(
            id = "iq_sql_020",
            trackId = "sql_interview",
            conceptId = "sql_indexing",
            conceptName = "Indexing & Query Optimization",
            title = "Zero-Downtime Index Creation in Production",
            question = "What locks does `CREATE INDEX` acquire by default in PostgreSQL and MySQL, and how do you create indexes in production without blocking live writes?",
            shortAnswer = "A default `CREATE INDEX` acquires an `AccessExclusiveLock` (PostgreSQL) or table write lock during build phases, blocking all concurrent INSERT, UPDATE, and DELETE queries for the entire duration of the build (hours on large tables). Solution: 1) PostgreSQL: Use `CREATE INDEX CONCURRENTLY idx_name ON table(col)`. This builds the index in two passes without taking an exclusive lock, allowing uninterrupted live writes. If it fails, it leaves an `INVALID` index that must be dropped and rebuilt. 2) MySQL (InnoDB): Use Online DDL (`ALGORITHM=INPLACE, LOCK=NONE`). InnoDB builds the index in-place while logging concurrent DML into an online alter log buffer, applying changes at the end with an instantaneous metadata lock.",
            keyPoints = listOf(
                "Default CREATE INDEX acquires exclusive locks that block live write transactions",
                "PostgreSQL solution: `CREATE INDEX CONCURRENTLY` runs in multiple passes without blocking writes",
                "Concurrent index creation takes longer and requires monitoring for INVALID index states",
                "MySQL solution: Online DDL with `ALGORITHM=INPLACE, LOCK=NONE` buffers concurrent DML",
                "Always set statement timeouts and check for active long-running transactions before running"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_021",
            trackId = "sql_interview",
            conceptId = "sql_transactions",
            conceptName = "ACID Transactions & Concurrency",
            title = "SQL Isolation Levels & Concurrency Anomalies",
            question = "Compare the 4 standard ANSI SQL isolation levels: Read Uncommitted, Read Committed, Repeatable Read, and Serializable. What anomalies does each prevent?",
            shortAnswer = "1) Read Uncommitted: permits Dirty Reads (reading uncommitted changes from concurrent transactions that may roll back). 2) Read Committed: prevents Dirty Reads, but permits Non-Repeatable Reads (re-reading the same row returns updated values). 3) Repeatable Read (MySQL InnoDB default): prevents Dirty and Non-Repeatable Reads via MVCC read views, but can permit Phantom Reads in ANSI definitions (new matching rows inserted). In InnoDB, Next-Key Locking also prevents phantoms for locking reads. 4) Serializable: prevents all anomalies (Dirty, Non-Repeatable, Phantom Reads, and Write Skew) by emulating serial execution using range/predicate locks or Serializable Snapshot Isolation (SSI).",
            keyPoints = listOf(
                "Dirty Read: reading uncommitted data that may subsequently roll back",
                "Non-Repeatable Read: re-reading the same row returns modified values committed by another tx",
                "Phantom Read: re-running a range query returns newly inserted rows matching the filter",
                "Repeatable Read in MySQL uses MVCC snapshot reads and Next-Key Locking to stop phantoms",
                "Higher isolation levels reduce concurrency and increase lock contention and deadlocks"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_022",
            trackId = "sql_interview",
            conceptId = "sql_transactions",
            conceptName = "ACID Transactions & Concurrency",
            title = "Multi-Version Concurrency Control (MVCC) Mechanics",
            question = "How does MVCC allow concurrent reads and writes without blocking in PostgreSQL and MySQL InnoDB? Compare their physical storage implementations.",
            shortAnswer = "MVCC guarantees that 'readers never block writers, and writers never block readers.' Instead of in-place row overwrites with exclusive locks, the database maintains multiple historical row versions: 1) PostgreSQL: Append-only tuple storage. Updates insert an entire new row version into the table heap with its creation transaction ID (xmin). Older row versions (with xmax set) remain in the heap until reclaimed by background VACUUM. 2) MySQL InnoDB: In-place update with Undo Log. The latest row version is kept in the clustered index data page. Modifications copy the previous state into the Undo Log segment, chained via roll_ptr. A snapshot SELECT reads the latest page and traverses the undo log chain backwards until finding a version matching its Read View.",
            keyPoints = listOf(
                "Core principle: readers never block writers, and writers never block readers",
                "PostgreSQL stores new versions directly in table heap (xmin/xmax), requiring VACUUM cleanup",
                "MySQL InnoDB updates in-place on clustered page and writes previous row states to Undo Log",
                "InnoDB snapshot reads traverse undo log chain backwards via roll_ptr to match Read View",
                "Eliminates shared read locks for SELECT queries, drastically boosting concurrent throughput"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_023",
            trackId = "sql_interview",
            conceptId = "sql_transactions",
            conceptName = "ACID Transactions & Concurrency",
            title = "The Write Skew Anomaly under Snapshot Isolation",
            question = "What is the Write Skew anomaly under Snapshot Isolation (Repeatable Read)? Illustrate with the classic 'Doctors on Call' problem.",
            shortAnswer = "Write Skew occurs under Snapshot Isolation when two concurrent transactions read overlapping data sets, make decisions based on that read data, and write to disjoint rows in a way that violates a global integrity constraint. Classic Example (Doctors on Call): Invariant: 'At least one doctor must be on call at all times.' Currently, Dr. Alice and Dr. Bob are on call (count = 2). Alice starts Tx 1, checks count (2 >= 1), and takes leave by updating her row. Concurrently, Bob starts Tx 2, checks count (2 >= 1 under his snapshot), and takes leave by updating his row. Both transactions commit successfully under Repeatable Read because they modified different rows, but the invariant is violated (zero doctors on call). Prevention requires `SELECT ... FOR UPDATE` or Serializable isolation.",
            keyPoints = listOf(
                "Write Skew occurs when concurrent transactions read overlapping data but write disjoint rows",
                "Repeatable Read cannot prevent Write Skew because neither transaction modifies the other's row",
                "Both transactions commit successfully, violating a cross-row business invariant",
                "Classic doctor-on-call example shows invariant failure without row lock collision",
                "Prevented via `SELECT ... FOR UPDATE` on both rows or moving to Serializable isolation"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_024",
            trackId = "sql_interview",
            conceptId = "sql_transactions",
            conceptName = "ACID Transactions & Concurrency",
            title = "Serializable Snapshot Isolation (SSI) vs Strict Two-Phase Locking",
            question = "How does PostgreSQL's Serializable Snapshot Isolation (SSI) achieve serializability without the severe blocking overhead of Strict Two-Phase Locking (S2PL)?",
            shortAnswer = "Strict Two-Phase Locking (S2PL) achieves serializability pessimistically: transactions acquire shared locks on reads and exclusive locks on writes, holding all locks until transaction commit. This causes severe blocking, lock queues, and deadlocks. PostgreSQL uses SSI (based on Cahill's paper), an optimistic algorithm: transactions execute concurrently using standard MVCC snapshot reads without blocking. The engine tracks 'siREAD locks' (in-memory lock tags on tuples/pages) to detect dangerous read-write dependency cycles (rw-antidependencies, where T1 reads a version replaced by T2, and T2 reads a version replaced by T1). If a dependency cycle is detected, the engine aborts one of the transactions with a serialization failure (`40001`), allowing non-conflicting transactions to run with full non-blocking concurrency.",
            keyPoints = listOf(
                "Strict 2PL blocks readers and writers pessimistically until transaction commit",
                "SSI allows transactions to execute concurrently using non-blocking MVCC snapshots",
                "Tracks siREAD locks in memory without acquiring physical blocking locks",
                "Monitors the Serialization Dependency Graph for cycles of rw-antidependencies",
                "Aborts conflicting transactions with serialization error (40001), requiring client retries"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_025",
            trackId = "sql_interview",
            conceptId = "sql_transactions",
            conceptName = "ACID Transactions & Concurrency",
            title = "PostgreSQL MVCC Tuple Headers: xmin, xmax, and Tuple Visibility",
            question = "Explain the role of `xmin`, `xmax`, and the `t_infomask` flags in PostgreSQL heap tuple headers during MVCC visibility checks.",
            shortAnswer = "Every PostgreSQL table row (tuple) begins with a 23-byte header containing: 1) `xmin`: The transaction ID that inserted this tuple. 2) `xmax`: The transaction ID that deleted or updated (superseded) this tuple (0 if active). 3) `t_infomask`: Bit flags recording transaction commit status (`HEAP_XMIN_COMMITTED`, `HEAP_XMAX_COMMITTED`, `HEAP_XMAX_INVALID`). When a query runs with an active Snapshot (`SnapshotData` containing `xmin_horizon`, `xmax_horizon`, and active `xip_list`): A tuple is visible IF its `xmin` committed before the snapshot began AND its `xmax` is either unset, aborted, or committed AFTER the snapshot began. If `t_infomask` hints are unset, Postgres consults the CLOG (commit log) and sets the hint bit to avoid future CLOG lookups.",
            keyPoints = listOf(
                "`xmin` records the transaction ID that created/inserted the row tuple",
                "`xmax` records the transaction ID that deleted or updated/superseded the tuple",
                "`t_infomask` stores hint bits for commit/abort status, avoiding repetitive CLOG lookups",
                "Snapshot visibility checks evaluate whether xmin/xmax precede snapshot horizons or are in active xip list",
                "Tuples with committed xmax are dead and become reclaimable by VACUUM"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_026",
            trackId = "sql_interview",
            conceptId = "sql_transactions",
            conceptName = "ACID Transactions & Concurrency",
            title = "MySQL InnoDB Read View and Undo Log Traversal",
            question = "How does MySQL InnoDB construct a `Read View` for consistent reads, and how does it traverse Undo Logs using `roll_ptr`?",
            shortAnswer = "When a consistent read transaction starts in InnoDB, it creates a `Read View` containing: 1) `m_low_limit_id`: Highest tx ID assigned + 1 (tx >= this are invisible). 2) `m_up_limit_id`: Smallest active uncommitted tx ID (tx < this are visible). 3) `m_ids`: List of active uncommitted tx IDs at snapshot creation. 4) `m_creator_trx_id`: Self tx ID (own changes are visible). Every clustered index row contains hidden columns: `DB_TRX_ID` (modifier tx) and `DB_ROLL_PTR` (pointer to Undo Log record). Visibility check: If a row's `DB_TRX_ID` is in `m_ids` or >= `m_low_limit_id`, it is invisible. InnoDB follows `DB_ROLL_PTR` into the Undo Log to fetch the previous version and repeats the check until finding a visible historical version.",
            keyPoints = listOf(
                "Read View records m_ids (active uncommitted transactions), m_up_limit_id, and m_low_limit_id",
                "Row hidden column `DB_TRX_ID` identifies the last modifying transaction",
                "`DB_ROLL_PTR` provides a direct memory pointer to the corresponding Undo Log record",
                "If DB_TRX_ID is uncommitted or created after Read View, InnoDB traverses the undo chain",
                "Read Committed generates a new Read View per statement; Repeatable Read reuses one Read View per transaction"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_027",
            trackId = "sql_interview",
            conceptId = "sql_transactions",
            conceptName = "ACID Transactions & Concurrency",
            title = "Two-Phase Locking (2PL) vs Strict 2PL vs Rigorous 2PL",
            question = "Describe the phases of standard Two-Phase Locking (2PL). Why do modern production databases implement Strict 2PL or Rigorous 2PL instead?",
            shortAnswer = "Standard 2PL has two phases: 1) Growing Phase: The transaction may acquire locks (Shared or Exclusive) but cannot release any lock. 2) Shrinking Phase: The transaction may release locks but cannot acquire any new locks. In standard 2PL, releasing an exclusive lock before transaction commit allows other transactions to read uncommitted data. If the first transaction rolls back, this leads to Cascading Aborts (other transactions must be recursively rolled back). Strict 2PL (S2PL) solves this by mandating that all Exclusive (X) locks must be held until transaction commit/abort. Rigorous 2PL (SS2PL) mandates that ALL locks (both Shared and Exclusive) must be held until commit, eliminating cascading aborts and ensuring strict serializability.",
            keyPoints = listOf(
                "Standard 2PL: Growing phase (locks acquired) followed by Shrinking phase (locks released)",
                "Releasing exclusive locks in shrinking phase prior to commit causes cascading aborts on rollback",
                "Strict 2PL (S2PL) holds all Exclusive (X) write locks until final commit or rollback",
                "Rigorous 2PL (SS2PL) holds both Shared (S) and Exclusive (X) locks until commit",
                "Completely eliminates cascading rollbacks and guarantees serializable transaction ordering"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_028",
            trackId = "sql_interview",
            conceptId = "sql_transactions",
            conceptName = "ACID Transactions & Concurrency",
            title = "Hierarchical Locking: Shared, Exclusive and Intent Locks",
            question = "What are Intent Locks (Intent Shared - IS, Intent Exclusive - IX) in relational databases, and how do they optimize table-level lock checks?",
            shortAnswer = "Intent Locks are table-level locks that declare an intention to lock individual rows lower in the hierarchy. Before a transaction acquires a Shared (S) row lock, it must acquire an Intent Shared (IS) table lock. Before acquiring an Exclusive (X) row lock, it must acquire an Intent Exclusive (IX) table lock. Why this is critical: Suppose Tx 1 updates a single row in a 10-million-row table, acquiring an X row lock and an IX table lock. Later, Tx 2 wants to run `ALTER TABLE` or `LOCK TABLE ... EXCLUSIVE`, requiring an exclusive table lock. Without intent locks, Tx 2 would have to scan all 10 million rows to verify if any individual row is locked. With intent locks, Tx 2 simply checks the table-level lock: IX conflicts with X table lock, so Tx 2 waits immediately (O(1) conflict detection).",
            keyPoints = listOf(
                "Intent locks (IS, IX) operate at the table/page level indicating fine-grained row lock intentions",
                "Transaction must acquire IS/IX at table level before acquiring S/X at row level",
                "Eliminates the need for table locks to scan millions of rows to check for existing row locks",
                "Enables O(1) instant conflict detection for coarse table operations (ALTER TABLE, TRUNCATE)",
                "IS and IX are compatible with each other, allowing high concurrent row-level operations"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_029",
            trackId = "sql_interview",
            conceptId = "sql_transactions",
            conceptName = "ACID Transactions & Concurrency",
            title = "MySQL InnoDB Row Locks: Record, Gap, and Next-Key Locks",
            question = "Explain the differences between Record Locks, Gap Locks, and Next-Key Locks in MySQL InnoDB. How do they eliminate phantom inserts?",
            shortAnswer = "1) Record Lock: Locks a specific existing index record (e.g. `WHERE id = 10` on unique key). 2) Gap Lock: Locks the empty space (the gap) BETWEEN index records, or before the first/after the last record, but does NOT lock the records themselves. Prevents concurrent transactions from inserting new rows into that gap. 3) Next-Key Lock: The default locking mechanism in InnoDB under Repeatable Read. It combines a Record Lock on the index record plus a Gap Lock on the gap immediately preceding that record (range: `(previous_record, this_record]`). How it stops phantoms: In `SELECT * FROM users WHERE age > 25 FOR UPDATE`, InnoDB places Next-Key locks on all index records > 25 and the supremum pseudo-record. Any concurrent transaction attempting `INSERT INTO users (age) VALUES (30)` is blocked by the gap lock.",
            keyPoints = listOf(
                "Record Lock locks an individual physical index record",
                "Gap Lock locks the open interval between two index records, blocking concurrent inserts",
                "Next-Key Lock combines a Gap Lock on the preceding gap plus a Record Lock on the record itself",
                "Default locking algorithm in MySQL InnoDB under Repeatable Read isolation",
                "Prevents phantom rows by locking index gaps against concurrent matching INSERT statements"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_030",
            trackId = "sql_interview",
            conceptId = "sql_transactions",
            conceptName = "ACID Transactions & Concurrency",
            title = "Locking Reads: FOR UPDATE, SKIP LOCKED, and NOWAIT",
            question = "How do `SELECT ... FOR UPDATE`, `NOWAIT`, and `SKIP LOCKED` differ when building high-throughput database task queues?",
            shortAnswer = "1) `SELECT ... FOR UPDATE`: Acquires exclusive row locks on matching rows. If another transaction has locked any matching row, the current query BLOCKS and waits until the lock is released. In job queues with multiple concurrent workers, all workers block on the first available rows, collapsing throughput. 2) `NOWAIT`: Attempts to acquire locks immediately. If any row is locked, it aborts instantly with an error (e.g. `could not obtain lock`), requiring retry logic. 3) `SKIP LOCKED`: Skips any rows that are currently locked by other concurrent transactions, returning only free rows and immediately acquiring exclusive locks on them. Perfect for distributed queues: `SELECT * FROM tasks WHERE status = 'PENDING' ORDER BY priority LIMIT 1 FOR UPDATE SKIP LOCKED` allows 50 workers to dequeue distinct tasks simultaneously with zero contention or blocking.",
            keyPoints = listOf(
                "`FOR UPDATE` blocks and waits until locked rows are committed or released",
                "`NOWAIT` fails immediately with an error if any requested row is locked",
                "`SKIP LOCKED` skips locked rows cleanly, returning only currently unreserved rows",
                "Enables lock-free, highly concurrent task worker processing directly in relational databases",
                "Transactions must commit or rollback promptly to release acquired task locks"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_031",
            trackId = "sql_interview",
            conceptId = "sql_transactions",
            conceptName = "ACID Transactions & Concurrency",
            title = "Pessimistic vs Optimistic Locking Trade-offs",
            question = "Compare Pessimistic Locking (`SELECT ... FOR UPDATE`) vs Optimistic Locking (version column / CAS). When does each pattern outperform the other?",
            shortAnswer = "1) Pessimistic Locking: Assumes conflicts will happen. Immediately locks rows in the database upon reading (`FOR UPDATE`), forcing concurrent transactions to wait. Ideal for: High-write contention on single rows (e.g. flash sales, bank account balances), short transaction durations, and when the cost of aborting/retrying a business flow is high. Drawback: Lock queues, reduced concurrency, potential deadlocks. 2) Optimistic Locking: Assumes conflicts are rare. Reads data without locks. On update, verifies version hasn't changed (`UPDATE accounts SET balance = 50, version = version + 1 WHERE id = 1 AND version = 5`). If updated rows == 0, the transaction aborts or retries. Ideal for: Low-to-moderate contention, long user think times (web forms, REST APIs), and distributed read-heavy workloads. Drawback: High retry overhead and wasted CPU under severe contention.",
            keyPoints = listOf(
                "Pessimistic locking locks rows in database upfront; prevents concurrent conflicting writes",
                "Optimistic locking uses application-managed version numbers or timestamps without DB locks",
                "Pessimistic excels under high contention and short transactions to avoid retry thrashing",
                "Optimistic excels across HTTP requests, distributed services, and low-contention scenarios",
                "Optimistic locking degrades sharply under high concurrency due to constant retry failures"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_sql_032",
            trackId = "sql_interview",
            conceptId = "sql_transactions",
            conceptName = "ACID Transactions & Concurrency",
            title = "Deadlock Detection vs Deadlock Prevention Schemes",
            question = "How does an RDBMS detect deadlocks using a Wait-For Graph? Compare this with Deadlock Prevention algorithms like Wait-Die and Wound-Wait.",
            shortAnswer = "Deadlock occurs when Tx 1 holds Lock A and requests Lock B, while Tx 2 holds Lock B and requests Lock A. 1) Deadlock Detection: The database maintains a directed 'Wait-For Graph' (nodes = transactions, edges = waiting for lock). A background thread periodically scans the graph (e.g. every 1s). If a directed cycle is found, a deadlock exists. The engine picks a 'victim' (typically the transaction with the fewest undo logs or changes) and aborts it with an error. 2) Deadlock Prevention: Uses transaction timestamps (older tx has smaller timestamp): a) Wait-Die (Non-preemptive): If old requests lock held by young, old WAITS; if young requests lock held by old, young DIES (aborts). b) Wound-Wait (Preemptive): If old requests lock held by young, old WOUNDS (aborts/preempts) young; if young requests lock held by old, young WAITS. Both prevent cycles entirely.",
            keyPoints = listOf(
                "Wait-For Graph models transaction lock dependencies; directed cycles indicate deadlocks",
                "Deadlock detector aborts the lowest-cost victim transaction to break the cycle",
                "Deadlock prevention eliminates cycles using monotonic transaction timestamps",
                "Wait-Die: Older transactions wait; younger transactions abort (die)",
                "Wound-Wait: Older transactions preempt (wound) younger; minimizes unnecessary aborts"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_033",
            trackId = "sql_interview",
            conceptId = "sql_transactions",
            conceptName = "ACID Transactions & Concurrency",
            title = "Transaction Savepoints and Partial Rollbacks",
            question = "How do SQL Savepoints work under the hood? What happens to acquired locks and undo logs when rolling back to a savepoint?",
            shortAnswer = "A Savepoint (`SAVEPOINT sp1`) creates a named checkpoint marker within an active transaction. Under the hood: 1) The engine records the current state of transaction logs (Undo log position in InnoDB or transaction sub-id in Postgres). 2) If an error occurs, the application can issue `ROLLBACK TO SAVEPOINT sp1`. The database undoes all data mutations performed AFTER the savepoint by traversing the undo log, but keeps the overarching transaction alive. 3) Lock Behavior: In PostgreSQL, row locks acquired after the savepoint are NOT released upon rollback to savepoint—they are held until final transaction COMMIT/ROLLBACK to prevent race conditions. 4) Savepoints incur memory overhead in the transaction's sub-transaction list; thousands of nested savepoints can cause performance degradation.",
            keyPoints = listOf(
                "Savepoint marks an intermediate milestone within an ongoing transaction",
                "`ROLLBACK TO SAVEPOINT` reverts mutations made after the marker without aborting the transaction",
                "Database traverses undo logs to restore table state to the savepoint milestone",
                "Row locks acquired after the savepoint remain held until full transaction commit/rollback",
                "Excessive nested savepoints inflate sub-transaction tracking memory structures"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_sql_034",
            trackId = "sql_interview",
            conceptId = "sql_transactions",
            conceptName = "ACID Transactions & Concurrency",
            title = "Hazards of Long-Running Transactions in Production",
            question = "Why are long-running read or write transactions dangerous in high-volume production databases? Detail the effects on MVCC, Undo logs, and VACUUM.",
            shortAnswer = "A long-running transaction holds an old transaction snapshot (low `xmin` in Postgres, old `Read View` in InnoDB). Consequences: 1) PostgreSQL Table Bloat: Autovacuum CANNOT remove dead tuples that were created after the old transaction's `xmin`. Dead tuples accumulate across the entire database, causing massive disk bloat and slowing all sequential scans. 2) Transaction ID Wraparound: In Postgres, if a transaction runs for days, it risks reaching the 2-billion transaction horizon, triggering emergency read-only cluster shutdown. 3) MySQL Undo Log Bloat: InnoDB purge threads cannot discard historical undo log pages older than the oldest active Read View. The undo tablespace swells to hundreds of gigabytes, exhausting disk space. 4) Lock Starvation: Long-running transactions block DDL (`ALTER TABLE`) and create cascading connection pool exhaustion.",
            keyPoints = listOf(
                "Holds open an old snapshot horizon, blocking garbage collection across the database",
                "PostgreSQL Autovacuum cannot clean dead tuples newer than the transaction's xmin, causing table bloat",
                "MySQL InnoDB purge threads cannot purge undo logs, leading to massive undo tablespace explosion",
                "Risks PostgreSQL transaction ID (TXID) wraparound failure under heavy load",
                "Blocks schema migrations (DDL) and holds connection pool slots, leading to connection exhaustion"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_035",
            trackId = "sql_interview",
            conceptId = "sql_transactions",
            conceptName = "ACID Transactions & Concurrency",
            title = "Write-Ahead Logging (WAL) and the ARIES Recovery Algorithm",
            question = "Explain the Write-Ahead Logging (WAL) protocol and the three phases of the ARIES recovery algorithm after a crash.",
            shortAnswer = "WAL Protocol: Before any dirty data page is written to table storage on disk, the corresponding log record describing the change must be written and flushed to durable log storage (`fsync`). ARIES Recovery operates in 3 phases after a crash: 1) Analysis Phase: Scans the log forward from the last checkpoint to determine: which transactions were active at the crash time (Losers), and which dirty pages were in buffer pool (Dirty Page Table). 2) Redo Phase (Repeating History): Scans forward from the earliest unwritten log sequence number (RecLSN), replaying all committed and uncommitted changes to restore the exact pre-crash database state. 3) Undo Phase: Scans backward from the end of the log, rolling back changes made by active uncommitted transactions (Losers) using Compensation Log Records (CLRs) to ensure idempotency.",
            keyPoints = listOf(
                "WAL invariant: log records must be flushed to disk before associated dirty data pages",
                "Analysis Phase: scans forward from checkpoint to identify active transactions and dirty pages",
                "Redo Phase: repeats history by replaying all physical/physiological logs forward",
                "Undo Phase: scans backwards rolling back uncommitted loser transactions",
                "Compensation Log Records (CLRs) ensure crash recovery itself is fully idempotent"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_036",
            trackId = "sql_interview",
            conceptId = "sql_transactions",
            conceptName = "ACID Transactions & Concurrency",
            title = "Durability and `innodb_flush_log_at_trx_commit` Settings",
            question = "Compare the durability guarantees and performance trade-offs of `innodb_flush_log_at_trx_commit` values 0, 1, and 2 in MySQL.",
            shortAnswer = "1) `Value 1 (Strict ACID - Default)`: The log buffer is written to the OS page cache AND flushed to physical disk via `fsync` on EVERY transaction commit. Guarantees 100% durability even on power outage. Bottlenecked by disk write IOPS (typically 1,000-5,000 commits/sec without group commit). 2) `Value 2 (OS Buffered)`: On commit, the log buffer is written to the OS page cache on every commit, but flushed to disk only once per second. If MySQL crashes, zero data is lost (OS cache intact). If the OS crashes or power fails, up to 1 second of transactions is lost. 3) `Value 0 (Process Buffered)`: Log buffer is written to OS cache and flushed to disk once per second; nothing on commit. If MySQL process crashes, up to 1 second of committed data is lost. Values 0 and 2 increase write throughput by 5-10x.",
            keyPoints = listOf(
                "Value 1 (Default): fsync on every commit; full ACID durability against power failure",
                "Value 2: Writes to OS cache on commit, fsyncs once per second; safe against MySQL process crash",
                "Value 0: Written and flushed once per second; susceptible to 1-second data loss on process crash",
                "Values 0 and 2 reduce disk I/O bottleneck significantly for non-critical logging/metrics tables",
                "Financial and transactional systems must strictly maintain Value 1"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_037",
            trackId = "sql_interview",
            conceptId = "sql_transactions",
            conceptName = "ACID Transactions & Concurrency",
            title = "Database Checkpointing: Sharp vs Fuzzy Checkpoints",
            question = "What is the purpose of database checkpointing? Why do modern engines use Fuzzy Checkpoints instead of Sharp Checkpoints?",
            shortAnswer = "Checkpointing flushes dirty buffer pool pages to disk so that the database does not need to replay WAL from the beginning of time during crash recovery, bounding recovery time and allowing old WAL segments to be deleted. 1) Sharp Checkpoint: Pauses all transaction processing, flushes EVERY dirty buffer pool page to disk at once, writes the checkpoint record, and resumes processing. This causes catastrophic I/O spikes and latency freezes (stalls of several seconds). 2) Fuzzy Checkpoint (used by InnoDB & Postgres): Does NOT pause transactions. Background page cleaner threads continuously flush dirty pages in batches. The checkpoint record simply records the oldest dirty page's Log Sequence Number (LSN) still in memory (Checkpoint LSN). Transactions continue running uninterrupted without I/O freezes.",
            keyPoints = listOf(
                "Checkpointing bounds crash recovery time and allows reclaiming obsolete WAL/redo log space",
                "Sharp checkpoint flushes all dirty pages synchronously, freezing transaction processing",
                "Fuzzy checkpoint flushes pages continuously in background without stalling live transactions",
                "Checkpoint LSN marks the point from which crash recovery redo phase must begin",
                "PostgreSQL configures `checkpoint_completion_target` (e.g. 0.9) to spread I/O over time"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_038",
            trackId = "sql_interview",
            conceptId = "sql_transactions",
            conceptName = "ACID Transactions & Concurrency",
            title = "Two-Phase Commit (2PC) in Distributed Databases",
            question = "How does the Two-Phase Commit (2PC) protocol ensure atomic commits across distributed database shards? What is the coordinator blocking vulnerability?",
            shortAnswer = "2PC coordinates atomic transaction outcomes across multiple database nodes: 1) Prepare Phase: Coordinator sends `PREPARE` to all participants. Participants execute the transaction locally, write changes and undo/redo records to WAL, lock resources, and respond `VOTE_COMMIT` or `VOTE_ABORT`. 2) Commit Phase: If all voted commit, coordinator writes a `COMMIT` record to its WAL and sends `GLOBAL_COMMIT` to participants, which commit and release locks. If any voted abort, coordinator sends `GLOBAL_ABORT`. Coordinator Blocking Problem: If the coordinator crashes AFTER participants have voted `VOTE_COMMIT` but BEFORE sending `GLOBAL_COMMIT`, participants are left in doubt (uncertain state). They cannot unilaterally commit or abort because they don't know other participants' votes, keeping row locks indefinitely held until coordinator recovery.",
            keyPoints = listOf(
                "Prepare Phase: participants execute, persist WAL entries, lock rows, and vote",
                "Commit Phase: coordinator aggregates votes and issues global commit or abort command",
                "Guarantees atomicity across multiple distributed database shards or heterogeneous databases",
                "Coordinator single-point-of-failure leaves participants blocked holding locks indefinitely",
                "High latency due to multiple roundtrips and synchronous WAL disk flushes on all nodes"
            ),
            difficulty = "Staff"
        )
    )

    private fun part3(): List<InterviewQuestion> = listOf(
        InterviewQuestion(
            id = "iq_sql_039",
            trackId = "sql_interview",
            conceptId = "sql_transactions",
            conceptName = "ACID Transactions & Concurrency",
            title = "Phantom Reads: ANSI SQL Definition vs MySQL InnoDB Behavior",
            question = "How does MySQL InnoDB's behavior regarding Phantom Reads differ between Consistent Reads (plain SELECT) and Locking Reads (SELECT FOR UPDATE)?",
            shortAnswer = "Under Repeatable Read: 1) Consistent Reads (Plain SELECT): InnoDB uses MVCC snapshot reads. When a transaction performs `SELECT * FROM users WHERE age > 20`, it reads from its initial Read View. Even if another transaction commits new rows matching `age > 20`, subsequent plain SELECTs in Tx 1 will NEVER see the new rows. It is completely immune to phantom reads. 2) Locking Reads (`SELECT ... FOR UPDATE` or `LOCK IN SHARE MODE`): InnoDB performs a Current Read (reads latest committed data, bypassing MVCC) and acquires Next-Key Locks. If Tx 1 runs a plain SELECT, and concurrent Tx 2 inserts a row, and THEN Tx 1 executes an UPDATE on all matching rows (`UPDATE users SET status = 'ACTIVE' WHERE age > 20`), Tx 1 updates the phantom row! A subsequent plain SELECT in Tx 1 now shows the phantom row because Tx 1 itself modified it (own changes visible).",
            keyPoints = listOf(
                "Plain SELECT uses MVCC snapshot read view, completely immune to phantom reads",
                "Locking reads (FOR UPDATE) perform Current Reads that fetch the latest committed data",
                "Next-Key Locking on index ranges prevents concurrent transactions from inserting phantoms",
                "Updating rows that were inserted by another transaction brings them into the current Read View",
                "Application code must consistently use either snapshot reads or locking reads"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_040",
            trackId = "sql_interview",
            conceptId = "sql_transactions",
            conceptName = "ACID Transactions & Concurrency",
            title = "Group Commit Optimization for High-Throughput WAL Flushes",
            question = "What is Group Commit in PostgreSQL and MySQL InnoDB, and how does it prevent disk fsync bottlenecks on concurrent transaction commits?",
            shortAnswer = "An enterprise SSD or NVMe drive can only perform a limited number of physical `fsync` calls per second (e.g. 2,000-10,000 IOPS). If 10,000 concurrent transactions each requested an individual `fsync` on commit, the database would choke. Group Commit solves this: When a transaction prepares to commit, it becomes the 'group leader' for a queue of waiting transactions. While the leader waits to flush or issues the `fsync()`, other concurrent transactions appending to the log buffer join its commit group. The leader issues a SINGLE `fsync` system call that flushes the entire accumulated log buffer containing commits for dozens or hundreds of transactions simultaneously, notifying all group members upon completion. This scales commit throughput linearly with concurrency.",
            keyPoints = listOf(
                "Physical disk fsync throughput limits single-threaded commit rates to hardware IOPS",
                "Group commit batches multiple concurrent transaction log flushes into a single fsync call",
                "First transaction becomes group leader; following transactions join the commit queue",
                "A single disk flush commits dozens of transactions simultaneously",
                "Throughput scales with concurrency rather than degrading due to disk queue saturation"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_041",
            trackId = "sql_interview",
            conceptId = "sql_querying",
            conceptName = "Advanced SQL Querying, Window Functions & CTEs",
            title = "Window Functions: ROW_NUMBER vs RANK vs DENSE_RANK",
            question = "Compare `ROW_NUMBER()`, `RANK()`, and `DENSE_RANK()`. How do they handle ties in values, and how are they used to solve Top-N per category problems?",
            shortAnswer = "When evaluating sorted values like `[100, 90, 90, 80]`: 1) `ROW_NUMBER()`: Assigns a strictly unique sequential integer to every row regardless of ties -> produces `[1, 2, 3, 4]`. 2) `RANK()`: Assigns the same rank to ties, but skips subsequent rank numbers by the number of ties -> produces `[1, 2, 2, 4]` (rank 3 is skipped). 3) `DENSE_RANK()`: Assigns the same rank to ties WITHOUT skipping subsequent rank numbers -> produces `[1, 2, 2, 3]`. Top-N per Category Pattern: Wrap the window function in a CTE: `WITH Ranked AS (SELECT *, DENSE_RANK() OVER (PARTITION BY category_id ORDER BY salary DESC) as rnk FROM employees) SELECT * FROM Ranked WHERE rnk <= 3;`. Window functions cannot be directly used in WHERE clauses because WHERE evaluates before window calculation.",
            keyPoints = listOf(
                "ROW_NUMBER produces unique sequential numbers without duplicates even on ties",
                "RANK assigns duplicate ranks on ties and skips corresponding subsequent numbers",
                "DENSE_RANK assigns duplicate ranks on ties without skipping rank numbers",
                "Window functions execute after WHERE/GROUP BY; must be wrapped in CTE or subquery to filter",
                "Standard approach for Top-N records per partition (e.g. top 3 earners per department)"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_sql_042",
            trackId = "sql_interview",
            conceptId = "sql_querying",
            conceptName = "Advanced SQL Querying, Window Functions & CTEs",
            title = "Window Frame Clauses: ROWS vs RANGE",
            question = "Explain the difference between `ROWS BETWEEN` and `RANGE BETWEEN` in SQL window framing. What is the performance danger of the default window frame?",
            shortAnswer = "A window frame defines the subset of rows within the partition evaluated by the aggregate: 1) `ROWS BETWEEN`: Operates on physical row counts relative to the current row (e.g. `ROWS BETWEEN 2 PRECEDING AND CURRENT ROW` counts exactly 2 physical rows prior). Fast and predictable. 2) `RANGE BETWEEN`: Operates on logical values of the ORDER BY expression. All rows sharing identical values with the current row are treated as peers. Danger of Default: In SQL standard, if you specify `ORDER BY` without a frame clause, the default is `RANGE BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW`. Because it is `RANGE`, the database must compare and group duplicate peer values on every row, often spilling sorting buffers to disk or disabling index streaming. Explicitly specifying `ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW` avoids duplicate peer evaluation and runs significantly faster.",
            keyPoints = listOf(
                "ROWS operates on physical row offsets; RANGE operates on logical value differences",
                "Default frame with ORDER BY is `RANGE BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW`",
                "RANGE must evaluate all rows with peer values together, adding CPU and buffering overhead",
                "ROWS allows streaming one-pass calculations with minimal memory footprint",
                "Always explicitly declare `ROWS` when calculating running totals or moving averages"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_043",
            trackId = "sql_interview",
            conceptId = "sql_querying",
            conceptName = "Advanced SQL Querying, Window Functions & CTEs",
            title = "Calculating Moving Averages and Rolling Aggregates",
            question = "Write a SQL query using window frames to calculate a 7-day moving average of revenue per product. How do you handle missing calendar days?",
            shortAnswer = "If table records exist for every consecutive day: `SELECT product_id, sale_date, AVG(daily_revenue) OVER (PARTITION BY product_id ORDER BY sale_date ROWS BETWEEN 6 PRECEDING AND CURRENT ROW) as moving_avg_7d FROM daily_sales;`. The Pitfall: If a product had no sales on Tuesday, that day is missing from the table. A `ROWS BETWEEN 6 PRECEDING` frame will simply look back 6 available records (which might span 14 calendar days). To fix missing days: 1) In PostgreSQL, use `generate_series()` to create a dense continuous calendar date table, `LEFT JOIN` sales data coalescing missing revenues to 0, and then apply `ROWS BETWEEN 6 PRECEDING`. 2) Or in PostgreSQL/MySQL 8, use `RANGE BETWEEN INTERVAL '6 days' PRECEDING AND CURRENT ROW` directly on date types.",
            keyPoints = listOf(
                "Moving average uses `AVG(...) OVER (ORDER BY date ROWS BETWEEN N PRECEDING AND CURRENT ROW)`",
                "ROWS frame relies on physical row count, failing if calendar days are sparse/missing",
                "RANGE with date intervals calculates exact temporal windows regardless of missing rows",
                "Dense date generation (`generate_series` or calendar dimension) guarantees continuous series",
                "Coalesce null revenues to zero on missing days before computing window aggregates"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_044",
            trackId = "sql_interview",
            conceptId = "sql_querying",
            conceptName = "Advanced SQL Querying, Window Functions & CTEs",
            title = "Inter-Row Differences with LEAD and LAG",
            question = "How do `LEAD()` and `LAG()` work? Provide a real-world query showing how to detect user session inactivity timeouts (> 30 minutes) using `LAG()`.",
            shortAnswer = "`LAG(col, offset, default)` accesses data from a preceding row in the window partition; `LEAD()` accesses a subsequent row. Detecting session timeouts: `WITH EventTimeDiffs AS (SELECT user_id, event_time, EXTRACT(EPOCH FROM (event_time - LAG(event_time) OVER (PARTITION BY user_id ORDER BY event_time))) / 60.0 as minutes_since_prev_event FROM user_events), FlaggedSessions AS (SELECT *, CASE WHEN minutes_since_prev_event > 30 OR minutes_since_prev_event IS NULL THEN 1 ELSE 0 END as is_new_session FROM EventTimeDiffs) SELECT *, SUM(is_new_session) OVER (PARTITION BY user_id ORDER BY event_time ROWS UNBOUNDED PRECEDING) as session_id FROM FlaggedSessions;`. This classic 'Sessionization' pattern uses LAG to identify 30-minute gaps and a running SUM to generate unique session IDs.",
            keyPoints = listOf(
                "LAG accesses previous row values without requiring self-joins",
                "LEAD accesses succeeding row values based on window partition ordering",
                "Accepts optional offset parameter (default 1) and default fallback value for boundaries",
                "Sessionization pattern: LAG computes time delta between consecutive user events",
                "Running SUM of boolean timeout flags creates incremental unique session IDs"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_045",
            trackId = "sql_interview",
            conceptId = "sql_querying",
            conceptName = "Advanced SQL Querying, Window Functions & CTEs",
            title = "Cumulative Sums (Running Totals): Window vs Self-Join",
            question = "Why is computing running totals with window functions orders of magnitude faster than historical correlated self-joins?",
            shortAnswer = "Historical Self-Join Approach: `SELECT a.id, a.amount, SUM(b.amount) FROM transactions a JOIN transactions b ON b.account_id = a.account_id AND b.created_at <= a.created_at GROUP BY a.id, a.amount;`. This requires an O(N^2) Cartesian product: for every row, it re-sums all previous rows, requiring N*(N+1)/2 row comparisons (billions of comparisons on 100,000 rows). Modern Window Function: `SELECT id, amount, SUM(amount) OVER (PARTITION BY account_id ORDER BY created_at ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) FROM transactions;`. This runs in O(N log N) sorting time and O(N) scanning time. The database engine sorts the data once and maintains a single running accumulator variable in CPU registers, updating it incrementally as it streams through rows.",
            keyPoints = listOf(
                "Correlated self-joins for running totals have quadratic O(N^2) computational complexity",
                "Window function approaches execute in linear O(N) time after initial O(N log N) sort",
                "Engine streams sorted rows, keeping a single accumulator register in memory",
                "Avoids massive Cartesian explosion, buffer spills to disk, and redundant page reads",
                "Enables real-time running ledger calculations on millions of transaction records"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_sql_046",
            trackId = "sql_interview",
            conceptId = "sql_querying",
            conceptName = "Advanced SQL Querying, Window Functions & CTEs",
            title = "Quantiles and Percentiles: NTILE vs PERCENT_RANK",
            question = "How do `NTILE(n)` and `PERCENT_RANK()` differ, and how does PostgreSQL's `percentile_cont()` calculate true continuous percentiles?",
            shortAnswer = "1) `NTILE(n)`: Divides the ordered partition into `n` approximately equal integer buckets (e.g. `NTILE(4)` assigns quartiles 1, 2, 3, 4). If rows don't divide evenly, extra rows are distributed to the first buckets. 2) `PERCENT_RANK()`: Computes relative rank as a floating point between 0.0 and 1.0 using the formula `(rank - 1) / (total_rows - 1)`. 3) `percentile_cont(fraction) WITHIN GROUP (ORDER BY val)`: An ordered-set aggregate function that calculates true continuous mathematical percentiles (e.g. p95, p99 latency). Unlike window functions that label existing rows, `percentile_cont(0.95)` interpolates between adjacent values if the percentile falls between rows.",
            keyPoints = listOf(
                "NTILE(n) partitions rows into n equal discrete integer buckets",
                "PERCENT_RANK calculates relative rank ratio between 0.0 and 1.0",
                "percentile_cont() is an ordered-set aggregate computing continuous interpolated percentiles",
                "percentile_disc() returns the discrete actual sample value matching the percentile",
                "Essential for SLA monitoring, telemetry latency percentiles (P50, P90, P99), and financial scoring"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_047",
            trackId = "sql_interview",
            conceptId = "sql_querying",
            conceptName = "Advanced SQL Querying, Window Functions & CTEs",
            title = "Recursive Common Table Expressions (CTEs)",
            question = "Explain the mechanics of a Recursive CTE (`WITH RECURSIVE`). Write a query to traverse an organizational hierarchy from CEO to individual contributors.",
            shortAnswer = "A Recursive CTE contains two parts joined by `UNION ALL`: 1) Anchor Member: The base query executed once to produce the initial result set (e.g. CEO where manager_id IS NULL). 2) Recursive Member: References the CTE itself, executed iteratively against the result of the PREVIOUS iteration until returning empty. Organizational Query: `WITH RECURSIVE OrgChart AS (SELECT emp_id, name, manager_id, 1 as depth, ARRAY[name] as path FROM employees WHERE manager_id IS NULL UNION ALL SELECT e.emp_id, e.name, e.manager_id, o.depth + 1, o.path || e.name FROM employees e JOIN OrgChart o ON e.manager_id = o.emp_id) SELECT * FROM OrgChart ORDER BY depth;`. Cycle Prevention: If a cycle exists (A manages B, B manages A), it loops infinitely. Fix: Track path array and add `WHERE NOT e.emp_id = ANY(o.path)` or `CYCLE` clause.",
            keyPoints = listOf(
                "Anchor member defines the base query executed once to seed the recursion",
                "Recursive member repeatedly queries the working table produced by the preceding iteration",
                "Terminates automatically when the recursive query yields an empty result set",
                "Tracks depth level and ancestor path array for hierarchy visualization",
                "Cycle prevention logic (`CYCLE` clause or visited array) stops infinite recursion on graph cycles"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_048",
            trackId = "sql_interview",
            conceptId = "sql_querying",
            conceptName = "Advanced SQL Querying, Window Functions & CTEs",
            title = "Materialized vs Inlined CTEs (The Optimization Fence)",
            question = "What was the CTE 'Optimization Fence' in PostgreSQL prior to version 12? How do `AS MATERIALIZED` and `AS NOT MATERIALIZED` give developers control?",
            shortAnswer = "Prior to PostgreSQL 12, all CTEs (`WITH cte AS (...)`) were strict 'Optimization Fences': the engine ALWAYS executed the CTE independently, materialized the entire result into a temporary memory/disk buffer, and then joined the main query. The planner COULD NOT push down outer WHERE predicates or index filters into the CTE. A query filtering `WHERE cte.id = 1` on a 10M row CTE would materialize all 10M rows before filtering! In Postgres 12+, CTEs are automatically inlined (like subqueries) if they have no side-effects and are referenced once. Developers can override: 1) `WITH cte AS MATERIALIZED (...)`: Forces materialization if the CTE is referenced multiple times to prevent re-evaluating an expensive query. 2) `WITH cte AS NOT MATERIALIZED (...)`: Forces inlining so outer WHERE predicates push down.",
            keyPoints = listOf(
                "Optimization fence prevents query optimizer from pushing outer predicates down into CTE",
                "Historically forced complete materialization of CTE results, defeating index seeks",
                "PostgreSQL 12+ automatically inlines CTEs into the outer query plan when beneficial",
                "`AS MATERIALIZED` forces the database to evaluate once and cache the temporary result",
                "`AS NOT MATERIALIZED` guarantees inlining, enabling index predicate pushdowns"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_049",
            trackId = "sql_interview",
            conceptId = "sql_querying",
            conceptName = "Advanced SQL Querying, Window Functions & CTEs",
            title = "Correlated vs Uncorrelated Subqueries & Decorrelation",
            question = "What makes a subquery correlated? How does the database optimizer perform Subquery Decorrelation (Unnesting), and when does it fail?",
            shortAnswer = "An Uncorrelated Subquery has no references to outer query columns; it executes once, caches its result, and passes it to the outer query. A Correlated Subquery references columns from the outer query row (e.g. `WHERE salary > (SELECT AVG(salary) FROM emp WHERE dept_id = outer.dept_id)`). Naively, it must re-execute for EVERY row in the outer table (O(N*M) loop). Optimizer Decorrelation (Unnesting): Modern optimizers rewrite the correlated subquery into a `LEFT JOIN` with a pre-aggregated subquery or a Window function (`AVG(salary) OVER (PARTITION BY dept_id)`). Decorrelation fails when subqueries contain non-deterministic functions (`RAND()`, `NOW()`), `LIMIT`/`OFFSET` without grouping, or complex OR conditions across outer and inner tables, falling back to slow row-by-row nested loops.",
            keyPoints = listOf(
                "Correlated subqueries reference outer row attributes, conceptually executing once per outer row",
                "Subquery decorrelation transforms nested subqueries into efficient joins or window functions",
                "Converts O(N*M) nested iteration into O(N log N) hash or merge join execution",
                "Decorrelation fails on non-deterministic functions, LIMIT/OFFSET, or complex disjunctions",
                "Manual refactoring to Window functions or CTE joins guarantees set-based performance"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_050",
            trackId = "sql_interview",
            conceptId = "sql_querying",
            conceptName = "Advanced SQL Querying, Window Functions & CTEs",
            title = "EXISTS vs IN vs JOIN Performance and NULL Pitfalls",
            question = "Compare `EXISTS`, `IN`, and `JOIN` for semi-join queries. Why is `NOT IN` notoriously dangerous when subquery columns contain NULLs?",
            shortAnswer = "1) Semi-Joins: `EXISTS (SELECT 1 FROM ...)` stops scanning as soon as the first matching row is found (short-circuit boolean check). Modern optimizers rewrite `WHERE id IN (SELECT id ...)` into a Semi-Join identical to `EXISTS`. Plain `JOIN` may produce duplicate rows if the joined table has multiple matches, requiring an expensive `DISTINCT`. 2) The `NOT IN` NULL Trap: In SQL three-valued logic, `WHERE id NOT IN (1, 2, NULL)` expands to `id != 1 AND id != 2 AND id != NULL`. Since any comparison with NULL yields `UNKNOWN`, and `TRUE AND UNKNOWN` is `UNKNOWN`, the entire condition evaluates to UNKNOWN! As a result, the query returns ZERO rows, even if there are thousands of valid non-matching records. Fix: Always use `NOT EXISTS` or ensure `WHERE col IS NOT NULL`.",
            keyPoints = listOf(
                "EXISTS short-circuits on the first matching record without evaluating full datasets",
                "Plain JOIN requires DISTINCT deduplication if right-side table has multiple matches",
                "Modern optimizers rewrite IN subqueries to semi-joins when keys are non-nullable",
                "`NOT IN` with a single NULL value in subquery yields UNKNOWN, returning zero rows",
                "`NOT EXISTS` correctly handles NULL values because it tests for presence of matching rows"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_sql_051",
            trackId = "sql_interview",
            conceptId = "sql_querying",
            conceptName = "Advanced SQL Querying, Window Functions & CTEs",
            title = "Lateral Joins (CROSS JOIN LATERAL / OUTER APPLY)",
            question = "What is a `LATERAL` join in PostgreSQL / MySQL 8.0 (`CROSS APPLY` in SQL Server)? Provide a concrete scenario where standard joins cannot work.",
            shortAnswer = "A `LATERAL` join acts like a SQL for-each loop: it allows the subquery on the right side of the join to reference columns provided by rows on the left side of the join. Standard `JOIN` cannot do this because subqueries in standard joins are evaluated independently. Scenario: 'Find the top 3 most recent orders for EVERY customer'. Standard join cannot limit orders per customer without window functions. With LATERAL: `SELECT c.id, c.name, o.order_id, o.order_date FROM customers c CROSS JOIN LATERAL (SELECT id as order_id, order_date FROM orders WHERE customer_id = c.id ORDER BY order_date DESC LIMIT 3) o;`. The database executes the subquery for each customer, leveraging an index on `(customer_id, order_date DESC)` to fetch exactly 3 rows per customer in microseconds.",
            keyPoints = listOf(
                "LATERAL enables the right-side subquery to reference columns from preceding left-side tables",
                "Acts as a parameterized inline subquery for each outer table row",
                "Solves Top-N per parent row problems directly leveraging index seeks and LIMIT",
                "Can unpack dynamic arrays, JSONB elements, or table-valued functions per row",
                "Equivalent to CROSS APPLY (CROSS JOIN LATERAL) and OUTER APPLY (LEFT JOIN LATERAL)"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_052",
            trackId = "sql_interview",
            conceptId = "sql_querying",
            conceptName = "Advanced SQL Querying, Window Functions & CTEs",
            title = "Multidimensional Aggregations: GROUPING SETS, ROLLUP, and CUBE",
            question = "Explain `GROUPING SETS`, `ROLLUP`, and `CUBE`. How do they replace multiple `UNION ALL` aggregation queries in OLAP reporting?",
            shortAnswer = "Instead of executing separate queries for different aggregation granularities and combining them with `UNION ALL` (requiring multiple table scans), multidimensional SQL extensions calculate all aggregations in a single pass: 1) `GROUPING SETS`: Explicitly specifies which combinations of columns to aggregate: `GROUP BY GROUPING SETS ((year, region), (year), ())`. 2) `ROLLUP(year, month, day)`: Creates hierarchical subtotals: `(year, month, day)`, `(year, month)`, `(year)`, and grand total `()`. 3) `CUBE(a, b, c)`: Generates the full power set of all \$2^N\$ possible combinations (\$2^3 = 8\$ groupings). The `GROUPING(col)` function returns 1 if the column is an aggregated subtotal row (NULL replacement) or 0 if it is a real data value.",
            keyPoints = listOf(
                "Calculates multi-level subtotals and grand totals in a single physical table scan",
                "Eliminates multiple separate queries joined by expensive UNION ALL operations",
                "ROLLUP generates hierarchical prefix subtotals (Year -> Month -> Day -> Grand Total)",
                "CUBE generates all 2^N dimensional combinations for cross-tabular OLAP cubes",
                "GROUPING() helper function distinguishes genuine data NULLs from subtotal aggregation NULLs"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_053",
            trackId = "sql_interview",
            conceptId = "sql_querying",
            conceptName = "Advanced SQL Querying, Window Functions & CTEs",
            title = "Pivoting Rows to Columns: Conditional Aggregation vs FILTER",
            question = "How do you pivot row-based metrics into columns using standard SQL conditional aggregation and the PostgreSQL `FILTER (WHERE ...)` clause?",
            shortAnswer = "1) Standard SQL Conditional Aggregation: Uses `CASE WHEN` inside aggregate functions: `SELECT employee_id, SUM(CASE WHEN quarter = 'Q1' THEN sales ELSE 0 END) as q1_sales, SUM(CASE WHEN quarter = 'Q2' THEN sales ELSE 0 END) as q2_sales FROM quarterly_sales GROUP BY employee_id;`. 2) PostgreSQL FILTER Clause (SQL standard): Cleaner and faster because the engine does not evaluate the `ELSE` branch: `SELECT employee_id, SUM(sales) FILTER (WHERE quarter = 'Q1') as q1_sales, SUM(sales) FILTER (WHERE quarter = 'Q2') as q2_sales FROM quarterly_sales GROUP BY employee_id;`. Benefits: Works across all major databases, handles non-numeric aggregates (`COUNT`, `AVG`, `STRING_AGG`), and executes in a single pass over table data.",
            keyPoints = listOf(
                "Pivoting transforms normalized row values into dimensional column attributes",
                "Conditional aggregation pairs aggregate functions (SUM, COUNT) with CASE WHEN expressions",
                "PostgreSQL `FILTER (WHERE ...)` clause provides cleaner syntax and skips non-matching rows",
                "Executes in a single sequential pass over the table dataset",
                "Avoids database-specific non-standard PIVOT commands and multiple self-joins"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_sql_054",
            trackId = "sql_interview",
            conceptId = "sql_querying",
            conceptName = "Advanced SQL Querying, Window Functions & CTEs",
            title = "Set Operations: UNION vs UNION ALL Deduplication Overhead",
            question = "Why should `UNION ALL` always be preferred over `UNION` unless deduplication is strictly required? Detail the engine execution mechanics.",
            shortAnswer = "`UNION ALL` simply concatenates the result streams of two queries: the engine outputs rows from Query 1 and streams rows from Query 2 immediately to the client without buffering. In contrast, `UNION` requires strict duplicate elimination: the database must buffer ALL rows from both queries into memory (or temporary disk files) and perform either a Hash Aggregate or a Sort Distinct pass across all returned columns. On large result sets (e.g. 500,000 rows), `UNION` consumes gigabytes of RAM, spills temporary files to disk, maxes out CPU for sorting/hashing, and introduces multi-second query latency. Rule: Default to `UNION ALL`; only use `UNION` when duplicate removal is an explicit business requirement.",
            keyPoints = listOf(
                "UNION ALL concatenates result streams directly with zero sorting or buffering overhead",
                "UNION performs full duplicate elimination across all columns in the select list",
                "Forces database to execute an expensive sort or hash-aggregate deduplication pass",
                "Spills memory to temporary disk work tables when result sets exceed work_mem",
                "Always default to UNION ALL unless row deduplication is an intentional requirement"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_sql_055",
            trackId = "sql_interview",
            conceptId = "sql_querying",
            conceptName = "Advanced SQL Querying, Window Functions & CTEs",
            title = "Set Differences: EXCEPT / MINUS vs Anti-Joins",
            question = "Compare `EXCEPT` (`MINUS` in Oracle) with `LEFT JOIN ... WHERE right.id IS NULL` (Anti-Join). Which executes faster on large tables?",
            shortAnswer = "`EXCEPT` returns distinct rows from Query 1 that do not appear in Query 2. Like `UNION`, `EXCEPT` compares ALL columns and performs full deduplication (sorting/hashing both entire result sets). An Anti-Join (`SELECT a.* FROM table_a a LEFT JOIN table_b b ON a.id = b.id WHERE b.id IS NULL`) or `WHERE NOT EXISTS` compares ONLY the specific join key (`id`). Because an Anti-Join operates on indexed keys, the optimizer uses an efficient Hash Anti-Join or Merge Anti-Join, short-circuiting on the first key match without deduplicating non-key attributes. On large tables, `NOT EXISTS` or `LEFT JOIN ... IS NULL` almost always outperforms `EXCEPT` by 5x-20x.",
            keyPoints = listOf(
                "EXCEPT/MINUS evaluates all selected columns and enforces full set deduplication",
                "Anti-Join (LEFT JOIN ... WHERE NULL or NOT EXISTS) evaluates only the join key",
                "Anti-Joins leverage B-Tree indexes on join keys for fast seeks and short-circuiting",
                "Avoids buffering and sorting wide payload columns across both queries",
                "NOT EXISTS is generally the most readable and consistently optimized anti-join pattern"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_056",
            trackId = "sql_interview",
            conceptId = "sql_querying",
            conceptName = "Advanced SQL Querying, Window Functions & CTEs",
            title = "SQL Three-Valued Logic and NULL Comparisons",
            question = "Explain Three-Valued Logic (3VL) in SQL. Why does `WHERE col = NULL` or `WHERE col != NULL` never return any rows?",
            shortAnswer = "In SQL, `NULL` represents 'unknown' or 'missing information', not a concrete value. Any arithmetic or comparison operation with NULL (`col = NULL`, `col != NULL`, `col < 5`) evaluates to `UNKNOWN`, not TRUE or FALSE. In SQL WHERE clauses, a row is ONLY returned if the predicate evaluates to `TRUE`. Because `UNKNOWN` is not `TRUE`, both `col = NULL` and `col != NULL` evaluate to `UNKNOWN` for every row, returning zero rows. Truth tables: `TRUE AND UNKNOWN = UNKNOWN`, `FALSE AND UNKNOWN = FALSE`, `TRUE OR UNKNOWN = TRUE`, `NOT UNKNOWN = UNKNOWN`. Correct syntax: Always use `IS NULL` or `IS NOT NULL` (which evaluate to boolean TRUE/FALSE) or `IS DISTINCT FROM` for null-safe equality.",
            keyPoints = listOf(
                "SQL logic has three truth values: TRUE, FALSE, and UNKNOWN",
                "NULL represents unknown information; comparing anything with NULL yields UNKNOWN",
                "WHERE clauses filter out rows unless the predicate evaluates strictly to TRUE",
                "`col = NULL` and `col != NULL` yield UNKNOWN for all rows, returning empty sets",
                "Use `IS NULL`, `IS NOT NULL`, or `IS DISTINCT FROM` for correct null evaluations"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_sql_057",
            trackId = "sql_interview",
            conceptId = "sql_querying",
            conceptName = "Advanced SQL Querying, Window Functions & CTEs",
            title = "COALESCE vs NULLIF and Short-Circuit Evaluation",
            question = "How do `COALESCE` and `NULLIF` work? Explain how combining them prevents division-by-zero errors in SQL calculations.",
            shortAnswer = "1) `COALESCE(val1, val2, ..., valN)`: Returns the first non-NULL expression from its argument list. Evaluates with short-circuiting: arguments after the first non-null are not evaluated. 2) `NULLIF(val1, val2)`: Compares two arguments; returns `NULL` if `val1 == val2`, otherwise returns `val1`. Preventing Division-by-Zero: Dividing by zero crashes SQL queries with a runtime error (`division by zero`). Fix: `SELECT total_revenue / NULLIF(total_orders, 0) FROM store_metrics;`. If `total_orders` is 0, `NULLIF(total_orders, 0)` returns `NULL`. In SQL, `number / NULL` cleanly evaluates to `NULL` without error. Wrapping in COALESCE provides a clean default: `COALESCE(total_revenue / NULLIF(total_orders, 0), 0) as avg_order_value`.",
            keyPoints = listOf(
                "COALESCE returns the first non-null argument with short-circuit evaluation",
                "NULLIF returns NULL if both arguments are equal, otherwise returns the first argument",
                "Dividing by NULL safely evaluates to NULL in SQL without throwing runtime exceptions",
                "Pattern: `COALESCE(numerator / NULLIF(denominator, 0), 0)` completely eliminates divide-by-zero errors",
                "Standardizes zero-safe calculations across financial and reporting metrics"
            ),
            difficulty = "Mid-Level"
        )
    )

    private fun part4(): List<InterviewQuestion> = listOf(
        InterviewQuestion(
            id = "iq_sql_058",
            trackId = "sql_interview",
            conceptId = "sql_querying",
            conceptName = "Advanced SQL Querying, Window Functions & CTEs",
            title = "JSONB Querying, Extraction Operators, and Path Indexing in PostgreSQL",
            question = "Explain PostgreSQL JSONB operators (`->`, `->>`, `@>`) and the difference between default GIN indexing vs `jsonb_path_ops`.",
            shortAnswer = "Operators: 1) `data->'key'`: Extracts JSON object field as `jsonb` (preserves quotes/types). 2) `data->>'key'`: Extracts JSON field as plain `text`. 3) `data @> '{\"status\": \"active\"}'`: JSON containment operator (returns true if left JSON contains right JSON). Indexing: 1) Default GIN index (`CREATE INDEX idx ON tbl USING gin (data)`): Indexes every key, value, and path. Supports containment (`@>`), key existence (`?`), any key existence (`?|`), and all key existence (`?&`). Drawback: larger index size. 2) `jsonb_path_ops` GIN index (`CREATE INDEX idx ON tbl USING gin (data jsonb_path_ops)`): Hashes the entire path from root to leaf into single 32-bit hash integers. Drastically smaller index footprint (often 60% smaller) and faster lookups, but ONLY supports the containment operator (`@>`).",
            keyPoints = listOf(
                "`->` extracts JSON object/array preserving jsonb type; `->>` extracts text value",
                "`@>` containment operator checks if a JSON document contains target key-value pairs",
                "Default GIN indexes all keys and values, supporting existence (`?`) and containment (`@>`)",
                "`jsonb_path_ops` hashes full paths to leaf values, creating significantly smaller and faster indexes",
                "`jsonb_path_ops` only accelerates containment queries (`@>`), not key existence (`?`)"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_059",
            trackId = "sql_interview",
            conceptId = "sql_querying",
            conceptName = "Advanced SQL Querying, Window Functions & CTEs",
            title = "Upsert Mechanics: PostgreSQL ON CONFLICT vs MySQL ON DUPLICATE KEY",
            question = "Compare PostgreSQL `INSERT ... ON CONFLICT DO UPDATE` with MySQL `INSERT ... ON DUPLICATE KEY UPDATE`. How do they handle concurrent updates?",
            shortAnswer = "1) PostgreSQL (`ON CONFLICT (unique_col) DO UPDATE SET ...`): Requires explicitly specifying the target unique constraint or index. Accesses candidate insert values via the pseudo-table `EXCLUDED` (e.g. `SET val = EXCLUDED.val`). Supports `DO NOTHING` for silent deduplication and partial index conflict targets (`WHERE status = 'active'`). Locks only the conflicting row. 2) MySQL (`ON DUPLICATE KEY UPDATE`): Does not require naming the conflicting index; automatically triggers on ANY primary key or unique index conflict. Accesses new values via `VALUES(val)` or alias (MySQL 8.0.20+ `AS new_row`). Caveat: If multiple unique indexes conflict simultaneously, MySQL updates the first conflicting row, which can create subtle data anomalies. Both operations acquire exclusive row locks during conflict resolution.",
            keyPoints = listOf(
                "PostgreSQL requires specifying the exact target unique constraint or column expression",
                "PostgreSQL uses `EXCLUDED.column` to reference incoming inserted values",
                "MySQL triggers on any conflicting unique key or primary key without explicit declaration",
                "MySQL table with multiple unique keys can update unexpected rows on conflict",
                "Both patterns acquire exclusive locks on conflicting rows, ensuring atomic idempotent writes"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_060",
            trackId = "sql_interview",
            conceptId = "sql_querying",
            conceptName = "Advanced SQL Querying, Window Functions & CTEs",
            title = "High-Performance Bulk Data Ingestion Techniques",
            question = "Compare the performance of row-by-row INSERTs, multi-row batch INSERTs, and binary/bulk loading (`COPY` / `LOAD DATA INFILE`). What makes bulk loaders 10x faster?",
            shortAnswer = "1) Row-by-Row INSERT: Worst approach. 100,000 queries require 100,000 network roundtrips, SQL parsing passes, query plan generations, and individual transaction commit fsyncs (hours to run). 2) Multi-Row Batch INSERT (`INSERT INTO t VALUES (...), (...), (...)`): Batches 1,000 rows into one SQL statement. Dramatically reduces network roundtrips and parsing overhead (10x-20x faster than row-by-row). 3) Bulk Loaders (`COPY table FROM stdin` in Postgres, `LOAD DATA INFILE` in MySQL): 50x-100x faster. Bypasses the SQL parser, query planner, and executor entirely. Streams raw binary/CSV records directly into storage engine table page builders, minimizes WAL logging (under `wal_level=minimal`), and batches secondary index maintenance into sort-and-merge passes instead of random leaf insertions.",
            keyPoints = listOf(
                "Single-row inserts suffer from massive network roundtrips, parsing overhead, and fsync commits",
                "Multi-row batch inserts combine thousands of rows per statement, reducing network latency",
                "Bulk loaders (COPY, LOAD DATA INFILE) stream directly into storage engine page buffers",
                "Bypasses SQL parser, query optimizer, and execution tree machinery completely",
                "Can defer index building and disable foreign key checks during initial load for maximum speed"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_061",
            trackId = "sql_interview",
            conceptId = "sql_schema",
            conceptName = "Relational Schema Design, Normalization & Constraints",
            title = "Database Normalization Forms: 1NF through BCNF",
            question = "Define First (1NF), Second (2NF), Third (3NF), and Boyce-Codd Normal Form (BCNF). What data anomalies does each form eliminate?",
            shortAnswer = "1) 1NF (Atomic Values): Every column contains atomic (indivisible) scalar values; no repeating groups, arrays, or comma-separated lists. 2) 2NF (No Partial Dependencies): Must be in 1NF, and every non-key attribute must depend on the WHOLE candidate key (relevant only for composite primary keys). Eliminates update anomalies on partial composite keys. 3) 3NF (No Transitive Dependencies): Must be in 2NF, and no non-key attribute can depend on another non-key attribute (`A -> B -> C`). Eliminates insertion/deletion anomalies (e.g. storing department_name in employee table). 4) BCNF (Strict 3NF): For every non-trivial functional dependency `X -> Y`, `X` must be a superkey. Eliminates anomalies when multiple overlapping candidate keys exist.",
            keyPoints = listOf(
                "1NF enforces atomic scalar attributes and eliminates repeating groups/arrays",
                "2NF eliminates partial dependencies where non-key attributes depend on subset of composite key",
                "3NF eliminates transitive dependencies (non-key attribute depending on non-key attribute)",
                "BCNF requires that every determinant in a functional dependency is a candidate superkey",
                "Eliminates update, insertion, and deletion anomalies across entity relationships"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_062",
            trackId = "sql_interview",
            conceptId = "sql_schema",
            conceptName = "Relational Schema Design, Normalization & Constraints",
            title = "Intentional Denormalization: Latency vs Synchronization Risk",
            question = "When is intentional denormalization justified in high-scale relational databases, and how do you protect against data synchronization anomalies?",
            shortAnswer = "Justification: When read latency must be minimized on high-traffic queries involving 5+ table joins, or when computing aggregate summaries (e.g. `order_count`, `total_spent`, `current_balance`) on tables with billions of rows. Reading a pre-computed denormalized column is O(1) compared to an expensive multi-table join or aggregate scan. Mitigating Synchronization Risk: 1) Transactional Invariants: Update both primary and denormalized columns in the SAME database transaction. 2) Database Triggers: Use triggers to maintain aggregated counters atomically. 3) Change Data Capture (CDC): Stream transaction logs via Debezium/Kafka to an asynchronous worker that updates denormalized read views. 4) Scheduled Reconciliation: Run nightly batch jobs to detect and repair drift.",
            keyPoints = listOf(
                "Denormalization trades storage space and write complexity for ultra-low read latency",
                "Eliminates expensive multi-table joins and high-cost aggregate calculations at runtime",
                "Creates severe synchronization anomaly risks (data divergence across tables)",
                "Mitigation via atomic same-transaction writes or database triggers",
                "Requires asynchronous CDC or nightly reconciliation jobs to audit and repair data drift"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_063",
            trackId = "sql_interview",
            conceptId = "sql_schema",
            conceptName = "Relational Schema Design, Normalization & Constraints",
            title = "Primary Key Selection: Auto-Increment vs UUIDv4 vs UUIDv7",
            question = "Compare Auto-Increment BIGINT, random UUIDv4, and time-ordered UUIDv7 as primary keys in clustered index tables (MySQL InnoDB / PostgreSQL).",
            shortAnswer = "1) Auto-Increment BIGINT (8 bytes): Highly compact. Sequential insertion appends to the rightmost B-Tree leaf, causing ZERO mid-tree page splits and maximum cache locality. Drawback: Predictable (vulnerable to URL scraping/enumeration attacks) and cannot be generated client-side before DB insert. 2) UUIDv4 (16 bytes): Globally unique and client-generatable. Disaster for B-Trees: Completely random distribution forces random writes across the entire clustered index, triggering constant 50% page splits, bloated indexes, and destroyed buffer cache hit ratios. 3) UUIDv7 (16 bytes, RFC 9562): The modern standard. Combines a 48-bit Unix timestamp with random bits. Sequential over time (appends like BIGINT, eliminating page splits) while providing global uniqueness and client-side generation without enumeration risks.",
            keyPoints = listOf(
                "Auto-increment BIGINT is compact (8B) and sequential, maximizing B-Tree append efficiency",
                "BIGINT drawbacks: enumeration attack vulnerability and centralized ID allocation bottleneck",
                "Random UUIDv4 scatters inserts across all B-Tree pages, causing severe page splits and fragmentation",
                "UUIDv7 combines 48-bit millisecond timestamp with random bits, preserving chronological sorting",
                "UUIDv7 delivers the append efficiency of BIGINT with the distributed uniqueness of UUID"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_064",
            trackId = "sql_interview",
            conceptId = "sql_schema",
            conceptName = "Relational Schema Design, Normalization & Constraints",
            title = "Surrogate Keys vs Natural Keys Architecture",
            question = "What are the trade-offs between Natural Keys (e.g. SSN, ISBN, email) and Surrogate Keys (auto-increment ID, UUID) in production schemas?",
            shortAnswer = "1) Natural Keys: Formed by real-world business attributes inherent to the entity. Pros: Eliminates a synthetic ID column; self-documenting in foreign keys. Cons: Real-world business requirements change (e.g. users change their email, company merges change customer codes). Updating a natural key cascades updates across millions of rows in all referencing child tables, causing massive lock escalation. Also, natural string keys make secondary indexes bloated. 2) Surrogate Keys: Artificial, meaningless identifiers (e.g. auto-increment BIGINT or UUID). Pros: Completely decoupled from business domain logic; NEVER changes over the lifetime of the record; compact integer size keeps foreign keys and secondary indexes small and fast. Best Practice: Always use an immutable surrogate key as PRIMARY KEY, with a UNIQUE constraint on the natural key.",
            keyPoints = listOf(
                "Natural keys represent real-world domain attributes (email, tax ID, ISBN)",
                "Natural key mutations trigger cascading updates across all child foreign key tables",
                "Natural string keys bloat index sizes and degrade B-Tree join performance",
                "Surrogate keys provide immutable, synthetic identifiers unaffected by business changes",
                "Industry standard: Surrogate primary key paired with unique constraint on natural candidate key"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_sql_065",
            trackId = "sql_interview",
            conceptId = "sql_schema",
            conceptName = "Relational Schema Design, Normalization & Constraints",
            title = "Foreign Key Cascading Rules and Production Hazards",
            question = "Explain `ON DELETE CASCADE`, `SET NULL`, `RESTRICT`, and `NO ACTION`. Why do high-scale production systems often forbid `ON DELETE CASCADE`?",
            shortAnswer = "Rules: 1) `CASCADE`: Deleting the parent automatically deletes all referencing child rows. 2) `SET NULL`: Sets child foreign key columns to NULL. 3) `RESTRICT`: Rejects the parent delete immediately if any child row exists. 4) `NO ACTION`: In standard SQL, defers the integrity check until the end of the transaction (`DEFERRABLE`), whereas RESTRICT checks immediately. Hazards of `ON DELETE CASCADE` in Production: Deleting a single parent user row in a large multi-tenant system can trigger an uncontrolled cascading deletion of millions of rows across 20+ tables. This generates massive transaction logs, holds thousands of row and table locks for minutes, blows up replication lag, exhausts database connections, and cannot be interrupted safely. Production best practice: Forbid cascade in schema; perform chunked soft-deletes or batch cleanup jobs.",
            keyPoints = listOf(
                "CASCADE deletes all referencing child records recursively upon parent deletion",
                "SET NULL reassigns child foreign key pointers to null upon parent deletion",
                "RESTRICT rejects parent deletion immediately if child records are detected",
                "NO ACTION defers referential checks until transaction commit when marked deferrable",
                "Production hazard: Uncontrolled cascading deletes lock dozens of tables, causing outages"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_066",
            trackId = "sql_interview",
            conceptId = "sql_schema",
            conceptName = "Relational Schema Design, Normalization & Constraints",
            title = "CHECK Constraints vs Application-Layer Validation",
            question = "Why is application-layer validation insufficient on its own for database invariants? How do database `CHECK` constraints enforce data integrity?",
            shortAnswer = "Application validation (e.g. Hibernate/Hibernate Validator or Spring validation) only validates data passing through that specific application service. It fails when: 1) Concurrent race conditions bypass check-then-act logic (e.g. two concurrent transactions both pass balance check). 2) Direct database updates occur via data migrations, analytics scripts, CLI tools (`psql`/`mysql`), or background batch workers. 3) Multi-service architectures connect multiple microservices to the same DB. Database `CHECK` constraints (`CHECK (price > 0 AND discount_percentage BETWEEN 0 AND 100)`) are enforced inside the database kernel at the physical row mutation level. Any transaction that violates the constraint is immediately aborted with a constraint violation error, regardless of source.",
            keyPoints = listOf(
                "Application validation is easily bypassed by direct migrations, batch scripts, and CLI tools",
                "Concurrent race conditions bypass application-layer check-then-act validations",
                "CHECK constraints are enforced atomically at the database kernel storage level",
                "Prevents illegal states (negative account balances, invalid state transition flags)",
                "Defense-in-depth: Validate in application for UX, enforce in database for mathematical guarantee"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_sql_067",
            trackId = "sql_interview",
            conceptId = "sql_schema",
            conceptName = "Relational Schema Design, Normalization & Constraints",
            title = "Unique Constraints vs Unique Indexes and NULL Handling",
            question = "How do PostgreSQL, MySQL, and standard SQL handle multiple `NULL` values in UNIQUE constraints? What is `UNIQUE NULLS NOT DISTINCT`?",
            shortAnswer = "SQL Standard Definition: Two `NULL` values are never equal (`NULL != NULL`). Therefore, in standard SQL, PostgreSQL (default), and MySQL InnoDB, a `UNIQUE` constraint permits MULTIPLE rows containing `NULL` in the unique column (e.g. ten users can have `phone_number = NULL`). The Pitfall: If you intend for `phone_number` to be optional, but when present, only one user may have it, standard unique works. BUT if you have a composite unique key `(tenant_id, code)` where `code` is NULL, standard unique permits unlimited duplicate `(tenant_1, NULL)` rows! Solutions: 1) PostgreSQL 15+ added `UNIQUE NULLS NOT DISTINCT`: Treats NULLs as equal values, allowing only a single NULL in the unique index. 2) Partial Unique Index: `CREATE UNIQUE INDEX ... WHERE code IS NOT NULL` combined with application rules.",
            keyPoints = listOf(
                "SQL standard treats NULLs as distinct values; unique constraints allow multiple NULLs",
                "MySQL and default PostgreSQL permit unlimited duplicate NULLs in UNIQUE columns",
                "Composite unique keys with nullable columns permit duplicate rows with identical non-null keys",
                "PostgreSQL 15+ introduces `UNIQUE NULLS NOT DISTINCT` to treat NULLs as equal",
                "Partial unique indexes provide flexible deduplication over non-null subsets of data"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_068",
            trackId = "sql_interview",
            conceptId = "sql_schema",
            conceptName = "Relational Schema Design, Normalization & Constraints",
            title = "Soft Deletes (`deleted_at`) Architecture and Index Pollution",
            question = "What architectural problems arise from using Soft Deletes (`deleted_at IS NOT NULL`)? How do you resolve unique constraint conflicts on soft-deleted rows?",
            shortAnswer = "Problems with Soft Deletes: 1) Query Pollution: Every single query, join, and foreign key check must append `WHERE deleted_at IS NULL`. Forgetting it in one query leaks deleted user data. 2) Table and Index Bloat: Deleted rows remain in the table forever, swelling B-Tree indexes and degrading buffer pool hit rates. 3) Unique Constraint Conflicts: If a user soft-deletes their account with `email = 'bob@example.com'`, a new user cannot register with `bob@example.com` because the unique constraint on `email` rejects it! Solutions: 1) Partial Unique Index: `CREATE UNIQUE INDEX uq_user_email ON users (email) WHERE deleted_at IS NULL;`. Allows unlimited deleted 'bob@example.com' rows while enforcing uniqueness on active accounts. 2) Dedicated Archive Table: Move deleted rows to an `archive_users` table atomically, keeping the main table lean.",
            keyPoints = listOf(
                "Soft deletes require appending `WHERE deleted_at IS NULL` to every application query",
                "Bloats active table pages and secondary indexes with dead historical records",
                "Violates standard unique constraints when re-registering previously deleted unique keys",
                "Partial unique index (`WHERE deleted_at IS NULL`) enforces uniqueness strictly on active records",
                "Architectural alternative: Atomically move deleted records to dedicated archive tables"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_069",
            trackId = "sql_interview",
            conceptId = "sql_schema",
            conceptName = "Relational Schema Design, Normalization & Constraints",
            title = "Multi-Tenancy Architectures: Database vs Schema vs Shared Table",
            question = "Compare the 3 primary multi-tenancy database architectures: Database-per-tenant, Schema-per-tenant, and Shared-table with Tenant ID. Detail trade-offs.",
            shortAnswer = "1) Database-per-Tenant: Each tenant has a completely separate physical database instance. Pros: Absolute data isolation, simplified compliance (HIPAA/GDPR), easy per-tenant backup/restore and vertical scaling. Cons: Massive infrastructure cost; connection pool explosion; complex cross-tenant analytics and schema migration orchestration across thousands of DBs. 2) Schema-per-Tenant (PostgreSQL namespaces): Single database instance with separate schemas per tenant. Pros: Logical data isolation with shared compute; simpler cross-tenant queries. Cons: Postgres catalog bloat (thousands of schemas exhausts shared memory and slows pg_dump). 3) Shared-Table with Tenant ID (Discriminator Column): All tenants share the same tables, differentiated by `tenant_id`. Pros: Lowest cost, effortless schema migrations, efficient hardware utilization. Cons: Risk of catastrophic data leak bugs if a query misses `tenant_id`; requires Row-Level Security (RLS) or mandatory ORM filters.",
            keyPoints = listOf(
                "Database-per-tenant offers absolute physical isolation and easy compliance at highest cost",
                "Schema-per-tenant provides logical namespace isolation but causes system catalog bloat at scale",
                "Shared-table with tenant_id provides maximum resource efficiency and simplest schema maintenance",
                "Shared-table introduces tenant data leakage hazard if query filtering fails",
                "Shared-table requires automated Row-Level Security (RLS) or framework-enforced query interceptors"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_070",
            trackId = "sql_interview",
            conceptId = "sql_schema",
            conceptName = "Relational Schema Design, Normalization & Constraints",
            title = "PostgreSQL Row-Level Security (RLS) Mechanics",
            question = "How does PostgreSQL Row-Level Security (RLS) enforce multi-tenant isolation at the database engine level? How do you prevent RLS performance degradation?",
            shortAnswer = "RLS applies security filter policies directly inside the query planner: `ALTER TABLE documents ENABLE ROW LEVEL SECURITY; CREATE POLICY tenant_isolation_policy ON documents FOR ALL USING (tenant_id = current_setting('app.current_tenant_id')::bigint);`. Under the hood, Postgres rewrites every query AST on `documents` to inject the policy predicate into the WHERE clause automatically. Even `SELECT * FROM documents` without a WHERE clause only returns the active tenant's rows. Performance Best Practices: 1) Index the tenant column: Ensure a composite index exists with `tenant_id` as the leftmost column (`(tenant_id, created_at)`). 2) Keep policy functions lightweight: Avoid subqueries in policies (e.g. `USING (tenant_id IN (SELECT ...))`), which can turn index scans into expensive nested loops. 3) BypassRLS: Table owners and superusers bypass RLS by default; use `FORCE ROW LEVEL SECURITY` to prevent accidental developer leakage.",
            keyPoints = listOf(
                "RLS rewrites query Abstract Syntax Trees (ASTs) to inject security policy predicates automatically",
                "Protects against data leakage even if application developers omit tenant_id in WHERE clauses",
                "Requires index on tenant_id column to prevent full table scans on policy evaluations",
                "Avoid subqueries inside policy definitions to prevent catastrophic nested loop overhead",
                "`FORCE ROW LEVEL SECURITY` ensures table owners cannot accidentally bypass policies"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_071",
            trackId = "sql_interview",
            conceptId = "sql_schema",
            conceptName = "Relational Schema Design, Normalization & Constraints",
            title = "Temporal Tables and System-Versioned Data",
            question = "What are System-Versioned Temporal Tables (SQL:2011 standard), and how do they enable time-travel queries like `AS OF SYSTEM TIME`?",
            shortAnswer = "System-versioned temporal tables track the full audit history of all data changes over time. The table defines a system time period: `PERIOD FOR SYSTEM_TIME (sys_start, sys_end)`. When a row is inserted, `sys_start` is set to the current transaction timestamp and `sys_end` is set to infinity (`9999-12-31`). When an update occurs: 1) The current row's `sys_end` is updated to current timestamp and archived to a hidden history table. 2) The new row version is inserted with `sys_start = current_timestamp` and `sys_end = infinity`. Time-Travel Queries: The application can query historical states seamlessly: `SELECT * FROM accounts FOR SYSTEM_TIME AS OF '2023-01-01 00:00:00' WHERE id = 42;`. The database automatically routes the query to the history table without requiring custom audit log tables or triggers.",
            keyPoints = listOf(
                "SQL:2011 temporal standard tracks complete historical record evolution automatically",
                "Maintains sys_start and sys_end timestamps indicating validity intervals for every row version",
                "Updates archive superseded versions to history tables without mutating historical records",
                "`FOR SYSTEM_TIME AS OF` allows querying exact database states at any historical point in time",
                "Native in SQL Server and MariaDB; implemented via triggers or temporal extensions in PostgreSQL"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_072",
            trackId = "sql_interview",
            conceptId = "sql_schema",
            conceptName = "Relational Schema Design, Normalization & Constraints",
            title = "EAV (Entity-Attribute-Value) Anti-Pattern vs JSONB",
            question = "Why is the traditional EAV (Entity-Attribute-Value) pattern considered an anti-pattern? How does PostgreSQL JSONB provide a superior architectural replacement?",
            shortAnswer = "EAV Pattern: Uses a generic table `(entity_id, attribute_name, attribute_value)` to store dynamic attributes. Flaws: 1) Query Nightmare: Fetching an entity with 10 attributes requires 10 self-joins or complex pivoting aggregation. 2) Data Type Loss: All values are stored as strings (`VARCHAR`); no database-level numeric or date validation. 3) Foreign Key / Constraint Loss: Cannot enforce NOT NULL, CHECK, or FK constraints on dynamic attributes. JSONB Replacement: Store core fixed attributes as strongly-typed relational columns, and dynamic/variable attributes in a single `JSONB` column (`extra_attributes JSONB`). Benefits: 1) Zero joins to fetch all dynamic attributes. 2) Retains native types (integers, booleans, nested arrays). 3) GIN indexing accelerates containment queries (`@>`). 4) JSON schema constraints can be enforced via `CHECK (jsonb_matches_schema(...))`.",
            keyPoints = listOf(
                "EAV requires multiple expensive self-joins to assemble a single entity with its attributes",
                "EAV loses native column types, forcing all values into generic strings without constraint checks",
                "JSONB combines relational schema discipline with flexible semi-structured attribute storage",
                "Eliminates join overhead by keeping dynamic attributes co-located in the row tuple",
                "GIN indexing on JSONB enables high-performance searches across dynamic nested attributes"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_073",
            trackId = "sql_interview",
            conceptId = "sql_schema",
            conceptName = "Relational Schema Design, Normalization & Constraints",
            title = "Polymorphic Associations: STI vs CTI vs Concrete Table Inheritance",
            question = "Compare Single Table Inheritance (STI), Class Table Inheritance (CTI / Joined), and Concrete Table Inheritance in relational database design.",
            shortAnswer = "1) Single Table Inheritance (STI): All classes in an inheritance hierarchy are stored in ONE table with a `dtype` discriminator column. Pros: Zero joins to query polymorphic entities; fastest reads and updates. Cons: Table contains many NULL columns for subclass-specific attributes; cannot enforce NOT NULL constraints on subclass fields. 2) Class Table Inheritance (CTI / Joined Table): Base class has a table, and each subclass has a table referencing the base table's primary key. Pros: Fully normalized; NOT NULL constraints enforced on subclass fields; no wasted NULL space. Cons: Every query requires a `LEFT JOIN` across all subclass tables, degrading performance as hierarchy grows. 3) Concrete Table Inheritance (Table-per-Class): Each subclass has its own standalone table duplicating base columns. Pros: No joins for single-type queries. Cons: Polymorphic queries across all types require expensive `UNION ALL` across all tables.",
            keyPoints = listOf(
                "STI stores entire class hierarchy in one table using a discriminator column (fast, but many NULLs)",
                "CTI stores base and subclasses in separate tables linked by FKs (normalized, but requires joins)",
                "Concrete Table Inheritance duplicates base columns into separate subclass tables",
                "STI is preferred when subclasses have few unique attributes and performance is paramount",
                "CTI is preferred when strict NOT NULL database constraints are required on subclass attributes"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_074",
            trackId = "sql_interview",
            conceptId = "sql_schema",
            conceptName = "Relational Schema Design, Normalization & Constraints",
            title = "Database ENUM Types vs Lookup Reference Tables",
            question = "What are the trade-offs between native database ENUM types (`CREATE TYPE status_enum`) and Foreign Key Lookup Reference Tables?",
            shortAnswer = "1) Native ENUM Types (`CREATE TYPE order_status AS ENUM ('PENDING', 'SHIPPED')`): Pros: Extremely compact storage (stored internally as a 4-byte integer in Postgres); self-documenting in schema; no table joins needed to display status. Cons: Schema migration friction: Adding a value is supported (`ALTER TYPE ... ADD VALUE`), but removing or renaming a value requires dropping and recreating the enum type and altering all dependent tables (requires metadata locks). 2) Lookup Reference Tables (`order_statuses (id, code, description)`): Pros: Dynamic runtime modification (adding, disabling with `is_active=false`, or renaming statuses requires a simple `INSERT`/`UPDATE` without DDL locks); supports metadata attributes (color_hex, display_order, localized_labels). Cons: Requires `JOIN` to resolve status codes; slightly larger storage footprint.",
            keyPoints = listOf(
                "ENUM types store values internally as compact integers without requiring join operations",
                "ENUM alterations (renaming, deleting values) require restrictive DDL metadata locks",
                "Lookup tables allow zero-downtime runtime updates via standard DML transactions",
                "Lookup tables support rich auxiliary metadata attributes (descriptions, localization, active flags)",
                "Use ENUM for truly static domain constants (days of week); use lookup tables for business states"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_sql_075",
            trackId = "sql_interview",
            conceptId = "sql_schema",
            conceptName = "Relational Schema Design, Normalization & Constraints",
            title = "Financial Precision: DECIMAL / NUMERIC vs Floating Point Types",
            question = "Why is storing monetary values in `FLOAT` or `DOUBLE` a critical architectural error? How does `DECIMAL` / `NUMERIC` guarantee precision?",
            shortAnswer = "`FLOAT` and `DOUBLE` use IEEE 754 binary floating-point representation. Many decimal fractions (like `0.1` or `0.01` - one cent) cannot be represented accurately in binary and result in infinite repeating fractions (e.g. `0.1 + 0.2 = 0.30000000000000004`). In financial systems, accumulating floating-point rounding errors across millions of transactions leads to ledger imbalances, tax discrepancies, and auditing failures. `DECIMAL(precision, scale)` / `NUMERIC` stores numbers as exact, base-10 packed decimal digits (binary-coded decimal). Arithmetic operations are calculated using software-driven exact decimal algorithms rather than CPU floating-point registers. Alternative: Store money as integers representing cents/sats (`BIGINT`), multiplying by 100 on input.",
            keyPoints = listOf(
                "IEEE 754 floating-point types cannot represent base-10 fractions (like 0.1) accurately in binary",
                "Floating-point rounding errors accumulate into significant ledger balance discrepancies",
                "DECIMAL/NUMERIC stores exact base-10 digits using packed decimal representations",
                "Performs software-driven exact mathematical operations with zero precision loss",
                "Alternative standard: Store monetary balances as integer minor units (cents) using BIGINT"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_sql_076",
            trackId = "sql_interview",
            conceptId = "sql_schema",
            conceptName = "Relational Schema Design, Normalization & Constraints",
            title = "Timestamp Storage: TIMESTAMPTZ vs TIMESTAMP WITHOUT TIME ZONE",
            question = "Explain the difference between `TIMESTAMPTZ` and `TIMESTAMP` in PostgreSQL. Why should distributed systems always use `TIMESTAMPTZ`?",
            shortAnswer = "1) `TIMESTAMP WITHOUT TIME ZONE`: Stores a naive year-month-day-hour-minute-second string with NO timezone offset. If client A in New York inserts '2024-06-01 12:00:00' and client B in Tokyo queries it, Tokyo sees '12:00:00'—losing 13 hours of temporal reality. Daylight saving transitions also create duplicate or missing hours. 2) `TIMESTAMPTZ (TIMESTAMP WITH TIME ZONE)`: Despite its name, PostgreSQL does NOT store the timezone string. It converts the incoming timestamp to UTC and stores an 8-byte UTC integer (microseconds since 2000-01-01). When queried, it converts the UTC integer into the querying client's session timezone. Benefits: Absolute universal time comparability across global microservices, immune to client timezone misconfigurations, and accurate interval calculations.",
            keyPoints = listOf(
                "`TIMESTAMP WITHOUT TIME ZONE` stores naive wall-clock time, losing geographical context",
                "`TIMESTAMPTZ` converts all incoming timestamps to UTC and stores them as universal UTC integers",
                "Converts UTC back to the client's session timezone upon SELECT query execution",
                "Guarantees accurate ordering and interval calculations across distributed global clients",
                "Always store timestamps in UTC via TIMESTAMPTZ; store user's local timezone string in a separate column if needed"
            ),
            difficulty = "Mid-Level"
        )
    )

    private fun part5(): List<InterviewQuestion> = listOf(
        InterviewQuestion(
            id = "iq_sql_077",
            trackId = "sql_interview",
            conceptId = "sql_schema",
            conceptName = "Relational Schema Design, Normalization & Constraints",
            title = "The Expand and Contract Pattern for Zero-Downtime Schema Evolution",
            question = "How do you rename a column or change a data type on a high-traffic table with zero downtime using the Expand and Contract (Parallel Run) pattern?",
            shortAnswer = "Renaming a column directly (`ALTER TABLE t RENAME COLUMN a TO b`) breaks running application instances that still expect column `a`. The Expand and Contract pattern safely orchestrates this across multiple deployments: 1) Expand Phase: Add the new column `b` as nullable. Deploy application version 1.1, which reads from `a` but writes to BOTH `a` and `b` (dual-writing). 2) Backfill Phase: Run an asynchronous background script to copy historical data from `a` to `b` in small batches (`UPDATE ... WHERE id BETWEEN ...`). 3) Switch Phase: Deploy application version 1.2, which now reads from `b` and writes to `b` (dual-writing back to `a` for fallback safety). 4) Contract Phase: Verify stability, deploy version 1.3 that only accesses `b`, and issue `ALTER TABLE t DROP COLUMN a`.",
            keyPoints = listOf(
                "Direct column renames or type changes cause immediate application downtime during rolling deploys",
                "Expand phase introduces new column while application dual-writes to both old and new columns",
                "Backfill phase populates historical data in small, non-locking batch transactions",
                "Switch phase flips application reads to the new column with rollback safety intact",
                "Contract phase cleans up the deprecated column after verifying application stability"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_078",
            trackId = "sql_interview",
            conceptId = "sql_schema",
            conceptName = "Relational Schema Design, Normalization & Constraints",
            title = "Zero-Downtime DDL: pt-online-schema-change vs gh-ost",
            question = "How do tools like `pt-online-schema-change` and GitHub's `gh-ost` alter massive MySQL tables without locking? Compare trigger-based vs binlog-based replication.",
            shortAnswer = "1) `pt-online-schema-change` (Trigger-based): Creates a new shadow table with the altered schema, copies historical rows in chunks, and attaches 3 triggers (`AFTER INSERT`, `AFTER UPDATE`, `AFTER DELETE`) on the original table to mirror live mutations to the shadow table. Flaws: Triggers run inside the caller's transaction, adding latency to production queries and frequently causing write deadlocks under high concurrency. 2) `gh-ost` (Triggerless, Binlog-based): Connects to MySQL as an external replica, reads the Binary Log (binlog) directly, transforms binlog events, and applies them asynchronously to the ghost shadow table. Benefits: ZERO triggers on production tables, zero lock contention, and can be throttled or paused dynamically if master database load or replication lag spikes.",
            keyPoints = listOf(
                "`pt-online-schema-change` uses database triggers to sync concurrent DML to a shadow table",
                "Database triggers add latency to production writes and frequently cause deadlocks",
                "`gh-ost` operates trigger-free by streaming and replaying MySQL binlog events asynchronously",
                "Shadow table copy proceeds in throttled micro-chunks to avoid CPU or I/O spikes",
                "Final table cutover executes via atomic `RENAME TABLE original TO old, shadow TO original`"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_079",
            trackId = "sql_interview",
            conceptId = "sql_schema",
            conceptName = "Relational Schema Design, Normalization & Constraints",
            title = "Relational Schema Anti-Patterns and Architectural Debt",
            question = "Identify three classic relational schema anti-patterns: Comma-Separated Values in VARCHAR, God Tables, and Missing Foreign Keys. Explain their structural damage.",
            shortAnswer = "1) Comma-Separated Values (`tags VARCHAR = 'tech,news,ai'`): Destroys 1NF. Cannot index individual elements, cannot enforce foreign key validity, requires slow `LIKE '%news%'` full table scans, and updating a single tag requires locking and rewriting the entire string. Fix: Normalized junction table or PostgreSQL array/JSONB with GIN index. 2) God Tables (Tables with 100+ columns): Combines unrelated business domains into one massive entity. Causes wide row sizes that blow past page limits (forcing off-page TOAST/LOB storage), increases buffer pool thrashing, and creates high lock contention between unrelated teams. Fix: Decompose vertically into focused domain tables with 1-to-1 relationships. 3) Missing Foreign Keys: Orphan records accumulate, query planner cannot use join elimination optimizations, and data integrity is permanently compromised.",
            keyPoints = listOf(
                "Comma-separated values violate 1NF, disable index seeks, and compromise referential integrity",
                "God tables with 100+ columns cause off-page LOB overflow and severe buffer pool waste",
                "Wide tables create high lock contention across separate application domains",
                "Missing foreign keys lead to orphaned child records and disable query planner join optimizations",
                "Proper vertical partitioning and junction tables preserve relational integrity and performance"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_sql_080",
            trackId = "sql_interview",
            conceptId = "sql_schema",
            conceptName = "Relational Schema Design, Normalization & Constraints",
            title = "Enforcing Race-Condition-Proof Invariants via Exclusion Constraints",
            question = "How does PostgreSQL's `EXCLUDE USING gist` (Exclusion Constraints) prevent overlapping calendar bookings where unique constraints fail?",
            shortAnswer = "A unique constraint can only enforce equality (`room_id = 5`). It CANNOT prevent overlapping time ranges (e.g. Booking A: 10:00-11:00, Booking B: 10:30-11:30) because the timestamps are not equal. Application checks (`SELECT COUNT(*) WHERE overlaps`) fail due to race conditions when two users book simultaneously. PostgreSQL Exclusion Constraints enforce that if any two rows are compared on the specified operators, at least one comparison returns false: `ALTER TABLE room_reservations ADD CONSTRAINT no_overlapping_bookings EXCLUDE USING gist (room_id WITH =, reservation_period WITH &&);`. If two concurrent transactions attempt to book room 5 with overlapping `tsrange` intervals (`&&` operator), the database GiST index detects the conflict atomically and aborts the second transaction.",
            keyPoints = listOf(
                "Standard UNIQUE constraints can only test for exact equality, failing on range overlaps",
                "Application-level overlap checks suffer from concurrency race conditions under high traffic",
                "Exclusion constraints (`EXCLUDE USING gist`) enforce generalized mathematical invariants",
                "Uses GiST range index to detect overlapping temporal intervals (`&&`) atomically",
                "Eliminates double-booking bugs in hotel, flight, and meeting room reservation architectures"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_081",
            trackId = "sql_interview",
            conceptId = "sql_internals",
            conceptName = "Storage Engine Internals, Buffer Pools & Memory",
            title = "Storage Engine Architecture: InnoDB vs MyRocks vs PostgreSQL Heap",
            question = "Compare the underlying storage architectures of MySQL InnoDB (B+ Tree), MyRocks (LSM-Tree), and PostgreSQL (Heap with WAL). What workloads favor each?",
            shortAnswer = "1) MySQL InnoDB (B+ Tree): Organizes data into clustered index B+ Trees. Updates are in-place on 16KB pages. Ideal for read-heavy and mixed OLTP with point lookups and primary key range queries. High write amplification on random inserts due to page splits and doublewrite buffers. 2) MyRocks (LSM-Tree via RocksDB): Appends writes sequentially to in-memory MemTables and flushes immutable SSTables to disk with leveled compaction. Drastically lower write amplification (often 5x-10x less SSD wear) and 50% better data compression than InnoDB. Ideal for massive write-heavy ingestion and SSD longevity. 3) PostgreSQL (Heap Storage): Stores rows in an unordered append-only heap; secondary indexes store physical tuple IDs (CTID). MVCC creates new tuples on update, requiring background VACUUM. Ideal for complex querying and rich indexing (GIN, GiST, BRIN).",
            keyPoints = listOf(
                "InnoDB uses clustered B+ Trees with in-place page updates; optimized for low-latency OLTP point reads",
                "MyRocks uses LSM-Trees with sequential append-only writes; superior write throughput and SSD endurance",
                "PostgreSQL uses an unordered heap where all indexes point to physical tuple CTIDs",
                "PostgreSQL MVCC writes new row versions to heap, requiring VACUUM garbage collection",
                "Choose MyRocks for high-volume append logs; InnoDB for standard OLTP; Postgres for complex analytics/types"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_082",
            trackId = "sql_interview",
            conceptId = "sql_internals",
            conceptName = "Storage Engine Internals, Buffer Pools & Memory",
            title = "Database Physical Page Anatomy and Slotted Pages",
            question = "Explain the physical page anatomy of an 8KB (PostgreSQL) or 16KB (InnoDB) database data block. How do Slotted Pages work?",
            shortAnswer = "Databases read and write data in fixed-size blocks called Pages. A Slotted Page layout solves variable-length row fragmentation: 1) Page Header (at top of page): Stores metadata, LSN (Log Sequence Number for crash recovery), transaction info, and free space boundary pointers. 2) Line Pointers / Slots (grows downward): An array of compact 4-byte pointers `(offset, length)` pointing to the actual row location within the page. 3) Row Data (grows upward from the bottom of the page): The actual raw record bytes. 4) Free Space: The unallocated gap in the middle. Benefits: When a row is updated or deleted, the row bytes can be shifted and compacted within the page without changing its external index pointer, because secondary indexes only point to the stable Line Pointer Index `(PageNumber, SlotNumber)`.",
            keyPoints = listOf(
                "Fixed-size data pages (8KB Postgres, 16KB InnoDB) represent the fundamental disk I/O unit",
                "Page header contains page LSN, transaction metadata, and free space boundary pointers",
                "Slotted page array grows downward from the top; raw row data grows upward from the bottom",
                "Secondary indexes point to stable slot index numbers, not raw physical byte offsets",
                "Enables defragmentation and variable-length row updates without updating external index pointers"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_083",
            trackId = "sql_interview",
            conceptId = "sql_internals",
            conceptName = "Storage Engine Internals, Buffer Pools & Memory",
            title = "Buffer Pool LRU Management: The Midpoint Insertion Strategy",
            question = "Why does a naive LRU (Least Recently Used) cache fail for database buffer pools? How does InnoDB's Midpoint Insertion Strategy solve scan resistance?",
            shortAnswer = "Failure of Naive LRU: A large table scan (`SELECT * FROM 50GB_table`) reads millions of pages into the buffer pool once and never accesses them again. In a naive LRU, these one-hit-wonder pages enter at the very top (head), pushing all frequently-accessed hot working-set pages (e.g. user lookups, primary key indexes) out of RAM onto disk. This destroys database throughput for minutes. InnoDB Midpoint Insertion Solution: InnoDB splits the LRU list into two sublists: 'New/Young' (default 63%) and 'Old' (default 37%). When a new page is read from disk, it is inserted at the MIDPOINT (the head of the Old sublist), not the head of the LRU! It only moves to the Young sublist if it is accessed AGAIN after remaining in the cache for longer than `innodb_old_blocks_time` (default 1000ms). One-pass sequential scans stay in the Old list and are rapidly evicted without evicting hot pages.",
            keyPoints = listOf(
                "Naive LRU flushes hot working-set cache when a large sequential table scan executes",
                "InnoDB splits LRU into Young (63%) and Old (37%) sublists",
                "New disk pages are inserted at the midpoint boundary (head of Old sublist)",
                "Pages graduate to Young sublist only if re-accessed after `innodb_old_blocks_time` (1000ms)",
                "Protects hot database caches from scan thrashing during backups and analytics queries"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_084",
            trackId = "sql_interview",
            conceptId = "sql_internals",
            conceptName = "Storage Engine Internals, Buffer Pools & Memory",
            title = "Buffer Pool Hit Ratio, Sizing, and Warmup on Restart",
            question = "How do you calculate Buffer Pool Hit Ratio? Why is `innodb_buffer_pool_dump_at_shutdown` essential for zero-downtime database maintenance?",
            shortAnswer = "Hit Ratio Formula: `(1 - (reads / read_requests)) * 100%`. An optimal OLTP system maintains > 99% hit ratio. Buffer Pool Sizing: On dedicated database instances, allocate 70-80% of total physical RAM to `innodb_buffer_pool_size`. The Cold Cache Problem: After restarting MySQL, the buffer pool is completely empty. The first thousands of queries suffer extreme latency (seconds instead of milliseconds) because every page must be read synchronously from physical disk. Warmup Solution: Set `innodb_buffer_pool_dump_at_shutdown = ON` and `innodb_buffer_pool_load_at_startup = ON`. At shutdown, MySQL dumps only the space IDs and page numbers of cached pages (a few megabytes text file). At startup, a background thread pre-loads those exact hot pages into RAM before traffic hits, warming the cache in seconds.",
            keyPoints = listOf(
                "Buffer Pool Hit Ratio measures percentage of page reads satisfied directly from RAM",
                "Allocate 70-80% of physical host memory to buffer pool on dedicated database nodes",
                "Cold cache after restart results in severe latency spikes from synchronous disk reads",
                "`innodb_buffer_pool_dump_at_shutdown` persists hot page space IDs and page numbers to disk",
                "`innodb_buffer_pool_load_at_startup` pre-warms cache in the background, eliminating cold restart spikes"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_085",
            trackId = "sql_interview",
            conceptId = "sql_internals",
            conceptName = "Storage Engine Internals, Buffer Pools & Memory",
            title = "Dirty Page Flushing and Page Cleaner Threads",
            question = "What is a 'dirty page' in a database buffer pool? How do Adaptive Flushing algorithms prevent buffer pool freeze stalls?",
            shortAnswer = "A 'dirty page' is a data page modified in RAM that has not yet been written back to physical table storage on disk (its changes are safely recorded in WAL/Redo log). If dirty pages consume 100% of the buffer pool, new queries that need to read pages from disk have no free pages available and must block synchronously while pages are flushed to disk (a buffer pool stall). Adaptive Flushing Solution: Dedicated background Page Cleaner threads continuously monitor the rate of Redo Log generation and the percentage of dirty pages. Using PID controllers, InnoDB dynamically throttles flushing speed: as dirty page percentage approaches `innodb_max_dirty_pages_pct` (default 75%) or redo log capacity fills, flushing intensity increases smoothly, ensuring a steady supply of clean pages and preventing catastrophic I/O stalls.",
            keyPoints = listOf(
                "Dirty pages have been updated in memory buffer pool but not yet written to table disk pages",
                "Lack of clean pages forces foreground query threads to flush pages, causing severe latency spikes",
                "Page cleaner background threads flush dirty pages continuously in controlled batches",
                "Adaptive flushing calculates dynamic flush rates based on redo log consumption velocity",
                "`innodb_max_dirty_pages_pct` controls the target threshold of dirty pages allowed in memory"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_086",
            trackId = "sql_interview",
            conceptId = "sql_internals",
            conceptName = "Storage Engine Internals, Buffer Pools & Memory",
            title = "InnoDB Row Formats and Off-Page LOB Storage",
            question = "Compare InnoDB `COMPACT` vs `DYNAMIC` row formats. What causes off-page storage for large VARCHAR, TEXT, and BLOB columns?",
            shortAnswer = "An InnoDB page is 16KB and must hold at least two rows (plus page header/trailer), meaning maximum in-page row size is ~8KB. When a row contains large VARCHAR, TEXT, or BLOB data: 1) `COMPACT` Format (legacy): Stores the first 768 bytes of large columns inline in the clustered index page, with a 20-byte pointer to overflow pages. If a row has ten 1KB columns, 10 * 768 bytes = 7.6KB, which exceeds the page limit and causes page split errors (`Row size too large`). 2) `DYNAMIC` Format (modern default since MySQL 5.7): Avoids partial inline storage. If a row exceeds the page limit, `DYNAMIC` moves the ENTIRE large column to off-page overflow pages, storing ONLY a 20-byte pointer inline on the clustered page. This maximizes clustered page density, keeps primary key indexes compact, and fits far more rows per page.",
            keyPoints = listOf(
                "InnoDB pages require storing at least 2 rows per page, bounding in-page row size to ~8KB",
                "COMPACT stores 768 bytes of overflow columns inline, wasting valuable clustered page space",
                "DYNAMIC format stores only a 20-byte pointer inline, pushing full oversized data to overflow pages",
                "DYNAMIC maximizes row density on clustered index pages, improving buffer pool cache efficiency",
                "Reduces random I/O on index scans that do not select the large LOB/TEXT columns"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_087",
            trackId = "sql_interview",
            conceptId = "sql_internals",
            conceptName = "Storage Engine Internals, Buffer Pools & Memory",
            title = "PostgreSQL TOAST (The Oversized-Attribute Storage Technique)",
            question = "How does PostgreSQL handle rows exceeding the 8KB page limit using TOAST? Explain the 4 TOAST storage strategies.",
            shortAnswer = "PostgreSQL pages are 8KB, and tuples cannot span multiple pages. When a row exceeds `TOAST_TUPLE_THRESHOLD` (typically 2KB), the engine triggers TOAST: 1) Step 1: Compresses wide variable-length attributes using pglz or lz4 compression. 2) Step 2: If still too large, moves data out of the main table heap into a hidden secondary TOAST table, chunking it into 2KB slices and storing an 18-byte pointer in the original row. Four Storage Strategies (per column): a) `PLAIN`: Prevents compression and out-of-line storage (used for fixed types like int, uuid). b) `EXTENDED` (default for text/jsonb): Attempts compression first; if still large, moves out-of-line. c) `EXTERNAL`: Allows out-of-line storage but forbids compression (speeds up substring operations on text). d) `MAIN`: Allows compression but prefers keeping data inline unless unavoidable.",
            keyPoints = listOf(
                "PostgreSQL tuples cannot span multiple 8KB pages; TOAST manages oversized row data",
                "Compresses wide attributes (lz4/pglz) when tuple exceeds ~2KB threshold",
                "Splits oversized values into 2KB chunks stored in a dedicated auxiliary TOAST table",
                "Main table row stores a lightweight 18-byte pointer to the out-of-line TOAST chunks",
                "Four strategies (PLAIN, EXTENDED, EXTERNAL, MAIN) provide fine-grained compression/storage control"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_088",
            trackId = "sql_interview",
            conceptId = "sql_internals",
            conceptName = "Storage Engine Internals, Buffer Pools & Memory",
            title = "PostgreSQL VACUUM Mechanics and Autovacuum Daemon Tuning",
            question = "How does PostgreSQL's Autovacuum reclaim dead tuple space? Why must Autovacuum be aggressively tuned in high-write production systems?",
            shortAnswer = "In PostgreSQL MVCC, an UPDATE or DELETE leaves the old tuple physically intact in the table heap marked as dead (via committed `xmax`). Dead tuples consume disk space and slow sequential scans. Autovacuum operates in phases: 1) Heap Scan: Scans heap pages, identifying dead tuples. 2) Index Vacuum: Scans all secondary indexes and deletes index pointers referencing dead tuples. 3) Heap Vacuum: Reclaims space on heap pages, marking line pointers as unused. 4) Visibility Map Update: Marks pages with zero dead tuples as all-visible (allowing Index-Only Scans). Why Default Tuning Fails: Default PostgreSQL autovacuum settings are too timid (cost limit 200, delay 2ms). On high-write tables, dead tuples accumulate 10x faster than autovacuum can clean them, leading to catastrophic table bloat. Fix: Increase `autovacuum_vacuum_cost_limit` to 2000-5000, decrease delay to 0, and tune per-table `autovacuum_vacuum_scale_factor` to 0.05 (5%).",
            keyPoints = listOf(
                "MVCC updates/deletes produce dead tuples that accumulate inside table heap pages",
                "VACUUM scans heap, removes dead pointers from all secondary indexes, and marks page space reusable",
                "Updates Visibility Map to enable fast Index-Only Scans without touching table heap",
                "Default autovacuum throttles are too conservative, falling behind high-volume update rates",
                "Tuning requires increasing cost limits, lowering scale factors, and enabling parallel vacuuming"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_089",
            trackId = "sql_interview",
            conceptId = "sql_internals",
            conceptName = "Storage Engine Internals, Buffer Pools & Memory",
            title = "Transaction ID (TXID) Wraparound in PostgreSQL",
            question = "What is the 32-bit Transaction ID (TXID) Wraparound hazard in PostgreSQL? What is `VACUUM FREEZE`, and what happens if wraparound occurs?",
            shortAnswer = "PostgreSQL transaction IDs are 32-bit unsigned integers, providing ~4.29 billion distinct IDs. Using modulo-2^32 arithmetic, at any given time, 2 billion TXIDs are in the past (visible), and 2 billion are in the future (invisible). The Hazard: If a database runs 2.1 billion transactions without freezing old tuples, historical transactions suddenly appear to be 'in the future', rendering historical data completely invisible (silent catastrophic data loss). The Solution (`VACUUM FREEZE`): Autovacuum periodically freezes old tuples by setting a special flag (`HEAP_XMIN_FROZEN`) in `t_infomask`. A frozen tuple is defined as being committed in the infinite past, immune to TXID comparisons. Emergency Shutdown: If wraparound gets within 10 million transactions of the limit (`autovacuum_freeze_max_age`), PostgreSQL enters emergency read-only mode and refuses all writes until a standalone single-user `VACUUM FREEZE` completes.",
            keyPoints = listOf(
                "PostgreSQL uses 32-bit transaction IDs; modulo arithmetic creates a 2-billion transaction horizon",
                "Without maintenance, historical transactions wrap around and become invisible future transactions",
                "`VACUUM FREEZE` marks older tuples as frozen (HEAP_XMIN_FROZEN), valid for all future transactions",
                "`autovacuum_freeze_max_age` triggers aggressive anti-wraparound vacuuming before the limit",
                "Failing to freeze forces PostgreSQL into emergency read-only mode to prevent data corruption"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_090",
            trackId = "sql_interview",
            conceptId = "sql_internals",
            conceptName = "Storage Engine Internals, Buffer Pools & Memory",
            title = "The Doublewrite Buffer in MySQL InnoDB",
            question = "Why does MySQL InnoDB implement a Doublewrite Buffer? What is a 'torn page' (partial page write), and why can't the Redo Log fix it alone?",
            shortAnswer = "A MySQL data page is 16KB, but the underlying OS/filesystem write block size is typically 4KB. Writing a 16KB page requires four 4KB physical disk sectors. If a power outage or OS crash occurs halfway through (e.g. after 2 sectors), the page on disk is left partially written and corrupt (a 'torn page'). Why Redo Log Cannot Fix It: Redo logs use physiological logging (e.g. 'update record X in page Y'). Replaying a physiological redo log requires the original page to be structurally intact and valid. Replaying on a corrupt, torn page causes instant crash and corruption. Doublewrite Buffer Solution: Before writing a dirty page to its actual table file, InnoDB writes the page to a contiguous, sequential disk area called the Doublewrite Buffer (`doublewrite`) and calls `fsync`. If a crash occurs during the subsequent table write, InnoDB recovers the pristine copy of the page from the doublewrite buffer, replaces the torn page, and then safely replays the Redo Log.",
            keyPoints = listOf(
                "InnoDB 16KB pages span multiple 4KB physical disk sectors",
                "A power failure mid-write causes a partial write or 'torn page', destroying page data structures",
                "Physiological redo logs cannot be applied to corrupted, torn pages during crash recovery",
                "Doublewrite buffer persists a contiguous copy of dirty pages before writing to table files",
                "Enables restoring a valid baseline page before replaying WAL/Redo logs during crash recovery"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_091",
            trackId = "sql_interview",
            conceptId = "sql_internals",
            conceptName = "Storage Engine Internals, Buffer Pools & Memory",
            title = "The Change Buffer (Insert Buffer) in MySQL InnoDB",
            question = "What is the InnoDB Change Buffer, and how does it convert random I/O writes into asynchronous batch writes for secondary indexes?",
            shortAnswer = "Modifying rows in a table with multiple secondary indexes requires updating those secondary B-Tree indexes. Secondary index keys are typically distributed randomly across disk pages that are NOT currently in the buffer pool, which would normally force synchronous random disk reads. Change Buffer Solution: If a secondary index page is not in the buffer pool, InnoDB does NOT read it from disk immediately. Instead, it buffers the INSERT, UPDATE, or DELETE change in a dedicated memory structure called the Change Buffer (part of the buffer pool). Merging Phases: The buffered changes are merged into the physical index page asynchronously when: 1) A foreground query naturally reads that secondary index page into the buffer pool (opportunistic merge). 2) A background master thread flushes buffered changes. 3) During clean server shutdown. Limitation: Only works for NON-UNIQUE secondary indexes (unique indexes require immediate disk reads to verify uniqueness).",
            keyPoints = listOf(
                "Secondary index modifications typically require expensive random disk reads to load index pages",
                "Change Buffer records modifications in RAM when target index pages are absent from buffer pool",
                "Bypasses immediate synchronous disk reads during high-throughput write operations",
                "Merges buffered changes lazily when the page is subsequent read into buffer pool",
                "Restricted to non-unique secondary indexes because unique indexes require immediate validation"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_092",
            trackId = "sql_interview",
            conceptId = "sql_internals",
            conceptName = "Storage Engine Internals, Buffer Pools & Memory",
            title = "Redo Log Sizing and Checkpoint Stalls",
            question = "How does Redo Log sizing affect write throughput in MySQL InnoDB? What happens when write bursts exhaust available Redo Log space?",
            shortAnswer = "InnoDB Redo Logs (`ib_logfile0`, `ib_logfile1` or modern redo log files) form a circular ring buffer of fixed total size (e.g. `innodb_redo_log_capacity = 8GB`). Two pointers track log state: 1) Write LSN: The position where active transactions append new log records. 2) Checkpoint LSN: The position up to which all dirty pages have been flushed to table data files. Available redo log space is `Write LSN - Checkpoint LSN`. Checkpoint Stall Catastrophe: If a massive write burst generates redo log records faster than page cleaner threads can flush dirty pages, the Write pointer catches up to the Checkpoint pointer (95% full). MySQL enters an emergency 'Synchronous Flush' mode: ALL new transactions and writes are completely frozen while foreground worker threads aggressively flush dirty pages to advance the Checkpoint LSN. Solution: Size redo log capacity to hold 1-2 hours of peak write volume.",
            keyPoints = listOf(
                "Redo logs operate as a circular ring buffer tracked by Write LSN and Checkpoint LSN",
                "Available log capacity represents unflushed dirty pages currently in the buffer pool",
                "When redo log fills up, MySQL freezes all client transactions in an emergency synchronous flush",
                "Causes catastrophic latency spikes (p99 latency jumps from 2ms to 10 seconds)",
                "Proper sizing: Set redo log capacity to accommodate 1-2 hours of sustained peak write traffic"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_093",
            trackId = "sql_interview",
            conceptId = "sql_internals",
            conceptName = "Storage Engine Internals, Buffer Pools & Memory",
            title = "Tablespace Architecture: File-per-Table vs System Tablespace",
            question = "Why is `innodb_file_per_table = ON` the universal standard? Why can't MySQL reclaim disk space after dropping a massive table in the shared system tablespace?",
            shortAnswer = "1) Shared System Tablespace (`ibdata1`): All tables, indexes, and undo logs share a single file. When you drop or truncate a 500GB table, the freed pages are marked reusable internally by InnoDB, but the physical `ibdata1` OS file NEVER shrinks. Reclaiming disk space back to the operating system filesystem requires dumping the entire database, deleting `ibdata1`, and re-importing everything. 2) File-per-Table (`innodb_file_per_table = ON`, default since 5.6): Every table has its own dedicated `.ibd` file on disk (`users.ibd`). Dropping or truncating a table immediately deletes or truncates the `.ibd` file, returning disk space directly to the OS filesystem in milliseconds. Furthermore, running `OPTIMIZE TABLE` rebuilds the table in a new temporary file and reclaims space lost to internal fragmentation.",
            keyPoints = listOf(
                "Shared system tablespace (ibdata1) never shrinks physical OS file size after table deletions",
                "Reclaiming shared tablespace disk space requires a full logical dump, wipe, and restore",
                "`innodb_file_per_table` stores each table in its own isolated `.ibd` datafile on disk",
                "Dropping or truncating tables instantly returns storage blocks to the host filesystem",
                "Enables per-table defragmentation via `OPTIMIZE TABLE` and table movement across storage volumes"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_sql_094",
            trackId = "sql_interview",
            conceptId = "sql_internals",
            conceptName = "Storage Engine Internals, Buffer Pools & Memory",
            title = "PostgreSQL Memory Configuration Architecture",
            question = "Explain the roles and sizing best practices for PostgreSQL memory parameters: `shared_buffers`, `work_mem`, `maintenance_work_mem`, and `effective_cache_size`.",
            shortAnswer = "1) `shared_buffers`: The database buffer pool in shared memory caching table/index pages. Sizing: 25% of total RAM on Linux (Postgres relies heavily on the OS page cache for the rest; >40% often suffers from double buffering and kernel cache contention). 2) `work_mem`: Memory allocated for internal sort operations (`ORDER BY`, `DISTINCT`), hash tables (`Hash Join`), and hash aggregations. Critical: Allocated PER OPERATION per connection (a query with 3 joins and a sort can allocate 4 * `work_mem`). Sizing: Keep conservative (e.g. 16MB-64MB) to prevent OOM killer when 100 concurrent connections run complex queries. 3) `maintenance_work_mem`: Memory for maintenance tasks (VACUUM, `CREATE INDEX`, foreign keys). Allocated per worker thread; set generously (e.g. 1GB-2GB). 4) `effective_cache_size`: An advisory setting (allocates zero RAM) telling the query planner how much total memory (shared_buffers + OS page cache) is available for caching, used to estimate index scan costs.",
            keyPoints = listOf(
                "`shared_buffers` represents shared RAM cache; best set to 25% of host memory on Linux",
                "`work_mem` is allocated per sort/hash operation per query; excessive values cause Linux OOM kills",
                "`maintenance_work_mem` accelerates VACUUM and CREATE INDEX; set generously (1-2GB)",
                "`effective_cache_size` is an advisory planner hint (typically 75% of total host RAM)",
                "PostgreSQL architecture leverages Linux OS Page Cache alongside its own shared buffers"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_095",
            trackId = "sql_interview",
            conceptId = "sql_internals",
            conceptName = "Storage Engine Internals, Buffer Pools & Memory",
            title = "Direct I/O (`O_DIRECT`) vs OS Page Cache Double Buffering",
            question = "What is 'double buffering' between an RDBMS and the OS Page Cache? How does MySQL InnoDB use `innodb_flush_method = O_DIRECT` to eliminate it?",
            shortAnswer = "Double Buffering Problem: When a database reads or writes data files using standard OS buffered I/O, the operating system kernel copies the 16KB data block into the Linux OS Page Cache. The database engine then copies that same block into its own Buffer Pool in user space. The exact same data page now occupies RAM in TWO places simultaneously, cutting effective memory caching capacity in half and wasting CPU on redundant memory copies. Solution (`O_DIRECT`): MySQL InnoDB configures `innodb_flush_method = O_DIRECT`. The `O_DIRECT` system flag instructs the Linux kernel to bypass the OS Page Cache entirely for data files (`.ibd`). Disk controllers perform direct DMA (Direct Memory Access) transfers between physical storage and InnoDB's buffer pool, eliminating double buffering and freeing all host RAM for the buffer pool.",
            keyPoints = listOf(
                "Double buffering duplicates database pages in both OS page cache and database buffer pool",
                "Halves effective RAM available for caching and wastes memory bus bandwidth on copies",
                "`O_DIRECT` instructs Linux kernel to bypass the OS page cache for database file reads and writes",
                "Enables direct DMA data transfer between storage controller and database buffer pool memory",
                "PostgreSQL deliberately uses buffered I/O; MySQL InnoDB strictly standardizes on O_DIRECT"
            ),
            difficulty = "Staff"
        )
    )

    private fun part6(): List<InterviewQuestion> = listOf(
        InterviewQuestion(
            id = "iq_sql_096",
            trackId = "sql_interview",
            conceptId = "sql_internals",
            conceptName = "Storage Engine Internals, Buffer Pools & Memory",
            title = "Write Amplification Factor (WAF) in Relational Databases",
            question = "What is Write Amplification Factor (WAF)? Trace how updating a single 20-byte column value can result in 1,000+ bytes written to physical storage.",
            shortAnswer = "Write Amplification Factor (WAF) is the ratio of physical bytes written to storage media relative to logical bytes requested by the application (`bytes_written_to_disk / logical_bytes_modified`). Tracing a 20-byte update in MySQL InnoDB: 1) Undo Log: Previous value is written to the undo log page and flushed (100+ bytes). 2) Redo Log: Redo log record describing the update is appended to the WAL buffer and flushed with fsync (200+ bytes). 3) Doublewrite Buffer: The modified 16KB data page is written sequentially to the doublewrite buffer (16,384 bytes). 4) Table Page: The 16KB data page is written to the table `.ibd` file (16,384 bytes). 5) Secondary Indexes: If an indexed column was updated, additional index leaf pages are modified and flushed. A 20-byte logical update easily triggers 32KB+ of physical disk writes, yielding a WAF of 1,600! High WAF exhausts SSD write endurance and saturates cloud EBS volume IOPS.",
            keyPoints = listOf(
                "WAF measures physical storage bytes written divided by logical application data written",
                "A 20-byte update writes to Undo Log, Redo Log, Doublewrite Buffer, and Table Data Pages",
                "Modifying a single byte requires writing the full 16KB page to disk during flushing",
                "Secondary index updates amplify write volume across multiple B-Tree leaf pages",
                "LSM-Tree engines (RocksDB) minimize WAF via sequential append-only log structured storage"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_097",
            trackId = "sql_interview",
            conceptId = "sql_internals",
            conceptName = "Storage Engine Internals, Buffer Pools & Memory",
            title = "Query Optimizer Statistics and Histograms",
            question = "How does the query optimizer use statistical metadata and Histograms (Equi-Width vs Equi-Height) to estimate query selectivity?",
            shortAnswer = "The query optimizer is cost-based: it estimates the disk I/O and CPU cost of different execution plans based on statistical samples gathered by `ANALYZE`. For uniform data, `n_distinct` (cardinality) is sufficient. But real-world data is skewed (e.g. 90% of orders are 'US', 1% are 'LU'). Histograms model skewed data distributions: 1) Equi-Width Histograms: Divides value ranges into equal intervals (e.g. 0-10, 10-20). Fails on skewed data because dense spikes are smoothed out. 2) Equi-Height (Equi-Depth) Histograms: Divides data so that every bucket contains an equal number of row samples. The bucket boundaries vary dynamically. Dense spikes (common values) get their own narrow buckets, while sparse values span wide buckets. In PostgreSQL, the Most Common Values (MCV) list stores the exact frequencies of top values, and an equi-height histogram models the remaining distribution, enabling accurate cardinality estimates.",
            keyPoints = listOf(
                "Cost-based query optimizers rely on table statistics generated by background ANALYZE jobs",
                "Histograms model non-uniform and skewed column value distributions accurately",
                "Equi-Height histograms allocate equal row counts per bucket, isolating dense value spikes",
                "PostgreSQL combines Most Common Values (MCV) lists with equi-height histogram buckets",
                "Stale statistics lead to catastrophic cardinality miscalculations and bad join choices"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_098",
            trackId = "sql_interview",
            conceptId = "sql_internals",
            conceptName = "Storage Engine Internals, Buffer Pools & Memory",
            title = "Parameter Sniffing and Generic vs Custom Query Plans",
            question = "What is Parameter Sniffing in prepared statements? How does PostgreSQL balance Custom Plans vs Generic Plans?",
            shortAnswer = "When an application executes a prepared statement (`PREPARE stmt AS SELECT * FROM orders WHERE status = \$1`), the optimizer creates an execution plan. Parameter Sniffing occurs when the optimizer sniffs the literal parameter passed in the FIRST execution (e.g. `\$1 = 'FAILED'`, which matches only 5 rows) and chooses an Index Scan. If the next execution passes `\$1 = 'COMPLETED'` (which matches 5,000,000 rows), using the cached Index Scan plan causes a catastrophic query stall. PostgreSQL Plan Caching Strategy: For the first 5 executions, PostgreSQL generates a 'Custom Plan' tailored to the specific parameter. On the 6th execution, it generates a 'Generic Plan' (using average table statistics without parameter values). If the generic plan's estimated cost is competitive with the custom plans, Postgres permanently switches to the generic plan, avoiding parameter sniffing traps.",
            keyPoints = listOf(
                "Prepared statements optimize once and reuse cached execution plans across executions",
                "Parameter sniffing creates suboptimal plans if initial execution uses unrepresentative values",
                "PostgreSQL creates Custom Plans for the first 5 executions to evaluate specific parameter costs",
                "Evaluates Generic Plan cost on the 6th execution; adopts generic plan if cost is acceptable",
                "Can force custom plans using `plan_cache_mode = force_custom_plan` for severely skewed tables"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_099",
            trackId = "sql_interview",
            conceptId = "sql_internals",
            conceptName = "Storage Engine Internals, Buffer Pools & Memory",
            title = "Asynchronous I/O (AIO) and io_uring in Modern Database Engines",
            question = "How does Linux `io_uring` revolutionize database disk I/O compared to legacy synchronous POSIX `pread/pwrite` and Linux `libaio`?",
            shortAnswer = "Legacy POSIX `pread()` / `pwrite()` is synchronous and blocking: each I/O call triggers a context switch from user space to kernel space, blocking the thread until the storage device responds. Linux `libaio` provided asynchronous I/O, but only worked with `O_DIRECT`, required separate system calls to submit and reap events, and had high overhead. Linux `io_uring` (Linux 5.1+) provides a true zero-copy, non-blocking asynchronous interface using two shared memory ring buffers between user space and kernel space: the Submission Queue (SQ) and Completion Queue (CQ). The database writes hundreds of read/write requests into the SQ and submits them with a SINGLE system call (or zero system calls using SQPOLL kernel polling). The kernel handles I/O asynchronously and posts results to the CQ. Benefits: Millions of IOPS, zero context-switch overhead, and support for all file descriptors.",
            keyPoints = listOf(
                "Synchronous POSIX I/O blocks worker threads and incurs high user-kernel context switch costs",
                "Linux libaio was restrictive, requiring O_DIRECT and multiple system calls per batch",
                "io_uring uses two shared-memory lockless ring buffers (Submission Queue and Completion Queue)",
                "Enables submitting and reaping hundreds of concurrent I/O operations with zero system calls (SQPOLL)",
                "Dramatically reduces CPU overhead and unlocks maximum NVMe storage throughput in modern databases"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_100",
            trackId = "sql_interview",
            conceptId = "sql_internals",
            conceptName = "Storage Engine Internals, Buffer Pools & Memory",
            title = "Log Sequence Numbers (LSN) and Crash Recovery Consistency",
            question = "What is a Log Sequence Number (LSN)? How do page headers use LSNs to prevent redundant redo replay during crash recovery?",
            shortAnswer = "An LSN (Log Sequence Number) is a strictly monotonically increasing 64-bit integer representing the byte offset position in the WAL/Redo log. Every log record has an LSN. In addition, EVERY physical database data page has a field in its page header called `Page LSN`, recording the LSN of the most recent log record that updated that specific page. Idempotent Crash Recovery: During the Redo recovery phase, the engine reads log records forward from the checkpoint. For each log record with LSN `X` targeting page `P`, the engine reads page `P` from disk and checks: `IF Page_LSN(P) >= X`, the update was ALREADY flushed to disk before the crash, so the engine SKIPS the log record! `IF Page_LSN(P) < X`, the update was lost in volatile RAM, so the engine REPLAYS the change and updates `Page_LSN(P) = X`. This makes crash recovery mathematically idempotent.",
            keyPoints = listOf(
                "LSN is a monotonic 64-bit integer representing physical byte offsets in the transaction log",
                "Every data page header stores a Page LSN recording the last log modification applied to it",
                "Enables crash recovery to compare Log Record LSN with physical Page LSN on disk",
                "Skips replaying changes if Page LSN is greater than or equal to Log LSN",
                "Guarantees that replaying recovery operations is strictly idempotent and safe against repeated crashes"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_101",
            trackId = "sql_interview",
            conceptId = "sql_replication",
            conceptName = "Replication, High Availability & Connection Pooling",
            title = "Replication Topologies: Single-Leader vs Multi-Leader vs Leaderless",
            question = "Compare Single-Leader (Primary-Replica), Multi-Leader (Active-Active), and Leaderless (Quorum) database replication models.",
            shortAnswer = "1) Single-Leader (Standard RDBMS): All writes go to one primary node; read replicas replicate changes asynchronously or semi-synchronously. Pros: Simple, guaranteed serializable or snapshot isolation, no write conflicts. Cons: Single point of failure for writes; failover causes brief write downtime; cross-datacenter write latency. 2) Multi-Leader: Multiple data centers have local leader nodes accepting writes, replicating changes asynchronously to each other. Pros: Local write latency, survives datacenter outage. Cons: Concurrent write conflicts occur on identical rows (e.g. concurrent updates to account balance); requires conflict resolution (LWW or CRDTs). 3) Leaderless (Dynamo-style / Cassandra): Clients write to and read from multiple peer nodes directly using quorum consensus (\$W + R > N\$). Pros: Extreme write availability and fault tolerance. Cons: Eventual consistency only; no multi-table ACID transactions.",
            keyPoints = listOf(
                "Single-Leader eliminates write conflicts by channeling all mutations through one primary",
                "Single-Leader creates write availability bottleneck and cross-region write latency",
                "Multi-Leader provides local write latency across regions but introduces write conflict hazards",
                "Leaderless architectures use client-side quorum reads and writes (W + R > N) for high availability",
                "Single-Leader dominates relational ACID databases; leaderless dominates distributed NoSQL"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_102",
            trackId = "sql_interview",
            conceptId = "sql_replication",
            conceptName = "Replication, High Availability & Connection Pooling",
            title = "Asynchronous vs Synchronous vs Lossless Semi-Synchronous Replication",
            question = "Explain the mechanics and failure modes of Asynchronous, Synchronous, and Lossless Semi-Synchronous replication in MySQL.",
            shortAnswer = "1) Asynchronous: Primary writes to binlog and immediately commits to client without waiting for replicas. Highest throughput, zero replication latency penalty. Failure mode: If primary crashes before binlog transmits to replica, committed transactions are permanently lost (data loss on failover). 2) Synchronous (2PC / Galera): Primary writes to all replicas and waits for all replicas to commit before acknowledging client. Zero data loss, but slowest: latency bounded by slowest replica; network partition freezes writes. 3) Lossless Semi-Synchronous (`AFTER_SYNC`): Primary writes transaction to storage engine and binlog, transmits binlog to replica network socket, and WAITS for at least one replica to acknowledge writing it to its Relay Log BEFORE the primary commits to storage engine and client. If primary dies at that exact instant, the replica has the full transaction in its relay log; zero data loss.",
            keyPoints = listOf(
                "Asynchronous replication commits locally first; risks data loss on ungraceful primary failover",
                "Synchronous replication guarantees zero data loss but stalls if any replica slows down",
                "Lossless Semi-Sync (`AFTER_SYNC`) waits for replica relay log ack before committing on primary",
                "Guarantees that no committed transaction is lost during an unexpected primary node crash",
                "Eliminates phantom commits where clients see committed data that vanishes after failover"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_103",
            trackId = "sql_interview",
            conceptId = "sql_replication",
            conceptName = "Replication, High Availability & Connection Pooling",
            title = "PostgreSQL Physical Streaming vs Logical Replication",
            question = "Compare PostgreSQL Physical Streaming Replication with Logical Replication. When is Logical Replication required?",
            shortAnswer = "1) Physical Streaming Replication: Replicates exact byte-level physical WAL records from primary to standby. Standby is a bit-for-bit physical clone. Pros: Blazing fast, minimal CPU overhead, guarantees exact replicas including system catalogs and secondary indexes. Cons: Replica must run identical PostgreSQL major version and operating system architecture; replica is read-only (cannot create local tables or indexes); cannot replicate a subset of tables. 2) Logical Replication (Publish/Subscribe): Decodes WAL into logical row change events (INSERT, UPDATE, DELETE) using a logical replication slot. Pros: Can replicate individual tables or schemas; publisher and subscriber can run different PostgreSQL major versions (enables zero-downtime upgrades); subscriber can have extra indexes, different partitioning, or be writable. Cons: Higher CPU overhead; cannot replicate DDL (schemas) automatically or sequence states.",
            keyPoints = listOf(
                "Physical streaming ships binary byte-for-byte WAL records; creates an exact byte clone",
                "Physical replicas are strictly read-only and require identical major versions and architecture",
                "Logical replication decodes WAL into row mutation events using Publication and Subscription",
                "Enables replicating specific tables, filtering rows, and syncing across different major versions",
                "Essential for zero-downtime major PostgreSQL version upgrades and data warehousing pipelines"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_104",
            trackId = "sql_interview",
            conceptId = "sql_replication",
            conceptName = "Replication, High Availability & Connection Pooling",
            title = "MySQL Binary Log Formats: Statement vs Row vs Mixed",
            question = "Explain Statement-Based (SBR), Row-Based (RBR), and Mixed Binary Logging in MySQL. Why is Row-Based Logging required for CDC?",
            shortAnswer = "1) Statement-Based (SBR): Logs the exact SQL statement executed (e.g. `UPDATE users SET status='ACTIVE' WHERE id=5`). Pros: Compact binlog size. Cons: Non-deterministic queries break replica consistency! Functions like `NOW()`, `UUID()`, `RAND()`, or `UPDATE ... LIMIT 1` (without ORDER BY) evaluate differently on primary and replica, causing silent data divergence. 2) Row-Based (RBR): Logs the exact physical before-and-after image of modified rows (`table_map` and `write_rows/update_rows/delete_rows` events). Pros: 100% deterministic and safe against non-deterministic functions; locks fewer tables during execution. Cons: Massive binlog size for bulk updates (`UPDATE 1M rows` writes 1M row events). 3) Mixed: Defaults to SBR; automatically switches to RBR for statements containing non-deterministic functions. Why CDC Requires RBR: Change Data Capture tools (Debezium) need exact row states to stream to downstream event buses without re-executing SQL.",
            keyPoints = listOf(
                "Statement-based logs raw SQL strings; causes data divergence on non-deterministic functions (NOW, UUID)",
                "Row-based logs exact before/after row byte values, guaranteeing absolute replication fidelity",
                "Row-based generates significantly larger binlog volumes during bulk data operations",
                "Mixed mode uses statement logging by default, switching dynamically to row logging when needed",
                "Debezium and modern CDC tools strictly require Row-Based Logging (binlog_format=ROW)"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_sql_105",
            trackId = "sql_interview",
            conceptId = "sql_replication",
            conceptName = "Replication, High Availability & Connection Pooling",
            title = "Replication Lag: Causes, Measurement, and Multi-Threaded Mitigations",
            question = "What causes database replication lag? How do MySQL Multi-Threaded Applier (MTS) and PostgreSQL parallel workers mitigate lag?",
            shortAnswer = "Causes of Replication Lag: The primary executes writes across 64 concurrent client connections in parallel, but historically, the replica applied changes via a SINGLE serial SQL applier thread. A single large query (e.g. `DELETE WHERE created_at < 2020` running for 30s) stalls the entire replication pipeline. Other causes: unindexed foreign keys on replica, long transactions, CPU/IOPS saturation. Mitigations: 1) MySQL Multi-Threaded Applier (MTS): Uses `replica_parallel_workers = 16` and `replica_parallel_type = LOGICAL_CLOCK`. Transactions that committed together in the same binary log group commit on the primary are guaranteed not to have conflicting locks; the replica can replay them concurrently in parallel across 16 threads, completely eliminating lag. 2) PostgreSQL: In PostgreSQL 16+, logical replication supports parallel streaming apply workers for large in-progress transactions.",
            keyPoints = listOf(
                "Replication lag arises when a single-threaded replica applier tries to replay 64-thread concurrent writes",
                "Long-running transactions and massive batch updates block the single applier queue",
                "MySQL MTS with LOGICAL_CLOCK replays transactions in parallel if they committed in the same group commit",
                "Multi-threaded applying scales replay speed linearly with available replica CPU cores",
                "Monitoring metrics: `Seconds_Behind_Master` in MySQL, `pg_stat_replication.replay_lag` in PostgreSQL"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_106",
            trackId = "sql_interview",
            conceptId = "sql_replication",
            conceptName = "Replication, High Availability & Connection Pooling",
            title = "Read-Your-Own-Writes Consistency in Replicated Architectures",
            question = "When splitting reads to replicas, users often update a profile, refresh the page, and see stale data due to replication lag. How do you solve this?",
            shortAnswer = "Solutions to Read-Your-Own-Writes Consistency: 1) User Sticky Routing with Temporal Window: After a user performs a write (POST/PUT/DELETE), the application records a timestamp in their session/JWT. For the next N seconds (e.g. 5 seconds, exceeding max replication lag), all subsequent read queries for that user are routed strictly to the PRIMARY database. After 5 seconds, reads revert to replicas. 2) LSN / GTID Version Tracking (Session Tokens): On write, the primary returns the Global Transaction ID (GTID) or LSN to the application (stored in a cookie). When reading, the application sends the GTID to the replica: `WAIT_FOR_EXECUTED_GTID_SET(gtid, timeout)`. The replica only serves the read once it has replayed that GTID; if delayed, it falls back to primary. 3) Caching Write-Through: Update the frontend client state or Redis cache immediately upon write.",
            keyPoints = listOf(
                "Asynchronous replication lag causes users to see stale data immediately after updating records",
                "Temporal sticky routing pins user read traffic to primary for 5-10 seconds following any write",
                "GTID/LSN tracking ensures the replica has caught up to the specific transaction before serving reads",
                "Application-level cache updates provide instantaneous client consistency without primary overload",
                "Guarantees intuitive UX while preserving read scalability for 95% of non-modifying traffic"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_107",
            trackId = "sql_interview",
            conceptId = "sql_replication",
            conceptName = "Replication, High Availability & Connection Pooling",
            title = "Automated Failover and Split-Brain Prevention: Patroni vs Orchestrator",
            question = "How do tools like Patroni (PostgreSQL) and Orchestrator (MySQL) orchestrate automated failover while preventing Split-Brain scenarios?",
            shortAnswer = "Split-Brain Danger: If a primary network partition occurs, and two nodes both believe they are the primary, both accept conflicting writes, causing irrecoverable data corruption. 1) Patroni (PostgreSQL): Relies on a Distributed Consensus Store (DCS: etcd or Consul). The primary node must continuously renew a short-lived lease key in etcd (heartbeat). If the primary loses network connectivity, its lease expires in etcd. Standbys hold an election via Raft consensus. The standby with the most advanced WAL position acquires the leader key, promotes itself to primary, and Patroni issues STONITH (Shoot The Other Node In The Head / fencing) or reboots the old primary via watchdog to prevent split-brain. 2) Orchestrator (MySQL): Uses Raft consensus among an Orchestrator cluster to discover MySQL topology via network probes, performing automated promotions and VIP reconfiguration.",
            keyPoints = listOf(
                "Split-brain occurs when network partitions cause two nodes to simultaneously act as writable primaries",
                "Patroni uses Raft consensus in etcd/Consul to maintain a single leader lease lock",
                "Primary must continuously renew DCS lease; failure triggers automatic lease forfeiture",
                "Standby with the most advanced WAL LSN is elected new primary via consensus voting",
                "Fencing mechanisms (STONITH, Linux watchdog) forcefully power off old primary to prevent rogue writes"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_108",
            trackId = "sql_interview",
            conceptId = "sql_replication",
            conceptName = "Replication, High Availability & Connection Pooling",
            title = "Connection Pooling Modes in PgBouncer: Session vs Transaction vs Statement",
            question = "Explain the 3 connection pooling modes in PgBouncer. Why is Transaction Pooling the industry standard, and what features does it break?",
            shortAnswer = "1) Session Pooling: A client connection is assigned a physical server connection for its ENTIRE connection lifetime (from login to disconnect). Solves connection creation latency, but does NOT solve PostgreSQL process memory exhaustion if 5,000 idle microservice pods connect. 2) Transaction Pooling (Industry Standard): A physical server connection is assigned to a client ONLY for the duration of a single database transaction. As soon as `COMMIT` or `ROLLBACK` executes, the server connection is returned to the pool to serve another client. 5,000 microservices can easily share 50 physical Postgres connections! What it breaks: Session-state features like prepared statements (`PREPARE`), temporary tables (`CREATE TEMP TABLE`), session variables (`SET timezone`), and `LISTEN/NOTIFY`. 3) Statement Pooling: Connection released after every single SQL statement; breaks multi-statement transactions entirely.",
            keyPoints = listOf(
                "Session pooling keeps physical connections tied to clients until client disconnects",
                "Transaction pooling shares server connections dynamically, returning them immediately on COMMIT",
                "Allows thousands of application threads to share a tiny pool of 50-100 database connections",
                "Transaction pooling breaks session-level state: session variables, temp tables, and advisory locks",
                "PgBouncer 1.21+ supports prepared statements in transaction pooling mode via automatic naming"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_109",
            trackId = "sql_interview",
            conceptId = "sql_replication",
            conceptName = "Replication, High Availability & Connection Pooling",
            title = "HikariCP Connection Pool Sizing and Connection Storms",
            question = "Why is a smaller database connection pool faster than a large one? Explain the HikariCP pool sizing formula and connection storms.",
            shortAnswer = "The Fallacy: 'More connections = higher concurrency.' In reality, if a database server has 16 CPU cores, it can only execute 16 threads simultaneously. If you open 1,000 active connections, the OS kernel spends 90% of its CPU time context switching, thrashing L1/L2/L3 CPU caches, and managing disk I/O queue lock contention. Throughput collapses while latency skyrockets. HikariCP Sizing Formula: `pool_size = (cpu_cores * 2) + effective_spindle_count`. For a 16-core server with SSD storage, 32-40 connections achieves MAXIMUM transaction throughput. Connection Storms: Occur when hundreds of app containers restart simultaneously after a deployment and open connections at once, exhausting DB RAM (each Postgres backend process consumes 10-20MB). Mitigation: Connection pool queueing with sensible `connectionTimeout` and proxy poolers (PgBouncer).",
            keyPoints = listOf(
                "Excessive database connections cause severe OS thread context switching and cache thrashing",
                "Hardware ceiling: A 16-core CPU can only execute 16 queries concurrently",
                "HikariCP sizing formula: `connections = (cores * 2) + disk_spindles` (typically 30-50 connections)",
                "Smaller pools keep CPU caches hot and eliminate disk queue contention, increasing total QPS",
                "Connection storms during container redeployments are mitigated by queuing at PgBouncer/ProxySQL"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_110",
            trackId = "sql_interview",
            conceptId = "sql_replication",
            conceptName = "Replication, High Availability & Connection Pooling",
            title = "Read/Write Splitting Architectures: ProxySQL vs Application Routing",
            question = "Compare database-proxy-based Read/Write splitting (ProxySQL / Pgpool) with Application-level routing (Spring `AbstractRoutingDataSource`).",
            shortAnswer = "1) Proxy-Based Splitting (ProxySQL / AWS RDS Proxy): A dedicated reverse proxy sits between application and databases. It parses incoming SQL queries in flight: routing `SELECT` queries to read replicas and `INSERT/UPDATE/DELETE/SELECT FOR UPDATE` to the primary. Pros: Application code remains completely agnostic; automatic load balancing across 10 replicas with health checking. Cons: Adds an extra network hop (0.5ms latency); parsing complex SQL at proxy layer consumes proxy CPU; transaction boundary tracking requires stateful connection pinning. 2) Application-Level Routing (Spring `AbstractRoutingDataSource`): Application code configures two DataSources: `@Transactional(readOnly = true)` routes to replica pool; `@Transactional(readOnly = false)` routes to primary. Pros: Zero network hop latency; complete programmatic control over routing and fallback. Cons: Requires strict developer discipline; forgetting `readOnly=true` routes reads to primary.",
            keyPoints = listOf(
                "ProxySQL parses SQL AST at network proxy layer, routing reads and writes transparently",
                "Proxy adds an extra network hop but centralizes connection management and replica health checks",
                "Application routing uses Spring AbstractRoutingDataSource based on transactional annotations",
                "Application routing eliminates proxy latency but requires disciplined code conventions",
                "Must ensure transactions with both writes and reads remain pinned to the primary connection"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_111",
            trackId = "sql_interview",
            conceptId = "sql_replication",
            conceptName = "Replication, High Availability & Connection Pooling",
            title = "Change Data Capture (CDC) with Debezium and Kafka",
            question = "How does Change Data Capture (CDC) using Debezium stream database mutations to Kafka without impacting OLTP application performance?",
            shortAnswer = "Traditional Polling Failure: Running `SELECT * FROM orders WHERE updated_at > last_poll` repeatedly induces table scans, fails to capture hard deletions, and misses intermediate state changes (A -> B -> A). Debezium CDC Architecture: Connects to the database transaction log directly (PostgreSQL logical replication slots or MySQL binlog). 1) Zero Application Overhead: The application performs standard SQL writes. Debezium reads the append-only WAL on disk asynchronously as a replica. 2) Captures 100% of Events: Captures every INSERT, UPDATE, and DELETE, including before-and-after row states and metadata (tx ID, timestamp). 3) Streams to Kafka: Serializes row events into JSON or Avro schemas and publishes to Kafka topics partitioned by primary key. Downstream consumers update search indexes (Elasticsearch), caches (Redis), or data lakes (Snowflake) with sub-second latency.",
            keyPoints = listOf(
                "Polling databases for updates wastes CPU, misses deletions, and creates indexing lag",
                "Debezium streams database transaction logs (binlog/WAL) asynchronously with near-zero OLTP impact",
                "Captures complete before and after row states, including hard DELETE operations",
                "Streams events into Kafka topics partitioned deterministically by primary key",
                "Eliminates dual-write inconsistencies when updating downstream caches and search engines"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_112",
            trackId = "sql_interview",
            conceptId = "sql_replication",
            conceptName = "Replication, High Availability & Connection Pooling",
            title = "Database Sharding Strategies: Hash vs Range vs Directory",
            question = "Compare Hash-based, Range-based, and Directory-based sharding strategies for horizontal relational database partitioning.",
            shortAnswer = "1) Hash-Based Sharding: Applies a hash function to the shard key: `shard_id = hash(user_id) % num_shards`. Pros: Uniform data distribution; virtually eliminates hot spots. Cons: Range queries (`WHERE user_id BETWEEN 100 AND 200`) require querying ALL shards (Scatter-Gather); adding/removing shards requires re-hashing all data (mitigated by Consistent Hashing). 2) Range-Based Sharding: Partitions data by continuous ranges (e.g. Shard 1: A-D, Shard 2: E-H, or by date). Pros: Range queries on shard key route to a single shard. Cons: Severe hot spots! For time-series data, 100% of writes hit the current day's shard, starving other shards. 3) Directory-Based (Lookup) Sharding: Maintains a centralized lookup table mapping `tenant_id -> shard_id`. Pros: Ultimate flexibility; allows migrating high-volume 'whale' tenants to dedicated shards without moving other tenants. Cons: Lookup table is a query bottleneck and SPOF (requires caching).",
            keyPoints = listOf(
                "Hash-based sharding distributes data evenly across nodes, eliminating write hot spots",
                "Hash sharding turns range scans into expensive scatter-gather operations across all nodes",
                "Range-based sharding clusters contiguous keys together but creates severe temporal write hot spots",
                "Directory-based sharding uses a mapping table to assign tenants dynamically to specific shards",
                "Enables isolated placement for massive enterprise tenants without rebalancing the whole cluster"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_113",
            trackId = "sql_interview",
            conceptId = "sql_replication",
            conceptName = "Replication, High Availability & Connection Pooling",
            title = "Cross-Shard Distributed Queries and Scatter-Gather Bottlenecks",
            question = "What happens when a query does not include the sharding key? Explain the Scatter-Gather execution model and memory bottlenecks.",
            shortAnswer = "When a query filters on the sharding key (`WHERE user_id = 42`), the routing coordinator hashes the key and targets exactly ONE shard (Single-Shard routing). When a query filters on a non-sharding key (`WHERE status = 'ACTIVE' ORDER BY created_at LIMIT 10`), the coordinator must execute a Scatter-Gather: 1) Scatter: Dispatches the query concurrently to ALL N shards. 2) Gather: Each shard executes the query locally and returns its top 10 rows to the coordinator. 3) Coordinator Aggregation: The coordinator receives N * 10 rows, sorts them in memory, and returns the top 10. Bottleneck: If pagination asks for `LIMIT 10 OFFSET 10000`, every shard must return 10,010 rows; the coordinator must sort N * 10,010 rows in memory! Network bandwidth saturates, coordinator memory explodes, and query latency is bounded by the SLOWEST shard in the cluster (straggler effect).",
            keyPoints = listOf(
                "Queries omitting the sharding key cannot be routed to a specific database node",
                "Scatter-gather dispatches the query concurrently across all cluster shards",
                "Coordinator node must aggregate, merge-sort, and deduplicate intermediate result streams",
                "High offset pagination forces every shard to return massive intermediate datasets",
                "Overall query latency is strictly dictated by the slowest responding shard node"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_114",
            trackId = "sql_interview",
            conceptId = "sql_replication",
            conceptName = "Replication, High Availability & Connection Pooling",
            title = "Distributed SQL Layers: Vitess (MySQL) and Citus (PostgreSQL)",
            question = "How do Vitess and Citus transform monolithic MySQL and PostgreSQL instances into horizontally scalable distributed SQL databases?",
            shortAnswer = "1) Vitess (powers YouTube / Slack): Runs stateless VTGate proxies that speak the MySQL protocol. Backed by VTTablet agents managing individual MySQL instances (keyspaces/shards). Vitess uses VSchema (declarative sharding schema) to parse SQL queries, determine target shards, rewrite queries, and coordinate scatter-gather operations. Features connection pooling at the VTTablet layer, automated live shard splitting (resharding with zero downtime using VReplication), and SQL syntax filtering. 2) Citus (PostgreSQL Extension): Extends PostgreSQL from within using extension hooks. Transforms tables into 'distributed tables' partitioned across worker nodes. The coordinator node parses queries, translates SQL into distributed execution trees, and executes parallel push-down queries to worker nodes using PostgreSQL foreign data wrappers and coprocessors.",
            keyPoints = listOf(
                "Vitess operates as a proxy layer (VTGate/VTTablet) orchestrating hundreds of MySQL instances",
                "Vitess enables zero-downtime resharding and shard splitting using VReplication streams",
                "Citus is an in-engine PostgreSQL extension turning Postgres into a distributed multi-node cluster",
                "Distributes tables across worker nodes using hash or reference table partitioning",
                "Pushes joins and aggregations down to local workers to minimize cross-node data movement"
            ),
            difficulty = "Staff"
        )
    )

    private fun part7(): List<InterviewQuestion> = listOf(
        InterviewQuestion(
            id = "iq_sql_115",
            trackId = "sql_interview",
            conceptId = "sql_replication",
            conceptName = "Replication, High Availability & Connection Pooling",
            title = "Zero-Downtime Major PostgreSQL Upgrades via Logical Replication",
            question = "Why can't physical streaming replication upgrade PostgreSQL from v14 to v16? Step through a zero-downtime logical replication upgrade workflow.",
            shortAnswer = "Physical replication cannot cross major versions because PostgreSQL internal data structures, page headers, and system catalogs change incompatibly between major releases. Zero-Downtime Logical Upgrade Workflow: 1) Spin up a new PostgreSQL 16 cluster alongside the existing PostgreSQL 14 cluster. 2) Export schema definitions (DDL only) from v14 and apply them to v16. 3) Configure PostgreSQL 14 as Publisher (`CREATE PUBLICATION all_tables FOR ALL TABLES`). 4) Configure PostgreSQL 16 as Subscriber (`CREATE SUBSCRIPTION sub CONNECTION '...' PUBLICATION all_tables`). 5) The v16 cluster performs initial table synchronization and continuously applies incoming WAL logical changes with sub-second replication lag. 6) Cutover: Briefly pause application writes (5 seconds), wait for subscription lag to hit 0, update sequences on v16, flip application connection strings to v16, and resume traffic. Zero risk and instant rollback available.",
            keyPoints = listOf(
                "Physical replication requires binary compatibility, forbidding cross-major-version standby nodes",
                "Logical replication decodes row events, enabling synchronization between PostgreSQL 14 and 16",
                "New cluster pre-syncs historical data while continuously replaying live incoming modifications",
                "Cutover requires brief maintenance window to synchronize sequence counters and flip endpoints",
                "Provides rollback safety by keeping the old cluster intact during validation"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_116",
            trackId = "sql_interview",
            conceptId = "sql_replication",
            conceptName = "Replication, High Availability & Connection Pooling",
            title = "Multi-Region Active-Active Conflict Resolution and LWW Dangers",
            question = "How do Active-Active multi-region relational setups handle concurrent conflicting writes? Why is Last-Write-Wins (LWW) dangerous?",
            shortAnswer = "In multi-region Active-Active setups, Region A and Region B both accept writes and replicate asynchronously. When concurrent transactions update the same row (e.g. Region A: `balance = balance - 50`, Region B: `balance = balance - 70`), a conflict occurs. 1) Last-Write-Wins (LWW): Resolves conflicts by comparing wall-clock timestamps (NTP) and keeping the newest write, silently discarding the older write. Dangers: Clock skew between regions (even 50ms drift) causes newer writes to be discarded. In financial/inventory updates, LWW causes silent data loss (one withdrawal is completely overwritten, violating balance correctness). 2) Safer Strategies: a) Conflict-Free Replicated Data Types (CRDTs) for commutative counter operations (`PN-Counter`). b) Partitioning by Geography: Direct European users strictly to EU region and US users to US region, eliminating cross-region write collisions.",
            keyPoints = listOf(
                "Active-Active multi-region writes inevitably produce concurrent conflicting mutations on identical rows",
                "Last-Write-Wins uses wall-clock timestamps to pick a winner, silently discarding the loser",
                "NTP clock skew causes legitimate updates to be overwritten, causing silent data corruption",
                "CRDTs (Conflict-Free Replicated Data Types) enable mathematically provable commutative merges",
                "Best practice: Enforce geographic domain partitioning to prevent cross-region write collisions"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_117",
            trackId = "sql_interview",
            conceptId = "sql_replication",
            conceptName = "Replication, High Availability & Connection Pooling",
            title = "Disaster Recovery Metrics: RPO and RTO in Database Context",
            question = "Define Recovery Point Objective (RPO) and Recovery Time Objective (RTO) in database architectures. How do different backup strategies satisfy them?",
            shortAnswer = "1) RPO (Recovery Point Objective): The maximum acceptable data loss measured in time (e.g. 'we can afford to lose at most 5 minutes of data'). Dictates backup frequency and replication mode. If you only take nightly backups, your RPO is 24 hours (disaster at 11:59 PM loses an entire day of transactions). Continuous WAL archiving / Point-In-Time-Recovery (PITR) achieves near-zero RPO (seconds). 2) RTO (Recovery Time Objective): The maximum acceptable duration to restore database operations after a catastrophe (e.g. 'the database must be back online within 15 minutes'). Dictates recovery architecture. Restoring a 10TB logical dump from S3 takes 18 hours (violating a 1-hour RTO). Meeting a 5-minute RTO requires automated standby failover (Patroni/Orchestrator) or hot warm standbys.",
            keyPoints = listOf(
                "RPO measures the acceptable time window of lost transactional data during disaster",
                "RTO measures the elapsed clock time required to restore database service to operational state",
                "Nightly backups provide 24-hour RPO; continuous WAL streaming provides sub-minute RPO",
                "Restoring massive data dumps takes hours, violating aggressive enterprise RTO targets",
                "Automated multi-region failover and live standbys satisfy stringent RTO (< 5 minutes)"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_sql_118",
            trackId = "sql_interview",
            conceptId = "sql_replication",
            conceptName = "Replication, High Availability & Connection Pooling",
            title = "Physical vs Logical Database Backups",
            question = "Compare Physical Backups (Percona XtraBackup, pgBackRest) with Logical Backups (mysqldump, pg_dump). Why are logical dumps unviable for terabyte databases?",
            shortAnswer = "1) Logical Backups (`pg_dump`, `mysqldump`): Exports database schema and data as plain text SQL statements (`CREATE TABLE`, `INSERT INTO`). Pros: Human readable; portable across OS and major versions; easy to restore individual tables. Cons: Disastrous at scale. Exporting a 5TB database executes billions of SELECTs, thrashing buffer pool caches and taking 12 hours. Restoring requires parsing SQL, executing inserts, and rebuilding all B-Tree indexes from scratch (taking 48+ hours). 2) Physical Backups (`pgBackRest`, `XtraBackup`): Copies actual raw data pages from disk blocks while database runs. Pros: Fast (bounded by disk read/write bandwidth); restoration simply copies binary blocks back to disk with indexes pre-built; zero index rebuild overhead. Supports block-level incremental and differential backups (copying only pages changed since last backup).",
            keyPoints = listOf(
                "Logical dumps export text SQL statements; physical backups copy raw disk pages directly",
                "Logical restores must parse SQL and rebuild all secondary indexes from scratch (taking days)",
                "Physical backups restore in minutes by streaming raw binary pages directly to storage",
                "Physical tools (pgBackRest, XtraBackup) capture consistent snapshots without locking live writes",
                "Physical backups support delta/differential block tracking, copying only modified disk pages"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_119",
            trackId = "sql_interview",
            conceptId = "sql_replication",
            conceptName = "Replication, High Availability & Connection Pooling",
            title = "Point-in-Time Recovery (PITR) Architecture",
            question = "How does Point-in-Time Recovery (PITR) work? How do you restore a database to 14:03:22 PM immediately before an accidental `DROP TABLE`?",
            shortAnswer = "PITR combines two continuous components: 1) Base Backups: A periodic full physical copy of all database files (e.g. weekly). 2) Continuous WAL Archiving: As WAL files fill up, the engine archives them continuously to durable object storage (`archive_command = 'aws s3 cp %p s3://bucket/wal/%f'`). Recovery Workflow for 14:03:22 PM: a) Restore the most recent physical base backup taken BEFORE the disaster into a new database directory. b) Create a `recovery.signal` file configuring target time: `recovery_target_time = '2024-06-01 14:03:22'`. c) Start PostgreSQL. The recovery engine downloads archived WAL segments from S3 and replays all committed transactions forward from the base backup. d) The moment the replayer hits timestamp 14:03:22, it stops recovery immediately, leaving the database restored to the exact millisecond before the catastrophic DROP TABLE was executed.",
            keyPoints = listOf(
                "PITR combines a baseline physical backup with a continuous stream of archived WAL files",
                "Archived WAL logs persist every committed change to durable object storage (S3)",
                "Recovery process applies the base backup and replays WAL forward through history",
                "Stops replay at the precise target timestamp or transaction ID prior to the disaster",
                "Enables rescuing data from accidental DROP/DELETE operations with near-zero data loss"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_120",
            trackId = "sql_interview",
            conceptId = "sql_replication",
            conceptName = "Replication, High Availability & Connection Pooling",
            title = "Cloud-Native Database Architecture: AWS Aurora Storage Engine",
            question = "How does AWS Aurora decouple compute from storage? Explain its 'The Log is the Database' design and 6-way replication across 3 AZs.",
            shortAnswer = "Traditional databases write both dirty data pages and redo logs to EBS volumes, causing high network I/O and slow crash recovery. AWS Aurora decouples compute (stateless SQL query engine) from storage (distributed storage fleet): 1) The Log is the Database: Compute nodes NEVER write dirty data pages over the network! They send ONLY lightweight Redo Log records to the storage fleet. The storage fleet asynchronously applies log records to generate physical data pages in background storage nodes. 2) 6-Way Replication: Every database volume is striped across 6 storage nodes spanning 3 Availability Zones (2 copies per AZ). 3) Quorum Model: Writes require a 4-of-6 quorum (survives loss of an entire AZ plus one extra node without losing writes). Reads require a 3-of-6 quorum. 4) Instant Crash Recovery: Storage nodes continuously replay logs; compute instances crash and restart in seconds without running recovery redo phases.",
            keyPoints = listOf(
                "Decouples stateless compute query execution from custom distributed multi-tenant storage",
                "Compute writes only redo logs over network ('The Log is the Database'), cutting write I/O by 80%",
                "Storage fleet asynchronously generates data pages from redo log streams in background",
                "Replicates 6 copies across 3 Availability Zones with 4-of-6 write quorum",
                "Enables near-instant crash recovery and creation of up to 15 read replicas with near-zero lag"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_121",
            trackId = "sql_interview",
            conceptId = "sql_tuning",
            conceptName = "Performance Tuning, Profiling & Troubleshooting",
            title = "Systematic Query Profiling: pg_stat_statements and Slow Query Logs",
            question = "How do you systematically identify and diagnose production database performance bottlenecks using `pg_stat_statements` (PostgreSQL) and the Slow Query Log (MySQL)?",
            shortAnswer = "1) PostgreSQL `pg_stat_statements`: Aggregates query execution statistics across all sessions normalized by query fingerprint. High-value queries: a) Highest cumulative time (`total_exec_time`): Top queries consuming the most total cluster CPU/IO. b) Highest average time (`mean_exec_time`): Slowest individual queries. c) Highest disk I/O (`shared_blk_read`): Queries missing the buffer pool and hitting physical disk. d) High execution count with moderate latency: Optimization candidates whose micro-gains compound across millions of calls. 2) MySQL Slow Query Log: Configured with `long_query_time = 0.5` and `log_queries_not_using_indexes = ON`. Analyzed using `pt-query-digest` to generate ranking reports by aggregate response time, identifying queries with high lock wait times or high rows examined vs rows sent.",
            keyPoints = listOf(
                "`pg_stat_statements` tracks execution metrics across normalized query template hashes",
                "Identifies top resource consumers by total cumulative runtime, average latency, and disk blocks read",
                "Focuses optimization on high-frequency queries where small improvements yield massive cluster gains",
                "MySQL Slow Query Log captures queries exceeding latency thresholds or missing indexes",
                "`pt-query-digest` aggregates slow logs into actionable percentiles and lock wait distributions"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_122",
            trackId = "sql_interview",
            conceptId = "sql_tuning",
            conceptName = "Performance Tuning, Profiling & Troubleshooting",
            title = "Join Algorithms: Nested Loop vs Hash Join vs Merge Join",
            question = "Compare the 3 fundamental join algorithms: Nested Loop Join, Hash Join, and Merge Join. When does the query planner select each?",
            shortAnswer = "1) Nested Loop Join: For every row in the outer table, scans the inner table. Computational complexity: O(N * M). The planner chooses it when the outer table is very small (e.g. 5 rows) and the inner table has a B-Tree index on the join key (Index Nested Loop), turning lookups into fast O(log M) seeks. 2) Hash Join: Scans the smaller table and builds an in-memory hash table on the join key (Build Phase); then streams the larger table and probes the hash table (Probe Phase). Complexity: O(N + M). Chosen for large unsorted datasets with equality join predicates (`a.id = b.id`). 3) Merge Join: Sorts both datasets by the join key (if not already sorted by an index) and walks both lists in parallel like a two-pointer merge. Complexity: O(N log N + M log M) or O(N + M) if pre-sorted. Chosen when data is pre-sorted or for range/merge joins.",
            keyPoints = listOf(
                "Nested Loop iterates outer table probing inner table; optimal when inner table is indexed on join key",
                "Hash Join builds in-memory hash table on smaller input and probes it with the larger input",
                "Hash Join is the default workhorse for large tables with equality join conditions",
                "Merge Join requires both inputs to be sorted, walking both streams sequentially in linear time",
                "Merge Join excels when inputs are already indexed in sorted order, avoiding memory hashing overhead"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_123",
            trackId = "sql_interview",
            conceptId = "sql_tuning",
            conceptName = "Performance Tuning, Profiling & Troubleshooting",
            title = "Nested Loop Join Catastrophe on Stale Cardinality Estimates",
            question = "How can stale optimizer statistics cause a catastrophic Nested Loop Join on multi-million row tables? How do you diagnose and fix it?",
            shortAnswer = "The Disaster: The optimizer checks table statistics and mistakenly estimates that table A will return only 5 rows (`estimated rows = 5`). Based on this tiny estimate, it selects a Nested Loop Join against table B. In reality, due to recent data skew or missing `ANALYZE`, table A actually returns 500,000 rows (`actual rows = 500,000`). The engine executes 500,000 individual index lookups or sequential scans against table B! A query estimated to take 2ms runs for 45 minutes, maxing out database CPU. Diagnosis: Run `EXPLAIN ANALYZE` and look for massive disparities between `rows=5` and `actual rows=500000` on the outer loop node. Fix: 1) Run `ANALYZE table_a` to refresh statistics. 2) Increase statistics target (`ALTER TABLE table_a ALTER COLUMN col SET STATISTICS 1000`). 3) As an emergency hotfix, disable nested loops: `SET enable_nestloop = off;`.",
            keyPoints = listOf(
                "Stale statistics cause query optimizer to severely underestimate outer table row cardinality",
                "Planner chooses Nested Loop expecting 5 iterations, but executes 500,000 physical iterations",
                "Causes catastrophic query execution stalls from millions of repetitive index probes",
                "Diagnosed via EXPLAIN ANALYZE by comparing estimated rows vs actual rows on outer loop",
                "Fixed by running ANALYZE, increasing column statistics target, or disabling nested loops temporarily"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_124",
            trackId = "sql_interview",
            conceptId = "sql_tuning",
            conceptName = "Performance Tuning, Profiling & Troubleshooting",
            title = "Hash Join Mechanics and Disk Spilling on `work_mem` Exhaustion",
            question = "Explain the Build and Probe phases of a Hash Join. What happens when the build table exceeds PostgreSQL's `work_mem` or MySQL's `join_buffer_size`?",
            shortAnswer = "Phases: 1) Build Phase: The engine scans the inner table, calculates a hash on the join key, and inserts rows into an in-memory hash table. 2) Probe Phase: The engine streams the outer table, hashes each join key, looks up matching buckets in the hash table, and outputs joined rows. Memory Spill Hazard: If the in-memory hash table exceeds `work_mem` (Postgres) or `join_buffer_size` (MySQL): In-Memory Hash Join fails. The engine falls back to a multi-batch External Hash Join (Grace Hash Join): It divides the hash table into multiple partitions (batches) and spills them to temporary files on disk. During the probe phase, it reads batches back and forth from disk. This results in massive random disk I/O, visible in EXPLAIN as `Batches: 64 Disk: 45000kB`. Fix: Increase `work_mem` for the query session.",
            keyPoints = listOf(
                "Build phase constructs an in-memory hash table from the smaller join table",
                "Probe phase streams the larger table, performing O(1) hash bucket lookups per row",
                "Exceeding `work_mem` forces an external multi-batch Grace Hash Join spilling to disk files",
                "Disk spilling degrades join performance by 10x-100x due to temporary file read/write I/O",
                "Tune `work_mem` dynamically for memory-intensive analytical reporting queries"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_125",
            trackId = "sql_interview",
            conceptId = "sql_tuning",
            conceptName = "Performance Tuning, Profiling & Troubleshooting",
            title = "Temporary Tables on Disk: Causes, Impact, and Tuning",
            question = "What causes relational databases to create implicit internal temporary tables on disk during query execution? How do you prevent disk spills?",
            shortAnswer = "When a query performs operations requiring intermediate state storage (e.g. `ORDER BY` without matching index, `GROUP BY`, `DISTINCT`, window functions, or complex CTEs), the engine creates an internal in-memory temporary table. In MySQL: If the intermediate result exceeds `tmp_table_size` or `max_heap_table_size` (or contains BLOB/TEXT columns prior to MySQL 8), MySQL converts the in-memory table to an on-disk TempTable or InnoDB table. In PostgreSQL: If operations exceed `work_mem`, they spill to temporary disk files (`pgsql_tmp`). Impact: Latency jumps from milliseconds to seconds due to disk I/O and mutex contention on disk allocation. Prevention: 1) Index optimization: Add composite indexes matching `GROUP BY` and `ORDER BY` columns to stream pre-sorted data directly without temporary tables. 2) Increase `tmp_table_size` and `work_mem`.",
            keyPoints = listOf(
                "Implicit temporary tables buffer intermediate results for unindexed sorting, grouping, or CTEs",
                "Exceeding memory thresholds converts in-memory temp tables to physical on-disk tables",
                "On-disk temporary tables incur severe disk I/O bottlenecks and latency spikes",
                "Visible in MySQL EXPLAIN as 'Using temporary; Using filesort'",
                "Prevent by indexing ORDER BY/GROUP BY columns or increasing memory limits (tmp_table_size, work_mem)"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_126",
            trackId = "sql_interview",
            conceptId = "sql_tuning",
            conceptName = "Performance Tuning, Profiling & Troubleshooting",
            title = "The Slow `COUNT(*)` Problem in MVCC Relational Databases",
            question = "Why is `SELECT COUNT(*)` notoriously slow on large tables in PostgreSQL and MySQL InnoDB? How do you implement fast approximate or exact counters?",
            shortAnswer = "Why it's slow: Legacy MyISAM stored an exact row count in the table header (O(1) lookup). But in MVCC databases (PostgreSQL, InnoDB), there is NO single global row count! Because of concurrent transactions, different transactions see different subsets of rows (some committed, some deleted, some uncommitted). Therefore, `SELECT COUNT(*)` must perform a FULL TABLE or FULL INDEX SCAN, checking visibility for every single row (taking seconds on 50M rows). Fast Solutions: 1) Fast Approximate Count (PostgreSQL): Query system catalogs: `SELECT reltuples::bigint FROM pg_class WHERE relname = 'orders';` (instantaneous O(1), updated by ANALYZE). 2) Summary Counter Table: Maintain a separate `counter_table (table_name, count)` updated via triggers or application logic (`UPDATE counter_table SET count = count + 1`). 3) Redis Caching: Maintain an atomic counter in Redis (`INCR`/`DECR`).",
            keyPoints = listOf(
                "MVCC prevents storing a static table row count because row visibility varies per transaction snapshot",
                "`COUNT(*)` forces a full table scan or full secondary index scan to verify visibility",
                "Takes seconds to minutes on tables with tens of millions of rows",
                "PostgreSQL approximate count: `SELECT reltuples FROM pg_class` provides instant O(1) estimate",
                "Exact real-time counting requires trigger-maintained counter tables or atomic Redis caches"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_127",
            trackId = "sql_interview",
            conceptId = "sql_tuning",
            conceptName = "Performance Tuning, Profiling & Troubleshooting",
            title = "Deep Pagination Disaster: `OFFSET 1000000 LIMIT 20` vs Keyset Pagination",
            question = "Why does `OFFSET 1000000 LIMIT 20` bring databases to a crawl? How does Keyset (Seek) Pagination achieve constant O(1) response time?",
            shortAnswer = "The Offset Disaster: When executing `SELECT * FROM orders ORDER BY id LIMIT 20 OFFSET 1000000`, the database does NOT jump straight to row 1,000,001. It must read all 1,000,020 rows from disk or index, sort them, count through 1,000,000 rows, discard all 1,000,000 rows, and return only the final 20 rows! As offset increases, CPU and I/O grow linearly (O(N)), exhausting resources. Keyset (Seek) Pagination Solution: Instead of skipping rows with OFFSET, the client remembers the last seen key from the previous page: `SELECT * FROM orders WHERE id > :last_seen_id ORDER BY id ASC LIMIT 20;`. The database performs an instant B-Tree index seek directly to `:last_seen_id` in O(log N) time and scans exactly 20 rows. Latency remains constant at ~1ms whether viewing page 1 or page 1,000,000.",
            keyPoints = listOf(
                "OFFSET N forces database to read, sort, and discard N rows before returning the requested slice",
                "Resource consumption and latency grow linearly with page depth, causing database timeouts",
                "Keyset (Seek) pagination filters on the last seen primary key or composite sort value",
                "Leverages B-Tree index seek directly to the starting position in constant O(log N) time",
                "Reads exactly LIMIT rows, providing flat 1ms response times regardless of pagination depth"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_128",
            trackId = "sql_interview",
            conceptId = "sql_tuning",
            conceptName = "Performance Tuning, Profiling & Troubleshooting",
            title = "The Hazards of Query Optimizer Hints in Production",
            question = "Why are query optimizer hints (e.g. `/*+ INDEX(...) */`, `STRAIGHT_JOIN`) considered dangerous in production code? When is hinting justified?",
            shortAnswer = "Dangers: An optimizer hint hardcodes a specific execution plan into application SQL. While it may fix a problem today on 10,000 rows, as the table grows to 10,000,000 rows or data distribution skews, that hinted plan becomes catastrophically wrong (e.g. forcing an Index Scan when a Hash Join is now 100x faster). Database engine upgrades also introduce new optimization algorithms that hinted queries can never use. When Justified: 1) Emergency Pager Incident Mitigation: A production query plan suddenly flips and causes an outage due to stale stats; adding an emergency hint in the ORM/proxy restores service immediately while root-cause analysis proceeds. 2) Known Planner Blind Spots: Complex multi-table joins where query plan parameter space is too large for the cost model to evaluate accurately.",
            keyPoints = listOf(
                "Optimizer hints override the cost-based planner, locking queries into static execution plans",
                "Hinted plans become severely suboptimal as data grows, distributions shift, or hardware changes",
                "Prevents database engine upgrades from applying new query optimization strategies",
                "Justified as temporary production hotfixes during critical latency incidents",
                "Permanent solution: Fix underlying root causes (stale statistics, missing indexes, query structure)"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_129",
            trackId = "sql_interview",
            conceptId = "sql_tuning",
            conceptName = "Performance Tuning, Profiling & Troubleshooting",
            title = "Diagnosing CPU vs I/O Bottlenecks on Database Servers",
            question = "How do you distinguish whether a database latency spike is caused by a CPU bottleneck or a Disk I/O bottleneck using OS metrics?",
            shortAnswer = "Use Linux performance tools (`top`, `vmstat 1`, `iostat -xz 1`): 1) CPU Bottleneck: In `top`/`vmstat`, `%us` (user CPU) is near 100%, and `wa` (iowait) is 0%. Run queue `r` is much higher than CPU core count. Causes: Expensive in-memory sorting, unindexed joins performing millions of CPU comparisons in buffer pool, complex regex/functions, or excessive concurrent client connections. 2) Disk I/O Bottleneck: In `top`/`vmstat`, `wa` (iowait) is high (> 20%), `%us` is low (< 30%), and block threads `b` is elevated. In `iostat`, disk `%util` is pegged at 100%, `await` (I/O service latency) jumps from 0.5ms to 20ms+, and `r/s` or `w/s` exceeds disk IOPS capacity. Causes: Cold cache misses, sequential table scans exceeding buffer pool, checkpoint dirty page flush storms, or WAL disk write queue saturation.",
            keyPoints = listOf(
                "CPU bottleneck indicated by high user CPU (%us near 100%) and low iowait (%wa near 0)",
                "CPU causes: In-memory hash joins, complex sorts, intensive math functions, connection context switches",
                "I/O bottleneck indicated by elevated iowait (%wa > 20%) and high iostat disk utilization (%util near 100%)",
                "Elevated `await` (I/O response time) signals that storage disk queues are saturated",
                "I/O causes: Low buffer cache hit ratios, massive full table scans, and redo log flush storms"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_sql_130",
            trackId = "sql_interview",
            conceptId = "sql_tuning",
            conceptName = "Performance Tuning, Profiling & Troubleshooting",
            title = "Investigating Database Lock Contention and Blocked Transactions",
            question = "How do you identify which transaction is holding a lock that is blocking dozens of other queries in PostgreSQL and MySQL?",
            shortAnswer = "1) PostgreSQL: Query `pg_stat_activity` and `pg_locks`: `SELECT blocked_locks.pid AS blocked_pid, blocked_activity.usename AS blocked_user, blocking_locks.pid AS blocking_pid, blocking_activity.usename AS blocking_user, blocked_activity.query AS blocked_statement, blocking_activity.query AS current_statement_in_blocking_process FROM pg_catalog.pg_locks blocked_locks JOIN pg_catalog.pg_stat_activity blocked_activity ON blocked_activity.pid = blocked_locks.pid JOIN pg_catalog.pg_locks blocking_locks ON blocking_locks.locktype = blocked_locks.locktype AND blocking_locks.database IS NOT DISTINCT FROM blocked_locks.database AND blocking_locks.relation IS NOT DISTINCT FROM blocked_locks.relation AND blocking_locks.page IS NOT DISTINCT FROM blocked_locks.page AND blocking_locks.tuple IS NOT DISTINCT FROM blocked_locks.tuple AND blocking_locks.virtualxid IS NOT DISTINCT FROM blocked_locks.virtualxid AND blocking_locks.transactionid IS NOT DISTINCT FROM blocked_locks.transactionid AND blocking_locks.classid IS NOT DISTINCT FROM blocked_locks.classid AND blocking_locks.objid IS NOT DISTINCT FROM blocked_locks.objid AND blocking_locks.objsubid IS NOT DISTINCT FROM blocked_locks.objsubid AND blocking_locks.pid != blocked_locks.pid JOIN pg_catalog.pg_stat_activity blocking_activity ON blocking_activity.pid = blocking_locks.pid WHERE NOT blocked_locks.granted;`. Terminate with `pg_terminate_backend(blocking_pid)`. 2) MySQL: Inspect `sys.innodb_lock_waits` or `SHOW ENGINE INNODB STATUS` under `TRANSACTIONS` to identify the blocking trx ID, thread ID, and row locks.",
            keyPoints = listOf(
                "Lock contention cascades when a root blocking transaction stalls downstream dependent transactions",
                "PostgreSQL: Join `pg_locks` and `pg_stat_activity` on lock keys to isolate the blocking PID",
                "`pg_terminate_backend(pid)` safely cancels the root offending connection to restore throughput",
                "MySQL: Inspect `sys.innodb_lock_waits` and `SHOW ENGINE INNODB STATUS` for blocking transaction info",
                "Root causes often include uncommitted interactive transactions, unindexed foreign keys, or broad DDL"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_131",
            trackId = "sql_interview",
            conceptId = "sql_tuning",
            conceptName = "Performance Tuning, Profiling & Troubleshooting",
            title = "Zero-Downtime Table Defragmentation: pg_repack vs VACUUM FULL",
            question = "Why is `VACUUM FULL` dangerous on a multi-gigabyte production table? How does `pg_repack` reclaim bloated table disk space without exclusive locks?",
            shortAnswer = "The Danger of `VACUUM FULL`: Rebuilds the entire table from scratch to reclaim dead tuple disk space, BUT acquires an `AccessExclusiveLock` on the table for the entire duration (hours). This blocks ALL incoming queries, including plain `SELECT` reads, causing a complete application outage. `pg_repack` Zero-Downtime Solution: 1) Creates a new physical log table and shadow table matching the bloated table. 2) Attaches a trigger to the original table that records live INSERT/UPDATE/DELETE mutations into the log table. 3) Copies all valid (non-dead) rows from original to shadow table. 4) Builds all indexes on the shadow table in parallel. 5) Applies accumulated mutations from the log table to the shadow table. 6) Briefly acquires an exclusive lock for ~50 milliseconds to swap system catalog entries (`pg_class`), dropping the old bloated table. Live reads and writes proceed uninterrupted throughout the process.",
            keyPoints = listOf(
                "VACUUM FULL acquires an AccessExclusiveLock, blocking all reads and writes for hours",
                "`pg_repack` builds a new clean shadow table and indexes online without exclusive table locks",
                "Uses temporary triggers and log tables to capture live concurrent data modifications",
                "Synchronizes caught-up changes to the shadow table in the background",
                "Executes an instantaneous metadata catalog swap in milliseconds to complete the cutover"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_132",
            trackId = "sql_interview",
            conceptId = "sql_tuning",
            conceptName = "Performance Tuning, Profiling & Troubleshooting",
            title = "The ORM N+1 Query Problem and Database Solutions",
            question = "What is the ORM N+1 query anti-pattern? How do you diagnose it at the database layer and fix it using `JOIN FETCH` or `EntityGraph`?",
            shortAnswer = "The N+1 Anti-Pattern: Occurs when an ORM (Hibernate, Prisma, Django) queries a parent entity list (`SELECT * FROM orders LIMIT 100`), and then when iterating through the 100 orders to access `order.getCustomer()`, lazily executes 100 individual queries (`SELECT * FROM customers WHERE id = ?`). Total queries executed: 1 + 100 = 101 queries! Database Impact: Saturates connection pools, triggers thousands of network roundtrips, and spikes database CPU. Detection: `pg_stat_statements` shows massive execution counts (`calls = 1,000,000`) for trivial point queries. Fixes: 1) JPQL / HQL `JOIN FETCH`: `SELECT o FROM Order o JOIN FETCH o.customer;` forces the ORM to generate a single SQL inner/left join fetching parent and child records in 1 query. 2) JPA `@EntityGraph`: Declaratively specifies child associations to eagerly fetch for specific repository methods. 3) Batch Fetching (`@BatchSize(size = 50)`): Reduces 100 queries to 2 queries using `WHERE id IN (?, ?, ...)`.",
            keyPoints = listOf(
                "N+1 occurs when an ORM loads a parent list in 1 query and fetches children via N separate queries",
                "Incurs massive network latency overhead and exhausts database connection pool threads",
                "Identified by high call counts for single-record lookups in query performance statistics",
                "`JOIN FETCH` instructs ORM to execute a single SQL join query retrieving parent and children together",
                "`@EntityGraph` and `@BatchSize` provide declarative eager loading and chunked IN clause fetching"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_sql_133",
            trackId = "sql_interview",
            conceptId = "sql_tuning",
            conceptName = "Performance Tuning, Profiling & Troubleshooting",
            title = "Index Skip Scan (Loose Index Scan) Mechanics",
            question = "What is an Index Skip Scan (Loose Index Scan)? How can you emulate it in PostgreSQL for composite indexes on `(tenant_id, created_at)`?",
            shortAnswer = "Scenario: You have a composite index on `(tenant_id, created_at)` with 1,000 tenants, and want to find the most recent order for a specific status without filtering on tenant_id (`SELECT * FROM orders WHERE created_at > NOW() - INTERVAL '1 day'`). Normally, because `tenant_id` is the leftmost prefix, the B-Tree cannot seek directly on `created_at` alone, forcing a full table scan. Index Skip Scan: The engine seeks to the first `tenant_id`, reads the matching `created_at`, skips to the NEXT distinct `tenant_id`, and seeks again, repeating across all distinct tenants in milliseconds. Native in MySQL 8.0. PostgreSQL Emulation using Recursive CTE: `WITH RECURSIVE SkipScan AS (SELECT (SELECT min(tenant_id) FROM orders) as tenant_id UNION ALL SELECT (SELECT min(orders.tenant_id) FROM orders WHERE orders.tenant_id > SkipScan.tenant_id) FROM SkipScan WHERE SkipScan.tenant_id IS NOT NULL) SELECT * FROM SkipScan;`. Transforms a 10GB sequential scan into a 10ms index hop.",
            keyPoints = listOf(
                "Composite indexes typically require the leftmost column to be present in query predicates",
                "Index Skip Scan navigates distinct values of the leading column, performing seeks on trailing columns",
                "Native in MySQL 8.0 ('Index skip scan') and Oracle; emulated in PostgreSQL via Recursive CTEs",
                "Bypasses full table scans on low-cardinality leading columns with high-cardinality trailing columns",
                "Achieves millisecond response times by jumping directly across distinct index prefix keys"
            ),
            difficulty = "Staff"
        )
    )

    private fun part8(): List<InterviewQuestion> = listOf(
        InterviewQuestion(
            id = "iq_sql_134",
            trackId = "sql_interview",
            conceptId = "sql_tuning",
            conceptName = "Performance Tuning, Profiling & Troubleshooting",
            title = "Chunked Bulk DELETE Strategies to Avoid Lock Stalls",
            question = "Why does `DELETE FROM audit_logs WHERE created_at < '2023-01-01'` cause production outages on 100M-row tables? Write a safe chunked deletion script.",
            shortAnswer = "Why Massive DELETE Fails: 1) Single Huge Transaction: Deleting 10M rows in one statement creates a single massive transaction. 2) Lock Contention: Acquires row locks on all 10M rows for minutes, blocking concurrent updates. 3) Redo/Undo Log Explosion: Generates gigabytes of undo logs and WAL, choking replication. 4) Memory Exhaustion: Buffer pool is overwhelmed by dirty pages. 5) Uncancellable: If you kill the query after 10 minutes, rolling back the 10M deletions takes another 20 minutes! Safe Chunked Deletion (MySQL / PostgreSQL): Delete in batches of 5,000 rows with short sleep intervals: `DO \$\$ DECLARE deleted_rows int; BEGIN LOOP DELETE FROM audit_logs WHERE id IN (SELECT id FROM audit_logs WHERE created_at < '2023-01-01' LIMIT 5000 FOR UPDATE SKIP LOCKED); GET DIAGNOSTICS deleted_rows = ROW_COUNT; COMMIT; EXIT WHEN deleted_rows = 0; PERFORM pg_sleep(0.1); END LOOP; END \$\$;`. Better: Partition by date and use `DROP TABLE partition_2022` (instantaneous O(1)).",
            keyPoints = listOf(
                "Monolithic DELETE statements hold millions of row locks, causing connection pool lockups",
                "Generates massive transaction logs that spike replication lag and risk undo tablespace exhaustion",
                "Rolling back an aborted massive delete takes twice as long as the original execution time",
                "Chunked deletes commit batches of 1,000-5,000 rows with brief sleep delays between transactions",
                "Architectural standard: Partition high-volume time-series tables and drop expired partitions instantly"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_135",
            trackId = "sql_interview",
            conceptId = "sql_tuning",
            conceptName = "Performance Tuning, Profiling & Troubleshooting",
            title = "Database Triggers and UDFs: Hidden Performance Traps",
            question = "What hidden performance and scalability traps do database Triggers and User-Defined Functions (UDFs) introduce into high-traffic systems?",
            shortAnswer = "Traps: 1) In-Transaction Latency Amplification: Triggers execute inside the caller's transaction. If an INSERT on `orders` has a trigger that updates audit tables, sends notifications, or recalibrates counters, the primary write transaction stays open significantly longer, multiplying row lock hold times. 2) Invisible Side Effects: Hidden database logic makes debugging, testing, and application refactoring extremely difficult; application developers are unaware why writes take 200ms. 3) Serialization and Deadlocks: Triggers updating centralized counter tables serialize all concurrent writes on a single row lock, causing deadlocks. 4) Black-Box Optimization: Relational optimizers treat complex UDFs as black boxes with default estimated costs, making bad join and index decisions. 5) Migration Friction: Stored logic binds the system to proprietary database procedural dialects (PL/pgSQL vs PL/SQL vs T-SQL).",
            keyPoints = listOf(
                "Triggers execute synchronously within the caller's transaction, prolonging row lock durations",
                "Updates to shared summary tables inside triggers create high lock contention and deadlocks",
                "Query optimizers treat UDFs as cost black boxes, frequently generating poor execution plans",
                "Obscures business logic from application code, making debugging and tracing difficult",
                "Binds application architecture to proprietary database-specific procedural scripting languages"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_136",
            trackId = "sql_interview",
            conceptId = "sql_tuning",
            conceptName = "Performance Tuning, Profiling & Troubleshooting",
            title = "Auto-Increment Lock Modes in MySQL: `innodb_autoinc_lock_mode`",
            question = "Explain the 3 values of `innodb_autoinc_lock_mode` (0, 1, 2) in MySQL InnoDB. Why is mode 2 (Interleaved) required for high-concurrency inserts?",
            shortAnswer = "To assign consecutive auto-increment IDs, InnoDB uses internal mutexes: 1) Mode 0 (Traditional): Every statement inserting rows acquires a special table-level `AUTO-INC` lock held until the statement FINISHES. Highly serialized; concurrent inserts queue up. 2) Mode 1 (Consecutive - Legacy Default): Simple inserts (known row count upfront) use a lightweight mutex to allocate an ID range and release it immediately. Bulk inserts (`INSERT ... SELECT`) still hold the full `AUTO-INC` lock until statement completion to guarantee consecutive IDs for statement-based replication (SBR). 3) Mode 2 (Interleaved - Default since MySQL 8.0): NEVER acquires the table-level `AUTO-INC` lock! All concurrent insert statements allocate IDs concurrently using lightweight mutexes. Insert throughput scales linearly across hundreds of threads. Note: IDs within a multi-row statement may be non-consecutive (interleaved with other concurrent inserts), requiring Row-Based Replication (`binlog_format = ROW`).",
            keyPoints = listOf(
                "Mode 0 (Traditional) acquires a table-level AUTO-INC lock held until statement completion",
                "Mode 1 (Consecutive) uses lightweight mutexes for simple inserts but locks on bulk inserts",
                "Mode 2 (Interleaved, MySQL 8 default) never uses table locks, allocating IDs concurrently",
                "Mode 2 maximizes parallel insert scalability and eliminates auto-increment lock contention",
                "Mode 2 requires Row-Based Replication (RBR) to prevent replica replication divergence"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_137",
            trackId = "sql_interview",
            conceptId = "sql_tuning",
            conceptName = "Performance Tuning, Profiling & Troubleshooting",
            title = "Detecting and Mitigating Database Connection Leaks",
            question = "What are the symptoms of a database connection leak in an application? How do you diagnose and eliminate connection leaks using HikariCP?",
            shortAnswer = "Symptoms: Over time, the application pool reaches maximum capacity (`HikariPool-1 - Connection is not available, request timed out`). The database shows hundreds of connections in `idle in transaction` state. Restarting the application temporarily clears the issue, but it recurs. Root Cause: Application code acquires a connection or begins a `@Transactional` block, encounters an unhandled exception or unclosed `ResultSet`/`Statement`, or executes a slow blocking external HTTP call while holding the DB connection open, never returning it to the pool. HikariCP Diagnostics: Set `leakDetectionThreshold = 5000` (5 seconds). If a thread holds a connection out of the pool for longer than 5 seconds, HikariCP logs a stack trace showing the exact line of code where the connection was checked out. Fix: Use try-with-resources, remove external HTTP calls from transactional boundaries, and configure statement timeouts.",
            keyPoints = listOf(
                "Connection leaks leave connections stranded in `idle in transaction` state on the database",
                "Exhausts connection pools, causing incoming application requests to time out waiting for connections",
                "Frequently caused by unclosed resources, unhandled exceptions, or HTTP calls inside DB transactions",
                "HikariCP `leakDetectionThreshold` logs a warning with stack trace for connections held too long",
                "Remediation: Enforce try-with-resources, short transaction scopes, and aggressive query timeouts"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_sql_138",
            trackId = "sql_interview",
            conceptId = "sql_tuning",
            conceptName = "Performance Tuning, Profiling & Troubleshooting",
            title = "Network and Connection Overhead: TCP, TLS, and Prepared Statement Pooling",
            question = "What latency penalties occur when an application creates a new database connection per query? How does prepared statement pooling reduce CPU overhead?",
            shortAnswer = "Connection Overhead: Establishing a new database connection requires: 1) TCP 3-way handshake (1 RTT). 2) TLS 1.3 handshake (1-2 RTTs). 3) Database authentication & process fork (Postgres forks a new 10MB backend process; MySQL allocates thread state). Total connection cost: 30-100ms of latency per query! A connection pool keeps persistent connections alive, reducing query startup to 0ms. Prepared Statement Pooling: When an application executes `SELECT * FROM users WHERE id = ?`, the database must parse the SQL text, validate semantics, generate a parse tree, and compute an execution plan (consuming database CPU). When prepared statement pooling is enabled in the driver/pooler (e.g. `cachePrepStmts=true` in MySQL, PgBouncer prepared statement caching), the query is parsed and planned ONCE. Subsequent queries send binary parameters directly to the cached plan, eliminating 30-50% of database CPU load.",
            keyPoints = listOf(
                "Creating connections on demand incurs TCP handshakes, TLS negotiation, and process fork overhead",
                "Adds 30-100ms latency penalty to every query execution without connection pooling",
                "Prepared statements parse, analyze, and compile the query execution plan once",
                "Driver-level statement caching passes binary parameters directly to pre-compiled plan handles",
                "Dramatically reduces database CPU cycles spent on SQL string parsing and AST optimization"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_sql_139",
            trackId = "sql_interview",
            conceptId = "sql_tuning",
            conceptName = "Performance Tuning, Profiling & Troubleshooting",
            title = "Statement and Transaction Timeouts as Production Circuit Breakers",
            question = "Why should every production database have strict statement timeouts and idle-in-transaction timeouts configured? How do you set them?",
            shortAnswer = "Why Timeouts are Mandatory: Without timeouts, a single rogue query (e.g. an accidental unindexed join running for 4 hours) or an unclosed transaction holding a row lock will stay active forever. This locks tables, bloats undo/WAL, and exhausts all available pool connections, causing a cluster-wide outage. Mandatory Timeouts in PostgreSQL: 1) `statement_timeout = 30000` (30 seconds): Any query running longer than 30s is forcefully aborted by the engine, releasing CPU and locks. 2) `idle_in_transaction_session_timeout = 60000` (60 seconds): If an application connection opens a transaction (`BEGIN`), performs a write, and remains idle for 60s without issuing `COMMIT` or `ROLLBACK`, Postgres terminates the connection and rolls back locks. 3) `lock_timeout = 5000` (5 seconds): Aborts queries if they cannot acquire their requested lock within 5s, preventing lock queues.",
            keyPoints = listOf(
                "Unbounded queries and abandoned transactions exhaust connection pools and trigger cascading outages",
                "`statement_timeout` aborts queries exceeding latency thresholds, releasing CPU and memory",
                "`idle_in_transaction_session_timeout` kills stalled connections holding active row locks",
                "`lock_timeout` prevents queries from waiting indefinitely in lock queues behind DDL",
                "Acts as an automated database-level circuit breaker protecting production stability"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_140",
            trackId = "sql_interview",
            conceptId = "sql_tuning",
            conceptName = "Performance Tuning, Profiling & Troubleshooting",
            title = "Index Bloat and Fragmentation Remediation in PostgreSQL",
            question = "What causes B-Tree index bloat in PostgreSQL? How do you detect index bloat and rebuild indexes without locking writes?",
            shortAnswer = "Causes: PostgreSQL MVCC updates write new heap tuples. Even if an indexed column is not updated, if the row does not qualify for Heap-Only Tuples (HOT), new index pointers are added to ALL secondary B-Tree indexes. When rows are deleted or updated, old index entries become dead. While B-Tree pages can reuse dead space for matching keys, if key distribution is uneven, pages remain half-empty (index bloat). A 500MB index can swell to 10GB, wasting buffer pool memory. Detection: Use the `pgstattuple` extension (`pgstatindex('my_index')`) to inspect `leaf_fragmentation` and `avg_leaf_density` (density < 70% indicates heavy bloat). Remediation: Use `REINDEX INDEX CONCURRENTLY my_index;` (PostgreSQL 12+). Rebuilds the index in a separate file in the background while live reads and writes continue uninterrupted, swapping pointers at completion and dropping the bloated file.",
            keyPoints = listOf(
                "PostgreSQL MVCC updates and deletes leave dead index leaf pointers that cause internal page fragmentation",
                "Index bloat swells disk footprint and wastes buffer pool cache holding half-empty pages",
                "Detected via `pgstattuple` / `pgstatindex` metrics (leaf page density and free space percentage)",
                "Legacy `REINDEX` acquires exclusive table locks, blocking all live concurrent writes",
                "`REINDEX CONCURRENTLY` rebuilds bloated B-Tree indexes online without blocking production queries"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_141",
            trackId = "sql_interview",
            conceptId = "sql_modern_db",
            conceptName = "Modern Database Paradigms: NoSQL, NewSQL & Search",
            title = "Database Paradigms: Relational vs Document vs Key-Value vs Columnar vs Graph",
            question = "Compare the 5 primary database paradigms: Relational, Document, Key-Value, Columnar, and Graph. What access patterns and workloads does each excel at?",
            shortAnswer = "1) Relational (PostgreSQL, MySQL): Tables, rows, foreign keys, strict ACID. Excels at complex multi-table joins, structured schemas, financial ledgers, and transactional consistency. 2) Document (MongoDB): JSON/BSON hierarchical documents. Excels at content management, catalog storage, and rapidly evolving polymorphic schemas where data is queried in self-contained units without joins. 3) Key-Value (Redis, DynamoDB): O(1) point lookups by primary key string. Excels at caching, session storage, rate limiting, and leaderboard queues. 4) Columnar (ClickHouse, Snowflake): Stores data column-by-column on disk. Excels at OLAP analytical aggregations (`SUM`, `AVG`) over billions of rows with high data compression. 5) Graph (Neo4j): Nodes and relationships with index-free adjacency. Excels at social networks, fraud detection rings, knowledge graphs, and deep recursive traversals.",
            keyPoints = listOf(
                "Relational databases provide strict ACID transactions, normalization, and complex relational joins",
                "Document databases store self-contained hierarchical JSON models for flexible schema evolution",
                "Key-Value stores provide ultra-low latency O(1) reads and writes for session and cache state",
                "Columnar engines optimize OLAP analytical aggregates over billions of records with massive compression",
                "Graph databases utilize index-free adjacency for high-speed multi-hop graph relationship traversals"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_142",
            trackId = "sql_interview",
            conceptId = "sql_modern_db",
            conceptName = "Modern Database Paradigms: NoSQL, NewSQL & Search",
            title = "Polyglot Persistence Architecture in Enterprise Platforms",
            question = "What is Polyglot Persistence? Design a real-world e-commerce architecture showing which specific database engines power each microservice domain.",
            shortAnswer = "Polyglot Persistence uses different specialized database engines for different application microservices based on data access patterns: 1) Order & Billing Service: PostgreSQL / MySQL InnoDB for ACID transactional integrity, foreign keys, and zero data loss on financial transactions. 2) Product Catalog Service: MongoDB or DynamoDB for semi-structured, polymorphic product attributes (shoes have sizes/colors; laptops have RAM/CPU). 3) Search & Autocomplete Service: Elasticsearch / OpenSearch for full-text search, fuzzy typo tolerance, and faceted filtering. 4) Session & Cart Service: Redis for sub-millisecond in-memory session validation, cart storage, and rate limiting. 5) Recommendation & Fraud Service: Neo4j to detect coordinated account rings and product recommendation graphs. 6) Analytics & Reporting: ClickHouse or Snowflake fed via Debezium CDC for sub-second analytical dashboards.",
            keyPoints = listOf(
                "Polyglot persistence pairs specific database paradigms to specialized microservice domain requirements",
                "Relational (PostgreSQL) handles transactional billing, payments, and financial invariants",
                "Document (MongoDB) manages semi-structured and polymorphic product catalog definitions",
                "In-memory (Redis) manages fast transient user sessions, carts, and distributed rate limits",
                "Columnar and Search engines (ClickHouse, Elasticsearch) power analytics and full-text search"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_143",
            trackId = "sql_interview",
            conceptId = "sql_modern_db",
            conceptName = "Modern Database Paradigms: NoSQL, NewSQL & Search",
            title = "Relational Databases vs Distributed SQL (NewSQL)",
            question = "What limitations of traditional relational databases do Distributed SQL (NewSQL) systems like CockroachDB and Google Spanner solve?",
            shortAnswer = "Traditional RDBMS (Postgres/MySQL) were designed as single-node systems. Scaling writes requires manual application sharding or active-passive replicas with failover lag. Sharding breaks ACID guarantees across shards, forbids cross-shard foreign keys, and makes cross-shard joins prohibitively slow. Distributed SQL (NewSQL) architecture provides the best of both worlds: 1) Full Relational SQL & ACID: Supports SQL queries, secondary indexes, foreign keys, and distributed ACID transactions across multiple nodes. 2) Horizontal Write Scalability: Tables are automatically partitioned into ranges/tablets that scale horizontally across dozens of machines. 3) Native Consensus: Built-in Raft or Paxos consensus across nodes guarantees automatic leader election and zero data loss (RPO = 0) without external orchestrators.",
            keyPoints = listOf(
                "Traditional RDBMS cannot scale writes horizontally across multiple nodes without manual sharding",
                "Manual sharding destroys cross-shard ACID transactions, joins, and foreign key integrity",
                "Distributed SQL combines relational SQL semantics and full ACID with horizontal elastic scalability",
                "Tables partition automatically into distributed ranges managed by consensus groups",
                "Built-in Raft/Paxos consensus delivers automated self-healing and zero data loss failover"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_144",
            trackId = "sql_interview",
            conceptId = "sql_modern_db",
            conceptName = "Modern Database Paradigms: NoSQL, NewSQL & Search",
            title = "Google Spanner Architecture: TrueTime and Commit-Wait",
            question = "How does Google Spanner achieve External Consistency (strict serializability) globally using TrueTime API, atomic clocks, and the Commit-Wait rule?",
            shortAnswer = "The Problem: Distributed transactions across global datacenters cannot agree on physical time due to clock drift (NTP can drift by hundreds of milliseconds). Google TrueTime Solution: Every Spanner datacenter is equipped with atomic clocks and GPS receivers. The TrueTime API returns time as an interval: `[earliest, latest]`, guaranteeing the true physical time lies within `now +/- epsilon` (where epsilon is approximately 1-7ms). The Commit-Wait Rule: When a transaction prepares to commit at timestamp S, the Spanner coordinator guarantees that S is >= `TrueTime.now().latest`. Then, Spanner DELIBERATELY WAITS (sleeps) until physical time has definitely passed S (`TrueTime.now().earliest > S`, an interval of 2*epsilon). This guarantees that any subsequent transaction anywhere in the world will receive a timestamp strictly greater than S, achieving linearizable, externally consistent global read-write transactions without communication locks.",
            keyPoints = listOf(
                "TrueTime API bounds physical time uncertainty using atomic clocks and GPS receivers",
                "Returns time as an interval `[earliest, latest]` with a guaranteed maximum uncertainty epsilon",
                "Commit-Wait rule forces transactions to wait 2*epsilon before committing",
                "Guarantees that transaction commit timestamps strictly match physical real-world chronological order",
                "Delivers global linearizable ACID transactions without distributed read locks"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_145",
            trackId = "sql_interview",
            conceptId = "sql_modern_db",
            conceptName = "Modern Database Paradigms: NoSQL, NewSQL & Search",
            title = "CockroachDB Architecture: Multi-Raft and Hybrid Logical Clocks",
            question = "How does CockroachDB implement distributed transactions without Google's proprietary atomic hardware using Hybrid Logical Clocks (HLC) and Multi-Raft?",
            shortAnswer = "CockroachDB operates on commodity cloud hardware: 1) Range Architecture: The entire database is a flat sorted key-value keyspace divided into 64MB chunks called 'Ranges'. 2) Multi-Raft: Each 64MB Range is replicated across 3 or 5 nodes via an independent Raft consensus group. Range leases allow local read leases without hitting the Raft log. 3) Hybrid Logical Clocks (HLC): Combines physical NTP time with Lamport logical counters: `HLC = (physical_time, logical_counter)`. It advances with physical time, but if a message arrives with a higher timestamp, the HLC advances its logical counter to guarantee causal ordering. 4) Read Restarts: If a read encounters a value in its clock uncertainty window (+/- 250ms), it transparently restarts the read at a higher timestamp to guarantee serializability.",
            keyPoints = listOf(
                "Keyspace is divided into 64MB Ranges, each managed by an independent Raft consensus group (Multi-Raft)",
                "Operates on commodity cloud virtual machines without requiring specialized atomic clock hardware",
                "Hybrid Logical Clocks (HLC) combine physical NTP time with Lamport causal counters",
                "Guarantees causal transaction ordering across nodes while bounding physical clock skew",
                "Read restart mechanisms preserve strict serializable isolation under clock uncertainty"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_146",
            trackId = "sql_interview",
            conceptId = "sql_modern_db",
            conceptName = "Modern Database Paradigms: NoSQL, NewSQL & Search",
            title = "LSM-Tree (Log-Structured Merge-Tree) vs B-Tree Architecture",
            question = "Why do write-heavy databases (Cassandra, RocksDB, ScyllaDB) implement LSM-Trees instead of B-Trees? Detail the write and read paths.",
            shortAnswer = "B-Tree Limitation: Writes require in-place updates to random disk pages, causing random I/O and heavy page splits. LSM-Tree Architecture: 1) Write Path: Writes are appended sequentially to a commit log (WAL for durability) and inserted into an in-memory sorted skip-list called a `MemTable` (O(log N)). ZERO random disk I/O! When the MemTable reaches capacity (e.g. 64MB), it is flushed to disk sequentially as an immutable sorted file called an `SSTable` (Sorted String Table). 2) Read Path: Because data is distributed across multiple SSTables, reading checks: MemTable -> OS page cache -> SSTable Level 0, Level 1... (slower than B-Tree). Mitigation: Bloom Filters in RAM instantly rule out SSTables that do not contain the target key. 3) Compaction: Background threads merge and deduplicate multiple SSTables, purging tombstones.",
            keyPoints = listOf(
                "B-Trees perform random disk I/O for in-place page updates, bottlenecking write throughput",
                "LSM-Trees append all writes sequentially to an in-memory MemTable, achieving peak write speed",
                "Flushes immutable Sorted String Tables (SSTables) sequentially to disk, eliminating random writes",
                "Reads require checking MemTable and multiple SSTables; accelerated via RAM Bloom Filters",
                "Background compaction merges SSTables, reclaims dead space, and sorts data across levels"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_147",
            trackId = "sql_interview",
            conceptId = "sql_modern_db",
            conceptName = "Modern Database Paradigms: NoSQL, NewSQL & Search",
            title = "Apache Cassandra Cluster Ring Architecture and Gossip Protocol",
            question = "How does Apache Cassandra achieve peer-to-peer masterless scalability using a distributed hash ring, Virtual Nodes (vnodes), and the Gossip protocol?",
            shortAnswer = "Cassandra has zero master nodes; all nodes are completely symmetric peers: 1) Consistent Hash Ring: The token range spans from \$-2^{63}\$ to \$2^{63}-1\$. The Partition Key is hashed via Murmur3, and the record is placed on the node owning that token range. 2) Virtual Nodes (vnodes): Instead of assigning a node a single large contiguous token range, each physical machine hosts 128-256 virtual nodes scattered evenly across the entire token ring. Benefits: Adding a new node rebalances small chunks from ALL existing machines in parallel, avoiding 1-to-1 data transfer bottlenecks. 3) Gossip Protocol: Nodes exchange cluster metadata (node health, heartbeat generations, schema versions) with 3 random peers every 1 second. The cluster state reaches convergence epidemically in seconds without any centralized coordinator.",
            keyPoints = listOf(
                "Peer-to-peer masterless topology eliminates single-point-of-failure and centralized coordinators",
                "Consistent hash ring maps Murmur3-hashed partition keys to specific token ranges",
                "Virtual nodes (vnodes) assign multiple token ranges per physical server for uniform distribution",
                "Enables parallel cluster rebalancing when adding or removing hardware nodes",
                "Gossip protocol disseminates cluster topology and health metrics epidemically across peer nodes"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_148",
            trackId = "sql_interview",
            conceptId = "sql_modern_db",
            conceptName = "Modern Database Paradigms: NoSQL, NewSQL & Search",
            title = "Cassandra Tunable Consistency: Quorum Math and Linearizability",
            question = "Explain Tunable Consistency in Apache Cassandra. How does configuring `Write Consistency = QUORUM` and `Read Consistency = QUORUM` guarantee strong consistency?",
            shortAnswer = "In Cassandra, consistency levels are configured PER QUERY by the client. With a Replication Factor of N (e.g. N=3 replicas): 1) Write Consistency Levels: `ONE` (acknowledges after 1 replica writes), `QUORUM` (waits for floor(N/2) + 1 = 2 replicas), `ALL` (waits for all 3 replicas). 2) Read Consistency Levels: `ONE`, `QUORUM`, `ALL`. Quorum Math Guarantee: If W + R > N, the read set and the write set MUST overlap on at least one replica by the Pigeonhole Principle. That overlapping node is guaranteed to hold the latest write (tracked via cell timestamps). When the coordinator performs a `QUORUM` read, it compares timestamps from the responding nodes, returns the latest value to the client, and issues an asynchronous background Read Repair to update stale replicas.",
            keyPoints = listOf(
                "Consistency levels are configured per query at runtime by the application client",
                "Replication factor N defines total data copies distributed across the ring",
                "Formula `W + R > N` guarantees read and write replica sets overlap on at least one node",
                "Pigeonhole principle ensures at least one responding replica holds the most recent write timestamp",
                "Read repair automatically reconciles and updates lagging replicas in the background"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_149",
            trackId = "sql_interview",
            conceptId = "sql_modern_db",
            conceptName = "Modern Database Paradigms: NoSQL, NewSQL & Search",
            title = "Cassandra Data Modeling: Partition Key vs Clustering Column",
            question = "How does data modeling in Cassandra differ fundamentally from relational modeling? Explain the roles of Partition Keys and Clustering Columns.",
            shortAnswer = "Relational modeling designs around entities and relationships (joins); Cassandra models around QUERIES (query-driven modeling: design one table per query pattern; duplicate data freely). Primary Key Anatomy: `PRIMARY KEY ((partition_key_1, partition_key_2), clustering_col_1, clustering_col_2)`. 1) Partition Key (`user_id`): Hashed by Murmur3 to determine WHICH NODE in the cluster ring stores the data row. All rows with the same partition key reside on the exact same physical node. 2) Clustering Column (`created_at`): Determines the physical SORT ORDER of rows on disk WITHIN that specific partition. This enables high-speed range scans within a single node (`WHERE user_id = 100 AND created_at > '2024-01-01'`). Anti-Pattern: Wide partitions exceeding 100MB or 100,000 cells cause garbage collection pauses and node memory crashes.",
            keyPoints = listOf(
                "Cassandra modeling is query-driven; duplicate data across multiple tables to satisfy query patterns",
                "Partition Key dictates which cluster node owns the row via consistent hashing",
                "Clustering Columns define physical sorted order of rows within an individual node partition",
                "Enables ultra-fast single-seek range queries on clustering keys without full table scans",
                "Anti-pattern: Massive partitions (>100MB) exhaust JVM heap and induce severe GC pauses"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_150",
            trackId = "sql_interview",
            conceptId = "sql_modern_db",
            conceptName = "Modern Database Paradigms: NoSQL, NewSQL & Search",
            title = "MongoDB Replica Sets: Oplog, Raft-like Consensus, and Write Concerns",
            question = "How do MongoDB Replica Sets operate? Explain the role of the Oplog and the difference between `w: 1` and `w: majority` write concerns.",
            shortAnswer = "A MongoDB Replica Set consists of one Primary and multiple Secondary nodes. 1) Oplog (Operations Log): A capped collection on the primary recording all write mutations. Secondaries continuously tail the primary's oplog and apply modifications locally asynchronously. 2) Election Protocol: If the primary becomes unreachable, secondaries hold an election using a Raft-like consensus algorithm. A node must receive a majority of votes to become primary. 3) Write Concerns: a) `w: 1`: Acknowledges the write as soon as the PRIMARY node writes to memory/journal. Fast, but if primary crashes before secondaries sync the oplog, the write is rolled back upon failover (data loss). b) `w: majority`: Waits until the write has been written and acknowledged by a MAJORITY of replica set members before returning success. Guarantees that the write will survive any primary node failover without data rollback.",
            keyPoints = listOf(
                "Replica set consists of one primary accepting writes and multiple secondaries replaying oplog",
                "Oplog is an internal capped collection storing idempotent row mutation events",
                "Secondaries elect a new primary using majority Raft-like consensus if the primary fails",
                "`w: 1` acknowledges write on primary only, risking data rollback on failover",
                "`w: majority` guarantees write persistence across a majority of nodes, preventing rollback loss"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_151",
            trackId = "sql_interview",
            conceptId = "sql_modern_db",
            conceptName = "Modern Database Paradigms: NoSQL, NewSQL & Search",
            title = "MongoDB Sharded Cluster Architecture: Mongos and Chunk Splitting",
            question = "Walk through the components of a MongoDB Sharded Cluster: Mongos, Config Servers, and Shards. How do chunk splits and chunk balancing work?",
            shortAnswer = "Architecture: 1) Shards: Physical replica sets storing data partitions. 2) Config Server Replica Set: Stores cluster metadata, shard key ranges, and routing tables. 3) Mongos: Stateless query routers that applications connect to. Applications query Mongos; Mongos consults cached config server metadata and routes queries directly to target shards. Chunk Mechanics: Data is partitioned into 'Chunks' (default 64MB) based on the Shard Key range. When a chunk grows beyond 64MB due to continuous writes, the primary shard splits it into two equal chunks (Chunk Split). Chunk Balancer: A background process monitors chunk distribution across shards. If the difference between the most and least loaded shard exceeds a migration threshold, the balancer migrates chunks in the background across shards to maintain equal data distribution.",
            keyPoints = listOf(
                "Mongos serves as a stateless routing proxy directing queries to appropriate storage shards",
                "Config Server replica set maintains authoritative cluster metadata and shard key routing ranges",
                "Shards are independent replica sets hosting subsets of partitioned data chunks",
                "Chunk splits divide chunks into two 32MB ranges when exceeding the 64MB threshold",
                "Background balancer migrates chunks between shards to ensure uniform cluster storage load"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_152",
            trackId = "sql_interview",
            conceptId = "sql_modern_db",
            conceptName = "Modern Database Paradigms: NoSQL, NewSQL & Search",
            title = "Graph Databases vs Relational DBs: Index-Free Adjacency",
            question = "What is 'Index-Free Adjacency' in native Graph Databases (Neo4j)? Why do relational recursive joins collapse on deep network queries?",
            shortAnswer = "Relational Collapse on Graphs: In a relational database, traversing a social network 4 hops deep (Friends of friends of friends) requires 4 consecutive `JOIN` operations. Each join executes a B-Tree index lookup with complexity O(log N). Traversing k hops across a table with N rows scales exponentially with total table size: O(k * log N), causing query execution time to jump from milliseconds to minutes. Index-Free Adjacency (Neo4j): In a native graph database, nodes do NOT use global index lookups to find neighbors. Each node maintains direct physical memory pointers to its adjacent relationships, and relationships hold direct pointers to adjacent nodes. Traversing an edge is a simple O(1) memory pointer dereference! Hop traversal speed is completely independent of total graph size, executing 5-hop traversals across billions of nodes in milliseconds.",
            keyPoints = listOf(
                "Relational multi-hop joins suffer from exponential complexity based on total table size",
                "Index-free adjacency connects nodes via direct physical memory pointers on disk/RAM",
                "Traversing a relationship executes as a constant O(1) pointer dereference without index seeks",
                "Graph traversal performance depends strictly on the traversed subgraph, not total database size",
                "Ideal for fraud detection rings, recommendation engines, and identity access graphs"
            ),
            difficulty = "Staff"
        )
    )

    private fun part9(): List<InterviewQuestion> = listOf(
        InterviewQuestion(
            id = "iq_sql_153",
            trackId = "sql_interview",
            conceptId = "sql_modern_db",
            conceptName = "Modern Database Paradigms: NoSQL, NewSQL & Search",
            title = "TimescaleDB Hypertables and Time-Series Data Lifecycle",
            question = "How does TimescaleDB use PostgreSQL extensions to build 'Hypertables'? Explain chunk slicing and automated zero-cost partition dropping.",
            shortAnswer = "TimescaleDB is packaged as a native PostgreSQL extension: 1) Hypertables: Users interact with a hypertable as a standard PostgreSQL table, but under the hood, TimescaleDB automatically slices the hypertable into smaller physical PostgreSQL tables called 'Chunks' partitioned by time (e.g. 1-day chunks). Benefits: Each 1-day chunk and its B-Tree indexes easily fit inside `shared_buffers` RAM, maintaining peak insert throughput without B-Tree thrashing. 2) Chunk Slicing: Queries with time filters (`WHERE time > NOW() - INTERVAL '2 hours'`) execute Chunk Exclusion, reading only the relevant physical chunk and skipping all other chunks. 3) Automated Data Retention: Dropping expired data does NOT run slow, lock-heavy `DELETE` queries. Instead, a background policy issues `DROP TABLE chunk_2023_01_01`, reclaiming disk space in milliseconds via O(1) filesystem file deletion.",
            keyPoints = listOf(
                "Hypertables appear as standard tables while partitioning data into time-sliced physical chunks",
                "Chunks and indexes fit inside RAM buffer pool, sustaining maximum append-only write throughput",
                "Query planner performs chunk exclusion, reading only physical chunks overlapping query time intervals",
                "Native columnar compression compresses older chunks by 90%+ in background jobs",
                "Data retention drops entire expired chunk tables in milliseconds without DELETE lock contention"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_154",
            trackId = "sql_interview",
            conceptId = "sql_modern_db",
            conceptName = "Modern Database Paradigms: NoSQL, NewSQL & Search",
            title = "Search Engines vs Relational Databases: Inverted Indexes",
            question = "How does an Inverted Index (Elasticsearch / Lucene) work? Why is full-text search fundamentally ill-suited for standard relational B-Trees?",
            shortAnswer = "B-Tree Ill-Suitedness: A B-Tree sorts whole text values. To search for words within text (`WHERE desc LIKE '%wireless%'`), the B-Tree is useless because the wildcard is leading, forcing a full table scan. Inverted Index Mechanics (Lucene): During document ingestion, the text is passed through an Analyzer (Tokenization -> Lowercasing -> Stop-word removal -> Stemming: 'running' becomes 'run'). An Inverted Index builds a dictionary mapping every unique extracted term (word) to a 'Posting List' (a sorted list of document IDs containing that word, along with term frequency and byte offsets). Querying: Searching for 'wireless headphones' looks up the posting lists for 'wireless' and 'headphone' in memory and performs a bitwise intersection (AND) in microseconds, ranking results using BM25 relevance scoring algorithms.",
            keyPoints = listOf(
                "B-Tree indexes sort whole column strings, failing on arbitrary substring or word searches",
                "Inverted indexes analyze text into normalized word tokens, building a vocabulary dictionary",
                "Posting lists map each unique term to a sorted array of document IDs containing the term",
                "Multi-term queries intersect posting lists using high-speed bitwise operations in memory",
                "Calculates statistical term relevance scoring (BM25 / TF-IDF) for ranked result ordering"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_sql_155",
            trackId = "sql_interview",
            conceptId = "sql_modern_db",
            conceptName = "Modern Database Paradigms: NoSQL, NewSQL & Search",
            title = "Columnar Databases (ClickHouse, Snowflake): Vectorized SIMD Execution",
            question = "Why are Columnar databases like ClickHouse and Snowflake 100x faster than PostgreSQL for analytical queries? Explain Columnar Storage and SIMD execution.",
            shortAnswer = "1) Columnar Storage: In row-oriented databases (Postgres), reading 1 column from 100M rows requires loading all 50 columns of every row from disk into RAM (massive wasted I/O). In ClickHouse, each column is stored in its own separate file. A query `SELECT AVG(revenue) FROM orders` reads ONLY the `revenue` file from disk, reducing disk I/O by 95%! 2) Extreme Compression: Because a single column file contains values of identical data type (e.g. all 32-bit timestamps), columnar engines apply domain-specific compression algorithms (Delta encoding, Double-delta / Gorilla, Dictionary compression, LZ4), achieving 10x compression ratios. 3) Vectorized SIMD Execution: Instead of processing one row at a time via Volcano iterator models, ClickHouse processes columnar vectors of 65,536 values at once, compiling queries into CPU SIMD (Single Instruction, Multiple Data) instructions that process 8-16 values per CPU clock cycle.",
            keyPoints = listOf(
                "Columnar storage reads only the specific columns referenced in the query, slashing disk I/O by 90%+",
                "Homogeneous column data enables high-ratio compression (Delta, Gorilla, Dictionary encoding)",
                "Vectorized query engines process columnar arrays of 65,536 values in tight CPU cache loops",
                "Leverages CPU SIMD vector instructions to perform operations on multiple data points per cycle",
                "Ideal for real-time analytics, user clickstream logs, and high-volume business intelligence aggregations"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_156",
            trackId = "sql_interview",
            conceptId = "sql_modern_db",
            conceptName = "Modern Database Paradigms: NoSQL, NewSQL & Search",
            title = "Embedded Analytics Engines: DuckDB vs SQLite",
            question = "What makes DuckDB the 'SQLite for Analytics'? Compare their storage layouts and query execution engines.",
            shortAnswer = "1) SQLite: Embedded row-oriented engine running in the application process space. Ideal for mobile apps, edge devices, and transactional OLTP workloads with point lookups and small writes. Poor performance on analytical queries (`GROUP BY` over 10M rows) due to row-by-row interpretation. 2) DuckDB: Embedded columnar OLAP engine running directly in-process (zero client-server network latency). Features: a) Columnar Vectorized Execution: Uses vectorized SIMD processing to aggregate millions of rows per second in memory. b) Direct Parquet / Arrow Integration: Can query external Parquet, CSV, or Apache Arrow memory buffers directly without copying data into database storage (Zero-Copy data querying). c) Multi-Threaded Query Engine: Automatically parallelizes analytical queries across all available CPU cores.",
            keyPoints = listOf(
                "SQLite is an embedded row-oriented engine optimized for mobile and local transactional OLTP",
                "DuckDB is an embedded columnar engine optimized for local high-speed OLAP analytical queries",
                "Runs inside the host application process, completely eliminating network socket latency",
                "Queries external Parquet, CSV, and Apache Arrow formats in-place using zero-copy memory mapping",
                "Multi-threaded vectorized execution aggregates millions of records in sub-second response times"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_157",
            trackId = "sql_interview",
            conceptId = "sql_modern_db",
            conceptName = "Modern Database Paradigms: NoSQL, NewSQL & Search",
            title = "Event Sourcing Data Model: Immutable Event Logs",
            question = "How does an Event Sourcing database model differ from traditional CRUD state storage? How are Snapshots used to bound state replay time?",
            shortAnswer = "Traditional CRUD: Updates overwrite the current state (`UPDATE accounts SET balance = 500 WHERE id = 1`), permanently destroying historical context of how the balance reached 500. Event Sourcing Model: Current state is NEVER stored directly. Instead, every state change is recorded as an immutable domain event appended to an append-only Event Store (`AccountOpened`, `MoneyDeposited \$1000`, `MoneyWithdrawn \$500`). Benefits: 100% complete audit trail, temporal time-travel (reconstruct state at any historical moment), and simplified write path (pure append, no locking). Bounding Replay with Snapshots: Reconstructing an entity with 50,000 events by replaying from event 1 takes seconds. Solution: Periodically persist a 'Snapshot' (e.g. every 100 events) storing the aggregated state at event 100. State reconstruction simply loads Snapshot 100 and replays only subsequent events.",
            keyPoints = listOf(
                "CRUD overwrites current state, permanently losing historical context and intermediate state changes",
                "Event Sourcing appends immutable domain events to an append-only event store log",
                "Current entity state is derived dynamically by replaying the event stream in chronological order",
                "Provides an indisputable audit trail and supports historical temporal point-in-time state queries",
                "Snapshots periodically capture state checkpoints, bounding replay latency to O(1) time"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_158",
            trackId = "sql_interview",
            conceptId = "sql_modern_db",
            conceptName = "Modern Database Paradigms: NoSQL, NewSQL & Search",
            title = "CQRS (Command Query Responsibility Segregation) Data Architecture",
            question = "Explain CQRS (Command Query Responsibility Segregation). How does it decouple write-optimized relational models from read-optimized views?",
            shortAnswer = "Traditional architectures use a single data model for both writes and reads, creating a compromise: normalizing for write consistency harms read query performance; denormalizing for fast reads creates write update anomalies. CQRS Architecture: 1) Command Side (Writes): Accepts commands (`PlaceOrder`), validates business invariants, and persists minimal, normalized state or event logs into a write-optimized database (PostgreSQL/EventStore). 2) Event Bus: Asynchronous event stream (Kafka) broadcasts state change events. 3) Query Side (Reads): Specialized Read Model workers consume events and project denormalized read views into read-optimized databases (e.g. Elasticsearch for search, Redis for caching, MongoDB for UI views). Queries read directly from these pre-computed read views with zero joins. Trade-off: Eventual consistency between write and read sides.",
            keyPoints = listOf(
                "Decouples write-handling command models from read-handling projection models",
                "Command side optimizes for strict business rule validation and normalized write consistency",
                "Query side optimizes for high-throughput, denormalized, zero-join read queries",
                "Asynchronous event bus (Kafka) synchronizes mutations from write side to read projections",
                "Accepts eventual consistency on the query side in exchange for massive independent scalability"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_159",
            trackId = "sql_interview",
            conceptId = "sql_modern_db",
            conceptName = "Modern Database Paradigms: NoSQL, NewSQL & Search",
            title = "Schema-on-Read vs Schema-on-Write Architectural Trade-offs",
            question = "Compare Schema-on-Write (RDBMS) with Schema-on-Read (Data Lakes / NoSQL). What long-term architectural debts does Schema-on-Read incur?",
            shortAnswer = "1) Schema-on-Write (Relational RDBMS): The database schema (tables, columns, types, constraints) is strictly defined upfront. Incoming data is validated by the engine before writing; malformed data is rejected immediately. Pros: Clean, mathematically guaranteed data integrity; consistent query interfaces. Cons: Schema changes require migrations and deployment coordination. 2) Schema-on-Read (S3 Data Lakes, Document NoSQL): Raw data (JSON, CSV, Parquet) is written directly to storage without validation. Schema interpretation is deferred until the data is queried (e.g. Presto, Spark, DuckDB). Pros: Instant ingestion, flexible schema evolution. Architectural Debt: 'Data Swamp' problem. Downstream consumer queries must handle dozens of historical schema variations, missing fields, type changes, and corrupted payloads, shifting schema complexity into application code.",
            keyPoints = listOf(
                "Schema-on-Write enforces data validation at insertion time, guaranteeing structural integrity",
                "Schema-on-Read ingests raw data unparsed, deferring schema parsing to query runtime",
                "Schema-on-Read enables high-velocity ingestion but shifts validation burden to downstream consumers",
                "Leads to 'Data Swamp' degradation where queries break due to inconsistent historical JSON schemas",
                "Modern compromise: Open table formats (Apache Iceberg) bringing schema evolution to data lakes"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_sql_160",
            trackId = "sql_interview",
            conceptId = "sql_modern_db",
            conceptName = "Modern Database Paradigms: NoSQL, NewSQL & Search",
            title = "CAP Theorem Application: CP vs AP Databases in Practice",
            question = "According to the CAP Theorem, why must a distributed database choose between Consistency (C) and Availability (A) during a Network Partition (P)? Classify modern engines.",
            shortAnswer = "In a distributed system, physical network partitions (P) are an inevitable physical reality (fiber cuts, router crashes). When a partition occurs, node A cannot communicate with node B. The database MUST choose: 1) CP (Consistency over Availability): When a write arrives at node A, because it cannot verify node B, it REJECTS or freezes the write to prevent split-brain. The system remains strictly consistent, but sacrifices availability (returns errors). Examples: Google Spanner, CockroachDB, HBase, MongoDB (with majority write concerns). 2) AP (Availability over Consistency): Node A accepts the write locally, and node B accepts a conflicting write locally. The system remains 100% available, but data diverges temporarily (eventual consistency). Examples: Apache Cassandra, Amazon DynamoDB, CouchDB. Note: CAP Consistency means Linearizability (single-copy serial recency), not ACID consistency.",
            keyPoints = listOf(
                "Network partitions (P) are unavoidable physical realities in distributed networks",
                "During a partition, systems must choose between rejecting writes (CP) or accepting divergent writes (AP)",
                "CP databases prioritize linearizability, sacrificing availability during network disruptions",
                "AP databases accept writes across all partitions, relying on eventual consistency convergence",
                "CAP 'Consistency' specifically denotes strict linearizability, differing from ACID C (invariants)"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_161",
            trackId = "sql_interview",
            conceptId = "sql_modern_db",
            conceptName = "Modern Database Paradigms: NoSQL, NewSQL & Search",
            title = "PACELC Theorem: Latency vs Consistency in Normal Operation",
            question = "Why is the CAP theorem insufficient for database selection? How does the PACELC theorem extend it to normal (non-partitioned) operation?",
            shortAnswer = "CAP theorem only describes system behavior during an active network partition (which happens < 0.1% of the time). It says nothing about the 99.9% of time when the network is completely healthy! PACELC Theorem (Daniel Abadi): If there is a Partition (P), how does the system trade Consistency (C) vs Availability (A); ELSE (E), how does the system trade Latency (L) vs Consistency (C)? Classifications: 1) PC/EC (Spanner, CockroachDB): During partitions, chooses Consistency; during normal operation, chooses Consistency over Latency (waits for synchronous replica roundtrips). 2) PA/EL (Cassandra, DynamoDB): During partitions, chooses Availability; during normal operation, chooses Latency over Consistency (returns immediately from local replica without waiting for peers).",
            keyPoints = listOf(
                "CAP theorem only applies during network partitions, ignoring normal operational trade-offs",
                "PACELC evaluates behavior during Partitions (C vs A) ELSE during normal operations (L vs C)",
                "In normal operation, databases must trade off query latency against data consistency guarantees",
                "PC/EC systems (Spanner) pay latency overhead during normal writes to enforce synchronous consistency",
                "PA/EL systems (Cassandra) prioritize ultra-low latency reads/writes during normal operations"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_162",
            trackId = "sql_interview",
            conceptId = "sql_modern_db",
            conceptName = "Modern Database Paradigms: NoSQL, NewSQL & Search",
            title = "Vector Databases for AI Embeddings: pgvector, HNSW vs IVFFlat",
            question = "How do Vector Databases support AI similarity searches? Compare HNSW (Hierarchical Navigable Small World) and IVFFlat index algorithms in `pgvector`.",
            shortAnswer = "AI models generate dense high-dimensional vectors (embeddings, e.g. 1536 dimensions) representing semantic meaning. Searching for similar items uses Approximate Nearest Neighbor (ANN) distance metrics (Cosine Similarity `<=>`, L2 Euclidean `<->`, Inner Product `<#>`). Algorithms in `pgvector`: 1) IVFFlat (Inverted File Flat): Partitions the vector space into \$K\$ Voronoi cells (clusters) using k-means. At query time, searches only the vectors inside the \$N\$ closest centroids (`probes`). Pros: Fast index build time, minimal memory consumption. Cons: Lower recall accuracy; requires training on populated data. 2) HNSW (Hierarchical Navigable Small World): Builds a multi-layer graph where lower layers contain dense clusters and upper layers contain long-range 'expressway' skip links. Querying traverses top-down like a skip list. Pros: Industry gold standard; 10x-20x faster search speeds and superior recall (>99%). Cons: High memory consumption (keeps full graph in RAM) and slower index creation.",
            keyPoints = listOf(
                "Vector databases perform Approximate Nearest Neighbor (ANN) searches across high-dimensional embeddings",
                "Distance metrics include Cosine similarity, Euclidean L2 distance, and dot product",
                "IVFFlat clusters vectors into Voronoi cells; fast build times and low memory footprint",
                "HNSW constructs multi-layered navigable graphs, delivering peak search throughput and recall",
                "`pgvector` embeds native vector similarity indexing directly inside standard PostgreSQL relational tables"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_163",
            trackId = "sql_interview",
            conceptId = "sql_modern_db",
            conceptName = "Modern Database Paradigms: NoSQL, NewSQL & Search",
            title = "Open Table Formats: Apache Iceberg on Object Storage",
            question = "How does Apache Iceberg bring ACID transactions, time travel, and schema evolution to raw Parquet files on cloud object storage (Amazon S3)?",
            shortAnswer = "Legacy Data Lakes: S3 directories with Parquet files had no transaction isolation. Multiple concurrent writes caused corrupted data; partition changes required rewriting all files; reading during writes saw partial results. Apache Iceberg Table Format: Organizes data using a 3-tier metadata tree: 1) Iceberg Catalog: Points to the current Metadata Pointer. 2) Metadata Files: Track schema evolution, partition specs, and snapshots. 3) Manifest Lists & Manifest Files: Index individual Parquet data files with min/max column statistics. ACID Transactions: Iceberg uses Optimistic Concurrency Control (OCC). Writes write new Parquet files and a new metadata snapshot file, committing via an atomic swap of the metadata pointer in the catalog. If another writer committed first, Iceberg retries. Enables time travel (`SELECT * FROM table FOR SYSTEM_TIME AS OF ...`), hidden partitioning, and zero-copy table rollbacks.",
            keyPoints = listOf(
                "Legacy data lakes lack transactional isolation, leading to corrupted data during concurrent writes",
                "Apache Iceberg introduces an architectural metadata layer over immutable Parquet files on S3",
                "Enforces ACID transactions via atomic metadata pointer swaps using Optimistic Concurrency Control",
                "Manifest files store file-level column statistics, enabling aggressive partition and file pruning",
                "Supports native time travel, in-place schema evolution, and hidden partition transformations"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_sql_164",
            trackId = "sql_interview",
            conceptId = "sql_modern_db",
            conceptName = "Modern Database Paradigms: NoSQL, NewSQL & Search",
            title = "Redis In-Memory Architecture: Single-Threaded Core vs Cluster Hash Slots",
            question = "Why is Redis's single-threaded event loop so fast? How does Redis Cluster partition data across 16,384 hash slots?",
            shortAnswer = "Why Single-Threaded is Fast: Redis executes command logic on a single thread using an epoll I/O multiplexing event loop. Benefits: 1) Zero Mutex/Lock Contention: No thread synchronization, race conditions, or deadlocks. 2) Zero Context Switching: Avoids CPU cache invalidation. 3) Memory-Bound: Bounded by RAM bandwidth, not CPU. (Redis 6+ uses background I/O threads ONLY for socket reading/writing, keeping command execution purely single-threaded). Redis Cluster Sharding: Data is partitioned across 16,384 Hash Slots. The key is hashed via CRC16: `slot = CRC16(key) % 16384`. The slots are distributed among master nodes (e.g. Node A owns slots 0-5500). Hash Tags: If multi-key operations are required (`MGET`, Lua scripts), wrapping a substring in curly braces (`{user:100}:profile` and `{user:100}:orders`) forces Redis to hash ONLY the text inside `{...}`, guaranteeing both keys land on the exact same hash slot and node.",
            keyPoints = listOf(
                "Single-threaded command execution eliminates CPU context switching, mutex locks, and thread contention",
                "I/O multiplexing with epoll manages tens of thousands of concurrent client socket connections",
                "Redis Cluster shards keyspace across 16,384 discrete hash slots using CRC16 hashing",
                "Client redirects (MOVED / ASK) route queries directly to the node owning the target slot",
                "Hash tags (`{...}`) force related multi-key records to hash to the exact same cluster slot"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sql_165",
            trackId = "sql_interview",
            conceptId = "sql_modern_db",
            conceptName = "Modern Database Paradigms: NoSQL, NewSQL & Search",
            title = "Database Engine Selection Framework for Enterprise Systems",
            question = "Provide a definitive architectural decision framework for choosing between PostgreSQL, MongoDB, Cassandra, ClickHouse, and Redis for a new platform.",
            shortAnswer = "Decision Matrix: 1) Choose PostgreSQL (Default Choice): When you need ACID transactions, strong data integrity, complex joins, financial accuracy, or rich relational schemas. (Handles 90% of enterprise use cases). 2) Choose Redis: When you require sub-millisecond in-memory caching, distributed locks, session stores, rate limiters, or transient real-time leaderboards. 3) Choose MongoDB: When your data is inherently hierarchical, self-contained documents with polymorphic schemas (e.g. CMS, user-defined forms), and high developer velocity without rigid DDL migrations is prioritized. 4) Choose Cassandra / ScyllaDB: When you have massive write ingestion (millions of writes/sec), require zero-downtime multi-region availability, and can query data strictly by pre-modeled partition keys. 5) Choose ClickHouse: When you need real-time OLAP analytics over billions of event rows (telemetry, clickstream, financial tick data) requiring sub-second aggregations.",
            keyPoints = listOf(
                "PostgreSQL is the default enterprise standard for relational ACID and mixed relational workloads",
                "Redis provides microsecond in-memory performance for transient cache, session, and lock state",
                "MongoDB serves semi-structured document hierarchies and rapidly changing polymorphic catalogs",
                "Cassandra delivers masterless write scalability across multi-datacenter active-active footprints",
                "ClickHouse powers real-time columnar analytical aggregations over massive event streams"
            ),
            difficulty = "Senior"
        )
    )
}
