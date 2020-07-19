package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrder {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null)return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int currCount = 1;
        int nextCount = 0;
        List<Integer> level = new ArrayList<>();
        while(!queue.isEmpty()){
            TreeNode treeNode = queue.poll();
            if(treeNode.left!= null){
                nextCount ++ ;
                queue.add(treeNode.left);
            }
            if(treeNode.right!=null){
                nextCount++;
                queue.add(treeNode.right);
            }
            level.add(treeNode.val);
            currCount--;
            if(currCount == 0){
                result.add(new ArrayList<>(level));
                level.clear();
                currCount = nextCount;
                nextCount = 0;
            }
        }
        return result;
    }
}
