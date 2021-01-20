package hashTable;

import java.util.*;

public class MostCommonWord {

    private List<String> getWords(String string){
        List<String> words = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <string.length() ; i++) {
            if(Character.isLetter(string.charAt(i))){
                stringBuilder.append(Character.toLowerCase(string.charAt(i)));
            }else if(stringBuilder.length()>0){
                words.add(stringBuilder.toString());
                stringBuilder = new StringBuilder();
            }
        }
        if(stringBuilder.length()>0){
            words.add(stringBuilder.toString());
        }
        return words;
    }


    public String mostCommonWord(String paragraph, String[] banned) {
        List<String> words = getWords(paragraph);
        Map<String,Integer> wordMap = new HashMap<>();
        Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));
        for(String word:words){
            if(!bannedSet.contains(word))
                wordMap.put(word,wordMap.getOrDefault(word,0) + 1);
        }
        int maxCount = 0;
        for(int i:wordMap.values()){
            maxCount = Math.max(maxCount,i);
        }
        for(String word:wordMap.keySet()){
            if(wordMap.get(word) == maxCount){
                return word;
            }
        }
        return "";
    }

    public static void main(String[] args) {
        MostCommonWord mostCommonWord = new MostCommonWord();
        String paragraph = "a, a, a, a, b,b,b,c, c";
        String[] banned = {"a"};
        System.out.println(mostCommonWord.mostCommonWord(paragraph,banned));
    }

}
