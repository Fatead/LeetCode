package array;

import java.util.Arrays;

public class SetMatrixZero {

    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] row = new int[m];
        int[] col = new int[n];
        for(int i = 0;i<m;i++){
            for(int j = 0;j<n;j++){
                if(matrix[i][j] == 0){
                    row[i] = 1;
                    col[j] = 1;
                }
            }
        }
        for(int i =0;i<m;i++){
            if(row[i] == 1){
                for(int j = 0;j<n;j++){
                    matrix[i][j] = 0;
                }
            }
        }
        for(int j = 0;j<n;j++){
            if(col[j] == 1){
                for(int i = 0;i<m;i++){
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1,1,1},
                {1,0,1},
                {1,1,1}
        };
        SetMatrixZero setMatrixZero = new SetMatrixZero();
        setMatrixZero.setZeroes(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }
}
