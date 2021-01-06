package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class AsteroidsCollision {


    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for(int ast:asteroids){
            collision:{
               while(!stack.isEmpty() && ast<0 && stack.peek()>0){
                   if(Math.abs(stack.peek())<Math.abs(ast)){
                       stack.pop();
                       continue;
                   }else if(Math.abs(stack.peek())==Math.abs(ast)){
                       stack.pop();
                   }
                   break collision;
               }
               stack.push(ast);
            }
        }
        int[] result = new int[stack.size()];
        int length = stack.size()-1;
        while(length>=0){
            result[length--] = stack.pop();
        }
        return result;
    }

    public static void main(String[] args) {
        AsteroidsCollision asteroidsCollision = new AsteroidsCollision();
        int[] asteroids = {10,2,-5};
        int[] result = asteroidsCollision.asteroidCollision(asteroids);
        System.out.println(Arrays.toString(result));
    }

}
