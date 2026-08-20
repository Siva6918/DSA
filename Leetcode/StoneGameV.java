/**
 * LeetCode 1563: Stone Game V
 * Link: https://leetcode.com/problems/stone-game-v/
 * 
 * Category: Dynamic Programming / Interval DP / Prefix Sum / Game Theory
 */
public class StoneGameV {

    private int[][] memo;
    private int[] prefix;

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        memo = new int[n][n];
        prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        return solve(0, n - 1);
    }

    private int solve(int i, int j) {
        if (i >= j) return 0;
        if (memo[i][j] != 0) return memo[i][j];

        int maxScore = 0;

        for (int k = i; k < j; k++) {
            int leftSum = prefix[k + 1] - prefix[i];
            int rightSum = prefix[j + 1] - prefix[k + 1];

            if (leftSum < rightSum) {
                maxScore = Math.max(maxScore, leftSum + solve(i, k));
            } else if (leftSum > rightSum) {
                maxScore = Math.max(maxScore, rightSum + solve(k + 1, j));
            } else {
                int leftOption = leftSum + solve(i, k);
                int rightOption = rightSum + solve(k + 1, j);
                maxScore = Math.max(maxScore, Math.max(leftOption, rightOption));
            }
        }

        return memo[i][j] = maxScore;
    }

    public static void main(String[] args) {
        StoneGameV solver = new StoneGameV();
        int[] stones1 = {6, 2, 3, 4, 5, 5};
        System.out.println("Stone Game V Result (Expected 18): " + solver.stoneGameV(stones1));

        StoneGameV solver2 = new StoneGameV();
        int[] stones2 = {7, 7, 7, 7, 7, 7, 7};
        System.out.println("Stone Game V Result (Expected 28): " + solver2.stoneGameV(stones2));
    }
}
