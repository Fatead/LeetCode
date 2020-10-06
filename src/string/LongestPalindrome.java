package string;

import java.util.HashMap;
import java.util.Map;

public class LongestPalindrome {


    public int longestPalindrome(String s) {
        Map<Character,Integer> map = new HashMap<>();
        for(int i = 0;i<s.length();i++){
            map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0) + 1);
        }
        int result = 0;
        int max = 0;
        boolean add = false;
        for(int i:map.values()){
            if(i%2 == 0)result += i;
            else {
                if(i>max){
                    max = i;
                }
                add = true;
                result += i-1;
            }
        }
        if(add)result +=1;
        return result;
    }


    public static void main(String[] args) {
        LongestPalindrome longestPalindrome = new LongestPalindrome();
        System.out.println(longestPalindrome.longestPalindrome("aaffcccbbb"));
    }

}
