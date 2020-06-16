package hashTable;

import java.util.*;

public class GroupAna {

    private String convertArr2Str(char[] chars){
        StringBuffer stringBuffer = new StringBuffer();
        for(char c:chars){
            stringBuffer.append(c);
        }
        return stringBuffer.toString();
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if(strs.length == 0)return result;
        List<String> strList = new ArrayList<>();
        List<Integer> valueList = new ArrayList<>();
        Set<Integer> integerSet = new HashSet<>();
        for(String str:strs){
            char[] array = str.toCharArray();
            Arrays.sort(array);
            int hashVal = convertArr2Str(array).hashCode();
            strList.add(str);
            valueList.add(hashVal);
            integerSet.add(hashVal);
        }
        for(Integer integer:integerSet){
            List<String> list = new ArrayList<>();
            for(int i =0;i<strList.size();i++){
                if(valueList.get(i).equals(integer))list.add(strList.get(i));
            }
            result.add(list);
        }
        return result;
    }

    public static void main(String[] args) {
        String[] strs = { "", "", ""};
        GroupAna groupAna = new GroupAna();
        System.out.println(groupAna.groupAnagrams(strs));
    }
}
