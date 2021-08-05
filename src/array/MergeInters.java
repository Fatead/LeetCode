package array;
import java.util.*;

/**
 * 先根据左端点进行排序，划动窗口看是否能够合并区间
 */
public class MergeInters {

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] ints, int[] t1) {
                return ints[0] - t1[0];
            }
        });
        List<int[]> list = new ArrayList<>();
        int left = intervals[0][0];
        int right = intervals[0][1];
        for (int i = 1; i <intervals.length ; i++) {
            if(intervals[i][0]<right && intervals[i][1]<right){
                continue;
            }else if(intervals[i][0]<=right && intervals[i][1]>right){
                right = intervals[i][1];
            }else if(intervals[i][0]>right){
                list.add(new int[]{left,right});
                left = intervals[i][0];
                right = intervals[i][1];
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
        MergeInters mergeInters = new MergeInters();
        int[][] intervals = {
                {1,3},
                {2,6},
                {3,4},
                {8,10},
                {6,7},
                {15,18}
        };
        System.out.println(Arrays.deepToString(mergeInters.merge(intervals)));
    }

}