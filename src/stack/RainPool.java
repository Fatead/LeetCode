package stack;

import java.util.Stack;

public class RainPool {


    private int trap(int[] height) {
        Stack<Integer> integerStack = new Stack<>();
        int len = height.length;
        if(len<=1)return 0;
        int waterNum = 0;
        int i = 0;
        while(i<height.length){
            while(!integerStack.isEmpty()&&height[i]>height[integerStack.peek()]){
                int h = height[integerStack.pop()];
                if(integerStack.isEmpty()){
                    break;
                }
                int dis = i - integerStack.peek() - 1;
                int min = Math.min(height[i],height[integerStack.peek()]);
                waterNum += dis*(min-h);
            }
            integerStack.push(i);
            i++;
        }
        return waterNum;
    }

    public static void main(String[] args) {
        int[] array = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        RainPool rainPool = new RainPool();
        System.out.println(rainPool.trap(array));
    }
}
