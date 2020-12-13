package greedy;

import java.util.Arrays;

public class MaxProduct {

    public int maximumProduct(int[] nums) {
        if(nums.length <3)return 0;
        int max = Integer.MIN_VALUE;
        Arrays.sort(nums);
        int len = nums.length;
        int product1 = nums[len-1]*nums[len-2]*nums[len-3];
        int product2 = nums[0]*nums[1]*nums[len-1];
        return Math.max(product1,product2);
    }

    public static void main(String[] args) {
        int[] array = {-100,-98,-1,2,3,4};
        MaxProduct maxProduct = new MaxProduct();
        System.out.println(maxProduct.maximumProduct(array));
    }

}
