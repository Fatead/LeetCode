package listTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class IntersectionNode {


    private static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            this.val = x;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null)return null;
        List<ListNode> listA = new ArrayList<>();
        List<ListNode> listB = new ArrayList<>();
        ListNode ptA = headA;
        ListNode ptB = headB;
        while (ptA!=null){
            listA.add(ptA);
            ptA = ptA.next;
        }
        while (ptB!=null){
            listB.add(ptB);
            ptB = ptB.next;
        }
        int size = Math.min(listA.size(),listB.size());
        int count = 1;
        for (int i = 0; i <size ; i++) {
            if(!listA.get(listA.size()-count).equals(listB.get(listB.size()-count))){
                if(count>1)
                    return listA.get(listA.size()-count+1);
                else
                    return null;
            }
            count++;
        }
        if(listA.size()<=listB.size()){
            return listA.get(0);
        }else{
            return listB.get(0);
        }
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if(headA == null || headB == null)return null;
        ListNode ptA = headA;
        ListNode ptB = headB;
        boolean flagA = false;
        while (ptA!=ptB){
            if(ptA == null && !flagA){
                ptA = headB;
                flagA = true;
            }else if(ptA == null){
              return null;
            } else {
                ptA = ptA.next;
            }
            if(ptB == null){
                ptB = headA;
            }else {
                ptB = ptB.next;
            }
        }
        return ptA;
    }

    public static void main(String[] args) {

    }
}
