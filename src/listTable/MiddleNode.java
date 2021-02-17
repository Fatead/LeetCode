package listTable;

public class MiddleNode {

    private static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            this.val = x;
        }
    }

    public ListNode middleNode(ListNode head) {
        ListNode pt1 = head,pt2 = head;
        int count = 0;
        while(pt2!=null){
            if(count%2==1){
                pt1 = pt1.next;
            }
            pt2 = pt2.next;
            count++;
        }
        return pt1;
    }

}