package array;

public class SurroundRegion {

    private int m;
    private int n;

    private void dfs(char[][] board,int i,int j){
        if(i<0||j<0||i>=m||j>=n||board[i][j] == 'X'||board[i][j] == '#'){
            return;
        }
        board[i][j] = '#';
        dfs(board,i-1,j);
        dfs(board,i,j-1);
        dfs(board,i+1,j);
        dfs(board,i,j+1);
    }

    public void solve(char[][] board) {
        m = board.length;
        if(m==0)return;
        n = board[0].length;
        for(int i = 0;i<m;i++){
            for(int j = 0;j<n;j++){
                boolean isEdge = (i==0||j==0||i==m-1||j==n-1);
                if(isEdge&&board[i][j]=='O'){
                    dfs(board,i,j);
                }
            }
        }
        for(int i = 0;i<m;i++){
            for(int j = 0;j<n;j++){
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }else if(board[i][j] == '#'){
                    board[i][j] = 'O';
                }
            }
        }
    }


}
