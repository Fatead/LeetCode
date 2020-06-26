package dp;

import java.util.Arrays;

public class EditDis {

    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        if(m == 0)return n;
        if(n == 0)return m;
        int[][] dp = new int[m+1][n+1];
        for(int i = 0;i<m+1;i++){
            dp[i][0] = i;
        }
        for(int i = 0;i<n+1;i++){
            dp[0][i] = i;
        }
        int num = 0;
        for(int i =1;i<m+1;i++){
            for(int j =1;j<n+1;j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    num = 0;
                }else {
                    num = 1;
                }
                dp[i][j] = Math.min(dp[i-1][j-1] + num,Math.min(dp[i-1][j] + 1,dp[i][j-1] + 1));
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "ros";
        EditDis editDis = new EditDis();
        System.out.println(editDis.minDistance(word1,word2));
    }
}
