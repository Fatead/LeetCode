package stack;

import java.util.Arrays;
import java.util.Stack;

public class DailyTemperatures {

    /**
     * 暴力解法
     */
    public int[] dailyTemperatures(int[] T) {
        int[] result = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            int time = 0;
            for (int j = i; j <T.length ; j++,time++) {
                if(T[j]>T[i]){
                    result[i] = time;
                    break;
                }
            }
        }
        return result;
    }


    /**
     * 单调栈
     */
    public int[] dailyTemperatures2(int[] T) {
        Stack<Integer> stack = new Stack<>();
        int[] result  = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            int temperature = T[i];
            while(!stack.isEmpty() && temperature > T[stack.peek()]){
                result[stack.peek()] = i - stack.peek();
                stack.pop();
            }
            stack.push(i);
        }
        return result;
    }


    public static void main(String[] args) {
        DailyTemperatures dailyTemperatures = new DailyTemperatures();
        int[] nums = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = dailyTemperatures.dailyTemperatures2(nums);
        System.out.println(Arrays.toString(result));
    }

}
