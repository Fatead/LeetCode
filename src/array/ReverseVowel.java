package array;

import java.util.HashSet;
import java.util.Set;

public class ReverseVowel {

    public String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        char[] vowels = {'a','e','i','o','u','A','E','I','O','U'};
        Set<Character> vowelSet = new HashSet<>();
        for(char c:vowels){
            vowelSet.add(c);
        }
        int left = 0;
        int right = s.length()-1;
        while(left<right){
            while(left<right&&!vowelSet.contains(chars[left])){
                left++;
            }
            char temp = chars[left];
            while(left<right&&!vowelSet.contains(chars[right])){
                right--;
            }
            if(left>=right)break;
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(char c:chars){
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }


    public static void main(String[] args) {
        ReverseVowel reverseVowel = new ReverseVowel();
        String str = "aA";
        System.out.println(reverseVowel.reverseVowels(str));
    }
}
