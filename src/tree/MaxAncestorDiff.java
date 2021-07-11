package tree;

import java.util.ArrayList;
import java.util.List;

public class MaxAncestorDiff {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    int min = 0;

    private void dfs(TreeNode node, List<Integer> list){
        if(node == null)return;
        for (Integer integer : list) {
            min = Math.max(Math.abs(integer - node.val),min);
        }
        List<Integer> newList = new ArrayList<>(list);
        newList.add(node.val);
        dfs(node.left,newList);
        dfs(node.right,newList);
    }

    public int maxAncestorDiff(TreeNode root) {
        dfs(root,new ArrayList<>());
        return min;
    }

}