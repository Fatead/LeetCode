package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class IsSymmetricTree {


    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    private boolean isSym(List<List<Integer>> lists){
        for (List<Integer> list : lists) {
            for (int i = 0; i < list.size()/2; i++) {
                if(!list.get(i).equals(list.get(list.size() - i - 1))){
                    return false;
                }
            }
        }
        return true;
    }


    public boolean isSymmetric(TreeNode root) {
        if(root==null)return false;
        List<List<Integer>> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int currNum = 1;
        int nextNum = 0;
        List<Integer> levelList = new ArrayList<>();
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node.left!=null){
                queue.add(node.left);
                levelList.add(node.left.val);
                nextNum++;
            }else {
                levelList.add(-1);
            }
            if(node.right!=null){
                queue.add(node.right);
                levelList.add(node.right.val);
                nextNum++;
            }else {
                levelList.add(-1);
            }
            currNum--;
            if(currNum<=0){
                list.add(new ArrayList<>(levelList));
                levelList = new ArrayList<>();
                currNum = nextNum;
                nextNum = 0;
            }
        }
        return isSym(list);
    }
}
