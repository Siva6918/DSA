## Pascal's Triangle

---

**Leetcode URL:** https://leetcode.com/problems/pascals-triangle/

**Problem Statement:**
Given an integer `numRows`, return the first `numRows` of Pascal's triangle.
In Pascal's triangle, each number is the sum of the two numbers directly above it.

**Example 1:**

Input: numRows = 5
Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]

**Example 2:**

Input: numRows = 1
Output: [[1]]

**Constraints:**

* 1 <= numRows <= 30

---

**Solution:**

---

```java
import java.util.*;

class Solution {
    public List<List<Integer>> generate(int numRows) {
        
        List<List<Integer>> triangle = new ArrayList<>();

        for(int i = 0; i < numRows; i++) {
            
            List<Integer> row = new ArrayList<>();

            for(int j = 0; j <= i; j++) {

                if(j == 0 || j == i) {
                    row.add(1);
                } 
                else {
                    int val = triangle.get(i-1).get(j-1) + triangle.get(i-1).get(j);
                    row.add(val);
                }
            }

            triangle.add(row);
        }

        return triangle;
    }
}
```
---

**Explanation:**

1. We initialize a 2D list called `triangle` to store the generated rows.
2. We run an outer loop `i` from `0` to `numRows - 1`. This loop manages the generation of each row.
3. For each row, we initialize a 1D list called `row` to store the elements of that specific row.
4. Inside, we run an inner loop `j` from `0` to `i`, because the `i`-th row (0-indexed) exactly has `i + 1` elements.
5. If `j == 0` (the first element of the row) or `j == i` (the last element of the row), we simply add `1` because the edges of Pascal's Triangle are always `1`.
6. If `j` is neither the first nor the last element, the value is calculated by taking the sum of the two elements directly above it from the previous row: `triangle.get(i-1).get(j-1) + triangle.get(i-1).get(j)`.
7. Once the inner loop finishes, the `row` is complete and we add it to the `triangle`.
8. Finally, we return the fully constructed `triangle`.

**Time Complexity:** 
- **O(numRows²)**: The outer loop runs `numRows` times. The inner loop runs `i + 1` times for each `i`. Total operations roughly equals `1 + 2 + 3 + ... + numRows = (numRows * (numRows + 1)) / 2`, which simplifies to O(numRows²).

**Space Complexity:** 
- **O(1) extra space**: We are only using the space required for the output list `triangle` itself. Excluding the output space, we only use a few variables (like `i` and `j`), resulting in O(1) auxiliary space. The output list takes O(numRows²) space to store all the elements.

---

**Example Walkthrough:**

Input: numRows = 4

1. Initialize `triangle = []`.
2. First Row `i = 0`: 
    - `row = []`.
    - Inner loop `j = 0`: `j == 0`, append 1. `row = [1]`.
    - Add `row` to `triangle`: `triangle = [[1]]`.
3. Second Row `i = 1`:
    - `row = []`.
    - Inner loop `j = 0`: `j == 0`, append 1. `row = [1]`.
    - Inner loop `j = 1`: `j == i`, append 1. `row = [1, 1]`.
    - Add `row` to `triangle`: `triangle = [[1], [1, 1]]`.
4. Third Row `i = 2`:
    - `row = []`.
    - Inner loop `j = 0`: `j == 0`, append 1. `row = [1]`.
    - Inner loop `j = 1`: Sum of previous row elements at indices 0 and 1 -> `1 + 1 = 2`. `row = [1, 2]`.
    - Inner loop `j = 2`: `j == i`, append 1. `row = [1, 2, 1]`.
    - Add `row` to `triangle`: `triangle = [[1], [1, 1], [1, 2, 1]]`.
5. Fourth Row `i = 3`:
    - `row = []`.
    - Inner loop `j = 0`: append 1. `row = [1]`.
    - Inner loop `j = 1`: `1 + 2 = 3`. `row = [1, 3]`.
    - Inner loop `j = 2`: `2 + 1 = 3`. `row = [1, 3, 3]`.
    - Inner loop `j = 3`: append 1. `row = [1, 3, 3, 1]`.
    - Add `row` to `triangle`: `triangle = [[1], [1, 1], [1, 2, 1], [1, 3, 3, 1]]`.

Output: `[[1], [1, 1], [1, 2, 1], [1, 3, 3, 1]]`

---

**Deep Dive into Concepts Used:**

### 1. Dynamic Programming (DP)

**What is it?**
Dynamic Programming is an algorithmic technique for solving an optimization or computation problem by breaking it down into simpler overlapping subproblems. The core idea is: **instead of solving the same subproblem repeatedly, solve it once and store the result for future use (memoization or tabulation)**. 

**How is it used in Pascal's Triangle?**
While Pascal's Triangle isn't an optimization problem (we aren't finding a "maximum" or "minimum"), it perfectly illustrates the **tabulation** approach of DP. 
- **Substructure:** The value of an element at row `i` and column `j` depends squarely on the values from the previous row `i-1`. 
- **State Transition Equation:** `dp[i][j] = dp[i-1][j-1] + dp[i-1][j]`.
By storing the previous rows in our `triangle` list, we reuse these previously computed sums to generate the next row instead of recalculating combinations from scratch every time.

**Another Example: The Fibonacci Sequence**
Finding the N-th Fibonacci number. 
- *Without DP (Recursion):* `fib(n) = fib(n-1) + fib(n-2)`. This recalculates `fib(n-2)` multiple times, taking exponential time `O(2^n)`.
- *With DP (Tabulation):* We maintain an array where `arr[i] = arr[i-1] + arr[i-2]`. Instead of recalculating, we just look up the previous two values. This takes linear time `O(n)`.

### 2. Arrays and Dynamic Arrays (Lists)

**What are they?**
An array is a data structure consisting of a collection of elements identified by an index or key, stored in contiguous memory locations. Arrays typically have a *fixed size*.

In our Java code, we use `ArrayList`, which is a **Dynamic Array**. Unlike static arrays, a dynamic array can automatically grow or shrink in size as elements are added or removed.

**1D Array (or List):** 
A standard list of elements. In our code, `List<Integer> row` represents a 1D Array containing the specific numbers for a single row of Pascal's triangle.
*Example:* `[1, 3, 3, 1]`

**2D Array (or List of Lists):**
An array of arrays. In our code, `List<List<Integer>> triangle` is a 2D Array where each entry is itself a row (a 1D array).
*Example:*
```java
[
  [1],
  [1, 1],
  [1, 2, 1]
]
```

**Why Dynamic Arrays instead of Fixed Arrays here?**
In Java, a standard 2D array is created as `int[][] arr = new int[rows][cols];` which forms a rectangle memory shape. However, Pascal's triangle is "jagged"—the first row has 1 element, the second has 2, the third has 3, etc. Using a `List<List<Integer>>` easily handles appending rows of different lengths, providing a lot of flexibility when we don't know (or don't want to statically define) the size of each row straight away.
