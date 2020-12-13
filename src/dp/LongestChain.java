package dp;
import java.util.Arrays;
import java.util.Comparator;

public class LongestChain {

    public int findLongestChain(int[][] pairs) {
        if(pairs.length == 0)return 0;
        if(pairs.length == 1)return 1;
        Arrays.sort(pairs, Comparator.comparingInt((int[] t) -> t[0]));
        int[] dp = new int[pairs.length];
        Arrays.fill(dp,1);
        for(int i = 1;i<dp.length;i++){
            for(int j = 0;j<i;j++){
                if(pairs[i][0] > pairs[j][1]){
                    dp[i] = Math.max(dp[j]+1,dp[i]);
                }
            }
        }
        return dp[dp.length-1];
    }

    public static void main(String[] args) {
        int[][] pair = {
                {1,2},
                {3,4},
                {2,3},
                {7,8}
        };
        LongestChain longestChain = new LongestChain();
        System.out.println(longestChain.findLongestChain(pair));
    }

}
