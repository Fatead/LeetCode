package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PreOrderTree {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int value){
            this.val = value;
        }
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root!=null || !stack.isEmpty()){
            while (root!=null){
                stack.add(root);
                list.add(root.val);
                root = root.left;
            }
            root = stack.pop();
            root = root.right;
        }
        return list;
    }

}
