package number;

import java.util.ArrayList;
import java.util.List;

public class MonotoneIncreaseNum {


    public int monotoneIncreasingDigits(int N) {
        char[] chars = Integer.toString(N).toCharArray();
        int i = 1;
        while(i<chars.length && chars[i-1]<=chars[i]){
            i++;
        }
        if(i<chars.length){
            while(i>0 && chars[i-1]>chars[i]){
                chars[i-1]-=1;
                i--;
            }
            for(i+=1;i<chars.length;i++){
                chars[i] = '9';
            }
        }
        return Integer.parseInt(new String(chars));
    }


    public static void main(String[] args) {
        MonotoneIncreaseNum monotoneIncreaseNum = new MonotoneIncreaseNum();
        System.out.println(monotoneIncreaseNum.monotoneIncreasingDigits(963856657));
    }

}
