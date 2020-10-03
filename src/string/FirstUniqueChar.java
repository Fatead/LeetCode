package string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FirstUniqueChar {

    public int firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        Set<Character> charSet = new HashSet<>();
        Set<Character> allCharSet = new HashSet<>();
        for(int i = 0;i<chars.length-1;i++){
            allCharSet.add(chars[i]);
            if(chars[i] == chars[i+1]){
                charSet.add(chars[i]);
            }
        }
        if(s.length()-1>=0)
            allCharSet.add(chars[s.length()-1]);
        allCharSet.removeAll(charSet);
        for(int i = 0;i<chars.length;i++){
            if(allCharSet.contains(s.charAt(i))){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        FirstUniqueChar firstUniqueChar = new FirstUniqueChar();
        System.out.println(firstUniqueChar.firstUniqChar(""));
    }
}
