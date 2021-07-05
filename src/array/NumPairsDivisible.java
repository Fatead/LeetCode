package array;

import java.util.HashMap;
import java.util.Map;

public class NumPairsDivisible {

    public int numPairsDivisibleBy60(int[] time) {
        int count = 0;
        int[] newTime = new int[time.length];
        for (int i = 0; i <time.length ; i++) {
            newTime[i] = time[i]%60;
        }
        Map<Integer,Integer> countMap = new HashMap<>();
        for (int i : newTime) {
            countMap.put(i,countMap.getOrDefault(i,0)+1);
        }
        for (int i : newTime) {
            if(i == 30){
                count += countMap.get(30)-1;
            }else if(i == 0){
                count += countMap.get(0)-1;
            } else {
                count += countMap.getOrDefault(60-i,0);
            }
        }
        return count/2;
    }

    public static void main(String[] args) {
        NumPairsDivisible numPairsDivisible = new NumPairsDivisible();
        int[] times = {120,120};
        System.out.println(numPairsDivisible.numPairsDivisibleBy60(times));
    }

}
