package stack;
import java.util.Stack;

public class RemoveOuterParentheses {

    public String removeOuterParentheses(String s) {
        int level = 0;
        StringBuilder result = new StringBuilder();
        for (char c : s.toCharArray()) {
            if(c == ')')level--;
            if(level>=1)result.append(c);
            if(c == '(')level++;
        }
        return result.toString();
    }

    public static void main(String[] args) {
        RemoveOuterParentheses removeOuterParentheses = new RemoveOuterParentheses();
        System.out.println(removeOuterParentheses.removeOuterParentheses("((()())(()()))"));
    }

}