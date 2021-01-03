package hashTable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

public class MyHashMap {

    class MyEntry<K,V> implements Map.Entry<K,V> {

        K key;

        V value;

        public MyEntry(K key,V value){
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V v) {
            V older = this.value;
            this.value = v;
            return older;
        }
    }

    MyEntry[] arrays;

    /** Initialize your data structure here. */
    public MyHashMap() {
        arrays = new MyEntry[1000000];
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        arrays[key] = new MyEntry(key,value);
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        if(arrays[key]!=null){
            return (int) arrays[key].value;
        }
        return -1;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        if(arrays[key] == null)return;
        arrays[key].value = -1;
    }


}
