package dfs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordLadder {


    private boolean change(String inputWord,String targetWord){
        int len = inputWord.length();
        int diff = 0;
        for(int i = 0;i<len;i++){
            if(inputWord.charAt(i)!=targetWord.charAt(i)){
                diff++;
            }
        }
        return diff <= 1;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>(wordList);
        beginSet.add(beginWord);
        if(!endSet.contains(endWord))return 0;
        int count = 1;
        while(true){
            Set<String> changeSet = new HashSet<>();
            for(String string1:beginSet){
                for(String string2:endSet){
                    if(change(string1,string2)){
                        changeSet.add(string2);
                    }
                }
            }
            count++;
            beginSet = changeSet;
            endSet.removeAll(changeSet);
            if(changeSet.isEmpty()||changeSet.contains(endWord)){
                break;
            }
        }
        if(beginSet.contains(endWord))
            return count;
        return 0;
    }

}
