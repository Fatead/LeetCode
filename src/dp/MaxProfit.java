package dp;

public class MaxProfit {

    /**
     * 贪心算法，只在买入的时候计入交易费用
     */
    public int maxProfit(int[] prices, int fee) {
        int profit = 0;
        int buy = prices[0] + fee;
        for (int i = 0; i <prices.length ; i++) {
            if(prices[i]+fee<buy){
                buy = prices[i] + fee;
            }else if(prices[i]>buy){
                profit += prices[i]-buy;
                buy = prices[i];
            }
        }
        return profit;
    }


    /**
     * 动态规划算法，定义二维数组dp[n][2]
     * dp[i][0]表示第i天不持有可以获得的最大利润
     * dp[i][1]表示第i天持有可以获得的最大利润
     * 不持有
     * dp[i][0] = max(dp[i-1][0],price[i] + dp[i-1][1] - fee)
     * 1.昨天不持有，今天不持有
     * 2.昨天持有，今天不持有，发生卖出事件
     * 持有
     * dp[i][1] = max(dp[i-1][0]-price[i],dp[i-1][1])
     * 1.昨天持有，今天持有
     * 2.昨天不持有，今天持有，发生买入事件
     */
    public int maxProfit2(int[] prices,int fee){
       int[][] dp = new int[prices.length][2];
       dp[0][1] = -prices[0];
        for (int i = 1; i <prices.length ; i++) {
            dp[i][0] = Math.max(dp[i-1][0],prices[i]+dp[i-1][1]-fee);
            dp[i][1] = Math.max(dp[i-1][0]-prices[i],dp[i-1][1]);
        }
        return Math.max(dp[prices.length-1][0],dp[prices.length-1][1]);
    }

    public static void main(String[] args) {
        MaxProfit profit = new MaxProfit();
        int[] prices = {1, 3, 2, 8, 4, 9};
        System.out.println(profit.maxProfit2(prices,2));
    }

}
