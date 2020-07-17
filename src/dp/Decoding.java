package dp;

import java.util.Arrays;

public class Decoding {

    public int numDecodings(String s) {
        if(s == null)return 0;
        if(s.length() == 0||s.startsWith("0"))return 0;
        if(s.length() == 1)return 1;
        char[] chars = s.toCharArray();
        int[] dp = new int[s.length()];
        dp[0] = 1;
        int n  =  (chars[0] - '0')*10 + (chars[1] - '0');
        if(n>26&&chars[1] == '0')return 0;
        if(n>26||chars[1] == '0')dp[1] = 1;
        else dp[1] = 2;
        for(int i = 2;i<s.length();i++){
            int num = (chars[i-1] - '0')*10 + (chars[i] - '0');
            if(num == 0||(num>26&&chars[i] == '0'))return 0;
            if((chars[i] == '0'&& num<=26)||num<10)dp[i] = dp[i-2];
            else if(num > 26 || chars[i] == '0')dp[i] = dp[i-1];
            else dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[s.length()-1];
    }

    public static void main(String[] args) {
        Decoding decoding = new Decoding();
        String s = "230";
        System.out.println(decoding.numDecodings(s));
    }
}
