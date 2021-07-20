package string;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class RemoveDuplicates {

    public String removeDuplicates(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i <s.length() ; i++) {
            if(stack.isEmpty()){
                stack.add(s.charAt(i));
                continue;
            }
            while (!stack.isEmpty() && i<s.length() && stack.peek() == s.charAt(i)){
                stack.pop();
                i++;
            }
            if(i>=s.length())break;
            stack.add(s.charAt(i));
        }
        while (!stack.isEmpty()){
            stringBuilder.append(stack.pop());
        }
        return stringBuilder.reverse().toString();
    }

    public static void main(String[] args) {
        RemoveDuplicates removeDuplicates = new RemoveDuplicates();
        System.out.println(removeDuplicates.removeDuplicates("aaaaaaaa"));
    }

}
