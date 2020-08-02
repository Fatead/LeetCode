package listTable;

import java.util.Comparator;
import java.util.PriorityQueue;

public class InsertSort {

    private static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            this.val = x;
        }
    }

    private class ListNodeComparator implements Comparator<ListNode>{

        @Override
        public int compare(ListNode listNode, ListNode t1) {
            return listNode.val - t1.val;
        }
    }

    public ListNode insertionSortList(ListNode head) {
        if(head == null)return null;
        if(head.next == null)return head;
        ListNodeComparator comparator = new ListNodeComparator();
        PriorityQueue<ListNode> queue = new PriorityQueue<>(comparator);
        ListNode pt = head;
        while(pt!=null){
            queue.add(pt);
            pt = pt.next;
        }
        ListNode newHead = queue.poll();
        pt = newHead;
        while(!queue.isEmpty()&&pt!=null){
            pt.next = queue.poll();
            pt = pt.next;
        }
        if(pt!=null)
            pt.next = null;
        return newHead;
    }

    public void print(ListNode head){
        while(head!=null){
            System.out.println(head.val);
            head = head.next;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(10);
        ListNode pt = head;
        for(int i = 9;i>0;i--){
            ListNode node = new ListNode(i);
            pt.next = node;
            pt = node;
        }
        InsertSort insertSort = new InsertSort();
        ListNode q = insertSort.insertionSortList(head);
        insertSort.print(q);
    }
}
