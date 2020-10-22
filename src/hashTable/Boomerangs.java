package hashTable;

import java.util.HashMap;
import java.util.Map;

public class Boomerangs {


    private int distance(int[] x,int[] y){
        return (y[0] - x[0])*(y[0] - x[0]) + (y[1] - x[1])*(y[1] - x[1]);
    }

    public int numberOfBoomerangs(int[][] points) {
        int result = 0;
        for(int i = 0;i<points.length;i++){
            Map<Integer,Integer> map = new HashMap<>();
            for(int j = 0;j<points.length;j++){
                if(i!=j){
                    int dis = distance(points[i],points[j]);
                    map.put(dis,map.getOrDefault(dis,0) + 1);
                }
            }
            for(int n:map.values()){
                result+=n*(n-1);
            }
        }
        return result;
    }

    public static void main(String[] args) {

    }

}
