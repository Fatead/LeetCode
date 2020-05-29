package string;

/**
 * Created by zero on 2019/8/7.
 */
public class longestSubString
{

    public static void main(String[] args)
    {
        System.out.println(lengthOfLongestSubstring(""));
    }


    private static int lengthOfLongestSubstring(String s)
    {
        if(s.equals(""))
        {
            return 0;
        }
        for(int i = s.length();i>1;i--)
        {
            for(int j = 0;j<s.length()-i+1;j++)
            {
                if(noReaptString(s.substring(j,j+i)))
                {
                    return i;
                }
            }
        }
        return 1;
    }

    private static Boolean noReaptString(String s)
    {
        for(int i =0;i<s.length();i++)
        {
            char choosed = s.charAt(i);
            for(int j =i+1;j<s.length();j++)
            {
                if(choosed == s.charAt(j))
                {
                    return false;
                }
            }
        }
        return true;
    }
}
