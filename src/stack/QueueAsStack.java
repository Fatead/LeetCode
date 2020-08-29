package stack;

import java.util.LinkedList;
import java.util.Queue;

public class QueueAsStack {

    Queue<Integer> queueA;
    Queue<Integer> queueB;
    Queue<Integer> currQueue;

    /** Initialize your data structure here. */
    public QueueAsStack() {
        queueA = new LinkedList<>();
        queueB = new LinkedList<>();
        currQueue = queueA;
    }

    /** Push element x onto stack. */
    public void push(int x) {
        currQueue.add(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if(currQueue == queueA){
            while(queueA.size()>1){
                queueB.add(queueA.poll());
            }
            currQueue = queueB;
            return queueA.poll();
        }else {
            while(queueB.size()>1){
                queueA.add(queueB.poll());
            }
            currQueue = queueA;
            return queueB.poll();
        }
    }

    /** Get the top element. */
    public int top() {
        int result = 0;
        if(currQueue == queueA){
            while(queueA.size()>1){
                queueB.add(queueA.poll());
            }
            currQueue = queueB;
            result =  queueA.peek();
            queueB.add(queueA.poll());
        }else {
            while(queueB.size()>1){
                queueA.add(queueB.poll());
            }
            currQueue = queueA;
            result =  queueB.peek();
            queueA.add(queueB.poll());
        }
        return result;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return currQueue.isEmpty();
    }

}
