package array;

import java.util.Arrays;
import java.util.Comparator;

public class MinArrowShots {

    public class MyComparator implements Comparator<int[]>{

        @Override
        public int compare(int[] ints, int[] t1) {
            //处理整型溢出的问题
            return Integer.compare(ints[1],t1[1]);
        }

    }

    public int findMinArrowShots(int[][] points) {
        if(points.length == 0)return 0;
        if(points.length == 1)return 1;
        MyComparator myComparator = new MyComparator();
        Arrays.sort(points,myComparator);
        int arrowNum = 1;
        int posEnd = points[0][1];
        for(int i = 1;i<points.length;i++){
            if(points[i][0] <= posEnd){
                if(points[i][1] < posEnd)
                    posEnd = points[i][1];
            }else{
                arrowNum++;
                posEnd = points[i][1];
            }
        }
        return arrowNum;
    }

    public static void main(String[] args) {
        MinArrowShots minArrowShots = new MinArrowShots();
        int[][] points = {
                {10,16},
                {2,8},
                {1,6},
                {7,12}
        };
        System.out.println(minArrowShots.findMinArrowShots(points));
    }

}
