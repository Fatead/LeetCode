package dp;

import java.util.*;

public class Rob3 {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    Map<TreeNode,Integer> fMap = new HashMap<>();
    Map<TreeNode,Integer> gMap = new HashMap<>();

    public int rob(TreeNode root) {
        dfs(root);
        return Math.max(fMap.getOrDefault(root,0),gMap.getOrDefault(root,0));
    }

    public void dfs(TreeNode node){
        if(node == null)return;
        dfs(node.left);
        dfs(node.right);
        fMap.put(node,node.val + gMap.getOrDefault(node.left,0)+gMap.getOrDefault(node.right,0));
        gMap.put(node, Math.max(fMap.getOrDefault(node.left, 0), gMap.getOrDefault(node.left, 0)) + Math.max(fMap.getOrDefault(node.right, 0), gMap.getOrDefault(node.right, 0)));
    }
}
