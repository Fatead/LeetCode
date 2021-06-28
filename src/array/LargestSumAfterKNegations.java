package array;

import java.util.Arrays;

public class LargestSumAfterKNegations {

    public int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        int minusNum = 0;
        for (int i = 0; i <nums.length ; i++) {
            if(nums[i]<0){
                minusNum++;
            }
        }
        int result = 0;
        if(k<minusNum){
            for (int i = 0; i <nums.length ; i++) {
                if(k>0){
                    result += Math.abs(nums[i]);
                    k--;
                }else {
                    result += nums[i];
                }
            }
        }else if((k-minusNum)%2 == 0){
            for (int i = 0; i <nums.length ; i++) {
                result += Math.abs(nums[i]);
            }
        }else {
            int min = Integer.MAX_VALUE;
            int minPos = 0;
            for (int i = 0; i <nums.length ; i++) {
                if(Math.abs(nums[i])<min){
                    min = Math.abs(nums[i]);
                    minPos = i;
                }
            }
            for (int i = 0; i <nums.length ; i++) {
                if(i == minPos)continue;
                result += Math.abs(nums[i]);
            }
            result -= min;
        }
        return result;
    }

    public static void main(String[] args) {
        LargestSumAfterKNegations largestSumAfterKNegations = new LargestSumAfterKNegations();
        int[] nums = {4};
        System.out.println(largestSumAfterKNegations.largestSumAfterKNegations(nums,1));
    }

}