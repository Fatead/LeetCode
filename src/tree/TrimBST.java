package tree;

public class TrimBST {

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    public TreeNode trimBST(TreeNode root, int low, int high) {
        if(root == null)return root;
        System.out.println(root.val);
        if(root.val>=low && root.val<=high){
            root.left = trimBST(root.left,low,high);
            root.right = trimBST(root.right,low,high);
            return root;
        }else {
            if(root.left == null)return trimBST(root.right,low,high);
            if(root.right == null)return trimBST(root.left,low,high);
            if(root.val<low){
                return trimBST(root.right,low,high);
            }else {
                return trimBST(root.left,low,high);
            }
        }
    }

}
