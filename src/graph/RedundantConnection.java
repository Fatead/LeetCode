package graph;

import java.util.*;

public class RedundantConnection {

    /**
     * 从最后一个边开始依次进行删除尝试
     */
    public int[] findRedundantConnection(int[][] edges) {
        for(int i = edges.length-1;i>=0;i--){
            flag = false;
            int[][] graph = new int[edges.length-1][2];
            int count = 0;
            for(int j = 0;j<edges.length;j++){
                if(j == i)continue;
                graph[count++] = edges[j];
            }
            isHaveLoop(graph);
            if(!flag)return edges[i];
        }
        return new int[2];
    }


    private boolean flag = false;

    private boolean isHaveLoop(int[][] graph){
        int num = graph.length + 1;
        //将图转换为邻接链表的表示形式
        ArrayList<Integer>[] adj = new ArrayList[num];
        for(int[] edge:graph){
            int node1 = edge[0]-1;
            int node2 = edge[1]-1;
            if (adj[node1] == null) {
                adj[node1] = new ArrayList<>();
            }
            if (adj[node2] == null) {
                adj[node2] = new ArrayList<>();
            }
            adj[node1].add(node2);
            adj[node2].add(node1);
        }
        //利用一维数组保存当前节点的状态（有没有被访问过）
        boolean[] visited = new boolean[num];
        for(int i = 0;i<num;i++){
            if(!visited[i]){
                //利用dfs去访问当前节点能够到达的所有节点
                dfsCycle(adj,i,-1,visited);
                if(flag)return true;
            }
        }
        return flag;
    }

    private void dfsCycle(List<Integer>[] adj,int current,int parent,boolean[] visited){
        visited[current] = true;
        List<Integer> currList = adj[current];
        if(currList == null)return;
        for(int i:currList){
            if(!visited[i]){
                dfsCycle(adj,i,current,visited);
            }else if(i!=parent){
                //如果当前节点已经被访问过且该节点不为父节点，则存在环
                flag = true;
            }
        }
    }

    public static void main(String[] args) {
        RedundantConnection redundantConnection = new RedundantConnection();
        int[][] edges = {
                {1,2},
                {2,3},
                {3,4},
                {1,4},
                {1,5}
        };
        int[] result = redundantConnection.findRedundantConnection(edges);
        System.out.println(Arrays.toString(result));
    }

}
