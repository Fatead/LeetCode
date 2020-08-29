package dp;

public class MaxSquare {


    public int maximalSquare(char[][] matrix) {
        if(matrix.length == 0)return 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for(int i = 0;i<matrix[0].length;i++){
            dp[0][i] = matrix[0][i] - '0';
        }
        for(int i = 0;i<matrix.length;i++){
            dp[i][0] = matrix[i][0] - '0';
        }
        for(int i = 1;i<matrix.length;i++){
            for(int j = 1;j<matrix[0].length;j++){
                if(matrix[i][j] == '0'){
                    dp[i][j] = 0;
                }else if(dp[i-1][j] == 0||dp[i][j-1] == 0){
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1])) + 1;
                }
                System.out.print(dp[i][j] + " ");
            }
            System.out.println("");
        }
        int max = Integer.MIN_VALUE;
        for(int i = 0;i<matrix.length;i++){
            for(int j = 0;j<matrix[0].length;j++){
                if(dp[i][j] > max){
                    max = dp[i][j];
                }
            }
        }
        return max*max;
    }



    public static void main(String[] args) {
        char[][] array = {
                {'1','1','1','1','0'},
                {'1','1','1','1','0'},
                {'1','1','1','1','1'},
                {'1','1','1','1','1'},
                {'0','0','1','1','1'}
        };
        MaxSquare maxSquare = new MaxSquare();
        int result = maxSquare.maximalSquare(array);
        System.out.println(result);
    }
}
