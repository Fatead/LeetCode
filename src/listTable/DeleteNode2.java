package listTable;

public class DeleteNode2 {

    private static class ListNode{
        int value;
        ListNode next;
        ListNode(int x){
            this.value = x;
        }
    }


    public ListNode deleteNode(ListNode head, int val) {
        if(head == null)return null;
        if(head.value==val){
            return head.next;
        }
        ListNode pt = head.next;
        ListNode pre = head;
        while (pt!=null){
            if(pt.value == val){
                pre.next = pt.next;
                pt = pt.next;
            }else {
                pt = pt.next;
                pre = pre.next;
            }
        }
        return head;
    }

}
