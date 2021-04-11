package number;

import java.util.Arrays;

/**
 * 使用动态规划来解决该题 dp[i][j]表示投掷完i枚骰子后，点数j出现的次数
 *
 */
public class DicesProbability {

    public double[] dicesProbability(int n) {
        double[] dp = new double[6];
        Arrays.fill(dp, 1.0 / 6.0);
        for (int i = 2; i <= n; i++) {
            double[] tmp = new double[5 * i + 1];
            for (int j = 0; j < dp.length; j++) {
                for (int k = 0; k < 6; k++) {
                    tmp[j + k] += dp[j] / 6.0;
                }
            }
            dp = tmp;
        }
        return dp;
    }


    public static void main(String[] args) {
        DicesProbability dicesProbability = new DicesProbability();
        double[] result = dicesProbability.dicesProbability(2);
    }

}
