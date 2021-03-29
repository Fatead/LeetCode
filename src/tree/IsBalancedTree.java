package tree;

import java.util.HashMap;
import java.util.Map;

public class IsBalancedTree {

    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
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

    private int getDepth(TreeNode node){
        maxDepth = 0;
        dfs(node,0);
        return maxDepth;
    }

    public boolean isBalanced(TreeNode root) {
        if(root == null)return true;
        if(Math.abs(getDepth(root.left) - getDepth(root.right))>2)return false;
        return (isBalanced(root.left) && isBalanced(root.right));
    }

    /**
     * 使用后序遍历对于节点进行访问，因为后序遍历最后访问根节点，所以能先得到左子树和右子树的高度
     * 如果左子树和右子树的高度差大于2的话直接返回-1，表明非平衡树
     */
    private int recur(TreeNode node){
        if(node == null)return 0;
        int left = recur(node.left);
        if(left == -1){
            return -1;
        }
        int right = recur(node.right);
        if(right == -1){
            return -1;
        }
        return Math.abs(left-right)<2 ? Math.max(left,right) + 1:-1;
    }

    public boolean isBalanced2(TreeNode root){
        return recur(root) !=-1;
    }

}
