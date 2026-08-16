## Singly Linked List - Creation & Finding Middle Element

---

**Topic:** Data Structures - Linked List (Java)  
**Concepts:** Node Structure, Head Pointer, Traversal, Two-Pointer (Tortoise & Hare / Slow & Fast Pointer)

---

### Problem Statement

1. **Create and traverse a Singly Linked List** with dynamic nodes containing integer data and reference pointers to subsequent nodes.
2. **Find the Middle Element** of the linked list efficiently in a single pass ($O(N)$ time and $O(1)$ extra space).

---

### Visual Representation

```
Linked List:
[ Head ] -> (1) -> (2) -> (3) -> (4) -> null

Slow & Fast Pointer Traversal:
Step 0: slow at (1), fast at (1)
Step 1: slow moves to (2), fast moves to (3)
Step 2: slow moves to (3), fast moves to null
Result: Middle Element = (3) (Second middle for even length)
```

---

### Implementation (`CreateLL.java`)

```java
public class CreateLL {
    // Node class definition
    public static class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    public static Node head;

    // Traversal & Printing
    public static void printLL() {
        Node n = head;
        while (n != null) {
            System.out.print(n.data + "->");
            n = n.next;
        }
        System.out.println("null");
    }

    // Two-Pointer Approach (Tortoise and Hare algorithm)
    public static void MidElement() {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        System.out.println("\nMid Element: " + slow.data);
    }

    public static void main(String[] args) {
        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);

        System.out.println("Linked List Contents:");
        printLL();

        MidElement();
    }
}
```

---

### How to Compile & Run

```bash
javac CreateLL.java
java CreateLL
```

**Output:**
```
Linked List Contents:
1->2->3->4->null

Mid Element: 3
```

---

### Complexity Analysis

| Operation | Algorithm | Time Complexity | Space Complexity |
| :--- | :--- | :--- | :--- |
| **List Traversal** | Iterative traversal | $O(N)$ | $O(1)$ |
| **Middle Node Lookup** | Slow & Fast Pointer | $O(N)$ | $O(1)$ |

---

### Key Takeaways

1. **Slow and Fast Pointers (Tortoise & Hare):** Fast pointer advances at $2\times$ speed (`fast = fast.next.next`) while slow pointer advances at $1\times$ speed (`slow = slow.next`). When fast reaches the end, slow is precisely at the midpoint.
2. **Cycle Detection & Variations:** This same pointer technique is the foundation of Floyd's Cycle-Finding Algorithm for detecting loops in linked structures.
