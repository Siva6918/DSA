# GeeksforGeeks: Transform String

## 📌 Problem Overview
Given two strings $A$ and $B$, find the **minimum number of operations** required to convert string $A$ into string $B$.

### Allowed Operation:
- Pick any single character in string $A$ and insert it at the **very front** of string $A$.
- If it is impossible to transform $A$ into $B$, return **`-1`**.

🔗 **Problem Link:** [GeeksforGeeks — Transform String](https://www.geeksforgeeks.org/problems/transform-string5648/1)

---

## 💡 Key Insights & Intuition

### 1. Possibility Condition (Anagram & Length Matching)
Since we can only rearrange existing characters in $A$ without adding or modifying characters:
- $|A|$ must equal $|B|$.
- The multiset of characters in $A$ must be identical to that in $B$ (both strings must have identical character frequency counts).
- If either condition fails, transformation is impossible $\implies$ return `-1`.

### 2. Why Greedy from Right to Left (Suffix Matching)?
When you pick a character and place it at the **front**, all other characters naturally shift rightward while preserving their relative order.
- To minimize the number of operations, we should keep as many characters as possible in their correct final positions without moving them.
- Because characters move to the **front**, characters that end up at the **end** (suffix) of $B$ should be matched first from $A$.
- Any character in $A$ that does not match the current suffix character of $B$ will inevitably have to be pulled out and moved to the front.

---

## 🔍 Step-by-Step Algorithm
1. Check if lengths are equal. If not, return `-1`.
2. Compute character frequencies using an array `int[256]`. If any frequency $\ne 0$, return `-1`.
3. Initialize pointers:
   - $i = |A| - 1$
   - $j = |B| - 1$
   - `operations = 0`
4. Traverse backwards while $i \ge 0$ and $j \ge 0$:
   - If $A[i] == B[j]$, this character is in the correct relative position for the matched suffix $\implies i--, j--$.
   - If $A[i] \ne B[j]$, $A[i]$ must be moved to the front at some step $\implies \text{operations}++, i--$.
5. Return `operations`.

---

## 🛠️ Java Implementation

```java
public class TransformString {

    public static int transform(String A, String B) {
        if (A.length() != B.length()) {
            return -1;
        }

        // Anagram / Frequency Check
        int[] freq = new int[256];
        for (int i = 0; i < A.length(); i++) {
            freq[A.charAt(i)]++;
            freq[B.charAt(i)]--;
        }

        for (int count : freq) {
            if (count != 0) {
                return -1;
            }
        }

        // Backward Two-Pointer Greedy Matching
        int i = A.length() - 1;
        int j = B.length() - 1;
        int operations = 0;

        while (i >= 0 && j >= 0) {
            if (A.charAt(i) == B.charAt(j)) {
                i--;
                j--;
            } else {
                operations++;
                i--;
            }
        }

        return operations;
    }
}
```

---

## ⏱️ Complexity Analysis

- **Time Complexity:** $\mathcal{O}(N)$ where $N = |A| = |B|$. A single frequency pass and a single backward two-pointer pass.
- **Space Complexity:** $\mathcal{O}(1)$ auxiliary space (constant size array of 256 integers).
