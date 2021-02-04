package hashTable;

import java.util.*;

public class ShortestCompletingWord {


    private boolean isCompletingWord(String word,Map<Character,Integer> map){
        Map<Character,Integer> wordMap = new HashMap<>();
        for (int i = 0; i <word.length() ; i++) {
            char c = Character.toLowerCase(word.charAt(i));
            wordMap.put(c,wordMap.getOrDefault(c,0)+1);
        }
        for(Character c:map.keySet()){
            if(wordMap.getOrDefault(c,0)<map.get(c)){
                return false;
            }
        }
        return true;
    }

    public String shortestCompletingWord(String licensePlate, String[] words) {
        List<String> wordList = new ArrayList<>();
        Map<Character,Integer> countMap = new HashMap<>();
        for(int i = 0; i <licensePlate.length() ; i++) {
            if(Character.isLetter(licensePlate.charAt(i))){
                char c = Character.toLowerCase(licensePlate.charAt(i));
                countMap.put(c,countMap.getOrDefault(c,0)+1);
            }
        }
        for(String word:words){
            if(isCompletingWord(word,countMap)){
                wordList.add(word);
            }
        }
        wordList.sort(new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                return -Integer.compare(t1.length(), s.length());
            }
        });
        return wordList.get(0);
    }

    public static void main(String[] args) {
        ShortestCompletingWord shortestCompletingWord = new ShortestCompletingWord();
        String[] words = {"claim","consumer","student","camera","public","never","wonder","simple","thought","use"};
        System.out.println(shortestCompletingWord.shortestCompletingWord("iMSlpe4",words));
    }

}
