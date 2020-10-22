package slidingWindows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 滑动窗口
 */
public class FindAnagrams {


    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if(p.length()>s.length())return result;
        Map<Character,Integer> need = new HashMap<>();
        Map<Character,Integer> window = new HashMap<>();
        for(int i = 0;i<p.length();i++){
            need.put(p.charAt(i),need.getOrDefault(p.charAt(i),0)+1);
        }
        int left = 0,right = 0;
        int valid = 0;
        while(right < s.length()){
            char c = s.charAt(right);
            right++;
            if(need.containsKey(c)){
                window.put(c,window.getOrDefault(c,0)+1);
                if(window.get(c).equals(need.get(c)))valid++;
            }
            //判断左侧窗口是否要进行收缩
            while(right - left >= p.length()){
                //当窗口符合条件的时候，将起始索引加入result
                if(valid == need.keySet().size()){
                    result.add(left);
                }
                char d = s.charAt(left);
                left++;
                //进行窗口内数据的一系列更新
                if(need.containsKey(d)){
                    if(window.get(d).equals(need.get(d))){
                        valid--;
                    }
                    window.put(d,window.get(d) -1 );
                }
            }
        }
        return result;
    }


    public List<Integer> anagrams(String s,String p){
        List<Integer> result = new ArrayList<>();
        Map<Character,Integer> need = new HashMap<>(),windows = new HashMap<>();
        for(int i= 0;i<p.length();i++){
            need.put(p.charAt(i),need.getOrDefault(p.charAt(i),0) + 1);
        }
        int left = 0,right = 0;
        int valid = 0;
        while(right<s.length()){
            //外层的while循环控制窗口右侧的变化
            char c = s.charAt(right);
            right++;
            //更新窗口中的内容
            if(need.containsKey(c)){
                windows.put(c,windows.getOrDefault(c,0) + 1);
                if(windows.get(c).equals(need.get(c))){
                    valid++;
                }
            }
            //左侧的窗口是否要收缩
            while(right - left >=p.length()){
                if(valid == need.keySet().size()){
                    result.add(left);
                }
                char d = s.charAt(left);
                left++;
                if(need.containsKey(d)){
                    if(windows.get(d).equals(need.get(d)))valid--;
                    windows.put(d,windows.get(d) -1);
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        FindAnagrams findAnagrams = new FindAnagrams();
        System.out.println(findAnagrams.anagrams("cbaebabacd","abc"));
    }
}
