package DFS;

/**
 * 寻找最小路径和，使用DFS超时
 */
public class MinPathSum {

    int minPath = Integer.MAX_VALUE;

    private void dfs(int[][] grid, int x, int y, int sum){
        if(x >= grid.length || y >= grid[0].length)return;
        sum += grid[x][y];
        if(x == grid.length-1 && y == grid[0].length-1){
            minPath = Math.min(minPath,sum);
            return;
        }
        dfs(grid,x+1,y, sum);
        dfs(grid,x,y+1, sum);
    }


    public int minPathSum(int[][] grid) {
        dfs(grid,0,0,0);
        return minPath;
    }


    public int minPathSum2(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for (int i = 1; i <grid.length ; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for (int i = 1; i <grid[0].length ; i++) {
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }
        for (int i = 1; i <grid.length ; i++) {
            for (int j = 1; j < grid[0].length ; j++) {
                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[grid.length-1][grid[0].length-1];
    }

    public static void main(String[] args) {
        MinPathSum minPathSum = new MinPathSum();
        int[][] grid = {
                {1,3,1},
                {1,5,1},
                {4,2,1}
        };
        System.out.println(minPathSum.minPathSum(grid));
    }

}