package backtracking;

import java.util.*;

public class SubSet2 {

    List<List<Integer>> resultList = new LinkedList<>();
    HashSet<List<Integer>> resultSet = new HashSet<>();


    private void calSubSet(List<Integer> list){
        if(!resultSet.contains(list)){
            resultList.add(list);
            resultSet.add(list);
        }
        for(int i = 0;i<list.size();i++){
            if(i == 0 || !list.get(i).equals(list.get(i - 1))){
                int num = list.remove(i);
                calSubSet(new LinkedList<>(list));
                list.add(i,num);
            }
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<Integer> list = new LinkedList<>();
        for(int i:nums){
            list.add(i);
        }
        Collections.sort(list);
        calSubSet(list);
        return resultList;
    }

    public static void main(String[] args) {
        int[] array = {4,4,4,1,4};
        SubSet2 subSet2 = new SubSet2();
        List<List<Integer>> result =  subSet2.subsetsWithDup(array);
        System.out.println(result);
    }
}
