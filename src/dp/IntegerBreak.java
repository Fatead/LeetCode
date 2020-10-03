package dp;

public class IntegerBreak {

    public int integerBreak(int n){
        int[] dp = new int[n+1];
        for(int i = 2;i<=n;i++){
            int currMax = 0;
            for(int j = 1;j<i;j++){
                currMax = Math.max(currMax,Math.max(j*(i-j),j*dp[i-j]));
            }
            dp[i] = currMax;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        IntegerBreak integerBreak = new IntegerBreak();
        System.out.println(integerBreak.integerBreak(10));
    }
}
