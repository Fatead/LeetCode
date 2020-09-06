package array;

public class ProductArray {

    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        if(nums.length == 0)return result;
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];
        left[0] = 1;
        right[nums.length-1] = 1;
        for(int i = 1;i<nums.length;i++){
            left[i] = left[i-1]*nums[i-1];
            right[nums.length-i-1] = right[nums.length-i]*nums[nums.length-i];
        }
        for(int i = 0;i<nums.length;i++){
            result[i] = left[i] * right[i];
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
