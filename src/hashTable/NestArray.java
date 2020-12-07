package hashTable;

import java.util.HashSet;
import java.util.Set;

public class NestArray {

    public int arrayNesting(int[] nums) {
        Set<Integer> totalSet = new HashSet<>();
        int maxCount = Integer.MIN_VALUE;
        for(int i = 0;i<nums.length;i++){
            if(totalSet.contains(nums[i]))continue;
            int num = nums[i];
            Set<Integer> set = new HashSet<>();
            int count = 0;
            while(!set.contains(num)){
                set.add(num);
                totalSet.add(num);
                num = nums[num];
                count++;
            }
            maxCount = Math.max(count,maxCount);
        }
        return maxCount;
    }

    public static void main(String[] args) {
        NestArray nestArray = new NestArray();
        int[] nums = {5,4,0,3,1,6,2};
        System.out.println(nestArray.arrayNesting(nums));
    }

}
