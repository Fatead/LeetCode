package UnionFind;
import java.util.Arrays;

public class FindRedundantConnection {

    class UnionFind{

        int[] parents;

        public UnionFind(int n){
            parents = new int[n];
            for (int i = 0; i <n ; i++) {
                parents[i] = i;
            }
        }

        public void union(int x,int y){
            int rootX = find(x);
            int rootY = find(y);
            if(rootX!=rootY){
                parents[rootX] = rootY;
            }
        }

        public int find(int x){
            while (parents[x]!=x){
                parents[x] = parents[parents[x]];
                x = parents[x];
            }
            return x;
        }

        public boolean isConnect(int x,int y){
            return find(x) == find(y);
        }

    }


    public int[] findRedundantConnection(int[][] edges) {
        int nodeNum = edges.length;
        UnionFind unionFind = new UnionFind(nodeNum+1);
        for (int i = 0; i <nodeNum ; i++) {
            if(unionFind.find(edges[i][0])!= unionFind.find(edges[i][1])){
                unionFind.union(edges[i][0],edges[i][1]);
            }else {
                return edges[i];
            }
        }
        return new int[0];
    }

    public static void main(String[] args) {
        FindRedundantConnection findRedundantConnection = new FindRedundantConnection();
        int[][] edges = {
                {1,2},
                {2,3},
                {3,4},
                {1,4},
                {1,5}
        };
        System.out.println(Arrays.toString(findRedundantConnection.findRedundantConnection(edges)));
    }

}