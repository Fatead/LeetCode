package tree;

import java.util.ArrayList;
import java.util.List;

public class AllPath {

    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    List<String> resultList = new ArrayList<>();

    private void inOrder(TreeNode node,StringBuilder path){
        path.append("->").append(node.val);
        if(node.right == null && node.left == null){
            resultList.add(path.toString());
        }else if(node.left == null){
            inOrder(node.right,new StringBuilder(path));
        }else if(node.right == null){
            inOrder(node.left,new StringBuilder(path));
        }else {
            inOrder(node.left,new StringBuilder(path));
            inOrder(node.right,new StringBuilder(path));
        }
    }

    public List<String> binaryTreePaths(TreeNode root) {
        if(root == null)return resultList;
        StringBuilder path = new StringBuilder();
        path.append(root.val);
        if(root.left!=null)inOrder(root.left,new StringBuilder(path));
        if(root.right!=null)inOrder(root.right,new StringBuilder(path));
        if(root.right == null && root.left == null){
            resultList.add(path.toString());
        }
        return resultList;
    }

}
