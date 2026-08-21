# Tree Recursion in Java

## 📌 Concept Overview
A recursive function is known as **Tree Recursion** if it makes **more than one recursive call** to itself within its execution body.

Unlike linear recursion (which forms a straight chain of call stack frames), tree recursion branches out into an **execution tree**, making total function calls grow exponentially with respect to input size $n$.

---

## 💻 Java Implementation

```java
public class treeRecursion {
    public static void fun(int n) {
        if (n > 0) {
            System.out.print(n + " ");
            fun(n - 1);
            fun(n - 1);
        }
    }

    public static void main(String args[]) {
        int n = 3;
        fun(n);
    }
}
```

### Output:
```text
3 2 1 1 2 1 1
```

---

## 🌲 Visual Execution Tree ($n = 3$)

```text
                            fun(3) [Prints 3]
                          /                   \
               fun(2) [Prints 2]            fun(2) [Prints 2]
               /              \             /              \
         fun(1) [1]        fun(1) [1]  fun(1) [1]        fun(1) [1]
         /       \         /       \   /       \         /       \
      fun(0)   fun(0)   fun(0)   fun(0) ... (Base cases return)
```

### Trace of Output Generation:
1. `fun(3)` prints **`3`**, calls left `fun(2)`.
2. `fun(2)` prints **`2`**, calls left `fun(1)`.
3. `fun(1)` prints **`1`**, calls left `fun(0)` (returns), calls right `fun(0)` (returns).
4. Returning back to `fun(2)`, it now calls right `fun(1)`:
   - `fun(1)` prints **`1`**, returns.
5. Returning back to `fun(3)`, it now executes its right `fun(2)` branch:
   - Prints **`2`**, left `fun(1)` prints **`1`**, right `fun(1)` prints **`1`**.
6. Complete stream printed: `3 2 1 1 2 1 1`.

---

## ⏱️ Mathematical & Complexity Analysis

### 1. Total Number of Calls:
At level 0 ($n$): $2^0 = 1$ call  
At level 1 ($n-1$): $2^1 = 2$ calls  
At level 2 ($n-2$): $2^2 = 4$ calls  
...  
At level $n$ ($0$): $2^n$ calls  

$$\text{Total Calls} = \sum_{k=0}^{n} 2^k = 2^{n+1} - 1$$

For $n = 3$: Total calls $= 2^{3+1} - 1 = 15$ calls.

### 2. Time Complexity:
$$\mathcal{O}(2^n) \quad \text{(Exponential)}$$

### 3. Space Complexity (JVM Stack Depth):
Even though $2^{n+1}-1$ calls are executed, the maximum number of stack frames resident in memory simultaneously is equal to the **height of the tree**:
$$\text{Stack Depth} = n + 1 \implies \mathcal{O}(n) \quad \text{(Linear Memory)}$$
