package com.example.data.local.interview

import com.example.domain.model.InterviewQuestion

object SecurityInterviewQuestions {

    fun getQuestions(): List<InterviewQuestion> = listOf(
        // --- Concept 1: Authentication & Authorization ---
        InterviewQuestion(
            id = "iq_sec_001",
            trackId = "security_interview",
            conceptId = "sec_auth_tokens",
            conceptName = "Authentication, OAuth 2.0 & JWT",
            title = "OAuth 2.0 Authorization Code Grant with PKCE",
            question = "Why is the Authorization Code Flow with PKCE (Proof Key for Code Exchange) mandatory for Single Page Apps (SPA) and Mobile clients instead of the Implicit Flow?",
            shortAnswer = "In public clients (SPAs, mobile apps), client secrets cannot be securely stored. The obsolete Implicit Flow returned access tokens directly in the browser URL fragment (#token), exposing tokens to browser history, open redirects, and Referer headers. PKCE eliminates client secrets by generating a cryptographically random code_verifier on the client, sending its SHA-256 hash (code_challenge) during the authorization request. When exchanging the authorization code for tokens at the backend token endpoint, the client presents the raw code_verifier. This guarantees that an intercepted authorization code cannot be used by an attacker.",
            keyPoints = listOf(
                "Public clients cannot protect hardcoded client secrets",
                "Implicit flow exposed access tokens directly in URL hash fragments",
                "PKCE dynamically generates code_verifier and code_challenge (SHA-256)",
                "Authorization server validates SHA256(verifier) == challenge before issuing tokens",
                "Mitigates authorization code interception and injection attacks"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_sec_002",
            trackId = "security_interview",
            conceptId = "sec_auth_tokens",
            conceptName = "Authentication, OAuth 2.0 & JWT",
            title = "JWT Revocation & Blacklisting in Stateless Microservices",
            question = "JWTs are stateless; how do you handle instantaneous user logout or revoke compromised tokens before their expiration (exp) timestamp?",
            shortAnswer = "Because stateless JWTs remain valid until their expiration claim, instantaneous revocation requires: 1) Short access token lifetimes (e.g. 5–15 minutes) paired with long-lived refresh tokens stored securely in the database. 2) A distributed token blacklist/denylist in Redis: when a user logs out, the JWT's unique jti (JWT ID) is stored in Redis with TTL equal to remaining token lifetime. Gateway checks Redis on incoming requests. 3) Storing a user token_version counter in the database/Redis, included in the JWT claims; bumping the counter invalidates all previously issued tokens immediately.",
            keyPoints = listOf(
                "Short-lived access tokens (5-15 min) minimize the vulnerability window",
                "Distributed Redis blacklist using unique jti claim with matching TTL",
                "Token version / epoch counter per user invalidates all user tokens on password reset",
                "Refresh token rotation detects and revokes reused compromised tokens",
                "API Gateway centralizes token revocation verification to keep microservices lean"
            ),
            difficulty = "Senior"
        ),

        // --- Concept 2: Web Application Vulnerabilities & Defense ---
        InterviewQuestion(
            id = "iq_sec_003",
            trackId = "security_interview",
            conceptId = "sec_appsec_defenses",
            conceptName = "Web Application Security & OWASP",
            title = "SQL Injection Prevention: Why Prepared Statements Work",
            question = "Why do Parameterized Queries (Prepared Statements) completely prevent SQL Injection, whereas input sanitization and regex often fail?",
            shortAnswer = "Prepared Statements completely prevent SQL Injection because they separate the SQL code structure from user-supplied data at the database parser level. When a prepared statement is compiled, the database engine builds the abstract syntax tree (AST) and query execution plan using parameter placeholders (?). When user values are bound to the parameters later, the database treats them strictly as literal string/numeric values, never as executable SQL syntax. Even if a string contains \"' OR '1'='1\", it is evaluated purely as text data, making syntax injection mathematically impossible.",
            keyPoints = listOf(
                "Prepared statements compile SQL command structure before binding parameters",
                "User data is passed as literals, never parsed as executable SQL syntax",
                "Input sanitization / blacklist regex is fragile and prone to encoding bypasses",
                "Stored procedures only protect against SQLi if they do not concatenate strings internally",
                "Object-Relational Mapping (ORM / JPA) uses parameterized queries by default"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_sec_004",
            trackId = "security_interview",
            conceptId = "sec_appsec_defenses",
            conceptName = "Web Application Security & OWASP",
            title = "CSRF Protection: Synchronizer Tokens vs SameSite Cookies",
            question = "How does Cross-Site Request Forgery (CSRF) exploit browser cookie handling, and how do SameSite=Lax/Strict cookies mitigate it?",
            shortAnswer = "CSRF tricks a user's browser into executing unwanted HTTP state-changing requests against an authenticated website where the user has active session cookies. Because browsers automatically attach cookies to cross-origin requests, the target server mistakenly trusts the request. Defenses: 1) Synchronizer Token Pattern (CSRF token): a unique, cryptographically random token embedded in HTML/header that the attacker cannot read due to the Same-Origin Policy. 2) Cookie SameSite attribute: SameSite=Strict blocks cookies on all cross-site requests; SameSite=Lax permits cookies only on top-level safe GET navigations, neutralizing background forged POST/PUT requests.",
            keyPoints = listOf(
                "Exploits automatic browser cookie inclusion on cross-site requests",
                "SameSite=Strict prevents cookie inclusion in all third-party context requests",
                "SameSite=Lax (modern browser default) permits cookies only on top-level GET navigations",
                "Synchronizer Token (CSRF token) must be sent in custom HTTP header (e.g. X-XSRF-TOKEN)",
                "Stateless APIs using Authorization: Bearer headers are inherently immune to CSRF"
            ),
            difficulty = "Senior"
        )
    )
}
