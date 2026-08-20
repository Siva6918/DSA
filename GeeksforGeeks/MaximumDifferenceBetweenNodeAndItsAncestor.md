# Maximum Difference Between Node and Its Ancestor

- **Platform:** GeeksforGeeks
- **Difficulty:** Medium
- **Category:** Binary Trees, Recursion, Post-Order Traversal (Bottom-Up)
- **Problem Link:** [Maximum difference between node and its ancestor - GeeksforGeeks](https://www.geeksforgeeks.org/problems/maximum-difference-between-node-and-its-ancestor/1)

---

## 📌 Problem Statement

Given a Binary Tree, find the maximum value of $V = \text{ancestor.data} - \text{descendant.data}$ where $\text{ancestor}$ is an ancestor of $\text{descendant}$.

> **Ancestor Definition:** A node $A$ is an ancestor of node $B$ if there exists a directed path from $A$ to $B$ (i.e., $A$ is above $B$ in the tree hierarchy).

---

## 🌲 Visual Explanation

Consider the following binary tree:

```
            [8]  <--- Ancestor
          /     \
        [3]     [10]
       /   \        \
     [1]   [6]      [14]
          /   \     /
        [4]   [7] [13]  <--- Descendants
```

### Possible Ancestor-Descendant Pairs & Differences:
- **Pair (8, 1):** $8 - 1 = \mathbf{7}$  *(Candidate)*
- **Pair (8, 4):** $8 - 4 = 4$
- **Pair (8, 13):** $8 - 13 = -5$
- **Pair (3, 1):** $3 - 1 = 2$
- **Pair (3, 4):** $3 - 4 = -1$
- **Pair (10, 13):** $10 - 13 = -3$
- **Pair (14, 13):** $14 - 13 = 1$

**Maximum Difference found = 7** (between ancestor `8` and descendant `1`).

---

## 💡 Key Algorithmic Insight

For any node $A$ acting as an ancestor:
$$\text{Difference}(A) = A.\text{data} - \text{min}(\text{all descendants in left and right subtrees})$$

To maximize this difference, for each node $A$, we must find the **minimum descendant value** in its left and right subtrees.

### Why Bottom-Up (Post-Order) Traversal?
1. Traverse to the deepest leaves first.
2. From each subtree, return the **minimum value** present in that subtree.
3. At the current node $A$:
   - Let $\text{minDescendant} = \min(\text{leftMin}, \text{rightMin})$.
   - Update global maximum difference: $\text{maxDiff} = \max(\text{maxDiff}, A.\text{data} - \text{minDescendant})$.
   - Return $\min(A.\text{data}, \text{minDescendant})$ to the parent node.

```
       [A]           <-- Calculate: A.data - min(leftMin, rightMin)
      /   \
  [Left]  [Right]   <-- Return min value in Left & Right subtrees
```

---

## 🔍 Deep-Dive: Java Language Concepts & Built-ins Explained

### 1. `Math.min()` and `Math.max()`
- **Syntax:** `Math.min(int a, int b)`, `Math.max(int a, int b)`
- **Class:** `java.lang.Math` (automatically imported in all Java programs).
- **Why is it `static`?**
  - `Math` is a utility class containing purely stateless mathematical functions.
  - You do **not** need to create an object (`new Math()`). You call it directly using the class name: `Math.max(x, y)`.

### 2. `Integer.MAX_VALUE` & `Integer.MIN_VALUE`
- **What are they?**
  - Constants defined in the `java.lang.Integer` wrapper class.
  - `Integer.MAX_VALUE = 2^{31}-1 = 2147483647` (largest positive 32-bit signed int).
  - `Integer.MIN_VALUE = -2^{31} = -2147483648` (smallest negative 32-bit signed int).
- **Why use them here?**
  - When reaching a `null` node, returning `Integer.MAX_VALUE` ensures that `Math.min(realValue, MAX_VALUE)` always evaluates to `realValue`.
  - Initializing `maxDiff = Integer.MIN_VALUE` guarantees any valid difference calculated will update it.

### 3. Understanding `static` vs. Instance Variables in Tree Recursion

#### Why Can't We Just Use a Primitive `int maxDiff` Passed as an Argument?
In Java, **all primitives are strictly passed by value** (a copy is passed).
```java
// ❌ DOES NOT WORK IN JAVA:
void findDiff(Node root, int maxDiff) {
    maxDiff = Math.max(maxDiff, ...); // Only modifies the local copy in this stack frame!
}
```

#### How to Share and Track State Across Recursive Calls:
1. **Approach A: Instance Variable (Recommended & Clean)**
   ```java
   public class Solution {
       private int maxDiff = Integer.MIN_VALUE; // Belongs to this Solution object
       
       public int maxDiff(Node root) {
           maxDiff = Integer.MIN_VALUE;
           helper(root);
           return maxDiff;
       }
   }
   ```
2. **Approach B: 1-Element Array as Mutable Reference**
   ```java
   int[] maxDiff = new int[]{Integer.MIN_VALUE};
   helper(root, maxDiff); // Array reference is passed by value, but elements are mutated!
   ```
3. **Why avoid `static int maxDiff` on platforms like LeetCode/GfG?**
   - `static` variables persist across multiple test cases run in the same JVM process.
   - If not reset explicitly before every test case, values from previous test cases will pollute the next test case!

---

## 💻 Complete Java Implementation

```java
/*
class Node {
    int data;
    Node left, right;

    Node(int item) {
        data = item;
        left = right = null;
    }
}
*/

class Solution {
    // Instance variable to store the running global maximum difference
    private int maxDiff;

    /**
     * Helper function that returns the minimum node value in the subtree rooted at 'root'
     * while updating maxDiff at every internal node.
     */
    private int findMin(Node root) {
        // Base Case 1: Empty node contributes infinity (neutral for Math.min)
        if (root == null) {
            return Integer.MAX_VALUE;
        }

        // Base Case 2: Leaf node cannot be an ancestor (has no descendants)
        // Return its value as the minimum of its own leaf subtree
        if (root.left == null && root.right == null) {
            return root.data;
        }

        // Recursive Step: Post-order traversal (Bottom-Up)
        int leftMin = findMin(root.left);
        int rightMin = findMin(root.right);

        // Minimum descendant among all nodes in left and right subtrees
        int minDescendant = Math.min(leftMin, rightMin);

        // Update maximum difference with current node as the ancestor
        maxDiff = Math.max(maxDiff, root.data - minDescendant);

        // Return minimum value in the entire subtree rooted at current node
        return Math.min(root.data, minDescendant);
    }

    public int maxDiff(Node root) {
        // Initialize maxDiff
        maxDiff = Integer.MIN_VALUE;

        // Traverse the tree
        findMin(root);

        return maxDiff;
    }
}
```

---

## 💻 C++ Implementation (for Reference)

```cpp
#include <iostream>
#include <algorithm>
#include <climits>
using namespace std;

struct Node {
    int data;
    Node* left;
    Node* right;
    Node(int val) : data(val), left(nullptr), right(nullptr) {}
};

class Solution {
    int maxDiff;

    int findMin(Node* root) {
        if (!root) return INT_MAX;
        
        // Leaf node
        if (!root->left && !root->right) return root->data;

        int leftMin = findMin(root->left);
        int rightMin = findMin(root->right);

        int minDescendant = min(leftMin, rightMin);

        // Update max difference
        maxDiff = max(maxDiff, root->data - minDescendant);

        return min(root->data, minDescendant);
    }

public:
    int maxDiff(Node* root) {
        maxDiff = INT_MIN;
        findMin(root);
        return maxDiff;
    }
};
```

---

## 🧪 Dry Run Execution Table

Given Tree:
```
       8
     /   \
    3     10
```

| Node Visited | Left Min | Right Min | `minDescendant` | Calculation (`root.data - minDescendant`) | `maxDiff` | Subtree Return Value `min(root.data, minDescendant)` |
|---|---|---|---|---|---|---|
| `3` (Leaf) | - | - | - | *(Leaf, skipped)* | `INT_MIN` | `3` |
| `10` (Leaf) | - | - | - | *(Leaf, skipped)* | `INT_MIN` | `10` |
| `8` (Root) | `3` | `10` | `min(3, 10) = 3` | $8 - 3 = 5$ | $\max(\text{MIN}, 5) = \mathbf{5}$ | $\min(8, 3) = 3$ |

**Final Result:** `5`

---

## ⏱️ Complexity Analysis

| Metric | Complexity | Explanation |
|---|---|---|
| **Time Complexity** | $\mathcal{O}(N)$ | Every node in the binary tree of $N$ nodes is visited exactly once. |
| **Space Complexity** | $\mathcal{O}(H)$ | Auxiliary space on the call stack proportional to tree height $H$ ($\mathcal{O}(\log N)$ for balanced, $\mathcal{O}(N)$ for skewed). |
