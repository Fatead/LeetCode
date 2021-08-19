package listTable;
import java.util.Stack;

public class ReverseBetween {

    private static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }

    private ListNode reverse(ListNode head){
        ListNode dump = new ListNode(0);
        ListNode pt = head;
        Stack<ListNode> stack = new Stack<>();
        while (pt!=null){
            stack.push(pt);
            pt = pt.next;
        }
        pt = dump;
        while (!stack.isEmpty()){
            ListNode node = stack.pop();
            pt.next = node;
            pt = pt.next;
            pt.next = null;
        }
        return dump.next;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dump = new ListNode(0);
        dump.next = head;
        ListNode pt1 = dump, pt2 = dump;
        int count = 1;
        while (count<left){
            pt1 = pt1.next;
            count++;
        }
        count = 0;
        while (count<right){
            pt2 = pt2.next;
            count++;
        }
        ListNode thirdPt = pt2.next;
        pt2.next = null;
        ListNode subHead = pt1.next;
        pt1.next = null;
        pt1.next = reverse(subHead);
        while (pt1.next!=null){
            pt1 = pt1.next;
        }
        pt1.next = thirdPt;
        return dump.next;
    }


    public static void main(String[] args) {
        ListNode dump = new ListNode(0);
        ListNode pt = dump;
        for (int i = 1; i <=5 ; i++) {
            ListNode node = new ListNode(i);
            pt.next = node;
            pt = pt.next;
        }
        ReverseBetween reverseBetween = new ReverseBetween();
        dump.next = reverseBetween.reverseBetween(dump.next,2,4);
    }

}