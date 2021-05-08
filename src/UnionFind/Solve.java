package UnionFind;

import java.util.Arrays;

/**
 * NO.130 被围绕的区域
 * 并查集：同一个连通区域的所有点的根节点是同一个，将每个点映射成一个数字
 *
 */
public class Solve {

    int row = 0;
    int col = 0;

    class UnionFind{

        int[] parents;

        public UnionFind(int n) {
            parents = new int[n];
            for (int i = 0; i <parents.length ; i++) {
                //初始化所有节点的父节点指向自己
                parents[i] = i;
            }
        }

        public boolean isConnect(int x,int y){
            int rootX = find(x);
            int rootY = find(y);
            return rootX == rootY;
        }

        public void union(int x,int y){
            int rootX = find(x);
            int rootY = find(y);
            if(rootX!=rootY){
                //连接X和Y的父节点
                parents[rootX] = rootY;
            }
        }

        /**
         * 找到节点X的根节点
         */
        public int find(int x){
            //循环条件为父节点不等于子节点，也就是还没找到根节点
            while (parents[x]!=x){
                //当前节点父节点等于父节点的父节点
                parents[x] = parents[parents[x]];
                //调整当前节点
                x = parents[x];
            }
            return x;
        }

    }

    /**
     * 二维坐标到一维坐标的转化
     */
    private int node(int x,int y){
        return x*col + y;
    }

    public void solve(char[][] board) {
        //由于并查集我们一般用一维数组来记录，所以需要将二维坐标转化为一维坐标
        if(board == null || board.length==0)return;
        row = board.length;
        col = board[0].length;
        //初始化并查集，大小为原来矩阵的大小 + 1,就是原来的所有节点加上虚拟节点
        UnionFind unionFind = new UnionFind(row*col + 1);
        //初始化一个虚拟节点，边界上所有O节点的父节点都是这个虚拟节点
        int dummyNode = row*col;
        for (int i = 0; i <row; i++) {
            for (int j = 0; j <col ; j++) {
                if(board[i][j] == 'O'){
                    if(i == 0 || j == 0 || i == row-1 || j == col-1){
                        //将所有边界上的节点都与虚拟节点合并
                        unionFind.union(node(i,j),dummyNode);
                    }else {
                        //对于非边界上的O节点，需要和上下左右的O节点合并成一个联通区域
                        if(board[i-1][j] == 'O'){
                            unionFind.union(node(i,j),node(i-1,j));
                        }
                        if(board[i+1][j] == 'O'){
                            unionFind.union(node(i,j),node(i+1,j));
                        }
                        if(board[i][j-1] == 'O'){
                            unionFind.union(node(i,j),node(i,j-1));
                        }
                        if(board[i][j+1] == 'O'){
                            unionFind.union(node(i,j),node(i,j+1));
                        }
                    }
                }
            }
        }
        for (int i = 0; i <row ; i++) {
            for (int j = 0; j <col ; j++) {
                if(unionFind.isConnect(node(i,j),dummyNode)){
                    board[i][j] = 'O';
                }else {
                    board[i][j] = 'X';
                }
            }
        }
    }


    public static void main(String[] args) {
        Solve solve = new Solve();
        char[][] board = {
                {'X','X','X','X'},
                {'X','O','O','X'},
                {'X','X','O','X'},
                {'X','O','X','X'}
        };
        solve.solve(board);
        System.out.println(Arrays.deepToString(board));
    }

}
