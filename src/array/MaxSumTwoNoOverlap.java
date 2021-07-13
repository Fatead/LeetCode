package array;

public class MaxSumTwoNoOverlap {

    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        //先求该数组的前缀和
        int[] sum = new int[nums.length+1];
        for (int i = 1; i <=nums.length ; i++) {
            sum[i] = sum[i-1] + nums[i-1];
        }
        //对应两个数组分别在左边的两种情况
        int result = 0;
        for(int t = 0; t<2; t++){
            int prevMaxSum = 0;
            for (int i = firstLen; i+secondLen <=nums.length ; i++) {
                prevMaxSum = Math.max(prevMaxSum,sum[i] - sum[i-firstLen]);
                result = Math.max(result, prevMaxSum + sum[i+secondLen] - sum[i]);
            }
            int temp = firstLen;
            firstLen = secondLen;
            secondLen = temp;
        }
        return result;
    }

    public static void main(String[] args) {
        MaxSumTwoNoOverlap maxSumTwoNoOverlap = new MaxSumTwoNoOverlap();
        int[] nums = {3,8,1,3,2,1,8,9,0};
        System.out.println(maxSumTwoNoOverlap.maxSumTwoNoOverlap(nums,3,2));
    }

}
