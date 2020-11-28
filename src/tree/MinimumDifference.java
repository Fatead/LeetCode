package tree;

import java.util.ArrayList;
import java.util.List;

public class MinimumDifference {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            this.val = x;
        }
    }

    List<Integer> list = new ArrayList<>();

    public void inOrder(TreeNode node){
        if(node.left!=null)inOrder(node.left);
        list.add(node.val);
        if(node.right!=null)inOrder(node.right);
    }

    public int getMinimumDifference(TreeNode root) {
        inOrder(root);
        if(list.size()<=1)return 0;
        int result = Integer.MAX_VALUE;
        for(int i = 0;i<list.size()-1;i++){
            if(Math.abs(list.get(i) - list.get(i+1)) < result){
                result = Math.abs(list.get(i) - list.get(i+1));
            }
        }
        return result;
    }

    public static void main(String[] args) {

    }

}
