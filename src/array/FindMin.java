package array;

public class FindMin {

    public int findMin(int[] nums) {
        if(nums.length == 1)return nums[0];
        int left = 0;
        int right = nums.length-1;
        int mid = 0;
        if(nums[left]<nums[right])return nums[left];
        while (left<right){
            if(left+1==right){
                return Math.min(nums[left],nums[right]);
            }
            mid = left + (right-left)/2;
            if(nums[mid]>nums[left]){
                //数组左边是有序的
                left = mid;
            }else if(nums[mid]<nums[right]){
                //数组右边是有序的
                right = mid;
            }
        }
        return nums[mid];
    }

    public static void main(String[] args) {
        FindMin findMin = new FindMin();
        int[] nums = {3,4,5,1,2};
        System.out.println(findMin.findMin(nums));
    }
}
