package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PostOrder {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            this.val = x;
        }
    }

    List<Integer> result = new ArrayList<>();

    private void postOrder(TreeNode node){
        if(node == null)return;
        postOrder(node.left);
        postOrder(node.right);
        result.add(node.val);
    }

    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null)return list;
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.add(root);
        while (!stack1.isEmpty()){
            TreeNode node = stack1.pop();
            stack2.add(node);
            if(node.left!=null){
                stack1.add(node.left);
            }
            if(node.right!=null){
                stack1.add(node.right);
            }
        }
        while (!stack2.isEmpty()){
            list.add(stack2.pop().val);
        }
        return list;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        postOrder(root);
        return result;
    }
}


