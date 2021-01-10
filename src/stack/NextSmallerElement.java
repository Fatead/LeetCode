package stack;

import java.util.Arrays;
import java.util.Stack;

public class NextSmallerElement {


    /**
     * 单调栈，单调栈中入栈的是元素的index，而不是元素本身
     * 单调栈计算模板
     */
    private int[] nextSmallerElementIndex(int[] nums){
        int[] result = new int[nums.length];
        Arrays.fill(result,-1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            int element = nums[i];
            while(!stack.isEmpty() && element < nums[stack.peek()]){
                result[stack.pop()] = i;
            }
            stack.push(i);
        }
        return result;
    }


    public static void main(String[] args) {
        NextSmallerElement nextSmallerElement = new NextSmallerElement();
        int[] nums = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = nextSmallerElement.nextSmallerElementIndex(nums);
        System.out.println(Arrays.toString(result));
    }


}
