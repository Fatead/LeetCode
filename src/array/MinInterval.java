package array;

import java.util.Arrays;
import java.util.Comparator;

public class MinInterval {

    static class MyComparator implements Comparator<int[]>{

        @Override
        public int compare(int[] ints, int[] t1) {
            return ints[0] - t1[0];
        }
    }

    public boolean isOverlap(int[] interval1,int[] interval2){
        return interval1[1] > interval2[0];
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length<2)return 0;
        Arrays.sort(intervals,new MyComparator());
        int[] dp = new int[intervals.length];
        Arrays.fill(dp,1);
        for(int i = 1;i<intervals.length;i++){
            for(int j = 0;j<i;j++){
                if(!isOverlap(intervals[j],intervals[i])){
                    dp[i] = Math.max(dp[i],dp[j] + 1);
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        return intervals.length - dp[intervals.length-1];
    }

    public static void main(String[] args) {
        MinInterval minInterval = new MinInterval();
        int[][] intervals = {
                {1,3},
                {2,5},
                {4,5},
                {6,7},
                {6,8},
                {7,8}
        };
        int result = minInterval.eraseOverlapIntervals(intervals);
        System.out.println(result);
    }
}
