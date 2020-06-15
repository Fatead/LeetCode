package dp;

import java.util.Arrays;

public class MaxSubArray {

    public int maxSubArray2(int[] nums) {
        int len = nums.length;
        if(len == 0)return 0;
        if(len == 1)return nums[0];
        int[][] dp = new int[len][len];
        int max = nums[0];
        for(int i =0;i<nums.length;i++){
            dp[i][i] = nums[i];
            if(dp[i][i]>max)max = dp[i][i];
        }
        for(int j = 1;j<len;j++){
            for(int i = 0;i<len-j;i++){
                dp[i][i+j] = nums[i+j] + dp[i][i+j-1];
                if(dp[i][i+j]>max)max = dp[i][i+j];
            }
        }
        return max;
    }

    public int maxSubArray(int[] nums){
        int res = nums[0];
        int sum = 0;
        for(int num:nums){
            if(sum>0)sum+=num;
            else sum = num;
            res = Math.max(sum,res);
        }
        return res;
    }


    public static void main(String[] args) {
        int[] array = {-2,1,-3,4,-1,2,1,-5,4};
        MaxSubArray maxSubArray = new MaxSubArray();
        int result = maxSubArray.maxSubArray(array);
        System.out.println(result);
    }
}
