package listTable;

import java.util.Stack;

public class RotateList {


    private static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }

    public ListNode rotateRight(ListNode head, int k) {
        if(k == 0||head == null)return head;
        ListNode pt1 = head,pt2 = head;
        for(int i= 0;i<k;i++){
            if(pt1.next!=null)
                pt1 = pt1.next;
            else pt1 = head;
        }
        while(pt1.next!=null){
            pt1 = pt1.next;
            pt2 = pt2.next;
        }
        ListNode pt = pt2.next;
        pt2.next = null;
        Stack<Integer> integerStack = new Stack<>();
        while(pt!=null){
            integerStack.push(pt.val);
            pt = pt.next;
        }
        while(!integerStack.isEmpty()){
            Integer val = integerStack.pop();
            ListNode node = new ListNode(val);
            node.next = head;
            head = node;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode pt = head;
        for(int i =0;i<=2;i++){
            ListNode listNode = new ListNode(i);
            pt.next = listNode;
            pt = listNode;
        }
        pt = head.next;
        RotateList rotateList = new RotateList();
        pt = rotateList.rotateRight(pt,4);
        while(pt!=null){
            System.out.println(pt.val);
            pt = pt.next;
        }
    }
}
