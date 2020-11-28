package array;

import java.util.*;

/**
 * 不应该用深搜，应该用宽搜
 */
public class UpdateMatrix {


    private int bfs(int[][] matrix,boolean[][] flagMatrix,int x,int y){
        int distance = 0;
        Queue<List<Integer>> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        list.add(x);
        list.add(y);
        list.add(distance);
        queue.add(list);
        while (!queue.isEmpty()){
            List<Integer> list1 = queue.poll();
            int i = list1.get(0);
            int j = list1.get(1);
            int dis = list1.get(2);
            if(matrix[i][j] == 0){
                return dis;
            }
            if(i-1>=0 && !flagMatrix[i-1][j]){
                flagMatrix[i-1][j] = true;
                List<Integer> list2 = new ArrayList<>();
                list2.add(i-1);
                list2.add(j);
                list2.add(dis+1);
                queue.add(list2);
            }
            if(i+1<matrix.length && !flagMatrix[i+1][j]){
                flagMatrix[i+1][j] = true;
                List<Integer> list2 = new ArrayList<>();
                list2.add(i+1);
                list2.add(j);
                list2.add(dis+1);
                queue.add(list2);
            }
            if(j-1>=0 && !flagMatrix[i][j-1]){
                flagMatrix[i][j-1] = true;
                List<Integer> list2 = new ArrayList<>();
                list2.add(i);
                list2.add(j-1);
                list2.add(dis+1);
                queue.add(list2);
            }
            if(j+1<matrix[0].length && !flagMatrix[i][j+1]){
                flagMatrix[i][j+1] = true;
                List<Integer> list2 = new ArrayList<>();
                list2.add(i);
                list2.add(j+1);
                list2.add(dis+1);
                queue.add(list2);
            }
        }
        return 0;
    }

    public int[][] updateMatrix(int[][] matrix) {
        int[][] result = new int[matrix.length][matrix[0].length];
        for(int i = 0;i<matrix.length;i++){
            for(int j = 0;j<matrix[0].length;j++){
                if(matrix[i][j] == 0){
                    result[i][j] = 0;
                }else {
                    boolean[][] flagMatrix = new boolean[matrix.length][matrix[0].length];
                    flagMatrix[i][j] = true;
                    int dis = bfs(matrix,flagMatrix,i,j);
                    result[i][j] = dis;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {0,0,0},
                {0,1,0},
                {1,1,1},
        };
        UpdateMatrix updateMatrix = new UpdateMatrix();
        int[][] result = updateMatrix.updateMatrix(matrix);
        System.out.println(Arrays.deepToString(result));
    }

}
