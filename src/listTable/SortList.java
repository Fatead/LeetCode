package listTable;

import java.util.Comparator;
import java.util.PriorityQueue;

public class SortList {


    private static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }

    public ListNode merge(ListNode pt1,ListNode pt2){
        ListNode pt = new ListNode(0);
        ListNode head = pt;
        while(pt1!=null&&pt2!=null){
            if(pt1.val<pt2.val){
                pt.next = pt1;
                pt = pt.next;
                pt1 = pt1.next;
            }else {
                pt.next = pt2;
                pt = pt.next;
                pt2 = pt2.next;
            }
        }
        if(pt1!=null)pt.next = pt1;
        if(pt2!=null)pt.next = pt2;
        return head.next;
    }

    public ListNode sortList(ListNode head) {
        if(head == null||head.next == null)return head;
        ListNode fastPt = head.next;
        ListNode slowPt = head;
        int count = 0;
        while(fastPt!=null){
            fastPt = fastPt.next;
            if(++count%2 == 0){
                slowPt = slowPt.next;
            }
        }
        ListNode tail1 = slowPt;
        slowPt = slowPt.next;
        tail1.next = null;
        ListNode head1 = sortList(head);
        ListNode head2 = sortList(slowPt);
        head = merge(head1,head2);
        return head;
    }


    public void print(ListNode head){
        while(head!=null){
            System.out.println(head.val);
            head = head.next;
        }
    }


    public static void main(String[] args) {
        ListNode head1 = new ListNode(0);
        ListNode head2 = new ListNode(0);
        ListNode pt1 = head1;
        ListNode pt2 = head2;
        for(int i = 1;i<8;i+=2){
            ListNode node1 = new ListNode(i);
            ListNode node2 = new ListNode(7-i);
            pt1.next = node1;
            pt2.next = node2;
            pt1 = pt1.next;
            pt2 = pt2.next;
        }
        SortList sortList = new SortList();
        head1 = sortList.merge(head1.next,head2.next);
        head1 = sortList.sortList(head1);
        sortList.print(head1);
    }


}
