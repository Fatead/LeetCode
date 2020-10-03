package array;

import java.util.*;

public class ArrayIntersection2 {

    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer,Integer> map1 = new HashMap<>();
        for(int i:nums1){
            map1.put(i,map1.getOrDefault(i,0) + 1);
        }
        Map<Integer,Integer> map2 = new HashMap<>();
        for(int i:nums2){
            map2.put(i,map2.getOrDefault(i,0) + 1);
        }
        List<Integer> resultList = new ArrayList<>();
        for(int i:map1.keySet()){
            if(map2.containsKey(i)){
                int count = Math.min(map1.get(i),map2.get(i));
                for(int j = 0;j<count;j++){
                    resultList.add(i);
                }
            }
        }
        int[] result = new int[resultList.size()];
        for(int i = 0;i<result.length;i++){
            result[i] = resultList.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        ArrayIntersection2 arrayIntersection2 = new ArrayIntersection2();
        int[] num1 = {4,9,5,9};
        int[] num2 = {9,4,9,8,4};
        int[] result = arrayIntersection2.intersect(num1,num2);
        System.out.println(Arrays.toString(result));

    }
}
