package hashTable;

import java.util.Arrays;

public class ErrorNums {

    public int[] findErrorNums(int[] nums) {
        int[] countArray = new int[nums.length];
        for(int i:nums){
            countArray[i-1]++;
        }
        int[] result = new int[2];
        for(int i = 0;i<countArray.length;i++){
            if(countArray[i] == 2){
                result[0] = i+1;
            }
            if(countArray[i] == 0){
                result[1] = i+1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ErrorNums errorNums = new ErrorNums();
        int[] array = {1,2,2,4};
        int[] result = errorNums.findErrorNums(array);
        System.out.println(Arrays.toString(result));
    }

}
