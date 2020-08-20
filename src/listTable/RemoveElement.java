package listTable;

public class RemoveElement {


    private static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
        ListNode(int val,ListNode next){
            this.val = val;
            this.next= next;
        }
    }

    public ListNode removeElements(ListNode head, int val) {
        if(head == null)return head;
        while(head.val == val){
            head = head.next;
        }
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode pt = head;
        while(pt!=null){
            if(pt.val == val){
                pre.next = pt.next;
                pt.next = null;
                pt = pre.next;
            }else{
                pt = pt.next;
                pre = pre.next;
            }
        }
        return head;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode next = new ListNode(1);
        head.next = next;
        RemoveElement removeElement = new RemoveElement();
        removeElement.removeElements(head,1);
    }


}
