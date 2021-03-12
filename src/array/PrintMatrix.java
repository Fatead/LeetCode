package array;

import java.util.Arrays;

public class PrintMatrix {

    public int[] spiralOrder(int[][] matrix) {
        int[] result  = new int[matrix.length*matrix[0].length];
        boolean[][] flagMatrix = new boolean[matrix.length][matrix[0].length];
        int position = 0;
        int posX = 0;
        int posY = 0;
        int count = 0;
        result[count++] = matrix[posX][posY];
        flagMatrix[posX][posY] = true;
        for (; count < result.length;) {
            if(position == 0){
                if(posY<matrix[0].length-1 && !flagMatrix[posX][posY+1]){
                    posY++;
                    result[count++] = matrix[posX][posY];
                    flagMatrix[posX][posY] = true;
                }else {
                    position = 1;
                }
            }else if(position ==1){
                if(posX<matrix.length-1 && !flagMatrix[posX+1][posY]){
                    posX++;
                    result[count++] = matrix[posX][posY];
                    flagMatrix[posX][posY] = true;
                }else {
                    position = 2;
                }
            }else if(position == 2){
                if(posY>0 && !flagMatrix[posX][posY-1]){
                    posY--;
                    result[count++] = matrix[posX][posY];
                    flagMatrix[posX][posY] = true;
                }else {
                    position = 3;
                }
            }else if(position == 3){
                if(posX>0 && !flagMatrix[posX-1][posY]){
                    posX--;
                    result[count++] = matrix[posX][posY];
                    flagMatrix[posX][posY] = true;
                }else {
                    position = 0;
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        PrintMatrix printMatrix = new PrintMatrix();
        int[] result = printMatrix.spiralOrder(matrix);
        System.out.println(Arrays.toString(result));
    }
}
