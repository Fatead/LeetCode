package dp;

public class CommonLength {

    public int findLength(int[] A, int[] B) {
        int[][] dp = new int[A.length+1][B.length + 1];
        for(int i = 0;i<A.length;i++){
            for(int j = 0;j<B.length;j++){
                if(A[i] == B[j]){
                    dp[i+1][j+1] = dp[i][j] + 1;
                }else {
                    dp[i+1][j+1] = 0;
                }
            }
        }
        int max = 0;
        for(int i= 0;i<dp.length;i++){
            for(int j = 0;j<dp[0].length;j++){
                if(dp[i][j]>max){
                    max = dp[i][j];
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        CommonLength commonLength = new CommonLength();
        int[] A = {1,2,3,2,1,7};
        int[] B = {3,2,1,4,7};
        int result = commonLength.findLength(A,B);
        System.out.println(result);
    }

}
