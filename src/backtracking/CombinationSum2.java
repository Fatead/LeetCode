package backtracking;

import java.util.*;

public class CombinationSum2 {

    Set<List<Integer>> resultSet = new HashSet<>();

    private void dfs(int[] nums, int target, int position, int curr,List<Integer> list){
        if(curr == target){
            resultSet.add(new ArrayList<>(list));
            return;
        }
        if(position>=nums.length || curr>target || nums[position]>target)return;
        for (int i = position; i <nums.length ; i++) {
            //对于重复的情况进行剪枝
            if(i>position && nums[i] == nums[i-1])continue;
            if(nums[i]>target || target-curr<nums[i])break;
            list.add(nums[i]);
            dfs(nums,target,i+1,curr+nums[i],list);
            list.remove(list.size()-1);
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(candidates,target,0,0,new ArrayList<>());
        return new ArrayList<>(resultSet);
    }

    public static void main(String[] args) {
        CombinationSum2 combinationSum2 = new CombinationSum2();
        int[] candidates = {2,5,2,1,2};
        System.out.println(combinationSum2.combinationSum2(candidates,5));
    }

}