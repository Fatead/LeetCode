package tree;

import java.util.LinkedList;
import java.util.Queue;

public class AddRow {

    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if(d == 1){
            TreeNode node = new TreeNode(v);
            node.left = root;
            return node;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 1;
        int currNum = 1;
        int nextNum = 0;
        while(!queue.isEmpty() && level < d){
            TreeNode node = queue.poll();
            currNum--;
            if(node.left!=null){
                queue.add(node.left);
                nextNum++;
            }
            if(node.right!=null){
                queue.add(node.right);
                nextNum++;
            }
            if(currNum == 0){
                currNum = nextNum;
                nextNum = 0;
                level++;
            }
        }
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            TreeNode tempLeft = node.left;
            TreeNode addNodeLeft = new TreeNode(v);
            node.left = addNodeLeft;
            addNodeLeft.left = tempLeft;
            TreeNode tempRight = node.right;
            TreeNode addNodeRight = new TreeNode(v);
            node.right = addNodeRight;
            addNodeRight.right = tempRight;
        }
        return root;
    }

}
