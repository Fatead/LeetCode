package dp;

public class MinCostClimbingStairs {

    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length+1];
        for(int i = 2;i<dp.length;i++){
            dp[i] = Math.min(dp[i-1] + cost[i-1],dp[i-2] + cost[i-2]);
        }
        return dp[cost.length];
    }


    public static void main(String[] args) {
        MinCostClimbingStairs minCostClimbingStairs = new MinCostClimbingStairs();
        int[] cost = {10, 15, 20};
        System.out.println(minCostClimbingStairs.minCostClimbingStairs(cost));
    }

}
