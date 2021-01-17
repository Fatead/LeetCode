package hashTable;

import java.util.Arrays;

public class NumOfLines {

    public int[] numberOfLines(int[] widths, String s) {
        int totalNum = 0;
        int num = 0;
        for(int i = 0;i<s.length();i++){
            int length =  widths[s.charAt(i)-'a'];
            if(totalNum+length>100){
                totalNum = length;
                num++;
            }else {
                totalNum+=length;
            }
        }
        if(totalNum!=0)num++;
        return new int[]{num,totalNum};
    }

    public static void main(String[] args) {
        NumOfLines numOfLines = new NumOfLines();
        int[] width = {10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10};
        String str = "abcdefghijklmnopqrstuvwxyz";
        int[] result = numOfLines.numberOfLines(width,str);
        System.out.println(Arrays.toString(result));
    }

}
