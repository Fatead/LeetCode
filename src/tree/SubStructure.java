package tree;

public class SubStructure {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    private boolean isSameTree(TreeNode A,TreeNode B){
        if(A == null && B==null)return true;
        if(A == null)return false;
        if(A.val!=B.val)return false;
        if(B.left!=null&&B.right!=null){
            return (isSameTree(A.left,B.left)&&isSameTree(A.right,B.right));
        }else if(B.left!=null){
            return isSameTree(A.left,B.left);
        }else if(B.right!=null){
            return isSameTree(A.right,B.right);
        }else {
            return true;
        }

    }

    boolean flag = false;

    private void dfs(TreeNode node,TreeNode B){
        if(node == null)return;
        if(isSameTree(node,B)){
            flag = true;
            return;
        }
        dfs(node.left,B);
        dfs(node.right,B);
    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        dfs(A,B);
        return flag;
    }

}
