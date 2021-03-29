package tree;

public class MaxDepthTree {


    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            this.val = x;
        }
    }


    int maxDepth = 0;

    private void dfs(TreeNode node,int depth){
        if(node == null){
            maxDepth = Math.max(maxDepth,depth);
            return;
        }
        dfs(node.left,depth+1);
        dfs(node.right,depth+1);
    }

    public int maxDepth(TreeNode root) {
        dfs(root,0);
        return maxDepth;
    }

}
