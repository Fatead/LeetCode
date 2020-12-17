package hashTable;

import java.util.HashMap;
import java.util.Map;

public class JudgeCircle {

    public boolean judgeCircle(String moves) {
        Map<Character,Integer> charMap = new HashMap<>();
        for(int i = 0;i<moves.length();i++){
            charMap.put(moves.charAt(i),charMap.getOrDefault(moves.charAt(i),0) + 1);
        }
        return charMap.getOrDefault('U', 0).equals(charMap.getOrDefault('D', 0)) && charMap.getOrDefault('L', 0).equals(charMap.getOrDefault('R', 0));
    }

}
