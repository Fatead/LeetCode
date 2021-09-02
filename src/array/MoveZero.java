package array;

import java.util.Arrays;

public class MoveZero {

    public void moveZeroes(int[] nums) {
        int pos = 0;
        for (int i = 0; i <nums.length ; i++) {
            if(nums[i] != 0){
                nums[pos++] = nums[i];
            }
        }
        for (int i = pos; i <nums.length ; i++) {
            nums[i] = 0;
        }
    }

    public static void main(String[] args) {
        MoveZero moveZero = new MoveZero();
        int[] nums = {0};
        moveZero.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

}
