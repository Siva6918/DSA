# Taylor Series ($e^x$) — 3 Algorithmic Approaches

## 📌 Mathematical Background
The Taylor series expansion of the natural exponential function $e^x$ is given by:
$$e^x = 1 + \frac{x}{1!} + \frac{x^2}{2!} + \frac{x^3}{3!} + \dots + \frac{x^n}{n!} = \sum_{k=0}^{n} \frac{x^k}{k!}$$

---

## 🔬 Approach 1: Direct Recursive Sum ($\mathcal{O}(n^2)$) — [`taylorSeries.java`](./taylorSeries.java)

Computes power and factorial independently for each term and recursively sums them in the ascending (returning) phase.

```java
public class taylorSeries {
    public static int fact(int n) {
        return (n == 0) ? 1 : fact(n - 1) * n;
    }

    public static int pow(int m, int n) {
        return (n == 0) ? 1 : pow(m, n - 1) * m;
    }

    public static double e(int x, int n) {
        if (n == 0) return 1;
        return (double) pow(x, n) / fact(n) + e(x, n - 1);
    }
}
```

> **⚠️ Critical Gotcha (Integer Division):**
> Writing `pow(x, n) / fact(n)` divides two `int` values, truncating decimals to `0`. Always cast to `(double)` before division!

- **Time Complexity:** $\mathcal{O}(n^2)$ (Repeated power and factorial calls for each $n$).
- **Space Complexity:** $\mathcal{O}(n)$ stack space.

---

## 🔬 Approach 2: Ascending Phase with Static Accumulators ($\mathcal{O}(n)$) — [`taylorSeries1.java`](./taylorSeries1.java)

Maintains running values of power $p$ and factorial $f$ as static variables across stack frames during the **returning (ascending)** phase.

```java
public class taylorSeries1 {
    static double p = 1, f = 1;

    public static double e(int x, int n) {
        if (n == 0) {
            return 1;
        } else {
            double r = e(x, n - 1);
            p = p * x;          // Multiplies x on the way back up
            f = f * n;          // Multiplies n on the way back up
            return r + p / f;
        }
    }
}
```

### Execution Trace ($x=2, n=3$):
```text
Calling:  e(2, 3) -> e(2, 2) -> e(2, 1) -> e(2, 0) returns 1
Return:
  e(2, 1): p = 2, f = 1, returns 1 + (2/1) = 3
  e(2, 2): p = 4, f = 2, returns 3 + (4/2) = 5
  e(2, 3): p = 8, f = 6, returns 5 + (8/6) = 6.333...
```

- **Time Complexity:** $\mathcal{O}(n)$ (Each step takes $\mathcal{O}(1)$).
- **Space Complexity:** $\mathcal{O}(n)$ stack depth.

---

## 🔬 Approach 3: Horner's Rule in Descending Phase ($\mathcal{O}(n)$) — [`taylorSeries2.java`](./taylorSeries2.java)

Horner's Rule reduces the number of multiplications by factoring common terms:
$$e^x \approx 1 + \frac{x}{1}\left[1 + \frac{x}{2}\left[1 + \frac{x}{3}\left[\dots \left[1 + \frac{x}{n}\right]\right]\right]\right]$$

Iterative Accumulator Formula:
$$s = 1 + \frac{x}{n} \cdot s$$

```java
public class taylorSeries2 {
    static double s = 1;

    public static double e(int x, int n) {
        if (n == 0) {
            return s;
        } else {
            s = 1 + ((double) x / n) * s; // Calculated on the way DOWN
            return e(x, n - 1);           // Tail-like descent
        }
    }
}
```

### Execution Trace ($x=2, n=3$):
```text
n=3: s = 1 + (2.0/3) * 1 = 1.666...
n=2: s = 1 + (2.0/2) * 1.666... = 2.666...
n=1: s = 1 + (2.0/1) * 2.666... = 6.333...
n=0: returns s = 6.333...
```

- **Time Complexity:** $\mathcal{O}(n)$
- **Space Complexity:** $\mathcal{O}(n)$
- **Efficiency:** Minimal multiplications; operates in the **calling phase**.

---

## 📊 Summary Comparison

| Metric | Approach 1 (Direct) | Approach 2 (Static Ascending) | Approach 3 (Horner's Rule) |
|---|---|---|---|
| **Multiplications** | $\approx n^2$ | $2n$ | $n$ |
| **Additions** | $n$ | $n$ | $n$ |
| **Time Complexity** | $\mathcal{O}(n^2)$ | $\mathcal{O}(n)$ | $\mathcal{O}(n)$ (Fastest & most numerical precision) |
| **Phase of Computation** | Returning (Ascending) | Returning (Ascending) | Calling (Descending) |
