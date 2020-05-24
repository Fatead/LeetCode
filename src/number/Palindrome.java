package number;

import java.util.Stack;

public class Palindrome {

    public boolean isPalindrome(int x) {
        String str = String.valueOf(x);
        StringBuilder stringBuilder = new StringBuilder(str);
        String str2 = stringBuilder.reverse().toString();
        if(str.equals(str2))return true;
        else return false;
    }

    public boolean isPalindrome2(int x){
        int x2 = x;
        if(x == 0)return true;
        else if(x<0)return false;
        else {
            Stack<Integer> intStack = new Stack<>();
            while(x2>0){
                int num = x2%10;
                x2 /=10;
                intStack.push(num);
            }
            int bit = intStack.size();
            int num = 1;
            int newX = 0;
            for(int i = 0;i<bit;i++){
                newX += num*intStack.pop();
                num*=10;
            }
            return newX == x;
        }
    }

    public static void main(String[] args) {
        Palindrome palindrome = new Palindrome();
        int num = 12321;
        System.out.println(palindrome.isPalindrome2(num));
    }
}
