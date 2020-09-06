package array;

import java.util.ArrayList;
import java.util.List;

public class SummaryRange {

    public List<String> summaryRanges(int[] nums) {
        List<String> ranges = new ArrayList<>();
        if(nums.length == 0)return ranges;
        boolean single = true;
        int begin = nums[0];
        int end = nums[0];
        int pre = nums[0];
        for(int i = 1;i<nums.length;i++){
            //不连续
            if(nums[i] - 1 >pre){
                if(single){
                    ranges.add(String.valueOf(begin));
                    single = true;
                    begin = nums[i];
                    pre = nums[i];
                    end = nums[i];
                }else {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(begin).append("->").append(end);
                    ranges.add(stringBuilder.toString());
                    single = true;
                    begin = nums[i];
                    pre = nums[i];
                    end = nums[i];
                }
            }else {
                single = false;
                end = nums[i];
                pre = nums[i];
            }
        }
        if(single){
            ranges.add(String.valueOf(begin));
        }else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(begin).append("->").append(end);
            ranges.add(stringBuilder.toString());
        }
        return ranges;
    }

    public static void main(String[] args) {
        SummaryRange summaryRange = new SummaryRange();
        int[] array = {-2147483648,-2147483647,2147483647};
        List<String> result =  summaryRange.summaryRanges(array);
        System.out.println(result);
    }
}
