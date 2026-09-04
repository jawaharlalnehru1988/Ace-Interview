package com.example.data.local.dsa

import com.example.domain.model.DsaProblem

object DsaProblemData {

    fun getAll(): List<DsaProblem> {
        return arrayProblems +
                stringProblems +
                linkedListProblems +
                stackQueueProblems +
                treeProblems +
                graphProblems +
                dpProblems
    }

    fun getByTopic(topicId: String): List<DsaProblem> {
        return when (topicId) {
            "arrays" -> arrayProblems
            "strings" -> stringProblems
            "linked_list" -> linkedListProblems
            "stack" -> stackQueueProblems.filter { it.topic == "stack" }
            "queue" -> stackQueueProblems.filter { it.topic == "queue" }
            "trees" -> treeProblems
            "graphs" -> graphProblems
            "dp" -> dpProblems
            "recursion" -> dpProblems.take(5) + treeProblems.take(5)
            else -> arrayProblems
        }
    }

    val arrayProblems = listOf(
        DsaProblem(
            id = "arr_001",
            topic = "arrays",
            title = "Two Sum",
            difficulty = "Easy",
            pattern = "Hash Map / Complement Lookup",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target. You may assume that each input would have exactly one solution, and you may not use the same element twice.",
            exampleInput = "nums = [2, 7, 11, 15], target = 9",
            exampleOutput = "[0, 1] (because nums[0] + nums[1] == 9)",
            keyInsight = "Instead of an O(N^2) brute-force nested loop, compute complement = target - nums[i]. Check if complement exists in a hash map storing value -> index. If found, return both indices.",
            solutionCode = """class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }
}"""
        ),
        DsaProblem(
            id = "arr_002",
            topic = "arrays",
            title = "Best Time to Buy and Sell Stock",
            difficulty = "Easy",
            pattern = "One Pass / Greedy Min Tracking",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "You are given an array prices where prices[i] is the price of a given stock on the ith day. You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock. Return the maximum profit.",
            exampleInput = "prices = [7, 1, 5, 3, 6, 4]",
            exampleOutput = "5 (Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6 - 1 = 5)",
            keyInsight = "Maintain a running minimum buy price seen so far. At each step, calculate potential profit (prices[i] - minPrice) and update maxProfit greedily in a single linear scan.",
            solutionCode = """class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            } else if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice;
            }
        }
        return maxProfit;
    }
}"""
        ),
        DsaProblem(
            id = "arr_003",
            topic = "arrays",
            title = "Product of Array Except Self",
            difficulty = "Medium",
            pattern = "Prefix & Suffix Products",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1) extra space",
            description = "Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i]. The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer. You must write an algorithm that runs in O(n) time and without using the division operation.",
            exampleInput = "nums = [1, 2, 3, 4]",
            exampleOutput = "[24, 12, 8, 6]",
            keyInsight = "Build answer[i] in two passes: first pass stores prefix products to the left of i. Second pass traverses backward maintaining a running suffix product variable, multiplying into answer[i].",
            solutionCode = """class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        ans[0] = 1;
        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            ans[i] = ans[i] * right;
            right *= nums[i];
        }
        return ans;
    }
}"""
        ),
        DsaProblem(
            id = "arr_004",
            topic = "arrays",
            title = "Maximum Subarray (Kadane's Algorithm)",
            difficulty = "Medium",
            pattern = "Kadane's Dynamic Programming",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given an integer array nums, find the subarray with the largest sum, and return its sum.",
            exampleInput = "nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]",
            exampleOutput = "6 (Subarray [4, -1, 2, 1] has the largest sum = 6)",
            keyInsight = "At each index i, decide whether to extend the previous subarray or start fresh from nums[i]: currentSum = max(nums[i], currentSum + nums[i]). Track overall maximum.",
            solutionCode = """class Solution {
    public int maxSubArray(int[] nums) {
        int maxSoFar = nums[0];
        int currentSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSoFar = Math.max(maxSoFar, currentSum);
        }
        return maxSoFar;
    }
}"""
        ),
        DsaProblem(
            id = "arr_005",
            topic = "arrays",
            title = "3Sum",
            difficulty = "Medium",
            pattern = "Sorting + Two Pointers",
            timeComplexity = "O(N^2)",
            spaceComplexity = "O(1) excluding output",
            description = "Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0. The solution set must not contain duplicate triplets.",
            exampleInput = "nums = [-1, 0, 1, 2, -1, -4]",
            exampleOutput = "[[-1, -1, 2], [-1, 0, 1]]",
            keyInsight = "Sort the array. Fix the first element nums[i] and use two pointers (left = i + 1, right = n - 1) to find pairs summing to -nums[i]. Skip duplicate elements to avoid duplicate triplets.",
            solutionCode = """class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++; right--;
                } else if (sum < 0) left++;
                else right--;
            }
        }
        return res;
    }
}"""
        ),
        DsaProblem(
            id = "arr_006",
            topic = "arrays",
            title = "Container With Most Water",
            difficulty = "Medium",
            pattern = "Two Pointers / Greedy Shrinking",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]). Find two lines that together with the x-axis form a container, such that the container contains the most water. Return the maximum amount of water a container can store.",
            exampleInput = "height = [1, 8, 6, 2, 5, 4, 8, 3, 7]",
            exampleOutput = "49 (between index 1 (height 8) and index 8 (height 7): area = min(8, 7) * (8 - 1) = 49)",
            keyInsight = "Start with two pointers at the widest boundaries (left = 0, right = n - 1). The area is limited by the shorter line: min(h[l], h[r]) * (r - l). Always advance the pointer pointing to the shorter line.",
            solutionCode = """class Solution {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxWater = 0;
        while (left < right) {
            int h = Math.min(height[left], height[right]);
            maxWater = Math.max(maxWater, h * (right - left));
            if (height[left] < height[right]) left++;
            else right--;
        }
        return maxWater;
    }
}"""
        ),
        DsaProblem(
            id = "arr_007",
            topic = "arrays",
            title = "Trapping Rain Water",
            difficulty = "Hard",
            pattern = "Two Pointers / Maximum Boundary",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.",
            exampleInput = "height = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]",
            exampleOutput = "6",
            keyInsight = "Water trapped at index i is min(maxLeft, maxRight) - height[i]. Using two pointers (left, right) and maintaining leftMax and rightMax, process the side with the smaller max bound.",
            solutionCode = """class Solution {
    public int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0, water = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) leftMax = height[left];
                else water += leftMax - height[left];
                left++;
            } else {
                if (height[right] >= rightMax) rightMax = height[right];
                else water += rightMax - height[right];
                right--;
            }
        }
        return water;
    }
}"""
        ),
        DsaProblem(
            id = "arr_008",
            topic = "arrays",
            title = "Merge Intervals",
            difficulty = "Medium",
            pattern = "Sorting + Interval Overlap Merge",
            timeComplexity = "O(N log N)",
            spaceComplexity = "O(N)",
            description = "Given an array of intervals where intervals[i] = [start_i, end_i], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.",
            exampleInput = "intervals = [[1, 3], [2, 6], [8, 10], [15, 18]]",
            exampleOutput = "[[1, 6], [8, 10], [15, 18]]",
            keyInsight = "Sort intervals by start time. Iterate through: if the current interval starts before the previous interval ends, merge them by setting previous end = max(prev.end, curr.end). Otherwise, append.",
            solutionCode = """class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> merged = new ArrayList<>();
        int[] current = intervals[0];
        merged.add(current);
        for (int[] interval : intervals) {
            if (interval[0] <= current[1]) {
                current[1] = Math.max(current[1], interval[1]);
            } else {
                current = interval;
                merged.add(current);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}"""
        ),
        DsaProblem(
            id = "arr_009",
            topic = "arrays",
            title = "Search in Rotated Sorted Array",
            difficulty = "Medium",
            pattern = "Modified Binary Search",
            timeComplexity = "O(log N)",
            spaceComplexity = "O(1)",
            description = "There is an integer array nums sorted in ascending order (with distinct values). Prior to being passed to your function, nums is possibly rotated at an unknown pivot index. Given nums and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.",
            exampleInput = "nums = [4, 5, 6, 7, 0, 1, 2], target = 0",
            exampleOutput = "4",
            keyInsight = "In any rotated sorted array, dividing at mid leaves at least ONE half sorted. Check if left half is sorted (nums[low] <= nums[mid]); if so, check if target falls in that range, otherwise search right half.",
            solutionCode = """class Solution {
    public int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) return mid;
            if (nums[low] <= nums[mid]) {
                if (target >= nums[low] && target < nums[mid]) high = mid - 1;
                else low = mid + 1;
            } else {
                if (target > nums[mid] && target <= nums[high]) low = mid + 1;
                else high = mid - 1;
            }
        }
        return -1;
    }
}"""
        ),
        DsaProblem(
            id = "arr_010",
            topic = "arrays",
            title = "Subarray Sum Equals K",
            difficulty = "Medium",
            pattern = "Prefix Sum + Hash Map",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k. A subarray is a contiguous non-empty sequence of elements within an array.",
            exampleInput = "nums = [1, 1, 1], k = 2",
            exampleOutput = "2",
            keyInsight = "Sum of subarray nums[j..i] is prefixSum[i] - prefixSum[j-1]. If prefixSum[i] - k exists in our map, there exist subarrays ending at i with sum k. Store prefixSum frequencies in map.",
            solutionCode = """class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int currentSum = 0, count = 0;
        for (int num : nums) {
            currentSum += num;
            if (map.containsKey(currentSum - k)) {
                count += map.get(currentSum - k);
            }
            map.put(currentSum, map.getOrDefault(currentSum, 0) + 1);
        }
        return count;
    }
}"""
        )
    )

    val stringProblems = listOf(
        DsaProblem(
            id = "str_001",
            topic = "strings",
            title = "Valid Anagram",
            difficulty = "Easy",
            pattern = "Frequency Array / Hash Map",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1) (fixed 26 chars)",
            description = "Given two strings s and t, return true if t is an anagram of s, and false otherwise. An Anagram is a word formed by rearranging the letters of a different word, typically using all the original letters exactly once.",
            exampleInput = "s = \"anagram\", t = \"nagaram\"",
            exampleOutput = "true",
            keyInsight = "Count character frequencies in s and decrement with t using a fixed-size 26-integer array. If all counts return to zero, t is an anagram.",
            solutionCode = """class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
            counts[t.charAt(i) - 'a']--;
        }
        for (int c : counts) {
            if (c != 0) return false;
        }
        return true;
    }
}"""
        ),
        DsaProblem(
            id = "str_002",
            topic = "strings",
            title = "Longest Substring Without Repeating Characters",
            difficulty = "Medium",
            pattern = "Sliding Window + Last Seen Index Map",
            timeComplexity = "O(N)",
            spaceComplexity = "O(min(N, AlphabetSize))",
            description = "Given a string s, find the length of the longest substring without repeating characters.",
            exampleInput = "s = \"abcabcbb\"",
            exampleOutput = "3 (\"abc\")",
            keyInsight = "Maintain a sliding window [left, right]. Store the last seen index of each character in a map. When a repeated character is encountered, jump left = max(left, lastSeen + 1).",
            solutionCode = """class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int maxLen = 0, left = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (map.containsKey(c)) {
                left = Math.max(left, map.get(c) + 1);
            }
            map.put(c, right);
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}"""
        ),
        DsaProblem(
            id = "str_003",
            topic = "strings",
            title = "Longest Palindromic Substring",
            difficulty = "Medium",
            pattern = "Expand Around Center",
            timeComplexity = "O(N^2)",
            spaceComplexity = "O(1)",
            description = "Given a string s, return the longest palindromic substring in s.",
            exampleInput = "s = \"babad\"",
            exampleOutput = "\"bab\" (or \"aba\")",
            keyInsight = "A palindrome mirrors around its center. There are 2n - 1 possible centers (single character or between two characters). Expand outwards from each center while characters match.",
            solutionCode = """class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expand(s, i, i);
            int len2 = expand(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }
    private int expand(String s, int L, int R) {
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--; R++;
        }
        return R - L - 1;
    }
}"""
        ),
        DsaProblem(
            id = "str_004",
            topic = "strings",
            title = "Group Anagrams",
            difficulty = "Medium",
            pattern = "Sorted Character Key / Hash Map",
            timeComplexity = "O(N * K log K)",
            spaceComplexity = "O(N * K)",
            description = "Given an array of strings strs, group the anagrams together. You can return the answer in any order.",
            exampleInput = "strs = [\"eat\",\"tea\",\"tan\",\"ate\",\"nat\",\"bat\"]",
            exampleOutput = "[[\"bat\"],[\"nat\",\"tan\"],[\"ate\",\"eat\",\"tea\"]]",
            keyInsight = "Sorted anagrams produce identical strings. Use the sorted string (e.g. \"aet\") as the map key, appending original words to the corresponding list.",
            solutionCode = """class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }
}"""
        )
    )

    val linkedListProblems = listOf(
        DsaProblem(
            id = "ll_001",
            topic = "linked_list",
            title = "Reverse Linked List",
            difficulty = "Easy",
            pattern = "In-Place Pointer Manipulation",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given the head of a singly linked list, reverse the list, and return the reversed list.",
            exampleInput = "head = [1, 2, 3, 4, 5]",
            exampleOutput = "[5, 4, 3, 2, 1]",
            keyInsight = "Maintain prev (null) and curr pointers. In each iteration: nextTemp = curr.next, curr.next = prev, prev = curr, curr = nextTemp.",
            solutionCode = """class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
}"""
        ),
        DsaProblem(
            id = "ll_002",
            topic = "linked_list",
            title = "Linked List Cycle (Floyd's Cycle Detection)",
            difficulty = "Easy",
            pattern = "Fast & Slow Pointers (Tortoise & Hare)",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given head, the head of a linked list, determine if the linked list has a cycle in it. Return true if there is some cycle in the linked list, otherwise return false.",
            exampleInput = "head = [3, 2, 0, -4], pos = 1 (tail connects to node index 1)",
            exampleOutput = "true",
            keyInsight = "Advance slow pointer by 1 step and fast pointer by 2 steps. If a cycle exists, the fast pointer will eventually lap and meet the slow pointer.",
            solutionCode = """public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) return false;
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}"""
        ),
        DsaProblem(
            id = "ll_003",
            topic = "linked_list",
            title = "Merge Two Sorted Lists",
            difficulty = "Easy",
            pattern = "Dummy Head Pointer",
            timeComplexity = "O(N + M)",
            spaceComplexity = "O(1)",
            description = "You are given the heads of two sorted linked lists list1 and list2. Merge the two lists into one sorted list by splicing together the nodes of the first two lists. Return the head of the merged linked list.",
            exampleInput = "list1 = [1, 2, 4], list2 = [1, 3, 4]",
            exampleOutput = "[1, 1, 2, 3, 4, 4]",
            keyInsight = "Use a dummy head node to eliminate edge cases when inserting the first element. Advance the pointer with the smaller value until one list is exhausted, then append the remainder.",
            solutionCode = """class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }
        curr.next = (list1 != null) ? list1 : list2;
        return dummy.next;
    }
}"""
        )
    )

    val stackQueueProblems = listOf(
        DsaProblem(
            id = "stk_001",
            topic = "stack",
            title = "Valid Parentheses",
            difficulty = "Easy",
            pattern = "LIFO Stack Matching",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid. Open brackets must be closed by the same type of brackets and in correct order.",
            exampleInput = "s = \"()[]{}\"",
            exampleOutput = "true",
            keyInsight = "Push opening brackets onto a stack. When encountering a closing bracket, verify that the stack is non-empty and the top matches the corresponding opening bracket.",
            solutionCode = """class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(')');
            else if (c == '{') stack.push('}');
            else if (c == '[') stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c) return false;
        }
        return stack.isEmpty();
    }
}"""
        ),
        DsaProblem(
            id = "stk_002",
            topic = "stack",
            title = "Daily Temperatures",
            difficulty = "Medium",
            pattern = "Monotonic Decreasing Stack",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.",
            exampleInput = "temperatures = [73, 74, 75, 71, 69, 72, 76, 73]",
            exampleOutput = "[1, 1, 4, 2, 1, 1, 0, 0]",
            keyInsight = "Store indices in a stack maintaining strictly decreasing temperatures. When a warmer day is found, pop previous days and record the difference (currIndex - prevIndex).",
            solutionCode = """class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prevDay = stack.pop();
                res[prevDay] = i - prevDay;
            }
            stack.push(i);
        }
        return res;
    }
}"""
        ),
        DsaProblem(
            id = "que_001",
            topic = "queue",
            title = "Implement Queue using Stacks",
            difficulty = "Easy",
            pattern = "Two Stacks (Input / Output)",
            timeComplexity = "Amortized O(1) per operation",
            spaceComplexity = "O(N)",
            description = "Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).",
            exampleInput = "push(1), push(2), peek() -> 1, pop() -> 1, empty() -> false",
            exampleOutput = "1, 1, false",
            keyInsight = "Push new elements into stack1. When popping/peeking, if stack2 is empty, transfer all elements from stack1 to stack2, naturally reversing their order to FIFO.",
            solutionCode = """class MyQueue {
    private Stack<Integer> in = new Stack<>();
    private Stack<Integer> out = new Stack<>();

    public void push(int x) { in.push(x); }

    public int pop() {
        peek();
        return out.pop();
    }

    public int peek() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) out.push(in.pop());
        }
        return out.peek();
    }

    public boolean empty() {
        return in.isEmpty() && out.isEmpty();
    }
}"""
        )
    )

    val treeProblems = listOf(
        DsaProblem(
            id = "tree_001",
            topic = "trees",
            title = "Invert Binary Tree",
            difficulty = "Easy",
            pattern = "Recursive Tree Traversal (DFS)",
            timeComplexity = "O(N)",
            spaceComplexity = "O(H) recursion stack",
            description = "Given the root of a binary tree, invert the tree, and return its root.",
            exampleInput = "root = [4, 2, 7, 1, 3, 6, 9]",
            exampleOutput = "[4, 7, 2, 9, 6, 3, 1]",
            keyInsight = "Swap left and right child pointers at each node recursively: invert(root.left) and invert(root.right).",
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
            id = "tree_002",
            topic = "trees",
            title = "Maximum Depth of Binary Tree",
            difficulty = "Easy",
            pattern = "Depth First Search (DFS)",
            timeComplexity = "O(N)",
            spaceComplexity = "O(H)",
            description = "Given the root of a binary tree, return its maximum depth. A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.",
            exampleInput = "root = [3, 9, 20, null, null, 15, 7]",
            exampleOutput = "3",
            keyInsight = "The maximum depth of a binary tree is 1 + max(maxDepth(left), maxDepth(right)). Base case: null node returns 0.",
            solutionCode = """class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}"""
        ),
        DsaProblem(
            id = "tree_003",
            topic = "trees",
            title = "Lowest Common Ancestor of a Binary Search Tree",
            difficulty = "Medium",
            pattern = "BST Properties Traversal",
            timeComplexity = "O(H)",
            spaceComplexity = "O(1)",
            description = "Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.",
            exampleInput = "root = [6, 2, 8, 0, 4, 7, 9], p = 2, q = 8",
            exampleOutput = "6 (The LCA of nodes 2 and 8 is 6)",
            keyInsight = "In a BST, if both p and q are smaller than root, LCA is in the left subtree. If both are greater, LCA is in the right subtree. If they split, the current root is the LCA.",
            solutionCode = """class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (p.val < root.val && q.val < root.val) {
                root = root.left;
            } else if (p.val > root.val && q.val > root.val) {
                root = root.right;
            } else {
                return root;
            }
        }
        return null;
    }
}"""
        )
    )

    val graphProblems = listOf(
        DsaProblem(
            id = "graph_001",
            topic = "graphs",
            title = "Number of Islands",
            difficulty = "Medium",
            pattern = "Matrix DFS / BFS Connected Components",
            timeComplexity = "O(M * N)",
            spaceComplexity = "O(M * N) worst case recursion stack",
            description = "Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.",
            exampleInput = "grid = [[\"1\",\"1\",\"0\",\"0\"],[\"1\",\"1\",\"0\",\"0\"],[\"0\",\"0\",\"1\",\"0\"],[\"0\",\"0\",\"0\",\"1\"]]",
            exampleOutput = "3",
            keyInsight = "Iterate through the grid. When encountering '1', increment island count and trigger DFS to flood-fill and sink all connected land cells ('1' -> '0').",
            solutionCode = """class Solution {
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }
    private void dfs(char[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] != '1') return;
        grid[r][c] = '0';
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }
}"""
        ),
        DsaProblem(
            id = "graph_002",
            topic = "graphs",
            title = "Course Schedule (Cycle Detection in DAG)",
            difficulty = "Medium",
            pattern = "Topological Sort / Kahn's Algorithm",
            timeComplexity = "O(V + E)",
            spaceComplexity = "O(V + E)",
            description = "There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [a_i, b_i] indicates that you must take course b_i first if you want to take course a_i. Return true if you can finish all courses. Otherwise, return false.",
            exampleInput = "numCourses = 2, prerequisites = [[1, 0]]",
            exampleOutput = "true",
            keyInsight = "Calculate in-degrees for each course. Add courses with in-degree 0 to a queue. As each course is completed, decrement in-degrees of dependent courses. If visited count == numCourses, there are no cycles.",
            solutionCode = """class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        int[] inDegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) adj.add(new ArrayList<>());
        for (int[] pre : prerequisites) {
            adj.get(pre[1]).add(pre[0]);
            inDegree[pre[0]]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) q.offer(i);
        }
        int visited = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            visited++;
            for (int next : adj.get(node)) {
                if (--inDegree[next] == 0) q.offer(next);
            }
        }
        return visited == numCourses;
    }
}"""
        )
    )

    val dpProblems = listOf(
        DsaProblem(
            id = "dp_001",
            topic = "dp",
            title = "Climbing Stairs",
            difficulty = "Easy",
            pattern = "Fibonacci 1D DP",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "You are climbing a staircase. It takes n steps to reach the top. Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?",
            exampleInput = "n = 3",
            exampleOutput = "3 (1+1+1, 1+2, 2+1)",
            keyInsight = "To reach step n, you must come from step n-1 or n-2. Thus ways(n) = ways(n-1) + ways(n-2), exactly the Fibonacci sequence.",
            solutionCode = """class Solution {
    public int climbStairs(int n) {
        if (n <= 2) return n;
        int a = 1, b = 2;
        for (int i = 3; i <= n; i++) {
            int c = a + b;
            a = b;
            b = c;
        }
        return b;
    }
}"""
        ),
        DsaProblem(
            id = "dp_002",
            topic = "dp",
            title = "Coin Change",
            difficulty = "Medium",
            pattern = "Unbounded Knapsack 1D DP",
            timeComplexity = "O(N * amount)",
            spaceComplexity = "O(amount)",
            description = "You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money. Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.",
            exampleInput = "coins = [1, 2, 5], amount = 11",
            exampleOutput = "3 (11 = 5 + 5 + 1)",
            keyInsight = "Initialize dp array of size amount + 1 with amount + 1. dp[0] = 0. For each coin, update dp[i] = min(dp[i], dp[i - coin] + 1).",
            solutionCode = """class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i >= coin) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}"""
        )
    )
}
