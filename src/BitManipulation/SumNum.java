package BitManipulation;

public class SumNum {

    public int getSum(int a, int b) {
        if(a == 0)return b;
        if(b == 0)return a;
        int x1 = a^b;
        int x2 = (a&b)<<1;
        return getSum(x1,x2);
    }

    public static void main(String[] args) {
        SumNum sumNum = new SumNum();
        System.out.println(sumNum.getSum(2,3));
    }

}
