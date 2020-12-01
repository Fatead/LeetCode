package tree;

public class SubTree {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    private boolean hasSameStruct = false;

    private boolean sameTree(TreeNode s,TreeNode t){
        if(s == null && t == null)return true;
        if(s == null || t == null)return false;
        if(s.val!=t.val)return false;
        return sameTree(s.left,t.left) & sameTree(s.right,t.right);
    }

    private void dfs(TreeNode s,TreeNode t){
        if(hasSameStruct)return;
        if(sameTree(s,t))hasSameStruct = true;
        dfs(s.left,t);
        dfs(s.right,t);
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        dfs(s,t);
        return hasSameStruct;
    }

}
