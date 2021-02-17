package tree;

import java.util.*;

/**
 * 一棵树如果左右高度相同则根节点就是
 * 最小子树，如果不是则向高的一侧递归
 */
public class SubTreeDeepest {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    int maxDepth = 0;
    Map<TreeNode,Integer> map = new HashMap<>();


    private void dfs(TreeNode treeNode,TreeNode parentNode){
        if(treeNode!=null){
            map.put(treeNode,map.get(parentNode)+1);
            maxDepth = Math.max(maxDepth,map.get(treeNode));
            dfs(treeNode.left,treeNode);
            dfs(treeNode.right,treeNode);
        }
    }

    private TreeNode answer(TreeNode node){
        if(node==null||map.get(node)==maxDepth){
            return node;
        }
        TreeNode leftAns = answer(node.left);
        TreeNode rightAns = answer(node.right);
        if(leftAns!=null&&rightAns!=null){
            return node;
        }
        if(leftAns!=null){
            return leftAns;
        }
        return rightAns;
    }

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        map.put(null,-1);
        dfs(root,null);
        return answer(root);
    }

}