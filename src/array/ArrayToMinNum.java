package array;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class ArrayToMinNum {

    public String minNumber(int[] nums) {
        String[] strings = new String[nums.length];
        for (int i = 0; i <nums.length ; i++) {
            strings[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strings, (x, y) -> (x+y).compareTo(y+x));
        StringBuilder result = new StringBuilder();
        for (String string : strings) {
            result.append(string);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        ArrayToMinNum arrayToMinNum = new ArrayToMinNum();
        int[] nums = {3,30,34,5,9};
        System.out.println(arrayToMinNum.minNumber(nums));
    }

}
