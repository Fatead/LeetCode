package hashTable;

import java.util.HashSet;
import java.util.Set;

public class RepeatNumber {

    public int findRepeatNumber(int[] nums) {
        Set<Integer> intSet = new HashSet<>();
        for (int i = 0; i <nums.length ; i++) {
            if(intSet.contains(nums[i])){
                return nums[i];
            }
            intSet.add(nums[i]);
        }
        return -1;
    }

    public static void main(String[] args) {
        RepeatNumber repeatNumber = new RepeatNumber();
        int[] number = {2, 3, 1, 0, 2, 5, 3};
        System.out.println(repeatNumber.findRepeatNumber(number));
    }

}
