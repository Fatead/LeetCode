package hashTable;

import java.util.HashMap;
import java.util.LinkedList;

public class LRU {

    int size;
    int capacity;
    HashMap<Integer,Integer> map = new HashMap<>();
    LinkedList<Integer> list = new LinkedList<>();

    public LRU(int capacity) {
        size = 0;
        this.capacity = capacity;
    }

    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }else {
            int index = 0;
            for (int i = 0; i <list.size() ; i++) {
                if(list.get(i) == key){
                    index = i;
                    break;
                }
            }
            list.remove(index);
            list.addFirst(key);
            return map.get(key);
        }
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            map.put(key, value);
            int index = 0;
            for (int i = 0; i <list.size() ; i++) {
                if(list.get(i) == key){
                    index = i;
                    break;
                }
            }
            list.remove(index);
            list.addFirst(key);
        }else {
            if(size<capacity){
                map.put(key,value);
                list.addFirst(key);
                size++;
            }else {
                int removeKey = list.removeLast();
                map.remove(removeKey);
                map.put(key,value);
                list.addFirst(key);
            }
        }
    }


    public static void main(String[] args) {
        LRU lru = new LRU(2);
        lru.put(2,1);
        lru.put(1,2);
        lru.put(2,3);
        lru.put(4,1);
        System.out.println(lru.get(1));
        System.out.println(lru.get(2));
    }

}