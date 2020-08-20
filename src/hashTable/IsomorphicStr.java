package hashTable;

import java.util.*;

public class IsomorphicStr {

    public boolean isIsomorphic(String s, String t) {
        if(s.length()!=t.length())return false;
        char[] charsS = s.toCharArray();
        char[] charsT = t.toCharArray();
        Map<Character,Character> characterMap = new HashMap<>();
        for(int i = 0;i<s.length();i++){
            if(characterMap.containsKey(charsS[i])){
                if(charsT[i] != characterMap.get(charsS[i])){
                    return false;
                }
            }else {
                characterMap.put(charsS[i],charsT[i]);
            }
        }
        List<Character> characterList = new ArrayList<>(characterMap.values());
        Set<Character> characterSet = new HashSet<>(characterMap.values());
        return characterList.size() == characterSet.size();
    }

    public static void main(String[] args) {
        String s = "aba";
        String t = "baa";
        IsomorphicStr isomorphicStr = new IsomorphicStr();
        boolean result = isomorphicStr.isIsomorphic(s,t);
        System.out.println(result);
    }

}
