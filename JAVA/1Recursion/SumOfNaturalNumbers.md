# Sum of First N Natural Numbers via Recursion

## 📌 Problem Overview
Calculate the sum of the first $n$ natural numbers:
$$S_n = 1 + 2 + 3 + \dots + n$$

---

## 💻 Java Implementation

```java
import java.util.Scanner;

public class sumOfNaturalNumbers {
    public static int sum(int n) {
        if (n == 0) {
            return 0;
        } else {
            return sum(n - 1) + n;
        }
    }

    public static void main(String args[]) {
        int n;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter value: ");
        n = sc.nextInt();
        sc.close();

        System.out.println("Sum = " + sum(n));
    }
}
```

---

## 🔄 Recurrence Relation & Call Stack Mechanics

$$\text{sum}(n) = \begin{cases} 0 & n = 0 \quad (\text{Base Case}) \\ \text{sum}(n-1) + n & n > 0 \quad (\text{Recursive Step}) \end{cases}$$

### Stack Frame Execution Trace ($n = 5$):

```text
[Calling Phase - Push Frames]
sum(5) = sum(4) + 5
sum(4) = sum(3) + 4
sum(3) = sum(2) + 3
sum(2) = sum(1) + 2
sum(1) = sum(0) + 1
sum(0) = 0 (Base Case)

[Returning Phase - Pop & Accumulate]
sum(1) returns 0 + 1 = 1
sum(2) returns 1 + 2 = 3
sum(3) returns 3 + 3 = 6
sum(4) returns 6 + 4 = 10
sum(5) returns 10 + 5 = 15
```

---

## ⏱️ Complexity Analysis

- **Time Complexity:** $\mathcal{O}(n)$ (Performs $n+1$ function calls).
- **Space Complexity:** $\mathcal{O}(n)$ (Call stack depth of $n+1$ frames).
- **Closed-Form Direct Math Formula Comparison:** $S_n = \frac{n(n+1)}{2} \implies \mathcal{O}(1)$ time & space.
