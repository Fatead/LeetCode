package array;


public class LastStoneWeight2 {

    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        int n = stones.length, m = sum/2;
        boolean[][] dp = new boolean[n+1][m+1];
        dp[0][0] = true;
        //对于第i个石头，考虑凑出重量j
        for (int i = 0; i <n ; i++) {
            for (int j = 0; j <=m ; j++) {
                if(j<stones[i]){
                    //凑不出重量j
                    dp[i+1][j] = dp[i][j];
                }else {
                    dp[i+1][j] = dp[i][j] || dp[i][j - stones[i]];
                }
            }
        }
        for (int j = m;j>=0; --j) {
            if (dp[n][j]) {
                return sum - 2 * j;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        LastStoneWeight2 lastStoneWeight2 = new LastStoneWeight2();
        int[] stones = {31,26,33,21,40};
        System.out.println(lastStoneWeight2.lastStoneWeightII(stones));
    }

}
