package com.example.data.local.interview

import com.example.domain.model.InterviewQuestion

object DevopsInterviewQuestions {

    fun getQuestions(): List<InterviewQuestion> = listOf(
        // --- Concept 1: Linux & OS Internals ---
        InterviewQuestion(
            id = "iq_devops_001",
            trackId = "devops_interview",
            conceptId = "devops_linux_internals",
            conceptName = "Linux & OS Internals",
            title = "Linux Namespaces and Control Groups (cgroups)",
            question = "Explain how Linux namespaces and cgroups combine to form containers in Docker and Kubernetes. What does each provide?",
            shortAnswer = "Containers are not virtual machines; they are standard Linux processes isolated by the Linux kernel using namespaces and cgroups. Namespaces provide isolation by partitioning kernel resources so a process sees only its own environment: PID (process IDs), NET (network interfaces & iptables), MNT (filesystem mounts), IPC (inter-process communication), and UTS (hostname). Control groups (cgroups v1/v2) provide resource metering and enforcement, capping and throttling CPU cycles (CFS scheduler quotas), physical RAM limits, swap, and disk I/O per container.",
            keyPoints = listOf(
                "Containers are isolated Linux processes, not hardware virtual machines",
                "Namespaces isolate what a process can see (network, mount, pid, ipc)",
                "cgroups meter and enforce what a process can use (CPU shares, RAM limits, blkio)",
                "cgroups v2 provides a unified hierarchy and reliable out-of-memory killing",
                "Rootless containers utilize User namespaces to map container root to unprivileged host UID"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_devops_002",
            trackId = "devops_interview",
            conceptId = "devops_linux_internals",
            conceptName = "Linux & OS Internals",
            title = "Graceful Process Termination: SIGTERM vs SIGKILL",
            question = "How should a containerized service handle SIGTERM (15) versus SIGKILL (9) to ensure zero-downtime rolling updates?",
            shortAnswer = "When Kubernetes terminates a Pod (e.g. during a deployment rolling update), it first removes the Pod from Service endpoints and sends SIGTERM to PID 1. The containerized application must catch SIGTERM to drain active network requests, close database connections, and flush logs gracefully within the terminationGracePeriodSeconds (default 30s). If the process is still running when the grace period expires, the kernel sends SIGKILL (which cannot be caught or handled), terminating the process abruptly and dropping remaining client connections.",
            keyPoints = listOf(
                "SIGTERM (signal 15) is catchable; allows in-flight request draining and connection cleanup",
                "SIGKILL (signal 9) cannot be caught or ignored; process is instantly killed by kernel",
                "Kubernetes waits terminationGracePeriodSeconds before escalating to SIGKILL",
                "PID 1 in containers must properly forward signals to child application worker threads",
                "preStop lifecycle hook can delay SIGTERM to ensure iptables endpoint removal propagates"
            ),
            difficulty = "Mid-Level"
        ),

        // --- Concept 2: Docker & Containerization ---
        InterviewQuestion(
            id = "iq_devops_003",
            trackId = "devops_interview",
            conceptId = "devops_docker_containers",
            conceptName = "Docker & Containerization",
            title = "Multi-Stage Docker Builds & Image Optimization",
            question = "Why are multi-stage Docker builds critical for production container security and image size? Give a practical example.",
            shortAnswer = "A multi-stage build uses multiple FROM instructions in a single Dockerfile. The first stage (builder) includes heavy compilers, SDKs, and build tools (e.g. Maven, JDK, Node.js) to compile the application artifact. The final stage uses a minimal runtime base (e.g. Alpine or Google Distroless) and copies only the compiled binary or JAR from the builder stage via 'COPY --from=builder'. This shrinks image sizes from 800MB to 50MB, drastically reduces container attack surface by removing package managers and shell utilities, and eliminates build secrets from the final image.",
            keyPoints = listOf(
                "Separates build environment (SDKs, compilers, package managers) from runtime environment",
                "COPY --from=builder transfers only compiled artifacts into the final stage",
                "Massive image size reduction (from 1GB+ down to <80MB)",
                "Drastically shrinks CVE attack surface (no curl, wget, bash, or package managers)",
                "Distroless images contain only application binary and minimum glibc/ca-certificates"
            ),
            difficulty = "Mid-Level"
        ),

        // --- Concept 3: Kubernetes Orchestration ---
        InterviewQuestion(
            id = "iq_devops_004",
            trackId = "devops_interview",
            conceptId = "devops_kubernetes",
            conceptName = "Kubernetes Orchestration",
            title = "Troubleshooting Pod CrashLoopBackOff",
            question = "A newly deployed Kubernetes Pod is stuck in CrashLoopBackOff. Walk through your step-by-step troubleshooting methodology.",
            shortAnswer = "CrashLoopBackOff means the container repeatedly starts, crashes, and Kubernetes backs off restart attempts exponentially. Troubleshooting steps: 1) Run 'kubectl describe pod <name>' to inspect Events for exit codes (e.g. Exit Code 137 = OOMKilled; Exit Code 1 = Application runtime error), failed volume mounts, or failing liveness probes. 2) Inspect previous container crash logs using 'kubectl logs <name> --previous'. 3) Check missing environment variables or ConfigMap/Secret keys. 4) Verify readiness and liveness probe paths and initialDelaySeconds. 5) If OOMKilled, inspect memory limit vs JVM heap allocation.",
            keyPoints = listOf(
                "Exit Code 137 indicates OOMKilled (exceeded container memory limit in cgroup)",
                "Exit Code 1 indicates uncaught application exception or misconfigured startup command",
                "kubectl describe pod reveals failure events, probe failures, and mount errors",
                "kubectl logs --previous retrieves the crash log of the terminated container instance",
                "Liveness probe misconfiguration can kill a healthy application during slow startup"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_devops_005",
            trackId = "devops_interview",
            conceptId = "devops_kubernetes",
            conceptName = "Kubernetes Orchestration",
            title = "Liveness vs Readiness vs Startup Probes",
            question = "Explain the difference between Liveness, Readiness, and Startup probes in Kubernetes. What happens when each fails?",
            shortAnswer = "Startup Probe protects slow-starting applications: all other probes are disabled until it succeeds; if it fails, the container is restarted. Liveness Probe checks if the internal container process is healthy (e.g. detects deadlocks); if it fails, kubelet kills and restarts the container. Readiness Probe checks if the application is ready to accept live traffic (e.g. database connections warmed); if it fails, Kubernetes removes the Pod's IP from the Service endpoints without restarting the container, preventing failed user requests.",
            keyPoints = listOf(
                "Startup Probe: disables other probes during boot; restarts container on failure",
                "Liveness Probe: detects unrecoverable hangs/deadlocks; restarts container on failure",
                "Readiness Probe: detects temporary overload or warming; removes Pod from Service traffic",
                "Never point Liveness Probe to downstream dependencies (causes cascading restart storms)",
                "Use distinct Actuator endpoints (/health/liveness vs /health/readiness)"
            ),
            difficulty = "Mid-Level"
        ),

        // --- Concept 4: CI/CD, GitOps & Infrastructure as Code ---
        InterviewQuestion(
            id = "iq_devops_006",
            trackId = "devops_interview",
            conceptId = "devops_cicd_gitops",
            conceptName = "CI/CD, GitOps & IaC",
            title = "Blue-Green vs Canary Deployment Strategies",
            question = "Compare Blue-Green and Canary deployment strategies. When would you choose one over the other?",
            shortAnswer = "In Blue-Green deployment, two identical production environments exist: Blue (current live version) and Green (new version). Once Green passes smoke testing, the router/load balancer instantly switches 100% of traffic from Blue to Green, enabling instant zero-downtime cutover and immediate rollback. In Canary deployment, the new version is rolled out to a small subset of servers (e.g. 5% traffic), while automated metrics (error rate, latency) are monitored. If healthy, traffic is incrementally shifted (10%, 25%, 50%, 100%). Canary is chosen when evaluating real production user traffic behavior with minimal blast radius.",
            keyPoints = listOf(
                "Blue-Green: instant 100% traffic cutover; requires 2x infrastructure capacity",
                "Blue-Green rollback: instant routing switch back to Blue environment",
                "Canary: gradual percentage-based traffic routing (e.g. 5% -> 25% -> 100%)",
                "Canary minimizes blast radius by testing on real production user traffic",
                "Service Meshes (Istio) or Ingress controllers (Argo Rollouts) manage canary traffic weight"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_devops_007",
            trackId = "devops_interview",
            conceptId = "devops_cicd_gitops",
            conceptName = "CI/CD, GitOps & IaC",
            title = "GitOps Principles & ArgoCD Reconciliation",
            question = "What is GitOps, and how does an operator like ArgoCD maintain cluster state and prevent configuration drift?",
            shortAnswer = "GitOps is an operational model where Git repositories serve as the single source of truth for declared infrastructure and application state. Instead of external CI push pipelines applying changes with cluster credentials ('push model'), an in-cluster GitOps operator like ArgoCD runs inside Kubernetes ('pull model'). ArgoCD continuously compares the desired state stored in Git against the live cluster state. When drift occurs (e.g. manual kubectl edits), ArgoCD flags the application as OutOfSync and automatically reconciles (self-heals) the cluster back to match Git.",
            keyPoints = listOf(
                "Git is the declarative single source of truth for all Kubernetes manifests and configs",
                "Pull model: in-cluster agent (ArgoCD) pulls from Git; no CI credentials exposed in cluster",
                "Continuous reconciliation loop detects and heals configuration drift automatically",
                "Full auditability and commit history for every production change",
                "Instant rollback by reverting Git commits (git revert)"
            ),
            difficulty = "Staff"
        )
    )
}
