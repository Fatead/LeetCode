package array;

import java.util.Arrays;

public class SearchIndex {

    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1,-1};
        int len = nums.length;
        if(len == 0||(len == 1&&nums[0] != target))return result;
        if(len == 1){
            result[0] = 0;
            result[1] = 0;
            return result;
        }
        int left = 0, right = len-1;
        while(left<=right){
            int mid = (left + right)/2;
            if(nums[mid] == target){
                //分别向前搜索和向后搜索
                int index1 = mid,index2 = mid;
                while(index1>=left&&nums[index1] == target)index1--;
                while(index2<=right&&nums[index2] == target)index2++;
                result[0] = index1+1;
                result[1] = index2-1;
                return result;
            }else if(nums[mid]<target){
                left = mid+1;
            }else {
                right = mid-1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SearchIndex searchIndex = new SearchIndex();
        int[] nums = {5,7,7,8,8,10};
        int[] result = searchIndex.searchRange(nums,10);
        System.out.println(Arrays.toString(result));
    }
}
