package array;

import java.util.Arrays;

public class MaxProfit2 {

    public int max(int[] prices) {
        int profit = 0;
        int min = prices[0];
        for(int i = 1;i<prices.length;i++){
            if(prices[i]-min>profit){
                profit = prices[i]-min;
            }
            if(prices[i] < min){
                min = prices[i];
            }
        }
        return profit;
    }

    public int maxProfit(int[] prices) {
        int begin = 0,end = 0,sum = 0;
        for(int i = 1;i<prices.length;i++){
            int profit = 0;
            end = i;
            if(i == prices.length-1){
                profit = max(Arrays.copyOfRange(prices,begin,end+1));
            } else if(begin <end && prices[i+1] <prices[i])
                profit = max(Arrays.copyOfRange(prices,begin,end+1));
            if(profit>0){
                sum += profit;
                begin = i + 1;
            }
        }
        return sum;
    }

}
