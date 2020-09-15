package hashTable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordPattern {

    public boolean wordPattern(String pattern, String str) {
        char[] chars = pattern.toCharArray();
        String[] words = str.split(" ");
        if(chars.length!=words.length)return false;
        Map<Character,String> wordMap = new HashMap<>();
        for(int i = 0;i<words.length;i++){
            if(!wordMap.containsKey(chars[i]) && !wordMap.containsValue(words[i])){
                wordMap.put(chars[i],words[i]);
            }else {
                if(!words[i].equals(wordMap.get(chars[i]))){
                    return false;
                }
            }
        }
        Set<String> valueSet = new HashSet<>();
        for(String value:wordMap.values()){
            if(valueSet.contains(value))return false;
            else valueSet.add(value);
        }
        return true;
    }

    public static void main(String[] args) {
        WordPattern wordPattern = new WordPattern();
        String pattern = "abba";
        String str = "dog cat cat dog";
        System.out.println(wordPattern.wordPattern(pattern,str));

    }
}
