# GeeksforGeeks Problem Solutions & Deep Dives

This directory contains comprehensive solutions, visual explanations, language mechanic deep-dives, and complexity breakdowns for GeeksforGeeks problems.

---

## 📚 Problem Index

| # | Problem | Difficulty | Key Topics / Techniques | Solution & Analysis | Java Code |
|---|---|---|---|---|---|
| **1** | **Maximum Difference Between Node and Its Ancestor** | Medium | Binary Tree, Post-Order Traversal, Bottom-Up Recursion | [MaximumDifferenceBetweenNodeAndItsAncestor.md](./MaximumDifferenceBetweenNodeAndItsAncestor.md) | [MaximumDifferenceBetweenNodeAndItsAncestor.java](./MaximumDifferenceBetweenNodeAndItsAncestor.java) |
| **2** | **Transform String** | Medium | Greedy, Suffix Matching, Two-Pointer, Frequency Hashing | [TransformString.md](./TransformString.md) | [TransformString.java](./TransformString.java) |

---

## 🧠 Master Core Concepts

### 1. Greedy Backward Suffix Matching (`Transform String`)
When a problem allows moving any element to the **front** of a string or array:
- Moving an element to the front preserves the relative ordering of all remaining elements behind it.
- To minimize moves, we find the **longest common subsequence of characters ending at the last position** (suffix matching).
- By traversing from right to left ($i = |A|-1, j = |B|-1$), if $A[i] == B[j]$, both pointers advance backward. If $A[i] \ne B[j]$, only $A$'s pointer decrements and operation count increments because that character in $A$ must be relocated to the front.

### 2. Frequency Hashing & Multiset Validity
Before attempting any structural transformation on strings:
- Verify that character counts in $A$ match $B$ identically using an array `int[256]` or hash map.
- If the frequency distribution does not match or lengths differ, transformation is impossible ($\implies -1$).

### 3. Tree Recursion & Bottom-Up Subtree Aggregation
In binary tree problems such as **Maximum Difference Between Node and Ancestor**:
- A **post-order (bottom-up)** traversal allows child subtrees to return their minimum and maximum values to the parent node.
- The parent node compares its own value with the minimum and maximum of its descendants to update a global maximum difference before returning the new subtree bounds up to its own ancestors.
