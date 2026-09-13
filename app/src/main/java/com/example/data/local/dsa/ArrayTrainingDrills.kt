package com.example.data.local.dsa

import com.example.domain.model.DrillQuiz
import com.example.domain.model.DrillTraceStep
import com.example.domain.model.TrainingDrill

val arrayTrainingDrills = listOf(
    // Lesson 1
    TrainingDrill(
        id = "arr_drill_01",
        topicId = "arrays",
        lessonNumber = 1,
        title = "The Single-Pointer Accumulator",
        subtitle = "Traversing elements with a single running state variable",
        conceptDelta = "Baseline: 1 pointer + 1 accumulator variable (running sum/max)",
        intuition = "Before doing fancy tricks, understand the core array loop: your pointer visits each index exactly once from 0 to N-1. At each step, you fold the current value into a single answer accumulator (e.g. running max, running sum). You never look backward.",
        codePattern = """// Lesson 1: Single Pass Traversal
public int findMax(int[] nums) {
    int maxVal = nums[0]; 
    for (int i = 1; i < nums.length; i++) {
        if (nums[i] > maxVal) {
            maxVal = nums[i];
        }
    }
    return maxVal;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "Init: maxVal = nums[0] = 3", "We start with the first element as the baseline answer."),
            DrillTraceStep(2, "i = 1, nums[1] = 1", "1 is NOT > 3. maxVal stays 3."),
            DrillTraceStep(3, "i = 2, nums[2] = 7", "7 > 3! We update accumulator maxVal = 7."),
            DrillTraceStep(4, "i = 3, nums[3] = 2", "2 is NOT > 7. Loop finishes."),
            DrillTraceStep(5, "Result: return 7", "Guaranteed optimal answer in O(N) time and O(1) space.")
        ),
        quizQuestion = DrillQuiz(
            question = "If initializing maxVal = 0 for an array containing all negative numbers like [-5, -2, -9], what happens?",
            options = listOf(
                "It works normally and returns -2",
                "It returns 0, which is incorrect because 0 isn't in the array",
                "It throws an IndexOutOfBoundsException",
                "It enters an infinite loop"
            ),
            correctOptionIndex = 1,
            explanation = "Initializing with an arbitrary constant like 0 fails when all values are negative. Always initialize your accumulator with nums[0] or Integer.MIN_VALUE."
        )
    ),
    // Lesson 2
    TrainingDrill(
        id = "arr_drill_02",
        topicId = "arrays",
        lessonNumber = 2,
        title = "Read vs Write Pointers (In-Place Filter)",
        subtitle = "Decoupling where you read from where you write",
        conceptDelta = "+1 Concept: Separate 'write' pointer that advances only on valid elements",
        intuition = "In Lesson 1, read and write happened at the same speed. Now we add exactly ONE new concept: a separate write index `w`. The read index `r` scans every item, but `w` only advances when an item meets our criteria. This enables in-place filtering without extra memory.",
        codePattern = """// Lesson 2: Added 'w' (write pointer)
public int removeElement(int[] nums, int val) {
    int w = 0; // +1 Write Pointer
    for (int r = 0; r < nums.length; r++) {
        if (nums[r] != val) {
            nums[w] = nums[r];
            w++;
        }
    }
    return w;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "nums = [3, 2, 2, 3], val = 3, w = 0", "Initial state. Both r and w start at index 0."),
            DrillTraceStep(2, "r = 0: nums[0] == 3 (target)", "Filter rejects 3. w stays at 0, r advances."),
            DrillTraceStep(3, "r = 1: nums[1] == 2 (valid!)", "Write 2 to nums[w=0]. Advance w to 1. Array: [2, 2, 2, 3]."),
            DrillTraceStep(4, "r = 2: nums[2] == 2 (valid!)", "Write 2 to nums[w=1]. Advance w to 2. Array: [2, 2, 2, 3]."),
            DrillTraceStep(5, "r = 3: nums[3] == 3 (target)", "Filter rejects 3. w stays at 2. Loop terminates. Return w = 2.")
        ),
        quizQuestion = DrillQuiz(
            question = "When an element does NOT meet the filter criteria, what moves?",
            options = listOf(
                "Both 'r' and 'w' advance",
                "Only 'w' advances",
                "Only 'r' advances, while 'w' stays in place to be overwritten next",
                "Neither pointer moves"
            ),
            correctOptionIndex = 2,
            explanation = "Only the read pointer 'r' advances. 'w' waits at the position where the next valid element should be placed, effectively overwriting discarded values."
        )
    ),
    // Lesson 3
    TrainingDrill(
        id = "arr_drill_03",
        topicId = "arrays",
        lessonNumber = 3,
        title = "Inward Opposing Pointers",
        subtitle = "Shrinking the candidate search space from both boundaries",
        conceptDelta = "+1 Concept: Second pointer starting from the opposite end (right = n - 1)",
        intuition = "In Lesson 2, both pointers moved left-to-right. Now we flip the second pointer to start from the opposite end: `left = 0, right = n - 1`. If the array is sorted, comparing elements at both ends allows us to eliminate an entire choice at each step with O(1) decisions.",
        codePattern = """// Lesson 3: Inward Opposing Pointers (Sorted Array)
public int[] twoSumSorted(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;
    while (left < right) {
        int sum = nums[left] + nums[right];
        if (sum == target) return new int[]{left, right};
        else if (sum < target) left++;
        else right--;
    }
    return new int[]{};
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "nums = [2, 7, 11, 15], target = 18", "left = 0 (2), right = 3 (15)."),
            DrillTraceStep(2, "sum = 2 + 15 = 17", "17 < 18 (too small). Advance left: left++ -> left = 1."),
            DrillTraceStep(3, "left = 1 (7), right = 3 (15)", "sum = 7 + 15 = 22. 22 > 18 (too big). Decrease right: right-- -> right = 2."),
            DrillTraceStep(4, "left = 1 (7), right = 2 (11)", "sum = 7 + 11 = 18 == target! Match found at indices [1, 2].")
        ),
        quizQuestion = DrillQuiz(
            question = "Why does this two-pointer strategy work in O(N) time without checking all pairs?",
            options = listOf(
                "Because arrays are stored in contiguous memory",
                "Because sorting creates a monotonic guarantee: left++ only increases sum, right-- only decreases sum",
                "Because binary search is invoked internally",
                "Because it skips all odd numbers"
            ),
            correctOptionIndex = 1,
            explanation = "The monotonic property of the sorted array guarantees that if nums[left] + nums[right] < target, no other pairing with nums[left] can ever reach target, safely discarding left."
        )
    ),
    // Lesson 4
    TrainingDrill(
        id = "arr_drill_04",
        topicId = "arrays",
        lessonNumber = 4,
        title = "The Dynamic Sliding Window",
        subtitle = "Managing a contiguous range with dynamic expansion and lazy contraction",
        conceptDelta = "+1 Concept: Contiguous window state where 'right' expands and 'left' contracts",
        intuition = "Opposing pointers worked on arbitrary pairs. But what if we need a CONTIGUOUS subarray? We use two pointers moving in the SAME direction, defining a window `[left..right]`. The right pointer expands the window to satisfy a condition; the left pointer contracts it to optimize the size.",
        codePattern = """// Lesson 4: Sliding Window (Dynamic Contraction)
public int minSubArrayLen(int target, int[] nums) {
    int left = 0;
    int currentSum = 0;
    int minLen = Integer.MAX_VALUE;
    for (int right = 0; right < nums.length; right++) {
        currentSum += nums[right];
        while (currentSum >= target) {
            minLen = Math.min(minLen, right - left + 1);
            currentSum -= nums[left++];
        }
    }
    return minLen == Integer.MAX_VALUE ? 0 : minLen;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "nums = [2, 3, 1, 2, 4, 3], target = 7", "left = 0, right = 0, currentSum = 2 (< 7)."),
            DrillTraceStep(2, "right = 1, 2: sum becomes 2+3+1 = 6", "Still < 7. Keep expanding right."),
            DrillTraceStep(3, "right = 3: nums[3]=2, sum = 8 (>= 7!)", "Valid window [0..3], len = 4. Contract left: subtract nums[0]=2, left=1, sum=6."),
            DrillTraceStep(4, "right = 4: nums[4]=4, sum = 10 (>= 7!)", "Valid window [1..4], len=4. Contract: sum-=nums[1]=3 -> sum=7 (valid! len=3). Contract: sum-=nums[2]=1 -> sum=6, left=3."),
            DrillTraceStep(5, "right = 5: nums[5]=3, sum = 9 (>= 7!)", "Valid window [3..5], len=3. Contract: sum-=nums[3]=2 -> sum=7 (valid! len=2 [4, 3]). Best minLen = 2.")
        ),
        quizQuestion = DrillQuiz(
            question = "Even though there is a nested while loop inside the for loop, why is the overall time complexity still O(N)?",
            options = listOf(
                "The JVM unrolls the loop",
                "Because each element is added at most once by 'right' and subtracted at most once by 'left'",
                "Because target is a constant",
                "Because Math.min runs in O(1)"
            ),
            correctOptionIndex = 1,
            explanation = "Both pointers only ever move forward. 'right' visits each index once (N times), and 'left' visits each index at most once (N times). Total operations: 2N = O(N)."
        )
    ),
    // Lesson 5
    TrainingDrill(
        id = "arr_drill_05",
        topicId = "arrays",
        lessonNumber = 5,
        title = "Prefix Sum with State Memory (Hash Map)",
        subtitle = "Turning O(N²) subarray searches into instant O(1) history lookups",
        conceptDelta = "+1 Concept: Storing past running sums in a HashMap for instant mathematical matches",
        intuition = "Sliding windows only work with non-negative numbers (monotonic growth). If an array contains negatives, expanding right might decrease the sum! The solution: maintain a running `prefixSum` and remember past sums in a HashMap. If `currSum - prevSum = k`, then `prevSum = currSum - k` must have occurred previously.",
        codePattern = """// Lesson 5: Prefix Sum + Hash Memory
public int subarraySum(int[] nums, int k) {
    int count = 0, currentSum = 0;
    Map<Integer, Integer> prefixMap = new HashMap<>();
    prefixMap.put(0, 1);
    for (int num : nums) {
        currentSum += num;
        if (prefixMap.containsKey(currentSum - k)) {
            count += prefixMap.get(currentSum - k);
        }
        prefixMap.put(currentSum, prefixMap.getOrDefault(currentSum, 0) + 1);
    }
    return count;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "nums = [1, 2, 1, 2, 1], k = 3", "prefixMap = {0: 1}, currentSum = 0, count = 0."),
            DrillTraceStep(2, "num = 1: currentSum = 1", "Looking for 1 - 3 = -2. Not in map. Record prefix 1 -> map = {0:1, 1:1}."),
            DrillTraceStep(3, "num = 2: currentSum = 3", "Looking for 3 - 3 = 0. Found in map (count 1)! count becomes 1. map = {0:1, 1:1, 3:1}."),
            DrillTraceStep(4, "num = 1: currentSum = 4", "Looking for 4 - 3 = 1. Found in map (count 1)! count becomes 2."),
            DrillTraceStep(5, "num = 2: currentSum = 6", "Looking for 6 - 3 = 3. Found in map (count 1)! count becomes 3."),
            DrillTraceStep(6, "num = 1: currentSum = 7", "Looking for 7 - 3 = 4. Found in map (count 1)! count becomes 4. Final total = 4.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why is `prefixMap.put(0, 1)` required before the loop begins?",
            options = listOf(
                "To prevent NullPointerException in Java",
                "To handle subarrays starting from index 0 whose currentSum equals k directly",
                "To set the size of the HashMap",
                "It is optional and can be safely removed"
            ),
            correctOptionIndex = 1,
            explanation = "If currentSum itself equals k, then currentSum - k = 0. The (0, 1) entry ensures that a subarray starting at index 0 is counted properly."
        )
    ),
    // Lesson 6
    TrainingDrill(
        id = "arr_drill_06",
        topicId = "arrays",
        lessonNumber = 6,
        title = "In-Place Multi-Occurrence Gatekeeper",
        subtitle = "Allowing at most K duplicates using lookback read/write offset",
        conceptDelta = "+1 Concept: Comparing candidate element against nums[write - K] instead of nums[write - 1]",
        intuition = "In Lesson 2 we filtered out elements. What if we allow duplicates, but at most twice? Instead of complex counters, look back at the element already committed `K` steps ago: `nums[w - 2]`. If `num > nums[w - 2]`, it cannot possibly be a third duplicate!",
        codePattern = """// Lesson 6: Lookback Gatekeeper
public int removeDuplicatesK(int[] nums, int k) {
    int w = 0;
    for (int num : nums) {
        if (w < k || num > nums[w - k]) {
            nums[w++] = num;
        }
    }
    return w;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "nums = [1, 1, 1, 2, 2, 3], k = 2", "w = 0. First k=2 elements (1, 1) are accepted unconditionally: w=2."),
            DrillTraceStep(2, "num = 1 (third '1')", "Check num (1) > nums[w - 2] -> 1 > 1 is FALSE. Reject and skip!"),
            DrillTraceStep(3, "num = 2", "Check 2 > nums[2 - 2] -> 2 > 1 is TRUE. Accept: nums[w=2] = 2, w=3."),
            DrillTraceStep(4, "num = 2", "Check 2 > nums[3 - 2] -> 2 > 1 is TRUE. Accept: nums[w=3] = 2, w=4."),
            DrillTraceStep(5, "num = 3", "Check 3 > nums[4 - 2] -> 3 > 2 is TRUE. Accept: nums[w=4] = 3, w=5. Return 5.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why does `num > nums[w - k]` work for sorted arrays when limiting duplicates to k?",
            options = listOf(
                "Because sorted order guarantees any value equal to nums[w-k] would be the (k+1)-th duplicate",
                "Because it hashes every number",
                "Because w resets to 0 periodically",
                "It only works for negative numbers"
            ),
            correctOptionIndex = 0,
            explanation = "Since the array is sorted, if num equals nums[w - k], all elements between index (w - k) and (w - 1) must also be equal to num, meaning k instances are already present."
        )
    ),
    // Lesson 7
    TrainingDrill(
        id = "arr_drill_07",
        topicId = "arrays",
        lessonNumber = 7,
        title = "Dutch National Flag (3-Way Partition)",
        subtitle = "Partitioning an array into 3 sections with low, mid, and high pointers",
        conceptDelta = "+1 Concept: 3-pointer boundary partitioning: [0..low-1], [low..mid-1], [high+1..N-1]",
        intuition = "Two pointers gave us two partitions. To sort 3 values (0s, 1s, 2s) in a single pass without counting, we use 3 pointers: `low` bounds 0s on the left, `high` bounds 2s on the right, and `mid` scans the unclassified middle.",
        codePattern = """// Lesson 7: 3-Way Partitioning
public void sortColors(int[] nums) {
    int low = 0, mid = 0, high = nums.length - 1;
    while (mid <= high) {
        if (nums[mid] == 0) {
            swap(nums, low++, mid++);
        } else if (nums[mid] == 1) {
            mid++;
        } else {
            swap(nums, mid, high--); // Don't advance mid: swapped element is uninspected!
        }
    }
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "nums = [2, 0, 2, 1, 1, 0]", "low = 0, mid = 0, high = 5."),
            DrillTraceStep(2, "mid=0: nums[0]==2", "Swap nums[mid] with nums[high=5]. high becomes 4. Array: [0, 0, 2, 1, 1, 2]. mid stays 0!"),
            DrillTraceStep(3, "mid=0: nums[0]==0", "Swap nums[0] with nums[low=0]. low=1, mid=1."),
            DrillTraceStep(4, "mid=1: nums[1]==0", "Swap nums[1] with nums[low=1]. low=2, mid=2."),
            DrillTraceStep(5, "mid passes through 1s", "mid increments to 4. Process finishes with fully segregated [0, 0, 1, 1, 2, 2].")
        ),
        quizQuestion = DrillQuiz(
            question = "When `nums[mid] == 2` and we swap `mid` with `high`, why does `mid` NOT increment?",
            options = listOf(
                "Because high is always smaller than mid",
                "Because the element swapped from `high` into `mid` has not yet been inspected and could be 0, 1, or 2",
                "To avoid ArrayIndexOutOfBoundsException",
                "Because high decrements twice"
            ),
            correctOptionIndex = 1,
            explanation = "The element at 'high' came from the unexamined region. We must re-examine the newly placed element at nums[mid] on the next iteration."
        )
    ),
    // Lesson 8
    TrainingDrill(
        id = "arr_drill_08",
        topicId = "arrays",
        lessonNumber = 8,
        title = "Greedy Boundary Inward Squeeze",
        subtitle = "Eliminating suboptimal pairs by discarding the shorter wall",
        conceptDelta = "+1 Concept: Inward contraction driven by bottleneck height (Math.min(h[L], h[R]))",
        intuition = "In Container With Most Water, Area = (R - L) * min(height[L], height[R]). As pointers move inward, width ALWAYS shrinks. The only chance to get a larger area is to find a taller height. Therefore, the shorter wall can never produce a larger container with any other boundary—discard it!",
        codePattern = """// Lesson 8: Greedy Wall Squeeze
public int maxArea(int[] height) {
    int left = 0, right = height.length - 1, maxWater = 0;
    while (left < right) {
        int width = right - left;
        int h = Math.min(height[left], height[right]);
        maxWater = Math.max(maxWater, width * h);
        if (height[left] < height[right]) {
            left++;
        } else {
            right--;
        }
    }
    return maxWater;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "height = [1, 8, 6, 2, 5, 4, 8, 3, 7]", "left = 0 (1), right = 8 (7). Area = 8 * 1 = 8."),
            DrillTraceStep(2, "height[0]=1 < height[8]=7", "Left wall is bottleneck. Discard left -> left = 1 (8)."),
            DrillTraceStep(3, "left = 1 (8), right = 8 (7)", "Area = 7 * 7 = 49. height[8]=7 < height[1]=8 -> right--."),
            DrillTraceStep(4, "Contraction continues", "Explores all potential peak configurations in O(N) steps without O(N²) brute force.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why is it mathematically safe to discard the smaller height pointer?",
            options = listOf(
                "Because width decreases and any other container with that smaller height would have strictly less area",
                "Because water always flows to the right",
                "Because array heights are sorted",
                "Because area can never exceed the total array sum"
            ),
            correctOptionIndex = 0,
            explanation = "Since the width strictly decreases on each step, pairing the shorter wall with any interior wall cannot exceed its current area."
        )
    ),
    // Lesson 9
    TrainingDrill(
        id = "arr_drill_09",
        topicId = "arrays",
        lessonNumber = 9,
        title = "Interval Sorting & Overlap Merging",
        subtitle = "Sorting intervals by start time to transform 2D overlaps into 1D comparisons",
        conceptDelta = "+1 Concept: Pre-sorting intervals by interval[0] so overlaps are strictly adjacent",
        intuition = "Without sorting, any interval could overlap with any other interval (O(N²)). Sorting by start time guarantees that if interval B starts after interval A ends (`B.start > A.end`), interval B can never overlap with interval A or any interval preceding A!",
        codePattern = """// Lesson 9: Interval Merge
public int[][] merge(int[][] intervals) {
    Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
    List<int[]> merged = new ArrayList<>();
    for (int[] interval : intervals) {
        if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
            merged.add(interval);
        } else {
            merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], interval[1]);
        }
    }
    return merged.toArray(new int[merged.size()][]);
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "intervals = [[1,3],[2,6],[8,10],[15,18]]", "Already sorted by start time."),
            DrillTraceStep(2, "interval [1,3]", "merged is empty -> add [1,3]."),
            DrillTraceStep(3, "interval [2,6]", "2 <= 3 (overlap!). Update merged tail end: max(3, 6) = 6. merged = [[1,6]]."),
            DrillTraceStep(4, "interval [8,10]", "8 > 6 (no overlap). Add [8,10]. merged = [[1,6], [8,10]]."),
            DrillTraceStep(5, "interval [15,18]", "15 > 10. Add [15,18]. Output: [[1,6], [8,10], [15,18]].")
        ),
        quizQuestion = DrillQuiz(
            question = "When two sorted intervals [start1, end1] and [start2, end2] overlap, what is the new merged end time?",
            options = listOf(
                "end1 + end2",
                "Math.max(end1, end2)",
                "end2",
                "start2 - start1"
            ),
            correctOptionIndex = 1,
            explanation = "The merged interval must cover the furthest reach of both intervals, which is Math.max(end1, end2)."
        )
    ),
    // Lesson 10
    TrainingDrill(
        id = "arr_drill_10",
        topicId = "arrays",
        lessonNumber = 10,
        title = "In-Place Interval Insertion (3-Phase Partition)",
        subtitle = "Inserting into sorted intervals in O(N) time without re-sorting",
        conceptDelta = "+1 Concept: 3-phase linear scan: (1) All strictly before, (2) Merge all overlaps, (3) All strictly after",
        intuition = "Since the given intervals are ALREADY sorted, calling `Arrays.sort` takes O(N log N). We can do it in linear O(N) by decomposing into 3 phases: add all intervals that end before `newInterval.start`, absorb all intervals that overlap with `newInterval`, then append the rest.",
        codePattern = """// Lesson 10: 3-Phase Interval Insertion
public int[][] insert(int[][] intervals, int[] newInterval) {
    List<int[]> result = new ArrayList<>();
    int i = 0, n = intervals.length;
    // Phase 1: Add intervals ending before newInterval
    while (i < n && intervals[i][1] < newInterval[0]) {
        result.add(intervals[i++]);
    }
    // Phase 2: Merge overlapping intervals
    while (i < n && intervals[i][0] <= newInterval[1]) {
        newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
        newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
        i++;
    }
    result.add(newInterval);
    // Phase 3: Add remaining intervals
    while (i < n) result.add(intervals[i++]);
    return result.toArray(new int[result.size()][]);
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "intervals = [[1,3],[6,9]], newInterval = [2,5]", "Phase 1: intervals[0][1] = 3 is not < 2. No intervals added in phase 1."),
            DrillTraceStep(2, "Phase 2: intervals[0]=[1,3]", "1 <= 5 (overlap!). newInterval = [min(2,1), max(5,3)] = [1,5]. i becomes 1."),
            DrillTraceStep(3, "Phase 2: intervals[1]=[6,9]", "6 <= 5 is false. Phase 2 terminates. Append merged newInterval [1,5]."),
            DrillTraceStep(4, "Phase 3: Append [6,9]", "Result: [[1,5], [6,9]] in pure O(N) time.")
        ),
        quizQuestion = DrillQuiz(
            question = "What condition signals that `newInterval` overlaps with `intervals[i]`?",
            options = listOf(
                "intervals[i][0] <= newInterval[1] && intervals[i][1] >= newInterval[0]",
                "intervals[i][0] == newInterval[0]",
                "intervals[i][1] < newInterval[0]",
                "intervals[i][0] > newInterval[1]"
            ),
            correctOptionIndex = 0,
            explanation = "An overlap occurs when interval A starts before or when B ends, and ends after or when B starts."
        )
    ),
    // Lesson 11
    TrainingDrill(
        id = "arr_drill_11",
        topicId = "arrays",
        lessonNumber = 11,
        title = "The Fixed-Size Sliding Window",
        subtitle = "Sliding a constant K-width aperture across an array",
        conceptDelta = "+1 Concept: Fixed window length constraint: maintain exactly K elements by dropping nums[i - K]",
        intuition = "In Lesson 4, the window grew and shrank dynamically. In a fixed-size window of length `K`, every time we take 1 step to the right (`+nums[i]`), we MUST drop exactly 1 element from the left (`-nums[i - K]`). The size is locked at K.",
        codePattern = """// Lesson 11: Fixed Window of Size K
public double findMaxAverage(int[] nums, int k) {
    int currentSum = 0;
    for (int i = 0; i < k; i++) currentSum += nums[i];
    int maxSum = currentSum;
    
    for (int i = k; i < nums.length; i++) {
        currentSum += nums[i] - nums[i - k]; // +Incoming, -Outgoing
        maxSum = Math.max(maxSum, currentSum);
    }
    return (double) maxSum / k;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "nums = [1, 12, -5, -6, 50, 3], k = 4", "Build initial window [0..3]: sum = 1 + 12 - 5 - 6 = 2. maxSum = 2."),
            DrillTraceStep(2, "i = 4: incoming = 50, outgoing = nums[0] = 1", "currentSum = 2 + 50 - 1 = 51. maxSum = max(2, 51) = 51."),
            DrillTraceStep(3, "i = 5: incoming = 3, outgoing = nums[1] = 12", "currentSum = 51 + 3 - 12 = 42. maxSum remains 51."),
            DrillTraceStep(4, "Result: 51 / 4.0 = 12.75", "Calculated all k-length averages in O(N) time.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why is fixed sliding window O(N) instead of O(N * K)?",
            options = listOf(
                "Because each window recalculates the sum using a loop",
                "Because sliding the window takes O(1) by adding the new element and subtracting the evicted one",
                "Because K is always equal to 1",
                "Because arrays are stored sequentially"
            ),
            correctOptionIndex = 1,
            explanation = "Instead of summing K elements from scratch, we reuse the existing sum by updating only the boundary elements in O(1)."
        )
    ),
    // Lesson 12
    TrainingDrill(
        id = "arr_drill_12",
        topicId = "arrays",
        lessonNumber = 12,
        title = "Window State with Multi-Key Frequency",
        subtitle = "Tracking complex criteria within a window using map size as a counter",
        conceptDelta = "+1 Concept: Contracting window only when map.size() exceeds allowed distinct count K",
        intuition = "When window validity depends on distinct counts (e.g. at most K distinct elements), a simple sum isn't enough. We maintain a frequency Map. The window is valid as long as `map.size() <= k`. When it exceeds `k`, `left` evicts frequencies until a key drops to 0 and is removed.",
        codePattern = """// Lesson 12: Frequency-Constrained Sliding Window
public int totalFruit(int[] fruits) { // at most 2 distinct elements
    Map<Integer, Integer> count = new HashMap<>();
    int left = 0, maxLen = 0;
    for (int right = 0; right < fruits.length; right++) {
        count.put(fruits[right], count.getOrDefault(fruits[right], 0) + 1);
        while (count.size() > 2) {
            count.put(fruits[left], count.get(fruits[left]) - 1);
            if (count.get(fruits[left]) == 0) count.remove(fruits[left]);
            left++;
        }
        maxLen = Math.max(maxLen, right - left + 1);
    }
    return maxLen;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "fruits = [1, 2, 1, 2, 3]", "left = 0, right = 0..3: map = {1:2, 2:2}, size = 2 <= 2. maxLen = 4."),
            DrillTraceStep(2, "right = 4: fruit = 3", "map = {1:2, 2:2, 3:1}. size = 3 > 2! Violates constraint."),
            DrillTraceStep(3, "Contract left until a key reaches 0", "left=0 (fruit 1): count[1]=1. left=1 (fruit 2): count[2]=1. left=2 (fruit 1): count[1]=0 -> removed!"),
            DrillTraceStep(4, "Window restored: [2, 3]", "size = 2. maxLen remains 4.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why must `count.remove(key)` be explicitly called when its count reaches 0?",
            options = listOf(
                "To free RAM",
                "Because `count.size()` counts keys with value 0 unless they are explicitly removed",
                "Because Java HashMaps crash on 0 values",
                "It is optional"
            ),
            correctOptionIndex = 1,
            explanation = "map.size() reports the total number of keys. A key with value 0 still counts as a distinct type unless removed."
        )
    ),
    // Lesson 13
    TrainingDrill(
        id = "arr_drill_13",
        topicId = "arrays",
        lessonNumber = 13,
        title = "Kadane's Algorithm (Restart or Extend)",
        subtitle = "The local vs global maximum decision boundary",
        conceptDelta = "+1 Concept: `currentMax = Math.max(num, currentMax + num)` (either restart or extend)",
        intuition = "Finding the maximum subarray sum in O(N). At each index `i`, we have exactly two choices: either extend the existing subarray (`currentMax + num`), OR discard all past history and start a fresh subarray right here (`num`). If past sum is negative, it can only drag us down—restart!",
        codePattern = """// Lesson 13: Kadane's Local vs Global Choice
public int maxSubArray(int[] nums) {
    int currentSum = nums[0];
    int maxSum = nums[0];
    for (int i = 1; i < nums.length; i++) {
        currentSum = Math.max(nums[i], currentSum + nums[i]);
        maxSum = Math.max(maxSum, currentSum);
    }
    return maxSum;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]", "Init: currentSum = -2, maxSum = -2."),
            DrillTraceStep(2, "i = 1: num = 1", "max(1, -2 + 1) = 1 (restart fresh at 1!). maxSum = 1."),
            DrillTraceStep(3, "i = 2: num = -3", "max(-3, 1 - 3) = -2. maxSum = 1."),
            DrillTraceStep(4, "i = 3: num = 4", "max(4, -2 + 4) = 4 (restart fresh at 4!). maxSum = 4."),
            DrillTraceStep(5, "i = 4..6: nums = [-1, 2, 1]", "currentSum accumulates: 3 -> 5 -> 6. maxSum = 6."),
            DrillTraceStep(6, "Result", "maxSum = 6 for contiguous subarray [4, -1, 2, 1].")
        ),
        quizQuestion = DrillQuiz(
            question = "When does Kadane's algorithm decide to discard the previous subarray and start fresh?",
            options = listOf(
                "Whenever currentSum becomes negative",
                "Whenever num is even",
                "Whenever index is at the midpoint",
                "Only at the end of the array"
            ),
            correctOptionIndex = 0,
            explanation = "If the accumulated sum from the past is negative, adding it to nums[i] produces a value smaller than nums[i] alone. Thus, starting fresh at nums[i] is always superior."
        )
    ),
    // Lesson 14
    TrainingDrill(
        id = "arr_drill_14",
        topicId = "arrays",
        lessonNumber = 14,
        title = "Dual-State Tracking (Max Product Subarray)",
        subtitle = "Tracking both min and max states when negatives can flip the sign",
        conceptDelta = "+1 Concept: Tracking two running variables (maxProd and minProd) because negative * negative = positive",
        intuition = "In Kadane's addition, a negative number only hurts. In multiplication, a large negative number can become a giant positive number if multiplied by another negative! We must maintain BOTH the running maximum AND the running minimum at every step.",
        codePattern = """// Lesson 14: Dual Min/Max Tracking
public int maxProduct(int[] nums) {
    int maxProd = nums[0], minProd = nums[0], result = nums[0];
    for (int i = 1; i < nums.length; i++) {
        int num = nums[i];
        if (num < 0) {
            int temp = maxProd;
            maxProd = minProd;
            minProd = temp;
        }
        maxProd = Math.max(num, maxProd * num);
        minProd = Math.min(num, minProd * num);
        result = Math.max(result, maxProd);
    }
    return result;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "nums = [2, 3, -2, 4]", "maxProd = 2, minProd = 2, result = 2."),
            DrillTraceStep(2, "num = 3", "maxProd = max(3, 6) = 6, minProd = min(3, 6) = 3, result = 6."),
            DrillTraceStep(3, "num = -2 (negative!)", "Swap max and min! maxProd becomes 3, minProd becomes 6. maxProd = max(-2, -6) = -2. minProd = min(-2, -12) = -12."),
            DrillTraceStep(4, "num = 4", "maxProd = max(4, -8) = 4. minProd = min(4, -48) = -48. Final result = 6.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why do we swap `maxProd` and `minProd` before multiplying by a negative number?",
            options = listOf(
                "Because multiplying by a negative inverts the order: multiplying the minimum produces the largest possible positive",
                "To prevent integer overflow",
                "Because Java requires variable swaps every 3 iterations",
                "It has no mathematical impact"
            ),
            correctOptionIndex = 0,
            explanation = "A very small negative number multiplied by another negative becomes a large positive number, swapping the roles of minimum and maximum."
        )
    ),
    // Lesson 15
    TrainingDrill(
        id = "arr_drill_15",
        topicId = "arrays",
        lessonNumber = 15,
        title = "The Circular Inversion Trick",
        subtitle = "Converting wrap-around circular problems into totalSum - minSubArray",
        conceptDelta = "+1 Concept: Circular Max = Math.max(normalMax, totalSum - minSubArray)",
        intuition = "In a circular array, a subarray can wrap around the ends. A wrap-around subarray is just the ENTIRE array with a non-circular middle subarray cut out! To maximize the wrap-around ends, we simply minimize the middle subarray: `circularMax = totalSum - minSubArray`.",
        codePattern = """// Lesson 15: Circular Subarray via Inversion
public int maxSubarraySumCircular(int[] nums) {
    int total = 0, maxSub = nums[0], curMax = 0, minSub = nums[0], curMin = 0;
    for (int x : nums) {
        curMax = Math.max(x, curMax + x);
        maxSub = Math.max(maxSub, curMax);
        curMin = Math.min(x, curMin + x);
        minSub = Math.min(minSub, curMin);
        total += x;
    }
    return maxSub > 0 ? Math.max(maxSub, total - minSub) : maxSub;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "nums = [5, -3, 5]", "total = 7. Running standard Kadane: maxSub = 7."),
            DrillTraceStep(2, "Running Min Kadane", "minSub = -3 (middle element)."),
            DrillTraceStep(3, "Calculate Wrap-Around", "total - minSub = 7 - (-3) = 10 (taking 5 from start and 5 from end!)."),
            DrillTraceStep(4, "Result: max(maxSub, total - minSub)", "max(7, 10) = 10.")
        ),
        quizQuestion = DrillQuiz(
            question = "What special corner case must be handled when all numbers in the array are negative?",
            options = listOf(
                "When all elements are negative, total == minSub, so total - minSub = 0 (an empty subarray), which is invalid",
                "Divide by zero error occurs",
                "Integer underflow occurs",
                "Arrays cannot hold all negative values in Java"
            ),
            correctOptionIndex = 0,
            explanation = "If all elements are negative, total equals minSub, making total - minSub = 0. Since non-empty subarrays are required, we must return maxSub (the least negative element)."
        )
    ),
    // Lesson 16
    TrainingDrill(
        id = "arr_drill_16",
        topicId = "arrays",
        lessonNumber = 16,
        title = "Prefix & Suffix Product Passes",
        subtitle = "Computing bidirectional products without division in O(1) auxiliary space",
        conceptDelta = "+1 Concept: Forward pass stores left products; backward pass multiplies right products in-place",
        intuition = "To find the product of all elements except `nums[i]` without division, `ans[i] = (product of elements to the left) * (product of elements to the right)`. We compute the prefix products in one left-to-right pass, then fold suffix products on the return pass using a single scalar variable.",
        codePattern = """// Lesson 16: Two-Pass Prefix/Suffix Products
public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] res = new int[n];
    res[0] = 1;
    for (int i = 1; i < n; i++) {
        res[i] = res[i - 1] * nums[i - 1];
    }
    int right = 1;
    for (int i = n - 1; i >= 0; i--) {
        res[i] *= right;
        right *= nums[i];
    }
    return res;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "nums = [1, 2, 3, 4]", "Pass 1: res = [1, 1, 2, 6] (left products)."),
            DrillTraceStep(2, "Pass 2 starts from right: right = 1", "i = 3: res[3] = 6 * 1 = 6. right becomes 1 * 4 = 4."),
            DrillTraceStep(3, "i = 2", "res[2] = 2 * 4 = 8. right becomes 4 * 3 = 12."),
            DrillTraceStep(4, "i = 1", "res[1] = 1 * 12 = 12. right becomes 12 * 2 = 24."),
            DrillTraceStep(5, "i = 0", "res[0] = 1 * 24 = 24. Final array: [24, 12, 8, 6].")
        ),
        quizQuestion = DrillQuiz(
            question = "Why is this approach preferred over computing the total product and dividing by `nums[i]`?",
            options = listOf(
                "Division by zero occurs if the array contains zeros, and division is banned in the problem specification",
                "Division is unsupported by CPU hardware",
                "Two passes run faster than one pass with division",
                "It uses O(N²) memory"
            ),
            correctOptionIndex = 0,
            explanation = "If the array contains zero, division throws ArithmeticException, and if multiple zeros exist, handling zero special cases becomes fragile."
        )
    ),
    // Lesson 17
    TrainingDrill(
        id = "arr_drill_17",
        topicId = "arrays",
        lessonNumber = 17,
        title = "The Difference Array Technique",
        subtitle = "Executing range updates [L..R] + val in O(1) time",
        conceptDelta = "+1 Concept: Mark `diff[L] += val` and `diff[R + 1] -= val`; prefix sum restores final values",
        intuition = "If you have 10,000 range update operations adding values to intervals `[L..R]`, doing it naively takes O(N * Q). With a difference array: `diff[L] += val` begins the increase, and `diff[R + 1] -= val` cancels it out. A single prefix sum pass at the end recovers all values in O(N + Q)!",
        codePattern = """// Lesson 17: Difference Array Range Updates
public int[] getModifiedArray(int length, int[][] updates) {
    int[] diff = new int[length];
    for (int[] u : updates) {
        int start = u[0], end = u[1], val = u[2];
        diff[start] += val;
        if (end + 1 < length) diff[end + 1] -= val;
    }
    for (int i = 1; i < length; i++) {
        diff[i] += diff[i - 1];
    }
    return diff;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "length = 5, update [1, 3, +2]", "diff[1] += 2, diff[3+1=4] -= 2. diff = [0, 2, 0, 0, -2]."),
            DrillTraceStep(2, "update [2, 4, +3]", "diff[2] += 3, diff[5] out of bounds. diff = [0, 2, 3, 0, -2]."),
            DrillTraceStep(3, "Prefix sum phase", "i=0: 0. i=1: 0+2=2. i=2: 2+3=5. i=3: 5+0=5. i=4: 5+(-2)=3."),
            DrillTraceStep(4, "Final recovered array", "[0, 2, 5, 5, 3] computed with zero inner loops.")
        ),
        quizQuestion = DrillQuiz(
            question = "What is the time complexity to apply Q range updates using a difference array?",
            options = listOf(
                "O(N * Q)",
                "O(Q + N)",
                "O(N log N)",
                "O(Q²)"
            ),
            correctOptionIndex = 1,
            explanation = "Each update is O(1) with 2 index writes, taking O(Q) total. The final prefix sum pass takes O(N). Total time is O(Q + N)."
        )
    ),
    // Lesson 18
    TrainingDrill(
        id = "arr_drill_18",
        topicId = "arrays",
        lessonNumber = 18,
        title = "Modulo Prefix Equivalence",
        subtitle = "Finding subarrays divisible by K using remainder pigeonholes",
        conceptDelta = "+1 Concept: If `(prefixSum[j] - prefixSum[i]) % K == 0`, then `prefixSum[j] % K == prefixSum[i] % K`",
        intuition = "By modular arithmetic, a subarray between `i` and `j` has a sum divisible by `K` if and only if both prefix sums yield the exact same remainder when divided by `K`. We count remainder frequencies; if a remainder has appeared `C` times, it creates `C` new valid subarrays!",
        codePattern = """// Lesson 18: Modulo Prefix Remainder Counting
public int subarraysDivByK(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);
    int runningSum = 0, count = 0;
    for (int num : nums) {
        runningSum += num;
        int rem = ((runningSum % k) + k) % k;
        count += map.getOrDefault(rem, 0);
        map.put(rem, map.getOrDefault(rem, 0) + 1);
    }
    return count;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "nums = [4, 5, 0, -2, -3, 1], k = 5", "map = {0: 1}, runningSum = 0."),
            DrillTraceStep(2, "num = 4: sum = 4, rem = 4", "rem 4 unseen. map = {0:1, 4:1}."),
            DrillTraceStep(3, "num = 5: sum = 9, rem = 4", "rem 4 seen once! count += 1. Subarray [5] is divisible. map = {0:1, 4:2}."),
            DrillTraceStep(4, "num = 0: sum = 9, rem = 4", "rem 4 seen twice! count += 2. Subarrays [0] and [5, 0]. map = {0:1, 4:3}."),
            DrillTraceStep(5, "Result accumulates", "Total valid subarrays = 7 in O(N) single pass.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why is `((runningSum % k) + k) % k` necessary in Java?",
            options = listOf(
                "Because Java's % operator returns negative remainders for negative operands (e.g. -2 % 5 = -2)",
                "To prevent integer overflow",
                "Because k could be zero",
                "It is a compiler optimization"
            ),
            correctOptionIndex = 0,
            explanation = "In Java, -2 % 5 yields -2. Adding k and taking modulo again maps negative remainders into the canonical positive range [0..k-1]."
        )
    ),
    // Lesson 19
    TrainingDrill(
        id = "arr_drill_19",
        topicId = "arrays",
        lessonNumber = 19,
        title = "Dual Peak Inward Contraction (Trapping Rain Water)",
        subtitle = "Calculating volume bounded by running leftMax and rightMax peaks",
        conceptDelta = "+1 Concept: Trapped water at position `i` is strictly determined by `min(leftMax, rightMax) - height[i]`",
        intuition = "At any index, the height of water is trapped by the shorter of the two highest walls to its left and right. With two pointers starting at both ends, if `leftMax < rightMax`, we know with 100% certainty that `leftMax` is the true global bottleneck for the left pointer, regardless of what lies between!",
        codePattern = """// Lesson 19: Two-Pointer Trapping Rain Water
public int trap(int[] height) {
    int left = 0, right = height.length - 1;
    int leftMax = 0, rightMax = 0, totalWater = 0;
    while (left < right) {
        if (height[left] < height[right]) {
            if (height[left] >= leftMax) leftMax = height[left];
            else totalWater += leftMax - height[left];
            left++;
        } else {
            if (height[right] >= rightMax) rightMax = height[right];
            else totalWater += rightMax - height[right];
            right--;
        }
    }
    return totalWater;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "height = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]", "left = 0 (0), right = 11 (1)."),
            DrillTraceStep(2, "height[left]=0 < height[right]=1", "leftMax becomes 0. left++ -> 1."),
            DrillTraceStep(3, "left = 1 (1): leftMax becomes 1", "left++ -> 2 (0)."),
            DrillTraceStep(4, "left = 2 (0): height[2] < leftMax (1)", "Water trapped = leftMax - height[2] = 1 - 0 = 1 unit! totalWater = 1."),
            DrillTraceStep(5, "Pointers meet at global peak 3", "All trapped pockets calculated in O(N) time and O(1) space.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why do we only need to check `height[left] < height[right]` to know `leftMax` is the bottleneck?",
            options = listOf(
                "Because rightMax is at least height[right], which is greater than leftMax, guaranteeing the right side wall is tall enough",
                "Because water flows downwards",
                "Because left pointer always moves first",
                "Because leftMax equals rightMax"
            ),
            correctOptionIndex = 0,
            explanation = "Since height[left] < height[right], and rightMax >= height[right], rightMax is guaranteed to be >= leftMax. Hence, leftMax is the definitive limiting factor."
        )
    ),
    // Lesson 20
    TrainingDrill(
        id = "arr_drill_20",
        topicId = "arrays",
        lessonNumber = 20,
        title = "Greedy Reachability Horizon",
        subtitle = "Tracking maximum reachable index without recursive branching",
        conceptDelta = "+1 Concept: `maxReach = Math.max(maxReach, i + nums[i])`; fail if `i > maxReach`",
        intuition = "In Jump Game, you don't need to try every jump sequence. Simply track the furthest index `maxReach` you can ever touch. As you step forward index by index: if your current position `i` exceeds `maxReach`, you've stepped into an unreachable abyss (return false). Otherwise, update `maxReach`.",
        codePattern = """// Lesson 20: Greedy Horizon Tracking
public boolean canJump(int[] nums) {
    int maxReach = 0;
    for (int i = 0; i < nums.length; i++) {
        if (i > maxReach) return false;
        maxReach = Math.max(maxReach, i + nums[i]);
        if (maxReach >= nums.length - 1) return true;
    }
    return true;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "nums = [2, 3, 1, 1, 4]", "maxReach = 0."),
            DrillTraceStep(2, "i = 0: nums[0] = 2", "maxReach = max(0, 0 + 2) = 2."),
            DrillTraceStep(3, "i = 1: nums[1] = 3", "maxReach = max(2, 1 + 3) = 4 >= 4! Target reached early! Return true."),
            DrillTraceStep(4, "Counter-example: [3, 2, 1, 0, 4]", "At i = 3: maxReach = 3. At i = 4: 4 > maxReach(3) -> trapped! Returns false.")
        ),
        quizQuestion = DrillQuiz(
            question = "What condition indicates that reaching the end of the array is impossible?",
            options = listOf(
                "When the current index i becomes strictly greater than maxReach",
                "When any element in the array is 0",
                "When array length is odd",
                "When nums[0] == nums[nums.length - 1]"
            ),
            correctOptionIndex = 0,
            explanation = "If the current index i is greater than maxReach, it means no prior jump was able to reach index i, making further progress impossible."
        )
    ),
    // Lesson 21
    TrainingDrill(
        id = "arr_drill_21",
        topicId = "arrays",
        lessonNumber = 21,
        title = "Classical Binary Search & Overflow-Safe Midpoint",
        subtitle = "Eliminating half the search space per iteration safely",
        conceptDelta = "+1 Concept: `mid = low + (high - low) / 2` to prevent 32-bit signed integer overflow",
        intuition = "Binary search on a sorted array cuts the search space in half each step: O(log N). The infamous bug that lived in standard libraries for decades was `(low + high) / 2`. If `low + high > 2^31 - 1`, it overflows into a negative number! Always use `low + (high - low) / 2`.",
        codePattern = """// Lesson 21: Safe Binary Search
public int binarySearch(int[] nums, int target) {
    int low = 0, high = nums.length - 1;
    while (low <= high) {
        int mid = low + (high - low) / 2;
        if (nums[mid] == target) return mid;
        else if (nums[mid] < target) low = mid + 1;
        else high = mid - 1;
    }
    return -1;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "nums = [-1, 0, 3, 5, 9, 12], target = 9", "low = 0, high = 5. mid = 0 + 5/2 = 2 (val 3)."),
            DrillTraceStep(2, "nums[2] = 3 < 9", "Discard left half. low = mid + 1 = 3."),
            DrillTraceStep(3, "low = 3, high = 5", "mid = 3 + 2/2 = 4 (val 9). nums[4] == 9 -> MATCH! Return index 4.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why does `(low + high) / 2` cause a bug when array size approaches 2 billion?",
            options = listOf(
                "Because low + high can exceed Integer.MAX_VALUE (2,147,483,647), overflowing to a negative integer and crashing with ArrayIndexOutOfBoundsException",
                "Because division by 2 is slow in Java",
                "Because low + high is always odd",
                "Because arrays cannot hold more than 1,000 elements"
            ),
            correctOptionIndex = 0,
            explanation = "Integer overflow wraps to negative numbers in two's complement representation, causing nums[mid] to throw an immediate exception."
        )
    ),
    // Lesson 22
    TrainingDrill(
        id = "arr_drill_22",
        topicId = "arrays",
        lessonNumber = 22,
        title = "Binary Search in Rotated Sorted Array",
        subtitle = "Identifying the guaranteed sorted half to direct the search",
        conceptDelta = "+1 Concept: In any rotated sorted array, AT LEAST ONE HALF (left or right of mid) is always perfectly sorted",
        intuition = "Even after rotation, splitting at `mid` always leaves one half strictly sorted. If `nums[low] <= nums[mid]`, the LEFT half is normally sorted. We then simply check if target lies within `[nums[low]..nums[mid]]`. If so, search left; otherwise search right!",
        codePattern = """// Lesson 22: Rotated Array Binary Search
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
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "nums = [4, 5, 6, 7, 0, 1, 2], target = 0", "low = 0 (4), high = 6 (2). mid = 3 (7)."),
            DrillTraceStep(2, "nums[0]=4 <= nums[3]=7", "Left half [4, 5, 6, 7] is sorted! Is 0 in [4..7]? No! Discard left half: low = mid + 1 = 4."),
            DrillTraceStep(3, "low = 4 (0), high = 6 (2)", "mid = 5 (1). nums[4]=0 <= nums[5]=1 -> left sorted. Target 0 == nums[4]. Match found in O(log N)!")
        ),
        quizQuestion = DrillQuiz(
            question = "How do we test if the left half `[low..mid]` of a rotated array is normally sorted?",
            options = listOf(
                "By checking if nums[low] <= nums[mid]",
                "By checking if nums[low] == target",
                "By sorting the array",
                "By checking if mid is even"
            ),
            correctOptionIndex = 0,
            explanation = "If the value at low is less than or equal to the value at mid, the rotation point must lie in the right half, leaving the left half monotonic and sorted."
        )
    ),
    // Lesson 23
    TrainingDrill(
        id = "arr_drill_23",
        topicId = "arrays",
        lessonNumber = 23,
        title = "Inflection Point Binary Search",
        subtitle = "Comparing mid against high to find the minimum element",
        conceptDelta = "+1 Concept: `if (nums[mid] > nums[high]) low = mid + 1; else high = mid;`",
        intuition = "To find the minimum element in a rotated sorted array, compare `nums[mid]` with `nums[high]`. If `nums[mid] > nums[high]`, the inflection cliff MUST lie to the right of `mid` (`low = mid + 1`). Otherwise, the minimum is either at `mid` or to the left (`high = mid`).",
        codePattern = """// Lesson 23: Finding the Inflection Minimum
public int findMin(int[] nums) {
    int low = 0, high = nums.length - 1;
    while (low < high) {
        int mid = low + (high - low) / 2;
        if (nums[mid] > nums[high]) {
            low = mid + 1;
        } else {
            high = mid;
        }
    }
    return nums[low];
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "nums = [3, 4, 5, 1, 2]", "low = 0 (3), high = 4 (2). mid = 2 (val 5)."),
            DrillTraceStep(2, "nums[2]=5 > nums[4]=2", "Inflection point is to the right! low = mid + 1 = 3."),
            DrillTraceStep(3, "low = 3 (1), high = 4 (2)", "mid = 3 (val 1). nums[3]=1 <= nums[4]=2 -> high = mid = 3."),
            DrillTraceStep(4, "low == high == 3", "Terminates cleanly at index 3 (value 1).")
        ),
        quizQuestion = DrillQuiz(
            question = "Why do we set `high = mid` instead of `high = mid - 1` when `nums[mid] <= nums[high]`?",
            options = listOf(
                "Because nums[mid] itself might be the minimum element, so excluding it could miss the answer",
                "Because mid - 1 would cause an infinite loop",
                "Because high must always be odd",
                "It is interchangeable with high = mid - 1"
            ),
            correctOptionIndex = 0,
            explanation = "If nums[mid] <= nums[high], nums[mid] could itself be the lowest value in the array. We cannot discard it until tested against other elements."
        )
    ),
    // Lesson 24
    TrainingDrill(
        id = "arr_drill_24",
        topicId = "arrays",
        lessonNumber = 24,
        title = "Binary Search on the Answer Domain",
        subtitle = "Searching over an abstract monotonic range of possible answers",
        conceptDelta = "+1 Concept: Binary searching over answer space [minSpeed..maxSpeed] with a feasibility predicate",
        intuition = "Binary search isn't just for sorted input arrays! If an answer has a monotonic threshold (e.g. at speed S, Koko finishes in time; at any speed > S, she ALSO finishes), we binary search the numerical range of possible speeds `[1..maxPile]` using a helper function `canFinish(speed)`.",
        codePattern = """// Lesson 24: Binary Search on Monotonic Answer
public int minEatingSpeed(int[] piles, int h) {
    int low = 1, high = 1;
    for (int p : piles) high = Math.max(high, p);
    
    while (low < high) {
        int mid = low + (high - low) / 2;
        if (canFinish(piles, mid, h)) {
            high = mid;
        } else {
            low = mid + 1;
        }
    }
    return low;
}
private boolean canFinish(int[] piles, int speed, int h) {
    int hours = 0;
    for (int p : piles) hours += (p + speed - 1) / speed;
    return hours <= h;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "piles = [3, 6, 7, 11], h = 8", "Search space: low = 1, high = 11."),
            DrillTraceStep(2, "Test mid = 6", "Hours = 1 + 1 + 2 + 2 = 6 <= 8 (Feasible!). high = 6."),
            DrillTraceStep(3, "Test mid = 3", "Hours = 1 + 2 + 3 + 4 = 10 > 8 (Too slow!). low = 4."),
            DrillTraceStep(4, "Test mid = 4", "Hours = 1 + 2 + 2 + 3 = 8 <= 8 (Feasible!). high = 4. Final optimal speed = 4.")
        ),
        quizQuestion = DrillQuiz(
            question = "What property must a problem possess to allow binary searching on the answer?",
            options = listOf(
                "Monotonicity: if an answer X is feasible, every answer > X (or < X) must also be feasible",
                "All array numbers must be prime",
                "The array must be presorted",
                "Answer must be a power of 2"
            ),
            correctOptionIndex = 0,
            explanation = "Binary search on answers requires a monotonic boolean predicate f(x) that transitions from False to True (or vice-versa) at exactly one optimal boundary."
        )
    ),
    // Lesson 25
    TrainingDrill(
        id = "arr_drill_25",
        topicId = "arrays",
        lessonNumber = 25,
        title = "Binary Search on Gradient Slopes",
        subtitle = "Ascending the slope to guarantee finding a local peak",
        conceptDelta = "+1 Concept: `if (nums[mid] < nums[mid + 1]) low = mid + 1; else high = mid;`",
        intuition = "An array can be completely unsorted, yet we can still find a peak element in O(log N)! If `nums[mid] < nums[mid + 1]`, we are on an ascending slope. Since the array boundary drops to -infinity, following the ascending slope upwards GUARANTEES encountering at least one peak.",
        codePattern = """// Lesson 25: Slope Climbing Binary Search
public int findPeakElement(int[] nums) {
    int low = 0, high = nums.length - 1;
    while (low < high) {
        int mid = low + (high - low) / 2;
        if (nums[mid] < nums[mid + 1]) {
            low = mid + 1;
        } else {
            high = mid;
        }
    }
    return low;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "nums = [1, 2, 1, 3, 5, 6, 4]", "low = 0, high = 6. mid = 3 (val 3)."),
            DrillTraceStep(2, "nums[3]=3 < nums[4]=5", "Ascending slope towards the right! low = mid + 1 = 4."),
            DrillTraceStep(3, "low = 4 (5), high = 6 (4)", "mid = 5 (val 6). nums[5]=6 > nums[6]=4 (descending!). high = mid = 5."),
            DrillTraceStep(4, "low == high == 5", "Peak found at index 5 (value 6) in O(log N) operations.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why does climbing the ascending slope guarantee finding a peak even in an unsorted array?",
            options = listOf(
                "Because values beyond the array boundaries are defined as -infinity; an upward slope must eventually peak or hit the boundary",
                "Because arrays can only have one peak",
                "Because Java sorts the array in hardware",
                "Because of the Dutch National Flag theorem"
            ),
            correctOptionIndex = 0,
            explanation = "Since nums[-1] = nums[n] = -inf, if nums[mid] < nums[mid+1], continuing rightward must either eventually drop (creating a peak) or hit index n-1, which will itself be a peak."
        )
    ),
    // Lesson 26
    TrainingDrill(
        id = "arr_drill_26",
        topicId = "arrays",
        lessonNumber = 26,
        title = "Matrix 1D Flattening & Coordinate Math",
        subtitle = "Treating a 2D matrix as a virtual 1D sorted array",
        conceptDelta = "+1 Concept: Coordinate mapping: `row = index / cols` and `col = index % cols`",
        intuition = "If an M x N matrix is sorted such that the first integer of each row is greater than the last integer of the previous row, the entire matrix is simply a 1D sorted array of length `M * N`. Rather than copying it, use integer division and modulo to map 1D indices directly to 2D coordinates.",
        codePattern = """// Lesson 26: 2D Matrix Binary Search
public boolean searchMatrix(int[][] matrix, int target) {
    int m = matrix.length, n = matrix[0].length;
    int low = 0, high = m * n - 1;
    while (low <= high) {
        int mid = low + (high - low) / 2;
        int row = mid / n;
        int col = mid % n;
        int val = matrix[row][col];
        if (val == target) return true;
        else if (val < target) low = mid + 1;
        else high = mid - 1;
    }
    return false;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "matrix 3x4 (m=3, n=4), target = 16", "Virtual length = 12. low = 0, high = 11."),
            DrillTraceStep(2, "mid = 5", "row = 5 / 4 = 1, col = 5 % 4 = 1. Inspect matrix[1][1] (e.g. 11)."),
            DrillTraceStep(3, "11 < 16", "low = mid + 1 = 6."),
            DrillTraceStep(4, "Binary search continues", "Dispatches with O(log(M * N)) time and O(1) auxiliary memory.")
        ),
        quizQuestion = DrillQuiz(
            question = "For an M x N matrix, what is the 2D coordinate for a 1D flattened index `k`?",
            options = listOf(
                "matrix[k / N][k % N]",
                "matrix[k % M][k / M]",
                "matrix[k / M][k % M]",
                "matrix[k * N][k]"
            ),
            correctOptionIndex = 0,
            explanation = "Division by row width N yields the 0-indexed row number, and the remainder modulo N yields the column position within that row."
        )
    ),
    // Lesson 27
    TrainingDrill(
        id = "arr_drill_27",
        topicId = "arrays",
        lessonNumber = 27,
        title = "Spiral Matrix (4 Boundary Walls)",
        subtitle = "Traversing 2D grids in concentric shells using shrinking borders",
        conceptDelta = "+1 Concept: 4 bounding walls (top, bottom, left, right) that shrink after each boundary traversal",
        intuition = "Traversing in a spiral is just visiting 4 walls in order: Right along `top`, Down along `right`, Left along `bottom`, and Up along `left`. After each traversal, the visited border contracts inward (`top++`, `right--`, etc.). Stop when the borders cross.",
        codePattern = """// Lesson 27: 4-Wall Spiral Traversal
public List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> result = new ArrayList<>();
    int top = 0, bottom = matrix.length - 1;
    int left = 0, right = matrix[0].length - 1;
    
    while (top <= bottom && left <= right) {
        for (int c = left; c <= right; c++) result.add(matrix[top][c]);
        top++;
        for (int r = top; r <= bottom; r++) result.add(matrix[r][right]);
        right--;
        if (top <= bottom) {
            for (int c = right; c >= left; c--) result.add(matrix[bottom][c]);
            bottom--;
        }
        if (left <= right) {
            for (int r = bottom; r >= top; r--) result.add(matrix[r][left]);
            left++;
        }
    }
    return result;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "matrix 3x3", "top=0, bottom=2, left=0, right=2."),
            DrillTraceStep(2, "Traverse Right across top", "Reads [1, 2, 3]. top becomes 1."),
            DrillTraceStep(3, "Traverse Down across right", "Reads [6, 9]. right becomes 1."),
            DrillTraceStep(4, "Traverse Left across bottom", "Reads [8, 7]. bottom becomes 1."),
            DrillTraceStep(5, "Traverse Up across left", "Reads [4]. left becomes 1. Loop visits center [5]. Finish!")
        ),
        quizQuestion = DrillQuiz(
            question = "Why are the `if (top <= bottom)` and `if (left <= right)` checks necessary before the bottom and left passes?",
            options = listOf(
                "Because the previous row or column increment may have caused the boundaries to cross, preventing duplicate traversals in rectangular matrices",
                "To prevent integer division by zero",
                "Because matrices must be square",
                "They are optional"
            ),
            correctOptionIndex = 0,
            explanation = "In non-square matrices (e.g. 1x3 or 3x1), top++ or right-- can cause the boundaries to cross after only the first or second pass."
        )
    ),
    // Lesson 28
    TrainingDrill(
        id = "arr_drill_28",
        topicId = "arrays",
        lessonNumber = 28,
        title = "In-Place Matrix Rotation 90° (Transpose + Reflect)",
        subtitle = "Rotating a 2D matrix clockwise without auxiliary grid memory",
        conceptDelta = "+1 Concept: Clockwise 90° rotation = Transpose (swap matrix[i][j] with matrix[j][i]) + Horizontal Reflection",
        intuition = "Trying to rotate four individual corners directly is messy and prone to off-by-one errors. A clean algebraic decomposition: 90° Clockwise Rotation = (1) Transpose matrix across main diagonal, followed by (2) Reverse each row horizontally. O(1) extra space!",
        codePattern = """// Lesson 28: Transpose & Reverse Rotation
public void rotate(int[][] matrix) {
    int n = matrix.length;
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            int temp = matrix[i][j];
            matrix[i][j] = matrix[j][i];
            matrix[j][i] = temp;
        }
    }
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n / 2; j++) {
            int temp = matrix[i][j];
            matrix[i][j] = matrix[i][n - 1 - j];
            matrix[i][n - 1 - j] = temp;
        }
    }
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "matrix = [[1,2],[3,4]]", "Original."),
            DrillTraceStep(2, "Transpose across diagonal", "Swap matrix[0][1]=2 with matrix[1][0]=3. Result: [[1,3],[2,4]]."),
            DrillTraceStep(3, "Reverse rows horizontally", "Row 0 becomes [3, 1]. Row 1 becomes [4, 2]. Final: [[3,1],[4,2]]. Exactly 90° clockwise!")
        ),
        quizQuestion = DrillQuiz(
            question = "How can you rotate a matrix 90° counter-clockwise using the same primitives?",
            options = listOf(
                "Transpose the matrix, then reverse each column vertically",
                "Reverse each row, then reverse each column",
                "Transpose twice",
                "Rotate the first element only"
            ),
            correctOptionIndex = 0,
            explanation = "A counter-clockwise 90° rotation is achieved by transposing the matrix and then reversing each column vertically (or reversing rows first, then transposing)."
        )
    ),
    // Lesson 29
    TrainingDrill(
        id = "arr_drill_29",
        topicId = "arrays",
        lessonNumber = 29,
        title = "In-Place Matrix Zeroing via Boundary Flags",
        subtitle = "Using the first row and column as O(1) auxiliary state storage",
        conceptDelta = "+1 Concept: Reusing row 0 and col 0 as marker flags instead of allocating boolean arrays",
        intuition = "Allocating `boolean[m]` and `boolean[n]` takes O(M + N) space. To achieve true O(1) space, reuse `matrix[0][j]` and `matrix[i][0]` as your zero-marker flags! We just need two single booleans `firstRowZero` and `firstColZero` to track whether row 0 and col 0 themselves originally had zeros.",
        codePattern = """// Lesson 29: O(1) Space Matrix Zeroes
public void setZeroes(int[][] matrix) {
    int m = matrix.length, n = matrix[0].length;
    boolean firstColZero = false;
    
    for (int i = 0; i < m; i++) {
        if (matrix[i][0] == 0) firstColZero = true;
        for (int j = 1; j < n; j++) {
            if (matrix[i][j] == 0) {
                matrix[i][0] = 0;
                matrix[0][j] = 0;
            }
        }
    }
    for (int i = m - 1; i >= 0; i--) {
        for (int j = n - 1; j >= 1; j--) {
            if (matrix[i][0] == 0 || matrix[0][j] == 0) matrix[i][j] = 0;
        }
        if (firstColZero) matrix[i][0] = 0;
    }
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "matrix with zero at (1, 1)", "Record flag at row marker matrix[1][0]=0 and col marker matrix[0][1]=0."),
            DrillTraceStep(2, "Marker inspection", "During back-pass, any cell (i, j) with matrix[i][0]==0 or matrix[0][j]==0 is set to 0."),
            DrillTraceStep(3, "Result", "Entire row 1 and col 1 zeroed in O(M*N) time and O(1) extra memory.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why must the zeroing back-pass iterate backwards (from bottom-right up)?",
            options = listOf(
                "To prevent overwriting row 0 and column 0 markers before all other cells have had a chance to read them",
                "Because arrays iterate backwards faster in Java",
                "To prevent NullPointerExceptions",
                "Because column 0 is immutable"
            ),
            correctOptionIndex = 0,
            explanation = "Row 0 and Col 0 store the status for the entire grid. If we zeroed them out early on a forward pass, all remaining rows would mistakenly read zeroes as original markers."
        )
    ),
    // Lesson 30
    TrainingDrill(
        id = "arr_drill_30",
        topicId = "arrays",
        lessonNumber = 30,
        title = "Boyer-Moore Majority Voting Algorithm",
        subtitle = "Finding the majority element (> N/2) in O(N) time and O(1) space",
        conceptDelta = "+1 Concept: Counter cancellation: pairwise annihilating different elements leaves the majority standing",
        intuition = "If an element occurs MORE than N/2 times, its count exceeds the sum of all other elements combined! We pick a `candidate` and maintain a `count`. When `count == 0`, pick the current element. If the next element matches `candidate`, increment count; if it differs, decrement count. Pairwise cancellation guarantees the majority survives.",
        codePattern = """// Lesson 30: Boyer-Moore Majority Voting
public int majorityElement(int[] nums) {
    int candidate = 0, count = 0;
    for (int num : nums) {
        if (count == 0) {
            candidate = num;
        }
        count += (num == candidate) ? 1 : -1;
    }
    return candidate;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "nums = [2, 2, 1, 1, 1, 2, 2]", "Init: count = 0, candidate = 0."),
            DrillTraceStep(2, "num = 2: count was 0", "candidate = 2, count = 1."),
            DrillTraceStep(3, "num = 2: match", "count becomes 2."),
            DrillTraceStep(4, "num = 1: mismatch", "count becomes 1."),
            DrillTraceStep(5, "num = 1: mismatch", "count becomes 0 (cancelled out!)."),
            DrillTraceStep(6, "num = 1: count was 0", "candidate becomes 1, count = 1."),
            DrillTraceStep(7, "nums = 2, 2", "1 cancelled, candidate 2 emerges with positive count. Result: 2 in O(1) space!")
        ),
        quizQuestion = DrillQuiz(
            question = "Under what condition is the Boyer-Moore majority voting candidate guaranteed to be the majority element?",
            options = listOf(
                "Only when a majority element (> N/2 occurrences) is guaranteed to exist in the array",
                "For any array, even if the most frequent element appears only twice",
                "Only when the array is sorted",
                "Only for arrays of odd length"
            ),
            correctOptionIndex = 0,
            explanation = "Boyer-Moore guarantees correctness if a true majority (> N/2) exists. If no element exceeds N/2, a second verification pass is needed to confirm the candidate's actual count."
        )
    )
)
