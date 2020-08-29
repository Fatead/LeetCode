package number;

import java.util.Stack;

public class Calculator2 {

    public int calculate(String s) {
        char[] chars = s.toCharArray();
        int result = 0;
        Stack<Character> operatorStack = new Stack<>();
        Stack<Integer> intStack = new Stack<>();
        int currNum = 0;
        for(char c:chars){
            if(c>='0'&&c<='9'){
                currNum = currNum*10 + (c - '0');
            }else if(c == '+'||c == '-'||c =='*'||c =='/'){
                if(!operatorStack.isEmpty()){
                    if(operatorStack.peek() == '*'){
                        currNum = currNum*intStack.pop();
                        operatorStack.pop();
                    }else if(operatorStack.peek() == '/'){
                        currNum = intStack.pop()/currNum;
                        operatorStack.pop();
                    }
                }
                operatorStack.add(c);
                intStack.add(currNum);
                currNum = 0;
            }
        }
        intStack.add(currNum);
        while(!operatorStack.isEmpty()&&(operatorStack.peek() == '*'||operatorStack.peek() == '/')){
            int num1 = intStack.pop();
            int num2 = intStack.pop();
            if(operatorStack.peek() == '*'){
                result = num1*num2;
                intStack.push(result);
            }else if(operatorStack.peek() == '/'){
                result = num2/num1;
                intStack.push(result);
            }
            operatorStack.pop();
        }
        Stack<Integer> newIntStack = new Stack<>();
        Stack<Character> newOperaStack = new Stack<>();
        while(!intStack.isEmpty()){
            newIntStack.add(intStack.pop());
        }
        while(!operatorStack.isEmpty()){
            newOperaStack.add(operatorStack.pop());
        }
        while(!newOperaStack.isEmpty()){
            int num1 = newIntStack.pop();
            int num2 = newIntStack.pop();
            if(newOperaStack.peek() == '+'){
                result = num1 + num2;
                newIntStack.push(result);
            } else if(newOperaStack.peek() == '-'){
                result = num1 - num2;
                newIntStack.push(result);
            }
            newOperaStack.pop();
        }
        return newIntStack.pop();
    }

    public static void main(String[] args) {
        Calculator2 calculator2 = new Calculator2();
        int result = calculator2.calculate(" 14/3*2/4 ");
        System.out.println(result);
    }
}
