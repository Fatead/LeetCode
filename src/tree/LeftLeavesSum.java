package tree;

public class LeftLeavesSum {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    int sum = 0;

    public void dfs(TreeNode node,boolean isLeft){
        if(isLeft && node.left == null&&node.right == null){
            sum+=node.val;
        }
        if(node.left!=null){
            dfs(node.left,true);
        }
        if(node.right!=null){
            dfs(node.right,false);
        }
    }

    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null)return 0;
        if(root.left!=null){
            dfs(root.left,true);
        }
        if(root.right!=null){
            dfs(root.right,false);
        }
        return sum;
    }

}
