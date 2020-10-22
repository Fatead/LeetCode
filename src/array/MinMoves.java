package array;

import java.util.Arrays;

public class MinMoves {

    public int minMoves(int[] nums) {
        int len = nums.length;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int sum  = 0;
        for(int i = 0;i<nums.length;i++){
            if(nums[i] > max){
                max = nums[i];
            }
            if(nums[i] < min){
                min = nums[i];
            }
            sum+=nums[i];
        }
        return sum - len*min;
    }

    public static void main(String[] args) {
        int[] array = {1,2,3};
        MinMoves minMoves = new MinMoves();
        int result =  minMoves.minMoves(array);
        System.out.println(result);
    }
}
