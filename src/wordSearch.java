/**
 * Created by zero on 2019/8/14.
 */
public class wordSearch {
    public static boolean exist(char[][] board, String word)
    {
        int row = board.length;
        int col = board[0].length;
        boolean[][] visited = new boolean[row][col];
        //TODO
        for(int i = 0;i<row;i++)
        {
            for(int j = 0;j<col;j++)
            {
                if(dfs(board,word,i,j,visited,0))return true;
            }
        }
        return false;
    }

    public static boolean dfs(char[][] board,String word,int i,int j,boolean[][] visited,int index)
    {
        if(i<0||j<0||i>board.length-1||j>board[0].length-1)return false;
        if(board[i][j]==word.charAt(index)&&!visited[i][j])
        {
            visited[i][j] = true;
            if(index==word.length()-1)return true;
            boolean rs = (dfs(board,word,i+1,j,visited,index+1)
            ||dfs(board,word,i-1,j,visited,index+1)
            ||dfs(board,word,i,j+1,visited,index+1)
            ||dfs(board,word,i,j-1,visited,index+1));
            visited[i][j] = false;
            return rs;
        }
        return false;
    }

    public static void main(String[] args)
    {
        char[][] board = {{'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}};
        System.out.println(exist(board,"ABCB"));
    }

}
