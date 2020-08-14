package stack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class MinStack {

    List<Integer> integerList;
    int size;
    int min;

    /** initialize your data structure here. */
    public MinStack() {
        integerList = new LinkedList<>();
        size = 0;
        min = Integer.MAX_VALUE;
    }

    public void push(int x) {
        integerList.add(x);
        size++;
        if(x<min)min = x;
    }

    public void pop() {
        if(integerList.get(size-1) == min){
            min = Integer.MAX_VALUE;
            for(int i = 0;i<integerList.size()-1;i++){
                if(integerList.get(i)<min){
                    min = integerList.get(i);
                }
            }
        }
        integerList.remove(size-1);
        size--;
    }

    public int top() {
        return integerList.get(size-1);
    }

    public int getMin() {
        return min;
    }
}