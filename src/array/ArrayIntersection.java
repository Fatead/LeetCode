package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ArrayIntersection {

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        for(int num:nums1){
            set1.add(num);
        }
        Set<Integer> resultSet = new HashSet<>();
        for(int i:nums2){
            if(set1.contains(i)){
                resultSet.add(i);
            }
        }
        int[] result = new int[resultSet.size()];
        int count = 0;
        for(Integer integer:resultSet){
            result[count++] = integer;
        }
        return result;
    }

    public static void main(String[] args) {


    }
}
