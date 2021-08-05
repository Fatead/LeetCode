package backtracking;

import java.util.*;

public class CombinationSum {

    Set<List<Integer>> resultList = new HashSet<>();

    private void dfs(int[] candidates, int curr, int target,int position, List<Integer> list){
        if(curr>target)return;
        if(curr == target){
            resultList.add(list);
            return;
        }
        for (int i = position; i <candidates.length ; i++) {
            List<Integer> newList = new ArrayList<>(list);
            newList.add(candidates[i]);
            dfs(candidates, curr + candidates[i],target,i,newList);
        }
    }


    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(candidates,0,target,0,new ArrayList<>());
        return new ArrayList<>(resultList);
    }

    public static void main(String[] args) {
        CombinationSum combinationSum = new CombinationSum();
        int[] candidates = {2,3,5};
        System.out.println(combinationSum.combinationSum(candidates,8));
    }

}