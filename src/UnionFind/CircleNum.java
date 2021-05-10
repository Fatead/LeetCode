package UnionFind;

import java.util.HashSet;
import java.util.Set;

public class CircleNum {

    class UnionFind{

        int[] parents;

        public UnionFind(int n){
            parents = new int[n];
            for (int i = 0; i <n ; i++) {
                parents[i] = i;
            }
        }

        public void union(int x,int y){
            int rootX = find(x);
            int rootY = find(y);
            if(rootX!=rootY){
                parents[rootX] = rootY;
            }
        }


        public int find(int x){
            while (x!=parents[x]){
                parents[x] = parents[parents[x]];
                x = parents[x];
            }
            return x;
        }


        public boolean isConnected(int x,int y){
            return find(x) == find(y);
        }
    }


    public int findCircleNum(int[][] isConnected) {
        if(isConnected == null || isConnected[0].length == 0)return 0;
        UnionFind unionFind = new UnionFind(isConnected.length);
        for (int i = 0; i <isConnected.length ; i++) {
            for (int j = 0; j <isConnected[0].length ; j++) {
                if(isConnected[i][j] == 1){
                    unionFind.union(i,j);
                }
            }
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i <isConnected.length ; i++) {
            set.add(unionFind.find(i));
        }
        return set.size();
    }


    public static void main(String[] args) {
        CircleNum circleNum = new CircleNum();
        int[][] connected = {
                {1,1,0},
                {1,1,0},
                {0,0,1}
        };
        System.out.println(circleNum.findCircleNum(connected));
    }

}