package dp;
import java.util.Arrays;
import java.util.Comparator;

public class LongestStrChain {

    public boolean isExtend(String word1,String word2){
        if(word1.length() + 1!=word2.length())return false;
        int count = 0;
        int index1 = 0,index2 = 0;
        while (index1<word1.length() && index2<word2.length()){
            if(word1.charAt(index1) == word2.charAt(index2)){
                index1++;
                index2++;
            }else {
                index2++;
                count++;
            }
        }
        if(index2<word2.length())count++;
        return count==1;
    }


    public int longestStrChain(String[] words) {
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                return s.length() - t1.length();
            }
        });
        int[] dp = new int[words.length];
        Arrays.fill(dp,1);
        for (int i = 1; i <words.length ; i++) {
            for (int j = 0; j <i ; j++) {
                if(isExtend(words[j],words[i])){
                    dp[i] = Math.max(dp[i],dp[j] + 1);
                }
            }
        }
        int max = 0;
        for (int i : dp) {
            max = Math.max(i,max);
        }
        return max;
    }

    public static void main(String[] args) {
        LongestStrChain longestStrChain = new LongestStrChain();
        String[] words = {"xbc","pcxbcf","xb","cxbc","pcxbc"};
        System.out.println(longestStrChain.longestStrChain(words));
    }

}