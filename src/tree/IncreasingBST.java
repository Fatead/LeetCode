package tree;

import java.util.ArrayList;
import java.util.List;

public class IncreasingBST {

    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    List<TreeNode> list = new ArrayList<>();

    private void postOrder(TreeNode node){
        if(node == null)return;
        postOrder(node.left);
        list.add(node);
        postOrder(node.right);
    }

    public TreeNode increasingBST(TreeNode root) {
        postOrder(root);
        for (int i = 0; i < list.size()-1; i++) {
            list.get(i).left = null;
            list.get(i).right = list.get(i+1);
        }
        list.get(list.size()-1).left = null;
        list.get(list.size()-1).right = null;
        return list.get(0);
    }

}