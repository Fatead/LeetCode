package hashTable;

import java.util.HashSet;
import java.util.Set;

public class UniqueMorse {


    public int uniqueMorseRepresentations(String[] words) {
        String[] morseArray = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        Set<String> set = new HashSet<>();
        for(String word:words){
            StringBuilder stringBuilder = new StringBuilder();
            for(int i = 0;i<word.length();i++){
                stringBuilder.append(morseArray[(word.charAt(i)-'a')]);
            }
            set.add(stringBuilder.toString());
        }
        return set.size();
    }

    public static void main(String[] args) {

    }

}
