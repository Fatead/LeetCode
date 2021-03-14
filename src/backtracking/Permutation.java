package backtracking;

import java.util.*;

public class Permutation {


    Set<String> resultSet = new HashSet<>();

    private void backTracking(List<Character> list,StringBuilder stringBuilder){
        if(list.size() == 0){
            resultSet.add(stringBuilder.toString());
        }
        for (int i = 0; i < list.size(); i++) {
            char c = list.get(i);
            List<Character> newList = new ArrayList<>(list);
            newList.remove(i);
            StringBuilder sb = new StringBuilder(stringBuilder);
            sb.append(c);
            backTracking(newList,sb);
        }
    }

    public String[] permutation(String s) {
        List<Character> list = new ArrayList<>();
        for (int i = 0; i <s.length() ; i++) {
            list.add(s.charAt(i));
        }
        backTracking(list,new StringBuilder());
        String[] result = new String[resultSet.size()];
        int pos = 0;
        for (String s1 : resultSet) {
            result[pos++] = s1;
        }
        return result;
    }

    public static void main(String[] args) {
        Permutation permutation = new Permutation();
        String[] result = permutation.permutation("abc");
        System.out.println(Arrays.toString(result));
    }

}
