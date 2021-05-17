package UnionFind;

public class MinCostConnectPoints {

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




    public static void main(String[] args) {
        MinCostConnectPoints minCostConnectPoints = new MinCostConnectPoints();
        int[][] points = {
                {0,0},
                {2,2},
                {3,10},
                {5,2},
                {7,0}
        };
    }

}