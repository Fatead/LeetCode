package array;

public class ReverseString {

    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length-1;
        while(left<right){
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            right--;
            left++;
        }
    }

    public static void main(String[] args) {
        ReverseString reverseString = new ReverseString();
        char[] chars = {'h','e','l','l','o'};
        reverseString.reverseString(chars);
        System.out.println(chars);
    }
}
