package UnionFind;

import java.util.HashSet;
import java.util.Set;

public class NumIslands {


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
            while (x!=parents[x]){
                parents[x] = parents[parents[x]];
                x = parents[x];
            }
            return x;
        }


        public boolean isConnect(int x,int y){
            return find(x) == find(y);
        }

    }

    int row;

    int col;


    private int node(int x,int y){
        return x*col + y;
    }

    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0)return 0;
        row = grid.length;
        col = grid[0].length;
        UnionFind unionFind = new UnionFind(row*col);
        for (int i = 0; i <row ; i++) {
            for (int j = 0; j <col ; j++) {
                if(grid[i][j] == '1'){
                    if(i>0 && grid[i-1][j]=='1'){
                        unionFind.union(node(i,j),node(i-1,j));
                    }
                    if(i<row-1 && grid[i+1][j]=='1'){
                        unionFind.union(node(i,j),node(i+1,j));
                    }
                    if(j>0 && grid[i][j-1]=='1'){
                        unionFind.union(node(i,j),node(i,j-1));
                    }
                    if(j<col-1 && grid[i][j+1]=='1'){
                        unionFind.union(node(i,j),node(i,j+1));
                    }
                }
            }
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i <row ; i++) {
            for (int j = 0; j <col ; j++) {
                if(grid[i][j] == '1'){
                    set.add(unionFind.find(node(i,j)));
                }
            }
        }
        return set.size();
    }

    public static void main(String[] args) {
        char[][] grid = {
                {'1','1','0','0','1'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'1','0','0','1','1'}
        };
        NumIslands numIslands = new NumIslands();
        System.out.println(numIslands.numIslands(grid));
    }

}