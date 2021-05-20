package tree;

import java.util.LinkedList;
import java.util.Queue;

public class UnivalTree {



    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    public boolean isUnivalTree(TreeNode root) {
        if(root == null)return false;
        int value = root.val;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node.val!=value){
                return false;
            }
            if(node.left!=null){
                queue.add(node.left);
            }
            if(node.right!=null){
                queue.add(node.right);
            }
        }
        return true;
    }


}
