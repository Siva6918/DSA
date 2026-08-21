# Power / Exponentiation ($m^n$) via Recursion

## 📌 Problem Overview
Compute the exponentiation of a base $m$ raised to the power $n$:
$$m^n = \underbrace{m \times m \times \dots \times m}_{n \text{ times}}$$
with base condition $m^0 = 1$.

---

## 💻 Java Implementation

```java
import java.util.Scanner;

public class power {
    public static int pow(int m, int n) {
        if (n == 0) {
            return 1;
        } else {
            return pow(m, n - 1) * m;
        }
    }

    public static void main(String[] args) {
        int m, n;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter base (m) and power (n): ");
        m = sc.nextInt();
        n = sc.nextInt();
        sc.close();

        System.out.println(m + "^" + n + " = " + pow(m, n));
    }
}
```

---

## 🔄 Recurrence Relation

$$\text{pow}(m, n) = \begin{cases} 1 & n = 0 \\ \text{pow}(m, n-1) \times m & n > 0 \end{cases}$$

### Complexity:
- **Linear Approach:** $\mathcal{O}(n)$ Time | $\mathcal{O}(n)$ Stack Space

---

## 🚀 Optimization: Binary Exponentiation ($\mathcal{O}(\log n)$)

We can optimize the computation of $m^n$ by dividing the exponent by 2:
$$m^n = \begin{cases} (m^2)^{n/2} & \text{if } n \text{ is even} \\ m \times (m^2)^{(n-1)/2} & \text{if } n \text{ is odd} \end{cases}$$

```java
public static int fastPow(int m, int n) {
    if (n == 0) return 1;
    if (n % 2 == 0) {
        return fastPow(m * m, n / 2);
    } else {
        return m * fastPow(m * m, (n - 1) / 2);
    }
}
```
- **Optimized Time:** $\mathcal{O}(\log n)$
- **Optimized Space:** $\mathcal{O}(\log n)$ stack frames
