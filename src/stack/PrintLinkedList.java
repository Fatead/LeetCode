package stack;

import java.util.Stack;

public class PrintLinkedList {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public int[] reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode pt = head;
        while (pt!=null){
            stack.push(pt.val);
            pt = pt.next;
        }
        int[] result = new int[stack.size()];
        int pos = 0;
        while (!stack.isEmpty()){
            result[pos] = stack.pop();
            pos++;
        }
        return result;
    }

    public static void main(String[] args) {

    }

}
