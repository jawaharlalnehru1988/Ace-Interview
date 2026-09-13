package com.example.data.local.dsa

import com.example.domain.model.DrillQuiz
import com.example.domain.model.DrillTraceStep
import com.example.domain.model.TrainingDrill

val recursionTrainingDrills = listOf(
    // Lesson 1
    TrainingDrill(
        id = "rec_drill_01",
        topicId = "recursion",
        lessonNumber = 1,
        title = "The Base Case & Linear Descent",
        subtitle = "Trusting the base case and understanding the return unwind",
        conceptDelta = "Baseline: Exactly 1 base condition + 1 recursive call that shrinks input size",
        intuition = "Recursion is simply delegating work to a smaller copy of the same problem. Every recursive function has two parts: (1) The Base Case (the emergency brake that returns immediately), and (2) The Recursive Leap (calling itself with N - 1). Never trace in your head beyond 1 level—trust the contract.",
        codePattern = """// Lesson 1: Linear Recursion
public int factorial(int n) {
    if (n <= 1) return 1;
    return n * factorial(n - 1);
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "Call factorial(3)", "3 > 1 -> needs 3 * factorial(2). Stack pushes factorial(2)."),
            DrillTraceStep(2, "Call factorial(2)", "2 > 1 -> needs 2 * factorial(1). Stack pushes factorial(1)."),
            DrillTraceStep(3, "Call factorial(1)", "1 <= 1 (Base Case reached!). Returns 1 immediately without new calls."),
            DrillTraceStep(4, "Unwind factorial(2)", "Receives 1 from below. Computes 2 * 1 = 2. Returns 2."),
            DrillTraceStep(5, "Unwind factorial(3)", "Receives 2 from below. Computes 3 * 2 = 6. Returns 6.")
        ),
        quizQuestion = DrillQuiz(
            question = "What occurs if the base case `if (n <= 1)` is accidentally omitted?",
            options = listOf(
                "The function returns 0",
                "The JVM executes forever without stopping",
                "StackOverflowError occurs because the call stack exhausts available memory",
                "The compiler generates an error"
            ),
            correctOptionIndex = 2,
            explanation = "Without a base case, recursive stack frames are continuously pushed until the thread's stack memory limit is exceeded, throwing a StackOverflowError."
        )
    ),
    // Lesson 2
    TrainingDrill(
        id = "rec_drill_02",
        topicId = "recursion",
        lessonNumber = 2,
        title = "Binary Tree Branching (Two Recursive Calls)",
        subtitle = "Splitting one problem into two independent sub-problems",
        conceptDelta = "+1 Concept: Making TWO recursive calls and combining their outputs",
        intuition = "In Lesson 1, each frame made only 1 call (a single chain). Now we make TWO calls: `left` and `right`. This turns a line into a call TREE. The parent frame waits for both branches to return before combining their results.",
        codePattern = """// Lesson 2: Binary Branching (Tree Traversal)
public int maxDepth(TreeNode root) {
    if (root == null) return 0;
    int leftDepth = maxDepth(root.left);
    int rightDepth = maxDepth(root.right);
    return 1 + Math.max(leftDepth, rightDepth);
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "Call root = [3, 9, 20]", "root != null. Dispatches maxDepth(9) and maxDepth(20)."),
            DrillTraceStep(2, "maxDepth(9)", "9.left is null (returns 0), 9.right is null (returns 0). Returns 1 + max(0, 0) = 1."),
            DrillTraceStep(3, "maxDepth(20)", "20 dispatches left and right children (each depth 1). Returns 1 + max(1, 1) = 2."),
            DrillTraceStep(4, "Root combines answers", "1 + Math.max(1, 2) = 3. Final max depth = 3.")
        ),
        quizQuestion = DrillQuiz(
            question = "In dual branching like `fib(n) = fib(n-1) + fib(n-2)` without caching, what is the time complexity?",
            options = listOf(
                "O(N)",
                "O(N log N)",
                "O(2^N) because each call doubles the number of subproblems",
                "O(1)"
            ),
            correctOptionIndex = 2,
            explanation = "Each node spawns 2 children, forming a binary tree of depth N with ~2^N total operations."
        )
    ),
    // Lesson 3
    TrainingDrill(
        id = "rec_drill_03",
        topicId = "recursion",
        lessonNumber = 3,
        title = "State Accumulator via Helper Parameters",
        subtitle = "Passing context DOWN the call stack rather than calculating on the way UP",
        conceptDelta = "+1 Concept: Helper function carrying running state as an extra parameter",
        intuition = "In Lessons 1 & 2, answers were combined on the return path (bottom-up). Sometimes, however, a sub-problem needs to know the path taken from above (top-down). We introduce a helper function that carries running state down the stack.",
        codePattern = """// Lesson 3: Helper with Parameter Accumulator
public boolean hasPathSum(TreeNode root, int targetSum) {
    return helper(root, 0, targetSum);
}
private boolean helper(TreeNode node, int runningSum, int targetSum) {
    if (node == null) return false;
    runningSum += node.val;
    if (node.left == null && node.right == null) {
        return runningSum == targetSum;
    }
    return helper(node.left, runningSum, targetSum) || 
           helper(node.right, runningSum, targetSum);
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "helper(root=5, runningSum=0, target=22)", "runningSum becomes 0 + 5 = 5. Passes 5 to children."),
            DrillTraceStep(2, "helper(node=4, runningSum=5, target=22)", "runningSum becomes 5 + 4 = 9. Passes 9 down left child (11)."),
            DrillTraceStep(3, "helper(node=11, runningSum=9, target=22)", "runningSum becomes 9 + 11 = 20. Passes 20 down right leaf (2)."),
            DrillTraceStep(4, "helper(node=2, runningSum=20, target=22)", "runningSum becomes 20 + 2 = 22 == target! Leaf reached with exact sum -> returns TRUE!")
        ),
        quizQuestion = DrillQuiz(
            question = "Why pass `runningSum` by value as a primitive `int` rather than a shared `int[]` or object?",
            options = listOf(
                "Java does not support arrays in recursion",
                "Passing by value automatically isolates each branch so left child's mutations do not pollute the right child",
                "Primitive ints run on the GPU",
                "It saves memory on the heap"
            ),
            correctOptionIndex = 1,
            explanation = "Primitive values are copied onto each stack frame. When a branch finishes and returns, the parent's runningSum remains untainted."
        )
    ),
    // Lesson 4
    TrainingDrill(
        id = "rec_drill_04",
        topicId = "recursion",
        lessonNumber = 4,
        title = "Backtracking: State Mutation & Undo",
        subtitle = "The 3-step rhythm: Choose, Recurse, and Un-choose (Undo)",
        conceptDelta = "+1 Concept: Mutating a shared state list, recursing, and UNDOING (backtracking) upon return",
        intuition = "When generating combinations/subsets, creating new lists at every recursive call wastes O(N²) memory. Instead, we share ONE list. The rule is strictly 3 steps: (1) Add element (CHOOSE), (2) Recurse into next choices (EXPLORE), (3) Remove element (UNDO / BACKTRACK).",
        codePattern = """// Lesson 4: The Backtracking Triad
public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    backtrack(0, nums, new ArrayList<>(), result);
    return result;
}
private void backtrack(int start, int[] nums, List<Integer> current, List<List<Integer>> result) {
    result.add(new ArrayList<>(current));
    for (int i = start; i < nums.length; i++) {
        current.add(nums[i]);                     // 1. CHOOSE
        backtrack(i + 1, nums, current, result);  // 2. EXPLORE
        current.remove(current.size() - 1);       // 3. UNDO
    }
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "nums = [1, 2], current = []", "Snapshot [] into result. Loop i = 0 (val 1)."),
            DrillTraceStep(2, "CHOOSE 1: current = [1]", "Recurse backtrack(start=1). Snapshot [1] into result."),
            DrillTraceStep(3, "CHOOSE 2: current = [1, 2]", "Recurse backtrack(start=2). Snapshot [1, 2] into result. Loop ends."),
            DrillTraceStep(4, "UNDO 2: current reverts to [1]", "Backtrack removes 2. i=1 loop finishes at level 1."),
            DrillTraceStep(5, "UNDO 1: current reverts to []", "Backtrack removes 1. Loop moves to i = 1 (val 2). Snapshot [2]."),
            DrillTraceStep(6, "Result", "[[], [1], [1, 2], [2]]. Full exploration with minimal allocations.")
        ),
        quizQuestion = DrillQuiz(
            question = "What happens if you omit the undo step `current.remove(current.size() - 1)`?",
            options = listOf(
                "The code still generates correct subsets",
                "Elements accumulate permanently in `current`, corrupting subsequent branch explorations",
                "The code throws ConcurrentModificationException",
                "Result will contain empty lists"
            ),
            correctOptionIndex = 1,
            explanation = "Because 'current' is a shared object reference across all stack frames, failing to undo leaves elements in the list for sibling branches."
        )
    ),
    // Lesson 5
    TrainingDrill(
        id = "rec_drill_05",
        topicId = "recursion",
        lessonNumber = 5,
        title = "Permutations & The Visited State Array",
        subtitle = "Exploring all orderings while preventing element reuse using a boolean used array",
        conceptDelta = "+1 Concept: `boolean[] used` array allows loop to always start from index 0 without repeating used elements",
        intuition = "In subsets, order didn't matter so `i` always moved forward (`start = i + 1`). In Permutations, `[1, 2]` is DIFFERENT from `[2, 1]`. Every position can pick ANY unused number! We start our loop from `0` every time, guarded by a `boolean[] used` flag that is flipped to true on CHOOSE and false on UNDO.",
        codePattern = """// Lesson 5: Permutation with Used Flag
public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    boolean[] used = new boolean[nums.length];
    backtrack(nums, new ArrayList<>(), used, result);
    return result;
}
private void backtrack(int[] nums, List<Integer> current, boolean[] used, List<List<Integer>> result) {
    if (current.size() == nums.length) {
        result.add(new ArrayList<>(current));
        return;
    }
    for (int i = 0; i < nums.length; i++) {
        if (used[i]) continue; // Skip already picked elements
        used[i] = true;
        current.add(nums[i]);
        backtrack(nums, current, used, result);
        current.remove(current.size() - 1);
        used[i] = false;
    }
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "nums = [1, 2, 3]", "current = [], used = [F, F, F]."),
            DrillTraceStep(2, "Pick 1, then pick 2, then pick 3", "current = [1, 2, 3], size == 3 -> snapshot [1, 2, 3]."),
            DrillTraceStep(3, "Backtrack 3, backtrack 2", "current = [1], used[2]=F, used[1]=F."),
            DrillTraceStep(4, "Pick 3 next: current = [1, 3]", "Loop finds used[1]=F -> picks 2. Snapshot [1, 3, 2]."),
            DrillTraceStep(5, "Generates all N! = 6 permutations", "Every order explored cleanly in O(N! * N) time.")
        ),
        quizQuestion = DrillQuiz(
            question = "How many total leaves (complete permutations) are generated for an array of length N with distinct elements?",
            options = listOf(
                "N!",
                "2^N",
                "N²",
                "N * (N - 1)"
            ),
            correctOptionIndex = 0,
            explanation = "The first position has N choices, the second has N-1 choices, ..., yielding N * (N-1) * ... * 1 = N! total permutations."
        )
    ),
    // Lesson 6
    TrainingDrill(
        id = "rec_drill_06",
        topicId = "recursion",
        lessonNumber = 6,
        title = "Constrained Branch Pruning (Combinations of Size K)",
        subtitle = "Terminating loops early when remaining elements cannot fulfill the target size",
        conceptDelta = "+1 Concept: Loop bound pruning: `i <= n - (k - current.size()) + 1`",
        intuition = "When choosing K elements from 1 to N, looping up to N even when only 1 element remains in the array wastes time. If we still need 3 elements, but only 2 elements remain from `i` to `N`, the entire branch is mathematically doomed! Pruning the loop bound saves massive recursion overhead.",
        codePattern = """// Lesson 6: Pruned Combination Loop
public List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> result = new ArrayList<>();
    backtrack(1, n, k, new ArrayList<>(), result);
    return result;
}
private void backtrack(int start, int n, int k, List<Integer> current, List<List<Integer>> result) {
    if (current.size() == k) {
        result.add(new ArrayList<>(current));
        return;
    }
    // Pruned boundary: only loop while enough candidates remain
    for (int i = start; i <= n - (k - current.size()) + 1; i++) {
        current.add(i);
        backtrack(i + 1, n, k, current, result);
        current.remove(current.size() - 1);
    }
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "n = 4, k = 2", "Need 2 elements. Bound: i <= 4 - (2 - 0) + 1 = 3. Loop only runs for i = 1, 2, 3!"),
            DrillTraceStep(2, "i = 4 is pruned at level 1", "Because picking 4 first leaves zero elements to pick for the second slot!"),
            DrillTraceStep(3, "Result generated", "[[1,2], [1,3], [1,4], [2,3], [2,4], [3,4]]. Pure valid outputs.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why is `i <= n - (k - current.size()) + 1` safe and optimal?",
            options = listOf(
                "Because if fewer than the remaining required elements exist from i to n, the branch can never reach size K",
                "Because array indexing starts at 1",
                "To prevent integer division by zero",
                "It is a heuristic that may miss some valid combinations"
            ),
            correctOptionIndex = 0,
            explanation = "The number of elements available from i to n is n - i + 1. If this is strictly less than k - current.size(), reaching size k is impossible."
        )
    ),
    // Lesson 7
    TrainingDrill(
        id = "rec_drill_07",
        topicId = "recursion",
        lessonNumber = 7,
        title = "Duplicate Sibling Pruning (Subsets II)",
        subtitle = "Sorting and skipping duplicate sibling branches while allowing vertical duplicates",
        conceptDelta = "+1 Concept: `if (i > start && nums[i] == nums[i - 1]) continue;` (horizontal sibling deduplication)",
        intuition = "If `nums = [1, 2, 2]`, picking the first 2 generates `[1, 2]`. Later, picking the second 2 at the same tree level would ALSO generate `[1, 2]`! To deduplicate: (1) SORT the array. (2) If `nums[i] == nums[i-1]` at the SAME level (`i > start`), skip it. Note: `i > start` allows picking duplicates down different levels!",
        codePattern = """// Lesson 7: Sibling Duplicate Pruning
public List<List<Integer>> subsetsWithDup(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(nums); // Prerequisite for adjacent duplicate detection
    backtrack(0, nums, new ArrayList<>(), result);
    return result;
}
private void backtrack(int start, int[] nums, List<Integer> current, List<List<Integer>> result) {
    result.add(new ArrayList<>(current));
    for (int i = start; i < nums.length; i++) {
        // Skip identical sibling choices at the same recursion depth
        if (i > start && nums[i] == nums[i - 1]) continue;
        current.add(nums[i]);
        backtrack(i + 1, nums, current, result);
        current.remove(current.size() - 1);
    }
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "nums = [1, 2, 2]", "Sorted: [1, 2, 2]. Snapshot []."),
            DrillTraceStep(2, "Pick 1, then pick first 2", "Snapshot [1, 2]. Recurse: pick second 2 -> snapshot [1, 2, 2]."),
            DrillTraceStep(3, "Backtrack to current = [1]", "Next i = 2 (second '2'). Here i (2) > start (1) and nums[2] == nums[1] -> SKIPPED!"),
            DrillTraceStep(4, "Duplicate prevented!", "Result contains zero redundant subsets without using a HashSet.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why does `i > start` specifically prevent duplicates without blocking identical values in deeper levels?",
            options = listOf(
                "Because `i == start` represents the first time the value is chosen at this depth; only subsequent sibling iterations (i > start) are duplicates",
                "Because start is always 0",
                "Because start tracks the size of result",
                "It is a random convention"
            ),
            correctOptionIndex = 0,
            explanation = "At a given recursion depth, index `start` is the first branch. If `i > start` and the value equals the preceding sibling, it will explore an identical decision subtree."
        )
    ),
    // Lesson 8
    TrainingDrill(
        id = "rec_drill_08",
        topicId = "recursion",
        lessonNumber = 8,
        title = "Self-Reusing Branches (Combination Sum)",
        subtitle = "Permitting unlimited element reuse by recursing on `i` instead of `i + 1`",
        conceptDelta = "+1 Concept: Recurse with `backtrack(i, ...)` instead of `i + 1` to allow repeated element selection",
        intuition = "In standard combinations, once an element `nums[i]` is picked, the next choice must come from `i + 1`. If an element can be reused infinitely (like coins in a change machine), simply pass `i` to the recursive call! The base case `remain < 0` acts as the natural boundary to terminate infinite recursion.",
        codePattern = """// Lesson 8: Unlimited Element Reuse
public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(candidates);
    backtrack(0, candidates, target, new ArrayList<>(), result);
    return result;
}
private void backtrack(int start, int[] candidates, int remain, List<Integer> current, List<List<Integer>> result) {
    if (remain == 0) {
        result.add(new ArrayList<>(current));
        return;
    }
    for (int i = start; i < candidates.length; i++) {
        if (candidates[i] > remain) break; // Prune: sorted candidates only get bigger
        current.add(candidates[i]);
        backtrack(i, candidates, remain - candidates[i], current, result); // Notice 'i', not 'i + 1'
        current.remove(current.size() - 1);
    }
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "candidates = [2, 3, 6, 7], target = 7", "Pick 2: remain = 5."),
            DrillTraceStep(2, "Pick 2 again: remain = 3", "Allowed because we passed `start = i = 0`."),
            DrillTraceStep(3, "Pick 2 again: remain = 1", "Pick 2 again: 2 > 1 -> pruned! Backtrack."),
            DrillTraceStep(4, "At remain = 3: pick 3", "remain = 3 - 3 = 0 -> MATCH! Add [2, 2, 3]."),
            DrillTraceStep(5, "Later: pick 7", "remain = 0 -> MATCH! Add [7]. Result: [[2, 2, 3], [7]].")
        ),
        quizQuestion = DrillQuiz(
            question = "Why does sorting candidates allow `if (candidates[i] > remain) break;`?",
            options = listOf(
                "Because if candidates[i] exceeds the remainder, all subsequent candidates are even larger and guaranteed to fail",
                "Because sorting makes target negative",
                "Because break restarts the recursion",
                "It is only valid for powers of 2"
            ),
            correctOptionIndex = 0,
            explanation = "Since candidates is sorted in ascending order, if the current element exceeds the remaining target, all remaining elements in the loop will also exceed it."
        )
    ),
    // Lesson 9
    TrainingDrill(
        id = "rec_drill_09",
        topicId = "recursion",
        lessonNumber = 9,
        title = "Combination Sum II (Single-Use with Duplicates)",
        subtitle = "Combining `i + 1` single-use recursion with `i > start` sibling deduplication",
        conceptDelta = "+1 Concept: Merging single-use progression (`i + 1`) with sibling duplicate pruning (`i > start && num[i] == num[i-1]`)",
        intuition = "Now combine Lessons 7 and 8: candidate numbers can contain duplicates, but each number may ONLY be used once. We sort the array, advance the index to `i + 1` (single use), and skip sibling duplicates with `if (i > start && candidates[i] == candidates[i - 1]) continue`.",
        codePattern = """// Lesson 9: Single-Use with Duplicates
public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(candidates);
    backtrack(0, candidates, target, new ArrayList<>(), result);
    return result;
}
private void backtrack(int start, int[] candidates, int remain, List<Integer> current, List<List<Integer>> result) {
    if (remain == 0) {
        result.add(new ArrayList<>(current));
        return;
    }
    for (int i = start; i < candidates.length; i++) {
        if (candidates[i] > remain) break;
        if (i > start && candidates[i] == candidates[i - 1]) continue; // Skip duplicates
        current.add(candidates[i]);
        backtrack(i + 1, candidates, remain - candidates[i], current, result); // Single use
        current.remove(current.size() - 1);
    }
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "candidates = [10, 1, 2, 7, 6, 1, 5], target = 8", "Sorted: [1, 1, 2, 5, 6, 7, 10]."),
            DrillTraceStep(2, "Pick first 1, then second 1, then 6", "1 + 1 + 6 = 8 -> Snapshot [1, 1, 6]."),
            DrillTraceStep(3, "At level 0: loop moves from first 1 to second 1", "i = 1 > start = 0, candidates[1] == candidates[0] -> SKIPPED!"),
            DrillTraceStep(4, "Result: unique combinations only", "[[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]].")
        ),
        quizQuestion = DrillQuiz(
            question = "What prevents combination `[1, 7]` from being produced twice from the two '1's in the input?",
            options = listOf(
                "The sibling deduplication check skips starting a combination with the second '1' if it was already explored with the first '1'",
                "The target sum check",
                "A HashSet in result",
                "ArrayDeque uniqueness"
            ),
            correctOptionIndex = 0,
            explanation = "At recursion depth 0, after exploring all combinations starting with candidates[0]=1, candidates[1]=1 is skipped because i > start and candidates[1] == candidates[0]."
        )
    ),
    // Lesson 10
    TrainingDrill(
        id = "rec_drill_10",
        topicId = "recursion",
        lessonNumber = 10,
        title = "Cartesian Product Tree (Phone Keypad Combinations)",
        subtitle = "Branching based on static string mapping tables",
        conceptDelta = "+1 Concept: Recursion depth `index` advances through input string; loop iterates through mapped letters",
        intuition = "On a phone keypad, '2' maps to \"abc\" and '3' maps to \"def\". To generate all letter combinations, recursion depth tracks the current digit `digits.charAt(index)`. The loop at each frame branches across all mapped letters for that specific digit, creating a full Cartesian product tree.",
        codePattern = """// Lesson 10: Cartesian Product Recursion
public List<String> letterCombinations(String digits) {
    if (digits.isEmpty()) return new ArrayList<>();
    String[] mapping = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    List<String> result = new ArrayList<>();
    backtrack(0, digits, new StringBuilder(), mapping, result);
    return result;
}
private void backtrack(int index, String digits, StringBuilder current, String[] mapping, List<String> result) {
    if (index == digits.length()) {
        result.add(current.toString());
        return;
    }
    String letters = mapping[digits.charAt(index) - '0'];
    for (char c : letters.toCharArray()) {
        current.append(c);
        backtrack(index + 1, digits, current, mapping, result);
        current.deleteCharAt(current.length() - 1);
    }
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "digits = \"23\"", "Depth 0 (digit '2'): letters = \"abc\"."),
            DrillTraceStep(2, "Choose 'a', depth becomes 1", "Depth 1 (digit '3'): letters = \"def\"."),
            DrillTraceStep(3, "Choose 'd', depth becomes 2", "Base case reached: add \"ad\"."),
            DrillTraceStep(4, "Backtrack: choose 'e', choose 'f'", "Produces \"ae\", \"af\"."),
            DrillTraceStep(5, "Backtrack to depth 0: choose 'b', 'c'", "Generates all 3 * 3 = 9 combinations.")
        ),
        quizQuestion = DrillQuiz(
            question = "For a 4-digit string on buttons with 3 letters each, how many leaf nodes are visited?",
            options = listOf(
                "3^4 = 81",
                "4 * 3 = 12",
                "4^3 = 64",
                "3! = 6"
            ),
            correctOptionIndex = 0,
            explanation = "At each of the 4 steps, there are 3 independent choices, resulting in 3 * 3 * 3 * 3 = 81 total leaf strings."
        )
    ),
    // Lesson 11
    TrainingDrill(
        id = "rec_drill_11",
        topicId = "recursion",
        lessonNumber = 11,
        title = "In-Place Grid Visited Masking (Word Search)",
        subtitle = "Mutating `board[r][c] = '#'` and restoring on backtrack to avoid extra memory",
        conceptDelta = "+1 Concept: In-place visited flag: save `char temp = board[r][c]; board[r][c] = '#';`, restore `board[r][c] = temp`",
        intuition = "Allocating a `boolean[m][n]` visited matrix takes extra memory and copying overhead. To track visited cells in a 2D grid in-place: overwrite the cell with a sentinel character like `'#'` before exploring its 4 neighbors, and restore the original character right before returning!",
        codePattern = """// Lesson 11: In-Place Grid Backtracking
public boolean exist(char[][] board, String word) {
    for (int r = 0; r < board.length; r++) {
        for (int c = 0; c < board[0].length; c++) {
            if (dfs(board, r, c, word, 0)) return true;
        }
    }
    return false;
}
private boolean dfs(char[][] board, int r, int c, String word, int idx) {
    if (idx == word.length()) return true;
    if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != word.charAt(idx)) {
        return false;
    }
    char temp = board[r][c];
    board[r][c] = '#'; // Mark visited in-place
    boolean found = dfs(board, r + 1, c, word, idx + 1) ||
                   dfs(board, r - 1, c, word, idx + 1) ||
                   dfs(board, r, c + 1, word, idx + 1) ||
                   dfs(board, r, c - 1, word, idx + 1);
    board[r][c] = temp; // Backtrack & restore original character
    return found;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "board contains \"ABCE\", search \"ABC\"", "Find 'A' at (0, 0). idx = 0 matches 'A'."),
            DrillTraceStep(2, "board[0][0] masked to '#'", "Recurse into neighbor (0, 1) ('B'). idx = 1 matches 'B'."),
            DrillTraceStep(3, "board[0][1] masked to '#'", "Neighbor (0, 0) cannot be revisited because '#' != 'C'."),
            DrillTraceStep(4, "Step to (0, 2) ('C')", "idx = 2 matches. Next call idx == 3 == word.length() -> returns true!"),
            DrillTraceStep(5, "Grid restored", "All cells unmasked back to original values.")
        ),
        quizQuestion = DrillQuiz(
            question = "What would happen if `board[r][c] = temp` was omitted after the neighbor checks?",
            options = listOf(
                "The board would be permanently corrupted with '#' characters, causing subsequent searches from other cells to fail",
                "The code would throw IndexOutOfBoundsException",
                "The search would run faster",
                "Nothing, because board is a copy"
            ),
            correctOptionIndex = 0,
            explanation = "Because the board array is passed by reference across all calls, failing to restore the cell leaves permanent '#' blockers that ruin subsequent search paths."
        )
    ),
    // Lesson 12
    TrainingDrill(
        id = "rec_drill_12",
        topicId = "recursion",
        lessonNumber = 12,
        title = "Matrix Flood Fill Traversal",
        subtitle = "Recursive 4-directional matrix coloring with boundary checks",
        conceptDelta = "+1 Concept: Check `image[r][c] != originalColor` to prevent infinite loops without a separate visited set",
        intuition = "Flood Fill replaces connected pixels of color C with newColor. The beauty is that the recoloring itself acts as the visited marker! If `image[r][c] == newColor`, we return immediately, naturally terminating recursion without any extra visited matrix.",
        codePattern = """// Lesson 12: Recursive Flood Fill
public int[][] floodFill(int[][] image, int sr, int sc, int color) {
    if (image[sr][sc] == color) return image; // Avoid infinite loop
    fill(image, sr, sc, image[sr][sc], color);
    return image;
}
private void fill(int[][] img, int r, int c, int oldColor, int newColor) {
    if (r < 0 || r >= img.length || c < 0 || c >= img[0].length || img[r][c] != oldColor) {
        return;
    }
    img[r][c] = newColor; // Recolor
    fill(img, r + 1, c, oldColor, newColor);
    fill(img, r - 1, c, oldColor, newColor);
    fill(img, r, c + 1, oldColor, newColor);
    fill(img, r, c - 1, oldColor, newColor);
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "image at (1, 1) has color 1, newColor = 2", "oldColor = 1."),
            DrillTraceStep(2, "img[1][1] becomes 2", "Dispatches to 4 neighbors."),
            DrillTraceStep(3, "Neighbors with color 1 become 2", "Recursion spreads to contiguous region."),
            DrillTraceStep(4, "Neighbors with color != 1 return immediately", "Terminates cleanly in O(M * N) time.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why is `if (image[sr][sc] == color) return image;` a critical guard at the entry point?",
            options = listOf(
                "If oldColor equals newColor, the condition `img[r][c] == oldColor` would always remain true, causing infinite recursive cycles and StackOverflowError",
                "To optimize performance only",
                "Because color 0 is illegal",
                "Because matrices cannot be modified in Java"
            ),
            correctOptionIndex = 0,
            explanation = "If the target color is identical to the starting color, recoloring doesn't change the cell value, causing neighbors to endlessly re-trigger each other."
        )
    ),
    // Lesson 13
    TrainingDrill(
        id = "rec_drill_13",
        topicId = "recursion",
        lessonNumber = 13,
        title = "Sink the Island (Connected Components)",
        subtitle = "Counting connected graph components by sinking visited land cells in-place",
        conceptDelta = "+1 Concept: Sinking land: change `'1'` to `'0'` during DFS to extinguish the entire connected island",
        intuition = "In Number of Islands, when an unvisited land cell `'1'` is found, increment your island count, then launch a DFS that 'sinks' that entire island by flipping every connected `'1'` to `'0'`. When the DFS finishes, that island is gone forever, so the outer scan will never double-count it!",
        codePattern = """// Lesson 13: Island Sinking DFS
public int numIslands(char[][] grid) {
    int count = 0;
    for (int r = 0; r < grid.length; r++) {
        for (int c = 0; c < grid[0].length; c++) {
            if (grid[r][c] == '1') {
                count++;
                sink(grid, r, c); // Sink entire island
            }
        }
    }
    return count;
}
private void sink(char[][] grid, int r, int c) {
    if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] != '1') return;
    grid[r][c] = '0'; // Sink cell
    sink(grid, r + 1, c);
    sink(grid, r - 1, c);
    sink(grid, r, c + 1);
    sink(grid, r, c - 1);
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "Scan finds '1' at (0, 0)", "count becomes 1. Launch sink(0, 0)."),
            DrillTraceStep(2, "sink flattens all connected land", "(0, 0), (0, 1), (1, 0) all flipped to '0'."),
            DrillTraceStep(3, "Scan continues through (0, 1)", "It is now '0' -> skipped! No double counting."),
            DrillTraceStep(4, "Scan finds isolated '1' at (3, 3)", "count becomes 2. Sinks (3, 3). Total islands = 2.")
        ),
        quizQuestion = DrillQuiz(
            question = "What is the time complexity of the Sink-the-Island algorithm?",
            options = listOf(
                "O(M * N) because every cell is visited at most twice (once by the loops, once by sink)",
                "O((M * N)²)",
                "O(2^(M + N))",
                "O(M * N log(M * N))"
            ),
            correctOptionIndex = 0,
            explanation = "Since each cell is flipped from '1' to '0' exactly once, no cell is processed more than a constant number of times."
        )
    ),
    // Lesson 14
    TrainingDrill(
        id = "rec_drill_14",
        topicId = "recursion",
        lessonNumber = 14,
        title = "Boundary Inversion Traversal (Surrounded Regions)",
        subtitle = "Traversing from edges inward to mark un-capturable cells",
        conceptDelta = "+1 Concept: Instead of searching inside for surrounded cells, search the border for UN-surrounded cells",
        intuition = "In Surrounded Regions, any 'O' connected to the border cannot be captured. Rather than checking if each interior 'O' can reach a border (slow), INVERT the logic: run DFS from all 'O's on the 4 outer borders and mark them as safe ('E'). Then flip all remaining 'O's to 'X', and restore 'E' back to 'O'!",
        codePattern = """// Lesson 14: Boundary Inward Traversal
public void solve(char[][] board) {
    int m = board.length, n = board[0].length;
    // Step 1: Mark all border-connected 'O's as 'E' (escaped)
    for (int r = 0; r < m; r++) {
        dfs(board, r, 0);
        dfs(board, r, n - 1);
    }
    for (int c = 0; c < n; c++) {
        dfs(board, 0, c);
        dfs(board, m - 1, c);
    }
    // Step 2: Flip 'O' to 'X' (captured), and 'E' to 'O' (safe)
    for (int r = 0; r < m; r++) {
        for (int c = 0; c < n; c++) {
            if (board[r][c] == 'O') board[r][c] = 'X';
            else if (board[r][c] == 'E') board[r][c] = 'O';
        }
    }
}
private void dfs(char[][] b, int r, int c) {
    if (r < 0 || r >= b.length || c < 0 || c >= b[0].length || b[r][c] != 'O') return;
    b[r][c] = 'E';
    dfs(b, r + 1, c); dfs(b, r - 1, c); dfs(b, r, c + 1); dfs(b, r, c - 1);
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "Board with center 'O' and border 'O'", "Border 'O' at (0, 1) launches DFS."),
            DrillTraceStep(2, "Border 'O' marked 'E'", "Center 'O' at (1, 1) is surrounded by 'X's and untouched."),
            DrillTraceStep(3, "Second pass flips cells", "Center 'O' becomes 'X'. 'E' reverts to 'O'."),
            DrillTraceStep(4, "Finished", "Surrounded region captured in O(M * N) time.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why is boundary-first traversal faster than checking each interior 'O'?",
            options = listOf(
                "Because border traversal touches safe components directly in a single pass without redundant path explorations",
                "Because borders are cached in L1 cache",
                "Because interior cells cannot run DFS",
                "It has the same complexity but uses more memory"
            ),
            correctOptionIndex = 0,
            explanation = "Checking interior cells requires exploring dead ends repeatedly. Starting from the border identifies all uncapturable cells in one clean pass."
        )
    ),
    // Lesson 15
    TrainingDrill(
        id = "rec_drill_15",
        topicId = "recursion",
        lessonNumber = 15,
        title = "Dual Reverse Flood Fill (Pacific Atlantic Water Flow)",
        subtitle = "Flowing backwards uphill from two oceans to find the intersection",
        conceptDelta = "+1 Concept: Flowing uphill (`neighbor >= current`) from ocean coastlines into two boolean reachability grids",
        intuition = "Water flows downhill from high to low. Finding which cells can reach BOTH oceans is tricky if you trace forward from every cell. Invert the problem: let water flow UPHILL from Pacific coast into `pacific[r][c]`, and from Atlantic coast into `atlantic[r][c]`. Any cell where `pacific[r][c] && atlantic[r][c]` can reach both!",
        codePattern = """// Lesson 15: Dual Uphill Flood Fill
public List<List<Integer>> pacificAtlantic(int[][] heights) {
    int m = heights.length, n = heights[0].length;
    boolean[][] pac = new boolean[m][n], atl = new boolean[m][n];
    for (int i = 0; i < m; i++) {
        dfs(heights, i, 0, pac, heights[i][0]);
        dfs(heights, i, n - 1, atl, heights[i][n - 1]);
    }
    for (int j = 0; j < n; j++) {
        dfs(heights, 0, j, pac, heights[0][j]);
        dfs(heights, m - 1, j, atl, heights[m - 1][j]);
    }
    List<List<Integer>> result = new ArrayList<>();
    for (int r = 0; r < m; r++) {
        for (int c = 0; c < n; c++) {
            if (pac[r][c] && atl[r][c]) result.add(List.of(r, c));
        }
    }
    return result;
}
private void dfs(int[][] h, int r, int c, boolean[][] visited, int prevH) {
    if (r < 0 || r >= h.length || c < 0 || c >= h[0].length || visited[r][c] || h[r][c] < prevH) return;
    visited[r][c] = true;
    dfs(h, r + 1, c, visited, h[r][c]);
    dfs(h, r - 1, c, visited, h[r][c]);
    dfs(h, r, c + 1, visited, h[r][c]);
    dfs(h, r, c - 1, visited, h[r][c]);
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "Initialize pac[][] and atl[][]", "Coast cells launch uphill DFS."),
            DrillTraceStep(2, "Water flows uphill: h[next] >= h[curr]", "Fills all plateaus reachable from Pacific and Atlantic."),
            DrillTraceStep(3, "Find intersection", "Cells where both pac[r][c] and atl[r][c] are true form the continental divide."),
            DrillTraceStep(4, "Result accumulated in O(M * N)", "Clean, symmetrical dual-flow traversal.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why does reverse flow check `h[r][c] >= prevH` instead of `<= prevH`?",
            options = listOf(
                "Because moving backwards from ocean to land requires moving uphill so that actual water can flow downhill into the ocean",
                "Because heights cannot be negative",
                "To prevent integer underflow",
                "It is a typo in the algorithm"
            ),
            correctOptionIndex = 0,
            explanation = "Since the simulation starts at the ocean and climbs back up to the mountains, moving to an equal or higher cell guarantees downhill flow in the real world."
        )
    ),
    // Lesson 16
    TrainingDrill(
        id = "rec_drill_16",
        topicId = "recursion",
        lessonNumber = 16,
        title = "The N-Queens Diagonal Constraint Arrays",
        subtitle = "Replacing O(N) board scans with O(1) diagonal index hash lookups",
        conceptDelta = "+1 Concept: Main diagonal index: `r - c + n`; Anti-diagonal index: `r + c`",
        intuition = "In N-Queens, checking if another queen threatens cell `(r, c)` naively takes O(N) diagonal scans. Notice the mathematical property: all cells on the same major diagonal have the same `r - c` (offset by +n to stay positive); all cells on the same anti-diagonal have the same `r + c`. Three boolean arrays give O(1) safety checks!",
        codePattern = """// Lesson 16: O(1) Diagonal Check N-Queens
public int totalNQueens(int n) {
    boolean[] cols = new boolean[n];
    boolean[] diag1 = new boolean[2 * n]; // r - c + n
    boolean[] diag2 = new boolean[2 * n]; // r + c
    return backtrack(0, n, cols, diag1, diag2);
}
private int backtrack(int r, int n, boolean[] cols, boolean[] d1, boolean[] d2) {
    if (r == n) return 1;
    int count = 0;
    for (int c = 0; c < n; c++) {
        int id1 = r - c + n, id2 = r + c;
        if (cols[c] || d1[id1] || d2[id2]) continue;
        cols[c] = d1[id1] = d2[id2] = true;
        count += backtrack(r + 1, n, cols, d1, d2);
        cols[c] = d1[id1] = d2[id2] = false; // Backtrack
    }
    return count;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "Row 0, try c = 0", "cols[0]=T, d1[0-0+4=4]=T, d2[0+0=0]=T. Advance to row 1."),
            DrillTraceStep(2, "Row 1, c = 0 and c = 1 are blocked", "c = 0 blocked by col. c = 1 blocked by diag2 (1+0=1 vs diag1). Try c = 2."),
            DrillTraceStep(3, "Backtracking unsets flags", "cols[c] = d1[id1] = d2[id2] = false restores state instantly in O(1)."),
            DrillTraceStep(4, "Count for N=4", "Returns 2 valid solutions in minimal CPU cycles.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why do all cells on the same anti-diagonal share the exact same `row + col` value?",
            options = listOf(
                "Because moving down-left increases row by 1 and decreases col by 1, leaving their sum (r + 1) + (c - 1) = r + c invariant",
                "Because matrices are symmetric",
                "Because chessboard squares alternate colors",
                "It is only true for 8x8 boards"
            ),
            correctOptionIndex = 0,
            explanation = "Stepping diagonally from top-right to bottom-left trades 1 column for 1 row, keeping the sum row + col constant for all cells on that diagonal."
        )
    ),
    // Lesson 17
    TrainingDrill(
        id = "rec_drill_17",
        topicId = "recursion",
        lessonNumber = 17,
        title = "Sudoku Constraint Validation & Early Return",
        subtitle = "Halting full tree exploration on the first valid board configuration",
        conceptDelta = "+1 Concept: `if (solve()) return true;` propagates success up the stack to halt further searching",
        intuition = "Unlike problems that collect ALL solutions (where backtrack returns void), Sudoku only asks for ONE valid solution. Therefore, our backtrack function returns `boolean`. The moment a valid board is found, `return true` short-circuits all recursive loops, instantly freezing the solved board!",
        codePattern = """// Lesson 17: Short-Circuiting Sudoku Solver
public boolean solveSudoku(char[][] board) {
    for (int r = 0; r < 9; r++) {
        for (int c = 0; c < 9; c++) {
            if (board[r][c] == '.') {
                for (char d = '1'; d <= '9'; d++) {
                    if (isValid(board, r, c, d)) {
                        board[r][c] = d;
                        if (solveSudoku(board)) return true; // Halt and propagate success
                        board[r][c] = '.';                  // Backtrack
                    }
                }
                return false; // No digit worked -> trigger backtrack
            }
        }
    }
    return true; // All cells filled
}
private boolean isValid(char[][] b, int r, int c, char d) {
    for (int i = 0; i < 9; i++) {
        if (b[r][i] == d || b[i][c] == d) return false;
        if (b[3 * (r / 3) + i / 3][3 * (c / 3) + i % 3] == d) return false; // 3x3 box
    }
    return true;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "Scan finds first empty cell '.'", "Tries digits '1' through '9'."),
            DrillTraceStep(2, "Digit '5' passes row, col, and 3x3 box check", "Places '5'. Recurses to next empty cell."),
            DrillTraceStep(3, "Subsequent cell has zero legal options", "Returns false. Backtrack resets '5' to '.'."),
            DrillTraceStep(4, "When last cell is successfully placed", "Returns true all the way up the stack without undoing.")
        ),
        quizQuestion = DrillQuiz(
            question = "How does the formula `3 * (r / 3) + i / 3` calculate the row inside a 3x3 block?",
            options = listOf(
                "`3 * (r / 3)` finds the top-left row of the 3x3 block, and `i / 3` steps 0, 1, 2 across the 3 rows",
                "It converts 2D coordinates to 1D",
                "It hashes the digit value",
                "It is only valid when r is a multiple of 3"
            ),
            correctOptionIndex = 0,
            explanation = "Integer division (r / 3) * 3 gives block starts 0, 3, 6. Adding i / 3 for i from 0 to 8 scans rows 0, 1, 2 within that block."
        )
    ),
    // Lesson 18
    TrainingDrill(
        id = "rec_drill_18",
        topicId = "recursion",
        lessonNumber = 18,
        title = "Palindrome Partitioning (Substring Segmentation)",
        subtitle = "Partitioning strings where every cut prefix must be a valid palindrome",
        conceptDelta = "+1 Concept: Branch only if `isPalindrome(s, start, i)` is true; recurse with `start = i + 1`",
        intuition = "To partition a string into palindromic substrings: from `start`, test every prefix `s.substring(start, i + 1)`. If the prefix is a palindrome, CHOOSE it, RECURSE on the remaining suffix `i + 1`, and UNDO. If a prefix is not a palindrome, prune that branch immediately!",
        codePattern = """// Lesson 18: Palindromic Partitioning
public List<List<String>> partition(String s) {
    List<List<String>> result = new ArrayList<>();
    backtrack(0, s, new ArrayList<>(), result);
    return result;
}
private void backtrack(int start, String s, List<String> current, List<List<String>> result) {
    if (start == s.length()) {
        result.add(new ArrayList<>(current));
        return;
    }
    for (int i = start; i < s.length(); i++) {
        if (isPal(s, start, i)) {
            current.add(s.substring(start, i + 1));
            backtrack(i + 1, s, current, result);
            current.remove(current.size() - 1);
        }
    }
}
private boolean isPal(String s, int l, int r) {
    while (l < r) if (s.charAt(l++) != s.charAt(r--)) return false;
    return true;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "s = \"aab\", start = 0", "i = 0: \"a\" is pal. current = [\"a\"]. Recurse start = 1."),
            DrillTraceStep(2, "start = 1", "i = 1: \"a\" is pal. current = [\"a\", \"a\"]. Recurse start = 2."),
            DrillTraceStep(3, "start = 2", "i = 2: \"b\" is pal. current = [\"a\", \"a\", \"b\"]. start == length -> snapshot!"),
            DrillTraceStep(4, "Backtrack to start = 0, test i = 1", "\"aa\" is pal. current = [\"aa\"]. Recurse start = 2 -> snapshot [\"aa\", \"b\"]."),
            DrillTraceStep(5, "Result", "[[\"a\", \"a\", \"b\"], [\"aa\", \"b\"]].")
        ),
        quizQuestion = DrillQuiz(
            question = "Why does checking `isPal` before recursing prune suboptimal paths effectively?",
            options = listOf(
                "Because if the current prefix isn't a palindrome, no continuation can ever make that prefix valid",
                "Because palindromes must have odd length",
                "Because strings are immutable",
                "It does not prune anything"
            ),
            correctOptionIndex = 0,
            explanation = "Since the definition requires EVERY substring in the partition to be a palindrome, an invalid prefix immediately disqualifies the entire branch."
        )
    ),
    // Lesson 19
    TrainingDrill(
        id = "rec_drill_19",
        topicId = "recursion",
        lessonNumber = 19,
        title = "Dual Counter Tree (Generate Parentheses)",
        subtitle = "Guiding recursive branching using open and close counter invariants",
        conceptDelta = "+1 Concept: Two rules: (1) Add '(' if `open < n`, (2) Add ')' if `close < open`",
        intuition = "Never generate all 2^(2N) strings and test validity at the end! Instead, enforce validity at every single step using two counters: (1) You can ALWAYS add an open bracket if you haven't used all N: `open < n`. (2) You can ONLY add a closing bracket if there is an unmatched open bracket: `close < open`.",
        codePattern = """// Lesson 19: Constrained Parentheses Generation
public List<String> generateParenthesis(int n) {
    List<String> result = new ArrayList<>();
    backtrack(new StringBuilder(), 0, 0, n, result);
    return result;
}
private void backtrack(StringBuilder sb, int open, int close, int n, List<String> result) {
    if (sb.length() == 2 * n) {
        result.add(sb.toString());
        return;
    }
    if (open < n) {
        sb.append('(');
        backtrack(sb, open + 1, close, n, result);
        sb.deleteCharAt(sb.length() - 1);
    }
    if (close < open) {
        sb.append(')');
        backtrack(sb, open, close + 1, n, result);
        sb.deleteCharAt(sb.length() - 1);
    }
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "n = 2", "open = 0, close = 0. Only '(' can be added (close < open is 0 < 0, false)."),
            DrillTraceStep(2, "sb = \"(\"", "open = 1, close = 0. Can add '(' OR ')'. Branch 1: \"((\". Branch 2: \"()\"."),
            DrillTraceStep(3, "Branch 1 \"((\"", "open = 2. Only ')' allowed -> \"(())\" (Snapshot!)."),
            DrillTraceStep(4, "Branch 2 \"()\"", "Add '(' then ')' -> \"()()\" (Snapshot!)."),
            DrillTraceStep(5, "Result: [\"(())\", \"()()\"]", "Zero invalid strings ever generated (Catalan number complexity).")
        ),
        quizQuestion = DrillQuiz(
            question = "Why does the condition `close < open` guarantee that no prefix has unmatched closing parentheses?",
            options = listOf(
                "Because a closing bracket is only permitted when there is an active surplus of open brackets awaiting closure",
                "Because close is always positive",
                "Because open cannot exceed 2N",
                "It is guaranteed by the JVM"
            ),
            correctOptionIndex = 0,
            explanation = "A parenthesis string is valid if and only if every prefix has close <= open. Enforcing close < open before adding ')' strictly maintains this invariant."
        )
    ),
    // Lesson 20
    TrainingDrill(
        id = "rec_drill_20",
        topicId = "recursion",
        lessonNumber = 20,
        title = "3-Dot Segmentation (Restore IP Addresses)",
        subtitle = "Segmenting a string into exactly 4 valid numeric octets",
        conceptDelta = "+1 Concept: Multi-character step (len 1 to 3) with leading-zero and <= 255 validation",
        intuition = "An IPv4 address has exactly 4 octets. At each step, take 1, 2, or 3 digits. Validate: (1) `val <= 255`, and (2) NO leading zeros (e.g. \"01\" is illegal, \"0\" is legal). Recurse with `dots + 1`. When `dots == 4` and the entire string is consumed, a valid IP is formed!",
        codePattern = """// Lesson 20: 4-Segment IP Backtracking
public List<String> restoreIpAddresses(String s) {
    List<String> result = new ArrayList<>();
    if (s.length() < 4 || s.length() > 12) return result;
    backtrack(0, 0, s, new ArrayList<>(), result);
    return result;
}
private void backtrack(int start, int dots, String s, List<String> current, List<String> result) {
    if (dots == 4 && start == s.length()) {
        result.add(String.join(".", current));
        return;
    }
    if (dots == 4 || start == s.length()) return;
    for (int len = 1; len <= 3 && start + len <= s.length(); len++) {
        String segment = s.substring(start, start + len);
        int val = Integer.parseInt(segment);
        if (val > 255 || (segment.startsWith("0") && segment.length() > 1)) break;
        current.add(segment);
        backtrack(start + len, dots + 1, s, current, result);
        current.remove(current.size() - 1);
    }
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "s = \"25525511135\"", "Segment 1: takes \"255\". dots = 1."),
            DrillTraceStep(2, "Segment 2: takes \"255\"", "dots = 2."),
            DrillTraceStep(3, "Segment 3: takes \"11\"", "dots = 3. Remaining: \"135\" (<= 255)."),
            DrillTraceStep(4, "Segment 4: takes \"135\"", "dots = 4, start == s.length() -> \"255.255.11.135\"!"),
            DrillTraceStep(5, "Leading zero rejection", "Any segment like \"01\" fails `startsWith(\"0\")` check and breaks immediately.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why can the loop `break` instead of `continue` when `val > 255`?",
            options = listOf(
                "Because taking an even longer substring of digits will only produce an even larger integer, which will also exceed 255",
                "Because break resets start to 0",
                "Because len can only be 3",
                "To prevent NullPointerException"
            ),
            correctOptionIndex = 0,
            explanation = "Appending more digits to a number > 255 will strictly increase its numerical value, so further lengths in the loop are guaranteed to fail."
        )
    ),
    // Lesson 21
    TrainingDrill(
        id = "rec_drill_21",
        topicId = "recursion",
        lessonNumber = 21,
        title = "Divide & Conquer: The Merge Sort Blueprint",
        subtitle = "Splitting into halves recursively and zipping sorted lists in linear time",
        conceptDelta = "+1 Concept: Dividing input in half until base case (len <= 1), then recombining with linear `merge`",
        intuition = "Divide & Conquer splits a problem into independent equal subproblems, solves them recursively, and merges the answers. In Merge Sort: sort the left half, sort the right half, then merge two sorted halves using two pointers in O(N). Recurrence: T(N) = 2T(N/2) + O(N) = O(N log N).",
        codePattern = """// Lesson 21: Merge Sort Divide & Conquer
public void mergeSort(int[] nums, int l, int r) {
    if (l >= r) return; // Base case: 1 element
    int mid = l + (r - l) / 2;
    mergeSort(nums, l, mid);     // Divide left
    mergeSort(nums, mid + 1, r); // Divide right
    merge(nums, l, mid, r);      // Conquer (Combine)
}
private void merge(int[] nums, int l, int mid, int r) {
    int[] temp = new int[r - l + 1];
    int i = l, j = mid + 1, k = 0;
    while (i <= mid && j <= r) {
        temp[k++] = (nums[i] <= nums[j]) ? nums[i++] : nums[j++];
    }
    while (i <= mid) temp[k++] = nums[i++];
    while (j <= r) temp[k++] = nums[j++];
    System.arraycopy(temp, 0, nums, l, temp.length);
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "nums = [38, 27, 43, 3, 9, 82, 10]", "Divides into [38, 27, 43] and [3, 9, 82, 10]."),
            DrillTraceStep(2, "Subproblems divide to base cases", "[38], [27], [43]... each length 1."),
            DrillTraceStep(3, "Merge [38] and [27]", "Becomes sorted [27, 38]."),
            DrillTraceStep(4, "Merge [27, 38] and [43]", "Becomes [27, 38, 43]."),
            DrillTraceStep(5, "Final merge of both halves", "Combined in O(N) into [3, 9, 10, 27, 38, 43, 82].")
        ),
        quizQuestion = DrillQuiz(
            question = "Why is Merge Sort strictly O(N log N) in all cases (best, average, worst)?",
            options = listOf(
                "Because the recursion tree always has exactly log N levels, and each level performs exactly O(N) merging work",
                "Because it uses binary search",
                "Because Java optimizes array copies",
                "Because elements are compared in pairs"
            ),
            correctOptionIndex = 0,
            explanation = "Regardless of input ordering, splitting into halves creates a balanced tree of depth log2(N), and merging across each level takes N operations."
        )
    ),
    // Lesson 22
    TrainingDrill(
        id = "rec_drill_22",
        topicId = "recursion",
        lessonNumber = 22,
        title = "Quick Sort Partitioning (Divide in Place)",
        subtitle = "Placing a pivot in its exact final position before recursing on sub-arrays",
        conceptDelta = "+1 Concept: Partitioning in-place around a pivot element: all smaller left, all larger right",
        intuition = "In Merge Sort, division was trivial (split in middle) and work was done on the way UP (merge). In Quick Sort, work is done on the way DOWN (partition)! Pick a pivot, partition so smaller elements are on left and larger on right. The pivot is now PERMANENTLY sorted. Recurse left and right.",
        codePattern = """// Lesson 22: Quick Sort Lomuto Partition
public void quickSort(int[] nums, int low, int high) {
    if (low < high) {
        int pIndex = partition(nums, low, high);
        quickSort(nums, low, pIndex - 1);
        quickSort(nums, pIndex + 1, high);
    }
}
private int partition(int[] nums, int low, int high) {
    int pivot = nums[high];
    int i = low - 1;
    for (int j = low; j < high; j++) {
        if (nums[j] <= pivot) {
            i++;
            swap(nums, i, j);
        }
    }
    swap(nums, i + 1, high); // Place pivot in its permanent spot
    return i + 1;
}
private void swap(int[] a, int i, int j) {
    int t = a[i]; a[i] = a[j]; a[j] = t;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "nums = [10, 80, 30, 90, 40, 50, 70]", "pivot = 70 (nums[high])."),
            DrillTraceStep(2, "Partitioning passes through", "Elements <= 70 (10, 30, 40, 50) swapped to the left side."),
            DrillTraceStep(3, "Swap pivot 70 into index 4", "Array: [10, 30, 40, 50, 70, 90, 80]. Index 4 is pivot's final spot!"),
            DrillTraceStep(4, "Recurse on [10, 30, 40, 50] and [90, 80]", "Sorts in-place with O(1) extra array memory.")
        ),
        quizQuestion = DrillQuiz(
            question = "What is the worst-case time complexity of Quick Sort, and when does it occur?",
            options = listOf(
                "O(N²) when the array is already sorted and the pivot is chosen as the first or last element",
                "O(N log N) always",
                "O(N!)",
                "O(N) when all elements are distinct"
            ),
            correctOptionIndex = 0,
            explanation = "If the pivot is always the extreme smallest or largest element, partitioning produces lopsided subproblems of size N-1, degrading to N levels of N work = O(N²)."
        )
    ),
    // Lesson 23
    TrainingDrill(
        id = "rec_drill_23",
        topicId = "recursion",
        lessonNumber = 23,
        title = "Binary Exponentiation (Fast Power `x^n`)",
        subtitle = "Halving powers recursively to achieve O(log N) runtime",
        conceptDelta = "+1 Concept: `x^n = (x^(n/2))^2` (if even) or `x * (x^(n/2))^2` (if odd)",
        intuition = "Computing 2^1,000,000 by multiplying 2 a million times takes O(N). But 2^1000 = (2^500)^2! By computing `half = pow(x, n / 2)` ONCE and squaring it, each step cuts the exponent in half. The problem solves in only 20 multiplications instead of 1,000,000!",
        codePattern = """// Lesson 23: Fast Binary Power
public double myPow(double x, int n) {
    long N = n;
    if (N < 0) {
        x = 1 / x;
        N = -N;
    }
    return fastPow(x, N);
}
private double fastPow(double x, long n) {
    if (n == 0) return 1.0;
    double half = fastPow(x, n / 2); // Compute half power once
    if (n % 2 == 0) {
        return half * half;
    } else {
        return half * half * x;
    }
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "Compute 2^10", "n is even: fastPow(2, 5)."),
            DrillTraceStep(2, "Compute 2^5", "n is odd: 2 * fastPow(2, 2)^2."),
            DrillTraceStep(3, "Compute 2^2", "n is even: fastPow(2, 1)^2."),
            DrillTraceStep(4, "Unwind: half squared", "half = 2 -> 4 -> 32 -> 1024. Only 4 recursive calls instead of 10!")
        ),
        quizQuestion = DrillQuiz(
            question = "Why is `long N = n` used before `N = -N`?",
            options = listOf(
                "Because in Java, Integer.MIN_VALUE (-2,147,483,648) negated overflows signed 32-bit int and remains negative",
                "Because double requires long",
                "To support float exponents",
                "It is optional"
            ),
            correctOptionIndex = 0,
            explanation = "Math.abs(Integer.MIN_VALUE) overflows back to -2,147,483,648 in standard 32-bit two's complement. Promoting to 64-bit long allows safe negation."
        )
    ),
    // Lesson 24
    TrainingDrill(
        id = "rec_drill_24",
        topicId = "recursion",
        lessonNumber = 24,
        title = "Recursive Binary Search & Tail Call Form",
        subtitle = "Framing binary search as a pure divide-and-conquer function",
        conceptDelta = "+1 Concept: Tail recursion: the recursive call is the very last operation, making return combine O(1)",
        intuition = "In Lesson 21 (Merge Sort), the combine step did O(N) work. In Binary Search, the combine step does ZERO work! The return value of the recursive sub-call is returned directly. This is called 'tail recursion', which compilers can optimize into a simple loop without stack growth.",
        codePattern = """// Lesson 24: Tail Recursive Binary Search
public int search(int[] nums, int target) {
    return binarySearch(nums, target, 0, nums.length - 1);
}
private int binarySearch(int[] nums, int target, int low, int high) {
    if (low > high) return -1; // Base case: not found
    int mid = low + (high - low) / 2;
    if (nums[mid] == target) return mid;
    if (nums[mid] > target) {
        return binarySearch(nums, target, low, mid - 1); // Tail call left
    } else {
        return binarySearch(nums, target, mid + 1, high); // Tail call right
    }
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "nums = [1, 3, 5, 7, 9], target = 7", "Call binarySearch(0, 4): mid = 2 (val 5)."),
            DrillTraceStep(2, "5 < 7", "Direct tail call binarySearch(3, 4)."),
            DrillTraceStep(3, "mid = 3: val 7 == 7", "Match! Returns index 3 directly up the stack.")
        ),
        quizQuestion = DrillQuiz(
            question = "What distinguishes 'tail recursion' from general recursion?",
            options = listOf(
                "In tail recursion, the recursive call is the final expression executed before returning, with no pending computations after it",
                "Tail recursion only works on sorted arrays",
                "Tail recursion requires two base cases",
                "Tail recursion runs on background threads"
            ),
            correctOptionIndex = 0,
            explanation = "Because no work remains in the current stack frame after the tail call returns, the current frame can be discarded or reused immediately."
        )
    ),
    // Lesson 25
    TrainingDrill(
        id = "rec_drill_25",
        topicId = "recursion",
        lessonNumber = 25,
        title = "Merge Sort Inversion Counting",
        subtitle = "Piggybacking frequency counts onto the merge step in O(N log N)",
        conceptDelta = "+1 Concept: When `nums[i] > nums[j]` during merge, `nums[i]` and ALL remaining elements in left half are greater than nums[j]",
        intuition = "Finding how many pairs `(i, j)` have `i < j` and `nums[i] > nums[j]` naively takes O(N²). Because Merge Sort already compares and sorts both halves, during the merge step: if `nums[i] > nums[j]`, then since left half is sorted, EVERY element from `i` to `mid` is ALSO > `nums[j]`! We count `(mid - i + 1)` inversions in O(1)!",
        codePattern = """// Lesson 25: Merge Sort Inversion Counter
public int countInversions(int[] nums) {
    return mergeSortAndCount(nums, 0, nums.length - 1);
}
private int mergeSortAndCount(int[] nums, int l, int r) {
    if (l >= r) return 0;
    int mid = l + (r - l) / 2;
    int count = mergeSortAndCount(nums, l, mid) + mergeSortAndCount(nums, mid + 1, r);
    count += mergeAndCount(nums, l, mid, r);
    return count;
}
private int mergeAndCount(int[] nums, int l, int mid, int r) {
    int[] temp = new int[r - l + 1];
    int i = l, j = mid + 1, k = 0, inversions = 0;
    while (i <= mid && j <= r) {
        if (nums[i] <= nums[j]) {
            temp[k++] = nums[i++];
        } else {
            temp[k++] = nums[j++];
            inversions += (mid - i + 1); // Key insight: all remaining left elements are inversions!
        }
    }
    while (i <= mid) temp[k++] = nums[i++];
    while (j <= r) temp[k++] = nums[j++];
    System.arraycopy(temp, 0, nums, l, temp.length);
    return inversions;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "Left half: [2, 4], Right half: [1, 3]", "Comparing 2 with 1: 2 > 1."),
            DrillTraceStep(2, "Inversion discovered!", "Since 2 > 1, 4 must ALSO be > 1. Add (mid - i + 1) = (1 - 0 + 1) = 2 inversions."),
            DrillTraceStep(3, "Compare 2 with 3: 2 <= 3", "No inversion. Add 2 to temp."),
            DrillTraceStep(4, "Compare 4 with 3: 4 > 3", "Add (mid - i + 1) = 1 inversion. Total = 3 inversions in O(N log N).")
        ),
        quizQuestion = DrillQuiz(
            question = "Why does `inversions += (mid - i + 1)` correctly count inversions without examining elements individually?",
            options = listOf(
                "Because the left subarray is sorted, meaning all elements from index i to mid are >= nums[i] > nums[j]",
                "Because mid is the average of l and r",
                "Because right subarray is discarded",
                "It is an approximation"
            ),
            correctOptionIndex = 0,
            explanation = "Since the left partition is already sorted, any element located at or after index i in the left half is guaranteed to be greater than nums[j]."
        )
    ),
    // Lesson 26
    TrainingDrill(
        id = "rec_drill_26",
        topicId = "recursion",
        lessonNumber = 26,
        title = "Memoized Backtracking (Word Break II)",
        subtitle = "Pruning overlapping recursive subproblems with a Map<Integer, List<String>> cache",
        conceptDelta = "+1 Concept: Memoizing the result of a backtracking subproblem at index `start` to avoid re-segmentation",
        intuition = "In Word Break II (\"catsanddog\"), generating all valid sentence segmentations naively takes O(2^N). Multiple paths can reach the exact same suffix (e.g. \"dog\"). By caching `memo.put(start, validSentencesFromStart)`, we solve the suffix once and reuse the list instantly!",
        codePattern = """// Lesson 26: Memoized Backtracking
public List<String> wordBreak(String s, List<String> wordDict) {
    Set<String> dict = new HashSet<>(wordDict);
    Map<Integer, List<String>> memo = new HashMap<>();
    return dfs(s, 0, dict, memo);
}
private List<String> dfs(String s, int start, Set<String> dict, Map<Integer, List<String>> memo) {
    if (memo.containsKey(start)) return memo.get(start);
    List<String> res = new ArrayList<>();
    if (start == s.length()) {
        res.add("");
        return res;
    }
    for (int end = start + 1; end <= s.length(); end++) {
        String word = s.substring(start, end);
        if (dict.contains(word)) {
            List<String> subList = dfs(s, end, dict, memo);
            for (String sub : subList) {
                res.add(word + (sub.isEmpty() ? "" : " " + sub));
            }
        }
    }
    memo.put(start, res);
    return res;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "s = \"catsanddog\"", "Branches on \"cat\" and \"cats\"."),
            DrillTraceStep(2, "Both paths eventually need to segment \"anddog\" and \"dog\"", "DFS computes segmentations for \"dog\" and memoizes at index 7: [\"dog\"]."),
            DrillTraceStep(3, "Second path reaches index 7", "Instant cache hit! Reuses [\"dog\"] without re-traversing the dictionary."),
            DrillTraceStep(4, "Combines answers", "Returns [\"cats and dog\", \"cat sand dog\"].")
        ),
        quizQuestion = DrillQuiz(
            question = "How does memoization bridge pure backtracking with dynamic programming?",
            options = listOf(
                "Memoization caches solutions to identical sub-problems encountered along different branches, pruning the exponential search tree",
                "It converts recursion to loops",
                "It eliminates all stack frames",
                "It only works on strings"
            ),
            correctOptionIndex = 0,
            explanation = "Top-down memoized backtracking is mathematically equivalent to dynamic programming: overlapping subproblems are computed once and stored."
        )
    ),
    // Lesson 27
    TrainingDrill(
        id = "rec_drill_27",
        topicId = "recursion",
        lessonNumber = 27,
        title = "Multiplication Precedence Compensation",
        subtitle = "Tracking previous operand value to undo addition when `*` arrives",
        conceptDelta = "+1 Concept: State parameter `prevOperand`: when '*' arrives, compute `currentVal - prevOperand + (prevOperand * cur)`",
        intuition = "In Expression Add Operators, building expressions like `2 + 3 * 4` left-to-right evaluates `2 + 3 = 5`, then `5 * 4 = 20` (WRONG, precedence requires 14). Solution: carry `prevOperand` down the stack! When `*` is applied to `cur`, subtract `prevOperand` and add `(prevOperand * cur)` back!",
        codePattern = """// Lesson 27: Operator Precedence Backtracking
public List<String> addOperators(String num, int target) {
    List<String> result = new ArrayList<>();
    backtrack(0, "", 0, 0, num, target, result);
    return result;
}
private void backtrack(int idx, String path, long eval, long prev, String num, int target, List<String> result) {
    if (idx == num.length()) {
        if (eval == target) result.add(path);
        return;
    }
    for (int i = idx; i < num.length(); i++) {
        if (i != idx && num.charAt(idx) == '0') break; // No leading zero numbers
        long cur = Long.parseLong(num.substring(idx, i + 1));
        if (idx == 0) {
            backtrack(i + 1, path + cur, cur, cur, num, target, result);
        } else {
            backtrack(i + 1, path + "+" + cur, eval + cur, cur, num, target, result);
            backtrack(i + 1, path + "-" + cur, eval - cur, -cur, num, target, result);
            // Precedence correction for multiplication:
            backtrack(i + 1, path + "*" + cur, eval - prev + (prev * cur), prev * cur, num, target, result);
        }
    }
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "Expression so far: 2 + 3, eval = 5, prev = 3", "Next number is 4 with operator '*'."),
            DrillTraceStep(2, "eval - prev + (prev * cur)", "5 - 3 + (3 * 4) = 2 + 12 = 14!"),
            DrillTraceStep(3, "Correct multiplication precedence restored", "New prev becomes 3 * 4 = 12."),
            DrillTraceStep(4, "Matches target 14", "Result adds \"2+3*4\".")
        ),
        quizQuestion = DrillQuiz(
            question = "Why is `eval - prev + (prev * cur)` mathematically correct for multiplication precedence?",
            options = listOf(
                "It undoes the previous addition/subtraction of `prev` and replaces it with the grouped product `prev * cur`",
                "Because multiplication distributes over addition",
                "Because eval is always positive",
                "It is an approximation"
            ),
            correctOptionIndex = 0,
            explanation = "If the expression was A + B, eval holds (A + B) and prev holds B. Subtracting B gives A, and adding B * C gives A + (B * C)."
        )
    ),
    // Lesson 28
    TrainingDrill(
        id = "rec_drill_28",
        topicId = "recursion",
        lessonNumber = 28,
        title = "K-Partitioning & Descending Pruning",
        subtitle = "Partitioning matchsticks into 4 equal sides with heavy branch pruning",
        conceptDelta = "+1 Concept: Sort descending so large elements fail early; skip identical side lengths",
        intuition = "In Matchsticks to Square (partition into 4 equal sides): sorting the array DESCENDING makes large sticks placed first. If a 10-length stick cannot fit into a side of length 12, it fails immediately on step 1 rather than after 20 recursive steps! Also, if `sides[j] == sides[j-1]`, trying side j produces the exact same branch—skip it!",
        codePattern = """// Lesson 28: Pruned 4-Partitioning
public boolean makesquare(int[] matchsticks) {
    int sum = 0;
    for (int m : matchsticks) sum += m;
    if (sum % 4 != 0 || matchsticks.length < 4) return false;
    Arrays.sort(matchsticks); // Sort ascending, then traverse backwards for descending
    int target = sum / 4;
    return dfs(matchsticks.length - 1, matchsticks, new int[4], target);
}
private boolean dfs(int idx, int[] matches, int[] sides, int target) {
    if (idx < 0) return true; // All sticks placed
    for (int i = 0; i < 4; i++) {
        if (sides[i] + matches[idx] > target) continue;
        // Skip identical side capacities
        if (i > 0 && sides[i] == sides[i - 1]) continue;
        sides[i] += matches[idx];
        if (dfs(idx - 1, matches, sides, target)) return true;
        sides[i] -= matches[idx];
    }
    return false;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "matchsticks = [1, 1, 2, 2, 2], sum = 8, target = 2", "Sorted descending: [2, 2, 2, 1, 1]."),
            DrillTraceStep(2, "First 2 placed in side 0", "side 0 = 2 == target (full)."),
            DrillTraceStep(3, "Second 2 placed in side 1", "side 1 = 2 == target (full)."),
            DrillTraceStep(4, "All 4 sides reached capacity 2", "Returns true in fewer than 10 total steps due to pruning.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why does sorting descending speed up partitioning problems by orders of magnitude?",
            options = listOf(
                "Because large elements have fewer valid placement choices and hit capacity overflow early, pruning massive dead branches near the root",
                "Because descending arrays take less cache memory",
                "Because 4 is an even number",
                "It changes the time complexity to O(N)"
            ),
            correctOptionIndex = 0,
            explanation = "Placing large elements first triggers constraints almost immediately, cutting off huge subtrees that would otherwise explore hundreds of tiny elements before failing."
        )
    ),
    // Lesson 29
    TrainingDrill(
        id = "rec_drill_29",
        topicId = "recursion",
        lessonNumber = 29,
        title = "Dual Sign Decision Tree (Target Sum)",
        subtitle = "Exploring + and - binary branches at each array position",
        conceptDelta = "+1 Concept: Binary tree recursion: at index `i`, evaluate `recurse(+nums[i])` + `recurse(-nums[i])`",
        intuition = "Given an array where each number can be preceded by '+' or '-': this forms a binary decision tree of depth N with 2^N leaves. At each index `i`, we branch into two calls: (1) add `nums[i]` to running sum, (2) subtract `nums[i]` from running sum. Base case: `i == nums.length && sum == target`.",
        codePattern = """// Lesson 29: +/- Binary Branching
public int findTargetSumWays(int[] nums, int target) {
    return backtrack(0, 0, nums, target);
}
private int backtrack(int i, int currentSum, int[] nums, int target) {
    if (i == nums.length) {
        return currentSum == target ? 1 : 0;
    }
    // Branch 1: Add nums[i]
    int add = backtrack(i + 1, currentSum + nums[i], nums, target);
    // Branch 2: Subtract nums[i]
    int subtract = backtrack(i + 1, currentSum - nums[i], nums, target);
    return add + subtract;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "nums = [1, 1, 1], target = 1", "i = 0: branches into +1 and -1."),
            DrillTraceStep(2, "From +1", "Branches into +1 (+2) and -1 (0)."),
            DrillTraceStep(3, "From +2", "Branches into +1 (+3) and -1 (+1 -> MATCH!)."),
            DrillTraceStep(4, "Total matches accumulated", "Returns 3 valid expression combinations.")
        ),
        quizQuestion = DrillQuiz(
            question = "What is the total number of operations in this unmemoized binary decision tree?",
            options = listOf(
                "O(2^N)",
                "O(N²)",
                "O(N)",
                "O(N!)"
            ),
            correctOptionIndex = 0,
            explanation = "Every index branches into exactly 2 possibilities (+ and -), creating a complete binary tree of depth N with 2^N leaves."
        )
    ),
    // Lesson 30
    TrainingDrill(
        id = "rec_drill_30",
        topicId = "recursion",
        lessonNumber = 30,
        title = "Stateful Directional Backtracking (Robot Room Cleaner)",
        subtitle = "Recovering physical orientation after exploring an unmapped virtual maze",
        conceptDelta = "+1 Concept: Physical orientation recovery: after exploring, robot must turn 180°, move back, and turn 180° again",
        intuition = "When backtracking on a physical entity (like a robot in an unknown room), you cannot simply decrement an index. You must PHYSICALLY move the robot back to where it came from! The undo pattern: `robot.turnRight(); robot.turnRight(); robot.move(); robot.turnRight(); robot.turnRight();`. Orientation and position are fully preserved.",
        codePattern = """// Lesson 30: Stateful Physical Undo
public void cleanRoom(Robot robot) {
    // Directions: 0: Up, 1: Right, 2: Down, 3: Left
    Set<String> visited = new HashSet<>();
    dfs(0, 0, 0, robot, visited);
}
private void dfs(int r, int c, int d, Robot robot, Set<String> visited) {
    visited.add(r + "," + c);
    robot.clean();
    
    int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
    for (int i = 0; i < 4; i++) {
        int newD = (d + i) % 4;
        int nr = r + dr[newD], nc = c + dc[newD];
        if (!visited.contains(nr + "," + nc) && robot.move()) {
            dfs(nr, nc, newD, robot, visited);
            goBack(robot); // Physical backtrack!
        }
        robot.turnRight(); // Turn to next direction
    }
}
private void goBack(Robot robot) {
    robot.turnRight(); robot.turnRight(); // 180 degrees
    robot.move();                         // Step back
    robot.turnRight(); robot.turnRight(); // Restore original direction
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "Robot starts at (0, 0) facing UP", "Cleans (0, 0), marks visited."),
            DrillTraceStep(2, "Tries UP: obstacle", "robot.turnRight() -> now facing RIGHT."),
            DrillTraceStep(3, "Tries RIGHT: moves to (0, 1)", "Cleans (0, 1), explores sub-room."),
            DrillTraceStep(4, "Sub-room complete: goBack() called", "Turns 180°, moves back to (0, 0), turns 180° to face original direction."),
            DrillTraceStep(5, "Room cleaned", "Zero cells missed, robot returns safely to starting state.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why must `goBack(robot)` turn right four times in total (two 180-degree turns)?",
            options = listOf(
                "Two right turns face backwards to take a step back; two more right turns restore the robot's original facing orientation",
                "Because robots can only rotate 360 degrees",
                "To clean the floor twice",
                "It is an arbitrary motor requirement"
            ),
            correctOptionIndex = 0,
            explanation = "Stepping backward requires facing 180 degrees away. Once back in the parent cell, another 180-degree turn restores the exact heading the parent loop expected."
        )
    )
)
