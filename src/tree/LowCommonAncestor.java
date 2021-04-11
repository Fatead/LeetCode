package tree;

import java.util.ArrayList;
import java.util.List;

public class LowCommonAncestor {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    List<TreeNode> path = new ArrayList<>();

    private void dfs(TreeNode node,TreeNode target,List<TreeNode> list){
        if(node == null){
            path = list;
            return;
        }
        list.add(node);
        dfs(node.left,target,new ArrayList<>(list));
        dfs(node.right,target,new ArrayList<>(list));
    }

    private List<TreeNode> getPath(TreeNode root, TreeNode node){
        dfs(root,node,new ArrayList<>());
        return path;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> listP = getPath(root,p);
        List<TreeNode> listQ = getPath(root,q);
        int minLength = Math.min(listP.size(),listQ.size());
        for (int i = 0; i <minLength ; i++) {
            if(!listP.get(i).equals(listQ.get(i))){
                return listP.get(i-1);
            }
        }
        return listP.get(minLength-1);
    }

}