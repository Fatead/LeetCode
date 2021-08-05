package backtracking;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Combine {

    List<List<Integer>> result = new ArrayList<>();

    private void dfs(int n, int k, int index, int num, LinkedList<Integer> list){
        if(num == k){
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i <=n ; i++) {
            list.add(i);
            dfs(n,k,i+1,num+1,list);
            list.removeLast();
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        dfs(n,k,1,0,new LinkedList<>());
        return result;
    }


    public static void main(String[] args) {
        Combine combine = new Combine();
        System.out.println(combine.combine(1,1));
    }

}