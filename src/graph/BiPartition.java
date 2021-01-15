package graph;

import java.util.Arrays;

/**
 * 相邻的两个节点肯定属于不同的集合
 * 使用染色法进行处理，从任意节点开始，将其染成红色，并从该节点开始对整个无向图进行遍历；
 * 在遍历的过程中，如果我们通过节点 u 遍历到了节点 v（即 u 和 v 在图中有一条边直接相连），那么会有两种情况：
 * 如果 v 未被染色，那么我们将其染成与 u 不同的颜色，并对 v 直接相连的节点进行遍历；
 * 如果 v 被染色，并且颜色与 u 相同，那么说明给定的无向图不是二分图。我们可以直接退出遍历并返回False作为答案。
 */
public class BiPartition {

    private static final int UNCOLORED = 0;
    private static final int RED = 1;
    private static final int GREEN = 2;
    private int[] color;
    private boolean valid;

    public void dfs(int node,int c,int[][] graph){
        color[node] = c;
        int nextColor;
        if(c == RED)nextColor = GREEN;
        else nextColor = RED;
        for(int neighbor:graph[node]){
            if(color[neighbor] == UNCOLORED){
                dfs(neighbor,nextColor,graph);
                if(!valid)return;
            }else if(color[neighbor] == c){
                valid = false;
                return;
            }
        }
    }


    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        valid = true;
        color = new int[n];
        Arrays.fill(color,UNCOLORED);
        for(int i = 0;i<n&&valid;i++){
            if(color[i] == UNCOLORED){
                dfs(i,RED,graph);
            }
        }
        return valid;
    }

    public static void main(String[] args) {
        BiPartition biPartition = new BiPartition();
        int[][] graph = {
                {1,3},
                {0,2},
                {1,3},
                {0,2}
        };
        System.out.println(biPartition.isBipartite(graph));
    }

}