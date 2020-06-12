package number;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Multiply {

    public String multiply(String num1, String num2) {
        if(num1.length() == 0||num2.length()==0)return "";
        if(num1.equals("0")||num2.equals("0"))return "0";
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        int cap = num1.length()+num2.length()-1;
        List<List<Integer>> lists = new ArrayList<>();
        for(int i = 0;i<cap;i++){
            lists.add(new ArrayList<>());
        }
        for(int j = 0;j<chars2.length;j++){
            for(int i = 0;i<chars1.length;i++){
                List<Integer> numList = lists.get(i+j);
                int num = (chars1[i] - '0')*(chars2[j] - '0');
                numList.add(num);
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        int nextAdd = 0;
        for(int i = cap-1;i>=0;i--){
            int sum = 0;
            for(Integer integer:lists.get(i)){
                sum+=integer;
            }
            sum+=nextAdd;
            int num = sum%10;
            stringBuffer.append(num);
            nextAdd = (sum-num)/10;
        }
        if(nextAdd!=0)stringBuffer.append(nextAdd);
        return stringBuffer.reverse().toString();
    }

    public static void main(String[] args) {
        Multiply multiply = new Multiply();
        System.out.println(multiply.multiply("1231","0"));
    }
}
