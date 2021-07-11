package tree;

import java.util.ArrayList;
import java.util.List;

public class SumRootToLeaf {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    private List<List<Integer>> allPaths = new ArrayList<>();

    private void dfs(TreeNode node,List<Integer> path){
        if(node == null)return;
        List<Integer> newPath = new ArrayList<>(path);
        newPath.add(node.val);
        if(node.left == null && node.right == null){
            allPaths.add(newPath);
            return;
        }
        dfs(node.left,newPath);
        dfs(node.right,newPath);
    }

    public int sumRootToLeaf(TreeNode root) {
        dfs(root,new ArrayList<>());
        int sum = 0;
        for (List<Integer> path : allPaths) {
            int num = 0;
            for (Integer integer : path) {
                num += integer;
                num *= 2;
            }
            num/=2;
            sum += num;
        }
        return sum;
    }

}