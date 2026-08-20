import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 3069: Distribute Elements Into Two Arrays I
 * Link: https://leetcode.com/problems/distribute-elements-into-two-arrays-i/
 * 
 * Concepts Demonstrated:
 * 1. Dynamic Arrays (ArrayList vs primitive int[])
 * 2. Autoboxing & Auto-unboxing in Java
 * 3. List Interface vs ArrayList Implementation (Polymorphism)
 * 4. Two-Pointer Pre-allocation vs Dynamic Collections
 */
public class DistributeElementsIntoTwoArraysI {

    /**
     * Approach 1: Standard Dynamic Array (ArrayList) Simulation
     * Clean, intuitive, and standard interview implementation.
     */
    public static int[] resultArray(int[] nums) {
        int n = nums.length;

        // Using List interface reference with ArrayList implementation (Abstraction)
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();

        // Operation 1 & 2: First element to arr1, second element to arr2
        arr1.add(nums[0]);
        arr2.add(nums[1]);

        // Operation 3 to n: Compare last elements
        for (int i = 2; i < n; i++) {
            // arr1.get(arr1.size() - 1) gets the last element
            // Unboxing converts Integer -> int for '>' comparison
            if (arr1.get(arr1.size() - 1) > arr2.get(arr2.size() - 1)) {
                arr1.add(nums[i]);
            } else {
                arr2.add(nums[i]);
            }
        }

        // Concatenate arr1 followed by arr2 into result array
        int[] result = new int[n];
        int index = 0;

        for (int val : arr1) {
            result[index++] = val;
        }
        for (int val : arr2) {
            result[index++] = val;
        }

        return result;
    }

    /**
     * Approach 2: Ultra-Fast Fixed Array Simulation (Zero Allocation Overhead)
     * Demonstrates deep understanding of memory and indexing without wrapper objects.
     */
    public static int[] resultArrayOptimized(int[] nums) {
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

    public static void main(String[] args) {
        int[] test1 = {2, 1, 3};
        int[] test2 = {5, 4, 3, 8};

        System.out.println("Test 1 Result: " + Arrays.toString(resultArray(test1))); // Expected: [2, 3, 1]
        System.out.println("Test 2 Result: " + Arrays.toString(resultArray(test2))); // Expected: [5, 3, 4, 8]
    }
}
