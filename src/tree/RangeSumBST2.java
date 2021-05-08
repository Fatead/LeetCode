package tree;

import java.util.ArrayList;
import java.util.List;

public class RangeSumBST2 {


    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }



    public int rangeSumBST(TreeNode root, int low, int high) {
        if(root == null)return 0;
        if(root.val<low){
            return rangeSumBST(root.right,low,high);
        }else if(root.val>high){
            return rangeSumBST(root.left,low,high);
        }
        return root.val + rangeSumBST(root.left,low,high) + rangeSumBST(root.right,low,high);
    }

}
