package listTable;

import java.util.PriorityQueue;

public class MedianFinder2 {

    private int count;
    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;

    public MedianFinder2(){
        count = 0;
        maxHeap = new PriorityQueue<>((x,y)->y-x);
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num){
        count++;
        maxHeap.add(num);
        minHeap.add(maxHeap.poll());
        if(count % 2 == 1){
            maxHeap.add(minHeap.poll());
        }
    }

    public double findMedian(){
        if(count%2 == 0){
            return (double) (maxHeap.peek() + minHeap.peek())/2;
        }else {
            return (double) maxHeap.peek();
        }
    }


    public static void main(String[] args) {
        MedianFinder2 medianFinder2 = new MedianFinder2();
        medianFinder2.maxHeap.add(10);
        medianFinder2.maxHeap.add(120);
        System.out.println(medianFinder2.maxHeap.poll());
    }

}
