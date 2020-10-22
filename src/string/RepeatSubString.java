package string;

public class RepeatSubString {


    private boolean repeat(String s,String pattern){
        if(s.length()%pattern.length()!=0)return false;
        for(int i = 1;i<s.length()/pattern.length();i++){
            if(!s.substring(i*pattern.length(),(i+1)*pattern.length()).equals(pattern)){
                return false;
            }
        }
        return true;
    }

    public boolean repeatedSubstringPattern(String s) {
        if(s == null||s.length()<2)return false;
        for(int i = 1;i<s.length()/2+1;i++){
            if(repeat(s,s.substring(0,i))){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        RepeatSubString repeatSubString = new RepeatSubString();
        System.out.println(repeatSubString.repeatedSubstringPattern("abab"));
    }
}
