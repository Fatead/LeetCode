package hashTable;

import java.util.HashSet;

public class ValidSudoku {

    public boolean isValidSudoku(char[][] board) {
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

    public static void main(String[] args) {
        ValidSudoku validSudoku = new ValidSudoku();
        char[][] array = {
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
        System.out.println(validSudoku.isValidSudoku(array));
    }
}
