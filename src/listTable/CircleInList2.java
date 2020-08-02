package listTable;

import java.util.HashSet;
import java.util.Set;

public class CircleInList2 {

    class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            this.val = x;
        }
    }

    public ListNode detectCycle(ListNode head) {
        if(head == null)return null;
        Set<ListNode> nodeSet = new HashSet<>();
        ListNode pt = head;
        while(pt!=null){
            if(nodeSet.contains(pt))return pt;
            nodeSet.add(pt);
            pt = pt.next;
        }
        return null;
    }

}
