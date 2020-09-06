package number;

import java.util.HashSet;
import java.util.PriorityQueue;

public class NUglyNum {

    public int nthUglyNumber(int n) {
        int[] nums = new int[1690];
        HashSet<Long> numSet = new HashSet<>();
        PriorityQueue<Long> heap = new PriorityQueue<>();
        numSet.add(1L);
        heap.add(1L);
        int[] primes = new int[]{2,3,5};
        long currNum,newNum;
        for(int i = 0;i<1690;i++){
            currNum = heap.poll();
            nums[i] = (int)currNum;
            for(int j :primes){
                newNum = currNum*j;
                if(!numSet.contains(newNum)){
                    heap.add(newNum);
                    numSet.add(newNum);
                }
            }
        }
        return nums[n-1];
    }

}
