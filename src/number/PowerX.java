package number;

public class PowerX {

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
        PowerX powerX = new PowerX();
        System.out.println(powerX.myPow(2.1,3));
        System.out.println(-3%2);
    }
}
