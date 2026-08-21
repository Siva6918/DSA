# Recursion in Java & C++ — Complete Guide & Memory Mechanics

This directory contains foundational programs and deep dives demonstrating how **Recursion** executes in memory (JVM Call Stack, Heap, Metaspace), recursive classifications, mathematical series approximations (Taylor Series), and core execution nuances.

---

## 📂 File Index

| File | Language | Topic | Key Concept / Technique |
|---|---|---|---|
| [`Recursion.java`](./Recursion.java) | Java | Head vs Tail Recursion | Execution flow in calling vs returning phase |
| [`Recursion.cpp`](./Recursion.cpp) | C++ | C++ Recursion | Identical call stack mechanics in native memory |
| [`recursionWithStatic.java`](./recursionWithStatic.java) | Java | Static Variables in Recursion | Class-level state across recursive calls |
| [`recursionWithOutStatic.java`](./recursionWithOutStatic.java) | Java | Stack Frame Variables | Pure functional recursion with distinct frame copies |
| [`sumOfNaturalNumbers.java`](./sumOfNaturalNumbers.java) | Java | Sum of Natural Numbers | $f(n) = f(n-1) + n$ recurrence relation |
| [`Factorial.java`](./Factorial.java) | Java | Factorial ($N!$) | Base case $0! = 1$, multiplicative reduction |
| [`power.java`](./power.java) | Java | Exponentiation ($m^n$) | Multiplicative recurrence $m \times m^{n-1}$ |
| [`treeRecursion.java`](./treeRecursion.java) | Java | Tree Recursion | Multiple recursive calls per frame ($\mathcal{O}(2^n)$ calls) |
| [`indirectRecursion.java`](./indirectRecursion.java) | Java | Indirect (Mutual) Recursion | Function $A \to B \to A$ cycle; Java method resolution |
| [`nestedRecursion.java`](./nestedRecursion.java) | Java | Nested Recursion | Function passing recursive result as parameter $f(f(n+11))$ |
| [`taylorSeries.java`](./taylorSeries.java) | Java | Taylor Series ($e^x$) | Basic implementation using recursive `pow()` and `fact()` ($\mathcal{O}(n^2)$) |
| [`taylorSeries1.java`](./taylorSeries1.java) | Java | Taylor Series with Static State | Accumulator in returning phase ($\mathcal{O}(n)$) |
| [`taylorSeries2.java`](./taylorSeries2.java) | Java | Taylor Series via Horner's Rule | Factoring common terms in calling phase ($\mathcal{O}(n)$) |

---

## 🧠 Complete Classification of Recursion

```
                                  ┌───────────────────────────┐
                                  │    Types of Recursion     │
                                  └─────────────┬─────────────┘
          ┌─────────────────────┬───────────────┴───────────────┬─────────────────────┐
          ▼                     ▼                               ▼                     ▼
┌──────────────────┐  ┌──────────────────┐            ┌──────────────────┐  ┌──────────────────┐
│ Linear Recursion │  │  Tree Recursion  │            │Indirect Recursion│  │ Nested Recursion │
│ - Tail Recursion │  │ - Multiple calls │            │ - Circular cycle │  │ - Parameter call │
│ - Head Recursion │  │   per frame      │            │   (A -> B -> A)  │  │   f(f(n+11))     │
└──────────────────┘  └──────────────────┘            └──────────────────┘  └──────────────────┘
```

---

### 1. Linear Recursion (Tail vs. Head)
- **Tail Recursion:** The recursive call is the **very last statement** executed in the function. Nothing is computed in the returning phase.
- **Head Recursion:** The recursive call is made **at the beginning** before any main processing. All work happens in the returning (ascending) phase.

---

