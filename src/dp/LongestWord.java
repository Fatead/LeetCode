package dp;

import java.util.Arrays;

public class LongestWord {

    private boolean beginWith(String str,String prefix){
        for(int i = 0;i<prefix.length();i++){
            if(str.charAt(i)!=prefix.charAt(i))return false;
        }
        return prefix.length() == str.length()-1;
    }

    public String longestWord(String[] words) {
        Arrays.sort(words);
        int[] dp = new int[words.length];
        for(int i = 0;i<dp.length;i++){
            if(words[i].length() == 1){
                dp[i] =1;
            }
        }
        for(int i = 0;i<words.length;i++){
            for(int j = 0;j<i;j++){
                if(beginWith(words[i],words[j]) && dp[j]!=0){
                    dp[i] = dp[j]+1;
                }
            }
        }
        int max = 0;
        int maxPos = 0;
        for(int i = 0;i<dp.length;i++){
            if(dp[i]>max){
                max = dp[i];
                maxPos = i;
            }
        }
        return words[maxPos];
    }

    public static void main(String[] args) {
        LongestWord longestWord = new LongestWord();
        String[] words = {"a", "banana", "app", "appl", "ap", "apply", "apple"};
        System.out.println(longestWord.longestWord(words));
    }

}
