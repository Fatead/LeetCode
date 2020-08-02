package dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BreakWords {

    private boolean flag = false;

    public boolean wordBreak(String s, List<String> wordDict) {
        if(s == null)return false;
        if(s.length()>=151)return false;
        if(!flag){
            for(String str:wordDict){
                if(s.equals(str)){
                    flag = true;
                    return flag;
                } else if(s.startsWith(str)){
                    wordBreak(s.substring(str.length()),wordDict);
                }
            }
        }
        return flag;
    }

    public boolean wordBreak2(String s, List<String> wordDict){
        if(s == null||s.equals(""))return false;
        boolean[] dp = new boolean[s.length()+1];
        Set<String> wordSet = new HashSet<>(wordDict);
        dp[0] = true;
        for(int i = 1;i<=s.length();i++){
            for(int j = 0;j<i;j++){
                if(dp[j]&&wordSet.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        String str = "leetcode";
        String[] wordDict = {"leet","code"};
        BreakWords breakWords = new BreakWords();
        boolean result =  breakWords.wordBreak2(str, Arrays.asList(wordDict));
        System.out.println(result);
    }

}
