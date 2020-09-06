package array;

import java.util.Arrays;

public class ValidAnagramWord {

    public boolean isAnagram(String s, String t) {
        char[] charS = s.toCharArray();
        char[] charT = t.toCharArray();
        if(charS.length!=charT.length)return false;
        Arrays.sort(charS);
        Arrays.sort(charT);
        for(int i = 0;i<charS.length;i++){
            if(charS[i]!=charT[i])return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "";
        String t = "";
        ValidAnagramWord validAnagramWord = new ValidAnagramWord();
        System.out.println(validAnagramWord.isAnagram(s,t));

    }

}
