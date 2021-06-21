package DFS;

public class NumRookCapture {

    private int[] getRPosition(char[][] grid){
        for (int i = 0; i <grid.length ; i++) {
            for (int j = 0; j <grid[0].length ; j++) {
                if(grid[i][j] == 'R'){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{0,0};
    }


    public int numRookCaptures(char[][] board) {
        int[] RPosition = getRPosition(board);
        //找到R的位置后分别向四个方向找能捕获的pawn
        int[][] direction = {
                {-1,0},
                {1,0},
                {0,-1},
                {0,1}
        };
        int result = 0;
        for (int i = 0; i <direction.length ; i++) {
            int x = RPosition[0];
            int y = RPosition[1];
            while (x>=0 && y>=0 && x<board.length && y<board[0].length){
                if(board[x][y] == 'B')break;
                else if(board[x][y]=='p'){
                    result++;
                    break;
                }
                x += direction[i][0];
                y += direction[i][1];
            }
        }
        return result;
    }


    public static void main(String[] args) {
        NumRookCapture numRookCapture = new NumRookCapture();
        char[][] grid = {
                {'.','.','.','.','.','.','.','.'},
                {'.','.','.','p','.','.','.','.'},
                {'.','.','.','p','.','.','.','.'},
                {'p','p','.','R','.','p','B','.'},
                {'.','.','.','.','.','.','.','.'},
                {'.','.','.','B','.','.','.','.'},
                {'.','.','.','p','.','.','.','.'},
                {'.','.','.','.','.','.','.','.'}
        };
        System.out.println(numRookCapture.numRookCaptures(grid));
    }

}