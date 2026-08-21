# LeetCode 3116: Kth Smallest Amount With Single Denomination Combination

## 📌 Problem Overview
You are given an integer array `coins` representing coin denominations of various values and an integer `k`.

You have an infinite number of coins of each denomination. However, you are **not allowed to combine coins of different denominations**.

Return the **$k^{\text{th}}$ smallest amount** that can be made using any combination of single denominations (i.e., amounts that are positive integer multiples of at least one coin denomination in `coins`).

---

## 💡 Key Insights & Intuition

### 1. Rephrasing the Problem
Any amount produced by a single denomination $c$ is a multiple of $c$ ($1c, 2c, 3c, \dots$).
Thus, an amount $X$ is valid if and only if:
$$\exists \, c \in \text{coins} \quad \text{such that} \quad X \pmod c = 0$$

We want to find the $k^{\text{th}}$ positive integer that is divisible by at least one element in `coins`.

### 2. Monotonicity & Binary Search on Answer
Let $f(M)$ be the number of positive integers $\le M$ that are divisible by at least one coin in `coins`.
- If $M_1 < M_2$, then $f(M_1) \le f(M_2)$ (strictly non-decreasing monotonic function).
- This allows us to use **Binary Search on the Answer Range**:
  - **Lower bound:** `low = 1`
  - **Upper bound:** `high = min(coins) * k` (In the worst case where only the smallest coin exists, the $k^{\text{th}}$ multiple is $\min(\text{coins}) \times k$).

### 3. Counting Multiples with Principle of Inclusion-Exclusion (PIE)
To compute $f(M)$ without duplicates:
- If we have 2 coins $\{2, 5\}$ and $M = 12$:
  - Multiples of 2: $\lfloor 12 / 2 \rfloor = 6$
  - Multiples of 5: $\lfloor 12 / 5 \rfloor = 2$
  - Multiples of both $\text{lcm}(2, 5) = 10$: $\lfloor 12 / 10 \rfloor = 1$
  - Total unique multiples $= 6 + 2 - 1 = 7$.

For $N$ coins, we iterate through all $2^N - 1$ non-empty subsets $S \subseteq \text{coins}$:
$$f(M) = \sum_{\emptyset \ne S \subseteq \text{coins}} (-1)^{|S| - 1} \cdot \left\lfloor \frac{M}{\text{LCM}(S)} \right\rfloor$$

- If $|S|$ is **odd**, **add** $\lfloor M / \text{LCM}(S) \rfloor$.
- If $|S|$ is **even**, **subtract** $\lfloor M / \text{LCM}(S) \rfloor$.

Since $N \le 15$, $2^{15} = 32,768$ subsets, which is very small and fast to compute per binary search step!

---

## 🛠️ Java Implementation

```java
import java.util.Arrays;

public class KthSmallestAmountWithSingleDenominationCombination {

    public static long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        
        // Find minimum coin to bound search space
        long minCoin = coins[0];
        for (int coin : coins) {
            minCoin = Math.min(minCoin, coin);
        }
        
        long low = 1;
        long high = minCoin * k;
        long ans = high;
        
        while (low <= high) {
            long mid = low + (high - low) / 2;
            
            if (countMultiples(mid, coins) >= k) {
                ans = mid;
                high = mid - 1; // Try smaller value
            } else {
                low = mid + 1;  // Need more multiples
            }
        }
        
        return ans;
    }

    private static long countMultiples(long target, int[] coins) {
        int n = coins.length;
        long totalCount = 0;
        int totalSubsets = 1 << n;
        
        for (int mask = 1; mask < totalSubsets; mask++) {
            long currentLcm = 1;
            int bitCount = 0;
            boolean overflow = false;
            
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    bitCount++;
                    currentLcm = lcm(currentLcm, coins[i]);
                    if (currentLcm > target) {
                        overflow = true;
                        break;
                    }
                }
            }
            
            if (overflow) continue;
            
            long count = target / currentLcm;
            if (bitCount % 2 == 1) {
                totalCount += count;
            } else {
                totalCount -= count;
            }
        }
        
        return totalCount;
    }

    private static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private static long lcm(long a, long b) {
        if (a == 0 || b == 0) return 0;
        return (a / gcd(a, b)) * b;
    }
}
```

---

## ⏱️ Complexity Analysis

- **Time Complexity:**
  - Binary search range: $\log_2(\min(\text{coins}) \times k) \approx \log_2(25 \times 2 \cdot 10^9) \approx 36$ steps.
  - Per step: $2^N$ subset evaluations, with $N \le 15 \implies 2^{15} = 32,768$ operations.
  - Overall Time: $\mathcal{O}(2^N \cdot \log(\min(\text{coins}) \cdot k)) \approx 36 \times 32768 \approx 1.1 \times 10^6$ ops $\implies$ **< 30ms (Optimal)**.
- **Space Complexity:** $\mathcal{O}(1)$ auxiliary space.
