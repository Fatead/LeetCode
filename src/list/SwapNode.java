package list;

public class SwapNode {

    private static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            this.val = x;
        }
    }

    public ListNode swapPairs(ListNode head) {
        if(head == null)return null;
        else if(head.next == null)return head;
        else {
            ListNode pt1,pt2;
            pt1 = head;
            pt2 = head.next;
            pt1.next = pt2.next;
            pt2.next = pt1;
            head = pt2;
            pt1.next = swapPairs(pt1.next);
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode pt = head;
        for(int i =1;i<5;i++){
            ListNode node = new ListNode(i);
            pt.next = node;
            pt = node;
        }
        SwapNode swapNode = new SwapNode();
        head = swapNode.swapPairs(head);
        while(head!=null){
            System.out.println(head.val);
            head = head.next;
        }
    }
}
