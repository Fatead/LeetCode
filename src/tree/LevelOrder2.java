package tree;

import java.util.*;

public class LevelOrder2 {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null)return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int currCount = 1;
        int nextCount = 0;
        List<Integer> level = new ArrayList<>();
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
                result.add(new ArrayList<>(level));
                level.clear();
            }
        }
        Collections.reverse(result);
        return result;
    }
}
