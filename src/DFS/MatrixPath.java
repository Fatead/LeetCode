package DFS;

public class MatrixPath {

    boolean ans = false;

    private void dfs(char[][] board,String word,int posX,int posY,int index,boolean[][] visited){
        if(ans||posX<0||posY<0||posX>=board.length||posY>=board[0].length||board[posX][posY]!=word.charAt(index)){
            return;
        }
        if(index == word.length()-1){
            ans = true;
            return;
        }
        for (int i = posX-1; i <=posX+1 ; i+=2) {
            if(i<0||i>=board.length||visited[i][posY])continue;
            visited[i][posY] = true;
            dfs(board,word,i,posY,index+1,visited);
            visited[i][posY] = false;
        }
        for (int i = posY-1; i <=posY+1 ; i+=2) {
            if(i<0||i>=board[0].length||visited[posX][i])continue;
            visited[posX][i] = true;
            dfs(board,word,posX,i,index+1,visited);
            visited[posX][i] = false;
        }
    }


    public boolean exist(char[][] board, String word) {
        if(board.length==0||board[0].length==0)return false;
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i <board.length; i++) {
            for (int j = 0; j <board[0].length ; j++) {
                visited[i][j] = true;
                dfs(board,word,i,j,0,visited);
                visited[i][j] = false;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        MatrixPath matrixPath = new MatrixPath();
        char[][] matrix = {
                {'A','B'}
        };
        boolean result =  matrixPath.exist(matrix,"AB");
        System.out.println(result);
    }

}
