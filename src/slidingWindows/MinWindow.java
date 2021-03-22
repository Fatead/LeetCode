package slidingWindows;

import java.util.HashMap;
import java.util.Map;

/**
 * 最小覆盖子串
 * 一个字符串S、一个字符串T，请在字符串S里面找出：包含T所有字母的最小子串
 * 输入：S=“ADOBECODEBANC”, T=“ABC”
 * 输出：“BANC”
 */
public class MinWindow {


    public String minWindow(String S,String T){
        Map<Character,Integer> needMap = new HashMap<>();
        Map<Character,Integer> currMap = new HashMap<>();
        int valid = 0;
        for (int i = 0; i <T.length() ; i++) {
            needMap.put(T.charAt(i),needMap.getOrDefault(T.charAt(i),0) + 1);
        }
        int left = 0;
        int right = 0;
        int minLength = Integer.MAX_VALUE;
        int start = 0;
        while (right<S.length()){
            char c = S.charAt(right);
            right++;
            if(needMap.containsKey(c)){
                currMap.put(c,currMap.getOrDefault(c,0) + 1);
                if(needMap.get(c).equals(currMap.get(c))){
                    valid++;
                }
            }
            //判断左侧窗口是否要进行收缩，如果当前已经满足最小覆盖子串的条件就要考虑收缩
            while (valid == needMap.size()){
                //d是要移除窗口的字符
                char d = S.charAt(left);
                //更新最小子串的长度和起始位置
                if(right-left<minLength){
                    minLength = right-left;
                    start = left;
                }
                left++;
                if(needMap.containsKey(d)){
                    if(needMap.get(d).equals(currMap.get(d))){
                        valid--;
                    }
                    currMap.put(d,currMap.get(d) -1);
                }
            }
        }
        return minLength == Integer.MAX_VALUE ? "":S.substring(start,start+minLength);
    }


    public static void main(String[] args) {
        MinWindow minWindow = new MinWindow();
        System.out.println(minWindow.minWindow("ADOBECODEBANC","ABC"));
    }

}
