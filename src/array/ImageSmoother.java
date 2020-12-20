package array;

import java.util.Arrays;

public class ImageSmoother {

    private int getSum(int[][] M,int posX,int posY){
        int sum = 0;
        int count = 0;
        for(int i = posX-1;i<=posX+1;i++){
            for(int j = posY-1;j<=posY+1;j++){
                if(i>=0 && i<M.length && j>=0&&j<M[0].length){
                    sum += M[i][j];
                    count++;
                }
            }
        }
        return sum/count;
    }


    public int[][] imageSmoother(int[][] M) {
        int[][] matrix = new int[M.length][M[0].length];
        for(int i = 0;i<matrix.length;i++){
            for(int j = 0;j<matrix[0].length;j++){
                matrix[i][j] = getSum(M,i,j);
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        ImageSmoother imageSmoother = new ImageSmoother();
        int[][] matrix = {
                {1,1,1},
                {1,0,1},
                {1,1,1}
        };
        int[][]result = imageSmoother.imageSmoother(matrix);
        System.out.println(Arrays.deepToString(result));
    }

}
