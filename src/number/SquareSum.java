package number;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SquareSum {

    public boolean judgeSquareSum(int c) {
        Set<Integer> squareSet = new HashSet<>();
        int num = 0;
        while(num*num<=c && num*num>=0){
            squareSet.add(num*num);
            num++;
            if(num*num == c||num*num*2 == c)return true;
        }
        System.out.println(squareSet);
        for(int i:squareSet){
            if(squareSet.contains(c-i))return true;
        }
        return false;
    }

    public static void main(String[] args) {
        SquareSum squareSum = new SquareSum();
        System.out.println(squareSum.judgeSquareSum(2147482647));

    }
}
