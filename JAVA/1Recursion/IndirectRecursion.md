# Indirect (Mutual) Recursion in Java

## 📌 Concept Overview
**Indirect Recursion** (also called **Mutual Recursion**) occurs when a function does not call itself directly, but instead calls another function, which in turn (directly or via a chain of calls) calls the original function back, creating a cycle:

$$A \longrightarrow B \longrightarrow A \longrightarrow B \dots$$

---

## 💻 Java Implementation

```java
public class indirectRecursion {
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

    public static void main(String args[]) {
        int n = 20;
        A(n);
    }
}
```

### Output:
```text
20 19 9 8 4 3 1
```

---

## 🔄 Step-by-Step Call Trace ($n = 20$)

| Step | Function Call | Condition Checked | Output Printed | Next Action |
|:---|:---|:---|:---|:---|
| 1 | `A(20)` | $20 > 0$ (True) | `20 ` | Calls `B(20 - 1) = B(19)` |
| 2 | `B(19)` | $19 > 1$ (True) | `19 ` | Calls `A(19 / 2) = A(9)` |
| 3 | `A(9)`  | $9 > 0$ (True)  | `9 `  | Calls `B(9 - 1) = B(8)` |
| 4 | `B(8)`  | $8 > 1$ (True)  | `8 `  | Calls `A(8 / 2) = A(4)` |
| 5 | `A(4)`  | $4 > 0$ (True)  | `4 `  | Calls `B(4 - 1) = B(3)` |
| 6 | `B(3)`  | $3 > 1$ (True)  | `3 `  | Calls `A(3 / 2) = A(1)` |
| 7 | `A(1)`  | $1 > 0$ (True)  | `1 `  | Calls `B(1 - 1) = B(0)` |
| 8 | `B(0)`  | $0 > 1$ (False) | *(None)* | Base case reached $\to$ Returns |

---

## 💡 Important Language Nuance: Java vs. C/C++ Function Prototypes

In **C / C++**:
Because C/C++ compilers process source code in a single top-to-bottom pass, if `A()` calls `B()` before `B()` is declared, compilation fails unless a **forward declaration / function prototype** is provided:
```c
void B(int n); // Prototype needed in C/C++
void A(int n) { ... B(n - 1); ... }
void B(int n) { ... A(n / 2); ... }
```

In **Java**:
- Java compilers (`javac`) use **multi-pass symbol resolution**.
- All method identifiers and signatures within the class are collected before method bodies are compiled.
- Therefore, **Java does not require or allow function prototypes**.
