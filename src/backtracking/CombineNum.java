package backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CombineNum {

    private List<List<Integer>> resultList = new ArrayList<>();

    private void cal(int begin,int end,int k,LinkedList<Integer> result){
        if(end-begin+1 == k){
            for(int i = begin;i<=end;i++){
                result.add(i);
            }
            resultList.add(result);
        }else if(k == 0||begin>=end){
            resultList.add(result);
        }else {
            for(int i = begin;i<=end-k+1;i++){
                result.add(i);
                cal(i+1,end,k-1,new LinkedList<>(result));
                result.removeLast();
            }
        }
    }


    public List<List<Integer>> combine(int n, int k) {
        if(n == 0||k == 0||k>n)return resultList;
        for(int i = 1;i<=n-k+1;i++){
            LinkedList<Integer> list = new LinkedList<>();
            list.add(i);
            cal(i+1,n,k-1,new LinkedList<>(list));
            list.removeLast();
        }
        return resultList;
    }

    public static void main(String[] args) {
        CombineNum combineNum = new CombineNum();
        List<List<Integer>> result = combineNum.combine(4,2);
        System.out.println(result.toString());
    }

}
