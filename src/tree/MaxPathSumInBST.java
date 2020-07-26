package tree;

public class MaxPathSumInBST {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            this.val = x;
        }
    }
    int max = Integer.MIN_VALUE;

    private int getValueOfTree(TreeNode node){
        if(node == null)return 0;
        int leftValue = Math.max(0,getValueOfTree(node.left));
        int rightValue = Math.max(0,getValueOfTree(node.right));
        max = Math.max(max,node.val + leftValue + rightValue);
        return node.val + Math.max(leftValue,rightValue);
    }

    public int maxPathSum(TreeNode root) {
        if(root == null)return 0;
        getValueOfTree(root);
        return max;
    }

}
