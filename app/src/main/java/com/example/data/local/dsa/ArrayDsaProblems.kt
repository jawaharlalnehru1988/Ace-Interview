package com.example.data.local.dsa

import com.example.domain.model.DsaProblem

object ArrayDsaProblems {
    fun getProblems(): List<DsaProblem> {
        return problems
    }

    private val problems = listOf(
        DsaProblem(
            id = "arr_001",
            topic = "arrays",
            title = "Two Sum",
            difficulty = "Easy",
            pattern = "Hash Map / Complement Lookup",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.",
            exampleInput = "nums = [2, 7, 11, 15], target = 9",
            exampleOutput = "[0, 1]",
            keyInsight = "Use a hash map to store each number and its index. For each element, check if target - nums[i] is already in the map.",
            solutionCode = """class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int comp = target - nums[i];
            if (map.containsKey(comp)) return new int[]{map.get(comp), i};
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
            pattern = "Greedy / Single Pass",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Find the maximum profit you can achieve by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.",
            exampleInput = "prices = [7, 1, 5, 3, 6, 4]",
            exampleOutput = "5 (Buy day 2 at 1, sell day 5 at 6)",
            keyInsight = "Track the minimum price seen so far. At each step, calculate potential profit (price - minPrice) and update maxProfit.",
            solutionCode = """class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE, maxProfit = 0;
        for (int p : prices) {
            minPrice = Math.min(minPrice, p);
            maxProfit = Math.max(maxProfit, p - minPrice);
        }
        return maxProfit;
    }
}"""
        ),
        DsaProblem(
            id = "arr_003",
            topic = "arrays",
            title = "Contains Duplicate",
            difficulty = "Easy",
            pattern = "Hash Set",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.",
            exampleInput = "nums = [1, 2, 3, 1]",
            exampleOutput = "true",
            keyInsight = "Add elements to a HashSet. If an element is already present in the set, a duplicate exists.",
            solutionCode = """class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int x : nums) {
            if (!seen.add(x)) return true;
        }
        return false;
    }
}"""
        ),
        DsaProblem(
            id = "arr_004",
            topic = "arrays",
            title = "Product of Array Except Self",
            difficulty = "Medium",
            pattern = "Prefix & Suffix Products",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i] without using division.",
            exampleInput = "nums = [1, 2, 3, 4]",
            exampleOutput = "[24, 12, 8, 6]",
            keyInsight = "Build the result array using left running products in a first pass, then multiply by right running products in a second reverse pass.",
            solutionCode = """class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) res[i] = res[i - 1] * nums[i - 1];
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }
}"""
        ),
        DsaProblem(
            id = "arr_005",
            topic = "arrays",
            title = "Maximum Subarray (Kadane's Algorithm)",
            difficulty = "Medium",
            pattern = "Dynamic Programming / Greedy",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given an integer array nums, find the subarray with the largest sum, and return its sum.",
            exampleInput = "nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]",
            exampleOutput = "6 ([4, -1, 2, 1])",
            keyInsight = "At each element, decide whether to add it to the running sum or start a fresh subarray if running sum dropped below 0.",
            solutionCode = """class Solution {
    public int maxSubArray(int[] nums) {
        int max = nums[0], cur = nums[0];
        for (int i = 1; i < nums.length; i++) {
            cur = Math.max(nums[i], cur + nums[i]);
            max = Math.max(max, cur);
        }
        return max;
    }
}"""
        ),
        DsaProblem(
            id = "arr_006",
            topic = "arrays",
            title = "Maximum Product Subarray",
            difficulty = "Medium",
            pattern = "Dynamic Programming / Min-Max Tracking",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given an integer array nums, find a contiguous non-empty subarray within the array that has the largest product.",
            exampleInput = "nums = [2, 3, -2, 4]",
            exampleOutput = "6 ([2, 3])",
            keyInsight = "Maintain both maxProduct and minProduct ending at current index because a negative number can turn the minimum into the maximum.",
            solutionCode = """class Solution {
    public int maxProduct(int[] nums) {
        int max = nums[0], min = nums[0], res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) { int t = max; max = min; min = t; }
            max = Math.max(nums[i], max * nums[i]);
            min = Math.min(nums[i], min * nums[i]);
            res = Math.max(res, max);
        }
        return res;
    }
}"""
        ),
        DsaProblem(
            id = "arr_007",
            topic = "arrays",
            title = "Find Minimum in Rotated Sorted Array",
            difficulty = "Medium",
            pattern = "Binary Search",
            timeComplexity = "O(log N)",
            spaceComplexity = "O(1)",
            description = "Given the sorted rotated array nums of unique elements, return the minimum element of this array.",
            exampleInput = "nums = [3, 4, 5, 1, 2]",
            exampleOutput = "1",
            keyInsight = "If mid element is greater than rightmost element, the minimum must lie strictly in the right half.",
            solutionCode = """class Solution {
    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > nums[r]) l = mid + 1;
            else r = mid;
        }
        return nums[l];
    }
}"""
        ),
        DsaProblem(
            id = "arr_008",
            topic = "arrays",
            title = "Search in Rotated Sorted Array",
            difficulty = "Medium",
            pattern = "Binary Search",
            timeComplexity = "O(log N)",
            spaceComplexity = "O(1)",
            description = "Given array nums sorted in ascending order (with distinct values) and rotated at an unknown pivot, find index of target.",
            exampleInput = "nums = [4, 5, 6, 7, 0, 1, 2], target = 0",
            exampleOutput = "4",
            keyInsight = "One half of the rotated array is always sorted. Determine which half is sorted and check if target falls within its range.",
            solutionCode = """class Solution {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) return mid;
            if (nums[l] <= nums[mid]) {
                if (target >= nums[l] && target < nums[mid]) r = mid - 1;
                else l = mid + 1;
            } else {
                if (target > nums[mid] && target <= nums[r]) l = mid + 1;
                else r = mid - 1;
            }
        }
        return -1;
    }
}"""
        ),
        DsaProblem(
            id = "arr_009",
            topic = "arrays",
            title = "3Sum",
            difficulty = "Medium",
            pattern = "Two Pointers / Sorting",
            timeComplexity = "O(N^2)",
            spaceComplexity = "O(1)",
            description = "Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.",
            exampleInput = "nums = [-1, 0, 1, 2, -1, -4]",
            exampleOutput = "[[-1, -1, 2], [-1, 0, 1]]",
            keyInsight = "Sort the array. Fix the first number, and use two pointers (left and right) to find pairs summing to -nums[i]. Skip duplicates.",
            solutionCode = """class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l] == nums[l + 1]) l++;
                    while (l < r && nums[r] == nums[r - 1]) r--;
                    l++; r--;
                } else if (sum < 0) l++;
                else r--;
            }
        }
        return res;
    }
}"""
        ),
        DsaProblem(
            id = "arr_010",
            topic = "arrays",
            title = "Container With Most Water",
            difficulty = "Medium",
            pattern = "Two Pointers",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given n non-negative integers height where each represents a point at coordinate (i, height[i]), find two lines that form a container with the most water.",
            exampleInput = "height = [1, 8, 6, 2, 5, 4, 8, 3, 7]",
            exampleOutput = "49",
            keyInsight = "Place pointers at both ends. Compute area = min(height[l], height[r]) * (r - l). Move the pointer with smaller height inward.",
            solutionCode = """class Solution {
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1, max = 0;
        while (l < r) {
            int h = Math.min(height[l], height[r]);
            max = Math.max(max, h * (r - l));
            if (height[l] < height[r]) l++;
            else r--;
        }
        return max;
    }
}"""
        ),
        DsaProblem(
            id = "arr_011",
            topic = "arrays",
            title = "Remove Duplicates from Sorted Array",
            difficulty = "Easy",
            pattern = "Two Pointers",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Remove the duplicates in-place such that each unique element appears only once.",
            exampleInput = "nums = [1, 1, 2]",
            exampleOutput = "2, nums = [1, 2, _]",
            keyInsight = "Maintain write index k. Only increment k and write when nums[i] != nums[k-1].",
            solutionCode = """class Solution {
    // Remove Duplicates from Sorted Array
    public static void solve() {}
    // Core solution logic:
    // int k = 1; for (int i = 1; i < nums.length; i++) if (nums[i] != nums[k-1]) nums[k++] = nums[i]; return k;
}"""
        ),
        DsaProblem(
            id = "arr_012",
            topic = "arrays",
            title = "Remove Element",
            difficulty = "Easy",
            pattern = "Two Pointers",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Remove all instances of val in nums in-place and return the new length.",
            exampleInput = "nums = [3, 2, 2, 3], val = 3",
            exampleOutput = "2, nums = [2, 2, _, _]",
            keyInsight = "Iterate through array; write nums[i] to index k only when nums[i] != val.",
            solutionCode = """class Solution {
    // Remove Element
    public static void solve() {}
    // Core solution logic:
    // int k = 0; for (int x : nums) if (x != val) nums[k++] = x; return k;
}"""
        ),
        DsaProblem(
            id = "arr_013",
            topic = "arrays",
            title = "Move Zeroes",
            difficulty = "Easy",
            pattern = "Two Pointers",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Move all 0's to the end of array while maintaining the relative order of non-zero elements.",
            exampleInput = "nums = [0, 1, 0, 3, 12]",
            exampleOutput = "[1, 3, 12, 0, 0]",
            keyInsight = "Collect all non-zero elements to front, then fill remainder of array with zeroes.",
            solutionCode = """class Solution {
    // Move Zeroes
    public static void solve() {}
    // Core solution logic:
    // int k = 0; for (int x : nums) if (x != 0) nums[k++] = x; while (k < nums.length) nums[k++] = 0;
}"""
        ),
        DsaProblem(
            id = "arr_014",
            topic = "arrays",
            title = "Plus One",
            difficulty = "Easy",
            pattern = "Math / Carry",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Increment the large integer represented by the integer array digits by one.",
            exampleInput = "digits = [1, 2, 9]",
            exampleOutput = "[1, 3, 0]",
            keyInsight = "Traverse backwards from least significant digit. If digit is 9 set to 0, otherwise add 1 and return.",
            solutionCode = """class Solution {
    // Plus One
    public static void solve() {}
    // Core solution logic:
    // for (int i = digits.length - 1; i >= 0; i--) { if (digits[i] < 9) { digits[i]++; return digits; } digits[i] = 0; } int[] res = new int[digits.length + 1]; res[0] = 1; return res;
}"""
        ),
        DsaProblem(
            id = "arr_015",
            topic = "arrays",
            title = "Merge Sorted Array",
            difficulty = "Easy",
            pattern = "Three Pointers / Backwards Fill",
            timeComplexity = "O(M+N)",
            spaceComplexity = "O(1)",
            description = "Merge nums2 into nums1 as one sorted array in-place, filling nums1 from the back.",
            exampleInput = "nums1 = [1, 2, 3, 0, 0, 0], m = 3, nums2 = [2, 5, 6], n = 3",
            exampleOutput = "[1, 2, 2, 3, 5, 6]",
            keyInsight = "Fill from back (p = m+n-1) comparing largest elements of nums1 and nums2 to prevent overwriting.",
            solutionCode = """class Solution {
    // Merge Sorted Array
    public static void solve() {}
    // Core solution logic:
    // int i = m - 1, j = n - 1, p = m + n - 1; while (j >= 0) { if (i >= 0 && nums1[i] > nums2[j]) nums1[p--] = nums1[i--]; else nums1[p--] = nums2[j--]; }
}"""
        ),
        DsaProblem(
            id = "arr_016",
            topic = "arrays",
            title = "Rotate Array",
            difficulty = "Medium",
            pattern = "Reverse Array Trick",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Rotate the array to the right by k steps, where k is non-negative.",
            exampleInput = "nums = [1, 2, 3, 4, 5, 6, 7], k = 3",
            exampleOutput = "[5, 6, 7, 1, 2, 3, 4]",
            keyInsight = "Reverse entire array, reverse first k elements, then reverse remaining n-k elements.",
            solutionCode = """class Solution {
    // Rotate Array
    public static void solve() {}
    // Core solution logic:
    // k %= nums.length; reverse(nums, 0, nums.length - 1); reverse(nums, 0, k - 1); reverse(nums, k, nums.length - 1);
}"""
        ),
        DsaProblem(
            id = "arr_017",
            topic = "arrays",
            title = "Subarray Sum Equals K",
            difficulty = "Medium",
            pattern = "Prefix Sum + Hash Map",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.",
            exampleInput = "nums = [1, 1, 1], k = 2",
            exampleOutput = "2",
            keyInsight = "If prefixSum - k exists in map, it indicates subarrays ending at current index sum to k.",
            solutionCode = """class Solution {
    // Subarray Sum Equals K
    public static void solve() {}
    // Core solution logic:
    // Map<Integer, Integer> map = new HashMap<>(); map.put(0, 1); int sum = 0, count = 0; for (int x : nums) { sum += x; count += map.getOrDefault(sum - k, 0); map.put(sum, map.getOrDefault(sum, 0) + 1); } return count;
}"""
        ),
        DsaProblem(
            id = "arr_018",
            topic = "arrays",
            title = "Find All Numbers Disappeared in an Array",
            difficulty = "Easy",
            pattern = "Index As Hash Key",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given an array nums of n integers where nums[i] is in the range [1, n], return an array of all integers in [1, n] that do not appear in nums.",
            exampleInput = "nums = [4, 3, 2, 7, 8, 2, 3, 1]",
            exampleOutput = "[5, 6]",
            keyInsight = "Negate the value at index Math.abs(nums[i]) - 1. Any positive indices at the end were never visited.",
            solutionCode = """class Solution {
    // Find All Numbers Disappeared in an Array
    public static void solve() {}
    // Core solution logic:
    // for (int i = 0; i < nums.length; i++) { int idx = Math.abs(nums[i]) - 1; if (nums[idx] > 0) nums[idx] = -nums[idx]; } List<Integer> res = new ArrayList<>(); for (int i = 0; i < nums.length; i++) if (nums[i] > 0) res.add(i + 1); return res;
}"""
        ),
        DsaProblem(
            id = "arr_019",
            topic = "arrays",
            title = "Intersection of Two Arrays",
            difficulty = "Easy",
            pattern = "Hash Set",
            timeComplexity = "O(N+M)",
            spaceComplexity = "O(N)",
            description = "Given two integer arrays nums1 and nums2, return an array of their unique intersection.",
            exampleInput = "nums1 = [1, 2, 2, 1], nums2 = [2, 2]",
            exampleOutput = "[2]",
            keyInsight = "Store unique elements of nums1 in a set, check containment for nums2 and collect matches into output set.",
            solutionCode = """class Solution {
    // Intersection of Two Arrays
    public static void solve() {}
    // Core solution logic:
    // Set<Integer> set1 = new HashSet<>(), res = new HashSet<>(); for (int x : nums1) set1.add(x); for (int x : nums2) if (set1.contains(x)) res.add(x); return res.stream().mapToInt(i -> i).toArray();
}"""
        ),
        DsaProblem(
            id = "arr_020",
            topic = "arrays",
            title = "Intersection of Two Arrays II",
            difficulty = "Easy",
            pattern = "Hash Map / Frequency Count",
            timeComplexity = "O(N+M)",
            spaceComplexity = "O(min(N,M))",
            description = "Given two integer arrays nums1 and nums2, return an array of their intersection with duplicate counts preserved.",
            exampleInput = "nums1 = [1, 2, 2, 1], nums2 = [2, 2]",
            exampleOutput = "[2, 2]",
            keyInsight = "Count frequencies of nums1 in HashMap. Decrement count whenever matching element is found in nums2.",
            solutionCode = """class Solution {
    // Intersection of Two Arrays II
    public static void solve() {}
    // Core solution logic:
    // Map<Integer, Integer> map = new HashMap<>(); for (int x : nums1) map.put(x, map.getOrDefault(x, 0) + 1); List<Integer> list = new ArrayList<>(); for (int x : nums2) if (map.getOrDefault(x, 0) > 0) { list.add(x); map.put(x, map.get(x) - 1); } return list.stream().mapToInt(i -> i).toArray();
}"""
        ),
        DsaProblem(
            id = "arr_021",
            topic = "arrays",
            title = "Squares of a Sorted Array",
            difficulty = "Easy",
            pattern = "Two Pointers",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.",
            exampleInput = "nums = [-4, -1, 0, 3, 10]",
            exampleOutput = "[0, 1, 9, 16, 100]",
            keyInsight = "Largest squares are at the extreme ends. Use two pointers and populate result backwards from end.",
            solutionCode = """class Solution {
    // Squares of a Sorted Array
    public static void solve() {}
    // Core solution logic:
    // int n = nums.length, l = 0, r = n - 1, p = n - 1; int[] res = new int[n]; while (l <= r) { if (Math.abs(nums[l]) > Math.abs(nums[r])) res[p--] = nums[l] * nums[l++]; else res[p--] = nums[r] * nums[r--]; } return res;
}"""
        ),
        DsaProblem(
            id = "arr_022",
            topic = "arrays",
            title = "Running Sum of 1d Array",
            difficulty = "Easy",
            pattern = "Prefix Sum",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given an array nums, we define a running sum of an array as runningSum[i] = sum(nums[0]...nums[i]).",
            exampleInput = "nums = [1, 2, 3, 4]",
            exampleOutput = "[1, 3, 6, 10]",
            keyInsight = "Accumulate running sum in-place by adding previous element: nums[i] += nums[i-1].",
            solutionCode = """class Solution {
    // Running Sum of 1d Array
    public static void solve() {}
    // Core solution logic:
    // for (int i = 1; i < nums.length; i++) nums[i] += nums[i - 1]; return nums;
}"""
        ),
        DsaProblem(
            id = "arr_023",
            topic = "arrays",
            title = "Find Pivot Index",
            difficulty = "Easy",
            pattern = "Prefix Sum Balance",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Calculate the pivot index where the sum of all the numbers strictly to the left equals the sum of numbers strictly to the right.",
            exampleInput = "nums = [1, 7, 3, 6, 5, 6]",
            exampleOutput = "3 (left sum 1+7+3 = 11, right sum 5+6 = 11)",
            keyInsight = "Compute total sum. Left sum and right sum satisfy: rightSum = totalSum - leftSum - nums[i].",
            solutionCode = """class Solution {
    // Find Pivot Index
    public static void solve() {}
    // Core solution logic:
    // int total = 0, left = 0; for (int x : nums) total += x; for (int i = 0; i < nums.length; i++) { if (left == total - left - nums[i]) return i; left += nums[i]; } return -1;
}"""
        ),
        DsaProblem(
            id = "arr_024",
            topic = "arrays",
            title = "Majority Element",
            difficulty = "Easy",
            pattern = "Boyer-Moore Voting",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given an array nums of size n, return the majority element that appears more than ⌊n / 2⌋ times.",
            exampleInput = "nums = [3, 2, 3]",
            exampleOutput = "3",
            keyInsight = "Candidate cancellation: increment count on match, decrement on mismatch. If count hits 0, choose new candidate.",
            solutionCode = """class Solution {
    // Majority Element
    public static void solve() {}
    // Core solution logic:
    // int candidate = nums[0], count = 0; for (int x : nums) { if (count == 0) candidate = x; count += (x == candidate) ? 1 : -1; } return candidate;
}"""
        ),
        DsaProblem(
            id = "arr_025",
            topic = "arrays",
            title = "Sort Colors (Dutch National Flag)",
            difficulty = "Medium",
            pattern = "Three Pointers / In-Place Partition",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Sort an array nums containing objects colored red (0), white (1), and blue (2) in-place without using sort library.",
            exampleInput = "nums = [2, 0, 2, 1, 1, 0]",
            exampleOutput = "[0, 0, 1, 1, 2, 2]",
            keyInsight = "Three pointers: low for 0s boundary, high for 2s boundary, mid for scanning.",
            solutionCode = """class Solution {
    // Sort Colors (Dutch National Flag)
    public static void solve() {}
    // Core solution logic:
    // int low = 0, mid = 0, high = nums.length - 1; while (mid <= high) { if (nums[mid] == 0) { int t = nums[low]; nums[low++] = nums[mid]; nums[mid++] = t; } else if (nums[mid] == 1) mid++; else { int t = nums[high]; nums[high--] = nums[mid]; nums[mid] = t; } }
}"""
        ),
        DsaProblem(
            id = "arr_026",
            topic = "arrays",
            title = "Pascal's Triangle",
            difficulty = "Easy",
            pattern = "Dynamic Programming / Matrix",
            timeComplexity = "O(N^2)",
            spaceComplexity = "O(1)",
            description = "Given an integer numRows, return the first numRows of Pascal's triangle.",
            exampleInput = "numRows = 5",
            exampleOutput = "[[1], [1,1], [1,2,1], [1,3,3,1], [1,4,6,4,1]]",
            keyInsight = "Each interior element row[j] is the sum of prevRow[j-1] + prevRow[j]. Outer elements are always 1.",
            solutionCode = """class Solution {
    // Pascal's Triangle
    public static void solve() {}
    // Core solution logic:
    // List<List<Integer>> res = new ArrayList<>(); for (int i = 0; i < numRows; i++) { List<Integer> row = new ArrayList<>(); for (int j = 0; j <= i; j++) { if (j == 0 || j == i) row.add(1); else row.add(res.get(i-1).get(j-1) + res.get(i-1).get(j)); } res.add(row); } return res;
}"""
        ),
        DsaProblem(
            id = "arr_027",
            topic = "arrays",
            title = "Pascal's Triangle II",
            difficulty = "Easy",
            pattern = "Single Row DP",
            timeComplexity = "O(N^2)",
            spaceComplexity = "O(N)",
            description = "Given an integer rowIndex, return the rowIndex-th (0-indexed) row of the Pascal's triangle using only O(rowIndex) extra space.",
            exampleInput = "rowIndex = 3",
            exampleOutput = "[1, 3, 3, 1]",
            keyInsight = "Update row from right to left in-place to avoid overwriting values needed for the next computation.",
            solutionCode = """class Solution {
    // Pascal's Triangle II
    public static void solve() {}
    // Core solution logic:
    // List<Integer> row = new ArrayList<>(); row.add(1); for (int i = 1; i <= rowIndex; i++) { for (int j = i - 1; j >= 1; j--) row.set(j, row.get(j) + row.get(j - 1)); row.add(1); } return row;
}"""
        ),
        DsaProblem(
            id = "arr_028",
            topic = "arrays",
            title = "Missing Number",
            difficulty = "Easy",
            pattern = "Bitwise XOR / Gauss Sum",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing.",
            exampleInput = "nums = [3, 0, 1]",
            exampleOutput = "2",
            keyInsight = "XOR all numbers from 0 to n and XOR all array elements. Identical numbers cancel out (x ^ x = 0), leaving missing.",
            solutionCode = """class Solution {
    // Missing Number
    public static void solve() {}
    // Core solution logic:
    // int xor = nums.length; for (int i = 0; i < nums.length; i++) xor ^= i ^ nums[i]; return xor;
}"""
        ),
        DsaProblem(
            id = "arr_029",
            topic = "arrays",
            title = "Single Number",
            difficulty = "Easy",
            pattern = "Bitwise XOR",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Every element in nums appears twice except for one. Find that single one.",
            exampleInput = "nums = [4, 1, 2, 1, 2]",
            exampleOutput = "4",
            keyInsight = "XOR of a number with itself is 0, and XOR with 0 is the number itself. Cumulative XOR yields the unique element.",
            solutionCode = """class Solution {
    // Single Number
    public static void solve() {}
    // Core solution logic:
    // int res = 0; for (int x : nums) res ^= x; return res;
}"""
        ),
        DsaProblem(
            id = "arr_030",
            topic = "arrays",
            title = "Next Permutation",
            difficulty = "Medium",
            pattern = "Array Rearrangement",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Rearrange numbers into the lexicographically next greater permutation of numbers.",
            exampleInput = "nums = [1, 2, 3]",
            exampleOutput = "[1, 3, 2]",
            keyInsight = "Find first decreasing element from right (i), swap with element just larger than it from right (j), reverse suffix.",
            solutionCode = """class Solution {
    // Next Permutation
    public static void solve() {}
    // Core solution logic:
    // int i = nums.length - 2; while (i >= 0 && nums[i] >= nums[i+1]) i--; if (i >= 0) { int j = nums.length - 1; while (nums[j] <= nums[i]) j--; swap(nums, i, j); } reverse(nums, i + 1, nums.length - 1);
}"""
        ),
        DsaProblem(
            id = "arr_031",
            topic = "arrays",
            title = "Longest Consecutive Sequence",
            difficulty = "Medium",
            pattern = "Hash Set",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence in O(N) time.",
            exampleInput = "nums = [100, 4, 200, 1, 3, 2]",
            exampleOutput = "4 ([1, 2, 3, 4])",
            keyInsight = "Add all elements to HashSet. Only start streak count if num - 1 is NOT in set (identifying sequence start).",
            solutionCode = """class Solution {
    // Longest Consecutive Sequence
    public static void solve() {}
    // Core solution logic:
    // Set<Integer> set = new HashSet<>(); for (int x : nums) set.add(x); int max = 0; for (int x : set) { if (!set.contains(x - 1)) { int cur = x, len = 1; while (set.contains(cur + 1)) { cur++; len++; } max = Math.max(max, len); } } return max;
}"""
        ),
        DsaProblem(
            id = "arr_032",
            topic = "arrays",
            title = "Set Matrix Zeroes",
            difficulty = "Medium",
            pattern = "Matrix In-Place Markers",
            timeComplexity = "O(M*N)",
            spaceComplexity = "O(1)",
            description = "Given an m x n integer matrix, if an element is 0, set its entire row and column to 0's in-place.",
            exampleInput = "matrix = [[1,1,1],[1,0,1],[1,1,1]]",
            exampleOutput = "[[1,0,1],[0,0,0],[1,0,1]]",
            keyInsight = "Use the first row and first column as marker flags to achieve O(1) auxiliary space.",
            solutionCode = """class Solution {
    // Set Matrix Zeroes
    public static void solve() {}
    // Core solution logic:
    // boolean firstRowZero = false, firstColZero = false; int m = matrix.length, n = matrix[0].length; for (int i = 0; i < m; i++) if (matrix[i][0] == 0) firstColZero = true; for (int j = 0; j < n; j++) if (matrix[0][j] == 0) firstRowZero = true; for (int i = 1; i < m; i++) for (int j = 1; j < n; j++) if (matrix[i][j] == 0) { matrix[i][0] = 0; matrix[0][j] = 0; } for (int i = 1; i < m; i++) for (int j = 1; j < n; j++) if (matrix[i][0] == 0 || matrix[0][j] == 0) matrix[i][j] = 0; if (firstRowZero) for (int j = 0; j < n; j++) matrix[0][j] = 0; if (firstColZero) for (int i = 0; i < m; i++) matrix[i][0] = 0;
}"""
        ),
        DsaProblem(
            id = "arr_033",
            topic = "arrays",
            title = "Spiral Matrix",
            difficulty = "Medium",
            pattern = "Matrix Simulation / 4 Boundaries",
            timeComplexity = "O(M*N)",
            spaceComplexity = "O(1)",
            description = "Given an m x n matrix, return all elements of the matrix in spiral order.",
            exampleInput = "matrix = [[1,2,3],[4,5,6],[7,8,9]]",
            exampleOutput = "[1, 2, 3, 6, 9, 8, 7, 4, 5]",
            keyInsight = "Maintain 4 boundary pointers: top, bottom, left, right. Traverse top row, right col, bottom row, left col.",
            solutionCode = """class Solution {
    // Spiral Matrix
    public static void solve() {}
    // Core solution logic:
    // List<Integer> res = new ArrayList<>(); int top = 0, bottom = matrix.length - 1, left = 0, right = matrix[0].length - 1; while (top <= bottom && left <= right) { for (int j = left; j <= right; j++) res.add(matrix[top][j]); top++; for (int i = top; i <= bottom; i++) res.add(matrix[i][right]); right--; if (top <= bottom) { for (int j = right; j >= left; j--) res.add(matrix[bottom][j]); bottom--; } if (left <= right) { for (int i = bottom; i >= top; i--) res.add(matrix[i][left]); left++; } } return res;
}"""
        ),
        DsaProblem(
            id = "arr_034",
            topic = "arrays",
            title = "Rotate Image (90 Degrees Clockwise)",
            difficulty = "Medium",
            pattern = "Transpose + Reverse",
            timeComplexity = "O(N^2)",
            spaceComplexity = "O(1)",
            description = "Rotate an n x n 2D matrix representing an image by 90 degrees clockwise in-place.",
            exampleInput = "matrix = [[1,2,3],[4,5,6],[7,8,9]]",
            exampleOutput = "[[7,4,1],[8,5,2],[9,6,3]]",
            keyInsight = "Transpose the matrix (swap matrix[i][j] with matrix[j][i]) then reverse each row horizontally.",
            solutionCode = """class Solution {
    // Rotate Image (90 Degrees Clockwise)
    public static void solve() {}
    // Core solution logic:
    // int n = matrix.length; for (int i = 0; i < n; i++) for (int j = i + 1; j < n; j++) { int t = matrix[i][j]; matrix[i][j] = matrix[j][i]; matrix[j][i] = t; } for (int i = 0; i < n; i++) { int l = 0, r = n - 1; while (l < r) { int t = matrix[i][l]; matrix[i][l++] = matrix[i][r]; matrix[i][r--] = t; } }
}"""
        ),
        DsaProblem(
            id = "arr_035",
            topic = "arrays",
            title = "Merge Intervals",
            difficulty = "Medium",
            pattern = "Sorting Intervals",
            timeComplexity = "O(N log N)",
            spaceComplexity = "O(N)",
            description = "Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals.",
            exampleInput = "intervals = [[1,3],[2,6],[8,10],[15,18]]",
            exampleOutput = "[[1,6],[8,10],[15,18]]",
            keyInsight = "Sort intervals by start time. If current start <= previous end, merge by updating prev end = max(prev end, cur end).",
            solutionCode = """class Solution {
    // Merge Intervals
    public static void solve() {}
    // Core solution logic:
    // Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0])); List<int[]> res = new ArrayList<>(); for (int[] inv : intervals) { if (res.isEmpty() || res.get(res.size() - 1)[1] < inv[0]) res.add(inv); else res.get(res.size() - 1)[1] = Math.max(res.get(res.size() - 1)[1], inv[1]); } return res.toArray(new int[res.size()][]);
}"""
        ),
        DsaProblem(
            id = "arr_036",
            topic = "arrays",
            title = "Insert Interval",
            difficulty = "Medium",
            pattern = "Interval Linear Scan",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals.",
            exampleInput = "intervals = [[1,3],[6,9]], newInterval = [2,5]",
            exampleOutput = "[[1,5],[6,9]]",
            keyInsight = "Add intervals ending before newInterval starts. Merge overlapping intervals into newInterval. Add remaining.",
            solutionCode = """class Solution {
    // Insert Interval
    public static void solve() {}
    // Core solution logic:
    // List<int[]> res = new ArrayList<>(); int i = 0, n = intervals.length; while (i < n && intervals[i][1] < newInterval[0]) res.add(intervals[i++]); while (i < n && intervals[i][0] <= newInterval[1]) { newInterval[0] = Math.min(newInterval[0], intervals[i][0]); newInterval[1] = Math.max(newInterval[1], intervals[i][1]); i++; } res.add(newInterval); while (i < n) res.add(intervals[i++]); return res.toArray(new int[res.size()][]);
}"""
        ),
        DsaProblem(
            id = "arr_037",
            topic = "arrays",
            title = "Non-overlapping Intervals",
            difficulty = "Medium",
            pattern = "Greedy / Earliest End Time",
            timeComplexity = "O(N log N)",
            spaceComplexity = "O(1)",
            description = "Given an array of intervals, return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.",
            exampleInput = "intervals = [[1,2],[2,3],[3,4],[1,3]]",
            exampleOutput = "1",
            keyInsight = "Greedy strategy: sort by end time. Pick intervals that finish earliest to leave maximum room for others.",
            solutionCode = """class Solution {
    // Non-overlapping Intervals
    public static void solve() {}
    // Core solution logic:
    // Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1])); int count = 0, end = intervals[0][1]; for (int i = 1; i < intervals.length; i++) { if (intervals[i][0] < end) count++; else end = intervals[i][1]; } return count;
}"""
        ),
        DsaProblem(
            id = "arr_038",
            topic = "arrays",
            title = "Meeting Rooms",
            difficulty = "Easy",
            pattern = "Sorting",
            timeComplexity = "O(N log N)",
            spaceComplexity = "O(1)",
            description = "Given an array of meeting time intervals where intervals[i] = [start, end], determine if a person could attend all meetings.",
            exampleInput = "intervals = [[0,30],[5,10],[15,20]]",
            exampleOutput = "false",
            keyInsight = "Sort meetings by start time. Check if any meeting starts before the previous meeting finishes.",
            solutionCode = """class Solution {
    // Meeting Rooms
    public static void solve() {}
    // Core solution logic:
    // Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0])); for (int i = 1; i < intervals.length; i++) if (intervals[i][0] < intervals[i - 1][1]) return false; return true;
}"""
        ),
        DsaProblem(
            id = "arr_039",
            topic = "arrays",
            title = "Summary Ranges",
            difficulty = "Easy",
            pattern = "Two Pointers / Interval Grouping",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Return the smallest sorted list of ranges that cover all the numbers in the array exactly.",
            exampleInput = "nums = [0, 1, 2, 4, 5, 7]",
            exampleOutput = "[\"0->2\",\"4->5\",\"7\"]",
            keyInsight = "Find contiguous streaks where nums[i] == nums[i-1] + 1. Emit range string when streak ends.",
            solutionCode = """class Solution {
    // Summary Ranges
    public static void solve() {}
    // Core solution logic:
    // List<String> res = new ArrayList<>(); for (int i = 0; i < nums.length; i++) { int start = nums[i]; while (i + 1 < nums.length && nums[i + 1] == nums[i] + 1) i++; if (start == nums[i]) res.add(String.valueOf(start)); else res.add(start + "->" + nums[i]); } return res;
}"""
        ),
        DsaProblem(
            id = "arr_040",
            topic = "arrays",
            title = "Search Insert Position",
            difficulty = "Easy",
            pattern = "Binary Search",
            timeComplexity = "O(log N)",
            spaceComplexity = "O(1)",
            description = "Given a sorted array of distinct integers and a target value, return the index if target is found. If not, return index where it would be inserted in order.",
            exampleInput = "nums = [1, 3, 5, 6], target = 5",
            exampleOutput = "2",
            keyInsight = "Standard binary search. If target is not present, low pointer points to the correct insert location.",
            solutionCode = """class Solution {
    // Search Insert Position
    public static void solve() {}
    // Core solution logic:
    // int l = 0, r = nums.length - 1; while (l <= r) { int mid = l + (r - l) / 2; if (nums[mid] == target) return mid; else if (nums[mid] < target) l = mid + 1; else r = mid - 1; } return l;
}"""
        ),
        DsaProblem(
            id = "arr_041",
            topic = "arrays",
            title = "Find Peak Element",
            difficulty = "Medium",
            pattern = "Binary Search",
            timeComplexity = "O(log N)",
            spaceComplexity = "O(1)",
            description = "A peak element is an element that is strictly greater than its neighbors. Find a peak element index in O(log N) time.",
            exampleInput = "nums = [1, 2, 3, 1]",
            exampleOutput = "2 (index of 3)",
            keyInsight = "If nums[mid] < nums[mid+1], a peak is guaranteed to exist on the right side. Otherwise on left side.",
            solutionCode = """class Solution {
    // Find Peak Element
    public static void solve() {}
    // Core solution logic:
    // int l = 0, r = nums.length - 1; while (l < r) { int mid = l + (r - l) / 2; if (nums[mid] < nums[mid + 1]) l = mid + 1; else r = mid; } return l;
}"""
        ),
        DsaProblem(
            id = "arr_042",
            topic = "arrays",
            title = "Kth Largest Element in an Array",
            difficulty = "Medium",
            pattern = "Min Heap / QuickSelect",
            timeComplexity = "O(N log K)",
            spaceComplexity = "O(K)",
            description = "Given an integer array nums and an integer k, return the kth largest element in the array.",
            exampleInput = "nums = [3, 2, 1, 5, 6, 4], k = 2",
            exampleOutput = "5",
            keyInsight = "Maintain a min-heap of size k. Heap top always stores the kth largest element seen so far.",
            solutionCode = """class Solution {
    // Kth Largest Element in an Array
    public static void solve() {}
    // Core solution logic:
    // PriorityQueue<Integer> pq = new PriorityQueue<>(); for (int x : nums) { pq.offer(x); if (pq.size() > k) pq.poll(); } return pq.peek();
}"""
        ),
        DsaProblem(
            id = "arr_043",
            topic = "arrays",
            title = "Top K Frequent Elements",
            difficulty = "Medium",
            pattern = "Bucket Sort / Min Heap",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given an integer array nums and an integer k, return the k most frequent elements in O(N) time.",
            exampleInput = "nums = [1, 1, 1, 2, 2, 3], k = 2",
            exampleOutput = "[1, 2]",
            keyInsight = "Count frequencies in HashMap, then bucket elements by frequency index (buckets 0..N). Traverse buckets from high to low.",
            solutionCode = """class Solution {
    // Top K Frequent Elements
    public static void solve() {}
    // Core solution logic:
    // Map<Integer, Integer> map = new HashMap<>(); for (int x : nums) map.put(x, map.getOrDefault(x, 0) + 1); List<Integer>[] bucket = new List[nums.length + 1]; for (int key : map.keySet()) { int f = map.get(key); if (bucket[f] == null) bucket[f] = new ArrayList<>(); bucket[f].add(key); } int[] res = new int[k]; int idx = 0; for (int i = bucket.length - 1; i >= 0 && idx < k; i--) if (bucket[i] != null) for (int val : bucket[i]) { res[idx++] = val; if (idx == k) break; } return res;
}"""
        ),
        DsaProblem(
            id = "arr_044",
            topic = "arrays",
            title = "Can Place Flowers",
            difficulty = "Easy",
            pattern = "Greedy Linear Scan",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Determine if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule.",
            exampleInput = "flowerbed = [1, 0, 0, 0, 1], n = 1",
            exampleOutput = "true",
            keyInsight = "Check if current plot is 0 and both adjacent plots (prev and next) are also 0 or out-of-bounds.",
            solutionCode = """class Solution {
    // Can Place Flowers
    public static void solve() {}
    // Core solution logic:
    // int count = 0; for (int i = 0; i < flowerbed.length; i++) { if (flowerbed[i] == 0) { boolean prev = (i == 0 || flowerbed[i - 1] == 0); boolean next = (i == flowerbed.length - 1 || flowerbed[i + 1] == 0); if (prev && next) { flowerbed[i] = 1; count++; } } } return count >= n;
}"""
        ),
        DsaProblem(
            id = "arr_045",
            topic = "arrays",
            title = "Monotonic Array",
            difficulty = "Easy",
            pattern = "Single Pass",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "An array is monotonic if it is either monotone increasing or monotone decreasing. Return true if nums is monotonic.",
            exampleInput = "nums = [1, 2, 2, 3]",
            exampleOutput = "true",
            keyInsight = "Track two booleans: increasing and decreasing. If an element violates increasing, set it false; same for decreasing.",
            solutionCode = """class Solution {
    // Monotonic Array
    public static void solve() {}
    // Core solution logic:
    // boolean inc = true, dec = true; for (int i = 1; i < nums.length; i++) { if (nums[i] < nums[i - 1]) inc = false; if (nums[i] > nums[i - 1]) dec = false; } return inc || dec;
}"""
        ),
        DsaProblem(
            id = "arr_046",
            topic = "arrays",
            title = "Third Maximum Number",
            difficulty = "Easy",
            pattern = "Three Pointers / Distinct Max Tracking",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given an integer array nums, return the third distinct maximum number in this array. If it does not exist, return the maximum number.",
            exampleInput = "nums = [3, 2, 1]",
            exampleOutput = "1",
            keyInsight = "Maintain max1, max2, max3 as Long.MIN_VALUE. Skip duplicates, cascade values downward on new maxima.",
            solutionCode = """class Solution {
    // Third Maximum Number
    public static void solve() {}
    // Core solution logic:
    // Long m1 = null, m2 = null, m3 = null; for (int n : nums) { if ((m1 != null && n == m1) || (m2 != null && n == m2) || (m3 != null && n == m3)) continue; if (m1 == null || n > m1) { m3 = m2; m2 = m1; m1 = (long)n; } else if (m2 == null || n > m2) { m3 = m2; m2 = (long)n; } else if (m3 == null || n > m3) { m3 = (long)n; } } return m3 == null ? m1.intValue() : m3.intValue();
}"""
        ),
        DsaProblem(
            id = "arr_047",
            topic = "arrays",
            title = "Maximum Average Subarray I",
            difficulty = "Easy",
            pattern = "Fixed Sliding Window",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Find a contiguous subarray whose length is equal to k that has the maximum average value.",
            exampleInput = "nums = [1, 12, -5, -6, 50, 3], k = 4",
            exampleOutput = "12.75000",
            keyInsight = "Maintain running sum of window of size k. Add new element nums[i] and subtract outgoing nums[i-k].",
            solutionCode = """class Solution {
    // Maximum Average Subarray I
    public static void solve() {}
    // Core solution logic:
    // double sum = 0; for (int i = 0; i < k; i++) sum += nums[i]; double max = sum; for (int i = k; i < nums.length; i++) { sum += nums[i] - nums[i - k]; max = Math.max(max, sum); } return max / k;
}"""
        ),
        DsaProblem(
            id = "arr_048",
            topic = "arrays",
            title = "Minimum Size Subarray Sum",
            difficulty = "Medium",
            pattern = "Dynamic Sliding Window",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray whose sum is >= target.",
            exampleInput = "target = 7, nums = [2, 3, 1, 2, 4, 3]",
            exampleOutput = "2 ([4, 3])",
            keyInsight = "Expand right pointer to accumulate sum. When sum >= target, contract left pointer to minimize length.",
            solutionCode = """class Solution {
    // Minimum Size Subarray Sum
    public static void solve() {}
    // Core solution logic:
    // int l = 0, sum = 0, min = Integer.MAX_VALUE; for (int r = 0; r < nums.length; r++) { sum += nums[r]; while (sum >= target) { min = Math.min(min, r - l + 1); sum -= nums[l++]; } } return min == Integer.MAX_VALUE ? 0 : min;
}"""
        ),
        DsaProblem(
            id = "arr_049",
            topic = "arrays",
            title = "Max Consecutive Ones",
            difficulty = "Easy",
            pattern = "Linear Scan",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given a binary array nums, return the maximum number of consecutive 1's in the array.",
            exampleInput = "nums = [1, 1, 0, 1, 1, 1]",
            exampleOutput = "3",
            keyInsight = "Increment current streak on 1, reset streak to 0 on 0. Track overall maximum streak.",
            solutionCode = """class Solution {
    // Max Consecutive Ones
    public static void solve() {}
    // Core solution logic:
    // int max = 0, cur = 0; for (int x : nums) { cur = (x == 1) ? cur + 1 : 0; max = Math.max(max, cur); } return max;
}"""
        ),
        DsaProblem(
            id = "arr_050",
            topic = "arrays",
            title = "Max Consecutive Ones III",
            difficulty = "Medium",
            pattern = "Sliding Window",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.",
            exampleInput = "nums = [1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0], k = 2",
            exampleOutput = "6",
            keyInsight = "Maintain window [l, r]. If count of 0s exceeds k, advance left pointer until zeroes <= k.",
            solutionCode = """class Solution {
    // Max Consecutive Ones III
    public static void solve() {}
    // Core solution logic:
    // int l = 0, zeroes = 0, max = 0; for (int r = 0; r < nums.length; r++) { if (nums[r] == 0) zeroes++; while (zeroes > k) if (nums[l++] == 0) zeroes--; max = Math.max(max, r - l + 1); } return max;
}"""
        ),
        DsaProblem(
            id = "arr_051",
            topic = "arrays",
            title = "Subarray Product Less Than K",
            difficulty = "Medium",
            pattern = "Sliding Window",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given an array of positive integers nums and an integer k, return the number of contiguous subarrays where the product is strictly less than k.",
            exampleInput = "nums = [10, 5, 2, 6], k = 100",
            exampleOutput = "8",
            keyInsight = "Expand right pointer multiplying into product. Shrink left pointer when product >= k. Add (r - l + 1) subarrays.",
            solutionCode = """class Solution {
    // Subarray Product Less Than K
    public static void solve() {}
    // Core solution logic:
    // if (k <= 1) return 0; int prod = 1, l = 0, count = 0; for (int r = 0; r < nums.length; r++) { prod *= nums[r]; while (prod >= k) prod /= nums[l++]; count += r - l + 1; } return count;
}"""
        ),
        DsaProblem(
            id = "arr_052",
            topic = "arrays",
            title = "Shortest Unsorted Continuous Subarray",
            difficulty = "Medium",
            pattern = "Two Pointers / Extrema Scan",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Find the shortest continuous subarray that if sorted in ascending order, the entire array will be sorted.",
            exampleInput = "nums = [2, 6, 4, 8, 10, 9, 15]",
            exampleOutput = "5 ([6, 4, 8, 10, 9])",
            keyInsight = "Scan left-to-right to find right boundary of anomaly; scan right-to-left to find left boundary.",
            solutionCode = """class Solution {
    // Shortest Unsorted Continuous Subarray
    public static void solve() {}
    // Core solution logic:
    // int n = nums.length, max = nums[0], min = nums[n-1], l = -1, r = -2; for (int i = 1; i < n; i++) { max = Math.max(max, nums[i]); if (nums[i] < max) r = i; } for (int i = n - 2; i >= 0; i--) { min = Math.min(min, nums[i]); if (nums[i] > min) l = i; } return r - l + 1;
}"""
        ),
        DsaProblem(
            id = "arr_053",
            topic = "arrays",
            title = "Find the Duplicate Number",
            difficulty = "Medium",
            pattern = "Floyd's Tortoise and Hare Cycle Detection",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive, find the duplicate without modifying array.",
            exampleInput = "nums = [1, 3, 4, 2, 2]",
            exampleOutput = "2",
            keyInsight = "Treat array as a linked list where nums[i] is the pointer to next index. Detect cycle entrance.",
            solutionCode = """class Solution {
    // Find the Duplicate Number
    public static void solve() {}
    // Core solution logic:
    // int slow = nums[0], fast = nums[0]; do { slow = nums[slow]; fast = nums[nums[fast]]; } while (slow != fast); fast = nums[0]; while (slow != fast) { slow = nums[slow]; fast = nums[fast]; } return slow;
}"""
        ),
        DsaProblem(
            id = "arr_054",
            topic = "arrays",
            title = "Valid Mountain Array",
            difficulty = "Easy",
            pattern = "Two Climbers / Two Pointers",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given an array of integers arr, return true if and only if it is a valid mountain array.",
            exampleInput = "arr = [0, 3, 2, 1]",
            exampleOutput = "true",
            keyInsight = "Simulate two hikers starting at left and right moving uphill. They must meet at the same peak (0 < peak < n-1).",
            solutionCode = """class Solution {
    // Valid Mountain Array
    public static void solve() {}
    // Core solution logic:
    // int n = arr.length, l = 0, r = n - 1; while (l + 1 < n && arr[l] < arr[l + 1]) l++; while (r > 0 && arr[r - 1] > arr[r]) r--; return l > 0 && r < n - 1 && l == r;
}"""
        ),
        DsaProblem(
            id = "arr_055",
            topic = "arrays",
            title = "Relative Sort Array",
            difficulty = "Easy",
            pattern = "Counting Sort / Custom Order",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2. Place remaining at the end sorted.",
            exampleInput = "arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]",
            exampleOutput = "[2,2,2,1,4,3,3,9,6,7,19]",
            keyInsight = "Count frequencies of numbers in a count array (0..1000). Place arr2 elements first, then remaining elements.",
            solutionCode = """class Solution {
    // Relative Sort Array
    public static void solve() {}
    // Core solution logic:
    // int[] count = new int[1001]; for (int x : arr1) count[x]++; int idx = 0; for (int x : arr2) while (count[x]-- > 0) arr1[idx++] = x; for (int i = 0; i < 1001; i++) while (count[i]-- > 0) arr1[idx++] = i; return arr1;
}"""
        ),
        DsaProblem(
            id = "arr_056",
            topic = "arrays",
            title = "Height Checker",
            difficulty = "Easy",
            pattern = "Comparison with Sorted",
            timeComplexity = "O(N log N)",
            spaceComplexity = "O(N)",
            description = "Return the number of indices where heights[i] != expected[i] if expected is heights sorted in non-decreasing order.",
            exampleInput = "heights = [1, 1, 4, 2, 1, 3]",
            exampleOutput = "3",
            keyInsight = "Clone the array and sort it. Count how many indices differ from the original.",
            solutionCode = """class Solution {
    // Height Checker
    public static void solve() {}
    // Core solution logic:
    // int[] expected = heights.clone(); Arrays.sort(expected); int count = 0; for (int i = 0; i < heights.length; i++) if (heights[i] != expected[i]) count++; return count;
}"""
        ),
        DsaProblem(
            id = "arr_057",
            topic = "arrays",
            title = "Duplicate Zeros",
            difficulty = "Easy",
            pattern = "In-Place Modification from End",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given a fixed-length integer array arr, duplicate each occurrence of zero, shifting the remaining elements to the right in-place.",
            exampleInput = "arr = [1, 0, 2, 3, 0, 4, 5, 0]",
            exampleOutput = "[1, 0, 0, 2, 3, 0, 0, 4]",
            keyInsight = "Count zeros to find the expanded boundary, then write backwards from the end of original array.",
            solutionCode = """class Solution {
    // Duplicate Zeros
    public static void solve() {}
    // Core solution logic:
    // int zeros = 0, n = arr.length; for (int x : arr) if (x == 0) zeros++; int i = n - 1, j = n + zeros - 1; while (i >= 0) { if (j < n) arr[j] = arr[i]; if (arr[i] == 0) { j--; if (j < n) arr[j] = 0; } i--; j--; }
}"""
        ),
        DsaProblem(
            id = "arr_058",
            topic = "arrays",
            title = "Replace Elements with Greatest Element on Right Side",
            difficulty = "Easy",
            pattern = "Reverse Traversal",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Replace every element in that array with the greatest element among the elements to its right, and replace the last element with -1.",
            exampleInput = "arr = [17, 18, 5, 4, 6, 1]",
            exampleOutput = "[18, 6, 6, 6, 1, -1]",
            keyInsight = "Traverse backwards from right to left, keeping track of max element seen so far.",
            solutionCode = """class Solution {
    // Replace Elements with Greatest Element on Right Side
    public static void solve() {}
    // Core solution logic:
    // int max = -1; for (int i = arr.length - 1; i >= 0; i--) { int cur = arr[i]; arr[i] = max; max = Math.max(max, cur); } return arr;
}"""
        ),
        DsaProblem(
            id = "arr_059",
            topic = "arrays",
            title = "Check If N and Its Double Exist",
            difficulty = "Easy",
            pattern = "Hash Set",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given an array arr of integers, check if there exist two indices i and j such that i != j and arr[i] == 2 * arr[j].",
            exampleInput = "arr = [10, 2, 5, 3]",
            exampleOutput = "true (10 is double of 5)",
            keyInsight = "Check if current element * 2 is in set, or if current element is even and element / 2 is in set.",
            solutionCode = """class Solution {
    // Check If N and Its Double Exist
    public static void solve() {}
    // Core solution logic:
    // Set<Integer> set = new HashSet<>(); for (int x : arr) { if (set.contains(x * 2) || (x % 2 == 0 && set.contains(x / 2))) return true; set.add(x); } return false;
}"""
        ),
        DsaProblem(
            id = "arr_060",
            topic = "arrays",
            title = "Sort Array By Parity",
            difficulty = "Easy",
            pattern = "Two Pointers In-Place",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given an integer array nums, move all the even integers at the beginning of the array followed by all the odd integers.",
            exampleInput = "nums = [3, 1, 2, 4]",
            exampleOutput = "[2, 4, 3, 1]",
            keyInsight = "Use two pointers from left and right. Swap when left is odd and right is even.",
            solutionCode = """class Solution {
    // Sort Array By Parity
    public static void solve() {}
    // Core solution logic:
    // int l = 0, r = nums.length - 1; while (l < r) { if (nums[l] % 2 > nums[r] % 2) { int t = nums[l]; nums[l] = nums[r]; nums[r] = t; } if (nums[l] % 2 == 0) l++; if (nums[r] % 2 == 1) r--; } return nums;
}"""
        ),
        DsaProblem(
            id = "arr_061",
            topic = "arrays",
            title = "Sort Array By Parity II",
            difficulty = "Easy",
            pattern = "Two Pointers (Even/Odd Indices)",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Sort nums so that whenever nums[i] is odd, i is odd; and whenever nums[i] is even, i is even.",
            exampleInput = "nums = [4, 2, 5, 7]",
            exampleOutput = "[4, 5, 2, 7]",
            keyInsight = "Advance even index by 2 and odd index by 2. Swap misplaced elements.",
            solutionCode = """class Solution {
    // Sort Array By Parity II
    public static void solve() {}
    // Core solution logic:
    // int i = 0, j = 1, n = nums.length; while (i < n && j < n) { while (i < n && nums[i] % 2 == 0) i += 2; while (j < n && nums[j] % 2 == 1) j += 2; if (i < n && j < n) { int t = nums[i]; nums[i] = nums[j]; nums[j] = t; } } return nums;
}"""
        ),
        DsaProblem(
            id = "arr_062",
            topic = "arrays",
            title = "Minimum Absolute Difference",
            difficulty = "Easy",
            pattern = "Sorting + Pair Check",
            timeComplexity = "O(N log N)",
            spaceComplexity = "O(N)",
            description = "Find all pairs of elements with the minimum absolute difference of any two elements in ascending order.",
            exampleInput = "arr = [4, 2, 1, 3]",
            exampleOutput = "[[1, 2], [2, 3], [3, 4]]",
            keyInsight = "Sort the array. The minimum difference must occur between adjacent elements.",
            solutionCode = """class Solution {
    // Minimum Absolute Difference
    public static void solve() {}
    // Core solution logic:
    // Arrays.sort(arr); int min = Integer.MAX_VALUE; for (int i = 1; i < arr.length; i++) min = Math.min(min, arr[i] - arr[i - 1]); List<List<Integer>> res = new ArrayList<>(); for (int i = 1; i < arr.length; i++) if (arr[i] - arr[i - 1] == min) res.add(Arrays.asList(arr[i - 1], arr[i])); return res;
}"""
        ),
        DsaProblem(
            id = "arr_063",
            topic = "arrays",
            title = "Array Partition I",
            difficulty = "Easy",
            pattern = "Sorting / Greedy",
            timeComplexity = "O(N log N)",
            spaceComplexity = "O(1)",
            description = "Given an integer array nums of 2n integers, group these integers into n pairs (a1, b1)... such that sum of min(ai, bi) is maximized.",
            exampleInput = "nums = [1, 4, 3, 2]",
            exampleOutput = "4 (min(1, 2) + min(3, 4))",
            keyInsight = "Sorting pairs adjacent elements together. Sum every element at even index (0, 2, 4...).",
            solutionCode = """class Solution {
    // Array Partition I
    public static void solve() {}
    // Core solution logic:
    // Arrays.sort(nums); int sum = 0; for (int i = 0; i < nums.length; i += 2) sum += nums[i]; return sum;
}"""
        ),
        DsaProblem(
            id = "arr_064",
            topic = "arrays",
            title = "Reshape the Matrix",
            difficulty = "Easy",
            pattern = "Matrix Index Mapping",
            timeComplexity = "O(M*N)",
            spaceComplexity = "O(M*N)",
            description = "Reshape an m x n matrix into a new r x c matrix with the same row-traversing order. If illegal, return original.",
            exampleInput = "mat = [[1, 2], [3, 4]], r = 1, c = 4",
            exampleOutput = "[[1, 2, 3, 4]]",
            keyInsight = "If m*n != r*c return mat. Map flat index k to mat[k/n][k%n] and res[k/c][k%c].",
            solutionCode = """class Solution {
    // Reshape the Matrix
    public static void solve() {}
    // Core solution logic:
    // int m = mat.length, n = mat[0].length; if (m * n != r * c) return mat; int[][] res = new int[r][c]; for (int k = 0; k < m * n; k++) res[k / c][k % c] = mat[k / n][k % n]; return res;
}"""
        ),
        DsaProblem(
            id = "arr_065",
            topic = "arrays",
            title = "Toeplitz Matrix",
            difficulty = "Easy",
            pattern = "Diagonal Invariance Check",
            timeComplexity = "O(M*N)",
            spaceComplexity = "O(1)",
            description = "A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same elements. Return true if matrix is Toeplitz.",
            exampleInput = "matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]",
            exampleOutput = "true",
            keyInsight = "Check if matrix[i][j] == matrix[i-1][j-1] for all i > 0 and j > 0.",
            solutionCode = """class Solution {
    // Toeplitz Matrix
    public static void solve() {}
    // Core solution logic:
    // for (int i = 1; i < matrix.length; i++) for (int j = 1; j < matrix[0].length; j++) if (matrix[i][j] != matrix[i - 1][j - 1]) return false; return true;
}"""
        ),
        DsaProblem(
            id = "arr_066",
            topic = "arrays",
            title = "Flipping an Image",
            difficulty = "Easy",
            pattern = "Two Pointers + Inversion",
            timeComplexity = "O(M*N)",
            spaceComplexity = "O(1)",
            description = "Given an n x n binary matrix image, flip the image horizontally, then invert it (0->1, 1->0) in-place.",
            exampleInput = "image = [[1,1,0],[1,0,1],[0,0,0]]",
            exampleOutput = "[[1,0,0],[0,1,0],[1,1,1]]",
            keyInsight = "Two pointers l and r per row. If image[row][l] == image[row][r], both invert. Otherwise unchanged.",
            solutionCode = """class Solution {
    // Flipping an Image
    public static void solve() {}
    // Core solution logic:
    // for (int[] row : image) { int l = 0, r = row.length - 1; while (l <= r) { int t = row[l] ^ 1; row[l++] = row[r] ^ 1; row[r--] = t; } } return image;
}"""
        ),
        DsaProblem(
            id = "arr_067",
            topic = "arrays",
            title = "Transpose Matrix",
            difficulty = "Easy",
            pattern = "Row to Column Swap",
            timeComplexity = "O(M*N)",
            spaceComplexity = "O(M*N)",
            description = "Given a 2D integer array matrix, return the transpose of matrix (flipped over its main diagonal).",
            exampleInput = "matrix = [[1, 2, 3], [4, 5, 6]]",
            exampleOutput = "[[1, 4], [2, 5], [3, 6]]",
            keyInsight = "Create result matrix with swapped dimensions res[cols][rows]. Set res[j][i] = matrix[i][j].",
            solutionCode = """class Solution {
    // Transpose Matrix
    public static void solve() {}
    // Core solution logic:
    // int m = matrix.length, n = matrix[0].length; int[][] res = new int[n][m]; for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) res[j][i] = matrix[i][j]; return res;
}"""
        ),
        DsaProblem(
            id = "arr_068",
            topic = "arrays",
            title = "Matrix Diagonal Sum",
            difficulty = "Easy",
            pattern = "Diagonal Index Arithmetic",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given a square matrix mat, return the sum of the matrix diagonals (primary + secondary, avoid double counting center).",
            exampleInput = "mat = [[1,2,3],[4,5,6],[7,8,9]]",
            exampleOutput = "25 (1+5+9 + 3+7)",
            keyInsight = "Iterate i from 0 to n-1. Add mat[i][i] and mat[i][n-1-i]. If n is odd, subtract center mat[n/2][n/2].",
            solutionCode = """class Solution {
    // Matrix Diagonal Sum
    public static void solve() {}
    // Core solution logic:
    // int sum = 0, n = mat.length; for (int i = 0; i < n; i++) { sum += mat[i][i] + mat[i][n - 1 - i]; } if (n % 2 == 1) sum -= mat[n / 2][n / 2]; return sum;
}"""
        ),
        DsaProblem(
            id = "arr_069",
            topic = "arrays",
            title = "Lucky Numbers in a Matrix",
            difficulty = "Easy",
            pattern = "Row Min and Col Max",
            timeComplexity = "O(M*N)",
            spaceComplexity = "O(M+N)",
            description = "A lucky number is an element of the matrix such that it is the minimum element in its row and maximum in its column.",
            exampleInput = "matrix = [[3,7,8],[9,11,13],[15,16,17]]",
            exampleOutput = "[15]",
            keyInsight = "Precompute min in each row and max in each column. Any element matching both is lucky.",
            solutionCode = """class Solution {
    // Lucky Numbers in a Matrix
    public static void solve() {}
    // Core solution logic:
    // int m = matrix.length, n = matrix[0].length; int[] rowMin = new int[m], colMax = new int[n]; Arrays.fill(rowMin, Integer.MAX_VALUE); for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) { rowMin[i] = Math.min(rowMin[i], matrix[i][j]); colMax[j] = Math.max(colMax[j], matrix[i][j]); } List<Integer> res = new ArrayList<>(); for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) if (matrix[i][j] == rowMin[i] && matrix[i][j] == colMax[j]) res.add(matrix[i][j]); return res;
}"""
        ),
        DsaProblem(
            id = "arr_070",
            topic = "arrays",
            title = "Count Negative Numbers in a Sorted Matrix",
            difficulty = "Easy",
            pattern = "Staircase Search (Top-Right / Bottom-Left)",
            timeComplexity = "O(M+N)",
            spaceComplexity = "O(1)",
            description = "Given an m x n matrix grid which is sorted in non-increasing order both row-wise and column-wise, return count of negative numbers.",
            exampleInput = "grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]",
            exampleOutput = "8",
            keyInsight = "Start from bottom-left (row = m-1, col = 0). If grid[r][c] < 0, all cells to right in row are negative.",
            solutionCode = """class Solution {
    // Count Negative Numbers in a Sorted Matrix
    public static void solve() {}
    // Core solution logic:
    // int m = grid.length, n = grid[0].length, r = m - 1, c = 0, count = 0; while (r >= 0 && c < n) { if (grid[r][c] < 0) { count += n - c; r--; } else c++; } return count;
}"""
        ),
        DsaProblem(
            id = "arr_071",
            topic = "arrays",
            title = "Leaders in an Array",
            difficulty = "Easy",
            pattern = "Scan From Right",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "An element is a leader if it is greater than or equal to all elements to its right side. Find all leaders.",
            exampleInput = "arr = [16, 17, 4, 3, 5, 2]",
            exampleOutput = "[17, 5, 2]",
            keyInsight = "The rightmost element is always a leader. Traverse from right to left keeping track of max.",
            solutionCode = """class Solution {
    // Leaders in an Array
    public static void solve() {}
    // Core solution logic:
    // List<Integer> res = new ArrayList<>(); int max = Integer.MIN_VALUE; for (int i = arr.length - 1; i >= 0; i--) { if (arr[i] >= max) { res.add(arr[i]); max = arr[i]; } } Collections.reverse(res); return res;
}"""
        ),
        DsaProblem(
            id = "arr_072",
            topic = "arrays",
            title = "Equilibrium Index of an Array",
            difficulty = "Easy",
            pattern = "Prefix Sum Equality",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Find first index such that sum of elements at lower indices equals sum of elements at higher indices.",
            exampleInput = "arr = [-7, 1, 5, 2, -4, 3, 0]",
            exampleOutput = "3",
            keyInsight = "Accumulate total sum. As you iterate, check if leftSum == totalSum - leftSum - arr[i].",
            solutionCode = """class Solution {
    // Equilibrium Index of an Array
    public static void solve() {}
    // Core solution logic:
    // long total = 0, left = 0; for (int x : arr) total += x; for (int i = 0; i < arr.length; i++) { if (left == total - left - arr[i]) return i; left += arr[i]; } return -1;
}"""
        ),
        DsaProblem(
            id = "arr_073",
            topic = "arrays",
            title = "Rearrange Array Elements by Sign",
            difficulty = "Medium",
            pattern = "Two Pointers",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Rearrange nums into pairs of positive and negative numbers alternately, starting with positive, preserving relative order.",
            exampleInput = "nums = [3, 1, -2, -5, 2, -4]",
            exampleOutput = "[3, -2, 1, -5, 2, -4]",
            keyInsight = "Use two pointers: pos = 0 and neg = 1. Place positives at pos (advance by 2) and negatives at neg.",
            solutionCode = """class Solution {
    // Rearrange Array Elements by Sign
    public static void solve() {}
    // Core solution logic:
    // int[] res = new int[nums.length]; int pos = 0, neg = 1; for (int x : nums) { if (x > 0) { res[pos] = x; pos += 2; } else { res[neg] = x; neg += 2; } } return res;
}"""
        ),
        DsaProblem(
            id = "arr_074",
            topic = "arrays",
            title = "Find Smallest Missing Positive Number",
            difficulty = "Medium",
            pattern = "Index Swapping Cycle Sort",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given an unsorted integer array nums, return the smallest missing positive integer in O(N) time and O(1) space.",
            exampleInput = "nums = [3, 4, -1, 1]",
            exampleOutput = "2",
            keyInsight = "Place each number x in its bucket index x-1 if 1 <= x <= n. Then find first index i where nums[i] != i+1.",
            solutionCode = """class Solution {
    // Find Smallest Missing Positive Number
    public static void solve() {}
    // Core solution logic:
    // int n = nums.length; for (int i = 0; i < n; i++) while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) { int t = nums[nums[i] - 1]; nums[nums[i] - 1] = nums[i]; nums[i] = t; } for (int i = 0; i < n; i++) if (nums[i] != i + 1) return i + 1; return n + 1;
}"""
        ),
        DsaProblem(
            id = "arr_075",
            topic = "arrays",
            title = "Subarrays with XOR Sum K",
            difficulty = "Medium",
            pattern = "Prefix XOR + Hash Map",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given an array of integers A and an integer B, find the total number of subarrays having bitwise XOR equal to B.",
            exampleInput = "A = [4, 2, 2, 6, 4], B = 6",
            exampleOutput = "4",
            keyInsight = "If prefixXOR ^ B exists in the map, it represents subarrays ending at current index with XOR sum B.",
            solutionCode = """class Solution {
    // Subarrays with XOR Sum K
    public static void solve() {}
    // Core solution logic:
    // Map<Integer, Integer> map = new HashMap<>(); map.put(0, 1); int xor = 0, count = 0; for (int x : A) { xor ^= x; count += map.getOrDefault(xor ^ B, 0); map.put(xor, map.getOrDefault(xor, 0) + 1); } return count;
}"""
        ),
        DsaProblem(
            id = "arr_076",
            topic = "arrays",
            title = "Majority Element II",
            difficulty = "Medium",
            pattern = "Boyer-Moore Voting (N/3 Threshold)",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given an integer array of size n, find all elements that appear more than ⌊n/3⌋ times.",
            exampleInput = "nums = [3, 2, 3]",
            exampleOutput = "[3]",
            keyInsight = "At most two elements can appear > n/3 times. Track two candidates and their counts.",
            solutionCode = """class Solution {
    // Majority Element II
    public static void solve() {}
    // Core solution logic:
    // int c1 = 0, c2 = 0, count1 = 0, count2 = 0; for (int x : nums) { if (x == c1) count1++; else if (x == c2) count2++; else if (count1 == 0) { c1 = x; count1 = 1; } else if (count2 == 0) { c2 = x; count2 = 1; } else { count1--; count2--; } } count1 = 0; count2 = 0; for (int x : nums) { if (x == c1) count1++; else if (x == c2) count2++; } List<Integer> res = new ArrayList<>(); if (count1 > nums.length / 3) res.add(c1); if (count2 > nums.length / 3) res.add(c2); return res;
}"""
        ),
        DsaProblem(
            id = "arr_077",
            topic = "arrays",
            title = "Subarray With 0 Sum",
            difficulty = "Easy",
            pattern = "Prefix Sum Hash Set",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given an array of positive and negative numbers, find if there is a subarray (of size at least one) with 0 sum.",
            exampleInput = "arr = [4, 2, -3, 1, 6]",
            exampleOutput = "true (subarray [2, -3, 1])",
            keyInsight = "If a prefix sum repeats or reaches 0, the elements in between must sum to 0.",
            solutionCode = """class Solution {
    // Subarray With 0 Sum
    public static void solve() {}
    // Core solution logic:
    // Set<Integer> set = new HashSet<>(); int sum = 0; for (int x : arr) { sum += x; if (sum == 0 || set.contains(sum)) return true; set.add(sum); } return false;
}"""
        ),
        DsaProblem(
            id = "arr_078",
            topic = "arrays",
            title = "Range Sum Query - Immutable",
            difficulty = "Easy",
            pattern = "Prefix Sum Precomputation",
            timeComplexity = "O(1) query",
            spaceComplexity = "O(N)",
            description = "Calculate the sum of elements of nums between indices left and right inclusive in O(1) query time.",
            exampleInput = "nums = [-2, 0, 3, -5, 2, -1], sumRange(0, 2)",
            exampleOutput = "1 (-2 + 0 + 3)",
            keyInsight = "Precompute prefixSum array where prefix[i+1] = prefix[i] + nums[i]. sumRange(l, r) = prefix[r+1] - prefix[l].",
            solutionCode = """class Solution {
    // Range Sum Query - Immutable
    public static void solve() {}
    // Core solution logic:
    // class NumArray { int[] prefix; public NumArray(int[] nums) { prefix = new int[nums.length + 1]; for (int i = 0; i < nums.length; i++) prefix[i + 1] = prefix[i] + nums[i]; } public int sumRange(int left, int right) { return prefix[right + 1] - prefix[left]; } }
}"""
        ),
        DsaProblem(
            id = "arr_079",
            topic = "arrays",
            title = "Check if Array Is Sorted and Rotated",
            difficulty = "Easy",
            pattern = "Inversion Count",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given an array nums, return true if the array was originally sorted in non-decreasing order, then rotated some number of positions.",
            exampleInput = "nums = [3, 4, 5, 1, 2]",
            exampleOutput = "true",
            keyInsight = "Count how many times nums[i] > nums[(i + 1) % n]. A valid rotated sorted array has at most 1 such drop.",
            solutionCode = """class Solution {
    // Check if Array Is Sorted and Rotated
    public static void solve() {}
    // Core solution logic:
    // int drops = 0, n = nums.length; for (int i = 0; i < n; i++) if (nums[i] > nums[(i + 1) % n]) drops++; return drops <= 1;
}"""
        ),
        DsaProblem(
            id = "arr_080",
            topic = "arrays",
            title = "Best Time to Buy and Sell Stock II",
            difficulty = "Easy",
            pattern = "Greedy Sum of All Slopes",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Find maximum profit by making as many transactions as you like (buy one and sell one multiple times).",
            exampleInput = "prices = [7, 1, 5, 3, 6, 4]",
            exampleOutput = "7 (1->5 + 3->6)",
            keyInsight = "Accumulate all positive differences between consecutive days: if prices[i] > prices[i-1], add to profit.",
            solutionCode = """class Solution {
    // Best Time to Buy and Sell Stock II
    public static void solve() {}
    // Core solution logic:
    // int profit = 0; for (int i = 1; i < prices.length; i++) if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1]; return profit;
}"""
        ),
        DsaProblem(
            id = "arr_081",
            topic = "arrays",
            title = "Count Pairs With Given Sum",
            difficulty = "Easy",
            pattern = "Hash Map Frequency",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given an array of integers and a sum target, find the number of pairs of elements in the array whose sum is equal to target.",
            exampleInput = "arr = [1, 5, 7, -1, 5], k = 6",
            exampleOutput = "3 ((1, 5), (1, 5), (7, -1))",
            keyInsight = "Store frequencies in map. For each x, add map.getOrDefault(target - x, 0) to count.",
            solutionCode = """class Solution {
    // Count Pairs With Given Sum
    public static void solve() {}
    // Core solution logic:
    // Map<Integer, Integer> map = new HashMap<>(); int count = 0; for (int x : arr) { count += map.getOrDefault(k - x, 0); map.put(x, map.getOrDefault(x, 0) + 1); } return count;
}"""
        ),
        DsaProblem(
            id = "arr_082",
            topic = "arrays",
            title = "Common Elements in 3 Sorted Arrays",
            difficulty = "Easy",
            pattern = "Three Pointers",
            timeComplexity = "O(N1+N2+N3)",
            spaceComplexity = "O(1)",
            description = "Given three sorted arrays, find the elements that are common in all three arrays.",
            exampleInput = "A = [1, 5, 10, 20], B = [5, 13, 15, 20], C = [5, 20]",
            exampleOutput = "[5, 20]",
            keyInsight = "Three pointers i, j, k. If A[i] == B[j] == C[k], add to result. Otherwise advance the pointer pointing to smallest.",
            solutionCode = """class Solution {
    // Common Elements in 3 Sorted Arrays
    public static void solve() {}
    // Core solution logic:
    // int i = 0, j = 0, k = 0; List<Integer> res = new ArrayList<>(); while (i < A.length && j < B.length && k < C.length) { if (A[i] == B[j] && B[j] == C[k]) { if (res.isEmpty() || res.get(res.size()-1) != A[i]) res.add(A[i]); i++; j++; k++; } else if (A[i] < B[j]) i++; else if (B[j] < C[k]) j++; else k++; } return res;
}"""
        ),
        DsaProblem(
            id = "arr_083",
            topic = "arrays",
            title = "Alternate Positive and Negative Numbers",
            difficulty = "Easy",
            pattern = "Separate Queues",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Rearrange array so that positive and negative numbers are placed alternatively, maintaining their initial relative order.",
            exampleInput = "arr = [1, 2, 3, -4, -1, 4]",
            exampleOutput = "[1, -4, 2, -1, 3, 4]",
            keyInsight = "Collect positives and negatives into two lists. Interleave them back into the array.",
            solutionCode = """class Solution {
    // Alternate Positive and Negative Numbers
    public static void solve() {}
    // Core solution logic:
    // List<Integer> pos = new ArrayList<>(), neg = new ArrayList<>(); for (int x : arr) if (x >= 0) pos.add(x); else neg.add(x); int i = 0, p = 0, n = 0; while (p < pos.size() && n < neg.size()) { arr[i++] = pos.get(p++); arr[i++] = neg.get(n++); } while (p < pos.size()) arr[i++] = pos.get(p++); while (n < neg.size()) arr[i++] = neg.get(n++);
}"""
        ),
        DsaProblem(
            id = "arr_084",
            topic = "arrays",
            title = "Reverse an Array",
            difficulty = "Easy",
            pattern = "Two Pointers In-Place",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Reverse an array in-place without using extra auxiliary memory.",
            exampleInput = "arr = [1, 2, 3, 4, 5]",
            exampleOutput = "[5, 4, 3, 2, 1]",
            keyInsight = "Use left = 0, right = n-1. Swap elements and move inward until pointers cross.",
            solutionCode = """class Solution {
    // Reverse an Array
    public static void solve() {}
    // Core solution logic:
    // int l = 0, r = arr.length - 1; while (l < r) { int t = arr[l]; arr[l++] = arr[r]; arr[r--] = t; }
}"""
        ),
        DsaProblem(
            id = "arr_085",
            topic = "arrays",
            title = "Rotate Array by K Positions Left",
            difficulty = "Easy",
            pattern = "Reverse Array Trick",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given an array of size n, rotate the array to the left by k positions.",
            exampleInput = "arr = [1, 2, 3, 4, 5], k = 2",
            exampleOutput = "[3, 4, 5, 1, 2]",
            keyInsight = "Reverse first k elements, reverse remaining n-k elements, then reverse entire array.",
            solutionCode = """class Solution {
    // Rotate Array by K Positions Left
    public static void solve() {}
    // Core solution logic:
    // k %= arr.length; reverse(arr, 0, k - 1); reverse(arr, k, arr.length - 1); reverse(arr, 0, arr.length - 1);
}"""
        ),
        DsaProblem(
            id = "arr_086",
            topic = "arrays",
            title = "Find Minimum Difference Pair",
            difficulty = "Easy",
            pattern = "Sorting",
            timeComplexity = "O(N log N)",
            spaceComplexity = "O(1)",
            description = "Given an unsorted array, find the minimum difference between any pair in the array.",
            exampleInput = "arr = [2, 4, 5, 9, 7]",
            exampleOutput = "1 (5 - 4)",
            keyInsight = "Sort the array. The minimum difference must lie between two adjacent elements.",
            solutionCode = """class Solution {
    // Find Minimum Difference Pair
    public static void solve() {}
    // Core solution logic:
    // Arrays.sort(arr); int min = Integer.MAX_VALUE; for (int i = 1; i < arr.length; i++) min = Math.min(min, arr[i] - arr[i - 1]); return min;
}"""
        ),
        DsaProblem(
            id = "arr_087",
            topic = "arrays",
            title = "Peak Element in Array (Unsorted)",
            difficulty = "Easy",
            pattern = "Linear Scan",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "An element is peak if it is not smaller than its neighbours. For corner elements, we need consider only one neighbour.",
            exampleInput = "arr = [5, 10, 20, 15]",
            exampleOutput = "2 (index of 20)",
            keyInsight = "Check if current element >= prev and current element >= next.",
            solutionCode = """class Solution {
    // Peak Element in Array (Unsorted)
    public static void solve() {}
    // Core solution logic:
    // int n = arr.length; if (n == 1 || arr[0] >= arr[1]) return 0; for (int i = 1; i < n - 1; i++) if (arr[i] >= arr[i - 1] && arr[i] >= arr[i + 1]) return i; return n - 1;
}"""
        ),
        DsaProblem(
            id = "arr_088",
            topic = "arrays",
            title = "Find Triplet with Zero Sum",
            difficulty = "Easy",
            pattern = "Sorting + Two Pointers",
            timeComplexity = "O(N^2)",
            spaceComplexity = "O(1)",
            description = "Given an array of distinct elements. The task is to find if there is a triplet that sums to zero.",
            exampleInput = "arr = [0, -1, 2, -3, 1]",
            exampleOutput = "true (0, -1, 1)",
            keyInsight = "Sort array. For each i, use two pointers l and r to find pair summing to -arr[i].",
            solutionCode = """class Solution {
    // Find Triplet with Zero Sum
    public static void solve() {}
    // Core solution logic:
    // Arrays.sort(arr); for (int i = 0; i < arr.length - 2; i++) { int l = i + 1, r = arr.length - 1; while (l < r) { int sum = arr[i] + arr[l] + arr[r]; if (sum == 0) return true; else if (sum < 0) l++; else r--; } } return false;
}"""
        ),
        DsaProblem(
            id = "arr_089",
            topic = "arrays",
            title = "Maximum Consecutive 1s After Flipping One Zero",
            difficulty = "Easy",
            pattern = "Sliding Window",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.",
            exampleInput = "nums = [1, 0, 1, 1, 0]",
            exampleOutput = "4",
            keyInsight = "Maintain sliding window with at most one 0.",
            solutionCode = """class Solution {
    // Maximum Consecutive 1s After Flipping One Zero
    public static void solve() {}
    // Core solution logic:
    // int l = 0, zeroes = 0, max = 0; for (int r = 0; r < nums.length; r++) { if (nums[r] == 0) zeroes++; while (zeroes > 1) if (nums[l++] == 0) zeroes--; max = Math.max(max, r - l + 1); } return max;
}"""
        ),
        DsaProblem(
            id = "arr_090",
            topic = "arrays",
            title = "Find the Highest Altitude",
            difficulty = "Easy",
            pattern = "Prefix Sum / Gain Tracking",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "A biker goes on a road trip with gains between points. Return the highest altitude reached.",
            exampleInput = "gain = [-5, 1, 5, 0, -7]",
            exampleOutput = "1",
            keyInsight = "Start at altitude 0. Track running altitude and update maximum altitude.",
            solutionCode = """class Solution {
    // Find the Highest Altitude
    public static void solve() {}
    // Core solution logic:
    // int max = 0, cur = 0; for (int g : gain) { cur += g; max = Math.max(max, cur); } return max;
}"""
        ),
        DsaProblem(
            id = "arr_091",
            topic = "arrays",
            title = "Count Number of Pairs With Absolute Difference K",
            difficulty = "Easy",
            pattern = "Hash Map Frequency",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given an integer array nums and an integer k, return the number of pairs (i, j) where i < j such that |nums[i] - nums[j]| == k.",
            exampleInput = "nums = [1, 2, 2, 1], k = 1",
            exampleOutput = "4",
            keyInsight = "Use frequency map. Check occurrences of num - k and num + k.",
            solutionCode = """class Solution {
    // Count Number of Pairs With Absolute Difference K
    public static void solve() {}
    // Core solution logic:
    // Map<Integer, Integer> map = new HashMap<>(); int count = 0; for (int x : nums) { count += map.getOrDefault(x - k, 0) + map.getOrDefault(x + k, 0); map.put(x, map.getOrDefault(x, 0) + 1); } return count;
}"""
        ),
        DsaProblem(
            id = "arr_092",
            topic = "arrays",
            title = "Find Target Indices After Sorting Array",
            difficulty = "Easy",
            pattern = "Sorting or Two Pass Count",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Return a list of the target indices of nums after sorting nums in non-decreasing order.",
            exampleInput = "nums = [1, 2, 5, 2, 3], target = 2",
            exampleOutput = "[1, 2]",
            keyInsight = "Count elements strictly less than target and elements equal to target without actually sorting.",
            solutionCode = """class Solution {
    // Find Target Indices After Sorting Array
    public static void solve() {}
    // Core solution logic:
    // int less = 0, equal = 0; for (int x : nums) if (x < target) less++; else if (x == target) equal++; List<Integer> res = new ArrayList<>(); for (int i = 0; i < equal; i++) res.add(less + i); return res;
}"""
        ),
        DsaProblem(
            id = "arr_093",
            topic = "arrays",
            title = "Check if All Characters Have Equal Number of Occurrences (Array Context)",
            difficulty = "Easy",
            pattern = "Frequency Count",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given an array of numbers, return true if all values that appear have the same frequency.",
            exampleInput = "nums = [1, 2, 2, 1, 3, 3]",
            exampleOutput = "true",
            keyInsight = "Count frequencies in array or map. Check if all positive counts are equal.",
            solutionCode = """class Solution {
    // Check if All Characters Have Equal Number of Occurrences (Array Context)
    public static void solve() {}
    // Core solution logic:
    // Map<Integer, Integer> map = new HashMap<>(); for (int x : nums) map.put(x, map.getOrDefault(x, 0) + 1); int freq = map.values().iterator().next(); for (int v : map.values()) if (v != freq) return false; return true;
}"""
        ),
        DsaProblem(
            id = "arr_094",
            topic = "arrays",
            title = "Maximum Difference Between Increasing Elements",
            difficulty = "Easy",
            pattern = "Single Pass Min Tracking",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Find the maximum difference nums[j] - nums[i] such that 0 <= i < j < n and nums[i] < nums[j].",
            exampleInput = "nums = [7, 1, 5, 4]",
            exampleOutput = "4 (5 - 1)",
            keyInsight = "Maintain minimum element seen so far. If current element > min, update maxDiff.",
            solutionCode = """class Solution {
    // Maximum Difference Between Increasing Elements
    public static void solve() {}
    // Core solution logic:
    // int min = nums[0], maxDiff = -1; for (int i = 1; i < nums.length; i++) { if (nums[i] > min) maxDiff = Math.max(maxDiff, nums[i] - min); min = Math.min(min, nums[i]); } return maxDiff;
}"""
        ),
        DsaProblem(
            id = "arr_095",
            topic = "arrays",
            title = "Two Sum II - Input Array Is Sorted",
            difficulty = "Medium",
            pattern = "Two Pointers",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given a 1-indexed array of integers numbers already sorted, find two numbers that add up to target.",
            exampleInput = "numbers = [2, 7, 11, 15], target = 9",
            exampleOutput = "[1, 2]",
            keyInsight = "Since array is sorted, start pointers at left and right. Sum < target -> l++, sum > target -> r--.",
            solutionCode = """class Solution {
    // Two Sum II - Input Array Is Sorted
    public static void solve() {}
    // Core solution logic:
    // int l = 0, r = numbers.length - 1; while (l < r) { int sum = numbers[l] + numbers[r]; if (sum == target) return new int[]{l + 1, r + 1}; if (sum < target) l++; else r--; } return new int[0];
}"""
        ),
        DsaProblem(
            id = "arr_096",
            topic = "arrays",
            title = "Count Subarrays with Fixed Bounds",
            difficulty = "Medium",
            pattern = "Sliding Window / Pointer Bounds",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Count the number of subarrays where min element is minK and max element is maxK.",
            exampleInput = "nums = [1, 3, 5, 2, 7, 5], minK = 1, maxK = 5",
            exampleOutput = "2",
            keyInsight = "Track last seen positions of invalid element, minK, and maxK. Valid subarrays start before min(minPos, maxPos).",
            solutionCode = """class Solution {
    // Count Subarrays with Fixed Bounds
    public static void solve() {}
    // Core solution logic:
    // long count = 0; int minPos = -1, maxPos = -1, badPos = -1; for (int i = 0; i < nums.length; i++) { if (nums[i] < minK || nums[i] > maxK) badPos = i; if (nums[i] == minK) minPos = i; if (nums[i] == maxK) maxPos = i; count += Math.max(0, Math.min(minPos, maxPos) - badPos); } return count;
}"""
        ),
        DsaProblem(
            id = "arr_097",
            topic = "arrays",
            title = "Continuous Subarray Sum",
            difficulty = "Medium",
            pattern = "Prefix Sum Modulo K",
            timeComplexity = "O(N)",
            spaceComplexity = "O(min(N,K))",
            description = "Given an integer array nums and an integer k, return true if nums has a good subarray of length at least two whose sum is a multiple of k.",
            exampleInput = "nums = [23, 2, 4, 6, 7], k = 6",
            exampleOutput = "true ([2, 4])",
            keyInsight = "If prefixSum % k repeats at indices with difference >= 2, the subarray in between has sum divisible by k.",
            solutionCode = """class Solution {
    // Continuous Subarray Sum
    public static void solve() {}
    // Core solution logic:
    // Map<Integer, Integer> map = new HashMap<>(); map.put(0, -1); int sum = 0; for (int i = 0; i < nums.length; i++) { sum += nums[i]; int rem = sum % k; if (map.containsKey(rem)) { if (i - map.get(rem) >= 2) return true; } else map.put(rem, i); } return false;
}"""
        ),
        DsaProblem(
            id = "arr_098",
            topic = "arrays",
            title = "Maximum Subarray Sum with One Deletion",
            difficulty = "Medium",
            pattern = "Dynamic Programming",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Find maximum subarray sum where you can delete at most one element from the subarray.",
            exampleInput = "arr = [1, -2, 0, 3]",
            exampleOutput = "4 (ignore -2)",
            keyInsight = "Track max sum without deletion (noDel) and max sum with one deletion (oneDel).",
            solutionCode = """class Solution {
    // Maximum Subarray Sum with One Deletion
    public static void solve() {}
    // Core solution logic:
    // int noDel = arr[0], oneDel = 0, max = arr[0]; for (int i = 1; i < arr.length; i++) { oneDel = Math.max(noDel, oneDel + arr[i]); noDel = Math.max(arr[i], noDel + arr[i]); max = Math.max(max, Math.max(noDel, oneDel)); } return max;
}"""
        ),
        DsaProblem(
            id = "arr_099",
            topic = "arrays",
            title = "Maximum Length of Repeated Subarray",
            difficulty = "Medium",
            pattern = "Dynamic Programming 2D",
            timeComplexity = "O(M*N)",
            spaceComplexity = "O(N)",
            description = "Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears in both arrays.",
            exampleInput = "nums1 = [1, 2, 3, 2, 1], nums2 = [3, 2, 1, 4, 7]",
            exampleOutput = "3 ([3, 2, 1])",
            keyInsight = "2D DP where dp[i][j] = dp[i-1][j-1] + 1 if nums1[i-1] == nums2[j-1].",
            solutionCode = """class Solution {
    // Maximum Length of Repeated Subarray
    public static void solve() {}
    // Core solution logic:
    // int m = nums1.length, n = nums2.length, max = 0; int[][] dp = new int[m + 1][n + 1]; for (int i = 1; i <= m; i++) for (int j = 1; j <= n; j++) if (nums1[i - 1] == nums2[j - 1]) { dp[i][j] = dp[i - 1][j - 1] + 1; max = Math.max(max, dp[i][j]); } return max;
}"""
        ),
        DsaProblem(
            id = "arr_100",
            topic = "arrays",
            title = "Minimum Swaps to Group All 1's Together",
            difficulty = "Medium",
            pattern = "Fixed Sliding Window",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given a binary array data, return the minimum number of swaps required to group all 1's present in the array together in any place.",
            exampleInput = "data = [1, 0, 1, 0, 1]",
            exampleOutput = "1",
            keyInsight = "Count total ones (window size W). Slide window of size W and find minimum count of 0s in any window.",
            solutionCode = """class Solution {
    // Minimum Swaps to Group All 1's Together
    public static void solve() {}
    // Core solution logic:
    // int totalOnes = 0; for (int x : data) totalOnes += x; if (totalOnes <= 1) return 0; int curOnes = 0; for (int i = 0; i < totalOnes; i++) curOnes += data[i]; int maxOnes = curOnes; for (int i = totalOnes; i < data.length; i++) { curOnes += data[i] - data[i - totalOnes]; maxOnes = Math.max(maxOnes, curOnes); } return totalOnes - maxOnes;
}"""
        )
    )
}
