package listTable;

import java.util.HashSet;
import java.util.Set;

public class CircleInList {

    class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            this.val = x;
        }
    }

    public boolean hasCycle(ListNode head) {
        if(head == null)return false;
        Set<ListNode> nodeSet = new HashSet<>();
        ListNode pt = head;
        while(pt!=null){
            if(nodeSet.contains(pt))return true;
            nodeSet.add(pt);
            pt = pt.next;
        }
        return false;
    }
}
