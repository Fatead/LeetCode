package dp;

import java.util.Arrays;

public class NumberOfLIS {

    public int findNumberOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int[] count = new int[nums.length];
        Arrays.fill(count,1);
        Arrays.fill(dp,1);
        for(int i = 0;i<nums.length;i++){
            for(int j = 0;j<i;j++){
                if(nums[i] > nums[j]){
                    if(dp[i] <= dp[j]){
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    }else if(dp[j] + 1 == dp[i]){
                        count[i] = count[i] + count[j];
                    }
                }
            }
        }
        int longest = Integer.MIN_VALUE;
        for (int value : dp) {
            longest = Math.max(longest, value);
        }
        int result = 0;
        for(int i = 0;i<dp.length;i++){
            if(dp[i] == longest){
                result += count[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        NumberOfLIS numberOfLIS = new NumberOfLIS();
        int[] nums = {1,2,4,3,5,4,7,2};
        int result = numberOfLIS.findNumberOfLIS(nums);
        System.out.println(result);
    }
}
