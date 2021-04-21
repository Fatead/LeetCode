package hashTable;

import java.util.*;

public class WordSubSet {

    public List<String> wordSubsets(String[] A, String[] B) {
        int[] count = new int[26];
        for (String s : B) {
            Map<Character,Integer> map = new HashMap<>();
            for (int i = 0; i <s.length() ; i++) {
                map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0) + 1);
            }
            for(Character c:map.keySet()){
                count[c-'a'] = Math.max(count[c-'a'],map.get(c));
            }
        }
        List<String> result = new ArrayList<>();
        for (String s : A) {
            Map<Character,Integer> mapA = new HashMap<>();
            for (int i = 0; i <s.length() ; i++) {
                mapA.put(s.charAt(i),mapA.getOrDefault(s.charAt(i),0) + 1);
            }
            boolean flag = true;
            for (int i = 0; i <count.length ; i++) {
                if(count[i]>mapA.getOrDefault((char)('a' + i),0)){
                    flag = false;
                    break;
                }
            }
            if(flag){
                result.add(s);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        WordSubSet wordSubSet = new WordSubSet();
        String[] A = {"amazon","apple","facebook","google","leetcode"};
        String[] B = {};
        System.out.println(wordSubSet.wordSubsets(A,B));
    }

}
