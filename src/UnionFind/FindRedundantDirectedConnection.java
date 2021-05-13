package UnionFind;
import java.util.*;

public class FindRedundantDirectedConnection {


    public class UnionFind{

        int[] parents;

        public UnionFind(int n){
            parents = new int[n];
            for (int i = 0; i <n ; i++) {
                parents[i] = i;
            }
        }

        public int find(int x){
            while (x!=parents[x]){
                parents[x] = parents[parents[x]];
                x = parents[x];
            }
            return x;
        }

        public void union(int x,int y){
            int rootX = find(x);
            int rootY = find(y);
            if(rootX!=rootY){
                parents[rootX] = rootY;
            }
        }

        public boolean isConnect(int x,int y){
            return find(x) == find(y);
        }

    }


    public int[] findRedundantDirectedConnection(int[][] edges) {
        int nodesCount = edges.length;
        UnionFind uf = new UnionFind(nodesCount + 1);
        int[] parent = new int[nodesCount + 1];
        for (int i = 1; i <= nodesCount; ++i) {
            parent[i] = i;
        }
        int conflict = -1;
        int cycle = -1;
        for (int i = 0; i < nodesCount; ++i) {
            int[] edge = edges[i];
            int node1 = edge[0], node2 = edge[1];
            if (parent[node2] != node2) {
                conflict = i;
            } else {
                parent[node2] = node1;
                if (uf.find(node1) == uf.find(node2)) {
                    cycle = i;
                } else {
                    uf.union(node1, node2);
                }
            }
        }
        if (conflict < 0) {
            int[] redundant = {edges[cycle][0], edges[cycle][1]};
            return redundant;
        } else {
            int[] conflictEdge = edges[conflict];
            if (cycle >= 0) {
                int[] redundant = {parent[conflictEdge[1]], conflictEdge[1]};
                return redundant;
            } else {
                int[] redundant = {conflictEdge[0], conflictEdge[1]};
                return redundant;
            }
        }
    }

    public static void main(String[] args) {
        FindRedundantDirectedConnection findRedundantDirectedConnection = new FindRedundantDirectedConnection();
        int[][] edges = {
                {1,2},
                {1,3},
                {2,3}
        };
        System.out.println(Arrays.toString(findRedundantDirectedConnection.findRedundantDirectedConnection(edges)));
    }

}