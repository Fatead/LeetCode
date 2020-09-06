package tree;

import java.util.ArrayList;
import java.util.List;

public class KthElement {

    List<Integer> visited = new ArrayList<>();

    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    private void preOrder(TreeNode node){
        if(node!=null){
            preOrder(node.left);
            visited.add(node.val);
            preOrder(node.right);
        }
    }

    public int kthSmallest(TreeNode root, int k) {
        preOrder(root);
        return visited.get(k-1);
    }
}
