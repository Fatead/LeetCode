package greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxSwap {

    public int maximumSwap(int num) {
        List<Integer> list = new ArrayList<>();
        while(num!=0){
            list.add(num%10);
            num /=10;
        }
        Collections.reverse(list);
        for(int i = 0;i<list.size()-1;i++){
            int max = Integer.MIN_VALUE;
            int pos = 0;
            for(int j = i+1;j<list.size();j++){
                if(list.get(j) >= max){
                    max = list.get(j);
                    pos = j;
                }
            }
            if(max > list.get(i)){
                int temp = list.get(i);
                list.set(i,max);
                list.set(pos,temp);
                break;
            }
        }
        int result = 0;
        int count = 1;
        for(int i = list.size()-1;i>=0;i--){
            result += list.get(i)*count;
            count*=10;
        }
        return result;
    }

    public static void main(String[] args) {
        MaxSwap maxSwap = new MaxSwap();
        System.out.println(maxSwap.maximumSwap(9973));

    }

}
