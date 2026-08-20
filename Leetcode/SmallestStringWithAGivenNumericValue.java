import java.util.Arrays;

/**
 * LeetCode 1663: Smallest String With A Given Numeric Value
 * Link: https://leetcode.com/problems/smallest-string-with-a-given-numeric-value/
 * 
 * Category: Greedy / String Manipulation / Math
 */
public class SmallestStringWithAGivenNumericValue {

    public static String getSmallestString(int n, int k) {
        char[] result = new char[n];
        Arrays.fill(result, 'a');

        int remaining = k - n; // Each 'a' already accounts for 1 unit

        for (int i = n - 1; i >= 0 && remaining > 0; i--) {
            int add = Math.min(25, remaining);
            result[i] = (char) ('a' + add);
            remaining -= add;
        }

        return new String(result);
    }

    public static void main(String[] args) {
        System.out.println("n = 3, k = 27 -> " + getSmallestString(3, 27)); // Expected: "aay"
        System.out.println("n = 5, k = 73 -> " + getSmallestString(5, 73)); // Expected: "aaszz"
    }
}
