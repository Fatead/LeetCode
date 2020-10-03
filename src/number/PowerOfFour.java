package number;

public class PowerOfFour {

    public boolean isPowerOfFour(int n) {
        if(n<=0)return false;
        double num = 0;
        int count = 0;
        while(num<n){
            num = Math.pow(4,count++);
            if(num == n)return true;
        }
        return false;
    }

    public static void main(String[] args) {
        PowerOfFour powerOfFour = new PowerOfFour();
        boolean result = powerOfFour.isPowerOfFour(16);
        System.out.println(result);
    }

}
