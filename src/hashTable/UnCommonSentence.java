package hashTable;

import java.util.*;

public class UnCommonSentence {

    public String[] uncommonFromSentences(String A, String B) {
        String[] wordsA = A.split(" ");
        String[] wordsB = B.split(" ");
        List<String> resultList = new ArrayList<>();
        Map<String,Integer> mapA = new HashMap<>();
        Map<String,Integer> mapB = new HashMap<>();
        for (String s : wordsA) {
            mapA.put(s,mapA.getOrDefault(s,0) + 1);
        }
        for (String s : wordsB) {
            mapB.put(s,mapB.getOrDefault(s,0) + 1);
        }
        for(String word:mapA.keySet()){
            if(mapA.get(word) == 1 && !mapB.containsKey(word)){
                resultList.add(word);
            }
        }
        for(String word:mapB.keySet()){
            if(mapB.get(word) == 1 && !mapA.containsKey(word)){
                resultList.add(word);
            }
        }
        String[] result = new String[resultList.size()];
        for (int i = 0; i <resultList.size() ; i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        UnCommonSentence unCommonSentence = new UnCommonSentence();
        System.out.println(Arrays.toString(unCommonSentence.uncommonFromSentences("apple apple", "")));
    }

}
