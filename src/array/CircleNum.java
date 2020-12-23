package array;

public class CircleNum {

    public static class UnionFind{

        int count;
        int[] parent;
        int[] size;

        public UnionFind(int n){
            this.count = n;
            parent = new int[n];
            size = new int[n];
            for(int i = 0;i<n;i++){
                parent[i] = i;
                size[i] = i;
            }
        }

        public void union(int p,int q){
            int rootP = find(p);
            int rootQ = find(q);
            if(rootP == rootQ)return;
            if(size[rootP] > size[rootQ]){
                parent[rootQ] = rootP;
                size[rootQ] += size[rootP];
            }else {
                parent[rootP] = rootQ;
                size[rootP] += size[rootQ];
            }
            count--;
        }


        public int find(int x){
            while(parent[x]!=x){
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        public int count(){
            return count;
        }

    }


    public int findCircleNum(int[][] M) {
        UnionFind unionFind = new UnionFind(M.length);
        for(int i = 0;i<M.length;i++){
            for(int j = i+1;j<M[0].length;j++){
                if(M[i][j] == 1){
                    unionFind.union(i,j);
                }
            }
        }
        return unionFind.count;
    }


    public static void main(String[] args) {
        CircleNum circleNum = new CircleNum();
        int[][] M = {
                {1,1,0},
                {1,1,0},
                {0,0,1}
        };
        System.out.println(circleNum.findCircleNum(M));
    }

}
