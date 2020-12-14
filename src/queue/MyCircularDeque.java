package queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyCircularDeque {

    //队列的总长度
    private int length;
    //当前队列的大小
    private int currSize;
    //队列容器
    private List<Integer> list;
    //队尾元素的位置
    private int rear;
    //队首元素的位置
    private int front;


    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        length = k;
        currSize = 0;
        list = new LinkedList<>();
        rear = 0;
        front = 0;
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if(currSize<length){
            list.add(front,value);
            currSize++;
            return true;
        }
        return false;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if(currSize<length){
            list.add(value);
            currSize++;
            return true;
        }
        return false;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if(currSize>0){
            list.remove(0);
            currSize--;
            return true;
        }
        return false;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if(currSize>0){
            list.remove(currSize-1);
            currSize--;
            return true;
        }
        return false;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        if(currSize>0){
            return list.get(0);
        }
        return -1;
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if(currSize>0){
            return list.get(currSize-1);
        }
        return -1;
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return currSize == 0;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return currSize == length;
    }

}
