package dp;

public class Rob4 {

    public int rob(int[] nums) {
        int[] dp = new int[nums.length + 1];
        if(nums.length==0)return 0;
        if(nums.length == 1)return nums[0];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <=nums.length ; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i-1]);
        }
        return dp[nums.length];
    }

    public static void main(String[] args) {
        int[] nums = {2,1,1,2};
        Rob4 rob4 = new Rob4();
        System.out.println(rob4.rob(nums));
    }

}
