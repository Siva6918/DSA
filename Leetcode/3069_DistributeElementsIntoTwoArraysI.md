# LeetCode 3069: Distribute Elements Into Two Arrays I

- **Problem Link:** [3069. Distribute Elements Into Two Arrays I](https://leetcode.com/problems/distribute-elements-into-two-arrays-i/)
- **Difficulty:** Easy
- **Tags:** Array, Simulation, Dynamic Arrays (`ArrayList`), Two Pointers

---

## 📌 Problem Statement

You are given a **1-indexed** array of **distinct** integers `nums` of length `n`.

You need to distribute all the elements of `nums` between two arrays `arr1` and `arr2` using $n$ operations:
1. **Operation 1:** Append `nums[0]` (the 1st element) to `arr1`.
2. **Operation 2:** Append `nums[1]` (the 2nd element) to `arr2`.
3. **Operation $i$ (for $i \ge 2$):**
   - If the **last element of `arr1`** is **strictly greater** than the **last element of `arr2`**, append `nums[i]` to `arr1`.
   - Otherwise, append `nums[i]` to `arr2`.

After processing all elements, concatenate `arr1` and `arr2` such that all elements of `arr1` come first, followed by all elements of `arr2`. Return the combined array.

---

## 🧠 Core Language & Computer Science Concepts Explained

To truly master both the logic and the programming language, let's break down the foundational concepts at play:

### 1. Fixed-Size Primitive Arrays (`int[]`) vs. Dynamic Arrays (`ArrayList<Integer>`)

| Feature | Primitive Array (`int[]`) | Dynamic List (`ArrayList<Integer>`) |
|---|---|---|
| **Size** | Fixed at allocation time (`new int[n]`) | Dynamically resizes automatically |
| **Data Types** | Stores raw primitive values directly (`int`) | Stores Object references (`Integer` wrapper) |
| **Memory Overhead** | Minimal (contiguous flat memory buffer) | Higher (Object headers, references, boxing) |
| **Access Syntax** | `arr[index]` | `arr.get(index)` |
| **Append Syntax** | `arr[index++] = val` | `arr.add(val)` |

---

### 2. Interface Polymorphism: Why `List<Integer> list = new ArrayList<>()`?
In Java, you frequently see:
```java
List<Integer> arr1 = new ArrayList<>();
```
Instead of:
```java
ArrayList<Integer> arr1 = new ArrayList<>();
```
- **Why?** `List` is an **interface** that defines a contract of behavior (`add`, `get`, `size`), while `ArrayList` is a **concrete class** implementing that interface.
- Writing to the interface (`List`) allows you to swap the underlying implementation (e.g., to `LinkedList` or `Vector`) without modifying any of your business logic.

---

### 3. Autoboxing and Auto-Unboxing
Java collections cannot store primitives directly (e.g., `ArrayList<int>` is illegal syntax). Instead, they store wrapper objects (`ArrayList<Integer>`).

```java
arr1.add(nums[i]); 
// ⬆️ AUTOBOXING: Primitive 'int' is automatically boxed into an 'Integer' object via Integer.valueOf(nums[i])

if (arr1.get(arr1.size() - 1) > arr2.get(arr2.size() - 1))
// ⬆️ AUTO-UNBOXING: 'Integer' objects are automatically unboxed to primitive 'int' via .intValue() for comparison
```

---

### 4. Efficient Array Concatenation: `System.arraycopy`
When copying contiguous chunks of memory in Java, `System.arraycopy()` is a native JNI (C-level) function:
```java
System.arraycopy(srcArray, srcPos, destArray, destPos, length);
```
It is much faster than manual loops because the JVM performs a direct block memory copy (`memcpy`).

---

## 🌲 Visual Simulation & Step-by-Step Walkthrough

### Example: `nums = [5, 4, 3, 8]`

```
Initial State:
nums = [ 5 , 4 , 3 , 8 ]
         ^   ^   ^   ^
         0   1   2   3

Step 1: Put nums[0] into arr1 -> arr1 = [5]
Step 2: Put nums[1] into arr2 -> arr2 = [4]

Step 3 (i = 2, val = 3):
   Last of arr1 = 5
   Last of arr2 = 4
   Compare: 5 > 4 (True) -> Append 3 to arr1
   arr1 = [5, 3], arr2 = [4]

Step 4 (i = 3, val = 8):
   Last of arr1 = 3
   Last of arr2 = 4
   Compare: 3 > 4 (False) -> Append 8 to arr2
   arr1 = [5, 3], arr2 = [4, 8]

Final Concatenation:
   result = arr1 + arr2 = [5, 3, 4, 8]
```

---

## 🧪 Dry Run State Table

| Index $i$ | `nums[i]` | `arr1.last()` | `arr2.last()` | Condition (`last1 > last2`) | Action | `arr1` State | `arr2` State |
|---|---|---|---|---|---|---|---|
| **0** | `5` | - | - | Initial Init | Put in `arr1` | `[5]` | `[]` |
| **1** | `4` | - | - | Initial Init | Put in `arr2` | `[5]` | `[4]` |
| **2** | `3` | `5` | `4` | $5 > 4$ (True) | Add to `arr1` | `[5, 3]` | `[4]` |
| **3** | `8` | `3` | `4` | $3 > 4$ (False) | Add to `arr2` | `[5, 3]` | `[4, 8]` |

**Result:** `[5, 3, 4, 8]`

---

## 💻 Clean Java Solution (Approach 1: `ArrayList`)

```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();
        
        // Initial 2 operations
        arr1.add(nums[0]);
        arr2.add(nums[1]);
        
        // Distribute remaining elements
        for (int i = 2; i < n; i++) {
            int last1 = arr1.get(arr1.size() - 1);
            int last2 = arr2.get(arr2.size() - 1);
            
            if (last1 > last2) {
                arr1.add(nums[i]);
            } else {
                arr2.add(nums[i]);
            }
        }
        
        // Build final concatenated array
        int[] result = new int[n];
        int idx = 0;
        
        for (int num : arr1) {
            result[idx++] = num;
        }
        for (int num : arr2) {
            result[idx++] = num;
        }
        
        return result;
    }
}
```

---

## ⚡ High-Performance Java Solution (Approach 2: Primitive Array Simulation)

```java
class SolutionOptimized {
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        int[] arr1 = new int[n];
        int[] arr2 = new int[n];
        
        int size1 = 0, size2 = 0;
        
        arr1[size1++] = nums[0];
        arr2[size2++] = nums[1];
        
        for (int i = 2; i < n; i++) {
            if (arr1[size1 - 1] > arr2[size2 - 1]) {
                arr1[size1++] = nums[i];
            } else {
                arr2[size2++] = nums[i];
            }
        }
        
        int[] result = new int[n];
        System.arraycopy(arr1, 0, result, 0, size1);
        System.arraycopy(arr2, 0, result, size1, size2);
        
        return result;
    }
}
```

---

## 💻 C++ Solution (for Comparison)

```cpp
#include <vector>
using namespace std;

class Solution {
public:
    vector<int> resultArray(vector<int>& nums) {
        vector<int> arr1, arr2;
        
        arr1.push_back(nums[0]);
        arr2.push_back(nums[1]);
        
        for (int i = 2; i < nums.size(); i++) {
            if (arr1.back() > arr2.back()) {
                arr1.push_back(nums[i]);
            } else {
                arr2.push_back(nums[i]);
            }
        }
        
        // Concatenate arr2 into arr1
        arr1.insert(arr1.end(), arr2.begin(), arr2.end());
        return arr1;
    }
};
```

---

## ⏱️ Complexity Analysis

| Approach | Time Complexity | Space Complexity | Why? |
|---|---|---|---|
| **Approach 1 (`ArrayList`)** | $\mathcal{O}(N)$ | $\mathcal{O}(N)$ | Single linear pass of $N$ elements + linear concatenation pass. |
| **Approach 2 (Primitive Arrays)** | $\mathcal{O}(N)$ | $\mathcal{O}(N)$ | Single pass with $\mathcal{O}(1)$ native block memory copy (`System.arraycopy`). |
