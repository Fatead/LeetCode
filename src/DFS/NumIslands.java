package DFS;

public class NumIslands {

    int num = 0;


    private void dfs(char[][] grid, int x, int y){
        if(x<0 || y<0 || x>=grid.length ||y>=grid[0].length){
            return;
        }
        if(grid[x][y] == '0'){
            return;
        }
        grid[x][y] = '0';
        dfs(grid,x+1,y);
        dfs(grid,x-1,y);
        dfs(grid,x,y+1);
        dfs(grid,x,y-1);
    }

    public int numIslands(char[][] grid) {
        for (int i = 0; i <grid.length ; i++) {
            for (int j = 0; j <grid[0].length ; j++) {
                if(grid[i][j] == '1'){
                    num++;
                    dfs(grid,i,j);
                }
            }
        }
        return num;
    }

    public static void main(String[] args) {
        NumIslands numIslands = new NumIslands();
        char[][] grid = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'},
        };
        System.out.println(numIslands.numIslands(grid));
    }

}
