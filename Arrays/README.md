# Array Data Structures & Algorithms

This directory contains solved problems, comprehensive write-ups, code implementations, and complexity analyses for core array algorithms and LeetCode challenges.

---

## Problem Index

| # | Problem | Difficulty | Key Concepts | Documentation | Java Code |
|---|---|---|---|---|---|
| 1 | **Set Matrix Zeroes** | Medium | Matrix Traversal, Row/Col Flagging, Space Optimization | [SetMatrixZeroes.md](./SetMatrixZeroes.md) | [SetMatrixZeroes.java](./SetMatrixZeroes.java) |
| 2 | **Next Permutation** | Medium | Lexicographical Order, Two Pointers, Array Inversion | [NextPermutation.md](./NextPermutation.md) | [NextPermutation.java](./NextPermutation.java) |
| 3 | **Pascal's Triangle** | Easy / Medium | Dynamic Programming, Combinatorics, Row Generation | [PascalsTriangle.md](./PascalsTriangle.md) | [PascalsTriangle.java](./PascalsTriangle.java) |

---

## Highlights & Approaches

### 1. Set Matrix Zeroes
- **Goal:** If `matrix[i][j] == 0`, set its entire row $i$ and column $j$ to $0$.
- **Approaches:**
  - *Hash Table / Map based:* $O(M \times N)$ time, $O(M + N)$ extra space.
  - *In-place Flagging (Optimal):* Use row 0 and col 0 as flags for $O(1)$ space.

### 2. Next Permutation
- **Goal:** Rearrange numbers into the lexicographically next greater permutation.
- **Algorithm (3 Steps):**
  1. Find the first decreasing element from the right (`break point`).
  2. Find the smallest element greater than `nums[break_point]` to the right and swap.
  3. Reverse the subarray to the right of `break_point`.
- **Complexity:** $O(N)$ time, $O(1)$ space.

### 3. Pascal's Triangle
- **Goal:** Generate rows where each number is the sum of the two numbers directly above it.
- **Formulas:**
  - Finding value at $(r, c)$: $\binom{r-1}{c-1} = \frac{(r-1)!}{(c-1)! \times (r-c)!}$
  - Generating row $n$ in $O(N)$ time without factorials: $\text{ans} = \text{ans} \times \frac{\text{row} - \text{col}}{\text{col}}$.
