package array;

import java.util.PriorityQueue;

public class KthLargest2 {

    PriorityQueue<Integer> queue;
    int k;

    public KthLargest2(int k, int[] nums) {
        this.k = k;
        queue = new PriorityQueue<Integer>(k);
        for(int i: nums) {
            add(i);
        }
    }


    public int add(int val){
        if(queue.size()<k){
            queue.add(val);
        }else if(queue.peek()<val){
            queue.poll();
            queue.add(val);
        }
        return queue.peek();
    }


}
