package DFS;

public class LandNums {


    private int row;
    private int col;

    private void deepLandSearch(char[][] grid,int i,int j){
        if(i>=0&&i<row&&j>=0&&j<col&&grid[i][j] == '1'){
            grid[i][j] = '2';
            deepLandSearch(grid,i-1,j);
            deepLandSearch(grid,i,j-1);
            deepLandSearch(grid,i+1,j);
            deepLandSearch(grid,i,j+1);
        }
    }

    public int numIslands(char[][] grid) {
        row = grid.length;
        col = grid[0].length;
        int landNum = 0;
        for(int i = 0;i<grid.length;i++){
            for(int j = 0;j<grid[0].length;j++){
                if(grid[i][j] == '1'){
                    deepLandSearch(grid,i,j);
                    landNum++;
                }
            }
        }
        return landNum;
    }

    public static void main(String[] args) {
        char[][] array = {
                {'1','1','0','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','1'}
        };
        LandNums landNums = new LandNums();
        int result =  landNums.numIslands(array);
        System.out.println(result);
    }
}
