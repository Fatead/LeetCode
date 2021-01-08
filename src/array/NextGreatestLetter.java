package array;

import java.util.Arrays;

public class NextGreatestLetter {

    public char nextGreatestLetter(char[] letters, char target) {
        Arrays.sort(letters);
        for(int i = 0;i<letters.length;i++){
            if(letters[i]>target)return letters[i];
        }
        return letters[0];
    }


    public static void main(String[] args) {
        NextGreatestLetter nextGreatestLetter = new NextGreatestLetter();
        char[] letters = {'c', 'f', 'j'};
        System.out.println(nextGreatestLetter.nextGreatestLetter(letters,'d'));
    }

}
