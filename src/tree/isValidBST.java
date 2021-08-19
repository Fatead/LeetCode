package tree;

public class isValidBST {

    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }


    private boolean isValid(TreeNode node, long low, long high){
        if(node == null)return true;
        if(node.val <=low || node.val>=high)return false;
        return isValid(node.left,low, node.val) && isValid(node.right, node.val,high);
    }

    public boolean isValidBST(TreeNode node){
        return isValid(node,Long.MIN_VALUE,Long.MAX_VALUE);
    }

}
