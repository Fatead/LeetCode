package string;

public class LengthOfLastWord {

    public int lengthOfLastWord(String s) {
        if(s == null || s.length() == 0)return 0;
        String[] words = s.split(" ");
        if(words.length == 0)return 0;
        String[] newWords = new String[words.length];
        int count = 0;
        for (String word : words) {
            word = word.replace(" ","");
            newWords[count++] = word;
        }
        return words[newWords.length-1].length();
    }

    public static void main(String[] args) {
        LengthOfLastWord lengthOfLastWord = new LengthOfLastWord();
        System.out.println(lengthOfLastWord.lengthOfLastWord(" 123  12314   21  "));
    }

}