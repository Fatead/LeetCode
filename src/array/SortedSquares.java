package array;

import java.util.Arrays;

public class SortedSquares {

    public int[] sortedSquares(int[] nums) {
        int[] squares = new int[nums.length];
        int[] result = new int[nums.length];
        for (int i = 0; i <nums.length ; i++) {
            squares[i] = nums[i] * nums[i];
        }
        int left = 0,right = nums.length-1;
        int index = nums.length-1;
        while (left<=right && index>=0){
            if(squares[right]>squares[left]){
                result[index--] = squares[right];
                right--;
            }else {
                result[index--] = squares[left];
                left++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SortedSquares sortedSquares = new SortedSquares();
        int[] nums = {-7,-3,2,3,11};
        System.out.println(Arrays.toString(sortedSquares.sortedSquares(nums)));
    }

}
