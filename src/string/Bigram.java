package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bigram {

    public String[] findOcurrences(String text, String first, String second) {
        String[] words = text.split(" ");
        List<String> list = new ArrayList<>();
        for (int i = 2; i <words.length ; i++) {
            if(words[i-1].equals(second) && words[i-2].equals(first)){
                list.add(words[i]);
            }
        }
        String[] result = new String[list.size()];
        for (int i = 0; i <list.size() ; i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        Bigram bigram = new Bigram();
        String text = "alice is a good girl she is a good student";
        String[] result = bigram.findOcurrences(text,"a","good");
        System.out.println(Arrays.toString(result));
    }

}

