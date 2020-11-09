package tree;

import java.util.*;

public class FrequentTreeSum {

    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    Map<Integer,Integer> map = new HashMap<>();

    private int dfs(TreeNode node){
        if(node == null)return 0;
        return node.val + dfs(node.left) + dfs(node.right);
    }

    private void preOrder(TreeNode node){
        if(node == null)return;
        int value = dfs(node);
        map.put(value,map.getOrDefault(value,0) +1);
        preOrder(node.left);
        preOrder(node.right);
    }

    public int[] findFrequentTreeSum(TreeNode root) {
        preOrder(root);
        int maxCount = Integer.MIN_VALUE;
        for(Integer integer:map.keySet()){
            if(map.get(integer) > maxCount){
                maxCount = map.get(integer);
            }
        }
        List<Integer> resultList = new ArrayList<>();
        for(Integer integer:map.keySet()){
            if(map.get(integer) == maxCount){
                resultList.add(integer);
            }
        }
        int[] result = new int[resultList.size()];
        for(int i = 0;i<resultList.size();i++){
            result[i] = resultList.get(i);
        }
        return result;
    }


}
