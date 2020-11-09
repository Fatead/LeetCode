package number;

public class Fib {

    public int fib(int N) {
        if(N == 0)return 0;
        if(N==1)return 1;
        return fib(N-1) + fib(N-2);
    }

    public static void main(String[] args) {
        Fib fib = new Fib();
        System.out.println(fib.fib(0));
    }

}
