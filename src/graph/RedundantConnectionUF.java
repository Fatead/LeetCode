package graph;

public class RedundantConnectionUF {

    private int[] result = new int[2];

    class UnionFind{

        private int[] roots;

        public UnionFind(int size){
            roots = new int[size];
            //开始的时候将每个节点的父节点都指向自己
            for(int i = 0;i<size;i++){
                roots[i] = i;
            }
        }

        /**
         * 把两个节点连接起来
         */
        public void union(int p,int q){
            int rootP = find(p);
            int rootQ = find(q);
            if(rootP!=rootQ){
                roots[rootP] = rootQ;
            }else {
                //如果节点p和q本来就连接在一起，找到答案
                result[0] = p;
                result[1] = q;
            }
        }

        public int find(int x){
            if(roots[x] != x){
                roots[x] = find(roots[x]);
            }
            return roots[x];
        }

    }

    private int[] findRedundantConnection(int[][] edges){
        UnionFind unionFind = new UnionFind(edges.length + 1);
        for(int[] edge:edges){
            unionFind.union(edge[0],edge[1]);
        }
        return result;
    }


    public static void main(String[] args) {

    }

}