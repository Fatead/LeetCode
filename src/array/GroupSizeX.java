package array;

import java.util.HashMap;
import java.util.Map;

public class GroupSizeX {

    public boolean hasGroupsSizeX(int[] deck) {
        if(deck.length<=1)return false;
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i <deck.length ; i++) {
            map.put(deck[i],map.getOrDefault(deck[i],0) + 1);
        }
        int minCount = Integer.MAX_VALUE;
        for(int i:map.values()){
            minCount = Math.min(minCount,i);
        }
        if(minCount==1)return false;
        for (int j = 2; j <=minCount ; j++) {
            boolean flag = true;
            for(Integer i:map.values()){
                if(i%j != 0){
                    flag = false;
                    break;
                }
            }
            if(flag)return true;
        }
        return false;
    }

    public static void main(String[] args) {
        GroupSizeX groupSizeX = new GroupSizeX();
        int[] desk = {1,1};
        System.out.println(groupSizeX.hasGroupsSizeX(desk));
    }

}
