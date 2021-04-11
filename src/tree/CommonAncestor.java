package tree;

import java.util.ArrayList;
import java.util.List;

public class CommonAncestor {

    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }


    private List<TreeNode> getPath(TreeNode root,TreeNode target){
        List<TreeNode> list = new ArrayList<>();
        TreeNode pt = root;
        while (pt!=target){
            list.add(pt);
            if(target.val<pt.val){
                pt = pt.left;
            }else {
                pt = pt.right;
            }
        }
        return list;
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
