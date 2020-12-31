package array;

public class BinarySearch {

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        while(left<=right){
            int mid = left -(left-right)/2;
            if(nums[mid] ==target){
                return mid;
            }else if(nums[mid]<target){
                left = mid+1;
            }else {
                right = mid-1;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        int[] nums = {-1,0,3,5,9,12};
        System.out.println(binarySearch.search(nums,2));
    }
}
