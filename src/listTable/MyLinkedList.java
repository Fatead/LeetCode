package listTable;

public class MyLinkedList {

    static class ListNode{

        int val;
        ListNode prev;
        ListNode next;

        public ListNode(int val){
            this.val = val;
        }

    }

    private int size;
    ListNode head;
    ListNode tail;


    /** Initialize your data structure here. */
    public MyLinkedList() {
        head = new ListNode(-1);
        tail = new ListNode(-1);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if(index>size-1){
            return -1;
        }else {
            ListNode pt = head.next;
            while(index>0){
                pt = pt.next;
                index--;
            }
            return pt.val;
        }
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        ListNode addNode = new ListNode(val);
        head.next.prev = addNode;
        addNode.next = head.next;
        head.next = addNode;
        addNode.prev = head;
        size++;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        ListNode addNode = new ListNode(val);
        tail.prev.next = addNode;
        addNode.prev = tail.prev;
        addNode.next = tail;
        tail.prev = addNode;
        size++;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if(index>size){
            return;
        }else if(index<=0){
            addAtHead(val);
        }else {
            ListNode pt = head.next;
            while(index>0){
                pt = pt.next;
                index--;
            }
            ListNode addNode = new ListNode(val);
            pt.prev.next = addNode;
            addNode.prev = pt.prev;
            addNode.next = pt;
            pt.prev = addNode;
            size++;
        }
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if(index<0||index>= size){
            return;
        }
        ListNode pt = head.next;
        while(index>0){
            index--;
            pt = pt.next;
        }
        pt.prev.next = pt.next;
        pt.next.prev = pt.prev;
        size--;
    }

}
