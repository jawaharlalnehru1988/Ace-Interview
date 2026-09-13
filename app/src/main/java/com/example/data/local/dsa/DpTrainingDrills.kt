package com.example.data.local.dsa

import com.example.domain.model.DrillQuiz
import com.example.domain.model.DrillTraceStep
import com.example.domain.model.TrainingDrill

val dpTrainingDrills = listOf(
    // Lesson 1
    TrainingDrill(
        id = "dp_drill_01",
        topicId = "dp",
        lessonNumber = 1,
        title = "Memoization (Top-Down Cache)",
        subtitle = "Intercepting overlapping subproblems with a cache table",
        conceptDelta = "Baseline: Adding a memo array to existing recursion to prune redundant branches",
        intuition = "In pure recursion, computing Fibonacci or climbing stairs re-evaluates the same subproblems thousands of times (O(2^N)). We add exactly ONE thing: a memo table. Before computing, check `if (memo[n] != 0) return memo[n]`. Save result before returning. That's it!",
        codePattern = """// Lesson 1: Recursion + Memoization
public int climbStairs(int n) {
    int[] memo = new int[n + 1];
    return helper(n, memo);
}
private int helper(int n, int[] memo) {
    if (n <= 2) return n;
    if (memo[n] != 0) return memo[n];
    memo[n] = helper(n - 1, memo) + helper(n - 2, memo);
    return memo[n];
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "climbStairs(4)", "memo table initialized to [0, 0, 0, 0, 0]."),
            DrillTraceStep(2, "Branch helper(3)", "Needs helper(2) and helper(1). Both hit base cases (2 and 1). memo[3] = 3."),
            DrillTraceStep(3, "Second branch helper(2) of climbStairs(4)", "memo[2] is a base case / instant return. No tree re-expansion!"),
            DrillTraceStep(4, "memo[4] = memo[3] + memo[2]", "3 + 2 = 5. Time slashed from O(2^N) to linear O(N)!")
        ),
        quizQuestion = DrillQuiz(
            question = "How does memoization reduce time complexity from exponential O(2^N) to linear O(N)?",
            options = listOf(
                "By running branches concurrently on multiple threads",
                "Because each unique state from 1 to N is computed exactly once and answered from cache in O(1) thereafter",
                "By sorting the subproblem answers",
                "By replacing the stack with a queue"
            ),
            correctOptionIndex = 1,
            explanation = "There are only N distinct subproblems. Computing each takes O(1) once its children are known, so N * O(1) = O(N)."
        )
    ),
    // Lesson 2
    TrainingDrill(
        id = "dp_drill_02",
        topicId = "dp",
        lessonNumber = 2,
        title = "Bottom-Up 1D Tabulation",
        subtitle = "Inverting recursion into an iterative table fill from base cases",
        conceptDelta = "+1 Concept: Eliminating the call stack entirely with an iterative dp[] array",
        intuition = "In Lesson 1, the computer had to recurse all the way down to base cases before returning. In Tabulation, we start AT the base cases (`dp[1]=1, dp[2]=2`) and fill forward using a simple for loop. Zero recursion overhead, zero stack overflow risk.",
        codePattern = """// Lesson 2: 1D Tabulation (Bottom-Up)
public int climbStairs(int n) {
    if (n <= 2) return n;
    int[] dp = new int[n + 1];
    dp[1] = 1;
    dp[2] = 2;
    for (int i = 3; i <= n; i++) {
        dp[i] = dp[i - 1] + dp[i - 2];
    }
    return dp[n];
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "Init: dp[1] = 1, dp[2] = 2", "Base states hardcoded directly."),
            DrillTraceStep(2, "i = 3", "dp[3] = dp[2] + dp[1] = 2 + 1 = 3."),
            DrillTraceStep(3, "i = 4", "dp[4] = dp[3] + dp[2] = 3 + 2 = 5."),
            DrillTraceStep(4, "Return dp[4]", "Returns 5. Clean, predictable O(N) time and O(N) space.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why is bottom-up tabulation generally preferred over top-down memoization in production?",
            options = listOf(
                "Tabulation has no recursive function call overhead and can never trigger StackOverflowError",
                "Tabulation solves NP-hard problems",
                "Tabulation requires no memory",
                "Tabulation runs at compile-time"
            ),
            correctOptionIndex = 0,
            explanation = "Recursive memoization consumes O(N) call stack frames, which can crash with StackOverflowError when N > 10,000. Tabulation uses a plain iterative loop."
        )
    ),
    // Lesson 3
    TrainingDrill(
        id = "dp_drill_03",
        topicId = "dp",
        lessonNumber = 3,
        title = "State Space Optimization (O(1) Space)",
        subtitle = "Rolling variables to eliminate the dp[] array",
        conceptDelta = "+1 Concept: Keeping only the previous 2 variables instead of an entire O(N) array",
        intuition = "Notice that to compute `dp[i]`, we ONLY ever look at `dp[i-1]` and `dp[i-2]`. We don't care about `dp[i-3]` or earlier! By keeping two scalar variables `prev1` and `prev2` and shifting them forward, space drops from O(N) to O(1).",
        codePattern = """// Lesson 3: Rolling Variables (O(1) Space)
public int climbStairs(int n) {
    if (n <= 2) return n;
    int prev2 = 1, prev1 = 2;
    for (int i = 3; i <= n; i++) {
        int current = prev1 + prev2;
        prev2 = prev1;
        prev1 = current;
    }
    return prev1;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "Init: prev2 = 1, prev1 = 2", "Start state for n = 4."),
            DrillTraceStep(2, "i = 3: current = 2 + 1 = 3", "Shift: prev2 becomes 2, prev1 becomes 3."),
            DrillTraceStep(3, "i = 4: current = 3 + 2 = 5", "Shift: prev2 becomes 3, prev1 becomes 5."),
            DrillTraceStep(4, "Return prev1 = 5", "Optimal answer in O(N) time and strictly O(1) space!")
        ),
        quizQuestion = DrillQuiz(
            question = "When can a dynamic programming problem be optimized to O(1) auxiliary space?",
            options = listOf(
                "Whenever the problem has subproblems",
                "When computing state `i` only depends on a constant number of immediately preceding states",
                "Only when all inputs are powers of 2",
                "Only in Java 17 and above"
            ),
            correctOptionIndex = 1,
            explanation = "If the transition only references the previous K states (where K is constant, e.g. 2 for Fibonacci), we only need K variables instead of an entire array."
        )
    ),
    // Lesson 4
    TrainingDrill(
        id = "dp_drill_04",
        topicId = "dp",
        lessonNumber = 4,
        title = "The Rob or Skip Binary Decision (House Robber)",
        subtitle = "Choosing between skipping the current house or robbing it with non-adjacent earnings",
        conceptDelta = "+1 Concept: `dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i])`",
        intuition = "In House Robber, you cannot rob adjacent houses. At house `i`, you have exactly two choices: (1) Skip house `i`: your loot is `dp[i-1]`. (2) Rob house `i`: you collect `nums[i]` plus the best loot from non-adjacent house `dp[i-2]`. Take the max of both choices!",
        codePattern = """// Lesson 4: Non-Adjacent Decision State
public int rob(int[] nums) {
    if (nums.length == 1) return nums[0];
    int robPrev2 = 0; // Represents dp[i-2]
    int robPrev1 = 0; // Represents dp[i-1]
    
    for (int num : nums) {
        int current = Math.max(robPrev1, robPrev2 + num);
        robPrev2 = robPrev1;
        robPrev1 = current;
    }
    return robPrev1;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "nums = [2, 7, 9, 3, 1]", "robPrev2 = 0, robPrev1 = 0."),
            DrillTraceStep(2, "House 2: max(0, 0 + 2) = 2", "robPrev2 = 0, robPrev1 = 2."),
            DrillTraceStep(3, "House 7: max(2, 0 + 7) = 7", "robPrev2 = 2, robPrev1 = 7."),
            DrillTraceStep(4, "House 9: max(7, 2 + 9) = 11", "robPrev2 = 7, robPrev1 = 11."),
            DrillTraceStep(5, "House 3: max(11, 7 + 3) = 11", "robPrev2 = 11, robPrev1 = 11."),
            DrillTraceStep(6, "House 1: max(11, 11 + 1) = 12", "Result: 12 (robbing houses 2, 9, 1).")
        ),
        quizQuestion = DrillQuiz(
            question = "Why is it guaranteed that we never rob two adjacent houses?",
            options = listOf(
                "Because when we add `num`, we add it strictly to `robPrev2` (house i - 2), naturally skipping house i - 1",
                "Because police are simulated in software",
                "Because nums is sorted",
                "It is not guaranteed"
            ),
            correctOptionIndex = 0,
            explanation = "The recurrence relation strictly pairs nums[i] with dp[i-2], maintaining an untouched buffer house between any two robbed targets."
        )
    ),
    // Lesson 5
    TrainingDrill(
        id = "dp_drill_05",
        topicId = "dp",
        lessonNumber = 5,
        title = "Circular Array Decoupling (House Robber II)",
        subtitle = "Breaking circular dependencies into two independent linear DP passes",
        conceptDelta = "+1 Concept: Circular dependency: house 0 and house N-1 cannot both be robbed; solve max(rob(0, N-2), rob(1, N-1))",
        intuition = "In a circle, house 0 and house N-1 are adjacent. If you rob house 0, you CANNOT rob house N-1. If you rob house N-1, you CANNOT rob house 0. The circular problem breaks cleanly into two simple linear runs: (1) Houses `0` to `N - 2`, and (2) Houses `1` to `N - 1`. The global maximum is simply `max(pass1, pass2)`!",
        codePattern = """// Lesson 5: Circular DP Decoupling
public int rob(int[] nums) {
    if (nums.length == 1) return nums[0];
    return Math.max(robLinear(nums, 0, nums.length - 2), 
                    robLinear(nums, 1, nums.length - 1));
}
private int robLinear(int[] nums, int start, int end) {
    int prev2 = 0, prev1 = 0;
    for (int i = start; i <= end; i++) {
        int cur = Math.max(prev1, prev2 + nums[i]);
        prev2 = prev1;
        prev1 = cur;
    }
    return prev1;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "nums = [2, 3, 2] (circular)", "Pass 1 on [2, 3]: returns 3. Pass 2 on [3, 2]: returns 3."),
            DrillTraceStep(2, "nums = [1, 2, 3, 1]", "Pass 1 on [1, 2, 3]: max is 1 + 3 = 4. Pass 2 on [2, 3, 1]: max is 2 + 1 = 3."),
            DrillTraceStep(3, "Result: max(4, 3) = 4", "Circular constraint solved without any complex circular indexing.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why does running two linear passes cover all possible optimal solutions in a circular array?",
            options = listOf(
                "Because house 0 and house N-1 cannot both be in the optimal set; the optimal set either excludes house N-1 or excludes house 0",
                "Because the array length is always even",
                "Because house 1 is always robbed",
                "It is an approximation that misses edge cases"
            ),
            correctOptionIndex = 0,
            explanation = "Since houses 0 and N-1 are mutually exclusive, any valid subset of houses must omit at least one of them. The two ranges [0..N-2] and [1..N-1] exhaustively cover both possibilities."
        )
    ),
    // Lesson 6
    TrainingDrill(
        id = "dp_drill_06",
        topicId = "dp",
        lessonNumber = 6,
        title = "Unbounded Knapsack Min Cost (Coin Change)",
        subtitle = "Finding the minimum coins to make up an amount using forward 1D transition",
        conceptDelta = "+1 Concept: `dp[a] = Math.min(dp[a], dp[a - coin] + 1)` with initialized infinity values",
        intuition = "To make up amount `a` using coins with infinite supply (unbounded knapsack): if we use `coin`, we need 1 coin plus the minimum coins to make `a - coin`. Initialize `dp[0] = 0` and all other amounts to infinity (`amount + 1`). Iterate forward!",
        codePattern = """// Lesson 6: Unbounded Coin Change Min
public int coinChange(int[] coins, int amount) {
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, amount + 1); // Sentinel infinity
    dp[0] = 0; // 0 coins needed for amount 0
    
    for (int a = 1; a <= amount; a++) {
        for (int coin : coins) {
            if (a - coin >= 0) {
                dp[a] = Math.min(dp[a], dp[a - coin] + 1);
            }
        }
    }
    return dp[amount] > amount ? -1 : dp[amount];
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "coins = [1, 2, 5], amount = 11", "dp[0] = 0, all others initialized to 12."),
            DrillTraceStep(2, "a = 1: coin 1", "dp[1] = min(12, dp[0] + 1) = 1."),
            DrillTraceStep(3, "a = 2: coin 1 and 2", "dp[2] = min(dp[1]+1, dp[0]+1) = min(2, 1) = 1."),
            DrillTraceStep(4, "a = 11: uses coin 5 + dp[6]=2", "dp[11] = 1 + 2 = 3 coins (5 + 5 + 1).")
        ),
        quizQuestion = DrillQuiz(
            question = "Why is `dp` initialized with `amount + 1` instead of `Integer.MAX_VALUE`?",
            options = listOf(
                "Because `Integer.MAX_VALUE + 1` overflows to a negative number, breaking Math.min",
                "Because amount + 1 is faster to write",
                "Because arrays cannot hold MAX_VALUE",
                "To save memory"
            ),
            correctOptionIndex = 0,
            explanation = "If dp[a - coin] is Integer.MAX_VALUE, adding 1 causes 32-bit signed integer overflow into Integer.MIN_VALUE, corrupting the minimum."
        )
    ),
    // Lesson 7
    TrainingDrill(
        id = "dp_drill_07",
        topicId = "dp",
        lessonNumber = 7,
        title = "Order Independence: Combinations vs Permutations",
        subtitle = "Loop order dictates whether coin orders matter (Coin Change II)",
        conceptDelta = "+1 Concept: Coins on outer loop = Combinations (no order); Coins on inner loop = Permutations (order matters)",
        intuition = "This is one of the most critical insights in all of DP! If COIN is on the OUTER loop and AMOUNT is on the INNER loop, each coin is introduced once, preventing duplicate permutations like [1, 2] and [2, 1]. The transition: `dp[a] += dp[a - coin]` counts total unique combinations.",
        codePattern = """// Lesson 7: Combinations via Outer Coin Loop
public int change(int amount, int[] coins) {
    int[] dp = new int[amount + 1];
    dp[0] = 1; // 1 way to make 0: pick nothing
    
    // Outer loop over COINS enforces combination order
    for (int coin : coins) {
        for (int a = coin; a <= amount; a++) {
            dp[a] += dp[a - coin];
        }
    }
    return dp[amount];
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "coins = [1, 2], amount = 3", "dp = [1, 0, 0, 0]."),
            DrillTraceStep(2, "Process coin = 1", "dp becomes [1, 1, 1, 1] (all 1s)."),
            DrillTraceStep(3, "Process coin = 2", "a = 2: dp[2] += dp[0] = 1+1 = 2. a = 3: dp[3] += dp[1] = 1+1 = 2."),
            DrillTraceStep(4, "Result: 2 ways", "Combinations are [1, 1, 1] and [1, 2]. Zero duplicate orderings!")
        ),
        quizQuestion = DrillQuiz(
            question = "What would happen if the outer loop was `amount` and the inner loop was `coins`?",
            options = listOf(
                "It would compute the number of PERMUTATIONS (where [1, 2] and [2, 1] are counted as two different ways)",
                "It would compute the exact same answer",
                "It would crash with ArrayIndexOutOfBoundsException",
                "It would return 0"
            ),
            correctOptionIndex = 0,
            explanation = "Looping over amounts first allows choosing coin 1 then coin 2, and in another branch choosing coin 2 then coin 1, generating all ordered permutations."
        )
    ),
    // Lesson 8
    TrainingDrill(
        id = "dp_drill_08",
        topicId = "dp",
        lessonNumber = 8,
        title = "Kadane as a 1D DP State Machine",
        subtitle = "Formalizing local vs global optimum as a dynamic programming recurrence",
        conceptDelta = "+1 Concept: `dp[i] = Math.max(nums[i], dp[i - 1] + nums[i])`; space-optimized to single scalar",
        intuition = "In Arrays (Lesson 13) we met Kadane's algorithm intuitively. Now see its true mathematical identity: it is a 1D Dynamic Programming state machine! State `dp[i]` represents the maximum subarray sum ending at index `i`. Recurrence: `dp[i] = max(nums[i], dp[i-1] + nums[i])`.",
        codePattern = """// Lesson 8: Kadane DP Form
public int maxSubArray(int[] nums) {
    int dp = nums[0]; // Represents dp[i-1]
    int maxSoFar = nums[0];
    
    for (int i = 1; i < nums.length; i++) {
        dp = Math.max(nums[i], dp + nums[i]);
        maxSoFar = Math.max(maxSoFar, dp);
    }
    return maxSoFar;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "nums = [-2, 1, -3, 4, -1, 2, 1]", "Init: dp = -2, maxSoFar = -2."),
            DrillTraceStep(2, "i = 1 (val 1)", "dp = max(1, -2 + 1) = 1. maxSoFar = 1."),
            DrillTraceStep(3, "i = 2 (val -3)", "dp = max(-3, 1 - 3) = -2. maxSoFar = 1."),
            DrillTraceStep(4, "i = 3 (val 4)", "dp = max(4, -2 + 4) = 4. maxSoFar = 4."),
            DrillTraceStep(5, "i = 4..6", "dp accumulates to 6. maxSoFar = 6.")
        ),
        quizQuestion = DrillQuiz(
            question = "What does the state `dp[i]` represent in Kadane's recurrence?",
            options = listOf(
                "The maximum sum of any contiguous subarray that MUST end at index `i`",
                "The global maximum of the entire array",
                "The prefix sum up to i",
                "The count of positive numbers"
            ),
            correctOptionIndex = 0,
            explanation = "Defining dp[i] as ending strictly at index i guarantees that extending to i+1 is contiguous, allowing local decisions to produce global optimality."
        )
    ),
    // Lesson 9
    TrainingDrill(
        id = "dp_drill_09",
        topicId = "dp",
        lessonNumber = 9,
        title = "Greedy-DP Horizon Boundary (Jump Game II)",
        subtitle = "Transitioning from O(N²) DP to an O(N) BFS horizon window",
        conceptDelta = "+1 Concept: `curEnd` represents current jump boundary; whenever `i == curEnd`, jumps++ and `curEnd = maxFarthest`",
        intuition = "Finding minimum jumps to reach the end naively takes O(N²) DP: `dp[i] = min(dp[j] + 1)`. We can optimize this to O(N) by viewing jumps as BFS levels! `curEnd` is the boundary of the current jump level. As you step through, accumulate `maxFarthest = max(maxFarthest, i + nums[i])`. When `i == curEnd`, you MUST jump: increment jumps and update `curEnd = maxFarthest`.",
        codePattern = """// Lesson 9: Greedy BFS-DP Horizon
public int jump(int[] nums) {
    int jumps = 0, curEnd = 0, maxFarthest = 0;
    for (int i = 0; i < nums.length - 1; i++) {
        maxFarthest = Math.max(maxFarthest, i + nums[i]);
        if (i == curEnd) {
            jumps++;
            curEnd = maxFarthest; // Expand horizon to next level
        }
    }
    return jumps;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "nums = [2, 3, 1, 1, 4]", "jumps = 0, curEnd = 0, maxFarthest = 0."),
            DrillTraceStep(2, "i = 0: nums[0] = 2", "maxFarthest = 2. i == curEnd(0) -> jumps = 1, curEnd = 2."),
            DrillTraceStep(3, "i = 1: nums[1] = 3", "maxFarthest = max(2, 1 + 3) = 4."),
            DrillTraceStep(4, "i = 2: nums[2] = 1", "maxFarthest = 4. i == curEnd(2) -> jumps = 2, curEnd = 4."),
            DrillTraceStep(5, "Loop exits before last index", "Returns 2 jumps. Time O(N), Space O(1).")
        ),
        quizQuestion = DrillQuiz(
            question = "Why does the loop terminate at `nums.length - 1` instead of `nums.length`?",
            options = listOf(
                "Because once you reach the last element, no additional jump is required to reach the destination",
                "To prevent ArrayIndexOutOfBoundsException",
                "Because array lengths are even",
                "It is an arbitrary cutoff"
            ),
            correctOptionIndex = 0,
            explanation = "If you stepped into index N-1 and i == curEnd triggered, jumps would mistakenly increment an extra unnecessary jump."
        )
    ),
    // Lesson 10
    TrainingDrill(
        id = "dp_drill_10",
        topicId = "dp",
        lessonNumber = 10,
        title = "Dual History Branching (Decode Ways)",
        subtitle = "Evaluating single-digit and double-digit transitions with zero constraints",
        conceptDelta = "+1 Concept: `dp[i] = (valid 1-digit ? dp[i - 1] : 0) + (valid 2-digit ? dp[i - 2] : 0)`",
        intuition = "In Decode Ways ('1'='A'..'26'='Z'): at character `i`, you can decode it as: (1) A single digit (valid if `s[i] != '0'`) -> contributes `dp[i-1]`. (2) A two-digit pair with previous char (valid if `10 <= pair <= 26`) -> contributes `dp[i-2]`. Zero handling is strictly enforced!",
        codePattern = """// Lesson 10: 1-Digit and 2-Digit Decode DP
public int numDecodings(String s) {
    if (s.charAt(0) == '0') return 0;
    int prev2 = 1, prev1 = 1;
    
    for (int i = 1; i < s.length(); i++) {
        int current = 0;
        int oneDigit = s.charAt(i) - '0';
        int twoDigit = Integer.parseInt(s.substring(i - 1, i + 1));
        
        if (oneDigit != 0) current += prev1;
        if (twoDigit >= 10 && twoDigit <= 26) current += prev2;
        
        prev2 = prev1;
        prev1 = current;
    }
    return prev1;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "s = \"226\"", "prev2 = 1, prev1 = 1."),
            DrillTraceStep(2, "i = 1 ('2')", "oneDigit = 2 (!=0 -> +prev1=1). twoDigit = 22 (10..26 -> +prev2=1). current = 2. prev2=1, prev1=2."),
            DrillTraceStep(3, "i = 2 ('6')", "oneDigit = 6 (+prev1=2). twoDigit = 26 (+prev2=1). current = 2 + 1 = 3."),
            DrillTraceStep(4, "Result: 3", "Decodings: \"BZ\" (2, 26), \"VF\" (22, 6), \"BBF\" (2, 2, 6).")
        ),
        quizQuestion = DrillQuiz(
            question = "What happens when `oneDigit == 0` and `twoDigit > 26` (e.g. \"30\")?",
            options = listOf(
                "`current` remains 0, meaning zero valid decodings exist and the string cannot be parsed",
                "It defaults to 'Z'",
                "It crashes",
                "It ignores the zero"
            ),
            correctOptionIndex = 0,
            explanation = "Since '0' cannot stand alone and '30' is not a valid alphabet code, neither condition triggers, setting current to 0."
        )
    ),
    // Lesson 11
    TrainingDrill(
        id = "dp_drill_11",
        topicId = "dp",
        lessonNumber = 11,
        title = "Quadratic Longest Increasing Subsequence (LIS)",
        subtitle = "The classic O(N²) predecessor scanning dynamic programming pattern",
        conceptDelta = "+1 Concept: `dp[i] = Math.max(dp[i], dp[j] + 1)` for all `j < i` where `nums[j] < nums[i]`",
        intuition = "In LIS, state `dp[i]` is the length of the longest increasing subsequence ending at index `i`. For each element `i`, inspect all preceding elements `j < i`. If `nums[j] < nums[i]`, we can extend the subsequence ending at `j` by 1! Maximize over all valid `j`.",
        codePattern = """// Lesson 11: O(N²) LIS DP
public int lengthOfLIS(int[] nums) {
    if (nums.length == 0) return 0;
    int[] dp = new int[nums.length];
    Arrays.fill(dp, 1); // Baseline: each element is an LIS of length 1
    int maxLIS = 1;
    
    for (int i = 1; i < nums.length; i++) {
        for (int j = 0; j < i; j++) {
            if (nums[j] < nums[i]) {
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        maxLIS = Math.max(maxLIS, dp[i]);
    }
    return maxLIS;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "nums = [10, 9, 2, 5, 3, 7, 101, 18]", "All dp initialized to 1."),
            DrillTraceStep(2, "i = 3 (val 5)", "j = 2 (val 2) < 5: dp[3] = max(1, dp[2]+1) = 2 ([2, 5])."),
            DrillTraceStep(3, "i = 5 (val 7)", "Can extend from 5 or 3: dp[5] = max(..., dp[3]+1) = 3 ([2, 5, 7])."),
            DrillTraceStep(4, "i = 6 (val 101)", "Can extend from 7: dp[6] = 3 + 1 = 4 ([2, 5, 7, 101])."),
            DrillTraceStep(5, "Final maxLIS", "Returns 4 in O(N²) time.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why is every element in `dp` initialized to 1?",
            options = listOf(
                "Because a single element on its own forms a valid increasing subsequence of length 1",
                "Because 0 is not a valid length",
                "To prevent division by zero",
                "Because of 1-based indexing"
            ),
            correctOptionIndex = 0,
            explanation = "Even if no preceding smaller element exists, the element itself constitutes a trivial increasing subsequence of length 1."
        )
    ),
    // Lesson 12
    TrainingDrill(
        id = "dp_drill_12",
        topicId = "dp",
        lessonNumber = 12,
        title = "Patience Sorting LIS in O(N log N)",
        subtitle = "Replacing linear inner scan with binary search replacement (Tails Array)",
        conceptDelta = "+1 Concept: `tails[i]` stores smallest tail of all increasing subsequences of length `i+1`; binary search to update",
        intuition = "Can we do LIS in O(N log N)? Yes! Maintain array `tails` where `tails[len]` stores the smallest possible ending element of an increasing subsequence of length `len+1`. Since `tails` is strictly sorted, for each number, use binary search (`Arrays.binarySearch`) to find where it belongs. Either extend length or replace a tail with a smaller, more promising value!",
        codePattern = """// Lesson 12: O(N log N) Patience Sorting LIS
public int lengthOfLIS(int[] nums) {
    int[] tails = new int[nums.length];
    int size = 0;
    for (int x : nums) {
        int i = 0, j = size;
        while (i < j) {
            int mid = i + (j - i) / 2;
            if (tails[mid] < x) i = mid + 1;
            else j = mid;
        }
        tails[i] = x;
        if (i == size) size++;
    }
    return size;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "x = 10", "tails = [10], size = 1."),
            DrillTraceStep(2, "x = 9", "9 replaces 10 (smaller tail is better!). tails = [9], size = 1."),
            DrillTraceStep(3, "x = 2", "2 replaces 9. tails = [2], size = 1."),
            DrillTraceStep(4, "x = 5", "5 > 2 -> appends! tails = [2, 5], size = 2."),
            DrillTraceStep(5, "x = 3", "3 replaces 5 (better tail for len 2). tails = [2, 3], size = 2."),
            DrillTraceStep(6, "x = 7", "tails = [2, 3, 7], size = 3. Final size = 4 in O(N log N) time.")
        ),
        quizQuestion = DrillQuiz(
            question = "Does the `tails` array always store the actual elements of the longest increasing subsequence?",
            options = listOf(
                "No, `tails` only tracks the optimal tail values and length; its contents may be a hybrid of elements from different subsequences",
                "Yes, it is always identical to the subsequence",
                "Only for sorted arrays",
                "Yes, because binary search preserves order"
            ),
            correctOptionIndex = 0,
            explanation = "Replacing an element in tails maintains the correct length and optimal thresholds for future extensions, but does not represent the literal subsequence order."
        )
    ),
    // Lesson 13
    TrainingDrill(
        id = "dp_drill_13",
        topicId = "dp",
        lessonNumber = 13,
        title = "The 2D Grid Match Matrix (Longest Common Subsequence)",
        subtitle = "Navigating match diagonals vs mismatch max-propagation",
        conceptDelta = "+1 Concept: `if (s1[i]==s2[j]) dp[i][j] = 1 + dp[i-1][j-1]; else max(dp[i-1][j], dp[i][j-1])`",
        intuition = "In LCS between text1 and text2: compare `c1 = text1[i-1]` and `c2 = text2[j-1]`. If they match, take the diagonal answer and add 1 (`1 + dp[i-1][j-1]`). If they mismatch, the answer is the best you can get by dropping either `c1` or `c2`: `max(dp[i-1][j], dp[i][j-1])`. Base cases are 0s.",
        codePattern = """// Lesson 13: 2D Longest Common Subsequence
public int longestCommonSubsequence(String text1, String text2) {
    int m = text1.length(), n = text2.length();
    int[][] dp = new int[m + 1][n + 1];
    
    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                dp[i][j] = 1 + dp[i - 1][j - 1]; // Diagonal match
            } else {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); // Propagate best
            }
        }
    }
    return dp[m][n];
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "text1 = \"abcde\", text2 = \"ace\"", "Matrix size 6x4."),
            DrillTraceStep(2, "Match at (1, 1): 'a' == 'a'", "dp[1][1] = 1 + dp[0][0] = 1."),
            DrillTraceStep(3, "Mismatch at (2, 1): 'b' != 'a'", "dp[2][1] = max(dp[1][1], dp[2][0]) = max(1, 0) = 1."),
            DrillTraceStep(4, "Matches accumulate at 'a', 'c', 'e'", "Final cell dp[5][3] = 3 (\"ace\").")
        ),
        quizQuestion = DrillQuiz(
            question = "Why do we allocate an `(m + 1) x (n + 1)` matrix instead of `m x n`?",
            options = listOf(
                "Row 0 and Column 0 serve as empty string base cases initialized to 0, eliminating boundary if checks",
                "Because Java 2D arrays require padding",
                "To store null terminators",
                "To prevent integer overflow"
            ),
            correctOptionIndex = 0,
            explanation = "Using 1-based indexing where index 0 represents an empty prefix allows dp[i-1][j-1] to cleanly evaluate to 0 without out-of-bounds guards."
        )
    ),
    // Lesson 14
    TrainingDrill(
        id = "dp_drill_14",
        topicId = "dp",
        lessonNumber = 14,
        title = "The 3-Operation Levenshtein Metric (Edit Distance)",
        subtitle = "Balancing insertion, deletion, and substitution in a 2D cost matrix",
        conceptDelta = "+1 Concept: On mismatch: `dp[i][j] = 1 + min(dp[i-1][j] (del), dp[i][j-1] (ins), dp[i-1][j-1] (rep))`",
        intuition = "To find minimum edits to transform word1 to word2: if `word1[i-1] == word2[j-1]`, zero cost: `dp[i-1][j-1]`. If they mismatch, take 1 + minimum of: (1) Delete from word1 (`dp[i-1][j]`), (2) Insert into word1 (`dp[i][j-1]`), or (3) Replace character (`dp[i-1][j-1]`).",
        codePattern = """// Lesson 14: Levenshtein Distance Matrix
public int minDistance(String word1, String word2) {
    int m = word1.length(), n = word2.length();
    int[][] dp = new int[m + 1][n + 1];
    for (int i = 0; i <= m; i++) dp[i][0] = i; // Deleting i chars to reach empty
    for (int j = 0; j <= n; j++) dp[0][j] = j; // Inserting j chars from empty
    
    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                dp[i][j] = dp[i - 1][j - 1];
            } else {
                dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], // Replace
                               Math.min(dp[i - 1][j],     // Delete
                                        dp[i][j - 1]));   // Insert
            }
        }
    }
    return dp[m][n];
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "word1 = \"horse\", word2 = \"ros\"", "Base row/col initialized: dp[i][0] = i, dp[0][j] = j."),
            DrillTraceStep(2, "Cell (1, 1): 'h' != 'r'", "1 + min(replace 0, del 1, ins 1) = 1 + 0 = 1 (replace 'h' with 'r')."),
            DrillTraceStep(3, "Cell (2, 2): 'o' == 'o'", "Match! dp[2][2] = dp[1][1] = 1."),
            DrillTraceStep(4, "Final cell dp[5][3] = 3", "3 operations: replace 'h' with 'r', remove 'r', remove 'e'.")
        ),
        quizQuestion = DrillQuiz(
            question = "What operation does transitioning from `dp[i - 1][j]` correspond to?",
            options = listOf(
                "Deleting character `word1[i - 1]`",
                "Inserting a character into word1",
                "Replacing character word1[i - 1]",
                "Transposing adjacent characters"
            ),
            correctOptionIndex = 0,
            explanation = "Moving from row i-1 to row i consumes a character from word1 without advancing word2, representing a deletion."
        )
    ),
    // Lesson 15
    TrainingDrill(
        id = "dp_drill_15",
        topicId = "dp",
        lessonNumber = 15,
        title = "Dual Option Subsequence Counting (Distinct Subsequences)",
        subtitle = "Counting ways to form target string T as a subsequence of source S",
        conceptDelta = "+1 Concept: On match: `dp[i][j] = dp[i-1][j-1] (use match) + dp[i-1][j] (ignore match)`",
        intuition = "In Distinct Subsequences, we count how many times string T appears in string S. At `s[i-1]` and `t[j-1]`: if they mismatch, we MUST skip `s[i-1]`: `dp[i][j] = dp[i-1][j]`. If they match, we have TWO choices: use this character (`dp[i-1][j-1]`) OR skip it and look for another match in S (`dp[i-1][j]`)! Sum both.",
        codePattern = """// Lesson 15: Distinct Subsequence Counting
public int numDistinct(String s, String t) {
    int m = s.length(), n = t.length();
    int[][] dp = new int[m + 1][n + 1];
    for (int i = 0; i <= m; i++) dp[i][0] = 1; // 1 way to form empty string T
    
    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (s.charAt(i - 1) == t.charAt(j - 1)) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j]; // Use + Skip
            } else {
                dp[i][j] = dp[i - 1][j];                     // Skip only
            }
        }
    }
    return dp[m][n];
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "s = \"rabbbit\", t = \"rabbit\"", "Base col 0 is all 1s."),
            DrillTraceStep(2, "Encounter 'b' matches", "There are 3 'b's in s and 2 in t."),
            DrillTraceStep(3, "Use + Skip adds combinations", "Choosing 2 'b's out of 3 gives 3 ways!"),
            DrillTraceStep(4, "Result: dp[7][6] = 3", "3 distinct occurrences of \"rabbit\" in \"rabbbit\".")
        ),
        quizQuestion = DrillQuiz(
            question = "Why does `dp[i][0] = 1` for all `i`?",
            options = listOf(
                "Because an empty string T can always be formed by deleting all remaining characters of S (exactly 1 way)",
                "Because 0 is not a valid character",
                "To avoid division by zero",
                "It is a placeholder"
            ),
            correctOptionIndex = 0,
            explanation = "An empty target string T is formed by picking the empty subsequence from any prefix of S, which can only be done in exactly 1 way."
        )
    ),
    // Lesson 16
    TrainingDrill(
        id = "dp_drill_16",
        topicId = "dp",
        lessonNumber = 16,
        title = "Grid Lattice Path Addition (Unique Paths)",
        subtitle = "Applying Pascal's triangle addition across a 2D coordinate grid",
        conceptDelta = "+1 Concept: `dp[r][c] = dp[r - 1][c] + dp[r][c - 1]`; optimizable to 1D rolling array",
        intuition = "A robot starts at `(0, 0)` and can only move Down or Right. The number of paths to cell `(r, c)` is simply the paths coming from above (`r-1, c`) PLUS the paths coming from the left (`r, c-1`). This is mathematically Pascal's Triangle in a matrix! A single 1D array of size N can roll the values.",
        codePattern = """// Lesson 16: Rolling 1D Grid Paths
public int uniquePaths(int m, int n) {
    int[] dp = new int[n];
    Arrays.fill(dp, 1); // Row 0 is all 1s
    
    for (int r = 1; r < m; r++) {
        for (int c = 1; c < n; c++) {
            dp[c] += dp[c - 1]; // dp[c] is from above; dp[c-1] is from left
        }
    }
    return dp[n - 1];
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "m = 3, n = 3", "dp initialized to [1, 1, 1]."),
            DrillTraceStep(2, "r = 1: c = 1", "dp[1] += dp[0] = 1 + 1 = 2."),
            DrillTraceStep(3, "r = 1: c = 2", "dp[2] += dp[1] = 1 + 2 = 3. dp is now [1, 2, 3]."),
            DrillTraceStep(4, "r = 2", "dp becomes [1, 3, 6]. Result = 6 paths.")
        ),
        quizQuestion = DrillQuiz(
            question = "What closed-form mathematical formula calculates `uniquePaths(m, n)` in O(M) time?",
            options = listOf(
                "Combination: C(m + n - 2, m - 1)",
                "(m * n) / 2",
                "2^(m + n)",
                "m! * n!"
            ),
            correctOptionIndex = 0,
            explanation = "The robot must take exactly (m - 1) down steps and (n - 1) right steps in any order. The total combinations is C((m-1) + (n-1), m-1)."
        )
    ),
    // Lesson 17
    TrainingDrill(
        id = "dp_drill_17",
        topicId = "dp",
        lessonNumber = 17,
        title = "Obstacle State Annihilation (Unique Paths II)",
        subtitle = "Zeroing path count whenever a grid obstacle is encountered",
        conceptDelta = "+1 Concept: `if (obstacleGrid[r][c] == 1) dp[c] = 0;` (obstacles destroy all path continuity)",
        intuition = "In Unique Paths with obstacles: if a cell has an obstacle (`grid[r][c] == 1`), zero paths can pass through it! We simply set `dp[c] = 0`. Any cell trying to read from it will add 0, seamlessly routing paths around the blockage with zero branching logic.",
        codePattern = """// Lesson 17: Obstacle Routing DP
public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    int width = obstacleGrid[0].length;
    int[] dp = new int[width];
    dp[0] = 1; // Start cell
    
    for (int[] row : obstacleGrid) {
        for (int c = 0; c < width; c++) {
            if (row[c] == 1) {
                dp[c] = 0; // Blocked cell destroys path flow
            } else if (c > 0) {
                dp[c] += dp[c - 1];
            }
        }
    }
    return dp[width - 1];
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "grid = [[0,0,0],[0,1,0],[0,0,0]]", "dp[0] = 1."),
            DrillTraceStep(2, "Row 0: no obstacles", "dp becomes [1, 1, 1]."),
            DrillTraceStep(3, "Row 1: obstacle at (1, 1)", "dp[1] = 0! dp[2] += dp[1] = 1 + 0 = 1. dp is [1, 0, 1]."),
            DrillTraceStep(4, "Row 2: routes around obstacle", "dp[1] += dp[0] = 1. dp[2] += dp[1] = 1 + 1 = 2."),
            DrillTraceStep(5, "Result: 2 paths", "Obstacle successfully skirted.")
        ),
        quizQuestion = DrillQuiz(
            question = "What happens if the starting cell `obstacleGrid[0][0] == 1`?",
            options = listOf(
                "On the very first step, `dp[0]` is zeroed to 0, causing the entire grid to evaluate to 0 paths",
                "It crashes",
                "The robot jumps over it",
                "It returns 1"
            ),
            correctOptionIndex = 0,
            explanation = "If the start cell is an obstacle, row[0] == 1 triggers dp[0] = 0 immediately, correctly producing 0 paths."
        )
    ),
    // Lesson 18
    TrainingDrill(
        id = "dp_drill_18",
        topicId = "dp",
        lessonNumber = 18,
        title = "In-Place Matrix Cost Accumulation (Min Path Sum)",
        subtitle = "Overwriting the input grid in-place with running path sums",
        conceptDelta = "+1 Concept: `grid[r][c] += Math.min(grid[r - 1][c], grid[r][c - 1])`",
        intuition = "To find the path with the minimum sum from top-left to bottom-right: each cell's optimal entry cost is `min(cost from top, cost from left)`. Rather than allocating a new matrix, update `grid[r][c]` directly in-place! The bottom-right cell `grid[m-1][n-1]` holds the final answer in O(1) extra space.",
        codePattern = """// Lesson 18: In-Place Path Cost Accumulation
public int minPathSum(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    for (int r = 0; r < m; r++) {
        for (int c = 0; c < n; c++) {
            if (r == 0 && c == 0) continue;
            if (r == 0) grid[r][c] += grid[r][c - 1];       // First row: from left only
            else if (c == 0) grid[r][c] += grid[r - 1][c];  // First col: from top only
            else grid[r][c] += Math.min(grid[r - 1][c], grid[r][c - 1]);
        }
    }
    return grid[m - 1][n - 1];
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "grid = [[1,3,1],[1,5,1],[4,2,1]]", "Row 0 becomes: [1, 1+3=4, 4+1=5]."),
            DrillTraceStep(2, "Col 0 becomes", "[1, 1+1=2, 2+4=6]."),
            DrillTraceStep(3, "Cell (1, 1)", "grid[1][1] = 5 + min(top 4, left 2) = 5 + 2 = 7."),
            DrillTraceStep(4, "Accumulation finishes at (2, 2)", "Returns 7 (path: 1 -> 3 -> 1 -> 1 -> 1).")
        ),
        quizQuestion = DrillQuiz(
            question = "When is in-place modification of the input matrix acceptable in production?",
            options = listOf(
                "When documented in API contracts and memory optimization is critical, or when the input array is not reused by caller",
                "Always, without exception",
                "Never, under any circumstances",
                "Only on Android"
            ),
            correctOptionIndex = 0,
            explanation = "In-place modification saves O(M*N) memory allocation, but must be agreed upon if caller expects immutability."
        )
    ),
    // Lesson 19
    TrainingDrill(
        id = "dp_drill_19",
        topicId = "dp",
        lessonNumber = 19,
        title = "The 3-Neighbor Constraint (Maximal Square)",
        subtitle = "Finding the largest square of 1s using a 3-way min transition",
        conceptDelta = "+1 Concept: `dp[r][c] = 1 + Math.min(dp[r-1][c], Math.min(dp[r][c-1], dp[r-1][c-1]))`",
        intuition = "A square of size K requires ALL THREE neighbors—left, top, and top-left diagonal—to be at least size K-1! If any of the three neighbors has a smaller square, it acts as a bottleneck. When `matrix[r][c] == '1'`, `dp[r][c] = 1 + min(top, left, diagonal)`. Area is `maxSide * maxSide`.",
        codePattern = """// Lesson 19: 3-Neighbor Square DP
public int maximalSquare(char[][] matrix) {
    int m = matrix.length, n = matrix[0].length;
    int[][] dp = new int[m + 1][n + 1];
    int maxSide = 0;
    
    for (int r = 1; r <= m; r++) {
        for (int c = 1; c <= n; c++) {
            if (matrix[r - 1][c - 1] == '1') {
                dp[r][c] = 1 + Math.min(dp[r - 1][c - 1], // Diagonal
                               Math.min(dp[r - 1][c],     // Top
                                        dp[r][c - 1]));   // Left
                maxSide = Math.max(maxSide, dp[r][c]);
            }
        }
    }
    return maxSide * maxSide; // Area
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "matrix contains 2x2 block of '1's at (0,0)..(1,1)", "dp[1][1] = 1."),
            DrillTraceStep(2, "dp[1][2] = 1, dp[2][1] = 1", "Left and top cells are size 1."),
            DrillTraceStep(3, "Cell dp[2][2]: min(top 1, left 1, diag 1) = 1", "dp[2][2] = 1 + 1 = 2 (square of side 2 confirmed!)."),
            DrillTraceStep(4, "maxSide = 2", "Area = 2 * 2 = 4.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why must the diagonal neighbor `dp[r-1][c-1]` be checked in addition to top and left?",
            options = listOf(
                "Because a cell can have 1s to its left and top while the top-left corner is a '0', which prevents forming a valid solid square",
                "Because squares have diagonal symmetry",
                "To prevent integer overflow",
                "It is optional"
            ),
            correctOptionIndex = 0,
            explanation = "Without the diagonal check, an L-shaped corner of 1s with a '0' in the interior would be mistakenly counted as a square."
        )
    ),
    // Lesson 20
    TrainingDrill(
        id = "dp_drill_20",
        topicId = "dp",
        lessonNumber = 20,
        title = "Bottom-Up Triangular Reduction",
        subtitle = "Collapsing a triangle from the bottom row up to avoid boundary edge cases",
        conceptDelta = "+1 Concept: Traverse rows backwards from bottom: `dp[c] = val + Math.min(dp[c], dp[c + 1])`",
        intuition = "In Triangle, top-down DP requires complex boundary checks for left and right edges. Reverse the direction: start at the bottom row! For each row moving UPWARDS: `dp[c] = triangle[r][c] + min(dp[c], dp[c+1])`. The two children are naturally adjacent in the row below. When row 0 is reached, `dp[0]` holds the answer with ZERO edge checks!",
        codePattern = """// Lesson 20: Bottom-Up Triangle Collapse
public int minimumTotal(List<List<Integer>> triangle) {
    int n = triangle.size();
    int[] dp = new int[n + 1];
    // Collapse rows from bottom to top
    for (int r = n - 1; r >= 0; r--) {
        List<Integer> row = triangle.get(r);
        for (int c = 0; c <= r; c++) {
            dp[c] = row.get(c) + Math.min(dp[c], dp[c + 1]);
        }
    }
    return dp[0];
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "triangle: [[2], [3,4], [6,5,7], [4,1,8,3]]", "Init dp of size 5 with all 0s."),
            DrillTraceStep(2, "Row 3 (bottom): [4, 1, 8, 3]", "dp becomes [4, 1, 8, 3, 0]."),
            DrillTraceStep(3, "Row 2: [6, 5, 7]", "dp[0] = 6 + min(4, 1) = 7. dp[1] = 5 + min(1, 8) = 6. dp[2] = 7 + min(8, 3) = 10."),
            DrillTraceStep(4, "Row 1: [3, 4]", "dp[0] = 3 + min(7, 6) = 9. dp[1] = 4 + min(6, 10) = 10."),
            DrillTraceStep(5, "Row 0: [2]", "dp[0] = 2 + min(9, 10) = 11. Final answer = 11.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why does bottom-up traversal eliminate edge checks compared to top-down traversal?",
            options = listOf(
                "Every node moving upwards always has exactly two child options (c and c+1) below it, whereas top-down nodes on the borders have only one parent",
                "Because bottom-up runs in O(log N)",
                "Because triangles are equilateral",
                "It uses recursion internally"
            ),
            correctOptionIndex = 0,
            explanation = "In bottom-up, every cell (r, c) unconditionally chooses between (r+1, c) and (r+1, c+1), removing all boundary conditional checks."
        )
    ),
    // Lesson 21
    TrainingDrill(
        id = "dp_drill_21",
        topicId = "dp",
        lessonNumber = 21,
        title = "0/1 Knapsack Reverse Iteration",
        subtitle = "Traversing capacity backwards to prevent reusing the same item in 1D space",
        conceptDelta = "+1 Concept: `for (int w = capacity; w >= weight; w--)` enforces single-use item constraint",
        intuition = "In Unbounded Knapsack (Lesson 6) we iterated forward (`w++`), which allowed reusing the same coin. In 0/1 Knapsack, each item can be used AT MOST ONCE. To compress the 2D table into a 1D array, iterate capacity BACKWARDS: `for (int w = capacity; w >= weight; w--)`. This ensures `dp[w - weight]` is from the PREVIOUS item, not the current one!",
        codePattern = """// Lesson 21: 0/1 Knapsack Backward Pass
public int knapsack01(int capacity, int[] weights, int[] values) {
    int[] dp = new int[capacity + 1];
    for (int i = 0; i < weights.length; i++) {
        int wt = weights[i], val = values[i];
        // Reverse pass prevents multiple inclusion of item i
        for (int w = capacity; w >= wt; w--) {
            dp[w] = Math.max(dp[w], dp[w - wt] + val);
        }
    }
    return dp[capacity];
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "capacity = 4, item 1: wt = 2, val = 3", "Backward loop: dp[4] = max(0, dp[2]+3)=3. dp[2]=3. dp = [0, 0, 3, 0, 3]."),
            DrillTraceStep(2, "Item 2: wt = 3, val = 4", "w = 4: dp[4] = max(3, dp[1]+4)=3. w = 3: dp[3] = max(0, dp[0]+4)=4."),
            DrillTraceStep(3, "Notice item 1 was never reused twice", "Backward scan cleanly preserves single-use rule.")
        ),
        quizQuestion = DrillQuiz(
            question = "What would happen if the inner loop ran forward (`for (int w = wt; w <= capacity; w++)`)?",
            options = listOf(
                "An item could be chosen multiple times because `dp[w - wt]` would reflect the inclusion of the current item earlier in the same pass",
                "It would produce an ArrayIndexOutOfBoundsException",
                "The result would be negated",
                "Nothing, direction does not matter"
            ),
            correctOptionIndex = 0,
            explanation = "Forward iteration allows item i to be added to dp[w-wt] which was ALREADY updated with item i, turning 0/1 Knapsack into unbounded knapsack."
        )
    ),
    // Lesson 22
    TrainingDrill(
        id = "dp_drill_22",
        topicId = "dp",
        lessonNumber = 22,
        title = "Subset Sum Reduction (Partition Equal Subset Sum)",
        subtitle = "Mapping partition problems into 0/1 knapsack boolean reachability",
        conceptDelta = "+1 Concept: `target = totalSum / 2`; `dp[s] = dp[s] || dp[s - num]` with backward pass",
        intuition = "Partitioning an array into two subsets with equal sum means finding a subset that sums to EXACTLY `totalSum / 2`. If `totalSum` is odd, return false immediately. Otherwise, this is pure 0/1 knapsack with boolean reachability! `dp[s] = dp[s] || dp[s - num]` running backwards from `target` down to `num`.",
        codePattern = """// Lesson 22: Boolean Subset Sum DP
public boolean canPartition(int[] nums) {
    int totalSum = 0;
    for (int n : nums) totalSum += n;
    if (totalSum % 2 != 0) return false; // Odd sum cannot be partitioned
    
    int target = totalSum / 2;
    boolean[] dp = new boolean[target + 1];
    dp[0] = true; // 0 sum is always achievable (empty set)
    
    for (int num : nums) {
        for (int s = target; s >= num; s--) {
            dp[s] = dp[s] || dp[s - num];
        }
        if (dp[target]) return true; // Early exit
    }
    return dp[target];
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "nums = [1, 5, 11, 5]", "totalSum = 22, target = 11. dp[0] = true."),
            DrillTraceStep(2, "Process num = 1", "dp[1] = true."),
            DrillTraceStep(3, "Process num = 5", "dp[6]=T, dp[5]=T."),
            DrillTraceStep(4, "Process num = 11", "s = 11: dp[11] = dp[11] || dp[0] = true!"),
            DrillTraceStep(5, "Early exit: true", "Partition exists: [11] and [1, 5, 5].")
        ),
        quizQuestion = DrillQuiz(
            question = "Why does `totalSum % 2 != 0` immediately return false?",
            options = listOf(
                "Because an odd integer cannot be split into two equal integer halves",
                "Because odd numbers are prime",
                "Because boolean arrays cannot have odd length",
                "To avoid rounding errors"
            ),
            correctOptionIndex = 0,
            explanation = "If total sum is odd, any two integer subsets will have sums that differ by at least 1, making equal partition mathematically impossible."
        )
    ),
    // Lesson 23
    TrainingDrill(
        id = "dp_drill_23",
        topicId = "dp",
        lessonNumber = 23,
        title = "Algebraic Problem Transformation (Target Sum)",
        subtitle = "Transforming a +/- assignment problem into a pure positive subset sum",
        conceptDelta = "+1 Concept: `P - N = target` and `P + N = total` -> `2P = target + total` -> `P = (target + total) / 2`",
        intuition = "In Lesson 29 of Recursion, Target Sum explored 2^N states. We can solve it in linear DP! Let P be the sum of positive numbers, and N be the sum of negative numbers. `P - N = target` and `P + N = total`. Adding both: `2P = target + total`, so `P = (target + total) / 2`. The problem is simply finding how many subsets sum to P!",
        codePattern = """// Lesson 23: Target Sum to Subset Sum Transform
public int findTargetSumWays(int[] nums, int target) {
    int total = 0;
    for (int n : nums) total += n;
    if (Math.abs(target) > total || (total + target) % 2 != 0) return 0;
    
    int P = (total + target) / 2;
    int[] dp = new int[P + 1];
    dp[0] = 1;
    
    for (int num : nums) {
        for (int s = P; s >= num; s--) {
            dp[s] += dp[s - num];
        }
    }
    return dp[P];
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "nums = [1, 1, 1, 1, 1], target = 3", "total = 5. (5 + 3) / 2 = 4. Target P = 4."),
            DrillTraceStep(2, "Subsets of nums that sum to 4", "Equivalent to picking four 1s (and leaving one 1 negative)."),
            DrillTraceStep(3, "0/1 Knapsack count pass", "dp[4] accumulates ways to make 4."),
            DrillTraceStep(4, "Result: 5", "5 distinct ways computed in O(N * P) time instead of O(2^N) recursion.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why does `(total + target) % 2 != 0` immediately return 0?",
            options = listOf(
                "Because 2P must be an even integer; if total + target is odd, no integer subset P can satisfy the equation",
                "Because target must be even",
                "Because nums must have even length",
                "To prevent integer underflow"
            ),
            correctOptionIndex = 0,
            explanation = "Since P is an integer, 2P must be even. An odd sum for (total + target) has no integer solution."
        )
    ),
    // Lesson 24
    TrainingDrill(
        id = "dp_drill_24",
        topicId = "dp",
        lessonNumber = 24,
        title = "Permutation DP Ordering (Combination Sum IV)",
        subtitle = "Switching loops to calculate ordered sequence arrangements",
        conceptDelta = "+1 Concept: `for (int t = 1; t <= target; t++)` on outer loop generates permutations",
        intuition = "In Lesson 7 (Coin Change II) we put coins on the outer loop to count COMBINATIONS (order doesn't matter). In Combination Sum IV, `(1, 2)` and `(2, 1)` are considered DIFFERENT arrangements! We flip the loops: TARGET is on the outer loop, and NUMS is on the inner loop. True permutation counting!",
        codePattern = """// Lesson 24: Permutations with Target Outer Loop
public int combinationSum4(int[] nums, int target) {
    int[] dp = new int[target + 1];
    dp[0] = 1;
    
    for (int t = 1; t <= target; t++) {
        for (int num : nums) {
            if (t - num >= 0) {
                dp[t] += dp[t - num];
            }
        }
    }
    return dp[target];
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "nums = [1, 2, 3], target = 4", "dp[0] = 1."),
            DrillTraceStep(2, "t = 1", "dp[1] = dp[0] = 1 (ways: [1])."),
            DrillTraceStep(3, "t = 2", "dp[2] = dp[1] + dp[0] = 1 + 1 = 2 (ways: [1, 1], [2])."),
            DrillTraceStep(4, "t = 3", "dp[3] = dp[2] + dp[1] + dp[0] = 2 + 1 + 1 = 4."),
            DrillTraceStep(5, "t = 4", "dp[4] = dp[3] + dp[2] + dp[1] = 4 + 2 + 1 = 7 permutations.")
        ),
        quizQuestion = DrillQuiz(
            question = "Compare Lesson 7 and Lesson 24: What fundamentally changes the outcome between combinations and permutations?",
            options = listOf(
                "Outer loop over items = Combinations; Outer loop over target amount = Permutations",
                "Using arrays vs ArrayList",
                "Sorting the numbers",
                "Integer vs Double types"
            ),
            correctOptionIndex = 0,
            explanation = "Iterating over target first considers all numbers as potential final elements for that target, effectively generating all orderings."
        )
    ),
    // Lesson 25
    TrainingDrill(
        id = "dp_drill_25",
        topicId = "dp",
        lessonNumber = 25,
        title = "Finite State Machine DP (Stock with Cooldown)",
        subtitle = "Modeling interconnected state transitions: Held, Sold, and Rest",
        conceptDelta = "+1 Concept: 3 state variables: `held[i] = max(held[i-1], rest[i-1] - price)`, `sold[i] = held[i-1] + price`, `rest[i] = max(rest[i-1], sold[i-1])`",
        intuition = "In Stock with Cooldown, you cannot buy the day after selling. Model the problem as a 3-state machine: (1) HELD: holding a stock, (2) SOLD: just sold today (cooldown tomorrow), (3) REST: empty-handed and free to buy. Write transition formulas between the 3 states and update in O(1) space!",
        codePattern = """// Lesson 25: 3-State Stock Machine
public int maxProfit(int[] prices) {
    if (prices.length == 0) return 0;
    int held = -prices[0]; // Bought stock on day 0
    int sold = 0;          // Just sold today
    int rest = 0;          // Free to buy
    
    for (int i = 1; i < prices.length; i++) {
        int prevSold = sold;
        sold = held + prices[i];               // Sell today
        held = Math.max(held, rest - prices[i]);// Buy today (requires rest yesterday)
        rest = Math.max(rest, prevSold);       // Rest today
    }
    return Math.max(sold, rest);
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "prices = [1, 2, 3, 0, 2]", "Day 0: held = -1, sold = 0, rest = 0."),
            DrillTraceStep(2, "Day 1 (price 2)", "sold = -1 + 2 = 1. held = max(-1, 0 - 2) = -1. rest = 0."),
            DrillTraceStep(3, "Day 2 (price 3)", "sold = -1 + 3 = 2. held = -1. rest = max(0, 1) = 1."),
            DrillTraceStep(4, "Day 3 (price 0, cooldown/buy)", "rest = 2. held = max(-1, 2 - 0) = 2 (bought at 0!)."),
            DrillTraceStep(5, "Day 4 (price 2)", "sold = 2 + 2 = 4. Final max profit = 4.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why does buying stock use `rest - prices[i]` instead of `sold - prices[i]`?",
            options = listOf(
                "Because the cooldown rule forbids buying immediately after a sale; buying is only allowed from the rest state",
                "Because sold is negative",
                "To prevent integer overflow",
                "Because rest has higher priority"
            ),
            correctOptionIndex = 0,
            explanation = "State 'rest' represents days where you did not sell yesterday, satisfying the mandatory 1-day cooldown before buying."
        )
    ),
    // Lesson 26
    TrainingDrill(
        id = "dp_drill_26",
        topicId = "dp",
        lessonNumber = 26,
        title = "Dual State Machine with Transaction Fee",
        subtitle = "Incorporating flat fees into hold/cash state transitions",
        conceptDelta = "+1 Concept: `hold = max(hold, cash - price)` and `cash = max(cash, hold + price - fee)`",
        intuition = "When each transaction incurs a fee: at any day, you are either holding a stock (`hold`) or cash (`cash`). If you buy, you deduct `price` from cash. If you sell, you add `price - fee` to cash. Two rolling scalar variables solve the problem in O(N) time and O(1) space.",
        codePattern = """// Lesson 26: 2-State Transaction Fee
public int maxProfit(int[] prices, int fee) {
    int cash = 0;
    int hold = -prices[0];
    
    for (int i = 1; i < prices.length; i++) {
        cash = Math.max(cash, hold + prices[i] - fee); // Sell with fee
        hold = Math.max(hold, cash - prices[i]);       // Buy
    }
    return cash;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "prices = [1, 3, 2, 8, 4, 9], fee = 2", "cash = 0, hold = -1."),
            DrillTraceStep(2, "price = 3", "cash = max(0, -1 + 3 - 2) = 0. hold = -1."),
            DrillTraceStep(3, "price = 8", "cash = max(0, -1 + 8 - 2) = 5. hold = max(-1, 5 - 8) = -1."),
            DrillTraceStep(4, "price = 4, then 9", "Final cash reaches 8 after deducting fees."),
            DrillTraceStep(5, "Result: 8", "Optimal trade execution in linear time.")
        ),
        quizQuestion = DrillQuiz(
            question = "Can the transaction fee be deducted during the buy step instead of the sell step?",
            options = listOf(
                "Yes, deducting the fee upon buying (`cash - price - fee`) is mathematically equivalent since every complete trade has exactly 1 buy and 1 sell",
                "No, tax laws forbid it",
                "No, it produces negative answers",
                "Only if fee is zero"
            ),
            correctOptionIndex = 0,
            explanation = "A transaction is a paired buy and sell. Whether the flat fee is deducted on entry or exit makes no difference to the final net profit."
        )
    ),
    // Lesson 27
    TrainingDrill(
        id = "dp_drill_27",
        topicId = "dp",
        lessonNumber = 27,
        title = "Prefix Substring Boolean Matching (Word Break)",
        subtitle = "Determining string segmentability using a 1D boolean reachability chain",
        conceptDelta = "+1 Concept: `dp[i] = true` if exists `j < i` such that `dp[j] == true && dict.contains(s.substring(j, i))`",
        intuition = "In Word Break (\"leetcode\"): `dp[i]` represents whether `s[0..i]` can be segmented into dictionary words. For index `i`, we test all partition points `j < i`. If `dp[j]` is true (prefix `0..j` was valid) AND the remaining word `s[j..i]` is in our dictionary, then `dp[i] = true`! Stop inner loop on first match.",
        codePattern = """// Lesson 27: Boolean Word Break Chain
public boolean wordBreak(String s, List<String> wordDict) {
    Set<String> dict = new HashSet<>(wordDict);
    boolean[] dp = new boolean[s.length() + 1];
    dp[0] = true; // Empty prefix is valid
    
    for (int i = 1; i <= s.length(); i++) {
        for (int j = 0; j < i; j++) {
            if (dp[j] && dict.contains(s.substring(j, i))) {
                dp[i] = true;
                break; // One valid segmentation suffices
            }
        }
    }
    return dp[s.length()];
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "s = \"leetcode\", dict = [\"leet\", \"code\"]", "dp[0] = true."),
            DrillTraceStep(2, "i = 4 (\"leet\")", "j = 0: dp[0] is true, dict has \"leet\" -> dp[4] = true!"),
            DrillTraceStep(3, "i = 8 (\"leetcode\")", "j = 4: dp[4] is true, dict has \"code\" -> dp[8] = true!"),
            DrillTraceStep(4, "Result: true", "Segmentable in O(N²) time without recursive backtracking.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why is `dict` converted to a `HashSet` before the loop begins?",
            options = listOf(
                "To ensure `dict.contains(...)` runs in O(1) time instead of O(W) linear search in a List",
                "Because lists cannot store strings",
                "To remove duplicate characters",
                "It is a compiler requirement"
            ),
            correctOptionIndex = 0,
            explanation = "Calling .contains() on an ArrayList is an O(W) search. Converting to HashSet enables O(1) average lookup time."
        )
    ),
    // Lesson 28
    TrainingDrill(
        id = "dp_drill_28",
        topicId = "dp",
        lessonNumber = 28,
        title = "2D Interval Expansion DP (Palindromic Substrings)",
        subtitle = "Evaluating substring palindromes via `dp[i][j] = (s[i] == s[j]) && dp[i+1][j-1]`",
        conceptDelta = "+1 Concept: Outer loop over substring length or backwards `i`: inner state depends on interior subproblem `dp[i+1][j-1]`",
        intuition = "A substring `s[i..j]` is a palindrome if and only if: (1) `s[i] == s[j]`, AND (2) The inner substring `s[i+1..j-1]` is ALSO a palindrome (or length <= 2). Because `dp[i][j]` depends on `dp[i+1]`, the outer loop for `i` MUST run backwards from N-1 down to 0!",
        codePattern = """// Lesson 28: 2D Palindrome Interval Table
public int countSubstrings(String s) {
    int n = s.length(), count = 0;
    boolean[][] dp = new boolean[n][n];
    
    // Iterate i backwards so i+1 is already computed
    for (int i = n - 1; i >= 0; i--) {
        for (int j = i; j < n; j++) {
            if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1])) {
                dp[i][j] = true;
                count++;
            }
        }
    }
    return count;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "s = \"aaa\"", "i runs 2 down to 0."),
            DrillTraceStep(2, "i = 2: j = 2 (\"a\")", "j - i = 0 <= 2 -> dp[2][2] = true. count = 1."),
            DrillTraceStep(3, "i = 1: j = 1, 2 (\"a\", \"aa\")", "Both <= 2 -> dp[1][1]=T, dp[1][2]=T. count = 3."),
            DrillTraceStep(4, "i = 0: j = 2 (\"aaa\")", "'a'=='a' and dp[1][1] is true -> dp[0][2]=T. count = 6.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why does `j - i <= 2` bypass the `dp[i + 1][j - 1]` lookup?",
            options = listOf(
                "Substrings of length 1, 2, or 3 with matching endpoints are trivially palindromes without needing an interior check",
                "Because indices would be out of bounds",
                "To save memory",
                "Because 2 is an even number"
            ),
            correctOptionIndex = 0,
            explanation = "For length 1 (\"a\"), length 2 (\"aa\"), and length 3 (\"aba\"), if endpoints match, the interior has at most 1 character, which is always a palindrome."
        )
    ),
    // Lesson 29
    TrainingDrill(
        id = "dp_drill_29",
        topicId = "dp",
        lessonNumber = 29,
        title = "Longest Palindromic Subsequence (Interval DP)",
        subtitle = "Finding optimal non-contiguous palindrome length on sub-intervals [i..j]",
        conceptDelta = "+1 Concept: `if (s[i] == s[j]) dp[i][j] = 2 + dp[i+1][j-1]; else max(dp[i+1][j], dp[i][j-1])`",
        intuition = "Unlike substrings (which must be contiguous), subsequences allow dropping characters. If endpoints match (`s[i] == s[j]`), both contribute: `2 + dp[i+1][j-1]`. If they mismatch, take the best subsequence by dropping either `s[i]` or `s[j]`: `max(dp[i+1][j], dp[i][j-1])`. Base cases: `dp[i][i] = 1`.",
        codePattern = """// Lesson 29: Longest Palindromic Subsequence
public int longestPalindromeSubseq(String s) {
    int n = s.length();
    int[][] dp = new int[n][n];
    
    for (int i = n - 1; i >= 0; i--) {
        dp[i][i] = 1; // Single char is palindrome of length 1
        for (int j = i + 1; j < n; j++) {
            if (s.charAt(i) == s.charAt(j)) {
                dp[i][j] = 2 + dp[i + 1][j - 1];
            } else {
                dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }
    }
    return dp[0][n - 1];
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "s = \"bbbab\"", "n = 5. Diagonal dp[i][i] set to 1."),
            DrillTraceStep(2, "Substrings of length 2", "\"bb\" matches -> 2 + 0 = 2."),
            DrillTraceStep(3, "Substrings expand outward", "Interval [0..3] (\"bbba\"): matches 'b's -> 2 + dp[1][2]=4."),
            DrillTraceStep(4, "Full interval dp[0][4] = 4", "Optimal subsequence is \"bbbb\" of length 4.")
        ),
        quizQuestion = DrillQuiz(
            question = "How does Longest Palindromic Subsequence relate to Longest Common Subsequence (Lesson 13)?",
            options = listOf(
                "LPS of string S is mathematically identical to LCS(S, reverse(S))",
                "They have no relationship",
                "LPS is strictly twice LCS",
                "LPS can only be solved with recursion"
            ),
            correctOptionIndex = 0,
            explanation = "Finding the longest subsequence that reads identically forwards and backwards is equivalent to finding the LCS between S and its reverse."
        )
    ),
    // Lesson 30
    TrainingDrill(
        id = "dp_drill_30",
        topicId = "dp",
        lessonNumber = 30,
        title = "Reverse Thinking in Interval DP (Burst Balloons)",
        subtitle = "Choosing which element bursts LAST to isolate independent subproblems",
        conceptDelta = "+1 Concept: Think backwards: pick balloon `k` that bursts LAST in interval `(left, right)`, so boundary balloons remain intact",
        intuition = "In Burst Balloons, thinking forward makes subproblems dependent because popping a balloon makes its neighbors adjacent! GENIUS REVERSAL: think about which balloon `k` bursts LAST in `(left, right)`. Since `k` is the last to burst, `left` and `right` boundaries are still alive next to it! Cost is `nums[left] * nums[k] * nums[right] + dp[left][k] + dp[k][right]`. Perfect subproblem independence!",
        codePattern = """// Lesson 30: Burst Balloons Interval DP
public int maxCoins(int[] nums) {
    int n = nums.length;
    int[] arr = new int[n + 2];
    arr[0] = 1; arr[n + 1] = 1; // Virtual boundaries with value 1
    for (int i = 0; i < n; i++) arr[i + 1] = nums[i];
    
    int[][] dp = new int[n + 2][n + 2];
    // Length of interval from 1 to n
    for (int len = 1; len <= n; len++) {
        for (int left = 1; left <= n - len + 1; left++) {
            int right = left + len - 1;
            // Pick balloon k that bursts LAST in [left..right]
            for (int k = left; k <= right; k++) {
                int coins = arr[left - 1] * arr[k] * arr[right + 1];
                coins += dp[left][k - 1] + dp[k + 1][right];
                dp[left][right] = Math.max(dp[left][right], coins);
            }
        }
    }
    return dp[1][n];
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "nums = [3, 1, 5, 8]", "Padded array: [1, 3, 1, 5, 8, 1]."),
            DrillTraceStep(2, "Intervals of length 1", "dp[2][2] (val 1 bursts last): 3 * 1 * 5 = 15."),
            DrillTraceStep(3, "Intervals expand up to length 4", "Interval [1..4] evaluates all possible last-burst balloons k in 1..4."),
            DrillTraceStep(4, "Result: dp[1][4] = 167", "Optimal coin collection sequence calculated in O(N³) time.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why does choosing the balloon that bursts LAST make the subproblems independent?",
            options = listOf(
                "Because balloon `k` remains alive while all other balloons in `[left..right]` burst, keeping the boundary walls `left-1` and `right+1` fixed and known",
                "Because balloon k has the largest value",
                "Because all coins are multiplied by zero",
                "It is a heuristic approximation"
            ),
            correctOptionIndex = 0,
            explanation = "If k bursts last, any balloons burst inside [left..k-1] will be bounded by left-1 and k. Their coin payouts depend only on fixed, known neighbors."
        )
    )
)
