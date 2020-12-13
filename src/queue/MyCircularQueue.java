package queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyCircularQueue {

    //队列的总长度
    private int length;
    //当前队列的大小
    private int currSize;
    //队列容器
    private List<Integer> list;
    //队尾元素的位置
    private int rear;

    public MyCircularQueue(int k) {
        this.length = k;
        currSize = 0;
        list = new LinkedList<>();
        rear = 0;
    }

    public boolean enQueue(int value) {
        if(currSize < length){
            list.add(value);
            currSize++;
            rear++;
            return true;
        }else {
            return false;
        }
    }

    /**
     * 删除循环队列首部元素
     */
    public boolean deQueue() {
        if(currSize > 0){
            list.remove(0);
            rear--;
            currSize--;
            return true;
        }
        return false;
    }

    public int Front() {
        if(currSize>0)return list.get(0);
        return -1;
    }

    public int Rear() {
        if(currSize>0)return list.get(rear-1);
        return -1;
    }

    public boolean isEmpty() {
        return currSize == 0;
    }

    public boolean isFull() {
        return currSize == length;
    }

}
