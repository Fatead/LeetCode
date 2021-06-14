package BFS;

import java.util.LinkedList;
import java.util.Queue;

public class OrangesRotting {


    int[] dir = {-1,0,1,0,-1};


    /**
     * 验证网格中有没有新鲜橘子
     */
    private boolean validOrange(int[][] grid){
        for (int i = 0; i <grid.length ; i++) {
            for (int j = 0; j <grid[i].length ; j++) {
                if(grid[i][j] == 1)return true;
            }
        }
        return false;
    }


    public int orangesRotting(int[][] grid) {
        if(!validOrange(grid))return 0;
        Queue<int[]> queue = new LinkedList<>();
        int currNum = 0;
        int nextNum = 0;
        for (int i = 0; i <grid.length ; i++) {
            for (int j = 0; j <grid[0].length ; j++) {
                if(grid[i][j] == 2){
                    queue.add(new int[]{i,j});
                    currNum++;
                }
            }
        }
        int time = -1;
        while (!queue.isEmpty()){
            int[] point = queue.poll();
            currNum--;
            for (int i = 0; i <dir.length-1 ; i++) {
                int x = point[0] + dir[i];
                int y = point[1] + dir[i+1];
                if(x<0 ||y<0 || x>=grid.length || y>=grid[0].length){
                    continue;
                }
                if(grid[x][y] == 1){
                    grid[x][y] = 2;
                    nextNum++;
                    queue.add(new int[]{x,y});
                }
            }
            if(currNum == 0){
                currNum = nextNum;
                nextNum = 0;
                time++;
            }
        }
        if(validOrange(grid))return -1;
        return time;
    }

    public static void main(String[] args) {
        OrangesRotting orangesRotting = new OrangesRotting();
        int[][] grid = {
                {0,2}
        };
        System.out.println(orangesRotting.orangesRotting(grid));
    }

}
