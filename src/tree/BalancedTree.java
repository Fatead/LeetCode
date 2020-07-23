package tree;

public class BalancedTree {

    public class TreeNode{
        public TreeNode left;
        public TreeNode right;
        public int value;
        public TreeNode(int value){
            this.value = value;
        }
    }


    public int maxDepth(TreeNode root){
        if(root == null)return 0;
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth,rightDepth) + 1;
    }

    public boolean isBalanced(TreeNode root) {
        if(root!=null){
            if(Math.abs(maxDepth(root.left) - maxDepth(root.right)) <2){
                return (isBalanced(root.left) && isBalanced(root.right));
            }else {
                return false;
            }
        }return true;
    }

}
