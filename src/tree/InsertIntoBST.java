package tree;

public class InsertIntoBST {

    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }


    public TreeNode insertIntoBST2(TreeNode root, int val) {
        if(root == null)return new TreeNode(val);
        TreeNode node = root;
        while( node.left!=null || node.right!=null){
            if(node.val<val){
                if(node.right == null){
                    node.right = new TreeNode(val);
                    return root;
                }
                node = node.right;
            }else{
                if(node.left == null){
                    node.left = new TreeNode(val);
                    return root;
                }
                node = node.left;
            }
        }
        if(node.val>val){
            node.left = new TreeNode(val);
        }else {
            node.right = new TreeNode(val);
        }
        return root;
    }


    public TreeNode insertIntoBST(TreeNode root,int val){
        if(root == null)return new TreeNode(val);
        if(root.val>val){
            root.left = insertIntoBST(root.left,val);
        }else {
            root.right = insertIntoBST(root.right,val);
        }
        return root;
    }


}
