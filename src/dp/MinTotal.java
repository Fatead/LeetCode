package dp;

import java.util.List;

public class MinTotal {

    public int minimumTotal(List<List<Integer>> triangle) {
        int len = triangle.size();
        int[][] array = new int[len][len];
        int[][] dp = new int[len][len];
        for(int i = 0;i<len;i++){
            for(int j = 0;j<=i;j++){
                array[i][j] = triangle.get(i).get(j);
            }
        }
        dp[0][0] = array[0][0];
        for(int i = 1;i<len;i++){
            dp[i][0] = dp[i-1][0] + array[i][0];
            dp[i][i] = dp[i-1][i-1] + array[i][i];
        }
        for(int i = 1;i<len;i++){
            for(int j = 1;j<i;j++){
                dp[i][j] = Math.min(dp[i-1][j-1],dp[i-1][j]) + array[i][j];
            }
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0;i<=len;i++){
            if(dp[len-1][i]<min){
                min = dp[len-1][i];
            }
        }
        return min;
    }
}
