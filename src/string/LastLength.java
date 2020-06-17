package string;

public class LastLength {

    public int lengthOfLastWord(String s) {
        if(s==null||s.length() == 0)return 0;
        String[] strArray = s.split(" ");
        if(strArray.length == 0)return 0;
        return strArray[strArray.length-1].length();
    }

    public static void main(String[] args) {
        String str = " ";
        LastLength lastLength = new LastLength();
        System.out.println(lastLength.lengthOfLastWord(str));
    }
}
