package listTable;

public class SwapKNode {

    private static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            this.val = x;
        }
    }


    private ListNode reverse(ListNode head){
        ListNode pre = null;
        ListNode curr = head;
        while(curr!=null){
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode end= dummy;
        ListNode pre = dummy;
        while(end.next!=null){
            for(int i =0;i<k&&end!=null;i++){
                end = end.next;
            }
            if(end == null)break;
            ListNode start = pre.next;
            ListNode next = end.next;
            end.next = null;
            pre.next = reverse(start);
            start.next = next;
            pre = start;
            end = pre;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode pt = head;
        for(int i =1;i<5;i++){
            ListNode node = new ListNode(i);
            pt.next = node;
            pt = node;
        }
        SwapKNode swapKNode = new SwapKNode();
        head =  swapKNode.reverseKGroup(head,2);
        while(head!=null){
            System.out.println(head.val);
            head = head.next;
        }
    }


}
