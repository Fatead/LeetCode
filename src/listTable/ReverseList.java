package listTable;

public class ReverseList {

    private static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null)return null;
        ListNode pre = null;
        ListNode cur = head;
        while(m>1){
            pre = cur;
            cur = cur.next;
            m--;
            n--;
        }
        ListNode pt1 = pre;
        ListNode pt2 = cur;
        while(n>0){
            ListNode third = cur.next;
            cur.next = pre;
            pre = cur;
            cur = third;
            n--;
        }
        if(pt1!=null){
            pt1.next = pre;
        }else {
            head = pre;
        }
        pt2.next = cur;
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        ListNode pt = head;
        for(int i = 1;i<=5;i++){
            ListNode listNode = new ListNode(i);
            pt.next = listNode;
            pt = listNode;
        }
        ReverseList reverseList = new ReverseList();
        pt = reverseList.reverseBetween(head.next,1,4);
        while(pt!=null){
            System.out.println(pt.val);
            pt = pt.next;
        }
    }
}
