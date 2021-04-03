package listTable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MaxQueue {

    List<Integer> list;

    int max = -1;

    public MaxQueue() {
        list = new LinkedList<>();
    }

    public int max_value() {
        return max;
    }

    public void push_back(int value) {
        list.add(value);
        max = Math.max(value,max);
    }

    public int pop_front() {
        if(list.size() == 0)return -1;
        if(list.get(0) == max){
            max = -1;
            for (int i = 1; i <list.size() ; i++) {
                max = Math.max(max,list.get(i));
            }
        }
        return list.remove(0);
    }

    public static void main(String[] args) {

    }
}
