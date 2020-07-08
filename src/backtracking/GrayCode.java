package backtracking;

import java.util.ArrayList;
import java.util.List;

public class GrayCode {


    public List<Integer> grayCode(int n) {
        List<Integer> grayList = new ArrayList<>();
        if(n == 0){
            grayList.add(0);
            return grayList;
        }else {
            List<Integer> list = new ArrayList<>();
            list = grayCode(n-1);
            grayList.addAll(list);
            for(int i = list.size()-1;i>=0;i--){
                grayList.add((int)(list.get(i) + Math.pow(2,n-1)));
            }
            return grayList;
        }
    }

    public static void main(String[] args) {
        GrayCode grayCode = new GrayCode();
        List<Integer> result = grayCode.grayCode(2);
        System.out.println(result);
    }
}
