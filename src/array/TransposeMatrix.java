package array;

import java.util.Arrays;

public class TransposeMatrix {

    public int[][] transpose(int[][] A) {
        int[][] matrix = new int[A[0].length][A.length];
        for (int i = 0; i <A.length ; i++) {
            for (int j = 0; j <A[0].length ; j++) {
                matrix[j][i] = A[i][j];
            }
        }
        return matrix;
    }


    public static void main(String[] args) {
        TransposeMatrix transposeMatrix = new TransposeMatrix();
        int[][] matrix = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        int[][] result = transposeMatrix.transpose(matrix);
        System.out.println(Arrays.deepToString(result));
    }

}
