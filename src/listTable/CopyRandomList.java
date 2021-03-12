package listTable;

import java.util.HashMap;
import java.util.Map;

public class CopyRandomList {


    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        Node newHead = new Node(-1);
        Node pt = head;
        Node newPt = newHead;
        while (pt!=null){
            newPt.next = new Node(pt.val);
            newPt = newPt.next;
            pt = pt.next;
        }
        Map<Node,Node> map = new HashMap<>();
        pt = head;
        newPt = newHead.next;
        while (pt!=null){
            map.put(pt,newPt);
            pt = pt.next;
            newPt = newPt.next;
        }
        pt = head;
        newPt = newHead.next;
        while (pt!=null){
            newPt.random = map.get(pt.random);
            pt = pt.next;
            newPt = newPt.next;
        }
        return newHead.next;
    }


    public static void main(String[] args) {

    }
}
