package listTable;

import java.util.Stack;

public class AddTwoNums {

    class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            this.val = x;
        }
    }

    private ListNode reverseList(ListNode head){
        Stack<Integer> stack = new Stack<>();
        while (head!=null){
            stack.add(head.val);
            head = head.next;
        }
        ListNode newHead = new ListNode(-1);
        ListNode pt = newHead;
        while (!stack.isEmpty()){
            ListNode node = new ListNode(stack.pop());
            pt.next = node;
            pt = pt.next;
        }
        return newHead.next;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode reverseList1 = reverseList(l1);
        ListNode reverseList2 = reverseList(l2);
        int nextBit = 0;
        ListNode head = new ListNode(-1);
        ListNode pt = head;
        while(reverseList1!=null&&reverseList2!=null){
            int value = reverseList1.val + reverseList2.val;
            ListNode node = new ListNode((value + nextBit)%10);
            if(value+nextBit>=10){
                nextBit = 1;
            }else {
                nextBit = 0;
            }
            pt.next = node;
            pt = pt.next;
            reverseList1 = reverseList1.next;
            reverseList2 = reverseList2.next;
        }
        while(reverseList1!=null){
            pt.next = new ListNode((reverseList1.val+nextBit)%10);
            pt = pt.next;
            if(reverseList1.val+nextBit>=10){
                nextBit = 1;
            }else {
                nextBit = 0;
            }
            reverseList1 = reverseList1.next;
        }
        while(reverseList2!=null){
            pt.next = new ListNode((reverseList2.val+nextBit)%10);
            pt = pt.next;
            if(reverseList2.val+nextBit>=10){
                nextBit = 1;
            }else {
                nextBit = 0;
            }
            reverseList2 = reverseList2.next;
        }
        if(nextBit>0){
            pt.next = new ListNode(nextBit);
        }
        return reverseList(head.next);
    }

    public static void main(String[] args) {

    }

}
