# Recursion in Java & C++ — Complete Guide & Memory Mechanics

This directory contains foundational programs, visual call-stack traces, recursive classifications, mathematical series approximations (Taylor Series), and individual deep-dive guides for each problem.

---

## 📂 Topic & Problem Index

| Topic / Problem | Source Code | Deep-Dive Write-up | Key Concept / Complexity |
|---|---|---|---|
| **Head vs Tail Recursion** | [`Recursion.java`](./Recursion.java), [`Recursion.cpp`](./Recursion.cpp) | [README.md](./README.md) | Execution flow in calling vs returning phase |
| **Static vs Stack Variables** | [`recursionWithStatic.java`](./recursionWithStatic.java), [`recursionWithOutStatic.java`](./recursionWithOutStatic.java) | [README.md#3-static-variables-vs-local-stack-variables-in-recursion](./README.md) | Metaspace vs Stack Frame isolation |
| **Sum of Natural Numbers** | [`sumOfNaturalNumbers.java`](./sumOfNaturalNumbers.java) | [SumOfNaturalNumbers.md](./SumOfNaturalNumbers.md) | $f(n) = f(n-1) + n$ Recurrence ($\mathcal{O}(n)$) |
| **Factorial ($N!$)** | [`Factorial.java`](./Factorial.java) | [Factorial.md](./Factorial.md) | Base case $0! = 1$, Integer overflow handling |
| **Exponentiation ($m^n$)** | [`power.java`](./power.java) | [Power.md](./Power.md) | Linear $\mathcal{O}(n)$ & Binary Exponentiation $\mathcal{O}(\log n)$ |
| **Tree Recursion** | [`treeRecursion.java`](./treeRecursion.java) | [TreeRecursion.md](./TreeRecursion.md) | Exponential branching $\mathcal{O}(2^n)$, call tree trace |
| **Indirect Recursion** | [`indirectRecursion.java`](./indirectRecursion.java) | [IndirectRecursion.md](./IndirectRecursion.md) | Mutual cycle $A \leftrightarrow B$; Java multi-pass method resolution |
| **Nested Recursion** | [`nestedRecursion.java`](./nestedRecursion.java) | [NestedRecursion.md](./NestedRecursion.md) | Passing recursive returns $f(f(n+11))$ (McCarthy 91) |
| **Taylor Series ($e^x$)** | [`taylorSeries.java`](./taylorSeries.java), [`taylorSeries1.java`](./taylorSeries1.java), [`taylorSeries2.java`](./taylorSeries2.java) | [TaylorSeries.md](./TaylorSeries.md) | Direct $\mathcal{O}(n^2)$ vs Static Ascending vs Horner's Rule $\mathcal{O}(n)$ |

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

### 2. Tree Recursion ([`TreeRecursion.md`](./TreeRecursion.md))
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
- **Time Complexity:** $\mathcal{O}(2^n)$ (Total calls $= 2^{n+1} - 1$)
- **Space Complexity:** $\mathcal{O}(n)$ (Maximum stack depth equals height of the recursion tree).

---

### 3. Indirect Recursion ([`IndirectRecursion.md`](./IndirectRecursion.md))
Occurs when function $A$ calls function $B$, and function $B$ calls function $A$, forming a cycle:
$$A(n) \xrightarrow{\text{calls}} B(n-1) \xrightarrow{\text{calls}} A(n/2) \dots$$

> **Note on Java vs. C/C++:** Java does **not** need forward declarations/function prototypes (`void A(int);`) because the Java compiler operates in multiple passes and resolves all methods in the class symbol table automatically.

---

### 4. Nested Recursion ([`NestedRecursion.md`](./NestedRecursion.md))
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

## 📈 Taylor Series Expansion ($e^x$) — 3 Approaches ([`TaylorSeries.md`](./TaylorSeries.md))

The Taylor series expansion for $e^x$ is given by:
$$e^x = 1 + \frac{x}{1!} + \frac{x^2}{2!} + \frac{x^3}{3!} + \dots + \frac{x^n}{n!} = \sum_{k=0}^{n} \frac{x^k}{k!}$$

| Approach | File | Time Complexity | Multiplications | Key Technique |
|---|---|---|---|---|
| **Direct Sum** | [`taylorSeries.java`](./taylorSeries.java) | $\mathcal{O}(n^2)$ | $\approx n^2$ | Recurrence with independent `pow()` & `fact()` |
| **Static Ascending** | [`taylorSeries1.java`](./taylorSeries1.java) | $\mathcal{O}(n)$ | $2n$ | Static accumulators on returning phase |
| **Horner's Rule** | [`taylorSeries2.java`](./taylorSeries2.java) | $\mathcal{O}(n)$ | $n$ | Factoring terms in descending phase $s = 1 + \frac{x}{n} s$ |
