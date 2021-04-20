package sort;

import java.util.Arrays;

public class SortArray {

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private int partition(int[] nums,int left,int right){
        int value = nums[left];
        int i = left,j = right+1;
        while (true){
            while (++i<right && nums[i]<value);
            while (--j>left && nums[j]>value);
            if(i>=j)break;
            swap(nums,i,j);
        }
        nums[left] = nums[j];
        nums[j] = value;
        return j;
    }


    private void sort(int[] nums,int left, int right){
        if(left>= right)return;
        int pos = partition(nums,left,right);
        sort(nums,left,pos-1);
        sort(nums,pos+1,right);
    }

    public int[] sortArray(int[] nums) {
        if(nums.length<=1)return nums;
        sort(nums,0,nums.length-1);
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = {5,1,1,2,0,0,-4};
        SortArray sortArray = new SortArray();
        System.out.println(Arrays.toString(sortArray.sortArray(nums)));
    }

}