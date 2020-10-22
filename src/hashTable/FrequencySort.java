package hashTable;

import java.util.*;

public class FrequencySort {

    public String frequencySort(String s) {
        Map<Character,Integer> charMap = new HashMap<>();
        for(int i = 0;i<s.length();i++){
            charMap.put(s.charAt(i), charMap.getOrDefault(s.charAt(i),0) + 1);
        }
        List<Integer> countList = new ArrayList<>();
        List<Character> charList = new ArrayList<>();
        for(Character c:charMap.keySet()){
            charList.add(c);
            countList.add(charMap.get(c));
        }
        for(int i = 0;i<charList.size();i++){
            for(int j = 0;j<charList.size()-i-1;j++){
                if(countList.get(j)<countList.get(j+1)){
                    int temp = countList.get(j);
                    countList.set(j,countList.get(j+1));
                    countList.set(j+1,temp);
                    char tempC = charList.get(j);
                    charList.set(j,charList.get(j+1));
                    charList.set(j+1,tempC);
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0;i<charList.size();i++){
            for(int j = 0;j<countList.get(i);j++){
                stringBuilder.append(charList.get(i));
            }
        }
        return stringBuilder.toString();
    }


    private String freqSort(String s){
        Map<Character, Integer> map = new HashMap<>();
        for (char chr : s.toCharArray()) {
            map.put(chr, map.getOrDefault(chr, 0) + 1);
        }
        PriorityQueue<Map.Entry<Character,Integer>> maxHeap = new PriorityQueue<>((e1,e2) -> e2.getValue() - e1.getValue());
        maxHeap.addAll(map.entrySet());
        StringBuilder stringBuilder = new StringBuilder();
        while(!maxHeap.isEmpty()){
            Map.Entry<Character,Integer> entry = maxHeap.poll();
            for(int i = 0;i<entry.getValue();i++){
                stringBuilder.append(entry.getKey());
            }
        }
        return stringBuilder.toString();
    }

    public class MyComparator implements Comparator<Map.Entry<Character,Integer>>{

        @Override
        public int compare(Map.Entry<Character, Integer> characterIntegerEntry, Map.Entry<Character, Integer> t1) {
            return t1.getValue() - characterIntegerEntry.getValue();
        }
    }

//    private String freqSort2(String s){
//        Map<Character, Integer> map = new HashMap<>();
//        for (char chr : s.toCharArray()) {
//            map.put(chr, map.getOrDefault(chr, 0) + 1);
//        }
//        List<Map.Entry<Character,Integer>> list = new ArrayList<>();
//        list.addAll(map.entrySet());
//        MyComparator myComparator = new MyComparator();
//        list.sort(myComparator);
//        StringBuilder stringBuilder = new StringBuilder();
//        for(Map.Entry<Character,Integer> entry:list){
//
//        }
//    }

    public static void main(String[] args) {
        FrequencySort frequencySort = new FrequencySort();
        System.out.println(frequencySort.frequencySort(""));
    }

}
