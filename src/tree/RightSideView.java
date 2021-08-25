package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RightSideView {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<List<Integer>> levelList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int currNum = 1;
        int nextNum = 0;
        List<Integer> list = new ArrayList<>();
        if(root == null)return list;
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            list.add(node.val);
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
                levelList.add(list);
                list = new ArrayList<>();
            }
        }
        List<Integer> result = new ArrayList<>();
        for (List<Integer> integers : levelList) {
            result.add(integers.get(integers.size()-1));
        }
        return result;
    }

    List<Integer> rightList = new ArrayList<>();

    private void dfs(TreeNode node, int depth){
        if(node == null)return;
        if(rightList.size()<depth){
            rightList.add(node.val);
        }
        dfs(node.right,depth+1);
        dfs(node.left,depth+1);
    }

    public List<Integer> rightSideView2(TreeNode root){
        dfs(root,1);
        return rightList;
    }

}