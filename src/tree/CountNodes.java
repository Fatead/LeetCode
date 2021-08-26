package tree;

import java.util.Stack;

public class CountNodes {

    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    public int countNodes(TreeNode root) {
        if(root == null)return 0;
        Stack<TreeNode> stack = new Stack<>();
        int num = 0;
        while (root!=null || !stack.isEmpty()){
            while (root!=null){
                stack.add(root);
                root = root.left;
            }
            root = stack.pop();
            num++;
            root = root.right;
        }
        return num;
    }

}
