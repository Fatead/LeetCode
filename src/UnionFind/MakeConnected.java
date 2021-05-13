package UnionFind;
import java.util.HashSet;
import java.util.Set;

public class MakeConnected {

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

        public void Union(int x,int y){
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

    public int makeConnected(int n, int[][] connections) {
        if(connections.length<n-1){
            return -1;
        }
        UnionFind unionFind = new UnionFind(n);
        for (int[] connection : connections) {
            unionFind.Union(connection[0],connection[1]);
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i <n ; i++) {
            set.add(unionFind.find(i));
        }
        return set.size()-1;
    }

    public static void main(String[] args) {
        MakeConnected makeConnected = new MakeConnected();
        int[][] connections = {
                {1,5},
                {1,7},
                {1,2},
                {1,4},
                {3,7},
                {4,7},
                {3,5},
                {0,6},
                {0,1},
                {0,4},
                {2,6},
                {0,3},
                {0,2}
        };
        System.out.println(makeConnected.makeConnected(12,connections));
    }

}