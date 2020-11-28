package array;

import java.util.*;

public class LongestWords {

    private boolean containStr(String s,String str){
        int j = 0;
        for(int i = 0;i<s.length();i++){
            if(s.charAt(i) == str.charAt(j)){
                j++;
            }
            if(j >= str.length()-1)return true;
        }
        return false;
    }

    public String findLongestWord(String s, List<String> d) {
        Map<String,Boolean> map = new HashMap<>();
        for(String str:d){
            if(containStr(s,str)){
                map.put(str,true);
            }
        }
        int longest = 0;
        for(String str:map.keySet()){
            if(str.length()>longest){
                longest = str.length();
            }
        }
        List<String> resultList = new ArrayList<>();
        for(String str:map.keySet()){
            if(str.length() == longest){
                resultList.add(str);
            }
        }
        if(resultList.size() == 0)return "";
        Collections.sort(resultList);
        System.out.println(resultList);
        return resultList.get(0);
    }


    public static void main(String[] args) {
        LongestWords longestWords = new LongestWords();
        List<String> list = new ArrayList<>();
        list.add("ale");
        list.add("apple");
        System.out.println(longestWords.findLongestWord("abpcplea",list));
    }

}
