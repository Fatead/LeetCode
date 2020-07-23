package tree;

public class MinDepth {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            this.val = x;
        }
    }

    public int min(TreeNode root){
        if(root == null)return 0;
        int leftDepth = minDepth(root.left);
        int rightDepth = minDepth(root.right);
        return Math.min(leftDepth,rightDepth) + 1;
    }

    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null){
            return minDepth(root.right) + 1;
        }
        if(root.right == null){
            return minDepth(root.left) + 1;
        }
        return min(root);
    }
}
