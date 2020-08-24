package hashTable;

import java.util.HashMap;
import java.util.Map;

public class DuplicateNum3 {


    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        Map<Integer,Integer> map = new HashMap<>();
        if(nums.length == 1)return false;
        for(int i = 1;i<nums.length;i++){
            for(int j = i-1;j>=0&&i-j<=k;j--){
                long num1 = nums[j];
                long num2 = nums[i];
                long absNum = Math.abs(num1 - num2);
                absNum = Math.abs(absNum);
                if(absNum<=t){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {2147483647,-2147483647};
        DuplicateNum3 duplicateNum3 = new DuplicateNum3();
        boolean result =  duplicateNum3.containsNearbyAlmostDuplicate(nums,1,2147483647);
        System.out.println(result);
    }

}
