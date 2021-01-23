package array;

import java.util.Arrays;

public class InvertImg {



    public int[][] flipAndInvertImage(int[][] A) {
        for (int i = 0; i <A.length ; i++) {
            for (int j = 0; j <A[0].length/2 ; j++) {
                int temp = A[i][j];
                A[i][j] = A[i][A[0].length-j-1];
                A[i][A[0].length-j-1] = temp;
            }
            for (int j = 0; j <A[0].length ; j++) {
                A[i][j] = A[i][j]^1;
            }
        }
        return A;
    }

    public static void main(String[] args) {
        InvertImg invertImg = new InvertImg();
        int[][] A = {
                {1,1,0,0},
                {1,0,0,1},
                {0,1,1,1},
                {1,0,1,0}
        };
        int[][] result = invertImg.flipAndInvertImage(A);
        System.out.println(Arrays.deepToString(result));
    }

}
