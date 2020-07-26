package array;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {

    public List<List<Integer>> generate(int numRows) {
        int[][] array = new int[numRows][numRows+1];
        for(int i = 0;i<numRows;i++){
            array[i][0] = 1;
            array[i][i] = 1;
        }
        for(int i = 1;i<numRows;i++){
            for(int j = 1;j<i;j++){
                array[i][j] = array[i-1][j-1] + array[i-1][j];
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0;i<numRows;i++){
            List<Integer> list = new ArrayList<>();
            for(int j = 0;j<=i;j++){
                list.add(array[i][j]);
            }
            result.add(list);
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
