package DFS;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Subsets {

    Set<List<Integer>> resultSet = new HashSet<>();

    private void dfs(int[] nums, int pos, List<Integer> list){
        if(pos == nums.length){
            resultSet.add(list);
            return;
        }
        dfs(nums,pos+1,list);
        List<Integer> newList = new ArrayList<>(list);
        newList.add(nums[pos]);
        dfs(nums, pos+1, newList);
    }

    public List<List<Integer>> subsets(int[] nums) {
        dfs(nums,0,new ArrayList<>());
        return new ArrayList<>(resultSet);
    }

    public static void main(String[] args) {
        Subsets subsets = new Subsets();
        int[] nums = {0};
        System.out.println(subsets.subsets(nums));
    }

}