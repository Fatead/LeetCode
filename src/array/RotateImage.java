package array;

import java.util.Arrays;

public class RotateImage {

    public void rotate(int[][] matrix) {
        int n = matrix[0].length;
        if(n == 0)return;
        //先做矩阵的转置
        for(int i = 0;i<n;i++){
            for(int j =0;j<i;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for(int i =0;i<n;i++){
            for(int j = 0;j<n/2;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n-j-1];
                matrix[i][n-j-1] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        RotateImage rotateImage = new RotateImage();
        rotateImage.rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));

    }
}
