package number;

import java.util.ArrayList;
import java.util.List;

public class BitAdd {

    private List<Integer> num2list(int num){
        List<Integer> intList = new ArrayList<>();
        while(num >= 1){
            intList.add(num%10);
            num = num/10;
        }
        return intList;
    }

    public int addDigits(int num) {
        if(num<10)return num;
        while(num >= 10){
            List<Integer> intList = num2list(num);
            int result = 0;
            for(Integer i:intList){
                result += i;
            }
            num = result;
        }
        return num;
    }

    public static void main(String[] args) {
        BitAdd bitAdd = new BitAdd();
        System.out.println(bitAdd.addDigits(38));
    }
}
