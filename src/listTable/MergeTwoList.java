package listTable;

public class MergeTwoList {

    class ListNode{

        int val;
        ListNode next;

        public ListNode(int value){
            this.val = value;
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

}
