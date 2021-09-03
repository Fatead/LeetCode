package string;

public class GcdOfStrings {

    private boolean isFactor(String str1,String factor){
        int n = factor.length();
        if(str1.length()%n!=0)return false;
        for (int i = 0; i <str1.length()/n ; i++) {
            if(!str1.substring(i*n,(i+1)*n).equals(factor)){
                return false;
            }
        }
        return true;
    }

    public String gcdOfStrings(String str1, String str2) {
        int length = Math.min(str1.length(),str2.length());
        for (int i = length; i >0 ; i--) {
            String factor = str1.substring(0,i);
            if(isFactor(str1,factor) && isFactor(str2,factor)){
                return factor;
            }
        }
        return "";
    }

    public static void main(String[] args) {
        GcdOfStrings gcdOfStrings = new GcdOfStrings();
        System.out.println(gcdOfStrings.gcdOfStrings("LEET","CODE"));
    }

}
