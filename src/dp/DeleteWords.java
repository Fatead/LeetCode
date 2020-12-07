package dp;

public class DeleteWords {

    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for(int i = 0;i<word1.length();i++){
            for(int j = 0;j<word2.length();j++){
                if(word1.charAt(i) == word2.charAt(j)){
                    dp[i+1][j+1] = dp[i][j] + 1;
                }else {
                    dp[i+1][j+1] = Math.max(dp[i][j+1],dp[i+1][j]);
                }
            }
        }
        int maxSubSequence = dp[word1.length()][word2.length()];
        return word1.length() + word2.length() - maxSubSequence*2;
    }

    public static void main(String[] args) {
        DeleteWords deleteWords = new DeleteWords();
        System.out.println(deleteWords.minDistance("sea","eat"));
    }
}
