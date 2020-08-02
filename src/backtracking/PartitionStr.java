package backtracking;

import java.util.LinkedList;
import java.util.List;

public class PartitionStr {


    List<List<String>> partList = new LinkedList<>();

    private boolean isPalindrome(String str){
        if(str.length() == 1)return true;
        char[] chars = str.toCharArray();
        int len = chars.length;
        for(int i =0;i<len/2;i++){
            if(chars[i] != chars[len-i-1])return false;
        }
        return true;
    }

    private void part(String s,List<String> list){
        if(s == null||s.equals("")){
            partList.add(new LinkedList<>(list));
            return;
        }else if(s.length() == 1){
            list.add(s);
            partList.add(new LinkedList<>(list));
            return;
        }
        for(int i = 0;i<s.length();i++){
            String partStr = s.substring(0,i+1);
            if(isPalindrome(partStr)){
                list.add(partStr);
                part(s.substring(i+1),new LinkedList<>(list));
                list.remove(list.size()-1);
            }
        }
    }

    public List<List<String>> partition(String s) {
        if(s == null||s.length() == 0){
            partList.add(new LinkedList<>());
            return partList;
        }
        List<String> list = new LinkedList<>();
        part(s,list);
        return partList;
    }

    public static void main(String[] args) {
        String str = "cbbbcc";
        PartitionStr partitionStr = new PartitionStr();
        List<List<String>> result = partitionStr.partition(str);
        System.out.println(result);
    }

}
