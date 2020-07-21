package tree;

import java.util.LinkedList;
import java.util.Queue;

public class MaxDepth {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            this.val = x;
        }
    }

    public int maxDepth(TreeNode root) {
        if(root == null)return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int currCount = 1;
        int nextCount = 0;
        int level = 0;
        while(!queue.isEmpty()){
            TreeNode treeNode = queue.poll();
            if(treeNode.left!=null){
                queue.add(treeNode.left);
                nextCount ++;
            }
            if(treeNode.right!=null){
                queue.add(treeNode.right);
                nextCount ++;
            }
            currCount --;
            if(currCount == 0){
                currCount = nextCount;
                level ++;
                nextCount  = 0;
            }
        }
        return level;
    }

    public int maxDepth2(TreeNode root){
        if(root == null) return 0;
        int left_height = maxDepth2(root.left);
        int right_height = maxDepth2(root.right);
        return Math.max(left_height,right_height) + 1;
    }
}
