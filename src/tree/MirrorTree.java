package tree;

public class MirrorTree {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            this.val = x;
        }
    }

    private void exchange(TreeNode node){
        if(node == null)return;
        TreeNode tempNode = node.left;
        node.left = node.right;
        node.right = tempNode;
        exchange(node.left);
        exchange(node.right);
    }

    public TreeNode mirrorTree(TreeNode root) {
        exchange(root);
        return root;
    }

}
