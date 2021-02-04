package array;

public class MagicSquaresInside {

    private boolean isMagic(int[][] grid,int x,int y){
        int target = 0;
        for (int i = x; i <x+3 ; i++) {
            target += grid[i][y];
        }
        //验证每行
        for (int i = x; i <x+3 ; i++) {
            int sum = 0;
            for (int j = y; j <y+3 ; j++) {
                sum+=grid[i][j];
            }
            if(sum!=target)return false;
        }
        //验证每列
        for (int j = y; j <y+3 ; j++) {
            int sum = 0;
            for (int i = x; i <x+3 ; i++) {
                sum+=grid[i][j];
            }
            if(sum!=target)return false;
        }
        //验证对角线
        int sumX = 0,sumY = 0;
        for (int i = 0; i <3 ; i++) {
            sumX += grid[x+i][y+i];
            sumY += grid[x+2-i][y+i];
        }
        int[] num = new int[9];
        //验证是否从1到9
        for (int i = x; i <x+3 ; i++) {
            for (int j = y; j <y+3 ; j++) {
                if(grid[i][j]<1||grid[i][j]>9)return false;
                if(num[grid[i][j]-1]==0){
                    num[grid[i][j]-1] = 1;
                }else {
                    return false;
                }
            }
        }
        return sumX == target && sumY == target;
    }

    public int numMagicSquaresInside(int[][] grid) {
        int height = grid.length;
        int width = grid[0].length;
        if(height<3||width<3)return 0;
        int result = 0;
        for (int i = 0; i <height-2 ; i++) {
            for (int j = 0; j <width-2 ; j++) {
                if(isMagic(grid,i,j)){
                    result++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        MagicSquaresInside magicSquaresInside = new MagicSquaresInside();
        int[][] grid = {
                {5,5,5},
                {5,5,5},
                {5,5,5}
        };
        System.out.println(magicSquaresInside.numMagicSquaresInside(grid));
    }

}
