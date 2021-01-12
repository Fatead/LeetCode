package DFS;

import java.util.ArrayList;
import java.util.List;

public class LetterCasePermutation {

    List<String> result = new ArrayList<>();

    private void dfs(String S,StringBuilder string,int pos,boolean smallCase){
        if(pos==S.length()){
            if(!result.contains(string.toString()))
                result.add(string.toString());
            return;
        }
        if(Character.isDigit(S.charAt(pos))){
            string.append(S.charAt(pos));
        }else if(smallCase){
            string.append(Character.toLowerCase(S.charAt(pos)));
        }else {
            string.append(Character.toUpperCase(S.charAt(pos)));
        }
        if(pos<S.length()-1 && Character.isDigit(S.charAt(pos+1))){
            dfs(S,new StringBuilder(string),pos+1,true);
        }else {
            dfs(S,new StringBuilder(string),pos+1,true);
            dfs(S,new StringBuilder(string),pos+1,false);
        }
    }

    public List<String> letterCasePermutation(String S) {
        if(S.length()<1)return result;
        if(Character.isDigit(S.charAt(0))){
            dfs(S,new StringBuilder(),0,true);
        }else {
            dfs(S,new StringBuilder(),0,true);
            dfs(S,new StringBuilder(),0,false);
        }
        return result;
    }


    public static void main(String[] args) {
        LetterCasePermutation letterCasePermutation = new LetterCasePermutation();
        System.out.println(letterCasePermutation.letterCasePermutation("a1b2"));
    }

}
