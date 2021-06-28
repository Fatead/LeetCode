package array;

import java.util.Stack;

public class ValidAbc {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i <s.length() ; i++) {
            if(s.charAt(i)=='a'||s.charAt(i)=='b'){
                stack.push(s.charAt(i));
            }else if(s.charAt(i)=='c'){
                if(stack.size()<2)return false;
                if(stack.pop()!='b')return false;
                if(stack.pop()!='a')return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidAbc validAbc = new ValidAbc();
        System.out.println(validAbc.isValid("aababcbccabc"));
    }

}
