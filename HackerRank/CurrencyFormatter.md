## Currency Formatter

---

**HackerRank URL:** https://www.hackerrank.com/challenges/java-currency-formatter/problem

**Platform:** HackerRank — Java (Difficulty: Easy)

---

**Problem Statement:**

Given a double-precision number denoting an amount of money, use the `NumberFormat` class' `getCurrencyInstance` method to convert it into the **US, Indian, Chinese, and French** currency formats. Then print the formatted values.

> **Note:** India does not have a built-in `Locale`, so you must construct one using `new Locale("en", "IN")`.

---

**Input Format**

A single double-precision number denoting the payment amount.

**Constraints**

```
-10^25 <= payment <= 10^25
```

**Output Format**

```
US: <US formatted>
India: <India formatted>
China: <China formatted>
France: <France formatted>
```

---

**Sample Input**

```
12324.134
```

**Sample Output**

```
US: $12,324.13
India: Rs.12,324.13
China: 12,324.13
France: 12 324,13
```

**Explanation**

Each line contains the payment formatted according to that country's currency locale:
- **US** uses `$`, comma as thousands separator, dot as decimal separator
- **India** uses `Rs.`, comma as thousands separator, dot as decimal separator
- **China** uses (CNY symbol), comma as thousands separator, dot as decimal separator
- **France** uses euro symbol (appended), space as thousands separator, comma as decimal separator

---

**Solution:**

---

```java
import java.util.*;
import java.text.*;

public class currencyFormatter {

    public static void main(String[] args) {
        System.out.println("Enter Payment:");
        Scanner scanner = new Scanner(System.in);
        double payment = scanner.nextDouble();
        scanner.close();

        // India requires a manually constructed Locale (no built-in constant)
        NumberFormat fIndia  = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
        NumberFormat fUs     = NumberFormat.getCurrencyInstance(Locale.US);
        NumberFormat fChina  = NumberFormat.getCurrencyInstance(Locale.CHINA);
        NumberFormat fFrance = NumberFormat.getCurrencyInstance(Locale.FRANCE);

        String us     = fUs.format(payment);
        String india  = fIndia.format(payment);
        String china  = fChina.format(payment);
        String france = fFrance.format(payment);

        System.out.println("US: "     + us);
        System.out.println("India: "  + india);
        System.out.println("China: "  + china);
        System.out.println("France: " + france);
    }
}
```

---

**How to Compile & Run**

```bash
javac currencyFormatter.java
java currencyFormatter
```

> Compiling may show a deprecation warning about `new Locale("en", "IN")`.
> This is expected and does not affect correctness.

---

**Explanation:**

1. `NumberFormat.getCurrencyInstance(Locale)` returns a formatter that automatically applies the correct currency symbol, thousands separator, decimal separator, and 2-decimal precision.

2. `Locale.US`, `Locale.CHINA`, `Locale.FRANCE` are built-in constants in `java.util.Locale`.

3. India has no built-in constant, so `new Locale("en", "IN")` constructs it manually:
   - Language: `"en"` (English)
   - Country: `"IN"` (India)

4. `fUs.format(payment)` converts the `double` to the locale-specific currency string.

---

**Key Classes Used**

| Class | Package | Purpose |
|---|---|---|
| `NumberFormat` | `java.text` | Abstract base for number/currency formatting |
| `Locale` | `java.util` | Represents a geographic/cultural region |
| `Scanner` | `java.util` | Reads user input |

---

**Time Complexity:** O(1) — single format call per locale, no loops.

**Space Complexity:** O(1) — constant number of formatter objects.

---

**Common Mistakes**

- Entering `Rs.12000` as input: `scanner.nextDouble()` expects a plain number like `12000.0`, not a formatted string.
- Using `Locale.INDIA`: this constant does not exist in Java; you must use `new Locale("en", "IN")`.
- Forgetting to import `java.text.*`: `NumberFormat` is in `java.text`, not `java.util`.
