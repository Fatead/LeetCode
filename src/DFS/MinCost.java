package DFS;

public class MinCost {

    int min = Integer.MAX_VALUE;

    private void dfs(int[][] matrix,boolean[][] flagMatrix, int x, int y, int cost){
        if(x<0 || y<0 || x>=matrix.length || y>=matrix[0].length)return;
        if(matrix[x][y] == 2 || flagMatrix[x][y])return;
        if(cost>=min)return;
        if(x!=0 || y!=0){
            if(matrix[x][y] == 0){
                cost += 2;
            }else {
                cost += 1;
            }
        }
        if(x == matrix.length-1 && y == matrix[0].length-1){
            min = Math.min(min,cost);
        }
        flagMatrix[x][y] = true;
        dfs(matrix,flagMatrix,x+1,y,cost);
        dfs(matrix,flagMatrix,x-1,y,cost);
        dfs(matrix,flagMatrix,x,y+1,cost);
        dfs(matrix,flagMatrix,x,y-1,cost);
        flagMatrix[x][y] = false;
    }

    private int minCost(int[][] matrix){
        boolean[][] flagMatrix = new boolean[matrix.length][matrix[0].length];
        dfs(matrix,flagMatrix,0,0,0);
        return min;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1,1,1,1,0},
                {0,1,0,1,0},
                {1,1,2,1,1},
                {0,2,0,0,1}
        };
        MinCost minCost = new MinCost();
        System.out.println(minCost.minCost(matrix));
    }

}
