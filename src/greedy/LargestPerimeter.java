package greedy;

import java.util.Arrays;

public class LargestPerimeter {

    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        for (int i = nums.length - 1; i >= 2; i--) {
            if(nums[i-2] + nums[i-1]>nums[i]){
                return nums[i] + nums[i-1] + nums[i-2];
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        LargestPerimeter largestPerimeter = new LargestPerimeter();
        int[] nums = {3,2,3,4};
        System.out.println(largestPerimeter.largestPerimeter(nums));
    }


}
