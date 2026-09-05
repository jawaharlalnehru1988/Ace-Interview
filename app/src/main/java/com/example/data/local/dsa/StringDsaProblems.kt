package com.example.data.local.dsa

import com.example.domain.model.DsaProblem

object StringDsaProblems {
    fun getProblems(): List<DsaProblem> {
        return problems
    }

    private val problems = listOf(
        DsaProblem(
            id = "str_001",
            topic = "strings",
            title = "Valid Palindrome",
            difficulty = "Easy",
            pattern = "Two Pointers",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given a string s, return true if it is a palindrome, considering only alphanumeric characters and ignoring cases.",
            exampleInput = "s = \"A man, a plan, a canal: Panama\"",
            exampleOutput = "true",
            keyInsight = "Use two pointers from left and right skipping non-alphanumerics and comparing lowercased characters.",
            solutionCode = """class Solution {
    // Valid Palindrome
    public static void solve() {}
    // Core logic:
    // int l = 0, r = s.length() - 1; while (l < r) { while (l < r && !Character.isLetterOrDigit(s.charAt(l))) l++; while (l < r && !Character.isLetterOrDigit(s.charAt(r))) r--; if (Character.toLowerCase(s.charAt(l++)) != Character.toLowerCase(s.charAt(r--))) return false; } return true;
}"""
        ),
        DsaProblem(
            id = "str_002",
            topic = "strings",
            title = "Valid Anagram",
            difficulty = "Easy",
            pattern = "Frequency Map / Array",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given two strings s and t, return true if t is an anagram of s, and false otherwise.",
            exampleInput = "s = \"anagram\", t = \"nagaram\"",
            exampleOutput = "true",
            keyInsight = "Use an integer array of size 26. Increment counts for s and decrement for t. All entries must equal zero.",
            solutionCode = """class Solution {
    // Valid Anagram
    public static void solve() {}
    // Core logic:
    // if (s.length() != t.length()) return false; int[] count = new int[26]; for (int i = 0; i < s.length(); i++) { count[s.charAt(i) - 'a']++; count[t.charAt(i) - 'a']--; } for (int c : count) if (c != 0) return false; return true;
}"""
        ),
        DsaProblem(
            id = "str_003",
            topic = "strings",
            title = "Longest Common Prefix",
            difficulty = "Easy",
            pattern = "Horizontal Scanning",
            timeComplexity = "O(S)",
            spaceComplexity = "O(1)",
            description = "Write a function to find the longest common prefix string amongst an array of strings.",
            exampleInput = "strs = [\"flower\",\"flow\",\"flight\"]",
            exampleOutput = "\"fl\"",
            keyInsight = "Initialize prefix with first string. While current string does not start with prefix, trim one character from end of prefix.",
            solutionCode = """class Solution {
    // Longest Common Prefix
    public static void solve() {}
    // Core logic:
    // if (strs == null || strs.length == 0) return ""; String prefix = strs[0]; for (int i = 1; i < strs.length; i++) while (!strs[i].startsWith(prefix)) prefix = prefix.substring(0, prefix.length() - 1); return prefix;
}"""
        ),
        DsaProblem(
            id = "str_004",
            topic = "strings",
            title = "Reverse String",
            difficulty = "Easy",
            pattern = "Two Pointers In-Place",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Write a function that reverses a string. The input string is given as an array of characters s.",
            exampleInput = "s = [\"h\",\"e\",\"l\",\"l\",\"o\"]",
            exampleOutput = "[\"o\",\"l\",\"l\",\"e\",\"h\"]",
            keyInsight = "Swap characters at left and right pointers while moving inward.",
            solutionCode = """class Solution {
    // Reverse String
    public static void solve() {}
    // Core logic:
    // int l = 0, r = s.length - 1; while (l < r) { char t = s[l]; s[l++] = s[r]; s[r--] = t; }
}"""
        ),
        DsaProblem(
            id = "str_005",
            topic = "strings",
            title = "First Unique Character in a String",
            difficulty = "Easy",
            pattern = "Frequency Array",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.",
            exampleInput = "s = \"leetcode\"",
            exampleOutput = "0",
            keyInsight = "Count frequencies of each character in an array of size 26, then find the first character in s with count 1.",
            solutionCode = """class Solution {
    // First Unique Character in a String
    public static void solve() {}
    // Core logic:
    // int[] count = new int[26]; for (char c : s.toCharArray()) count[c - 'a']++; for (int i = 0; i < s.length(); i++) if (count[s.charAt(i) - 'a'] == 1) return i; return -1;
}"""
        ),
        DsaProblem(
            id = "str_006",
            topic = "strings",
            title = "String to Integer (atoi)",
            difficulty = "Medium",
            pattern = "State Machine / Parsing",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer.",
            exampleInput = "s = \"   -042\"",
            exampleOutput = "-42",
            keyInsight = "Skip leading whitespace, determine sign, parse digits checking for overflow before multiplying by 10.",
            solutionCode = """class Solution {
    // String to Integer (atoi)
    public static void solve() {}
    // Core logic:
    // int i = 0, n = s.length(), sign = 1; while (i < n && s.charAt(i) == ' ') i++; if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) sign = (s.charAt(i++) == '-') ? -1 : 1; long num = 0; while (i < n && Character.isDigit(s.charAt(i))) { num = num * 10 + (s.charAt(i++) - '0'); if (sign * num > Integer.MAX_VALUE) return Integer.MAX_VALUE; if (sign * num < Integer.MIN_VALUE) return Integer.MIN_VALUE; } return (int)(sign * num);
}"""
        ),
        DsaProblem(
            id = "str_007",
            topic = "strings",
            title = "Find the Index of the First Occurrence in a String",
            difficulty = "Easy",
            pattern = "Sliding Window / Substring Matching",
            timeComplexity = "O(N*M)",
            spaceComplexity = "O(1)",
            description = "Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.",
            exampleInput = "haystack = \"sadbutsad\", needle = \"sad\"",
            exampleOutput = "0",
            keyInsight = "Slide a window of length needle.length() across haystack and compare characters.",
            solutionCode = """class Solution {
    // Find the Index of the First Occurrence in a String
    public static void solve() {}
    // Core logic:
    // int hLen = haystack.length(), nLen = needle.length(); for (int i = 0; i <= hLen - nLen; i++) if (haystack.substring(i, i + nLen).equals(needle)) return i; return -1;
}"""
        ),
        DsaProblem(
            id = "str_008",
            topic = "strings",
            title = "Valid Parentheses",
            difficulty = "Easy",
            pattern = "Stack",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.",
            exampleInput = "s = \"()[]{}\"",
            exampleOutput = "true",
            keyInsight = "Push closing brackets corresponding to opening brackets onto stack. For closing brackets, pop and verify equality.",
            solutionCode = """class Solution {
    // Valid Parentheses
    public static void solve() {}
    // Core logic:
    // Deque<Character> stack = new ArrayDeque<>(); for (char c : s.toCharArray()) { if (c == '(') stack.push(')'); else if (c == '{') stack.push('}'); else if (c == '[') stack.push(']'); else if (stack.isEmpty() || stack.pop() != c) return false; } return stack.isEmpty();
}"""
        ),
        DsaProblem(
            id = "str_009",
            topic = "strings",
            title = "Is Subsequence",
            difficulty = "Easy",
            pattern = "Two Pointers",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given two strings s and t, return true if s is a subsequence of t, or false otherwise.",
            exampleInput = "s = \"abc\", t = \"ahbgdc\"",
            exampleOutput = "true",
            keyInsight = "Pointer i scans s, pointer j scans t. Whenever s[i] == t[j], increment i. Return true if i == s.length().",
            solutionCode = """class Solution {
    // Is Subsequence
    public static void solve() {}
    // Core logic:
    // int i = 0, j = 0; while (i < s.length() && j < t.length()) { if (s.charAt(i) == t.charAt(j)) i++; j++; } return i == s.length();
}"""
        ),
        DsaProblem(
            id = "str_010",
            topic = "strings",
            title = "Reverse Words in a String",
            difficulty = "Medium",
            pattern = "Two Pointers / String Tokenization",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given an input string s, reverse the order of the words. Return a string of the words in reverse order concatenated by a single space.",
            exampleInput = "s = \"the sky is blue\"",
            exampleOutput = "\"blue is sky the\"",
            keyInsight = "Split by whitespace ignoring empty tokens, or traverse from right to left collecting words.",
            solutionCode = """class Solution {
    // Reverse Words in a String
    public static void solve() {}
    // Core logic:
    // String[] words = s.trim().split("\\s+"); StringBuilder sb = new StringBuilder(); for (int i = words.length - 1; i >= 0; i--) { sb.append(words[i]); if (i > 0) sb.append(" "); } return sb.toString();
}"""
        ),
        DsaProblem(
            id = "str_011",
            topic = "strings",
            title = "Reverse Words in a String III",
            difficulty = "Easy",
            pattern = "In-Place Word Reversal",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given a string s, reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.",
            exampleInput = "s = \"Let's take LeetCode contest\"",
            exampleOutput = "\"s'teL ekat edoCteeL tsetnoc\"",
            keyInsight = "Find boundaries of each word separated by spaces and reverse characters of each word in-place.",
            solutionCode = """class Solution {
    // Reverse Words in a String III
    public static void solve() {}
    // Core logic:
    // char[] arr = s.toCharArray(); int start = 0; for (int i = 0; i <= arr.length; i++) { if (i == arr.length || arr[i] == ' ') { int l = start, r = i - 1; while (l < r) { char t = arr[l]; arr[l++] = arr[r]; arr[r--] = t; } start = i + 1; } } return new String(arr);
}"""
        ),
        DsaProblem(
            id = "str_012",
            topic = "strings",
            title = "Longest Palindromic Substring",
            difficulty = "Medium",
            pattern = "Expand Around Center",
            timeComplexity = "O(N^2)",
            spaceComplexity = "O(1)",
            description = "Given a string s, return the longest palindromic substring in s.",
            exampleInput = "s = \"babad\"",
            exampleOutput = "\"bab\" (or \"aba\")",
            keyInsight = "Expand around each character for odd-length palindromes (i, i) and between characters for even-length palindromes (i, i+1).",
            solutionCode = """class Solution {
    // Longest Palindromic Substring
    public static void solve() {}
    // Core logic:
    // int start = 0, end = 0; for (int i = 0; i < s.length(); i++) { int len1 = expand(s, i, i), len2 = expand(s, i, i + 1); int len = Math.max(len1, len2); if (len > end - start) { start = i - (len - 1) / 2; end = i + len / 2; } } return s.substring(start, end + 1);
}"""
        ),
        DsaProblem(
            id = "str_013",
            topic = "strings",
            title = "Group Anagrams",
            difficulty = "Medium",
            pattern = "Hash Map / Sorted Key or Frequency Tuple",
            timeComplexity = "O(N * K log K)",
            spaceComplexity = "O(N * K)",
            description = "Given an array of strings strs, group the anagrams together. You can return the answer in any order.",
            exampleInput = "strs = [\"eat\",\"tea\",\"tan\",\"ate\",\"nat\",\"bat\"]",
            exampleOutput = "[[\"bat\"],[\"nat\",\"tan\"],[\"ate\",\"eat\",\"tea\"]]",
            keyInsight = "Sort each string's characters to form a canonical key. Group all strings with the same sorted key in a HashMap.",
            solutionCode = """class Solution {
    // Group Anagrams
    public static void solve() {}
    // Core logic:
    // Map<String, List<String>> map = new HashMap<>(); for (String s : strs) { char[] ca = s.toCharArray(); Arrays.sort(ca); String key = String.valueOf(ca); map.computeIfAbsent(key, k -> new ArrayList<>()).add(s); } return new ArrayList<>(map.values());
}"""
        ),
        DsaProblem(
            id = "str_014",
            topic = "strings",
            title = "Longest Substring Without Repeating Characters",
            difficulty = "Medium",
            pattern = "Sliding Window / Last Seen Index",
            timeComplexity = "O(N)",
            spaceComplexity = "O(min(N, Alphabet))",
            description = "Given a string s, find the length of the longest substring without repeating characters.",
            exampleInput = "s = \"abcabcbb\"",
            exampleOutput = "3 (\"abc\")",
            keyInsight = "Slide window [l, r]. If character at r was seen at index >= l, advance left pointer to lastSeen[char] + 1.",
            solutionCode = """class Solution {
    // Longest Substring Without Repeating Characters
    public static void solve() {}
    // Core logic:
    // int[] last = new int[128]; Arrays.fill(last, -1); int max = 0, l = 0; for (int r = 0; r < s.length(); r++) { char c = s.charAt(r); if (last[c] >= l) l = last[c] + 1; last[c] = r; max = Math.max(max, r - l + 1); } return max;
}"""
        ),
        DsaProblem(
            id = "str_015",
            topic = "strings",
            title = "Palindrome Permutation",
            difficulty = "Easy",
            pattern = "Hash Set / Bitmask",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given a string s, return true if a permutation of the string could form a palindrome.",
            exampleInput = "s = \"carerac\"",
            exampleOutput = "true (\"racecar\")",
            keyInsight = "A string can form a palindrome if and only if at most one character has an odd frequency.",
            solutionCode = """class Solution {
    // Palindrome Permutation
    public static void solve() {}
    // Core logic:
    // Set<Character> oddSet = new HashSet<>(); for (char c : s.toCharArray()) { if (!oddSet.add(c)) oddSet.remove(c); } return oddSet.size() <= 1;
}"""
        ),
        DsaProblem(
            id = "str_016",
            topic = "strings",
            title = "Count and Say",
            difficulty = "Medium",
            pattern = "Run-Length Encoding / Simulation",
            timeComplexity = "O(N * M)",
            spaceComplexity = "O(M)",
            description = "The count-and-say sequence is a sequence of digit strings defined by run-length encoding the previous string.",
            exampleInput = "n = 4",
            exampleOutput = "\"1211\" (say \"21\" as one 2 then one 1)",
            keyInsight = "Iteratively build string by counting consecutive runs of identical characters and appending count + character.",
            solutionCode = """class Solution {
    // Count and Say
    public static void solve() {}
    // Core logic:
    // String s = "1"; for (int i = 2; i <= n; i++) { StringBuilder sb = new StringBuilder(); int count = 1; for (int j = 1; j < s.length(); j++) { if (s.charAt(j) == s.charAt(j - 1)) count++; else { sb.append(count).append(s.charAt(j - 1)); count = 1; } } sb.append(count).append(s.charAt(s.length() - 1)); s = sb.toString(); } return s;
}"""
        ),
        DsaProblem(
            id = "str_017",
            topic = "strings",
            title = "Add Strings",
            difficulty = "Easy",
            pattern = "Two Pointers / Column Addition",
            timeComplexity = "O(max(N,M))",
            spaceComplexity = "O(max(N,M))",
            description = "Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2 as a string without using BigInteger.",
            exampleInput = "num1 = \"11\", num2 = \"123\"",
            exampleOutput = "\"134\"",
            keyInsight = "Add digits from right to left with carry, just like elementary school column-addition.",
            solutionCode = """class Solution {
    // Add Strings
    public static void solve() {}
    // Core logic:
    // StringBuilder sb = new StringBuilder(); int i = num1.length() - 1, j = num2.length() - 1, carry = 0; while (i >= 0 || j >= 0 || carry > 0) { int d1 = (i >= 0) ? num1.charAt(i--) - '0' : 0; int d2 = (j >= 0) ? num2.charAt(j--) - '0' : 0; int sum = d1 + d2 + carry; sb.append(sum % 10); carry = sum / 10; } return sb.reverse().toString();
}"""
        ),
        DsaProblem(
            id = "str_018",
            topic = "strings",
            title = "Multiply Strings",
            difficulty = "Medium",
            pattern = "Grade School Multiplication",
            timeComplexity = "O(N*M)",
            spaceComplexity = "O(N+M)",
            description = "Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2 as a string.",
            exampleInput = "num1 = \"12\", num2 = \"10\"",
            exampleOutput = "\"120\"",
            keyInsight = "Product of digits at indices i and j contributes to positions i+j and i+j+1 in result array.",
            solutionCode = """class Solution {
    // Multiply Strings
    public static void solve() {}
    // Core logic:
    // int m = num1.length(), n = num2.length(); int[] pos = new int[m + n]; for (int i = m - 1; i >= 0; i--) for (int j = n - 1; j >= 0; j--) { int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0'); int p1 = i + j, p2 = i + j + 1; int sum = mul + pos[p2]; pos[p1] += sum / 10; pos[p2] = sum % 10; } StringBuilder sb = new StringBuilder(); for (int p : pos) if (!(sb.length() == 0 && p == 0)) sb.append(p); return sb.length() == 0 ? "0" : sb.toString();
}"""
        ),
        DsaProblem(
            id = "str_019",
            topic = "strings",
            title = "Longest Repeating Character Replacement",
            difficulty = "Medium",
            pattern = "Sliding Window",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Return the length of the longest substring containing the same letter you can get after performing at most k character replacements.",
            exampleInput = "s = \"AABABBA\", k = 1",
            exampleOutput = "4 (\"AABA\")",
            keyInsight = "Maintain sliding window. If window size - maxFrequency > k, shrink window from left.",
            solutionCode = """class Solution {
    // Longest Repeating Character Replacement
    public static void solve() {}
    // Core logic:
    // int[] count = new int[26]; int l = 0, maxCount = 0, maxLen = 0; for (int r = 0; r < s.length(); r++) { maxCount = Math.max(maxCount, ++count[s.charAt(r) - 'A']); while (r - l + 1 - maxCount > k) count[s.charAt(l++) - 'A']--; maxLen = Math.max(maxLen, r - l + 1); } return maxLen;
}"""
        ),
        DsaProblem(
            id = "str_020",
            topic = "strings",
            title = "Word Pattern",
            difficulty = "Easy",
            pattern = "Bijective Mapping",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given a pattern and a string s, find if s follows the same pattern where letters map 1:1 with words.",
            exampleInput = "pattern = \"abba\", s = \"dog cat cat dog\"",
            exampleOutput = "true",
            keyInsight = "Use two hash maps or compare index mappings between characters of pattern and words of s.",
            solutionCode = """class Solution {
    // Word Pattern
    public static void solve() {}
    // Core logic:
    // String[] words = s.split(" "); if (words.length != pattern.length()) return false; Map<Character, String> charToWord = new HashMap<>(); Map<String, Character> wordToChar = new HashMap<>(); for (int i = 0; i < pattern.length(); i++) { char c = pattern.charAt(i); String w = words[i]; if (!charToWord.computeIfAbsent(c, k -> w).equals(w)) return false; if (wordToChar.computeIfAbsent(w, k -> c) != c) return false; } return true;
}"""
        ),
        DsaProblem(
            id = "str_021",
            topic = "strings",
            title = "Isomorphic Strings",
            difficulty = "Easy",
            pattern = "Character Mapping Array",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given two strings s and t, determine if they are isomorphic (characters in s can be replaced to get t).",
            exampleInput = "s = \"egg\", t = \"add\"",
            exampleOutput = "true",
            keyInsight = "Store last seen indices of characters in two 256-sized arrays. Both characters must have identical last-seen indices.",
            solutionCode = """class Solution {
    // Isomorphic Strings
    public static void solve() {}
    // Core logic:
    // int[] m1 = new int[256], m2 = new int[256]; for (int i = 0; i < s.length(); i++) { if (m1[s.charAt(i)] != m2[t.charAt(i)]) return false; m1[s.charAt(i)] = i + 1; m2[t.charAt(i)] = i + 1; } return true;
}"""
        ),
        DsaProblem(
            id = "str_022",
            topic = "strings",
            title = "Valid Palindrome II",
            difficulty = "Easy",
            pattern = "Two Pointers (One Mismatch Tolerance)",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given a string s, return true if the s can be palindrome after deleting at most one character from it.",
            exampleInput = "s = \"abca\"",
            exampleOutput = "true (delete 'c')",
            keyInsight = "When s[l] != s[r], check if either substring(l+1, r) or substring(l, r-1) is a palindrome.",
            solutionCode = """class Solution {
    // Valid Palindrome II
    public static void solve() {}
    // Core logic:
    // int l = 0, r = s.length() - 1; while (l < r) { if (s.charAt(l) != s.charAt(r)) return isPal(s, l + 1, r) || isPal(s, l, r - 1); l++; r--; } return true;
}"""
        ),
        DsaProblem(
            id = "str_023",
            topic = "strings",
            title = "Length of Last Word",
            difficulty = "Easy",
            pattern = "Backward Traversal",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given a string s consisting of words and spaces, return the length of the last word in the string.",
            exampleInput = "s = \"   fly me   to   the moon  \"",
            exampleOutput = "4 (\"moon\")",
            keyInsight = "Traverse backwards from end: skip trailing spaces, then count non-space characters until space or start.",
            solutionCode = """class Solution {
    // Length of Last Word
    public static void solve() {}
    // Core logic:
    // int i = s.length() - 1, len = 0; while (i >= 0 && s.charAt(i) == ' ') i--; while (i >= 0 && s.charAt(i) != ' ') { len++; i--; } return len;
}"""
        ),
        DsaProblem(
            id = "str_024",
            topic = "strings",
            title = "Defanging an IP Address",
            difficulty = "Easy",
            pattern = "String Replacement",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given a valid (IPv4) IP address, return a defanged version of that IP address where every '.' is replaced with '[.]'.",
            exampleInput = "address = \"1.1.1.1\"",
            exampleOutput = "\"1[.]1[.]1[.]1\"",
            keyInsight = "Iterate characters or use StringBuilder to replace '.' with '[.]'.",
            solutionCode = """class Solution {
    // Defanging an IP Address
    public static void solve() {}
    // Core logic:
    // StringBuilder sb = new StringBuilder(); for (char c : address.toCharArray()) { if (c == '.') sb.append("[.]"); else sb.append(c); } return sb.toString();
}"""
        ),
        DsaProblem(
            id = "str_025",
            topic = "strings",
            title = "To Lower Case",
            difficulty = "Easy",
            pattern = "ASCII Bit Manipulation",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given a string s, return the string after replacing every uppercase letter with the same lowercase letter.",
            exampleInput = "s = \"Hello\"",
            exampleOutput = "\"hello\"",
            keyInsight = "If character is between 'A' and 'Z', add 32 (or bitwise OR with 32) to convert to lowercase.",
            solutionCode = """class Solution {
    // To Lower Case
    public static void solve() {}
    // Core logic:
    // char[] arr = s.toCharArray(); for (int i = 0; i < arr.length; i++) if (arr[i] >= 'A' && arr[i] <= 'Z') arr[i] = (char)(arr[i] + 32); return new String(arr);
}"""
        ),
        DsaProblem(
            id = "str_026",
            topic = "strings",
            title = "Ransom Note",
            difficulty = "Easy",
            pattern = "Frequency Counting",
            timeComplexity = "O(N+M)",
            spaceComplexity = "O(1)",
            description = "Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine.",
            exampleInput = "ransomNote = \"aa\", magazine = \"aab\"",
            exampleOutput = "true",
            keyInsight = "Count available characters in magazine using int[26]. Decrement count for ransomNote. Return false if any count < 0.",
            solutionCode = """class Solution {
    // Ransom Note
    public static void solve() {}
    // Core logic:
    // int[] count = new int[26]; for (char c : magazine.toCharArray()) count[c - 'a']++; for (char c : ransomNote.toCharArray()) if (--count[c - 'a'] < 0) return false; return true;
}"""
        ),
        DsaProblem(
            id = "str_027",
            topic = "strings",
            title = "Roman to Integer",
            difficulty = "Easy",
            pattern = "Symbol Value Lookups",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given a roman numeral string s, convert it to an integer.",
            exampleInput = "s = \"MCMXCIV\"",
            exampleOutput = "1994",
            keyInsight = "Map roman symbols to integers. If current symbol value is smaller than next symbol value, subtract it; otherwise add.",
            solutionCode = """class Solution {
    // Roman to Integer
    public static void solve() {}
    // Core logic:
    // Map<Character, Integer> map = Map.of('I',1,'V',5,'X',10,'L',50,'C',100,'D',500,'M',1000); int res = 0; for (int i = 0; i < s.length(); i++) { int cur = map.get(s.charAt(i)); if (i + 1 < s.length() && cur < map.get(s.charAt(i + 1))) res -= cur; else res += cur; } return res;
}"""
        ),
        DsaProblem(
            id = "str_028",
            topic = "strings",
            title = "Integer to Roman",
            difficulty = "Medium",
            pattern = "Greedy Subtraction",
            timeComplexity = "O(1)",
            spaceComplexity = "O(1)",
            description = "Given an integer, convert it to a roman numeral string.",
            exampleInput = "num = 58",
            exampleOutput = "\"LVIII\"",
            keyInsight = "Pair values and roman symbols in descending order (1000, 900, 500, 400...). Greedily append symbols while num >= val.",
            solutionCode = """class Solution {
    // Integer to Roman
    public static void solve() {}
    // Core logic:
    // int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1}; String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"}; StringBuilder sb = new StringBuilder(); for (int i = 0; i < values.length; i++) while (num >= values[i]) { num -= values[i]; sb.append(symbols[i]); } return sb.toString();
}"""
        ),
        DsaProblem(
            id = "str_029",
            topic = "strings",
            title = "Remove All Adjacent Duplicates In String",
            difficulty = "Easy",
            pattern = "Stack / StringBuilder as Stack",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "You are given a string s. A duplicate removal consists of choosing two adjacent and equal letters and removing them.",
            exampleInput = "s = \"abbaca\"",
            exampleOutput = "\"ca\"",
            keyInsight = "Use StringBuilder as a stack. If incoming character equals top of stack, delete last char; else append char.",
            solutionCode = """class Solution {
    // Remove All Adjacent Duplicates In String
    public static void solve() {}
    // Core logic:
    // StringBuilder sb = new StringBuilder(); for (char c : s.toCharArray()) { int len = sb.length(); if (len > 0 && sb.charAt(len - 1) == c) sb.deleteCharAt(len - 1); else sb.append(c); } return sb.toString();
}"""
        ),
        DsaProblem(
            id = "str_030",
            topic = "strings",
            title = "Backspace String Compare",
            difficulty = "Easy",
            pattern = "Two Pointers from Back",
            timeComplexity = "O(N+M)",
            spaceComplexity = "O(1)",
            description = "Given two strings s and t, return true if they are equal when both are typed into empty text editors ('#' is backspace).",
            exampleInput = "s = \"ab#c\", t = \"ad#c\"",
            exampleOutput = "true (\"ac\" == \"ac\")",
            keyInsight = "Iterate backwards from both strings. Count '#' symbols to skip valid characters, then compare remaining valid characters.",
            solutionCode = """class Solution {
    // Backspace String Compare
    public static void solve() {}
    // Core logic:
    // int i = s.length() - 1, j = t.length() - 1, skipS = 0, skipT = 0; while (i >= 0 || j >= 0) { while (i >= 0) { if (s.charAt(i) == '#') { skipS++; i--; } else if (skipS > 0) { skipS--; i--; } else break; } while (j >= 0) { if (t.charAt(j) == '#') { skipT++; j--; } else if (skipT > 0) { skipT--; j--; } else break; } if (i >= 0 && j >= 0 && s.charAt(i) != t.charAt(j)) return false; if ((i >= 0) != (j >= 0)) return false; i--; j--; } return true;
}"""
        ),
        DsaProblem(
            id = "str_031",
            topic = "strings",
            title = "Fizz Buzz",
            difficulty = "Easy",
            pattern = "Simulation",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Return string array where answer[i] is FizzBuzz if divisible by 3 and 5, Fizz if by 3, Buzz if by 5, or string of i.",
            exampleInput = "n = 5",
            exampleOutput = "[\"1\",\"2\",\"Fizz\",\"4\",\"Buzz\"]",
            keyInsight = "Check divisibility by 15, then 3, then 5, else string conversion.",
            solutionCode = """class Solution {
    // Fizz Buzz
    public static void solve() {}
    // Core logic:
    // List<String> res = new ArrayList<>(); for (int i = 1; i <= n; i++) { if (i % 15 == 0) res.add("FizzBuzz"); else if (i % 3 == 0) res.add("Fizz"); else if (i % 5 == 0) res.add("Buzz"); else res.add(String.valueOf(i)); } return res;
}"""
        ),
        DsaProblem(
            id = "str_032",
            topic = "strings",
            title = "Excel Sheet Column Number",
            difficulty = "Easy",
            pattern = "Base-26 Conversion",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given a string columnTitle that represents the column title as appears in an Excel sheet, return its corresponding column number.",
            exampleInput = "columnTitle = \"AB\"",
            exampleOutput = "28",
            keyInsight = "Accumulate in base 26: res = res * 26 + (c - 'A' + 1).",
            solutionCode = """class Solution {
    // Excel Sheet Column Number
    public static void solve() {}
    // Core logic:
    // int res = 0; for (char c : columnTitle.toCharArray()) res = res * 26 + (c - 'A' + 1); return res;
}"""
        ),
        DsaProblem(
            id = "str_033",
            topic = "strings",
            title = "Excel Sheet Column Title",
            difficulty = "Easy",
            pattern = "Base-26 Reverse Conversion",
            timeComplexity = "O(log26 N)",
            spaceComplexity = "O(1)",
            description = "Given an integer columnNumber, return its corresponding column title as it appears in an Excel sheet.",
            exampleInput = "columnNumber = 28",
            exampleOutput = "\"AB\"",
            keyInsight = "Decrement columnNumber by 1 at each step to account for 1-based indexing, then prepend character (col % 26 + 'A').",
            solutionCode = """class Solution {
    // Excel Sheet Column Title
    public static void solve() {}
    // Core logic:
    // StringBuilder sb = new StringBuilder(); while (columnNumber > 0) { columnNumber--; sb.append((char)(columnNumber % 26 + 'A')); columnNumber /= 26; } return sb.reverse().toString();
}"""
        ),
        DsaProblem(
            id = "str_034",
            topic = "strings",
            title = "Longest Palindrome",
            difficulty = "Easy",
            pattern = "Frequency Map",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given a string s which consists of lowercase or uppercase letters, return the length of the longest palindrome that can be built.",
            exampleInput = "s = \"abccccdd\"",
            exampleOutput = "7 (\"dccaccd\")",
            keyInsight = "Count character pairs. Add all even counts. If any character has an odd count, add 1 for the center.",
            solutionCode = """class Solution {
    // Longest Palindrome
    public static void solve() {}
    // Core logic:
    // int[] count = new int[128]; for (char c : s.toCharArray()) count[c]++; int len = 0; boolean odd = false; for (int c : count) { len += (c / 2) * 2; if (c % 2 == 1) odd = true; } return odd ? len + 1 : len;
}"""
        ),
        DsaProblem(
            id = "str_035",
            topic = "strings",
            title = "Detect Capital",
            difficulty = "Easy",
            pattern = "Case Rules Validation",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "We define the usage of capitals in a word to be right when all capitals, all lowercase, or only first capital.",
            exampleInput = "word = \"USA\"",
            exampleOutput = "true",
            keyInsight = "Count uppercase characters. Word is valid if upper count is 0, length, or 1 (at index 0).",
            solutionCode = """class Solution {
    // Detect Capital
    public static void solve() {}
    // Core logic:
    // int upper = 0; for (char c : word.toCharArray()) if (Character.isUpperCase(c)) upper++; return upper == 0 || upper == word.length() || (upper == 1 && Character.isUpperCase(word.charAt(0)));
}"""
        ),
        DsaProblem(
            id = "str_036",
            topic = "strings",
            title = "Reverse Vowels of a String",
            difficulty = "Easy",
            pattern = "Two Pointers",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given a string s, reverse only all the vowels in the string and return it.",
            exampleInput = "s = \"hello\"",
            exampleOutput = "\"holle\"",
            keyInsight = "Use two pointers from left and right. Advance pointers until both hit a vowel, swap, and repeat.",
            solutionCode = """class Solution {
    // Reverse Vowels of a String
    public static void solve() {}
    // Core logic:
    // char[] arr = s.toCharArray(); int l = 0, r = arr.length - 1; String vowels = "aeiouAEIOU"; while (l < r) { while (l < r && vowels.indexOf(arr[l]) == -1) l++; while (l < r && vowels.indexOf(arr[r]) == -1) r--; char t = arr[l]; arr[l++] = arr[r]; arr[r--] = t; } return new String(arr);
}"""
        ),
        DsaProblem(
            id = "str_037",
            topic = "strings",
            title = "Reverse Only Letters",
            difficulty = "Easy",
            pattern = "Two Pointers",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given a string s, reverse the string according to the following rules: all non-letters remain in place, letters are reversed.",
            exampleInput = "s = \"a-bC-dEf-ghIj\"",
            exampleOutput = "\"j-Ih-gfE-dCba\"",
            keyInsight = "Two pointers skipping non-letter characters and swapping letters.",
            solutionCode = """class Solution {
    // Reverse Only Letters
    public static void solve() {}
    // Core logic:
    // char[] arr = s.toCharArray(); int l = 0, r = arr.length - 1; while (l < r) { while (l < r && !Character.isLetter(arr[l])) l++; while (l < r && !Character.isLetter(arr[r])) r--; char t = arr[l]; arr[l++] = arr[r]; arr[r--] = t; } return new String(arr);
}"""
        ),
        DsaProblem(
            id = "str_038",
            topic = "strings",
            title = "Check if the Sentence Is Pangram",
            difficulty = "Easy",
            pattern = "Bitmask / Set",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "A pangram is a sentence where every letter of the English alphabet appears at least once. Return true if sentence is pangram.",
            exampleInput = "sentence = \"thequickbrownfoxjumpsoverthelazydog\"",
            exampleOutput = "true",
            keyInsight = "Use a 32-bit integer bitmask. Mark (1 << (c - 'a')). Check if mask == (1 << 26) - 1.",
            solutionCode = """class Solution {
    // Check if the Sentence Is Pangram
    public static void solve() {}
    // Core logic:
    // int mask = 0; for (char c : sentence.toCharArray()) mask |= (1 << (c - 'a')); return mask == (1 << 26) - 1;
}"""
        ),
        DsaProblem(
            id = "str_039",
            topic = "strings",
            title = "Goal Parser Interpretation",
            difficulty = "Easy",
            pattern = "String Scanning",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Interpret Goal Parser commands: 'G' -> 'G', '()' -> 'o', '(al)' -> 'al'.",
            exampleInput = "command = \"G()(al)\"",
            exampleOutput = "\"Goal\"",
            keyInsight = "Check patterns while scanning: 'G' -> G, followed by '(' checks if next is ')' -> 'o' else 'al'.",
            solutionCode = """class Solution {
    // Goal Parser Interpretation
    public static void solve() {}
    // Core logic:
    // StringBuilder sb = new StringBuilder(); for (int i = 0; i < command.length(); i++) { if (command.charAt(i) == 'G') sb.append('G'); else if (command.charAt(i + 1) == ')') { sb.append('o'); i++; } else { sb.append("al"); i += 3; } } return sb.toString();
}"""
        ),
        DsaProblem(
            id = "str_040",
            topic = "strings",
            title = "Truncate Sentence",
            difficulty = "Easy",
            pattern = "Space Counting",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "A sentence is a list of words that are separated by a single space. Truncate s such that it contains only the first k words.",
            exampleInput = "s = \"Hello how are you Contestant\", k = 4",
            exampleOutput = "\"Hello how are you\"",
            keyInsight = "Count spaces until k spaces are found, then return substring up to that index.",
            solutionCode = """class Solution {
    // Truncate Sentence
    public static void solve() {}
    // Core logic:
    // int count = 0; for (int i = 0; i < s.length(); i++) { if (s.charAt(i) == ' ') count++; if (count == k) return s.substring(0, i); } return s;
}"""
        ),
        DsaProblem(
            id = "str_041",
            topic = "strings",
            title = "Check If Two String Arrays are Equivalent",
            difficulty = "Easy",
            pattern = "String Join or Pointers",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given two string arrays word1 and word2, return true if the two arrays represent the same string.",
            exampleInput = "word1 = [\"ab\", \"c\"], word2 = [\"a\", \"bc\"]",
            exampleOutput = "true",
            keyInsight = "Compare joined strings or traverse characters using word-and-character index pointers.",
            solutionCode = """class Solution {
    // Check If Two String Arrays are Equivalent
    public static void solve() {}
    // Core logic:
    // return String.join("", word1).equals(String.join("", word2));
}"""
        ),
        DsaProblem(
            id = "str_042",
            topic = "strings",
            title = "Sorting the Sentence",
            difficulty = "Easy",
            pattern = "Index Extraction & Array",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Reconstruct original sentence from shuffled words where each word has its 1-indexed position appended.",
            exampleInput = "s = \"is2 sentence4 This1 a3\"",
            exampleOutput = "\"This is a sentence\"",
            keyInsight = "Extract trailing digit for position index. Place word without digit into array of size 10.",
            solutionCode = """class Solution {
    // Sorting the Sentence
    public static void solve() {}
    // Core logic:
    // String[] words = s.split(" "); String[] res = new String[words.length]; for (String w : words) { int idx = w.charAt(w.length() - 1) - '1'; res[idx] = w.substring(0, w.length() - 1); } return String.join(" ", res);
}"""
        ),
        DsaProblem(
            id = "str_043",
            topic = "strings",
            title = "Determine if String Halves Are Alike",
            difficulty = "Easy",
            pattern = "Vowel Counting",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Two halves of a string of even length are alike if they have the same number of vowels. Return true if alike.",
            exampleInput = "s = \"book\"",
            exampleOutput = "true (bo vs ok)",
            keyInsight = "Count vowels in first half and subtract vowels in second half. Result must equal zero.",
            solutionCode = """class Solution {
    // Determine if String Halves Are Alike
    public static void solve() {}
    // Core logic:
    // String v = "aeiouAEIOU"; int diff = 0, n = s.length(); for (int i = 0; i < n / 2; i++) { if (v.indexOf(s.charAt(i)) != -1) diff++; if (v.indexOf(s.charAt(i + n / 2)) != -1) diff--; } return diff == 0;
}"""
        ),
        DsaProblem(
            id = "str_044",
            topic = "strings",
            title = "Maximum Number of Vowels in a Substring",
            difficulty = "Medium",
            pattern = "Fixed Sliding Window",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.",
            exampleInput = "s = \"abciiidef\", k = 3",
            exampleOutput = "3 (\"iii\")",
            keyInsight = "Maintain sliding window of size k, tracking vowel counts on entering and leaving characters.",
            solutionCode = """class Solution {
    // Maximum Number of Vowels in a Substring
    public static void solve() {}
    // Core logic:
    // String v = "aeiou"; int count = 0; for (int i = 0; i < k; i++) if (v.indexOf(s.charAt(i)) != -1) count++; int max = count; for (int i = k; i < s.length(); i++) { if (v.indexOf(s.charAt(i)) != -1) count++; if (v.indexOf(s.charAt(i - k)) != -1) count--; max = Math.max(max, count); } return max;
}"""
        ),
        DsaProblem(
            id = "str_045",
            topic = "strings",
            title = "Find All Anagrams in a String",
            difficulty = "Medium",
            pattern = "Sliding Window / Frequency Array",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given two strings s and p, return an array of all the start indices of p's anagrams in s.",
            exampleInput = "s = \"cbaebabacd\", p = \"abc\"",
            exampleOutput = "[0, 6]",
            keyInsight = "Compare frequency arrays countP and countS for sliding window of size p.length().",
            solutionCode = """class Solution {
    // Find All Anagrams in a String
    public static void solve() {}
    // Core logic:
    // List<Integer> res = new ArrayList<>(); if (s.length() < p.length()) return res; int[] cp = new int[26], cs = new int[26]; for (char c : p.toCharArray()) cp[c - 'a']++; for (int i = 0; i < s.length(); i++) { cs[s.charAt(i) - 'a']++; if (i >= p.length()) cs[s.charAt(i - p.length()) - 'a']--; if (Arrays.equals(cp, cs)) res.add(i - p.length() + 1); } return res;
}"""
        ),
        DsaProblem(
            id = "str_046",
            topic = "strings",
            title = "Permutation in String",
            difficulty = "Medium",
            pattern = "Sliding Window / Matching Frequency",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.",
            exampleInput = "s1 = \"ab\", s2 = \"eidbaooo\"",
            exampleOutput = "true",
            keyInsight = "Check if any window in s2 of length s1.length() matches s1 character frequencies.",
            solutionCode = """class Solution {
    // Permutation in String
    public static void solve() {}
    // Core logic:
    // if (s1.length() > s2.length()) return false; int[] c1 = new int[26], c2 = new int[26]; for (char c : s1.toCharArray()) c1[c - 'a']++; for (int i = 0; i < s2.length(); i++) { c2[s2.charAt(i) - 'a']++; if (i >= s1.length()) c2[s2.charAt(i - s1.length()) - 'a']--; if (Arrays.equals(c1, c2)) return true; } return false;
}"""
        ),
        DsaProblem(
            id = "str_047",
            topic = "strings",
            title = "Repeated Substring Pattern",
            difficulty = "Easy",
            pattern = "String Concatenation Trick",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given a string s, check if it can be constructed by taking a substring of it and appending multiple copies.",
            exampleInput = "s = \"abab\"",
            exampleOutput = "true (\"ab\" repeated twice)",
            keyInsight = "Concatenate s + s. Strip first and last character. If s is present in the trimmed string, pattern exists.",
            solutionCode = """class Solution {
    // Repeated Substring Pattern
    public static void solve() {}
    // Core logic:
    // String doubled = s + s; return doubled.substring(1, doubled.length() - 1).contains(s);
}"""
        ),
        DsaProblem(
            id = "str_048",
            topic = "strings",
            title = "License Key Formatting",
            difficulty = "Easy",
            pattern = "String Formatting Backwards",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Format license key string into groups of k uppercase characters separated by dashes, starting from the right.",
            exampleInput = "s = \"5F3Z-2e-9-w\", k = 4",
            exampleOutput = "\"5F3Z-2E9W\"",
            keyInsight = "Traverse backwards skipping dashes. Append uppercase char and add dash whenever count % k == 0.",
            solutionCode = """class Solution {
    // License Key Formatting
    public static void solve() {}
    // Core logic:
    // StringBuilder sb = new StringBuilder(); int count = 0; for (int i = s.length() - 1; i >= 0; i--) { char c = s.charAt(i); if (c != '-') { if (count > 0 && count % k == 0) sb.append('-'); sb.append(Character.toUpperCase(c)); count++; } } return sb.reverse().toString();
}"""
        ),
        DsaProblem(
            id = "str_049",
            topic = "strings",
            title = "Unique Morse Code Words",
            difficulty = "Easy",
            pattern = "Hash Set Transformation",
            timeComplexity = "O(Total Chars)",
            spaceComplexity = "O(N)",
            description = "Return the number of different transformations among all words we have using standard morse codes.",
            exampleInput = "words = [\"gin\",\"zen\",\"gig\",\"msg\"]",
            exampleOutput = "2",
            keyInsight = "Convert each word to its Morse string representation and insert into a HashSet.",
            solutionCode = """class Solution {
    // Unique Morse Code Words
    public static void solve() {}
    // Core logic:
    // String[] morse = {".-","-...", "-.-.","-..",".","..-.","--.","....","..",".---","-.- ",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."}; Set<String> seen = new HashSet<>(); for (String w : words) { StringBuilder sb = new StringBuilder(); for (char c : w.toCharArray()) sb.append(morse[c - 'a']); seen.add(sb.toString()); } return seen.size();
}"""
        ),
        DsaProblem(
            id = "str_050",
            topic = "strings",
            title = "Buddy Strings",
            difficulty = "Easy",
            pattern = "Pair Swap Verification",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given two strings s and goal, return true if you can swap two letters in s so the result is equal to goal.",
            exampleInput = "s = \"ab\", goal = \"ba\"",
            exampleOutput = "true",
            keyInsight = "If strings equal, requires at least one duplicate letter. If unequal, must differ at exactly 2 indices with swapped characters.",
            solutionCode = """class Solution {
    // Buddy Strings
    public static void solve() {}
    // Core logic:
    // if (s.length() != goal.length()) return false; if (s.equals(goal)) { Set<Character> set = new HashSet<>(); for (char c : s.toCharArray()) if (!set.add(c)) return true; return false; } List<Integer> diff = new ArrayList<>(); for (int i = 0; i < s.length(); i++) if (s.charAt(i) != goal.charAt(i)) diff.add(i); return diff.size() == 2 && s.charAt(diff.get(0)) == goal.charAt(diff.get(1)) && s.charAt(diff.get(1)) == goal.charAt(diff.get(0));
}"""
        ),
        DsaProblem(
            id = "str_051",
            topic = "strings",
            title = "Most Common Word",
            difficulty = "Easy",
            pattern = "Hash Map / Word Parsing",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given a string paragraph and a string array of the banned words, return the most frequent word that is not banned.",
            exampleInput = "paragraph = \"Bob hit a ball, the hit BALL flew far after it was hit.\", banned = [\"hit\"]",
            exampleOutput = "\"ball\"",
            keyInsight = "Normalize to lowercase letters. Split by non-alphanumeric characters. Count occurrences of unbanned words.",
            solutionCode = """class Solution {
    // Most Common Word
    public static void solve() {}
    // Core logic:
    // Set<String> ban = new HashSet<>(Arrays.asList(banned)); Map<String, Integer> count = new HashMap<>(); String[] words = paragraph.toLowerCase().replaceAll("[^a-zA-Z]", " ").split("\\s+"); String res = ""; int max = 0; for (String w : words) if (!w.isEmpty() && !ban.contains(w)) { int c = count.getOrDefault(w, 0) + 1; count.put(w, c); if (c > max) { max = c; res = w; } } return res;
}"""
        ),
        DsaProblem(
            id = "str_052",
            topic = "strings",
            title = "Robot Return to Origin",
            difficulty = "Easy",
            pattern = "Coordinate Simulation",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Determine if the robot returns to (0, 0) after performing moves (U, D, L, R).",
            exampleInput = "moves = \"UD\"",
            exampleOutput = "true",
            keyInsight = "Track horizontal x (R - L) and vertical y (U - D). Return x == 0 && y == 0.",
            solutionCode = """class Solution {
    // Robot Return to Origin
    public static void solve() {}
    // Core logic:
    // int x = 0, y = 0; for (char c : moves.toCharArray()) { if (c == 'U') y++; else if (c == 'D') y--; else if (c == 'R') x++; else if (c == 'L') x--; } return x == 0 && y == 0;
}"""
        ),
        DsaProblem(
            id = "str_053",
            topic = "strings",
            title = "String Compression",
            difficulty = "Medium",
            pattern = "Two Pointers / In-Place Counting",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Compress characters array in-place by writing character followed by count (if count > 1). Return new length.",
            exampleInput = "chars = [\"a\",\"a\",\"b\",\"b\",\"c\",\"c\",\"c\"]",
            exampleOutput = "6 ([\"a\",\"2\",\"b\",\"2\",\"c\",\"3\"])",
            keyInsight = "Scan consecutive duplicates with anchor index. Write character and digits of count to write pointer.",
            solutionCode = """class Solution {
    // String Compression
    public static void solve() {}
    // Core logic:
    // int write = 0, anchor = 0; for (int i = 0; i < chars.length; i++) { if (i + 1 == chars.length || chars[i + 1] != chars[i]) { chars[write++] = chars[anchor]; int count = i - anchor + 1; if (count > 1) for (char c : String.valueOf(count).toCharArray()) chars[write++] = c; anchor = i + 1; } } return write;
}"""
        ),
        DsaProblem(
            id = "str_054",
            topic = "strings",
            title = "Count Binary Substrings",
            difficulty = "Easy",
            pattern = "Consecutive Group Counts",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given a binary string s, return the number of non-empty substrings that have the same number of 0's and 1's consecutively.",
            exampleInput = "s = \"00110011\"",
            exampleOutput = "6 (\"0011\", \"01\", \"1100\", \"10\", \"0011\", \"01\")",
            keyInsight = "Count consecutive streaks of identical digits. Answer is sum of min(streak[i], streak[i-1]).",
            solutionCode = """class Solution {
    // Count Binary Substrings
    public static void solve() {}
    // Core logic:
    // int cur = 1, prev = 0, res = 0; for (int i = 1; i < s.length(); i++) { if (s.charAt(i) == s.charAt(i - 1)) cur++; else { res += Math.min(prev, cur); prev = cur; cur = 1; } } return res + Math.min(prev, cur);
}"""
        ),
        DsaProblem(
            id = "str_055",
            topic = "strings",
            title = "Custom Sort String",
            difficulty = "Medium",
            pattern = "Frequency Bucket Sorting",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Permute characters of s so that they match the custom character ordering given in order.",
            exampleInput = "order = \"cba\", s = \"abcd\"",
            exampleOutput = "\"cbad\"",
            keyInsight = "Count frequencies of s in int[26]. Append characters in order sequence, then append remaining characters.",
            solutionCode = """class Solution {
    // Custom Sort String
    public static void solve() {}
    // Core logic:
    // int[] count = new int[26]; for (char c : s.toCharArray()) count[c - 'a']++; StringBuilder sb = new StringBuilder(); for (char c : order.toCharArray()) while (count[c - 'a']-- > 0) sb.append(c); for (int i = 0; i < 26; i++) while (count[i]-- > 0) sb.append((char)('a' + i)); return sb.toString();
}"""
        ),
        DsaProblem(
            id = "str_056",
            topic = "strings",
            title = "Consecutive Characters",
            difficulty = "Easy",
            pattern = "Linear Scan",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "The power of the string is the maximum length of a non-empty substring that contains only one unique character.",
            exampleInput = "s = \"leetcode\"",
            exampleOutput = "2 (\"ee\")",
            keyInsight = "Count consecutive matching characters and track maximum count.",
            solutionCode = """class Solution {
    // Consecutive Characters
    public static void solve() {}
    // Core logic:
    // int max = 1, cur = 1; for (int i = 1; i < s.length(); i++) { if (s.charAt(i) == s.charAt(i - 1)) cur++; else cur = 1; max = Math.max(max, cur); } return max;
}"""
        ),
        DsaProblem(
            id = "str_057",
            topic = "strings",
            title = "Maximum Score After Splitting a String",
            difficulty = "Easy",
            pattern = "Prefix Zeroes / Suffix Ones",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given a string s of zeros and ones, return the maximum score after splitting into two non-empty substrings (zeros left + ones right).",
            exampleInput = "s = \"011101\"",
            exampleOutput = "5 (zeroes: \"0\", ones: \"11101\")",
            keyInsight = "Count total ones. On scan, increment left zeroes and decrement right ones. Track maximum sum.",
            solutionCode = """class Solution {
    // Maximum Score After Splitting a String
    public static void solve() {}
    // Core logic:
    // int totalOnes = 0; for (char c : s.toCharArray()) if (c == '1') totalOnes++; int zeros = 0, ones = totalOnes, max = 0; for (int i = 0; i < s.length() - 1; i++) { if (s.charAt(i) == '0') zeros++; else ones--; max = Math.max(max, zeros + ones); } return max;
}"""
        ),
        DsaProblem(
            id = "str_058",
            topic = "strings",
            title = "Split a String in Balanced Strings",
            difficulty = "Easy",
            pattern = "Greedy Counter",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Balanced strings have an equal quantity of 'L' and 'R' characters. Return the maximum number of balanced substrings.",
            exampleInput = "s = \"RLRRLLRLRL\"",
            exampleOutput = "4",
            keyInsight = "Maintain balance counter (+1 for R, -1 for L). Every time balance reaches 0, count a balanced string.",
            solutionCode = """class Solution {
    // Split a String in Balanced Strings
    public static void solve() {}
    // Core logic:
    // int balance = 0, count = 0; for (char c : s.toCharArray()) { balance += (c == 'R') ? 1 : -1; if (balance == 0) count++; } return count;
}"""
        ),
        DsaProblem(
            id = "str_059",
            topic = "strings",
            title = "Check If String Is a Prefix of Array",
            difficulty = "Easy",
            pattern = "Prefix Accumulation",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given a string s and an array of strings words, determine whether s is a prefix string of words.",
            exampleInput = "s = \"iloveleetcode\", words = [\"i\", \"love\", \"leetcode\", \"apples\"]",
            exampleOutput = "true",
            keyInsight = "Append words one by one. If accumulated string equals s, return true. If length exceeds s, return false.",
            solutionCode = """class Solution {
    // Check If String Is a Prefix of Array
    public static void solve() {}
    // Core logic:
    // StringBuilder sb = new StringBuilder(); for (String w : words) { sb.append(w); if (sb.length() == s.length()) return sb.toString().equals(s); if (sb.length() > s.length()) return false; } return false;
}"""
        ),
        DsaProblem(
            id = "str_060",
            topic = "strings",
            title = "First Letter to Appear Twice",
            difficulty = "Easy",
            pattern = "Boolean Array / Set",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given a string s consisting of lowercase English letters, return the first letter to appear twice.",
            exampleInput = "s = \"abccbaacz\"",
            exampleOutput = "'c'",
            keyInsight = "Use a boolean array of size 26. The first letter whose slot is already true is the answer.",
            solutionCode = """class Solution {
    // First Letter to Appear Twice
    public static void solve() {}
    // Core logic:
    // boolean[] seen = new boolean[26]; for (char c : s.toCharArray()) { if (seen[c - 'a']) return c; seen[c - 'a'] = true; } return ' ';
}"""
        ),
        DsaProblem(
            id = "str_061",
            topic = "strings",
            title = "Check if Binary String Has at Most One Segment of Ones",
            difficulty = "Easy",
            pattern = "String Pattern Search",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given a binary string s without leading zeros, return true if s contains at most one contiguous segment of ones.",
            exampleInput = "s = \"1001\"",
            exampleOutput = "false",
            keyInsight = "If the pattern \"01\" exists, it means a segment of ones started, ended with 0, and another 1 began.",
            solutionCode = """class Solution {
    // Check if Binary String Has at Most One Segment of Ones
    public static void solve() {}
    // Core logic:
    // return !s.contains("01");
}"""
        ),
        DsaProblem(
            id = "str_062",
            topic = "strings",
            title = "Largest 3-Same-Digit Number in String",
            difficulty = "Easy",
            pattern = "String Matching / Sliding Window",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Find the maximum integer in string that consists of three consecutive identical digits.",
            exampleInput = "num = \"6777133339\"",
            exampleOutput = "\"777\"",
            keyInsight = "Check for each digit 9 down to 0 whether three consecutive identical digits (e.g. \"999\") are contained in num.",
            solutionCode = """class Solution {
    // Largest 3-Same-Digit Number in String
    public static void solve() {}
    // Core logic:
    // for (char c = '9'; c >= '0'; c--) { String s = "" + c + c + c; if (num.contains(s)) return s; } return "";
}"""
        ),
        DsaProblem(
            id = "str_063",
            topic = "strings",
            title = "Percentage of Letter in String",
            difficulty = "Easy",
            pattern = "Counting",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given a string s and a character letter, return the percentage of characters in s that equal letter rounded down.",
            exampleInput = "s = \"foobar\", letter = 'o'",
            exampleOutput = "33",
            keyInsight = "Count occurrences of letter, then compute (count * 100) / s.length().",
            solutionCode = """class Solution {
    // Percentage of Letter in String
    public static void solve() {}
    // Core logic:
    // int count = 0; for (char c : s.toCharArray()) if (c == letter) count++; return (count * 100) / s.length();
}"""
        ),
        DsaProblem(
            id = "str_064",
            topic = "strings",
            title = "Check If Word Equals Summation of Two Words",
            difficulty = "Easy",
            pattern = "Letter to Digit Conversion",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "The letter value of 'a' is 0, 'b' is 1... Check if firstWord + secondWord numerical values equal targetWord value.",
            exampleInput = "firstWord = \"acb\", secondWord = \"cba\", targetWord = \"cdb\"",
            exampleOutput = "true (21 + 210 = 231)",
            keyInsight = "Helper function converts characters to numbers: val = val * 10 + (c - 'a'). Check val1 + val2 == val3.",
            solutionCode = """class Solution {
    // Check If Word Equals Summation of Two Words
    public static void solve() {}
    // Core logic:
    // return getVal(firstWord) + getVal(secondWord) == getVal(targetWord);
}"""
        ),
        DsaProblem(
            id = "str_065",
            topic = "strings",
            title = "Minimum Remove to Make Valid Parentheses",
            difficulty = "Medium",
            pattern = "Stack / Marking Invalid Indices",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given a string s of '(' , ')' and lowercase English characters, remove minimum parentheses to make string valid.",
            exampleInput = "s = \"lee(t(c)o)de)\"",
            exampleOutput = "\"lee(t(c)o)de\"",
            keyInsight = "Use stack to store indices of unmatched '('. Mark unmatched ')' and remaining '(' indices for removal.",
            solutionCode = """class Solution {
    // Minimum Remove to Make Valid Parentheses
    public static void solve() {}
    // Core logic:
    // Set<Integer> remove = new HashSet<>(); Deque<Integer> stack = new ArrayDeque<>(); for (int i = 0; i < s.length(); i++) { if (s.charAt(i) == '(') stack.push(i); else if (s.charAt(i) == ')') { if (stack.isEmpty()) remove.add(i); else stack.pop(); } } while (!stack.isEmpty()) remove.add(stack.pop()); StringBuilder sb = new StringBuilder(); for (int i = 0; i < s.length(); i++) if (!remove.contains(i)) sb.append(s.charAt(i)); return sb.toString();
}"""
        ),
        DsaProblem(
            id = "str_066",
            topic = "strings",
            title = "Palindromic Substrings",
            difficulty = "Medium",
            pattern = "Expand Around Center",
            timeComplexity = "O(N^2)",
            spaceComplexity = "O(1)",
            description = "Given a string s, return the number of palindromic substrings in it.",
            exampleInput = "s = \"aaa\"",
            exampleOutput = "6 (\"a\", \"a\", \"a\", \"aa\", \"aa\", \"aaa\")",
            keyInsight = "Expand around every possible center (both 1-character and 2-character centers). Increment count for each valid expansion.",
            solutionCode = """class Solution {
    // Palindromic Substrings
    public static void solve() {}
    // Core logic:
    // int count = 0; for (int i = 0; i < s.length(); i++) { count += countPal(s, i, i) + countPal(s, i, i + 1); } return count;
}"""
        ),
        DsaProblem(
            id = "str_067",
            topic = "strings",
            title = "Check If String Is Decomposable",
            difficulty = "Easy",
            pattern = "Run Length / Grouping",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "A string is decomposable if it can be partitioned into blocks of 3 identical characters and exactly one block of 2.",
            exampleInput = "s = \"000111000\"",
            exampleOutput = "false (needs exactly one block of 2)",
            keyInsight = "Count consecutive streaks. Each streak length must be 3k or 3k+2, with exactly one 3k+2.",
            solutionCode = """class Solution {
    // Check If String Is Decomposable
    public static void solve() {}
    // Core logic:
    // int i = 0, twos = 0; while (i < s.length()) { int j = i; while (j < s.length() && s.charAt(j) == s.charAt(i)) j++; int len = j - i; if (len % 3 == 2) twos++; else if (len % 3 != 0) return false; i = j; } return twos == 1;
}"""
        ),
        DsaProblem(
            id = "str_068",
            topic = "strings",
            title = "Decrypt String from Alphabet to Integer Mapping",
            difficulty = "Easy",
            pattern = "Scan with Lookahead",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Characters 'a' to 'i' mapped as '1' to '9'. 'j' to 'z' mapped as '10#' to '26#'. Decrypt s.",
            exampleInput = "s = \"10#11#12\"",
            exampleOutput = "\"jkab\"",
            keyInsight = "Look ahead 2 characters: if s[i+2] == '#', parse 2 digits and advance by 3; otherwise parse 1 digit.",
            solutionCode = """class Solution {
    // Decrypt String from Alphabet to Integer Mapping
    public static void solve() {}
    // Core logic:
    // StringBuilder sb = new StringBuilder(); int i = 0, n = s.length(); while (i < n) { if (i + 2 < n && s.charAt(i + 2) == '#') { int val = Integer.parseInt(s.substring(i, i + 2)); sb.append((char)('a' + val - 1)); i += 3; } else { sb.append((char)('a' + (s.charAt(i) - '0') - 1)); i++; } } return sb.toString();
}"""
        ),
        DsaProblem(
            id = "str_069",
            topic = "strings",
            title = "Greatest Common Divisor of Strings",
            difficulty = "Easy",
            pattern = "Euclidean GCD on Lengths",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.",
            exampleInput = "str1 = \"ABCABC\", str2 = \"ABC\"",
            exampleOutput = "\"ABC\"",
            keyInsight = "Check if str1 + str2 equals str2 + str1. If yes, answer is substring from 0 to gcd(str1.length, str2.length).",
            solutionCode = """class Solution {
    // Greatest Common Divisor of Strings
    public static void solve() {}
    // Core logic:
    // if (!(str1 + str2).equals(str2 + str1)) return ""; int g = gcd(str1.length(), str2.length()); return str1.substring(0, g);
}"""
        ),
        DsaProblem(
            id = "str_070",
            topic = "strings",
            title = "Shortest Distance to a Character",
            difficulty = "Easy",
            pattern = "Two Pass Min Distance",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given a string s and a character c, return an array of distances from each index to closest occurrence of c.",
            exampleInput = "s = \"loveleetcode\", c = 'e'",
            exampleOutput = "[3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]",
            keyInsight = "Pass left-to-right tracking last seen index of c, then pass right-to-left taking minimum distance.",
            solutionCode = """class Solution {
    // Shortest Distance to a Character
    public static void solve() {}
    // Core logic:
    // int n = s.length(), pos = -n; int[] res = new int[n]; for (int i = 0; i < n; i++) { if (s.charAt(i) == c) pos = i; res[i] = i - pos; } for (int i = pos - 1; i >= 0; i--) { if (s.charAt(i) == c) pos = i; res[i] = Math.min(res[i], pos - i); } return res;
}"""
        ),
        DsaProblem(
            id = "str_071",
            topic = "strings",
            title = "Reorganize String",
            difficulty = "Medium",
            pattern = "Max Heap / Most Frequent First",
            timeComplexity = "O(N log A)",
            spaceComplexity = "O(1)",
            description = "Rearrange characters of s so that any two adjacent characters are not the same. Return \"\" if not possible.",
            exampleInput = "s = \"aab\"",
            exampleOutput = "\"aba\"",
            keyInsight = "If max frequency > (n + 1) / 2, impossible. Otherwise place most frequent character in even positions, then others.",
            solutionCode = """class Solution {
    // Reorganize String
    public static void solve() {}
    // Core logic:
    // int[] count = new int[26]; for (char c : s.toCharArray()) count[c - 'a']++; int max = 0, letter = 0; for (int i = 0; i < 26; i++) if (count[i] > max) { max = count[i]; letter = i; } if (max > (s.length() + 1) / 2) return ""; char[] res = new char[s.length()]; int idx = 0; while (count[letter]-- > 0) { res[idx] = (char)('a' + letter); idx += 2; } for (int i = 0; i < 26; i++) while (count[i]-- > 0) { if (idx >= res.length) idx = 1; res[idx] = (char)('a' + i); idx += 2; } return new String(res);
}"""
        ),
        DsaProblem(
            id = "str_072",
            topic = "strings",
            title = "Check if Numbers Are Ascending in a Sentence",
            difficulty = "Easy",
            pattern = "Token Parsing",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given a sentence s containing numbers and words, check if all numbers appearing in s are strictly increasing from left to right.",
            exampleInput = "s = \"1 box has 3 blue 4 red 6 green and 12 yellow\"",
            exampleOutput = "true",
            keyInsight = "Parse tokens. For each integer token, check if strictly greater than previous number.",
            solutionCode = """class Solution {
    // Check if Numbers Are Ascending in a Sentence
    public static void solve() {}
    // Core logic:
    // int prev = -1; for (String t : s.split(" ")) if (Character.isDigit(t.charAt(0))) { int val = Integer.parseInt(t); if (val <= prev) return false; prev = val; } return true;
}"""
        ),
        DsaProblem(
            id = "str_073",
            topic = "strings",
            title = "Count Items Matching a Rule",
            difficulty = "Easy",
            pattern = "Linear Rule Filtering",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given a list of items where items[i] = [type, color, name], count how many items match ruleKey and ruleValue.",
            exampleInput = "items = [[\"phone\",\"blue\",\"pixel\"]], ruleKey = \"color\", ruleValue = \"blue\"",
            exampleOutput = "1",
            keyInsight = "Determine index corresponding to ruleKey (0 for type, 1 for color, 2 for name). Count matching items.",
            solutionCode = """class Solution {
    // Count Items Matching a Rule
    public static void solve() {}
    // Core logic:
    // int idx = ruleKey.equals("type") ? 0 : ruleKey.equals("color") ? 1 : 2; int count = 0; for (List<String> item : items) if (item.get(idx).equals(ruleValue)) count++; return count;
}"""
        ),
        DsaProblem(
            id = "str_074",
            topic = "strings",
            title = "Thousand Separator",
            difficulty = "Easy",
            pattern = "String Formatting Backwards",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given an integer n, add a dot ('.') as the thousands separator and return it in string format.",
            exampleInput = "n = 1234",
            exampleOutput = "\"1.234\"",
            keyInsight = "Traverse digits from right to left, adding dot every 3 digits (except at the very beginning).",
            solutionCode = """class Solution {
    // Thousand Separator
    public static void solve() {}
    // Core logic:
    // String s = String.valueOf(n); StringBuilder sb = new StringBuilder(); int count = 0; for (int i = s.length() - 1; i >= 0; i--) { sb.append(s.charAt(i)); if (++count % 3 == 0 && i > 0) sb.append('.'); } return sb.reverse().toString();
}"""
        ),
        DsaProblem(
            id = "str_075",
            topic = "strings",
            title = "Make The String Great",
            difficulty = "Easy",
            pattern = "Stack / In-Place StringBuilder",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "A string is good if no two adjacent characters satisfy s[i] is lowercase and s[i+1] is uppercase of same letter.",
            exampleInput = "s = \"leEeetcode\"",
            exampleOutput = "\"leetcode\"",
            keyInsight = "Use StringBuilder as stack. If abs(sb.lastChar - nextChar) == 32, delete last char; else append.",
            solutionCode = """class Solution {
    // Make The String Great
    public static void solve() {}
    // Core logic:
    // StringBuilder sb = new StringBuilder(); for (char c : s.toCharArray()) { int len = sb.length(); if (len > 0 && Math.abs(sb.charAt(len - 1) - c) == 32) sb.deleteCharAt(len - 1); else sb.append(c); } return sb.toString();
}"""
        ),
        DsaProblem(
            id = "str_076",
            topic = "strings",
            title = "Path Crossing",
            difficulty = "Easy",
            pattern = "Visited Coordinates Set",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given a string path where path[i] = 'N', 'S', 'E' or 'W', return true if the path crosses itself at any point.",
            exampleInput = "path = \"NES\"",
            exampleOutput = "false",
            keyInsight = "Track (x, y) coordinates. Add each point as string \"x,y\" to a HashSet. Return true if point was visited.",
            solutionCode = """class Solution {
    // Path Crossing
    public static void solve() {}
    // Core logic:
    // Set<String> visited = new HashSet<>(); visited.add("0,0"); int x = 0, y = 0; for (char c : path.toCharArray()) { if (c == 'N') y++; else if (c == 'S') y--; else if (c == 'E') x++; else if (c == 'W') x--; if (!visited.add(x + "," + y)) return true; } return false;
}"""
        ),
        DsaProblem(
            id = "str_077",
            topic = "strings",
            title = "Longest Substring with At Least K Repeating Characters",
            difficulty = "Medium",
            pattern = "Divide and Conquer",
            timeComplexity = "O(N log N)",
            spaceComplexity = "O(N)",
            description = "Find the length of the longest substring of T in which the frequency of each character is at least k.",
            exampleInput = "s = \"aaabb\", k = 3",
            exampleOutput = "3 (\"aaa\")",
            keyInsight = "Count frequencies. If any char has count < k, it can never be part of answer; split by that character.",
            solutionCode = """class Solution {
    // Longest Substring with At Least K Repeating Characters
    public static void solve() {}
    // Core logic:
    // int[] count = new int[26]; for (char c : s.toCharArray()) count[c - 'a']++; for (int i = 0; i < s.length(); i++) { if (count[s.charAt(i) - 'a'] < k) { int max = 0; for (String sub : s.split("" + s.charAt(i))) max = Math.max(max, longestSubstring(sub, k)); return max; } } return s.length();
}"""
        ),
        DsaProblem(
            id = "str_078",
            topic = "strings",
            title = "Decode String",
            difficulty = "Medium",
            pattern = "Two Stacks (Count & String)",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given an encoded string, return its decoded string. Pattern is k[encoded_string].",
            exampleInput = "s = \"3[a]2[bc]\"",
            exampleOutput = "\"aaabcbc\"",
            keyInsight = "Use countStack and stringStack. On '[', push current count and string. On ']', pop and repeat string.",
            solutionCode = """class Solution {
    // Decode String
    public static void solve() {}
    // Core logic:
    // Deque<Integer> countStack = new ArrayDeque<>(); Deque<StringBuilder> strStack = new ArrayDeque<>(); StringBuilder cur = new StringBuilder(); int k = 0; for (char c : s.toCharArray()) { if (Character.isDigit(c)) k = k * 10 + (c - '0'); else if (c == '[') { countStack.push(k); strStack.push(cur); cur = new StringBuilder(); k = 0; } else if (c == ']') { StringBuilder prev = strStack.pop(); int repeat = countStack.pop(); while (repeat-- > 0) prev.append(cur); cur = prev; } else cur.append(c); } return cur.toString();
}"""
        ),
        DsaProblem(
            id = "str_079",
            topic = "strings",
            title = "Check If String Is Valid Parenthesis String with Wildcard",
            difficulty = "Medium",
            pattern = "Greedy Range / Low-High Counters",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given a string s containing '(', ')' and '*', where '*' can be '(', ')' or empty string, return true if valid.",
            exampleInput = "s = \"(*))\"",
            exampleOutput = "true",
            keyInsight = "Track range of possible open brackets [low, high]. Low decreases on '*' or ')', high increases on '*' or '('.",
            solutionCode = """class Solution {
    // Check If String Is Valid Parenthesis String with Wildcard
    public static void solve() {}
    // Core logic:
    // int low = 0, high = 0; for (char c : s.toCharArray()) { low += (c == '(') ? 1 : -1; high += (c != ')') ? 1 : -1; if (high < 0) return false; low = Math.max(low, 0); } return low == 0;
}"""
        ),
        DsaProblem(
            id = "str_080",
            topic = "strings",
            title = "Simplify Path",
            difficulty = "Medium",
            pattern = "Stack Directory Traversal",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given an absolute path for a Unix-style file system, convert it to the simplified canonical path.",
            exampleInput = "path = \"/home//foo/\"",
            exampleOutput = "\"/home/foo\"",
            keyInsight = "Split path by '/'. Ignore empty and '.'. On '..', pop directory from stack if non-empty. Join remaining with '/'.",
            solutionCode = """class Solution {
    // Simplify Path
    public static void solve() {}
    // Core logic:
    // Deque<String> stack = new ArrayDeque<>(); for (String token : path.split("/")) { if (token.equals("..")) { if (!stack.isEmpty()) stack.pop(); } else if (!token.isEmpty() && !token.equals(".")) stack.push(token); } StringBuilder sb = new StringBuilder(); for (Iterator<String> it = stack.descendingIterator(); it.hasNext(); ) sb.append("/").append(it.next()); return sb.length() == 0 ? "/" : sb.toString();
}"""
        ),
        DsaProblem(
            id = "str_081",
            topic = "strings",
            title = "Zigzag Conversion",
            difficulty = "Medium",
            pattern = "Row Simulation",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Write the code that will take a string and make this conversion given a number of rows: 'PAYPALISHIRING' in 3 rows.",
            exampleInput = "s = \"PAYPALISHIRING\", numRows = 3",
            exampleOutput = "\"PAHNAPLSIIGYIR\"",
            keyInsight = "Simulate bouncing between row 0 and row numRows-1 using a step direction variable (+1 or -1).",
            solutionCode = """class Solution {
    // Zigzag Conversion
    public static void solve() {}
    // Core logic:
    // if (numRows == 1 || s.length() <= numRows) return s; StringBuilder[] rows = new StringBuilder[numRows]; for (int i = 0; i < numRows; i++) rows[i] = new StringBuilder(); int cur = 0; boolean down = false; for (char c : s.toCharArray()) { rows[cur].append(c); if (cur == 0 || cur == numRows - 1) down = !down; cur += down ? 1 : -1; } StringBuilder res = new StringBuilder(); for (StringBuilder row : rows) res.append(row); return res.toString();
}"""
        ),
        DsaProblem(
            id = "str_082",
            topic = "strings",
            title = "Compare Version Numbers",
            difficulty = "Medium",
            pattern = "Two Pointers Revision Token Parsing",
            timeComplexity = "O(max(N,M))",
            spaceComplexity = "O(1)",
            description = "Compare two version strings version1 and version2. Revisions are separated by dots. Ignore leading zeros.",
            exampleInput = "version1 = \"1.2\", version2 = \"1.10\"",
            exampleOutput = "-1",
            keyInsight = "Parse integers chunk by chunk between dots. Missing chunks are treated as 0.",
            solutionCode = """class Solution {
    // Compare Version Numbers
    public static void solve() {}
    // Core logic:
    // int i = 0, j = 0, n1 = v1.length(), n2 = v2.length(); while (i < n1 || j < n2) { int num1 = 0, num2 = 0; while (i < n1 && v1.charAt(i) != '.') num1 = num1 * 10 + (v1.charAt(i++) - '0'); while (j < n2 && v2.charAt(j) != '.') num2 = num2 * 10 + (v2.charAt(j++) - '0'); if (num1 != num2) return num1 < num2 ? -1 : 1; i++; j++; } return 0;
}"""
        ),
        DsaProblem(
            id = "str_083",
            topic = "strings",
            title = "Minimum Length of String After Deleting Similar Ends",
            difficulty = "Medium",
            pattern = "Two Pointers Matching Ends",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Delete a non-empty prefix and suffix made of same character repeatedly until not possible.",
            exampleInput = "s = \"ca\", return 2; s = \"caba\", return 1;",
            exampleOutput = "1",
            keyInsight = "While l < r and s[l] == s[r], advance l past identical characters and shrink r past identical characters.",
            solutionCode = """class Solution {
    // Minimum Length of String After Deleting Similar Ends
    public static void solve() {}
    // Core logic:
    // int l = 0, r = s.length() - 1; while (l < r && s.charAt(l) == s.charAt(r)) { char c = s.charAt(l); while (l <= r && s.charAt(l) == c) l++; while (l <= r && s.charAt(r) == c) r--; } return Math.max(0, r - l + 1);
}"""
        ),
        DsaProblem(
            id = "str_084",
            topic = "strings",
            title = "Defuse the Bomb (String Circular Context)",
            difficulty = "Easy",
            pattern = "Circular Sliding Window",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given a circular array code and key k, replace each element with sum of next k (or previous -k) elements.",
            exampleInput = "code = [5,7,1,4], k = 3",
            exampleOutput = "[12, 10, 16, 13]",
            keyInsight = "Maintain fixed circular sliding window using modulo indexing (i + step) % n.",
            solutionCode = """class Solution {
    // Defuse the Bomb (String Circular Context)
    public static void solve() {}
    // Core logic:
    // int n = code.length; int[] res = new int[n]; if (k == 0) return res; int start = (k > 0) ? 1 : n + k, end = (k > 0) ? k : n - 1, sum = 0; for (int i = start; i <= end; i++) sum += code[i % n]; for (int i = 0; i < n; i++) { res[i] = sum; sum -= code[start++ % n]; sum += code[++end % n]; } return res;
}"""
        ),
        DsaProblem(
            id = "str_085",
            topic = "strings",
            title = "Number of Segments in a String",
            difficulty = "Easy",
            pattern = "Scan Transitions",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given a string s, return the number of segments in the string (contiguous sequence of non-space characters).",
            exampleInput = "s = \"Hello, my name is John\"",
            exampleOutput = "5",
            keyInsight = "Count instances where current character != ' ' and (i == 0 or previous character was ' ').",
            solutionCode = """class Solution {
    // Number of Segments in a String
    public static void solve() {}
    // Core logic:
    // int count = 0; for (int i = 0; i < s.length(); i++) if (s.charAt(i) != ' ' && (i == 0 || s.charAt(i - 1) == ' ')) count++; return count;
}"""
        ),
        DsaProblem(
            id = "str_086",
            topic = "strings",
            title = "Rotated Digits",
            difficulty = "Medium",
            pattern = "Valid Rotation Digits Check",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "An integer x is good if after rotating each digit independently, x becomes a valid number different from x (2,5,6,9 change; 0,1,8 remain same).",
            exampleInput = "n = 10",
            exampleOutput = "4 (2, 5, 6, 9)",
            keyInsight = "Check digits: must contain at least one of {2, 5, 6, 9} and none of {3, 4, 7}.",
            solutionCode = """class Solution {
    // Rotated Digits
    public static void solve() {}
    // Core logic:
    // int count = 0; for (int i = 1; i <= n; i++) if (isGood(i)) count++; return count;
}"""
        ),
        DsaProblem(
            id = "str_087",
            topic = "strings",
            title = "Goat Latin",
            difficulty = "Easy",
            pattern = "String Rules Translation",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Translate sentence to Goat Latin: vowel-start appends 'ma', consonant-start moves first letter to end + 'ma', then 'a' * index.",
            exampleInput = "sentence = \"I speak Goat Latin\"",
            exampleOutput = "\"Imaa speaksmaaa oatGmaaaa atinLmaaaaa\"",
            keyInsight = "Process word by word. Append 'a' based on 1-based word index.",
            solutionCode = """class Solution {
    // Goat Latin
    public static void solve() {}
    // Core logic:
    // String[] words = sentence.split(" "); String vowels = "aeiouAEIOU"; StringBuilder res = new StringBuilder(); for (int i = 0; i < words.length; i++) { String w = words[i]; if (vowels.indexOf(w.charAt(0)) != -1) res.append(w); else res.append(w.substring(1)).append(w.charAt(0)); res.append("ma").append("a".repeat(i + 1)); if (i < words.length - 1) res.append(" "); } return res.toString();
}"""
        ),
        DsaProblem(
            id = "str_088",
            topic = "strings",
            title = "Shifting Letters",
            difficulty = "Medium",
            pattern = "Suffix Sum Shifts",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Shift characters of s where shifts[i] means shifting first i+1 characters by shifts[i] steps forward.",
            exampleInput = "s = \"abc\", shifts = [3, 5, 9]",
            exampleOutput = "\"rpl\"",
            keyInsight = "Accumulate suffix sum of shifts backwards modulo 26, then shift each character.",
            solutionCode = """class Solution {
    // Shifting Letters
    public static void solve() {}
    // Core logic:
    // char[] arr = s.toCharArray(); long shift = 0; for (int i = shifts.length - 1; i >= 0; i--) { shift = (shift + shifts[i]) % 26; arr[i] = (char)('a' + (arr[i] - 'a' + shift) % 26); } return new String(arr);
}"""
        ),
        DsaProblem(
            id = "str_089",
            topic = "strings",
            title = "Find and Replace Pattern",
            difficulty = "Medium",
            pattern = "Bijective Word Normalization",
            timeComplexity = "O(N * K)",
            spaceComplexity = "O(K)",
            description = "Return a list of words that match pattern where each letter in pattern maps to a unique letter in word.",
            exampleInput = "words = [\"abc\",\"deq\",\"mee\",\"aqq\",\"dkd\",\"ccc\"], pattern = \"abb\"",
            exampleOutput = "[\"mee\",\"aqq\"]",
            keyInsight = "Normalize string to canonical format using first occurrence index: \"abb\" -> \"0 1 1\".",
            solutionCode = """class Solution {
    // Find and Replace Pattern
    public static void solve() {}
    // Core logic:
    // List<String> res = new ArrayList<>(); String pat = normalize(pattern); for (String w : words) if (normalize(w).equals(pat)) res.add(w); return res;
}"""
        ),
        DsaProblem(
            id = "str_090",
            topic = "strings",
            title = "Remove Palindromic Subsequences",
            difficulty = "Easy",
            pattern = "Observation / Whole Palindrome Check",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "You can remove one palindromic subsequence at a time. Return minimum steps to make s empty (s contains only 'a' and 'b').",
            exampleInput = "s = \"ababa\"",
            exampleOutput = "1",
            keyInsight = "If s is already a palindrome, 1 step (remove whole string). Otherwise, exactly 2 steps (remove all 'a's, then all 'b's).",
            solutionCode = """class Solution {
    // Remove Palindromic Subsequences
    public static void solve() {}
    // Core logic:
    // if (s.isEmpty()) return 0; int l = 0, r = s.length() - 1; while (l < r) if (s.charAt(l++) != s.charAt(r--)) return 2; return 1;
}"""
        ),
        DsaProblem(
            id = "str_091",
            topic = "strings",
            title = "Occurrences After Bigram",
            difficulty = "Easy",
            pattern = "Token Sequence Matching",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Given words first and second, find all words third that immediately follow an occurrence of 'first second'.",
            exampleInput = "text = \"alice is a good girl she is a good student\", first = \"a\", second = \"good\"",
            exampleOutput = "[\"girl\",\"student\"]",
            keyInsight = "Split text into words array. Check if words[i] == first && words[i+1] == second.",
            solutionCode = """class Solution {
    // Occurrences After Bigram
    public static void solve() {}
    // Core logic:
    // String[] words = text.split(" "); List<String> res = new ArrayList<>(); for (int i = 0; i < words.length - 2; i++) if (words[i].equals(first) && words[i + 1].equals(second)) res.add(words[i + 2]); return res.toArray(new String[0]);
}"""
        ),
        DsaProblem(
            id = "str_092",
            topic = "strings",
            title = "Count the Number of Consistent Strings",
            difficulty = "Easy",
            pattern = "Allowed Characters Bitmask",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "A string is consistent if all characters in the string appear in the string allowed. Return count of consistent strings.",
            exampleInput = "allowed = \"ab\", words = [\"ad\",\"bd\",\"aaab\",\"baa\",\"badab\"]",
            exampleOutput = "2",
            keyInsight = "Create boolean array or bitmask of allowed characters. Validate each word.",
            solutionCode = """class Solution {
    // Count the Number of Consistent Strings
    public static void solve() {}
    // Core logic:
    // int count = 0; boolean[] allow = new boolean[26]; for (char c : allowed.toCharArray()) allow[c - 'a'] = true; for (String w : words) { boolean ok = true; for (char c : w.toCharArray()) if (!allow[c - 'a']) { ok = false; break; } if (ok) count++; } return count;
}"""
        ),
        DsaProblem(
            id = "str_093",
            topic = "strings",
            title = "Minimum Number of Steps to Make Two Strings Anagram",
            difficulty = "Medium",
            pattern = "Frequency Difference",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Return minimum number of steps to make t an anagram of s by replacing characters.",
            exampleInput = "s = \"bab\", t = \"aba\"",
            exampleOutput = "1",
            keyInsight = "Count frequencies of s in int[26]. Decrement for t. Sum all positive differences.",
            solutionCode = """class Solution {
    // Minimum Number of Steps to Make Two Strings Anagram
    public static void solve() {}
    // Core logic:
    // int[] count = new int[26]; for (int i = 0; i < s.length(); i++) { count[s.charAt(i) - 'a']++; count[t.charAt(i) - 'a']--; } int steps = 0; for (int c : count) if (c > 0) steps += c; return steps;
}"""
        ),
        DsaProblem(
            id = "str_094",
            topic = "strings",
            title = "Longest Happy String",
            difficulty = "Medium",
            pattern = "Greedy / Max Heap of Characters",
            timeComplexity = "O(A+B+C)",
            spaceComplexity = "O(1)",
            description = "A string s is called happy if it does not contain 'aaa', 'bbb' or 'ccc'. Build longest possible happy string.",
            exampleInput = "a = 1, b = 1, c = 7",
            exampleOutput = "\"ccaccbcc\"",
            keyInsight = "Greedily append the character with highest remaining count, unless the last two characters were already that character.",
            solutionCode = """class Solution {
    // Longest Happy String
    public static void solve() {}
    // Core logic:
    // StringBuilder sb = new StringBuilder(); int[][] counts = {{a, 'a'}, {b, 'b'}, {c, 'c'}}; while (true) { Arrays.sort(counts, (x, y) -> y[0] - x[0]); boolean added = false; for (int i = 0; i < 3; i++) { if (counts[i][0] == 0) break; int len = sb.length(); if (len >= 2 && sb.charAt(len - 1) == counts[i][1] && sb.charAt(len - 2) == counts[i][1]) continue; sb.append((char)counts[i][1]); counts[i][0]--; added = true; break; } if (!added) break; } return sb.toString();
}"""
        ),
        DsaProblem(
            id = "str_095",
            topic = "strings",
            title = "Remove Duplicate Letters",
            difficulty = "Medium",
            pattern = "Monotonic Stack / Greedy",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given a string s, remove duplicate letters so that every letter appears once and only once in smallest lexicographical order.",
            exampleInput = "s = \"bcabc\"",
            exampleOutput = "\"abc\"",
            keyInsight = "Track last seen index of each char and boolean visited array. Pop from stack if top > current and top appears later.",
            solutionCode = """class Solution {
    // Remove Duplicate Letters
    public static void solve() {}
    // Core logic:
    // int[] last = new int[26]; for (int i = 0; i < s.length(); i++) last[s.charAt(i) - 'a'] = i; boolean[] seen = new boolean[26]; Deque<Character> stack = new ArrayDeque<>(); for (int i = 0; i < s.length(); i++) { char c = s.charAt(i); if (seen[c - 'a']) continue; while (!stack.isEmpty() && stack.peek() > c && last[stack.peek() - 'a'] > i) seen[stack.pop() - 'a'] = false; stack.push(c); seen[c - 'a'] = true; } StringBuilder sb = new StringBuilder(); while (!stack.isEmpty()) sb.append(stack.pollLast()); return sb.toString();
}"""
        ),
        DsaProblem(
            id = "str_096",
            topic = "strings",
            title = "Maximum Value of a String in an Array",
            difficulty = "Easy",
            pattern = "Numeric vs Alphanumeric Check",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Value of string is its integer value if all numeric, or its length if it contains at least one letter. Find maximum value.",
            exampleInput = "strs = [\"alic3\",\"bob\",\"3\",\"4\",\"00000\"]",
            exampleOutput = "5",
            keyInsight = "Check if string contains only digits. If yes, parse as int; otherwise take length.",
            solutionCode = """class Solution {
    // Maximum Value of a String in an Array
    public static void solve() {}
    // Core logic:
    // int max = 0; for (String s : strs) { boolean digits = true; for (char c : s.toCharArray()) if (!Character.isDigit(c)) { digits = false; break; } max = Math.max(max, digits ? Integer.parseInt(s) : s.length()); } return max;
}"""
        ),
        DsaProblem(
            id = "str_097",
            topic = "strings",
            title = "Strong Password Checker II",
            difficulty = "Easy",
            pattern = "Character Classification",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Password must be at least 8 chars, contain lower, upper, digit, special char, and no two adjacent identical characters.",
            exampleInput = "password = \"IloveLe3tcode!\"",
            exampleOutput = "true",
            keyInsight = "Check length >= 8. Use flags for lower, upper, digit, special. Verify s[i] != s[i-1].",
            solutionCode = """class Solution {
    // Strong Password Checker II
    public static void solve() {}
    // Core logic:
    // if (password.length() < 8) return false; boolean lower = false, upper = false, digit = false, special = false; String spec = "!@#$%^&*()-+"; for (int i = 0; i < password.length(); i++) { char c = password.charAt(i); if (i > 0 && c == password.charAt(i - 1)) return false; if (Character.isLowerCase(c)) lower = true; else if (Character.isUpperCase(c)) upper = true; else if (Character.isDigit(c)) digit = true; else if (spec.indexOf(c) != -1) special = true; } return lower && upper && digit && special;
}"""
        ),
        DsaProblem(
            id = "str_098",
            topic = "strings",
            title = "Reformat Date",
            difficulty = "Easy",
            pattern = "Date String Parsing",
            timeComplexity = "O(1)",
            spaceComplexity = "O(1)",
            description = "Convert date string '20th Oct 2052' to format 'YYYY-MM-DD'.",
            exampleInput = "date = \"20th Oct 2052\"",
            exampleOutput = "\"2052-10-20\"",
            keyInsight = "Map month string to 2-digit representation. Strip non-digits from day. Concatenate YYYY-MM-DD.",
            solutionCode = """class Solution {
    // Reformat Date
    public static void solve() {}
    // Core logic:
    // String[] p = date.split(" "); List<String> months = List.of("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"); int m = months.indexOf(p[1]) + 1; String day = p[0].replaceAll("[^0-9]", ""); return String.format("%s-%02d-%02d", p[2], m, Integer.parseInt(day));
}"""
        ),
        DsaProblem(
            id = "str_099",
            topic = "strings",
            title = "Reformat Phone Number",
            difficulty = "Easy",
            pattern = "Greedy Chunking",
            timeComplexity = "O(N)",
            spaceComplexity = "O(N)",
            description = "Remove spaces/dashes. Group digits into blocks of 3. If 4 digits remain, group into 2 blocks of 2.",
            exampleInput = "number = \"1-23-45 6\"",
            exampleOutput = "\"123-456\"",
            keyInsight = "Strip non-digits. While remaining > 4, take 3 digits. If 4 remain, split as 2-2.",
            solutionCode = """class Solution {
    // Reformat Phone Number
    public static void solve() {}
    // Core logic:
    // String s = number.replaceAll("[- ]", ""); StringBuilder sb = new StringBuilder(); int i = 0, n = s.length(); while (n - i > 4) { sb.append(s.substring(i, i + 3)).append("-"); i += 3; } if (n - i == 4) sb.append(s.substring(i, i + 2)).append("-").append(s.substring(i + 2)); else sb.append(s.substring(i)); return sb.toString();
}"""
        ),
        DsaProblem(
            id = "str_100",
            topic = "strings",
            title = "Check If a Word Occurs As a Prefix of Any Word in a Sentence",
            difficulty = "Easy",
            pattern = "Token Prefix Check",
            timeComplexity = "O(N)",
            spaceComplexity = "O(1)",
            description = "Given a sentence text and searchWord, return the 1-indexed position of the first word that has searchWord as a prefix.",
            exampleInput = "sentence = \"i love eating burger\", searchWord = \"burg\"",
            exampleOutput = "4",
            keyInsight = "Split sentence by space. Check if word starts with searchWord, return index + 1.",
            solutionCode = """class Solution {
    // Check If a Word Occurs As a Prefix of Any Word in a Sentence
    public static void solve() {}
    // Core logic:
    // String[] words = sentence.split(" "); for (int i = 0; i < words.length; i++) if (words[i].startsWith(searchWord)) return i + 1; return -1;
}"""
        )
    )
}
