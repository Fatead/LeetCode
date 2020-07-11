package backtracking;

public class NQueens2 {

    int[] col;
    int[] queens;
    int[] main;
    int[] second;
    int n;
    int totalNum;
    int[][] array;


    private boolean check(int i,int j){
        return col[j] + main[n-1 + i-j]  + second[i+j] == 0;
    }

    private void placeQueen(int i,int j){
        col[j] = 1;
        queens[i] = j;
        main[n-1+i-j] = 1;
        second[i+j] = 1;
        array[i][j] = 1;
    }

    private void removeQueen(int i,int j){
        col[j] = 0;
        queens[i] = 0;
        main[n-1+i-j] = 0;
        second[i+j] = 0;
        array[i][j] = 0;
    }

    public void printArray(){
        for(int i = 0;i<n;i++){
            for(int j = 0;j<n;j++){
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("***********************");
    }


    public void backTrack(int row){
        if(row >= n)return;
        for(int col = 0;col<n;col++){
            if(check(row,col)){
                placeQueen(row,col);
                if(row == n-1){
                    totalNum ++;
                    printArray();
                }
                backTrack(row+1);
                removeQueen(row,col);
            }
        }
    }

    public int totalNQueens(int n) {
        col = new int[n];
        queens = new int[n];
        main = new int[2*n-1];
        second = new int[2*n-1];
        this.n = n;
        totalNum = 0;
        array = new int[n][n];
        backTrack(0);
        return totalNum;
    }

    public static void main(String[] args) {
        NQueens2 nQueens2 = new NQueens2();
        int result = nQueens2.totalNQueens(8);
        System.out.println(result);
    }
}
