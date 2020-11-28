package string;

public class LUSLength {

    public int findLUSlength(String a, String b) {
        if(a.equals(b))return -1;
        return Math.max(a.length(),b.length());
    }

    public static void main(String[] args) {
        LUSLength lusLength = new LUSLength();
        System.out.println(lusLength.findLUSlength("abc","cdc"));
    }

}
