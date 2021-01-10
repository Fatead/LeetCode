package tree;

public class MinDiffInBST {


    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            this.val = x;
        }
    }

    int min = Integer.MAX_VALUE;
    int pre = Integer.MIN_VALUE;

    private void postOrder(TreeNode node){
        if(node == null)return;
        if(node.left!=null){
            postOrder(node.left);
        }
        if(pre!=Integer.MIN_VALUE){
            min = Math.min(min,node.val-pre);
        }
        pre = node.val;
        if(node.right!=null){
            postOrder(node.right);
        }
    }

    public int minDiffInBST(TreeNode root) {
        postOrder(root);
        return min;
    }

}
