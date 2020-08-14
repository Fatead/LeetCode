package hashTable;

import java.util.HashMap;
import java.util.Map;

public class LRUCache3 {

    int capacity;
    int size;
    Node head,tail;
    Map<Integer,Node> nodeMap = new HashMap<>();

    public class Node{
        int key;
        int value;
        Node pre;
        Node next;
        public Node(int key,int value){
            this.key = key;
            this.value = value;
        }
        public Node(){

        }
    }

    public LRUCache3(int capacity){
        this.size = 0;
        this.capacity = capacity;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    public void put(int key,int value){
        Node node = nodeMap.get(key);
        if(node==null){
            Node addNode = new Node(key,value);
            nodeMap.put(key,addNode);
            moveToHead(addNode);
            size++;
            if(size>capacity){
                Node tail = removeTail();
                nodeMap.remove(tail.key);
                size--;
            }
        }else {
            node.value = value;
            removeNode(node);
            moveToHead(node);
        }
    }

    public int get(int key){
        if(size == 0)return -1;
        Node node = nodeMap.getOrDefault(key,new Node(-1,-1));
        if(node.value!=-1){
            removeNode(node);
            moveToHead(node);
        }
        return node.value;
    }


    private Node removeTail(){
        Node node = tail.pre;
        removeNode(node);
        return node;
    }

    private void moveToHead(Node node){
        head.next.pre = node;
        node.next = head.next;
        head.next = node;
        node.pre = head;
    }

    private void removeNode(Node node){
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    public static void main(String[] args) {
        LRUCache3 lruCache = new LRUCache3(3);
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
