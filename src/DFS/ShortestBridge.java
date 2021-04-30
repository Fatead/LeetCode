package DFS;

import java.util.*;

public class ShortestBridge {


    Queue<int[]> queue = new LinkedList<>();
    Set<int[]> set = new HashSet<>();
    //向四个方向遍历的方法很巧妙
    int[] direction = {-1,0,1,0,-1};

    private void dfs(int[][] A,int i,int j){
        if(i<0||j<0||i>=A.length||j>=A[0].length||A[i][j]==2)return;
        //找到相邻的0并加入到队列中
        if(A[i][j] == 0){
            int[] ints = new int[]{i,j};
            if(!set.contains(ints)) {
                queue.add(ints);
                set.add(ints);
            }
            return;
        }
        A[i][j] = 2;
        for (int k = 0; k <4 ; k++) {
            int x = i+direction[k];
            int y = j+direction[k+1];
            dfs(A,x,y);
        }
    }


    public int shortestBridge(int[][] A) {
        //首先发现第一座岛
        boolean flag = false;
        for (int i = 0; i <A.length ; i++) {
            if(flag)break;
            for (int j = 0; j <A[0].length ; j++) {
                if(A[i][j] == 1){
                    //找到第一座岛，基于DFS将第一个岛的位置全变成2，并且将第一个岛相邻的0入队
                    dfs(A,i,j);
                    flag = true;
                    break;
                }
            }
        }
        //基于BFS找到两个岛之间的最短距离
        int level = 0;
        while (!queue.isEmpty()){
            level++;
            int pointsNum = queue.size();
            //分别从四个方向找
            while (pointsNum-->0){
                int[] point = queue.poll();
                A[point[0]][point[1]] = 2;
                for (int i = 0; i <4 ; i++) {
                    int x = point[0] + direction[i];
                    int y = point[1] + direction[i+1];
                    if(x>=0 && y>=0 && x<A.length && y<A[0].length){
                        if(A[x][y] == 2)continue;
                        if(A[x][y] == 1)return level;
                        queue.add(new int[]{x,y});
                        A[x][y] = 2;
                    }
                }
            }
        }
        return 0;
    }


    public static void main(String[] args) {
        ShortestBridge shortestBridge = new ShortestBridge();
        int[][] A = {
                {1,1,0},
                {0,0,0},
                {0,0,1}
        };
        System.out.println(shortestBridge.shortestBridge(A));
    }

}
