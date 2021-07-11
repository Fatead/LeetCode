package greedy;

public class DivisorGame {

    public boolean divisorGame(int n) {
        boolean[] dp = new boolean[n+1];
        dp[1] = false;
        for (int i = 1; i <=n ; i++) {
            for (int j = 1; j <i ; j++) {
                if (!dp[j] && i % (i-j) == 0) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        DivisorGame divisorGame = new DivisorGame();
        System.out.println(divisorGame.divisorGame(3));
    }

}
