package dp;

import java.util.LinkedList;
import java.util.List;

public class PartitionStr2 {

    private boolean isPalindrome(String str,int left,int right){
        if(str.length() == 1)return true;
        while(left<right){
            if(str.charAt(left)!=str.charAt(right))return false;
            left++;
            right--;
        }
        return true;
    }

    public int minCut(String s) {
        if(s == null||s.length() < 2){
            return 0;
        }
        int len = s.length();
        int[] dp = new int[len];
        for(int i =0 ;i<len;i++){
            dp[i] = i;
        }
        for(int i = 1;i<len;i++){
            if(isPalindrome(s,0,i)){
                dp[i] = 0;
                continue;
            }
            for(int j = 0;j<i;j++){
                if(isPalindrome(s,j+1,i)){
                    dp[i] = Math.min(dp[i],dp[j] + 1);
                }
            }
        }
        return dp[len-1];
    }

    public static void main(String[] args) {


    }
}
