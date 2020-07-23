package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListToBST {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    public class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val = x;
        }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length == 0)return null;
        if(nums.length == 1)return new TreeNode(nums[0]);
        int len = nums.length;
        int mid = len/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(Arrays.copyOfRange(nums,0,mid));
        root.right = sortedArrayToBST(Arrays.copyOfRange(nums,mid+1,len));
        return root;
    }

    public TreeNode sortedListToBST2(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode pt = head;
        while(pt!=null){
            list.add(pt.val);
            pt = pt.next;
        }
        int[] array = new int[list.size()];
        for(int i = 0;i<list.size();i++){
            array[i] = list.get(i);
        }
        return sortedArrayToBST(array);
    }


    public TreeNode sortedListToBST(ListNode head) {
        if(head == null)return null;
        ListNode fast_pt = head;
        ListNode slow_pt = head;
        ListNode pre_pt = null;
        while(fast_pt!=null && fast_pt.next!=null){
            pre_pt = slow_pt;
            fast_pt = fast_pt.next.next;
            slow_pt = slow_pt.next;
        }
        if(pre_pt!=null)pre_pt.next =null;
        if(head == slow_pt){
            return new TreeNode(head.val);
        }
        TreeNode treeNode = new TreeNode(slow_pt.val);
        treeNode.left = sortedListToBST(head);
        treeNode.right = sortedListToBST(slow_pt.next);
        return treeNode;
    }



}
