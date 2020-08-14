package dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MaxContinueSeq {

    public int longestConsecutive(int[] nums) {
        Arrays.sort(nums);
        if(nums.length == 0)return 0;
        if(nums.length == 1)return 1;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        for(int i = 1;i<nums.length;i++){
            if(nums[i] == nums[i-1] + 1){
                dp[i] = dp[i-1] + 1;
            }else if(nums[i] == nums[i-1]){
                dp[i] = dp[i-1];
            }else {
                dp[i] = 1;
            }
        }
        int max = Integer.MIN_VALUE;
        for(int i = 0;i<nums.length;i++){
            if(dp[i]>max){
                max = dp[i];
            }
        }
        return max;
    }


    public int longestConsecutive2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(Integer integer:nums){
            set.add(integer);
        }
        int max = 0;
        for(int num:set){
            if(!set.contains(num-1)){
                int currNum = num;
                int currStreak = 1;
                while(set.contains(currNum+1)){
                    currNum++;
                    currStreak++;
                }
                max = Math.max(max,currStreak);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {100,4,200,1,3,2};
        MaxContinueSeq maxContinueSeq = new MaxContinueSeq();
        int result = maxContinueSeq.longestConsecutive(nums);
        System.out.println(result);
    }

}
