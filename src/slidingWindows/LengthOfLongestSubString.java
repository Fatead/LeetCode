package slidingWindows;

import tree.MaxAncestorDiff;

import java.util.HashSet;
import java.util.Set;

public class LengthOfLongestSubString {

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

    int maxLength = 0;

    public int lengthOfLongestSubstring(String s) {
        int left = 0, right = 0;
        int maxLength = 0;
        while (right<s.length()){
            while (!containsDuplicateChar(s.substring(left,right)) && right<s.length()){
                maxLength = Math.max(right-left,maxLength);
                right++;
            }
            while (containsDuplicateChar(s.substring(left,right)) && left<right){
                left++;
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        LengthOfLongestSubString length = new LengthOfLongestSubString();
        System.out.println(length.lengthOfLongestSubstring("abcabcbb"));
    }

}