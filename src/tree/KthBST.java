package tree;

import java.util.ArrayList;
import java.util.List;

public class KthBST {

    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }


    List<Integer> list = new ArrayList<>();

    private void postOrder(TreeNode node){
        if(node == null)return;
        postOrder(node.left);
        list.add(node.val);
        postOrder(node.right);
    }

    public int kthLargest(TreeNode root, int k) {
        postOrder(root);
        return list.get(list.size()-k);
    }


}
