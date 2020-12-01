package tree;

public class TreeTilt {

    int sum = 0;

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    private int getSum(TreeNode node){
        if(node == null)return 0;
        if(node.left == null && node.right == null)return node.val;
        return getSum(node.left) + getSum(node.right) + node.val;
    }

    public int findTilt(TreeNode root) {
        if(root == null)return 0;
        sum += Math.abs(getSum(root.left) - getSum(root.right));
        findTilt(root.left);
        findTilt(root.right);
        return sum;
    }

    int tilt = 0;

    public int findTilt2(TreeNode root){
        traverse(root);
        return tilt;
    }

    public int traverse(TreeNode root){
        if(root == null)return 0;
        int left = traverse(root.left);
        int right = traverse(root.right);
        tilt += Math.abs(left - right);
        return left + right + root.val;
    }

}
