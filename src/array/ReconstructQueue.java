package array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class ReconstructQueue {

    public class MyComparator implements Comparator<int[]>{

        @Override
        public int compare(int[] ints, int[] t1) {
            if(ints[0] > t1[0])return -1;
            else if(ints[0] < t1[0])return 1;
            else {
                return Integer.compare(ints[1],t1[1]);
            }
        }

    }

    public int[][] reconstructQueue(int[][] people) {
        int[][] newQueue = new int[people.length][people[0].length];
        Arrays.sort(people,new MyComparator());
//        for(int i = 0;i<people.length;i++){
//            int pos = people[i][1];
//            for(int j = people.length-1;j>pos;j--){
//                newQueue[j][0] = newQueue[j-1][0];
//                newQueue[j][1] = newQueue[j-1][1];
//            }
//            newQueue[pos][0] = people[i][0];
//            newQueue[pos][1] = people[i][1];
//        }
        LinkedList<int[]> list = new LinkedList<>();
        for(int[] element:people){
            list.add(element[1],element);
        }
        return list.toArray(new int[people.length][2]);
    }


    public static void main(String[] args) {
        ReconstructQueue reconstructQueue = new ReconstructQueue();
        int[][] people = {
                {7,0},
                {4,4},
                {7,1},
                {5,0},
                {6,1},
                {5,2}
        };
        int[][] result =  reconstructQueue.reconstructQueue(people);
        System.out.println(Arrays.deepToString(result));
    }
}
