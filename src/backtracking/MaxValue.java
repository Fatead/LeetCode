package backtracking;

public class MaxValue {

    int maxValue = 0;

    private void dfs(int[][] grid,int x,int y,int currValue){
        if(x >= grid.length || y>=grid[0].length){
            maxValue = Math.max(maxValue,currValue);
            return;
        }
        if(x == grid.length-1 && y ==grid[0].length-1 ){
            maxValue = Math.max(maxValue,currValue);
        }
        dfs(grid,x+1,y,currValue+grid[x][y]);
        dfs(grid,x,y+1,currValue+grid[x][y]);
    }

    /**
     * dfs超时
     */
    public int maxValue(int[][] grid) {
        dfs(grid,0,0,0);
        return maxValue;
    }

    private int maxValue2(int[][] grid){
        if(grid.length==0||grid[0].length==0)return 0;
        int[][] dp = new int[grid.length][grid[0].length];
        for (int i = 0; i <dp.length ; i++) {
            if(i == 0){
                dp[i][0] = grid[0][0];
            }else {
                dp[i][0] = dp[i-1][0] + grid[i][0];
            }
        }
        for (int i = 1; i <dp[0].length ; i++) {
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }

        for (int i = 1; i <dp.length ; i++) {
            for (int j = 1; j <dp[0].length ; j++) {
                dp[i][j] = Math.max(dp[i-1][j] + grid[i][j],dp[i][j-1] + grid[i][j]);
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }

    public static void main(String[] args) {
        MaxValue maxValue = new MaxValue();
        int[][] grid = {
                {1,3,1},
                {4,5,6}
        };
        System.out.println(maxValue.maxValue(grid));
    }

}