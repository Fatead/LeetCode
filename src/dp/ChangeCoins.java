package dp;

public class ChangeCoins {

    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for(int coin:coins){
            for(int x = coin;x<amount+1;x++){
                dp[x] += dp[x-coin];
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        ChangeCoins changeCoins = new ChangeCoins();
        int[] coins = {1,2,5};
        int result =  changeCoins.change(5,coins);
        System.out.println(result);
    }

}
