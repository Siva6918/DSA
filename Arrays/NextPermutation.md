## Next Permutation

---

**Leetcode URL:** https://leetcode.com/problems/next-permutation/

**Problem Statement:**
The **Next Permutation** problem asks us to rearrange numbers into the **next lexicographically greater permutation**.
Lexicographical order means dictionary order. The goal is to move from one arrangement to the next larger arrangement.
If the array is already the largest permutation, we return the smallest permutation (sorted ascending).

**Example 1:**

Input: nums = [1,2,3]
Output: [1,3,2]

**Example 2:**

Input: nums = [3,2,1]
Output: [1,2,3]

**Example 3:**

Input: nums = [1,1,5]
Output: [1,5,1]

**Constraints:**

* 1 <= nums.length <= 100
* 0 <= nums[i] <= 100

---

**Solution:**

---

```java
class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 2;

        // Step 1: Find breakpoint
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // Step 2: Find next greater element and swap
        if (i >= 0) {
            int j = n - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }

        // Step 3: Reverse using two pointers
        reverse(nums, i + 1, n - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }
}
```
---

**Explanation:**

The algorithm works in **three logical steps**:

1. **Step 1 — Find the Breakpoint:** Traverse from the **right side** and find the first index `i` where `nums[i] < nums[i+1]`. This means the sequence stops increasing from the right. If no such index exists, the entire array is in descending order (the largest permutation).
2. **Step 2 — Find the Next Greater Element:** From the **right side again**, find the **smallest element greater than nums[i]**. We denote its index as `j`. Swap `nums[i]` and `nums[j]`.
3. **Step 3 — Reverse the Remaining Array:** The part after index `i` is in **descending order**, so we reverse it to make it **ascending**. We use the two-pointer technique to reverse the subarray from `i+1` to `n-1`.

**Time Complexity:** 
- **O(N)**: We perform at most three passes over the array: one to find the breakpoint, one to find the swap element, and one to reverse the remaining elements. Each step takes linear time in the worst case.

**Space Complexity:** 
- **O(1) in-place**: We modify the input array directly and only use a few auxiliary variables (`i`, `j`, `left`, `right`, `temp`), meaning no extra space that scales with the input size is required.

---

**Example Walkthrough:**

Input: `nums = [1, 3, 5, 4, 2]`

1. **Find Breakpoint:**
    - `i = n - 2`. We traverse backwards: `2 < 4` (no), `4 < 5` (no), `5 < 3` (no), `3 < 5` (yes).
    - Breakpoint found at `i = 1` (value is `3`).
2. **Find Next Greater Element:**
    - Since `i >= 0`, we find an element greater than `3` from the right.
    - `j = n - 1`. We check `2 > 3` (no), `4 > 3` (yes).
    - Next greater element found at `j = 3` (value is `4`).
3. **Swap:**
    - Swap `nums[1]` (3) and `nums[3]` (4).
    - Array becomes: `[1, 4, 5, 3, 2]`.
4. **Reverse:**
    - Reverse the subarray starting from `i+1 = 2` to the end (`5, 3, 2`).
    - Using two pointers (`left = 2`, `right = 4`), swap elements until `left >= right`.
    - Array becomes: `[1, 4, 2, 3, 5]`.

Output: `[1, 4, 2, 3, 5]`

---

**Deep Dive into Concepts Used:**

### 1. Two-Pointer Technique
The **Two-Pointer technique** is a common algorithmic strategy where two variable references ("pointers") are used to iterate through a data structure (usually an array, string, or linked list) to achieve a specific goal. They are often initialized at different positions and move towards each other, parallel to each other, or at varying speeds.

Here are the different ways the two-pointer pattern is commonly used:

#### A. Opposite Ends (Meet in the Middle)
This is the approach we used to **reverse the subarray** in step 3.
- **How it works:** Initialize one pointer at the start `left` and another at the end `right`. Move them towards each other (`left++`, `right--`) until they meet or cross (`left >= right`).
- **Common Uses:**
    - Reversing an array or string.
    - Checking for palindromes (e.g., checking if `"racecar"` is a palindrome).
    - Two Sum problem on a sorted array (finding two numbers that add up to a target).

#### B. Same Direction (Fast and Slow Pointers / Tortoise and Hare)
- **How it works:** Both pointers start from the beginning but move at different speeds or increments.
- **Common Uses:**
    - Detecting a cycle in a linked list (Floyd's Cycle-Finding Algorithm).
    - Finding the middle of a linked list (fast pointer moves 2 steps, slow moves 1 step).
    - Removing duplicates from a sorted array in-place.

#### C. Sliding Window
- **How it works:** Two pointers represent the boundaries of a "window" (subarray or substring). The `right` pointer expands the window to include elements, while the `left` pointer shrinks the window from the left to satisfy a specific condition.
- **Common Uses:**
    - Finding the longest substring without repeating characters.
    - Finding the minimum size subarray sum that is greater than or equal to a target.

---

### Brutally Honest Interview Insight

Many beginners try to generate all permutations, which costs `O(N!)`. That is catastrophic for interviews. The expected solution uses pattern recognition in permutations and in-place modification using two pointers, giving `O(N)`.

Companies like Amazon, Google, and Microsoft love this problem because it tests:
- Pattern recognition
- Array manipulation
- The Two-Pointer technique
- Understanding of sequential generation of elements
