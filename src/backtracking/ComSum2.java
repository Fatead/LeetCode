package backtracking;

import java.util.*;

public class ComSum2 {

    List<List<Integer>> resultList = new ArrayList<>();
    Set<String> resultSet = new HashSet<>();

    public void calculate(int[] candidates, int target,List<Integer> integerList, int left){
        if(target == 0){
            Collections.sort(integerList);
            if(!resultSet.contains(integerList.toString())){
                resultList.add(new ArrayList<>(integerList));
                resultSet.add(integerList.toString());
            }
        }
        if(left>=candidates.length||candidates[left]>target)return;
        for(int i = left;i<candidates.length;i++){
            integerList.add(candidates[i]);
            calculate(candidates,target-candidates[i],integerList,i+1);
            integerList.remove(integerList.size()-1);
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<Integer> integerList = new ArrayList<>();
        calculate(candidates,target,integerList,0);
        return resultList;
    }

    public static void main(String[] args) {
        int[] array = {10,1,2,7,6,1,5};
        ComSum2 comSum2 = new ComSum2();
        System.out.println(comSum2.combinationSum2(array,8));
    }
}
