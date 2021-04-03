package listTable;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 维护一个单调的双端队列
 * 当一个元素进入队列的时候，它前面所有比它小的元素就不会再对答案产生影响了
 */
public class MaxQueue2 {

    Queue<Integer> q;
    Deque<Integer> d;

    public MaxQueue2(){
        q = new LinkedList<>();
        d = new LinkedList<>();
    }

    public int max_value(){
        if(!d.isEmpty()){
            return d.peekFirst();
        }
        return -1;
    }


    public void push_back(int value){
        q.add(value);
        while (!d.isEmpty() && d.peekLast()<value){
            d.removeLast();
        }
        d.add(value);
    }

    public int pop_front(){
        if(q.size() == 0)return -1;
        int element = q.poll();
        if(element == d.getFirst()){
            d.poll();
        }
        return element;
    }


    public static void main(String[] args) {

    }

}
