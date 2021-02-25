package number;

/**
 * 快速幂，二分思想的一种应用
 */
public class MyPow {

    public double myPow(double x, int n) {
        if(n == 0)return 1;
        if(n == 1)return x;
        if(n == -1)return 1/x;
        if(n%2 == 1){
            double ret = myPow(x,(n-1)/2);
            return ret*ret*x;
        }else if(n%2 == -1){
            double ret = myPow(x,(n+1)/2);
            return ret*ret*(1/x);
        } else {
            double ret = myPow(x,n/2);
            return ret*ret;
        }
    }

    public static void main(String[] args) {
        MyPow myPow = new MyPow();
        System.out.println(myPow.myPow(2.1,3));
    }

}
