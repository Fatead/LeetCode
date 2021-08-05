package array;

import java.util.Arrays;

public class SearchRange {

    public int[] searchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        int begin = -1, end = -1;
        while (left<=right){
            int mid = left + (right-left)/2;
            if(nums[mid] == target){
                begin = mid;
                end = mid;
                break;
            }else if(nums[mid]<target){
                left = mid+1;
            }else {
                right = mid-1;
            }
        }
        while (begin-1>=0 && nums[begin-1] == target){
            begin--;
        }
        while (end + 1 < nums.length && nums[end + 1] == target){
            end++;
        }
        return new int[]{begin,end};
    }

    public static void main(String[] args) {
        SearchRange searchRange = new SearchRange();
        int[] nums = {1,1,1,1,1,1,1};
        System.out.println(Arrays.toString(searchRange.searchRange(nums, 1)));
    }

}
