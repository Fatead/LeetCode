package listTable;

public class MergeList {

    private static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            this.val = x;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null)return l2;
        if(l2 == null)return l1;
        ListNode head,pt = new ListNode(Integer.MAX_VALUE);
        head = pt;
        while(l1!=null&&l2!=null){
            if(l1.val<=l2.val){
                ListNode node = new ListNode(l1.val);
                pt.next = node;
                pt = node;
                l1 = l1.next;
            }else{
                ListNode node = new ListNode(l2.val);
                pt.next = node;
                pt = node;
                l2 = l2.next;
            }
        }
        if(l1==null){
            pt.next = l2;
        }
        if(l2 == null){
            pt.next = l1;
        }
        return head.next;
    }
}
