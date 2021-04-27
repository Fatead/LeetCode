package dp;

import java.util.Arrays;

public class MinFallingPathSum {

    public int minFallingPathSum(int[][] matrix) {
        if(matrix.length==0||matrix[0].length==0)return 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i <matrix[0].length ; i++) {
            dp[0][i] = matrix[0][i];
        }
        for (int i = 1; i <matrix.length ; i++) {
            for (int j = 0; j <matrix[0].length ; j++) {
                int min = dp[i-1][j];
                if(j>0){
                    min = Math.min(min,dp[i-1][j-1]);
                }
                if(j<matrix[0].length-1){
                    min = Math.min(min,dp[i-1][j+1]);
                }
                dp[i][j] = min + matrix[i][j];
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <matrix.length ; i++) {
            min = Math.min(min,dp[matrix.length-1][i]);
        }
        return min;
    }

    public static void main(String[] args) {
        MinFallingPathSum minFallingPathSum = new MinFallingPathSum();
        int[][] matrix = {
                {10,-98,44},
                {-20,65,34},
                {-100,-1,74}
        };
        System.out.println(minFallingPathSum.minFallingPathSum(matrix));
    }

}
