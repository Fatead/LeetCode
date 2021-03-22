package slidingWindows;

import java.util.HashMap;
import java.util.Map;

/**
 * 字符串排列
 * 给定两个字符串S和T，写一个函数来判断S是否包含T的排列。
 * 输入：T=“ab”, S=“eidbaooo”
 * 输出:True
 * 因为"ba"在S中
 *
 * 滑动窗口的大小保持为T的长度
 */
public class CheckInclusion2 {

    public boolean checkInclusion(String S,String T){
        Map<Character,Integer> needMap = new HashMap<>();
        Map<Character,Integer> currMap = new HashMap<>();
        for (int i = 0; i <T.length() ; i++) {
            char c = T.charAt(i);
            needMap.put(c,needMap.getOrDefault(c,0) + 1);
        }
        int left = 0;
        int right = 0;
        int valid = 0;
        while (right<S.length()){
            //c是需要加入窗口的字符
            char c = S.charAt(right);
            right++;
            if(needMap.containsKey(c)){
                currMap.put(c,currMap.getOrDefault(c,0) + 1);
                if(needMap.get(c).equals(currMap.get(c))){
                    valid++;
                }
            }
            //d是要从窗口移除的字符
            if(right-left>=T.length()){
                if(valid == needMap.size()){
                    return true;
                }
                char d= S.charAt(left);
                left++;
                if(needMap.containsKey(d)){
                    if(currMap.get(d).equals(needMap.get(d))){
                        valid--;
                    }
                    currMap.put(d,currMap.get(d)-1);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        CheckInclusion2 checkInclusion2 = new CheckInclusion2();
        System.out.println(checkInclusion2.checkInclusion("eidbaooo","ab"));
    }

}