package array;

import java.util.ArrayList;
import java.util.List;

public class PrefixesDivBy5 {

    public List<Boolean> prefixesDivBy5(int[] nums) {
        int curr = 0;
        List<Boolean> result = new ArrayList<>();
        for (int num : nums) {
            curr += num;
            if (curr % 5 == 0) {
                result.add(true);
            }
            else result.add(false);
            curr *= 2;
            curr%=10;
        }
        return result;
    }

    public static void main(String[] args) {
        PrefixesDivBy5 prefixesDivBy5 = new PrefixesDivBy5();
        int[] nums = {0,1,1};
        System.out.println(prefixesDivBy5.prefixesDivBy5(nums));
    }

}
