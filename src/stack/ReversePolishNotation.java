package stack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class ReversePolishNotation {

    private int StrToInt(String str){
        char[] chars = str.toCharArray();
        int result = 0;
        if(chars[0]!='-'){
            for(char c:chars){
                result = result*10 + (c-'0');
            }
            return result;
        }else {
            for(int i =1;i<chars.length;i++){
                result = result*10 + (chars[i]-'0');
            }
            result = -result;
        }
        return result;
    }

    public int evalRPN(String[] tokens) {
        if(tokens.length == 0)return 0;
        Stack<Integer> integerStack = new Stack<>();
        String[] operations = {"+","-","*","/"};
        Set<String> operateSet = new HashSet<>(Arrays.asList(operations));
        for(String str:tokens){
            if(!operateSet.contains(str)){
                int num = StrToInt(str);
                integerStack.push(num);
            }else {
                int num1 = integerStack.pop();
                int num2 = integerStack.pop();
                int newNum = 0;
                switch (str){
                    case "+":
                        newNum = num1 + num2;
                        break;
                    case "-":
                        newNum = num2 - num1;
                        break;
                    case "*":
                        newNum = num1*num2;
                        break;
                    case "/":
                        newNum = num2/num1;
                        break;
                }
                integerStack.push(newNum);
            }
        }
        return integerStack.pop();
    }


    public static void main(String[] args) {
        ReversePolishNotation reversePolishNotation = new ReversePolishNotation();
        String[] tokens = {"3","-4","+"};
        System.out.println(reversePolishNotation.evalRPN(tokens));
    }
}
