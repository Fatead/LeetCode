package array;

public class MaxSubArray {


    public int maxSubArray(int[] nums) {
        if(nums.length == 0)return 0;
        int max = nums[0];
        int currSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            currSum = Math.max(currSum+nums[i],nums[i]);
            max = Math.max(max,currSum);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {-2,-1};
        MaxSubArray maxSubArray = new MaxSubArray();
        System.out.println(maxSubArray.maxSubArray(nums));
    }
}
