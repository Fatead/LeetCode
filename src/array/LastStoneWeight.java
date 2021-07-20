package array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class LastStoneWeight {

    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((integer, t1) -> t1-integer);
        for (int stone : stones) {
            queue.add(stone);
        }
        while (queue.size()>1){
            int num1 = queue.poll();
            int num2 = queue.poll();
            if(num1 != num2){
                queue.add(Math.abs(num1-num2));
            }
        }
        if(queue.isEmpty())return 0;
        return queue.peek();
    }

    public static void main(String[] args) {
        LastStoneWeight lastStoneWeight = new LastStoneWeight();
        int[] stones = {2,7,4,1,8,1};
        System.out.println(lastStoneWeight.lastStoneWeight(stones));
    }

}
