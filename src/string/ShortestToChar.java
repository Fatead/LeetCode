package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShortestToChar {


    private int getDistance(List<Integer> list,int pos){
        int min = Integer.MAX_VALUE;
        for(int i:list){
            min = Math.min(min,Math.abs(pos-i));
        }
        return min;
    }

    public int[] shortestToChar(String S, char C) {
        List<Integer> position = new ArrayList<>();
        for (int i = 0; i <S.length() ; i++) {
            if(S.charAt(i) == C){
                position.add(i);
            }
        }
        int[] result = new int[S.length()];
        for (int i = 0; i <result.length ; i++) {
            result[i] = getDistance(position,i);
        }
        return result;
    }

    public static void main(String[] args) {
        ShortestToChar shortestToChar = new ShortestToChar();
        int[] result = shortestToChar.shortestToChar("loveleetcode",'e');
        System.out.println(Arrays.toString(result));
    }

}
