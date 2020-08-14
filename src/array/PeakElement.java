package array;

public class PeakElement {

    public int findPeakElement(int[] nums) {
        int len = nums.length;
        if(len == 0||len == 1)return 0;
        if(len == 2)return nums[0]>nums[1]?0:1;
        int left = 0;
        int right = len;
        while(left<=right){
            int mid = (left + right)/2;
            if(mid == 0||mid == len-1)return mid;
            if(nums[mid] > nums[mid-1]&&nums[mid]>nums[mid+1]){
                return mid;
            }else if(nums[mid-1]>nums[mid+1]){
                right = mid-1;
            }else {
                left = mid+1;
            }
        }
        return left;
    }


}
