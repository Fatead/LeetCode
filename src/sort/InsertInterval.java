package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertInterval {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        if(intervals.length == 0){
            return new int[][]{
                    newInterval
            };
        }
        int left = newInterval[0],right = newInterval[1];
        List<List<Integer>> resultList = new ArrayList<>();
        boolean add = false;
        for(int i =0;i<intervals.length;i++){
            if(intervals[i][1]>=left&&intervals[i][0]<=right){
                left = Math.min(left,intervals[i][0]);
                right = Math.max(right,intervals[i][1]);
                if(i == intervals.length-1){
                    List<Integer> list = new ArrayList<>();
                    list.add(left);
                    list.add(right);
                    resultList.add(list);
                    add = true;
                }
                continue;
            }if(left<intervals[i][0]&&!add){
                List<Integer> list = new ArrayList<>();
                list.add(left);
                list.add(right);
                resultList.add(list);
                add = true;
            }
            List<Integer> list = new ArrayList<>();
            list.add(intervals[i][0]);
            list.add(intervals[i][1]);
            resultList.add(list);
            if(i == intervals.length-1&&!add){
                List<Integer> list1 = new ArrayList<>();
                list1.add(left);
                list1.add(right);
                resultList.add(list1);
                add = true;
            }
        }
        int[][] result = new int[resultList.size()][2];
        for(int i = 0;i<resultList.size();i++){
            for(int j = 0;j<2;j++){
                result[i][j] = resultList.get(i).get(j);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] intervals = {
                {1,2},
                {4,7}
        };
        int[] newInterval = {3,4};
        InsertInterval insertInterval = new InsertInterval();
        int[][] result = insertInterval.insert(intervals,newInterval);
        System.out.println(Arrays.deepToString(result));
    }
}
