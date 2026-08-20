# LeetCode Problem Solutions & Algorithms

This directory contains in-depth solutions, algorithmic breakdowns, and complexity analyses for LeetCode challenges.

---

## 📚 Problem Index

| # | Problem | Difficulty | Category / Concepts | Write-up | Java Solution |
|---|---|---|---|---|---|
| **1563** | **Stone Game V** | Hard | Dynamic Programming, Interval DP, Prefix Sum | [1563_StoneGameV.md](./1563_StoneGameV.md) | [StoneGameV.java](./StoneGameV.java) |
| **1663** | **Smallest String With A Given Numeric Value** | Medium | Greedy, String Manipulation, Math | [1663_SmallestStringWithAGivenNumericValue.md](./1663_SmallestStringWithAGivenNumericValue.md) | [SmallestStringWithAGivenNumericValue.java](./SmallestStringWithAGivenNumericValue.java) |
| **2029** | **Stone Game IX** | Medium | Game Theory, Modulo Arithmetic, Greedy | [2029_StoneGameIX.md](./2029_StoneGameIX.md) | [StoneGameIX.java](./StoneGameIX.java) |
| **3069** | **Distribute Elements Into Two Arrays I** | Easy | Array, Simulation, Dynamic Arrays (`ArrayList`) | [3069_DistributeElementsIntoTwoArraysI.md](./3069_DistributeElementsIntoTwoArraysI.md) | [DistributeElementsIntoTwoArraysI.java](./DistributeElementsIntoTwoArraysI.java) |

---

## 🔍 Problem Summaries & Highlights

### 1. [LeetCode 1563: Stone Game V](./1563_StoneGameV.md)
- **Goal:** Find the maximum score Alice can achieve by partitioning rows of stones where Bob discards the larger half (or Alice picks if equal).
- **Key Insight:** Interval Dynamic Programming with memoization $\text{dp}[i][j] = \max \text{score}$ across all split points $k$. Prefix sums compute range sums in $\mathcal{O}(1)$.
- **Complexity:** $\mathcal{O}(N^3)$ Time | $\mathcal{O}(N^2)$ Space

### 2. [LeetCode 1663: Smallest String With A Given Numeric Value](./1663_SmallestStringWithAGivenNumericValue.md)
- **Goal:** Find the lexicographically smallest string of length $n$ whose character values sum up to $k$.
- **Key Insight:** Fill all $n$ positions with `'a'` (sum = $n$), then greedily distribute the remaining value $(k - n)$ from the rightmost character backwards, allocating up to $+25$ (i.e. `'z'`) per position.
- **Complexity:** $\mathcal{O}(n)$ Time | $\mathcal{O}(n)$ Space

### 3. [LeetCode 2029: Stone Game IX](./2029_StoneGameIX.md)
- **Goal:** Determine if Alice can win a two-player turn-based game where picking a stone that causes the cumulative sum to be divisible by 3 loses.
- **Key Insight:** Categorize stones by remainder modulo 3 ($c_0, c_1, c_2$). The game reduces to:
  - When $c_0$ is even: Alice wins if $c_1 \ge 1$ and $c_2 \ge 1$.
  - When $c_0$ is odd: Alice wins if $|c_1 - c_2| > 2$.
- **Complexity:** $\mathcal{O}(N)$ Time | $\mathcal{O}(1)$ Space

### 4. [LeetCode 3069: Distribute Elements Into Two Arrays I](./3069_DistributeElementsIntoTwoArraysI.md)
- **Goal:** Distribute elements of an array into two separate arrays by comparing their last elements, then concatenate them.
- **Key Insight:** Compare the last inserted values using dynamic lists (`ArrayList`) or two pre-allocated fixed buffers with running size pointers.
- **Complexity:** $\mathcal{O}(N)$ Time | $\mathcal{O}(N)$ Space

