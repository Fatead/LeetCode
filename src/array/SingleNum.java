package array;

import java.util.Arrays;

public class SingleNum {

    public int singleNumber(int[] nums) {
        if(nums.length==1)return nums[0];
        Arrays.sort(nums);
        for (int i = 0; i <nums.length-1 ; i++) {
            if(i == 0 && nums[0]!=nums[1]){
                return nums[i];
            }else if(i==0){
                continue;
            }
            if(i==nums.length-2 && nums[i]!=nums[i+1]){
                return nums[i+1];
            }
            if(nums[i]!=nums[i-1] && nums[i]!=nums[i+1]){
                return nums[i];
            }
        }
        return 0;
    }


    public static void main(String[] args) {
        SingleNum singleNum = new SingleNum();
        int[] nums = {3,2,3,3};
        System.out.println(singleNum.singleNumber(nums));
    }

}
