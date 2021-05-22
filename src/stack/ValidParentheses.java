package stack;

import java.util.Stack;

public class ValidParentheses {


    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i <s.length() ; i++) {
            char c = s.charAt(i);
            if(c == '('||c=='{'||c=='['){
                stack.push(c);
            }else if(stack.isEmpty()){
                return false;
            }else if(stack.peek() == '(' && c == ')'){
                stack.pop();
            }else if(stack.peek() == '[' && c == ']'){
                stack.pop();
            }else if(stack.peek() == '{' && c == '}'){
                stack.pop();
            }else {
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidParentheses validParentheses = new ValidParentheses();
        System.out.println(validParentheses.isValid("[{()}]"));
    }

}
