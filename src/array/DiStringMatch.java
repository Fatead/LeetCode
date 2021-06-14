package array;

import java.util.Arrays;

public class DiStringMatch {

    public int[] diStringMatch(String s) {
        int[] result = new int[s.length()+1];
        int high = s.length();
        int low = 0;
        for (int i = 0; i <s.length() ; i++) {
            if(s.charAt(i) == 'I'){
                result[i] = low++;
            }else {
                result[i] = high--;
            }
        }
        result[s.length()] = low;
        return result;
    }

    public static void main(String[] args) {
        DiStringMatch diStringMatch = new DiStringMatch();
        System.out.println(Arrays.toString(diStringMatch.diStringMatch("IDID")));
    }

}
