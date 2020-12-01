package array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ReshapeMatrix {

    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int length = nums.length;
        int width = nums[0].length;
        if(r*c != length*width)return nums;
        int[][] matrix = new int[r][c];
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0;i<length;i++){
            for(int j = 0;j<width;j++){
                queue.add(nums[i][j]);
            }
        }
        for(int i = 0;i<r;i++){
            for(int j = 0;j<c;j++){
                matrix[i][j] = queue.poll();
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        ReshapeMatrix reshapeMatrix = new ReshapeMatrix();
        int[][] nums = {
                {1,2},
                {3,4}
        };
        int[][] result = reshapeMatrix.matrixReshape(nums,1,4);
        System.out.println(Arrays.deepToString(result));
    }

}