package array;

import number.MissingNum;

public class MissingNumber {

    public int missingNumber(int[] nums) {
        if(nums.length==0)return 0;
        if(nums.length == 1)return nums[0]==1?0:1;
        int left = 0;
        int right = nums.length-1;
        while (left<=right){
            int mid = left + (right-left)/2;
            if(mid+1 == nums.length||(mid+1<nums.length && nums[mid+1]!=nums[mid]+1)){
                return nums[mid] + 1;
            }
            if(nums[mid]>mid){
                right = mid-1;
            }else if(nums[mid] == mid){
                left = mid+1;
            }
        }
        return 0;
    }


    public static void main(String[] args) {
        MissingNum missingNum = new MissingNum();
        int[] nums = {0,1,2,3};
        System.out.println(missingNum.missingNumber(nums));
    }

}
