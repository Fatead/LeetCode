package sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SortColor {

    public void sortColors(int[] nums) {
        int length = nums.length;
        if(length == 0)return;
        Map<Integer,Integer> countMap = new HashMap<>();
        for(int i = 0;i<nums.length;i++){
            if(countMap.get(nums[i])!=null){
                countMap.put(nums[i],countMap.get(nums[i]) + 1);
            }else {
                countMap.put(nums[i],1);
            }
        }
        int m = 0;
        for(int i = 0;i<=2;i++){
            if(countMap.get(i)!=null){
                for(int j = 0;j<countMap.get(i);j++){
                    nums[m++] = i;
                }
            }
        }
    }

    public static double f1(double pre,double recall){
        return (pre*recall*2)/(pre + recall);
    }

    public static void main(String[] args) {
//        int[] array = {0};
//        SortColor sortColor = new SortColor();
//        sortColor.sortColors(array);
//        System.out.println(Arrays.toString(array));
    }

}
