package array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class InsertIntervals {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int[][] nums = new int[intervals.length + 1][2];
        for (int i = 0; i <intervals.length ; i++) {
            for (int j = 0; j <2 ; j++) {
                nums[i][j] = intervals[i][j];
            }
        }
        nums[intervals.length][0] = newInterval[0];
        nums[intervals.length][1] = newInterval[1];
        Arrays.sort(nums, Comparator.comparingInt(ints -> ints[0]));
        List<int[]> list = new ArrayList<>();
        int left = nums[0][0];
        int right = nums[0][1];
        for (int i = 1; i <nums.length ; i++) {
            if(nums[i][0]<right && nums[i][1]<right){
                continue;
            }else if(nums[i][0]<=right && nums[i][1]>right){
                right = nums[i][1];
            }else if(nums[i][0]>right){
                list.add(new int[]{left,right});
                left = nums[i][0];
                right = nums[i][1];
            }
        }
        list.add(new int[]{left,right});
        int[][] result = new int[list.size()][];
        int count = 0;
        for (int[] ints : list) {
            result[count++] = ints;
        }
        return result;
    }


    public static void main(String[] args) {
        InsertIntervals intervals = new InsertIntervals();
        int[][] interval = {
                {1,2},
                {3,5},
                {6,7},
                {8,10},
                {12,16}
        };
        int[] newIntervals = {4,8};
        System.out.println(Arrays.deepToString(intervals.insert(interval, newIntervals)));
    }

}