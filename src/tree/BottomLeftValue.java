package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BottomLeftValue {

    public class TreeNode{
        public TreeNode left;
        public TreeNode right;
        public int val;
        public TreeNode(int value){
            this.val = value;
        }
        public TreeNode(int value,TreeNode left,TreeNode right){
            this.val = value;
            this.left = left;
            this.right = right;
        }
    }

    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> levelOrder = new ArrayList<>();
        queue.add(root);
        int currNum = 1;
        int nextNum = 0;
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            list.add(node.val);
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
                levelOrder.add(list);
                list = new ArrayList<>();
            }
        }
        return levelOrder.get(levelOrder.size()-1).get(0);
    }

}
