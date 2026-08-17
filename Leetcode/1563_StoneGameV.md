## 1563. Stone Game V

---

**LeetCode URL:** https://leetcode.com/problems/stone-game-v/

**Difficulty:** Hard | **Category:** Dynamic Programming / Interval DP / Prefix Sum / Game Theory

---

### Problem Statement

There are several stones arranged in a row, and each stone has an associated value given in an integer array `stoneValue`.

In each round of the game, Alice divides the row into two non-empty parts (left row and right row):
- Bob calculates the sum of stone values in each part.
- Bob throws away the row that has the **maximum value**, and Alice's score increases by the value of the **remaining row**.
- If the values of the two rows are **equal**, Bob allows Alice to decide which row to throw away.
- The game ends when only **1 stone remains**. Alice's score starts at `0`.

Return the **maximum score** that Alice can obtain.

---

### Constraints

- $1 \le \text{stoneValue.length} \le 500$
- $1 \le \text{stoneValue}[i] \le 10^6$

---

### Examples

**Example 1:**
```text
Input: stoneValue = [6,2,3,4,5,5]
Output: 18
Explanation:
- Round 1: Alice divides row into [6,2,3] (sum 11) and [4,5,5] (sum 14). Bob discards right row. Alice gains 11. Row remaining: [6,2,3].
- Round 2: Alice divides [6,2,3] into [6] (sum 6) and [2,3] (sum 5). Bob discards left row. Alice gains 5. Row remaining: [2,3].
- Round 3: Alice divides [2,3] into [2] (sum 2) and [3] (sum 3). Bob discards right row. Alice gains 2. Row remaining: [2].
- Total score = 11 + 5 + 2 = 18.
```

**Example 2:**
```text
Input: stoneValue = [7,7,7,7,7,7,7]
Output: 28
```

**Example 3:**
```text
Input: stoneValue = [4]
Output: 0
```

---

### Intuition & Approach (Interval Dynamic Programming)

This problem exhibits **optimal substructure** and **overlapping subproblems**, making it a classic candidate for **Interval Dynamic Programming** (or Memoized Top-Down Recursion).

#### State Definition:
Let `dp[i][j]` represent the maximum score Alice can earn from the subarray `stoneValue[i...j]`.

#### Base Case:
- When $i == j$ (a single stone), no further partition can be made $\implies \text{dp}[i][j] = 0$.

#### Recurrence Relation:
For any interval $[i, j]$, Alice can choose a split point $k$ where $i \le k < j$:
- $\text{leftSum} = \sum_{m=i}^{k} \text{stoneValue}[m]$
- $\text{rightSum} = \sum_{m=k+1}^{j} \text{stoneValue}[m]$

Depending on the sums:
1. **$\text{leftSum} < \text{rightSum}$:**
   Bob throws away the right row. Alice takes the left row:
   $$\text{score} = \text{leftSum} + \text{dp}[i][k]$$
2. **$\text{leftSum} > \text{rightSum}$:**
   Bob throws away the left row. Alice takes the right row:
   $$\text{score} = \text{rightSum} + \text{dp}[k+1][j]$$
3. **$\text{leftSum} == \text{rightSum}$:**
   Bob lets Alice choose which side to keep to maximize her future earnings:
   $$\text{score} = \text{leftSum} + \max(\text{dp}[i][k],\, \text{dp}[k+1][j])$$

Alice maximizes over all possible split points $k \in [i, j-1]$:
$$\text{dp}[i][j] = \max_{i \le k < j} (\text{score for split } k)$$

---

### Solution Implementations

#### Java (Top-Down with Memoization & Prefix Sums)
```java
class Solution {
    private int[][] memo;
    private int[] prefix;

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        memo = new int[n][n];
        prefix = new int[n + 1];
        
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }
        
        return solve(0, n - 1);
    }

    private int solve(int i, int j) {
        if (i >= j) return 0;
        if (memo[i][j] != 0) return memo[i][j];

        int maxScore = 0;

        for (int k = i; k < j; k++) {
            int leftSum = prefix[k + 1] - prefix[i];
            int rightSum = prefix[j + 1] - prefix[k + 1];

            if (leftSum < rightSum) {
                maxScore = Math.max(maxScore, leftSum + solve(i, k));
            } else if (leftSum > rightSum) {
                maxScore = Math.max(maxScore, rightSum + solve(k + 1, j));
            } else {
                int leftOption = leftSum + solve(i, k);
                int rightOption = rightSum + solve(k + 1, j);
                maxScore = Math.max(maxScore, Math.max(leftOption, rightOption));
            }
        }

        return memo[i][j] = maxScore;
    }
}
```

#### Python 3 (Top-Down with `@cache` & Prefix Sums)
```python
from functools import lru_cache

class Solution:
    def stoneGameV(self, stoneValue: list[int]) -> int:
        n = len(stoneValue)
        prefix = [0] * (n + 1)
        for idx, val in enumerate(stoneValue):
            prefix[idx + 1] = prefix[idx] + val

        @lru_cache(maxsize=None)
        def solve(i: int, j: int) -> int:
            if i >= j:
                return 0
            
            max_score = 0
            for k in range(i, j):
                left_sum = prefix[k + 1] - prefix[i]
                right_sum = prefix[j + 1] - prefix[k + 1]

                if left_sum < right_sum:
                    max_score = max(max_score, left_sum + solve(i, k))
                elif left_sum > right_sum:
                    max_score = max(max_score, right_sum + solve(k + 1, j))
                else:
                    left_option = left_sum + solve(i, k)
                    right_option = right_sum + solve(k + 1, j)
                    max_score = max(max_score, max(left_option, right_option))

            return max_score

        return solve(0, n - 1)
```

#### C++
```cpp
class Solution {
public:
    int memo[501][501];
    int prefix[501];

    int solve(int i, int j) {
        if (i >= j) return 0;
        if (memo[i][j] != -1) return memo[i][j];

        int maxScore = 0;
        for (int k = i; k < j; ++k) {
            int leftSum = prefix[k + 1] - prefix[i];
            int rightSum = prefix[j + 1] - prefix[k + 1];

            if (leftSum < rightSum) {
                maxScore = max(maxScore, leftSum + solve(i, k));
            } else if (leftSum > rightSum) {
                maxScore = max(maxScore, rightSum + solve(k + 1, j));
            } else {
                int leftOpt = leftSum + solve(i, k);
                int rightOpt = rightSum + solve(k + 1, j);
                maxScore = max(maxScore, max(leftOpt, rightOpt));
            }
        }

        return memo[i][j] = maxScore;
    }

    int stoneGameV(vector<int>& stoneValue) {
        int n = stoneValue.size();
        memset(memo, -1, sizeof(memo));
        prefix[0] = 0;
        for (int i = 0; i < n; ++i) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }
        return solve(0, n - 1);
    }
};
```

---

### Complexity Analysis

- **Time Complexity:** $\mathcal{O}(N^3)$ — There are $\mathcal{O}(N^2)$ distinct intervals $(i, j)$. For each interval, we evaluate $k$ from $i$ to $j - 1$ ($\mathcal{O}(N)$ transitions). Prefix sums allow $\mathcal{O}(1)$ range sum evaluations.
- **Space Complexity:** $\mathcal{O}(N^2)$ — For the memoization table / recursion stack.

---

### Key Takeaways
- Prefix sums reduce subarray sum calculations from $\mathcal{O}(N)$ to $\mathcal{O}(1)$ per split.
- When sums are equal, testing both choices is essential to find the global optimum.
