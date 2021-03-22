package string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UniqueChar {

    public char firstUniqChar(String s) {
        List<Character> list = new ArrayList<>();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i <s.length() ; i++) {
            if(!list.contains(s.charAt(i))){
                list.add(s.charAt(i));
            }else {
                set.add(s.charAt(i));
            }
        }
        for (int i = 0; i <list.size() ; i++) {
            if(!set.contains(list.get(i))){
                return list.get(i);
            }
        }
        return ' ';
    }

    public static void main(String[] args) {
        UniqueChar uniqueChar = new UniqueChar();
        System.out.println(uniqueChar.firstUniqChar(""));
    }
}
