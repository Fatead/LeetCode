package array;

import java.util.HashMap;
import java.util.Map;

/**
 * 利用前缀和的思路，Pi = nums[0] + ... + nums[i]
 * sum(i,j) 可以写为Pj - Pi-1，根据同余定理，只要Pj mod k == Pi-1 mod k
 * 就可以保证等式成立
 */
public class SubArraysDiv {

    public int subarraysDivByK(int[] nums, int k) {
        int result = 0;
        Map<Integer,Integer> record = new HashMap<>();
        record.put(0,1);
        int sum = 0;
        for (int num : nums) {
            sum += num;
            int modules = (sum%k + k) % k;
            int same = record.getOrDefault(modules,0);
            result += same;
            record.put(modules,same + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        SubArraysDiv subArraysDiv = new SubArraysDiv();
        int[] nums = {4,5,0,-2,-3,1};
        System.out.println(subArraysDiv.subarraysDivByK(nums,5));
    }

}
