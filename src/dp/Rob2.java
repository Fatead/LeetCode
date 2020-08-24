package dp;

import java.util.Arrays;

public class Rob2 {



    private int cal(int[] nums){
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        for(int i = 2;i<nums.length;i++){
            dp[i] = Math.max(dp[i-1],dp[i-2] + nums[i]);
        }
        return dp[nums.length-1];
    }

    public int rob(int[] nums) {
        int len = nums.length;
        if(len == 0)return 0;
        if(len == 1)return nums[0];
        if(len == 2)return Math.max(nums[0],nums[1]);
        return Math.max(cal(Arrays.copyOfRange(nums,1,len)),cal(Arrays.copyOfRange(nums,0,len-1)));
    }

    public static void main(String[] args) {
        Rob2 rob2 = new Rob2();
        int[] nums = {1,2};
        int result =  rob2.rob(nums);
        System.out.println(result);
    }

}
