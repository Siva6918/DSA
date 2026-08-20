# Recursion in Java & C++ — Core Concepts & Memory Mechanics

This directory contains foundational programs demonstrating how recursion works in Java and C++, specifically focusing on **Call Stack execution**, **Instance vs. Static Methods**, and **State Preservation (Static Variables vs. Stack Frames)**.

---

## 📂 File Index

| File | Language | Topic | Key Concept |
|---|---|---|---|
| [`Recursion.java`](./Recursion.java) | Java | Basic Recursion | Recursion printing $N \to 1$, Static methods in Java |
| [`Recursion.cpp`](./Recursion.cpp) | C++ | Basic Recursion | Equivalent C++ implementation |
| [`recursionWithStatic.java`](./recursionWithStatic.java) | Java | Static Variables in Recursion | Global/Class-level state across recursive calls |
| [`recursionWithOutStatic.java`](./recursionWithOutStatic.java) | Java | Pure Functional Recursion | Local stack frames and return value propagation |

---

## 🧠 Deep-Dive Concepts

### 1. What is Recursion?
Recursion is a programming technique where a method calls itself to solve smaller subproblems of the same type until it reaches a **Base Case** (termination condition).

Every recursive function consists of:
1. **Base Case:** Stops further recursive calls to prevent infinite loops / StackOverflowError.
2. **Recursive Call (Subproblem):** Solves a smaller instance ($n-1$, $n/2$, etc.).
3. **Work/Action Phase:**
   - **Pre-order / Descending Phase (Calling phase):** Actions executed *before* the recursive call (top-down).
   - **Post-order / Ascending Phase (Returning phase):** Actions executed *after* the recursive call returns (bottom-up).

---

### 2. Static vs. Non-Static Methods & Common Compilation Errors

#### The Problem:
```java
public class Recursion {
    public void display(int n) { ... } // Non-static (Instance method)

    public static void main(String args[]) {
        display(10); // ❌ ERROR: Cannot make a static reference to the non-static method
    }
}
```

#### Why does this happen?
- **`static` methods (like `main`)** belong to the **Class blueprint** itself. They are loaded when the JVM loads the class, before any objects (`new Recursion()`) exist.
- **Non-static methods (instance methods)** belong to a **specific object instance** and operate on instance data (`this`).
- A static method cannot directly call an instance method because the compiler doesn't know *which instance's* method to invoke.

#### Solutions:
1. **Make the method static:**
   ```java
   public static void display(int n) { ... }
   ```
2. **Instantiate the class:**
   ```java
   new Recursion().display(10);
   ```

---

### 3. Static Variables vs. Local Stack Variables in Recursion

#### Case A: Recursion with Static Variable ([`recursionWithStatic.java`](./recursionWithStatic.java))
```java
public class recursionWithStatic {
    static int x = 0; // Single shared copy in Metaspace/Class data

    public static int display(int n) {
        if (n > 0) {
            x++;                             // Increments during CALLING phase (x becomes 5)
            return display(n - 1) + x;       // Adds x during RETURNING phase (when x is 5 for all!)
        }
        return 0;
    }

    public static void main(String args[]) {
        System.out.println(display(5)); // Outputs 25
    }
}
```

#### Execution Trace ($n=5$):
```
Calling Phase:
display(5) -> x=1 -> calls display(4)
display(4) -> x=2 -> calls display(3)
display(3) -> x=3 -> calls display(2)
display(2) -> x=4 -> calls display(1)
display(1) -> x=5 -> calls display(0)
display(0) -> returns 0

Returning Phase (Note: x is now 5 permanently):
display(1) returns 0 + 5 = 5
display(2) returns 5 + 5 = 10
display(3) returns 10 + 5 = 15
display(4) returns 15 + 5 = 20
display(5) returns 20 + 5 = 25
Result = 25
```

---

#### Case B: Recursion without Static Variable ([`recursionWithOutStatic.java`](./recursionWithOutStatic.java))
```java
public class recursionWithOutStatic {
    public static int display(int n) {
        if (n > 0) {
            return display(n - 1) + n; // 'n' is stored locally in EACH stack frame
        }
        return 0;
    }

    public static void main(String args[]) {
        System.out.println(display(5)); // Outputs 15 (1 + 2 + 3 + 4 + 5)
    }
}
```

#### Execution Trace ($n=5$):
```
Calling Phase:
[Stack Frame: n=5] -> waits for display(4) + 5
[Stack Frame: n=4] -> waits for display(3) + 4
[Stack Frame: n=3] -> waits for display(2) + 3
[Stack Frame: n=2] -> waits for display(1) + 2
[Stack Frame: n=1] -> waits for display(0) + 1
[Stack Frame: n=0] -> returns 0

Returning Phase:
display(0) = 0
display(1) = 0 + 1 = 1
display(2) = 1 + 2 = 3
display(3) = 3 + 3 = 6
display(4) = 6 + 4 = 10
display(5) = 10 + 5 = 15
Result = 15 (Sum of first 5 natural numbers)
```

---

## 📊 Summary Comparison

| Attribute | `recursionWithStatic` | `recursionWithOutStatic` |
|---|---|---|
| **Storage Location** | Method Area / Metaspace (Static memory) | Stack Frame (Local Activation Record) |
| **Copies of Variable** | Exactly **1 copy** shared across all recursive calls | **1 copy per call** stored in each frame |
| **Side Effects** | State changes persist across subsequent calls | Pure & thread-safe; no side-effects |
| **Value for $n=5$** | $25$ ($5 \times 5$) | $15$ ($1 + 2 + 3 + 4 + 5$) |
| **Use Case** | Global counters, shared accumulators | Pure mathematical functions, Divide & Conquer |
