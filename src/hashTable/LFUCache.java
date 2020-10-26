package hashTable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LFUCache {

    Map<Integer,Integer> key2valueMap = new HashMap<>();
    Map<Integer,Integer> countMap = new HashMap<>();
    int capacity;
    int num;
    List<Integer> list = new LinkedList<>();

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    private List<Integer>  changePos(int key){
        int pos = 0;
        for(int i = 0;i<list.size();i++){
            if(list.get(i) == key){
                pos = i;
            }
        }
        int value = list.get(pos);
        list.remove(pos);
        list.add(0,value);
        return list;
    }

    public int get(int key) {
        if(key2valueMap.containsKey(key)){
            countMap.put(key,countMap.get(key) + 1);
            changePos(key);
            return key2valueMap.get(key);
        }else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if(capacity==0)return;
        if(!key2valueMap.containsKey(key)){
            //移除最不经常使用的项
            if(num==capacity){
                int minKey = 0;
                int minCount = Integer.MAX_VALUE;
                for(int i:countMap.keySet()){
                    if(countMap.get(i)<minCount){
                        minCount = countMap.get(i);
                        minKey = i;
                    }
                }
                key2valueMap.remove(minKey,key2valueMap.get(minKey));
                countMap.remove(minKey,minCount);
                key2valueMap.put(key,value);
                countMap.put(key,1);
            }else {
                num++;
                changePos(key);
                key2valueMap.put(key,value);
                countMap.put(key,1);
            }
        }else {
            changePos(key);
            key2valueMap.put(key,value);
            countMap.put(key,countMap.get(key) + 1);
        }
    }

    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(2);
        lfuCache.put(1,1);
        lfuCache.put(2,2);
        lfuCache.get(1);
        lfuCache.put(3,3);
        lfuCache.get(2);
        lfuCache.get(3);
        lfuCache.put(4,4);
        lfuCache.get(1);
        lfuCache.get(3);
        lfuCache.get(4);
    }

}
