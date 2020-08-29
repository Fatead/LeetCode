package backtracking;

import java.util.ArrayList;
import java.util.List;

public class CombineNum3 {


    List<List<Integer>> result = new ArrayList<>();


    private void calNum(int max,int k,int n,List<Integer> list){
        //if(max>=n)return;
        if(k == 0&&n == 0){
            result.add(new ArrayList<>(list));
            return;
        }
        if(k == 0||n == 0)return;
        for(int i = max+1;i<=9;i++){
            list.add(i);
            calNum(i,k-1,n-i,new ArrayList<>(list));
            list.remove(list.size()-1);
        }
    }


    public List<List<Integer>> combinationSum3(int k, int n) {
        if(k == 0||n == 0)return result;
        for(int i = 1;i<=9;i++){
            List<Integer> list = new ArrayList<>();
            list.add(i);
            calNum(i,k-1,n-i,new ArrayList<>(list));
            list.remove(list.size()-1);
        }
        return result;
    }

    public static void main(String[] args) {
        CombineNum3 combineNum3 = new CombineNum3();
        List<List<Integer>> array =  combineNum3.combinationSum3(1,9);
        System.out.println(array);
    }
}
