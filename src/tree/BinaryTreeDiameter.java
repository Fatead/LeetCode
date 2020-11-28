package tree;

public class BinaryTreeDiameter {


    public static class TreeNode{
        public TreeNode left;
        public TreeNode right;
        public int val;
        public TreeNode(int value){
            this.val = value;
        }
        public TreeNode(int value,TreeNode left,TreeNode right){
            this.val = value;
            this.left = left;
            this.right = right;
        }
    }


    private int getHeight(TreeNode node){
        if(node == null)return 0;
        if(node.left == null && node.right == null)return 1;
        return Math.max(getHeight(node.left),getHeight(node.right)) + 1;
    }

    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null)return 0;
        return Math.max(Math.max(diameterOfBinaryTree(root.left),diameterOfBinaryTree(root.right)),getHeight(root.left) + getHeight(root.right));
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        root.left = node1;
        node1.left = node2;
        BinaryTreeDiameter binaryTreeDiameter = new BinaryTreeDiameter();
        //int result =  binaryTreeDiameter.diameterOfBinaryTree(root);
        //System.out.println(result);
        System.out.println(binaryTreeDiameter.getHeight(root));
    }

}
