package listTable;

import java.util.Stack;

public class IntersectList {

    private static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            this.val = x;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Stack<ListNode> stackA = new Stack<>();
        Stack<ListNode> stackB = new Stack<>();
        ListNode ptA = headA;
        ListNode ptB = headB;
        while(ptA!=null){
            stackA.push(ptA);
            ptA = ptA.next;
        }
        while(ptB!=null){
            stackB.push(ptB);
            ptB = ptB.next;
        }
        ListNode node = null;
        while(!stackA.isEmpty()&&!stackB.isEmpty()){
            if(stackA.peek().equals(stackB.peek())){
                node = stackA.pop();
                stackB.pop();
            }else {
                return node;
            }
        }
        return null;
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB){
        if(headA == null||headB == null)return null;
        ListNode ptA = headA;
        ListNode ptB = headB;
        while(!ptA.equals(ptB)){
            ptA = ptA.next;
            ptB = ptB.next;
            if(ptA == null&&ptB!=null)ptA = headB;
            if(ptB == null&&ptA!=null)ptB = headA;
            if(ptA == null&&ptB==null)return null;
        }
        return ptA;
    }

}
