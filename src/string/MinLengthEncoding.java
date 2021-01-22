package string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MinLengthEncoding {

    public int minimumLengthEncoding(String[] words) {
        Set<String> set = new HashSet<>(Arrays.asList(words));
        for(String word:words){
            for (int i = 1; i <word.length() ; i++) {
                set.remove(word.substring(i));
            }
        }
        int length = 0;
        for(String word:set){
            length+= word.length()+1;
        }
        return length;
    }

    public static void main(String[] args) {
        MinLengthEncoding minLengthEncoding = new MinLengthEncoding();
        String[] words = {"time", "me", "bell"};
        System.out.println(minLengthEncoding.minimumLengthEncoding(words));
    }

}