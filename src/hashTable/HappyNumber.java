package hashTable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HappyNumber {

    Set<Integer> intSet = new HashSet<>();
    private boolean isHappyNum = false;

    private List<Integer> num2List(int n){
        List<Integer> list = new ArrayList<>();
        while(n>0){
            list.add(n%10);
            n = n/10;
        }
        return list;
    }

    public void decide(int n){
        if(!intSet.contains(n)){
            List<Integer> list = num2List(n);
            int num = 0;
            for(Integer i:list){
                num = num+i*i;
            }
            if(num == 1){
                isHappyNum = true;
            }else {
                intSet.add(n);
                isHappy(num);
            }
        }
    }

    public boolean isHappy(int n) {
        if(n<=0)return false;
        decide(n);
        return isHappyNum;
    }

    public static void main(String[] args) {
        HappyNumber happyNumber = new HappyNumber();
        System.out.println(happyNumber.isHappy(19));

    }
}
