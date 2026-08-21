# Nested Recursion in Java

## 📌 Concept Overview
**Nested Recursion** occurs when a recursive function passes the return value of a recursive call as the parameter/argument to another recursive call:

$$\text{fun}(\text{fun}(n + \Delta))$$

A classic example of nested recursion is the **McCarthy 91 Function** in computer science and theoretical computability.

---

## 💻 Java Implementation

```java
public class nestedRecursion {
    public static int fun(int n) {
        if (n > 100) {
            return n - 10;
        } else {
            return fun(fun(n + 11));
        }
    }

    public static void main(String args[]) {
        int n = 95;
        System.out.println(fun(n));
    }
}
```

### Output:
```text
91
```

---

## 🔍 Step-by-Step Call Trace for `fun(95)`

```text
fun(95)
 = fun(fun(106))           [since 95 <= 100, calls fun(95 + 11 = 106)]
 = fun(96)                 [since 106 > 100, fun(106) returns 106 - 10 = 96]
 = fun(fun(107))           [since 96 <= 100, calls fun(96 + 11 = 107)]
 = fun(97)                 [since 107 > 100, fun(107) returns 97]
 = fun(fun(108))
 = fun(98)
 = fun(fun(109))
 = fun(99)
 = fun(fun(110))
 = fun(100)
 = fun(fun(111))
 = fun(101)
 = 101 - 10
 = 91
```

---

## 💡 Mathematical Property of McCarthy 91
For the definition:
$$M(n) = \begin{cases} n - 10 & \text{if } n > 100 \\ M(M(n+11)) & \text{if } n \le 100 \end{cases}$$

For **all** integers $n \le 100$, the function universally evaluates to **`91`**.
