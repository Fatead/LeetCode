package graph;

import java.util.*;

public class CourseGraph {

    //分别用0，1，2表示三种状态，没有搜索过，搜索中和搜索过

    List<List<Integer>> edgeList;
    int[] visited;
    boolean valid = true;


    private void dfs(int num){
        //结点进入搜索状态1
        visited[num] = 1;
        for(int i:edgeList.get(num)){
            if(visited[i] == 0){
                dfs(i);
                if(!valid){
                    return;
                }
            }else if(visited[i] == 1){
                valid = false;
                return;
            }
        }
        visited[num] = 2;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        visited = new int[numCourses];
        edgeList = new ArrayList<>();
        for(int i = 0;i<numCourses;i++){
            edgeList.add(new ArrayList<>());
        }
        for(int i = 0;i<prerequisites.length;i++){
            edgeList.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }
        for(int i = 0;i<numCourses&valid;i++){
            if(visited[i] == 0){
                dfs(i);
            }
        }
        return valid;
    }
}
