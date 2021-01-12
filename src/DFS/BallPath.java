package DFS;

public class BallPath {

    int totalNum = 0;

    private void dfs(int m,int n,int N,int i,int j){
        if(i<0 || j<0 || i>=m|| j>=n){
            totalNum++;
            return;
        }
        if(N<=0)return;
        dfs(m,n,N-1,i-1,j);
        dfs(m,n,N-1,i,j-1);
        dfs(m,n,N-1,i+1,j);
        dfs(m,n,N-1,i,j+1);
    }


    /**
     * 暴力搜索，超时
     */
    public int findPaths2(int m, int n, int N, int i, int j) {
        dfs(m,n,N,i,j);
        return totalNum;
    }


    /**
     * 三维数组的动态规划，状态机
     */
    public int findPaths(int m, int n, int N, int i, int j){
        long[][][] dp = new long[m+2][n+2][N+1];
        int mod = 1000000007;
        if(N == 0)return 0;
        for(int k = 0;k<m+2;k++){
            dp[k][0][0] = 1;
            dp[k][n+1][0] = 1;
        }
        for(int k = 0;k<n+2;k++){
            dp[0][k][0] = 1;
            dp[m+1][k][0] = 1;
        }
        for(int k = 1;k<=N;k++){
            for(int b = 1;b<m+1;b++){
                for(int c = 1;c<n+1;c++){
                    dp[b][c][k] = (dp[b-1][c][k-1] + dp[b][c-1][k-1] + dp[b+1][c][k-1] + dp[b][c+1][k-1])%mod;
                }
            }
        }
        int sum = 0;
        for(int k = 1;k<=N;k++){
            sum += dp[i+1][j+1][k];
            sum%=mod;
        }
        return sum;
    }


    public static void main(String[] args) {
        BallPath ballPath = new BallPath();
        System.out.println(ballPath.findPaths(1,3,3,0,1));
    }
}
