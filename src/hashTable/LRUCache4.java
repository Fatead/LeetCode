package hashTable;

import java.util.HashMap;
import java.util.Map;

public class LRUCache4 {

    class Node{
        int key;
        int value;
        Node pre;
        Node next;

        public Node(){

        }

        public Node(int key, int value){
            this.value = value;
            this.key = key;
        }

    }

    int size;
    int capacity;
    Node head,tail;
    Map<Integer,Node> map = new HashMap<>();

    public LRUCache4(int capacity){
        this.size = 0;
        this.capacity = capacity;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key){
        Node node = map.get(key);
        if(node == null){
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value){
        Node node = map.get(key);
        if(node == null){
            Node node1 = new Node(key,value);
            map.put(key,node1);
            addToHead(node1);
            size++;
            if(size>capacity){
                Node tailNode = removeTail();
                map.remove(tailNode.key);
                size--;
            }
        }else {
            moveToHead(node);
            node.value = value;
        }
    }

    public void addToHead(Node node){
        head.next.pre = node;
        node.next = head.next;
        head.next = node;
        node.pre = head;
    }

    public void removeNode(Node node){
        node.next.pre = node.pre;
        node.pre.next = node.next;
    }

    public void moveToHead(Node node){
        removeNode(node);
        addToHead(node);
    }

    public Node removeTail(){
        Node node = tail.pre;
        removeNode(node);
        return node;
    }

}