package listTable;

import java.util.Stack;

public class ReverseList2 {

    private static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }

    public ListNode reverseList(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode pt = head;
        ListNode pre = head;
        while (pt!=null){
            stack.add(pt);
            pt = pt.next;
            pre.next = null;
            pre = pt;
        }
        if(stack.size()==0)return null;
        head = stack.pop();
        pt = head;
        while (!stack.isEmpty()){
            pt.next = stack.pop();
            pt = pt.next;
        }
        return head;
    }

}
