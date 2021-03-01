package listTable;

public class getKthFromEnd {

    private static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            this.val = x;
        }
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode pt = head;
        ListNode preK = head;
        while (pt!=null && k>0){
            pt = pt.next;
            k--;
        }
        while (pt!=null){
            pt = pt.next;
            preK = preK.next;
        }
        return preK;
    }

}
