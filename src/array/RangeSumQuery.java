package array;

public class RangeSumQuery {

    int[] dp;

    public RangeSumQuery(int[] nums) {
        dp = new int[nums.length];
        if(nums.length == 0)return;
        dp[0] = nums[0];
        for(int i = 1;i<nums.length;i++){
            dp[i] = dp[i-1] + nums[i];
        }
    }

    public void update(int i, int val) {
        int curr = 0;
        if(i == 0)curr = val;
        else {
            curr = dp[i-1] + val;
        }
        int dis = curr - dp[i];
        for(int n = i;n<dp.length;n++){
            dp[n] = dp[n] + dis;
        }
    }

    public int sumRange(int i, int j) {
        if(i == 0)return dp[j];
        return dp[j] - dp[i-1];
    }


    public static void main(String[] args) {
        int[] nums = {1,3,5};
        RangeSumQuery rangeSumQuery = new RangeSumQuery(nums);
        rangeSumQuery.update(1,2);
        System.out.println(rangeSumQuery.sumRange(0,2));
    }
}
