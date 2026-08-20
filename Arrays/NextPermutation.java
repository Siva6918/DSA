import java.util.Arrays;

/**
 * LeetCode 31: Next Permutation
 * Link: https://leetcode.com/problems/next-permutation/
 * 
 * Rearranges numbers into the lexicographically next greater permutation.
 * If no such arrangement is possible, rearranges as the lowest possible order (sorted ascending).
 */
public class NextPermutation {

    public static void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 2;

        // Step 1: Find breakpoint (first decreasing element from the right)
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // Step 2: Find next greater element from the right and swap
        if (i >= 0) {
            int j = n - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }

        // Step 3: Reverse the remaining suffix to make it ascending
        reverse(nums, i + 1, n - 1);
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        int[] test1 = {1, 2, 3};
        nextPermutation(test1);
        System.out.println("Next Permutation for [1, 2, 3]: " + Arrays.toString(test1)); // [1, 3, 2]

        int[] test2 = {3, 2, 1};
        nextPermutation(test2);
        System.out.println("Next Permutation for [3, 2, 1]: " + Arrays.toString(test2)); // [1, 2, 3]

        int[] test3 = {1, 1, 5};
        nextPermutation(test3);
        System.out.println("Next Permutation for [1, 1, 5]: " + Arrays.toString(test3)); // [1, 5, 1]
    }
}
