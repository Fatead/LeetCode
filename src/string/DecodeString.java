package string;

import javax.xml.stream.events.Characters;
import java.util.Stack;

public class DecodeString {

    public String decodeString(String s) {
        if(s == null||s.equals("")){
            return "";
        }
        StringBuilder result = new StringBuilder();
        Stack<Integer> countStack = new Stack<>();
        Stack<String> strStack = new Stack<>();
        int count = 0;
        StringBuilder tempStr = new StringBuilder();
        for(int i = 0;i<s.length();i++){
            if(s.charAt(i)>='0'&&s.charAt(i)<='9'){
                count = count*10 + (s.charAt(i) - '0');
            }else if(s.charAt(i) == '['){
                if(tempStr.length()!=0){
                    strStack.add(tempStr.toString());
                    tempStr = new StringBuilder();
                }
                countStack.add(count);
                count = 0;
            }else if(s.charAt(i) == ']'){
                StringBuilder str = new StringBuilder();
                int time = countStack.pop();
                for(int j = 0;j<time;j++){
                    str.append(tempStr);
                }
                tempStr = new StringBuilder();
                if(strStack.isEmpty()){
                    result.append(str);
                }else {
                    tempStr.append(strStack.pop());
                    tempStr.append(str);
                }
            }else {
                tempStr.append(s.charAt(i));
            }
        }
        if(s.charAt(s.length()-1)!=']')
            result.append(tempStr);
        return result.toString();
    }

    public static void main(String[] args) {
        DecodeString decodeString = new DecodeString();
        System.out.println(decodeString.decodeString("10[leetcode]"));
    }
}
