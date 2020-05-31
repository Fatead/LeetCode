package number;

import string.MaxCommonPrefix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClosestNum {

    public int threeSumClosest(int[] nums, int target) {
        int sum = 0;
        if(nums.length<=3){
            for (int num : nums) {
                sum += num;
            }
            return sum;
        }
        Arrays.sort(nums);
        List<Integer> numList = new ArrayList<>();
        for(int i =0;i<nums.length-2;i++){
            int L = i+1;
            int R = nums.length-1;
            int distance = Integer.MAX_VALUE;

            while(L<R){
                sum = nums[i] + nums[L] + nums[R];
                if(sum == target)return sum;
                if(Math.abs(sum-target)<=distance){
                    distance = Math.abs(sum-target);
                    numList.add(sum);
                }else {
                    numList.add(sum);
                    //break;
                }
                if(sum<target)L++;
                else R--;
                if(L==R)numList.add(sum);
            }
        }
        int distance = Integer.MAX_VALUE;
        int result = 0;
        for(Integer integer:numList){
            if(Math.abs(integer-target)<distance){
                distance = Math.abs(integer-target);
                result = integer;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-55,-24,-18,-11,-7,-3,4,5,6,9,11,23,33};
        ClosestNum closestNum = new ClosestNum();
        System.out.println(closestNum.threeSumClosest(nums,0));
    }
}
