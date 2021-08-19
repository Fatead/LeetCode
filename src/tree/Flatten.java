package tree;
import java.util.ArrayList;
import java.util.List;

public class Flatten {

    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    List<TreeNode> list = new ArrayList<>();

    private void inOrder(TreeNode node){
        if(node == null)return;
        list.add(node);
        inOrder(node.left);
        inOrder(node.right);
    }

    public void flatten(TreeNode root) {
        inOrder(root);
        for (int i = 0; i <list.size()-1 ; i++) {
            list.get(i).left = null;
            list.get(i).right = list.get(i+1);
        }
    }

}