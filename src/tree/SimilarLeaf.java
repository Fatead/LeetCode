package tree;

import java.util.*;

public class SimilarLeaf {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    List<Integer> list = new ArrayList<>();

    private void dfs(TreeNode node){
        if(node==null)return;
        if(node.left==null&&node.right==null){
            list.add(node.val);
            return;
        }
        dfs(node.left);
        dfs(node.right);
    }


    private boolean sameList(List<Integer> list1,List<Integer> list2){
        if(list1.size()!=list2.size())return false;
        for (int i = 0; i <list1.size() ; i++) {
            if(!list1.get(i).equals(list2.get(i))){
                return false;
            }
        }
        return true;
    }


    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        dfs(root1);
        List<Integer> leaves1 = new ArrayList<>(list);
        list = new ArrayList<>();
        dfs(root2);
        return sameList(leaves1,list);
    }

}