package array;

import java.util.Arrays;

public class NextPer {

    private void swap(int[] nums, int pos1, int pos2){
        int temp = nums[pos1];
        nums[pos1] = nums[pos2];
        nums[pos2] = temp;
    }

    private void nextPermutation(int[] nums) {
        boolean decreaseFlag = true;
        for(int i =0;i<nums.length;i++){
            if (i > 0 && nums[i] > nums[i - 1]) {
                decreaseFlag = false;
                break;
            }
        }
        if(decreaseFlag){
            Arrays.sort(nums);
            return;
        }
        for(int i = nums.length-1;i>=0;i--){
            if(i>0&&nums[i-1]<nums[i]){
                int pos = i;
                int min = nums[i];
                for(int j = i;j<nums.length;j++){
                    if(nums[j]<min&&nums[j]>nums[i-1]){
                        min = nums[j];
                        pos = j;
                    }
                }
                swap(nums,i-1,pos);
                Arrays.sort(nums,i,nums.length);
                return;
            }
        }
    }

    public static void main(String[] args) {
        NextPer nextPer = new NextPer();
        int[] nums = {1,2,3};
        nextPer.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
