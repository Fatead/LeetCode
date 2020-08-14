package hashTable;

import java.util.*;

public class RepeatedDNA {


    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> result = new HashSet<>();
        Map<String,Integer> countMap = new HashMap<>();
        int len = s.length();
        if(len<10)return new ArrayList<>(result);
        for(int i = 0;i<len-9;i++){
            String str = s.substring(i,i+10);
            if(countMap.getOrDefault(str,0) == 1){
                result.add(str);
            }else {
                int count = countMap.getOrDefault(str,0);
                countMap.put(str,++count);
            }
        }
        return new ArrayList<>(result);
    }


    public static void main(String[] args) {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        RepeatedDNA repeatedDNA = new RepeatedDNA();
        List<String> result =  repeatedDNA.findRepeatedDnaSequences(s);
        System.out.println(result);
    }
}
