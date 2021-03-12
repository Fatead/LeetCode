package stack;
import java.util.Stack;

public class ValidStackSeq {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int posPushed = 0;
        int posPopped = 0;
        while (posPopped < popped.length && posPushed < pushed.length){
            if(stack.isEmpty() || stack.peek() != popped[posPopped]){
                stack.push(pushed[posPushed++]);
            }
            while (!stack.isEmpty()&&stack.peek() == popped[posPopped] ){
                stack.pop();
                posPopped++;
            }
        }
        while (!stack.isEmpty() && posPopped < popped.length && stack.peek() == popped[posPopped]){
            posPopped++;
            stack.pop();
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidStackSeq validStackSeq = new ValidStackSeq();
        int[] pushed = {1,2,3,4,5};
        int[] popped = {4,5,3,2,1};
        System.out.println(validStackSeq.validateStackSequences(pushed,popped));
    }

}