package number;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Calculator {

    public int calculate(String string) {
        List<String> expression = new ArrayList<>();
        Stack<String> operatorStack = new Stack<>();
        char[] chars = string.toCharArray();
        for(int i = 0;i<chars.length;i++){
            if(chars[i] == ' '){
                continue;
            }
            if(chars[i]>='0'&&chars[i]<='9'){
                StringBuilder stringBuilder = new StringBuilder();
                while(i<chars.length&&chars[i]>='0'&&chars[i]<='9'){
                    stringBuilder.append(chars[i]);
                    i++;
                }
                i--;
                expression.add(stringBuilder.toString());
            }else if(chars[i] == '('){
                operatorStack.add(String.valueOf(chars[i]));
            }else if(chars[i] == ')'){
                while(!operatorStack.peek().equals("(")){
                    expression.add(operatorStack.pop());
                }
                operatorStack.pop();
            }else {
                if(!operatorStack.isEmpty()&&priority(operatorStack.peek()) >= priority(String.valueOf(chars[i]))){
                    expression.add(operatorStack.pop());
                }
                operatorStack.push(String.valueOf(chars[i]));
            }
        }
        while(!operatorStack.isEmpty()){
            expression.add(operatorStack.pop());
        }
        Stack<Integer> intStack = new Stack<>();
        for(String str:expression){
            if(str.equals("+")){
                int num1 = intStack.pop();
                int num2 = intStack.pop();
                intStack.push(num1 + num2);
            }else if(str.equals("-")){
                int num1 = intStack.pop();
                int num2 = intStack.pop();
                intStack.push(num2-num1);
            }else if(str.equals("*")){
                int num1 = intStack.pop();
                int num2 = intStack.pop();
                intStack.push(num2*num1);
            }else if(str.equals("/")){
                int num1 = intStack.pop();
                int num2 = intStack.pop();
                intStack.push(num2/num1);
            }else {
                intStack.push(Integer.parseInt(str));
            }
        }
        return intStack.pop();
    }

    //计算符优先级
    private static int priority(String operator){
        if(operator.equals("-")||operator.equals("+")){
            return 0;
        }else if(operator.equals("*")||operator.equals("/")){
            return 1;
        }else {
            return -1;
        }
    }


    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        int result = calculator.calculate(" 30");
        System.out.println(result);
    }

}
