package string;

public class InterLeave {

    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.equals(""))return s2.equals(s3);
        if(s2.equals(""))return s1.equals(s3);
        if(s1.length() + s2.length() != s3.length())return false;
        int pos1 = 0, pos2 = 0, pos3 = 0;
        while ((pos1<s1.length() || pos2<s2.length()) && pos3<s3.length()){
            if(pos1<s1.length() && pos2<s2.length() && s1.charAt(pos1) != s3.charAt(pos3) && s2.charAt(pos2) != s3.charAt(pos3)){
                break;
            }
            while (pos1<s1.length() && s1.charAt(pos1) == s3.charAt(pos3)){
                pos1++;
                pos3++;
            }
            while (pos2<s2.length() && s2.charAt(pos2) == s3.charAt(pos3)){
                pos2++;
                pos3++;
            }
            if(pos1 == s1.length() && pos2 == s2.length())break;
            if(pos1 == s1.length() && s2.charAt(pos2) != s3.charAt(pos3)){
                break;
            }
            if(pos2 == s2.length() && s1.charAt(pos1) != s3.charAt(pos3)){
                break;
            }
        }
        return pos1 == s1.length() && pos2 == s2.length() && pos3 == s3.length();
    }

    public static void main(String[] args) {
        InterLeave interLeave = new InterLeave();
        System.out.println(interLeave.isInterleave("aa","ab","aaba"));
    }

}