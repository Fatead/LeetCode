package tree;

public class LongestUnivaluePath {


    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    int result;

    public int arrowLength(TreeNode node){
        if(node == null)return 0;
        int left = arrowLength(node.left);
        int right = arrowLength(node.right);
        int arrowLeft = 0;
        int arrowRight = 0;
        if(node.left!=null && node.left.val == node.val){
            arrowLeft = left + 1;
        }
        if(node.right!=null && node.right.val == node.val){
            arrowRight = right + 1;
        }
        result = Math.max(result,arrowLeft + arrowRight);
        return Math.max(arrowLeft,arrowRight);
    }

    public int longestUnivaluePath(TreeNode root) {
        if(root == null || (root.left == null&&root.right ==null))return 0;
        result = 0;
        arrowLength(root);
        return result;
    }


}
