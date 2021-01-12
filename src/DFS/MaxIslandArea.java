package DFS;

public class MaxIslandArea {

    int length;

    private void dfs(int[][] grid,boolean[][] matrix,int posX,int poxY){
        if(posX<0||poxY<0||posX>=matrix.length||poxY>=matrix[0].length||matrix[posX][poxY]||grid[posX][poxY]==0)return;
        length++;
        matrix[posX][poxY] = true;
        dfs(grid,matrix,posX-1,poxY);
        dfs(grid,matrix,posX+1,poxY);
        dfs(grid,matrix,posX,poxY-1);
        dfs(grid,matrix,posX,poxY+1);
    }

    public int maxAreaOfIsland(int[][] grid) {
        boolean[][] flagMatrix = new boolean[grid.length][grid[0].length];
        int max = 0;
        for(int i = 0;i<grid.length;i++){
            for(int j = 0;j<grid[0].length;j++){
                //进入搜索状态
                length = 0;
                if(!flagMatrix[i][j] && grid[i][j] == 1){
                    dfs(grid,flagMatrix,i,j);
                }
                max = Math.max(max,length);
            }
        }
        return max;
    }


    public static void main(String[] args) {

    }
}
