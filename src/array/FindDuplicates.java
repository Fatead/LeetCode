package array;

import java.util.*;

public class FindDuplicates {

    public List<Integer> findDuplicates(int[] nums) {
        Set<Integer> set = new HashSet<>();
        List<Integer> result = new ArrayList<>();
        for(int i:nums){
            if(set.contains(i)){
                result.add(i);
            }else {
                set.add(i);
            }
        }
        return result;
    }

    public List<Integer> findDuplicates2(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for(int i = 0;i<nums.length;i++){
            if(nums[Math.abs(nums[i])-1]<0){
                result.add(Math.abs(nums[i]));
            }else {
                nums[Math.abs(nums[i])-1] = -nums[Math.abs(nums[i])-1];
            }
        }
        return result;
    }


    public static void main(String[] args) {
        FindDuplicates findDuplicates = new FindDuplicates();
        int[] nums = {4,3,2,7,8,2,3,1};
        System.out.println(findDuplicates.findDuplicates2(nums));
    }
}
