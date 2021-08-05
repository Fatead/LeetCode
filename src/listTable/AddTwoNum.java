package listTable;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class AddTwoNum {


    class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            this.val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int nextBit = 0;
        ListNode pt1 = l1, pt2 = l2;
        ListNode dump = new ListNode(0);
        ListNode pt = dump;
        while (pt1!= null && pt2!= null){
            int value = pt1.val + pt2.val + nextBit;
            pt1 = pt1.next;
            pt2 = pt2.next;
            if(value>=10){
                nextBit = 1;
                value = value%10;
            }else {
                nextBit = 0;
            }
            pt.next = new ListNode(value);
            pt = pt.next;
        }
        while (pt1!= null){
            int value = pt1.val + nextBit;
            pt1 = pt1.next;
            if(value>=10){
                nextBit = 1;
                value = value%10;
            }else {
                nextBit = 0;
            }
            pt.next = new ListNode(value);
            pt = pt.next;
        }

        while (pt2!= null){
            int value = pt2.val + nextBit;
            pt2 = pt2.next;
            if(value>=10){
                nextBit = 1;
                value = value%10;
            }else {
                nextBit = 0;
            }
            pt.next = new ListNode(value);
            pt = pt.next;
        }
        if(nextBit == 1){
            ListNode node = new ListNode(nextBit);
            pt.next = node;
        }
        return dump.next;
    }

}