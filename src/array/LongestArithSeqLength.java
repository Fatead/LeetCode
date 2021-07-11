package array;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode
 * 从前往后遍历，在往前求不同公差下的最长序列
 */
public class LongestArithSeqLength {

    public int longestArithSeqLength(int[] nums) {
        int n = nums.length;
        if(n == 0)return 0;
        //定义哈希表，第一个键表示数组下标索引，内部的哈希表用于存储不同公差包含的最长序列
        Map<Integer, Map<Integer,Integer>> map = new HashMap<>();
        int result = 1;
        for (int i = 0; i <n ; i++) {
            map.put(i,new HashMap<>());
            for (int j = i-1; j >=0 ; j--) {
                if(map.get(i).containsKey(nums[i] - nums[j])) continue;
                int curr = map.get(j).getOrDefault(nums[i] - nums[j], 0);
                result = Math.max(result, curr + 2);
                map.get(i).put(nums[i] - nums[j], curr+1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        LongestArithSeqLength longestArithSeqLength = new LongestArithSeqLength();
        int[] nums = {9,4,7,2,10};
        System.out.println(longestArithSeqLength.longestArithSeqLength(nums));
    }

}