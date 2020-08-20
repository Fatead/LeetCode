package array;

import java.util.Arrays;

public class RotateArray {

    private void swap(int[] nums,int i,int j){
        if(i == j)return;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void rotate(int[] nums, int k) {
        int len = nums.length;
        if(len == 0)return;
        k = k%len;
        for(int i = 0;i<(len+1)/2;i++){
            swap(nums,i,len-i-1);
        }
        for(int i = 0;i<(k+1)/2;i++){
            swap(nums,i,k-1-i);
        }
        for(int i = k;i<(len+k)/2;i++){
            swap(nums,i,len-i-1+k);
        }
    }


    public static void main(String[] args) {
        int[] nums = {-1,-100,3,99};
        RotateArray rotateArray = new RotateArray();
        rotateArray.rotate(nums,2);
    }
}
