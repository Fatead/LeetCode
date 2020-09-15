package array;

import java.util.Arrays;

public class MatrixSum {

    int[][] dp;

    public MatrixSum(int[][] matrix) {
        int row = matrix.length;
        if(row == 0)return;
        int col = matrix[0].length;
        if(col == 0)return;
        dp = new int[row][col];
        dp[0][0] = matrix[0][0];
        for(int i = 1;i<row;i++){
            dp[i][0] = dp[i-1][0] + matrix[i][0];
        }
        for(int j = 1;j<col;j++){
            dp[0][j] = dp[0][j-1] + matrix[0][j];
        }
        for(int i =1;i<row;i++){
            for(int j = 1;j<col;j++){
                int sum = 0;
                for(int n = 0;n<=i;n++){
                    sum += matrix[n][j];
                }
                dp[i][j] = dp[i][j-1] + sum;
            }
        }
        System.out.println(Arrays.deepToString(dp));
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if(row1 == 0 && col1 == 0) return dp[row2][col2];
        if(row1 == 0)return dp[row2][col2] - dp[row2][col1-1];
        if(col1 == 0)return dp[row2][col2] - dp[row1-1][col2];
        return dp[row2][col2] - dp[row2][col1-1] - dp[row1-1][col2] + dp[row1-1][col1-1];
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };
        MatrixSum matrixSum = new MatrixSum(matrix);
        System.out.println(matrixSum.sumRegion(1,2,2,4));
    }
}
