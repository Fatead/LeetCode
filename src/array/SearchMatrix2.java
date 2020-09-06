package array;

import java.util.Arrays;

public class SearchMatrix2 {


    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        if(row == 0)return false;
        int col = matrix[0].length;
        if(col == 0)return false;
        int i = 0;
        while(matrix[i][i]<=target && i<col - 1 && i< row -1){
            if(matrix[i][i] == target)return true;
            i++;
        }
        if(matrix[i][i] == target)return true;
        if(matrix[i][i] > target){
            for(int m = 0;m<i;m++){
                for(int n = i;n<col;n++){
                    if(matrix[m][n] == target)return true;
                    if(matrix[m][n] > target)break;
                }
            }
            for(int m = i;m<row;m++){
                for(int n = 0;n<i;n++){
                    if(matrix[m][n] == target)return true;
                    if(matrix[m][n] > target)break;
                }
            }
            return false;
        }else if(row>=col){
            for(int m = col;m<row;m++){
                for(int n = 0;n<col;n++){
                    if(matrix[m][n] == target)return true;
                }
            }
            return false;
        }else {
            for(int m = 0;m<row;m++){
                for(int n = row;n<col;n++){
                    if(matrix[m][n] == target)return true;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
//        int[][] matrix = new int[5][5];
//        for(int i = 0;i<5;i++){
//            for(int j = 0;j<5;j++){
//                matrix[i][j] = i*5 + j + 1;
//            }
//        }
//        System.out.println(Arrays.deepToString(matrix));
        int[][] matrix = {
                {5,6,10,14},
                {6,10,13,18},
                {10,13,18,19}
        };
        SearchMatrix2 searchMatrix2 = new SearchMatrix2();
        boolean result = searchMatrix2.searchMatrix(matrix,12);
        System.out.println(result);
    }

}
