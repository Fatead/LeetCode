package tree;

import java.util.Arrays;

public class ArrayToBST {

    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
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

}
