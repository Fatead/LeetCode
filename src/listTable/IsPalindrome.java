package listTable;

import java.util.ArrayList;
import java.util.List;

public class IsPalindrome {

    private static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            this.val = x;
        }
    }

    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode pt = head;
        while (pt!=null){
            list.add(pt.val);
            pt = pt.next;
        }
        int n = list.size();
        for (int i = 0; i <list.size()/2 ; i++) {
            if(!list.get(i).equals(list.get(n - i - 1))){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

    }

}
