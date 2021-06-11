package array;

public class SurfaceArea {

    public int surfaceArea(int[][] grid) {
        int area = 0;
        int n = grid.length;
        for (int i = 0; i <n ; i++) {
            for (int j = 0; j <n ; j++) {
                int curr = grid[i][j];
                if(curr>0){
                    area += curr*4 + 2;
                    if(i>0){
                        area -= Math.min(curr,grid[i-1][j])*2;
                    }
                    if(j>0){
                        area-= Math.min(curr,grid[i][j-1])*2;
                    }
                }
            }
        }
        return area;
    }


    public static void main(String[] args) {
        SurfaceArea surfaceArea = new SurfaceArea();
        int[][] grid = {{1,2},{3,4}};
        System.out.println(surfaceArea.surfaceArea(grid));
    }


}