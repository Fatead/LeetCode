package hashTable;

import java.util.HashMap;
import java.util.Map;

public class RansomMagazine {

    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character,Integer> ransomMap = new HashMap<>();
        Map<Character,Integer> magazineMap = new HashMap<>();
        for(int i = 0;i<ransomNote.length();i++){
            ransomMap.put(ransomNote.charAt(i),ransomMap.getOrDefault(ransomNote.charAt(i),0) +1);
        }
        for(int i = 0;i<magazine.length();i++){
            magazineMap.put(magazine.charAt(i),magazineMap.getOrDefault(magazine.charAt(i),0) +1);
        }
        for(Character c:ransomMap.keySet()){
            if((!magazineMap.containsKey(c))||magazineMap.get(c)<ransomMap.get(c)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        RansomMagazine ransomMagazine = new RansomMagazine();
        boolean result =  ransomMagazine.canConstruct("aa","aab");
        System.out.println(result);
    }
}
