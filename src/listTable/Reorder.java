package listTable;

public class Reorder {

    private static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
        ListNode(int val,ListNode next){
            this.val = val;
            this.next= next;
        }
    }

    public void print(ListNode head){
        while(head!=null){
            System.out.println(head.val);
            head = head.next;
        }
    }

    public void reorderList(ListNode head) {
        if(head == null ||head.next == null)return;
        ListNode fastPt = head;
        ListNode slowPt = head;
        int count = 0;
        while(fastPt!=null){
            fastPt = fastPt.next;
            if(count++%2 == 0&&count>1)
                slowPt = slowPt.next;
        }
        ListNode preNode = slowPt;
        slowPt = slowPt.next;
        preNode.next = null;
        ListNode pre = slowPt;
        ListNode pt = slowPt.next;
        slowPt.next = null;
        if(pt != null){
            ListNode next = pt.next;
            while(next!=null){
                pt.next = pre;
                pre = pt;
                pt = next;
                next = pt.next;
            }
            pt.next = pre;
        }else {
            pt = slowPt;
        }
        ListNode pt1 = head;
        ListNode pt2 = pt;
        ListNode ptNext1 = pt1.next,ptNext2 = pt2.next;
//        print(pt1);
//        System.out.println();
//        print(pt2);
        while(ptNext1!=null&&ptNext2!=null){
            pt1.next = pt2;
            pt2.next = ptNext1;
            pt1 = ptNext1;
            ptNext1 = pt1.next;
            pt2 = ptNext2;
            ptNext2 = ptNext2.next;
        }
        pt1.next = pt2;
        pt2.next = ptNext1;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode pt = head;
        for(int i = 2;i<=10;i++){
            ListNode node = new ListNode(i);
            pt.next = node;
            pt = node;
        }
        Reorder reorder = new Reorder();
        reorder.reorderList(head);
        pt = head;
        while(pt!=null){
            System.out.println(pt.val);
            pt = pt.next;
        }
    }
}
