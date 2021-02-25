package array;

public class ProjectionArea {

    public int projectionArea(int[][] grid) {
        int vertical = 0;
        int left = 0;
        int right = 0;
        for (int i = 0; i <grid.length ; i++) {
            int leftMax = 0;
            for (int j = 0; j <grid[0].length ; j++) {
                leftMax = Math.max(leftMax,grid[i][j]);
                if(grid[i][j]!=0){
                    vertical++;
                }
            }
            left+=leftMax;
        }
        for (int i = 0; i <grid[0].length ; i++) {
            int rightMax = 0;
            for (int j = 0; j <grid.length ; j++) {
                rightMax = Math.max(rightMax,grid[j][i]);
            }
            right+=rightMax;
        }
        return vertical+left+right;
    }

    public static void main(String[] args) {
        ProjectionArea projectionArea = new ProjectionArea();
        int[][] grid ={
                {1,2},
                {3,4}
        };
        System.out.println(projectionArea.projectionArea(grid));
    }

}