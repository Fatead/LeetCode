package array;

import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {

    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if(nums.length == 0)return result;
        int left = nums[0], right = nums[0];
        for (int i = 1; i <nums.length ; i++) {
            if(nums[i-1] +1 == nums[i]){
                right++;
            }else if(left == right){
                result.add(String.valueOf(left));
                left = nums[i];
                right = nums[i];
            }else {
                result.add(left + "->" + right);
                left = nums[i];
                right = nums[i];
            }
        }
        if(left == right){
            result.add(String.valueOf(left));
        }else {
            result.add(left + "->" + right);
        }
        return result;
    }

    public static void main(String[] args) {
        SummaryRanges summaryRanges = new SummaryRanges();
        int[] nums = {0,1,2};
        System.out.println(summaryRanges.summaryRanges(nums));
    }

}