package hashTable;

import java.util.HashSet;
import java.util.Set;

public class DuplicateNum {


    public boolean containsDuplicate(int[] nums) {
        if(nums.length == 0)return false;
        Set<Integer> intSet = new HashSet<>();
        for(Integer i:nums){
            if(!intSet.contains(i)){
                intSet.add(i);
            }else {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {

    }
}
