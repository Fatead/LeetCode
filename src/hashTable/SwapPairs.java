package hashTable;

import java.util.LinkedList;
import java.util.Queue;

public class SwapPairs {


    class ListNode{

        int val;
        ListNode next;

        public ListNode(int value){
            this.val = value;
        }

    }

    /**
     * 利用队列交换节点的顺序
     */
    public ListNode swapPairs(ListNode head) {
        Queue<ListNode> queue1 = new LinkedList<>();
        Queue<ListNode> queue2 = new LinkedList<>();
        ListNode pt = head;
        int count = 0;
        while (pt!=null){
            if(count++%2 == 0){
                queue1.add(pt);
            }else {
                queue2.add(pt);
            }
            pt = pt.next;
        }
        ListNode newHead = new ListNode(0);
        pt = newHead;
        while (!queue1.isEmpty() && !queue2.isEmpty()){
            ListNode node1 = queue2.poll();
            pt.next = node1;
            pt = pt.next;
            ListNode node2 = queue1.poll();
            pt.next = node2;
            pt = pt.next;
            pt.next = null;
        }
        if(!queue1.isEmpty()){
            ListNode node = queue1.poll();
            pt.next = node;
            pt = pt.next;
            pt.next = null;
        }
        return newHead.next;
    }

    public ListNode swapPairs2(ListNode head) {
        if(head == null || head.next == null)return head;
        ListNode pt1 = head;
        ListNode pt2 = head.next;
        pt1.next = pt2.next;
        pt2.next = pt1;
        pt1.next = swapPairs2(pt1.next);
        return pt2;
    }

    public static void main(String[] args) {

    }

}
