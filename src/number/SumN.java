package number;

import java.io.File;
import java.util.Scanner;

public class SumN {

    public int sumNums(int n) {
        boolean flag = n > 0&&(n+=sumNums(n-1))>0;
        return n;
    }

    public static void main(String[] args) {
//        SumN sumN = new SumN();
//        System.out.println(sumN.sumNums(9));
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            double num = scanner.nextDouble();
            System.out.println("结果：" + num*10/11);
        }
    }

}
