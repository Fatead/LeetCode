package array;

public class SearchPos {


    public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        if(len==0)return 0;
        int left = 0,right = len-1;
        int mid = 0;
        while(left<=right){
            mid = (left + right)/2;
            if(nums[mid] == target)return mid;
            if(nums[mid]<target){
                left = mid+1;
            }else if(nums[mid]>target){
                right = mid-1;
            }
        }
        if(nums[mid]<target)return mid+1;
        return mid;
    }

    public static void main(String[] args) {
        SearchPos searchPos = new SearchPos();
        int[] nums = {1,3,5,6};
        System.out.println(searchPos.searchInsert(nums,0));
    }
}
