/**
 * LeetCode 2029: Stone Game IX
 * Link: https://leetcode.com/problems/stone-game-ix/
 * 
 * Category: Math / Game Theory / Modulo Arithmetic
 */
public class StoneGameIX {

    public static boolean stoneGameIX(int[] stones) {
        int[] count = new int[3];
        for (int stone : stones) {
            count[stone % 3]++;
        }

        // When count of 0-stones is even
        if (count[0] % 2 == 0) {
            return count[1] >= 1 && count[2] >= 1;
        }

        // When count of 0-stones is odd
        return Math.abs(count[1] - count[2]) > 2;
    }

    public static void main(String[] args) {
        int[] stones1 = {2, 1};
        System.out.println("Test 1 [2, 1] -> Alice wins: " + stoneGameIX(stones1)); // Expected: true

        int[] stones2 = {2};
        System.out.println("Test 2 [2] -> Alice wins: " + stoneGameIX(stones2)); // Expected: false

        int[] stones3 = {5, 1, 2, 4, 3};
        System.out.println("Test 3 [5, 1, 2, 4, 3] -> Alice wins: " + stoneGameIX(stones3)); // Expected: false
    }
}
