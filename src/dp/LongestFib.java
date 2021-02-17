package dp;

import java.util.*;

public class LongestFib {

    public int lenLongestFibSubseq(int[] arr) {
        Map<Integer,Integer> indexMap = new HashMap<>();
        for (int i = 0; i <arr.length ; i++) {
            indexMap.put(arr[i],i);
        }
        Map<Integer,Integer> longest = new HashMap<>();
        int result = 0;
        for(int k = 0;k<arr.length;k++){
            for(int j = 0;j<k;j++){
                int i = indexMap.getOrDefault(arr[k]-arr[j],-1);
                if(i>=0&&i<j){
                    int candidate = longest.getOrDefault(i*arr.length+j,2)+1;
                    longest.put(j*arr.length+k,candidate);
                    result = Math.max(result,candidate);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        LongestFib longestFib = new LongestFib();
        int[] array = {1,2,3,4,5,6,7,8};
        System.out.println(longestFib.lenLongestFibSubseq(array));
    }

}
