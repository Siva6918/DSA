# Coding & DSA Knowledge Repository

A structured repository of Data Structures & Algorithms (DSA), competitive programming challenges (LeetCode, GeeksforGeeks, HackerRank), Java Object-Oriented Programming (OOP) concepts, and Python development notes.

---

## 📁 Repository Structure

```text
Coding/
├── Arrays/
│   ├── README.md               # Array problem summary & formula cheat sheet
│   ├── SetMatrixZeroes.md      # Matrix manipulation & space optimization
│   ├── NextPermutation.md      # Lexicographical permutation & two-pointer technique
│   └── PascalsTriangle.md      # Combinatorics & dynamic programming
├── GeeksforGeeks/
│   ├── README.md               # GeeksforGeeks problem index & core concepts
│   ├── MaximumDifferenceBetweenNodeAndItsAncestor.java
│   ├── MaximumDifferenceBetweenNodeAndItsAncestor.md # Binary Tree post-order recursion
│   ├── TransformString.java    # Minimum operations to transform string via front insertion
│   └── TransformString.md      # Greedy backward suffix matching & anagram validation
├── HackerRank/
│   ├── README.md               # HackerRank problem directory
│   ├── currencyFormatter.java  # Java solution for currency formatting
│   └── CurrencyFormatter.md    # NumberFormat, Locale & formatting guide
├── JAVA/
│   ├── 1Recursion/             # Recursion mechanics, classifications, stack traces & Taylor Series
│   │   ├── README.md           # Recursion master guide & Horner's rule
│   │   ├── TreeRecursion.md    # Tree recursion & exponential call tree analysis
│   │   ├── IndirectRecursion.md# Indirect/mutual recursion & Java symbol resolution
│   │   ├── NestedRecursion.md  # Nested recursion & McCarthy 91 function
│   │   ├── SumOfNaturalNumbers.md # Sum of natural numbers recurrence analysis
│   │   ├── Factorial.md        # Factorial recurrence & integer overflow analysis
│   │   ├── Power.md            # Exponentiation & fast binary exponentiation
│   │   ├── TaylorSeries.md     # Taylor series e^x: 3 algorithmic methods compared
│   │   ├── Recursion.java      # Head vs Tail recursion
│   │   ├── recursionWithStatic.java    # Class-level state across recursive calls
│   │   ├── recursionWithOutStatic.java # Stack frame propagation
│   │   ├── sumOfNaturalNumbers.java    # Sum of first N natural numbers
│   │   ├── Factorial.java              # Factorial computation
│   │   ├── power.java                  # Power/exponentiation m^n
│   │   ├── treeRecursion.java          # Exponential tree recursion O(2^n)
│   │   ├── indirectRecursion.java      # Mutual indirect recursion (A -> B -> A)
│   │   ├── nestedRecursion.java        # Nested parameter recursion f(f(n+11))
│   │   ├── taylorSeries.java           # Taylor series e^x via direct pow/fact
│   │   ├── taylorSeries1.java          # Taylor series e^x via static variables in return phase
│   │   └── taylorSeries2.java          # Taylor series e^x via Horner's rule O(n)
│   ├── LinkedList/
│   │   ├── CreateLL.java       # Linked list implementation & middle node finder
│   │   └── CreateLinkedList.md # Fast & Slow pointer (Tortoise & Hare) guide
│   ├── UdemyLearn.java         # OOP Fundamentals (Static, Encapsulation, Constructors)
│   └── DP_problems/            # Dynamic programming challenges (WIP)
├── Leetcode/                   # LeetCode solution archive
│   ├── README.md               # LeetCode problem index, DP guide & paradigms
│   ├── 1563_StoneGameV.md      # Interval dynamic programming & prefix sums
│   ├── 1663_SmallestStringWithAGivenNumericValue.md # Greedy & lexicographical minimization
│   ├── 2029_StoneGameIX.md     # Game theory & modulo arithmetic
│   ├── 3069_DistributeElementsIntoTwoArraysI.md     # Array simulation & ArrayList concepts
│   ├── 3116_KthSmallestAmountWithSingleDenominationCombination.md # Binary Search + Inclusion-Exclusion
│   ├── DistributeElementsIntoTwoArraysI.java
│   ├── SmallestStringWithAGivenNumericValue.java
│   ├── StoneGameIX.java
│   ├── StoneGameV.java
│   └── KthSmallestAmountWithSingleDenominationCombination.java
└── PYTHON/
    └── Workshop.ipynb          # Python workshops & computational scripts
```

---

## 🧠 Problem & Topic Index

