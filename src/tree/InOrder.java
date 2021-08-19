package tree;

import java.util.*;

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

    /**
     * 使用递归法对树实现中序遍历
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        traversal(root);
        return resultList;
    }

    /**
     * 使用迭代法实现中序遍历
     */
    public List<Integer> inorderTraversal2(TreeNode root){
        List<Integer> result = new ArrayList<>();
        //Stack底层是使用Vector实现的，效率较低，应该改为使用LinkedList实现的Deque效率更高。
        Deque<TreeNode> stack = new LinkedList<>();
        while (root!=null || !stack.isEmpty()){
            while (root!=null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            result.add(root.val);
            root = root.right;
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        treeNode1.right = treeNode2;
        treeNode2.left = treeNode3;
        InOrder inOrder = new InOrder();
        List<Integer> result =  inOrder.inorderTraversal2(treeNode1);
        System.out.println(result);
    }

}
