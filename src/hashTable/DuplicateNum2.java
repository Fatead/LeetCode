package hashTable;

import java.util.HashMap;
import java.util.Map;

public class DuplicateNum2 {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<nums.length;i++){
            if(map.containsKey(nums[i])){
                int pos = map.get(nums[i]);
                if(i - pos<=k){
                    return true;
                }
            }
            map.put(nums[i],i);
        }
        return false;
    }


    public static void main(String[] args) {

    }
}
