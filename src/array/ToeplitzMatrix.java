package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToeplitzMatrix {

    /**
     * 如果一个点的横坐标与纵坐标之差相等，则它们属于同一条左对角线
     * 如果一个点的横坐标与纵坐标之和相等，则它们属于同一条右对角线
     */
    public boolean isToeplitzMatrix(int[][] matrix) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i <matrix.length ; i++) {
            for (int j = 0; j <matrix[0].length ; j++) {
                if(!map.containsKey(i-j)){
                    map.put(i-j,matrix[i][j]);
                }else if(map.get(i-j)!=matrix[i][j])return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3,4},
                {5,1,2,3},
                {9,5,1,2},
        };
        ToeplitzMatrix toeplitzMatrix = new ToeplitzMatrix();
        System.out.println(toeplitzMatrix.isToeplitzMatrix(matrix));
    }


}
