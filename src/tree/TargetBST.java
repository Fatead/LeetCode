package tree;

import java.util.HashSet;
import java.util.Set;

public class TargetBST {


    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            this.val = x;
        }
    }


    Set<Integer> valueSet = new HashSet<>();

    private void preOrder(TreeNode node){
        valueSet.add(node.val);
        if(node.left!=null)preOrder(node.left);
        if(node.right!=null)preOrder(node.right);
    }

    public boolean findTarget(TreeNode root, int k) {
        if(root == null)return false;
        preOrder(root);
        for(int i:valueSet){
            if(valueSet.contains(k-i))return true;
        }
        return false;
    }

}
