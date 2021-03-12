package stack;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MiniStack {

    List<Integer> stack;

    int pos = 0;

    public MiniStack() {
        stack = new LinkedList<>();
    }

    public void push(int x) {
        stack.add(x);
        pos++;
    }

    public void pop() {
        if(pos>0){
            stack.remove(pos-1);
            pos--;
        }
    }

    public int top() {
        return stack.get(pos-1);
    }

    public int min() {
        return Collections.min(stack);
    }


}
