package string;

public class RotateString {

    /**
     * 字符串首尾相连
     */
    public boolean rotateString2(String A, String B){
        return A.length() == B.length() && (A+A).contains(B);
    }

    public boolean rotateString(String A, String B) {
        if(A.length()!=B.length())return false;
        if(A.equals(B))return true;
        for(int i = 0;i<A.length();i++){
            String str1 = A.substring(0,i);
            String str2 = A.substring(i);
            if(B.equals(str2+str1)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        RotateString rotateString = new RotateString();
        System.out.println(rotateString.rotateString("abcde","abced"));
    }

}
