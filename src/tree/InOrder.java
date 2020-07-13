package tree;

import java.util.ArrayList;
import java.util.List;

public class InOrder {

    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    List<Integer> resultList = new ArrayList<>();

    private void traversal(TreeNode treeNode){
        if(treeNode == null)return;
        traversal(treeNode.left);
        resultList.add(treeNode.val);
        traversal(treeNode.right);
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        traversal(root);
        return resultList;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        treeNode1.right = treeNode2;
        treeNode2.left = treeNode3;
        InOrder inOrder = new InOrder();
        List<Integer> result =  inOrder.inorderTraversal(treeNode1);
        System.out.println(result);
    }
}
