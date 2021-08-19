package listTable;
import java.util.HashSet;
import java.util.Set;

public class DeleteDuplicates3 {

    public static class ListNode{
        int val;
        ListNode next;
        ListNode(int value){
            this.val = value;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        Set<Integer> set = new HashSet<>();
        ListNode pt= head;
        while (pt!=null){
            if(pt.next!=null && pt.val == pt.next.val){
                set.add(pt.val);
            }
            pt = pt.next;
        }
        ListNode dump = new ListNode(0);
        dump.next = head;
        ListNode pre = dump;
        pt = head;
        while (pt!=null){
            if(set.contains(pt.val)){
                if(pt.next == null){
                    pre.next = null;
                    pt = null;
                }else {
                    pre.next = pt.next;
                    pt = pre.next;
                }
            }else {
                pt = pt.next;
                pre = pre.next;
            }
        }
        return dump.next;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        ListNode dumpNode = new ListNode(0);
        dumpNode.next = head;
        ListNode curr = dumpNode;
        while (curr.next!=null && curr.next.next!=null){
            if(curr.next.val == curr.next.next.val){
                int x = curr.next.val;
                while (curr.next!=null && curr.next.val == x){
                    curr.next = curr.next.next;
                }
            }else {
                curr = curr.next;
            }
        }
        return dumpNode.next;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(1);
        head.next = node1;
        node1.next = node2;
        DeleteDuplicates3 deleteDuplicates3 = new DeleteDuplicates3();
        deleteDuplicates3.deleteDuplicates(head.next);
    }

}