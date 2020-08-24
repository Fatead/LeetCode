package graph;
import java.util.*;

public class CourseGraph2 {

    private List<List<Integer>> edgeList = new ArrayList<>();
    private int[] visited;
    private boolean valid = true;
    private List<Integer> resultList;
    private int[] inDegree;

    private void dfs(int num){
        visited[num] = 1;
        for(int i:edgeList.get(num)){
            if(visited[i] == 0){
                if(inDegree[i] == 1){
                    resultList.add(i);
                    dfs(i);
                }else {
                    inDegree[i]--;
                }
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


    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if(numCourses<=0)return new int[]{};
        visited = new int[numCourses];
        inDegree = new int[numCourses];
        Set<Integer> nodeSet = new HashSet<>();
        for(int i = 0;i<numCourses;i++){
            edgeList.add(new ArrayList<>());
            nodeSet.add(i);
        }
        for(int i = 0;i<prerequisites.length;i++){
            edgeList.get(prerequisites[i][1]).add(prerequisites[i][0]);
            nodeSet.remove(prerequisites[i][0]);
            inDegree[prerequisites[i][0]]++;
        }
        if(nodeSet.isEmpty()){
            return new int[]{};
        }
        Stack<Integer> intStack = new Stack<>();
        intStack.addAll(nodeSet);
        resultList = new ArrayList<>();
        while(!intStack.isEmpty()){
            int i = intStack.pop();
            resultList.add(i);
            dfs(i);
        }
        int[] result = new int[numCourses];
        if(resultList.size()!=numCourses)return new int[]{};
        for(int i = 0;i<numCourses;i++){
            result[i] = resultList.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        CourseGraph2 courseGraph2 = new CourseGraph2();
        int[][] order = {{1,0},{1,2},{0,1}};
        int[] arr =  courseGraph2.findOrder(3,order);
        System.out.println(Arrays.toString(arr));
    }

}
