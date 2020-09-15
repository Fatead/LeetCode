package dp;

import java.util.Arrays;

public class LongestIncreaseSequence {

    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0)return 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp,1);
        for(int i = 1;i<nums.length;i++){
            int maxPre = 1;
            for(int j = 0;j<i;j++){
                if(nums[i] > nums[j]){
                    maxPre = Math.max(maxPre,dp[j] + 1);
                }
            }
            dp[i] = maxPre;
        }
        int result = 0;
        for(int i:dp){
            if(i > result){
                result = i;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int[] array = {4,10,4,3,8,9};
        LongestIncreaseSequence longestIncreaseSequence = new LongestIncreaseSequence();
        int result = longestIncreaseSequence.lengthOfLIS(array);
        System.out.println(result);
    }
}
