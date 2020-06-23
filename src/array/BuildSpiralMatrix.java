package array;

import java.util.Arrays;

public class BuildSpiralMatrix {

    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        if(n == 0)return matrix;
        int direction = 0;
        boolean[][] boolMatrix = new boolean[n][n];
        int i = 0,j = 0;
        int count = 1;
        while(count<=n*n){
            matrix[i][j] = count++;
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
                    if(i+1>=n|| boolMatrix[i+1][j]){
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
        return matrix;
    }

    public static void main(String[] args) {
        BuildSpiralMatrix buildSpiralMatrix = new BuildSpiralMatrix();
        System.out.println(Arrays.deepToString(buildSpiralMatrix.generateMatrix(4)));

    }
}
