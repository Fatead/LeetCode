package string;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Parenthesis {

    private List<String> ParenthesisList = new ArrayList<>();

    private void generate(String result, int n, Stack<Character> characterStack){
        if(characterStack.isEmpty()&&n==0){
            ParenthesisList.add(result);
            return;
        }
        if(characterStack.isEmpty()&&n>0){
            characterStack.push('(');
            generate(result+ '(',n-1,characterStack);
        }else if(characterStack.peek()=='('&&n>0){
            Stack<Character> newStack = (Stack<Character>) characterStack.clone();
            newStack.pop();
            generate(result+')',n,newStack);
            characterStack.push('(');
            generate(result+'(',n-1,characterStack);
        }else if(characterStack.peek()=='('&&n == 0){
            characterStack.pop();
            generate(result+')',n,characterStack);
        }
    }

    public List<String> generateParenthesis(int n) {
        Stack<Character> characterStack = new Stack<>();
        characterStack.push('(');
        generate("(",n-1,characterStack);
        return ParenthesisList;
    }

    public static void main(String[] args) {
        Parenthesis parenthesis = new Parenthesis();
        List<String> resultList = parenthesis.generateParenthesis(3);
        for(String str:resultList){
            System.out.println(str);
        }
    }

}
