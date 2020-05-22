package dp;

public class LongestSubString {


    public boolean palindrome(String s){
        char[] chars = s.toCharArray();
        for(int i = 0;i<(chars.length+1)/2;i++){
            if(chars[i]!=chars[chars.length-i-1])return false;
        }
        return true;
    }


    //暴力方法,时间超时
    public String longestPalindromeTest(String s) {
        int length = s.length();
        if(length == 0)return null;
        for(int i = length;i>1;i--){
            for(int j = 0;i+j<=length;j++){
                if(palindrome(s.substring(j,j+i)))return s.substring(j,j+i);
            }
        }
        return s.substring(0,1);
    }

    public String longestPalindrome(String s){
        int length = s.length();
        if(length<2)return s;
        char[] chars = s.toCharArray();
        int maxLen = 1;
        int begin = 0;
        Boolean[][] array = new Boolean[length][length];
        for(int i = 0;i<length;i++){
            array[i][i] = true;
        }
        for(int j = 1;j<length;j++){
            for(int i = 0;i<j;i++){
                if(chars[i]!=chars[j])array[i][j] = false;
                else if(j-i<3)array[i][j] = true;
                else array[i][j] = array[i+1][j-1];

                if(array[i][j]&&j-i+1>maxLen){
                    maxLen = j-i+1;
                    begin = i;
                }
            }
        }
        return s.substring(begin,begin+maxLen);
    }


    public static void main(String[] args) {
        String s = "babad";
        LongestSubString longestSubString = new LongestSubString();
        System.out.println(longestSubString.longestPalindrome(s));
    }
}
