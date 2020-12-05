package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LHS {

    public int findLHS(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i:nums){
            map.put(i,map.getOrDefault(i,0) + 1);
        }
        int[] array = new int[map.keySet().size()];
        int count = 0;
        for(int i:map.keySet()){
            array[count++] = i;
        }
        Arrays.sort(array);
        int maxLength = 0;
        for(int i = 0;i<array.length-1;i++){
            if(array[i] +1 == array[i+1]){
                maxLength = Math.max(maxLength,map.get(array[i]) + map.get(array[i+1]));
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int[] array = {1};
        LHS lhs = new LHS();
        System.out.println(lhs.findLHS(array));
    }
}
