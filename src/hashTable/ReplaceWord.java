package hashTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ReplaceWord {


    private boolean beginWith(String word,String root){
        if(word.length()<root.length())return false;
        for(int i = 0;i<root.length();i++){
            if(word.charAt(i)!=root.charAt(i))return false;
        }
        return true;
    }


    public String replaceWords(List<String> dictionary, String sentence) {
        dictionary.sort((s, t1) -> {
            if (s.length() < t1.length()) return -1;
            else if (s.length() > t1.length()) return 1;
            else return Character.compare(s.charAt(0), t1.charAt(0));
        });
        System.out.println(dictionary);
        String[] words = sentence.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for(String word:words){
            boolean flag = false;
            for(String root:dictionary){
                if(beginWith(word,root)){
                    stringBuilder.append(root).append(" ");
                    flag = true;
                    break;
                }
            }
            if(!flag){
                stringBuilder.append(word).append(" ");
            }
        }
        return stringBuilder.toString().trim();
    }

    public static void main(String[] args) {
        List<String> dictionary = new ArrayList<>();
        dictionary.add("ac");
        dictionary.add("ab");
        String sentence = "it is abnormal that this solution is accepted";
        ReplaceWord replaceWord = new ReplaceWord();
        System.out.println(replaceWord.replaceWords(dictionary,sentence));
    }
}
