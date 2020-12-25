package hashTable;

import java.util.*;

public class TopKWords {

    public List<String> topKFrequent(String[] words, int k) {
        Map<String,Integer> map = new HashMap<>();
        for(String word:words){
            map.put(word,map.getOrDefault(word,0) + 1);
        }
        List<Map.Entry<String,Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((stringIntegerEntry, t1) -> {
            if (stringIntegerEntry.getValue() > t1.getValue()) return -1;
            else if (stringIntegerEntry.getValue() < t1.getValue()) return 1;
            else{
                return stringIntegerEntry.getKey().compareTo(t1.getKey());
            }
        });
        List<String> result = new ArrayList<>();
        for(int i = 0;i<k;i++){
            result.add(list.get(i).getKey());
        }
        return result;
    }

    public static void main(String[] args) {
        TopKWords topKWords = new TopKWords();
        String[] words = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        List<String> result = topKWords.topKFrequent(words,4);
        System.out.println(result);
    }

}
