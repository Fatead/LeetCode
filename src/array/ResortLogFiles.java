package array;

import java.util.*;

public class ResortLogFiles {

    private List<String> sortLetter(List<String> list){
        list.sort((s, t1) -> {
            String sBegin = s.substring(0,s.indexOf(" "));
            String t1Begin = t1.substring(0,s.indexOf(" "));
            s = s.substring(s.indexOf(" ") + 1);
            t1 = t1.substring(t1.indexOf(" ") + 1);
            if(s.equals(t1)){
                return sBegin.compareTo(t1Begin);
            }else {
                return s.compareTo(t1);
            }
        });
        return list;
    }

    public String[] reorderLogFiles(String[] logs) {
        String[] result = new String[logs.length];
        List<String> letterLog = new ArrayList<>();
        List<String> digitLog = new ArrayList<>();
        for (String log : logs) {
            String[] words = log.split(" ");
            if(Character.isDigit(words[1].charAt(0))){
                //是数字日志，保留原有顺序
                digitLog.add(log);
            }else {
                letterLog.add(log);
            }
        }
        letterLog = sortLetter(letterLog);
        int count = 0;
        for (String s : letterLog) {
            result[count++] = s;
        }
        for (String s : digitLog) {
            result[count++] = s;
        }
        return result;
    }

    public static void main(String[] args) {
        ResortLogFiles resortLogFiles = new ResortLogFiles();
        String[] logs = {"a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"};
        System.out.println(Arrays.toString(resortLogFiles.reorderLogFiles(logs)));
    }

}
