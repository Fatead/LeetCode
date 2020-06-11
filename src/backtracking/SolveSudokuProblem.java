package backtracking;

import java.util.Arrays;
import java.util.HashSet;

public class SolveSudokuProblem {


    private boolean backTrack(char[][] board,int row,int col){
        if(col == 9){
            return backTrack(board,row+1,0);
        }
        //已经找到答案，返回结果
        if(row == 9)return true;
        if(board[row][col]!='.')return backTrack(board,row,col+1);
        for(int i = 1;i<=9;i++){
            board[row][col] = (char)(i+'0');
            if(!isValidSudoku(board)){
                board[row][col] = '.';
                continue;
            }
            if(backTrack(board,row,col+1))return true;
            //如果下一个位置没有解，则撤销本次的选择
            board[row][col] = '.';
        }
        return false;
    }

    private boolean isValidSudoku(char[][] board) {
        //对行,列验证
        for(int i = 0;i<9;i++){
            HashSet<Character> rowSet = new HashSet<>();
            HashSet<Character> colSet = new HashSet<>();
            for(int j = 0;j<9;j++){
                if(board[i][j]!='.'&&!rowSet.contains(board[i][j]))rowSet.add(board[i][j]);
                else if(board[i][j]!='.')return false;
                if(board[j][i]!='.'&&!colSet.contains(board[j][i]))colSet.add(board[j][i]);
                else if(board[j][i]!='.')return false;
            }
        }
        //对小矩形验证
        for(int k =0;k<9;k++){
            HashSet<Character> charSet = new HashSet<>();
            for(int i = k/3*3 ;i<k/3*3+3;i++){
                for(int j = k%3*3; j<k%3*3+3; j++){
                    if(board[i][j]!='.'&&!charSet.contains(board[i][j]))charSet.add(board[i][j]);
                    else if(board[i][j]!='.')return false;
                }
            }
        }
        return true;
    }

    public void solveSudoku(char[][] board) {
        backTrack(board,0,0);
    }


    public boolean solveSudoku2(char[][] board,int row,int col){
        if(col == 9)return solveSudoku2(board,row+1,0);
        if(row == 9)return true;
        if(board[row][col]!='.')return solveSudoku2(board,row,col+1);
        for(int i = '1';i<='9';i++){
            board[row][col] = (char)i;
            if(!isValidSudoku(board)){
                board[row][col] = '.';
                continue;
            }
            if(solveSudoku2(board,row,col+1)){
                return true;
            }
            board[row][col] = '.';
        }
        return false;
    }


    public static void main(String[] args) {
        SolveSudokuProblem solveSudokuProblem = new SolveSudokuProblem();
        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        solveSudokuProblem.solveSudoku2(board,0,0);
        System.out.println(Arrays.deepToString(board));
    }
}
