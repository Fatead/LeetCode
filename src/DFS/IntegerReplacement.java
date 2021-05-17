package DFS;

public class IntegerReplacement {

    int minCount = Integer.MAX_VALUE;

    private void dfs(int n, int count){
        if(n == Integer.MAX_VALUE){
            minCount = 32;
            return;
        }
        if(n<0)return;
        if(n == 1){
            minCount = Math.min(minCount,count);
        }else if(n%2 == 1){
            dfs(n+1,count+1);
            dfs(n-1,count+1);
        }else {
            dfs(n/2,count+1);
        }
    }

    public int integerReplacement(int n) {
        dfs(n,0);
        return minCount;
    }

    public static void main(String[] args) {
        IntegerReplacement integerReplacement = new IntegerReplacement();
        System.out.println(integerReplacement.integerReplacement(2147483646));
    }

}