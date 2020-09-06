package number;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SingleNum3 {

    public int[] singleNumber(int[] nums) {
        Set<Integer> intSet = new HashSet<>();
        for(int i:nums){
            if(intSet.contains(i)){
                intSet.remove(i);
            }else {
                intSet.add(i);
            }
        }
        int[] result = new int[2];
        int num = 0;
        for(int i : intSet){
            result[num++] = i;
        }
        return result;
    }

    public static void main(String[] args) {
        SingleNum3 singleNum3 = new SingleNum3();
        int[] array = {1,2,1,3,2,5};
        int[] result = singleNum3.singleNumber(array);
        System.out.println(Arrays.toString(result));
    }

}
