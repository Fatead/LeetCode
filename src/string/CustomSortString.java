package string;

import java.util.*;

public class CustomSortString {

    public String customSortString(String S, String T) {
        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0; i <S.length() ; i++) {
            map.put(S.charAt(i),i);
        }
        char[] chars = T.toCharArray();
        Character[] characters = new Character[chars.length];
        int count = 0;
        for(char c:chars){
            characters[count++] = c;
        }
        Arrays.sort(characters, Comparator.comparingInt(character -> map.getOrDefault(character, 0)));
        StringBuilder stringBuilder = new StringBuilder();
        for(Character c:characters){
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        CustomSortString customSortString = new CustomSortString();
        System.out.println(customSortString.customSortString("cba","abcd"));
    }

}
