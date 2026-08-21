# Fibonacci Series with Memoization (Dynamic Programming)

## 📌 Problem Overview
Compute the $n^{\text{th}}$ Fibonacci number:
$$F_n = \begin{cases} 0 & n = 0 \\ 1 & n = 1 \\ F_{n-1} + F_{n-2} & n \ge 2 \end{cases}$$

---

## ⚡ Naive Tree Recursion vs. Memoization (Top-Down DP)

### 1. Naive Tree Recursion ($\mathcal{O}(2^n)$)
In naive recursion (`fib(n-1) + fib(n-2)`), the same subproblems are computed repeatedly. For example, `fib(3)` is computed multiple times when evaluating `fib(5)`.

```
                    fib(5)
               /              \
           fib(4)            fib(3)
          /      \          /      \
      fib(3)   fib(2)    fib(2)   fib(1)
```

### 2. Memoized Recursion ($\mathcal{O}(n)$)
By storing results in an array `arr` initialized to `-1`, each Fibonacci subproblem is computed **only once**. Subsequent lookups take $\mathcal{O}(1)$.

---

## 💻 Java Implementation ([`fibonacci1.java`](./fibonacci1.java))

```java
import java.util.Arrays;
import java.util.Scanner;

public class fibonacci1 {

    public static int fib(int n, int[] arr) {
        if (n <= 1) {
            arr[n] = n;
            return n;
        } else {
            // Check memo table before calculating
            if (arr[n - 2] == -1) {
                arr[n - 2] = fib(n - 2, arr);
            }
            if (arr[n - 1] == -1) {
                arr[n - 1] = fib(n - 1, arr);
            }
            arr[n] = arr[n - 2] + arr[n - 1];
            return arr[n];
        }
    }

    public static void main(String args[]) {
        int n;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter n: ");
        n = sc.nextInt();
        sc.close();

        // Size n + 1 to accommodate indices 0 through n
        int[] arr = new int[n + 1];
        Arrays.fill(arr, -1);

        int k = fib(n, arr);
        System.out.println("Fibonacci(" + n + ") = " + k);
        System.out.println("Memoization Array: " + Arrays.toString(arr));
    }
}
```

---

## ⏱️ Complexity Analysis

- **Time Complexity:** $\mathcal{O}(n)$ (Each state from $0$ to $n$ is visited and stored exactly once).
- **Space Complexity:** $\mathcal{O}(n)$ (Recursion call stack depth + size $n+1$ memo array).
