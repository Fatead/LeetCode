package greedy;

import java.util.Arrays;

public class CookiesAndChildren {

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int posS = s.length-1;
        int posG = g.length-1;
        int result = 0;
        while(posS>=0 && posG>=0){
            if(s[posS] >= g[posG]){
                posG--;
                posS--;
                result++;
            }else {
                posG--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        CookiesAndChildren cookiesAndChildren = new CookiesAndChildren();
        int[] g = {1,2};
        int[] s = {1,2,3};
        System.out.println(cookiesAndChildren.findContentChildren(g,s));
    }

}