package string;

public class Palindrome {

    public boolean isPalindrome(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = s.toLowerCase().toCharArray();
        for(char c:chars){
            if(Character.isLetterOrDigit(c)){
                stringBuilder.append(c);
            }
        }
        char[] charArray = stringBuilder.toString().toCharArray();
        int len = charArray.length;
        for(int i =0;i<(len+1)/2;i++){
            if(charArray[i] != charArray[len-1-i])return false;
        }
        return true;
    }
}
