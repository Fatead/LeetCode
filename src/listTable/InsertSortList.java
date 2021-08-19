package listTable;

public class InsertSortList {

    private static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            this.val = x;
        }
    }

    public void printList(ListNode node){
        System.out.println("现在的链表为：");
        while (node!=null){
            System.out.println(node.val + " ");
            node = node.next;
        }
    }

    public ListNode insertionSortList(ListNode head) {
        if(head == null)return head;
        ListNode dump = new ListNode(0);
        ListNode pt = head,next = pt.next;
        ListNode pt2 = dump.next;
        ListNode pre = dump;
        while (next!=null){
            pt.next = null;
            pt2 = dump.next;
            pre = dump;
            //printList(dump.next);
            while (pt2!=null && pt2.val < pt.val){
                pre = pt2;
                pt2 = pt2.next;
            }
            pre.next = pt;
            pt.next = pt2;
            pt = next;
            next = pt.next;
        }
        pt2 = dump.next;
        pre = dump;
        while (pt2!=null && pt2.val< pt.val){
            pre = pt2;
            pt2 = pt2.next;
        }
        pre.next = pt;
        pt.next = pt2;
        return dump.next;
    }


    public static void main(String[] args) {
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(1);
//        ListNode node4 = new ListNode(7);
//        ListNode node5 = new ListNode(8);
//        ListNode node6 = new ListNode(-2);
//        ListNode node7 = new ListNode(8);
        node1.next = node2;
        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//        node5.next = node6;
//        node6.next = node7;
        InsertSortList insertSortList = new InsertSortList();
        ListNode resultNode = insertSortList.insertionSortList(node1);
        while (resultNode!=null){
            System.out.println(resultNode.val);
            resultNode = resultNode.next;
        }
    }

}