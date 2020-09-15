package number;

public class PowerOfThree {

    public boolean isPowerOfThree(int n) {
        if(n<=0)return false;
        double num = 0;
        int count = 0;
        while(num<n){
            num = Math.pow(3,count++);
            if(num == n)return true;
        }
        return false;
    }

    public static void main(String[] args) {
        PowerOfThree powerOfThree = new PowerOfThree();
        boolean result = powerOfThree.isPowerOfThree(81);
        System.out.println(result);
    }
}
