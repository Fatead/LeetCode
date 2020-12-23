package hashTable;

import java.util.HashMap;
import java.util.Map;

public class MapSum {

    Map<String,Integer> map;

    /** Initialize your data structure here. */
    public MapSum() {
        map = new HashMap<>();
    }

    public void insert(String key, int val) {
        map.put(key,val);
    }

    public int sum(String prefix) {
        int sum = 0;
        for(String str:map.keySet()){
            if(str.startsWith(prefix)){
                sum += map.get(str);
            }
        }
        return sum;
    }

}
