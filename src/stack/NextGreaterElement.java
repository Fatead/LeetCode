package stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NextGreaterElement {


    /*
     * 暴力法求解
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];
        for(int i = 0;i<nums1.length;i++){
            for(int j = 0;j<nums2.length;j++){
                if(nums1[i] == nums2[j]){
                    if(j == nums2.length-1){
                        result[i] = -1;
                        break;
                    }
                    for(int n = j+1;n<nums2.length;n++){
                        if(nums2[n] > nums2[j]){
                            result[i] = nums2[n];
                            break;
                        }
                        if (n == nums2.length - 1) {
                            result[i] = -1;
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

    public int[] nextGreaterElement2(int[] num1,int[] num2){
        Stack<Integer> stack = new Stack<>();
        Map<Integer,Integer> map = new HashMap<>();
        int[] result = new int[num1.length];
        for(int i:num2){
            if(stack.isEmpty()){
                stack.add(i);
            }else {
                if(i<stack.peek()){
                    stack.add(i);
                }
                while (!stack.isEmpty() && i>stack.peek()){
                    map.put(stack.pop(),i);
                }
                stack.add(i);
            }
        }
        while(!stack.isEmpty()){
            map.put(stack.pop(),-1);
        }
        for(int i = 0;i<num1.length;i++){
            result[i] = map.getOrDefault(num1[i],-1);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] num1 = {2,4};
        int[] num2 = {1,2,3,4};
        NextGreaterElement nextGreaterElement = new NextGreaterElement();
        int[] result = nextGreaterElement.nextGreaterElement2(num1,num2);
        System.out.println(Arrays.toString(result));
    }


}
