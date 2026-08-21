# LeetCode Problem Solutions & Algorithmic Deep Dives

This directory contains in-depth solutions, algorithmic breakdowns, design patterns, and complexity analyses for LeetCode challenges across various domains including **Dynamic Programming**, **Game Theory**, **Greedy Algorithms**, and **Combinatorics**.

---

## 📚 Problem Index

| # | Problem | Difficulty | Category / Concepts | Write-up | Java Solution |
|---|---|---|---|---|---|
| **1563** | **Stone Game V** | Hard | Dynamic Programming, Interval DP, Prefix Sum | [1563_StoneGameV.md](./1563_StoneGameV.md) | [StoneGameV.java](./StoneGameV.java) |
| **1663** | **Smallest String With A Given Numeric Value** | Medium | Greedy, Suffix Fill, String Manipulation | [1663_SmallestStringWithAGivenNumericValue.md](./1663_SmallestStringWithAGivenNumericValue.md) | [SmallestStringWithAGivenNumericValue.java](./SmallestStringWithAGivenNumericValue.java) |
| **2029** | **Stone Game IX** | Medium | Game Theory, Modulo Arithmetic, Greedy | [2029_StoneGameIX.md](./2029_StoneGameIX.md) | [StoneGameIX.java](./StoneGameIX.java) |
| **3069** | **Distribute Elements Into Two Arrays I** | Easy | Array Simulation, Dynamic Arrays (`ArrayList`) | [3069_DistributeElementsIntoTwoArraysI.md](./3069_DistributeElementsIntoTwoArraysI.md) | [DistributeElementsIntoTwoArraysI.java](./DistributeElementsIntoTwoArraysI.java) |
| **3116** | **Kth Smallest Amount With Single Denomination Combination** | Hard | Binary Search on Answer, Principle of Inclusion-Exclusion, Bitmasking, Number Theory (LCM/GCD) | [3116_KthSmallestAmountWithSingleDenominationCombination.md](./3116_KthSmallestAmountWithSingleDenominationCombination.md) | [KthSmallestAmountWithSingleDenominationCombination.java](./KthSmallestAmountWithSingleDenominationCombination.java) |

---

## 🧠 Master Core Concepts

```
                               ┌────────────────────────┐
                               │  Algorithmic Paradigms │
                               └───────────┬────────────┘
              ┌────────────────────────────┼────────────────────────────┐
              ▼                            ▼                            ▼
   ┌───────────────────────┐   ┌───────────────────────┐   ┌───────────────────────┐
   │  Dynamic Programming  │   │  Binary Search on Ans │   │     Greedy Method     │
   │  - Interval DP        │   │  - Monotonicity       │   │  - Local Optima       │
   │  - Prefix Sum Accel   │   │  - Inclusion-Exclusion│   │  - Backward Filling   │
   │  (e.g., LC 1563)      │   │  (e.g., LC 3116)      │   │  (e.g., LC 1663)      │
   └───────────────────────┘   └───────────────────────┘   └───────────────────────┘
```

---

### 1. Dynamic Programming (DP) — Comprehensive Guide

Dynamic Programming solves complex problems by breaking them down into **overlapping subproblems** and storing the results to prevent redundant calculations (**memoization** or **tabulation**).

#### When is a problem solvable with DP?
1. **Optimal Substructure:** An optimal solution to the problem contains within it optimal solutions to subproblems.
2. **Overlapping Subproblems:** The recursive solution visits the same subproblems repeatedly rather than generating new subproblems at each step.

#### Approaches: Top-Down vs. Bottom-Up
| Feature | Top-Down (Memoization) | Bottom-Up (Tabulation) |
|---|---|---|
| **Mechanism** | Recursion + Lookup Table/Map | Iterative loops filling a table |
| **State Exploration** | Computes only required states | Computes all table states in topological order |
| **Stack Overhead** | Call stack space $\mathcal{O}(\text{depth})$ | $\mathcal{O}(1)$ stack overhead |
| **Space Optimization** | Difficult to optimize | Often can compress dimensions ($\mathcal{O}(N^2) \to \mathcal{O}(N)$) |

#### Major DP Patterns:
1. **1D Linear DP:** Fibonacci, House Robber, Climbing Stairs ($\text{dp}[i] = f(\text{dp}[i-1], \text{dp}[i-2])$)
2. **2D Grid / Matrix DP:** Unique Paths, Minimum Path Sum, Longest Common Subsequence ($\text{dp}[i][j]$)
3. **0/1 and Unbounded Knapsack:** Subset Sum, Coin Change, Target Sum
4. **Interval DP (e.g., [LeetCode 1563: Stone Game V](./1563_StoneGameV.md)):**
   - Solves subproblems defined over subarrays/intervals $[i, j]$.
   - Iterates over all possible partition points $k \in [i, j-1]$:
     $$\text{dp}[i][j] = \max_{i \le k < j} \left( \text{gain}(i, k, j) + \text{dp}[\text{subproblem}] \right)$$
   - Accelerated using **Prefix Sums** to calculate any subarray sum $\sum_{m=i}^{j} \text{arr}[m] = \text{pref}[j+1] - \text{pref}[i]$ in $\mathcal{O}(1)$.

