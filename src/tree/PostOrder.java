package tree;

import java.util.ArrayList;
import java.util.List;

public class PostOrder {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            this.val = x;
        }
    }

    List<Integer> result = new ArrayList<>();

    private void postOrder(TreeNode node){
        if(node == null)return;
        postOrder(node.left);
        postOrder(node.right);
        result.add(node.val);
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        postOrder(root);
        return result;
    }
}


