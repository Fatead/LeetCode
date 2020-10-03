package string;

import java.util.Arrays;

public class FindDifference {

    public char findTheDifference(String s, String t) {
        char[] charsS = s.toCharArray();
        char[] charsT = t.toCharArray();
        Arrays.sort(charsS);
        Arrays.sort(charsT);
        for(int i = 0;i<charsS.length;i++){
            if(charsS[i] !=charsT[i]){
                return charsT[i];
            }
        }
        return charsT[t.length()-1];
    }


    public static void main(String[] args) {
        FindDifference findDifference = new FindDifference();
        System.out.println(findDifference.findTheDifference("abcd","abcde"));
    }
}
