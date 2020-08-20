package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RightViewTree {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }


    List<List<Integer>> levelOrder = new ArrayList<>();

    public List<Integer> rightSideView(TreeNode root) {
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        int currLevel = 1;
        int nextLevel = 0;
        List<Integer> levelList = new ArrayList<>();
        while(!nodeQueue.isEmpty()){
            TreeNode node = nodeQueue.poll();
            if(node.left!=null){
                nodeQueue.add(node.left);
                nextLevel++;
            }
            if(node.right!=null){
                nodeQueue.add(node.right);
                nextLevel++;
            }
            currLevel--;
            if(currLevel == 0){
                currLevel = nextLevel;
                nextLevel = 0;
                levelOrder.add(new ArrayList<>(levelList));
                levelList.clear();
            }
        }
        List<Integer> resultList = new ArrayList<>();
        for(List<Integer> list:levelOrder){
            resultList.add(list.get(list.size()-1));
        }
        return resultList;
    }

    public static void main(String[] args) {

    }
}
