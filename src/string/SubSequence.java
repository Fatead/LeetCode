package string;

public class SubSequence {


    public boolean isSubsequence(String s, String t) {
        if(s == null||s.length() == 0)return false;
        if(s.length()>t.length())return false;
        char[] charsS = s.toCharArray();
        char[] charsT = t.toCharArray();
        int pos1 = 0,pos2 = 0;
        while(pos1<s.length()&&pos2<t.length()){
            if(pos1 == s.length()-1){
                return true;
            }
            if(s.charAt(pos1) == t.charAt(pos2)){
                pos1++;
                pos2++;
            }else {
                pos2++;
            }
        }
        return pos1 == s.length() - 1;
    }

    public static void main(String[] args) {

    }
}
