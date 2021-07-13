package dp;

public class MaxUncrossedLines {

    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length][nums2.length];
        for (int i = 0; i <dp.length ; i++) {
            if(nums1[i] == nums2[0]){
                dp[i][0] = 1;
            }
            if(i>0){
                dp[i][0] = Math.max(dp[i][0],dp[i-1][0]);
            }
        }
        for (int i = 0; i <dp[0].length ; i++) {
            if(nums2[i] == nums1[0]){
                dp[0][i] = 1;
            }
            if(i>0){
                dp[0][i] = Math.max(dp[0][i],dp[0][i-1]);
            }
        }
        for (int i = 1; i <dp.length ; i++) {
            for (int j = 1; j <dp[0].length ; j++) {
                if(nums1[i] == nums2[j]){
                    dp[i][j] = Math.max(dp[i-1][j-1] + 1,Math.max(dp[i-1][j],dp[i][j-1]));
                }else {
                    dp[i][j] = Math.max(dp[i-1][j-1],Math.max(dp[i-1][j],dp[i][j-1]));
                }
            }
        }
        return dp[nums1.length-1][nums2.length-1];
    }

    public static void main(String[] args) {
        MaxUncrossedLines maxUncrossedLines = new MaxUncrossedLines();
        int[] nums1 = {1,3,7,1,7,5};
        int[] nums2 = {1,9,2,5,1};
        System.out.println(maxUncrossedLines.maxUncrossedLines(nums1,nums2));
    }

}