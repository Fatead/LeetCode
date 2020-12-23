package string;

public class Palindrome2 {


    private boolean isPalindrome(String s,int left,int right){
        while(left<right){
            if(s.charAt(left)!=s.charAt(right))return false;
            left++;
            right--;
        }
        return true;
    }



    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length()-1;
        while(left<=right){
            if(s.charAt(left)!=s.charAt(right)){
                return isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1);
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "ddfadaf";
        Palindrome2 palindrome2 = new Palindrome2();
        System.out.println(palindrome2.validPalindrome(str));
    }

}
