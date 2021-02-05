package array;

import java.util.Arrays;

public class MaxDistToClosest {

    public int maxDistToClosest(int[] seats) {
        int[] dp = new int[seats.length];
        for (int i = 0; i <seats.length ; i++) {
            if(seats[i] == 0){
                dp[i] = 1;
            }else {
                dp[i] = 0;
            }
        }
        for (int i = 0; i <seats.length ; i++) {
            if(seats[i] == 0 && i>0){
                dp[i] = dp[i-1] + 1;
            }
        }
        int max = 0;
        int maxPosition = 0;
        for (int i = 0; i <dp.length ; i++) {
            if(dp[i]>=max){
                max = dp[i];
                maxPosition = i;
            }
        }
        if(maxPosition==max-1||maxPosition==seats.length-1){
            return max;
        }
        int left = 0,right = 0;
        int pos = 0;
        while(dp[pos]>0){
            left = dp[pos];
            pos++;
        }
        if(dp[dp.length-1]>0){
            right = dp[dp.length-1];
        }
        return Math.max(left,Math.max(right,(max+1)/2));
    }

    public static void main(String[] args) {
        MaxDistToClosest maxDistToClosest = new MaxDistToClosest();
        int[] seats = {1,0,0,0,1,0,1};
        System.out.println(maxDistToClosest.maxDistToClosest(seats));
    }

}