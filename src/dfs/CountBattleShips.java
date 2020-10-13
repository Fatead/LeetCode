package dfs;

public class CountBattleShips {

    private void dfs(char[][] board,int i,int j){
        if(i<0||j<0||i>=board.length||j>=board[0].length||board[i][j] == '.')return;
        board[i][j] = '.';
        dfs(board,i+1,j);
        dfs(board,i,j+1);
        dfs(board,i-1,j);
        dfs(board,i,j-1);
    }


    public int countBattleships(char[][] board) {
        int count = 0;
        for(int i = 0;i<board.length;i++){
            for(int j = 0;j<board[0].length;j++){
                if(board[i][j] == 'X'){
                    dfs(board,i,j);
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'X','.','.','X'},
                {'.','.','.','X'},
                {'.','.','.','X'}
        };
        CountBattleShips countBattleShips = new CountBattleShips();
        System.out.println(countBattleShips.countBattleships(board));
    }
}
