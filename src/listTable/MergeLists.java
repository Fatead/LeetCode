package listTable;

public class MergeLists {

    private static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            this.val = x;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode pt = head;
        while (l1!=null && l2!=null){
            if(l1.val>l2.val){
                pt.next = l2;
                pt = pt.next;
                l2 = l2.next;
            }else {
                pt.next = l1;
                pt = pt.next;
                l1 = l1.next;
            }
        }
        if(l1!=null){
            pt.next = l1;
        }
        if(l2!=null){
            pt.next = l2;
        }
        return head.next;
    }

}
