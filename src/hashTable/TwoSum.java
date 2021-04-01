package hashTable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (int i = 0; i <nums.length ; i++) {
            if(set.contains(target - nums[i]) && target != 2*nums[i]){
                return new int[]{nums[i],target - nums[i]};
            }else if(target == 2*nums[i]){
                int count = 0;
                for (int num : nums) {
                    if(num == nums[i]){
                        count++;
                    }
                }
                if(count>1)return new int[]{nums[i],target - nums[i]};
            }
        }
        return new int[]{};
    }

    /**
     * 双指针法
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        int left = 0, right = nums.length-1;
        while (left<right){
            int sum = nums[left] + nums[right];
            if(sum==target){
                return new int[]{nums[left],nums[right]};
            }else if(sum<target){
                left++;
            }else {
                right--;
            }
        }
        return new int[]{};
    }


    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        int[] array = {2,4,4,7,11,15};
        int[] result = twoSum.twoSum2(array,8);
        System.out.println(Arrays.toString(result));
    }

}
