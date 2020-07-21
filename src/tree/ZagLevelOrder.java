package tree;

import java.util.*;

public class ZagLevelOrder {


    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> level = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if(root == null)return result;
        queue.add(root);
        int currCount = 1;
        int nextCount = 0;
        boolean fromLeft = true;
        while(!queue.isEmpty()){
            TreeNode treeNode = queue.poll();
            if(treeNode.left!=null){
                queue.add(treeNode.left);
                nextCount++;
            }
            if(treeNode.right!=null){
                queue.add(treeNode.right);
                nextCount++;
            }
            currCount--;
            level.add(treeNode.val);
            if(currCount == 0){
                currCount = nextCount;
                nextCount = 0;
                if(!fromLeft) {
                    Collections.reverse(level);
                }
                fromLeft = !fromLeft;
                result.add(new ArrayList<>(level));
                level.clear();
            }
        }
        return result;
    }

}
