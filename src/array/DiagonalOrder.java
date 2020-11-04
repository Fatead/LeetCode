package array;

import java.util.Arrays;

public class DiagonalOrder {

    public int[] findDiagonalOrder(int[][] matrix) {
        int M = matrix.length;
        if(M == 0)return new int[0];
        int N = matrix[0].length;
        int[] result = new int[M*N];
        int count = 0;
        boolean flag = false;
        for(int i = 0;i<M + N -1 ;i++){
            for(int j = 0;j<i+1;j++){
                if(flag){
                    if(i-j>=0 && i-j<N && j<M){
                        result[count++] = matrix[j][i-j];
                    }
                }else {
                    if(i-j>=0&&i-j<M&&j<N)
                        result[count++] = matrix[i-j][j];
                }
            }
            flag = !flag;
        }
        return result;
    }


    public static void main(String[] args) {
        DiagonalOrder diagonalOrder = new DiagonalOrder();
        int[][] matrix = {
                {1,2,3},
                {4,5,6}
        };
        int[] result = diagonalOrder.findDiagonalOrder(matrix);
        System.out.println(Arrays.toString(result));
    }
}
