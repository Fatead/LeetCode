package backtracking;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComSum {

    public static List<List<Integer>> resultList = new ArrayList<>();

    private boolean calculate(int[] candidates,int target,List<Integer> integerList,int left){
        if(target == 0){
            resultList.add(new ArrayList<>(integerList));
            return true;
        }
        for(int i = left;i<candidates.length;i++){
            if(candidates[i]>target){
                break;
            }
            integerList.add(candidates[i]);
            calculate(candidates,target-candidates[i],integerList,i);
            integerList.remove(integerList.size()-1);
        }
        return false;
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<Integer> list = new ArrayList<>();
        calculate(candidates,target,list,0);
        return resultList;
    }

    public static void main(String[] args) {
        int[] array = {2,3,5};
        ComSum comSum = new ComSum();
        System.out.println(comSum.combinationSum(array,8));
    }
}
