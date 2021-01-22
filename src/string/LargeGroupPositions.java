package string;

import java.util.ArrayList;
import java.util.List;

public class LargeGroupPositions {

    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> result = new ArrayList<>();
        if(s==null||s.length() == 0)return result;
        int left = 0,right = 0;
        char currChar = s.charAt(0);
        for (int i = 0; i <s.length() ; i++) {
            if(s.charAt(i) == currChar){
                right = i;
            }else {
                if(right-left>=2){
                    List<Integer> list= new ArrayList<>();
                    list.add(left);
                    list.add(right);
                    result.add(list);
                }
                left = i;
                right = i;
                currChar = s.charAt(i);
            }
        }
        if(right-left>=2){
            List<Integer> list= new ArrayList<>();
            list.add(left);
            list.add(right);
            result.add(list);
        }
        return result;
    }

    public static void main(String[] args) {
        LargeGroupPositions largeGroupPositions = new LargeGroupPositions();
        System.out.println(largeGroupPositions.largeGroupPositions("abcdddeeeeaabbbcderweeeeeetttttoooooassssss"));
    }

}
