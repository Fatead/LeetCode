package graph;
import java.util.ArrayList;
import java.util.List;

public class AllPathSourceTarget {

    List<List<Integer>> result = new ArrayList<>();

    private void dfs(int[][] graph,int node,List<Integer> list){
        if(node == graph.length-1){
            result.add(list);
        }
        list.add(node);
        int[] target = graph[node];
        for(int i:target){
            dfs(graph,i,new ArrayList<>(list));
        }
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        if(graph.length == 0)return result;
        dfs(graph,0,new ArrayList<>());
        return result;
    }

    public static void main(String[] args) {
        AllPathSourceTarget allPathSourceTarget = new AllPathSourceTarget();
        int[][] graph = {
                {1,2},
                {3},
                {3},
                {}
        };
        System.out.println(allPathSourceTarget.allPathsSourceTarget(graph));
    }

}
