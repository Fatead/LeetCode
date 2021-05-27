package stack;

import java.util.*;

public class PostOrder {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            this.val = x;
        }
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null)return result;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root!=null || stack.isEmpty()){
            while (root!=null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(root.right == null || root.right == pre){
                result.add(root.val);
                pre = root;
                root = null;
            }else {
                stack.push(root);
                root = root.right;
            }
        }
        return result;
    }

    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if(root == null)return result;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()){
            root = stack.pop();
            if(root.left!=null){
                stack.add(root.left);
            }
            if(root.right!=null){
                stack.add(root.right);
            }
            result.add(0,root.val);
        }
        return result;
    }

}
