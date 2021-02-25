package dp;

public class NumWays {

    public int numWays(int n) {
        if(n<=0)return 0;
        if(n==1)return 1;
        if(n==2)return 2;
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i <n ; i++) {
            dp[i] = (dp[i-1] + dp[i-2])%1000000007;
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        NumWays numWays = new NumWays();
        System.out.println(numWays.numWays(7));
    }

}
