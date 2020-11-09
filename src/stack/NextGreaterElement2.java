package stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NextGreaterElement2 {


    public int[] nextGreaterElements(int[] nums) {
        int[] array = Arrays.copyOfRange(nums,0,nums.length);
        int[] result = new int[nums.length];
        Arrays.fill(result,-1);
        for(int i = 0;i<array.length;i++){
            int j = i+1;
            while(j!=i){
                j = j%nums.length;
                if(i == j)break;
                if(array[j]>array[i]){
                    result[i] = array[j];
                    break;
                }
                j++;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        NextGreaterElement2 nextGreaterElement2 = new NextGreaterElement2();
        int[] nums = {100,1,11,1,120,111,123,1,-1,-100};
        int[] result = nextGreaterElement2.nextGreaterElements(nums);
        System.out.println(Arrays.toString(result));
    }

}
