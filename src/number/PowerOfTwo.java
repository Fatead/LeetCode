package number;

public class PowerOfTwo {


    public boolean isPowerOfTwo(int n) {
        int number = 0;
        int i = 0;
        while(number < n){
            number = (int)Math.pow(2,i);
            if(number == n)return true;
            i++;
        }
        return false;
    }
}
