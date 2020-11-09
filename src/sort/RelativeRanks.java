package sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RelativeRanks {

    public String[] findRelativeRanks(int[] nums) {
        int[] array = Arrays.copyOfRange(nums,0,nums.length);
        Arrays.sort(array);
        for(int i = 0;i<array.length/2;i++){
            int temp = array[i];
            array[i] = array[array.length-i-1];
            array[array.length-i-1] = temp;
        }
        Map<Integer,String> map = new HashMap<>();
        for(int i = 0;i<array.length;i++){
            if(i == 0){
                map.put(array[i], "Gold Medal");
            }else if(i == 1){
                map.put(array[i],"Silver Medal");
            }else if(i == 2){
                map.put(array[i],"Bronze Medal");
            }else {
                map.put(array[i], String.valueOf(i+1));
            }
        }
        String[] result = new String[nums.length];
        for(int i = 0;i<nums.length;i++){
            result[i] = String.valueOf(map.get(nums[i]));
        }
        return result;
    }

    public static void main(String[] args) {
        RelativeRanks relativeRanks = new RelativeRanks();
        int[] nums = {5,4,3,2,1};
        String[] result =  relativeRanks.findRelativeRanks(nums);
        System.out.println(Arrays.toString(result));
    }
}
