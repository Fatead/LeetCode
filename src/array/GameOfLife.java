package array;

public class GameOfLife {

    private int getNumOfCell(int[][] board,int m,int n){
        int num = 0;
        int row = board.length;
        int col = board[0].length;
        for(int i = m -1;i<=m+1;i++){
            for(int j = n-1;j<=n+1;j++){
                if(i<0||j<0||(i == m&&j == n)||i>=row||j>=col)continue;
                if(board[i][j] == 1)num++;
            }
        }
        return num;
    }

    public void gameOfLife(int[][] board) {
        int row = board.length;
        if(row == 0)return;
        int col = board[0].length;
        if(col == 0)return;
        int[][] originBoard = new int[row][col];
        for(int i = 0;i<row;i++){
            System.arraycopy(board[i], 0, originBoard[i], 0, col);
        }
        for(int i = 0;i<row;i++){
            for(int j = 0;j<col;j++){
                int num = getNumOfCell(originBoard,i,j);
                if(num<2)board[i][j] = 0;
                else if(board[i][j] == 1 && num>3)board[i][j] = 0;
                else if(num == 3)board[i][j] = 1;
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] board = {
                {0,1,0},
                {0,0,1},
                {1,1,1},
                {0,0,0}
        };
        GameOfLife gameOfLife = new GameOfLife();
        gameOfLife.gameOfLife(board);
        for(int i = 0;i<board.length;i++){
            for(int j = 0;j<board[0].length;j++){
                //System.out.println(gameOfLife.getNumOfCell(board,i,j));
                //System.out.print(board[i][j] + " ");
            }
            //System.out.println();
        }
    }

}
