package tree;

public class InsertIntoMaxTree {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        TreeNode node = new TreeNode(val);
        if(root == null) return node;
        if(val> root.val){
            node.left = root;
            return node;
        }
        root.right = insertIntoMaxTree(root.right,val);
        return root;
    }

}
