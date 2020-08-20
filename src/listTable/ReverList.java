package listTable;

import java.util.Stack;

public class ReverList {

    private static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }

    public ListNode reverseList(ListNode head) {
        if(head==null)return null;
        ListNode reHead = new ListNode(0);
        Stack<ListNode> nodeStack = new Stack<>();
        ListNode pt = head;
        ListNode p;
        while(pt!=null){
            p = pt;
            pt = pt.next;
            p.next = null;
            nodeStack.add(p);
        }
        pt = reHead;
        while(!nodeStack.isEmpty()){
            pt.next = nodeStack.pop();
            pt = pt.next;
        }
        return reHead.next;
    }

    public static void main(String[] args) {

    }
}
