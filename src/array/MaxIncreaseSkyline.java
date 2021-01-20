package array;

public class MaxIncreaseSkyline {

    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int[] horizontalMax = new int[grid.length];
        int[] verticalMax = new int[grid[0].length];
        for(int i = 0;i<grid.length;i++){
            int max = 0;
            for (int j = 0; j <grid[0].length ; j++) {
                max = Math.max(max,grid[i][j]);
            }
            horizontalMax[i] = max;
        }
        for (int i = 0; i <grid[0].length ; i++) {
            int max = 0;
            for (int j = 0; j <grid.length ; j++) {
                max = Math.max(grid[j][i],max);
            }
            verticalMax[i] = max;
        }
        int increase = 0;
        for (int i = 0; i <grid.length ; i++) {
            for (int j = 0; j <grid[0].length ; j++) {
                increase += Math.min(horizontalMax[i],verticalMax[j])-grid[i][j];
            }
        }
        return increase;
    }

    public static void main(String[] args) {
        MaxIncreaseSkyline maxIncreaseSkyline = new MaxIncreaseSkyline();
        int[][] grid = {
                {3,0,8,4},
                {2,4,5,7},
                {9,2,6,3},
                {0,3,1,0}
        };
        System.out.println(maxIncreaseSkyline.maxIncreaseKeepingSkyline(grid));
    }

}
