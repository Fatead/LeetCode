package number;

public class CountPrime {

    private boolean isPrime(int n){
        if(n<=1)return false;
        int num = (int) Math.sqrt(n);
        for(int i = 2;i<=num;i++){
            if(n%i == 0){
                return false;
            }
        }
        return true;
    }

    public int countPrimes(int n) {
        if(n == 1)return 1;
        int count = 0;
        for(int i = 2;i<n;i++){
            if(isPrime(i))count++;
        }
        return count;
    }

    public static void main(String[] args) {

    }
}
