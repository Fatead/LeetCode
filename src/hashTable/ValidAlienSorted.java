package hashTable;

import java.util.HashMap;

public class ValidAlienSorted {

    HashMap<Character,Integer> map = new HashMap<>();

    public boolean valid(String word1,String word2){
        int minSize = Math.min(word1.length(),word2.length());
        for (int i = 0; i <minSize ; i++) {
            if(map.get(word1.charAt(i))>map.get(word2.charAt(i))){
                return false;
            }else if(map.get(word1.charAt(i))<map.get(word2.charAt(i))){
                return true;
            }
        }
        return word1.length()<=word2.length();
    }

    public boolean isAlienSorted(String[] words, String order) {
        for (int i = 0; i <order.length() ; i++) {
            map.put(order.charAt(i),i);
        }
        if(words.length<=1)return true;
        for (int i = 0; i <words.length-1 ; i++) {
            if(!valid(words[i],words[i+1])){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ValidAlienSorted validAlienSorted = new ValidAlienSorted();
        String[] words = {"hello","leetcode"};
        String order = "hlabcdefgijkmnopqrstuvwxyz";
        System.out.println(validAlienSorted.isAlienSorted(words,order));
    }

}
