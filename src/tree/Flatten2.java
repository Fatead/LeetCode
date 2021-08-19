package tree;

public class Flatten2 {

    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    TreeNode preNode = null;

    private void inOrder(TreeNode node){
        if(node == null)return;
        if(preNode!=null){
            preNode.left = null;
            preNode.right = node;
        }
        inOrder(node.left);
        inOrder(node.right);
    }

    public void flatten(TreeNode node){
        inOrder(node);
    }

}