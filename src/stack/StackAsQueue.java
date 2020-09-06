package stack;

import java.util.Stack;

public class StackAsQueue {

    Stack<Integer> stack1;
    Stack<Integer> stack2;
    Stack<Integer> currStack;

    public StackAsQueue(){
        stack1 = new Stack<>();
        stack2 = new Stack<>();
        currStack = stack1;
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        currStack.add(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        int result = 0;
        if(currStack == stack1){
            while(stack1.size()>1){
                stack2.add(stack1.pop());
            }
            //currStack = stack2;
            result =  stack1.pop();
            while(!stack2.isEmpty()){
                stack1.add(stack2.pop());
            }
        }else {
            while(stack2.size()>1){
                stack1.add(stack2.pop());
            }
            currStack = stack1;
            result = stack2.pop();
            while(!stack1.isEmpty()){
                stack2.add(stack1.pop());
            }
        }
        return result;
    }

    /** Get the front element. */
    public int peek() {
        int result = 0;
        if(currStack == stack1){
            while(!stack1.isEmpty()){
                stack2.add(stack1.pop());
            }
            result = stack2.peek();
            while(!stack2.isEmpty()){
                stack1.add(stack2.pop());
            }
        }else {
            while(!stack2.isEmpty()){
                stack1.add(stack2.pop());
            }
            result = stack1.peek();
            while(!stack1.isEmpty()){
                stack2.add(stack1.pop());
            }
        }
        return result;
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return currStack.isEmpty();
    }
}
