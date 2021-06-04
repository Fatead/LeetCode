package tree;

import java.util.LinkedList;
import java.util.Queue;

public class CousinsNode {


    public class TreeNode{
        public TreeNode left;
        public TreeNode right;
        public int val;
        public TreeNode(int value){
            this.val = value;
        }
    }

    public boolean isCousins(TreeNode root, int x, int y) {
        if(root == null)return false;
        int xLevel = 0, yLevel = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int currNum = 1;
        int nextNum = 0;
        int level = 1;
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node.left!=null && node.right!=null){
                if((node.left.val == x && node.right.val == y) || (node.left.val == y && node.right.val == x)){
                    return false;
                }
            }
            if(node.val == x){
                xLevel = level;
            }
            if(node.val == y){
                yLevel = level;
            }
            if(node.left!=null){
                queue.add(node.left);
                nextNum++;
            }
            if(node.right!=null){
                queue.add(node.right);
                nextNum++;
            }
            currNum--;
            if(currNum == 0){
                currNum = nextNum;
                nextNum = 0;
                level++;
            }
        }
        return xLevel == yLevel;
    }

}
