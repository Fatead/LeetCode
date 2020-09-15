package number;

import java.util.Arrays;

public class NumOfSquares {


    public int numSquares(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;
        int maxSquareNum = (int)Math.sqrt(n) + 1;
        int[] squareArray = new int[maxSquareNum];
        for(int i = 0;i<maxSquareNum;i++){
            squareArray[i] = i*i;
        }
        for(int i = 1;i<n+1;i++){
            for(int j = 1;j<maxSquareNum;j++){
                if(i<squareArray[j])break;;
                dp[i] = Math.min(dp[i],dp[i-squareArray[j]]+1);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        NumOfSquares numOfSquares = new NumOfSquares();
        int result =  numOfSquares.numSquares(48);
        System.out.println(result);
    }

}
