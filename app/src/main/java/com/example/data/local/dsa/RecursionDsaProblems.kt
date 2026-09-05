package com.example.data.local.dsa
import com.example.domain.model.DsaProblem

object RecursionDsaProblems {
    fun getProblems(): List<DsaProblem> {
        return problems
    }

    private val problems = listOf(
        DsaProblem(
            id = "rec_001",
            topic = "recursion",
            title = "Factorial of a Number",
            difficulty = "Easy",
            pattern = "Basic Linear Recursion",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N) call stack",
            description = "Calculate the factorial of a non-negative integer n using recursion. Factorial of n (n!) is the product of all positive integers less than or equal to n.",
            exampleInput = "n = 5",
            exampleOutput = "120",
            keyInsight = "Base case: if n <= 1, return 1. Recursive case: return n * factorial(n - 1). Each call reduces the subproblem by 1.",
            solutionCode = """class Solution {
    public long factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }
}"""
        ),
        DsaProblem(
            id = "rec_002",
            topic = "recursion",
            title = "Fibonacci Number",
            difficulty = "Easy",
            pattern = "Tree Recursion / Memoization",
            timeComplexity = "O(2^N) naive or O(N) memoized",
            spaceComplexity = "O(N)",
            description = "The Fibonacci numbers form a sequence where each number is the sum of the two preceding ones, starting from 0 and 1. Compute F(n) recursively.",
            exampleInput = "n = 4",
            exampleOutput = "3 (0, 1, 1, 2, 3)",
            keyInsight = "Base cases: F(0) = 0, F(1) = 1. Recursive step: F(n) = F(n-1) + F(n-2). Use memoization array to avoid exponential redundant branches.",
            solutionCode = """class Solution {
    public int fib(int n) {
        if (n <= 1) return n;
        return fib(n - 1) + fib(n - 2);
    }
}"""
        ),
        DsaProblem(
            id = "rec_003",
            topic = "recursion",
            title = "Sum of Natural Numbers up to N",
            difficulty = "Easy",
            pattern = "Linear Recursion",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given an integer n, find the sum of all integers from 1 to n using recursion.",
            exampleInput = "n = 10",
            exampleOutput = "55",
            keyInsight = "Base case: when n == 0, sum is 0. Otherwise, return n + sum(n - 1).",
            solutionCode = """class Solution {
    public int sumToN(int n) {
        if (n <= 0) return 0;
        return n + sumToN(n - 1);
    }
}"""
        ),
        DsaProblem(
            id = "rec_004",
            topic = "recursion",
            title = "Print 1 to N Without Loops",
            difficulty = "Easy",
            pattern = "Post-Order / Head Recursion",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Print numbers from 1 to n without using any for, while, or do-while loops.",
            exampleInput = "n = 5",
            exampleOutput = "1 2 3 4 5",
            keyInsight = "Recursively call print(n - 1) first, and then print n after the recursive call returns (head recursion / post-order print).",
            solutionCode = """class Solution {
    public void print1ToN(int n) {
        if (n == 0) return;
        print1ToN(n - 1);
        System.out.print(n + " ");
    }
}"""
        ),
        DsaProblem(
            id = "rec_005",
            topic = "recursion",
            title = "Print N to 1 Without Loops",
            difficulty = "Easy",
            pattern = "Pre-Order / Tail Recursion",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Print numbers from n down to 1 without using any loop constructs.",
            exampleInput = "n = 5",
            exampleOutput = "5 4 3 2 1",
            keyInsight = "Print n immediately before making the recursive call print(n - 1) (pre-order print).",
            solutionCode = """class Solution {
    public void printNTo1(int n) {
        if (n == 0) return;
        System.out.print(n + " ");
        printNTo1(n - 1);
    }
}"""
        ),
        DsaProblem(
            id = "rec_006",
            topic = "recursion",
            title = "Sum of Digits of a Number",
            difficulty = "Easy",
            pattern = "Division & Modulo Recursion",
            timeComplexity = "O(log10(N))",
            spaceComplexity = "O(log10(N))",
            description = "Given a non-negative integer n, compute the sum of its decimal digits recursively.",
            exampleInput = "n = 12345",
            exampleOutput = "15 (1 + 2 + 3 + 4 + 5)",
            keyInsight = "Base case: if n == 0, return 0. Recursive step: return (n % 10) + sumDigits(n / 10).",
            solutionCode = """class Solution {
    public int sumDigits(int n) {
        if (n == 0) return 0;
        return (n % 10) + sumDigits(n / 10);
    }
}"""
        ),
        DsaProblem(
            id = "rec_007",
            topic = "recursion",
            title = "Count Digits of a Number",
            difficulty = "Easy",
            pattern = "Division Recursion",
            timeComplexity = "O(log10(N))",
            spaceComplexity = "O(log10(N))",
            description = "Count how many digits are in a positive integer n using recursion.",
            exampleInput = "n = 9876",
            exampleOutput = "4",
            keyInsight = "Base case: if n == 0, return 0 (or handle single digit if n < 10 return 1). Recursive step: return 1 + countDigits(n / 10).",
            solutionCode = """class Solution {
    public int countDigits(int n) {
        if (n < 10) return 1;
        return 1 + countDigits(n / 10);
    }
}"""
        ),
        DsaProblem(
            id = "rec_008",
            topic = "recursion",
            title = "Power of a Number (Pow(x, n))",
            difficulty = "Easy",
            pattern = "Divide and Conquer / Binary Exponentiation",
            timeComplexity = "O(log N)",
            spaceComplexity = "O(log N)",
            description = "Implement double myPow(double x, int n) which calculates x raised to the power n using recursive binary exponentiation.",
            exampleInput = "x = 2.0, n = 10",
            exampleOutput = "1024.0",
            keyInsight = "If n is even, x^n = (x^(n/2))^2. If n is odd, x^n = x * (x^(n/2))^2. Cut the exponent in half at each recursive call.",
            solutionCode = """class Solution {
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
        double half = fastPow(x, n / 2);
        if (n % 2 == 0) return half * half;
        else return half * half * x;
    }
}"""
        ),
        DsaProblem(
            id = "rec_009",
            topic = "recursion",
            title = "Greatest Common Divisor (Euclidean Algorithm)",
            difficulty = "Easy",
            pattern = "Euclidean Division Recursion",
            timeComplexity = "O(log(min(a, b)))",
            spaceComplexity = "O(log(min(a, b)))",
            description = "Compute the greatest common divisor (GCD) of two integers a and b using the recursive Euclidean algorithm.",
            exampleInput = "a = 48, b = 18",
            exampleOutput = "6",
            keyInsight = "Base case: if b == 0, gcd(a, 0) = a. Recursive case: gcd(a, b) = gcd(b, a % b).",
            solutionCode = """class Solution {
    public int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}"""
        ),
        DsaProblem(
            id = "rec_010",
            topic = "recursion",
            title = "Reverse an Array Recursively",
            difficulty = "Easy",
            pattern = "Two Pointers Recursive",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Reverse an array in-place using a recursive helper function with left and right indices.",
            exampleInput = "arr = [1, 2, 3, 4, 5]",
            exampleOutput = "[5, 4, 3, 2, 1]",
            keyInsight = "Swap arr[left] and arr[right], then recursively call reverse(arr, left + 1, right - 1) until left >= right.",
            solutionCode = """class Solution {
    public void reverseArray(int[] arr) {
        helper(arr, 0, arr.length - 1);
    }
    private void helper(int[] arr, int l, int r) {
        if (l >= r) return;
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
        helper(arr, l + 1, r - 1);
    }
}"""
        ),
        DsaProblem(
            id = "rec_011",
            topic = "recursion",
            title = "Check if Array is Sorted Recursively",
            difficulty = "Easy",
            pattern = "Linear Array Recursion",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given an array of integers, check whether it is sorted in non-decreasing order using recursion.",
            exampleInput = "arr = [2, 4, 6, 8, 10]",
            exampleOutput = "true",
            keyInsight = "If index == arr.length - 1, return true. If arr[i] > arr[i + 1], return false. Otherwise recurse on index + 1.",
            solutionCode = """class Solution {
    public boolean isSorted(int[] arr) {
        return helper(arr, 0);
    }
    private boolean helper(int[] arr, int i) {
        if (i >= arr.length - 1) return true;
        if (arr[i] > arr[i + 1]) return false;
        return helper(arr, i + 1);
    }
}"""
        ),
        DsaProblem(
            id = "rec_012",
            topic = "recursion",
            title = "Sum of Array Elements Recursively",
            difficulty = "Easy",
            pattern = "Divide and Conquer / Linear Recursion",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Find the sum of all elements in an integer array using recursion.",
            exampleInput = "arr = [1, 2, 3, 4, 5]",
            exampleOutput = "15",
            keyInsight = "Base case: if index == arr.length, return 0. Recursive case: return arr[index] + sum(arr, index + 1).",
            solutionCode = """class Solution {
    public int arraySum(int[] arr) {
        return sum(arr, 0);
    }
    private int sum(int[] arr, int i) {
        if (i == arr.length) return 0;
        return arr[i] + sum(arr, i + 1);
    }
}"""
        ),
        DsaProblem(
            id = "rec_013",
            topic = "recursion",
            title = "Find Max Element in Array Recursively",
            difficulty = "Easy",
            pattern = "Divide and Conquer / Linear Recursion",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Find the maximum element in an array using recursion without any loops.",
            exampleInput = "arr = [3, 9, 2, 14, 6]",
            exampleOutput = "14",
            keyInsight = "Base case: if i == arr.length - 1, return arr[i]. Recursive case: return Math.max(arr[i], findMax(arr, i + 1)).",
            solutionCode = """class Solution {
    public int findMax(int[] arr) {
        return helper(arr, 0);
    }
    private int helper(int[] arr, int i) {
        if (i == arr.length - 1) return arr[i];
        return Math.max(arr[i], helper(arr, i + 1));
    }
}"""
        ),
        DsaProblem(
            id = "rec_014",
            topic = "recursion",
            title = "Reverse a String Recursively",
            difficulty = "Easy",
            pattern = "String Shrinkage Recursion",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Reverse a given string s using recursion.",
            exampleInput = "s = \"hello\"",
            exampleOutput = "\"olleh\"",
            keyInsight = "Base case: if string is empty or length 1, return s. Recursive case: return reverse(s.substring(1)) + s.charAt(0).",
            solutionCode = """class Solution {
    public String reverse(String s) {
        if (s == null || s.length() <= 1) return s;
        return reverse(s.substring(1)) + s.charAt(0);
    }
}"""
        ),
        DsaProblem(
            id = "rec_015",
            topic = "recursion",
            title = "Check if String is Palindrome Recursively",
            difficulty = "Easy",
            pattern = "Two Pointers Recursive",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Determine if a string s is a palindrome using a recursive helper function comparing the first and last characters.",
            exampleInput = "s = \"racecar\"",
            exampleOutput = "true",
            keyInsight = "Base case: if left >= right, return true. If s.charAt(left) != s.charAt(right), return false. Else recurse on (left + 1, right - 1).",
            solutionCode = """class Solution {
    public boolean isPalindrome(String s) {
        return check(s, 0, s.length() - 1);
    }
    private boolean check(String s, int l, int r) {
        if (l >= r) return true;
        if (s.charAt(l) != s.charAt(r)) return false;
        return check(s, l + 1, r - 1);
    }
}"""
        ),
        DsaProblem(
            id = "rec_016",
            topic = "recursion",
            title = "Recursive Binary Search",
            difficulty = "Easy",
            pattern = "Divide and Conquer",
            timeComplexity = "O(log N)",
            spaceComplexity = "O(log N)",
            description = "Search for a target value in a sorted array using recursive binary search. Return index if found, else -1.",
            exampleInput = "nums = [-1, 0, 3, 5, 9, 12], target = 9",
            exampleOutput = "4",
            keyInsight = "Find mid. If nums[mid] == target, return mid. If target < nums[mid], search left half (low, mid - 1). Otherwise search right half (mid + 1, high).",
            solutionCode = """class Solution {
    public int search(int[] nums, int target) {
        return bSearch(nums, target, 0, nums.length - 1);
    }
    private int bSearch(int[] nums, int target, int low, int high) {
        if (low > high) return -1;
        int mid = low + (high - low) / 2;
        if (nums[mid] == target) return mid;
        if (target < nums[mid]) return bSearch(nums, target, low, mid - 1);
        return bSearch(nums, target, mid + 1, high);
    }
}"""
        ),
        DsaProblem(
            id = "rec_017",
            topic = "recursion",
            title = "Count Occurrences of a Character",
            difficulty = "Easy",
            pattern = "Linear String Traversal Recursion",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Count how many times a character target appears in a given string s using recursion.",
            exampleInput = "s = \"banana\", target = 'a'",
            exampleOutput = "3",
            keyInsight = "Base case: if index == s.length(), return 0. Add 1 if char matches current index, plus recursive count on remaining string.",
            solutionCode = """class Solution {
    public int countChar(String s, char target) {
        return count(s, target, 0);
    }
    private int count(String s, char ch, int i) {
        if (i == s.length()) return 0;
        int match = (s.charAt(i) == ch) ? 1 : 0;
        return match + count(s, ch, i + 1);
    }
}"""
        ),
        DsaProblem(
            id = "rec_018",
            topic = "recursion",
            title = "Remove All Occurrences of a Character",
            difficulty = "Easy",
            pattern = "String Filtering Recursion",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Return a new string where all occurrences of a target character ch are removed using recursion.",
            exampleInput = "s = \"abracadabra\", ch = 'a'",
            exampleOutput = "\"brcdbr\"",
            keyInsight = "Base case: if index == s.length(), return \"\". If current char == ch, skip it; otherwise prepend it to the result of recursing on i + 1.",
            solutionCode = """class Solution {
    public String removeChar(String s, char ch) {
        return filter(s, ch, 0);
    }
    private String filter(String s, char ch, int i) {
        if (i == s.length()) return "";
        char curr = s.charAt(i);
        String rest = filter(s, ch, i + 1);
        return (curr == ch) ? rest : curr + rest;
    }
}"""
        ),
        DsaProblem(
            id = "rec_019",
            topic = "recursion",
            title = "Tower of Hanoi",
            difficulty = "Medium",
            pattern = "Divide and Conquer Classical Recursion",
            timeComplexity = "O(2^N)",
            spaceComplexity = "O(N)",
            description = "Solve the classic Tower of Hanoi puzzle with n disks, moving all disks from rod A to rod C using rod B as auxiliary.",
            exampleInput = "n = 3, from = 'A', to = 'C', aux = 'B'",
            exampleOutput = "7 moves total: A->C, A->B, C->B, A->C, B->A, B->C, A->C",
            keyInsight = "1. Move n-1 disks from A to B using C. 2. Move disk n from A to C. 3. Move n-1 disks from B to C using A.",
            solutionCode = """class Solution {
    public void towerOfHanoi(int n, char from, char to, char aux) {
        if (n == 0) return;
        towerOfHanoi(n - 1, from, aux, to);
        System.out.println("Move disk " + n + " from " + from + " to " + to);
        towerOfHanoi(n - 1, aux, to, from);
    }
}"""
        ),
        DsaProblem(
            id = "rec_020",
            topic = "recursion",
            title = "Print All Subsequences of a String",
            difficulty = "Medium",
            pattern = "Pick / Don't Pick (Include / Exclude)",
            timeComplexity = "O(2^N)",
            spaceComplexity = "O(N)",
            description = "Given a string s, print all possible subsequences (including the empty string).",
            exampleInput = "s = \"abc\"",
            exampleOutput = "[\"\", \"c\", \"b\", \"bc\", \"a\", \"ac\", \"ab\", \"abc\"]",
            keyInsight = "At each index i, we have two choices: include s[i] into the current accumulated string, or do not include s[i]. Base case is when i == s.length().",
            solutionCode = """class Solution {
    public List<String> getSubsequences(String s) {
        List<String> result = new ArrayList<>();
        helper(s, 0, "", result);
        return result;
    }
    private void helper(String s, int i, String curr, List<String> res) {
        if (i == s.length()) {
            res.add(curr);
            return;
        }
        helper(s, i + 1, curr + s.charAt(i), res); // include
        helper(s, i + 1, curr, res);               // exclude
    }
}"""
        ),
        DsaProblem(
            id = "rec_021",
            topic = "recursion",
            title = "Subsets (Power Set)",
            difficulty = "Medium",
            pattern = "Backtracking / Pick & Don't Pick",
            timeComplexity = "O(N * 2^N)",
            spaceComplexity = "O(N)",
            description = "Given an integer array nums of unique elements, return all possible subsets (the power set). The solution set must not contain duplicate subsets.",
            exampleInput = "nums = [1, 2, 3]",
            exampleOutput = "[[], [1], [2], [1,2], [3], [1,3], [2,3], [1,2,3]]",
            keyInsight = "At each step, add the current subset to result. Then loop from currentIndex to end of array, add element, recurse, and backtrack by removing it.",
            solutionCode = """class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }
    private void backtrack(int[] nums, int start, List<Integer> current, List<List<Integer>> result) {
        result.add(new ArrayList<>(current));
        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);
            backtrack(nums, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
}"""
        ),
        DsaProblem(
            id = "rec_022",
            topic = "recursion",
            title = "Subsets II (With Duplicates)",
            difficulty = "Medium",
            pattern = "Backtracking with Duplicate Pruning",
            timeComplexity = "O(N * 2^N)",
            spaceComplexity = "O(N)",
            description = "Given an integer array nums that may contain duplicates, return all possible subsets without duplicate subsets in the output.",
            exampleInput = "nums = [1, 2, 2]",
            exampleOutput = "[[], [1], [1,2], [1,2,2], [2], [2,2]]",
            keyInsight = "Sort the array first. In the loop, if i > start and nums[i] == nums[i - 1], skip it to avoid generating duplicate branches at the same tree depth.",
            solutionCode = """class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }
    private void backtrack(int[] nums, int start, List<Integer> curr, List<List<Integer>> res) {
        res.add(new ArrayList<>(curr));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue;
            curr.add(nums[i]);
            backtrack(nums, i + 1, curr, res);
            curr.remove(curr.size() - 1);
        }
    }
}"""
        ),
        DsaProblem(
            id = "rec_023",
            topic = "recursion",
            title = "Combinations (n choose k)",
            difficulty = "Medium",
            pattern = "Backtracking / Combinations",
            timeComplexity = "O(k * C(n, k))",
            spaceComplexity = "O(k)",
            description = "Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].",
            exampleInput = "n = 4, k = 2",
            exampleOutput = "[[1,2], [1,3], [1,4], [2,3], [2,4], [3,4]]",
            keyInsight = "Backtrack from start to n. Base case: when current combination size reaches k, copy and add to result, then return.",
            solutionCode = """class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(1, n, k, new ArrayList<>(), result);
        return result;
    }
    private void backtrack(int start, int n, int k, List<Integer> curr, List<List<Integer>> res) {
        if (curr.size() == k) {
            res.add(new ArrayList<>(curr));
            return;
        }
        for (int i = start; i <= n; i++) {
            curr.add(i);
            backtrack(i + 1, n, k, curr, res);
            curr.remove(curr.size() - 1);
        }
    }
}"""
        ),
        DsaProblem(
            id = "rec_024",
            topic = "recursion",
            title = "Combination Sum",
            difficulty = "Medium",
            pattern = "Backtracking / Unbounded Multi-Pick",
            timeComplexity = "O(2^(target / min))",
            spaceComplexity = "O(target / min)",
            description = "Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations where candidate numbers sum to target. An element may be chosen unlimited times.",
            exampleInput = "candidates = [2, 3, 6, 7], target = 7",
            exampleOutput = "[[2, 2, 3], [7]]",
            keyInsight = "When an element is picked, stay at the same index i in the recursive call because elements can be reused. If target == 0, save combination.",
            solutionCode = """class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }
    private void backtrack(int[] cand, int remain, int start, List<Integer> curr, List<List<Integer>> res) {
        if (remain < 0) return;
        if (remain == 0) {
            res.add(new ArrayList<>(curr));
            return;
        }
        for (int i = start; i < cand.length; i++) {
            curr.add(cand[i]);
            backtrack(cand, remain - cand[i], i, curr, res);
            curr.remove(curr.size() - 1);
        }
    }
}"""
        ),
        DsaProblem(
            id = "rec_025",
            topic = "recursion",
            title = "Combination Sum II (Each Number Used Once)",
            difficulty = "Medium",
            pattern = "Backtracking with Sorting & Duplicate Pruning",
            timeComplexity = "O(2^N)",
            spaceComplexity = "O(N)",
            description = "Given a collection of candidate numbers (candidates) which may contain duplicates and a target, find all unique combinations where candidates sum to target. Each number may only be used once in the combination.",
            exampleInput = "candidates = [10, 1, 2, 7, 6, 1, 5], target = 8",
            exampleOutput = "[[1,1,6], [1,2,5], [1,7], [2,6]]",
            keyInsight = "Sort candidates first. Recurse with i + 1 (single use). Skip duplicates at the same level using if (i > start && cand[i] == cand[i - 1]) continue.",
            solutionCode = """class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }
    private void backtrack(int[] cand, int remain, int start, List<Integer> curr, List<List<Integer>> res) {
        if (remain < 0) return;
        if (remain == 0) {
            res.add(new ArrayList<>(curr));
            return;
        }
        for (int i = start; i < cand.length; i++) {
            if (i > start && cand[i] == cand[i - 1]) continue;
            curr.add(cand[i]);
            backtrack(cand, remain - cand[i], i + 1, curr, res);
            curr.remove(curr.size() - 1);
        }
    }
}"""
        ),
        DsaProblem(
            id = "rec_026",
            topic = "recursion",
            title = "Combination Sum III (k numbers summing to n)",
            difficulty = "Medium",
            pattern = "Constrained Backtracking",
            timeComplexity = "O(C(9, k))",
            spaceComplexity = "O(k)",
            description = "Find all valid combinations of k numbers that add up to n such that only numbers 1 through 9 are used and each number is used at most once.",
            exampleInput = "k = 3, n = 9",
            exampleOutput = "[[1, 2, 6], [1, 3, 5], [2, 3, 4]]",
            keyInsight = "Loop from start to 9. If curr.size() == k and remain == 0, add to result. Prune branches where curr.size() > k or remain < 0.",
            solutionCode = """class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(1, k, n, new ArrayList<>(), result);
        return result;
    }
    private void backtrack(int start, int k, int remain, List<Integer> curr, List<List<Integer>> res) {
        if (curr.size() == k && remain == 0) {
            res.add(new ArrayList<>(curr));
            return;
        }
        if (curr.size() > k || remain < 0) return;
        for (int i = start; i <= 9; i++) {
            curr.add(i);
            backtrack(i + 1, k, remain - i, curr, res);
            curr.remove(curr.size() - 1);
        }
    }
}"""
        ),
        DsaProblem(
            id = "rec_027",
            topic = "recursion",
            title = "Permutations",
            difficulty = "Medium",
            pattern = "Backtracking / Visited Array",
            timeComplexity = "O(N * N!)",
            spaceComplexity = "O(N)",
            description = "Given an array nums of distinct integers, return all the possible permutations in any order.",
            exampleInput = "nums = [1, 2, 3]",
            exampleOutput = "[[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]",
            keyInsight = "Maintain a boolean[] visited array. At each step, pick an unvisited number, mark it visited, recurse, and on backtrack unmark it and remove from path.",
            solutionCode = """class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, new boolean[nums.length], new ArrayList<>(), result);
        return result;
    }
    private void backtrack(int[] nums, boolean[] used, List<Integer> curr, List<List<Integer>> res) {
        if (curr.size() == nums.length) {
            res.add(new ArrayList<>(curr));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            used[i] = true;
            curr.add(nums[i]);
            backtrack(nums, used, curr, res);
            curr.remove(curr.size() - 1);
            used[i] = false;
        }
    }
}"""
        ),
        DsaProblem(
            id = "rec_028",
            topic = "recursion",
            title = "Permutations II (With Duplicates)",
            difficulty = "Medium",
            pattern = "Backtracking with Sorting & Order Constraint",
            timeComplexity = "O(N * N!)",
            spaceComplexity = "O(N)",
            description = "Given a collection of numbers nums that might contain duplicates, return all possible unique permutations in any order.",
            exampleInput = "nums = [1, 1, 2]",
            exampleOutput = "[[1,1,2], [1,2,1], [2,1,1]]",
            keyInsight = "Sort the array. If nums[i] == nums[i - 1] and !used[i - 1], skip to ensure duplicate elements are picked only in their original relative order.",
            solutionCode = """class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, new boolean[nums.length], new ArrayList<>(), result);
        return result;
    }
    private void backtrack(int[] nums, boolean[] used, List<Integer> curr, List<List<Integer>> res) {
        if (curr.size() == nums.length) {
            res.add(new ArrayList<>(curr));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            used[i] = true;
            curr.add(nums[i]);
            backtrack(nums, used, curr, res);
            curr.remove(curr.size() - 1);
            used[i] = false;
        }
    }
}"""
        ),
        DsaProblem(
            id = "rec_029",
            topic = "recursion",
            title = "Letter Combinations of a Phone Number",
            difficulty = "Medium",
            pattern = "Multi-way Branching Recursion",
            timeComplexity = "O(4^N)",
            spaceComplexity = "O(N)",
            description = "Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent based on telephone buttons.",
            exampleInput = "digits = \"23\"",
            exampleOutput = "[\"ad\",\"ae\",\"af\",\"bd\",\"be\",\"bf\",\"cd\",\"ce\",\"cf\"]",
            keyInsight = "Map each digit to its letters. Recurse through the digits: for digit index i, branch out for each character mapped to digits[i], appending to current path.",
            solutionCode = """class Solution {
    private final String[] mapping = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.isEmpty()) return result;
        backtrack(digits, 0, new StringBuilder(), result);
        return result;
    }
    private void backtrack(String digits, int idx, StringBuilder sb, List<String> res) {
        if (idx == digits.length()) {
            res.add(sb.toString());
            return;
        }
        String letters = mapping[digits.charAt(idx) - '0'];
        for (char c : letters.toCharArray()) {
            sb.append(c);
            backtrack(digits, idx + 1, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}"""
        ),
        DsaProblem(
            id = "rec_030",
            topic = "recursion",
            title = "Generate Parentheses",
            difficulty = "Medium",
            pattern = "Backtracking / Valid Prefix Constraint",
            timeComplexity = "O(4^N / sqrt(N)) Catalan number",
            spaceComplexity = "O(N)",
            description = "Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.",
            exampleInput = "n = 3",
            exampleOutput = "[\"((()))\",\"(()())\",\"(())()\",\"()(())\",\"()()()\"]",
            keyInsight = "Add '(' if openCount < n. Add ')' if closeCount < openCount. When string length reaches 2 * n, save valid combination.",
            solutionCode = """class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(n, 0, 0, new StringBuilder(), result);
        return result;
    }
    private void backtrack(int n, int open, int close, StringBuilder sb, List<String> res) {
        if (sb.length() == 2 * n) {
            res.add(sb.toString());
            return;
        }
        if (open < n) {
            sb.append('(');
            backtrack(n, open + 1, close, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (close < open) {
            sb.append(')');
            backtrack(n, open, close + 1, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}"""
        ),
        DsaProblem(
            id = "rec_031",
            topic = "recursion",
            title = "Palindrome Partitioning",
            difficulty = "Medium",
            pattern = "Backtracking / Partitioning",
            timeComplexity = "O(N * 2^N)",
            spaceComplexity = "O(N)",
            description = "Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitionings.",
            exampleInput = "s = \"aab\"",
            exampleOutput = "[[\"a\",\"a\",\"b\"], [\"aa\",\"b\"]]",
            keyInsight = "Try every split point from start to end. If s.substring(start, i + 1) is a palindrome, add to list and recurse on i + 1, then backtrack.",
            solutionCode = """class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }
    private void backtrack(String s, int start, List<String> curr, List<List<String>> res) {
        if (start == s.length()) {
            res.add(new ArrayList<>(curr));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (isPal(s, start, i)) {
                curr.add(s.substring(start, i + 1));
                backtrack(s, i + 1, curr, res);
                curr.remove(curr.size() - 1);
            }
        }
    }
    private boolean isPal(String s, int l, int r) {
        while (l < r) if (s.charAt(l++) != s.charAt(r--)) return false;
        return true;
    }
}"""
        ),
        DsaProblem(
            id = "rec_032",
            topic = "recursion",
            title = "Word Search (Grid Backtracking)",
            difficulty = "Medium",
            pattern = "2D Grid DFS / Backtracking",
            timeComplexity = "O(M * N * 3^L)",
            spaceComplexity = "O(L)",
            description = "Given an m x n grid of characters and a string word, return true if word exists in the grid. Letters can be horizontally or vertically adjacent.",
            exampleInput = "board = [[\"A\",\"B\",\"C\",\"E\"],[\"S\",\"F\",\"C\",\"S\"],[\"A\",\"D\",\"E\",\"E\"]], word = \"ABCCED\"",
            exampleOutput = "true",
            keyInsight = "For each cell matching word[0], run DFS. Mark cell visited by setting board[r][c] = '#', explore 4 directions, and restore board[r][c] on return.",
            solutionCode = """class Solution {
    public boolean exist(char[][] board, String word) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (dfs(board, word, r, c, 0)) return true;
            }
        }
        return false;
    }
    private boolean dfs(char[][] b, String w, int r, int c, int idx) {
        if (idx == w.length()) return true;
        if (r < 0 || r >= b.length || c < 0 || c >= b[0].length || b[r][c] != w.charAt(idx)) return false;
        char temp = b[r][c];
        b[r][c] = '#';
        boolean found = dfs(b, w, r + 1, c, idx + 1) || dfs(b, w, r - 1, c, idx + 1)
                     || dfs(b, w, r, c + 1, idx + 1) || dfs(b, w, r, c - 1, idx + 1);
        b[r][c] = temp;
        return found;
    }
}"""
        ),
        DsaProblem(
            id = "rec_033",
            topic = "recursion",
            title = "Flood Fill",
            difficulty = "Easy",
            pattern = "Recursive Connected Components / DFS",
            timeComplexity = "O(M * N)",
            spaceComplexity = "O(M * N)",
            description = "Perform flood fill on an image starting from pixel (sr, sc) with new color color.",
            exampleInput = "image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2",
            exampleOutput = "[[2,2,2],[2,2,0],[2,0,1]]",
            keyInsight = "If image[sr][sc] == color, return immediately to avoid infinite recursion. Recurse in 4 cardinal directions matching initial color.",
            solutionCode = """class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int orig = image[sr][sc];
        if (orig != color) dfs(image, sr, sc, orig, color);
        return image;
    }
    private void dfs(int[][] img, int r, int c, int orig, int color) {
        if (r < 0 || r >= img.length || c < 0 || c >= img[0].length || img[r][c] != orig) return;
        img[r][c] = color;
        dfs(img, r + 1, c, orig, color);
        dfs(img, r - 1, c, orig, color);
        dfs(img, r, c + 1, orig, color);
        dfs(img, r, c - 1, orig, color);
    }
}"""
        ),
        DsaProblem(
            id = "rec_034",
            topic = "recursion",
            title = "Number of Islands",
            difficulty = "Medium",
            pattern = "2D Grid DFS / Island Sinking",
            timeComplexity = "O(M * N)",
            spaceComplexity = "O(M * N)",
            description = "Given an m x n 2D binary grid representing a map of '1's (land) and '0's (water), count the number of islands.",
            exampleInput = "grid = [[\"1\",\"1\",\"0\"],[\"1\",\"1\",\"0\"],[\"0\",\"0\",\"1\"]]",
            exampleOutput = "2",
            keyInsight = "Loop over each cell. When a '1' is found, increment island count and trigger a recursive DFS sink helper that flips all connected '1's to '0's.",
            solutionCode = """class Solution {
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == '1') {
                    count++;
                    sink(grid, r, c);
                }
            }
        }
        return count;
    }
    private void sink(char[][] g, int r, int c) {
        if (r < 0 || r >= g.length || c < 0 || c >= g[0].length || g[r][c] != '1') return;
        g[r][c] = '0';
        sink(g, r + 1, c);
        sink(g, r - 1, c);
        sink(g, r, c + 1);
        sink(g, r, c - 1);
    }
}"""
        ),
        DsaProblem(
            id = "rec_035",
            topic = "recursion",
            title = "Max Area of Island",
            difficulty = "Medium",
            pattern = "Grid DFS with Accumulation",
            timeComplexity = "O(M * N)",
            spaceComplexity = "O(M * N)",
            description = "Return the maximum area of an island in a binary grid. An island is surrounded by water and formed by connecting adjacent lands horizontally or vertically.",
            exampleInput = "grid = [[0,1],[1,1]]",
            exampleOutput = "3",
            keyInsight = "DFS returns 1 + sum of areas from 4 directions. Mark visited cells as 0 so they are not recounted.",
            solutionCode = """class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) max = Math.max(max, area(grid, r, c));
            }
        }
        return max;
    }
    private int area(int[][] g, int r, int c) {
        if (r < 0 || r >= g.length || c < 0 || c >= g[0].length || g[r][c] == 0) return 0;
        g[r][c] = 0;
        return 1 + area(g, r + 1, c) + area(g, r - 1, c) + area(g, r, c + 1) + area(g, r, c - 1);
    }
}"""
        ),
        DsaProblem(
            id = "rec_036",
            topic = "recursion",
            title = "Restore IP Addresses",
            difficulty = "Medium",
            pattern = "Backtracking / Partitioning",
            timeComplexity = "O(3^4) = constant",
            spaceComplexity = "O(1)",
            description = "Given a string s containing only digits, return all possible valid IPv4 addresses that can be formed by inserting dots into s.",
            exampleInput = "s = \"25525511135\"",
            exampleOutput = "[\"255.255.11.135\", \"255.255.111.35\"]",
            keyInsight = "Track segment index (0 to 3). For each segment, take length 1 to 3 digits. Validate: no leading zero unless value is 0, and value <= 255.",
            solutionCode = """class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        backtrack(s, 0, 0, "", result);
        return result;
    }
    private void backtrack(String s, int idx, int dots, String curr, List<String> res) {
        if (dots == 4 && idx == s.length()) {
            res.add(curr.substring(0, curr.length() - 1));
            return;
        }
        if (dots > 4) return;
        for (int len = 1; len <= 3 && idx + len <= s.length(); len++) {
            String part = s.substring(idx, idx + len);
            if ((part.startsWith("0") && part.length() > 1) || Integer.parseInt(part) > 255) break;
            backtrack(s, idx + len, dots + 1, curr + part + ".", res);
        }
    }
}"""
        ),
        DsaProblem(
            id = "rec_037",
            topic = "recursion",
            title = "Subset Sum Problem",
            difficulty = "Medium",
            pattern = "Pick / Don't Pick Recursion with Memoization",
            timeComplexity = "O(N * Target)",
            spaceComplexity = "O(N * Target)",
            description = "Given an array of non-negative integers nums and a value sum, determine if there exists a subset with sum equal to sum.",
            exampleInput = "nums = [3, 34, 4, 12, 5, 2], sum = 9",
            exampleOutput = "true (4 + 5 = 9)",
            keyInsight = "At element i: choice 1 is include nums[i] (target becomes sum - nums[i]), choice 2 is exclude nums[i]. Return true if either branch succeeds.",
            solutionCode = """class Solution {
    public boolean isSubsetSum(int[] nums, int sum) {
        Boolean[][] memo = new Boolean[nums.length][sum + 1];
        return canSum(nums, 0, sum, memo);
    }
    private boolean canSum(int[] nums, int i, int rem, Boolean[][] memo) {
        if (rem == 0) return true;
        if (i >= nums.length || rem < 0) return false;
        if (memo[i][rem] != null) return memo[i][rem];
        return memo[i][rem] = canSum(nums, i + 1, rem - nums[i], memo) || canSum(nums, i + 1, rem, memo);
    }
}"""
        ),
        DsaProblem(
            id = "rec_038",
            topic = "recursion",
            title = "Partition Equal Subset Sum",
            difficulty = "Medium",
            pattern = "0/1 Knapsack / Subset Sum Reduction",
            timeComplexity = "O(N * Sum/2)",
            spaceComplexity = "O(Sum/2)",
            description = "Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal.",
            exampleInput = "nums = [1, 5, 11, 5]",
            exampleOutput = "true (sum = 22, target subset = 11)",
            keyInsight = "If total sum is odd, return false. Otherwise, find if there is a subset summing to total / 2 using 0/1 Knapsack recursion.",
            solutionCode = """class Solution {
    public boolean canPartition(int[] nums) {
        int total = 0;
        for (int n : nums) total += n;
        if (total % 2 != 0) return false;
        int target = total / 2;
        Boolean[][] memo = new Boolean[nums.length][target + 1];
        return dfs(nums, 0, target, memo);
    }
    private boolean dfs(int[] nums, int i, int rem, Boolean[][] memo) {
        if (rem == 0) return true;
        if (i >= nums.length || rem < 0) return false;
        if (memo[i][rem] != null) return memo[i][rem];
        return memo[i][rem] = dfs(nums, i + 1, rem - nums[i], memo) || dfs(nums, i + 1, rem, memo);
    }
}"""
        ),
        DsaProblem(
            id = "rec_039",
            topic = "recursion",
            title = "Target Sum (Assign + or -)",
            difficulty = "Medium",
            pattern = "Binary Choice Tree Recursion",
            timeComplexity = "O(2^N) or O(N * Sum) with memo",
            spaceComplexity = "O(N)",
            description = "You are given an integer array nums and an integer target. Build an expression out of nums by adding '+' or '-' before each integer. Find number of ways to achieve target.",
            exampleInput = "nums = [1, 1, 1, 1, 1], target = 3",
            exampleOutput = "5",
            keyInsight = "At index i, recursively branch: add nums[i] or subtract nums[i]. When i == nums.length, return 1 if currentSum == target, else 0.",
            solutionCode = """class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        Map<String, Integer> memo = new HashMap<>();
        return ways(nums, 0, 0, target, memo);
    }
    private int ways(int[] nums, int i, int current, int target, Map<String, Integer> memo) {
        if (i == nums.length) return current == target ? 1 : 0;
        String key = i + "," + current;
        if (memo.containsKey(key)) return memo.get(key);
        int add = ways(nums, i + 1, current + nums[i], target, memo);
        int sub = ways(nums, i + 1, current - nums[i], target, memo);
        memo.put(key, add + sub);
        return add + sub;
    }
}"""
        ),
        DsaProblem(
            id = "rec_040",
            topic = "recursion",
            title = "Rat in a Maze",
            difficulty = "Medium",
            pattern = "Backtracking / Path Finding",
            timeComplexity = "O(4^(N^2))",
            spaceComplexity = "O(N^2)",
            description = "Find all paths a rat can take to reach from (0,0) to (n-1, n-1) in an N x N matrix with 1s (open) and 0s (blocked). Possible moves: D, L, R, U in alphabetical order.",
            exampleInput = "maze = [[1,0,0,0],[1,1,0,1],[1,1,0,0],[0,1,1,1]]",
            exampleOutput = "[\"DDRDRR\", \"DRDDRR\"]",
            keyInsight = "Mark cell visited before moving. Try moves: Down ('D'), Left ('L'), Right ('R'), Up ('U'). Unmark cell when backtracking.",
            solutionCode = """class Solution {
    public List<String> findPath(int[][] m, int n) {
        List<String> result = new ArrayList<>();
        if (m[0][0] == 1) solve(m, 0, 0, n, "", result);
        return result;
    }
    private void solve(int[][] m, int r, int c, int n, String path, List<String> res) {
        if (r == n - 1 && c == n - 1) {
            res.add(path);
            return;
        }
        m[r][c] = 0; // mark visited
        if (r + 1 < n && m[r + 1][c] == 1) solve(m, r + 1, c, n, path + "D", res);
        if (c - 1 >= 0 && m[r][c - 1] == 1) solve(m, r, c - 1, n, path + "L", res);
        if (c + 1 < n && m[r][c + 1] == 1) solve(m, r, c + 1, n, path + "R", res);
        if (r - 1 >= 0 && m[r - 1][c] == 1) solve(m, r - 1, c, n, path + "U", res);
        m[r][c] = 1; // unmark
    }
}"""
        ),
        DsaProblem(
            id = "rec_041",
            topic = "recursion",
            title = "N-Queens",
            difficulty = "Hard",
            pattern = "Classical Constraint Satisfaction Backtracking",
            timeComplexity = "O(N!)",
            spaceComplexity = "O(N)",
            description = "Place n queens on an n x n chessboard such that no two queens attack each other. Return all distinct board configurations.",
            exampleInput = "n = 4",
            exampleOutput = "[[.Q.., ...Q, Q..., ..Q.], [..Q., Q..., ...Q, .Q..]]",
            keyInsight = "Place one queen per row. Maintain sets for used columns, main diagonals (r - c), and anti-diagonals (r + c) to validate placement in O(1).",
            solutionCode = """class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] row : board) Arrays.fill(row, '.');
        boolean[] cols = new boolean[n];
        boolean[] d1 = new boolean[2 * n];
        boolean[] d2 = new boolean[2 * n];
        place(0, n, board, cols, d1, d2, result);
        return result;
    }
    private void place(int r, int n, char[][] b, boolean[] cols, boolean[] d1, boolean[] d2, List<List<String>> res) {
        if (r == n) {
            List<String> config = new ArrayList<>();
            for (char[] row : b) config.add(new String(row));
            res.add(config);
            return;
        }
        for (int c = 0; c < n; c++) {
            int id1 = r - c + n, id2 = r + c;
            if (cols[c] || d1[id1] || d2[id2]) continue;
            b[r][c] = 'Q';
            cols[c] = d1[id1] = d2[id2] = true;
            place(r + 1, n, b, cols, d1, d2, res);
            b[r][c] = '.';
            cols[c] = d1[id1] = d2[id2] = false;
        }
    }
}"""
        ),
        DsaProblem(
            id = "rec_042",
            topic = "recursion",
            title = "N-Queens II (Total Distinct Solutions)",
            difficulty = "Hard",
            pattern = "Count-Only Backtracking",
            timeComplexity = "O(N!)",
            spaceComplexity = "O(N)",
            description = "Return the total number of distinct solutions to the n-queens puzzle.",
            exampleInput = "n = 4",
            exampleOutput = "2",
            keyInsight = "Same backtracking logic as N-Queens I, but return an integer count directly instead of constructing string boards.",
            solutionCode = """class Solution {
    private int count = 0;
    public int totalNQueens(int n) {
        boolean[] cols = new boolean[n];
        boolean[] d1 = new boolean[2 * n];
        boolean[] d2 = new boolean[2 * n];
        solve(0, n, cols, d1, d2);
        return count;
    }
    private void solve(int r, int n, boolean[] cols, boolean[] d1, boolean[] d2) {
        if (r == n) { count++; return; }
        for (int c = 0; c < n; c++) {
            int id1 = r - c + n, id2 = r + c;
            if (cols[c] || d1[id1] || d2[id2]) continue;
            cols[c] = d1[id1] = d2[id2] = true;
            solve(r + 1, n, cols, d1, d2);
            cols[c] = d1[id1] = d2[id2] = false;
        }
    }
}"""
        ),
        DsaProblem(
            id = "rec_043",
            topic = "recursion",
            title = "Sudoku Solver",
            difficulty = "Hard",
            pattern = "Backtracking / 9x9 Grid Filling",
            timeComplexity = "O(9^(empty cells))",
            spaceComplexity = "O(1)",
            description = "Write a program to solve a Sudoku puzzle by filling empty cells ('.') with digits '1'-'9' adhering to Sudoku rules.",
            exampleInput = "9x9 board with partially filled digits",
            exampleOutput = "Completed valid Sudoku board",
            keyInsight = "Scan for next empty cell. Try digits '1'-'9'. If valid in row, col, and 3x3 box, place digit and recurse. If downstream fails, reset cell to '.' and backtrack.",
            solutionCode = """class Solution {
    public void solveSudoku(char[][] board) {
        solve(board);
    }
    private boolean solve(char[][] b) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (b[r][c] == '.') {
                    for (char ch = '1'; ch <= '9'; ch++) {
                        if (isValid(b, r, c, ch)) {
                            b[r][c] = ch;
                            if (solve(b)) return true;
                            b[r][c] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    private boolean isValid(char[][] b, int r, int c, char ch) {
        for (int i = 0; i < 9; i++) {
            if (b[r][i] == ch || b[i][c] == ch) return false;
            int boxR = 3 * (r / 3) + i / 3, boxC = 3 * (c / 3) + i % 3;
            if (b[boxR][boxC] == ch) return false;
        }
        return true;
    }
}"""
        ),
        DsaProblem(
            id = "rec_044",
            topic = "recursion",
            title = "Word Break II",
            difficulty = "Hard",
            pattern = "Backtracking with Memoization",
            timeComplexity = "O(2^N)",
            spaceComplexity = "O(2^N)",
            description = "Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is in wordDict. Return all sentences.",
            exampleInput = "s = \"catsanddog\", wordDict = [\"cat\",\"cats\",\"and\",\"sand\",\"dog\"]",
            exampleOutput = "[\"cats and dog\", \"cat sand dog\"]",
            keyInsight = "Memoize Map<String, List<String>>. For each prefix in wordDict, recursively solve the suffix and prepend the prefix to every returned suffix sentence.",
            solutionCode = """class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        return dfs(s, set, new HashMap<>());
    }
    private List<String> dfs(String s, Set<String> dict, Map<String, List<String>> memo) {
        if (memo.containsKey(s)) return memo.get(s);
        List<String> res = new ArrayList<>();
        if (s.isEmpty()) { res.add(""); return res; }
        for (String word : dict) {
            if (s.startsWith(word)) {
                List<String> sub = dfs(s.substring(word.length()), dict, memo);
                for (String sentence : sub) {
                    res.add(word + (sentence.isEmpty() ? "" : " " + sentence));
                }
            }
        }
        memo.put(s, res);
        return res;
    }
}"""
        ),
        DsaProblem(
            id = "rec_045",
            topic = "recursion",
            title = "Expression Add Operators",
            difficulty = "Hard",
            pattern = "Backtracking / Expression Parsing",
            timeComplexity = "O(4^N)",
            spaceComplexity = "O(N)",
            description = "Given a string num containing only digits and an integer target, return all possibilities to insert binary operators '+', '-', and '*' between digits so result equals target.",
            exampleInput = "num = \"232\", target = 8",
            exampleOutput = "[\"2*3+2\", \"2+3*2\"]",
            keyInsight = "Track current value and previous multiplied operand. For '*', revert previous operation: (curr - prev) + (prev * val) and pass prev * val to next frame.",
            solutionCode = """class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        dfs(num, target, 0, 0, 0, "", res);
        return res;
    }
    private void dfs(String num, int target, int idx, long val, long prev, String expr, List<String> res) {
        if (idx == num.length()) {
            if (val == target) res.add(expr);
            return;
        }
        for (int i = idx; i < num.length(); i++) {
            if (i > idx && num.charAt(idx) == '0') break;
            long curr = Long.parseLong(num.substring(idx, i + 1));
            if (idx == 0) {
                dfs(num, target, i + 1, curr, curr, "" + curr, res);
            } else {
                dfs(num, target, i + 1, val + curr, curr, expr + "+" + curr, res);
                dfs(num, target, i + 1, val - curr, -curr, expr + "-" + curr, res);
                dfs(num, target, i + 1, val - prev + prev * curr, prev * curr, expr + "*" + curr, res);
            }
        }
    }
}"""
        ),
        DsaProblem(
            id = "rec_046",
            topic = "recursion",
            title = "Climbing Stairs",
            difficulty = "Easy",
            pattern = "Linear Recurrence / Fibonacci",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "You are climbing a staircase. It takes n steps to reach the top. Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?",
            exampleInput = "n = 3",
            exampleOutput = "3 (1+1+1, 1+2, 2+1)",
            keyInsight = "Base cases: climb(1) = 1, climb(2) = 2. Recursive step: ways(n) = ways(n - 1) + ways(n - 2). Use memoization array to run in O(N).",
            solutionCode = """class Solution {
    public int climbStairs(int n) {
        int[] memo = new int[n + 1];
        return climb(n, memo);
    }
    private int climb(int n, int[] memo) {
        if (n <= 2) return n;
        if (memo[n] != 0) return memo[n];
        return memo[n] = climb(n - 1, memo) + climb(n - 2, memo);
    }
}"""
        ),
        DsaProblem(
            id = "rec_047",
            topic = "recursion",
            title = "Tribonacci Number",
            difficulty = "Easy",
            pattern = "Three-Term Recurrence",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "The Tribonacci sequence Tn is defined as: T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0. Compute Tn recursively with memoization.",
            exampleInput = "n = 4",
            exampleOutput = "4 (0 + 1 + 1 + 2 = 4)",
            keyInsight = "Store subproblem results in a memo array. Return T(n-1) + T(n-2) + T(n-3).",
            solutionCode = """class Solution {
    public int tribonacci(int n) {
        int[] memo = new int[n + 1];
        return helper(n, memo);
    }
    private int helper(int n, int[] memo) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        if (memo[n] != 0) return memo[n];
        return memo[n] = helper(n - 1, memo) + helper(n - 2, memo) + helper(n - 3, memo);
    }
}"""
        ),
        DsaProblem(
            id = "rec_048",
            topic = "recursion",
            title = "Min Cost Climbing Stairs",
            difficulty = "Easy",
            pattern = "Recursive Optimization with Memoization",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given an integer array cost where cost[i] is the cost of ith step, find minimum cost to reach top starting from step 0 or step 1.",
            exampleInput = "cost = [10, 15, 20]",
            exampleOutput = "15",
            keyInsight = "minCost(i) = cost[i] + min(minCost(i + 1), minCost(i + 2)). Return min(minCost(0), minCost(1)).",
            solutionCode = """class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int[] memo = new int[cost.length];
        return Math.min(minCost(cost, 0, memo), minCost(cost, 1, memo));
    }
    private int minCost(int[] cost, int i, int[] memo) {
        if (i >= cost.length) return 0;
        if (memo[i] != 0) return memo[i];
        return memo[i] = cost[i] + Math.min(minCost(cost, i + 1, memo), minCost(cost, i + 2, memo));
    }
}"""
        ),
        DsaProblem(
            id = "rec_049",
            topic = "recursion",
            title = "House Robber",
            difficulty = "Medium",
            pattern = "Choose or Skip (Pick / Don't Pick)",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Rob adjacent houses to maximize stolen money without alerting police. Cannot rob two adjacent houses.",
            exampleInput = "nums = [2, 7, 9, 3, 1]",
            exampleOutput = "12 (Rob house 1, 3, 5: 2 + 9 + 1 = 12)",
            keyInsight = "At house i: either rob it (nums[i] + rob(i + 2)) or skip it (rob(i + 1)). Take maximum of the two choices.",
            solutionCode = """class Solution {
    public int rob(int[] nums) {
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return solve(nums, 0, memo);
    }
    private int solve(int[] nums, int i, int[] memo) {
        if (i >= nums.length) return 0;
        if (memo[i] != -1) return memo[i];
        int steal = nums[i] + solve(nums, i + 2, memo);
        int skip = solve(nums, i + 1, memo);
        return memo[i] = Math.max(steal, skip);
    }
}"""
        ),
        DsaProblem(
            id = "rec_050",
            topic = "recursion",
            title = "Decode Ways",
            difficulty = "Medium",
            pattern = "Prefix Choice Recursion with Memoization",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "A message containing letters from A-Z can be encoded into numbers using 'A' -> \"1\", 'B' -> \"2\", ... 'Z' -> \"26\". Given a string s of digits, return number of ways to decode it.",
            exampleInput = "s = \"226\"",
            exampleOutput = "3 (\"BZ\", \"VF\", \"BBF\")",
            keyInsight = "If s[i] == '0', return 0. Choice 1: take 1 digit and recurse on i + 1. Choice 2: if next 2 digits <= 26, recurse on i + 2. Sum the results.",
            solutionCode = """class Solution {
    public int numDecodings(String s) {
        int[] memo = new int[s.length()];
        Arrays.fill(memo, -1);
        return decode(s, 0, memo);
    }
    private int decode(String s, int i, int[] memo) {
        if (i == s.length()) return 1;
        if (s.charAt(i) == '0') return 0;
        if (memo[i] != -1) return memo[i];
        int ways = decode(s, i + 1, memo);
        if (i + 1 < s.length()) {
            int twoDigit = Integer.parseInt(s.substring(i, i + 2));
            if (twoDigit <= 26) ways += decode(s, i + 2, memo);
        }
        return memo[i] = ways;
    }
}"""
        ),
        DsaProblem(
            id = "rec_051",
            topic = "recursion",
            title = "Maximum Depth of Binary Tree",
            difficulty = "Easy",
            pattern = "Tree DFS / Divide and Conquer",
            timeComplexity = "O(N)",
            spaceComplexity = "O(H)",
            description = "Given the root of a binary tree, return its maximum depth (number of nodes along longest path from root to farthest leaf).",
            exampleInput = "root = [3,9,20,null,null,15,7]",
            exampleOutput = "3",
            keyInsight = "If root is null, depth is 0. Otherwise, depth is 1 + max(maxDepth(left), maxDepth(right)).",
            solutionCode = """class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}"""
        ),
        DsaProblem(
            id = "rec_052",
            topic = "recursion",
            title = "Invert Binary Tree",
            difficulty = "Easy",
            pattern = "Tree Recursion / Node Swapping",
            timeComplexity = "O(N)",
            spaceComplexity = "O(H)",
            description = "Given the root of a binary tree, invert the tree (swap left and right children of every node) and return its root.",
            exampleInput = "root = [4,2,7,1,3,6,9]",
            exampleOutput = "[4,7,2,9,6,3,1]",
            keyInsight = "Base case: if root == null, return null. Swap root.left and root.right, then recursively invert both subtrees.",
            solutionCode = """class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode temp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(temp);
        return root;
    }
}"""
        ),
        DsaProblem(
            id = "rec_053",
            topic = "recursion",
            title = "Same Tree",
            difficulty = "Easy",
            pattern = "Simultaneous Tree Traversal Recursion",
            timeComplexity = "O(N)",
            spaceComplexity = "O(H)",
            description = "Given the roots of two binary trees p and q, write a function to check if they are the same or structurally identical.",
            exampleInput = "p = [1,2,3], q = [1,2,3]",
            exampleOutput = "true",
            keyInsight = "If both null, true. If one null or values differ, false. Return isSameTree(p.left, q.left) && isSameTree(p.right, q.right).",
            solutionCode = """class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null || p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}"""
        ),
        DsaProblem(
            id = "rec_054",
            topic = "recursion",
            title = "Symmetric Tree (Mirror Reflection)",
            difficulty = "Easy",
            pattern = "Simultaneous Dual Recursion",
            timeComplexity = "O(N)",
            spaceComplexity = "O(H)",
            description = "Given the root of a binary tree, check whether it is a mirror of itself (symmetric around its center).",
            exampleInput = "root = [1,2,2,3,4,4,3]",
            exampleOutput = "true",
            keyInsight = "Helper isMirror(t1, t2): compare t1.val == t2.val, and recursively verify isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left).",
            solutionCode = """class Solution {
    public boolean isSymmetric(TreeNode root) {
        return root == null || isMirror(root.left, root.right);
    }
    private boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null || t1.val != t2.val) return false;
        return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }
}"""
        ),
        DsaProblem(
            id = "rec_055",
            topic = "recursion",
            title = "Path Sum (Root-to-Leaf)",
            difficulty = "Easy",
            pattern = "Tree DFS / Target Subtraction",
            timeComplexity = "O(N)",
            spaceComplexity = "O(H)",
            description = "Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path where node values sum to targetSum.",
            exampleInput = "root = [5,4,8,11,null,13,4,7,2], targetSum = 22",
            exampleOutput = "true (5 + 4 + 11 + 2 = 22)",
            keyInsight = "If leaf node (left == null && right == null), check if root.val == targetSum. Otherwise recurse left and right with targetSum - root.val.",
            solutionCode = """class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return root.val == targetSum;
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }
}"""
        ),
        DsaProblem(
            id = "rec_056",
            topic = "recursion",
            title = "Path Sum II (Find All Paths)",
            difficulty = "Medium",
            pattern = "Tree Backtracking / Path Collection",
            timeComplexity = "O(N^2)",
            spaceComplexity = "O(H)",
            description = "Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node values equals targetSum.",
            exampleInput = "root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22",
            exampleOutput = "[[5,4,11,2], [5,8,4,5]]",
            keyInsight = "Add root.val to current list. If leaf and sum matches, add copy of list to result. Recurse left and right. Backtrack by removing root.val on return.",
            solutionCode = """class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(root, targetSum, new ArrayList<>(), result);
        return result;
    }
    private void backtrack(TreeNode node, int sum, List<Integer> curr, List<List<Integer>> res) {
        if (node == null) return;
        curr.add(node.val);
        if (node.left == null && node.right == null && node.val == sum) {
            res.add(new ArrayList<>(curr));
        } else {
            backtrack(node.left, sum - node.val, curr, res);
            backtrack(node.right, sum - node.val, curr, res);
        }
        curr.remove(curr.size() - 1);
    }
}"""
        ),
        DsaProblem(
            id = "rec_057",
            topic = "recursion",
            title = "Lowest Common Ancestor of a Binary Search Tree",
            difficulty = "Medium",
            pattern = "BST Property Exploitation Recursion",
            timeComplexity = "O(H)",
            spaceComplexity = "O(H)",
            description = "Find the lowest common ancestor (LCA) node of two given nodes p and q in a Binary Search Tree (BST).",
            exampleInput = "root = [6,2,8,0,4,7,9], p = 2, q = 8",
            exampleOutput = "6",
            keyInsight = "If both p and q are smaller than root, LCA is in left subtree. If both larger, LCA is in right subtree. If they diverge, root is LCA.",
            solutionCode = """class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val < root.val && q.val < root.val) return lowestCommonAncestor(root.left, p, q);
        if (p.val > root.val && q.val > root.val) return lowestCommonAncestor(root.right, p, q);
        return root;
    }
}"""
        ),
        DsaProblem(
            id = "rec_058",
            topic = "recursion",
            title = "Lowest Common Ancestor of a Binary Tree",
            difficulty = "Medium",
            pattern = "Binary Tree Bottom-Up Recursion",
            timeComplexity = "O(N)",
            spaceComplexity = "O(H)",
            description = "Given a binary tree (not BST), find the lowest common ancestor (LCA) of two given nodes p and q.",
            exampleInput = "root = [3,5,1,6,2,0,8], p = 5, q = 1",
            exampleOutput = "3",
            keyInsight = "If root is null or root == p or root == q, return root. Recurse left and right. If both return non-null, root is LCA. If only one non-null, return that one.",
            solutionCode = """class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        return left != null ? left : right;
    }
}"""
        ),
        DsaProblem(
            id = "rec_059",
            topic = "recursion",
            title = "Validate Binary Search Tree",
            difficulty = "Medium",
            pattern = "Range Constrained Recursion",
            timeComplexity = "O(N)",
            spaceComplexity = "O(H)",
            description = "Given the root of a binary tree, determine if it is a valid binary search tree (BST).",
            exampleInput = "root = [2,1,3]",
            exampleOutput = "true",
            keyInsight = "Pass valid min and max bounds down the tree: check(node, min, max). For left child, max becomes node.val. For right child, min becomes node.val.",
            solutionCode = """class Solution {
    public boolean isValidBST(TreeNode root) {
        return validate(root, null, null);
    }
    private boolean validate(TreeNode node, Integer min, Integer max) {
        if (node == null) return true;
        if ((min != null && node.val <= min) || (max != null && node.val >= max)) return false;
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }
}"""
        ),
        DsaProblem(
            id = "rec_060",
            topic = "recursion",
            title = "Merge Two Binary Trees",
            difficulty = "Easy",
            pattern = "Simultaneous Tree Construction",
            timeComplexity = "O(N)",
            spaceComplexity = "O(H)",
            description = "Imagine putting two binary trees on top of each other. Sum node values where they overlap. Return the merged tree root.",
            exampleInput = "root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]",
            exampleOutput = "[3,4,5,5,4,null,7]",
            keyInsight = "If root1 is null, return root2. If root2 is null, return root1. Merge: root1.val += root2.val, then merge left and right subtrees recursively.",
            solutionCode = """class Solution {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        root1.val += root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
    }
}"""
        ),
        DsaProblem(
            id = "rec_061",
            topic = "recursion",
            title = "Merge Sort Algorithm",
            difficulty = "Medium",
            pattern = "Divide and Conquer",
            timeComplexity = "O(N log N)",
            spaceComplexity = "O(N)",
            description = "Implement merge sort recursively by dividing the array into two halves, sorting them, and merging the sorted halves.",
            exampleInput = "arr = [38, 27, 43, 3, 9, 82, 10]",
            exampleOutput = "[3, 9, 10, 27, 38, 43, 82]",
            keyInsight = "Divide: find mid = (l + r) / 2. Conquer: mergeSort(l, mid) and mergeSort(mid + 1, r). Combine: merge the two sorted halves in O(N).",
            solutionCode = """class Solution {
    public void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }
    private void merge(int[] arr, int l, int m, int r) {
        int[] temp = new int[r - l + 1];
        int i = l, j = m + 1, k = 0;
        while (i <= m && j <= r) temp[k++] = (arr[i] <= arr[j]) ? arr[i++] : arr[j++];
        while (i <= m) temp[k++] = arr[i++];
        while (j <= r) temp[k++] = arr[j++];
        System.arraycopy(temp, 0, arr, l, temp.length);
    }
}"""
        ),
        DsaProblem(
            id = "rec_062",
            topic = "recursion",
            title = "Quick Sort Algorithm",
            difficulty = "Medium",
            pattern = "Divide and Conquer / Partitioning",
            timeComplexity = "O(N log N) avg, O(N^2) worst",
            spaceComplexity = "O(log N)",
            description = "Implement quick sort recursively by choosing a pivot, partitioning elements around it, and recursing on left and right partitions.",
            exampleInput = "arr = [10, 7, 8, 9, 1, 5]",
            exampleOutput = "[1, 5, 7, 8, 9, 10]",
            keyInsight = "Partition places pivot in its correct sorted position p. Recursively quickSort(low, p - 1) and quickSort(p + 1, high).",
            solutionCode = """class Solution {
    public void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high], i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int t = arr[i]; arr[i] = arr[j]; arr[j] = t;
            }
        }
        int t = arr[i + 1]; arr[i + 1] = arr[high]; arr[high] = t;
        return i + 1;
    }
}"""
        ),
        DsaProblem(
            id = "rec_063",
            topic = "recursion",
            title = "Quickselect (Kth Largest Element)",
            difficulty = "Medium",
            pattern = "Divide and Conquer / Selection",
            timeComplexity = "O(N) avg, O(N^2) worst",
            spaceComplexity = "O(1)",
            description = "Find the kth largest element in an unsorted array using recursive quickselect.",
            exampleInput = "nums = [3,2,1,5,6,4], k = 2",
            exampleOutput = "5",
            keyInsight = "Target index is nums.length - k in sorted order. Partition array around pivot: if pivot index == target, return it; otherwise recurse only into the matching half.",
            solutionCode = """class Solution {
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }
    private int quickSelect(int[] nums, int l, int r, int k) {
        int pi = partition(nums, l, r);
        if (pi == k) return nums[pi];
        if (pi < k) return quickSelect(nums, pi + 1, r, k);
        return quickSelect(nums, l, pi - 1, k);
    }
    private int partition(int[] nums, int l, int r) {
        int pivot = nums[r], i = l;
        for (int j = l; j < r; j++) {
            if (nums[j] <= pivot) {
                int t = nums[i]; nums[i] = nums[j]; nums[j] = t;
                i++;
            }
        }
        int t = nums[i]; nums[i] = nums[r]; nums[r] = t;
        return i;
    }
}"""
        ),
        DsaProblem(
            id = "rec_064",
            topic = "recursion",
            title = "Inversion Count in an Array",
            difficulty = "Medium",
            pattern = "Enhanced Merge Sort",
            timeComplexity = "O(N log N)",
            spaceComplexity = "O(N)",
            description = "Count the number of inversions in an array (pairs (i, j) such that i < j and arr[i] > arr[j]) using modified merge sort.",
            exampleInput = "arr = [8, 4, 2, 1]",
            exampleOutput = "6",
            keyInsight = "During merge, if arr[i] > arr[j], then all remaining elements from i to mid in left half are also greater than arr[j], adding (mid - i + 1) inversions.",
            solutionCode = """class Solution {
    public long countInversions(int[] arr) {
        return mergeSort(arr, 0, arr.length - 1);
    }
    private long mergeSort(int[] arr, int l, int r) {
        long inv = 0;
        if (l < r) {
            int m = l + (r - l) / 2;
            inv += mergeSort(arr, l, m);
            inv += mergeSort(arr, m + 1, r);
            inv += merge(arr, l, m, r);
        }
        return inv;
    }
    private long merge(int[] arr, int l, int m, int r) {
        long count = 0;
        int[] temp = new int[r - l + 1];
        int i = l, j = m + 1, k = 0;
        while (i <= m && j <= r) {
            if (arr[i] <= arr[j]) temp[k++] = arr[i++];
            else {
                temp[k++] = arr[j++];
                count += (m - i + 1);
            }
        }
        while (i <= m) temp[k++] = arr[i++];
        while (j <= r) temp[k++] = arr[j++];
        System.arraycopy(temp, 0, arr, l, temp.length);
        return count;
    }
}"""
        ),
        DsaProblem(
            id = "rec_065",
            topic = "recursion",
            title = "Reverse Linked List Recursively",
            difficulty = "Easy",
            pattern = "Linked List Head Recursion",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Reverse a singly linked list using recursion.",
            exampleInput = "head = [1, 2, 3, 4, 5]",
            exampleOutput = "[5, 4, 3, 2, 1]",
            keyInsight = "Base case: if head == null or head.next == null, return head. Reverse rest: newHead = reverseList(head.next). Set head.next.next = head; head.next = null; return newHead.",
            solutionCode = """class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}"""
        ),
        DsaProblem(
            id = "rec_066",
            topic = "recursion",
            title = "Merge Two Sorted Linked Lists Recursively",
            difficulty = "Easy",
            pattern = "Linked List Divide and Conquer",
            timeComplexity = "O(M + N)",
            spaceComplexity = "O(M + N)",
            description = "Merge two sorted linked lists and return it as a new sorted list using recursion.",
            exampleInput = "list1 = [1,2,4], list2 = [1,3,4]",
            exampleOutput = "[1,1,2,3,4,4]",
            keyInsight = "If list1 is null, return list2. If list2 is null, return list1. Pick smaller node: list1.next = merge(list1.next, list2) and return list1.",
            solutionCode = """class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val <= l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}"""
        ),
        DsaProblem(
            id = "rec_067",
            topic = "recursion",
            title = "Swap Nodes in Pairs Recursively",
            difficulty = "Medium",
            pattern = "Pairwise Node Rewiring",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given a linked list, swap every two adjacent nodes and return its head using recursion.",
            exampleInput = "head = [1,2,3,4]",
            exampleOutput = "[2,1,4,3]",
            keyInsight = "Base case: if head == null || head.next == null, return head. Second node = head.next. head.next = swapPairs(second.next); second.next = head; return second.",
            solutionCode = """class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode second = head.next;
        head.next = swapPairs(second.next);
        second.next = head;
        return second;
    }
}"""
        ),
        DsaProblem(
            id = "rec_068",
            topic = "recursion",
            title = "Flatten a Nested List Iterator",
            difficulty = "Medium",
            pattern = "Recursive Unpacking",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given a nested list of integers, flatten it recursively into a single flat list.",
            exampleInput = "[[1,1], 2, [1,1]]",
            exampleOutput = "[1, 1, 2, 1, 1]",
            keyInsight = "Iterate through items: if item is an integer, append it to flat list. If it is a nested list, recursively flatten it.",
            solutionCode = """class Solution {
    public List<Integer> flatten(List<Object> nested) {
        List<Integer> result = new ArrayList<>();
        helper(nested, result);
        return result;
    }
    @SuppressWarnings("unchecked")
    private void helper(List<Object> list, List<Integer> res) {
        for (Object item : list) {
            if (item instanceof Integer) res.add((Integer) item);
            else helper((List<Object>) item, res);
        }
    }
}"""
        ),
        DsaProblem(
            id = "rec_069",
            topic = "recursion",
            title = "Flatten Binary Tree to Linked List",
            difficulty = "Medium",
            pattern = "Tree Pre-order Reconfiguration",
            timeComplexity = "O(N)",
            spaceComplexity = "O(H)",
            description = "Flatten a binary tree into a 'linked list' in-place following pre-order traversal using recursion.",
            exampleInput = "root = [1,2,5,3,4,null,6]",
            exampleOutput = "[1,null,2,null,3,null,4,null,5,null,6]",
            keyInsight = "Traverse in reverse pre-order (right, then left). Keep a prev pointer: node.right = prev; node.left = null; prev = node.",
            solutionCode = """class Solution {
    private TreeNode prev = null;
    public void flatten(TreeNode root) {
        if (root == null) return;
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }
}"""
        ),
        DsaProblem(
            id = "rec_070",
            topic = "recursion",
            title = "Construct Binary Tree from Preorder and Inorder Traversal",
            difficulty = "Medium",
            pattern = "Divide and Conquer Tree Reconstruction",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given two integer arrays preorder and inorder, construct and return the binary tree.",
            exampleInput = "preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]",
            exampleOutput = "[3,9,20,null,null,15,7]",
            keyInsight = "First element of preorder is root. Locate root in inorder using map. Elements left of it form left subtree; elements right form right subtree. Recurse.",
            solutionCode = """class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) map.put(inorder[i], i);
        return build(preorder, 0, preorder.length - 1, 0, inorder.length - 1, map);
    }
    private TreeNode build(int[] pre, int preL, int preR, int inL, int inR, Map<Integer, Integer> map) {
        if (preL > preR || inL > inR) return null;
        TreeNode root = new TreeNode(pre[preL]);
        int mid = map.get(pre[preL]);
        int leftLen = mid - inL;
        root.left = build(pre, preL + 1, preL + leftLen, inL, mid - 1, map);
        root.right = build(pre, preL + leftLen + 1, preR, mid + 1, inR, map);
        return root;
    }
}"""
        ),
        DsaProblem(
            id = "rec_071",
            topic = "recursion",
            title = "Nim Game",
            difficulty = "Easy",
            pattern = "Recursive Game Theory / Modulo Insight",
            timeComplexity = "O(1)",
            spaceComplexity = "O(1)",
            description = "You and a friend take turns removing 1 to 3 stones from a heap. You take the first turn. The person who removes the last stone wins. Determine if you can win given n stones.",
            exampleInput = "n = 4",
            exampleOutput = "false",
            keyInsight = "You will lose if and only if n is a multiple of 4, because whatever you pick (1, 2, or 3), your opponent can pick (4 - pick) to keep you on a multiple of 4.",
            solutionCode = """class Solution {
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}"""
        ),
        DsaProblem(
            id = "rec_072",
            topic = "recursion",
            title = "Stone Game",
            difficulty = "Medium",
            pattern = "Minimax / Interval Recursion with Memoization",
            timeComplexity = "O(N^2)",
            spaceComplexity = "O(N^2)",
            description = "Alice and Bob play a game with piles of stones. A player can pick the first or last pile on their turn. Can Alice always win if both play optimally?",
            exampleInput = "piles = [5, 3, 4, 5]",
            exampleOutput = "true",
            keyInsight = "Max score difference for current player: max(piles[i] - score(i + 1, j), piles[j] - score(i, j - 1)). Always true because total sum is odd and first player can choose all even or odd positions.",
            solutionCode = """class Solution {
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        Integer[][] memo = new Integer[n][n];
        return maxDiff(piles, 0, n - 1, memo) > 0;
    }
    private int maxDiff(int[] piles, int i, int j, Integer[][] memo) {
        if (i == j) return piles[i];
        if (memo[i][j] != null) return memo[i][j];
        int pickLeft = piles[i] - maxDiff(piles, i + 1, j, memo);
        int pickRight = piles[j] - maxDiff(piles, i, j - 1, memo);
        return memo[i][j] = Math.max(pickLeft, pickRight);
    }
}"""
        ),
        DsaProblem(
            id = "rec_073",
            topic = "recursion",
            title = "Predict the Winner",
            difficulty = "Medium",
            pattern = "Minimax Game Recursion",
            timeComplexity = "O(N^2)",
            spaceComplexity = "O(N^2)",
            description = "Given an array of scores nums, two players take turns picking from ends. Return true if Player 1 can win or tie assuming optimal play.",
            exampleInput = "nums = [1, 5, 2]",
            exampleOutput = "false (Player 2 scores 5, Player 1 scores 3)",
            keyInsight = "diff(i, j) = max(nums[i] - diff(i + 1, j), nums[j] - diff(i, j - 1)). If diff(0, n - 1) >= 0, Player 1 wins.",
            solutionCode = """class Solution {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        Integer[][] memo = new Integer[n][n];
        return diff(nums, 0, n - 1, memo) >= 0;
    }
    private int diff(int[] nums, int i, int j, Integer[][] memo) {
        if (i == j) return nums[i];
        if (memo[i][j] != null) return memo[i][j];
        int left = nums[i] - diff(nums, i + 1, j, memo);
        int right = nums[j] - diff(nums, i, j - 1, memo);
        return memo[i][j] = Math.max(left, right);
    }
}"""
        ),
        DsaProblem(
            id = "rec_074",
            topic = "recursion",
            title = "Can I Win",
            difficulty = "Medium",
            pattern = "Minimax with Bitmask Memoization",
            timeComplexity = "O(2^M * M)",
            spaceComplexity = "O(2^M)",
            description = "Two players take turns choosing numbers from 1 to maxChoosableInteger without replacement to reach desiredTotal. Determine if the first player can force a win.",
            exampleInput = "maxChoosableInteger = 10, desiredTotal = 11",
            exampleOutput = "false",
            keyInsight = "Use an integer bitmask to represent available numbers. Try picking each available number i: if i >= total or !canWin(total - i), current player wins.",
            solutionCode = """class Solution {
    public boolean canIWin(int maxChoosable, int desiredTotal) {
        int sum = (1 + maxChoosable) * maxChoosable / 2;
        if (sum < desiredTotal) return false;
        if (desiredTotal <= 0) return true;
        return helper(maxChoosable, desiredTotal, 0, new HashMap<>());
    }
    private boolean helper(int max, int total, int mask, Map<Integer, Boolean> memo) {
        if (memo.containsKey(mask)) return memo.get(mask);
        for (int i = 1; i <= max; i++) {
            if ((mask & (1 << i)) == 0) {
                if (i >= total || !helper(max, total - i, mask | (1 << i), memo)) {
                    memo.put(mask, true);
                    return true;
                }
            }
        }
        memo.put(mask, false);
        return false;
    }
}"""
        ),
        DsaProblem(
            id = "rec_075",
            topic = "recursion",
            title = "Partition to K Equal Sum Subsets",
            difficulty = "Medium",
            pattern = "Backtracking with Bucket Allocation",
            timeComplexity = "O(k * 2^N)",
            spaceComplexity = "O(N)",
            description = "Given an integer array nums and an integer k, return true if it is possible to divide this array into k non-empty subsets whose sums are all equal.",
            exampleInput = "nums = [4, 3, 2, 3, 5, 2, 1], k = 4",
            exampleOutput = "true (sum = 20, target per bucket = 5)",
            keyInsight = "Target per subset is total / k. Fill bucket 1 until it hits target, then move to bucket 2, etc. Sort descending to fail early.",
            solutionCode = """class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int n : nums) sum += n;
        if (sum % k != 0) return false;
        int target = sum / k;
        Arrays.sort(nums);
        return backtrack(nums, nums.length - 1, new int[k], target);
    }
    private boolean backtrack(int[] nums, int idx, int[] buckets, int target) {
        if (idx < 0) return true;
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] + nums[idx] <= target) {
                buckets[i] += nums[idx];
                if (backtrack(nums, idx - 1, buckets, target)) return true;
                buckets[i] -= nums[idx];
            }
            if (buckets[i] == 0) break; // pruning duplicate empty buckets
        }
        return false;
    }
}"""
        ),
        DsaProblem(
            id = "rec_076",
            topic = "recursion",
            title = "Matchsticks to Square",
            difficulty = "Medium",
            pattern = "Backtracking / 4-Bucket Allocation",
            timeComplexity = "O(4^N)",
            spaceComplexity = "O(N)",
            description = "You are given an integer array matchsticks where matchsticks[i] is the length of the ith matchstick. Determine if you can make a square using all matchsticks.",
            exampleInput = "matchsticks = [1, 1, 2, 2, 2]",
            exampleOutput = "true (perimeter = 8, each side = 2)",
            keyInsight = "Special case of Partition to K Equal Sum Subsets with k = 4. Target side length = total / 4. Sort in descending order to prune branches rapidly.",
            solutionCode = """class Solution {
    public boolean makesquare(int[] matchsticks) {
        int sum = 0;
        for (int m : matchsticks) sum += m;
        if (sum % 4 != 0) return false;
        int side = sum / 4;
        Arrays.sort(matchsticks);
        return dfs(matchsticks, matchsticks.length - 1, new int[4], side);
    }
    private boolean dfs(int[] m, int idx, int[] sides, int target) {
        if (idx < 0) return true;
        for (int i = 0; i < 4; i++) {
            if (sides[i] + m[idx] <= target) {
                sides[i] += m[idx];
                if (dfs(m, idx - 1, sides, target)) return true;
                sides[i] -= m[idx];
            }
            if (sides[i] == 0) break;
        }
        return false;
    }
}"""
        ),
        DsaProblem(
            id = "rec_077",
            topic = "recursion",
            title = "Gray Code",
            difficulty = "Medium",
            pattern = "Recursive Reflection",
            timeComplexity = "O(2^N)",
            spaceComplexity = "O(2^N)",
            description = "An n-bit gray code sequence is a sequence of 2^n integers where consecutive values differ by exactly one bit. Generate the sequence recursively.",
            exampleInput = "n = 2",
            exampleOutput = "[0, 1, 3, 2]",
            keyInsight = "Gray code for n is gray code for n-1 followed by reverse of gray code for n-1 with the nth bit set (1 << (n-1)).",
            solutionCode = """class Solution {
    public List<Integer> grayCode(int n) {
        if (n == 0) {
            List<Integer> base = new ArrayList<>();
            base.add(0);
            return base;
        }
        List<Integer> prev = grayCode(n - 1);
        int addMask = 1 << (n - 1);
        List<Integer> result = new ArrayList<>(prev);
        for (int i = prev.size() - 1; i >= 0; i--) {
            result.add(prev.get(i) | addMask);
        }
        return result;
    }
}"""
        ),
        DsaProblem(
            id = "rec_078",
            topic = "recursion",
            title = "K-th Symbol in Grammar",
            difficulty = "Medium",
            pattern = "Tree Symmetry / Binary Recursion",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Row 1 is \"0\". Every row is generated by replacing \"0\" with \"01\" and \"1\" with \"10\". Find the kth symbol in row n (1-indexed).",
            exampleInput = "n = 2, k = 1",
            exampleOutput = "0",
            keyInsight = "First half of row n is identical to row n-1. Second half is the bitwise inverse of row n-1. If k <= mid, recurse on k; else invert result of k - mid.",
            solutionCode = """class Solution {
    public int kthGrammar(int n, int k) {
        if (n == 1 && k == 1) return 0;
        int mid = (int) Math.pow(2, n - 2);
        if (k <= mid) return kthGrammar(n - 1, k);
        return 1 - kthGrammar(n - 1, k - mid);
    }
}"""
        ),
        DsaProblem(
            id = "rec_079",
            topic = "recursion",
            title = "Beautiful Arrangement",
            difficulty = "Medium",
            pattern = "Permutation Backtracking with Divisibility Filter",
            timeComplexity = "O(valid permutations)",
            spaceComplexity = "O(N)",
            description = "An arrangement of numbers 1 to n is beautiful if for every position i (1-indexed), either perm[i] % i == 0 or i % perm[i] == 0. Find total count.",
            exampleInput = "n = 2",
            exampleOutput = "2 ([1, 2] and [2, 1])",
            keyInsight = "Place numbers from pos = 1 to n. For each unvisited number num from 1 to n, check if (num % pos == 0 || pos % num == 0). If valid, place and recurse.",
            solutionCode = """class Solution {
    private int count = 0;
    public int countArrangement(int n) {
        backtrack(1, n, new boolean[n + 1]);
        return count;
    }
    private void backtrack(int pos, int n, boolean[] used) {
        if (pos > n) { count++; return; }
        for (int num = 1; num <= n; num++) {
            if (!used[num] && (num % pos == 0 || pos % num == 0)) {
                used[num] = true;
                backtrack(pos + 1, n, used);
                used[num] = false;
            }
        }
    }
}"""
        ),
        DsaProblem(
            id = "rec_080",
            topic = "recursion",
            title = "Unique Paths",
            difficulty = "Medium",
            pattern = "Grid Recursion with Memoization",
            timeComplexity = "O(M * N)",
            spaceComplexity = "O(M * N)",
            description = "A robot is located at the top-left corner of an m x n grid. It can only move either down or right at any point. Find the number of unique paths to the bottom-right corner.",
            exampleInput = "m = 3, n = 7",
            exampleOutput = "28",
            keyInsight = "paths(r, c) = paths(r + 1, c) + paths(r, c + 1). Base case: when r == m - 1 && c == n - 1, return 1. Memoize 2D array.",
            solutionCode = """class Solution {
    public int uniquePaths(int m, int n) {
        int[][] memo = new int[m][n];
        return count(0, 0, m, n, memo);
    }
    private int count(int r, int c, int m, int n, int[][] memo) {
        if (r == m - 1 && c == n - 1) return 1;
        if (r >= m || c >= n) return 0;
        if (memo[r][c] != 0) return memo[r][c];
        return memo[r][c] = count(r + 1, c, m, n, memo) + count(r, c + 1, m, n, memo);
    }
}"""
        ),
        DsaProblem(
            id = "rec_081",
            topic = "recursion",
            title = "Unique Paths II (With Obstacles)",
            difficulty = "Medium",
            pattern = "Constrained Grid Recursion",
            timeComplexity = "O(M * N)",
            spaceComplexity = "O(M * N)",
            description = "Find unique paths from top-left to bottom-right in an m x n grid where 1 represents an obstacle and 0 represents an open space.",
            exampleInput = "obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]",
            exampleOutput = "2",
            keyInsight = "If current cell is 1 or out of bounds, return 0. If bottom-right reached, return 1. Return memoized sum of right and down moves.",
            solutionCode = """class Solution {
    public int uniquePathsWithObstacles(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Integer[][] memo = new Integer[m][n];
        return dfs(grid, 0, 0, memo);
    }
    private int dfs(int[][] g, int r, int c, Integer[][] memo) {
        if (r >= g.length || c >= g[0].length || g[r][c] == 1) return 0;
        if (r == g.length - 1 && c == g[0].length - 1) return 1;
        if (memo[r][c] != null) return memo[r][c];
        return memo[r][c] = dfs(g, r + 1, c, memo) + dfs(g, r, c + 1, memo);
    }
}"""
        ),
        DsaProblem(
            id = "rec_082",
            topic = "recursion",
            title = "Minimum Path Sum",
            difficulty = "Medium",
            pattern = "Grid Optimization Recursion with Memoization",
            timeComplexity = "O(M * N)",
            spaceComplexity = "O(M * N)",
            description = "Given an m x n grid filled with non-negative numbers, find a path from top-left to bottom-right which minimizes the sum of all numbers along its path.",
            exampleInput = "grid = [[1,3,1],[1,5,1],[4,2,1]]",
            exampleOutput = "7 (1 -> 3 -> 1 -> 1 -> 1)",
            keyInsight = "minCost(r, c) = grid[r][c] + min(minCost(r + 1, c), minCost(r, c + 1)). Base case: bottom-right cell returns grid[m-1][n-1].",
            solutionCode = """class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Integer[][] memo = new Integer[m][n];
        return minPath(grid, 0, 0, memo);
    }
    private int minPath(int[][] g, int r, int c, Integer[][] memo) {
        if (r == g.length - 1 && c == g[0].length - 1) return g[r][c];
        if (r >= g.length || c >= g[0].length) return Integer.MAX_VALUE;
        if (memo[r][c] != null) return memo[r][c];
        int right = minPath(g, r, c + 1, memo);
        int down = minPath(g, r + 1, c, memo);
        return memo[r][c] = g[r][c] + Math.min(right, down);
    }
}"""
        ),
        DsaProblem(
            id = "rec_083",
            topic = "recursion",
            title = "Longest Increasing Subsequence (Recursive Formulation)",
            difficulty = "Medium",
            pattern = "Pick / Don't Pick with State Memoization",
            timeComplexity = "O(N^2)",
            spaceComplexity = "O(N^2)",
            description = "Given an integer array nums, return the length of the longest strictly increasing subsequence using recursive pick-or-skip choices.",
            exampleInput = "nums = [10, 9, 2, 5, 3, 7, 101, 18]",
            exampleOutput = "4 ([2, 3, 7, 101])",
            keyInsight = "At index i with previous index prev: option 1 is skip (recurse on i + 1, prev). Option 2: if prev == -1 || nums[i] > nums[prev], take (1 + recurse on i + 1, i). Return max.",
            solutionCode = """class Solution {
    public int lengthOfLIS(int[] nums) {
        Integer[][] memo = new Integer[nums.length][nums.length + 1];
        return lis(nums, 0, -1, memo);
    }
    private int lis(int[] nums, int i, int prev, Integer[][] memo) {
        if (i == nums.length) return 0;
        if (memo[i][prev + 1] != null) return memo[i][prev + 1];
        int skip = lis(nums, i + 1, prev, memo);
        int take = 0;
        if (prev == -1 || nums[i] > nums[prev]) {
            take = 1 + lis(nums, i + 1, i, memo);
        }
        return memo[i][prev + 1] = Math.max(skip, take);
    }
}"""
        ),
        DsaProblem(
            id = "rec_084",
            topic = "recursion",
            title = "Longest Common Subsequence",
            difficulty = "Medium",
            pattern = "2D String Recursion with Memoization",
            timeComplexity = "O(M * N)",
            spaceComplexity = "O(M * N)",
            description = "Given two strings text1 and text2, return the length of their longest common subsequence using recursion with memoization.",
            exampleInput = "text1 = \"abcde\", text2 = \"ace\"",
            exampleOutput = "3 (\"ace\")",
            keyInsight = "If text1[i] == text2[j], return 1 + lcs(i + 1, j + 1). Else return max(lcs(i + 1, j), lcs(i, j + 1)). Memoize results in 2D array.",
            solutionCode = """class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        Integer[][] memo = new Integer[text1.length()][text2.length()];
        return lcs(text1, text2, 0, 0, memo);
    }
    private int lcs(String s1, String s2, int i, int j, Integer[][] memo) {
        if (i == s1.length() || j == s2.length()) return 0;
        if (memo[i][j] != null) return memo[i][j];
        if (s1.charAt(i) == s2.charAt(j)) {
            return memo[i][j] = 1 + lcs(s1, s2, i + 1, j + 1, memo);
        }
        return memo[i][j] = Math.max(lcs(s1, s2, i + 1, j, memo), lcs(s1, s2, i, j + 1, memo));
    }
}"""
        ),
        DsaProblem(
            id = "rec_085",
            topic = "recursion",
            title = "Edit Distance",
            difficulty = "Medium",
            pattern = "3-Way Choice Recursion with Memoization",
            timeComplexity = "O(M * N)",
            spaceComplexity = "O(M * N)",
            description = "Given two strings word1 and word2, return the minimum number of operations (insert, delete, replace) required to convert word1 to word2.",
            exampleInput = "word1 = \"horse\", word2 = \"ros\"",
            exampleOutput = "3",
            keyInsight = "If chars match, recurse on (i + 1, j + 1). Else return 1 + min of insert (i, j + 1), delete (i + 1, j), and replace (i + 1, j + 1).",
            solutionCode = """class Solution {
    public int minDistance(String word1, String word2) {
        Integer[][] memo = new Integer[word1.length()][word2.length()];
        return dist(word1, word2, 0, 0, memo);
    }
    private int dist(String s1, String s2, int i, int j, Integer[][] memo) {
        if (i == s1.length()) return s2.length() - j;
        if (j == s2.length()) return s1.length() - i;
        if (memo[i][j] != null) return memo[i][j];
        if (s1.charAt(i) == s2.charAt(j)) return memo[i][j] = dist(s1, s2, i + 1, j + 1, memo);
        int insert = dist(s1, s2, i, j + 1, memo);
        int delete = dist(s1, s2, i + 1, j, memo);
        int replace = dist(s1, s2, i + 1, j + 1, memo);
        return memo[i][j] = 1 + Math.min(insert, Math.min(delete, replace));
    }
}"""
        ),
        DsaProblem(
            id = "rec_086",
            topic = "recursion",
            title = "Wildcard Matching",
            difficulty = "Hard",
            pattern = "Pattern Matching Recursion with Memoization",
            timeComplexity = "O(M * N)",
            spaceComplexity = "O(M * N)",
            description = "Implement wildcard pattern matching with support for '?' (matches single char) and '*' (matches any sequence of chars).",
            exampleInput = "s = \"aa\", p = \"*\"",
            exampleOutput = "true",
            keyInsight = "If p[j] == '*', branch into two: match zero characters (recurse j + 1) or match one character (recurse i + 1). Memoize in 2D Boolean array.",
            solutionCode = """class Solution {
    public boolean isMatch(String s, String p) {
        Boolean[][] memo = new Boolean[s.length() + 1][p.length() + 1];
        return match(s, p, 0, 0, memo);
    }
    private boolean match(String s, String p, int i, int j, Boolean[][] memo) {
        if (memo[i][j] != null) return memo[i][j];
        if (j == p.length()) return memo[i][j] = (i == s.length());
        if (i == s.length()) {
            for (int k = j; k < p.length(); k++) if (p.charAt(k) != '*') return memo[i][j] = false;
            return memo[i][j] = true;
        }
        if (p.charAt(j) == '?' || s.charAt(i) == p.charAt(j)) {
            return memo[i][j] = match(s, p, i + 1, j + 1, memo);
        }
        if (p.charAt(j) == '*') {
            return memo[i][j] = match(s, p, i, j + 1, memo) || match(s, p, i + 1, j, memo);
        }
        return memo[i][j] = false;
    }
}"""
        ),
        DsaProblem(
            id = "rec_087",
            topic = "recursion",
            title = "Regular Expression Matching",
            difficulty = "Hard",
            pattern = "Complex Multi-branch State Recursion",
            timeComplexity = "O(M * N)",
            spaceComplexity = "O(M * N)",
            description = "Implement regular expression matching with support for '.' and '*' where '*' matches zero or more of the preceding element.",
            exampleInput = "s = \"aab\", p = \"c*a*b\"",
            exampleOutput = "true",
            keyInsight = "Check if first char matches: match = (i < s.length() && (p[j] == s[i] || p[j] == '.')). If next char is '*', either skip pattern element (j + 2) or use it if match (i + 1).",
            solutionCode = """class Solution {
    public boolean isMatch(String s, String p) {
        Boolean[][] memo = new Boolean[s.length() + 1][p.length() + 1];
        return dfs(s, p, 0, 0, memo);
    }
    private boolean dfs(String s, String p, int i, int j, Boolean[][] memo) {
        if (memo[i][j] != null) return memo[i][j];
        if (j == p.length()) return memo[i][j] = (i == s.length());
        boolean firstMatch = (i < s.length() && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.'));
        boolean ans;
        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            ans = dfs(s, p, i, j + 2, memo) || (firstMatch && dfs(s, p, i + 1, j, memo));
        } else {
            ans = firstMatch && dfs(s, p, i + 1, j + 1, memo);
        }
        return memo[i][j] = ans;
    }
}"""
        ),
        DsaProblem(
            id = "rec_088",
            topic = "recursion",
            title = "Coin Change (Fewest Coins)",
            difficulty = "Medium",
            pattern = "Unbounded Knapsack Recursion with Memoization",
            timeComplexity = "O(Amount * N)",
            spaceComplexity = "O(Amount)",
            description = "Given integer array coins and integer amount, return the fewest number of coins needed to make up that amount using recursion. Return -1 if impossible.",
            exampleInput = "coins = [1, 2, 5], amount = 11",
            exampleOutput = "3 (5 + 5 + 1)",
            keyInsight = "minCoins(rem) = 1 + min(minCoins(rem - c) for c in coins). Base cases: rem == 0 return 0, rem < 0 return -1. Memoize by amount.",
            solutionCode = """class Solution {
    public int coinChange(int[] coins, int amount) {
        return helper(coins, amount, new int[amount + 1]);
    }
    private int helper(int[] coins, int rem, int[] count) {
        if (rem < 0) return -1;
        if (rem == 0) return 0;
        if (count[rem] != 0) return count[rem];
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = helper(coins, rem - coin, count);
            if (res >= 0 && res < min) min = 1 + res;
        }
        count[rem] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem];
    }
}"""
        ),
        DsaProblem(
            id = "rec_089",
            topic = "recursion",
            title = "Coin Change II (Number of Combinations)",
            difficulty = "Medium",
            pattern = "Unbounded Combination Recursion with Memoization",
            timeComplexity = "O(N * Amount)",
            spaceComplexity = "O(N * Amount)",
            description = "Given integer array coins and integer amount, return the number of combinations that make up that amount.",
            exampleInput = "amount = 5, coins = [1, 2, 5]",
            exampleOutput = "4",
            keyInsight = "At coin i: choose between using coin i again (recurse on i with amount - coins[i]) or skipping coin i (recurse on i + 1 with amount).",
            solutionCode = """class Solution {
    public int change(int amount, int[] coins) {
        Integer[][] memo = new Integer[coins.length][amount + 1];
        return ways(coins, 0, amount, memo);
    }
    private int ways(int[] coins, int i, int rem, Integer[][] memo) {
        if (rem == 0) return 1;
        if (i == coins.length || rem < 0) return 0;
        if (memo[i][rem] != null) return memo[i][rem];
        int pick = ways(coins, i, rem - coins[i], memo);
        int skip = ways(coins, i + 1, rem, memo);
        return memo[i][rem] = pick + skip;
    }
}"""
        ),
        DsaProblem(
            id = "rec_090",
            topic = "recursion",
            title = "0/1 Knapsack Problem",
            difficulty = "Medium",
            pattern = "Classical 0/1 Knapsack Recursion",
            timeComplexity = "O(N * W)",
            spaceComplexity = "O(N * W)",
            description = "Given weights wt and values val of n items, find maximum value that can be put in a knapsack of capacity W using recursion.",
            exampleInput = "val = [60, 100, 120], wt = [10, 20, 30], W = 50",
            exampleOutput = "220",
            keyInsight = "At item i: skip item (knapsack(i + 1, w)) or include item if wt[i] <= w (val[i] + knapsack(i + 1, w - wt[i])). Return max.",
            solutionCode = """class Solution {
    public int knapSack(int W, int[] wt, int[] val) {
        Integer[][] memo = new Integer[wt.length][W + 1];
        return solve(wt, val, 0, W, memo);
    }
    private int solve(int[] wt, int[] val, int i, int remW, Integer[][] memo) {
        if (i == wt.length || remW == 0) return 0;
        if (memo[i][remW] != null) return memo[i][remW];
        int skip = solve(wt, val, i + 1, remW, memo);
        int pick = 0;
        if (wt[i] <= remW) {
            pick = val[i] + solve(wt, val, i + 1, remW - wt[i], memo);
        }
        return memo[i][remW] = Math.max(skip, pick);
    }
}"""
        ),
        DsaProblem(
            id = "rec_091",
            topic = "recursion",
            title = "Count Sorted Vowel Strings",
            difficulty = "Medium",
            pattern = "Combinatorial Recursion / Stars and Bars",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given an integer n, return the number of strings of length n that consist only of vowels ('a', 'e', 'i', 'o', 'u') and are lexicographically sorted.",
            exampleInput = "n = 2",
            exampleOutput = "15",
            keyInsight = "For each vowel index from lastVowel to 4, recurse with (n - 1, v). When n == 0, count 1 valid combination.",
            solutionCode = """class Solution {
    public int countVowelStrings(int n) {
        Integer[][] memo = new Integer[n + 1][5];
        return count(n, 0, memo);
    }
    private int count(int n, int vowelIdx, Integer[][] memo) {
        if (n == 0) return 1;
        if (memo[n][vowelIdx] != null) return memo[n][vowelIdx];
        int total = 0;
        for (int v = vowelIdx; v < 5; v++) {
            total += count(n - 1, v, memo);
        }
        return memo[n][vowelIdx] = total;
    }
}"""
        ),
        DsaProblem(
            id = "rec_092",
            topic = "recursion",
            title = "All Possible Full Binary Trees",
            difficulty = "Medium",
            pattern = "Divide and Conquer Tree Enumeration",
            timeComplexity = "O(2^N / sqrt(N)) Catalan number",
            spaceComplexity = "O(2^N)",
            description = "Given an integer n, return a list of all possible full binary trees with n nodes. Each node of each tree in the answer must have Node.val == 0.",
            exampleInput = "n = 7",
            exampleOutput = "5 unique full binary tree structures",
            keyInsight = "A full binary tree must have an odd number of nodes (if n % 2 == 0 return empty). For left subtrees of size i (1 to n-2 step 2), right subtree has size n - 1 - i. Combine all pairs.",
            solutionCode = """class Solution {
    private Map<Integer, List<TreeNode>> memo = new HashMap<>();
    public List<TreeNode> allPossibleFBT(int n) {
        if (n % 2 == 0) return Collections.emptyList();
        if (n == 1) return Collections.singletonList(new TreeNode(0));
        if (memo.containsKey(n)) return memo.get(n);
        List<TreeNode> res = new ArrayList<>();
        for (int i = 1; i < n; i += 2) {
            for (TreeNode left : allPossibleFBT(i)) {
                for (TreeNode right : allPossibleFBT(n - 1 - i)) {
                    TreeNode root = new TreeNode(0);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        memo.put(n, res);
        return res;
    }
}"""
        ),
        DsaProblem(
            id = "rec_093",
            topic = "recursion",
            title = "Additive Number",
            difficulty = "Medium",
            pattern = "Backtracking / Number Verification",
            timeComplexity = "O(N^3)",
            spaceComplexity = "O(N)",
            description = "An additive number is a string whose digits can form an additive sequence (every number is sum of preceding two). Determine if string is additive.",
            exampleInput = "num = \"112358\"",
            exampleOutput = "true (1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8)",
            keyInsight = "Try all pairs of first two numbers (lengths up to n/2). For each pair, recursively verify whether remaining string starts with (num1 + num2).",
            solutionCode = """class Solution {
    public boolean isAdditiveNumber(String num) {
        int n = num.length();
        for (int i = 1; i <= n / 2; i++) {
            if (num.charAt(0) == '0' && i > 1) break;
            long n1 = Long.parseLong(num.substring(0, i));
            for (int j = 1; Math.max(i, j) <= n - i - j; j++) {
                if (num.charAt(i) == '0' && j > 1) break;
                long n2 = Long.parseLong(num.substring(i, i + j));
                if (isValid(num, i + j, n1, n2)) return true;
            }
        }
        return false;
    }
    private boolean isValid(String s, int idx, long n1, long n2) {
        if (idx == s.length()) return true;
        long sum = n1 + n2;
        String sumStr = String.valueOf(sum);
        if (!s.startsWith(sumStr, idx)) return false;
        return isValid(s, idx + sumStr.length(), n2, sum);
    }
}"""
        ),
        DsaProblem(
            id = "rec_094",
            topic = "recursion",
            title = "Split Array into Fibonacci Sequence",
            difficulty = "Medium",
            pattern = "Backtracking with Numeric Constraints",
            timeComplexity = "O(N^2)",
            spaceComplexity = "O(N)",
            description = "Split string num into a Fibonacci-like sequence [F[0], F[1], ...] where each number fits in a 32-bit signed integer and F[i] + F[i+1] == F[i+2].",
            exampleInput = "num = \"1101111\"",
            exampleOutput = "[11, 0, 11, 11]",
            keyInsight = "Backtrack from start to end. Maintain current sequence list. If list has >= 2 elements, next number must equal sum of last two. Stop when out of range.",
            solutionCode = """class Solution {
    public List<Integer> splitIntoFibonacci(String num) {
        List<Integer> result = new ArrayList<>();
        backtrack(num, 0, result);
        return result;
    }
    private boolean backtrack(String s, int idx, List<Integer> res) {
        if (idx == s.length() && res.size() >= 3) return true;
        for (int len = 1; len <= 10 && idx + len <= s.length(); len++) {
            if (s.charAt(idx) == '0' && len > 1) break;
            long val = Long.parseLong(s.substring(idx, idx + len));
            if (val > Integer.MAX_VALUE) break;
            int size = res.size();
            if (size >= 2 && val > (long) res.get(size - 1) + res.get(size - 2)) break;
            if (size < 2 || val == (long) res.get(size - 1) + res.get(size - 2)) {
                res.add((int) val);
                if (backtrack(s, idx + len, res)) return true;
                res.remove(res.size() - 1);
            }
        }
        return false;
    }
}"""
        ),
        DsaProblem(
            id = "rec_095",
            topic = "recursion",
            title = "Letter Case Permutation",
            difficulty = "Medium",
            pattern = "Two-Way Branching per Alpha Char",
            timeComplexity = "O(2^L * N)",
            spaceComplexity = "O(N)",
            description = "Given a string s, transform every letter to lowercase or uppercase to create another string. Return a list of all possible strings.",
            exampleInput = "s = \"a1b2\"",
            exampleOutput = "[\"a1b2\",\"a1B2\",\"A1b2\",\"A1B2\"]",
            keyInsight = "If char is a digit, advance index without branching. If char is a letter, branch into two calls: one with lowercase and one with uppercase.",
            solutionCode = """class Solution {
    public List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();
        dfs(s.toCharArray(), 0, result);
        return result;
    }
    private void dfs(char[] ch, int idx, List<String> res) {
        if (idx == ch.length) {
            res.add(new String(ch));
            return;
        }
        if (Character.isLetter(ch[idx])) {
            ch[idx] = Character.toLowerCase(ch[idx]);
            dfs(ch, idx + 1, res);
            ch[idx] = Character.toUpperCase(ch[idx]);
            dfs(ch, idx + 1, res);
        } else {
            dfs(ch, idx + 1, res);
        }
    }
}"""
        ),
        DsaProblem(
            id = "rec_096",
            topic = "recursion",
            title = "Non-decreasing Subsequences",
            difficulty = "Medium",
            pattern = "Subsequence Backtracking with Local HashSet",
            timeComplexity = "O(2^N)",
            spaceComplexity = "O(N)",
            description = "Given an integer array nums, return all the different possible non-decreasing subsequences of the given array with at least two elements.",
            exampleInput = "nums = [4, 6, 7, 7]",
            exampleOutput = "[[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]",
            keyInsight = "Use a Set at each recursive frame to avoid trying the same element twice at that recursion level. Only append if element >= last picked.",
            solutionCode = """class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }
    private void backtrack(int[] nums, int start, List<Integer> curr, List<List<Integer>> res) {
        if (curr.size() >= 2) res.add(new ArrayList<>(curr));
        Set<Integer> usedAtLevel = new HashSet<>();
        for (int i = start; i < nums.length; i++) {
            if (usedAtLevel.contains(nums[i])) continue;
            if (curr.isEmpty() || nums[i] >= curr.get(curr.size() - 1)) {
                usedAtLevel.add(nums[i]);
                curr.add(nums[i]);
                backtrack(nums, i + 1, curr, res);
                curr.remove(curr.size() - 1);
            }
        }
    }
}"""
        ),
        DsaProblem(
            id = "rec_097",
            topic = "recursion",
            title = "Maximum Length of a Concatenated String with Unique Characters",
            difficulty = "Medium",
            pattern = "Bitmask Pick / Don't Pick Recursion",
            timeComplexity = "O(2^N)",
            spaceComplexity = "O(N)",
            description = "Given an array of strings arr, return maximum possible length of a string formed by concatenating a subsequence of arr that has all unique characters.",
            exampleInput = "arr = [\"un\",\"iq\",\"ue\"]",
            exampleOutput = "4 (\"uniq\")",
            keyInsight = "Convert each string to a bitmask of characters (skip strings with internal duplicates). At each word, choose to skip or include if (mask & currMask) == 0.",
            solutionCode = """class Solution {
    public int maxLength(List<String> arr) {
        return dfs(arr, 0, 0);
    }
    private int dfs(List<String> arr, int idx, int mask) {
        if (idx == arr.size()) return Integer.bitCount(mask);
        int skip = dfs(arr, idx + 1, mask);
        int take = 0;
        int wordMask = getMask(arr.get(idx));
        if (wordMask != 0 && (mask & wordMask) == 0) {
            take = dfs(arr, idx + 1, mask | wordMask);
        }
        return Math.max(skip, take);
    }
    private int getMask(String s) {
        int m = 0;
        for (char c : s.toCharArray()) {
            int bit = 1 << (c - 'a');
            if ((m & bit) != 0) return 0;
            m |= bit;
        }
        return m;
    }
}"""
        ),
        DsaProblem(
            id = "rec_098",
            topic = "recursion",
            title = "Word Search II (Trie + Grid DFS Backtracking)",
            difficulty = "Hard",
            pattern = "Trie Guided Backtracking",
            timeComplexity = "O(M * N * 4^L)",
            spaceComplexity = "O(Total Chars in Words)",
            description = "Given an m x n board of characters and a list of strings words, return all words on the board. Each word must be constructed from sequentially adjacent cells.",
            exampleInput = "board = [[\"o\",\"a\",\"a\",\"n\"],[\"e\",\"t\",\"a\",\"e\"],[\"i\",\"h\",\"k\",\"r\"],[\"i\",\"f\",\"l\",\"v\"]], words = [\"oath\",\"pea\",\"eat\",\"rain\"]",
            exampleOutput = "[\"eat\",\"oath\"]",
            keyInsight = "Build a Trie from words. Search the board using DFS guided by Trie nodes; prune branches immediately if prefix does not exist in Trie.",
            solutionCode = """class Solution {
    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word;
    }
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode p = root;
            for (char c : w.toCharArray()) {
                if (p.next[c - 'a'] == null) p.next[c - 'a'] = new TrieNode();
                p = p.next[c - 'a'];
            }
            p.word = w;
        }
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                dfs(board, r, c, root, res);
            }
        }
        return res;
    }
    private void dfs(char[][] b, int r, int c, TrieNode p, List<String> res) {
        char ch = b[r][c];
        if (ch == '#' || p.next[ch - 'a'] == null) return;
        p = p.next[ch - 'a'];
        if (p.word != null) {
            res.add(p.word);
            p.word = null; // de-duplicate
        }
        b[r][c] = '#';
        if (r > 0) dfs(b, r - 1, c, p, res);
        if (c > 0) dfs(b, r, c - 1, p, res);
        if (r < b.length - 1) dfs(b, r + 1, c, p, res);
        if (c < b[0].length - 1) dfs(b, r, c + 1, p, res);
        b[r][c] = ch;
    }
}"""
        ),
        DsaProblem(
            id = "rec_099",
            topic = "recursion",
            title = "Remove Invalid Parentheses",
            difficulty = "Hard",
            pattern = "BFS / Backtracking with State Validation",
            timeComplexity = "O(2^N)",
            spaceComplexity = "O(N)",
            description = "Given a string s that may contain parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid. Return all unique results.",
            exampleInput = "s = \"()())()\"",
            exampleOutput = "[\"()()()\", \"(())()\"]",
            keyInsight = "First count misplaced '(' and ')'. Then backtrack removing only misplaced parentheses, skipping consecutive duplicates to prevent duplicate states.",
            solutionCode = """class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int left = 0, right = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') left++;
            else if (c == ')') {
                if (left > 0) left--;
                else right++;
            }
        }
        Set<String> res = new HashSet<>();
        dfs(s, 0, left, right, 0, new StringBuilder(), res);
        return new ArrayList<>(res);
    }
    private void dfs(String s, int idx, int leftRem, int rightRem, int open, StringBuilder sb, Set<String> res) {
        if (leftRem < 0 || rightRem < 0 || open < 0) return;
        if (idx == s.length()) {
            if (leftRem == 0 && rightRem == 0 && open == 0) res.add(sb.toString());
            return;
        }
        char c = s.charAt(idx);
        int len = sb.length();
        if (c == '(') {
            dfs(s, idx + 1, leftRem - 1, rightRem, open, sb, res); // discard
            dfs(s, idx + 1, leftRem, rightRem, open + 1, sb.append(c), res); // keep
        } else if (c == ')') {
            dfs(s, idx + 1, leftRem, rightRem - 1, open, sb, res); // discard
            dfs(s, idx + 1, leftRem, rightRem, open - 1, sb.append(c), res); // keep
        } else {
            dfs(s, idx + 1, leftRem, rightRem, open, sb.append(c), res);
        }
        sb.setLength(len);
    }
}"""
        ),
        DsaProblem(
            id = "rec_100",
            topic = "recursion",
            title = "Parsing a Boolean Expression",
            difficulty = "Hard",
            pattern = "Recursive Descent Parser",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Return the result of evaluating a given boolean expression represented as a string. Supported operators: '!' (NOT), '&' (AND), '|' (OR), 't' (true), 'f' (false).",
            exampleInput = "expression = \"&(t,f,!(&(t)))\"",
            exampleOutput = "false",
            keyInsight = "Parse recursively: parse sub-expressions separated by commas within parentheses. Apply boolean operator to the list of evaluated child booleans.",
            solutionCode = """class Solution {
    private int idx = 0;
    public boolean parseBoolExpr(String expression) {
        idx = 0;
        return parse(expression);
    }
    private boolean parse(String s) {
        char c = s.charAt(idx++);
        if (c == 't') return true;
        if (c == 'f') return false;
        idx++; // skip '('
        List<Boolean> list = new ArrayList<>();
        while (s.charAt(idx) != ')') {
            if (s.charAt(idx) == ',') idx++;
            else list.add(parse(s));
        }
        idx++; // skip ')'
        if (c == '!') return !list.get(0);
        if (c == '&') {
            for (boolean b : list) if (!b) return false;
            return true;
        }
        for (boolean b : list) if (b) return true;
        return false;
    }
}"""
        )
    )
}
