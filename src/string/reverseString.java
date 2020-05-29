package string;

/**
 * Created by zero on 2019/8/14.
 */
public class reverseString {

    public static int countSubstrings(String s) {
        int count = 0;
        for(int i = 1;i<=s.length();i++)
        {
            int j = 0;
            while(i+j<=s.length())
            {
                if(decideConverse(s.substring(j,j+i)))count++;
                j++;
            }
        }
        return count;

    }

    public static boolean decideConverse(String s)
    {
        for(int i =0;i<s.length();i++)
        {
            if(s.charAt(i)!=s.charAt(s.length()-i-1))return false;
        }
        return true;
    }

    public static void main(String[] args)
    {
        System.out.println(countSubstrings("abc"));
    }

}
