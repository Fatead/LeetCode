package stack;

import java.util.Stack;

public class ValidString {

    public boolean checkValidString(String s) {
        Stack<Integer> leftStack = new Stack<>();
        Stack<Integer> starStack = new Stack<>();
        for(int i = 0;i<s.length();i++){
            if(s.charAt(i) == '('){
                leftStack.push(i);
            }else if(s.charAt(i) == ')'){
                if(!leftStack.isEmpty()){
                    leftStack.pop();
                }else {
                    if(!starStack.isEmpty()){
                        starStack.pop();
                    }else {
                        return false;
                    }
                }
            }else{
                starStack.push(i);
            }
        }
        if(leftStack.size()>starStack.size()){
            return false;
        }else {
            while(!leftStack.isEmpty()&&leftStack.peek()<starStack.peek()){
                leftStack.pop();
                starStack.pop();
            }
            return leftStack.isEmpty();
        }
    }

    public static void main(String[] args) {
        String str = "((**)))((**)))((";
        ValidString validString = new ValidString();
        System.out.println(validString.checkValidString(str));
    }

}
