package tree;

import java.util.ArrayList;
import java.util.List;

public class PreOrder {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            this.val = x;
        }
    }

    List<Integer> integerList = new ArrayList<>();

    private void preOrder(TreeNode node){
        if(node == null)return;
        integerList.add(node.val);
        preOrder(node.left);
        preOrder(node.right);
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        preOrder(root);
        return integerList;
    }
}
