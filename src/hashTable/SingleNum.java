package hashTable;

import java.util.HashMap;
import java.util.Map;

public class SingleNum {

    public int singleNumber(int[] nums) {
        Map<Integer,Integer> numMap = new HashMap<>();
        for(Integer integer:nums){
            int count = numMap.getOrDefault(integer,0);
            count++;
            numMap.put(integer,count);
        }
        for(Integer integer:numMap.keySet()){
            if(numMap.get(integer) == 1){
                return integer;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] array = {2,2,1};
        SingleNum singleNum = new SingleNum();
        int result =  singleNum.singleNumber(array);
        System.out.println(result);
    }
}
