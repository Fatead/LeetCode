package string;

public class ReverseLeftWords {

    public String reverseLeftWords(String s, int n) {
        if(s==null || s.length()==0)return "";
        n = n%s.length();
        return s.substring(n) + s.substring(0, n - 1);
    }

    public static void main(String[] args) {
        ReverseLeftWords reverseLeftWords = new ReverseLeftWords();
        System.out.println(reverseLeftWords.reverseLeftWords("",6));
    }

}
