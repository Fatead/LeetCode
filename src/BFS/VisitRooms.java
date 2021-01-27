package BFS;

import java.util.*;

public class VisitRooms {

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if(rooms.size() ==0)return true;
        Queue<Integer> queue = new LinkedList<>();
        int[] canVisit = new int[rooms.size()];
        canVisit[0] = 1;
        queue.add(0);
        while (!queue.isEmpty()){
            int index = queue.poll();
            List<Integer> room  = rooms.get(index);
            for(int i:room){
                if(canVisit[i]==0){
                    canVisit[i] = 1;
                    queue.add(i);
                }
            }
        }
        for(int i:canVisit){
            if(i==0)return false;
        }
        return true;
    }

    public static void main(String[] args) {
        VisitRooms visitRooms = new VisitRooms();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        List<Integer> list4 = new ArrayList<>();
        List<List<Integer>> rooms = new ArrayList<>();
        list1.add(1);
        list2.add(2);
        list3.add(3);
        rooms.add(list1);
        rooms.add(list2);
        rooms.add(list3);
        rooms.add(list4);
        System.out.println(visitRooms.canVisitAllRooms(rooms));
    }

}
