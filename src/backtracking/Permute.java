package backtracking;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Permute {

    Set<List<Integer>> result = new HashSet<>();

    private void swap(int[] nums, int i, int j){
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }

    private void dfs(int[] nums,int index){
        if(index>nums.length)return;
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        result.add(list);
        for (int i = index; i < nums.length ; i++) {
            swap(nums,index,i);
            dfs(nums,index+1);
            swap(nums,index,i);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        dfs(nums,0);
        return new ArrayList<>(result);
    }


    public static void main(String[] args) {
        Permute permute = new Permute();
        int[] nums = {1,2,3};
        System.out.println(permute.permute(nums));
    }

}