import java.util.Arrays;
import java.util.Scanner;

/**
 * LeetCode 3116: Kth Smallest Amount With Single Denomination Combination
 * 
 * Category: Hard
 * Topics: Binary Search, Inclusion-Exclusion Principle, Bitmasking, Number Theory (GCD & LCM)
 */
public class KthSmallestAmountWithSingleDenominationCombination {

    /**
     * Finds the kth smallest amount that can be generated using a single denomination combination.
     * 
     * @param coins Array of coin denominations
     * @param k The target rank (1-indexed)
     * @return The kth smallest amount
     */
    public static long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        
        // Find minimum coin to establish upper bound for binary search
        long minCoin = coins[0];
        for (int coin : coins) {
            minCoin = Math.min(minCoin, coin);
        }
        
        long low = 1;
        long high = minCoin * k;
        long ans = high;
        
        while (low <= high) {
            long mid = low + (high - low) / 2;
            
            if (countMultiples(mid, coins) >= k) {
                ans = mid;
                high = mid - 1; // Try to find a smaller valid amount
            } else {
                low = mid + 1;  // Amount too small, need more multiples
            }
        }
        
        return ans;
    }

    /**
     * Counts how many positive integers <= target are divisible by at least one coin
     * using the Principle of Inclusion-Exclusion (PIE) via bitmask subset enumeration.
     */
    private static long countMultiples(long target, int[] coins) {
        int n = coins.length;
        long totalCount = 0;
        
        // Iterate through all 2^n - 1 non-empty subsets
        int totalSubsets = 1 << n;
        for (int mask = 1; mask < totalSubsets; mask++) {
            long currentLcm = 1;
            int bitCount = 0;
            boolean overflow = false;
            
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    bitCount++;
                    currentLcm = lcm(currentLcm, coins[i]);
                    if (currentLcm > target) {
                        overflow = true;
                        break;
                    }
                }
            }
            
            if (overflow) continue;
            
            long count = target / currentLcm;
            if (bitCount % 2 == 1) {
                totalCount += count; // Add odd-sized subsets
            } else {
                totalCount -= count; // Subtract even-sized subsets
            }
        }
        
        return totalCount;
    }

    /**
     * Greatest Common Divisor using Euclidean Algorithm
     */
    private static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    /**
     * Least Common Multiple
     */
    private static long lcm(long a, long b) {
        if (a == 0 || b == 0) return 0;
        return (a / gcd(a, b)) * b;
    }

    public static void main(String[] args) {
        // Test Case 1
        int[] coins1 = {3, 6, 9};
        int k1 = 3;
        System.out.println("Test Case 1: coins = [3, 6, 9], k = 3");
        System.out.println("Output: " + findKthSmallest(coins1, k1)); // Expected: 9 (Multiples: 3, 6, 9)

        // Test Case 2
        int[] coins2 = {5, 2};
        int k2 = 7;
        System.out.println("\nTest Case 2: coins = [5, 2], k = 7");
        System.out.println("Output: " + findKthSmallest(coins2, k2)); // Expected: 12 (Multiples: 2, 4, 5, 6, 8, 10, 12)
    }
}
