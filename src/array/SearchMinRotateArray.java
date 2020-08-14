package array;

public class SearchMinRotateArray {

    public int findMin(int[] nums) {
        int len = nums.length;
        int min = nums[0];
        if(nums[0]<nums[len-1]){
            return nums[0];
        }
        for(int i = 1;i<len;i++){
            if(nums[i]<min)return nums[i];
        }
        return min;
    }

}
