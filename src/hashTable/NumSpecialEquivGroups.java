package hashTable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class NumSpecialEquivGroups {

    private String sortString(String string){
        char[] arrayOdd = new char[string.length()/2 + 1];
        char[] arrayEven = new char[string.length()/2 + 1];
        int pos = 0;
        for (int i = 0; i <string.length() ; i+=2) {
            arrayOdd[pos++] = string.charAt(i);
        }
        Arrays.sort(arrayOdd);
        pos = 0;
        for (int i = 1; i <string.length() ; i+=2) {
            arrayEven[pos++] = string.charAt(i);
        }
        Arrays.sort(arrayEven);
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : arrayOdd) {
            stringBuilder.append(c);
        }
        for (char c : arrayEven) {
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }


    public int numSpecialEquivGroups(String[] words) {
        Set<String> set = new HashSet<>();
        for (String word : words) {
            set.add(sortString(word));
        }
        return set.size();
    }

    public static void main(String[] args) {
        NumSpecialEquivGroups numSpecialEquivGroups = new NumSpecialEquivGroups();
        String[] words = {"abc","acb","bac","bca","cab","cba"};
        System.out.println(numSpecialEquivGroups.numSpecialEquivGroups(words));
    }

}
