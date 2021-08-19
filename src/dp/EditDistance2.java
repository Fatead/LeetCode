package dp;

public class EditDistance2 {

    public int minDistance(String word1, String word2) {
        if(word1.length() == 0)return word2.length();
        if(word2.length() == 0)return word1.length();
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for (int i = 0; i <=word1.length() ; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <=word2.length() ; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <=word1.length() ; i++) {
            for (int j = 1; j <=word2.length() ; j++) {
                int count = 0;
                if(word1.charAt(i-1) != word2.charAt(j-1)){
                    count = 1;
                }
                dp[i][j] = Math.min(dp[i-1][j] + 1,Math.min(dp[i][j-1] + 1, dp[i-1][j-1] + count));
            }
        }
        return dp[word1.length()][word2.length()];
    }

    public static void main(String[] args) {
        EditDistance2 editDistance2 = new EditDistance2();
        System.out.println(editDistance2.minDistance("intention",""));
    }

}