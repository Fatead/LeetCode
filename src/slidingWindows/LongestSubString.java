package slidingWindows;

import java.util.HashSet;
import java.util.Set;

public class LongestSubString {

    private boolean containsDuplicateChar(String s){
        Set<Character> set = new HashSet<>();
        for (int i = 0; i <s.length() ; i++) {
            if(set.contains(s.charAt(i))){
                return true;
            }else {
                set.add(s.charAt(i));
            }
        }
        return false;
    }

    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int right = 0;
        int maxLength = 0;
        while (right<s.length()){
            while ( right<s.length() && !containsDuplicateChar(s.substring(left,right))){
                maxLength = Math.max(maxLength,right-left);
                right++;
            }
            while (containsDuplicateChar(s.substring(left,right))){
                left++;
            }
        }
        maxLength = Math.max(maxLength,right-left);
        return maxLength;
    }

    public static void main(String[] args) {
        LongestSubString longestSubString = new LongestSubString();
        System.out.println(longestSubString.lengthOfLongestSubstring("ugubvj"));
    }

}
