package hashTable;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class LFUCache2 {

    class Node implements Comparable<Node>{

        int count;
        int time;
        int key;
        int value;

        Node(int count,int time,int key,int value){
            this.count = count;
            this.time = time;
            this.key = key;
            this.value = value;
        }

        public boolean equals(Object o){
            if(this == o)return true;
            if(o instanceof Node){
                Node compareNode = (Node) o;
                return this.count == compareNode.count && this.time == compareNode.time;
            }
            return false;
        }

        @Override
        public int compareTo(Node node) {
            return this.count == node.count? this.time - node.time : this.count - node.count;
        }

    }

    int capacity, time;
    Map<Integer,Node> map;
    TreeSet<Node> set;

    public LFUCache2(int capacity) {
        this.capacity = capacity;
        this.time = 0;
        map = new HashMap<>();
        set = new TreeSet<>();
    }

    public int get(int key) {
        if(capacity == 0 || !map.containsKey(key)){
            return -1;
        }
        Node node  = map.get(key);
        set.remove(node);
        node.count+=1;
        node.time = ++time;
        set.add(node);
        map.put(key,node);
        return node.value;
    }

    public void put(int key, int value) {
        if(capacity == 0){
            return;
        }
        if(!map.containsKey(key)){
            if(map.size() == this.capacity){
                //缓存满了
                map.remove(set.first().key);
                set.remove(set.first());
            }
            Node node = new Node(1,++time,key,value);
            map.put(key,node);
            set.add(node);
        }else {
            Node node = map.get(key);
            set.remove(node);
            node.value = value;
            node.time = ++time;
            node.count += 1;
            set.add(node);
            map.put(key,node);
        }
    }

}
