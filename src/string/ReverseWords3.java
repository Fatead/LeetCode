package string;

public class ReverseWords3 {

    public String reverseWords(String s) {
        String[] strings = s.split(" ");
        StringBuilder result = new StringBuilder();
        for(String str:strings){
            result.append(new StringBuilder(str).reverse()).append(" ");
        }
        return result.toString().trim();
    }

    public static void main(String[] args) {
        ReverseWords3 reverseWords3 = new ReverseWords3();
        System.out.println(reverseWords3.reverseWords("Let's take LeetCode contest"));
    }
}
