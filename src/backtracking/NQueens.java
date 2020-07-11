package backtracking;

import java.util.ArrayList;
import java.util.List;

public class NQueens {

    int[] col;
    int[] main;
    int[] second;
    int[] queens;
    List<List<String>> output = new ArrayList<>();
    int n;


    private boolean check(int i,int j){
        return col[j] + main[n + i - j -1] + second[i + j] == 0;
    }

    private void placeQueen(int i,int j){
        queens[i] = j;
        col[j] = 1;
        main[n+i-j -1] = 1;
        second[i+j] = 1;
    }

    private void removeQueen(int i,int j){
        queens[i] = 0;
        col[j] = 0;
        main[n+i-j -1] = 0;
        second[i+j] = 0;
    }

    public void addOutput(){
        List<String> result = new ArrayList<>();
        for(int i = 0;i<n;i++){
            StringBuilder stringBuilder = new StringBuilder();
            for(int j = 0;j<n;j++){
                if(j == queens[i]){
                    stringBuilder.append("Q");
                }else {
                    stringBuilder.append(".");
                }
            }
            result.add(stringBuilder.toString());
        }
        output.add(result);
    }

    private void backTrack(int row){
        if(row >= n)return;
        for(int col = 0;col<n;col++){
            if(check(row,col)){
                placeQueen(row,col);
                if(row == n-1){
                    addOutput();
                }
                backTrack(row +1);
                removeQueen(row,col);
            }
        }
    }

    public List<List<String>> solveNQueens(int n) {
        col = new int[n];
        main = new int[2*n-1];
        second = new int[2*n-1];
        queens = new int[n];
        this.n = n;
        backTrack(0);
        return output;
    }

    public static void main(String[] args) {
        NQueens nQueens = new NQueens();
        List<List<String>> result =  nQueens.solveNQueens(8);
        System.out.println(result.size());
        System.out.println(result);
    }

}
