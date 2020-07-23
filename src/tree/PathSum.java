package tree;

import dp.PathNum;

public class PathSum {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            this.val = x;
        }
    }

    private boolean pathSum(TreeNode root,int sum){
        if(root == null)return false;
        if(root.left == null && root.right == null && root.val == sum)return true;
        return pathSum(root.left,sum - root.val) || pathSum(root.right,sum - root.val);
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null)return false;
        if(root.left == null && root.right == null){
            return root.val == sum;
        }
        if(root.left == null){
            return hasPathSum(root.right,sum - root.val);
        }
        if(root.right == null){
            return hasPathSum(root.left,sum-root.val);
        }
        return pathSum(root,sum);
    }

    private TreeNode buildTree(){
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(-2);
        TreeNode node2 = new TreeNode(-3);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(-2);
        TreeNode node6 = new TreeNode(-1);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node3.left = node6;
        return root;
    }

    public static void main(String[] args) {
        PathSum pathSum = new PathSum();
        TreeNode treeNode = pathSum.buildTree();
        System.out.println(pathSum.hasPathSum(treeNode,0));
    }
}
