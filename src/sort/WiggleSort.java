package sort;

import java.util.Arrays;

public class WiggleSort {

    public void wiggleSort(int[] nums) {
        int[] array = new int[nums.length];
        for(int i = 0;i<array.length;i++){
            array[i] = nums[i];
        }
        Arrays.sort(array);
        int count = 0;
        for(int i = 0;i<nums.length/2;i++){
            nums[count++] = array[nums.length/2-1-i];
            nums[count++] = array[nums.length-i-1];
        }
//        if(nums.length%2 == 1){
//            nums[nums.length/2] = array[nums.length-1];
//        }
    }




    public static void main(String[] args) {
        WiggleSort wiggleSort = new WiggleSort();
        int[] array = {1,3,2,2,3,1};
        wiggleSort.wiggleSort(array);
        System.out.println(Arrays.toString(array));

    }
}
