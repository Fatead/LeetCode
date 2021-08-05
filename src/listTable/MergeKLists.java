package listTable;

import java.util.Arrays;

public class MergeKLists {

    private static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            this.val = x;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //创建傀儡头节点
        ListNode head = new ListNode(0);
        ListNode pt = head;
        while (l1!=null && l2!=null){
            if(l1.val< l2.val){
                pt.next = l1;
                pt = pt.next;
                l1 = l1.next;
            }else {
                pt.next = l2;
                pt = pt.next;
                l2 = l2.next;
            }
        }
        while (l1!=null){
            pt.next = l1;
            pt = pt.next;
            l1 = l1.next;
        }
        while (l2!=null){
            pt.next = l2;
            pt = pt.next;
            l2 = l2.next;
        }
        return head.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0)return null;
        if(lists.length == 1)return lists[0];
        //if(lists.length == 2)return mergeTwoLists(lists[0],lists[1]);
        return mergeTwoLists(mergeKLists(Arrays.copyOfRange(lists,0,lists.length/2)),mergeKLists(Arrays.copyOfRange(lists,lists.length/2,lists.length)));
    }

}
