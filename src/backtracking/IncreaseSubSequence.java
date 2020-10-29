package backtracking;

import java.util.*;

public class IncreaseSubSequence {

    List<List<Integer>> result = new ArrayList<>();
    Set<List<Integer>>  set = new HashSet<>();

    private void dfs(int[] nums,int begin,List<Integer> list){
        if(list.size() >= 2 && !set.contains(list)){
            result.add(list);
            set.add(list);
        }
        if(begin>=nums.length)return;
        for(int i = begin;i<nums.length;i++){
            if(list.size() == 0 || nums[i] >= list.get(list.size()-1)){
                list.add(nums[i]);
                dfs(nums,i+1,new ArrayList<>(list));
                list.remove(list.size()-1);
            }
        }
    }

    public List<List<Integer>> findSubsequences(int[] nums) {
        for(int i = 0;i<nums.length;i++){
            dfs(nums,i,new ArrayList<>());
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {4,6,7,7};
        IncreaseSubSequence increaseSubSequence = new IncreaseSubSequence();
        List<List<Integer>> result = increaseSubSequence.findSubsequences(nums);
        System.out.println(result);
    }

}
