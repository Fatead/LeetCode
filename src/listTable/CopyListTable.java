package listTable;

import java.util.HashMap;
import java.util.Map;

public class CopyListTable {


    class Node{
        int val;
        Node next;
        Node random;

        public Node(int x){
            this.val = x;
            next = null;
            random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if(head == null)return null;
        Map<Node,Node> nodeMap = new HashMap<>();
        Node newHead = new Node(head.val);
        nodeMap.put(head,newHead);
        Node pt = head.next;
        Node pre = newHead;
        while(pt!=null){
            Node node = new Node(pt.val);
            pre.next = node;
            pre = node;
            nodeMap.put(pt,node);
            pt = pt.next;
        }
        pt = head;
        while(pt!=null){
            Node node = nodeMap.get(pt);
            node.random = nodeMap.get(pt.random);
            pt = pt.next;
        }
        return newHead;
    }
}
