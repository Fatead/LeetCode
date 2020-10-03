package tree;

/**
 * 并查集
 */
public class UnionFind {

    //记录连通分量
    private int count;

    private int[] parent;

    private int[] size;

    public UnionFind(int n){
        this.count = n;
        parent = new int[n];
        size = new int[n];
        //父节点指针指向自己
        for(int i = 0;i<n;i++){
            parent[i] = i;
            size[i] = 1;
        }
    }

    /**
     * 找到某个结点的根节点，有路径压缩，使树的高度始终保持为常数
     */
    private int find(int x){
        while(parent[x]!=x){
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    //连接结点p和q
    public void union(int p,int q){
        int rootP = find(p);
        int rootQ = find(q);
        if(rootP == rootQ)return;
        //将小的树加到大的树下面，会比较平衡
        if(size[rootP] > size[rootQ]){
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }else {
            parent[rootP] = rootQ;
            size[rootQ] += rootP;
        }
        count--;
    }

    public boolean connected(int p,int q){
        int rootP = find(p);
        int rootQ = find(q);
        return rootP == rootQ;
    }

    public int count(){
        return count;
    }

    public static void main(String[] args) {
        UnionFind unionFind = new UnionFind(10);
        unionFind.union(1,6);
        unionFind.union(1,3);
        System.out.println(unionFind.connected(3,6));
    }

}
