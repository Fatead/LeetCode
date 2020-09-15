package array;

import java.util.Arrays;

public class MoveZeros {


    private void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void moveZeroes(int[] nums) {
        int count = 0;
        int len = nums.length;
        for(int i = 0;i<len;i++){
            if(nums[i] == 0){
                count++;
                for(int j = i;j<len-count;j++){
                    swap(nums,j,j+1);
                }
                i--;
            }
            if(count ==len)break;
        }
    }

    public static void main(String[] args) {
        int[] array = {0,0,1};
        MoveZeros moveZeros = new MoveZeros();
        moveZeros.moveZeroes(array);
        System.out.println(Arrays.toString(array));
    }
}
