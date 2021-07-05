package DFS;

public class NumEnclaves {

    private void dfs(int[][] grid, boolean[][] matrix, int x,int y){
        if(x<0 || y<0 || x>=grid.length || y>=grid[0].length){
            return;
        }
        if(matrix[x][y])return;
        if(grid[x][y] == 1){
            matrix[x][y] = true;
            //向四个方向搜索
            dfs(grid, matrix, x-1,y);
            dfs(grid, matrix, x+1,y);
            dfs(grid, matrix, x,y+1);
            dfs(grid, matrix, x,y-1);
        }
    }

    /**
     * 从边缘的1开始，标记可以到达的所有节点
     */
    public int numEnclaves(int[][] grid) {
        boolean[][] matrix = new boolean[grid.length][grid[0].length];
        for (int i = 0; i <matrix.length ; i++) {
            if(grid[i][0] == 1){
                dfs(grid,matrix,i,0);
            }
            if(grid[i][grid[0].length-1] == 1){
                dfs(grid,matrix,i,grid[0].length-1);
            }
        }
        for(int j = 0; j <matrix[0].length; j++){
            if(grid[0][j] == 1){
                dfs(grid,matrix,0,j);
            }
            if(grid[grid.length-1][j] == 1){
                dfs(grid,matrix,grid.length-1,j);
            }
        }
        int count = 0;
        for (int i = 0; i <grid.length ; i++) {
            for (int j = 0; j <grid[0].length ; j++) {
                if(grid[i][j] == 1 && !matrix[i][j]){
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        NumEnclaves numEnclaves = new NumEnclaves();
        int[][] grid = {
                {0,0,0,0},
                {1,0,1,0},
                {0,1,1,0},
                {0,0,0,0}
        };
        System.out.println(numEnclaves.numEnclaves(grid));
    }

}