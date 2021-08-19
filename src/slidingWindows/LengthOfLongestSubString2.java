package slidingWindows;
import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestSubString2 {

    private boolean hasDuplicate(Map<Character,Integer> map){
        for(Character c:map.keySet()){
            if(map.get(c)>1){
                return true;
            }
        }
        return false;
    }

    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        int left = 0, right = 0;
        Map<Character,Integer> map = new HashMap<>();
        while (right<s.length()){
            char c =  s.charAt(right);
            map.put(c, map.getOrDefault(c,0) + 1);
            right++;
            if(map.get(c)>1){
                //左边的窗口需要收缩
                while (hasDuplicate(map) && left<right){
                    char l = s.charAt(left);
                    map.put(l,map.get(l)-1);
                    left++;
                }
            }
            maxLength = Math.max(maxLength,right-left);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        LengthOfLongestSubString2 lengthOfLongestSubString2 = new LengthOfLongestSubString2();
        System.out.println(lengthOfLongestSubString2.lengthOfLongestSubstring("abcabcbb"));
    }

}