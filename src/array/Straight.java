package array;

import java.util.Arrays;

public class Straight {


    public boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        int queen = 0;
        int pre = -1;
        for (int i = 0; i <nums.length ; i++) {
            if(nums[i] == 0){
                queen++;
                continue;
            }
            if(i == 0 || pre == -1){
                pre = nums[i];
                continue;
            }
            if(nums[i] == nums[i-1])return false;
            if(nums[i - 1] + 1 == nums[i]){
                pre = nums[i];
                continue;
            }
            if(nums[i]- nums[i-1]-1<=queen){
                queen -= nums[i]- nums[i-1];
            }else {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Straight straight = new Straight();
        int[] nums = {10,11,0,12,6};
        System.out.println(straight.isStraight(nums));
    }

}
