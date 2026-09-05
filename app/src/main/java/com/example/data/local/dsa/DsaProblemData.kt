package com.example.data.local.dsa

import com.example.domain.model.DsaProblem

object DsaProblemData {

    fun getAll(): List<DsaProblem> {
        return arrayProblems +
                stringProblems +
                recursionProblems +
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
            "recursion" -> recursionProblems
            "linked_list" -> linkedListProblems
            "stack" -> stackQueueProblems.filter { it.topic == "stack" }
            "queue" -> stackQueueProblems.filter { it.topic == "queue" }
            "trees" -> treeProblems
            "graphs" -> graphProblems
            "dp" -> dpProblems
            else -> arrayProblems
        }
    }

    val arrayProblems = ArrayDsaProblems.getProblems()
    val stringProblems = StringDsaProblems.getProblems()
    val recursionProblems = RecursionDsaProblems.getProblems()

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
