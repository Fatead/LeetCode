package string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MinWindowsStr {


    public String minWindow(String s, String t) {
        int m = s.length(), n = t.length();
        if (m < n) return "";
        int[] freq = new int[128];
        for (int i = 0; i < n; ++i)
            freq[t.charAt(i)]++;
        int start = 0, len = m + 1, cnt = 0, left = 0;

        for (int i = 0; i < m; ++i) {
            if (--freq[s.charAt(i)] >= 0) ++cnt;
            while (cnt == n) {
                if (i - left + 1 < len) {
                    start = left;
                    len = i - left + 1;
                }
                if (++freq[s.charAt(left)] > 0) --cnt;
                ++left;
            }
        }
        return len > m ? "" : s.substring(start, start + len);
    }

    public static void main(String[] args) {
        MinWindowsStr minWindowsStr = new MinWindowsStr();
        String s = "ab";
        String t = "b";
        String result = minWindowsStr.minWindow(s,t);
        System.out.println(result);
    }
}
