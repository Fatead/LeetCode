package number;

import java.util.ArrayList;
import java.util.List;

public class ReverseNum {

    public int reverse(int x) {
        if(x == 0 || (x < 0 && -x < 0))
            return 0;
        Boolean flag = x>0;
        StringBuilder str = new StringBuilder(String.valueOf(Math.abs(x)));
        if(str.length()>32)return 0;
        str = str.reverse();
        double value = Double.parseDouble(str.toString());
        if(value>Integer.MAX_VALUE)return 0;
        if(flag) return (int)value;
        else
            return -(int)value;
    }

    public static void main(String[] args) {
        ReverseNum reverseNum = new ReverseNum();
        System.out.println(reverseNum.reverse(-2147483648));
    }
}
