import java.util.HashMap;

/**
 * GeeksforGeeks: Transform String
 * 
 * Category: Medium
 * Link: https://www.geeksforgeeks.org/problems/transform-string5648/1
 * Topics: Greedy, Two-Pointer (Backward Suffix Matching), Frequency Hashing, String Manipulation
 */
public class TransformString {

    /**
     * Finds the minimum number of operations to transform string A to string B.
     * Allowed operation: Pick any character from A and place it at the front of A.
     * 
     * @param A Initial string
     * @param B Target string
     * @return Minimum operations, or -1 if impossible
     */
    public static int transform(String A, String B) {
        // Step 1: Length check
        if (A.length() != B.length()) {
            return -1;
        }

        // Step 2: Anagram / Character Frequency Check
        // If frequencies don't match, transformation is impossible.
        int[] freq = new int[256];
        for (int i = 0; i < A.length(); i++) {
            freq[A.charAt(i)]++;
            freq[B.charAt(i)]--;
        }

        for (int count : freq) {
            if (count != 0) {
                return -1;
            }
        }

        // Step 3: Greedy Backward Two-Pointer Matching
        int i = A.length() - 1;
        int j = B.length() - 1;
        int operations = 0;

        while (i >= 0 && j >= 0) {
            if (A.charAt(i) == B.charAt(j)) {
                // Characters match in position relative to the suffix
                i--;
                j--;
            } else {
                // A[i] cannot be part of the current matched suffix;
                // It will have to be moved to the front in some operation.
                operations++;
                i--;
            }
        }

        return operations;
    }

    public static void main(String[] args) {
        // Test Case 1:
        String A1 = "abd";
        String B1 = "bad";
        System.out.println("Test Case 1: A = \"abd\", B = \"bad\"");
        System.out.println("Output: " + transform(A1, B1)); // Expected: 1 (Move 'b' to front -> "bad")

        // Test Case 2:
        String A2 = "GeeksForGeeks";
        String B2 = "ForGeeksGeeks";
        System.out.println("\nTest Case 2: A = \"GeeksForGeeks\", B = \"ForGeeksGeeks\"");
        System.out.println("Output: " + transform(A2, B2)); // Expected: 3

        // Test Case 3:
        String A3 = "abc";
        String B3 = "def";
        System.out.println("\nTest Case 3: A = \"abc\", B = \"def\"");
        System.out.println("Output: " + transform(A3, B3)); // Expected: -1
    }
}
