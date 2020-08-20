package dp;

public class Rob1 {

    public int rob(int[] nums) {
        int len = nums.length;
        if(len == 0)return 0;
        if(len == 1)return nums[0];
        int[] dp = new int[len+1];
        dp[0] = 0;
        dp[1] = nums[0];
        for(int i = 2;i<=len;i++){
            dp[i] = Math.max(dp[i-1],dp[i-2] + nums[i-1]);
        }
        return dp[len];
    }


    public static void main(String[] args) {
        int[] nums = {2,7,9,3,1};
        Rob1 rob1 = new Rob1();
        int result = rob1.rob(nums);
        System.out.println(result);
    }
}
