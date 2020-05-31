package tree;

public class SymmetricTree {

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    public boolean symmetric(TreeNode left,TreeNode right){
        if(left == null&&right==null)return true;
        else if((left == null&&right!=null)||(left!=null&&right==null))return false;
        else if(left.val!=right.val)return false;
        else if(left.left == null&&left.right == null&&right.left==null&&right.right==null)return true;
        else if((left.left==null&&right.right!=null)||(left.left!=null&&right.right == null))return false;
        else if((left.right==null&&right.left!=null)||(left.right!=null&&right.left == null))return false;
        else{
            return symmetric(left.left,right.right)&&symmetric(left.right,right.left);
        }
    }

    public boolean isSymmetric(TreeNode root) {
        if(root == null)return true;
        return symmetric(root.left,root.right);
    }

    public static void main(String[] args) {

    }

}
