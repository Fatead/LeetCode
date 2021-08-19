package tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class IsSymmetric {

    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    private boolean isSym(TreeNode node1,TreeNode node2){
        if(node1 == null && node2 == null)return true;
        if(node1 == null || node2 == null)return false;
        if(node1.val != node2.val)return false;
        return isSym(node1.left,node2.right) && isSym(node1.right,node2.left);
    }

    public boolean isSymmetric(TreeNode root) {
        if(root == null)return false;
        return isSym(root.left,root.right);
    }

}