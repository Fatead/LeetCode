package tree;

import java.util.ArrayList;
import java.util.List;

public class BST2List {

    public class TreeNode{
        public TreeNode left;
        public TreeNode right;
        public int val;
        public TreeNode(int value){
            this.val = value;
        }
        public TreeNode(int value,TreeNode left,TreeNode right){
            this.val = value;
            this.left = left;
            this.right = right;
        }
    }

    List<TreeNode> list = new ArrayList<>();

    private void visit(TreeNode root){
        if(root == null)return;
        list.add(root);
        visit(root.left);
        visit(root.right);
    }

    public void flatten(TreeNode root) {
        if(root == null)return;
        visit(root);
        TreeNode treeNode = list.get(0);
        for(int i =1;i<list.size();i++){
            TreeNode node = list.get(i);
            treeNode.right = node;
            treeNode.left = null;
            treeNode = node;
        }
    }

}
