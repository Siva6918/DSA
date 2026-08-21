# Factorial of a Number via Recursion

## 📌 Problem Overview
Calculate the factorial of a non-negative integer $n$:
$$n! = 1 \times 2 \times 3 \times \dots \times n$$
with the mathematical definition $0! = 1$.

---

## 💻 Java Implementation

```java
import java.util.Scanner;

public class Factorial {
    public static int fact(int n) {
        if (n == 0) {
            return 1;
        } else {
            return fact(n - 1) * n;
        }
    }

    public static void main(String args[]) {
        int n;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter value: ");
        n = sc.nextInt();
        sc.close();

        System.out.println("Factorial = " + fact(n));
    }
}
```

---

## 🔄 Recurrence & Call Stack

$$\text{fact}(n) = \begin{cases} 1 & n = 0 \\ \text{fact}(n-1) \times n & n > 0 \end{cases}$$

### Stack Frame Execution Trace ($n = 4$):
```text
fact(4) = fact(3) * 4
fact(3) = fact(2) * 3
fact(2) = fact(1) * 2
fact(1) = fact(0) * 1
fact(0) = 1

Returning Phase:
fact(1) = 1 * 1 = 1
fact(2) = 1 * 2 = 2
fact(3) = 2 * 3 = 6
fact(4) = 6 * 4 = 24
```

---

## ⏱️ Complexity & Memory Limit
- **Time Complexity:** $\mathcal{O}(n)$
- **Space Complexity:** $\mathcal{O}(n)$ (Call stack)
- **Data Type Overflow Consideration:** 
  - Standard 32-bit `int` overflows at $13!$ ($13! = 6,227,020,800 > 2^{31}-1$).
  - For $n > 12$, use `long` (up to $20!$) or `java.math.BigInteger` for arbitrary precision.
