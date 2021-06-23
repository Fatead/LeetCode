package hashTable;
import java.util.*;

public class CommonChars {

    public List<String> commonChars(String[] words) {
        Set<Character> set = new HashSet<>();
        List<String> result = new ArrayList<>();
        if(words.length==0)return result;
        String word1 = words[0];
        for (char c : word1.toCharArray()) {
            set.add(c);
        }
        int[] minCount = new int[26];
        for (String word : words) {
            int[] count = new int[26];
            char[] chars = word.toCharArray();
            List<Character> list = new ArrayList<>();
            for (char aChar : chars) {
                list.add(aChar);
                count[aChar-'a']++;
            }
            for (int i = 0; i <count.length ; i++) {
                if(count[i]!=0 && minCount[i]!=0){
                    minCount[i] = Math.min(minCount[i],count[i]);
                }else if(count[i]!=0 && minCount[i]==0 && set.contains((char)('a' + i))){
                    minCount[i] = count[i];
                }
            }
            set.retainAll(list);
        }
        for (Character character : set) {
            for (int i = 0; i <minCount[character-'a'] ; i++) {
                result.add(String.valueOf(character));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        CommonChars commonChars = new CommonChars();
        String[] words = {"cool"};
        System.out.println(commonChars.commonChars(words));
    }

}