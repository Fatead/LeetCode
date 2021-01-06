package number;

import java.util.ArrayList;
import java.util.List;

public class SelfDividingNum {

    private boolean isDivideNum(int num){
        List<Integer> bitList = new ArrayList<>();
        int temp = num;
        while(temp>0){
            int bitNum = temp%10;
            if(bitNum == 0)return false;
            bitList.add(bitNum);
            temp/=10;
        }
        for(int i:bitList){
            if(num%i!=0){
                return false;
            }
        }
        return true;
    }


    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> result = new ArrayList<>();
        for(int i = left;i<=right;i++){
            if(isDivideNum(i)){
                result.add(i);
            }
        }
        return result;
    }


    public static void main(String[] args) {
        SelfDividingNum selfDividingNum = new SelfDividingNum();
        System.out.println(selfDividingNum.selfDividingNumbers(1,22));
    }

}
