package string;

import java.util.ArrayList;
import java.util.List;

public class StringJust {

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<List<String>> strLists = new ArrayList<>();
        List<String> stringList = new ArrayList<>();
        List<String> resultList = new ArrayList<>();
        int countLength = 0;
        for(String str:words){
            if(countLength + str.length() > maxWidth&&countLength!=0){
                strLists.add(new ArrayList<>(stringList));
                stringList.clear();
                stringList.add(str);
                countLength = str.length() + 1;
            }else{
                stringList.add(str);
                countLength =countLength + str.length() + 1;
            }
        }
        strLists.add(stringList);
        for(int i = 0;i<strLists.size()-1;i++){
            StringBuilder stringBuilder = new StringBuilder();
            int length = 0;
            int wordCounts = 0;
            for(String str:strLists.get(i)){
                length +=str.length();
                wordCounts++;
            }
            int blankLength = 0,remain = 0;
            if(wordCounts>1){
                blankLength = (maxWidth - length)/(wordCounts-1);
                remain = (maxWidth - length)%(wordCounts-1);
            }
            StringBuilder blankStringBuilder = new StringBuilder();
            for(int j = 0;j<blankLength;j++){
                blankStringBuilder.append(" ");
            }
            for(String str:strLists.get(i)){
                stringBuilder.append(str).append(blankStringBuilder);
                if(remain>0){
                    stringBuilder.append(" ");
                    remain--;
                }
            }
            String resultString;
            if(stringBuilder.length()>maxWidth){
                resultString = stringBuilder.toString().trim();
            }else if(stringBuilder.length()<maxWidth){
                for(int j = stringBuilder.length();j<maxWidth;j++){
                    stringBuilder.append(" ");
                }
                resultString = stringBuilder.toString();
            }else {
                resultString = stringBuilder.toString();
            }
            resultList.add(resultString);
        }
        List<String> stringList1 = strLists.get(strLists.size()-1);
        StringBuilder stringBuilder = new StringBuilder();
        for(String str:stringList1){
            stringBuilder.append(str).append(" ");
        }
        for(int i = stringBuilder.length();i<maxWidth;i++){
            stringBuilder.append(" ");
        }
        if(stringBuilder.length() == maxWidth)
            resultList.add(stringBuilder.toString());
        else resultList.add(stringBuilder.toString().trim());
        return resultList;
    }

    public static void main(String[] args) {
        String[] words = {
                "ac","b","cd","d","e"
               //"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"
        };
        StringJust stringJust = new StringJust();
        List<String> result = stringJust.fullJustify(words,2);
        for(String str:result){
            System.out.println(str);
            System.out.println(str.length());
        }
    }
}
