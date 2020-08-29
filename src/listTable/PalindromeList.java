package listTable;

import java.util.ArrayList;
import java.util.List;

public class PalindromeList {


    private static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            this.val = x;
        }
    }

    public boolean isPalindrome(ListNode head) {
        if(head == null)return false;
        List<Integer> list = new ArrayList<>();
        ListNode pt = head;
        while(pt!=null){
            list.add(pt.val);
            pt = pt.next;
        }
        for(int i = 0;i<list.size()/2 + 1;i++){
            if(!list.get(i).equals(list.get(list.size() - i - 1))){
                return false;
            }
        }
        return true;
    }

}
