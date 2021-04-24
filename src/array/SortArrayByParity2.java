package array;

import java.util.Arrays;

public class SortArrayByParity2 {

    private void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public int[] sortArrayByParityII(int[] nums) {
        int oddIndex = 1;
        int evenIndex = 0;
        while (oddIndex<nums.length && evenIndex<nums.length){
            while (oddIndex<nums.length &&nums[oddIndex]%2!=0){
                oddIndex+=2;
            }
            while (evenIndex<nums.length && nums[evenIndex]%2!=1){
                evenIndex+=2;
            }
            if(oddIndex>=nums.length||evenIndex>=nums.length)break;
            swap(nums,oddIndex,evenIndex);
        }
        return nums;
    }

    public static void main(String[] args) {
        SortArrayByParity2 sortArrayByParity2 = new SortArrayByParity2();
        int[] nums = {};
        System.out.println(Arrays.toString(sortArrayByParity2.sortArrayByParityII(nums)));
    }

}


