package tree;

public class MaxPathSum {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            this.val = x;
        }
    }

    int max = Integer.MIN_VALUE;


    private int getMax(TreeNode root){
        if(root == null)return 0;
        int leftValue = Math.max(getMax(root.left),0);
        int rightValue = Math.max(getMax(root.right),0);
        max = Math.max(max, root.val + leftValue + rightValue);
        return root.val + Math.max(leftValue,rightValue);
    }

    public int maxPathSum(TreeNode root) {
        getMax(root);
        return max;
    }

}
