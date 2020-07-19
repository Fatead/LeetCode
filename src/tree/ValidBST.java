package tree;

public class ValidBST {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            this.val = x;
        }
    }

    private boolean isVaild(TreeNode root,int val,boolean isLeft){
        if(root == null)return true;
        if(isLeft){
            if(root.val>=val)return false;
        }else {
            if(root.val<=val)return false;
        }
        return isVaild(root.left,val,isLeft) && isVaild(root.right,val,isLeft);
    }



    public boolean isValidBST(TreeNode root) {
        if(root == null||root.left == null && root.right == null)return true;
        boolean result = isVaild(root.left,root.val,true) && isVaild(root.right,root.val,false);
        if(!result)return false;
        return isValidBST(root.left)&&isValidBST(root.right);
    }

    public static void main(String[] args) {

    }
}
