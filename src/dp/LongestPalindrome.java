package dp;

import java.util.Arrays;

public class LongestPalindrome {


    private boolean isPalindrome(String str){
        if(str.length() == 0 || str.length() == 1)return true;
        for (int i = 0; i <str.length()/2 + 1 ; i++) {
            if(str.charAt(i)!=str.charAt(str.length()-1-i)){
                return false;
            }
        }
        return true;
    }

    public String longestPalindrome(String s) {
        if(s == null || s.length() == 1 ||s.length() == 0)return s;
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i <n ; i++) {
            dp[i][i] = 1;
        }
        int max = 1;
        String subString = s.substring(0,1);
        for (int i = 1; i <n ; i++) {
            for (int j = 0; i+j <n ; j++) {
                if((s.charAt(j) == s.charAt(i+j) && i+j-1>=j+1 && isPalindrome(s.substring(j+1,i+j-1)))||(s.charAt(j) == s.charAt(i+j) && i==1) ){
                    dp[j][i+j] = dp[j+1][i+j-1] + 2;
                }else {
                    dp[j][i+j] = Math.max(dp[j][i+j-1],dp[j+1][i+j]);
                }
                if(dp[j][i+j]>max){
                    max = dp[j][i+j];
                    subString = s.substring(j,i+j+1);
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return subString;
    }

    public static void main(String[] args) {
        LongestPalindrome longestPalindrome = new LongestPalindrome();
        System.out.println(longestPalindrome.longestPalindrome("abbbd"));
    }

}
