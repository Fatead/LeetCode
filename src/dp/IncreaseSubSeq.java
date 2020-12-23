package dp;

import java.util.Arrays;

public class IncreaseSubSeq {

    public int findLengthOfLCIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp,1);
        for(int i = 1;i<nums.length;i++){
            if(nums[i] > nums[i-1]){
                dp[i] = dp[i-1] + 1;
            }
        }
        int max = 0;
        for(int i:dp){
            max = Math.max(max,i);
        }
        return max;
    }

    public static void main(String[] args) {
        IncreaseSubSeq increaseSubSeq = new IncreaseSubSeq();
        int[] nums = {1,3,5,4,7};
        System.out.println(increaseSubSeq.findLengthOfLCIS(nums));
    }

}
