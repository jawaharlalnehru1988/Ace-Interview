package com.example.data.local.dsa

import com.example.domain.model.DrillQuiz
import com.example.domain.model.DrillTraceStep
import com.example.domain.model.TrainingDrill

val linkedListTrainingDrills = listOf(
    // Lesson 1
    TrainingDrill(
        id = "ll_drill_01",
        topicId = "linked_list",
        lessonNumber = 1,
        title = "Safe Pointer Marching & Null Guards",
        subtitle = "Navigating node references without NullPointerExceptions",
        conceptDelta = "Baseline: Traversing node = node.next with strict while (curr != null) boundary",
        intuition = "In arrays, `i++` never crashes until `i >= length`. In linked lists, accessing `curr.next` when `curr == null` causes instant NPE. Always guard before advancing.",
        codePattern = """// Lesson 1: Safe Traversal
public int getLength(ListNode head) {
    int count = 0;
    ListNode curr = head;
    while (curr != null) {
        count++;
        curr = curr.next;
    }
    return count;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "head = [1 -> 2 -> null]", "curr = 1, count = 0."),
            DrillTraceStep(2, "curr != null", "count = 1, curr moves to node 2."),
            DrillTraceStep(3, "curr != null", "count = 2, curr moves to null."),
            DrillTraceStep(4, "curr == null", "Loop terminates cleanly. Returns 2.")
        ),
        quizQuestion = DrillQuiz(
            question = "What is the difference between `while (curr != null)` and `while (curr.next != null)`?",
            options = listOf(
                "They are identical",
                "`while (curr != null)` visits every node including the last; `while (curr.next != null)` stops AT the last node and crashes if head is null",
                "`while (curr.next != null)` is always faster",
                "`while (curr != null)` leaks memory"
            ),
            correctOptionIndex = 1,
            explanation = "Use `curr != null` to inspect all elements. Use `curr.next != null` only when you need to stop at the tail node to append something."
        )
    ),
    // Lesson 2
    TrainingDrill(
        id = "ll_drill_02",
        topicId = "linked_list",
        lessonNumber = 2,
        title = "The 3-Pointer Reversal Slide",
        subtitle = "Inverting pointer direction without losing the remaining list",
        conceptDelta = "+1 Concept: Preserving next node with nextTemp before severing curr.next",
        intuition = "If you change `curr.next = prev`, you immediately sever your link to the rest of the list! To prevent orphan references, use 3 pointers: (1) save `nextTemp = curr.next`, (2) reverse `curr.next = prev`, (3) slide `prev = curr; curr = nextTemp;`.",
        codePattern = """// Lesson 2: The 3-Pointer Pivot
public ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;
    while (curr != null) {
        ListNode nextTemp = curr.next; // Save forward link
        curr.next = prev;              // Invert pointer
        prev = curr;                   // Step prev forward
        curr = nextTemp;               // Step curr forward
    }
    return prev; // New head of reversed list
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "List: 1 -> 2 -> 3 -> null", "prev = null, curr = 1."),
            DrillTraceStep(2, "Iter 1: nextTemp = 2", "1.next points to null (prev). prev = 1, curr = 2. Sublist: null <- 1."),
            DrillTraceStep(3, "Iter 2: nextTemp = 3", "2.next points to 1 (prev). prev = 2, curr = 3. Sublist: null <- 1 <- 2."),
            DrillTraceStep(4, "Iter 3: nextTemp = null", "3.next points to 2 (prev). prev = 3, curr = null. Sublist: null <- 1 <- 2 <- 3."),
            DrillTraceStep(5, "curr == null", "Returns prev (node 3) as the new head.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why does `prev` become the new head instead of `curr` when the loop finishes?",
            options = listOf(
                "Because prev was declared first",
                "Because curr has walked off the end into null, whereas prev sits on the final valid node",
                "Because Java returns variables alphabetically",
                "curr points to the dummy node"
            ),
            correctOptionIndex = 1,
            explanation = "When curr becomes null, prev holds the last non-null node, which is now the first node of the reversed list."
        )
    ),
    // Lesson 3
    TrainingDrill(
        id = "ll_drill_03",
        topicId = "linked_list",
        lessonNumber = 3,
        title = "Fast & Slow Velocity Differential",
        subtitle = "Tortoise and Hare pointers for cycle detection and midpoint discovery",
        conceptDelta = "+1 Concept: Two pointers moving at different speeds (slow advances by 1, fast advances by 2)",
        intuition = "Without array indexing, how do you find the exact middle of a list in 1 pass? Launch two pointers: `slow` moves 1 step per beat, `fast` moves 2 steps per beat. When `fast` hits the end, `slow` is mathematically at the midpoint!",
        codePattern = """// Lesson 3: Runner Velocity Differential
public ListNode middleNode(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    return slow;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "List: [1 -> 2 -> 3 -> 4 -> 5]", "slow = 1, fast = 1."),
            DrillTraceStep(2, "Beat 1", "slow = 2, fast = 3."),
            DrillTraceStep(3, "Beat 2", "slow = 3, fast = 5."),
            DrillTraceStep(4, "fast.next is null", "Loop exits. slow = 3 is precisely the middle element in 1 pass!")
        ),
        quizQuestion = DrillQuiz(
            question = "In Floyd's cycle detection, if a cycle exists, why is `fast` guaranteed to meet `slow`?",
            options = listOf(
                "Because both pointers share the same memory address",
                "Because the distance between fast and slow decreases by exactly 1 node per iteration inside the cycle",
                "Because fast will wrap around to index 0",
                "It is not guaranteed and can skip over slow"
            ),
            correctOptionIndex = 1,
            explanation = "Relative velocity: relative to slow, fast closes the gap by (2 - 1) = 1 node every step, making it impossible to jump over slow."
        )
    ),
    // Lesson 4
    TrainingDrill(
        id = "ll_drill_04",
        topicId = "linked_list",
        lessonNumber = 4,
        title = "The Sentinel Dummy Head",
        subtitle = "Eliminating tedious null checks and special-casing the first node",
        conceptDelta = "+1 Concept: Dummy node prepended to head so every actual node has a predecessor",
        intuition = "Whenever you delete or merge nodes, modifying `head` usually requires separate `if (head == target)` code. By creating `dummy = new ListNode(0); dummy.next = head;`, EVERY node in the list now has a predecessor `prev`, completely removing edge-case branches.",
        codePattern = """// Lesson 4: Dummy Sentinel Pattern
public ListNode removeElements(ListNode head, int val) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode curr = dummy;
    while (curr.next != null) {
        if (curr.next.val == val) {
            curr.next = curr.next.next; // Bypass deleted node
        } else {
            curr = curr.next;
        }
    }
    return dummy.next;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "List: [7 -> 7 -> 2], val = 7", "dummy.next = head (7). curr = dummy."),
            DrillTraceStep(2, "curr.next.val == 7", "Bypass! dummy.next becomes node 7(second). curr stays at dummy."),
            DrillTraceStep(3, "curr.next.val == 7", "Bypass! dummy.next becomes node 2. curr stays at dummy."),
            DrillTraceStep(4, "curr.next.val == 2 (valid)", "curr advances to node 2. curr.next is null."),
            DrillTraceStep(5, "Return dummy.next", "Returns node 2. Both leading 7s removed without any special head-null checks!")
        ),
        quizQuestion = DrillQuiz(
            question = "Why do we return `dummy.next` instead of `head` at the end?",
            options = listOf(
                "Because dummy holds the reversed list",
                "Because the original `head` might have been deleted, whereas `dummy.next` always points to the true current head",
                "To free memory allocated for dummy",
                "Java requires returning .next"
            ),
            correctOptionIndex = 1,
            explanation = "If the original head was deleted, the 'head' variable still references the deleted node, but dummy.next references the updated true starting node."
        )
    ),
    // Lesson 5
    TrainingDrill(
        id = "ll_drill_05",
        topicId = "linked_list",
        lessonNumber = 5,
        title = "N-Step Lead Runner (Remove Nth From End)",
        subtitle = "Maintaining an N-node offset between two pointers to locate the Nth node from the tail",
        conceptDelta = "+1 Concept: Launch fast pointer N+1 steps ahead; when fast hits null, slow sits immediately before target",
        intuition = "To remove the Nth node from the end in ONE pass: advance `fast` pointer N + 1 steps ahead of `slow` (starting at dummy). Then march both at the exact same speed! When `fast` walks off the end into null, `slow` is positioned precisely at the node BEFORE the one to be deleted (`slow.next = slow.next.next`).",
        codePattern = """// Lesson 5: N-Step Lead Offset
public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode fast = dummy, slow = dummy;
    
    // Create N + 1 separation gap
    for (int i = 0; i <= n; i++) {
        fast = fast.next;
    }
    // Slide gap until fast hits null
    while (fast != null) {
        fast = fast.next;
        slow = slow.next;
    }
    slow.next = slow.next.next; // Bypass Nth node
    return dummy.next;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "List: [1 -> 2 -> 3 -> 4 -> 5], n = 2", "dummy -> 1. Advance fast 2+1=3 steps. fast points to 3. slow points to dummy."),
            DrillTraceStep(2, "Slide both pointers forward", "fast: 3 -> 4 -> 5 -> null. slow: dummy -> 1 -> 2 -> 3."),
            DrillTraceStep(3, "fast is null", "slow is at node 3. Target to remove is node 4 (2nd from end)."),
            DrillTraceStep(4, "Bypass: slow.next = slow.next.next", "3.next becomes 5. Node 4 removed in 1 pass!")
        ),
        quizQuestion = DrillQuiz(
            question = "Why do we advance `fast` by `N + 1` steps rather than `N` steps?",
            options = listOf(
                "Because starting at dummy and moving N+1 steps places slow at the predecessor node, allowing simple deletion `slow.next = slow.next.next`",
                "Because arrays are 0-indexed",
                "To account for garbage collection",
                "It is a compensatory offset for null"
            ),
            correctOptionIndex = 0,
            explanation = "To delete a node in a singly linked list, you must stop at its predecessor node. An N+1 offset stops slow exactly one node before the target."
        )
    ),
    // Lesson 6
    TrainingDrill(
        id = "ll_drill_06",
        topicId = "linked_list",
        lessonNumber = 6,
        title = "Dual List Splicing (Merge Two Sorted Lists)",
        subtitle = "Interweaving nodes from two sorted chains into a single sorted list",
        conceptDelta = "+1 Concept: `tail.next = (l1.val <= l2.val) ? l1 : l2; tail = tail.next;` + O(1) remainder append",
        intuition = "Merging two sorted linked lists is even easier than arrays because no elements are shifted! Simply compare `l1.val` and `l2.val`, link `tail.next` to the smaller node, and advance that list's pointer. When one list runs out, append the entire remainder of the other list in a single O(1) pointer assignment!",
        codePattern = """// Lesson 6: Linear List Splicing
public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode tail = dummy;
    while (l1 != null && l2 != null) {
        if (l1.val <= l2.val) {
            tail.next = l1;
            l1 = l1.next;
        } else {
            tail.next = l2;
            l2 = l2.next;
        }
        tail = tail.next;
    }
    tail.next = (l1 != null) ? l1 : l2; // O(1) remainder splice!
    return dummy.next;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "l1 = [1, 2, 4], l2 = [1, 3, 4]", "dummy -> tail. l1.val <= l2.val (1 <= 1)."),
            DrillTraceStep(2, "tail.next = l1(1)", "tail = 1, l1 moves to 2."),
            DrillTraceStep(3, "tail.next = l2(1)", "tail = 1, l2 moves to 3."),
            DrillTraceStep(4, "Splicing continues", "tail stitches [1, 1, 2, 3, 4, 4] with zero memory allocations.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why does `tail.next = (l1 != null) ? l1 : l2;` take only O(1) time instead of looping?",
            options = listOf(
                "Because the remaining nodes are already linked together in their own sorted list; only the single head reference needs to be attached",
                "Because Java handles it concurrently",
                "Because linked lists have pointers to all elements",
                "Because l1 and l2 have the same length"
            ),
            correctOptionIndex = 0,
            explanation = "Unlike arrays where remaining elements must be copied one-by-one into a new buffer, linked list nodes are already chained. Splicing takes 1 pointer update."
        )
    ),
    // Lesson 7
    TrainingDrill(
        id = "ll_drill_07",
        topicId = "linked_list",
        lessonNumber = 7,
        title = "Symmetrical Path Equalization (List Intersection)",
        subtitle = "Switching heads upon reaching null to equalize traversal distance",
        conceptDelta = "+1 Concept: `pA = (pA == null) ? headB : pA.next` (Path A + Path B == Path B + Path A)",
        intuition = "If two lists intersect, list A has length `a + c` and list B has length `b + c`. If pointer A walks path A then switches to head B, it travels `(a + c) + (b + c)`. If pointer B walks path B then switches to head A, it travels `(b + c) + (a + c)`. The total distance is IDENTICAL! They are guaranteed to collide at the intersection node on pass 2!",
        codePattern = """// Lesson 7: Path Length Equalization
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) return null;
    ListNode pA = headA, pB = headB;
    while (pA != pB) {
        pA = (pA == null) ? headB : pA.next; // Switch tracks at end
        pB = (pB == null) ? headA : pB.next;
    }
    return pA; // Either intersection node or null (if no intersection)
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "List A has length 5, List B has length 6, shared tail 2", "pA and pB advance simultaneously."),
            DrillTraceStep(2, "pA hits null first (after 5 steps)", "pA switches to headB."),
            DrillTraceStep(3, "pB hits null next (after 6 steps)", "pB switches to headA."),
            DrillTraceStep(4, "Both pointers now have identical remaining distance", "They meet precisely at the intersection node!")
        ),
        quizQuestion = DrillQuiz(
            question = "If the two linked lists do NOT intersect, what happens?",
            options = listOf(
                "Both pointers reach null simultaneously at the end of their second passes and the loop exits with null",
                "The loop runs infinitely",
                "A NullPointerException is thrown",
                "It returns headA"
            ),
            correctOptionIndex = 0,
            explanation = "After traversing lenA + lenB nodes each without finding an intersection, both pointers reach null at the exact same step, satisfying pA == pB (null == null)."
        )
    ),
    // Lesson 8
    TrainingDrill(
        id = "ll_drill_08",
        topicId = "linked_list",
        lessonNumber = 8,
        title = "Midpoint Split + Inversion (Palindrome List)",
        subtitle = "Reversing the second half in-place to compare against the first half in O(1) space",
        conceptDelta = "+1 Concept: Find middle with runner, reverse second half, compare heads, restore list",
        intuition = "Checking if a singly linked list is a palindrome without O(N) extra space: (1) Use fast/slow pointers to find the middle. (2) Reverse the second half using our 3-pointer slide. (3) Compare the first half and the reversed second half node-by-node. (4) Re-reverse the second half to leave the input untainted.",
        codePattern = """// Lesson 8: O(1) Space Palindrome List
public boolean isPalindrome(ListNode head) {
    if (head == null || head.next == null) return true;
    // 1. Find midpoint
    ListNode slow = head, fast = head;
    while (fast.next != null && fast.next.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    // 2. Reverse second half
    ListNode secondHalf = reverse(slow.next);
    // 3. Compare first and second halves
    ListNode p1 = head, p2 = secondHalf;
    boolean isPal = true;
    while (p2 != null) {
        if (p1.val != p2.val) { isPal = false; break; }
        p1 = p1.next;
        p2 = p2.next;
    }
    // 4. Restore original list
    slow.next = reverse(secondHalf);
    return isPal;
}
private ListNode reverse(ListNode head) {
    ListNode prev = null, curr = head;
    while (curr != null) {
        ListNode temp = curr.next;
        curr.next = prev;
        prev = curr;
        curr = temp;
    }
    return prev;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "List: 1 -> 2 -> 2 -> 1", "Fast/slow runner stops slow at first '2'."),
            DrillTraceStep(2, "Reverse second half [2 -> 1]", "Becomes [1 -> 2]. Sublists: [1, 2] and [1, 2]."),
            DrillTraceStep(3, "Compare p1 and p2", "1 == 1, 2 == 2. Matches perfectly!"),
            DrillTraceStep(4, "Re-reverse second half", "Restores input structure. Returns true in O(N) time and O(1) space.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why is re-reversing the second half considered a best practice in professional engineering?",
            options = listOf(
                "Because mutating an input parameter destructively without restoring it violates caller expectations and causes concurrency bugs",
                "Because Java requires it for garbage collection",
                "To make the algorithm run twice as fast",
                "It is optional and never needed"
            ),
            correctOptionIndex = 0,
            explanation = "Methods that query properties (like isPalindrome) should avoid permanent side effects on the caller's data structure."
        )
    ),
    // Lesson 9
    TrainingDrill(
        id = "ll_drill_09",
        topicId = "linked_list",
        lessonNumber = 9,
        title = "Identity Theft Deletion (No Head Access)",
        subtitle = "Overwriting current node value with next node's value and bypassing next",
        conceptDelta = "+1 Concept: `node.val = node.next.val; node.next = node.next.next;`",
        intuition = "How do you delete a node if you are ONLY given a reference to that node itself (no head reference, no access to predecessor)? You can't change the predecessor's pointer! But you can steal your neighbor's identity: copy `node.next.val` into `node.val`, then delete `node.next`!",
        codePattern = """// Lesson 9: Value Substitution Deletion
public void deleteNode(ListNode node) {
    // Copy the next node's value into this node
    node.val = node.next.val;
    // Bypass the next node
    node.next = node.next.next;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "List: 4 -> [5] -> 1 -> 9, delete node 5", "Target node has value 5. node.next has value 1."),
            DrillTraceStep(2, "Copy value: node.val = 1", "List temporarily: 4 -> [1] -> 1 -> 9."),
            DrillTraceStep(3, "Bypass next node: node.next = node.next.next", "List becomes: 4 -> [1] -> 9. Node 5 is completely vanished in O(1) time!")
        ),
        quizQuestion = DrillQuiz(
            question = "Why does this trick fail if the node to delete is the tail node?",
            options = listOf(
                "Because a tail node has `node.next == null`, so there is no next value to copy or bypass",
                "Because tail nodes are immutable",
                "Because tail nodes have negative values",
                "It does not fail for tail nodes"
            ),
            correctOptionIndex = 0,
            explanation = "If node.next is null, accessing node.next.val throws a NullPointerException. Truly deleting a tail requires changing the predecessor's pointer."
        )
    ),
    // Lesson 10
    TrainingDrill(
        id = "ll_drill_10",
        topicId = "linked_list",
        lessonNumber = 10,
        title = "Dual Chain Interleaving (Odd-Even Linked List)",
        subtitle = "Separating odd and even indexed nodes into parallel chains then reconnecting",
        conceptDelta = "+1 Concept: Maintain two advancing pointers `odd` and `even` with `evenHead` saved for the final join",
        intuition = "To group all odd-indexed nodes followed by even-indexed nodes: let `odd` start at node 1 and `even` start at node 2. Save `evenHead = even`. In each step, link `odd.next = even.next`, advance odd, then link `even.next = odd.next`, advance even. Finally, stitch `odd.next = evenHead`!",
        codePattern = """// Lesson 10: Dual Chain Odd-Even Partition
public ListNode oddEvenList(ListNode head) {
    if (head == null) return null;
    ListNode odd = head, even = head.next, evenHead = even;
    while (even != null && even.next != null) {
        odd.next = even.next;
        odd = odd.next;
        even.next = odd.next;
        even = even.next;
    }
    odd.next = evenHead; // Connect odd chain tail to even chain head
    return head;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "List: 1 -> 2 -> 3 -> 4 -> 5", "odd = 1, even = 2, evenHead = 2."),
            DrillTraceStep(2, "Step 1: odd.next = 3, even.next = 4", "odd moves to 3, even moves to 4. Odd list: 1->3, Even list: 2->4."),
            DrillTraceStep(3, "Step 2: odd.next = 5, even.next = null", "odd moves to 5, even moves to null. Loop terminates."),
            DrillTraceStep(4, "odd.next = evenHead (2)", "Result: 1 -> 3 -> 5 -> 2 -> 4 in O(N) time and O(1) space.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why does the while loop condition check `even != null && even.next != null`?",
            options = listOf(
                "Because `even` is always ahead of `odd`, so checking `even` safely guards both pointers from null reference errors",
                "Because odd indices can never be null",
                "Because even numbers are divisible by 2",
                "To prevent integer overflow"
            ),
            correctOptionIndex = 0,
            explanation = "Since the even pointer leads the traversal, ensuring even and even.next are non-null guarantees that advancing odd and even will not throw an NPE."
        )
    ),
    // Lesson 11
    TrainingDrill(
        id = "ll_drill_11",
        topicId = "linked_list",
        lessonNumber = 11,
        title = "Cycle Proof & Relative Convergence",
        subtitle = "Proving why fast and slow pointers must collide if a cycle exists",
        conceptDelta = "+1 Concept: In a cycle of length C, distance between fast and slow decreases by 1 node per iteration",
        intuition = "Why can't `fast` just jump over `slow` forever? Consider the relative speed: relative to `slow`, `fast` is moving at speed `2 - 1 = 1 node per step`. Inside a loop of circumference C, closing the distance by 1 unit every single beat means the distance MUST decrement: d, d-1, ..., 2, 1, 0 (collision)! Collision is inevitable.",
        codePattern = """// Lesson 11: Floyd's Cycle Test
public boolean hasCycle(ListNode head) {
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if (slow == fast) return true; // Mathematical collision!
    }
    return false; // Fast reached terminal null -> No cycle!
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "List with cycle: 3 -> 2 -> 0 -> -4 (points back to 2)", "Cycle length C = 3."),
            DrillTraceStep(2, "Beat 1: slow=2, fast=0", "Distance inside cycle = 2 nodes."),
            DrillTraceStep(3, "Beat 2: slow=0, fast=2", "Distance inside cycle closes to 1 node."),
            DrillTraceStep(4, "Beat 3: slow=-4, fast=-4", "Distance = 0 -> Collision! Returns true.")
        ),
        quizQuestion = DrillQuiz(
            question = "What is the maximum number of steps fast pointer takes inside a cycle of length C before meeting slow?",
            options = listOf(
                "At most C iterations",
                "C² iterations",
                "Infinitely many",
                "2^C iterations"
            ),
            correctOptionIndex = 0,
            explanation = "Because the distance decreases by 1 on every iteration, fast will catch slow in at most C steps after slow enters the cycle."
        )
    ),
    // Lesson 12
    TrainingDrill(
        id = "ll_drill_12",
        topicId = "linked_list",
        lessonNumber = 12,
        title = "Floyd's Cycle Origin Math (Phase 2)",
        subtitle = "Resetting one pointer to head to find the exact entry node of the cycle",
        conceptDelta = "+1 Concept: `Distance(Head -> Entry) == Distance(Meeting Point -> Entry)`",
        intuition = "Mathematical proof: Let distance to cycle entry be `L1`, entry to meeting point be `L2`, and cycle circumference be `C`. When they meet: `2*(L1 + L2) = L1 + L2 + n*C`, which simplifies to `L1 = n*C - L2`. This proves the distance from HEAD to Entry equals the distance from MEETING POINT to Entry! Reset one pointer to head and advance both by 1—they collide at the entry node!",
        codePattern = """// Lesson 12: Cycle Entry Discovery
public ListNode detectCycle(ListNode head) {
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if (slow == fast) {
            // Phase 2: Reset slow to head
            slow = head;
            while (slow != fast) {
                slow = slow.next; // Advance at 1 step
                fast = fast.next; // Advance at 1 step
            }
            return slow; // Collide at cycle entry!
        }
    }
    return null;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "Pointers collide at node -4", "Meeting point confirmed."),
            DrillTraceStep(2, "Reset slow to head (node 3)", "fast remains at node -4."),
            DrillTraceStep(3, "Advance both by 1 step", "slow moves from 3 to 2. fast moves from -4 to 2."),
            DrillTraceStep(4, "slow == fast == node 2!", "Entry node found in O(N) time and O(1) space.")
        ),
        quizQuestion = DrillQuiz(
            question = "In Phase 2, at what speed do the two pointers advance?",
            options = listOf(
                "Both advance at 1 step per beat",
                "One advances at 1 step, the other at 2 steps",
                "One moves backwards",
                "They swap positions"
            ),
            correctOptionIndex = 0,
            explanation = "Since the remaining distances L1 and (n*C - L2) are identical, advancing both pointers by 1 step per beat guarantees they meet at the entrance node."
        )
    ),
    // Lesson 13
    TrainingDrill(
        id = "ll_drill_13",
        topicId = "linked_list",
        lessonNumber = 13,
        title = "The Triad Composition (Reorder List)",
        subtitle = "Combining Midpoint, Reversal, and Merging into a composite pipeline",
        conceptDelta = "+1 Concept: Pipeline architecture: (1) Runner midpoint, (2) Sublist reversal, (3) Alternating merge",
        intuition = "To reorder `L0 -> L1 -> ... -> Ln-1 -> Ln` into `L0 -> Ln -> L1 -> Ln-1...`: notice it is simply the composition of our three previous lessons! (1) Find midpoint with runner, (2) Split and reverse the second half, (3) Interleave nodes from first half and reversed second half one by one.",
        codePattern = """// Lesson 13: 3-Stage Reorder Pipeline
public void reorderList(ListNode head) {
    if (head == null || head.next == null) return;
    // Stage 1: Midpoint
    ListNode slow = head, fast = head;
    while (fast.next != null && fast.next.next != null) {
        slow = slow.next; fast = fast.next.next;
    }
    // Stage 2: Reverse second half
    ListNode prev = null, curr = slow.next;
    slow.next = null; // Sever into two independent lists
    while (curr != null) {
        ListNode temp = curr.next; curr.next = prev; prev = curr; curr = temp;
    }
    // Stage 3: Zig-zag interleave
    ListNode p1 = head, p2 = prev;
    while (p2 != null) {
        ListNode t1 = p1.next, t2 = p2.next;
        p1.next = p2;
        p2.next = t1;
        p1 = t1;
        p2 = t2;
    }
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "List: 1 -> 2 -> 3 -> 4", "Stage 1 finds midpoint node 2. Split into [1, 2] and [3, 4]."),
            DrillTraceStep(2, "Stage 2 reverses [3, 4]", "Becomes [4, 3]."),
            DrillTraceStep(3, "Stage 3 interleaves [1, 2] and [4, 3]", "1 -> 4 -> 2 -> 3."),
            DrillTraceStep(4, "Reordered cleanly", "Runs in O(N) time and strictly O(1) space.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why must `slow.next = null;` be called before Stage 2?",
            options = listOf(
                "To terminate the first half list with null, preventing an infinite circular loop during the final interleave",
                "To delete the slow node",
                "To free memory",
                "Because slow is garbage collected"
            ),
            correctOptionIndex = 0,
            explanation = "Severing the connection between the two halves ensures the first half has a proper null terminator so that interleaving ends cleanly."
        )
    ),
    // Lesson 14
    TrainingDrill(
        id = "ll_drill_14",
        topicId = "linked_list",
        lessonNumber = 14,
        title = "Carry Propagation Accumulator (Add Two Numbers)",
        subtitle = "Simulating arbitrary-precision addition along reverse-ordered digit chains",
        conceptDelta = "+1 Concept: `while (l1 != null || l2 != null || carry != 0)` cleanly absorbs the trailing carry",
        intuition = "In Add Two Numbers, digits are stored in reverse order (1s place at head), matching standard arithmetic! Sum `l1.val + l2.val + carry`. The new digit is `sum % 10`, and the new carry is `sum / 10`. Including `carry != 0` in the loop condition automatically handles an extra overflow digit at the end!",
        codePattern = """// Lesson 14: Carry Accumulation Chain
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode curr = dummy;
    int carry = 0;
    
    while (l1 != null || l2 != null || carry != 0) {
        int sum = carry;
        if (l1 != null) { sum += l1.val; l1 = l1.next; }
        if (l2 != null) { sum += l2.val; l2 = l2.next; }
        carry = sum / 10;
        curr.next = new ListNode(sum % 10);
        curr = curr.next;
    }
    return dummy.next;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "l1 = 2 -> 4 -> 3 (342), l2 = 5 -> 6 -> 4 (465)", "dummy -> curr, carry = 0."),
            DrillTraceStep(2, "Digits 2 + 5 = 7", "carry = 0. Append node 7. curr = 7."),
            DrillTraceStep(3, "Digits 4 + 6 = 10", "carry = 1. Append node 0. curr = 0."),
            DrillTraceStep(4, "Digits 3 + 4 + 1(carry) = 8", "carry = 0. Append node 8."),
            DrillTraceStep(5, "Result: 7 -> 0 -> 8 (807)", "Correct sum generated in linear O(max(N, M)) time.")
        ),
        quizQuestion = DrillQuiz(
            question = "What bug happens if `carry != 0` is omitted from the while condition for input `5 + 5`?",
            options = listOf(
                "The result will be [0] instead of [0, 1] because the loop exits before the final carry creates the most significant digit",
                "An infinite loop occurs",
                "A NullPointerException is thrown",
                "It works normally"
            ),
            correctOptionIndex = 0,
            explanation = "When both l1 and l2 become null, a lingering carry (e.g. 5+5=10) still needs to create a final node with value 1."
        )
    ),
    // Lesson 15
    TrainingDrill(
        id = "ll_drill_15",
        topicId = "linked_list",
        lessonNumber = 15,
        title = "Dual Stack Most-Significant-Digit Addition",
        subtitle = "Adding linked lists with digits in normal order without mutating the input lists",
        conceptDelta = "+1 Concept: Push nodes onto two Stacks to naturally reverse addition order; build result right-to-left via `newNode.next = head`",
        intuition = "In Add Two Numbers II, numbers are most-significant-digit first: `(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)`. If modifying the input lists is forbidden, how do we add from the right? Push all digits onto two stacks! As you pop digits, build the result chain backwards: `newNode.next = head; head = newNode;`.",
        codePattern = """// Lesson 15: Stack-Assisted Addition
public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
    Deque<Integer> s1 = new ArrayDeque<>(), s2 = new ArrayDeque<>();
    while (l1 != null) { s1.push(l1.val); l1 = l1.next; }
    while (l2 != null) { s2.push(l2.val); l2 = l2.next; }
    
    ListNode head = null;
    int carry = 0;
    while (!s1.isEmpty() || !s2.isEmpty() || carry != 0) {
        int sum = carry;
        if (!s1.isEmpty()) sum += s1.pop();
        if (!s2.isEmpty()) sum += s2.pop();
        carry = sum / 10;
        
        // Build list backwards by prepending
        ListNode node = new ListNode(sum % 10);
        node.next = head;
        head = node;
    }
    return head;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "s1 = [3, 4, 2, 7], s2 = [4, 6, 5]", "Top of stacks hold least significant digits: 3 and 4."),
            DrillTraceStep(2, "Pop 3 + 4 = 7", "head = node(7)."),
            DrillTraceStep(3, "Pop 4 + 6 = 10", "carry = 1. Prepend node(0) -> head = 0 -> 7."),
            DrillTraceStep(4, "Pop 2 + 5 + 1 = 8", "Prepend node(8) -> head = 8 -> 0 -> 7."),
            DrillTraceStep(5, "Pop 7", "Prepend node(7) -> head = 7 -> 8 -> 0 -> 7."),
            DrillTraceStep(6, "Result: 7807", "Zero mutations to original input lists.")
        ),
        quizQuestion = DrillQuiz(
            question = "How does `node.next = head; head = node;` construct the list without needing to reverse it later?",
            options = listOf(
                "By prepending each new node to the front, earlier computed lower-order digits naturally get pushed to the tail",
                "It uses a circular buffer",
                "Because ArrayDeque maintains the order",
                "It reverses the digits automatically"
            ),
            correctOptionIndex = 0,
            explanation = "Prepending to head builds the linked list from right to left, perfectly matching the order generated by popping from stacks."
        )
    ),
    // Lesson 16
    TrainingDrill(
        id = "ll_drill_16",
        topicId = "linked_list",
        lessonNumber = 16,
        title = "Bounded Segment Inversion (Reverse Between)",
        subtitle = "Reversing a subsegment [left..right] in a single pass with four anchoring pointers",
        conceptDelta = "+1 Concept: `pre` anchors node before left; repeatedly pluck `curr.next` and move to `pre.next`",
        intuition = "Reversing a specific subsegment `[left..right]` without cutting the list: advance `pre` to index `left - 1`. Let `curr = pre.next`. In each step of the subsegment, pluck `then = curr.next`, connect `curr.next = then.next`, link `then.next = pre.next`, and update `pre.next = then`. In N steps, the subsegment is reversed in-place!",
        codePattern = """// Lesson 16: In-Place Subsegment Reversal
public ListNode reverseBetween(ListNode head, int left, int right) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode pre = dummy;
    for (int i = 0; i < left - 1; i++) pre = pre.next; // Anchor before subsegment
    
    ListNode curr = pre.next;
    for (int i = 0; i < right - left; i++) {
        ListNode then = curr.next;
        curr.next = then.next;
        then.next = pre.next;
        pre.next = then;
    }
    return dummy.next;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "List: 1 -> 2 -> 3 -> 4 -> 5, left = 2, right = 4", "pre = 1, curr = 2."),
            DrillTraceStep(2, "Iter 1: pluck node 3", "Move 3 before 2: 1 -> 3 -> 2 -> 4 -> 5. pre = 1, curr = 2."),
            DrillTraceStep(3, "Iter 2: pluck node 4", "Move 4 before 3: 1 -> 4 -> 3 -> 2 -> 5."),
            DrillTraceStep(4, "Subsegment [2..4] reversed", "Result: 1 -> 4 -> 3 -> 2 -> 5 in single pass.")
        ),
        quizQuestion = DrillQuiz(
            question = "Notice that `curr` is never updated (`curr = curr.next` is never called). Why?",
            options = listOf(
                "Because curr stays fixed on the original first node of the subsegment, which naturally drifts toward the end as other nodes are placed ahead of it",
                "Because curr is immutable",
                "It is a bug in the code",
                "Because right - left is always 2"
            ),
            correctOptionIndex = 0,
            explanation = "Node 'curr' started at the beginning of the subsegment. As subsequent nodes are hoisted in front of it, curr naturally becomes the subsegment's tail."
        )
    ),
    // Lesson 17
    TrainingDrill(
        id = "ll_drill_17",
        topicId = "linked_list",
        lessonNumber = 17,
        title = "K-Group Reversal Lookahead",
        subtitle = "Reversing nodes in chunks of K while leaving incomplete final chunks untouched",
        conceptDelta = "+1 Concept: Lookahead K steps: only reverse if K nodes exist; stitch previous group tail to new group head",
        intuition = "In Reverse Nodes in K-Group, we must reverse chunks of K, but leave the remaining < K nodes alone. At each group: look ahead K nodes. If fewer than K nodes exist, STOP. Otherwise, reverse those K nodes, connect the previous group's tail to the new reversed head, and update pointers for the next group.",
        codePattern = """// Lesson 17: K-Group Reversal
public ListNode reverseKGroup(ListNode head, int k) {
    ListNode curr = head;
    int count = 0;
    // Look ahead K steps
    while (curr != null && count != k) {
        curr = curr.next;
        count++;
    }
    if (count == k) {
        // Reverse first K nodes
        ListNode reversedHead = reverseK(head, k);
        // Recurse for remaining list and connect
        head.next = reverseKGroup(curr, k);
        return reversedHead;
    }
    return head; // < K nodes remaining: leave as is
}
private ListNode reverseK(ListNode head, int k) {
    ListNode prev = null, curr = head;
    while (k-- > 0) {
        ListNode temp = curr.next;
        curr.next = prev;
        prev = curr;
        curr = temp;
    }
    return prev;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "List: 1 -> 2 -> 3 -> 4 -> 5, k = 2", "Lookahead counts 2 nodes (1, 2)."),
            DrillTraceStep(2, "Reverse [1, 2]", "Becomes [2 -> 1]. head (1) will connect to next group."),
            DrillTraceStep(3, "Next group [3, 4]", "Reversed to [4 -> 3]."),
            DrillTraceStep(4, "Remaining [5] has count 1 < 2", "Left untouched: [5]."),
            DrillTraceStep(5, "Stitched: 2 -> 1 -> 4 -> 3 -> 5", "Clean, recursive/iterative group reversal.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why does `head.next = reverseKGroup(curr, k);` correctly connect the reversed chunk to the next chunk?",
            options = listOf(
                "Because after reversal, original `head` has become the tail of the current K-group",
                "Because curr is the head of the whole list",
                "Because k is decremented to 0",
                "It connects to the dummy node"
            ),
            correctOptionIndex = 0,
            explanation = "When a group is reversed, the node that was initially 'head' becomes the last node of that group, making head.next the exact attachment point for the next group."
        )
    ),
    // Lesson 18
    TrainingDrill(
        id = "ll_drill_18",
        topicId = "linked_list",
        lessonNumber = 18,
        title = "Dual Sentinel Partitioning Around Pivot",
        subtitle = "Splitting nodes into `< x` and `>= x` chains and joining in O(1) space",
        conceptDelta = "+1 Concept: Two separate dummy heads (`lessHead` and `greaterHead`) preserving relative ordering",
        intuition = "In Partition List, we must partition around value X while PRESERVING the relative original order of elements. Use two dummy sentinels: `less` and `greater`. Traverse the list: if `val < x`, append to `less`; otherwise append to `greater`. At the end, set `greater.next = null`, connect `less.next = greaterHead.next`!",
        codePattern = """// Lesson 18: Dual Sentinel Partition
public ListNode partition(ListNode head, int x) {
    ListNode lessHead = new ListNode(0), less = lessHead;
    ListNode greaterHead = new ListNode(0), greater = greaterHead;
    
    ListNode curr = head;
    while (curr != null) {
        if (curr.val < x) {
            less.next = curr;
            less = less.next;
        } else {
            greater.next = curr;
            greater = greater.next;
        }
        curr = curr.next;
    }
    greater.next = null;          // Prevent cycle!
    less.next = greaterHead.next; // Stitch two partitions
    return lessHead.next;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "List: [1, 4, 3, 2, 5, 2], x = 3", "lessHead -> less, greaterHead -> greater."),
            DrillTraceStep(2, "Traverse elements", "less accumulates [1, 2, 2]. greater accumulates [4, 3, 5]."),
            DrillTraceStep(3, "Terminate greater: greater.next = null", "Prevents memory cycle back to less."),
            DrillTraceStep(4, "Stitch less.next = greaterHead.next", "Combined into [1, 2, 2, 4, 3, 5] in O(N) time with stable ordering.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why is `greater.next = null;` absolutely essential before stitching?",
            options = listOf(
                "Because the last node in greater might still point to an earlier node in less, creating a fatal infinite pointer cycle",
                "To free RAM",
                "Because Java garbage collection requires null tails",
                "It is optional"
            ),
            correctOptionIndex = 0,
            explanation = "If the last node placed into 'greater' originally pointed to a node that was moved to 'less', failing to clear its next pointer creates a circular reference loop."
        )
    ),
    // Lesson 19
    TrainingDrill(
        id = "ll_drill_19",
        topicId = "linked_list",
        lessonNumber = 19,
        title = "Adjacent Duplicate Elimination (Sorted List I)",
        subtitle = "Collapsing consecutive identical values while retaining one instance",
        conceptDelta = "+1 Concept: `if (curr.val == curr.next.val) curr.next = curr.next.next; else curr = curr.next;`",
        intuition = "In a sorted list, all duplicate values are strictly contiguous! If `curr.val == curr.next.val`, bypass the duplicate: `curr.next = curr.next.next`. Notice we DO NOT advance `curr` when a duplicate is bypassed, because the third node might ALSO have the same value!",
        codePattern = """// Lesson 19: Contiguous Duplicate Bypass
public ListNode deleteDuplicates(ListNode head) {
    ListNode curr = head;
    while (curr != null && curr.next != null) {
        if (curr.val == curr.next.val) {
            curr.next = curr.next.next; // Bypass duplicate, stay at curr
        } else {
            curr = curr.next;          // Distinct value, step forward
        }
    }
    return head;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "List: 1 -> 1 -> 1 -> 2", "curr = node 1."),
            DrillTraceStep(2, "curr.val (1) == curr.next.val (1)", "Bypass second 1. List: 1 -> 1 -> 2. curr stays at first 1!"),
            DrillTraceStep(3, "curr.val (1) == curr.next.val (1)", "Bypass third 1. List: 1 -> 2. curr stays at first 1!"),
            DrillTraceStep(4, "curr.val (1) != curr.next.val (2)", "Advance curr to 2. Finished: 1 -> 2 in O(N) single pass.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why must `curr` NOT advance when `curr.val == curr.next.val`?",
            options = listOf(
                "Because the new `curr.next` after the bypass might also be identical to `curr`, requiring another bypass",
                "Because curr is locked in memory",
                "To save CPU cycles",
                "Because head would become null"
            ),
            correctOptionIndex = 0,
            explanation = "If there are three or more identical values (e.g. 1 -> 1 -> 1), advancing curr would skip verifying the newly linked adjacent neighbor."
        )
    ),
    // Lesson 20
    TrainingDrill(
        id = "ll_drill_20",
        topicId = "linked_list",
        lessonNumber = 20,
        title = "Total Duplicate Purging (Sorted List II)",
        subtitle = "Removing ALL occurrences of elements that appear more than once",
        conceptDelta = "+1 Concept: Detect duplicate run with inner while loop; if duplicate occurred, bypass entire block from `prev`",
        intuition = "In Lesson 19, we kept one copy. In Remove Duplicates II, if a number appears twice, ALL copies must be wiped out (`1 -> 2 -> 3 -> 3 -> 4` becomes `1 -> 2 -> 4`)! Use `dummy -> head`. Maintain `prev`. When `curr` has duplicate neighbors, loop `curr` to the end of the run and set `prev.next = curr.next`.",
        codePattern = """// Lesson 20: Total Duplicate Purge
public ListNode deleteDuplicatesAll(ListNode head) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode prev = dummy;
    ListNode curr = head;
    
    while (curr != null) {
        // Detect duplicate run
        if (curr.next != null && curr.val == curr.next.val) {
            while (curr.next != null && curr.val == curr.next.val) {
                curr = curr.next;
            }
            prev.next = curr.next; // Bypass entire duplicate block
        } else {
            prev = prev.next;     // Distinct element confirmed
        }
        curr = curr.next;
    }
    return dummy.next;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "List: 1 -> 2 -> 3 -> 3 -> 4", "dummy -> prev=1 -> curr=2."),
            DrillTraceStep(2, "curr = 3: curr.next.val == 3", "Duplicate run detected! Inner while advances curr to second 3."),
            DrillTraceStep(3, "Bypass: prev.next = curr.next", "prev (node 2) points directly to node 4! Both 3s completely obliterated."),
            DrillTraceStep(4, "Result: 1 -> 2 -> 4", "Only strictly unique elements survive.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why does `prev` only advance in the `else` branch?",
            options = listOf(
                "Because if duplicates were bypassed, `prev.next` now points to a new node that hasn't yet been checked for duplicates itself",
                "Because prev cannot move more than 3 times",
                "To prevent dummy from being deleted",
                "It is a style preference"
            ),
            correctOptionIndex = 0,
            explanation = "The node connected to prev.next after a bypass might itself be part of another duplicate run, so prev must wait until that node is validated."
        )
    ),
    // Lesson 21
    TrainingDrill(
        id = "ll_drill_21",
        topicId = "linked_list",
        lessonNumber = 21,
        title = "Interleaved Node Weaving (Copy List with Random Pointer)",
        subtitle = "Cloning complex graphs in O(1) space by weaving copy nodes directly into the original list",
        conceptDelta = "+1 Concept: Phase 1: weave `curr.next = new Node(curr.val, curr.next)`. Phase 2: `curr.next.random = curr.random.next`",
        intuition = "Using a `HashMap<Node, Node>` to copy random pointers takes O(N) space. Genius O(1) space trick: (1) Weave a copy of each node directly after it: `A -> A' -> B -> B'`. (2) Set copy random pointers: `curr.next.random = curr.random.next`! (3) Unweave the lists back into original and clone.",
        codePattern = """// Lesson 21: Weaving Clone Technique
public Node copyRandomList(Node head) {
    if (head == null) return null;
    // Step 1: Weave cloned nodes
    Node curr = head;
    while (curr != null) {
        Node copy = new Node(curr.val);
        copy.next = curr.next;
        curr.next = copy;
        curr = copy.next;
    }
    // Step 2: Assign random pointers
    curr = head;
    while (curr != null) {
        if (curr.random != null) {
            curr.next.random = curr.random.next;
        }
        curr = curr.next.next;
    }
    // Step 3: Unweave lists
    curr = head;
    Node dummy = new Node(0), copyCurr = dummy;
    while (curr != null) {
        copyCurr.next = curr.next;
        copyCurr = copyCurr.next;
        curr.next = curr.next.next; // Restore original list
        curr = curr.next;
    }
    return dummy.next;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "List: A -> B, A.random = B", "Step 1 weaves: A -> A' -> B -> B'."),
            DrillTraceStep(2, "Step 2 copies random", "A'.random = A.random.next = B.next = B'. Perfect random link without HashMap!"),
            DrillTraceStep(3, "Step 3 unweaves", "Original restored: A -> B. Clone extracted: A' -> B'."),
            DrillTraceStep(4, "Result", "Deep copy completed in O(N) time and strictly O(1) auxiliary space.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why does `curr.next.random = curr.random.next;` correctly assign the cloned random pointer?",
            options = listOf(
                "Because `curr.random` points to the original target node, and its clone was woven directly at `curr.random.next`",
                "Because random pointers are sorted",
                "Because Java clones memory automatically",
                "It only works for acyclic lists"
            ),
            correctOptionIndex = 0,
            explanation = "In the woven list, every original node's clone sits immediately at its .next pointer. Therefore, the clone of curr.random is located at curr.random.next."
        )
    ),
    // Lesson 22
    TrainingDrill(
        id = "ll_drill_22",
        topicId = "linked_list",
        lessonNumber = 22,
        title = "Stack-Assisted Multilevel Doubly List Flattening",
        subtitle = "Flattening child branch levels into a single linear doubly linked list",
        conceptDelta = "+1 Concept: When `curr.child != null`, push `curr.next` onto stack, splice `child` as `curr.next`, and nullify `curr.child`",
        intuition = "In a multilevel doubly linked list, nodes can have `child` pointers branching down. As you traverse: if a node has a child, push its `next` neighbor onto a stack, point `curr.next` to `child`, point `child.prev` to `curr`, and set `curr.child = null`. When reaching the end of a branch, pop from stack and continue!",
        codePattern = """// Lesson 22: Multilevel Flattening
public Node flatten(Node head) {
    if (head == null) return null;
    Node curr = head;
    Deque<Node> stack = new ArrayDeque<>();
    
    while (curr != null) {
        if (curr.child != null) {
            if (curr.next != null) stack.push(curr.next);
            curr.next = curr.child;
            curr.child.prev = curr;
            curr.child = null; // Clean up child pointer
        } else if (curr.next == null && !stack.isEmpty()) {
            Node nextNode = stack.pop();
            curr.next = nextNode;
            nextNode.prev = curr;
        }
        curr = curr.next;
    }
    return head;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "Node 3 has child node 7, next node 4", "Stack pushes node 4. Node 3 connects to child node 7."),
            DrillTraceStep(2, "Traverse child list 7 -> 8", "At node 8 (next is null), pop node 4 from stack."),
            DrillTraceStep(3, "Connect 8.next = 4, 4.prev = 8", "Sub-branch seamlessly folded into main list."),
            DrillTraceStep(4, "All levels flattened", "Clean doubly linked list in O(N) time.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why must `curr.child = null;` be explicitly set after splicing?",
            options = listOf(
                "Because in a flattened list, nodes must not retain child references, transforming the multilevel structure into a standard doubly linked list",
                "To avoid compiler errors",
                "To prevent recursion",
                "It is optional"
            ),
            correctOptionIndex = 0,
            explanation = "The problem requires outputting a standard doubly linked list where all child pointers are cleared to null."
        )
    ),
    // Lesson 23
    TrainingDrill(
        id = "ll_drill_23",
        topicId = "linked_list",
        lessonNumber = 23,
        title = "Circular Closure & Ring Severing (Rotate List)",
        subtitle = "Connecting the tail to head to form a ring, then severing at length - K % length",
        conceptDelta = "+1 Concept: Ring creation: `tail.next = head`; new tail is at index `length - (k % length)`",
        intuition = "To rotate a list right by K: instead of shifting nodes repeatedly, connect the tail to the head (`tail.next = head`) to form a circular ring! The new head is at index `length - (k % length)`. Advance `length - (k % length) - 1` steps from the old tail, break the ring (`newTail.next = null`), and return the new head!",
        codePattern = """// Lesson 23: Circular Ring Rotation
public ListNode rotateRight(ListNode head, int k) {
    if (head == null || head.next == null || k == 0) return head;
    // Step 1: Calculate length and locate tail
    int length = 1;
    ListNode tail = head;
    while (tail.next != null) {
        tail = tail.next;
        length++;
    }
    // Step 2: Form circular ring
    tail.next = head;
    // Step 3: Find new tail and sever
    k = k % length;
    int stepsToNewTail = length - k;
    ListNode newTail = tail;
    while (stepsToNewTail-- > 0) {
        newTail = newTail.next;
    }
    ListNode newHead = newTail.next;
    newTail.next = null; // Sever the ring!
    return newHead;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "List: 1 -> 2 -> 3 -> 4 -> 5, k = 2", "Length = 5, tail = node 5."),
            DrillTraceStep(2, "Connect tail to head", "5.next = 1 (circular ring formed)."),
            DrillTraceStep(3, "stepsToNewTail = 5 - (2 % 5) = 3", "Starting from old tail 5, step 3 times: 1, 2, 3. newTail = node 3."),
            DrillTraceStep(4, "newHead = 3.next (4), sever: 3.next = null", "Result: 4 -> 5 -> 1 -> 2 -> 3 in O(N) time.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why is `k = k % length` applied before calculating the new tail position?",
            options = listOf(
                "Rotating a list of length N by N positions returns it to its original state, so only the remainder k % N matters",
                "Because k could be negative",
                "To prevent integer division by zero",
                "It makes the list shorter"
            ),
            correctOptionIndex = 0,
            explanation = "A full rotation of length N leaves the list unchanged. Modulo arithmetic handles arbitrarily large K (e.g. K = 2,000,000,000) in O(1)."
        )
    ),
    // Lesson 24
    TrainingDrill(
        id = "ll_drill_24",
        topicId = "linked_list",
        lessonNumber = 24,
        title = "Iterative Pair Swapping via Anchor Pointers",
        subtitle = "Rewiring 3 pointers per pair using a dummy predecessor anchor",
        conceptDelta = "+1 Concept: `first = prev.next; second = curr.next.next;` rewire: `prev.next = second; first.next = second.next; second.next = first;`",
        intuition = "In Swap Nodes in Pairs: swapping `first` and `second` requires updating three links: (1) `prev.next = second`, (2) `first.next = second.next`, and (3) `second.next = first`. Then advance `prev = first` and repeat for all remaining pairs in O(1) space.",
        codePattern = """// Lesson 24: Pair Swapping Anchor
public ListNode swapPairs(ListNode head) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode prev = dummy;
    
    while (prev.next != null && prev.next.next != null) {
        ListNode first = prev.next;
        ListNode second = prev.next.next;
        
        // 3-way pointer rewire
        prev.next = second;
        first.next = second.next;
        second.next = first;
        
        prev = first; // Advance anchor
    }
    return dummy.next;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "List: 1 -> 2 -> 3 -> 4", "dummy -> prev=0, first=1, second=2."),
            DrillTraceStep(2, "Rewire pair (1, 2)", "0 points to 2, 1 points to 3, 2 points to 1. List: dummy -> 2 -> 1 -> 3 -> 4."),
            DrillTraceStep(3, "prev advances to 1", "first=3, second=4. Rewires to 4 -> 3."),
            DrillTraceStep(4, "Result: 2 -> 1 -> 4 -> 3", "All pairs swapped without modifying node values.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why is it forbidden in interview settings to simply swap `first.val` and `second.val`?",
            options = listOf(
                "Because in real systems, nodes may be complex objects where swapping pointers is strictly O(1) while cloning values is expensive or illegal",
                "Because node values are read-only in Java",
                "Because swapping values breaks hashCode",
                "It is not forbidden"
            ),
            correctOptionIndex = 0,
            explanation = "Interviewers test linked list pointer manipulation mechanics. Value swapping bypasses the core test and is often prohibited in problem constraints."
        )
    ),
    // Lesson 25
    TrainingDrill(
        id = "ll_drill_25",
        topicId = "linked_list",
        lessonNumber = 25,
        title = "In-Place Linked List Insertion Sort",
        subtitle = "Maintaining a sorted prefix list and inserting unsorted nodes via linear scan",
        conceptDelta = "+1 Concept: `prev = dummy`; scan sorted prefix until `prev.next.val >= curr.val`, then splice `curr`",
        intuition = "To perform Insertion Sort on a linked list: maintain a `dummy` head for the sorted prefix. For each node `curr` in the unsorted remainder: scan `prev` from `dummy` until `prev.next.val >= curr.val`. Insert `curr` between `prev` and `prev.next`. O(1) space with no shifting overhead!",
        codePattern = """// Lesson 25: Insertion Sort on List
public ListNode insertionSortList(ListNode head) {
    if (head == null) return null;
    ListNode dummy = new ListNode(0);
    ListNode curr = head;
    
    while (curr != null) {
        ListNode nextTemp = curr.next;
        // Find insertion position in sorted prefix
        ListNode prev = dummy;
        while (prev.next != null && prev.next.val < curr.val) {
            prev = prev.next;
        }
        // Splice curr into sorted prefix
        curr.next = prev.next;
        prev.next = curr;
        curr = nextTemp;
    }
    return dummy.next;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "Input: 4 -> 2 -> 1 -> 3", "dummy -> null."),
            DrillTraceStep(2, "Insert 4", "dummy -> 4 -> null."),
            DrillTraceStep(3, "Insert 2", "2 < 4 -> inserted before 4: dummy -> 2 -> 4 -> null."),
            DrillTraceStep(4, "Insert 1", "1 < 2 -> dummy -> 1 -> 2 -> 4 -> null."),
            DrillTraceStep(5, "Insert 3", "2 < 3 < 4 -> dummy -> 1 -> 2 -> 3 -> 4. Sorted in O(N²) time.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why is Insertion Sort faster on linked lists than on arrays?",
            options = listOf(
                "Because inserting a node into a linked list takes O(1) pointer updates without shifting remaining elements",
                "Because linked lists use binary search",
                "Because linked list insertion sort is O(N log N)",
                "It is not faster"
            ),
            correctOptionIndex = 0,
            explanation = "In an array, inserting into position i requires shifting all elements from i to N (O(N) data copies). In a linked list, insertion is a simple O(1) link splice."
        )
    ),
    // Lesson 26
    TrainingDrill(
        id = "ll_drill_26",
        topicId = "linked_list",
        lessonNumber = 26,
        title = "Divide & Conquer Multi-Way Merging (Merge K Lists)",
        subtitle = "Pairwise merging K sorted lists in O(N log K) time",
        conceptDelta = "+1 Concept: Merging lists in pairs `(0, k-1)`, `(1, k-2)` cuts the number of lists in half each round",
        intuition = "Merging K lists one by one takes O(K * N). By using Divide & Conquer (pairwise merging), each round merges pairs of lists using our 2-list merger (Lesson 6). The number of lists halves each round: K -> K/2 -> K/4 -> 1. Total runtime drops to O(N log K)!",
        codePattern = """// Lesson 26: Divide & Conquer K-Way Merge
public ListNode mergeKLists(ListNode[] lists) {
    if (lists == null || lists.length == 0) return null;
    int interval = 1;
    while (interval < lists.length) {
        for (int i = 0; i + interval < lists.length; i += interval * 2) {
            lists[i] = mergeTwo(lists[i], lists[i + interval]);
        }
        interval *= 2;
    }
    return lists[0];
}
private ListNode mergeTwo(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0), tail = dummy;
    while (l1 != null && l2 != null) {
        if (l1.val <= l2.val) { tail.next = l1; l1 = l1.next; }
        else { tail.next = l2; l2 = l2.next; }
        tail = tail.next;
    }
    tail.next = (l1 != null) ? l1 : l2;
    return dummy.next;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "lists = [L1, L2, L3, L4]", "interval = 1. Merges (L1, L2) and (L3, L4)."),
            DrillTraceStep(2, "Round 1 complete", "lists = [L1+2, null, L3+4, null]. interval = 2."),
            DrillTraceStep(3, "Round 2 merges (L1+2, L3+4)", "Final single sorted list formed in lists[0]."),
            DrillTraceStep(4, "Total time: O(N log K)", "Extremely cache-friendly with zero PriorityQueue object allocations.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why is Divide & Conquer faster than merging lists sequentially into a single running accumulator?",
            options = listOf(
                "Sequential merge processes earlier lists over and over again in O(N * K), while Divide & Conquer touches each node exactly log K times",
                "Because Divide & Conquer uses multi-threading",
                "Because K is always a power of 2",
                "Because sequential merge crashes with StackOverflowError"
            ),
            correctOptionIndex = 0,
            explanation = "Sequential merging suffers from lopsided work: list 1 is merged K times. Divide & conquer balances the tree so all nodes participate in exactly log K merge operations."
        )
    ),
    // Lesson 27
    TrainingDrill(
        id = "ll_drill_27",
        topicId = "linked_list",
        lessonNumber = 27,
        title = "LRU Cache Architecture (Doubly Linked List + HashMap)",
        subtitle = "Achieving true O(1) Get and Put with sentinel dummy nodes",
        conceptDelta = "+1 Concept: Doubly linked list with `head` and `tail` sentinels allows O(1) removal and O(1) front insertion",
        intuition = "An LRU Cache must support `get` and `put` in O(1). A HashMap alone gives O(1) lookup but cannot reorder in O(1). An array reorders in O(N). A Doubly Linked List with `head` and `tail` sentinels allows removing any node in O(1) and adding to head in O(1)! The Map stores `key -> Node` pointers.",
        codePattern = """// Lesson 27: LRU Cache
class LRUCache {
    class Node {
        int key, val;
        Node prev, next;
        Node(int k, int v) { key = k; val = v; }
    }
    private Map<Integer, Node> map = new HashMap<>();
    private Node head = new Node(0, 0), tail = new Node(0, 0);
    private int capacity;
    
    public LRUCache(int cap) {
        capacity = cap;
        head.next = tail; tail.prev = head; // Sentinels
    }
    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);
        remove(node); insertAtHead(node); // Mark recently used
        return node.val;
    }
    public void put(int key, int val) {
        if (map.containsKey(key)) remove(map.get(key));
        if (map.size() == capacity) {
            map.remove(tail.prev.key);
            remove(tail.prev); // Evict least recently used
        }
        Node newNode = new Node(key, val);
        insertAtHead(newNode);
        map.put(key, newNode);
    }
    private void remove(Node n) { n.prev.next = n.next; n.next.prev = n.prev; }
    private void insertAtHead(Node n) {
        n.next = head.next; n.prev = head; head.next.prev = n; head.next = n;
    }
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "Capacity = 2, put(1, 1), put(2, 2)", "List: head <-> 2 <-> 1 <-> tail."),
            DrillTraceStep(2, "get(1)", "Removes 1, inserts at head. List: head <-> 1 <-> 2 <-> tail. 1 is newest."),
            DrillTraceStep(3, "put(3, 3) at full capacity", "Evicts tail.prev (node 2). Inserts 3 at head. List: head <-> 3 <-> 1 <-> tail."),
            DrillTraceStep(4, "get(2) returns -1", "Node 2 was successfully evicted in O(1) operations.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why must `Node` store the `key` in addition to the `val`?",
            options = listOf(
                "When the tail node is evicted, its `key` is required to delete its entry from the HashMap in O(1)",
                "To hash the node",
                "Because Java maps require bidirectional keys",
                "It is optional"
            ),
            correctOptionIndex = 0,
            explanation = "When capacity overflows, tail.prev is removed. Without node.key, we wouldn't know which key to remove from map, requiring an O(N) search."
        )
    ),
    // Lesson 28
    TrainingDrill(
        id = "ll_drill_28",
        topicId = "linked_list",
        lessonNumber = 28,
        title = "LFU Cache (Multi-Frequency Bucket Architecture)",
        subtitle = "Tracking least-frequently used nodes using frequency buckets",
        conceptDelta = "+1 Concept: `Map<Integer, DoublyLinkedList> freqBuckets` with running `minFreq` counter",
        intuition = "In an LFU Cache, evict the LEAST FREQUENTLY used item (and tiebreak by LRU). We maintain: (1) `Map<key, Node>`, (2) `Map<frequency, DoublyLinkedList>`, and (3) `minFreq`. When a node is accessed, move it from bucket `F` to bucket `F + 1`. If bucket `minFreq` is now empty, `minFreq++`. True O(1) LFU!",
        codePattern = """// Lesson 28: LFU Cache Multi-Bucket
class LFUCache {
    class Node {
        int key, val, freq = 1;
        Node prev, next;
        Node(int k, int v) { key = k; val = v; }
    }
    class DLList {
        Node head = new Node(0, 0), tail = new Node(0, 0);
        int size = 0;
        DLList() { head.next = tail; tail.prev = head; }
        void add(Node n) { n.next = head.next; n.prev = head; head.next.prev = n; head.next = n; size++; }
        void remove(Node n) { n.prev.next = n.next; n.next.prev = n.prev; size--; }
        Node popTail() { if (size == 0) return null; Node n = tail.prev; remove(n); return n; }
    }
    private Map<Integer, Node> nodeMap = new HashMap<>();
    private Map<Integer, DLList> freqMap = new HashMap<>();
    private int capacity, minFreq = 0;
    
    public LFUCache(int cap) { capacity = cap; }
    public int get(int key) {
        if (!nodeMap.containsKey(key)) return -1;
        Node node = nodeMap.get(key);
        updateFreq(node);
        return node.val;
    }
    private void updateFreq(Node n) {
        DLList oldList = freqMap.get(n.freq);
        oldList.remove(n);
        if (n.freq == minFreq && oldList.size == 0) minFreq++;
        n.freq++;
        freqMap.computeIfAbsent(n.freq, k -> new DLList()).add(n);
    }
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "Put key 1 and 2 (freq = 1)", "minFreq = 1. Bucket 1 holds: 2 <-> 1."),
            DrillTraceStep(2, "get(1): freq of 1 becomes 2", "Node 1 moves to Bucket 2. Bucket 1 has node 2. minFreq stays 1."),
            DrillTraceStep(3, "Put key 3 at capacity", "Evicts from bucket minFreq (1): node 2 popped! Key 1 survives because freq=2."),
            DrillTraceStep(4, "All operations in O(1)", "Optimal multi-level cache architecture.")
        ),
        quizQuestion = DrillQuiz(
            question = "When does `minFreq` increment during a node access?",
            options = listOf(
                "When the accessed node belonged to the `minFreq` bucket AND that bucket has now become completely empty",
                "On every get call",
                "When capacity is reached",
                "Never"
            ),
            correctOptionIndex = 0,
            explanation = "If the node with the lowest frequency was promoted and no other nodes remain in that frequency bucket, the new minimum frequency must be minFreq + 1."
        )
    ),
    // Lesson 29
    TrainingDrill(
        id = "ll_drill_29",
        topicId = "linked_list",
        lessonNumber = 29,
        title = "In-Place Tree Flattening via Predecessor Stitch",
        subtitle = "Converting a binary tree into a singly linked list in O(1) space",
        conceptDelta = "+1 Concept: Attach right subtree to the rightmost leaf of left subtree; move left subtree to right",
        intuition = "To flatten a binary tree into a linked list in-place without recursion or extra memory (Morris traversal style): for every node `curr`, if it has a left child, find the rightmost node of that left child. Splice `curr.right` onto that rightmost node! Then move `curr.left` to `curr.right` and set `curr.left = null`.",
        codePattern = """// Lesson 29: In-Place Tree Flattening
public void flatten(TreeNode root) {
    TreeNode curr = root;
    while (curr != null) {
        if (curr.left != null) {
            // Find rightmost node of left subtree
            TreeNode prev = curr.left;
            while (prev.right != null) {
                prev = prev.right;
            }
            // Splice original right subtree to predecessor's right
            prev.right = curr.right;
            curr.right = curr.left;
            curr.left = null; // Clear left branch
        }
        curr = curr.right; // March down the linked list
    }
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "Root 1 has left 2 (with 3, 4) and right 5 (with 6)", "curr = 1. Predecessor of left child is node 4."),
            DrillTraceStep(2, "prev.right = curr.right", "Node 4.right becomes node 5. Subtree [5, 6] stitched behind [2, 3, 4]!"),
            DrillTraceStep(3, "curr.right = curr.left, curr.left = null", "Right pointer points to 2. Left pointer nullified."),
            DrillTraceStep(4, "Tree transformed into linear list", "1 -> 2 -> 3 -> 4 -> 5 -> 6 in O(N) time and O(1) space.")
        ),
        quizQuestion = DrillQuiz(
            question = "Why does attaching `curr.right` to the rightmost node of the left subtree preserve preorder traversal?",
            options = listOf(
                "Because in preorder (Root, Left, Right), the entire left subtree is processed before the first node of the right subtree",
                "Because trees are binary search trees",
                "Because left nodes are smaller",
                "It only works on balanced trees"
            ),
            correctOptionIndex = 0,
            explanation = "Preorder visits all nodes in the left subtree before any node in the right subtree. Attaching the right subtree to the very last node of the left subtree honors this exact sequence."
        )
    ),
    // Lesson 30
    TrainingDrill(
        id = "ll_drill_30",
        topicId = "linked_list",
        lessonNumber = 30,
        title = "Divmod Chunk Sizing (Split Linked List into K Parts)",
        subtitle = "Evenly distributing N elements into K parts with remainder front-loading",
        conceptDelta = "+1 Concept: Base size = `len / k`; first `len % k` parts receive extra +1 node; sever with `prev.next = null`",
        intuition = "To split a list of length N into K parts as evenly as possible: compute `baseSize = N / K` and `extra = N % K`. The first `extra` parts receive `baseSize + 1` nodes, and the remaining parts receive `baseSize` nodes. Advance through each part, sever the connection with `prev.next = null`, and store the sublist heads.",
        codePattern = """// Lesson 30: Divmod Part Splitting
public ListNode[] splitListToParts(ListNode head, int k) {
    int totalLen = 0;
    ListNode curr = head;
    while (curr != null) { totalLen++; curr = curr.next; }
    
    int baseSize = totalLen / k;
    int extra = totalLen % k; // Number of parts that get baseSize + 1
    
    ListNode[] result = new ListNode[k];
    curr = head;
    for (int i = 0; i < k && curr != null; i++) {
        result[i] = curr;
        int partSize = baseSize + (i < extra ? 1 : 0);
        ListNode prev = null;
        for (int j = 0; j < partSize; j++) {
            prev = curr;
            curr = curr.next;
        }
        if (prev != null) prev.next = null; // Sever part from remaining list
    }
    return result;
}""",
        stepTrace = listOf(
            DrillTraceStep(1, "List of 10 nodes, k = 3", "baseSize = 10 / 3 = 3, extra = 10 % 3 = 1."),
            DrillTraceStep(2, "Part 0 (gets extra +1)", "Size = 3 + 1 = 4. Takes [1, 2, 3, 4]. Sever 4.next = null."),
            DrillTraceStep(3, "Part 1 (gets baseSize)", "Size = 3. Takes [5, 6, 7]. Sever 7.next = null."),
            DrillTraceStep(4, "Part 2 (gets baseSize)", "Size = 3. Takes [8, 9, 10]. Sever 10.next = null."),
            DrillTraceStep(5, "Result: [[1..4], [5..7], [8..10]]", "Perfect even distribution in O(N) time and O(1) extra space.")
        ),
        quizQuestion = DrillQuiz(
            question = "If the list length is 3 and k = 5, what do the final 2 parts in the result array contain?",
            options = listOf(
                "`null` entries, representing empty sublists",
                "A copy of the first node",
                "They are omitted from the array",
                "Zero-valued nodes"
            ),
            correctOptionIndex = 0,
            explanation = "When k > length, the first N parts receive 1 node each, and the remaining (k - N) parts remain null."
        )
    )
)
