package hashTable;
import java.util.*;

/**
 * 基于链表 + HashMap实现LRU
 * 当需要插入新的数据项的时候，如果新数据项在链表中存在（命中），
 * 则把该节点移动到链表头部，如果不存在，则新建一个节点。
 * 若缓存满了，则把链表中最后一个节点删除
 */
class LRUCache {


    int capacity;
    int size;
    Map<Integer,Integer> map = new HashMap<>();
    LinkedList<Integer> list = new LinkedList<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        int result = map.getOrDefault(key,-1);
        if(result!=-1){
            int index = 0;
            for(int i = 0;i<list.size();i++){
                if(list.get(i) == key){
                    index = i;
                    break;
                }
            }
            //将当前访问的节点移动到链表的头部
            list.remove(index);
            list.add(key);
        }
        return result;
    }

    public void put(int key, int value) {
        if(++size>capacity&&!map.containsKey(key)){
            int last = list.poll();
            int lastValue = map.get(last);
            map.remove(last,lastValue);
            map.put(key,value);
            list.add(key);
            size = capacity;
        }else {
            if(!map.containsKey(key)){
                map.put(key,value);
                list.add(key);
            }else {
                size--;
                map.put(key,value);
                int index = 0;
                for(int i = 0;i<list.size();i++){
                    if(list.get(i) == key){
                        index = i;
                        break;
                    }
                }
                list.remove(index);
                list.add(key);
            }
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);
        lruCache.put(1,1);
        lruCache.put(2,2);
        lruCache.put(3,3);
        lruCache.put(4,4);
        lruCache.get(4);
        lruCache.get(3);
        lruCache.get(2);
        lruCache.get(1);
        lruCache.put(5,5);
        lruCache.get(1);
        lruCache.get(2);
        lruCache.get(3);
        lruCache.get(4);
        lruCache.get(5);
    }

}
