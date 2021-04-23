package string;

import java.util.ArrayList;
import java.util.List;

public class ReverseOnlyLetters {

    public String reverseOnlyLetters(String S) {
        List<Character> reverseList = new ArrayList<>();
        for (int i = S.length()-1; i >=0 ; i--) {
            if(Character.isLetter(S.charAt(i))){
                reverseList.add(S.charAt(i));
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        for (int i = 0; i <S.length() ; i++) {
            if(Character.isLetter(S.charAt(i))){
                stringBuilder.append(reverseList.get(count++));
            }else {
                stringBuilder.append(S.charAt(i));
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        ReverseOnlyLetters reverseOnlyLetters = new ReverseOnlyLetters();
        System.out.println(reverseOnlyLetters.reverseOnlyLetters(""));
    }

}
