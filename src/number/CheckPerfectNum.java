package number;

import java.util.ArrayList;
import java.util.List;

public class CheckPerfectNum {

    public boolean checkPerfectNumber(int num) {
        if(num<=0)return false;
        List<Integer> factorList = new ArrayList<>();
        int n = 1;
        while(n<=Math.sqrt(num)){
            if(num%n == 0){
                factorList.add(n);
                if(n*n!=num){
                    factorList.add(num/n);
                }
            }
            n++;
        }
        int sum = 0;
        for(int i:factorList){
            sum += i;
        }
        System.out.println(factorList);
        return sum == 2*num;
    }

    public static void main(String[] args) {
        CheckPerfectNum checkPerfectNum = new CheckPerfectNum();
        System.out.println(checkPerfectNum.checkPerfectNumber(28));
    }
}
