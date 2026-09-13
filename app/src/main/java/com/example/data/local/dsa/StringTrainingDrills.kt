package com.example.data.local.dsa

import com.example.domain.model.DrillQuiz
import com.example.domain.model.DrillTraceStep
import com.example.domain.model.TrainingDrill

val stringTrainingDrills = listOf(
    // Lesson 1
    TrainingDrill(
        id = "str_drill_01",
        topicId = "strings",
        lessonNumber = 1,
        title = "Character Frequency Direct Indexing",
        subtitle = "Replacing heavy HashMaps with fixed-size ASCII arrays",
        conceptDelta = "Baseline: Using an int[26] frequency array instead of Map<Character, Integer>",
        intuition = "In string problems, the alphabet is usually bounded (e.g. 'a' through 'z'). Using `int[26]` with index `c - 'a'` provides instant O(1) cache-friendly counting with zero memory allocation overhead.",
        codePattern = """// Lesson 1: Direct ASCII Frequency Array
public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) return false;
    int[] count = new int[26];
    for (int i = 0; i < s.length(); i++) {
        count[s.charAt(i) - 'a']++;
        count[t.charAt(i) - 'a']--;
    }
    for (int val : count) {
        if (val != 0) return false;
    }
    return true;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "s = \"cat\", t = \"act\"", "count array initialized to all 0s (size 26)."),
            DrillTraceStep(2, "i = 0: s[0]='c', t[0]='a'", "count['c'-'a']++ (+1), count['a'-'a']-- (-1)."),
            DrillTraceStep(3, "i = 1: s[1]='a', t[1]='c'", "count['a'-'a']++ (cancels to 0), count['c'-'a']-- (cancels to 0)."),
            DrillTraceStep(4, "i = 2: s[2]='t', t[2]='t'", "count['t'-'a']++ then -- (cancels to 0)."),
            DrillTraceStep(5, "Verification", "All 26 elements are exactly 0 -> valid anagram!")
        ),
        quizQuestion = DrillQuiz(
            question = "What is the primary advantage of `int[26]` over `HashMap<Character, Integer>` for lowercase English strings?",
            options = listOf(
                "int[26] handles Unicode emoji characters automatically",
                "int[26] avoids boxing, object hashing, and gives O(1) fixed space and blazing memory locality",
                "int[26] automatically sorts the string",
                "HashMap cannot store characters"
            ),
            correctOptionIndex = 1,
            explanation = "int[26] is allocated on the stack/heap as a contiguous 104-byte array, avoiding HashNode allocations and pointer chasing."
        )
    ),
    // Lesson 2
    TrainingDrill(
        id = "str_drill_02",
        topicId = "strings",
        lessonNumber = 2,
        title = "Symmetrical Inward Matching with Filters",
        subtitle = "Mirror pointers with character skip logic",
        conceptDelta = "+1 Concept: Opposing pointers on strings with conditional skipping of non-alphanumeric chars",
        intuition = "In Arrays we did opposing pointers on numbers. On strings, we frequently check palindromes. The added twist is handling dirty input (spaces, punctuation, case differences) without creating new cleaned string copies.",
        codePattern = """// Lesson 2: Two-Pointer String Mirror with Skip
public boolean isPalindrome(String s) {
    int left = 0, right = s.length() - 1;
    while (left < right) {
        while (left < right && !Character.isLetterOrDigit(s.charAt(left))) left++;
        while (left < right && !Character.isLetterOrDigit(s.charAt(right))) right--;
        if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
            return false;
        }
        left++;
        right--;
    }
    return true;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "s = \"A man, a plan, a canal: Panama\"", "left = 0 ('A'), right = 30 ('a')."),
            DrillTraceStep(2, "Match: 'a' == 'a'", "Both match! left moves to 1, right to 29."),
            DrillTraceStep(3, "left = 1 (' '), right = 29 ('m')", "left skips space -> left = 2 ('m'). Compare 'm' == 'm' -> match!"),
            DrillTraceStep(4, "right encounters ':' and ' '", "Inner while skips non-alphanumerics safely without O(N) regex replace."),
            DrillTraceStep(5, "Pointers cross at center", "Returns true in O(N) time with O(1) extra space.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why is in-place pointer skipping preferred over `s.replaceAll(\"[^a-zA-Z0-9]\", \"\").toLowerCase()`?",
            options = listOf(
                "Because replaceAll does not work on Android",
                "Because replaceAll creates multiple temporary heap copies of size O(N), whereas pointers use O(1) auxiliary space",
                "Because pointers can run in reverse",
                "There is no difference"
            ),
            correctOptionIndex = 1,
            explanation = "String replace and lowercase create 2-3 intermediate String objects, putting pressure on the Garbage Collector in mobile devices."
        )
    ),
    // Lesson 3
    TrainingDrill(
        id = "str_drill_03",
        topicId = "strings",
        lessonNumber = 3,
        title = "Sliding Window Substring with Unique Set",
        subtitle = "Tracking dynamic character presence within a moving boundary",
        conceptDelta = "+1 Concept: Sliding window with a HashSet or visited boolean array to enforce uniqueness",
        intuition = "To find the longest substring without repeating characters, we combine the array sliding window with a character lookup. When `s[right]` is already in our window set, `left` contracts until the duplicate character is eliminated.",
        codePattern = """// Lesson 3: Sliding Window with Char Set
public int lengthOfLongestSubstring(String s) {
    int left = 0, maxLen = 0;
    Set<Character> seen = new HashSet<>();
    for (int right = 0; right < s.length(); right++) {
        char c = s.charAt(right);
        while (seen.contains(c)) {
            seen.remove(s.charAt(left++));
        }
        seen.add(c);
        maxLen = Math.max(maxLen, right - left + 1);
    }
    return maxLen;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "s = \"abcabcbb\"", "left = 0, right = 0 ('a'), seen = {'a'}, maxLen = 1."),
            DrillTraceStep(2, "right = 1 ('b'), 2 ('c')", "No duplicates. seen = {'a', 'b', 'c'}, maxLen = 3."),
            DrillTraceStep(3, "right = 3 ('a' duplicate!)", "seen.contains('a') is true. Remove s[left=0] ('a'), left becomes 1. Add new 'a'."),
            DrillTraceStep(4, "Window moves to [b, c, a]", "len = 3. Duplicate was ejected in O(1) operations."),
            DrillTraceStep(5, "Final result", "maxLen = 3 (\"abc\").")
        ),
        quizQuestion = DrillQuiz(
            question = "When right reaches a duplicate character, why do we remove `s.charAt(left)` rather than the duplicate directly?",
            options = listOf(
                "Because sets only allow removal from the left",
                "Because the window must remain contiguous; we must slide the left boundary forward until the duplicate is dropped",
                "Because characters are immutable",
                "It doesn't matter which character is removed"
            ),
            correctOptionIndex = 1,
            explanation = "A substring must be contiguous. If a duplicate exists at index 'right', all characters up to the prior occurrence must leave the window."
        )
    ),
    // Lesson 4
    TrainingDrill(
        id = "str_drill_04",
        topicId = "strings",
        lessonNumber = 4,
        title = "StringBuilder as a LIFO Stack",
        subtitle = "Linear string reduction with instant top-of-stack inspection",
        conceptDelta = "+1 Concept: Using StringBuilder with index pointer as a zero-overhead stack",
        intuition = "When removing adjacent duplicates or simplifying paths, a Stack is natural. But converting Stack<Character> back to String is slow. By using a `StringBuilder` as a stack (checking `sb.charAt(sb.length() - 1)`), we get LIFO operations with O(1) append/deleteCharAt and zero object overhead.",
        codePattern = """// Lesson 4: StringBuilder as Stack
public String removeDuplicates(String s) {
    StringBuilder sb = new StringBuilder();
    for (char c : s.toCharArray()) {
        int len = sb.length();
        if (len > 0 && sb.charAt(len - 1) == c) {
            sb.deleteCharAt(len - 1);
        } else {
            sb.append(c);
        }
    }
    return sb.toString();
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "s = \"abbaca\", sb = \"\"", "Start with empty StringBuilder stack."),
            DrillTraceStep(2, "c = 'a'", "sb empty -> append 'a'. sb = \"a\"."),
            DrillTraceStep(3, "c = 'b'", "top ('a') != 'b' -> append 'b'. sb = \"ab\"."),
            DrillTraceStep(4, "c = 'b'", "top ('b') == 'b' -> MATCH! deleteCharAt top. sb becomes \"a\"!"),
            DrillTraceStep(5, "c = 'a'", "top ('a') == 'a' -> MATCH! deleteCharAt top. sb becomes \"\"!"),
            DrillTraceStep(6, "c = 'c', then 'a'", "Append 'c', then 'a'. Final answer: \"ca\".")
        ),
        quizQuestion = DrillQuiz(
            question = "What is the time complexity of `sb.deleteCharAt(sb.length() - 1)`?",
            options = listOf(
                "O(N) because it shifts all characters",
                "O(1) because deleting the last character requires only decrementing the internal count",
                "O(log N)",
                "O(N²)"
            ),
            correctOptionIndex = 1,
            explanation = "Deleting the last character of a StringBuilder only updates its internal count integer without copying or shifting any array elements."
        )
    ),
    // Lesson 5
    TrainingDrill(
        id = "str_drill_05",
        topicId = "strings",
        lessonNumber = 5,
        title = "In-Place Reversal & Word Decomposition",
        subtitle = "Reversing the entire string followed by reversing individual word tokens",
        conceptDelta = "+1 Concept: Double reversal: reverse(0, n-1) reverses word order, then reverse(wordStart, wordEnd) restores word spelling",
        intuition = "To reverse words in a string (\"the sky is blue\" -> \"blue is sky the\") in-place without splitting into arrays: (1) Reverse the entire string: \"eulb si yks eht\". Notice all words are in the correct final position, but each word is backwards! (2) Reverse each individual word back.",
        codePattern = """// Lesson 5: Double Reversal Technique
public void reverseWords(char[] s) {
    // 1. Reverse entire string
    reverse(s, 0, s.length - 1);
    // 2. Reverse each individual word
    int start = 0;
    for (int end = 0; end <= s.length; end++) {
        if (end == s.length || s[end] == ' ') {
            reverse(s, start, end - 1);
            start = end + 1;
        }
    }
}
private void reverse(char[] s, int l, int r) {
    while (l < r) {
        char temp = s[l]; s[l++] = s[r]; s[r--] = temp;
    }
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "Input: \"hello world\"", "Step 1 reverses entire char array -> \"dlrow olleh\"."),
            DrillTraceStep(2, "Detect word 'dlrow'", "Reverse [0..4] -> \"world\"."),
            DrillTraceStep(3, "Detect word 'olleh'", "Reverse [6..10] -> \"hello\"."),
            DrillTraceStep(4, "Result: \"world hello\"", "Clean in-place reversal in O(N) time with O(1) extra space.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why does reversing the entire character array first help in reversing word order?",
            options = listOf(
                "Because it moves the last word to the front and the first word to the back in O(N) time",
                "Because spaces are automatically deleted",
                "Because characters become sorted",
                "It only works on English words"
            ),
            correctOptionIndex = 0,
            explanation = "Reversing the full array places each word token into its correct final position in the sentence, leaving only the letters within each word to be flipped."
        )
    ),
    // Lesson 6
    TrainingDrill(
        id = "str_drill_06",
        topicId = "strings",
        lessonNumber = 6,
        title = "Branching Skip Helper (Palindrome with 1 Deletion)",
        subtitle = "Splitting into two independent verification branches upon first mismatch",
        conceptDelta = "+1 Concept: Upon first mismatch (s[L] != s[R]), test two sub-palindromes: (L+1, R) or (L, R-1)",
        intuition = "In strict palindromes, a mismatch immediately returns false. If allowed at most ONE deletion, the first mismatch gives you a binary choice: either delete `s[left]` OR delete `s[right]`. We delegate this to a helper function that checks if either substring is a perfect palindrome.",
        codePattern = """// Lesson 6: Single-Fault Branching Palindrome
public boolean validPalindrome(String s) {
    int left = 0, right = s.length() - 1;
    while (left < right) {
        if (s.charAt(left) != s.charAt(right)) {
            // Test deleting left character OR deleting right character
            return isSubPalindrome(s, left + 1, right) || isSubPalindrome(s, left, right - 1);
        }
        left++;
        right--;
    }
    return true;
}
private boolean isSubPalindrome(String s, int l, int r) {
    while (l < r) {
        if (s.charAt(l++) != s.charAt(r--)) return false;
    }
    return true;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "s = \"abca\"", "left = 0 ('a'), right = 3 ('a'). Matches! left=1, right=2."),
            DrillTraceStep(2, "Mismatch: s[1]='b' != s[2]='c'", "Fork into two checks: (1) skip 'b' -> test \"c\", or (2) skip 'c' -> test \"b\"."),
            DrillTraceStep(3, "Sub-check 1: isSubPalindrome(s, 2, 2)", "\"c\" is a palindrome! Returns true!"),
            DrillTraceStep(4, "Result: true", "Runs in O(N) time with zero extra heap allocations.")
        ),
        quizQuestion = DrillQuiz(
            question = "What is the time complexity of `validPalindrome` when branching occurs?",
            options = listOf(
                "O(N) because at most two linear passes of the remaining substring are performed",
                "O(2^N) exponential recursion",
                "O(N²)",
                "O(log N)"
            ),
            correctOptionIndex = 0,
            explanation = "Since deletion is allowed at most once, the algorithm forks at most once into two linear checks, taking 2 * O(N) = O(N) total time."
        )
    ),
    // Lesson 7
    TrainingDrill(
        id = "str_drill_07",
        topicId = "strings",
        lessonNumber = 7,
        title = "Vertical Character Scanning across Strings",
        subtitle = "Comparing column-by-column character matches across multiple strings",
        conceptDelta = "+1 Concept: Vertical scanning (character index `i` of every string) over horizontal string-by-string folding",
        intuition = "To find the Longest Common Prefix across N strings, horizontal scanning compares string 1 and 2, then compares the result with string 3. Vertical scanning is smarter: inspect character `i` of all strings simultaneously. The moment any string ends or has a different character, stop and return `s[0].substring(0, i)` immediately!",
        codePattern = """// Lesson 7: Vertical Scanning Prefix
public String longestCommonPrefix(String[] strs) {
    if (strs == null || strs.length == 0) return "";
    for (int i = 0; i < strs[0].length(); i++) {
        char c = strs[0].charAt(i);
        for (int j = 1; j < strs.length; j++) {
            // Check boundary or character mismatch
            if (i == strs[j].length() || strs[j].charAt(i) != c) {
                return strs[0].substring(0, i);
            }
        }
    }
    return strs[0];
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "strs = [\"flower\", \"flow\", \"flight\"]", "i = 0: c = 'f'. All strings have 'f' at index 0."),
            DrillTraceStep(2, "i = 1: c = 'l'.", "All strings have 'l' at index 1."),
            DrillTraceStep(3, "i = 2: c = 'o'.", "strs[0]='o', strs[1]='o', strs[2]='i' (MISMATCH!)."),
            DrillTraceStep(4, "Return strs[0].substring(0, 2)", "Returns \"fl\" immediately without reading any further characters.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why is vertical scanning often faster than horizontal scanning in practice?",
            options = listOf(
                "Because if the mismatch occurs at the first character, it returns after just N comparisons instead of scanning entire strings",
                "Because vertical scanning uses threads",
                "Because strings are stored vertically in memory",
                "Because horizontal scanning is O(N³)"
            ),
            correctOptionIndex = 0,
            explanation = "In worst-case or early-mismatch scenarios, vertical scanning stops after checking very few characters across all strings."
        )
    ),
    // Lesson 8
    TrainingDrill(
        id = "str_drill_08",
        topicId = "strings",
        lessonNumber = 8,
        title = "In-Place String Compression with Read/Write Pointers",
        subtitle = "Compressing repeated characters into character + count in O(1) space",
        conceptDelta = "+1 Concept: Counting repeating runs with inner pointer, then writing multi-digit counts as characters",
        intuition = "In array compression (\"aabbb\" -> \"a2b3\"), we have repeated runs. Let `read` find the end of the current run of identical characters. Write the character to `chars[write++]`. If `count > 1`, write each digit of `count` to `chars[write++]`. Advance `read` to start the next run.",
        codePattern = """// Lesson 8: In-Place Run-Length Encoding
public int compress(char[] chars) {
    int write = 0, read = 0;
    while (read < chars.length) {
        char currentChar = chars[read];
        int count = 0;
        while (read < chars.length && chars[read] == currentChar) {
            read++;
            count++;
        }
        chars[write++] = currentChar;
        if (count > 1) {
            for (char c : Integer.toString(count).toCharArray()) {
                chars[write++] = c;
            }
        }
    }
    return write;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "chars = ['a', 'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'] (12 'b's)", "read=0, write=0."),
            DrillTraceStep(2, "Run 'a': count = 2", "Write 'a' to chars[0]. Write '2' to chars[1]. write = 2."),
            DrillTraceStep(3, "Run 'b': count = 12", "Write 'b' to chars[2]. Convert 12 to '1', '2'. Write '1' to chars[3], '2' to chars[4]. write = 5."),
            DrillTraceStep(4, "Return write = 5", "Array modified in-place: ['a', '2', 'b', '1', '2'].")
        ),
        quizQuestion = DrillQuiz(
            question = "Why is writing digits directly to `chars[write++]` guaranteed not to overwrite unread characters?",
            options = listOf(
                "Because compressed representation length is always <= the original run length (e.g. 12 'b's take 3 cells: 'b', '1', '2')",
                "Because chars is duplicated internally",
                "Because write pointer is always ahead of read pointer",
                "Because Java allocates a new array"
            ),
            correctOptionIndex = 0,
            explanation = "Any run of length K >= 2 takes 1 (char) + digits(K) characters to represent. Since 1 + digits(K) <= K for all K >= 2, write is never ahead of read."
        )
    ),
    // Lesson 9
    TrainingDrill(
        id = "str_drill_09",
        topicId = "strings",
        lessonNumber = 9,
        title = "Lookahead Subtractive Evaluation",
        subtitle = "Parsing Roman numerals by evaluating `curr < next` pairs",
        conceptDelta = "+1 Concept: Lookahead comparison: if `val(s[i]) < val(s[i+1])`, subtract `val(s[i])`, else add it",
        intuition = "Roman numerals are normally additive: VI = 5 + 1 = 6. But when a smaller numeral precedes a larger one, it's subtractive: IV = 4. With a 1-character lookahead: if `curr < next`, we subtract `curr` from total (`-1`); when `next` is processed on the next step, it adds `+5`, netting `-1 + 5 = 4`!",
        codePattern = """// Lesson 9: Lookahead Subtractive Parsing
public int romanToInt(String s) {
    Map<Character, Integer> map = Map.of(
        'I', 1, 'V', 5, 'X', 10, 'L', 50,
        'C', 100, 'D', 500, 'M', 1000
    );
    int total = 0;
    for (int i = 0; i < s.length(); i++) {
        int current = map.get(s.charAt(i));
        if (i + 1 < s.length() && current < map.get(s.charAt(i + 1))) {
            total -= current; // Subtractive condition
        } else {
            total += current;
        }
    }
    return total;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "s = \"MCMXCIV\"", "Total = 0."),
            DrillTraceStep(2, "i=0: 'M' (1000) >= 'C' (100)", "total += 1000 = 1000."),
            DrillTraceStep(3, "i=1: 'C' (100) < 'M' (1000)", "Subtractive! total -= 100 = 900."),
            DrillTraceStep(4, "i=2: 'M' (1000) >= 'X' (10)", "total += 1000 = 1900."),
            DrillTraceStep(5, "i=3: 'X' (10) < 'C' (100)", "total -= 10 = 1890."),
            DrillTraceStep(6, "Full string parsed", "Returns 1994 in O(N) time with zero backtracking.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why does subtracting `current` when `current < next` yield the correct numeric result?",
            options = listOf(
                "Because subtracting X now and adding Y later nets (Y - X), which exactly matches the subtractive Roman pair value",
                "Because Roman numbers are base 10",
                "Because subtraction resets the accumulator",
                "It is a compiler quirk"
            ),
            correctOptionIndex = 0,
            explanation = "In 'IX', subtracting 1 gives -1. Adding 10 on the next iteration gives -1 + 10 = 9, exactly matching (10 - 1)."
        )
    ),
    // Lesson 10
    TrainingDrill(
        id = "str_drill_10",
        topicId = "strings",
        lessonNumber = 10,
        title = "Greedy Subtractive Lookup Table",
        subtitle = "Converting integers to Roman numerals using paired greedy tables",
        conceptDelta = "+1 Concept: Parallel descending value and symbol arrays incorporating 2-letter subtractive pairs",
        intuition = "To convert integer to Roman, pair each value with its symbol in strictly descending order—including the 6 subtractive pairs (900: CM, 400: CD, 90: XC, 40: XL, 9: IX, 4: IV). Greedily subtract the largest possible value and append its symbol while `num >= values[i]`.",
        codePattern = """// Lesson 10: Greedy Table Lookup
public String intToRoman(int num) {
    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < values.length && num > 0; i++) {
        while (num >= values[i]) {
            num -= values[i];
            sb.append(symbols[i]);
        }
    }
    return sb.toString();
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "num = 58", "i=0..4: values > 58. i=5: values[5]=50 (L) <= 58."),
            DrillTraceStep(2, "num -= 50 -> 8", "sb.append(\"L\"). num is now 8."),
            DrillTraceStep(3, "values[9]=5 (V) <= 8", "num -= 5 -> 3. sb.append(\"V\")."),
            DrillTraceStep(4, "values[12]=1 (I) <= 3", "num -= 1 (x3). sb.append(\"III\")."),
            DrillTraceStep(5, "Final result", "Returns \"LVIII\".")
        ),
        quizQuestion = DrillQuiz(
            question = "Why are the subtractive cases like \"CM\" (900) and \"IV\" (4) included directly in the values array?",
            options = listOf(
                "Including subtractive pairs turns the problem into a pure greedy coin-change problem that never needs backtracking",
                "Because Roman law required it",
                "To make the array length equal to 13",
                "Because StringBuilder cannot handle 4 characters"
            ),
            correctOptionIndex = 0,
            explanation = "By treating 'IV' (4) as an atomic denomination, greedy largest-first selection is guaranteed to produce the canonical Roman numeral."
        )
    ),
    // Lesson 11
    TrainingDrill(
        id = "str_drill_11",
        topicId = "strings",
        lessonNumber = 11,
        title = "Sliding Window with Match Counter (Minimum Window Substring)",
        subtitle = "Tracking multi-character target frequencies with a single `formed` match counter",
        conceptDelta = "+1 Concept: `formed == required` counter avoids comparing all 26 frequencies on every step",
        intuition = "In Minimum Window Substring, comparing frequency maps takes O(26) each step. Optimization: let `required` be the number of unique characters in `target`. Maintain `formed`: increment `formed` only when a character's window count MATCHES its target count. When `formed == required`, the window is valid—contract `left`!",
        codePattern = """// Lesson 11: Sliding Window Match Counter
public String minWindow(String s, String t) {
    if (s.length() < t.length()) return "";
    Map<Character, Integer> target = new HashMap<>();
    for (char c : t.toCharArray()) target.put(c, target.getOrDefault(c, 0) + 1);
    
    int required = target.size(), formed = 0, left = 0;
    int minLen = Integer.MAX_VALUE, start = 0;
    Map<Character, Integer> window = new HashMap<>();
    
    for (int right = 0; right < s.length(); right++) {
        char c = s.charAt(right);
        window.put(c, window.getOrDefault(c, 0) + 1);
        if (target.containsKey(c) && window.get(c).intValue() == target.get(c).intValue()) {
            formed++;
        }
        while (left <= right && formed == required) {
            if (right - left + 1 < minLen) {
                minLen = right - left + 1;
                start = left;
            }
            char leftChar = s.charAt(left);
            window.put(leftChar, window.get(leftChar) - 1);
            if (target.containsKey(leftChar) && window.get(leftChar) < target.get(leftChar)) {
                formed--;
            }
            left++;
        }
    }
    return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "s = \"ADOBECODEBANC\", t = \"ABC\"", "target = {A:1, B:1, C:1}, required = 3, formed = 0."),
            DrillTraceStep(2, "right expands until index 5 ('C')", "window has A, B, C. formed = 3 == required. Window: \"ADOBEC\" (len 6)."),
            DrillTraceStep(3, "Contract left", "Drop 'A': formed drops to 2. right expands to find next 'A'."),
            DrillTraceStep(4, "At end: window \"BANC\"", "len = 4. formed = 3. Best minimal window is \"BANC\".")
        ),
        quizQuestion = DrillQuiz(
            question = "Why use `window.get(c).intValue() == target.get(c).intValue()` when comparing Integer objects?",
            options = listOf(
                "Because Integer objects outside the cache [-128..127] compared with `==` check object reference identity, causing false negatives",
                "Because Java autoboxing requires it",
                "To convert chars to uppercase",
                "It is a syntax requirement in Kotlin"
            ),
            correctOptionIndex = 0,
            explanation = "Using `==` on boxed Integer objects compares memory addresses, not numerical values, when frequencies exceed 127."
        )
    ),
    // Lesson 12
    TrainingDrill(
        id = "str_drill_12",
        topicId = "strings",
        lessonNumber = 12,
        title = "Fixed Sliding Window Frequency Differencing",
        subtitle = "Finding all anagram substrings using a running window diff",
        conceptDelta = "+1 Concept: Fixed window of length `p.length()`: slide by +s[i] and -s[i - p.length()]",
        intuition = "An anagram of string P is simply an exact permutation of P's characters. Since an anagram MUST have length `p.length()`, we slide a fixed-size window. Maintain two frequency arrays `sCount` and `pCount`. Check `Arrays.equals(sCount, pCount)` in O(26) = O(1) time.",
        codePattern = """// Lesson 12: Fixed Anagram Window
public List<Integer> findAnagrams(String s, String p) {
    List<Integer> result = new ArrayList<>();
    if (s.length() < p.length()) return result;
    int[] pCount = new int[26], sCount = new int[26];
    for (char c : p.toCharArray()) pCount[c - 'a']++;
    
    int k = p.length();
    for (int i = 0; i < s.length(); i++) {
        sCount[s.charAt(i) - 'a']++;
        if (i >= k) sCount[s.charAt(i - k) - 'a']--; // Evict leftmost
        if (Arrays.equals(sCount, pCount)) {
            result.add(i - k + 1);
        }
    }
    return result;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "s = \"cbaebabacd\", p = \"abc\"", "k = 3. pCount has {a:1, b:1, c:1}."),
            DrillTraceStep(2, "i = 2 (window \"cba\")", "sCount has {a:1, b:1, c:1}. Matches pCount! Add index 0."),
            DrillTraceStep(3, "i = 3: add 'e', remove s[0]='c'", "Window \"bae\". Not a match."),
            DrillTraceStep(4, "i = 6 (window \"bac\")", "Matches pCount! Add index 6. Result: [0, 6].")
        ),
        quizQuestion = DrillQuiz(
            question = "What is the time complexity of `findAnagrams` using `Arrays.equals(sCount, pCount)`?",
            options = listOf(
                "O(26 * N) = O(N) because the frequency array has fixed length 26",
                "O(N * M)",
                "O(N²)",
                "O(N log N)"
            ),
            correctOptionIndex = 0,
            explanation = "Comparing two fixed 26-element arrays takes exactly 26 operations, making the loop runtime O(26 * N) = O(N)."
        )
    ),
    // Lesson 13
    TrainingDrill(
        id = "str_drill_13",
        topicId = "strings",
        lessonNumber = 13,
        title = "Permutation In String via O(1) Match Counter",
        subtitle = "Eliminating the 26-character array comparison by tracking exact character matches",
        conceptDelta = "+1 Concept: `matches` integer tracking how many of the 26 characters have identical counts",
        intuition = "In Lesson 12 we called `Arrays.equals` (26 operations). We can reduce even that to O(1) by maintaining an integer `matches` (0 to 26). When incoming or outgoing characters change frequency, adjust `matches` only if a count transitions into or out of equality with target.",
        codePattern = """// Lesson 13: O(1) Per-Step Permutation Match
public boolean checkInclusion(String s1, String s2) {
    if (s1.length() > s2.length()) return false;
    int[] c1 = new int[26], c2 = new int[26];
    for (int i = 0; i < s1.length(); i++) {
        c1[s1.charAt(i) - 'a']++;
        c2[s2.charAt(i) - 'a']++;
    }
    int matches = 0;
    for (int i = 0; i < 26; i++) if (c1[i] == c2[i]) matches++;
    
    for (int i = 0; i < s2.length() - s1.length(); i++) {
        if (matches == 26) return true;
        int r = s2.charAt(i + s1.length()) - 'a', l = s2.charAt(i) - 'a';
        // Add right character
        c2[r]++;
        if (c2[r] == c1[r]) matches++;
        else if (c2[r] == c1[r] + 1) matches--;
        // Remove left character
        c2[l]--;
        if (c2[l] == c1[l]) matches++;
        else if (c2[l] == c1[l] - 1) matches--;
    }
    return matches == 26;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "s1 = \"ab\", s2 = \"eidbaooo\"", "k = 2. Initial window \"ei\". matches = 24 (since 24 characters have count 0 in both)."),
            DrillTraceStep(2, "Slide right: +d, -e", "matches updated in O(1) operations."),
            DrillTraceStep(3, "Window reaches \"ba\"", "All 26 character counts match! matches == 26 -> returns true immediately.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why does `matches == 26` signify a valid permutation?",
            options = listOf(
                "Because if all 26 character counts are identical, the window contains an exact anagram of s1",
                "Because English has 26 letters",
                "Because s1 length must be 26",
                "It only works if string contains all 26 letters"
            ),
            correctOptionIndex = 0,
            explanation = "Since the window length matches s1.length(), having identical counts for all 26 characters guarantees that the window is an exact permutation."
        )
    ),
    // Lesson 14
    TrainingDrill(
        id = "str_drill_14",
        topicId = "strings",
        lessonNumber = 14,
        title = "Window Slack Optimization (Character Replacement)",
        subtitle = "Allowing K edits by enforcing `windowLen - maxFreq <= K`",
        conceptDelta = "+1 Concept: Window condition: `(right - left + 1) - maxFreq <= k` (non-dominant characters <= K)",
        intuition = "If a window has 10 characters and the most frequent character appears 7 times, you need 10 - 7 = 3 replacements to make the whole window uniform. If 3 <= K, the window is valid! Remarkably, `maxFreq` does not even need to be decremented when shrinking `left`—a stale maxFreq can never produce a false positive larger window!",
        codePattern = """// Lesson 14: Max-Frequency Slack Window
public int characterReplacement(String s, int k) {
    int[] count = new int[26];
    int left = 0, maxFreq = 0, maxLen = 0;
    for (int right = 0; right < s.length(); right++) {
        maxFreq = Math.max(maxFreq, ++count[s.charAt(right) - 'a']);
        // If replacements needed exceed k, shrink window
        while ((right - left + 1) - maxFreq > k) {
            count[s.charAt(left) - 'a']--;
            left++;
        }
        maxLen = Math.max(maxLen, right - left + 1);
    }
    return maxLen;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "s = \"AABABBA\", k = 1", "left = 0, right = 0 ('A'): count['A']=1, maxFreq=1. Valid."),
            DrillTraceStep(2, "right expands to 3 (\"AABA\")", "len = 4, maxFreq = 3 ('A'). Replacements = 4 - 3 = 1 <= 1. maxLen = 4."),
            DrillTraceStep(3, "right = 4 ('B'): \"AABAB\"", "len = 5, maxFreq = 3. Replacements = 5 - 3 = 2 > 1! Shrink left: left becomes 1."),
            DrillTraceStep(4, "Result: maxLen = 4", "Optimal window found in O(N) single pass.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why is it mathematically safe NOT to recalculate `maxFreq` downwards when `left` advances?",
            options = listOf(
                "Because a smaller maxFreq could only produce a smaller window, which can never beat the already discovered maxLen",
                "Because characters never repeat",
                "Because count array is immutable",
                "It is an unsafe approximation"
            ),
            correctOptionIndex = 0,
            explanation = "We only care about discovering a LARGER maxLen. A larger window strictly requires a higher maxFreq, so a temporarily stale, slightly higher maxFreq does not invalidate correctness."
        )
    ),
    // Lesson 15
    TrainingDrill(
        id = "str_drill_15",
        topicId = "strings",
        lessonNumber = 15,
        title = "The `atMost(K) - atMost(K - 1)` Equivalence",
        subtitle = "Computing 'exactly K' substrings by subtracting 'at most K-1' substrings",
        conceptDelta = "+1 Concept: `countExactly(k) = countAtMost(k) - countAtMost(k - 1)`",
        intuition = "Finding substrings with EXACTLY K distinct characters directly with sliding windows is notoriously difficult because shrinking the window might make it valid or invalid unpredictably. But finding AT MOST K distinct characters is easy and monotonic! The difference `atMost(k) - atMost(k - 1)` gives exactly K.",
        codePattern = """// Lesson 15: Exact K via At Most K Difference
public int subarraysWithKDistinct(int[] nums, int k) {
    return atMost(nums, k) - atMost(nums, k - 1);
}
private int atMost(int[] nums, int k) {
    Map<Integer, Integer> count = new HashMap<>();
    int left = 0, total = 0;
    for (int right = 0; right < nums.length; right++) {
        count.put(nums[right], count.getOrDefault(nums[right], 0) + 1);
        while (count.size() > k) {
            count.put(nums[left], count.get(nums[left]) - 1);
            if (count.get(nums[left]) == 0) count.remove(nums[left]);
            left++;
        }
        total += right - left + 1; // Number of subarrays ending at right
    }
    return total;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "nums = [1, 2, 1, 2, 3], k = 2", "Compute atMost(2) and atMost(1)."),
            DrillTraceStep(2, "atMost(2)", "Counts all subarrays having <= 2 distinct values = 12."),
            DrillTraceStep(3, "atMost(1)", "Counts all subarrays having <= 1 distinct value (all identicals) = 5."),
            DrillTraceStep(4, "Result: 12 - 5 = 7", "Subarrays with exactly 2 distinct numbers = 7.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why does `right - left + 1` count the number of valid subarrays ending at `right`?",
            options = listOf(
                "Because any starting index from `left` to `right` forms a valid contiguous subarray ending at `right`",
                "Because it calculates the area of the window",
                "Because array elements are distinct",
                "It is an arbitrary formula"
            ),
            correctOptionIndex = 0,
            explanation = "If [left..right] is valid, every subsegment starting at left, left+1, ..., right and ending at right is also valid (total = right - left + 1)."
        )
    ),
    // Lesson 16
    TrainingDrill(
        id = "str_drill_16",
        topicId = "strings",
        lessonNumber = 16,
        title = "Balanced Bracket Validation via Stack",
        subtitle = "Pushing expected closing brackets for instant equality matching",
        conceptDelta = "+1 Concept: Push expected closing bracket onto stack when open bracket is seen",
        intuition = "The standard way to check brackets `()[]{}` is pushing open brackets and doing a big switch on close. A much cleaner trick: when you see '(', push ')'; when you see '{', push '}'; when you see '[', push ']'. When a closing bracket arrives, simply check `if (stack.isEmpty() || stack.pop() != c) return false`!",
        codePattern = """// Lesson 16: Expected-Closer Stack Matching
public boolean isValid(String s) {
    Deque<Character> stack = new ArrayDeque<>();
    for (char c : s.toCharArray()) {
        if (c == '(') stack.push(')');
        else if (c == '{') stack.push('}');
        else if (c == '[') stack.push(']');
        else if (stack.isEmpty() || stack.pop() != c) return false;
    }
    return stack.isEmpty();
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "s = \"{[()]}\"", "Stack empty."),
            DrillTraceStep(2, "c = '{'", "Push expected '}'. Stack: ['}']"),
            DrillTraceStep(3, "c = '['", "Push expected ']'. Stack: [']', '}']"),
            DrillTraceStep(4, "c = '('", "Push expected ')'. Stack: [')', ']', '}']"),
            DrillTraceStep(5, "c = ')'", "Pop matches top ')'. Stack: [']', '}']"),
            DrillTraceStep(6, "Stack unwinds cleanly", "At end, stack.isEmpty() == true -> Valid!")
        ),
        quizQuestion = DrillQuiz(
            question = "Why is pushing the expected closing bracket cleaner than pushing the open bracket?",
            options = listOf(
                "Because closing bracket comparison becomes a single O(1) equality check `stack.pop() != c`",
                "Because closing brackets take less memory",
                "Because open brackets cannot be hashed",
                "It avoids using Deque"
            ),
            correctOptionIndex = 0,
            explanation = "Pre-pushing the expected closer eliminates nested switch statements or pair lookup maps when verifying closing brackets."
        )
    ),
    // Lesson 17
    TrainingDrill(
        id = "str_drill_17",
        topicId = "strings",
        lessonNumber = 17,
        title = "Balanced Parentheses Repair (Index Tracking)",
        subtitle = "Marking invalid indices for removal using stack of unmatched indices",
        conceptDelta = "+1 Concept: Pushing index of unmatched '('; deleting unmatched ')' immediately",
        intuition = "In Minimum Remove to Make Valid Parentheses: when seeing ')', if stack has an open '(', pop it. If stack is empty, this ')' is illegal—mark it for deletion. At the end, any '(' indices still remaining on the stack are also illegal. Filter marked indices out in a second pass.",
        codePattern = """// Lesson 17: Index Stack Marking
public String minRemoveToMakeValid(String s) {
    Set<Integer> indexesToRemove = new HashSet<>();
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if (c == '(') {
            stack.push(i);
        } else if (c == ')') {
            if (stack.isEmpty()) indexesToRemove.add(i); // Illegal closing
            else stack.pop(); // Matched!
        }
    }
    while (!stack.isEmpty()) indexesToRemove.add(stack.pop()); // Illegal opens
    
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
        if (!indexesToRemove.contains(i)) sb.append(s.charAt(i));
    }
    return sb.toString();
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "s = \"a)b(c)d\"", "i = 1: ')' with empty stack -> mark index 1 for removal."),
            DrillTraceStep(2, "i = 3: '('", "Push index 3 onto stack."),
            DrillTraceStep(3, "i = 5: ')'", "Pop index 3 from stack (matched!)."),
            DrillTraceStep(4, "Build result", "Skip index 1. Result: \"ab(c)d\". Perfectly balanced in O(N) time.")
        ),
        quizQuestion = DrillQuiz(
            question = "Which parentheses are marked for deletion at the end of the first pass?",
            options = listOf(
                "Any ')' that had no matching '(' before it, plus any '(' that was never closed by the end of the string",
                "All parentheses",
                "Only the last parenthesis",
                "Every odd-indexed character"
            ),
            correctOptionIndex = 0,
            explanation = "Any excess closing bracket is rejected immediately, and any open bracket lingering on the stack without a closing partner is marked after the loop."
        )
    ),
    // Lesson 18
    TrainingDrill(
        id = "str_drill_18",
        topicId = "strings",
        lessonNumber = 18,
        title = "Path Tokenization & Directory Traversal",
        subtitle = "Simplifying Unix canonical paths using stack navigation",
        conceptDelta = "+1 Concept: Split by '/', skip empty and '.', pop on '..', push valid directory tokens",
        intuition = "Simplifying a Unix file path: split string by `/`. We encounter four types of tokens: (1) `\"\"` or `\".\"` (current directory -> do nothing), (2) `\"..\"` (parent directory -> pop from stack if not empty), (3) regular directory name -> push onto stack. Join remaining tokens with `/`.",
        codePattern = """// Lesson 18: Canonical Path Stack
public String simplifyPath(String path) {
    Deque<String> stack = new ArrayDeque<>();
    for (String dir : path.split("/")) {
        if (dir.isEmpty() || dir.equals(".")) continue;
        if (dir.equals("..")) {
            if (!stack.isEmpty()) stack.pop();
        } else {
            stack.push(dir);
        }
    }
    StringBuilder sb = new StringBuilder();
    // Reconstruct path from bottom of stack to top
    Iterator<String> it = stack.descendingIterator();
    while (it.hasNext()) {
        sb.append("/").append(it.next());
    }
    return sb.length() == 0 ? "/" : sb.toString();
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "path = \"/a/./b/../../c/\"", "Tokens: [\"\", \"a\", \".\", \"b\", \"..\", \"..\", \"c\", \"\"]."),
            DrillTraceStep(2, "Token \"a\"", "Push \"a\". Stack: [\"a\"]"),
            DrillTraceStep(3, "Token \".\"", "Ignored."),
            DrillTraceStep(4, "Token \"b\"", "Push \"b\". Stack: [\"b\", \"a\"]"),
            DrillTraceStep(5, "Two \"..\" tokens", "Pop \"b\", then pop \"a\". Stack is empty."),
            DrillTraceStep(6, "Token \"c\"", "Push \"c\". Final result: \"/c\".")
        ),
        quizQuestion = DrillQuiz(
            question = "Why does `path.split(\"/\")` produce empty strings for input like `\"/home//foo/\"`?",
            options = listOf(
                "Because adjacent slashes and leading/trailing slashes have zero characters between them, resulting in empty tokens",
                "Because split is broken in Java",
                "Because home is a root directory",
                "It does not produce empty strings"
            ),
            correctOptionIndex = 0,
            explanation = "Leading slashes and consecutive slashes contain empty character sequences between delimiters, producing empty strings in the token array."
        )
    ),
    // Lesson 19
    TrainingDrill(
        id = "str_drill_19",
        topicId = "strings",
        lessonNumber = 19,
        title = "Dual Stack Nested String Decoding",
        subtitle = "Parsing nested repeat patterns `k[encoded_string]` with count and string stacks",
        conceptDelta = "+1 Concept: Two stacks: `countStack` for repeat multipliers, `stringStack` for parent strings",
        intuition = "In Decode String (\"3[a2[c]]\"), brackets can be nested arbitrarily deep. When `[` appears, push the accumulated multiplier to `countStack` and the current string to `stringStack`, then reset both. When `]` appears, pop the multiplier and parent string, repeat current string, and append to parent!",
        codePattern = """// Lesson 19: Dual Stack Nested Decoding
public String decodeString(String s) {
    Deque<Integer> countStack = new ArrayDeque<>();
    Deque<StringBuilder> stringStack = new ArrayDeque<>();
    StringBuilder current = new StringBuilder();
    int k = 0;
    
    for (char c : s.toCharArray()) {
        if (Character.isDigit(c)) {
            k = k * 10 + (c - '0');
        } else if (c == '[') {
            countStack.push(k);
            stringStack.push(current);
            current = new StringBuilder();
            k = 0;
        } else if (c == ']') {
            StringBuilder decoded = stringStack.pop();
            int repeatTimes = countStack.pop();
            for (int i = 0; i < repeatTimes; i++) decoded.append(current);
            current = decoded;
        } else {
            current.append(c);
        }
    }
    return current.toString();
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "s = \"3[a2[c]]\"", "Parse '3', '[': countStack.push(3), stringStack.push(\"\")."),
            DrillTraceStep(2, "Parse 'a', '2', '['", "current = \"a\". countStack.push(2), stringStack.push(\"a\"). current reset to \"\"."),
            DrillTraceStep(3, "Parse 'c', ']'", "current = \"c\". Pop 2 and \"a\". Repeat 'c' 2 times: \"acc\". current becomes \"acc\"."),
            DrillTraceStep(4, "Parse final ']'", "Pop 3 and \"\". Repeat \"acc\" 3 times: \"accaccacc\". Result returned.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why are two separate stacks used instead of recursion?",
            options = listOf(
                "Dual stacks iteratively simulate the call frames with zero risk of StackOverflowError on deeply nested inputs",
                "Because Java cannot recurse on strings",
                "Because recursion takes O(N³)",
                "To sort the characters"
            ),
            correctOptionIndex = 0,
            explanation = "Using two explicit stacks avoids thread call-stack overhead and mirrors recursive state frames in a memory-safe iterative fashion."
        )
    ),
    // Lesson 20
    TrainingDrill(
        id = "str_drill_20",
        topicId = "strings",
        lessonNumber = 20,
        title = "Arithmetic Operator Precedence via Evaluation Stack",
        subtitle = "Handling `*` and `/` immediately while deferring `+` and `-`",
        conceptDelta = "+1 Concept: `+` pushes num, `-` pushes -num, `*` and `/` pop top, compute, and push result",
        intuition = "To calculate arithmetic expressions like \"3+2*2\" in a single pass without parentheses: multiplication and division have higher precedence than addition. So when `*` or `/` is encountered, IMMEDIATELY pop the previous operand, multiply/divide it with current number, and push back. At the end, sum everything in the stack!",
        codePattern = """// Lesson 20: Precedence Evaluation Stack
public int calculate(String s) {
    Deque<Integer> stack = new ArrayDeque<>();
    int num = 0;
    char sign = '+';
    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if (Character.isDigit(c)) num = num * 10 + (c - '0');
        if ((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {
            if (sign == '+') stack.push(num);
            else if (sign == '-') stack.push(-num);
            else if (sign == '*') stack.push(stack.pop() * num);
            else if (sign == '/') stack.push(stack.pop() / num);
            sign = c;
            num = 0;
        }
    }
    int result = 0;
    for (int val : stack) result += val;
    return result;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "s = \"3 + 2 * 2\"", "sign = '+', num = 0."),
            DrillTraceStep(2, "Encounter '+': num = 3", "sign was '+'. stack.push(3). sign becomes '+'. num = 0."),
            DrillTraceStep(3, "Encounter '*': num = 2", "sign was '+'. stack.push(2). sign becomes '*'. num = 0."),
            DrillTraceStep(4, "End of string: num = 2", "sign was '*'. stack.push(stack.pop() * 2) = 2 * 2 = 4."),
            DrillTraceStep(5, "Sum stack: 3 + 4 = 7", "Correct precedence evaluated in O(N) time.")
        ),
        quizQuestion = DrillQuiz(
            question = "How does pushing `-num` for subtraction simplify the final summation?",
            options = listOf(
                "It converts all remaining operations in the stack into pure addition, allowing a simple loop sum at the end",
                "It makes all numbers positive",
                "It prevents stack overflow",
                "It sorts the numbers"
            ),
            correctOptionIndex = 0,
            explanation = "By turning subtraction `a - b` into `a + (-b)`, all pending values in the stack can be folded with pure addition regardless of their original operators."
        )
    ),
    // Lesson 21
    TrainingDrill(
        id = "str_drill_21",
        topicId = "strings",
        lessonNumber = 21,
        title = "Rolling Hash & Rabin-Karp Substring Matching",
        subtitle = "Sliding a polynomial hash window across a text in O(1) per step",
        conceptDelta = "+1 Concept: `hash = (hash * BASE + newChar - oldChar * BASE^k) % MOD`",
        intuition = "Checking if a pattern of length M exists in text of length N naively takes O(N * M). Rabin-Karp computes a polynomial rolling hash of the pattern in O(M). As the M-length window slides across the text, we update the hash in O(1) by subtracting the departing character's high-order term and adding the incoming character!",
        codePattern = """// Lesson 21: Rolling Polynomial Hash
public int strStr(String haystack, String needle) {
    int n = haystack.length(), m = needle.length();
    if (m > n) return -1;
    long BASE = 31, MOD = 1_000_000_007L;
    long needleHash = 0, hayHash = 0, power = 1;
    
    for (int i = 0; i < m; i++) {
        needleHash = (needleHash * BASE + needle.charAt(i)) % MOD;
        hayHash = (hayHash * BASE + haystack.charAt(i)) % MOD;
        if (i < m - 1) power = (power * BASE) % MOD;
    }
    for (int i = 0; i <= n - m; i++) {
        if (hayHash == needleHash && haystack.substring(i, i + m).equals(needle)) {
            return i;
        }
        if (i < n - m) {
            hayHash = ((hayHash - haystack.charAt(i) * power) % MOD + MOD) % MOD;
            hayHash = (hayHash * BASE + haystack.charAt(i + m)) % MOD;
        }
    }
    return -1;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "haystack = \"hello\", needle = \"ll\"", "m = 2. Compute needleHash for \"ll\" and initial hayHash for \"he\"."),
            DrillTraceStep(2, "i = 0: \"he\" != \"ll\"", "Slide hash: subtract 'h'*power, multiply by BASE, add 'l'."),
            DrillTraceStep(3, "i = 1: \"el\" != \"ll\"", "Slide hash: subtract 'e'*power, multiply by BASE, add 'l'."),
            DrillTraceStep(4, "i = 2: \"ll\" == needleHash", "Exact substring check matches needle. Return index 2!")
        ),
        quizQuestion = DrillQuiz(
            question = "Why is `haystack.substring(i, i + m).equals(needle)` still checked when hashes match?",
            options = listOf(
                "Because hash collisions can occur when two different strings produce the same hash modulo MOD",
                "Because hash equality is random",
                "Because strings are immutable",
                "It is unnecessary"
            ),
            correctOptionIndex = 0,
            explanation = "Because MOD is finite, hash collisions are mathematically possible (pigeonhole principle). Verifying equality on hash match guarantees 100% accuracy."
        )
    ),
    // Lesson 22
    TrainingDrill(
        id = "str_drill_22",
        topicId = "strings",
        lessonNumber = 22,
        title = "Expand Around Center (Longest Palindrome)",
        subtitle = "Testing 2N-1 potential symmetry axes in O(1) auxiliary space",
        conceptDelta = "+1 Concept: Every palindrome expands around either a single-char center (odd) or a two-char center (even)",
        intuition = "Dynamic programming for Longest Palindromic Substring takes O(N²) space. By expanding around centers, we need zero extra space! There are exactly `2N - 1` centers: N single-character centers (odd length like \"aba\") and N-1 between-character centers (even length like \"abba\"). Expand both for every index `i`.",
        codePattern = """// Lesson 22: Expand Around Center
public String longestPalindrome(String s) {
    if (s == null || s.length() < 1) return "";
    int start = 0, end = 0;
    for (int i = 0; i < s.length(); i++) {
        int len1 = expand(s, i, i);     // Odd-length centers ("aba")
        int len2 = expand(s, i, i + 1); // Even-length centers ("abba")
        int len = Math.max(len1, len2);
        if (len > end - start) {
            start = i - (len - 1) / 2;
            end = i + len / 2;
        }
    }
    return s.substring(start, end + 1);
}
private int expand(String s, int l, int r) {
    while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
        l--; r++;
    }
    return r - l - 1;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "s = \"babad\"", "Center i = 0 ('b'): max len = 1."),
            DrillTraceStep(2, "Center i = 1 ('a')", "expand(1, 1): 'b' == 'b' -> \"bab\" (len 3). expand(1, 2): 'a' != 'b' (len 0). Best = 3."),
            DrillTraceStep(3, "Center i = 2 ('b')", "expand(2, 2): 'a' == 'a' -> \"aba\" (len 3)."),
            DrillTraceStep(4, "Result: \"bab\" (or \"aba\")", "Found in O(N²) time and strictly O(1) space.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why are there `2N - 1` centers instead of just N?",
            options = listOf(
                "Because an even-length palindrome (e.g. \"abba\") centers between two adjacent characters rather than on a single character",
                "Because strings have 2N characters",
                "Because of null terminators",
                "Because centers must be integers"
            ),
            correctOptionIndex = 0,
            explanation = "N centers exist on characters (odd palindromes), and N-1 centers exist between adjacent character pairs (even palindromes), totaling 2N - 1."
        )
    ),
    // Lesson 23
    TrainingDrill(
        id = "str_drill_23",
        topicId = "strings",
        lessonNumber = 23,
        title = "Count Palindromic Substrings",
        subtitle = "Aggregating all valid symmetrical expansions into an aggregate count",
        conceptDelta = "+1 Concept: Every successful expansion step `s[l] == s[r]` corresponds to exactly 1 valid palindromic substring",
        intuition = "In Lesson 22 we tracked the max length. To count the TOTAL number of palindromic substrings, notice that whenever `s[l] == s[r]` during outward expansion from center, that pair forms a distinct valid palindrome! Increment a global count for every valid step.",
        codePattern = """// Lesson 23: Counting Palindrome Expansions
public int countSubstrings(String s) {
    int totalCount = 0;
    for (int i = 0; i < s.length(); i++) {
        totalCount += countAroundCenter(s, i, i);     // Odd centers
        totalCount += countAroundCenter(s, i, i + 1); // Even centers
    }
    return totalCount;
}
private int countAroundCenter(String s, int l, int r) {
    int count = 0;
    while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
        count++;
        l--;
        r++;
    }
    return count;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "s = \"aaa\"", "i = 0: odd center (1, \"a\"), even center (1, \"aa\")."),
            DrillTraceStep(2, "i = 1", "odd center (2: \"a\", \"aaa\"), even center (1, \"aa\")."),
            DrillTraceStep(3, "i = 2", "odd center (1, \"a\"), even center (0)."),
            DrillTraceStep(4, "Total = 1 + 1 + 2 + 1 + 1 = 6", "All 6 palindromes counted in O(N²) time and O(1) space.")
        ),
        quizQuestion = DrillQuiz(
            question = "For a string with all identical characters like \"aaaa\", how many total palindromic substrings exist?",
            options = listOf(
                "N * (N + 1) / 2 = 10",
                "4",
                "16",
                "2^N"
            ),
            correctOptionIndex = 0,
            explanation = "When all characters are identical, every single substring is a palindrome. The total number of substrings for length N is N * (N + 1) / 2."
        )
    ),
    // Lesson 24
    TrainingDrill(
        id = "str_drill_24",
        topicId = "strings",
        lessonNumber = 24,
        title = "Canonical Signature Grouping",
        subtitle = "Clustering anagrams using deterministic character frequency signatures",
        conceptDelta = "+1 Concept: Canonical key generation: converting int[26] frequency array into a delimiter-separated string key",
        intuition = "To group anagrams (\"eat\", \"tea\", \"ate\"), sorting each string takes O(K log K). For long strings, an O(K) signature is faster: count characters in an `int[26]` array and serialize to a string like `#1#0#0#0#1...#1`. All anagrams generate the identical canonical key!",
        codePattern = """// Lesson 24: Canonical Key Grouping
public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> map = new HashMap<>();
    for (String s : strs) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) count[c - 'a']++;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            sb.append('#').append(count[i]);
        }
        String key = sb.toString();
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
    }
    return new ArrayList<>(map.values());
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "strs = [\"eat\", \"tea\", \"tan\", \"ate\", \"nat\", \"bat\"]", "Map empty."),
            DrillTraceStep(2, "\"eat\"", "Count has 1 'a', 1 'e', 1 't'. Key: \"#1#0#0#0#1...#1\". Add \"eat\"."),
            DrillTraceStep(3, "\"tea\" and \"ate\"", "Generate the identical key! Grouped into [\"eat\", \"tea\", \"ate\"]."),
            DrillTraceStep(4, "Result: 3 groups", "[[\"eat\",\"tea\",\"ate\"], [\"tan\",\"nat\"], [\"bat\"]] in O(N * K) time.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why is a delimiter like '#' necessary between counts when serializing the key?",
            options = listOf(
                "To distinguish count 1 followed by 0 from count 10 (e.g. \"10\" could mean 1 'a' and 0 'b', or 10 'a's)",
                "Because Java maps require symbols",
                "To sort the keys",
                "It is optional"
            ),
            correctOptionIndex = 0,
            explanation = "Without delimiters, [1, 0] and [10] both serialize to \"10\", causing hash collisions between completely different frequency distributions."
        )
    ),
    // Lesson 25
    TrainingDrill(
        id = "str_drill_25",
        topicId = "strings",
        lessonNumber = 25,
        title = "Grade-School Multiplication Lattice",
        subtitle = "Multiplying arbitrary-precision numeric strings in an int[m + n] accumulator",
        conceptDelta = "+1 Concept: `num1[i] * num2[j]` contributes strictly to position `pos[i + j]` and `pos[i + j + 1]`",
        intuition = "Multiplying two 200-digit numbers overflows all primitive integer and long types. Grade-school arithmetic shows that multiplying digit `num1[i]` with `num2[j]` lands exactly at indices `i + j` (carry) and `i + j + 1` (remainder) in an array of size `m + n`. Process right to left!",
        codePattern = """// Lesson 25: Column-by-Column Multiplier
public String multiply(String num1, String num2) {
    if (num1.equals("0") || num2.equals("0")) return "0";
    int m = num1.length(), n = num2.length();
    int[] pos = new int[m + n];
    
    for (int i = m - 1; i >= 0; i--) {
        for (int j = n - 1; j >= 0; j--) {
            int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
            int p1 = i + j, p2 = i + j + 1;
            int sum = mul + pos[p2];
            
            pos[p2] = sum % 10;
            pos[p1] += sum / 10; // Carry over
        }
    }
    StringBuilder sb = new StringBuilder();
    for (int p : pos) {
        if (!(sb.length() == 0 && p == 0)) sb.append(p); // Skip leading zeros
    }
    return sb.toString();
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "num1 = \"12\", num2 = \"34\"", "pos array size = 2 + 2 = 4: [0, 0, 0, 0]."),
            DrillTraceStep(2, "Multiply 2 * 4 = 8", "pos[2+1=3] = 8. pos = [0, 0, 0, 8]."),
            DrillTraceStep(3, "Multiply 1 * 4 = 4", "pos[1+1=2] += 4. pos = [0, 0, 4, 8]."),
            DrillTraceStep(4, "All cross products accumulated", "pos array becomes [0, 4, 0, 8] -> \"408\"."),
            DrillTraceStep(5, "Result: \"408\"", "Computed in O(M * N) without BigInteger.")
        ),
        quizQuestion = DrillQuiz(
            question = "What is the maximum number of digits the product of an M-digit number and an N-digit number can have?",
            options = listOf(
                "M + N",
                "M * N",
                "M + N - 1 only",
                "2^(M + N)"
            ),
            correctOptionIndex = 0,
            explanation = "For any two positive integers with M and N digits, their product has either M + N - 1 or M + N digits. An array of size M + N is guaranteed to fit the result."
        )
    ),
    // Lesson 26
    TrainingDrill(
        id = "str_drill_26",
        topicId = "strings",
        lessonNumber = 26,
        title = "KMP Failure Function (The LPS Array)",
        subtitle = "Computing the Longest Prefix which is also a Suffix for O(N) substring search",
        conceptDelta = "+1 Concept: LPS table: `lps[i]` is length of longest proper prefix of s[0..i] that is also a suffix",
        intuition = "When a character mismatch occurs during substring search, naive search rewinds all the way back. The Knuth-Morris-Pratt (KMP) algorithm never rewinds the text pointer! Instead, the LPS table tells us the longest prefix that matches the current suffix, jumping the pattern pointer directly to `lps[prev]`.",
        codePattern = """// Lesson 26: Building KMP LPS Array
public int[] computeLPS(String pattern) {
    int m = pattern.length();
    int[] lps = new int[m];
    int len = 0, i = 1;
    
    while (i < m) {
        if (pattern.charAt(i) == pattern.charAt(len)) {
            len++;
            lps[i] = len;
            i++;
        } else {
            if (len != 0) {
                len = lps[len - 1]; // Fallback to prior prefix
            } else {
                lps[i] = 0;
                i++;
            }
        }
    }
    return lps;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "pattern = \"ABABC\"", "lps[0] = 0, len = 0, i = 1."),
            DrillTraceStep(2, "i = 1 ('B') != pattern[len=0] ('A')", "len == 0 -> lps[1] = 0, i = 2."),
            DrillTraceStep(3, "i = 2 ('A') == pattern[len=0] ('A')", "len = 1, lps[2] = 1, i = 3."),
            DrillTraceStep(4, "i = 3 ('B') == pattern[len=1] ('B')", "len = 2, lps[3] = 2, i = 4."),
            DrillTraceStep(5, "i = 4 ('C') != pattern[len=2] ('A')", "len falls back to lps[1] = 0. lps[4] = 0. LPS = [0, 0, 1, 2, 0].")
        ),
        quizQuestion = DrillQuiz(
            question = "When `pattern.charAt(i) != pattern.charAt(len)` and `len != 0`, why does `len` jump to `lps[len - 1]` instead of resetting to 0?",
            options = listOf(
                "Because a shorter sub-prefix within the already matched prefix might still match the suffix up to index i",
                "Because of 1-based indexing",
                "To avoid negative numbers",
                "It is an arbitrary fallback"
            ),
            correctOptionIndex = 0,
            explanation = "The previously computed LPS values tell us the next longest viable prefix without re-examining matched characters."
        )
    ),
    // Lesson 27
    TrainingDrill(
        id = "str_drill_27",
        topicId = "strings",
        lessonNumber = 27,
        title = "Trie Node Architecture & Prefix Lookups",
        subtitle = "Building a 26-ary prefix tree for instant O(L) dictionary queries",
        conceptDelta = "+1 Concept: TrieNode with `children = new TrieNode[26]` and boolean `isWord`",
        intuition = "Searching a list of 100,000 words takes O(N * L). A Trie organizes words into an alphabet tree where common prefixes share the same path. Searching for any word or prefix of length L takes strictly O(L) operations, completely independent of how many millions of words exist in the dictionary!",
        codePattern = """// Lesson 27: Trie Prefix Tree
class Trie {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isWord = false;
    }
    private TrieNode root = new TrieNode();
    
    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) node.children[idx] = new TrieNode();
            node = node.children[idx];
        }
        node.isWord = true;
    }
    
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) return false;
            node = node.children[idx];
        }
        return true;
    }
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "Insert \"apple\"", "Creates path root -> 'a' -> 'p' -> 'p' -> 'l' -> 'e'. Marks 'e'.isWord = true."),
            DrillTraceStep(2, "Insert \"app\"", "Traverses existing 'a' -> 'p' -> 'p'. Marks 'p'.isWord = true. Zero duplicate nodes!"),
            DrillTraceStep(3, "Query startsWith(\"app\")", "Follows path root -> a -> p -> p. All nodes exist -> returns true in 3 steps!")
        ),
        quizQuestion = DrillQuiz(
            question = "What is the time complexity of searching for a word of length L in a Trie containing 1,000,000 words?",
            options = listOf(
                "O(L)",
                "O(1,000,000 * L)",
                "O(log 1,000,000)",
                "O(26^L)"
            ),
            correctOptionIndex = 0,
            explanation = "At each character, the Trie follows a single array pointer children[c - 'a'] in O(1). For a word of length L, it takes exactly L pointer lookups."
        )
    ),
    // Lesson 28
    TrainingDrill(
        id = "str_drill_28",
        topicId = "strings",
        lessonNumber = 28,
        title = "Trie-Pruned 2D Grid Backtracking",
        subtitle = "Searching a Boggle board in parallel using Trie prefix pruning",
        conceptDelta = "+1 Concept: If current grid path prefix is absent in Trie (`node.children[c] == null`), abort DFS immediately",
        intuition = "In Word Search II, searching 10,000 words individually on a grid runs 10,000 DFS traversals (TLE). By building a Trie of all words and traversing the grid once, the Trie guides the DFS: if the current letter isn't in `node.children`, prune the branch instantly! Pruning eliminates 99.9% of dead paths.",
        codePattern = """// Lesson 28: Trie-Directed Grid DFS
public List<String> findWords(char[][] board, String[] words) {
    TrieNode root = buildTrie(words);
    List<String> result = new ArrayList<>();
    for (int r = 0; r < board.length; r++) {
        for (int c = 0; c < board[0].length; c++) {
            dfs(board, r, c, root, result);
        }
    }
    return result;
}
private void dfs(char[][] board, int r, int c, TrieNode node, List<String> res) {
    char ch = board[r][c];
    if (ch == '#' || node.children[ch - 'a'] == null) return; // Pruned!
    node = node.children[ch - 'a'];
    if (node.word != null) {
        res.add(node.word);
        node.word = null; // De-duplicate found word
    }
    board[r][c] = '#'; // Mark visited
    int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    for (int i = 0; i < 4; i++) {
        int nr = r + dr[i], nc = c + dc[i];
        if (nr >= 0 && nr < board.length && nc >= 0 && nc < board[0].length) {
            dfs(board, nr, nc, node, res);
        }
    }
    board[r][c] = ch; // Backtrack
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "Board containing 'o', 'a', 'a', 'n', words = [\"oath\", \"pea\"]", "Build Trie with \"oath\" and \"pea\"."),
            DrillTraceStep(2, "Start DFS at board[0][0]='o'", "Trie has child 'o'. Advance node to 'o'."),
            DrillTraceStep(3, "Step to neighbor 'a'", "Trie has child 'a'. Advance node to 'a'."),
            DrillTraceStep(4, "Step to neighbor 'z'", "Trie has NO child 'z' -> aborts branch immediately! Zero wasted exploration."),
            DrillTraceStep(5, "Finds \"oath\"", "Added to results in record time.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why do we set `node.word = null` after adding a found word to `res`?",
            options = listOf(
                "To prevent the same word from being added multiple times if found via another grid path",
                "To free RAM",
                "Because Trie nodes can only hold one word",
                "To stop the recursion"
            ),
            correctOptionIndex = 0,
            explanation = "A word might appear in the grid along multiple paths. Setting node.word to null prevents duplicates without needing a HashSet."
        )
    ),
    // Lesson 29
    TrainingDrill(
        id = "str_drill_29",
        topicId = "strings",
        lessonNumber = 29,
        title = "Infrequent Character Partitioning (Divide & Conquer)",
        subtitle = "Splitting invalid strings on characters that can never belong to any valid substring",
        conceptDelta = "+1 Concept: If character C appears < K times in entire string, NO valid substring can contain C; split on C",
        intuition = "To find the longest substring where every character repeats at least K times: count frequencies in `s`. If character 'z' appears only twice (and K=3), 'z' CANNOT appear in ANY valid substring! It acts as a wall. Split `s` around every invalid character and solve recursively for each partition.",
        codePattern = """// Lesson 29: Divide & Conquer Substring Split
public int longestSubstring(String s, int k) {
    if (s == null || s.length() < k) return 0;
    int[] count = new int[26];
    for (char c : s.toCharArray()) count[c - 'a']++;
    
    for (int i = 0; i < s.length(); i++) {
        if (count[s.charAt(i) - 'a'] < k) {
            // Character is a disqualifying divider!
            int maxLen = 0;
            for (String sub : s.split(String.valueOf(s.charAt(i)))) {
                maxLen = Math.max(maxLen, longestSubstring(sub, k));
            }
            return maxLen;
        }
    }
    return s.length(); // All characters meet the >= k threshold
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "s = \"ababbc\", k = 2", "Frequencies: 'a':2, 'b':3, 'c':1. 'c' has count 1 < 2!"),
            DrillTraceStep(2, "'c' is a barrier", "Split around 'c': partitions into [\"ababb\"]."),
            DrillTraceStep(3, "Recursion on \"ababb\"", "Frequencies: 'a':2, 'b':3. All characters >= 2! Returns length 5."),
            DrillTraceStep(4, "Result: 5", "\"ababb\" is the longest valid substring.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why is it mathematically impossible for any character with total count < K to be part of the answer?",
            options = listOf(
                "Because a substring can only contain a subset of the full string's characters, so its count in any substring can never exceed its total count",
                "Because K is a prime number",
                "Because the string is sorted",
                "Because split removes all characters"
            ),
            correctOptionIndex = 0,
            explanation = "If character C appears only M times in the entire string where M < K, no substring can possibly contain K occurrences of C."
        )
    ),
    // Lesson 30
    TrainingDrill(
        id = "str_drill_30",
        topicId = "strings",
        lessonNumber = 30,
        title = "Alternating Parity Interleaving",
        subtitle = "Placing most frequent characters at even indices first to prevent adjacent duplicates",
        conceptDelta = "+1 Concept: If `maxCount > (N + 1) / 2`, impossible. Otherwise, fill even indices (0, 2, 4...) then odd indices",
        intuition = "To rearrange a string so no two identical characters are adjacent (\"aab\" -> \"aba\"): find the most frequent character. If its count > `(n + 1) / 2`, pigeonhole principle proves adjacent collision is unavoidable (return \"\"). Otherwise, place characters into even indices first (0, 2, 4...), then wrap around to odd indices (1, 3, 5...). Spacing guarantees zero adjacency!",
        codePattern = """// Lesson 30: Alternating Parity Interleaving
public String reorganizeString(String s) {
    int[] count = new int[26];
    for (char c : s.toCharArray()) count[c - 'a']++;
    int maxCount = 0, letter = 0;
    for (int i = 0; i < 26; i++) {
        if (count[i] > maxCount) {
            maxCount = count[i];
            letter = i;
        }
    }
    if (maxCount > (s.length() + 1) / 2) return ""; // Pigeonhole violation
    
    char[] res = new char[s.length()];
    int idx = 0;
    // Place dominant character at even indices first
    while (count[letter] > 0) {
        res[idx] = (char) ('a' + letter);
        idx += 2;
        count[letter]--;
    }
    // Place remaining characters
    for (int i = 0; i < 26; i++) {
        while (count[i] > 0) {
            if (idx >= res.length) idx = 1; // Wrap around to odd indices
            res[idx] = (char) ('a' + i);
            idx += 2;
            count[i]--;
        }
    }
    return new String(res);
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "s = \"aaabbc\", n = 6", "'a':3, 'b':2, 'c':1. maxCount = 3 <= (6 + 1)/2 = 3. Valid!"),
            DrillTraceStep(2, "Fill 'a' at even indices", "res[0]='a', res[2]='a', res[4]='a'. Array: ['a', _, 'a', _, 'a', _]."),
            DrillTraceStep(3, "Wrap to odd index 1 for remaining chars", "res[1]='b', res[3]='b', res[5]='c'."),
            DrillTraceStep(4, "Result: \"ababca\"", "No two identical characters adjacent in O(N) time and O(1) extra space.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why does filling even indices first guarantee that the most frequent character never touches itself?",
            options = listOf(
                "Because even indices (0, 2, 4...) have at least one intervening odd index between them",
                "Because even indices are powers of two",
                "Because odd indices are ignored",
                "It only works for strings of even length"
            ),
            correctOptionIndex = 0,
            explanation = "Stepping by 2 guarantees that every placement is separated by an untouched index, isolating identical characters safely."
        )
    )
)
