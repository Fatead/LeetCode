package number;

import java.util.Stack;

public class ConvertToBase7 {

    public String convertToBase7(int num) {
        if(num == 0)return "0";
        boolean positive = true;
        if(num < 0)positive = false;
        Stack<Integer> stack = new Stack<>();
        num = Math.abs(num);
        while(num > 0){
            stack.add(num%7);
            num = num/7;
        }
        StringBuilder stringBuilder = new StringBuilder();
        if(!positive)stringBuilder.append("-");
        while(!stack.isEmpty()){
            stringBuilder.append(stack.pop());
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        ConvertToBase7 convertToBase7 = new ConvertToBase7();
        System.out.println(convertToBase7.convertToBase7(0));
    }

}
