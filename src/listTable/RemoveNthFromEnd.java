package listTable;

import java.util.Stack;

public class RemoveNthFromEnd {

    public class ListNode{

        int val;
        ListNode next;
        public ListNode(int value){
            this.val = value;
        }

    }

    /**
     * 基于双指针的方法删除节点
     */
    public ListNode removeNthFromEnd(ListNode head, int n){
        if(head == null)return null;
        ListNode pt1 = head;
        ListNode dump = new ListNode(0);
        dump.next = head;
        ListNode pt2 = dump;
        while (n>1){
            pt1 = pt1.next;
            n--;
        }
        while (pt1!=null){
            pt1 = pt1.next;
            pt2 = pt2.next;
        }
        pt2.next = pt2.next.next;
        return head;
    }

    /**
     * 基于栈的方法删除节点
     */
    public ListNode removeNthFromEnd2(ListNode head,int n){
        if(head == null)return head;
        ListNode dump = new ListNode(0);
        dump.next = head;
        Stack<ListNode> stack = new Stack<>();
        ListNode pt = head;
        while (pt!=null){
            stack.add(pt);
            pt = pt.next;
        }
        while (n>0){
            stack.pop();
            n--;
        }
        ListNode list;
        if(stack.isEmpty()){
            list = dump;
        }else {
            list = stack.pop();
        }
        list.next = list.next.next;
        return dump.next;
    }

    /**
     * 先获取链表的长度，然后根据链表长度获取需要删除节点的位置
     */
    public ListNode removeNthFromEnd3(ListNode head,int n){
        //创建傀儡节点主要是为了应对删除头节点的情况
        ListNode dump = new ListNode(0);
        dump.next = head;
        ListNode pt = dump.next;
        int length = 0;
        while (pt!=null){
            pt = pt.next;
            length++;
        }
        pt = dump;
        for (int i = 0; i <length-n ; i++) {
            pt = pt.next;
        }
        if(pt!=null && pt.next != null)pt.next = pt.next.next;
        return dump.next;
    }

}