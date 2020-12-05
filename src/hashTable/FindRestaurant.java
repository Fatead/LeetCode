package hashTable;

import java.util.*;

public class FindRestaurant {

    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String,Integer> map = new HashMap<>();
        for(int i = 0;i<list1.length;i++){
            map.put(list1[i],i);
        }
        Map<String,Integer> likeMap = new HashMap<>();
        for(int i = 0;i<list2.length;i++){
            if(map.containsKey(list2[i])){
                likeMap.put(list2[i],i + map.get(list2[i]));
            }
        }
        int min = Integer.MAX_VALUE;
        for(int i:likeMap.values()){
            min = Math.min(min,i);
        }
        List<String> resultList = new ArrayList<>();
        for(String str:likeMap.keySet()){
            if(likeMap.get(str) == min){
                resultList.add(str);
            }
        }
        return resultList.toArray(new String[0]);
    }

    public static void main(String[] args) {
        FindRestaurant findRestaurant = new FindRestaurant();

    }
}
