package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelAvg {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    public List<Double> averageOfLevels(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Double> list = new ArrayList<>();
        int levelNum = 1;
        int currNum = 1;
        int nextNum = 0;
        double levelSum = 0;
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            System.out.println(node.val);
            levelSum += node.val;
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
                if(levelNum!=0){
                    list.add(levelSum/levelNum);
                }else {
                    list.add((double)0);
                }
                levelSum = 0;
                currNum = nextNum;
                nextNum = 0;
                levelNum = nextNum;
            }
        }
        return list;
    }

}
