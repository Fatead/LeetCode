package backtracking;

import java.util.*;

public class SubSet {

    List<List<Integer>> subSetList = new ArrayList<>();
    Set<List<Integer>> subSet = new HashSet<>();

    private void calSubSet(LinkedList<Integer> list){
        if(list.size() == 0)return;
        if(!subSet.contains(list)){
            subSetList.add(list);
            subSet.add(list);
        }
        for(int i = 0;i<list.size();i++){
            int num = list.remove(i);
            calSubSet(new LinkedList<>(list));
            list.add(i,num);
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        if(nums.length == 0)return subSetList;
        LinkedList<Integer> linkedList = new LinkedList<>();
        for(int i = 0;i<nums.length;i++){
            linkedList.add(nums[i]);
        }
        calSubSet(linkedList);
        subSetList.add(new LinkedList<>());
        return subSetList;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        SubSet subSet = new SubSet();
        List<List<Integer>> result = subSet.subsets(nums);
        System.out.println(result);
    }
}