### 2. Tree Recursion ([`treeRecursion.java`](./treeRecursion.java))
A function is called **Tree Recursive** if it makes more than one recursive call within its body.
```java
public static void fun(int n) {
    if (n > 0) {
        System.out.print(n + " ");
        fun(n - 1);
        fun(n - 1);
    }
}
```
- **Execution Tree ($n=3$):**
```
                     fun(3) [Prints 3]
                   /                  \
          fun(2) [Prints 2]          fun(2) [Prints 2]
          /             \            /             \
     fun(1) [1]     fun(1) [1]  fun(1) [1]     fun(1) [1]
```
- **Time Complexity:** $\mathcal{O}(2^n)$ (Total calls $= 2^{n+1} - 1$)
- **Space Complexity:** $\mathcal{O}(n)$ (Maximum stack depth equals height of the recursion tree).

---

### 3. Indirect Recursion ([`indirectRecursion.java`](./indirectRecursion.java))
Occurs when function $A$ calls function $B$, and function $B$ calls function $A$, forming a cycle:
$$A(n) \xrightarrow{\text{calls}} B(n-1) \xrightarrow{\text{calls}} A(n/2) \dots$$

```java
public static void A(int n) {
    if (n > 0) {
        System.out.print(n + " ");
        B(n - 1);
    }
}
public static void B(int n) {
    if (n > 1) {
        System.out.print(n + " ");
        A(n / 2);
    }
}
```
> **Note on Java vs. C/C++:** Java does **not** need forward declarations/function prototypes (`void A(int);`) because the Java compiler operates in multiple passes and resolves all methods in the class symbol table automatically.

---

### 4. Nested Recursion ([`nestedRecursion.java`](./nestedRecursion.java))
Recursion inside recursion, where the return value of a recursive call is passed directly as an argument to another recursive call:
```java
public static int fun(int n) {
    if (n > 100) {
        return n - 10;
    } else {
        return fun(fun(n + 11)); // McCarthy 91 Function pattern
    }
}
```
- For any input $n \le 100$, `fun(n)` evaluates to `91`.

---

## 📈 Taylor Series Expansion ($e^x$) — 3 Approaches

The Taylor series expansion for $e^x$ is given by:
$$e^x = 1 + \frac{x}{1!} + \frac{x^2}{2!} + \frac{x^3}{3!} + \dots + \frac{x^n}{n!}$$

### Method 1: Direct Recursive Sum ([`taylorSeries.java`](./taylorSeries.java))
Calculates $\text{pow}(x, n) / \text{fact}(n)$ at each step and adds $e(x, n-1)$.
- **Time Complexity:** $\mathcal{O}(n^2)$ (due to repeated power and factorial calls).
- **Critical Pitfall:** Ensure floating-point division `(double) pow(x, n) / fact(n)`, otherwise Java integer division results in `0`.

---

### Method 2: Recursive with Static Accumulators ([`taylorSeries1.java`](./taylorSeries1.java))
Preserves power $p$ and factorial $f$ as static variables across stack frames during the returning phase.
```java
static double p = 1, f = 1;
public static double e(int x, int n) {
    if (n == 0) return 1;
    double r = e(x, n - 1);
    p = p * x;
    f = f * n;
    return r + p / f;
}
```
- **Time Complexity:** $\mathcal{O}(n)$
- **Space Complexity:** $\mathcal{O}(n)$ call stack depth

---

### Method 3: Horner's Rule ([`taylorSeries2.java`](./taylorSeries2.java))
Factors out common terms to compute the series in linear time during the **calling (descending) phase**:
$$e^x \approx 1 + \frac{x}{1}\left[1 + \frac{x}{2}\left[1 + \frac{x}{3}\left[\dots \left[1 + \frac{x}{n}\right]\right]\right]\right]$$
Iterative recurrence:
$$s = 1 + \frac{x}{n} \cdot s$$

```java
static double s = 1;
public static double e(int x, int n) {
    if (n == 0) return s;
    s = 1 + ((double) x / n) * s;
    return e(x, n - 1);
}
```
- **Time Complexity:** $\mathcal{O}(n)$ (Only $n$ multiplications and additions).
