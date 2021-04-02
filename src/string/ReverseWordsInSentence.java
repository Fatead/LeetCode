package string;

public class ReverseWordsInSentence {

    public String reverseWords(String s) {
        String[] words = s.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = words.length-1; i >=0 ; i--) {
            if(words[i].length()==0||words[i].equals(" "))continue;
            stringBuilder.append(words[i]).append(" ");
        }
        return stringBuilder.toString().trim();
    }

    public static void main(String[] args) {
        ReverseWordsInSentence reverseWordsInSentence = new ReverseWordsInSentence();
        System.out.println(reverseWordsInSentence.reverseWords("a good   example"));
    }


}
