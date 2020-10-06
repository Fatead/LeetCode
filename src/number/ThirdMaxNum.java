package number;

import java.util.HashSet;
import java.util.Set;

public class ThirdMaxNum {

    public int thirdMax(int[] nums) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        Set<Integer> set = new HashSet<>();
        for(int i = 0;i<nums.length;i++){
            set.add(nums[i]);
            if(max1<nums[i]){
                max3 = max2;
                max2 = max1;
                max1 = nums[i];
            }else if(max2<nums[i] && nums[i]<max1){
                max3 = max2;
                max2 = nums[i];
            }else if(max3<nums[i] && nums[i] <max2){
                max3 = nums[i];
            }
        }
        if(set.size()>=3)return max3;
        return max1;
    }

    public static void main(String[] args) {
        ThirdMaxNum thirdMaxNum = new ThirdMaxNum();
        int[] array = {1,1,2};
        System.out.println(thirdMaxNum.thirdMax(array));
    }
}
