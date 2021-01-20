package tree;

public class PruneTree {

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            this.val = x;
        }
    }

    private boolean isTargetZeroTree(TreeNode node){
        if(node == null)return true;
        if(node.val == 1){
            return false;
        }else {
            return isTargetZeroTree(node.left)&&isTargetZeroTree(node.right);
        }
    }


    public TreeNode pruneTree(TreeNode root) {
        if(root == null||isTargetZeroTree(root))return null;
        if(isTargetZeroTree(root.left)){
            root.left = null;
        }
        if(isTargetZeroTree(root.right)){
            root.right = null;
        }
        pruneTree(root.left);
        pruneTree(root.right);
        return root;
    }


}