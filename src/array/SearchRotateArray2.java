package array;

public class SearchRotateArray2 {

    public boolean searchMid(int[] nums, int left ,int right,int target){
        while(left<=right){
            int mid = (left + right +1)/2;
            if(nums[mid]==target){
                return true;
            }else if(nums[mid]<target){
                left = mid +1;
            }else {
                right = mid-1;
            }
        }
        return false;
    }

    public boolean search(int[] nums, int target) {
        int length = nums.length;
        if(length == 0)return false;
        int left = 0,right = length-1;
        while(left<=right){
            int mid = (left + right)/2;
            if(nums[left] == target||nums[right] == target||nums[mid] == target)return true;
            if(nums[left] == nums[mid]&&nums[mid] == nums[right]){
                for(int i = left;i<right;i++){
                    if(nums[i] == target)return true;
                }
                return false;
            }else if(nums[left] == nums[mid]){
                left = mid+1;
                continue;
            }else if(nums[right] == nums[mid]){
                right = mid-1;
                continue;
            }
            if(nums[mid]<nums[right]){
                //旋转在左半边
                if(target<nums[mid]||target>nums[right]){
                    right = mid-1;
                }else {
                    return searchMid(nums,mid,right,target);
                }
            }else {
                //旋转在右半边
                if(target<nums[left]||target>nums[mid]){
                    left = mid+1;
                }else {
                    return searchMid(nums,left,mid,target);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SearchRotateArray2 searchRotateArray2 = new SearchRotateArray2();
        int[] array = {3,1,2,2,2};
        System.out.println(searchRotateArray2.search(array,3));
    }
}
