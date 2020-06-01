package number;

public class SumN {

    public int sumNums(int n) {
        boolean flag = n > 0&&(n+=sumNums(n-1))>0;
        return n;
    }

    public static void main(String[] args) {
        SumN sumN = new SumN();
        System.out.println(sumN.sumNums(9));
    }
}
