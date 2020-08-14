package string;

import java.util.Stack;

public class ReverseWords {

    public String reverseWords(String s) {
        s = s.trim();
        Stack<String> stringStack = new Stack<>();
        char[] chars = s.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for(char c:chars){
            if(c!=' '){
                stringBuilder.append(c);
            }else if(stringBuilder.length()!=0){
                stringStack.push(stringBuilder.toString());
                stringBuilder.delete(0,stringBuilder.length());
            }
        }
        stringStack.push(stringBuilder.toString());
        StringBuilder result = new StringBuilder();
        while(!stringStack.isEmpty()){
            result.append(stringStack.pop());
            result.append(' ');
        }
        return result.toString().trim();
    }

    public static void main(String[] args) {
        String str = "";
    }


}
