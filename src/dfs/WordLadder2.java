package dfs;

import java.util.*;

public class WordLadder2 {

    List<List<String>> pathList = new ArrayList<>();

    private boolean change(String inputWord,String targetWord){
        int len = inputWord.length();
        int diff = 0;
        for(int i = 0;i<len;i++){
            if(inputWord.charAt(i)!=targetWord.charAt(i)){
                diff++;
                if(diff>1)return false;
            }
        }
        return true;
    }

    private void addNode(String word1,String word2){
        if(word1.equals(word2))return;
        List<List<String>> convertList = new ArrayList<>();
        for(List<String> list:pathList){
            if(list.get(list.size()-1).equals(word1)){
                List<String> newList = new ArrayList<>(list);
                newList.add(word2);
                convertList.add(newList);
            }
        }
        pathList.addAll(convertList);
    }

    private void removePath(int count){
        Iterator<List<String>> it = pathList.iterator();
        for(int i = 0;i<pathList.size();i++){
            List<String> list = it.next();
            if(list.size()<count){
                it.remove();
                i--;
            }
        }
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>(wordList);
        beginSet.add(beginWord);
        List<String> list1 = new ArrayList<>();
        if(!endSet.contains(endWord))return pathList;
        list1.add(beginWord);
        pathList.add(list1);
        int count = 1;
        while(true){
            Set<String> changeSet = new HashSet<>();
            for(String string1:beginSet){
                for(String string2:endSet){
                    if(change(string1,string2)){
                        changeSet.add(string2);
                        addNode(string1,string2);
                    }
                }
            }
            count++;
            beginSet = changeSet;
            endSet.removeAll(changeSet);
            removePath(count);
            if(changeSet.isEmpty()||changeSet.contains(endWord)){
                break;
            }
        }
        if(beginSet.contains(endWord)){
            List<List<String>> result = new ArrayList<>();
            for(List<String> list:pathList){
                if(list.size() == count&&list.get(count-1).equals(endWord)){
                    result.add(list);
                }
            }
            return result;
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        String[] wordArray = {"hot","dot","dog","lot","log","cog"};
        List<String> wordList = Arrays.asList(wordArray);
        WordLadder2 wordLadder2 = new WordLadder2();
        System.out.println(wordLadder2.findLadders(beginWord,endWord,wordList));
    }
}
