package number;

import java.util.*;

public class MaxNum {

    private class NumComparator implements Comparator<String>{

        @Override
        public int compare(String a, String b) {
            String order1 = a + b;
            String order2 = b + a;
            return order2.compareTo(order1);
        }
    }

    public String largestNumber(int[] nums) {
        List<String> strList = new LinkedList<>();
        for(int i:nums){
            strList.add(String.valueOf(i));
        }
        NumComparator numComparator = new NumComparator();
        strList.sort(numComparator);
        System.out.println(strList);
        StringBuilder stringBuilder = new StringBuilder();
        for(String str:strList){
            stringBuilder.append(str);
        }
        while(stringBuilder.length()>1&&stringBuilder.charAt(0) == '0'){
            stringBuilder = new StringBuilder(stringBuilder.substring(1));
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        MaxNum maxNum = new MaxNum();
        int[] array = {121,12};
        String result =  maxNum.largestNumber(array);
        System.out.println(result);
    }

}
