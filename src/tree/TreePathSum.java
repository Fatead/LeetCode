package tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TreePathSum {

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    List<List<Integer>> allPathList = new ArrayList<>();

    private void getAllPath(TreeNode node,List<Integer> list){
        if(node == null){
            return;
        }
        list.add(node.val);
        if(node.left == null && node.right == null){
            allPathList.add(list);
            return;
        }
        getAllPath(node.left,new ArrayList<>(list));
        getAllPath(node.right,new ArrayList<>(list));
    }
    
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        getAllPath(root,new ArrayList<>());
        List<List<Integer>> result = new ArrayList<>();
        if(root == null)return result;
        for (List<Integer> list : allPathList) {
            int sum = 0;
            for (Integer integer : list) {
                sum += integer;
            }
            if(sum == target){
                result.add(list);
            }
        }
        return result;
    }

}
