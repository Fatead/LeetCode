package list;

import java.util.Stack;

public class FindN {

    public static class ListNode{
        int value;
        ListNode next;
        ListNode(int x){
            this.value = x;
        }
    }


    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null)return null;
        Stack<Integer> myStack = new Stack<>();
        ListNode pt = head;
        while(pt!=null){
            myStack.push(pt.value);
            pt = pt.next;
        }
        int length = myStack.size();
        for(int i = 0;i<length;i++){
            int value = myStack.pop();
            if(i!=n-1){
                ListNode node = new ListNode(value);
                node.next = pt;
                pt = node;
            }
        }
        return pt;
    }

    public static void main(String[] args) {
        ListNode head = null,pt = null;
        FindN findN = new FindN();
        for(int i = 1;i<=5;i++){
            if(i == 1){
                ListNode node = new ListNode(i);
                head = node;
                pt = node;
            }else {
                ListNode node = new ListNode(i);
                pt.next = node;
                pt = node;
            }
        }
        head = findN.removeNthFromEnd(head,2);
        while(head!=null){
            System.out.println(head.value);
            head = head.next;
        }
    }
}
