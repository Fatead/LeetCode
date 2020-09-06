package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MajorNum2 {

    public List<Integer> majorityElement(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i:nums){
            int count = map.getOrDefault(i,0);
            count++;
            map.put(i,count);
        }
        List<Integer> result = new ArrayList<>();
        for(int i:map.keySet()){
            if(map.get(i) > nums.length/3){
                result.add(i);
            }
        }
        return result;
    }

    public static void main(String[] args) {


    }
}
