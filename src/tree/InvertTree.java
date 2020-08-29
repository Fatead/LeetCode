package tree;

public class InvertTree {


    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    private void exchange(TreeNode node){
        if(node == null){
            return;
        }
        TreeNode tempNode = node.left;
        node.left = node.right;
        node.right = tempNode;
        exchange(node.left);
        exchange(node.right);
    }

    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return null;
        }
        exchange(root);
        return root;
    }

}
