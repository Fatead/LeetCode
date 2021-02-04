package array;

import java.util.Arrays;

public class MatrixScore {

    public int matrixScore(int[][] A) {
        //先行后列
        for (int i = 0; i <A.length ; i++) {
            int sum = 0;
            for (int j = 0; j <A[0].length ; j++) {
                if(A[i][j]==1)
                    sum += (int)Math.pow(2,A[0].length-1-j);
            }
            if(sum*2<(int)Math.pow(2,A[0].length)-1){
                for (int j = 0; j <A[0].length ; j++) {
                    if(A[i][j] ==1)A[i][j]=0;
                    else A[i][j]=1;
                }
            }
        }
        for (int i = 0; i <A[0].length ; i++) {
            int count = 0;
            for (int j = 0; j <A.length ; j++) {
                if(A[j][i]==1)count++;
            }
            if(count*2<A.length){
                for (int j = 0; j <A.length ; j++) {
                    if(A[j][i]==1){
                        A[j][i]=0;
                    }else {
                        A[j][i]=1;
                    }
                }
            }
        }
        int result = 0;
        for (int i = 0; i <A.length ; i++) {
            for (int j = 0; j <A[0].length ; j++) {
                if(A[i][j]==1)
                    result+=(int)Math.pow(2,A[0].length-1-j);
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int[][] matrix = {
                {0,0,1,1},
                {1,0,1,0},
                {1,1,0,0}
        };
        MatrixScore matrixScore = new MatrixScore();
        System.out.println(matrixScore.matrixScore(matrix));
    }

}
