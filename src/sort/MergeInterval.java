package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeInterval {

    public int[][] merge(int[][] intervals) {
        if(intervals.length<2)return intervals;
        //对所有的区间进行排序
        for(int i = 0;i<intervals.length;i++){
            for(int j = 0;j<intervals.length-i-1;j++){
                if(intervals[j][0]>intervals[j+1][0]){
                    for(int k = 0;k<2;k++){
                        int temp = intervals[j][k];
                        intervals[j][k] = intervals[j+1][k];
                        intervals[j+1][k] = temp;
                    }
                }
            }
        }
        int left = intervals[0][0],right = intervals[0][1];
        List<List<Integer>> resultList = new ArrayList<>();
        for(int i = 0;i<intervals.length-1;i++){
            if(right>=intervals[i+1][0]){
                //需要将两个区间进行合并
                right = Math.max(right,intervals[i+1][1]);
            }else{
                List<Integer> list = new ArrayList<>();
                list.add(left);
                list.add(right);
                resultList.add(list);
                left = intervals[i+1][0];
                right = intervals[i+1][1];
            }
            if(i == intervals.length-2){
                List<Integer> list = new ArrayList<>();
                list.add(left);
                list.add(right);
                resultList.add(list);
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
        int[][] array = {
                {2,3},
                {4,5},
                {6,7},
                {8,9},
                {1,10}
        };
        MergeInterval mergeInterval = new MergeInterval();
        int[][] result = mergeInterval.merge(array);
        System.out.println(Arrays.deepToString(result));
    }
}
