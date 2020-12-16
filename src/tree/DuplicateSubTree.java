package tree;

import java.util.*;

public class DuplicateSubTree {

    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    List<TreeNode> resultList = new ArrayList<>();
    Map<String,Integer> treeCount = new HashMap<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        collect(root);
        return resultList;
    }

    private String collect(TreeNode node){
        if(node == null)return "#";
        String serial = node.val +","+ collect(node.left) +"," + collect(node.right);
        treeCount.put(serial,treeCount.getOrDefault(serial,0) + 1);
        if(treeCount.get(serial) == 2){
            resultList.add(node);
        }
        return serial;
    }

}