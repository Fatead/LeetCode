package array;

import java.util.*;

public class RemoveDuplicates {

    public int removeDuplicates(int[] nums) {
        int length = nums.length;
        if(length == 0)return 0;
        else if(length == 1)return 1;
        Map<Integer,Integer> countMap = new HashMap<>();
        for(int num:nums){
            if(countMap.get(num)!=null){
                countMap.put(num,2);
            }else {
                countMap.put(num,1);
            }
        }
        int pos = 0;
        List<Integer> keyList = new ArrayList<>(countMap.keySet());
        Collections.sort(keyList);
        for(Integer integer:keyList){
            if(countMap.get(integer) == 1){
                nums[pos++] = integer;
            }else {
                nums[pos++] = integer;
                nums[pos++] = integer;
            }
        }
        return pos;
    }

    public static void main(String[] args) {
        int[] nums = {-3,-1,0,0,0,3,3};
        RemoveDuplicates removeDuplicates = new RemoveDuplicates();
        removeDuplicates.removeDuplicates(nums);
        System.out.println(Arrays.toString(nums));
    }
}
