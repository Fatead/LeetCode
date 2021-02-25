package dp;

import java.util.Arrays;

public class CuttingRope {

    public int cuttingRope(int n) {
        if(n<=3)return n-1;
        long result = 1L;
        int p = (int)1e9 + 7;
        while (n>4){
            result = result*3%p;
            n-=3;
        }
        return (int) (result*n%p);
    }


    public static void main(String[] args) {
        CuttingRope cuttingRope = new CuttingRope();
        System.out.println(cuttingRope.cuttingRope(10));
    }

}
