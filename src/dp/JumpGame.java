package dp;
import java.util.Arrays;

public class JumpGame {

    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        if(n==0)return 0;
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i <dp.length ; i++) {
            for (int j = i+1; j <=i+nums[i] && j<dp.length ; j++) {
                dp[j] = Math.min(dp[j],dp[i] + 1);
            }
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        int[] nums = {2,3,0,1,4};
        JumpGame jumpGame = new JumpGame();
        System.out.println(jumpGame.jump(nums));
    }

}
