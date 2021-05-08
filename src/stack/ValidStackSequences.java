package stack;

import java.util.Stack;

public class ValidStackSequences {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if(pushed.length!=popped.length)return false;
        Stack<Integer> stack = new Stack<>();
        int pos1 = 0,pos2 = 0;
        while (pos1<pushed.length){
            stack.push(pushed[pos1++]);
            while (!stack.isEmpty() && stack.peek() == popped[pos2]){
                stack.pop();
                pos2++;
            }
        }
        return pushed.length == pos1 && pos1 == pos2;
    }

    public static void main(String[] args) {
        ValidStackSequences validStackSequences = new ValidStackSequences();
        int[] pushed = {1,2,3,4,5};
        int[] popped = {4,3,5,1,2};
        System.out.println(validStackSequences.validateStackSequences(pushed,popped));
    }

}
