## Set Matrix Zeroes

---

**Leetcode URL:** https://leetcode.com/problems/set-matrix-zeroes/description/

**Problem Statement:**
Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's. 

**Example 1:**

Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]

**Example 2:**

Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]

**Constraints:**

m == matrix.length
n == matrix[0].length
1 <= m, n <= 200
-231 <= matrix[i][j] <= 231 - 1

---

**Solution:**

---

```java
import java.util.HashMap;
import java.util.Scanner;
import java.util.Arrays;

class Solution {
    public void setZeroes(int[][] matrix) {
        
      int m=matrix.length;
      int n=matrix[0].length;
      HashMap<Integer,Boolean> rowMap= new HashMap<>();
      HashMap<Integer,Boolean> colMap= new HashMap<>();

      for (int i=0;i<m;i++){
        for (int j=0;j<n;j++){
            if (matrix[i][j]==0){
                rowMap.put(i,true);
                colMap.put(j,true);
            }
            
        }
      }
      for (int row : rowMap.keySet()){
        for (int j=0;j<n;j++)
             matrix[row][j]=0;
      }
      for (int col : colMap.keySet()){
        for (int j=0;j<m;j++)
             matrix[j][col]=0;
      }
      
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of rows (m):");
        int m = scanner.nextInt();
        System.out.println("Enter number of columns (n):");
        int n = scanner.nextInt();
        
        int[][] matrix = new int[m][n];
        System.out.println("Enter the elements of the matrix:");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        
        Solution solution = new Solution();
        solution.setZeroes(matrix);
        
        System.out.println("Modified Matrix:");
        for (int i = 0; i < m; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
        scanner.close();
    }
}
```
---

**Explanation:**

1. We use two HashMaps, `rowMap` and `colMap`, to keep track of the rows and columns that contain at least one zero.
2. We iterate through the entire matrix. If we encounter a `0` at `matrix[i][j]`, we add the row index `i` to `rowMap` and the column index `j` to `colMap`.
3. After this first pass, we have identified all the unique rows and columns that need to be zeroed out.
4. Next, we iterate through the keys of `rowMap`. For each row index, we iterate through all columns and set the elements in that row to `0`.
5. Finally, we iterate through the keys of `colMap`. For each column index, we iterate through all rows and set the elements in that column to `0`.

**Time Complexity:** 
- **O(m * n)**: The initial pass to find all zeros takes O(m * n) time. The subsequent passes to set the rows and columns to zero take at most O(m * n) time combined, since we iterate through elements multiple times but each pass is bounded by the total dimensions of the matrix. Thus, the overall time complexity remains O(m * n).

**Space Complexity:** 
- **O(m + n)**: We use two HashMaps. `rowMap` can store at most `m` rows, and `colMap` can store at most `n` columns. Therefore, the extra space used scales linearly with the dimensions of the matrix.
---

**Example Walkthrough:**

Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]

1. Initialize `rowMap` and `colMap` as empty HashMaps.
2. First pass (finding zeros):
   - Traverse the matrix row by row.
   - At `matrix[1][1]`, the value is `0`.
   - Add row 1 to `rowMap`: `{1: true}`
   - Add column 1 to `colMap`: `{1: true}`
3. Second pass (setting rows to zero):
   - `rowMap` contains row `1`.
   - Set all elements in row `1` to `0`.
   - Matrix becomes: 
     [[1,1,1]
      [0,0,0]
      [1,1,1]]
4. Third pass (setting columns to zero):
   - `colMap` contains column `1`.
   - Set all elements in column `1` to `0`.
   - Matrix becomes: 
     [[1,0,1]
      [0,0,0]
      [1,0,1]]

Output: [[1,0,1],[0,0,0],[1,0,1]]

---

**Note:** While this solution is concise, it uses O(m + n) extra space. An optimized O(1) space approach exists by using the first row and first column themselves as the markers instead of HashMaps.
