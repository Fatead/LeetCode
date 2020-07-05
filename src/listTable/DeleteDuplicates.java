package listTable;

public class DeleteDuplicates {

    public static class ListNode{
        int val;
        ListNode next;
        ListNode(int value){
            this.val = value;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode pt = head;
        if(pt == null||pt.next == null)return head;
        ListNode ptNext = pt.next;
        while(pt.next!=null){
            if(pt.val == ptNext.val){
                pt.next = ptNext.next;
                ptNext = pt.next;
            }else {
                pt = pt.next;
                ptNext = ptNext.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode pt = head;
        for(int i = 0;i<2;i++){
            ListNode listNode = new ListNode(1);
            pt.next = listNode;
            pt = listNode;
        }
        ListNode listNode = new ListNode(2);
        pt.next = listNode;
        pt = listNode;
        DeleteDuplicates deleteDuplicates = new DeleteDuplicates();
        pt = deleteDuplicates.deleteDuplicates(head);
        while(pt!=null){
            System.out.println(pt.val);
            pt = pt.next;
        }
    }

}
