package listTable;

public class OddEvenList {



    private static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            this.val = x;
        }
    }


    public ListNode oddEvenList(ListNode head) {
        if(head == null||head.next == null)return head;
        ListNode pt1 = head;
        ListNode pt2 = head.next;
        ListNode pre = head;
        int count = 1;
        while(pt2!=null){
            if(count%2 == 1&&count>1){
                pre.next = pt2.next;
                pt2.next = pt1.next;
                pt1.next = pt2;
                pt1 = pt1.next;
                pt2 = pre.next;
            }else {
                pt2 = pt2.next;
                pre = pre.next;
            }
            count++;
        }
        return head;
    }


    public static void main(String[] args) {
        System.out.println();

    }
}
