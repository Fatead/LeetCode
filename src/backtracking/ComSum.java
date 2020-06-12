package backtracking;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComSum {

    List<List<Integer>> resultList = new ArrayList<>();

    private void calculate(int[] candidates ,int target,List<Integer> integerList, int left){
        if(target == 0)resultList.add(new ArrayList<>(integerList));
        if(candidates[left]>target){
            return;
        }
        for(int i = left;i<candidates.length;i++){
            integerList.add(candidates[i]);
            calculate(candidates,target-candidates[i],integerList,i);
            integerList.remove(integerList.size()-1);
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<Integer> integerList = new ArrayList<>();
        calculate(candidates,target,integerList,0);
        return resultList;
    }

    public static void main(String[] args) {
        int[] array = {2,3,5};
        ComSum comSum = new ComSum();
        System.out.println(comSum.combinationSum(array,8));
    }
}
