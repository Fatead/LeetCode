package slidingWindows;

import java.util.HashMap;
import java.util.Map;

public class CheckInclusion {


    public boolean checkInclusion(String s1, String s2) {
        Map<Character,Integer> need = new HashMap<>(),windows = new HashMap<>();
        for(int i = 0;i<s1.length();i++){
            need.put(s1.charAt(i),need.getOrDefault(s1.charAt(i),0) + 1);
        }
        int left = 0,right = 0;
        int valid = 0;
        while(right<s2.length()){
            char c = s2.charAt(right);
            right++;
            if(need.containsKey(c)){
                windows.put(c,windows.getOrDefault(c,0) + 1);
                if(windows.get(c).equals(need.get(c))){
                    valid++;
                }
            }

            while(right-left>=s1.length()){
                if(valid == need.keySet().size()){
                    return true;
                }
                char d = s2.charAt(left);
                left++;
                if(need.containsKey(d)){
                    if(windows.get(d).equals(need.get(d))){
                        valid--;
                    }
                    windows.put(d,windows.get(d) -1 );
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        CheckInclusion checkInclusion = new CheckInclusion();
        System.out.println(checkInclusion.checkInclusion("ab","eidbaooo"));
    }

}
