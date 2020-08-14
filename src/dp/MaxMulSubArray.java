package dp;


public class MaxMulSubArray {

    public int maxProduct(int[] nums) {
        int len = nums.length;
        if(len == 0)return 0;
        int max = nums[0],min = nums[0],result = nums[0];
        for(int i = 1;i<nums.length;i++){
            int mx = max,mn = min;
            max = Math.max(mx*nums[i],Math.max(mn*nums[i],nums[i]));
            min = Math.min(mn*nums[i],Math.min(mx*nums[i],nums[i]));
            result = Math.max(max,result);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = {-2,1,-1};
        MaxMulSubArray maxMulSubArray = new MaxMulSubArray();
        int result = maxMulSubArray.maxProduct(array);
        System.out.println(result);
    }

}
