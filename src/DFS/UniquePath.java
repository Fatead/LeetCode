package DFS;

/**
 * No.62 不同路径
 * 使用DFS算法超时
 */
public class UniquePath {

    int count = 0;

    private void dfs(int m, int n,int x, int y){
        if(x == m && y == n){
            count++;
            return;
        }
        if(x>m || y>n)return;
        dfs(m,n,x+1,y);
        dfs(m,n,x,y+1);
    }

    public int uniquePaths(int m, int n) {
        dfs(m,n,1,1);
        return count;
    }


    public int uniquePaths2(int m, int n) {
        int[][] matrix = new int[m][n];
        for (int i = 0; i <m ; i++) {
            matrix[i][0] = 1;
        }
        for (int i = 0; i <n ; i++) {
            matrix[0][i] = 1;
        }
        for (int i = 1; i <m ; i++) {
            for (int j = 1; j <n ; j++) {
                matrix[i][j] = matrix[i-1][j] + matrix[i][j-1];
            }
        }
        return matrix[m-1][n-1];
    }

    public static void main(String[] args) {
        UniquePath uniquePath = new UniquePath();
        System.out.println(uniquePath.uniquePaths2(3,7));
    }

}