## 2029. Stone Game IX

---

**LeetCode URL:** https://leetcode.com/problems/stone-game-ix/

**Difficulty:** Medium | **Category:** Math / Game Theory / Greedy / Modulo Arithmetic

---

### Problem Statement

Alice and Bob continue their games with stones. There is a non-empty array of integers `stones`, where `stones[i]` represents the value of the $i$-th stone.

Alice and Bob take turns, with **Alice starting first**. On each turn, the player may pick any stone from `stones` and remove it.

- The player who makes the **sum of all removed stones divisible by 3 loses the game**.
- If there are no more stones left and the sum of removed stones is **not divisible by 3**, **Bob wins**.
- If a player has no legal moves on their turn (i.e., every remaining stone will make the sum divisible by 3), **that player loses**.

Assuming both players play **optimally**, return `true` if Alice wins and `false` if Bob wins.

---

### Constraints

- $1 \le \text{stones.length} \le 10^5$
- $1 \le \text{stones}[i] \le 10^4$

---

### Examples

**Example 1:**
```text
Input: stones = [2,1]
Output: true
Explanation: The game goes as follows:
- Turn 1: Alice can remove either stone. Suppose she removes 1. The sum is 1.
- Turn 2: Bob must remove 2. The sum is 1 + 2 = 3 (divisible by 3). Bob loses, so Alice wins.
```

**Example 2:**
```text
Input: stones = [2]
Output: false
Explanation: Alice removes 2 (sum is 2). No more stones are left, and sum is not divisible by 3. Bob wins.
```

**Example 3:**
```text
Input: stones = [5,1,2,4,3]
Output: false
Explanation: Bob will always win with optimal play.
```

---

### Intuition & Mathematical Analysis

The exact numerical value of each stone is irrelevant—only its **remainder modulo 3** matters ($0$, $1$, or $2$).

Let:
- $c_0 = \text{count of stones where } \text{stone} \pmod 3 == 0$
- $c_1 = \text{count of stones where } \text{stone} \pmod 3 == 1$
- $c_2 = \text{count of stones where } \text{stone} \pmod 3 == 2$

#### Properties & Rules:
1. **$0$-modulo stones:** Adding a multiple of 3 does not change the running sum modulo 3. These stones act simply as **turn-passers / tempo switchers**.
2. **First Move:**
   - Alice cannot start with a stone with value $\equiv 0 \pmod 3$, because the initial sum is 0 and adding 0 results in a sum divisible by 3 (Alice loses immediately).
   - Alice must choose a stone with $\equiv 1 \pmod 3$ or $\equiv 2 \pmod 3$.
3. **Turn Progression:**
   - If Alice starts with $1$:
     - Current sum $\equiv 1 \pmod 3$.
     - Next player cannot pick $2$ (sum would become $3 \equiv 0$). They must pick $1$ or $0$.
     - If they pick $1$, sum becomes $2 \pmod 3$.
     - Now the next player must pick $2$ (or $0$), bringing sum back to $1 \pmod 3$.
     - Sequence of non-zero picks: $1 \to 1 \to 2 \to 1 \to 2 \to 1 \to 2 \dots$
   - If Alice starts with $2$:
     - Sequence of non-zero picks: $2 \to 2 \to 1 \to 2 \to 1 \to 2 \to 1 \dots$

#### Game Outcome Conditions:

##### Case 1: $c_0$ is EVEN ($c_0 \pmod 2 == 0$)
Since pairs of 0-stones cancel each other out, $c_0$ does not change who is forced to make the losing move.
- Alice wins if and only if **both $c_1 \ge 1$ and $c_2 \ge 1$**.
- If either $c_1 == 0$ or $c_2 == 0$, Alice has no valid alternative branch and Bob can force Alice into making the sum divisible by 3 or running out of stones (Bob wins).

##### Case 2: $c_0$ is ODD ($c_0 \pmod 2 == 1$)
The single odd 0-stone acts as a polarity inverter (swapping whose turn it is in the main sequence).
- Alice can win if and only if **$|c_1 - c_2| > 2$**.
- If the difference is $\le 2$, Bob can use the single 0-stone to reverse turn parity and exhaust Alice's stones, leading to Bob's victory.

---

### Solution Implementations

#### Java
```java
class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] count = new int[3];
        for (int stone : stones) {
            count[stone % 3]++;
        }
        
        // When count of 0-stones is even
        if (count[0] % 2 == 0) {
            return count[1] >= 1 && count[2] >= 1;
        }
        
        // When count of 0-stones is odd
        return Math.abs(count[1] - count[2]) > 2;
    }
}
```

#### Python 3
```python
class Solution:
    def stoneGameIX(self, stones: list[int]) -> bool:
        count = [0, 0, 0]
        for stone in stones:
            count[stone % 3] += 1
            
        # If count of 0-stones is even
        if count[0] % 2 == 0:
            return count[1] >= 1 and count[2] >= 1
        
        # If count of 0-stones is odd
        return abs(count[1] - count[2]) > 2
```

#### C++
```cpp
class Solution {
public:
    bool stoneGameIX(vector<int>& stones) {
        vector<int> count(3, 0);
        for (int stone : stones) {
            count[stone % 3]++;
        }
        
        if (count[0] % 2 == 0) {
            return count[1] >= 1 && count[2] >= 1;
        }
        
        return abs(count[1] - count[2]) > 2;
    }
};
```

---

### Complexity Analysis

- **Time Complexity:** $\mathcal{O}(N)$ — Single pass over the array of length $N$ to compute modulo-3 frequencies.
- **Space Complexity:** $\mathcal{O}(1)$ — Constant space used for counting array of size 3.

---

### Summary Logic Table

| $c_0 \pmod 2$ | Winning Condition for Alice | Reason |
|---|---|---|
| **Even (0)** | $c_1 \ge 1 \land c_2 \ge 1$ | $c_0$ does not change game parity; Alice needs both $1$ and $2$ available to navigate the alternating game state. |
| **Odd (1)** | $\|c_1 - c_2\| > 2$ | The extra $0$-stone shifts tempo; Alice needs a surplus of $> 2$ of one type to outlast Bob. |
