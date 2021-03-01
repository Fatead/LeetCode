package array;

import java.util.Arrays;

public class ExchangeArray {

    private void swap(int[] nums,int x,int y){
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

    public int[] exchange(int[] nums) {
        int left = 0;
        int right = nums.length-1;
        while (left<right){
            while (left<nums.length&&nums[left]%2!=0){
                left++;
            }
            while (right>=0&&nums[right]%2==0){
                right--;
            }
            if(left>=right)break;
            swap(nums,left,right);
        }
        return nums;
    }

    public static void main(String[] args) {
        ExchangeArray exchangeArray = new ExchangeArray();
        int[] nums = {1,2,3,4};
        System.out.println(Arrays.toString(exchangeArray.exchange(nums)));
    }

}
