package listTable;

public class FlattenList {


    class Node{
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }

    private Node toList(Node node){
        if(node.next == null&&node.child == null){
            return node;
        }
        if(node.next == null){
            node.next = node.child;
            node.child.prev = node;
            node.child = null;
            toList(node.next);
            return node;
        }
        if(node.child == null){
            toList(node.next);
            return node;
        }
        Node nextNode = node.next;
        node.next = node.child;
        node.child.prev = node;
        node.child = null;
        toList(node.next);
        Node pt = node;
        while(pt.next!=null){
            pt = pt.next;
        }
        pt.next = nextNode;
        nextNode.prev = pt;
        toList(pt.next);
        return node;
    }

    public Node flatten(Node head) {
        if(head == null)return head;
        head = toList(head);
        return head;
    }

    public static void main(String[] args) {

    }
}
