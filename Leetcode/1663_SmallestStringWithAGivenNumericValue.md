## 1663. Smallest String With A Given Numeric Value

---

**LeetCode URL:** https://leetcode.com/problems/smallest-string-with-a-given-numeric-value/

**Difficulty:** Medium | **Category:** Greedy / String Manipulation / Math

---

### Problem Statement

The **numeric value** of a lowercase character is defined as its position (1-indexed) in the alphabet, so the numeric value of `'a'` is `1`, `'b'` is `2`, `'c'` is `3`, and so on up to `'z'` which is `26`.

The **numeric value of a string** consisting of lowercase characters is defined as the sum of its characters' numeric values. For example, the numeric value of the string `"abe"` is equal to `1 + 2 + 5 = 8`.

You are given two integers `n` and `k`. Return the **lexicographically smallest string** with length equal to `n` and numeric value equal to `k`.

---

### Constraints

- $1 \le n \le 10^5$
- $n \le k \le 26 \times n$

---

### Examples

**Example 1:**
```text
Input: n = 3, k = 27
Output: "aay"
Explanation: The numeric value of the string is 1 + 1 + 25 = 27, and it is the smallest string with such a value and length equal to 3.
```

**Example 2:**
```text
Input: n = 5, k = 73
Output: "aaszz"
Explanation: The numeric value of the string is 1 + 1 + 19 + 26 + 26 = 73.
```

---

### Intuition & Approach (Greedy)

To make the resulting string **lexicographically smallest**, we want characters at the beginning of the string to have the lowest possible values (ideally `'a'` $= 1$), while characters toward the end absorb as much of the remaining sum as possible (up to `'z'` $= 26$).

#### Step-by-Step Strategy:
1. **Initialize:** Start by filling all $n$ positions with the character `'a'`.
   - Each `'a'` has a value of `1`.
   - The total initial value used is $n$.
   - The remaining value to distribute is `k - n`.
2. **Greedy Assignment from Right to Left:**
   - Iterate backwards from index $n - 1$ down to $0$.
   - At each index $i$, the maximum additional value we can add to `'a'` without exceeding `'z'` (26) is $\min(25, \text{remaining } k)$.
   - Add this value to `'a'` (`'a' + add`) and decrement $\text{remaining } k$ by `add`.
   - If $\text{remaining } k$ becomes $0$, we can stop early.
3. **Convert and Return:** Return the constructed character array as a string.

---

### Solution Implementations

#### Java
```java
class Solution {
    public String getSmallestString(int n, int k) {
        char[] result = new char[n];
        Arrays.fill(result, 'a');
        
        int remaining = k - n; // Each 'a' already accounts for 1 unit
        
        for (int i = n - 1; i >= 0 && remaining > 0; i--) {
            int add = Math.min(25, remaining);
            result[i] = (char) ('a' + add);
            remaining -= add;
        }
        
        return new String(result);
    }
}
```

#### Python 3
```python
class Solution:
    def getSmallestString(self, n: int, k: int) -> str:
        result = ['a'] * n
        remaining = k - n
        
        for i in range(n - 1, -1, -1):
            if remaining <= 0:
                break
            add = min(25, remaining)
            result[i] = chr(ord('a') + add)
            remaining -= add
            
        return "".join(result)
```

#### C++
```cpp
class Solution {
public:
    string getSmallestString(int n, int k) {
        string result(n, 'a');
        int remaining = k - n;
        
        for (int i = n - 1; i >= 0 && remaining > 0; --i) {
            int add = min(25, remaining);
            result[i] += add;
            remaining -= add;
        }
        
        return result;
    }
};
```

---

### Complexity Analysis

- **Time Complexity:** $\mathcal{O}(n)$ — We initialize an array of length $n$ and make at most one pass from right to left.
- **Space Complexity:** $\mathcal{O}(n)$ — For storing and returning the output string/character array ($\mathcal{O}(1)$ auxiliary space if output string buffer is not counted).

---

### Key Takeaways
- Always prioritize smaller values at more significant (leftmost) positions for lexicographical minimization.
- Filling with the base case (`'a'`) first prevents handling underflow or minimum constraint checks per character.
