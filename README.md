# Coding & DSA Knowledge Repository

A structured repository of Data Structures & Algorithms (DSA), competitive programming challenges (LeetCode, HackerRank), Java Object-Oriented Programming (OOP) concepts, and Python development notes.

---

## 📁 Repository Structure

```text
Coding/
├── Arrays/
│   ├── README.md               # Array problem summary & formula cheat sheet
│   ├── SetMatrixZeroes.md      # Matrix manipulation & space optimization
│   ├── NextPermutation.md      # Lexicographical permutation & two-pointer technique
│   └── PascalsTriangle.md      # Combinatorics & dynamic programming
├── HackerRank/
│   ├── README.md               # HackerRank problem directory
│   ├── currencyFormatter.java  # Java solution for currency formatting
│   └── CurrencyFormatter.md    # NumberFormat, Locale & formatting guide
├── JAVA/
│   ├── LinkedList/
│   │   ├── CreateLL.java       # Linked list implementation & middle node finder
│   │   └── CreateLinkedList.md # Fast & Slow pointer (Tortoise & Hare) guide
│   ├── UdemyLearn.java         # OOP Fundamentals (Static, Encapsulation, Constructors)
│   └── DP_problems/            # Dynamic programming challenges (WIP)
├── Leetcode/                   # LeetCode solution archive
│   ├── README.md               # LeetCode problem index & overview
│   ├── 1563_StoneGameV.md      # Interval dynamic programming & prefix sums
│   ├── 1663_SmallestStringWithAGivenNumericValue.md # Greedy & lexicographical minimization
│   └── 2029_StoneGameIX.md     # Game theory & modulo arithmetic
└── PYTHON/
    └── Workshop.ipynb          # Python workshops & computational scripts
```

---

## 🧠 Problem & Topic Index

### 1. Arrays & Matrices
| Problem | Difficulty | Key Technique | Details |
| :--- | :--- | :--- | :--- |
| **Set Matrix Zeroes** | Medium | Hash Mapping / In-place Markers | [Write-up](Arrays/SetMatrixZeroes.md) |
| **Next Permutation** | Medium | Pivot Finding + Suffix Reversal | [Write-up](Arrays/NextPermutation.md) |
| **Pascal's Triangle** | Easy/Medium | Combinatorics ($\binom{n-1}{r-1}$) & Row Generation | [Write-up](Arrays/PascalsTriangle.md) |

### 2. LeetCode Challenges
| # | Problem | Difficulty | Category | Details |
| :--- | :--- | :--- | :--- | :--- |
| **1563** | **Stone Game V** | Hard | Interval DP / Prefix Sum | [Write-up](Leetcode/1563_StoneGameV.md) |
| **1663** | **Smallest String With A Given Numeric Value** | Medium | Greedy / String Manipulation | [Write-up](Leetcode/1663_SmallestStringWithAGivenNumericValue.md) |
| **2029** | **Stone Game IX** | Medium | Game Theory / Modulo Arithmetic | [Write-up](Leetcode/2029_StoneGameIX.md) |

### 3. Linked Lists
| Problem / Structure | Technique | Complexity | Details |
| :--- | :--- | :--- | :--- |
| **Singly Linked List & Middle Node** | Two-Pointer (Slow & Fast / Tortoise & Hare) | Time: $O(N)$, Space: $O(1)$ | [Write-up](JAVA/LinkedList/CreateLinkedList.md) |

### 4. HackerRank Java Challenges
| Challenge | Topic | Concepts Covered | Details |
| :--- | :--- | :--- | :--- |
| **Java Currency Formatter** | String & Number Formatting | `NumberFormat`, `Locale`, Custom Locales | [Write-up](HackerRank/CurrencyFormatter.md) |

### 5. Java Object-Oriented Programming (`JAVA/UdemyLearn.java`)
- **Static Members & Blocks:** Memory allocation at class loading, static methods vs instance methods.
- **Encapsulation:** Private data hiding, public getters & setters, `this` reference.
- **Constructors:** Default vs parameterized constructors, object initialization lifecycle.

---

## 🚀 Quick Execution Guide

### Compile and run Java files:
```bash
# HackerRank Currency Formatter
cd HackerRank
javac currencyFormatter.java
java currencyFormatter

# Linked List Middle Finder
cd ../JAVA/LinkedList
javac CreateLL.java
java CreateLL
```
