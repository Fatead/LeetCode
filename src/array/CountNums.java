package array;

public class CountNums {


    public int count(int[] nums, int pos,int target){
        int count = 1;
        int left = pos-1;
        int right = pos+1;
        while (left>=0 && nums[left] == target){
            count++;
            left--;
        }
        while (right<nums.length && nums[right] == target){
            count++;
            right++;
        }
        return count;
    }

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        while (left<=right){
            int mid = left + (right-left)/2;
            if(nums[mid]<target){
                left = mid+1;
            }else if(nums[mid]>target){
                right = mid-1;
            }else return count(nums,mid,target);
        }
        return 0;
    }

    public static void main(String[] args) {
        CountNums countNums = new CountNums();
        int[] nums = {5,7,7,8,8,10};
        System.out.println(countNums.search(nums,8));
    }

}
