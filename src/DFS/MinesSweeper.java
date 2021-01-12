package DFS;

import java.util.Arrays;

public class MinesSweeper {

    private boolean hasNeighborMine(char[][] board,int i,int j){
        if((i-1>=0 && j-1>=0 && board[i-1][j-1] == 'M') || (i-1>=0 && board[i-1][j] == 'M') || (j-1>=0 && board[i][j-1] == 'M')){
            return true;
        }
        if((i+1<board.length && j+1<board[0].length && board[i+1][j+1] == 'M') || (i+1<board.length && board[i+1][j] == 'M') || (j+1<board.length && board[i][j+1] == 'M')){
            return true;
        }
        return (i + 1 < board.length && j - 1 >= 0 && board[i + 1][j - 1] == 'M') || (i - 1 >= 0 && j + 1 < board[0].length && board[i - 1][j + 1] == 'M');
    }

    private int countNum(char[][] board,int posX,int posY){
        int count = 0;
        for(int i = posX-1;i<=posX+1;i++){
            for(int j = posY-1;j<=posY + 1;j++){
                if(i<0||i>=board.length || j<0||j>=board[0].length||(i == posX&&j == posY)){
                    continue;
                }
                if(board[i][j] == 'M'){
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] board,int posX,int posY){
        if(board[posX][posY] != 'E'){
            return;
        }
        if(hasNeighborMine(board,posX,posY)){
            board[posX][posY] = (char) ((char) countNum(board,posX,posY) + '0');
        }else {
            board[posX][posY] = 'B';
            for(int i = posX-1;i<=posX+1;i++){
                for(int j = posY-1;j<=posY + 1;j++){
                    if(i<0||i>=board.length || j<0||j>=board[0].length||(i == posX&&j == posY)){
                        continue;
                    }
                    dfs(board,i,j);
                }
            }
        }
    }


    public char[][] updateBoard(char[][] board, int[] click) {
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        if(hasNeighborMine(board,click[0],click[1])){
            board[click[0]][click[1]] = (char) (countNum(board,click[0],click[1]) + '0');
        }else {
            board[click[0]][click[1]] = 'B';
            for(int i = click[0]-1;i<=click[0]+1;i++){
                for(int j = click[1]-1;j<=click[1] + 1;j++){
                    if(i<0||i>=board.length || j<0||j>=board[0].length||(i== click[0]&&j == click[1])){
                        continue;
                    }
                    dfs(board,i,j);
                }
            }
        }
        return board;
    }


    public static void main(String[] args) {
        char[][] board = {
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'M', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'}
        };
        int[] click = {3,0};
        MinesSweeper minesSweeper = new MinesSweeper();
        char[][] result = minesSweeper.updateBoard(board,click);
        System.out.println(Arrays.deepToString(result));
    }
}
