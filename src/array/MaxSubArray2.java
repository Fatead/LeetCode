package array;

public class MaxSubArray2 {

    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            max = Math.max(sum,max);
            if(sum<0)sum = 0;
        }
        return max;
    }

    public static void main(String[] args) {
        MaxSubArray2 maxSubArray2 = new MaxSubArray2();
        int[] nums = {1};
        System.out.println(maxSubArray2.maxSubArray(nums));
    }

}
