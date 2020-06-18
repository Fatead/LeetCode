package array;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int m = matrix.length;
        if(m == 0)return result;
        int n = matrix[0].length;
        if(n == 0)return result;
        int direction = 0;
        boolean[][] boolMatrix = new boolean[m][n];
        int i = 0,j = 0;
        while(result.size()<m*n){
            result.add(matrix[i][j]);
            boolMatrix[i][j] = true;
            switch (direction){
                case 0:
                    if(j+1>=n|| boolMatrix[i][j + 1]){
                        direction = 1;
                        i++;
                        break;
                    }
                    j++;
                    break;
                case 1:
                    if(i+1>=m|| boolMatrix[i+1][j]){
                        direction = 2;
                        j--;
                        break;
                    }
                    i++;
                    break;
                case 2:
                    if(j-1<0|| boolMatrix[i][j-1]){
                        direction = 3;
                        i--;
                        break;
                    }
                    j--;
                    break;
                case 3:
                    if(i-1<0|| boolMatrix[i-1][j]){
                        direction = 0;
                        j++;
                        break;
                    }
                    i--;
                    break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {},
        };
        SpiralMatrix spiralMatrix = new SpiralMatrix();
        List<Integer> result =  spiralMatrix.spiralOrder(matrix);
        System.out.println(result.toString());
    }
}
