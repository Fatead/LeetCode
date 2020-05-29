package dp;

import java.math.*;

public class EditDistance {

    public static void main(String[] args)
    {
        System.out.println(editDistance("abcde","123"));
        System.out.println(editDistance_recursive("abcde","123"));
    }

    private static int editDistance_recursive(String str1,String str2)
    {
        int d = 0;
        if(str1.length()==0)
        {
            return str2.length();
        } else if(str2.length() == 0)
        {
            return str1.length();
        }

        if(str1.charAt(str1.length()-1) == str2.charAt(str2.length()-1))
        {
            d = 0;
        }else {
            d = 1;
        }

        return Math.min(editDistance_recursive(str1.substring(0,str1.length()-1),str2)+1,Math.min(editDistance_recursive(str1,str2.substring(0,str2.length()-1))+1,editDistance_recursive(str1.substring(0,str1.length()-1),str2.substring(0,str2.length()-1))+d));
    }

    private static int editDistance(String str1,String str2)
    {
        int d = 0;
        int[][] array = new int[str1.length()+1][str2.length()+1];
        for(int i = 0;i < str1.length()+1;i++)
        {
            array[i][0] = i;
        }
        for(int i = 0;i<str2.length()+1;i++)
        {
            array[0][i] = i;
        }
        for(int i = 1;i<str1.length()+1;i++)
        {
            for(int j = 1;j<str2.length()+1;j++)
            {
                if(str1.charAt(i-1) == str2.charAt(j-1))
                {
                    d = 0;
                }else
                {
                    d = 1;
                }
                array[i][j] = minNum(array[i-1][j] + 1,array[i][j-1] + 1,array[i-1][j-1]+d);
            }
        }
        return array[str1.length()][str2.length()];
    }

    private static int minNum(int a,int b,int c)
    {
        if(a<b&&a<c)
        {
            return a;
        }else if(b<c&&b<a)
        {
            return b;
        }else {
            return c;
        }
    }
}
