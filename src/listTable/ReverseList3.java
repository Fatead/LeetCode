package listTable;

public class ReverseList3 {

    private static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }

    /**
     * 迭代法反转链表
     */
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null)return head;
        ListNode pre = null;
        ListNode pt = head;
        ListNode next = head.next;
        while (next!=null){
            pt.next = pre;
            pre = pt;
            pt = next;
            next = next.next;
        }
        pt.next = pre;
        return pt;
    }


    public ListNode reverseList2(ListNode head){
        if(head == null || head.next == null)return head;
        ListNode newHead = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

}