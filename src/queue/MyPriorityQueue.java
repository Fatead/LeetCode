package queue;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 自己手写一个PriorityQueue
 */
public class MyPriorityQueue {

    int size;
    int capacity;
    int[] elements;
    static final int DEFAULT_CAPACITY = 16;

    public MyPriorityQueue() {
        this.capacity = DEFAULT_CAPACITY;
        elements = new int[DEFAULT_CAPACITY];
        size = 0;
    }

    public int peek(){
        return elements[0];
    }

    public int size(){
        return this.size;
    }

    public int poll(){
        int result = elements[0];
        elements[0] = elements[--size];
        int index = 0;
        while (true){
            int left = index*2;
            int right = index*2 + 1;
            //交换当前节点和两个子节点中较小的一个
            if(left>size)break;
            else if(right<size && elements[right]<elements[index] && elements[right] < elements[left]){
                swap(index,right,elements);
                index = right;
            }else if(elements[left] < elements[index]){
                swap(left,index,elements);
                index = left;
            }else break;
        }
        return result;
    }

    public void add(int num){
        //在插入之前首先要判断是否需要扩容
        if(size == capacity){
            capacity *= 1.5;
            //将数组长度扩容到原来的1.5倍
            elements = Arrays.copyOf(elements,capacity);
        }
        //先将要加的数放到堆的最后一个节点上
        elements[size++] = num;
        //然后在堆中执行上浮操作
        int index = size - 1;
        //上浮到堆顶为止
        while (index > 0){
            if(elements[index]<elements[index/2]){
                swap(index,index/2,elements);
                index = index/2;
            }else break;
        }
    }


    public void swap(int i, int j,int[] nums){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}