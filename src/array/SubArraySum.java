package array;

import java.util.HashMap;
import java.util.Map;

public class SubArraySum {

    public int subArraySum(int[] nums,int k){
        int[] sum = new int[nums.length+1];
        for(int i = 0;i<nums.length;i++){
            sum[i+1] = sum[i] + nums[i];
        }
        int result = 0;
        for(int i = 1;i<=nums.length;i++){
            for(int j = 0;j<i;j++){
                if(sum[i] - sum[j] == k){
                    result++;
                }
            }
        }
        return result;
    }

    public int subArraySum2(int[] nums,int k){
        Map<Integer,Integer> preSum = new HashMap<>();
        int sum_i = 0;
        preSum.put(0,1);
        int result = 0;
        for(int i = 0;i<nums.length;i++){
            sum_i = sum_i + nums[i];
            int sum_j = sum_i - k;
            if(preSum.containsKey(sum_j))
                result+= preSum.get(sum_j);
            preSum.put(sum_i,preSum.getOrDefault(sum_i,0) + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array    = {1,1,1};
        SubArraySum subArraySum = new SubArraySum();
        System.out.println(subArraySum.subArraySum2(array,2));

    }
}
