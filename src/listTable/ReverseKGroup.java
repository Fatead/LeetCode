package listTable;

import java.util.Stack;

public class ReverseKGroup {

    static class ListNode{

        int val;
        ListNode next;

        public ListNode(int value){
            this.val = value;
        }

    }


    private ListNode reverse(ListNode node){
        Stack<ListNode> stack = new Stack<>();
        while (node!=null){
            stack.add(node);
            node = node.next;
        }
        ListNode head = new ListNode(0);
        ListNode pt = head;
        while (!stack.isEmpty()){
            pt.next = stack.pop();
            pt = pt.next;
        }
        pt.next = null;
        return head.next;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null)return head;
        int count = k;
        ListNode dump = new ListNode(0);
        dump.next = head;
        ListNode pt = dump;
        while (count >= 0 && pt!=null){
            pt = pt.next;
            count--;
        }
        if(count>0 || pt == null)return head;
        ListNode nextHead = pt.next;
        pt.next = null;
        dump.next = reverse(head);
        pt = dump.next;
        while (pt.next!=null){
            pt = pt.next;
        }
        pt.next = reverseKGroup(nextHead,k);
        return dump.next;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode pt = head;
        for (int i = 1; i <=5 ; i++) {
            ListNode node = new ListNode(i);
            pt.next = node;
            pt = pt.next;
        }
        ReverseKGroup reverseKGroup = new ReverseKGroup();
        head = reverseKGroup.reverseKGroup(head.next,2);
    }

}