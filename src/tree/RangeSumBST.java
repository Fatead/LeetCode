package tree;

import java.util.ArrayList;
import java.util.List;

public class RangeSumBST {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }


    List<Integer> list = new ArrayList<>();


    private void postOrder(TreeNode node){
        if(node == null)return;
        postOrder(node.left);
        list.add(node.val);
        postOrder(node.right);
    }

    public int rangeSumBST(TreeNode root, int low, int high) {
        postOrder(root);
        int sum = 0;
        for (Integer integer : list) {
            if(integer>=low && integer<=high){
                sum += integer;
            }
        }
        return sum;
    }

    public static void main(String[] args) {

    }

}