### 1. LeetCode Challenges
| # | Problem | Difficulty | Category | Key Technique | Details |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **1563** | **Stone Game V** | Hard | Dynamic Programming | Interval DP / Prefix Sums | [Write-up](Leetcode/1563_StoneGameV.md) |
| **1663** | **Smallest String With A Given Numeric Value** | Medium | Greedy | Suffix Allocation / Minimization | [Write-up](Leetcode/1663_SmallestStringWithAGivenNumericValue.md) |
| **2029** | **Stone Game IX** | Medium | Game Theory | Modulo 3 Arithmetic / Invariants | [Write-up](Leetcode/2029_StoneGameIX.md) |
| **3069** | **Distribute Elements Into Two Arrays I** | Easy | Array / Simulation | ArrayList / Dynamic Buffers | [Write-up](Leetcode/3069_DistributeElementsIntoTwoArraysI.md) |
| **3116** | **Kth Smallest Amount With Single Denomination Combination** | Hard | Binary Search / Combinatorics | Inclusion-Exclusion (PIE) + LCM Bitmask | [Write-up](Leetcode/3116_KthSmallestAmountWithSingleDenominationCombination.md) |

### 2. GeeksforGeeks Challenges
| # | Problem | Difficulty | Key Technique | Details |
| :--- | :--- | :--- | :--- | :--- |
| **1** | **Maximum Difference Between Node and Its Ancestor** | Medium | Post-Order Traversal / Bottom-Up Recursion | [Write-up](GeeksforGeeks/MaximumDifferenceBetweenNodeAndItsAncestor.md) |
| **2** | **Transform String** | Medium | Greedy Suffix Matching / Anagram Hashing | [Write-up](GeeksforGeeks/TransformString.md) |

### 3. Java Recursion Deep Dives (`JAVA/1Recursion/`)
| Topic | Concept | Details |
| :--- | :--- | :--- |
| **Tree Recursion** | Multiple branching calls per frame ($\mathcal{O}(2^n)$ calls) | [Write-up](JAVA/1Recursion/TreeRecursion.md) |
| **Indirect Recursion** | Mutual circular calls ($A \leftrightarrow B$) & Java multi-pass method resolution | [Write-up](JAVA/1Recursion/IndirectRecursion.md) |
| **Nested Recursion** | Passing recursive return values as parameters ($f(f(n+11))$) | [Write-up](JAVA/1Recursion/NestedRecursion.md) |
| **Sum of Natural Numbers** | Linear recursion & recurrence relation $S(n) = S(n-1) + n$ | [Write-up](JAVA/1Recursion/SumOfNaturalNumbers.md) |
| **Factorial** | Multiplicative reduction & 32-bit overflow analysis | [Write-up](JAVA/1Recursion/Factorial.md) |
| **Exponentiation (Power)** | Linear $\mathcal{O}(n)$ vs Binary Exponentiation $\mathcal{O}(\log n)$ | [Write-up](JAVA/1Recursion/Power.md) |
| **Taylor Series ($e^x$)** | Direct $\mathcal{O}(n^2)$ vs Static Ascending $\mathcal{O}(n)$ vs Horner's Rule $\mathcal{O}(n)$ | [Write-up](JAVA/1Recursion/TaylorSeries.md) |

### 4. Arrays & Matrices
| Problem | Difficulty | Key Technique | Details |
| :--- | :--- | :--- | :--- |
| **Set Matrix Zeroes** | Medium | Hash Mapping / In-place Markers | [Write-up](Arrays/SetMatrixZeroes.md) |
| **Next Permutation** | Medium | Pivot Finding + Suffix Reversal | [Write-up](Arrays/NextPermutation.md) |
| **Pascal's Triangle** | Easy/Medium | Combinatorics ($\binom{n-1}{r-1}$) & Row Generation | [Write-up](Arrays/PascalsTriangle.md) |

### 5. Linked Lists
| Problem / Structure | Technique | Complexity | Details |
| :--- | :--- | :--- | :--- |
| **Singly Linked List & Middle Node** | Two-Pointer (Slow & Fast / Tortoise & Hare) | Time: $O(N)$, Space: $O(1)$ | [Write-up](JAVA/LinkedList/CreateLinkedList.md) |

### 6. HackerRank Java Challenges
| Challenge | Topic | Concepts Covered | Details |
| :--- | :--- | :--- | :--- |
| **Java Currency Formatter** | String & Number Formatting | `NumberFormat`, `Locale`, Custom Locales | [Write-up](HackerRank/CurrencyFormatter.md) |

---

## 🚀 Quick Execution Guide

```bash
# LeetCode 3116
cd Leetcode
javac KthSmallestAmountWithSingleDenominationCombination.java
java KthSmallestAmountWithSingleDenominationCombination

# GeeksforGeeks Transform String
cd ../GeeksforGeeks
javac TransformString.java
java TransformString

# Recursion Taylor Series (Horner's Rule)
cd ../JAVA/1Recursion
javac taylorSeries2.java
java taylorSeries2
```
