package listTable;

import java.util.*;

public class ListComponents {

    private static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            this.val = x;
        }
    }

    public int numComponents(ListNode head, int[] G) {
        Map<Integer,Integer> map = new HashMap<>();
        ListNode pt = head;
        while(pt!=null){
            map.put(pt.val,Integer.MIN_VALUE);
            pt = pt.next;
        }
        for(int i:G){
            map.put(i,1);
        }
        pt = head;
        int num = 0;
        if(map.get(pt.val)==1){
            num++;
        }
        while(pt.next!=null){
            if(map.get(pt.val)==Integer.MIN_VALUE&&map.get(pt.next.val)==1){
                num++;
            }
            pt = pt.next;
        }
        return num;
    }

    public static void main(String[] args) {

    }

}
