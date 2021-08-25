package string;

import java.util.*;

public class CostOfBracket {

    public boolean isValid(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }
        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
        }};
        Deque<Character> stack = new LinkedList<Character>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (pairs.containsKey(ch)) {
                if (stack.isEmpty() || stack.peek() != pairs.get(ch)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }


    public int scoreOfParentheses(String S) {
        Stack<Integer> stack = new Stack();
        stack.push(1); // The score of the current frame
        for (char c: S.toCharArray()) {
            if (c == '(')
                stack.push(1);
            else {
                int v = stack.pop();
                int w = stack.pop();
                stack.push(w * Math.max( v + 1, 1));
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        CostOfBracket cost = new CostOfBracket();
        String S = "()";
        if(cost.isValid(S)){
            System.out.println(cost.scoreOfParentheses(S));
        }else {
            System.out.println("invalid bracket!");
        }
    }

}
