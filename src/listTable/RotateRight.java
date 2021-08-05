package listTable;

public class RotateRight {

    class ListNode{

        int val;
        ListNode next;
        public ListNode(int value){
            this.val = value;
        }

    }

    private int getLength(ListNode node){
        int count = 0;
        while (node!= null){
            count++;
            node = node.next;
        }
        return count;
    }

    public ListNode rotateRight(ListNode head, int k) {
        if(head == null)return head;
        int length = getLength(head);
        k = k%length;
        int count = length - k;
        ListNode pt = head;
        while (count>=0){
            pt = pt.next;
            count--;
        }
        ListNode dump = new ListNode(0);
        dump.next = pt.next;
        pt.next = null;
        pt = dump;
        while (pt.next!=null){
            pt = pt.next;
        }
        pt.next = head;
        return dump.next;
    }

}