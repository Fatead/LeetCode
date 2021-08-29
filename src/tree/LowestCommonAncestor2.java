package tree;

public class LowestCommonAncestor2 {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == p || root == q || root ==null)return root;
        TreeNode leftNode = lowestCommonAncestor(root.left,p,q);
        TreeNode rightNode = lowestCommonAncestor(root.right,p,q);
        if(leftNode == null){
            return rightNode;
        }else if(rightNode == null){
            return leftNode;
        }
        return root;
    }

}
