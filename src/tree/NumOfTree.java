package tree;

import java.util.LinkedList;
import java.util.Queue;

public class NumOfTree {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            this.val = x;
        }
    }

    public int countNodes(TreeNode root) {
        if(root == null)return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        int size = 0;
        queue.add(root);
        size++;
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node.left!=null){
                size++;
                queue.add(node.left);
            }
            if(node.right!=null){
                size++;
                queue.add(node.right);
            }
        }
        return size;
    }

}
