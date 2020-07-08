package listTable;

import java.util.ArrayList;
import java.util.List;

public class PartitionList {

    private static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }

    public ListNode partition(ListNode head, int x) {
        List<Integer> beforeXList = new ArrayList<>();
        List<Integer> afterXList = new ArrayList<>();
        while(head!=null){
            if(head.val<x){
                beforeXList.add(head.val);
            }else {
                afterXList.add(head.val);
            }
            head = head.next;
        }
        ListNode newHead = new ListNode(0);
        ListNode pt = newHead;
        for(int element:beforeXList){
            pt.next = new ListNode(element);
            pt = pt.next;
        }
        for(int element:afterXList){
            pt.next = new ListNode(element);
            pt = pt.next;
        }
        return newHead.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        ListNode pt = head;
        for(int i = 0;i<5;i++){
            ListNode listNode = new ListNode(i);
            pt.next = listNode;
            pt = listNode;
        }
        pt.next = new ListNode(2);
        PartitionList partitionList = new PartitionList();
        pt = partitionList.partition(head,3);
        while(pt!=null){
            System.out.println(pt.val);
            pt = pt.next;
        }
    }
}
