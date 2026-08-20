import java.util.Arrays;
import java.util.HashMap;

/**
 * LeetCode 73: Set Matrix Zeroes
 * Link: https://leetcode.com/problems/set-matrix-zeroes/
 * 
 * Given an m x n integer matrix matrix, if an element is 0,
 * set its entire row and column to 0's.
 */
public class SetMatrixZeroes {

    /**
     * Approach 1: HashMap / HashSet Tracking - O(M * N) Time, O(M + N) Space
     */
    public static void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        HashMap<Integer, Boolean> rowMap = new HashMap<>();
        HashMap<Integer, Boolean> colMap = new HashMap<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rowMap.put(i, true);
                    colMap.put(j, true);
                }
            }
        }

        for (int row : rowMap.keySet()) {
            for (int j = 0; j < n; j++) {
                matrix[row][j] = 0;
            }
        }

        for (int col : colMap.keySet()) {
            for (int j = 0; j < m; j++) {
                matrix[j][col] = 0;
            }
        }
    }

    /**
     * Approach 2: Optimal In-Place Marker - O(M * N) Time, O(1) Space
     */
    public static void setZeroesOptimized(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean firstColZero = false;

        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                firstColZero = true;
            }
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (matrix[0][0] == 0) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        if (firstColZero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix1 = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        };

        setZeroes(matrix1);

        System.out.println("Modified Matrix:");
        for (int[] row : matrix1) {
            System.out.println(Arrays.toString(row));
        }
    }
}
