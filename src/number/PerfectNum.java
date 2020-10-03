package number;

public class PerfectNum {

    public boolean isPerfectSquare(int num) {
        int count = 0;
        long number = 0;
        while(number<num){
            number = (long) Math.pow(count++,2);
            if(number == num)return true;
        }
        return false;
    }

    public static void main(String[] args) {
        PerfectNum perfectNum = new PerfectNum();
        boolean result = perfectNum.isPerfectSquare(1);
        System.out.println(result);
    }
}
