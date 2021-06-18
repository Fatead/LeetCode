package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AddToArrayForm {


    private int[] int2array(int num){
        List<Integer> list = new ArrayList<>();
        if(num == 0)return new int[]{0};
        while (num >0){
            list.add(num%10);
            num /=10;
        }
        int[] result = new int[list.size()];
        int pos = 0;
        Collections.reverse(list);
        for (Integer integer : list) {
            result[pos++] = integer;
        }
        return result;
    }


    public List<Integer> addToArrayForm(int[] num, int k) {
        int[] result = int2array(k);
        int maxLen = Math.max(num.length,result.length);
        int minLen = Math.min(num.length,result.length);
        List<Integer> list = new ArrayList<>();
        int nextBit = 0;
        for (int i = 0; i <minLen ; i++) {
            int sum = num[num.length-i-1] + result[result.length-i-1] + nextBit;
            if(sum>9){
                list.add(sum%10);
                nextBit = 1;
            }else {
                list.add(sum);
                nextBit = 0;
            }
        }
        if(num.length>= result.length){
            for (int i = minLen; i <maxLen ; i++) {
                int sum = num[num.length-i-1] + nextBit;
                if(sum>9){
                    list.add(sum%10);
                    nextBit = 1;
                }else {
                    list.add(sum);
                    nextBit = 0;
                }
            }
        }else {
            for (int i = minLen; i <maxLen ; i++) {
                int sum = result[result.length-i-1] + nextBit;
                if(sum>9){
                    list.add(sum%10);
                    nextBit = 1;
                }else {
                    list.add(sum);
                    nextBit = 0;
                }
            }
        }
        if(nextBit == 1){
            list.add(1);
        }
        Collections.reverse(list);
        return list;
    }


    public static void main(String[] args) {
        AddToArrayForm addToArrayForm = new AddToArrayForm();
        int[] nums = {1,2,6,3,0,7,1,7,1,9,7,5,6,6,4,4,0,0,6,3};
        System.out.println(addToArrayForm.addToArrayForm(nums,516));
    }

}