---

### 2. Binary Search on Answer + Inclusion-Exclusion Principle (PIE)

Used in **[LeetCode 3116](./3116_KthSmallestAmountWithSingleDenominationCombination.md)**:
1. **Monotonicity Criterion:** If asking "how many valid values exist $\le M$?" yields a function $f(M)$ that is monotonically non-decreasing, the optimal answer can be located in $\mathcal{O}(\log(\text{range}))$ binary search steps.
2. **Principle of Inclusion-Exclusion (PIE):**
   When multiple coin denominations share common multiples (e.g., $2$ and $5$ share $10$), naive addition results in duplicates:
   $$|A \cup B \cup C| = (|A| + |B| + |C|) - (|A \cap B| + |B \cap C| + |A \cap C|) + |A \cap B \cap C|$$
3. **Bitmask Subset Enumeration:** Using numbers $1 \dots 2^N - 1$ as binary masks where bit $i = 1$ indicates inclusion of coin $i$, with subset least common multiple ($\text{LCM}$) calculated via the Euclidean GCD algorithm:
   $$\text{LCM}(a, b) = \frac{a \times b}{\text{GCD}(a, b)}$$

---

### 3. Game Theory & Modulo Arithmetic

Used in **[LeetCode 2029: Stone Game IX](./2029_StoneGameIX.md)**:
- Reduces huge continuous numerical states into discrete equivalence classes modulo 3:
  - Count of stones where $\text{val} \equiv 0 \pmod 3 \implies c_0$ (neutral toggle / pass moves)
  - Count of stones where $\text{val} \equiv 1 \pmod 3 \implies c_1$
  - Count of stones where $\text{val} \equiv 2 \pmod 3 \implies c_2$
- Player choices are constrained because any pick resulting in $\text{sum} \equiv 0 \pmod 3$ loses immediately.

---

### 4. Greedy Method & Suffix Optimization

Used in **[LeetCode 1663: Smallest String With A Given Numeric Value](./1663_SmallestStringWithAGivenNumericValue.md)**:
- When seeking the **lexicographically smallest string**, priority is given to making earlier characters as small as possible (ideally `'a'`).
- The remaining numerical deficit is allocated greedily from the rightmost character backwards (allocating up to $+25$ or `'z'`).

---

## 🔍 Problem Summaries

### 1. [LeetCode 1563: Stone Game V](./1563_StoneGameV.md)
- **Goal:** Find the maximum score Alice can achieve by partitioning rows of stones where Bob discards the larger half (or Alice picks if equal).
- **Technique:** Interval DP with memoization + Prefix Sum table.
- **Complexity:** $\mathcal{O}(N^3)$ Time | $\mathcal{O}(N^2)$ Space

### 2. [LeetCode 1663: Smallest String With A Given Numeric Value](./1663_SmallestStringWithAGivenNumericValue.md)
- **Goal:** Build the lexicographically smallest string of length $n$ with numeric sum $k$.
- **Technique:** Fill with `'a'`, greedily place remaining weight from the right.
- **Complexity:** $\mathcal{O}(n)$ Time | $\mathcal{O}(n)$ Space

### 3. [LeetCode 2029: Stone Game IX](./2029_StoneGameIX.md)
- **Goal:** Determine if Alice can force a win in a turn-based modulo 3 removal game.
- **Technique:** Modulo 3 frequency grouping + Game Theory invariant analysis.
- **Complexity:** $\mathcal{O}(N)$ Time | $\mathcal{O}(1)$ Space

### 4. [LeetCode 3069: Distribute Elements Into Two Arrays I](./3069_DistributeElementsIntoTwoArraysI.md)
- **Goal:** Distribute elements based on the values of the last inserted elements.
- **Technique:** Dynamic array list simulation / two-pointer buffers.
- **Complexity:** $\mathcal{O}(N)$ Time | $\mathcal{O}(N)$ Space

### 5. [LeetCode 3116: Kth Smallest Amount With Single Denomination Combination](./3116_KthSmallestAmountWithSingleDenominationCombination.md)
- **Goal:** Find the $k^{\text{th}}$ smallest amount divisible by at least one coin denomination.
- **Technique:** Binary Search on answer + Bitmask Inclusion-Exclusion with LCM.
- **Complexity:** $\mathcal{O}(2^N \log(\min(\text{coins}) \cdot k))$ Time | $\mathcal{O}(1)$ Space
